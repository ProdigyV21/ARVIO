package com.arflix.tv.ui.screens.player.tv

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.AudioTrackInfo
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.ui.theme.ArflixTypography

@Composable
fun TvSubtitleSettingsPanel(
    isVisible: Boolean,
    selectedRow: Int,
    syncOffsetMs: Long,
    sizePct: Int,
    verticalPct: Int,
    onRowSelect: (Int) -> Unit,
    onOffsetDecrease: () -> Unit,
    onOffsetIncrease: () -> Unit,
    onSizeDecrease: () -> Unit,
    onSizeIncrease: () -> Unit,
    onVerticalDecrease: () -> Unit,
    onVerticalIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        val accent = LocalAccentColorOverride.current ?: Color.White

        val absMs = if (syncOffsetMs < 0) -syncOffsetMs else syncOffsetMs
        val offsetLabel = if (syncOffsetMs == 0L) "0.0s"
        else "${if (syncOffsetMs > 0) "+" else "-"}${absMs / 1000}.${(absMs % 1000) / 100}s"

        Column(
            modifier = Modifier
                .width(280.dp)
                .background(Color.Black.copy(alpha = 0.92f), RoundedCornerShape(16.dp))
                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(R.string.subtitle_settings_title),
                style = ArflixTypography.sectionTitle.copy(fontSize = 16.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            TvSubtitleSettingRow(
                label = stringResource(R.string.subtitle_delay),
                value = offsetLabel,
                selected = selectedRow == 0,
                accent = accent,
                onClick = { onRowSelect(0) },
                onDecrease = onOffsetDecrease,
                onIncrease = onOffsetIncrease
            )
            TvSubtitleSettingRow(
                label = stringResource(R.string.subtitle_size_label),
                value = "${sizePct}%",
                selected = selectedRow == 1,
                accent = accent,
                onClick = { onRowSelect(1) },
                onDecrease = onSizeDecrease,
                onIncrease = onSizeIncrease
            )
            TvSubtitleSettingRow(
                label = stringResource(R.string.subtitle_vertical_position),
                value = "${verticalPct}%",
                selected = selectedRow == 2,
                accent = accent,
                onClick = { onRowSelect(2) },
                onDecrease = onVerticalDecrease,
                onIncrease = onVerticalIncrease
            )
        }
    }
}

