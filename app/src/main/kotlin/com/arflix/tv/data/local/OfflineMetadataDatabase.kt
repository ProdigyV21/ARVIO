package com.arflix.tv.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [OfflineMetadataEntity::class, OfflineSearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OfflineMetadataDatabase : RoomDatabase() {
    abstract fun offlineMetadataDao(): OfflineMetadataDao
    abstract fun offlineSearchDao(): OfflineSearchDao
}
