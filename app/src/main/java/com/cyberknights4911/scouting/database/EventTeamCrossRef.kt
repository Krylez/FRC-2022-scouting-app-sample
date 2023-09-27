package com.cyberknights4911.scouting.database

import androidx.room.Entity

@Entity(
    tableName = "event_team_crossref_table",
    primaryKeys = ["event_key", "team_key"]
)
data class EventTeamCrossRef(
    val event_key: String,
    val team_key: String
)
