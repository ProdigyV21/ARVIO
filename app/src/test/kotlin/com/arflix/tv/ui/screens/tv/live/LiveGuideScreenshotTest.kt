package com.arflix.tv.ui.screens.tv.live

import android.app.Application
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.media3.exoplayer.ExoPlayer
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
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
 * Renders the Live TV page (info/preview row above the guide) with sample channels
 * to a PNG, for reviewing layout changes without a TV. Only runs when the
 * ARVIO_SCREENSHOTS environment variable names an output folder.
 */
@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], application = Application::class, qualifiers = "w960dp-h540dp-land-xhdpi")
class LiveGuideScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun renderLiveTvPage() {
        val out = System.getenv("ARVIO_SCREENSHOTS")
        assumeTrue(!out.isNullOrBlank())
        val now = 1_800_000_000_000L - (1_800_000_000_000L % 1_800_000L) + 600_000L
        val slot = 1_800_000L
        val names = listOf(
            "NPO 1 HD" to Genre.General, "RTL 4 HD" to Genre.General, "Ziggo Sport" to Genre.Sports,
            "ESPN 1" to Genre.Sports, "BBC One" to Genre.General, "CNN International" to Genre.News,
            "Discovery" to Genre.Docs, "Cartoon Network" to Genre.Kids, "MTV" to Genre.Music,
            "Sky Cinema" to Genre.Movies, "Eurosport 1" to Genre.Sports, "National Geographic" to Genre.Docs,
        )
        val titles = listOf("NOS Journaal", "Boer zoekt Vrouw", "Eredivisie: Ajax - PSV", "SportsCenter", "EastEnders",
            "World News", "Gold Rush", "Gumball", "Top 40", "Dune: Part Two", "Tennis: ATP Finals", "Air Crash Investigation")
        val channels = names.mapIndexed { i, (name, genre) ->
            EnrichedChannel(
                IptvChannel("ch$i", name, "https://example.com/$i.m3u8", genre.name),
                number = i + 1, country = "NL", genre = genre, quality = if (i % 3 == 0) Quality.FHD else Quality.HD,
                lang = "NL", brandBg = Color(0xFF1F2937), brandFg = Color.White, isAdult = false,
            )
        }
        val guide = channels.mapIndexed { i, ch ->
            val start = now - 300_000L - (i % 3) * 300_000L
            fun p(n: Int, title: String) = IptvProgram(title, description = "Sample programme for the screenshot.",
                startUtcMillis = start + n * slot, endUtcMillis = start + (n + 1) * slot)
            ch.id to IptvNowNext(now = p(0, titles[i]), next = p(1, "Next on ${ch.name}"), later = p(2, "Later on ${ch.name}"),
                upcoming = (3..6).map { p(it, "Programme $it") })
        }.toMap()
        compose.mainClock.autoAdvance = false
        compose.setContent {
            val player = androidx.compose.runtime.remember { ExoPlayer.Builder(compose.activity).build() }
            Column(Modifier.fillMaxSize().background(LiveColors.Bg)) {
                MiniPlayerRow(
                    exoPlayer = player, channel = channels[2], clockTickMillis = now, nowNext = guide["ch2"],
                    favoriteSet = setOf("ch2"), onFavoriteToggle = {}, playerActive = false,
                    modifier = Modifier.fillMaxWidth(),
                )
                EpgGrid(
                    channels = channels, clockTickMillis = now, nowNext = guide, selectedChannelId = "ch2",
                    playingChannelId = "ch2", focusSelectedChannelSignal = 0, onChannelSelect = {},
                    favorites = setOf("ch2"), modifier = Modifier.weight(1f),
                )
            }
        }
        repeat(30) {
            compose.mainClock.advanceTimeBy(100)
            Thread.sleep(30)
        }
        val view = compose.activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(android.graphics.Canvas(bitmap)) }
        File(out!!).mkdirs()
        File(out, "live-tv-page.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }
}
