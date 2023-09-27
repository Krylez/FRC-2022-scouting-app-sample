package com.cyberknights4911.scouting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey
    val tba_key: String,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "city")
    var city: String = "",

    @ColumnInfo(name = "start_date")
    var startDate: String = ""
)
