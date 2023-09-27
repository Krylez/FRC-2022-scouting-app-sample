package com.cyberknights4911.scouting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class Match(
    @PrimaryKey
    val tba_key: String,

    @ColumnInfo(name = "event_key")
    var eventKey: String = "",

    @ColumnInfo(name = "comp_level")
    var compLevel: String = "",

    @ColumnInfo(name = "match_number")
    var matchNumber: Int = -1,

    @ColumnInfo(name = "set_number")
    var setNumber: Int = -1,

    @ColumnInfo(name = "blue_alliance")
    var blueAllianceKeys: String = "",

    @ColumnInfo(name = "red_alliance")
    var redAllianceKeys: String = "",
)
