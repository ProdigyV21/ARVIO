package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbTvSeason
import com.arflix.tv.data.api.TmdbVideo
import java.util.Locale

object TrailerResolver {

    fun selectBestTrailerKey(
        videos: List<TmdbVideo>,
        preferredLanguage: String?,
        includeFallbackTypes: Boolean = true
    ): String? {
        return videos
            .asSequence()
            .filter { it.key.isNotBlank() && it.site.equals("YouTube", ignoreCase = true) }
            .filter { includeFallbackTypes || it.isTrailerLikeType() }
            .maxWithOrNull(compareBy<TmdbVideo> { score(it, preferredLanguage) }
                .thenBy { it.publishedAt.orEmpty() }
                .thenBy { it.key })
            ?.key
            ?.trim()
    }

    fun seasonFallbackOrder(seasons: List<TmdbTvSeason>): List<Int> {
        val regular = seasons
            .filter { it.seasonNumber > 0 && it.episodeCount > 0 }
            .map { it.seasonNumber }
            .distinct()
            .sortedDescending()
        val specials = seasons
            .filter { it.seasonNumber == 0 && it.episodeCount > 0 }
            .map { it.seasonNumber }
            .distinct()
        return regular + specials
    }

    private fun score(video: TmdbVideo, preferredLanguage: String?): Int {
        return typeScore(video.type) +
            officialScore(video.official) +
            languageScore(video.iso6391, preferredLanguage) +
            nameScore(video.name) +
            sizeScore(video.size)
    }

    private fun typeScore(type: String): Int {
        return when (type.trim().lowercase(Locale.US)) {
            "trailer" -> 10_000
            "teaser" -> 8_000
            "opening credits" -> 6_000
            "featurette" -> 5_000
            "clip" -> 4_000
            else -> 1_000
        }
    }

    private fun TmdbVideo.isTrailerLikeType(): Boolean {
        return when (type.trim().lowercase(Locale.US)) {
            "trailer", "teaser" -> true
            else -> false
        }
    }

    private fun officialScore(official: Boolean): Int {
        return if (official) 1_000 else 0
    }

    private fun languageScore(language: String?, preferredLanguage: String?): Int {
        val normalized = normalizeLanguage(language)
        val preferred = normalizeLanguage(preferredLanguage)
        return when {
            normalized != null && preferred != null && normalized == preferred -> 700
            normalized == "en" -> 500
            normalized == null -> 250
            else -> 0
        }
    }

    private fun nameScore(name: String): Int {
        val normalized = name.lowercase(Locale.US)
        val weak = listOf(
            "behind the scenes",
            "bloopers",
            "interview",
            "recap",
            "promo",
            "featurette"
        ).any { it in normalized }
        if (weak) return -600

        return when {
            "official trailer" in normalized -> 350
            "main trailer" in normalized -> 300
            "final trailer" in normalized -> 280
            "trailer" in normalized -> 220
            "teaser" in normalized -> 120
            else -> 0
        }
    }

    private fun sizeScore(size: Int): Int {
        return size.coerceIn(0, 2160) / 10
    }

    private fun normalizeLanguage(language: String?): String? {
        val normalized = language
            ?.trim()
            ?.replace('_', '-')
            ?.substringBefore('-')
            ?.lowercase(Locale.US)
        return normalized?.takeIf { it.isNotBlank() }
    }
}
