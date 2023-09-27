package com.cyberknights4911.scouting.match

import com.cyberknights4911.scouting.database.Team

data class MatchWithTeams(
    val key: String,
    val compLevel: String,
    val matchNumber: Int,
    val setNumber: Int,
    val matchTbaKey: String,
    val blueTeams: List<Team>,
    val redTeams: List<Team>
)
