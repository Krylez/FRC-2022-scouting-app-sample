package com.cyberknights4911.scouting

import com.cyberknights4911.scouting.event.EventJson
import com.cyberknights4911.scouting.match.MatchJson
import com.cyberknights4911.scouting.team.MediaJson
import com.cyberknights4911.scouting.team.TeamJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlueAllianceService {
    /**
     * Gets a list of all events for a district key.
     */
    @GET("district/{district_key}/events/simple")
    fun districteventList(@Path("district_key") district_key: String): Call<List<EventJson>>

    /**
     * Gets a list of all matches for an event key.
     */
    @GET("event/{event_key}/matches/simple")
    fun eventMatchList(@Path("event_key") event_key: String): Call<List<MatchJson>>

    /**
     * Gets a list of all teams for an event key.
     */
    @GET("event/{event_key}/teams/simple")
    fun eventTeamList(@Path("event_key") event_key: String): Call<List<TeamJson>>

    /**
     * Gets a list of all media for an event key.
     */
    @GET("team/{team_key}/media/2023")
    fun teamMediaList(@Path("team_key") team_key: String): Call<List<MediaJson>>
}
