package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Audio track data class
 */
data class AudioTrack(
    val id: String,
    val language: String,
    val label: String,
    val codec: String? = null,
    val channels: Int? = null,
    val isDefault: Boolean = false
)

/**
 * Audio track selector modal for the video player
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AudioTrackSelector(
    isVisible: Boolean,
    audioTracks: List<AudioTrack>,
    selectedTrackId: String?,
    onSelect: (AudioTrack) -> Unit,
    onClose: () -> Unit
) {
    var focusedIndex by remember(isVisible) { mutableIntStateOf(0) }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.9f))
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onClose()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusedIndex > 0) focusedIndex--
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusedIndex < audioTracks.size - 1) focusedIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                audioTracks.getOrNull(focusedIndex)?.let { track ->
                                    onSelect(track)
                                }
                                true
                            }
                            else -> false
                        }
                    } else false
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(420.dp)
                    .background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = null,
                        tint = Pink,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Audio Track",
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )
                }
                
                // Track list
                if (audioTracks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No audio tracks available",
                            style = ArflixTypography.body,
                            color = TextSecondary
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.height((audioTracks.size * 60).coerceAtMost(300).dp)
                    ) {
                        itemsIndexed(audioTracks) { index, track ->
                            AudioTrackItem(
                                track = track,
                                isSelected = track.id == selectedTrackId,
                                isFocused = focusedIndex == index,
                                onClick = { onSelect(track) }
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Help text
                Text(
                    text = "Press BACK to close",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AudioTrackItem(
    track: AudioTrack,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                when {
                    isFocused -> Pink
                    isSelected -> Color.White.copy(alpha = 0.1f)
                    else -> Color.Transparent
                },
                RoundedCornerShape(8.dp)
            )
            .border(
                width = if (isSelected && !isFocused) 1.dp else 0.dp,
                color = if (isSelected && !isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = track.label,
                style = ArflixTypography.body,
                color = if (isFocused) Color.White else if (isSelected) TextPrimary else TextSecondary
            )
            
            // Show codec/channel info if available
            val metadata = buildList {
                track.codec?.let { add(it.uppercase()) }
                track.channels?.let { 
                    add(when (it) {
                        1 -> "Mono"
                        2 -> "Stereo"
                        6 -> "5.1"
                        8 -> "7.1"
                        else -> "${it}ch"
                    })
                }
                if (track.isDefault) add("Default")
            }
            
            if (metadata.isNotEmpty()) {
                Text(
                    text = metadata.joinToString(" • "),
                    style = ArflixTypography.caption,
                    color = if (isFocused) Color.White.copy(alpha = 0.7f) else TextSecondary.copy(alpha = 0.7f)
                )
            }
        }
        
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = if (isFocused) Color.White else Pink,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
