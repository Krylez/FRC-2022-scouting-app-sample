package com.cyberknights4911.scouting.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val REPORT = "report"
private const val AUTHORITY = "com.cyberknights4911.scouting.provider"
private const val CODE_REPORTS_DIR = 1
private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI(AUTHORITY, REPORT, CODE_REPORTS_DIR)
}

class ScoutContentProvider: ContentProvider() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderEntryPoint {
        fun database(): ScoutDatabaseDao
    }

    lateinit var database: ScoutDatabaseDao
    override fun onCreate(): Boolean {
        setupDatabase()
        return true
    }
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (sUriMatcher.match(uri)) {
            CODE_REPORTS_DIR -> {
                context?.let { context ->
                    setupDatabase()
                    return database.geAllReportsCursor().apply {
                        setNotificationUri(context.contentResolver, uri)
                    }
                }
                Log.e("ScoutContentProvider", "No context")
                return null
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (sUriMatcher.match(uri)) {
            CODE_REPORTS_DIR -> "vnd.android.cursor.dir/$AUTHORITY.$REPORT"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        throw IllegalArgumentException("Inserts not allowed")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        throw IllegalArgumentException("Deletes not allowed")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        throw IllegalArgumentException("Updates not allowed")
    }

    private fun setupDatabase() {
        context?.let { context ->
            if (!this::database.isInitialized) {
                database = EntryPoints.get(context, ProviderEntryPoint::class.java).database()
            }
        }
    }
}
