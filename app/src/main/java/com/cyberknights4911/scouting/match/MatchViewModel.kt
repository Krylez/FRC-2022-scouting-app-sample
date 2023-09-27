package com.cyberknights4911.scouting.match

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.BlueAllianceService
import com.cyberknights4911.scouting.database.EventTeamCrossRef
import com.cyberknights4911.scouting.database.Match
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import com.cyberknights4911.scouting.database.Team
import com.cyberknights4911.scouting.team.TeamJson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val blueAllianceService: BlueAllianceService,
    private val databaseDao: ScoutDatabaseDao,
    state: SavedStateHandle
) : ViewModel() {

    private val eventId = state.get<String>("eventId")!!
    private val matches = databaseDao.getAllMatchesForEvent(eventId)
    private val teams = databaseDao.getAllTeamsForEvent(eventId)

    val matchesWithTeams : LiveData<List<MatchWithTeams>> = MatchWithTeamsLiveData(matches, teams)

    init {
        fetchMatches()
        fetchTeams()
    }

    private fun fetchMatches() {
        blueAllianceService.eventMatchList(eventId)
            .enqueue(object: Callback<List<MatchJson>> {
                override fun onResponse(call: Call<List<MatchJson>>, response: Response<List<MatchJson>>) {
                    val matchList = response.body()
                    if (matchList == null) {
                        Log.e("MatchViewModel", "No response for event: $eventId")
                    } else {
                        viewModelScope.launch {
                            Log.d("MatchViewModel", "Inserting ${matchList.size} matches into database")
                            databaseDao.insertMatches(
                                matchList.map {
                                    Match(
                                        tba_key = it.key,
                                        eventKey = it.event_key,
                                        compLevel = it.comp_level,
                                        matchNumber = it.match_number,
                                        setNumber = it.set_number,
                                        blueAllianceKeys = it.alliances.blue.team_keys.joinToString(","),
                                        redAllianceKeys = it.alliances.red.team_keys.joinToString(",")
                                    )
                                }
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<List<MatchJson>>, t: Throwable) {
                    Log.e("MatchViewModel", "Failure", t)
                }
            })
    }

    private fun fetchTeams() {
        blueAllianceService.eventTeamList(eventId)
            .enqueue(object: Callback<List<TeamJson>> {
                override fun onResponse(call: Call<List<TeamJson>>, response: Response<List<TeamJson>>) {
                    val teams = response.body()
                    if (teams == null) {
                        Log.e("RPC", "No response for event: $eventId")
                    } else {
                        viewModelScope.launch {
                            Log.d("MatchViewModel", "Inserting ${teams.size} cross refs into database")
                            databaseDao.insertEventTeamCrossRefs(
                                teams.map {
                                    EventTeamCrossRef(
                                        event_key = eventId,
                                        team_key = it.key
                                    )
                                }
                            )
                            Log.d("MatchViewModel", "Inserting ${teams.size} teams into database")
                            databaseDao.insertTeams(
                                teams.map {
                                    Team(
                                        tba_key = it.key,
                                        name = it.name,
                                        nickname = it.nickname,
                                        city = it.city,
                                        teamNumber = it.team_number
                                    )
                                }
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<List<TeamJson>>, t: Throwable) {
                    Log.e("EventsAdapter", "Failure", t)
                }
            })
    }
}