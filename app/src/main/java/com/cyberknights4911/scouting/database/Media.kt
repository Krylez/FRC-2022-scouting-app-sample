package com.cyberknights4911.scouting.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Media(
    @PrimaryKey(autoGenerate = true)
    var mediaId: Long = 0L,

    @ColumnInfo(name = "team_key")
    var teamKey: String = "",

    @ColumnInfo(name = "base64Image")
    var base64Image: String = "",

    @ColumnInfo(name = "type")
    var type: String = "",

    @ColumnInfo(name = "direct_url")
    var directUrl: String = "",

    @ColumnInfo(name = "foreign_key")
    var foreignKey: String = "",

    @ColumnInfo(name = "preferred")
    var preferred: String = "",

    @ColumnInfo(name = "view_url")
    var viewUrl: String = "",
) {
    fun decode(): Bitmap {
        val imageBytes: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}
