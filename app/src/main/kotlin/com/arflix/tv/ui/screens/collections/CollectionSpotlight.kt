package com.arflix.tv.ui.screens.collections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.imageLoader
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.components.ImdbSvgRatingBadge
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

internal fun collectionBudget(item: MediaItem?, locale: Locale = Locale.getDefault()): String? {
    val budget = item?.budget?.takeIf { it > 0 && item.mediaType == MediaType.MOVIE } ?: return null
    return NumberFormat.getCurrencyInstance(locale).apply {
        currency = Currency.getInstance("USD")
        maximumFractionDigits = 0
    }.format(budget)
}

internal fun collectionRating(item: MediaItem?): String? {
    if (item == null) return null
    fun valid(value: String) = value.toDoubleOrNull()?.let { it.isFinite() && it > 0 && it <= 10 } == true
    return when {
        valid(item.imdbRating) -> "IMDb ${item.imdbRating}"
        !item.isHomeServer && valid(item.tmdbRating) -> "TMDB ${item.tmdbRating}"
        else -> null
    }
}

internal fun collectionCompactBudget(item: MediaItem?, locale: Locale = Locale.getDefault()): String? {
    val value = item?.budget?.takeIf { it > 0 && item.mediaType == MediaType.MOVIE } ?: return null
    val divisor = when {
        value >= 1_000_000_000L -> 1_000_000_000L
        value >= 1_000_000L -> 1_000_000L
        else -> return collectionBudget(item, locale)
    }
    return NumberFormat.getCurrencyInstance(locale).apply {
        currency = Currency.getInstance("USD")
        minimumFractionDigits = 0
        maximumFractionDigits = 1
    }.format(value.toDouble() / divisor) + if (divisor == 1_000_000L) "M" else "B"
}

internal fun collectionSpotlightHeight(isMobile: Boolean, compact: Boolean): Dp = when {
    compact -> 174.dp
    isMobile -> 258.dp
    else -> 198.dp
}

/** One backdrop continues behind the header and grid, rather than ending at their boundary. */
@Composable
internal fun CollectionBackdrop(
    catalog: CatalogConfig?,
    item: MediaItem?,
    isMobile: Boolean,
    height: Dp,
    background: Color = appBackgroundDark()
) {
    val context = LocalContext.current
    val backdrop = item?.backdrop?.takeIf(String::isNotBlank)
        ?: catalog?.collectionHeroImageUrl?.takeIf(String::isNotBlank)
    val request = remember(backdrop, context) {
        ImageRequest.Builder(context).data(backdrop).size(1280, 720).crossfade(160).build()
    }
    Box(Modifier.fillMaxWidth().height(height).testTag("collection_backdrop")) {
        if (backdrop != null) {
            AsyncImage(request, null,
                Modifier.fillMaxHeight().fillMaxWidth(if (isMobile) 1f else 0.78f).align(Alignment.CenterEnd),
                contentScale = ContentScale.Crop, alignment = BiasAlignment(1f, -0.5f))
        }
        Box(Modifier.fillMaxSize().background(Brush.horizontalGradient(
            0f to background, 0.25f to background, 0.40f to background.copy(alpha = 0.88f),
            0.65f to background.copy(alpha = 0.45f), 0.92f to Color.Transparent,
            1f to background
        )))
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(
            0f to background, 0.12f to background.copy(alpha = 0.35f), 0.25f to Color.Transparent,
            0.55f to background.copy(alpha = 0.18f), 0.78f to background.copy(alpha = 0.72f),
            1f to background
        )))
    }
}

