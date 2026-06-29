package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arflix.tv.data.model.StreamSource

/** A single quality/format badge with optional white-on-transparent image asset. */
data class PlaybackBadge(val text: String, val imageUrl: String? = null)

private object BadgeImages {
    private const val BASE =
        "https://raw.githubusercontent.com/nobnobz/Omni-Template-Bot-Bid-Raiser/main/Other/white%20regex%20tags"
    val UHD_4K              = "$BASE/white_4k.png"
    val FULL_HD_1080        = "$BASE/white_1080p.png"
    val HD_720              = "$BASE/white_720p.png"
    val DOLBY_VISION        = "$BASE/white_DV.png"
    val HDR10_PLUS          = "$BASE/white_HDR10Plus.png"
    val HDR10               = "$BASE/white_HDR10.png"
    val HDR                 = "$BASE/white_HDR.png"
    val IMAX                = "$BASE/white_imax.png"
    val ATMOS               = "$BASE/white_Atmos.png"
    val TRUEHD              = "$BASE/white_TrueHD.png"
    val DOLBY_DIGITAL_PLUS  = "$BASE/white_DDPLUS.png"
    val DOLBY_DIGITAL       = "$BASE/white_DD.png"
    val DTS_X               = "$BASE/white_dtsx.png"
    val DTS_HD_MA           = "$BASE/white_dtsHDMA.png"
    val DTS_HD              = "$BASE/white_dtsHD.png"
    val DTS                 = "$BASE/white_dts.png"
}

private object BadgeRegex {
    val RES_4K     = Regex("""\b(4K|2160p|UHD)\b""",                            RegexOption.IGNORE_CASE)
    val RES_1080   = Regex("""\b1080p\b""",                                      RegexOption.IGNORE_CASE)
    val RES_720    = Regex("""\b720p\b""",                                       RegexOption.IGNORE_CASE)
    val RES_480    = Regex("""\b480p\b""",                                       RegexOption.IGNORE_CASE)
    val DV         = Regex("""\b(DV|DoVi|Dolby[\s._-]*Vision)\b""",             RegexOption.IGNORE_CASE)
    val HDR10_PLUS = Regex("""\b(HDR10\+|HDR10\s*PLUS|HDR\s*10\s*\+)\b""",    RegexOption.IGNORE_CASE)
    val HDR10      = Regex("""\bHDR10\b""",                                      RegexOption.IGNORE_CASE)
    val HDR        = Regex("""\bHDR\b""",                                        RegexOption.IGNORE_CASE)
    val IMAX       = Regex("""\bIMAX\b""",                                       RegexOption.IGNORE_CASE)
    val ATMOS      = Regex("""\bATMOS\b""",                                      RegexOption.IGNORE_CASE)
    val TRUEHD     = Regex("""\bTRUEHD\b""",                                     RegexOption.IGNORE_CASE)
    val DTS_X      = Regex("""\bDTS[-_.: ]?X\b""",                              RegexOption.IGNORE_CASE)
    val DTS_HD_MA  = Regex("""\bDTS[-_. ]?(?:HD[-_. ]?)?(?:MA|MASTER)\b""",    RegexOption.IGNORE_CASE)
    val DTS_HD     = Regex("""\bDTS[-_. ]?HD\b""",                              RegexOption.IGNORE_CASE)
    val DTS        = Regex("""\bDTS\b""",                                        RegexOption.IGNORE_CASE)
    val DD_PLUS    = Regex("""\b(DDP|DD\+|EAC-?3|E-?AC-?3)\b""",              RegexOption.IGNORE_CASE)
    val DD         = Regex("""\b(AC-?3|DD(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\b""", RegexOption.IGNORE_CASE)
}

/**
 * Builds quality badges for the currently playing [StreamSource].
 * Returns up to three badges: Resolution | Video format (DV/HDR/IMAX) | Audio format.
 * Reuses the same regex corpus as the source picker for consistent labelling.
 */
