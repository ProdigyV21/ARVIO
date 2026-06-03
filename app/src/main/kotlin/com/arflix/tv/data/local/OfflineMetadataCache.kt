package com.arflix.tv.data.local

import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineMetadataCache @Inject constructor(
    private val database: OfflineMetadataDatabase
) {

    companion object {
        const val RECORD_KIND_DETAILS = "details"
        const val RECORD_KIND_CAST = "cast"
        const val RECORD_KIND_SEARCH = "search"

        private val gson = Gson()
        private val mediaItemListType = object : TypeToken<List<MediaItem>>() {}.type
        private val castMemberListType = object : TypeToken<List<CastMember>>() {}.type
    }

    private fun metadataKey(mediaType: MediaType, mediaId: Int, kind: String): String {
        return "${mediaType.name}_$mediaId_$kind"
    }

    private fun normalizedSearchQuery(query: String): String {
        return URLEncoder.encode(query.trim().lowercase(Locale.ROOT), StandardCharsets.UTF_8.toString())
    }

    suspend fun getCachedMediaDetails(mediaType: MediaType, mediaId: Int, maxAgeMs: Long): MediaItem? {
        val entity = database.offlineMetadataDao().get(
            mediaType.name,
            mediaId,
            RECORD_KIND_DETAILS
        ) ?: return null
        return if (System.currentTimeMillis() - entity.lastUpdatedMillis <= maxAgeMs) {
            gson.fromJson(entity.payloadJson, MediaItem::class.java)
        } else {
            null
        }
    }

    suspend fun getCachedMediaDetailsStale(mediaType: MediaType, mediaId: Int): MediaItem? {
        val entity = database.offlineMetadataDao().get(
            mediaType.name,
            mediaId,
            RECORD_KIND_DETAILS
        ) ?: return null
        return gson.fromJson(entity.payloadJson, MediaItem::class.java)
    }

    suspend fun putCachedMediaDetails(mediaType: MediaType, mediaId: Int, item: MediaItem) {
        val entity = OfflineMetadataEntity(
            key = metadataKey(mediaType, mediaId, RECORD_KIND_DETAILS),
            mediaType = mediaType.name,
            mediaId = mediaId,
            recordKind = RECORD_KIND_DETAILS,
            payloadJson = gson.toJson(item),
            lastUpdatedMillis = System.currentTimeMillis()
        )
        database.offlineMetadataDao().upsert(entity)
    }

    suspend fun getCachedCast(mediaType: MediaType, mediaId: Int, maxAgeMs: Long): List<CastMember>? {
        val entity = database.offlineMetadataDao().get(
            mediaType.name,
            mediaId,
            RECORD_KIND_CAST
        ) ?: return null
        return if (System.currentTimeMillis() - entity.lastUpdatedMillis <= maxAgeMs) {
            gson.fromJson(entity.payloadJson, castMemberListType)
        } else {
            null
        }
    }

    suspend fun getCachedCastStale(mediaType: MediaType, mediaId: Int): List<CastMember>? {
        val entity = database.offlineMetadataDao().get(
            mediaType.name,
            mediaId,
            RECORD_KIND_CAST
        ) ?: return null
        return gson.fromJson(entity.payloadJson, castMemberListType)
    }

    suspend fun putCachedCast(mediaType: MediaType, mediaId: Int, cast: List<CastMember>) {
        val entity = OfflineMetadataEntity(
            key = metadataKey(mediaType, mediaId, RECORD_KIND_CAST),
            mediaType = mediaType.name,
            mediaId = mediaId,
            recordKind = RECORD_KIND_CAST,
            payloadJson = gson.toJson(cast),
            lastUpdatedMillis = System.currentTimeMillis()
        )
        database.offlineMetadataDao().upsert(entity)
    }

    suspend fun getCachedSearchResults(query: String, maxAgeMs: Long): List<MediaItem>? {
        val entity = database.offlineSearchDao().get(normalizedSearchQuery(query)) ?: return null
        return if (System.currentTimeMillis() - entity.lastUpdatedMillis <= maxAgeMs) {
            gson.fromJson(entity.payloadJson, mediaItemListType)
        } else {
            null
        }
    }

    suspend fun putCachedSearchResults(query: String, results: List<MediaItem>) {
        val entity = OfflineSearchEntity(
            query = normalizedSearchQuery(query),
            payloadJson = gson.toJson(results),
            lastUpdatedMillis = System.currentTimeMillis()
        )
        database.offlineSearchDao().upsert(entity)
    }

    suspend fun pruneOldSearchResults(maxAgeMs: Long) {
        val staleBefore = System.currentTimeMillis() - maxAgeMs
        database.offlineSearchDao().deleteOlderThan(staleBefore)
    }

    suspend fun getStaleRecordKeys(recordKind: String, maxAgeMs: Long, limit: Int): List<Pair<MediaType, Int>> {
        val staleBefore = System.currentTimeMillis() - maxAgeMs
        return database.offlineMetadataDao()
            .getStaleEntities(recordKind, staleBefore, limit)
            .mapNotNull { entity ->
                runCatching {
                    MediaType.valueOf(entity.mediaType) to entity.mediaId
                }.getOrNull()
            }
    }

    suspend fun clearAll() {
        database.offlineMetadataDao().clearAll()
        database.offlineSearchDao().clearAll()
    }

    suspend fun getCacheStats(): CacheStats {
        val metadataCount = database.offlineMetadataDao().count()
        val searchCount = database.offlineSearchDao().count()
        return CacheStats(metadataCount, searchCount)
    }

    data class CacheStats(
        val metadataCount: Int,
        val searchCount: Int
    )
}
