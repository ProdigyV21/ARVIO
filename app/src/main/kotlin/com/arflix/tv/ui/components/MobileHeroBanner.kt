package com.arflix.tv.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BorderLight
import com.arflix.tv.ui.theme.appBackgroundDark
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision

private val BannerShape = RoundedCornerShape(16.dp)
private val CardBorder = BorderLight
private val BottomScrim = Brush.verticalGradient(
    colorStops = arrayOf(
        0.00f to Color.Transparent,
        0.25f to Color.Transparent,
        0.48f to Color.Black.copy(alpha = 0.40f),
        0.66f to Color.Black.copy(alpha = 0.80f),
        0.82f to Color.Black.copy(alpha = 0.94f),
        1.00f to Color.Black
    )
)

@Composable
internal fun MobileHeroScrim(
    immersive: Boolean,
    background: Color,
    modifier: Modifier = Modifier,
    posterArtwork: Boolean = false,
) {
    val immersiveScrim = remember(background, posterArtwork) {
        Brush.verticalGradient(
            0f to Color.Black.copy(alpha = 0.10f),
            0.16f to Color.Transparent,
            0.40f to Color.Transparent,
            0.52f to background.copy(alpha = 0.06f),
            0.64f to background.copy(alpha = if (posterArtwork) 0.60f else 0.26f),
            0.76f to background.copy(alpha = if (posterArtwork) 1f else 0.62f),
            0.86f to background.copy(alpha = if (posterArtwork) 1f else 0.86f),
            0.94f to background.copy(alpha = if (posterArtwork) 1f else 0.97f),
            1f to background,
        )
    }
    Box(modifier) {
        Spacer(if (immersive || posterArtwork) Modifier.fillMaxSize().background(immersiveScrim)
            else Modifier.fillMaxWidth().fillMaxHeight(0.65f).align(Alignment.BottomCenter).background(BottomScrim))
    }
}

internal data class MobileHeroLayoutSpec(
    val cardWidthDp: Float,
    val cardHeightDp: Float,
    val carouselHorizontalPaddingDp: Float,
    val compactContent: Boolean,
)

internal fun resolveImmersiveMobileHeroLayout(widthDp: Float, usableHeightDp: Float): MobileHeroLayoutSpec {
    val width = widthDp.coerceAtLeast(1f)
    val height = usableHeightDp.coerceAtLeast(1f)
    return MobileHeroLayoutSpec(width, minOf(width * 1.65f, height * 0.88f), 0f, height < width)
}

/**
 * Resolves the mobile Home hero from the space that the Home viewport actually owns.
 * Portrait retains the original 3:4 card whenever it fits. Landscape uses a bounded
 * 16:9 banner so a wide window cannot turn into an extremely tall poster.
 */
internal fun resolveMobileHeroLayout(
    availableWidthDp: Float,
    availableHeightDp: Float,
): MobileHeroLayoutSpec {
    val width = availableWidthDp.coerceAtLeast(1f)
    val height = availableHeightDp.coerceAtLeast(1f)
    val landscape = width > height
    val horizontalPadding = 32f
    val pageWidth = (width - horizontalPadding * 2f).coerceAtLeast(1f)

    return if (landscape) {
        val heightLimit = minOf(height * 0.56f, 280f)
        val cardWidth = minOf(pageWidth, heightLimit * (16f / 9f))
        MobileHeroLayoutSpec(
            cardWidthDp = cardWidth,
            cardHeightDp = cardWidth / (16f / 9f),
            carouselHorizontalPaddingDp = (width - cardWidth) / 2f,
            compactContent = true,
        )
    } else {
        val heightLimit = height * 0.72f
        val cardHeight = minOf(pageWidth / (3f / 4f), heightLimit)
        val cardWidth = minOf(pageWidth, cardHeight * (3f / 4f))
        MobileHeroLayoutSpec(
            cardWidthDp = cardWidth,
            cardHeightDp = cardHeight,
            carouselHorizontalPaddingDp = (width - cardWidth) / 2f,
            compactContent = false,
        )
    }
}

/**
 * Netflix-style immersive mobile hero banner.
 *
 * Displays a full-bleed poster image with a layered dark scrim, a large title,
 * genre tags, and a metadata row showing the release year and IMDb rating.
 * No action buttons.
 *
 * @param imageUrl  URL of the backdrop / poster image.
 * @param title     Primary title shown as the visual anchor.
 * @param genres    List of genre strings displayed as a bullet-separated row.
 * @param year      Release year string (e.g. "2024"). Pass empty to hide.
 * @param rating    IMDb rating string (e.g. "8.7"). Pass empty to hide.
 * @param compactContent Use the condensed text treatment for short landscape banners.
 * @param modifier  Modifier applied to the card's outermost Box.
 */
