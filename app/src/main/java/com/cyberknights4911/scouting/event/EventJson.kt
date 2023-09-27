package com.cyberknights4911.scouting.event

import com.squareup.moshi.JsonClass

/*
  {
    "city": "Oregon City",
    "country": "USA",
    "district": {
      "abbreviation": "pnw",
      "display_name": "Pacific Northwest",
      "key": "2023pnw",
      "year": 2023
    },
    "end_date": "2023-03-04",
    "event_code": "orore",
    "event_type": 1,
    "key": "2023orore",
    "name": "PNW District Clackamas Academy Event",
    "start_date": "2023-03-02",
    "state_prov": "OR",
    "year": 2023
  }
 */
@JsonClass(generateAdapter = true)
data class EventJson(
    val key: String,
    val city: String,
    val name: String,
    val start_date: String
)