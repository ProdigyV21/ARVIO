package com.arflix.tv.data.api

data class TrailerPlaybackSource(
    val videoUrl: String,
    val audioUrl: String? = null,
    val fallbackVideoUrls: List<String> = emptyList(),
    val height: Int? = null,
    val bandwidth: Long? = null
)
