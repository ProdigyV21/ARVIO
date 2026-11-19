package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.util.PinUtil
import com.arflix.tv.util.settingsDataStore
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class YouTubeCatalogSafetyTest {
    private val state = MutableStateFlow(emptyPreferences())
    private var profile = Profile(id = "p", name = "Child", isKidsProfile = true, parentPin = PinUtil.hashPin("1234"))
    private lateinit var repository: CatalogRepository
    private val key = stringPreferencesKey("profile_p_catalogs_v1")

    @Before fun setup() {
        val mutex = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                mutex.withLock { transform(state.value).toPreferences().also { state.value = it } }
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().settingsDataStore } returns store
        val manager = mockk<ProfileManager>(relaxed = true)
        every { manager.getProfileIdSync() } answers { profile.id }
        coEvery { manager.getActiveProfile() } answers { profile }
        repository = spyk(CatalogRepository(RuntimeEnvironment.getApplication(), manager,
            mockk(relaxed = true), mockk(relaxed = true), CloudSyncInvalidationBus()))
        coEvery { repository["fetchUrl"](any<String>(), any<Boolean>()) } returns "{\"title\":\"Test video\"}"
    }

    @After fun cleanup() { unmockkStatic("com.arflix.tv.util.DataStoresKt") }

    @Test fun additionsRequireParentApprovalInTheRepository() = runBlocking {
        for (pin in listOf(null, "4321")) {
            val result = repository.addYouTubeVideo("https://youtu.be/abcDEF12_-x", parentPin = pin)
            assertEquals(R.string.youtube_parent_approval_required, (result.exceptionOrNull() as CatalogException).messageRes)
            assertNull(state.value[key])
        }
        assertTrue(repository.addYouTubeVideo("https://youtu.be/abcDEF12_-x", parentPin = "1234").isSuccess)
        assertEquals(1, YouTubeCollection.decode(repository.getCatalogsForProfile("p").first().sourceRef).size)
    }

    @Test fun fullCollectionRejectsTheAdditionWithoutRemovingAnything() = runBlocking {
        profile = profile.copy(isKidsProfile = false)
        val videos = (0 until YouTubeCollection.MAX_VIDEOS).map {
            YouTubeCollection.Video(it.toString().padStart(11, '0'), "Video $it")
        }
        repository.replaceCatalogsForProfile("p", listOf(CatalogConfig(
            id = "youtube_test", title = "YouTube", sourceType = CatalogSourceType.YOUTUBE,
            sourceRef = YouTubeCollection.encode(videos), isPreinstalled = false
        )))
        val before = state.value[key]
        val result = repository.addYouTubeVideo("https://youtu.be/abcDEF12_-x")
        assertEquals(R.string.youtube_collection_full, (result.exceptionOrNull() as CatalogException).messageRes)
        assertEquals(before, state.value[key])
    }

    @Test fun changingProfilesDuringMetadataFetchDoesNotSaveOrReuseApproval() = runBlocking {
        coEvery { repository["fetchUrl"](any<String>(), any<Boolean>()) } answers {
            profile = profile.copy(id = "other")
            "{\"title\":\"Video\"}"
        }
        val result = repository.addYouTubeVideo("https://youtu.be/abcDEF12_-x", parentPin = "1234")
        assertEquals(R.string.youtube_profile_changed, (result.exceptionOrNull() as CatalogException).messageRes)
        assertNull(state.value[key])
    }

    @Test fun changingTheParentPinDuringMetadataFetchRevokesApproval() = runBlocking {
        coEvery { repository["fetchUrl"](any<String>(), any<Boolean>()) } answers {
            profile = profile.copy(parentPin = PinUtil.hashPin("5678"))
            "{\"title\":\"Video\"}"
        }
        val result = repository.addYouTubeVideo("https://youtu.be/abcDEF12_-x", parentPin = "1234")
        assertEquals(R.string.youtube_parent_approval_required, (result.exceptionOrNull() as CatalogException).messageRes)
        assertNull(state.value[key])
    }
}
