package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Source info overlay for the video player
 * Shows current stream information in the top-right corner
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SourceInfoOverlay(
    isVisible: Boolean,
    stream: StreamSource?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible && stream != null,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        stream?.let { source ->
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        ),
                        shape = RoundedCornerShape(bottomStart = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    // Quality badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.HighQuality,
                            contentDescription = null,
                            tint = getQualityColor(source.quality),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = source.quality.uppercase(),
                            style = ArflixTypography.label,
                            color = getQualityColor(source.quality)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    // Source indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = if (source.isDebrid) Icons.Default.Cloud else Icons.Default.Storage,
                            contentDescription = null,
                            tint = if (source.isDebrid) SuccessGreen else TextSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = source.addonName,
                            style = ArflixTypography.caption,
                            color = if (source.isDebrid) SuccessGreen else TextSecondary
                        )
                    }
                    
                    // Size info
                    if (source.size.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = source.size,
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Player bottom bar showing playback info
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PlayerInfoBar(
    title: String,
    subtitle: String? = null,
    isLive: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                )
            )
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        
        if (isLive) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFEF4444), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LiveTv,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "LIVE",
                    style = ArflixTypography.badge,
                    color = Color.White
                )
            }
        }
    }
}

/**
 * Get color based on quality string
 */
private fun getQualityColor(quality: String): Color {
    return when {
        quality.contains("4K", ignoreCase = true) || 
        quality.contains("2160p", ignoreCase = true) -> Color(0xFFFFD700) // Gold
        quality.contains("1080p", ignoreCase = true) -> Pink
        quality.contains("720p", ignoreCase = true) -> Color(0xFF3B82F6) // Blue
        else -> TextSecondary
    }
}
