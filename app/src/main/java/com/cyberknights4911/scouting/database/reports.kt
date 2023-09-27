package com.cyberknights4911.scouting.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class Report(
    @PrimaryKey(autoGenerate = true)
    var reportId: Long = 0L,
    var matchId: String = "",
    var teamId: String = "",

    var startPosition: String = "",
    var startPiece: String = "",

    var collectedConesAuto: Int = 0,
    var collectedCubesAuto: Int = 0,
    var droppedPiecesAuto: Int = 0,
    var scoreHighAuto: Int = 0,
    var scoreMidAuto: Int = 0,
    var scoreLowAuto: Int = 0,
    var balanceAuto: String = "",

    var collectedConesTele: Int = 0,
    var collectedCubesTele: Int = 0,
    var droppedPiecesTele: Int = 0,
    var scoreHighTele: Int = 0,
    var scoreMidTele: Int = 0,
    var scoreLowTele: Int = 0,
    var balanceTele: String = ""
)
