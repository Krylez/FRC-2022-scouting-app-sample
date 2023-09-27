package com.cyberknights4911.scouting.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.BlueAllianceService
import com.cyberknights4911.scouting.database.Media
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import com.cyberknights4911.scouting.database.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val databaseDao: ScoutDatabaseDao,
    private val blueAllianceService: BlueAllianceService,
    state: SavedStateHandle
) : ViewModel() {
    val matchId = state.get<String>("matchId")!!
    private val match = databaseDao.getMatchForKey(matchId)

    val redTeams: LiveData<List<Team>> = match.switchMap { match ->
        databaseDao.getTeamsForKeys(match.redAllianceKeys.split(","))
    }

    val blueTeams: LiveData<List<Team>> = match.switchMap { match ->
        databaseDao.getTeamsForKeys(match.blueAllianceKeys.split(","))
    }

    private val thumbnails: LiveData<List<Media>> = match.switchMap { match ->
        val keys = match.redAllianceKeys.split(",") + match.blueAllianceKeys.split(",")
        fetch(keys)
        databaseDao.getThumbnailsForTeams(keys)
    }

    val thumbnailMap = thumbnails.map { media ->
        media.associate { it.teamKey to it.base64Image }
    }

    private fun fetch(teamKeys: List<String>) = teamKeys.forEach { fetch(it) }

    private fun fetch(teamKey: String) {
        blueAllianceService.teamMediaList(teamKey)
            .enqueue(object : Callback<List<MediaJson>> {
                override fun onResponse(
                    call: Call<List<MediaJson>>,
                    response: Response<List<MediaJson>>
                ) {
                    val mediaList = response.body()
                    if (mediaList.isNullOrEmpty()) {
                        Log.e("TeamViewModel", "No response for team: $teamKey")
                    } else {
                        viewModelScope.launch {
                            Log.d("TeamViewModel", "Inserting ${mediaList.size} medias into database")
                            databaseDao.insertMedia(
                                mediaList.map {
                                    Media(
                                        teamKey = teamKey,
                                        type = it.type,
                                        base64Image = it.details.base64Image,
                                        directUrl = it.direct_url,
                                        viewUrl = it.view_url
                                    )
                                }
                            )
                        }

                    }
                }

                override fun onFailure(call: Call<List<MediaJson>>, t: Throwable) {
                    Log.e("TeamViewModel", "Failure", t)
                }
            })
    }
}