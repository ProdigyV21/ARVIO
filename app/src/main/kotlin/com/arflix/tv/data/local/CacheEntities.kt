package com.arflix.tv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import com.arflix.tv.data.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "media_items", primaryKeys = ["id", "mediaType"])
data class CachedMediaItem(
    val id: Int,
    val mediaType: String, // MOVIE or TV
    val title: String,
    val subtitle: String = "",
    val overview: String = "",
    val year: String = "",
    val releaseDate: String? = null,
    val rating: String = "",
    val duration: String = "",
    val imdbRating: String = "",
    val tmdbRating: String = "",
    val image: String = "",
    val backdrop: String? = null,
    val progress: Int = 0,
    val isWatched: Boolean = false,
    val traktId: Int? = null,
    val badge: String? = null,
    val genreIdsJson: String = "[]", // Serialized List<Int>
    val originalLanguage: String? = null,
    val primaryNetworkLogo: String? = null,
    val isOngoing: Boolean = false,
    val totalEpisodes: Int? = null,
    val watchedEpisodes: Int? = null,
    val nextEpisodeJson: String? = null, // Serialized NextEpisode?
    val budget: Long? = null,
    val revenue: Long? = null,
    val status: String? = null,
    val character: String = "",
    val popularity: Float = 0f,
    val addedAt: Long = 0L,
    val sourceOrder: Int = Int.MAX_VALUE,
    val timeRemainingLabel: String? = null,
    val showPlaybackProgress: Boolean = true,
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastAccessed: Long = System.currentTimeMillis()
) {
    fun toDomain(gson: Gson): MediaItem {
        val type = MediaType.valueOf(mediaType)
        val genreIdsType = object : TypeToken<List<Int>>() {}.type
        val genreIds: List<Int> = gson.fromJson(genreIdsJson, genreIdsType) ?: emptyList()
        val nextEpisode: NextEpisode? = nextEpisodeJson?.let {
            gson.fromJson(it, NextEpisode::class.java)
        }
        
        return MediaItem(
            id = id,
            title = title,
            subtitle = subtitle,
            overview = overview,
            year = year,
            releaseDate = releaseDate,
            rating = rating,
            duration = duration,
            imdbRating = imdbRating,
            tmdbRating = tmdbRating,
            mediaType = type,
            image = image,
            backdrop = backdrop,
            progress = progress,
            isWatched = isWatched,
            traktId = traktId,
            badge = badge,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            primaryNetworkLogo = primaryNetworkLogo,
            isOngoing = isOngoing,
            totalEpisodes = totalEpisodes,
            watchedEpisodes = watchedEpisodes,
            nextEpisode = nextEpisode,
            budget = budget,
            revenue = revenue,
            status = status,
            character = character,
            popularity = popularity,
            addedAt = addedAt,
            sourceOrder = sourceOrder,
            timeRemainingLabel = timeRemainingLabel,
            showPlaybackProgress = showPlaybackProgress
        )
    }

    companion object {
        fun fromDomain(item: MediaItem, gson: Gson): CachedMediaItem {
            return CachedMediaItem(
                id = item.id,
                mediaType = item.mediaType.name,
                title = item.title,
                subtitle = item.subtitle,
                overview = item.overview,
                year = item.year,
                releaseDate = item.releaseDate,
                rating = item.rating,
                duration = item.duration,
                imdbRating = item.imdbRating,
                tmdbRating = item.tmdbRating,
                image = item.image,
                backdrop = item.backdrop,
                progress = item.progress,
                isWatched = item.isWatched,
                traktId = item.traktId,
                badge = item.badge,
                genreIdsJson = gson.toJson(item.genreIds),
                originalLanguage = item.originalLanguage,
                primaryNetworkLogo = item.primaryNetworkLogo,
                isOngoing = item.isOngoing,
                totalEpisodes = item.totalEpisodes,
                watchedEpisodes = item.watchedEpisodes,
                nextEpisodeJson = item.nextEpisode?.let { gson.toJson(it) },
                budget = item.budget,
                revenue = item.revenue,
                status = item.status,
                character = item.character,
                popularity = item.popularity,
                addedAt = item.addedAt,
                sourceOrder = item.sourceOrder,
                timeRemainingLabel = item.timeRemainingLabel,
                showPlaybackProgress = item.showPlaybackProgress
            )
        }
    }
}

