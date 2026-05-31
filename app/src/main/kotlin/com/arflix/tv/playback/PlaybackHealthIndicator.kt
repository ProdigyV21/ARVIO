package com.arflix.tv.playback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PlaybackHealthIndicator(
    metricsFlow: StateFlow<PlaybackMetrics>,
    modifier: Modifier = Modifier
) {
    val metrics by metricsFlow.collectAsState()
    
    val (color, text) = when (metrics.health) {
        PlaybackHealth.EXCELLENT -> Color.Green to "Excellent"
        PlaybackHealth.GOOD -> Color(0xFF8BC34A) to "Good" // Light Green
        PlaybackHealth.FAIR -> Color.Yellow to "Fair"
        PlaybackHealth.POOR -> Color(0xFFFF9800) to "Poor" // Orange
        PlaybackHealth.CRITICAL -> Color.Red to "Critical"
    }

    // Only show if network is degrading to not distract users normally,
    // or show always if requested. We'll show for FAIR, POOR, CRITICAL.
    if (metrics.health == PlaybackHealth.EXCELLENT || metrics.health == PlaybackHealth.GOOD) {
        return
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Network: $text",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
