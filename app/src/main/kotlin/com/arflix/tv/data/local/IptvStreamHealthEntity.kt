package com.arflix.tv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iptv_stream_health")
data class IptvStreamHealthEntity(
    @PrimaryKey val channelId: String,
    val httpStatusCode: Int? = null,
    val latencyMs: Long? = null,
    val consecutiveFailureCount: Int = 0,
    val lastSuccessfulAtMs: Long? = null,
    val lastCheckedAtMs: Long = 0L
)
