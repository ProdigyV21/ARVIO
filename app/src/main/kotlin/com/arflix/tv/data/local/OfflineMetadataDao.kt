package com.arflix.tv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OfflineMetadataDao {
    @Query("SELECT * FROM offline_metadata WHERE mediaType = :mediaType AND mediaId = :mediaId AND recordKind = :recordKind LIMIT 1")
    suspend fun get(mediaType: String, mediaId: Int, recordKind: String): OfflineMetadataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: OfflineMetadataEntity)

    @Query("DELETE FROM offline_metadata WHERE key = :key")
    suspend fun delete(key: String)

    @Query("SELECT * FROM offline_metadata WHERE recordKind = :recordKind AND lastUpdatedMillis < :staleBefore LIMIT :limit")
    suspend fun getStaleEntities(recordKind: String, staleBefore: Long, limit: Int): List<OfflineMetadataEntity>

    @Query("SELECT COUNT(*) FROM offline_metadata")
    suspend fun count(): Int

    @Query("DELETE FROM offline_metadata")
    suspend fun clearAll()
}
