package com.arflix.tv.ui.screens.tv.live

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import java.nio.charset.StandardCharsets

/**
 * Typographic channel logo placeholder. Variant chosen by first char-code % 3.
 * Real `logoUrl` loads over Coil when present; this placeholder always renders
 * underneath so a missing/slow image doesn't leave a blank box.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelLogo(
    channel: EnrichedChannel,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    val initials = initialsFor(channel.name)
    val variant = (channel.name.firstOrNull()?.code ?: 0) % 3
    val context = LocalContext.current
    val density = LocalDensity.current
    val logoUrl = safeChannelLogoUrl(channel.logo)
    var showFallback by remember(logoUrl) { mutableStateOf(logoUrl.isNullOrBlank()) }
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape((size.value / 5.5f).dp))
            .background(if (logoUrl.isNullOrBlank()) channel.brandBg else LiveColors.Panel),
        contentAlignment = Alignment.Center,
    ) {
        if (showFallback) {
            when (variant) {
                0 -> Text(
                    initials,
                    style = LiveType.ChannelName.copy(
                        color = channel.brandFg,
                        fontSize = (size.value * 0.34f).sp,
                        fontWeight = FontWeight.W700,
                        letterSpacing = 0.sp,
                    ),
                )
                1 -> Text(
                    initials,
                    style = LiveType.ChannelName.copy(
                        color = channel.brandFg,
                        fontSize = (size.value * 0.32f).sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 0.sp,
                    ),
                )
                else -> Text(
                    initials,
                    style = LiveType.ChannelName.copy(
                        color = channel.brandFg,
                        fontSize = (size.value * 0.33f).sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 0.sp,
                    ),
                )
            }
            if (variant == 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .height((size.value / 22f).coerceAtLeast(2f).dp)
                        .fillMaxWidth(0.6f)
                        .background(LiveColors.Accent),
                )
            }
        }
        if (!logoUrl.isNullOrBlank()) {
            val logoRequest = remember(logoUrl, size, density) {
                val px = with(density) { size.roundToPx() }.coerceAtLeast(1)
                ImageRequest.Builder(context)
                    .data(logoUrl)
                    .size(px, px)
                    .precision(Precision.INEXACT)
                    .allowHardware(true)
                    .crossfade(false)
                    .memoryCacheKey("$logoUrl|${px}x$px")
                    .placeholderMemoryCacheKey("$logoUrl|${px}x$px")
                    .build()
            }
            AsyncImage(
                model = logoRequest,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                onSuccess = { showFallback = false },
                onError = { showFallback = true },
                modifier = Modifier
                    .fillMaxSize()
                    .padding((size.value / 7f).coerceIn(4f, 8f).dp),
            )
        }
    }
}

internal fun initialsFor(name: String): String {
    val trimmed = name.trim()
    if (trimmed.isEmpty()) return "??"
    val parts = trimmed.split(Regex("\\s+")).filter { it.any(Char::isLetterOrDigit) }
    return when (parts.size) {
        0 -> "??"
        1 -> parts[0].take(2).uppercase()
        else -> (parts[0].first().toString() + parts[1].first().toString()).uppercase()
    }
}

private fun safeChannelLogoUrl(raw: String?): String? {
    val trimmed = raw?.trim().orEmpty()
    if (trimmed.isBlank()) return null
    val normalized = when {
        trimmed.startsWith("http://", ignoreCase = true) ||
            trimmed.startsWith("https://", ignoreCase = true) -> trimmed
        trimmed.startsWith("//") -> "https:$trimmed"
        else -> decodeLegacyLogoUrl(trimmed)
    } ?: return null
    return normalized.takeIf {
        it.startsWith("http://", ignoreCase = true) ||
            it.startsWith("https://", ignoreCase = true)
    }
}

private fun decodeLegacyLogoUrl(value: String): String? {
    if (value.length < 12 || value.any { it.isWhitespace() }) return null
    return listOf(Base64.DEFAULT, Base64.URL_SAFE or Base64.NO_WRAP)
        .asSequence()
        .mapNotNull { flags ->
            runCatching { String(Base64.decode(value, flags), StandardCharsets.UTF_8).trim() }.getOrNull()
        }
        .firstOrNull { decoded ->
            decoded.startsWith("http://", ignoreCase = true) ||
                decoded.startsWith("https://", ignoreCase = true)
        }
}

/** Small dot + label pair used in EPG rows. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SmallTag(text: String, color: Color = LiveColors.FgDim) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .background(LiveColors.Panel),
    ) {
        Text(
            text = text,
            style = LiveType.Badge.copy(color = color),
            modifier = Modifier.clip(RoundedCornerShape(3.dp)),
        )
    }
}
