package com.arflix.tv.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.background
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.decode.DataSource
import coil.decode.SvgDecoder
import coil.fetch.DrawableResult
import coil.fetch.Fetcher
import coil.request.Options
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.screens.home.MobileHeroCarousel
import com.arflix.tv.ui.screens.details.DetailsContent
import com.arflix.tv.ui.focus.FocusSection
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import java.util.concurrent.ConcurrentHashMap
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MobileHomeArtworkDeviceTest {
    @get:Rule val compose = createComposeRule()
    private var loader: ImageLoader? = null
    private var originalLoader: ImageLoader? = null
    private val requested = ConcurrentHashMap.newKeySet<String>()

    @After fun releaseLoader() {
        originalLoader?.let { Coil.setImageLoader(it) }
        loader?.shutdown()
    }

    private fun fixtureLoader(): ImageLoader {
        val context = ApplicationProvider.getApplicationContext<Context>()
        originalLoader = context.imageLoader
        return ImageLoader.Builder(context).components {
            add(SvgDecoder.Factory())
            add(object : Fetcher.Factory<Uri> {
                override fun create(data: Uri, options: Options, imageLoader: ImageLoader): Fetcher? {
                    if (data.scheme != "fixture") return null
                    return Fetcher {
                        val name = data.host.orEmpty()
                        if (name == "broken-logo") throw java.io.IOException("Fixture logo unavailable")
                        requested.add(name)
                        val bitmap = when (name) {
                            "poster" -> Bitmap.createBitmap(80, 120, Bitmap.Config.ARGB_8888).apply {
                                eraseColor(android.graphics.Color.BLUE)
                                for (y in 0 until height) for (x in 0 until 4) {
                                    setPixel(x, y, android.graphics.Color.RED)
                                    setPixel(width - x - 1, y, android.graphics.Color.GREEN)
                                }
                            }
                            "logo" -> Bitmap.createBitmap(80, 40, Bitmap.Config.ARGB_8888).apply {
                                eraseColor(android.graphics.Color.MAGENTA)
                            }
                            else -> Bitmap.createBitmap(160, 90, Bitmap.Config.ARGB_8888).apply {
                                eraseColor(android.graphics.Color.YELLOW)
                            }
                        }
                        DrawableResult(BitmapDrawable(context.resources, bitmap), false, DataSource.NETWORK)
                    }
                }
            })
        }.build().also { loader = it; Coil.setImageLoader(it) }
    }

    private fun hasCompletePoster(): Boolean {
        val pixels = compose.onNodeWithTag("mobile_hero_pager").captureToImage().toPixelMap()
        val y = pixels.height / 10
        val left = pixels[4, y]
        val right = pixels[pixels.width - 5, y]
        return left.red > 0.8f && left.green < 0.2f && right.green > 0.8f && right.red < 0.2f
    }

    private fun assertCentered(tag: String) {
        val hero = compose.onNodeWithTag("mobile_hero_pager").fetchSemanticsNode().boundsInRoot
        val content = compose.onAllNodesWithTag(tag, useUnmergedTree = true)
            .fetchSemanticsNodes().maxBy { it.boundsInRoot.width }.boundsInRoot
        assertEquals("$tag must be centred", hero.center.x, content.center.x, 2f)
    }

    @Test fun posterFooterMasksPrintedTitlesWithoutDarkeningUpperArtwork() {
        val background = mutableStateOf(BackgroundDark)
        compose.setContent {
            Box(Modifier.width(240.dp).height(400.dp).background(Color.White).testTag("poster_scrim")) {
                MobileHeroScrim(true, background.value, Modifier.fillMaxSize(), posterArtwork = true)
            }
        }
        for (colour in listOf(BackgroundDark, Color.Black)) {
            compose.runOnIdle { background.value = colour }
            val pixels = compose.onNodeWithTag("poster_scrim").captureToImage().toPixelMap()
            val x = pixels.width / 2
            assertTrue(pixels[x, 0].red > 0.85f)
            assertTrue(pixels[x, pixels.height * 4 / 10].red > 0.97f)
            assertTrue("Printed poster titles must stay behind the clearlogo",
                pixels[x, pixels.height * 8 / 10].red < colour.red + 0.12f)
            assertEquals(colour.red, pixels[x, pixels.height - 1].red, 0.01f)
        }
    }

    @Test fun bothPortraitStylesUseTheWholePosterAndCentreActualLogoPixels() {
        fixtureLoader()
        val posterStyle = mutableStateOf(false)
        var opened = 0
        val item = MediaItem(1, "Hero title", image = "fixture://poster", backdrop = "fixture://backdrop",
            genreIds = listOf(28), year = "2026", imdbRating = "8.4")
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.width(240.dp).height(550.dp)) {
                    MobileHeroCarousel(listOf(Category("featured", "Featured", listOf(item))),
                        heroLayout = MobileHeroLayoutSpec(240f, 420f, 0f, false),
                        posterStyle = posterStyle.value,
                        cardLogoUrls = mapOf("${item.mediaType}_${item.id}" to "fixture://logo"),
                        onNavigateToDetails = { _, id, _, _ -> opened = id })
                }
            }
        }
        for (style in listOf(false, true)) {
            compose.runOnIdle { posterStyle.value = style }
            compose.waitUntil(8000) { hasCompletePoster() && requested.contains("logo") }
            assertFalse("A portrait hero must not request the landscape image", requested.contains("backdrop"))
            assertCentered("mobile_hero_genres")
            assertCentered("mobile_hero_metadata")
            compose.waitUntil(8000) {
                val pixels = compose.onNodeWithTag("mobile_hero_pager").captureToImage().toPixelMap()
                val magentaX = mutableListOf<Int>()
                for (y in pixels.height / 2 until pixels.height) for (x in 0 until pixels.width) {
                    val pixel = pixels[x, y]
                    if (pixel.red > 0.8f && pixel.blue > 0.8f && pixel.green < 0.2f) magentaX.add(x)
                }
                magentaX.isNotEmpty() && kotlin.math.abs(magentaX.average() - (pixels.width - 1) / 2.0) < 2.0
            }
        }
        compose.onNodeWithTag("mobile_hero_pager").performTouchInput { click(center) }
        compose.runOnIdle { assertEquals(1, opened) }
    }

    @Test fun rotationUsesBackdropInLandscapeAndRestoresPosterWithMissingPosterFallback() {
        fixtureLoader()
        val landscape = mutableStateOf(true)
        val poster = mutableStateOf("fixture://poster")
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.width(320.dp).height(580.dp)) {
                    MobileHeroCarousel(listOf(Category("featured", "Featured", listOf(
                        MediaItem(1, "Hero title", image = poster.value, backdrop = "fixture://backdrop")))),
                        heroLayout = if (landscape.value) MobileHeroLayoutSpec(320f, 180f, 0f, true)
                            else MobileHeroLayoutSpec(320f, 480f, 0f, false),
                        posterStyle = false,
                        onNavigateToDetails = { _, _, _, _ -> })
                }
            }
        }
        compose.waitUntil(8000) { requested.contains("backdrop") }
        assertFalse(requested.contains("poster"))
        compose.runOnIdle { landscape.value = false }
        compose.waitUntil(8000) { hasCompletePoster() }
        compose.runOnIdle { poster.value = "" }
        compose.waitUntil(8000) {
            val pixels = compose.onNodeWithTag("mobile_hero_pager").captureToImage().toPixelMap()
            val top = pixels[pixels.width / 2, 2]
            top.red > 0.8f && top.green > 0.8f && top.blue < 0.2f
        }
    }

    @Test fun missingClearlogoKeepsTitleAndMetadataCentred() {
        compose.setContent {
            Box(Modifier.width(320.dp).height(480.dp).testTag("mobile_hero_pager")) {
                MobileHeroBanner(imageUrl = "", title = "Chosen", genres = listOf("Drama"),
                    year = "2026", rating = "8.4", logoUrl = " ", immersive = true,
                    modifier = Modifier.matchParentSize())
            }
        }
        assertCentered("mobile_hero_title")
        assertCentered("mobile_hero_genres")
        assertCentered("mobile_hero_metadata")
        assertTrue(compose.onAllNodesWithTag("mobile_hero_logo", useUnmergedTree = true)
            .fetchSemanticsNodes().isEmpty())
    }

    @Test fun failedClearlogoShowsTheTitleAndANewLogoCanStillLoad() {
        fixtureLoader()
        val url = mutableStateOf("fixture://broken-logo")
        compose.setContent {
            Box(Modifier.width(320.dp).height(480.dp).testTag("mobile_hero_pager")) {
                MobileHeroBanner("", "A readable title", listOf("Drama"), "2026", "8.4",
                    logoUrl = url.value, immersive = true, modifier = Modifier.fillMaxSize())
            }
        }
        compose.waitUntil(8_000) {
            compose.onAllNodesWithTag("mobile_hero_title").fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithTag("mobile_hero_title").assertIsDisplayed()
        assertCentered("mobile_hero_title")
        compose.runOnIdle { url.value = "fixture://logo" }
        compose.waitUntil(8_000) { requested.contains("logo") }
        compose.onNodeWithTag("mobile_hero_logo").assertIsDisplayed()
    }

    @Test fun detailsAlsoRecoversFromAFailedClearlogo() {
        fixtureLoader()
        val url = mutableStateOf("fixture://broken-logo")
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.width(320.dp).height(580.dp)) {
                    DetailsContent(MediaItem(1, "A readable title"), logoUrl = url.value,
                        episodes = emptyList(), totalSeasons = 0, currentSeason = 1,
                        cast = emptyList(), reviews = emptyList(), similar = emptyList(),
                        similarLogoUrls = emptyMap(), focusedSection = FocusSection.BUTTONS,
                        buttonIndex = 0, episodeIndex = 0, ratingsIndex = 0, seasonIndex = 0,
                        castIndex = 0, reviewIndex = 0, similarIndex = 0,
                        isInWatchlist = false, isMobile = true)
                }
            }
        }
        compose.waitUntil(8_000) {
            compose.onAllNodes(androidx.compose.ui.test.hasText("A readable title"))
                .fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithText("A readable title").assertIsDisplayed()
        compose.runOnIdle { url.value = "fixture://logo" }
        compose.waitUntil(8_000) { requested.contains("logo") }
        compose.onNodeWithContentDescription("A readable title").assertIsDisplayed()
    }
}
