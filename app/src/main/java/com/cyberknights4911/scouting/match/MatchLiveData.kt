package com.cyberknights4911.scouting.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cyberknights4911.scouting.database.Match
import com.cyberknights4911.scouting.database.Team

class MatchWithTeamsLiveData(
    matches: LiveData<List<Match>>,
    teams: LiveData<List<Team>>,
    private var matchList: List<Match> = listOf(),
    private var teamMap: Map<String, Team> = mapOf()
) : MediatorLiveData<List<MatchWithTeams>>() {
    init {
        addSource(matches) {
            matchList = it

            // if teams are already fetched, merge everything
            if (teamMap.isNotEmpty()) {
                merge()
            }
        }
        addSource(teams) { teamsList ->
            teamMap = teamsList.associateBy { it.tba_key }

            // if matches are already fetched, merge everything
            if (matchList.isNotEmpty()) {
                merge()
            }
        }
    }

    private fun merge() {
        value = matchList.map { match ->
            MatchWithTeams(
                key = match.tba_key,
                compLevel = match.compLevel,
                matchNumber = match.matchNumber,
                matchTbaKey = match.tba_key,
                blueTeams = match.blueAllianceKeys.split(",").mapNotNull {
                    teamMap[it]
                },
                redTeams = match.redAllianceKeys.split(",").mapNotNull {
                    teamMap[it]
                }
            )
        }
    }
}