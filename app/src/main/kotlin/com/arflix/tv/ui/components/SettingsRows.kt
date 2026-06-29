package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.arflix.tv.R
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.skin.resolveAccentColor

/** Display-only localization of stored setting values (Off/Any/Medium/White...).
 *  The stored/compared value stays English; only the shown label is translated.
 *  Unknown values (720p, 4K, language/DNS names) pass through unchanged. */
@Composable
internal fun localizeSettingValue(value: String): String = when (value) {
    "Off" -> stringResource(R.string.off)
    "On" -> stringResource(R.string.on)
    "Auto" -> stringResource(R.string.auto)
    "Any" -> stringResource(R.string.settings_value_any)
    "Always" -> stringResource(R.string.settings_value_always)
    "Seamless only" -> stringResource(R.string.settings_value_seamless)
    "Small" -> stringResource(R.string.settings_value_small)
    "Medium" -> stringResource(R.string.settings_value_medium)
    "Large" -> stringResource(R.string.settings_value_large)
    "Extra Large" -> stringResource(R.string.settings_value_extra_large)
    "White" -> stringResource(R.string.settings_value_white)
    "Red" -> stringResource(R.string.settings_value_red)
    "Orange" -> stringResource(R.string.settings_value_orange)
    "Yellow" -> stringResource(R.string.settings_value_yellow)
    "Green" -> stringResource(R.string.settings_value_green)
    "Blue" -> stringResource(R.string.settings_value_blue)
    "Indigo" -> stringResource(R.string.settings_value_indigo)
    "Violet" -> stringResource(R.string.settings_value_violet)
    "Cyan" -> stringResource(R.string.settings_value_cyan)
    "Normal" -> stringResource(R.string.settings_value_normal)
    "Bold" -> stringResource(R.string.settings_value_bold)
    "Background" -> stringResource(R.string.settings_value_background)
    "Low" -> stringResource(R.string.settings_value_low)
    "Bottom" -> stringResource(R.string.settings_value_bottom)
    "High" -> stringResource(R.string.settings_value_high)
    "None" -> stringResource(R.string.settings_value_none)
    "Forced" -> stringResource(R.string.settings_value_forced)
    "Auto (Original)" -> stringResource(R.string.settings_value_auto_original)
    "Tablet" -> stringResource(R.string.settings_ui_mode_tablet)
    "Phone" -> stringResource(R.string.settings_ui_mode_phone)
    "System DNS" -> stringResource(R.string.settings_dns_system)
    "12-hour" -> stringResource(R.string.settings_clock_12h)
    "24-hour" -> stringResource(R.string.settings_clock_24h)
    else -> value
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String = "",
    value: String,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRingColor = resolveAccentColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(19.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        style = ArflixTypography.caption.copy(fontSize = 13.sp),
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(12.dp))

        if (value.isNotBlank()) {
            Box(
                modifier = Modifier
                    .background(Pink.copy(alpha = 0.15f), RoundedCornerShape(999.dp))
                    .border(1.dp, Pink.copy(alpha = 0.3f), RoundedCornerShape(999.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = localizeSettingValue(value).uppercase(),
                    style = ArflixTypography.label.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                    color = Pink,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsToggleRow(
    title: String,
    subtitle: String,
    isEnabled: Boolean,
    isFocused: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRingColor = resolveAccentColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onToggle(!isEnabled) }
            )
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Box(
            modifier = Modifier
                .width(44.dp)
                .height(24.dp)
                .background(
                    color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(13.dp)
                )
                .padding(3.dp),
            contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
    }
}
