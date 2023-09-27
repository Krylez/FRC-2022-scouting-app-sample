package com.cyberknights4911.scouting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "red_alliance_member_table",
    foreignKeys = [
        ForeignKey(entity = Match::class,
            parentColumns = arrayOf("tbaKey"),
            childColumns = arrayOf("tbaMatchKey")
        ),
        ForeignKey(entity = Team::class,
            parentColumns = arrayOf("tbaKey"),
            childColumns = arrayOf("tbaTeamKey")
        )
    ]
)
data class RedAllianceMember(
    @PrimaryKey(autoGenerate = true)
    var allianceId: Long = 0L,

    @ColumnInfo(name = "match_key")
    var tbaMatchKey: String = "",

    @ColumnInfo(name = "team_key")
    var tbaTeamKey: String = ""
)
