package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.api.StremioManifestResponse
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.util.concurrent.atomic.AtomicReference

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class StreamRepositoryAddonMutationTest {
    private val state = MutableStateFlow(
        preferencesOf(stringPreferencesKey("shared_installed_addons_v1") to Gson().toJson(listOf(addon("seed"))))
    )
    private val beforeWrite = AtomicReference<(suspend () -> Unit)?>(null)
    private lateinit var repository: StreamRepository
    private lateinit var api: StreamApi

    @Before
    fun setup() {
        val mutex = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = state

            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
                beforeWrite.getAndSet(null)?.invoke()
                return mutex.withLock {
                    transform(state.value).toPreferences().also { state.value = it }
                }
            }
        }
        mockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().streamDataStore } returns store
        every { any<Context>().settingsDataStore } returns store
        val profiles = mockk<ProfileManager>(relaxed = true)
        every { profiles.getProfileIdSync() } returns "default"
        every { profiles.currentProfileId } returns MutableStateFlow("default")
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("profile_default_${firstArg<String>()}") }
        every { profiles.profileStringKeyFor(any(), any()) } answers {
            stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}")
        }
        api = mockk(relaxed = true)
        val httpRuntime = mockk<HttpLocalScraperRuntime>(relaxed = true)
        coEvery { httpRuntime.fetchInstallCandidate(any(), any()) } returns null
        repository = StreamRepository(
            RuntimeEnvironment.getApplication(), api, mockk(relaxed = true), profiles,
            mockk(relaxed = true), mockk(relaxed = true), httpRuntime, mockk(relaxed = true),
            CloudSyncInvalidationBus(), mockk(relaxed = true), mockk(relaxed = true)
        )
    }

    @After
    fun cleanup() {
        if (::repository.isInitialized) {
            val field = StreamRepository::class.java.getDeclaredField("repositoryScope").apply { isAccessible = true }
            (field.get(repository) as CoroutineScope).coroutineContext[Job]?.let { job ->
                runBlocking { job.cancelAndJoin() }
            }
        }
        unmockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    @Test
    fun overlappingInstallsKeepBothAddonsAndCloudChangeStamps() = runBlocking {
        overlap({ repository.installPreparedAddon(addon("a")) }, { repository.installPreparedAddon(addon("b")) })
        val saved = repository.exportAddonCloudState()
        assertEquals(setOf("seed", "opensubtitles", "a", "b"), saved.addons.map { it.id }.toSet())
        assertFalse(saved.changes.getValue("a").removed)
        assertFalse(saved.changes.getValue("b").removed)
    }

    @Test
    fun removalDoesNotDiscardAnOverlappingInstall() = runBlocking {
        overlap({ repository.removeAddon("seed") }, { repository.installPreparedAddon(addon("b")) })
        val saved = repository.exportAddonCloudState()
        assertEquals(setOf("opensubtitles", "b"), saved.addons.map { it.id }.toSet())
        assertTrue(saved.changes.getValue("seed").removed)
        assertFalse(saved.changes.getValue("b").removed)
    }

    @Test
    fun installDoesNotUndoAnOverlappingRemoval() = runBlocking {
        overlap({ repository.installPreparedAddon(addon("a")) }, { repository.removeAddon("seed") })
        assertEquals(setOf("opensubtitles", "a"), repository.installedAddons.first().map { it.id }.toSet())
    }

    @Test
    fun installPreservesAnOverlappingCloudAddition() = runBlocking {
        overlap(
            { repository.installPreparedAddon(addon("a")) },
            { repository.applyAddonCloudState(listOf(addon("seed"), addon("cloud")), 100L, emptyMap()) }
        )
        assertEquals(setOf("seed", "opensubtitles", "cloud", "a"), repository.installedAddons.first().map { it.id }.toSet())
    }

    @Test
    fun replacementKeepsItsPositionAndPreservesAnotherInstall() = runBlocking {
        overlap(
            { repository.installPreparedAddon(addon("replacement"), setOf("seed")) },
            { repository.installPreparedAddon(addon("b")) }
        )
        val saved = repository.exportAddonCloudState()
        assertEquals(listOf("replacement", "opensubtitles", "b"), saved.addons.map { it.id })
        assertTrue(saved.changes.getValue("seed").removed)
    }

    @Test
    fun reinstallClearsRemovalAndKeepsOpenSubtitlesProtected() = runBlocking {
        repository.removeAddon("seed")
        repository.installPreparedAddon(addon("seed"))
        repository.removeAddon("opensubtitles")
        val saved = repository.exportAddonCloudState()
        assertEquals(setOf("seed", "opensubtitles"), saved.addons.map { it.id }.toSet())
        assertFalse(saved.changes.getValue("seed").removed)
        assertFalse("opensubtitles" in saved.changes)
    }

    @Test
    fun togglePreservesAnOverlappingInstall() = runBlocking {
        overlap({ repository.toggleAddon("seed") }, { repository.installPreparedAddon(addon("b")) })
        val saved = repository.installedAddons.first()
        assertFalse(saved.first { it.id == "seed" }.isEnabled)
        assertTrue(saved.any { it.id == "b" })
    }

    @Test
    fun slowRefreshPreservesNewInstallsAndToggles() = runBlocking {
        val entered = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        coEvery { api.getAddonManifest("https://seed.example/manifest.json") } coAnswers {
            entered.complete(Unit)
            release.await()
            StremioManifestResponse("seed", "seed", "2.0.0")
        }
        withTimeout(10_000L) {
            val pending = async { repository.refreshInstalledAddons() }
            try {
                entered.await()
                repository.installPreparedAddon(addon("b"))
                repository.toggleAddon("seed")
            } finally {
                release.complete(Unit)
            }
            pending.await()
        }
        val saved = repository.installedAddons.first()
        assertTrue(saved.any { it.id == "b" })
        assertFalse(saved.first { it.id == "seed" }.isEnabled)
    }

    @Test
    fun slowRefreshDoesNotResurrectRemovedAddons() = runBlocking {
        val entered = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        coEvery { api.getAddonManifest("https://seed.example/manifest.json") } coAnswers {
            entered.complete(Unit)
            release.await()
            StremioManifestResponse("seed", "seed", "2.0.0")
        }
        withTimeout(10_000L) {
            val pending = async { repository.refreshInstalledAddons() }
            try {
                entered.await()
                repository.removeAddon("seed")
            } finally {
                release.complete(Unit)
            }
            pending.await()
        }
        assertEquals(listOf("opensubtitles"), repository.installedAddons.first().map { it.id })
    }

    @Test
    fun refreshStillUpdatesUnchangedInstalledManifest() = runBlocking {
        coEvery { api.getAddonManifest("https://seed.example/manifest.json") } returns
            StremioManifestResponse("seed", "seed", "2.0.0")
        repository.refreshInstalledAddons()
        assertEquals("2.0.0", repository.installedAddons.first().first { it.id == "seed" }.version)
    }

    // Hold the first write before its transaction, let a second write finish, then resume it.
    private suspend fun overlap(first: suspend () -> Unit, second: suspend () -> Unit) = withTimeout(10_000L) {
        coroutineScope {
            val entered = CompletableDeferred<Unit>()
            val release = CompletableDeferred<Unit>()
            beforeWrite.set { entered.complete(Unit); release.await() }
            val pending = async(Dispatchers.Default) { first() }
            try {
                entered.await()
                second()
            } finally {
                release.complete(Unit)
            }
            pending.await()
        }
    }

    private fun addon(id: String) = Addon(
        id, id, "1.0.0", "", true, true, AddonType.CUSTOM, url = "https://$id.example/manifest.json"
    )
}
