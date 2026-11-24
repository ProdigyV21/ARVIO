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
import androidx.media3.common.Player
import com.arflix.tv.music.MusicAddon
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicArtist
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
 * Renders the Music tab with sample data to PNGs, for reviewing the phone layout without a
 * device. Only runs when the ARVIO_SCREENSHOTS environment variable names an output folder.
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

    @Test fun connectScreen() = render("music-1-add-source", MusicUiState(configLoaded = true), MusicPlaybackState())

    @Test fun homeScreen() = render("music-2-home", home, playing)

    @Test fun albumPage() = render(
        "music-3-album",
        home.copy(pages = listOf(MusicPage.Album(album, tracks, loading = false))),
        playing,
    )

    @Test fun artistsTab() = render(
        "music-4-artists",
        home.copy(tab = MusicTab.ARTISTS, artists = listOf("Neon Coast", "Lena Vos", "Mira Holt", "Orbit Ensemble", "Kairo", "June Avery")
            .mapIndexed { i, name -> MusicArtist("a$i", name, cover("a$i", palette[i].first, palette[i].second), 2 + i) }),
        playing,
    )

    @Test fun player() = render("music-5-player", home.copy(playerOpen = true), playing)

    private fun render(name: String, state: MusicUiState, playback: MusicPlaybackState) {
        assumeTrue(!out.isNullOrBlank())
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Frame(showBar = !state.playerOpen) { MusicContent(state, playback, actions) }
            }
        }
        repeat(30) {
            compose.mainClock.advanceTimeBy(100)
            Thread.sleep(60) // covers decode off the main thread
        }
        val view = compose.activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(bitmap)) }
        File(out!!).mkdirs()
        File(out, "$name.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
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
