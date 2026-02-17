package com.arflix.tv.data.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * IntroDB segments API.
 */
interface IntroDbApi {
    @GET("segments")
    suspend fun getSegments(
        @Query("imdb_id") imdbId: String,
        @Query("season") season: Int,
        @Query("episode") episode: Int
    ): IntroDbSegmentsResponse
}

@Keep
data class IntroDbSegmentsResponse(
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("season") val season: Int? = null,
    @SerializedName("episode") val episode: Int? = null,
    @SerializedName("intro") val intro: IntroDbSegment? = null,
    @SerializedName("recap") val recap: IntroDbSegment? = null,
    @SerializedName("outro") val outro: IntroDbSegment? = null
)

@Keep
data class IntroDbSegment(
    @SerializedName("start_ms") val startMs: Long = 0L,
    @SerializedName("end_ms") val endMs: Long = 0L,
    @SerializedName("start_sec") val startSec: Double? = null,
    @SerializedName("end_sec") val endSec: Double? = null,
    @SerializedName("confidence") val confidence: Double? = null,
    @SerializedName("submission_count") val submissionCount: Int? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)

/**
 * AniSkip API (anime OP/ED/recap skip times).
 */
interface AniSkipApi {
    @GET("skip-times/{malId}/{episode}")
    suspend fun getSkipTimes(
        @Path("malId") malId: String,
        @Path("episode") episode: Int,
        @Query("types") types: List<String>,
        @Query("episodeLength") episodeLength: Int = 0
    ): AniSkipResponse
}

@Keep
data class AniSkipResponse(
    @SerializedName("found") val found: Boolean = false,
    @SerializedName("results") val results: List<AniSkipResult>? = null
)

@Keep
data class AniSkipResult(
    @SerializedName("interval") val interval: AniSkipInterval,
    @SerializedName("skipType") val skipType: String,
    @SerializedName("skipId") val skipId: String? = null
)

@Keep
data class AniSkipInterval(
    @SerializedName("startTime") val startTime: Double,
    @SerializedName("endTime") val endTime: Double
)

/**
 * ARM API (IMDB -> MAL ID resolution).
 */
interface ArmApi {
    @GET("imdb")
    suspend fun resolve(
        @Query("id") imdbId: String,
        @Query("include") include: String = "myanimelist"
    ): List<ArmEntry>
}

@Keep
data class ArmEntry(
    @SerializedName("myanimelist") val myanimelist: Int? = null
)
