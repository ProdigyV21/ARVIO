package com.arflix.tv.data.local

import com.arflix.tv.data.model.MediaType
import java.util.concurrent.TimeUnit

object CachePolicyManager {

    // Centralized TTL Configurations
    val MOVIE_TTL_MS = TimeUnit.DAYS.toMillis(7)      // 7 Days
    val TV_TTL_MS = TimeUnit.DAYS.toMillis(3)         // 3 Days
    val CAST_TTL_MS = TimeUnit.DAYS.toMillis(14)      // 14 Days
    val EPISODES_TTL_MS = TimeUnit.DAYS.toMillis(3)   // 3 Days (Same as TV details)
    val REVIEWS_TTL_MS = TimeUnit.DAYS.toMillis(7)    // 7 Days
    val COLLECTION_TTL_MS = TimeUnit.DAYS.toMillis(1) // 1 Day

    /**
     * Check if a cached media item is fresh based on its type-specific TTL
     */
    fun isMediaItemFresh(cachedItem: CachedMediaItem, forceRefresh: Boolean = false): Boolean {
        if (forceRefresh) return false
        val ttl = if (cachedItem.mediaType == MediaType.MOVIE.name) MOVIE_TTL_MS else TV_TTL_MS
        val age = System.currentTimeMillis() - cachedItem.updatedAt
        return age < ttl
    }

    /**
     * Check if cached cast/crew data is fresh
     */
    fun isCastFresh(updatedAt: Long, forceRefresh: Boolean = false): Boolean {
        if (forceRefresh) return false
        val age = System.currentTimeMillis() - updatedAt
        return age < CAST_TTL_MS
    }

    /**
     * Check if cached episodes data is fresh
     */
    fun isEpisodesFresh(updatedAt: Long, forceRefresh: Boolean = false): Boolean {
        if (forceRefresh) return false
        val age = System.currentTimeMillis() - updatedAt
        return age < EPISODES_TTL_MS
    }

    /**
     * Check if cached reviews are fresh
     */
    fun isReviewsFresh(updatedAt: Long, forceRefresh: Boolean = false): Boolean {
        if (forceRefresh) return false
        val age = System.currentTimeMillis() - updatedAt
        return age < REVIEWS_TTL_MS
    }

    /**
     * Check if cached collection/catalog items are fresh
     */
    fun isCollectionFresh(updatedAt: Long, forceRefresh: Boolean = false): Boolean {
        if (forceRefresh) return false
        val age = System.currentTimeMillis() - updatedAt
        return age < COLLECTION_TTL_MS
    }
}
