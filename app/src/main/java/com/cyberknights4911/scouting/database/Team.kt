package com.cyberknights4911.scouting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey
    val tba_key: String,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "nickname")
    val nickname: String = "",

    @ColumnInfo(name = "city")
    var city: String = "",

    @ColumnInfo(name = "team_number")
    val teamNumber: Int = 0,
)
