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
import androidx.compose.material.icons.filled.HighQuality
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
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.extractQuality
import com.arflix.tv.util.isQuality4K
import com.arflix.tv.util.isQuality1080
import com.arflix.tv.util.isQuality720

// Quality detection now uses StreamQualityUtils for hybrid filename + metadata detection

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
            val extractedQuality = source.extractQuality()
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
                        val qualityColor = getQualityColor(extractedQuality)
                        Icon(
                            imageVector = Icons.Default.HighQuality,
                            contentDescription = null,
                            tint = qualityColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = extractedQuality.uppercase(),
                            style = ArflixTypography.label,
                            color = qualityColor
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Source indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Storage,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = source.addonName,
                            style = ArflixTypography.caption,
                            color = TextSecondary
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
 * Get color based on quality string using hybrid detection
 */
private fun getQualityColor(quality: String): Color {
    return when {
        isQuality4K(quality) -> Color(0xFFFFD700) // Gold
        isQuality1080(quality) -> Pink
        isQuality720(quality) -> Color(0xFF3B82F6) // Blue
        else -> TextSecondary
    }
}