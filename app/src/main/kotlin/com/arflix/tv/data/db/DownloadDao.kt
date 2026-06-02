package com.arflix.tv.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDao {

    @Query("SELECT * FROM downloads ORDER BY created_at DESC")
    fun observeAll(): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloads WHERE tmdb_id = :tmdbId ORDER BY season, episode")
    fun observeByTmdbId(tmdbId: Int): Flow<List<DownloadEntity>>

    @Query(
        "SELECT * FROM downloads WHERE tmdb_id = :tmdbId AND media_type = :mediaType " +
            "AND season IS :season AND episode IS :episode LIMIT 1"
    )
    suspend fun findDownload(
        tmdbId: Int,
        mediaType: String,
        season: Int?,
        episode: Int?
    ): DownloadEntity?

    @Query("SELECT * FROM downloads WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): DownloadEntity?

    @Query("SELECT * FROM downloads WHERE tmdb_id = :tmdbId")
    suspend fun getAllByTmdbId(tmdbId: Int): List<DownloadEntity>

    @Query("SELECT * FROM downloads WHERE local_uri = :localUri LIMIT 1")
    suspend fun findByLocalUri(localUri: String): DownloadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DownloadEntity): Long

    @Update
    suspend fun update(entity: DownloadEntity)

    @Delete
    suspend fun delete(entity: DownloadEntity)

    @Query("UPDATE downloads SET progress = :progress, downloaded_bytes = :downloadedBytes WHERE id = :id")
    suspend fun updateProgress(id: Long, progress: Int, downloadedBytes: Long)

    @Query(
        "UPDATE downloads SET status = :status, local_uri = :localUri, file_size = :fileSize WHERE id = :id"
    )
    suspend fun markCompleted(id: Long, status: String, localUri: String, fileSize: Long)

    @Query("UPDATE downloads SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Long, status: String)

    @Query(
        "UPDATE downloads SET subtitle_local_uri = :subtitleLocalUri, subtitle_lang = :lang WHERE id = :id"
    )
    suspend fun updateSubtitle(id: Long, subtitleLocalUri: String, lang: String)
}
