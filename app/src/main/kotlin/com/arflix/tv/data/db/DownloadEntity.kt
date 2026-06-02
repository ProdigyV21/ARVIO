package com.arflix.tv.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

enum class DownloadStatus { QUEUED, DOWNLOADING, PAUSED, COMPLETED, FAILED }

@Entity(
    tableName = "downloads",
    indices = [Index(value = ["tmdb_id", "media_type", "season", "episode"], unique = true)]
)
data class DownloadEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "tmdb_id") val tmdbId: Int,
    @ColumnInfo(name = "media_type") val mediaType: String,
    val season: Int? = null,
    val episode: Int? = null,
    val title: String,
    @ColumnInfo(name = "episode_title") val episodeTitle: String? = null,
    @ColumnInfo(name = "poster_path") val posterPath: String? = null,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = null,
    @ColumnInfo(name = "local_uri") val localUri: String? = null,
    @ColumnInfo(name = "stream_url") val streamUrl: String,
    @ColumnInfo(name = "addon_id") val addonId: String = "",
    @ColumnInfo(name = "addon_name") val addonName: String = "",
    val quality: String = "",
    @ColumnInfo(name = "file_size") val fileSize: Long = 0L,
    @ColumnInfo(name = "downloaded_bytes") val downloadedBytes: Long = 0L,
    val status: String = DownloadStatus.QUEUED.name,
    val progress: Int = 0,
    @ColumnInfo(name = "worker_id") val workerId: String? = null,
    @ColumnInfo(name = "subtitle_url") val subtitleUrl: String? = null,
    @ColumnInfo(name = "subtitle_local_uri") val subtitleLocalUri: String? = null,
    @ColumnInfo(name = "subtitle_lang") val subtitleLang: String? = null,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    // JSON-serialized Map<String,String> of stream-specific request headers (Referer, Authorization, etc.)
    @ColumnInfo(name = "headers") val headers: String? = null
)
