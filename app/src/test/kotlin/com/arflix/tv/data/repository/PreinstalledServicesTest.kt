package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceKind
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Exercises `buildPreinstalledDefaults()` in MediaRepository. That's the
 * entry point used by getDefaultCatalogConfigs() to seed a fresh profile's
 * catalogs.
 */
class PreinstalledServicesTest {

    private val serviceOrder = listOf(
        "collection_service_netflix",
        "collection_service_disneyplus",
        "collection_service_apple_tvplus",
        "collection_service_prime_video",
        "collection_service_max",
        "collection_service_skyshowtime",
        "collection_service_viaplay",
        "collection_service_crunchyroll"
    )

    private val serviceVideoIds = setOf(
        "collection_service_netflix",
        "collection_service_disneyplus",
        "collection_service_apple_tvplus",
        "collection_service_prime_video",
        "collection_service_max",
        "collection_service_crunchyroll"
    )

    private val servicesWithoutHeroVideo = serviceOrder.toSet() - serviceVideoIds

    private fun loadServices() =
        MediaRepository.buildPreinstalledDefaults()
            .filter { it.id.startsWith("collection_service_") }

    @Test
    fun `services appear in template order`() {
        val services = loadServices()
        assertEquals(serviceOrder, services.map { it.id })
    }

    @Test
    fun `all services have focusGif equal to cover (no distinct GIF)`() {
        // The helper defaults `collectionFocusGifUrl` to `focusGif ?: cover`,
        // so passing focusGif = null resolves to the cover PNG itself. The
        // home-row tile treats `backdrop == image` as "no focus swap".
        val services = loadServices()
        assertEquals(serviceOrder.size, services.size)
        services.forEach { cfg ->
            assertEquals(
                "Service ${cfg.id} focusGif must equal cover (no distinct GIF)",
                cfg.collectionCoverImageUrl,
                cfg.collectionFocusGifUrl
            )
        }
    }

    @Test
    fun `all services have null collectionClearLogoUrl`() {
        val services = loadServices()
        services.forEach { cfg ->
            assertNull(
                "Service ${cfg.id} should not have a clearLogo",
                cfg.collectionClearLogoUrl
            )
        }
    }

    @Test
    fun `primary services have heroVideo URLs`() {
        val services = loadServices().filter { it.id in serviceVideoIds }
        val expectedVideos = mapOf(
            "collection_service_netflix" to "networks%20videos/netflix.mp4",
            "collection_service_disneyplus" to "networks%20videos/disneyplus.mp4",
            "collection_service_apple_tvplus" to "networks%20videos/appletv.mp4",
            "collection_service_prime_video" to "networks%20videos/amazonprime.mp4",
            "collection_service_max" to "networks%20videos/hbomax.mp4",
            "collection_service_crunchyroll" to "networks%20videos/crunchyroll.mp4"
        )
        services.forEach { cfg ->
            val video = cfg.collectionHeroVideoUrl
            assertNotNull("${cfg.id} heroVideo", video)
            assertTrue(
                "${cfg.id} heroVideo must be mrtxiv asset, was $video",
                video!!.contains("raw.githubusercontent.com/mrtxiv/networks-video-collection") &&
                    video.endsWith(expectedVideos[cfg.id]!!)
            )
        }
    }

    @Test
    fun `secondary services have no heroVideo`() {
        val services = loadServices().filter { it.id in servicesWithoutHeroVideo }
        assertEquals(servicesWithoutHeroVideo.size, services.size)
        services.forEach { cfg ->
            assertNull("${cfg.id} should not have heroVideo", cfg.collectionHeroVideoUrl)
        }
    }

    @Test
    fun `template service collections include Watchmode Swedish sources`() {
        val services = MediaRepository.buildPreinstalledDefaults()
            .filter { it.kind == CatalogKind.COLLECTION && it.collectionGroup == CollectionGroupKind.SERVICE }
        assertTrue("Expected service collections", services.isNotEmpty())
        services.forEach { cfg ->
            assertTrue(
                "${cfg.title} must have a Watchmode source",
                cfg.collectionSources.any { it.kind == CollectionSourceKind.WATCHMODE_SOURCE }
            )
        }
    }

