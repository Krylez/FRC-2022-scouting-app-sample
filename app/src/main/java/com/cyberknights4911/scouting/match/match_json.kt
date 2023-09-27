package com.cyberknights4911.scouting.match

import com.squareup.moshi.JsonClass

/*
  {
    "actual_time": 1678062001,
    "alliances": {
      "blue": {
        "dq_team_keys": [],
        "score": 111,
        "surrogate_team_keys": [],
        "team_keys": [
          "frc4131",
          "frc4512",
          "frc7627"
        ]
      },
      "red": {
        "dq_team_keys": [],
        "score": 154,
        "surrogate_team_keys": [],
        "team_keys": [
          "frc2930",
          "frc2910",
          "frc1778"
        ]
      }
    },
    "comp_level": "f",
    "event_key": "2023wasno",
    "key": "2023wasno_f1m1",
    "match_number": 1,
    "predicted_time": 1678062066,
    "set_number": 1,
    "time": 1678060380,
    "winning_alliance": "red"
  },
 */
@JsonClass(generateAdapter = true)
data class MatchJson (
    val event_key: String,
    val comp_level: String,
    val key: String,
    val match_number: Int,
    val set_number: Int,
    val predicted_time: String,
    val time: String,
    val actual_time: String,
    val alliances: AlliancesJson
)

@JsonClass(generateAdapter = true)
data class AlliancesJson(
    val blue: AllianceJson,
    val red: AllianceJson
)

@JsonClass(generateAdapter = true)
data class AllianceJson(
    val team_keys: List<String>
)