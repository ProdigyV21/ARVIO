package com.arflix.tv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offline_search")
data class OfflineSearchEntity(
    @PrimaryKey val query: String,
    val payloadJson: String,
    val lastUpdatedMillis: Long
)
