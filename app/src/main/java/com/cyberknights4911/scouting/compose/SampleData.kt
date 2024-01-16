package com.cyberknights4911.scouting.compose

import com.cyberknights4911.scouting.database.Event
import com.cyberknights4911.scouting.database.Team
import com.cyberknights4911.scouting.match.MatchWithTeams
import com.cyberknights4911.scouting.team.TeamWithLogo

class SampleData {
    companion object {
        val pnwEvent = Event(
            tba_key = "noId",
            name = "PNW FRC Event with and extremely long name because I want it to exceed the available space",
            city = "Seattle",
            startDate = "June 10, 2021"
        )
        val cyberKnights = Team(
            tba_key = "frc4911",
            name = "The CyberKnights of King's Senior High School",
            nickname = "CyberKnights",
            city = "Seattle",
            teamNumber = 4911
        )
        val matchWithTeams = MatchWithTeams(
            key = "matchId",
            compLevel = "qm",
            matchNumber = 4,
            setNumber = 1,
            matchTbaKey = "matchTbaId",
            blueTeams = List(3) { cyberKnights },
            redTeams = List(3) { cyberKnights }
        )
        val teamWithLogo = TeamWithLogo(
            team = cyberKnights,
            logo = "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"
        )
    }
}