    @Test
    fun `Swedish service Watchmode ids are wired`() {
        val expected = mapOf(
            "Netflix" to 203,
            "Disney+" to 372,
            "Apple TV+" to 371,
            "Prime Video" to 26,
            "Max" to 387,
            "SkyShowtime" to 464,
            "Viaplay" to 486,
            "Crunchyroll" to 80
        )
        val services = MediaRepository.buildPreinstalledDefaults()
            .filter { it.kind == CatalogKind.COLLECTION && it.collectionGroup == CollectionGroupKind.SERVICE }

        expected.forEach { (title, sourceId) ->
            val service = services.first { it.title == title }
            assertTrue(
                "$title missing Watchmode source $sourceId",
                service.collectionSources.any {
                    it.kind == CollectionSourceKind.WATCHMODE_SOURCE &&
                        it.watchmodeSourceId == sourceId
                }
            )
        }
    }

    @Test
    fun `genre collections include direct TMDB fallbacks`() {
        val genres = MediaRepository.buildPreinstalledDefaults()
            .filter { it.kind == CatalogKind.COLLECTION && it.collectionGroup == CollectionGroupKind.GENRE }
        assertTrue("Expected genre collections", genres.isNotEmpty())
        genres.forEach { cfg ->
            assertTrue(
                "${cfg.title} must have a direct TMDB genre or keyword fallback",
                cfg.collectionSources.any {
                    it.kind == CollectionSourceKind.TMDB_GENRE ||
                        it.kind == CollectionSourceKind.TMDB_KEYWORD
                }
            )
        }

        val action = genres.first { it.title == "Action" }
        assertTrue(action.collectionSources.any {
            it.kind == CollectionSourceKind.TMDB_GENRE &&
                it.mediaType == "movie" &&
                it.tmdbGenreId == 28
        })
        assertTrue(action.collectionSources.any {
            it.kind == CollectionSourceKind.TMDB_GENRE &&
                it.mediaType == "series" &&
                it.tmdbGenreId == 10759
        })
    }

    @Test
    fun `smart decision collections include runtime and vote guarded TMDB discover sources`() {
        val catalogs = MediaRepository.buildPreinstalledDefaults()
        val shortMovie = catalogs.first { it.id == "collection_intent_short_movie" }
        val bestMovies = catalogs.first { it.id == "collection_intent_best_movies" }
        val bestSeries = catalogs.first { it.id == "collection_intent_best_series" }
        val familyMovies = catalogs.first { it.id == "collection_intent_family_break_movies" }
        val newSeries = catalogs.first { it.id == "collection_intent_new_streaming_series" }
        val familySeries = catalogs.first { it.id == "collection_intent_family_series" }

        assertEquals(CollectionGroupKind.FEATURED, shortMovie.collectionGroup)
        val shortMovieSource = shortMovie.collectionSources.single()
        assertEquals(CollectionSourceKind.TMDB_DISCOVER, shortMovieSource.kind)
        assertEquals("movie", shortMovieSource.mediaType)
        assertEquals("vote_average.desc", shortMovieSource.sortBy)
        assertEquals(100, shortMovieSource.runtimeLteMinutes)
        assertEquals(250, shortMovieSource.voteCountGte)

        val bestMovieSource = bestMovies.collectionSources.single()
        assertEquals(CollectionSourceKind.TMDB_DISCOVER, bestMovieSource.kind)
        assertEquals("movie", bestMovieSource.mediaType)
        assertEquals(1200, bestMovieSource.voteCountGte)
        assertNull(bestMovieSource.runtimeLteMinutes)

        val bestSeriesSource = bestSeries.collectionSources.single()
        assertEquals(CollectionSourceKind.TMDB_DISCOVER, bestSeriesSource.kind)
        assertEquals("series", bestSeriesSource.mediaType)
        assertEquals(600, bestSeriesSource.voteCountGte)

        assertTrue(familyMovies.collectionSources.all { it.mediaType == "movie" })
        assertTrue(familyMovies.collectionSources.any {
            it.kind == CollectionSourceKind.TMDB_GENRE &&
                it.tmdbGenreId == 10751
        })

        val newSeriesSource = newSeries.collectionSources.single()
        assertEquals(CollectionSourceKind.MDBLIST_PUBLIC, newSeriesSource.kind)
        assertEquals("snoak/latest-tv-shows", newSeriesSource.mdblistSlug)

        assertTrue(familySeries.collectionSources.all { it.mediaType == "series" })
        assertTrue(familySeries.collectionSources.any {
            it.kind == CollectionSourceKind.TMDB_GENRE &&
                it.tmdbGenreId == 10751
        })
    }
}
