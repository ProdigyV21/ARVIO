package com.arflix.tv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IptvStreamHealthDao {
    @Query("SELECT * FROM iptv_stream_health")
    fun observeAll(): Flow<List<IptvStreamHealthEntity>>

    @Query("SELECT * FROM iptv_stream_health WHERE channelId IN (:channelIds)")
    suspend fun loadByChannelIds(channelIds: List<String>): List<IptvStreamHealthEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: IptvStreamHealthEntity)

    @Query("DELETE FROM iptv_stream_health WHERE channelId NOT IN (:channelIds)")
    suspend fun deleteStaleHealthEntries(channelIds: List<String>)

    @Query("DELETE FROM iptv_stream_health")
    suspend fun clearAll()
}
