package com.arflix.tv.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [IptvStreamHealthEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IptvHealthDatabase : RoomDatabase() {
    abstract fun streamHealthDao(): IptvStreamHealthDao
}
