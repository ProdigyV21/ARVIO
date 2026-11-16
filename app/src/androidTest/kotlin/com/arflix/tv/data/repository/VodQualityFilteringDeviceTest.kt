package com.arflix.tv.data.repository

import android.graphics.Bitmap
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.datastore.preferences.core.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem as PlayerMediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import com.arflix.tv.network.TmdbPriorityDispatcher
import com.arflix.tv.ui.components.StreamSelector
import com.arflix.tv.ui.screens.details.DetailsUiState
import com.arflix.tv.ui.screens.details.DetailsViewModel
import com.arflix.tv.ui.screens.player.PlayerViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import io.mockk.*
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Real Xtream HTTP parsing, source resolvers, view models, source picker and Media3; no live account. */
@RunWith(AndroidJUnit4::class)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class VodQualityFilteringDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val context get() = instrumentation.targetContext
    private val store = ViewModelStore()
    private val models = mutableListOf<ViewModel>()
    private val requests = CopyOnWriteArrayList<String>()
    private lateinit var server: MockWebServer
    private lateinit var streams: StreamRepository
    private lateinit var iptv: IptvRepository
    private lateinit var profiles: ProfileManager
    private lateinit var integrations: StreamIntegrationRepository
    private val rules = listOf(QualityFilterConfig(regexPattern = "\\b(CAM|TELESYNC)\\b"))

    @Before fun setup(): Unit = runBlocking {
        val bytes = instrumentation.context.assets.open("iptv_vod_unknown_duration.ts").use { it.readBytes() }
        server = MockWebServer().apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val url = checkNotNull(request.requestUrl)
                    requests += url.encodedPath + (url.query?.let { "?$it" } ?: "")
                    if (url.encodedPath.startsWith("/movie/") || url.encodedPath.startsWith("/series/")) {
                        return MockResponse().addHeader("Content-Type", "video/mp2t")
                            .setBody(Buffer().write(bytes))
                    }
                    val json = when (url.queryParameter("action")) {
                        "get_vod_streams" -> """[
                            {"stream_id":101,"name":"Example Movie CAM 2160p","tmdb":"123","container_extension":"ts"},
                            {"stream_id":102,"name":"Example Movie 1080p WEB-DL","tmdb":"123","container_extension":"ts"},
                            {"stream_id":103,"name":"Example Movie TELESYNC 1080p","tmdb":"123","container_extension":"ts"}
                        ]"""
                        "get_series" -> """[
                            {"series_id":51,"name":"Example Show CAM UHD","tmdb":"456"},
                            {"series_id":52,"name":"Example Show FHD","tmdb":"456"},
                            {"series_id":53,"name":"Example Show TELESYNC FHD","tmdb":"456"}
                        ]"""
                        "get_series_info" -> {
                            val id = checkNotNull(url.queryParameter("series_id")).toInt()
                            """{"info":{"name":"Example Show","tmdb":"456"},"episodes":{"1":[
                                {"id":${id * 100 + 2},"season":1,"episode_num":2,"title":"Episode 2","container_extension":"ts"}
                            ]}}"""
                        }
                        null -> """{"user_info":{"auth":1,"status":"Active","exp_date":"4102444800","active_cons":"0","max_connections":"10"}}"""
                        else -> "[]"
                    }
                    return MockResponse().addHeader("Content-Type", "application/json").setBody(json)
                }
            }
            start()
        }
        profiles = mockk(relaxed = true) {
            every { activeProfileId } returns MutableStateFlow("issue873")
            every { currentProfileId } returns MutableStateFlow("issue873")
            every { getProfileIdSync() } returns "issue873"
            coEvery { getProfileId() } returns "issue873"
            every { profileStringKey(any()) } answers { stringPreferencesKey("profile_issue873_${firstArg<String>()}") }
            every { profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
            every { profileBooleanKey(any()) } answers { booleanPreferencesKey("profile_issue873_${firstArg<String>()}") }
            every { profileBooleanKeyFor(any(), any()) } answers { booleanPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
            every { profileLongKey(any()) } answers { longPreferencesKey("profile_issue873_${firstArg<String>()}") }
            every { profileLongKeyFor(any(), any()) } answers { longPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
            every { profileIntKey(any()) } answers { intPreferencesKey("profile_issue873_${firstArg<String>()}") }
            every { profileIntKeyFor(any(), any()) } answers { intPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
        }
        context.settingsDataStore.edit { it[stringPreferencesKey("quality_filters")] = Gson().toJson(rules) }
        integrations = mockk(relaxed = true) {
            coEvery { isIntegrationEnabled(any()) } answers { firstArg<StreamIntegrationType>() == StreamIntegrationType.IPTV_VOD }
            coEvery { enabledProviderIds(StreamIntegrationType.IPTV_VOD, any()) } returns setOf("iptv_playlist:issue873")
            every { getUnifiedSourceOrderedIds() } returns flowOf(emptyList())
            coEvery { getUnifiedSourceOrderedIdsSync() } returns emptyList()
        }
        val client = OkHttpClient()
        iptv = IptvRepository(context, client, profiles, CloudSyncInvalidationBus())
        iptv.savePlaylists(listOf(IptvPlaylistEntry(
            "issue873", "Fixture", server.url("/get.php?username=user&password=pass&type=m3u_plus&output=ts").toString()
        )))
        streams = StreamRepository(context, mockk(relaxed = true), client, profiles,
            mockk(relaxed = true), iptv, mockk(relaxed = true), mockk(relaxed = true),
            CloudSyncInvalidationBus(), mockk(relaxed = true), integrations)
        val scope = field(streams, "repositoryScope").get(streams) as CoroutineScope
        withTimeout(10_000) { scope.coroutineContext[Job]!!.children.toList().forEach { it.join() } }
        streams.updateQualityFiltersCache(rules)
    }

    @After fun cleanup() = runBlocking {
        val jobs = models.mapNotNull { it.viewModelScope.coroutineContext[Job] }
        withContext(Dispatchers.Main) { store.clear() }
        withTimeout(10_000) { jobs.forEach { it.join() } }
        if (::streams.isInitialized) {
            (field(streams, "repositoryScope").get(streams) as CoroutineScope).coroutineContext[Job]?.cancelAndJoin()
        }
        if (::server.isInitialized) server.shutdown()
    }

    private fun field(owner: Any, name: String) = owner.javaClass.getDeclaredField(name).apply { isAccessible = true }

    private suspend fun invokeSuspend(owner: Any, name: String, vararg arguments: Any?): Unit = suspendCoroutine { continuation ->
        try {
            val method = owner.javaClass.declaredMethods.single { it.name == name }.apply { isAccessible = true }
            val result = method.invoke(owner, *(arguments.toList() + continuation).toTypedArray())
            if (result !== COROUTINE_SUSPENDED) continuation.resume(Unit)
        } catch (error: InvocationTargetException) {
            continuation.resumeWithException(error.targetException)
        }
    }

    private fun media() = mockk<MediaRepository>(relaxed = true) {
        every { episodeRatingsUpdated } returns MutableSharedFlow<Pair<Int, Int>>()
    }

    private fun kids() = mockk<KidsContentFilter>(relaxed = true) {
        every { state } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
    }

    private suspend fun details(type: MediaType): DetailsViewModel = withContext(Dispatchers.Main) {
        DetailsViewModel(context, media(), mockk(relaxed = true), mockk(relaxed = true), profiles,
            mockk(relaxed = true), mockk(relaxed = true), streams, mockk(relaxed = true),
            TmdbPriorityDispatcher(), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), integrations,
            mockk(relaxed = true), mockk(relaxed = true), kids(),
            IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))).also { model ->
            store.put("details", model)
            models += model
            field(model, "currentMediaType").set(model, type)
            field(model, "currentMediaId").setInt(model, if (type == MediaType.MOVIE) 123 else 456)
            @Suppress("UNCHECKED_CAST")
            val state = field(model, "_uiState").get(model) as MutableStateFlow<DetailsUiState>
            state.value = state.value.copy(item = MediaItem(if (type == MediaType.MOVIE) 123 else 456,
                if (type == MediaType.MOVIE) "Example Movie" else "Example Show", mediaType = type))
            val request = field(model, "loadStreamsRequestId").getLong(model)
            invokeSuspend(model, "appendVodSourceInBackground", "tt123", 1, 2, 15_000L,
                request, type, if (type == MediaType.MOVIE) 123 else 456)
        }
    }

    private fun showSources(model: DetailsViewModel, onSelect: (StreamSource) -> Unit) {
        compose.setContent {
            val state by model.uiState.collectAsState()
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    Box(Modifier.fillMaxSize()) {
                        StreamSelector(true, state.streams, null, onSelect = onSelect)
                    }
                }
            }
        }
        compose.waitUntil(5_000) { compose.onAllNodes(isFocused()).fetchSemanticsNodes().size == 1 }
    }

    @Test fun detailsMovieSourcesHideCamAndTelesyncAndTheCleanCopyIsSelectable() = runBlocking {
        val model = details(MediaType.MOVIE)
        assertEquals(listOf("Example Movie 1080p WEB-DL"), model.uiState.value.streams.map { it.source })
        var selected: StreamSource? = null
        showSources(model) { selected = it }
        compose.onNodeWithText("Example Movie 1080p WEB-DL").assertIsDisplayed()
        compose.onAllNodesWithText("CAM", substring = true).assertCountEquals(0)
        compose.onAllNodesWithText("TELESYNC", substring = true).assertCountEquals(0)
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER)
        compose.waitForIdle()
        assertTrue(checkNotNull(selected).url!!.endsWith("/102.ts"))
        capture("movie-sources")
    }

    @Test fun episodeSeriesMarkersAreFilteredAndCachedCopiesReturnWhenRulesAreDisabled() = runBlocking {
        val model = details(MediaType.TV)
        val clean = model.uiState.value.streams
        assertEquals(1, clean.size)
        assertEquals("Episode 2", clean.single().source)
        assertTrue(clean.single().url!!.endsWith("/5202.ts"))
        showSources(model) { }
        compose.onNodeWithText("Episode 2").assertIsDisplayed()
        capture("episode-sources")
        val before = requests.count { it.contains("action=get_series") }
        streams.updateQualityFiltersCache(emptyList())
        val all = streams.resolveEpisodeVodSources("tt123", 1, 2, "Example Show", tmdbId = 456)
        assertEquals(3, all.size)
        assertTrue(all.any { it.rawLabel?.contains("CAM") == true })
        assertEquals("Changing filters must reuse the raw provider cache", before,
            requests.count { it.contains("action=get_series") })
        streams.updateQualityFiltersCache(rules)
        assertEquals(clean.map { it.url }, streams.resolveEpisodeVodSources("tt123", 1, 2, "Example Show", tmdbId = 456).map { it.url })
    }

    @Test fun playerBackgroundLookupAutoplaysOnlyTheAllowedMovieAndMedia3RendersIt() = playerAutoplay(MediaType.MOVIE)

    @Test fun playerBackgroundLookupAutoplaysOnlyTheAllowedEpisodeAndMedia3RendersIt() = playerAutoplay(MediaType.TV)

    private fun playerAutoplay(type: MediaType) = runBlocking {
        val model = withContext(Dispatchers.Main) {
            PlayerViewModel(context, profiles, media(), streams, mockk(relaxed = true), mockk(relaxed = true),
                mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
                mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
                integrations, mockk(relaxed = true), kids(),
                IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))).also {
                store.put("player", it)
                models += it
                field(it, "currentMediaId").setInt(it, if (type == MediaType.MOVIE) 123 else 456)
                field(it, "currentMediaType").set(it, type)
                field(it, "currentItemTitle").set(it, if (type == MediaType.MOVIE) "Example Movie" else "Example Show")
            }
        }
        withContext(Dispatchers.Main) {
            invokeSuspend(model, "appendVodSourceInBackground", type, "tt123", 1, 2, 15_000L)
        }
        val state = withTimeout(30_000) { model.uiState.first { it.selectedStreamUrl != null } }
        assertEquals(1, state.streams.size)
        assertTrue(state.selectedStreamUrl!!.endsWith(if (type == MediaType.MOVIE) "/102.ts" else "/5202.ts"))
        val firstFrame = AtomicBoolean(false)
        val player = withContext(Dispatchers.Main) {
            ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onRenderedFirstFrame() { firstFrame.set(true) }
                })
            }
        }
        compose.setContent { AndroidView(modifier = Modifier.fillMaxSize(), factory = {
            PlayerView(it).apply { this.player = player; useController = false }
        }) }
        try {
            withContext(Dispatchers.Main) {
                player.setMediaItem(PlayerMediaItem.fromUri(checkNotNull(state.selectedStreamUrl)))
                player.prepare()
                player.play()
                withTimeout(20_000) {
                    while (!firstFrame.get() || player.currentPosition < 100) {
                        player.playerError?.let { throw AssertionError("Fixture playback failed", it) }
                        delay(25)
                    }
                }
            }
            assertTrue("No excluded copy may be opened or warmed", requests.none {
                it.substringBefore('?').let { path ->
                    path.endsWith("/101.ts") || path.endsWith("/103.ts") ||
                        path.endsWith("/5102.ts") || path.endsWith("/5302.ts")
                }
            })
            capture("autoplay-${type.name}")
        } finally {
            withContext(Dispatchers.Main) { player.release() }
        }
    }

    private fun capture(name: String) {
        val screenshot = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
        File(compose.activity.getExternalFilesDir(null), "issue873-$name.png").outputStream().use {
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        screenshot.recycle()
    }
}
