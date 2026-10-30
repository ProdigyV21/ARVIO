package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.repository.IptvAccountBadge
import com.arflix.tv.data.repository.IptvAccountInfo
import com.arflix.tv.data.repository.IptvAccountInfoParser
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.ErrorRed
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.WarningOrange
import java.text.DateFormat
import java.util.Date

/**
 * The second line of a playlist / portal row: remaining subscription time as a
 * coloured badge plus the allowed concurrent streams, in place of the source
 * address. A source that was never asked (or whose login changed since) keeps
 * showing [fallback], exactly as before.
 */
@Composable
internal fun IptvAccountSubtitle(
    info: IptvAccountInfo?,
    fingerprint: String,
    fallback: String,
    suffix: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    /** Phone layout: remaining time on the first line, streams and EPG on a second. */
    stacked: Boolean = false,
) {
    val style = ArflixTypography.caption.copy(fontSize = 13.sp)
    if (info == null || info.sourceFingerprint != fingerprint) {
        Text(fallback + suffix, style = style, color = textColor, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = modifier)
        return
    }
    val now = remember(info) { System.currentTimeMillis() }
    val badge = IptvAccountInfoParser.badge(info, now)
    val dateFormat = remember { DateFormat.getDateInstance(DateFormat.MEDIUM) }
    val (label, color) = when (badge) {
        IptvAccountBadge.Unlimited -> stringResource(R.string.iptv_account_unlimited) to SuccessGreen
        is IptvAccountBadge.Ok -> stringResource(R.string.iptv_account_days_left, badge.daysLeft) to SuccessGreen
        is IptvAccountBadge.Warning -> when (badge.daysLeft) {
            0 -> stringResource(R.string.iptv_account_ends_today)
            1 -> stringResource(R.string.iptv_account_one_day_left)
            else -> stringResource(R.string.iptv_account_days_left, badge.daysLeft)
        } to WarningOrange
        is IptvAccountBadge.Expired -> stringResource(R.string.iptv_account_expired) to ErrorRed
        IptvAccountBadge.Unavailable -> stringResource(R.string.iptv_account_no_info) to TextSecondary
    }
    val dateDetail = when (badge) {
        is IptvAccountBadge.Warning ->
            stringResource(R.string.iptv_account_until, dateFormat.format(Date(badge.expiresAtMs)))
        is IptvAccountBadge.Expired -> badge.expiredAtMs?.takeIf { it <= now }?.let {
            stringResource(R.string.iptv_account_since, dateFormat.format(Date(it)))
        }
        else -> null
    }
    val streams = info.maxConnections
    val streamsDescription = streams?.let {
        if (it == 1) stringResource(R.string.iptv_account_one_stream)
        else stringResource(R.string.iptv_account_streams, it)
    }

    @Composable
    fun Badge() = Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.18f), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 1.dp)
    ) {
        Text(label, style = style.copy(fontWeight = FontWeight.SemiBold), color = color, maxLines = 1)
    }

    // Icon plus number, not "3 streams": on a phone row the words were cut off
    // to "3 Stre…", and the stream limit is the part worth keeping.
    @Composable
    fun Streams() {
        if (streams == null) return
        Row(
            modifier = Modifier.semantics(mergeDescendants = true) {
                contentDescription = streamsDescription.orEmpty()
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Icon(
                imageVector = Icons.Default.People,
                contentDescription = null,
                tint = StreamsBlue,
                modifier = Modifier.size(15.dp),
            )
            Text(streams.toString(), style = style, color = textColor, maxLines = 1)
        }
    }

    @Composable
    fun Detail(text: String) {
        if (text.isNotBlank()) {
            Text(text.trim(), style = style, color = textColor, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }

    if (stacked) {
        // Phone rows are narrow: the remaining time gets the first line to
        // itself, the stream limit and the EPG note move to a second one.
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Badge()
                Detail(dateDetail.orEmpty())
            }
            if (streams != null || suffix.isNotBlank()) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Streams()
                    Detail(if (streams == null) suffix.removePrefix(" • ") else suffix)
                }
            }
        }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Badge()
            Streams()
            Detail(listOfNotNull(dateDetail).joinToString() + suffix)
        }
    }
}

/** The colour of the "concurrent streams" heads, as in the approved mock-up. */
private val StreamsBlue = Color(0xFF4DA3FF)

/** "Last checked: …" for the edit dialog, or null when the source was never asked. */
@Composable
internal fun iptvAccountCheckedAtLabel(checkedAtMs: Long?): String? {
    checkedAtMs ?: return null
    val format = remember { DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT) }
    return stringResource(R.string.iptv_account_checked_at, format.format(Date(checkedAtMs)))
}
