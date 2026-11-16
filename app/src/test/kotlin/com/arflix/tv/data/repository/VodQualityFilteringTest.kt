package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.QualityFilterConfig
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class VodQualityFilteringTest {
    private val iptv = mockk<IptvRepository>(relaxed = true)
    private lateinit var repository: StreamRepository
    private lateinit var scope: CoroutineScope
    private val excludePoorCopies = listOf(QualityFilterConfig(regexPattern = "\\b(CAM|TELESYNC)\\b"))
    private val copies = listOf(
        source("movie-CAM", "Example.CAM.2160p", "4K"),
        source("movie-HD", "Example.1080p.WEB-DL", "1080p"),
        source("movie-TS", "Example.TELESYNC.1080p", "1080p"),
        source("movie-SD", "Example.480p", "480p")
    )

    @Before fun setup(): Unit = runBlocking {
        val state = MutableStateFlow(emptyPreferences())
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                transform(state.value).also { state.value = it }
        }
        mockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().streamDataStore } returns store
        every { any<Context>().settingsDataStore } returns store
        repository = StreamRepository(
            RuntimeEnvironment.getApplication(), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), iptv, mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true)
        )
        val field = StreamRepository::class.java.getDeclaredField("repositoryScope").apply { isAccessible = true }
        scope = field.get(repository) as CoroutineScope
        withTimeout(5_000) { scope.coroutineContext[Job]!!.children.toList().forEach { it.join() } }
        repository.updateQualityFiltersCache(excludePoorCopies)
        coEvery { iptv.findMovieVodSources(any(), any(), any(), any(), any(), any(), any()) } returns copies
        coEvery { iptv.findEpisodeVodSources(any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns copies
    }

    @After fun cleanup() = runBlocking {
        scope.coroutineContext[Job]?.cancelAndJoin()
        unmockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    private fun source(id: String, name: String, quality: String) = StreamSource(
        name, "IPTV VOD", IptvVodSourceIds.XTREAM, quality, "",
        url = "https://fixture.invalid/$id.mp4"
    )

    private suspend fun movies() = repository.resolveMovieVodSources("tt123", "Example")
    private suspend fun episodes(timeout: Long = 1_000) =
        repository.resolveEpisodeVodSources("tt123", 1, 2, "Example", timeoutMs = timeout)

    @Test fun movieResolverFiltersBeforeAnyCallerCanDisplayOrAutoplay() = runBlocking {
        assertEquals(listOf(copies[1], copies[3]), movies())
    }

    @Test fun episodeResolverUsesTheSameExclusionRules() = runBlocking {
        assertEquals(listOf(copies[1], copies[3]), episodes())
    }

    @Test fun retainedEpisodeResultsAreFilteredWhenAnotherProviderTimesOut() = runBlocking {
        coEvery { iptv.findEpisodeVodSources(any(), any(), any(), any(), any(), any(), any(), any(), any()) } coAnswers {
            arg<(List<StreamSource>) -> Unit>(7)(copies)
            delay(5_000)
            emptyList()
        }
        assertEquals(listOf(copies[1], copies[3]), episodes(timeout = 500))
    }

    @Test fun retainedEpisodeResultsAreFilteredAfterAProviderFailure() = runBlocking {
        coEvery { iptv.findEpisodeVodSources(any(), any(), any(), any(), any(), any(), any(), any(), any()) } coAnswers {
            arg<(List<StreamSource>) -> Unit>(7)(copies)
            throw IOException("fixture provider failed")
        }
        assertEquals(listOf(copies[1], copies[3]), episodes())
    }

    @Test fun aFullyExcludedBatchStaysEmptyInsteadOfRestoringRawSources() = runBlocking {
        repository.updateQualityFiltersCache(listOf(QualityFilterConfig(regexPattern = ".*")))
        assertTrue(movies().isEmpty())
        assertTrue(episodes().isEmpty())
    }

    @Test fun currentFiltersApplyToReusedRawResultsWithoutMutatingTheCache() = runBlocking {
        assertEquals(listOf(copies[1], copies[3]), movies())
        repository.updateQualityFiltersCache(emptyList())
        assertEquals(copies, movies())
        repository.updateQualityFiltersCache(listOf(QualityFilterConfig(regexPattern = "480p|1080p")))
        assertEquals(listOf(copies[0]), movies())
        assertEquals(4, copies.size)
    }

    @Test fun disabledAndInvalidRulesDoNotHideValidSources() = runBlocking {
        repository.updateQualityFiltersCache(listOf(
            QualityFilterConfig(regexPattern = "CAM|TELESYNC", enabled = false),
            QualityFilterConfig(regexPattern = "[")
        ))
        assertEquals(copies, movies())
        assertEquals(copies, episodes())
    }

    @Test fun episodeVariantLabelsAreFilteredWithoutChangingTheirDisplayTitle() = runBlocking {
        val episode = source("episode", "Episode 2", "1080p").copy(rawLabel = "Example Show TELESYNC FHD")
        coEvery { iptv.findEpisodeVodSources(any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns listOf(episode)
        assertTrue(episodes().isEmpty())
        assertTrue(filterStreamsByQualityRegex(listOf(episode), excludePoorCopies).isEmpty())
        repository.updateQualityFiltersCache(emptyList())
        assertEquals("Episode 2", episodes().single().source)
    }

    @Test fun stalkerVodUsesTheSamePolicyWhileAddonMatchingRemainsUnchanged() = runBlocking {
        val stalker = copies[0].copy(addonId = IptvVodSourceIds.STALKER)
        assertTrue(repository.applyQualityRegexFilters(listOf(stalker)).isEmpty())
        val addon = copies[1].copy(addonId = "addon", rawLabel = "CAM")
        assertEquals(listOf(addon), repository.applyQualityRegexFilters(listOf(addon)))
    }

    @Test fun episodeCancellationIsNotSwallowedByFiltering() = runBlocking {
        coEvery { iptv.findEpisodeVodSources(any(), any(), any(), any(), any(), any(), any(), any(), any()) } throws
            CancellationException("fixture cancelled")
        try {
            episodes()
            fail("Cancellation must propagate")
        } catch (_: CancellationException) { }
    }
}