@Composable
fun MobileHeroBanner(
    imageUrl: String,
    title: String,
    genres: List<String>,
    year: String = "",
    rating: String = "",
    logoUrl: String? = null,
    onClick: (() -> Unit)? = null,
    compactContent: Boolean = false,
    immersive: Boolean = false,
    showPageIndicators: Boolean = false,
    modifier: Modifier = Modifier,
    isPosterArtwork: Boolean = false,
) {
    val context = LocalContext.current
    var logoFailed by remember(title, logoUrl) { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(if (immersive) androidx.compose.ui.graphics.RectangleShape else BannerShape)
            .background(BackgroundCard)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .then(if (immersive) Modifier else Modifier.border(width = 1.dp, color = CardBorder, shape = BannerShape))
    ) {
        // ── Layer 1: Full-bleed background image ────────────────────────────
        if (imageUrl.isNotBlank()) {
            MobileDetailsArtwork(
                source = MobileDetailsArtworkSource(imageUrl, isPosterArtwork),
                modifier = Modifier.fillMaxSize(),
            )
        }

        MobileHeroScrim(immersive, appBackgroundDark(), Modifier.fillMaxSize(), posterArtwork = isPosterArtwork)

        // ── Layer 3: Content overlay — pinned to bottom-center ───────────────
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = if (compactContent) 14.dp else 20.dp)
                .padding(bottom = if (showPageIndicators) 32.dp else if (compactContent) 12.dp else 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (compactContent) 4.dp else 8.dp)
        ) {
            // Element A — Logo image when available, otherwise large title text
            if (!logoUrl.isNullOrBlank() && !logoFailed) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(logoUrl)
                        .precision(Precision.INEXACT)
                        .allowHardware(true)
                        .crossfade(300)
                        .build(),
                    contentDescription = title,
                    contentScale = ContentScale.Fit,
                    onError = { logoFailed = true },
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .height(if (compactContent) 38.dp else 56.dp)
                        .fillMaxWidth()
                        .testTag("mobile_hero_logo")
                )
            } else {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = if (!immersive) 18.sp else if (compactContent) 24.sp else 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = if (!immersive) 24.sp else if (compactContent) 28.sp else 42.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag("mobile_hero_title"),
                    maxLines = if (!immersive || compactContent) 2 else 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Element B — Genre tags with bullet separators
            BannerGenres(genres = genres, compact = compactContent)

            // Element C — Year and IMDb rating
            BannerMeta(year = year, rating = rating)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Private sub-composables
// ─────────────────────────────────────────────────────────────────────────────

/** Year + IMDb rating row, matching the hero carousel metadata style. */
@Composable
private fun BannerMeta(year: String, rating: String) {
    val hasYear = year.isNotEmpty()
    val hasRating = rating.isNotEmpty()
    if (!hasYear && !hasRating) return

    val context = LocalContext.current
    val imdbSvgRequest = remember(context) {
        ImageRequest.Builder(context)
            .data(R.raw.logo_imdb_rectangle)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .allowRgb565(false)
            .build()
    }

    Row(
        modifier = Modifier.testTag("mobile_hero_metadata"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (hasYear) {
            Text(
                text = year,
                color = ArvioSkin.colors.textMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
        if (hasRating) {
            AsyncImage(
                model = imdbSvgRequest,
                contentDescription = "IMDb",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(28.dp)
                    .height(14.dp)
                    .clip(RoundedCornerShape(2.dp))
            )
            Text(
                text = rating,
                color = ArvioSkin.colors.textPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/** Bullet-separated genre string rendered in muted gray. */
@Composable
private fun BannerGenres(genres: List<String>, compact: Boolean) {
    if (genres.isEmpty()) return
    Text(
        text = genres.joinToString(" · "),
        modifier = Modifier.testTag("mobile_hero_genres"),
        color = ArvioSkin.colors.textMuted,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        lineHeight = 17.sp,
        maxLines = if (compact) 1 else 2,
        overflow = TextOverflow.Ellipsis
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF000000, widthDp = 360)
@Composable
private fun MobileHeroBannerSeriesPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        MobileHeroBanner(
            imageUrl = "",
            title = "Stranger Things",
            genres = listOf("Slick", "Psychological", "Thriller"),
            year = "2022",
            rating = "8.7",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, widthDp = 360)
@Composable
private fun MobileHeroBannerFilmPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        MobileHeroBanner(
            imageUrl = "",
            title = "Oppenheimer",
            genres = listOf("History", "Drama", "Biography"),
            year = "2023",
            rating = "8.3",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
        )
    }
}
