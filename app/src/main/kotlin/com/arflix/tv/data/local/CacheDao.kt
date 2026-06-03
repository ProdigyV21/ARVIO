package com.arflix.tv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CacheDao {

    // === Media Items ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(item: CachedMediaItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItems(items: List<CachedMediaItem>)

    @Query("SELECT * FROM media_items WHERE id = :id AND mediaType = :mediaType LIMIT 1")
    suspend fun getMediaItem(id: Int, mediaType: String): CachedMediaItem?

    @Query("UPDATE media_items SET lastAccessed = :timestamp WHERE id = :id AND mediaType = :mediaType")
    suspend fun updateMediaItemLastAccessed(id: Int, mediaType: String, timestamp: Long = System.currentTimeMillis())

    @Query("DELETE FROM media_items WHERE id = :id AND mediaType = :mediaType")
    suspend fun deleteMediaItem(id: Int, mediaType: String)

    // === Cast Members ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCastMembers(cast: List<CachedCastMember>)

    @Query("SELECT * FROM cast_members WHERE mediaId = :mediaId AND mediaType = :mediaType")
    suspend fun getCastMembers(mediaId: Int, mediaType: String): List<CachedCastMember>

    @Query("DELETE FROM cast_members WHERE mediaId = :mediaId AND mediaType = :mediaType")
    suspend fun deleteCastMembersForMedia(mediaId: Int, mediaType: String)

    // === Episodes ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<CachedEpisode>)

    @Query("SELECT * FROM episodes WHERE tvId = :tvId AND seasonNumber = :seasonNumber ORDER BY episodeNumber ASC")
    suspend fun getEpisodes(tvId: Int, seasonNumber: Int): List<CachedEpisode>

    @Query("DELETE FROM episodes WHERE tvId = :tvId AND seasonNumber = :seasonNumber")
    suspend fun deleteEpisodesForSeason(tvId: Int, seasonNumber: Int)

    // === Similar Items ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimilarRelations(relations: List<CachedSimilarItem>)

    @Query("DELETE FROM similar_items WHERE mediaId = :mediaId AND mediaType = :mediaType")
    suspend fun deleteSimilarRelations(mediaId: Int, mediaType: String)

    @Transaction
    @Query("SELECT mi.* FROM media_items mi INNER JOIN similar_items si ON mi.id = si.similarId AND mi.mediaType = si.similarType WHERE si.mediaId = :mediaId AND si.mediaType = :mediaType ORDER BY si.position ASC")
    suspend fun getSimilarItems(mediaId: Int, mediaType: String): List<CachedMediaItem>

    // === Reviews ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<CachedReview>)

    @Query("SELECT * FROM reviews WHERE mediaId = :mediaId AND mediaType = :mediaType ORDER BY rating DESC")
    suspend fun getReviews(mediaId: Int, mediaType: String): List<CachedReview>

    @Query("DELETE FROM reviews WHERE mediaId = :mediaId AND mediaType = :mediaType")
    suspend fun deleteReviewsForMedia(mediaId: Int, mediaType: String)

    // === Collection / Catalog Refs ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionRefs(refs: List<CachedCollectionRef>)

    @Query("DELETE FROM collection_refs WHERE catalogId = :catalogId")
    suspend fun deleteCollectionRefs(catalogId: String)

    @Transaction
    @Query("SELECT mi.* FROM media_items mi INNER JOIN collection_refs cr ON mi.id = cr.mediaId AND mi.mediaType = cr.mediaType WHERE cr.catalogId = :catalogId ORDER BY cr.position ASC")
    suspend fun getCollectionItems(catalogId: String): List<CachedMediaItem>

    // === Search History ===
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(query: CachedSearchQuery)

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getSearchHistory(limit: Int = 10): List<CachedSearchQuery>

    @Query("DELETE FROM search_history WHERE `query` = :query")
    suspend fun deleteSearchQuery(query: String)

    @Query("DELETE FROM search_history")
    suspend fun clearSearchHistory()

    // === General Cache Cleanup ===

    @Transaction
    suspend fun saveSimilarItems(mediaId: Int, mediaType: String, items: List<CachedMediaItem>, relations: List<CachedSimilarItem>) {
        deleteSimilarRelations(mediaId, mediaType)
        insertMediaItems(items)
        insertSimilarRelations(relations)
    }

    @Transaction
    suspend fun saveCollectionItems(catalogId: String, items: List<CachedMediaItem>, refs: List<CachedCollectionRef>) {
        deleteCollectionRefs(catalogId)
        insertMediaItems(items)
        insertCollectionRefs(refs)
    }

    @Transaction
    suspend fun saveTvSeason(tvId: Int, seasonNumber: Int, episodes: List<CachedEpisode>) {
        deleteEpisodesForSeason(tvId, seasonNumber)
        insertEpisodes(episodes)
    }

    @Transaction
    suspend fun saveCredits(mediaId: Int, mediaType: String, cast: List<CachedCastMember>) {
        deleteCastMembersForMedia(mediaId, mediaType)
        insertCastMembers(cast)
    }

    @Transaction
    suspend fun saveReviews(mediaId: Int, mediaType: String, reviews: List<CachedReview>) {
        deleteReviewsForMedia(mediaId, mediaType)
        insertReviews(reviews)
    }

    @Query("DELETE FROM media_items")
    suspend fun clearAllMediaItems()

    @Query("DELETE FROM cast_members")
    suspend fun clearAllCastMembers()

    @Query("DELETE FROM episodes")
    suspend fun clearAllEpisodes()

    @Query("DELETE FROM similar_items")
    suspend fun clearAllSimilarItems()

    @Query("DELETE FROM reviews")
    suspend fun clearAllReviews()

    @Query("DELETE FROM collection_refs")
    suspend fun clearAllCollectionRefs()

    @Transaction
    suspend fun clearAllMetadata() {
        clearAllMediaItems()
        clearAllCastMembers()
        clearAllEpisodes()
        clearAllSimilarItems()
        clearAllReviews()
        clearAllCollectionRefs()
        clearSearchHistory()
    }

    @Query("DELETE FROM media_items WHERE updatedAt < :staleTime")
    suspend fun deleteStaleMediaItems(staleTime: Long)

    @Query("DELETE FROM cast_members WHERE createdAt < :staleTime")
    suspend fun deleteStaleCastMembers(staleTime: Long)

    @Query("DELETE FROM episodes WHERE createdAt < :staleTime")
    suspend fun deleteStaleEpisodes(staleTime: Long)

    @Query("SELECT * FROM media_items WHERE updatedAt < :staleTime ORDER BY lastAccessed DESC LIMIT :limit")
    suspend fun getStaleMediaItems(staleTime: Long, limit: Int): List<CachedMediaItem>
}
