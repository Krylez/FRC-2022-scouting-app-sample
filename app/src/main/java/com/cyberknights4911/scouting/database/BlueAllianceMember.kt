package com.cyberknights4911.scouting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "blue_alliance_member_table",
    foreignKeys = [
        ForeignKey(entity = Match::class,
            parentColumns = arrayOf("tbaKey"),
            childColumns = arrayOf("tbaMatchKey")
        )
    ]
)
data class BlueAllianceMember(
    @PrimaryKey(autoGenerate = true)
    var allianceId: Long = 0L,

    @ColumnInfo(name = "match_key")
    var tbaMatchKey: String = ""
)
