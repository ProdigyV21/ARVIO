package com.arflix.tv.ui.screens.music

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import android.os.Looper
import androidx.media3.common.Player
import androidx.test.core.app.ApplicationProvider
import coil.Coil
import coil.ImageLoader
import coil.map.Mapper
import com.arflix.tv.music.MusicAddonSource
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.robolectric.Shadows.shadowOf
import com.arflix.tv.music.MusicAddon
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicSearchResult
import com.arflix.tv.music.MusicArtist
import com.arflix.tv.music.MusicCollections
import com.arflix.tv.music.MusicLibrary
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.music.MusicShelf
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.ui.components.AppBottomBar
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * Renders the Music tab to PNGs, for reviewing the phone layout without a device. Only runs when
 * the ARVIO_SCREENSHOTS environment variable names an output folder. With ARVIO_SCREENSHOTS_LIVE
 * set to a music addon address, the shelves, album and covers come from that addon instead of
 * the built-in sample data.
 */
@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], application = Application::class, qualifiers = "w393dp-h851dp-port-xxhdpi")
class MusicScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val out: String? = System.getenv("ARVIO_SCREENSHOTS")

    private val actions = object : MusicActions {
        override fun connect(type: MusicServerType, url: String, username: String, password: String) = Unit
        override fun addAddon(url: String) = Unit
        override fun disconnect() = Unit
        override fun removeAddon(addon: MusicAddon) = Unit
        override fun refresh() = Unit
        override fun selectTab(tab: MusicTab) = Unit
        override fun setQuery(query: String) = Unit
        override fun openAlbum(album: MusicAlbum) = Unit
        override fun openArtist(artist: MusicArtist) = Unit
        override fun openArtist(id: String?, name: String) = Unit
        override fun back() = false
        override fun setPlayerOpen(open: Boolean) = Unit
        override fun play(tracks: List<MusicTrack>, index: Int) = Unit
        override fun shuffle(tracks: List<MusicTrack>) = Unit
        override fun togglePlay() = Unit
        override fun next() = Unit
        override fun previous() = Unit
        override fun seekTo(positionMs: Long) = Unit
        override fun skipTo(index: Int) = Unit
        override fun toggleShuffle() = Unit
        override fun cycleRepeat() = Unit
        override fun playAlbum(album: MusicAlbum) = Unit
        override fun toggleFavorite(track: MusicTrack) = Unit
        override fun toggleFavorite(album: MusicAlbum) = Unit
        override fun openPlaylist(id: String) = Unit
        override fun createPlaylist(name: String, tracks: List<MusicTrack>) = Unit
        override fun renamePlaylist(id: String, name: String) = Unit
        override fun deletePlaylist(id: String) = Unit
        override fun removeFromPlaylist(id: String, index: Int) = Unit
        override fun movePlaylistTrack(id: String, from: Int, to: Int) = Unit
        override fun showAddToPlaylist(tracks: List<MusicTrack>?) = Unit
        override fun addToPlaylist(id: String, tracks: List<MusicTrack>) = Unit
        override fun importSpotify(files: List<android.net.Uri>) = Unit
        override fun dismissImportResult() = Unit
    }

    /** Sample covers: colour gradients written to disk, loaded like real artwork. */
    private fun cover(name: String, from: Int, to: Int): String {
        val dir = File(System.getProperty("java.io.tmpdir"), "arvio-music-art").apply { mkdirs() }
        val file = File(dir, "$name.png")
        if (!file.exists()) {
            val bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
            val paint = Paint().apply { shader = LinearGradient(0f, 0f, 300f, 300f, from, to, Shader.TileMode.CLAMP) }
            Canvas(bitmap).drawRect(0f, 0f, 300f, 300f, paint)
            file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        return file.absolutePath
    }

    private val palette = listOf(
        0xFFFF5F6D.toInt() to 0xFFFFC371.toInt(),
        0xFF2193B0.toInt() to 0xFF6DD5ED.toInt(),
        0xFF8E2DE2.toInt() to 0xFF4A00E0.toInt(),
        0xFF11998E.toInt() to 0xFF38EF7D.toInt(),
        0xFFFC466B.toInt() to 0xFF3F5EFB.toInt(),
        0xFFF7971E.toInt() to 0xFFFFD200.toInt(),
        0xFF434343.toInt() to 0xFF000000.toInt(),
        0xFFEE0979.toInt() to 0xFFFF6A00.toInt(),
    )

    private fun albums(prefix: String, names: List<Pair<String, String>>) = names.mapIndexed { i, (name, artist) ->
        val (from, to) = palette[(i + prefix.length) % palette.size]
        MusicAlbum("$prefix$i", name, artist, "ar$i", cover("$prefix$i", from, to), 2020 + i % 5, 10 + i)
    }

    private val trending = albums("t", listOf(
        "Top 100: trending" to "Audius", "Electronic: trending" to "Audius", "Hip-hop & rap: trending" to "Audius",
        "Lo-fi: trending" to "Audius", "Jazz: trending" to "Audius",
    ))
    private val playlists = albums("p", listOf(
        "Night Drive" to "Neon Coast", "Sunday Morning" to "Lena Vos", "Late Bloom" to "The Paper Kites Club",
        "Field Notes" to "Orbit Ensemble", "Warm Static" to "Kairo",
    ))
    private val newest = albums("n", listOf(
        "Afterglow" to "Mira Holt", "Concrete Gardens" to "Sable Youth", "Low Tide" to "Wave Theory",
        "Glass Houses" to "Northbound", "Second Light" to "June Avery",
    ))

    private val album = playlists[0]
    private val tracks = listOf("Intro", "Night Drive", "Coastline", "Overpass (feat. Kairo)", "Silver Hours",
        "Static Bloom", "Exit 14", "Neon Rain", "Morning Coast").mapIndexed { i, title ->
        MusicTrack("tr$i", title, if (i == 3) "Neon Coast, Kairo" else "Neon Coast", album.name, album.id,
            150 + i * 23, album.coverUrl, "https://example.invalid/$i.mp3", i + 1)
    }

    private val library = MusicLibrary(
        addons = listOf(MusicAddon("https://resolve.arvio.tv/music/audius", "tv.arvio.music.audius", "Audius")),
    )

    private val home = MusicUiState(
        configLoaded = true,
        library = library,
        shelves = listOf(
            MusicShelf("trending", trending, title = "Audius: trending this week"),
            MusicShelf("playlists", playlists, title = "Audius: popular albums & playlists"),
            MusicShelf("newest", newest, title = "Recently added"),
        ),
    )

    private val playing = MusicPlaybackState(
        queue = tracks, index = 1, isPlaying = true, positionMs = 74_000, durationMs = 173_000,
        repeatMode = Player.REPEAT_MODE_OFF,
    )


    private val liveAddon: String? = System.getenv("ARVIO_SCREENSHOTS_LIVE")?.takeIf { it.isNotBlank() }

    /** Live album ids to leave out, comma separated, e.g. one with unsuitable cover art. */
    private val skip: Set<String> = System.getenv("ARVIO_SCREENSHOTS_SKIP").orEmpty().split(',').map { it.trim() }.filter { it.isNotEmpty() }.toSet()

    private class Sample(val home: MusicUiState, val album: MusicAlbum, val tracks: List<MusicTrack>, val artists: List<MusicArtist>)

    /** Downloads a cover once, so the screenshot loads it from disk like the sample covers. */
    private fun download(http: OkHttpClient, url: String?): String? {
        if (url.isNullOrBlank()) return null
        val dir = File(System.getProperty("java.io.tmpdir"), "arvio-music-live").apply { mkdirs() }
        val file = File(dir, "${url.hashCode().toUInt()}.img")
        if (!file.exists()) runCatching {
            http.newCall(Request.Builder().url(url).build()).execute().use { response ->
                if (!response.isSuccessful) return null
                file.outputStream().use { out -> response.body!!.byteStream().copyTo(out) }
            }
        }.onFailure { return null }
        return file.absolutePath
    }

    private val sample: Sample by lazy {
        val addonUrl = liveAddon ?: return@lazy Sample(home, album, tracks, sampleArtists)
        runBlocking {
            val http = OkHttpClient()
            val addon = MusicAddonSource.install(http, addonUrl)
            val source = MusicAddonSource(http, addon)
            val shelves = source.shelves().map { shelf ->
                shelf.copy(albums = shelf.albums.filterNot { it.id in skip }.take(8).map { it.copy(coverUrl = download(http, it.coverUrl)) })
            }
            // The first playlist-like album makes a better album page than a 100-track chart.
            val picked = shelves.drop(1).firstOrNull()?.albums?.firstOrNull() ?: shelves.first().albums.first()
            val liveTracks = source.albumTracks(picked.id).take(12).map { it.copy(coverUrl = download(http, it.coverUrl) ?: picked.coverUrl) }
            val liveArtists = shelves.flatMap { it.albums }.filter { it.coverUrl != null }
                .groupBy { it.artist }.entries.take(8)
                .map { (name, albums) -> MusicArtist(albums.first().artistId ?: name, name, albums.first().coverUrl, albums.size) }
            Sample(
                MusicUiState(configLoaded = true, library = MusicLibrary(addons = listOf(addon)), shelves = shelves),
                picked, liveTracks, liveArtists,
            )
        }
    }

    private val sampleArtists by lazy {
        listOf("Neon Coast", "Lena Vos", "Mira Holt", "Orbit Ensemble", "Kairo", "June Avery")
            .mapIndexed { i, name -> MusicArtist("a$i", name, cover("a$i", palette[i].first, palette[i].second), 2 + i) }
    }

    private fun playingOf(s: Sample) = playing.copy(queue = s.tracks, index = minOf(1, s.tracks.lastIndex).coerceAtLeast(0),
        durationMs = (s.tracks.getOrNull(1)?.durationSec ?: 173) * 1000L)

    @Test fun connectScreen() = render("music-1-add-source", MusicUiState(configLoaded = true), MusicPlaybackState())

    @Test fun homeScreen() = render("music-2-home", sample.home, playingOf(sample))

    @Test fun albumPage() = render(
        "music-3-album",
        sample.home.copy(collections = collectionsOf(sample), pages = listOf(MusicPage.Album(sample.album, sample.tracks, loading = false))),
        playingOf(sample),
    )

    @Test fun artistsTab() = render("music-4-artists", sample.home.copy(tab = MusicTab.ARTISTS, artists = sample.artists), playingOf(sample))

    @Test fun player() = render("music-5-player", sample.home.copy(playerOpen = true, collections = collectionsOf(sample)), playingOf(sample))

    /** A few liked songs, a favourite album and two playlists made from the sample tracks. */
    private fun collectionsOf(s: Sample): MusicCollections {
        val tracks = s.tracks
        return MusicCollections(favoriteAlbums = listOf(s.album) + s.home.shelves.flatMap { it.albums }.filter { it.coverUrl != null }.take(3))
            .let { c -> tracks.take(5).fold(c) { acc, t -> acc.toggleFavorite(t) } }
            .createPlaylist("p1", "Late night", tracks.drop(2).take(6))
            .createPlaylist("p2", "Gym", tracks.reversed().take(4))
    }

    @Test fun myMusic() = render("music-6-my-music", sample.home.copy(tab = MusicTab.MINE, collections = collectionsOf(sample)), playingOf(sample))

    @Test fun playlistPage() = render(
        "music-7-playlist",
        sample.home.copy(collections = collectionsOf(sample), pages = listOf(MusicPage.Playlist("p1"))),
        playingOf(sample),
    )

    /** With ARVIO_SCREENSHOTS_CHARTS set to an app language (de-DE), the live charts home and a playlist search. */
    private val chartsLanguage: String? = System.getenv("ARVIO_SCREENSHOTS_CHARTS")?.takeIf { it.isNotBlank() }

    private fun withLocalCovers(http: OkHttpClient, albums: List<MusicAlbum>) = albums.take(8).map { it.copy(coverUrl = download(http, it.coverUrl)) }

    @Test fun chartsHome() {
        val language = chartsLanguage
        org.junit.Assume.assumeTrue(language != null)
        val http = OkHttpClient()
        val shelves = runBlocking { com.arflix.tv.music.ChartsSource(http, language!!).shelves() }
            .take(6).map { it.copy(key = "1/" + it.key, albums = withLocalCovers(http, it.albums)) }
        render("music-9-charts-home", sample.home.copy(shelves = shelves), playingOf(sample))
    }

    @Test fun playlistSearch() {
        val language = chartsLanguage
        org.junit.Assume.assumeTrue(language != null)
        val http = OkHttpClient()
        val query = "top gym hits"
        val found = runBlocking { com.arflix.tv.music.ChartsSource(http, language!!).search(query) }
        val results = MusicSearchResult(playlists = withLocalCovers(http, found.playlists), tracks = sample.tracks.take(6))
        render("music-10-search-playlists", sample.home.copy(query = query, results = results), playingOf(sample))
    }

    @Test fun addToPlaylist() = render(
        "music-8-add-to-playlist",
        sample.home.copy(
            collections = collectionsOf(sample),
            pages = listOf(MusicPage.Album(sample.album, sample.tracks, loading = false)),
            addingToPlaylist = sample.tracks.take(1),
        ),
        playingOf(sample),
    )

    private fun render(name: String, state: MusicUiState, playback: MusicPlaybackState) {
        assumeTrue(!out.isNullOrBlank())
        // Software bitmaps, so covers can be drawn into the screenshot canvas.
        Coil.setImageLoader(
            ImageLoader.Builder(ApplicationProvider.getApplicationContext<Application>()).allowHardware(false).crossfade(false)
                // A Windows path like C:\x.png would otherwise be read as a URI with scheme "C".
                .components { add(Mapper<String, File> { data, _ -> File(data).takeIf { it.isAbsolute && it.isFile } }) }
                .build()
        )
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Frame(showBar = !state.playerOpen) { MusicContent(state, playback, actions) }
            }
        }
        repeat(30) {
            compose.mainClock.advanceTimeBy(100)
            Thread.sleep(60) // covers decode off the main thread
            shadowOf(Looper.getMainLooper()).idle() // and are handed back on it
        }
        val view = compose.activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread {
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            // Dialogs open in windows of their own: dim the screen and draw each one centred, as Android shows it.
            dialogWindows(view).forEach { dialog ->
                canvas.drawColor(0x99000000.toInt())
                canvas.save()
                canvas.translate((bitmap.width - dialog.width) / 2f, (bitmap.height - dialog.height) / 2f)
                dialog.draw(canvas)
                canvas.restore()
            }
        }
        File(out!!).mkdirs()
        File(out, "$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    /** Root views of the windows other than the activity's own, such as an open dialog. */
    private fun dialogWindows(activityRoot: android.view.View): List<android.view.View> {
        val global = Class.forName("android.view.WindowManagerGlobal")
        val instance = global.getMethod("getInstance").invoke(null)
        val views = global.getDeclaredField("mViews").apply { isAccessible = true }.get(instance) as List<*>
        return views.filterIsInstance<android.view.View>().filter { it !== activityRoot && it.width > 0 && it.height > 0 }
    }

    /** The tab as it sits in the app: the bottom navigation bar over its lower edge. */
    @Composable
    private fun Frame(showBar: Boolean, content: @Composable () -> Unit) {
        Box(Modifier.fillMaxSize()) {
            CompositionLocalProvider(LocalBottomBarInset provides if (showBar) 64.dp else 0.dp) { content() }
            if (showBar) AppBottomBar(currentRoute = "music", onNavigate = {}, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