fun buildPlaybackBadges(stream: StreamSource): List<PlaybackBadge> {
    val blob = listOfNotNull(
        stream.quality,
        stream.source,
        stream.description,
        stream.behaviorHints?.filename
    ).joinToString(" ")

    return buildList {
        // Resolution
        when {
            BadgeRegex.RES_4K.containsMatchIn(blob)   -> add(PlaybackBadge("4K",    BadgeImages.UHD_4K))
            BadgeRegex.RES_1080.containsMatchIn(blob)  -> add(PlaybackBadge("1080p", BadgeImages.FULL_HD_1080))
            BadgeRegex.RES_720.containsMatchIn(blob)   -> add(PlaybackBadge("720p",  BadgeImages.HD_720))
            BadgeRegex.RES_480.containsMatchIn(blob)   -> add(PlaybackBadge("480p"))
        }
        // Video format (pick best)
        when {
            BadgeRegex.DV.containsMatchIn(blob)         -> add(PlaybackBadge("DV",     BadgeImages.DOLBY_VISION))
            BadgeRegex.HDR10_PLUS.containsMatchIn(blob) -> add(PlaybackBadge("HDR10+", BadgeImages.HDR10_PLUS))
            BadgeRegex.HDR10.containsMatchIn(blob)      -> add(PlaybackBadge("HDR10",  BadgeImages.HDR10))
            BadgeRegex.HDR.containsMatchIn(blob)        -> add(PlaybackBadge("HDR",    BadgeImages.HDR))
        }
        if (BadgeRegex.IMAX.containsMatchIn(blob)) add(PlaybackBadge("IMAX", BadgeImages.IMAX))
        // Audio format (pick best single label)
        when {
            BadgeRegex.ATMOS.containsMatchIn(blob)     -> add(PlaybackBadge("Atmos",     BadgeImages.ATMOS))
            BadgeRegex.TRUEHD.containsMatchIn(blob)    -> add(PlaybackBadge("TrueHD",    BadgeImages.TRUEHD))
            BadgeRegex.DTS_X.containsMatchIn(blob)     -> add(PlaybackBadge("DTS:X",     BadgeImages.DTS_X))
            BadgeRegex.DTS_HD_MA.containsMatchIn(blob) -> add(PlaybackBadge("DTS-HD MA", BadgeImages.DTS_HD_MA))
            BadgeRegex.DTS_HD.containsMatchIn(blob)    -> add(PlaybackBadge("DTS-HD",    BadgeImages.DTS_HD))
            BadgeRegex.DTS.containsMatchIn(blob)       -> add(PlaybackBadge("DTS",       BadgeImages.DTS))
            BadgeRegex.DD_PLUS.containsMatchIn(blob)   -> add(PlaybackBadge("DD+",       BadgeImages.DOLBY_DIGITAL_PLUS))
            BadgeRegex.DD.containsMatchIn(blob)        -> add(PlaybackBadge("DD",        BadgeImages.DOLBY_DIGITAL))
        }
    }
}

/**
 * Horizontal row of quality badges shown in the player bottom-left corner
 * while controls are visible. Shows at most 3-4 icons: resolution, video
 * format (DV/HDR/IMAX), and best audio format (Atmos/DTS/DD).
 */
@Composable
fun PlaybackQualityBadgeRow(
    stream: StreamSource?,
    modifier: Modifier = Modifier
) {
    if (stream == null) return
    val badges = remember(stream) {
        buildPlaybackBadges(stream)
    }
    if (badges.isEmpty()) return

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        badges.forEach { badge -> PlaybackBadgeItem(badge) }
    }
}

@Composable
private fun PlaybackBadgeItem(badge: PlaybackBadge) {
    if (badge.imageUrl != null) {
        AsyncImage(
            model = badge.imageUrl,
            contentDescription = badge.text,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(playbackBadgeWidth(badge.text))
                .height(18.dp)
        )
    } else {
        Text(
            text = badge.text,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.85f),
                letterSpacing = 0.5.sp
            )
        )
    }
}

private fun playbackBadgeWidth(text: String): Dp = when {
    text.equals("4K",        ignoreCase = true) -> 36.dp
    text.equals("1080p",     ignoreCase = true) -> 50.dp
    text.equals("720p",      ignoreCase = true) -> 44.dp
    text.equals("DV",        ignoreCase = true) -> 68.dp
    text.equals("HDR10+",    ignoreCase = true) -> 58.dp
    text.equals("HDR10",     ignoreCase = true) -> 52.dp
    text.equals("HDR",       ignoreCase = true) -> 42.dp
    text.equals("IMAX",      ignoreCase = true) -> 48.dp
    text.equals("Atmos",     ignoreCase = true) -> 58.dp
    text.equals("TrueHD",    ignoreCase = true) -> 56.dp
    text.equals("DTS:X",     ignoreCase = true) -> 52.dp
    text.equals("DTS-HD MA", ignoreCase = true) -> 72.dp
    text.equals("DTS-HD",    ignoreCase = true) -> 58.dp
    text.equals("DTS",       ignoreCase = true) -> 42.dp
    text.equals("DD+",       ignoreCase = true) -> 44.dp
    text.equals("DD",        ignoreCase = true) -> 38.dp
    else                                         -> 46.dp
}
