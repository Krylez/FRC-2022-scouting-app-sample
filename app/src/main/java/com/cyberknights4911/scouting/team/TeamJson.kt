package com.cyberknights4911.scouting.team

import com.squareup.moshi.JsonClass

/*
"key": "string",
"team_number": 0,
"nickname": "string",
"name": "string",
"city": "string",
"state_prov": "string",
"country": "string"
*/
@JsonClass(generateAdapter = true)
data class TeamJson(
    val key: String,
    val team_number: Int,
    val city: String,
    val name: String,
    val nickname: String,
)
