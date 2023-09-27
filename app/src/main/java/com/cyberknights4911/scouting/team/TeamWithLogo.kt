package com.cyberknights4911.scouting.team

import com.cyberknights4911.scouting.database.Team

data class TeamWithLogo (
    val team: Team,
    val logo: String?
)