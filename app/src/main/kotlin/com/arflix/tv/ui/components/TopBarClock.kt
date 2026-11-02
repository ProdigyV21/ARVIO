package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.theme.ArflixTypography
import kotlinx.coroutines.delay
import android.content.Context
import android.text.format.DateFormat
import androidx.compose.ui.platform.LocalContext
import java.util.Date

/**
 * Top bar clock display with optional profile indicator
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TopBarClock(
    modifier: Modifier = Modifier,
    profile: Profile? = null
) {
    val context = LocalContext.current
    var currentTime by remember { mutableStateOf(getCurrentTime(context)) }

    LaunchedEffect(context) {
        while (true) {
            currentTime = getCurrentTime(context)
            val now = System.currentTimeMillis()
            val delayToNextMinute = 60_000L - (now % 60_000L)
            delay(delayToNextMinute.coerceIn(1_000L, 60_000L))
        }
    }

    Box(
        modifier = modifier.padding(32.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            // Profile indicator (if profile provided)
            if (profile != null) {
                ProfileIndicator(profile = profile)
                Spacer(modifier = Modifier.width(16.dp))
            }

            // Clock
            Text(
                text = currentTime,
                style = ArflixTypography.clock,
                color = Color.White
            )
        }
    }
}

/**
 * Small profile indicator showing avatar color and name
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ProfileIndicator(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            ProfileAvatarVisual(
                profile = profile,
                letterFontSize = 12.sp,
                iconPadding = 2.dp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Profile name
        Text(
            text = profile.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.9f)
        )
    }
}

private fun getCurrentTime(context: Context): String {
    val timeFormat = DateFormat.getTimeFormat(context)
    return timeFormat.format(Date())
}
