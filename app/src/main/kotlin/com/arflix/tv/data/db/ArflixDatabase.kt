package com.arflix.tv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DownloadEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArflixDatabase : RoomDatabase() {
    abstract fun downloadDao(): DownloadDao

    companion object {
        const val DATABASE_NAME = "arflix_db"
    }
}
