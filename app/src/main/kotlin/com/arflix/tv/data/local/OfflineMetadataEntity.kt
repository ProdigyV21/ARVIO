package com.arflix.tv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offline_metadata")
data class OfflineMetadataEntity(
    @PrimaryKey val key: String,
    val mediaType: String,
    val mediaId: Int,
    val recordKind: String,
    val payloadJson: String,
    val lastUpdatedMillis: Long
)