@Composable
private fun TvSubtitleSettingRow(
    label: String,
    value: String,
    selected: Boolean,
    accent: Color,
    onClick: () -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    val rowBg = if (selected) Color.White.copy(alpha = 0.08f) else Color.Transparent
    val valueColor = if (selected) accent else Color.White

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowBg, RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = ArflixTypography.label.copy(fontWeight = FontWeight.Normal),
            color = Color.White.copy(alpha = 0.55f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDecrease
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "−",
                    style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
            Text(
                text = value,
                style = ArflixTypography.body.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                color = valueColor
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onIncrease
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TvSubtitleMenu(
    isVisible: Boolean,
    selectedSubtitle: Subtitle? = null,
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    activeTab: Int,
    focusedIndex: Int,
    subtitleGroups: List<Pair<String, List<Pair<Int, Subtitle>>>>,
    subtitleLangIndex: Int,
    subtitleTrackIndex: Int,
    subtitlePanelFocus: Int,
    onTabChanged: (Int) -> Unit,
    onSelectSubtitle: (Int) -> Unit,
    onSelectAudio: (AudioTrackInfo) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        val langListState = rememberLazyListState()
        val trackListState = rememberLazyListState()
        val audioListState = rememberLazyListState()

        LaunchedEffect(subtitleLangIndex) {
            langListState.animateScrollToItem(subtitleLangIndex.coerceAtLeast(0))
        }
        LaunchedEffect(subtitleTrackIndex, subtitlePanelFocus) {
            if (subtitlePanelFocus == 1 && subtitleTrackIndex >= 0) {
                trackListState.animateScrollToItem(subtitleTrackIndex)
            }
        }
        LaunchedEffect(focusedIndex, activeTab) {
            if (activeTab == 1 && focusedIndex >= 0) {
                audioListState.animateScrollToItem(focusedIndex)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable { onClose() },
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(if (activeTab == 0) 560.dp else 360.dp)
                    .background(Color(0xFF141414))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
                    .padding(24.dp)
            ) {
                // Tab switcher
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TvTabButton(
                        text = stringResource(R.string.subtitles),
                        isSelected = activeTab == 0,
                        onClick = { onTabChanged(0) }
                    )
                    TvTabButton(
                        text = stringResource(R.string.audio),
                        isSelected = activeTab == 1,
                        onClick = { onTabChanged(1) }
                    )
                }

                if (activeTab == 0) {
                    // Subtitles: Two-panel layout (Languages | Tracks)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Left: Language list
                        LazyColumn(
                            state = langListState,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            item {
                                TvTrackMenuItem(
                                    label = stringResource(R.string.off),
                                    subtitle = null,
                                    isSelected = selectedSubtitle == null,
                                    isFocused = subtitlePanelFocus == 0 && subtitleLangIndex == 0,
                                    onClick = { onSelectSubtitle(-1) }
                                )
                            }
                            itemsIndexed(subtitleGroups) { idx, (lang, tracks) ->
                                val listIdx = idx + 1
                                val isGroupSelected = selectedSubtitle != null && tracks.any { (_, sub) ->
                                    (sub.id != null && sub.id == selectedSubtitle.id) ||
                                        (selectedSubtitle.id.isNullOrBlank() && sub.url == selectedSubtitle.url)
                                }
                                TvTrackMenuItem(
                                    label = lang,
                                    subtitle = null,
                                    isSelected = isGroupSelected,
                                    isFocused = subtitlePanelFocus == 0 && subtitleLangIndex == listIdx,
                                    onClick = {}
                                )
                            }
                        }

                        // Right: Tracks in selected language
                        val selectedGroup = if (subtitleLangIndex > 0) subtitleGroups.getOrNull(subtitleLangIndex - 1) else null
                        if (selectedGroup != null) {
                            LazyColumn(
                                state = trackListState,
                                modifier = Modifier
                                .weight(1.2f)
                                .fillMaxHeight(),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                itemsIndexed(selectedGroup.second) { idx, (globalIdx, sub) ->
                                    val label = sub.label?.takeIf { it.isNotBlank() } ?: "Track ${idx + 1}"
                                    val isTrackSelected = selectedSubtitle != null &&
                                        ((sub.id != null && sub.id == selectedSubtitle.id) ||
                                            (selectedSubtitle.id.isNullOrBlank() && sub.url == selectedSubtitle.url))
                                    TvTrackMenuItem(
                                        label = label,
                                        subtitle = sub.provider?.takeIf { it.isNotBlank() },
                                        isSelected = isTrackSelected,
                                        isFocused = subtitlePanelFocus == 1 && subtitleTrackIndex == idx,
                                        onClick = { onSelectSubtitle(globalIdx) }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Audio: Single list
                    LazyColumn(
                        state = audioListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        itemsIndexed(audioTracks) { idx: Int, track: AudioTrackInfo ->
                            val label = track.label?.takeIf { it.isNotBlank() }
                                ?: track.language?.takeIf { it.isNotBlank() }
                                ?: "Audio ${idx + 1}"
                            TvTrackMenuItem(
                                label = label,
                                subtitle = track.codec ?: "Audio track",
                                isSelected = selectedAudioIndex == idx,
                                isFocused = focusedIndex == idx,
                                onClick = { onSelectAudio(track) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TvTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Transparent,
                RoundedCornerShape(20.dp)
            )
            .then(
                if (isSelected) Modifier.border(1.dp, Color.White, RoundedCornerShape(20.dp))
                else Modifier
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.body.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                fontSize = 14.sp
            ),
            color = Color.White
        )
    }
}

@Composable
fun TvTrackMenuItem(
    label: String,
    subtitle: String?,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = ArflixTypography.body.copy(fontSize = 14.sp),
                color = if (isFocused) Color.Black else Color.White
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption.copy(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = if (isFocused) Color.Black.copy(alpha = 0.85f) else Color.White.copy(alpha = 0.85f)
                )
            }
        }

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.selected),
                tint = if (isFocused) Color.Black else Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