/** Persistent preview above the grid, with artwork drawn separately behind the cards. */
@Composable
@OptIn(ExperimentalLayoutApi::class)
internal fun CollectionSpotlight(
    catalog: CatalogConfig?,
    item: MediaItem?,
    isMobile: Boolean,
    compact: Boolean,
    onBack: () -> Unit,
    providerLogoUrl: String? = item?.primaryNetworkLogo,
    clearLogoUrl: String? = null,
    horizontalInset: Dp = if (isMobile) 20.dp else 42.dp,
    showBackButton: Boolean = isMobile,
    topInset: Dp = 10.dp
) {
    val context = LocalContext.current
    val inset = horizontalInset
    val height = collectionSpotlightHeight(isMobile, compact)
    Box(Modifier.fillMaxWidth().height(height).testTag("collection_spotlight")) {
        Column(
            Modifier.fillMaxSize().padding(start = inset, end = inset, top = topInset, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(if (compact) 5.dp else 7.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showBackButton) {
                    IconButton(onClick = onBack, modifier = Modifier.size(40.dp)) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back), tint = Color.White)
                    }
                }
                Text(catalog?.title.orEmpty(), color = TextSecondary, fontSize = 12.sp,
                    fontWeight = FontWeight.Medium, letterSpacing = 0.sp,
                    maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            val textWidth = if (isMobile) 1f else 0.62f
            CollectionTitle(
                title = item?.title ?: catalog?.title.orEmpty(),
                logoUrl = clearLogoUrl,
                isMobile = isMobile,
                compact = compact,
                modifier = Modifier.fillMaxWidth(textWidth)
            )
            val facts = listOfNotNull(
                item?.year?.takeIf(String::isNotBlank),
                item?.duration?.takeIf(String::isNotBlank),
                item?.contentRating?.takeIf(String::isNotBlank)
            ).joinToString("  ·  ")
            Text(facts, modifier = Modifier.fillMaxWidth(textWidth).testTag("collection_spotlight_facts"),
                color = TextSecondary, fontSize = 12.sp, letterSpacing = 0.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis)
            val rating = collectionRating(item)
            val budget = remember(item?.budget, item?.mediaType) { collectionCompactBudget(item) }
            FlowRow(Modifier.fillMaxWidth(textWidth).testTag("collection_spotlight_brands"),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                if (rating?.startsWith("IMDb ") == true) {
                    ImdbSvgRatingBadge(rating.removePrefix("IMDb "), context.imageLoader,
                        ratingFontSize = 13, logoWidth = 30.dp, logoHeight = 16.dp)
                } else if (rating != null) {
                    Text(rating, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
                if (!providerLogoUrl.isNullOrBlank()) {
                    val logo = remember(providerLogoUrl, context) {
                        ImageRequest.Builder(context).data(providerLogoUrl)
                            .bitmapConfig(android.graphics.Bitmap.Config.ARGB_8888)
                            .allowRgb565(false).size(156, 54).build()
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        if (rating != null) MetadataDivider()
                        AsyncImage(logo, stringResource(R.string.home_cd_primary_provider),
                            contentScale = ContentScale.Fit, alignment = Alignment.CenterStart,
                            modifier = Modifier.width(52.dp).height(18.dp).testTag("collection_provider_logo"))
                    }
                }
                if (budget != null) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        if (rating != null || !providerLogoUrl.isNullOrBlank()) MetadataDivider()
                        Text("${stringResource(R.string.budget)} $budget", color = TextSecondary,
                            fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
            Text(item?.overview?.takeIf(String::isNotBlank) ?: catalog?.collectionDescription.orEmpty(),
                modifier = Modifier.fillMaxWidth(textWidth).testTag("collection_spotlight_overview"),
                color = TextSecondary, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 0.sp,
                maxLines = if (isMobile && !compact) 3 else 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
internal fun CollectionTitle(
    title: String,
    logoUrl: String?,
    isMobile: Boolean,
    compact: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val height = if (compact) 38.dp else 60.dp
    val url = logoUrl?.takeIf(String::isNotBlank)
    var logoReady by remember(title, url) { mutableStateOf(false) }
    val request = remember(url, context, density, height) {
        ImageRequest.Builder(context).data(url)
            .size(with(density) { 280.dp.roundToPx() }, with(density) { height.roundToPx() })
            .bitmapConfig(android.graphics.Bitmap.Config.ARGB_8888)
            .allowRgb565(false).crossfade(false).build()
    }
    // Keep the same slot during loading/failure so artwork cannot move the cards.
    Box(modifier.height(height), contentAlignment = Alignment.CenterStart) {
        if (url != null) {
            AsyncImage(
                model = request,
                contentDescription = title,
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart,
                modifier = Modifier.width(280.dp).fillMaxHeight().testTag("collection_clearlogo"),
                onSuccess = { logoReady = true },
                onError = { logoReady = false }
            )
        }
        if (!logoReady) {
            Text(title, modifier = Modifier.fillMaxWidth().testTag("collection_spotlight_title"),
                color = Color.White, fontSize = if (isMobile || compact) 23.sp else 28.sp,
                fontWeight = FontWeight.Bold, letterSpacing = 0.sp, lineHeight = 30.sp,
                maxLines = if (compact) 1 else 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun MetadataDivider() {
    Box(Modifier.width(1.dp).height(12.dp).background(Color.White.copy(alpha = 0.3f)))
}
