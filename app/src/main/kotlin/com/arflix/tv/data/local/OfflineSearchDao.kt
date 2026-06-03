package com.arflix.tv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OfflineSearchDao {
    @Query("SELECT * FROM offline_search WHERE query = :query LIMIT 1")
    suspend fun get(query: String): OfflineSearchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: OfflineSearchEntity)

    @Query("DELETE FROM offline_search WHERE lastUpdatedMillis < :staleBefore")
    suspend fun deleteOlderThan(staleBefore: Long)

    @Query("SELECT COUNT(*) FROM offline_search")
    suspend fun count(): Int

    @Query("DELETE FROM offline_search")
    suspend fun clearAll()
}
