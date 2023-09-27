package com.cyberknights4911.scouting.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesScoutDatabaseDao(@ApplicationContext context: Context): ScoutDatabaseDao {
        return Room.databaseBuilder(
            context,
            ScoutDatabase::class.java,
            "scout_database"
        )
            // Wipes and rebuilds instead of migrating
            .fallbackToDestructiveMigration()
            .build().scoutDatabaseDao
    }
}