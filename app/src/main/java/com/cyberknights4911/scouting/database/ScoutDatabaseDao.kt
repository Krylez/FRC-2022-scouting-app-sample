package com.cyberknights4911.scouting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ScoutDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<Team>)

    @Query("SELECT * from team_table")
    fun getAllTeams(): LiveData<List<Team>>

//    @Query("SELECT * from team_table")
//    fun getAllTeamsForEvent(eventKey: String): LiveData<List<Team>>

    @Query("SELECT * from team_table WHERE tba_key = :key")
    fun getTeamByTbaKey(key: String): LiveData<Team>

    @Query("DELETE FROM team_table")
    suspend fun clearTeams()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<Event>)

    @Query("SELECT * from event_table")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("SELECT * from event_table WHERE tba_key = :key")
    fun getEventByTbaKey(key: String): LiveData<Event>

//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEventWithTeams(teams: List<EventWithTeams>)
//
//    @Transaction
//    @Query("SELECT * from event_table WHERE tba_key = :key")
//    fun getEventWithTeams(key: String): LiveData<EventWithTeams>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<Match>)

    @Query("SELECT * from match_table WHERE tba_key = :key")
    fun getMatchForKey(key: String): LiveData<Match>

    @Query("SELECT * from match_table WHERE event_key = :key")
    fun getAllMatchesForEvent(key: String): LiveData<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<Media>)

    @Query("SELECT * from media_table WHERE team_key = :teamKey")
    fun getAllMediaForTeam(teamKey: String): LiveData<List<Media>>

    @Query("SELECT * from media_table WHERE team_key IN (:teamKeys) AND type = 'avatar'")
    fun getThumbnailsForTeams(teamKeys: List<String>): LiveData<List<Media>>

    @Query("SELECT * from team_table WHERE tba_key IN (:keys)")
    fun getTeamsForKeys(keys: List<String>): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventTeamCrossRefs(crossRefs: List<EventTeamCrossRef>)

    @Transaction
    @Query("SELECT * from team_table WHERE tba_key IN (SELECT team_key from event_team_crossref_table WHERE event_key = :eventKey)")
    fun getAllTeamsForEvent(eventKey: String): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: Report): Long

    @Transaction
    @Query("SELECT * FROM report_table WHERE reportId = :reportId")
    fun getReport(reportId: Long): LiveData<Report>
}