@Entity(tableName = "cast_members", primaryKeys = ["id", "mediaId", "mediaType", "character"])
data class CachedCastMember(
    val id: Int,
    val mediaId: Int,
    val mediaType: String, // MOVIE or TV
    val name: String,
    val character: String,
    val profilePath: String?,
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): CastMember {
        return CastMember(
            id = id,
            name = name,
            character = character,
            profilePath = profilePath
        )
    }

    companion object {
        fun fromDomain(cast: CastMember, mediaId: Int, mediaType: MediaType): CachedCastMember {
            return CachedCastMember(
                id = cast.id,
                mediaId = mediaId,
                mediaType = mediaType.name,
                name = cast.name,
                character = cast.character,
                profilePath = cast.profilePath
            )
        }
    }
}

@Entity(tableName = "episodes", primaryKeys = ["id", "tvId", "seasonNumber", "episodeNumber"])
data class CachedEpisode(
    val id: Int,
    val tvId: Int,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val name: String,
    val overview: String = "",
    val stillPath: String? = null,
    val voteAverage: Float = 0f,
    val runtime: Int = 0,
    val airDate: String = "",
    val isWatched: Boolean = false,
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): Episode {
        return Episode(
            id = id,
            episodeNumber = episodeNumber,
            seasonNumber = seasonNumber,
            name = name,
            overview = overview,
            stillPath = stillPath,
            voteAverage = voteAverage,
            runtime = runtime,
            airDate = airDate,
            isWatched = isWatched
        )
    }

    companion object {
        fun fromDomain(ep: Episode, tvId: Int): CachedEpisode {
            return CachedEpisode(
                id = ep.id,
                tvId = tvId,
                seasonNumber = ep.seasonNumber,
                episodeNumber = ep.episodeNumber,
                name = ep.name,
                overview = ep.overview,
                stillPath = ep.stillPath,
                voteAverage = ep.voteAverage,
                runtime = ep.runtime,
                airDate = ep.airDate,
                isWatched = ep.isWatched
            )
        }
    }
}

@Entity(tableName = "similar_items", primaryKeys = ["mediaId", "mediaType", "similarId", "similarType"])
data class CachedSimilarItem(
    val mediaId: Int,
    val mediaType: String,
    val similarId: Int,
    val similarType: String,
    val position: Int = 0, // Preserve similar order
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "reviews")
data class CachedReview(
    @PrimaryKey val id: String,
    val mediaId: Int,
    val mediaType: String,
    val author: String,
    val authorUsername: String = "",
    val authorAvatar: String? = null,
    val content: String,
    val rating: Float? = null,
    val createdAtStr: String = "",
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): Review {
        return Review(
            id = id,
            author = author,
            authorUsername = authorUsername,
            authorAvatar = authorAvatar,
            content = content,
            rating = rating,
            createdAt = createdAtStr
        )
    }

    companion object {
        fun fromDomain(review: Review, mediaId: Int, mediaType: MediaType): CachedReview {
            return CachedReview(
                id = review.id,
                mediaId = mediaId,
                mediaType = mediaType.name,
                author = review.author,
                authorUsername = review.authorUsername,
                authorAvatar = review.authorAvatar,
                content = review.content,
                rating = review.rating,
                createdAtStr = review.createdAt
            )
        }
    }
}

@Entity(tableName = "collection_refs", primaryKeys = ["catalogId", "mediaId", "mediaType"])
data class CachedCollectionRef(
    val catalogId: String,
    val mediaId: Int,
    val mediaType: String,
    val position: Int = 0,
    
    // Tracking fields
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "search_history")
data class CachedSearchQuery(
    @PrimaryKey val query: String,
    val timestamp: Long = System.currentTimeMillis()
)
