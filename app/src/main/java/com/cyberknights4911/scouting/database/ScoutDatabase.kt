package com.cyberknights4911.scouting.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Event::class,
        EventTeamCrossRef::class,
        Media::class,
        Match::class,
        Report::class,
        Team::class,
    ],
    version = 8,
    exportSchema = false
)
abstract class ScoutDatabase : RoomDatabase() {
    abstract val scoutDatabaseDao: ScoutDatabaseDao
}
