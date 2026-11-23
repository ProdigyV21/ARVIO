package com.arflix.tv.ui.screens.tv.live

import android.app.Application
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.arflix.tv.data.model.IptvChannel
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
 * Renders the Sports page with sample games to a PNG, for reviewing layout changes
 * without a TV. Only runs when the ARVIO_SCREENSHOTS environment variable names an output folder.
 */
@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], application = Application::class, qualifiers = "w960dp-h540dp-land-xhdpi")
class SportsGuidePaneScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun renderSportsPage() {
        val out = System.getenv("ARVIO_SCREENSHOTS")
        assumeTrue(!out.isNullOrBlank())
        val now = 1_800_000_000_000L
        fun channel(n: Int, name: String) = IptvChannel("p:$n", name, "https://example.com/$n.m3u8", "Sports")
        fun game(id: String, title: String, sport: GuideSport, startIn: Long, competition: String, vararg channels: IptvChannel) =
            SportsGuideEvent(id, title, sport,
                IptvProgram(title, startUtcMillis = now + startIn, endUtcMillis = now + startIn + 7_200_000L),
                channels.toList(), competition = competition,
                artwork = System.getenv("ARVIO_SCREENSHOT_ART")?.let { "$it/game$id.png" })
        val events = listOf(
            game("1", "Arsenal vs Chelsea", GuideSport.FOOTBALL, -1_800_000L, "Premier League", channel(1, "Sky Sports Main Event"), channel(2, "Sky Sports PL")),
            game("2", "Real Madrid vs Barcelona", GuideSport.FOOTBALL, -600_000L, "La Liga", channel(3, "LaLiga TV")),
            game("3", "Lakers vs Celtics", GuideSport.BASKETBALL, 3_600_000L, "NBA", channel(4, "NBA TV")),
            game("4", "Ajax vs PSV", GuideSport.FOOTBALL, 7_200_000L, "Eredivisie", channel(5, "ESPN 1")),
            game("5", "Djokovic vs Alcaraz", GuideSport.TENNIS, 10_800_000L, "ATP Finals", channel(6, "Sky Sports Tennis")),
            game("6", "Bayern vs Dortmund", GuideSport.FOOTBALL, 90_000_000L, "Bundesliga", channel(7, "Sky Sport Bundesliga")),
            game("7", "Verstappen: Abu Dhabi GP", GuideSport.F1, 100_000_000L, "Formula 1", channel(8, "Sky Sports F1")),
        )
        // The pane has looping animations, so drive the clock by hand instead of
        // waiting for Compose to go idle.
        compose.mainClock.autoAdvance = false
        compose.setContent {
            SportsGuidePane(
                events = events, now = now, loading = false, focusSignal = 1,
                onContentFocused = {}, onOpenCategories = {}, onPlay = {},
            )
        }
        repeat(40) {
            compose.mainClock.advanceTimeBy(100)
            Thread.sleep(50) // rows are built on a background dispatcher
        }
        // Draw the window straight into a bitmap (PixelCopy never completes on the JVM).
        val view = compose.activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(android.graphics.Canvas(bitmap)) }
        File(out).mkdirs()
        File(out!!, "sports-page.png").outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }
}
