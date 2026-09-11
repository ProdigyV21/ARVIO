package com.arflix.tv.ui.screens.player.mobile

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.AudioTrackInfo
import com.arflix.tv.ui.screens.player.SubtitleFontOption
import com.arflix.tv.ui.screens.player.resolveSubtitleTypeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import kotlin.math.abs
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.gestures.detectDragGestures
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * Shared dim scrim backdrop. Tapping dismisses any open panel.
 */
@Composable
fun SharedScrimBackdrop(
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(240)),
        exit = fadeOut(tween(200)),
        modifier = modifier.zIndex(38f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MobilePlayerTokens.ScrimColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                )
        )
    }
}

/**
 * Episodes Drawer (slides in from right side).
 */
@Composable
fun MobileEpisodesDrawer(
    visible: Boolean,
    episodes: List<Episode>,
    currentEpisodeNumber: Int?,
    onSelectEpisode: (Episode) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(tween(300)) { it },
        exit = slideOutHorizontally(tween(260)) { it },
        modifier = modifier.zIndex(40f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.82f)
                    .background(MobilePlayerTokens.PanelBg)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .displayCutoutPadding()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {} // Consume taps
                    )
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "This Season",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MobileIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = "Close",
                        onClick = onClose
                    )
                }

                // Episode List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(episodes, key = { index, ep -> "${index}_${ep.id}" }) { _, ep ->
                        val isActive = ep.episodeNumber == currentEpisodeNumber
                        EpisodeRow(
                            episode = ep,
                            isActive = isActive,
                            onClick = {
                                onSelectEpisode(ep)
                                onClose()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EpisodeRow(
    episode: Episode,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MobilePlayerTokens.ShapeCard)
            .background(if (isActive) MobilePlayerTokens.PanelBg2 else Color.Transparent)
            .then(
                if (isActive) Modifier.border(1.dp, MobilePlayerTokens.PanelBorder, MobilePlayerTokens.ShapeCard)
                else Modifier
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Thumbnail
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(36.dp)
                .clip(MobilePlayerTokens.ShapeThumb)
                .background(MobilePlayerTokens.PanelBg2)
                .then(
                    if (isActive) Modifier.border(1.5.dp, Color.White, MobilePlayerTokens.ShapeThumb)
                    else Modifier
                )
        ) {
            if (!episode.stillPath.isNullOrBlank()) {
                val fullUrl = if (episode.stillPath.startsWith("http")) episode.stillPath
                else "https://image.tmdb.org/t/p/w300${episode.stillPath}"
                AsyncImage(
                    model = fullUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Text (Title + Runtime)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "E${episode.episodeNumber} — ${episode.name}",
                color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                fontSize = 12.5.sp,
                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            val durationLabel = if (episode.runtime > 0) "${episode.runtime}m" else ""
            if (durationLabel.isNotBlank()) {
                Text(
                    text = durationLabel,
                    color = MobilePlayerTokens.InkTertiary,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }

        // Checkmark
        if (isActive) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MobilePlayerTokens.InkPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Sources Drawer (slides in from right side).
 */
@Composable
fun MobileSourcesDrawer(
    visible: Boolean,
    streams: List<StreamSource>,
    selectedStream: StreamSource?,
    onSelectSource: (StreamSource) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(tween(300)) { it },
        exit = slideOutHorizontally(tween(260)) { it },
        modifier = modifier.zIndex(40f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.82f)
                    .background(MobilePlayerTokens.PanelBg)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .displayCutoutPadding()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Source",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MobileIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = "Close",
                        onClick = onClose
                    )
                }

                val effectiveStreams = remember(streams, selectedStream) {
                    if (streams.isEmpty() && selectedStream != null) listOf(selectedStream)
                    else streams
                }

                if (effectiveStreams.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No sources available",
                            color = MobilePlayerTokens.InkTertiary,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    // Sources list
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        itemsIndexed(effectiveStreams, key = { index, stream -> "${index}_${stream.addonId}_${stream.url}" }) { _, stream ->
                            val isActive = selectedStream?.url == stream.url
                            SourceRow(
                                stream = stream,
                                isActive = isActive,
                                onClick = {
                                    onSelectSource(stream)
                                    onClose()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SourceRow(
    stream: StreamSource,
    isActive: Boolean,
    onClick: () -> Unit
) {
    val qualityLabel = stream.quality.ifBlank { stream.source.ifBlank { "Source" } }
    val metaLabel = listOfNotNull(
        stream.addonName.takeIf { it.isNotBlank() },
        stream.size.takeIf { it.isNotBlank() } ?: stream.sizeBytes?.let { formatBytes(it) }
    ).joinToString(" — ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MobilePlayerTokens.ShapeCard)
            .background(if (isActive) MobilePlayerTokens.PanelBg2 else Color.Transparent)
            .then(
                if (isActive) Modifier.border(1.dp, MobilePlayerTokens.PanelBorder, MobilePlayerTokens.ShapeCard)
                else Modifier
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = qualityLabel,
                color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                fontSize = 13.sp,
                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
            )
            if (metaLabel.isNotBlank()) {
                Text(
                    text = metaLabel,
                    color = MobilePlayerTokens.InkTertiary,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (isActive) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MobilePlayerTokens.InkPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Native touch-optimized Audio Bottom Sheet.
 * Root: Instant 1-tap audio track selection + Audio Settings entry.
 * Sub-page: Audio Settings (Audio Delay with +/-100ms and +/-25ms stepper chips, Volume Normalization, Volume Boost, Gemini Live Voice Translation).
 */
@Composable
fun MobileAudioTrackSheet(
    visible: Boolean,
    audioTracks: List<AudioTrackInfo>,
    selectedAudioIndex: Int,
    volumeLevelPct: Float,
    volumeBoostDb: Int,
    audioDelayMs: Long,
    volumeNormalization: Boolean,
    isLiveAudioTranslating: Boolean,
    onSelectAudio: (AudioTrackInfo) -> Unit,
    onUpdateVolumeLevel: (Float) -> Unit,
    onVolumeBoostChange: (Int) -> Unit = {},
    onUpdateAudioDelay: (Long) -> Unit,
    onToggleVolumeNorm: (Boolean) -> Unit,
    onToggleLiveAudioTranslation: () -> Unit,
    onClose: () -> Unit,
    initialTab: String = "tracks",
    initialView: String = initialTab,
    modifier: Modifier = Modifier
) {
    val viewStack = remember(visible, initialView) {
        mutableStateListOf(
            when (initialView) {
                "settings", "audiosettings" -> "audiosettings"
                "delay", "audiodelay" -> "audiodelay"
                else -> "root"
            }
        )
    }

    LaunchedEffect(visible, initialView) {
        if (visible) {
            viewStack.clear()
            viewStack.add(
                when (initialView) {
                    "settings", "audiosettings" -> "audiosettings"
                    "delay", "audiodelay" -> "audiodelay"
                    else -> "root"
                }
            )
        }
    }

    BackHandler(enabled = visible && viewStack.size > 1) {
        viewStack.removeAt(viewStack.lastIndex)
    }

    val currentView = viewStack.lastOrNull() ?: "root"
    val title = when (currentView) {
        "audiodelay" -> "Audio Delay"
        "audiosettings" -> "Audio Settings"
        else -> "Audio"
    }

    MobileBottomSheetBase(
        visible = visible,
        title = title,
        showBackButton = viewStack.size > 1,
        onBack = { if (viewStack.size > 1) viewStack.removeAt(viewStack.lastIndex) },
        onClose = onClose,
        headerExtra = null,
        modifier = modifier
    ) {
        when (currentView) {
            "audiodelay" -> {
                DelayAdjusterContent(
                    title = "Audio Delay",
                    delayMs = audioDelayMs,
                    minMs = -5000L,
                    maxMs = 5000L,
                    onUpdateDelay = onUpdateAudioDelay
                )
            }
            "audiosettings" -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    SectionHeader("ENHANCEMENTS")
                    ToggleRow(
                        label = "Volume Normalization",
                        checked = volumeNormalization,
                        onToggle = { onToggleVolumeNorm(!volumeNormalization) }
                    )

                    ToggleRow(
                        label = "Gemini Live Voice Translation",
                        checked = isLiveAudioTranslating,
                        onToggle = onToggleLiveAudioTranslation
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    item { SectionHeader("TRACKS") }
                    if (audioTracks.isEmpty()) {
                        item {
                            Text(
                                text = "Default Audio Track",
                                color = MobilePlayerTokens.InkSecondary,
                                fontSize = 13.5.sp,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                            )
                        }
                    } else {
                        itemsIndexed(audioTracks) { index, track ->
                            val isActive = index == selectedAudioIndex
                            val trackTitle = track.label?.takeIf { it.isNotBlank() }
                                ?: track.language?.takeIf { it.isNotBlank() }
                                ?: "Audio ${index + 1}"
                            val codecDetail = formatAudioCodecDisplayName(track.codec)
                            val channelDetail = when (track.channelCount) {
                                1 -> "Mono"
                                2 -> "Stereo"
                                6 -> "5.1"
                                8 -> "7.1"
                                else -> if (track.channelCount > 0) "${track.channelCount} ch" else null
                            }
                            val metaSummary = listOfNotNull(codecDetail, channelDetail).joinToString(" · ")

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MobilePlayerTokens.ShapeCard)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            onSelectAudio(track)
                                            onClose()
                                        }
                                    )
                                    .padding(horizontal = 14.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    if (isActive) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = MobilePlayerTokens.InkPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Spacer(modifier = Modifier.size(16.dp))
                                    }
                                    Column {
                                        Text(
                                            text = trackTitle,
                                            color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                            fontSize = 13.5.sp,
                                            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                                        )
                                        if (metaSummary.isNotBlank()) {
                                            Text(
                                                text = metaSummary,
                                                color = MobilePlayerTokens.InkTertiary,
                                                fontSize = 11.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Settings Section at the bottom
                    item {
                        Spacer(modifier = Modifier.height(6.dp))
                        SectionHeader("SETTINGS")
                    }
                    item {
                        NavRow(
                            label = "Audio Settings",
                            meta = "Enhancements & sync",
                            onClick = { viewStack.add("audiosettings") }
                        )
                    }
                    item {
                        NavRow(
                            label = "Audio Delay",
                            meta = if (audioDelayMs != 0L) (if (audioDelayMs > 0) "+$audioDelayMs ms" else "$audioDelayMs ms") else "0 ms",
                            onClick = { viewStack.add("audiodelay") }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Subtitles Bottom Sheet with Pinned Top Tabs (Subtitles vs Settings).
 */
@Composable
fun MobileSubtitlesSheet(
    visible: Boolean,
    subtitles: List<Subtitle>,
    selectedSubtitle: Subtitle?,
    isFindingBestMatch: Boolean = false,
    matchLanguageName: String = "",
    onFindBestMatch: () -> Unit = {},
    isAiTranslating: Boolean = false,
    isAiAvailable: Boolean = false,
    aiTargetLanguageName: String = "",
    onActivateAiTranslation: () -> Unit = {},
    subtitleDelayMs: Long = 0L,
    onUpdateSubtitleDelay: (Long) -> Unit = {},
    subtitleSizePct: Int = 100,
    onUpdateSubtitleSize: (Int) -> Unit = {},
    subtitleColorHex: String = "#fff",
    onUpdateSubtitleColor: (String) -> Unit = {},
    subtitlePosition: String = "bottom",
    onUpdateSubtitlePosition: (String) -> Unit = {},
    subtitleVerticalPct: Int = 2,
    onUpdateSubtitleVerticalPosition: (Int) -> Unit = {},
    onStartInteractiveRepositioning: () -> Unit = {},
    subtitleStyle: String = "Bold",
    onUpdateSubtitleStyle: (String) -> Unit = {},
    subtitleFont: String = "System",
    onUpdateSubtitleFont: (String) -> Unit = {},
    subtitleStylized: Boolean = true,
    onUpdateSubtitleStylized: (Boolean) -> Unit = {},
    subtitlePreloadEnabled: Boolean = false,
    onToggleSubtitlePreload: (Boolean) -> Unit = {},
    filterSubtitlesByLanguage: Boolean = true,
    onToggleFilterSubtitlesByLanguage: (Boolean) -> Unit = {},
    subtitleRemoveHearingImpaired: Boolean = false,
    onToggleSubtitleRemoveHearingImpaired: (Boolean) -> Unit = {},
    initialView: String = "root",
    onSelectSubtitle: (Subtitle?) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewStack = remember(visible, initialView) {
        mutableStateListOf(
            when (initialView) {
                "settings", "substyle" -> "substyle"
                "delay", "subdelay" -> "subdelay"
                else -> "root"
            }
        )
    }
    var selectedLanguageName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(visible, initialView) {
        if (visible) {
            viewStack.clear()
            viewStack.add(
                when (initialView) {
                    "settings", "substyle" -> "substyle"
                    "delay", "subdelay" -> "subdelay"
                    else -> "root"
                }
            )
            selectedLanguageName = null
        }
    }

    BackHandler(enabled = visible && (viewStack.size > 1 || selectedLanguageName != null)) {
        if (viewStack.size > 1) {
            viewStack.removeAt(viewStack.lastIndex)
        } else {
            selectedLanguageName = null
        }
    }

    val subtitleGroups = remember(subtitles) {
        subtitles
            .groupBy { getFullLanguageName(it.lang).ifBlank { "Unknown" } }
            .toList()
            .sortedBy { (lang, _) -> lang }
    }

    val activeLangName = selectedSubtitle?.let { getFullLanguageName(it.lang).ifBlank { "Unknown" } }

    val currentView = viewStack.lastOrNull() ?: "root"
    val title = when {
        currentView == "subdelay" -> "Subtitle Delay"
        currentView == "fonts" -> "Subtitle Font"
        currentView == "tracks" -> "${selectedLanguageName ?: "Track"} Subtitles"
        currentView == "substyle" -> "Subtitle Settings"
        else -> "Subtitles"
    }
    val showBack = viewStack.size > 1
    val MAX_SUBTITLE_OFFSET_MS = 120_000L

    MobileBottomSheetBase(
        visible = visible,
        title = title,
        showBackButton = showBack,
        onBack = {
            if (viewStack.size > 1) {
                viewStack.removeAt(viewStack.lastIndex)
            } else {
                selectedLanguageName = null
            }
        },
        onClose = onClose,
        headerExtra = null,
        modifier = modifier
    ) {
        when {
            currentView == "subdelay" -> {
                DelayAdjusterContent(
                    title = "Subtitle Delay",
                    delayMs = subtitleDelayMs,
                    minMs = -MAX_SUBTITLE_OFFSET_MS,
                    maxMs = MAX_SUBTITLE_OFFSET_MS,
                    onUpdateDelay = onUpdateSubtitleDelay
                )
            }
            currentView == "substyle" -> {
                SubtitleSettingsContent(
                    subtitleSizePct = subtitleSizePct,
                    onUpdateSubtitleSize = onUpdateSubtitleSize,
                    subtitleColorHex = subtitleColorHex,
                    onUpdateSubtitleColor = onUpdateSubtitleColor,
                    subtitlePosition = subtitlePosition,
                    onUpdateSubtitlePosition = onUpdateSubtitlePosition,
                    subtitleVerticalPct = subtitleVerticalPct,
                    onUpdateSubtitleVerticalPosition = onUpdateSubtitleVerticalPosition,
                    onStartInteractiveRepositioning = onStartInteractiveRepositioning,
                    subtitleStyle = subtitleStyle,
                    onUpdateSubtitleStyle = onUpdateSubtitleStyle,
                    subtitleFont = subtitleFont,
                    subtitleStylized = subtitleStylized,
                    onUpdateSubtitleStylized = onUpdateSubtitleStylized,
                    subtitlePreloadEnabled = subtitlePreloadEnabled,
                    onToggleSubtitlePreload = onToggleSubtitlePreload,
                    filterSubtitlesByLanguage = filterSubtitlesByLanguage,
                    onToggleFilterSubtitlesByLanguage = onToggleFilterSubtitlesByLanguage,
                    subtitleRemoveHearingImpaired = subtitleRemoveHearingImpaired,
                    onToggleSubtitleRemoveHearingImpaired = onToggleSubtitleRemoveHearingImpaired,
                    onOpenFonts = { viewStack.add("fonts") }
                )
            }
            currentView == "fonts" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(SubtitleFontOption.entries) { fontOption ->
                        val isSelected = fontOption.preferenceValue.equals(subtitleFont, ignoreCase = true)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MobilePlayerTokens.ShapeCard)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onUpdateSubtitleFont(fontOption.preferenceValue)
                                        viewStack.removeAt(viewStack.lastIndex)
                                    }
                                )
                                .padding(horizontal = 14.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = fontOption.preferenceValue,
                                color = if (isSelected) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                fontSize = 13.5.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = MobilePlayerTokens.InkPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
            currentView == "tracks" -> {
                val currentLang = selectedLanguageName
                val tracksForLang = subtitleGroups.firstOrNull { it.first == currentLang }?.second ?: emptyList()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    itemsIndexed(tracksForLang, key = { index, sub -> "${index}_${sub.id}_${sub.url}" }) { index, sub ->
                        val isActive = selectedSubtitle?.id == sub.id
                        val trackTitle = sub.label.ifBlank { "$currentLang Track ${index + 1}" }
                        val isSdh = trackTitle.contains("sdh", ignoreCase = true) ||
                            trackTitle.contains("cc", ignoreCase = true) ||
                            trackTitle.contains("hearing", ignoreCase = true)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MobilePlayerTokens.ShapeCard)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onSelectSubtitle(sub)
                                        onClose()
                                    }
                                )
                                .padding(horizontal = 14.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                if (isActive) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = MobilePlayerTokens.InkPrimary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.size(16.dp))
                                }
                                Column {
                                    Text(
                                        text = trackTitle,
                                        color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                        fontSize = 13.5.sp,
                                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                                    )

                                    Row(
                                        modifier = Modifier.padding(top = 4.dp),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (sub.isEmbedded) {
                                            SubtitleBadge(text = "Embedded")
                                        } else if (sub.provider.isNotBlank()) {
                                            SubtitleBadge(text = sub.provider)
                                        }
                                        if (isSdh) {
                                            SubtitleBadge(text = "SDH")
                                        }
                                        if (sub.isForced) {
                                            SubtitleBadge(text = "Forced")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    item {
                        val matchTitle = if (matchLanguageName.isNotBlank()) "Find Best Match ($matchLanguageName)" else "Find Best Match (Auto)"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MobilePlayerTokens.ShapeCard)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onFindBestMatch()
                                        onClose()
                                    }
                                )
                                .padding(horizontal = 14.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isFindingBestMatch) "Scanning Best Match…" else matchTitle,
                                    color = MobilePlayerTokens.InkPrimary,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Auto-sync and pick best timing match",
                                    color = MobilePlayerTokens.InkTertiary,
                                    fontSize = 11.sp
                                )
                            }
                            SubtitleBadge(text = if (isFindingBestMatch) "Scanning" else "Auto Sync")
                        }
                    }

                    if (isAiAvailable || isAiTranslating) {
                        item {
                            val aiTitle = if (aiTargetLanguageName.isNotBlank()) "AI Translation ($aiTargetLanguageName)" else "AI Translation"
                            ToggleRow(
                                label = aiTitle,
                                checked = isAiTranslating,
                                onToggle = { onActivateAiTranslation() }
                            )
                        }
                    }

                    item {
                        val isOffSelected = selectedSubtitle == null && !isAiTranslating
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MobilePlayerTokens.ShapeCard)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onSelectSubtitle(null)
                                        onClose()
                                    }
                                )
                                .padding(horizontal = 14.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                if (isOffSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = MobilePlayerTokens.InkPrimary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.size(16.dp))
                                }
                                Text(
                                    text = "Off",
                                    color = if (isOffSelected) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                    fontSize = 13.5.sp,
                                    fontWeight = if (isOffSelected) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(6.dp))
                        SectionHeader("LANGUAGES")
                    }

                    if (subtitleGroups.isEmpty()) {
                        item {
                            Text(
                                text = "No subtitles found",
                                color = MobilePlayerTokens.InkSecondary,
                                fontSize = 13.5.sp,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                            )
                        }
                    } else {
                        itemsIndexed(subtitleGroups, key = { index, group -> "${index}_${group.first}" }) { _, (langName, tracks) ->
                            val isLangActive = langName == activeLangName && selectedSubtitle != null
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MobilePlayerTokens.ShapeCard)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            selectedLanguageName = langName
                                            viewStack.add("tracks")
                                        }
                                    )
                                    .padding(horizontal = 14.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    if (isLangActive) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = MobilePlayerTokens.InkPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Spacer(modifier = Modifier.size(16.dp))
                                    }
                                    Column {
                                        Text(
                                            text = langName,
                                            color = if (isLangActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                            fontSize = 13.5.sp,
                                            fontWeight = if (isLangActive) FontWeight.SemiBold else FontWeight.Normal
                                        )
                                        if (isLangActive && selectedSubtitle != null) {
                                            val label = selectedSubtitle.label.ifBlank { "Track active" }
                                            Text(
                                                text = label,
                                                color = MobilePlayerTokens.InkTertiary,
                                                fontSize = 11.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = "${tracks.size}",
                                        color = MobilePlayerTokens.InkTertiary,
                                        fontSize = 12.5.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ChevronRight,
                                        contentDescription = null,
                                        tint = MobilePlayerTokens.InkTertiary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Settings Section at the bottom
                    item {
                        Spacer(modifier = Modifier.height(6.dp))
                        SectionHeader("SETTINGS")
                    }
                    item {
                        NavRow(
                            label = "Subtitle Settings",
                            meta = "Size, style, font & more",
                            onClick = { viewStack.add("substyle") }
                        )
                    }
                    item {
                        val delayMeta = if (subtitleDelayMs != 0L) (if (subtitleDelayMs > 0) "+$subtitleDelayMs ms" else "$subtitleDelayMs ms") else "0 ms"
                        NavRow(
                            label = "Subtitle Delay",
                            meta = delayMeta,
                            onClick = { viewStack.add("subdelay") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubtitleSettingsContent(
    subtitleSizePct: Int,
    onUpdateSubtitleSize: (Int) -> Unit,
    subtitleColorHex: String,
    onUpdateSubtitleColor: (String) -> Unit,
    subtitlePosition: String,
    onUpdateSubtitlePosition: (String) -> Unit,
    subtitleVerticalPct: Int,
    onUpdateSubtitleVerticalPosition: (Int) -> Unit,
    onStartInteractiveRepositioning: () -> Unit,
    subtitleStyle: String,
    onUpdateSubtitleStyle: (String) -> Unit,
    subtitleFont: String,
    subtitleStylized: Boolean,
    onUpdateSubtitleStylized: (Boolean) -> Unit,
    subtitlePreloadEnabled: Boolean,
    onToggleSubtitlePreload: (Boolean) -> Unit,
    filterSubtitlesByLanguage: Boolean,
    onToggleFilterSubtitlesByLanguage: (Boolean) -> Unit,
    subtitleRemoveHearingImpaired: Boolean,
    onToggleSubtitleRemoveHearingImpaired: (Boolean) -> Unit,
    onOpenFonts: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isBold = subtitleStyle.equals("Bold", ignoreCase = true)
    val previewTypeface = remember(context, subtitleFont, isBold) {
        resolveSubtitleTypeface(context, subtitleFont, isBold)
    }
    val previewFontFamily = remember(previewTypeface) {
        FontFamily(previewTypeface)
    }
    val previewColor = remember(subtitleColorHex) {
        when (subtitleColorHex.lowercase()) {
            "#ffe066" -> Color(0xFFFFE066)
            "#66d9ff" -> Color(0xFF66D9FF)
            "#ff6666" -> Color(0xFFFF6666)
            else -> Color.White
        }
    }
    val previewFontSize = (15f * (subtitleSizePct / 100f)).coerceIn(11f, 24f).sp

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SectionHeader("PREVIEW")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .clip(MobilePlayerTokens.ShapeCard)
                    .background(Color(0xFF0C0E14))
                    .border(1.dp, Color(0xFF1E2330), MobilePlayerTokens.ShapeCard)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hello, how are you?",
                    color = previewColor,
                    fontSize = previewFontSize,
                    fontFamily = previewFontFamily,
                    fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.95f),
                            offset = Offset(1.5f, 1.5f),
                            blurRadius = 3.5f
                        )
                    )
                )
            }
        }

        item {
            SectionHeader("SIZE")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MobilePlayerTokens.ShapeCard)
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Text size",
                    color = MobilePlayerTokens.InkPrimary,
                    fontSize = 13.5.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "–",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onUpdateSubtitleSize((subtitleSizePct - 10).coerceIn(50, 250)) }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                    Text(
                        text = "$subtitleSizePct%",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.width(46.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "+",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onUpdateSubtitleSize((subtitleSizePct + 10).coerceIn(50, 250)) }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }

        // Color
        item {
            SectionHeader("COLOR")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MobilePlayerTokens.ShapeCard)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val swatches = listOf(
                    "#fff" to Color(0xFFFFFFFF),
                    "#ffe066" to Color(0xFFFFE066),
                    "#66d9ff" to Color(0xFF66D9FF),
                    "#ff6666" to Color(0xFFFF6666)
                )
                swatches.forEach { (hex, color) ->
                    val isSelected = hex.equals(subtitleColorHex, ignoreCase = true)
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(color)
                            .then(
                                if (isSelected) Modifier.border(2.5.dp, Color(0xFF0B0C10), CircleShape)
                                    .border(4.dp, Color.White, CircleShape)
                                else Modifier.border(1.dp, Color(0x40FFFFFF), CircleShape)
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onUpdateSubtitleColor(hex) }
                            )
                    )
                }
            }
        }

        // Position (No fixed Bottom/Top presets, dedicated on-screen repositioning)
        item {
            SectionHeader("POSITION")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MobilePlayerTokens.ShapeCard)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onStartInteractiveRepositioning
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Subtitle Position",
                        color = MobilePlayerTokens.InkPrimary,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "$subtitleVerticalPct% from bottom",
                        color = MobilePlayerTokens.InkTertiary,
                        fontSize = 11.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Adjust on Screen",
                        color = Color(0xFFFFB300),
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Style & Font
        item {
            SectionHeader("STYLE & FONT")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MobilePlayerTokens.ShapeCard)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Font Weight",
                    color = MobilePlayerTokens.InkPrimary,
                    fontSize = 13.5.sp
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    listOf("Bold" to "Bold", "Normal" to "Normal").forEach { (valKey, label) ->
                        val isSelected = valKey.equals(subtitleStyle, ignoreCase = true)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) Color.White.copy(alpha = 0.22f) else Color.Transparent)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { onUpdateSubtitleStyle(valKey) }
                                )
                                .padding(horizontal = 14.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                color = if (isSelected) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                fontSize = 12.5.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }

        item {
            NavRow(
                label = "Font Family",
                meta = subtitleFont,
                onClick = onOpenFonts
            )
        }

        item {
            ToggleRow(
                label = "Stylized Subtitles (ASS / SSA)",
                checked = subtitleStylized,
                onToggle = { onUpdateSubtitleStylized(!subtitleStylized) }
            )
        }

        item {
            SectionHeader("GENERAL")
            ToggleRow(
                label = "Preload Subtitles",
                checked = subtitlePreloadEnabled,
                onToggle = { onToggleSubtitlePreload(!subtitlePreloadEnabled) }
            )
        }

        item {
            ToggleRow(
                label = "Filter by App Language",
                checked = filterSubtitlesByLanguage,
                onToggle = { onToggleFilterSubtitlesByLanguage(!filterSubtitlesByLanguage) }
            )
        }

        item {
            ToggleRow(
                label = "Remove Hearing Impaired (SDH)",
                checked = subtitleRemoveHearingImpaired,
                onToggle = { onToggleSubtitleRemoveHearingImpaired(!subtitleRemoveHearingImpaired) }
            )
        }
    }
}

@Composable
private fun SubtitleBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White.copy(alpha = 0.10f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            color = MobilePlayerTokens.InkTertiary,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

private fun getFullLanguageName(code: String?): String {
    if (code.isNullOrBlank()) return "Unknown"
    val raw = code.trim()
    val normalizedCode = raw.lowercase().replace('_', '-')

    val mapped = when {
        normalizedCode in listOf("en", "eng", "english") -> "English"
        normalizedCode in listOf("es", "spa", "spanish") -> "Spanish"
        normalizedCode in listOf("es-419", "es-la") -> "Spanish (Latin America)"
        normalizedCode in listOf("es-es") -> "Spanish (Spain)"
        normalizedCode in listOf("nl", "nld", "dut", "dutch") -> "Dutch"
        normalizedCode in listOf("de", "ger", "deu", "german") -> "German"
        normalizedCode in listOf("fr", "fra", "fre", "french") -> "French"
        normalizedCode in listOf("it", "ita", "italian") -> "Italian"
        normalizedCode in listOf("pt", "por", "portuguese") -> "Portuguese"
        normalizedCode in listOf("pt-br", "pob") -> "Portuguese (Brazil)"
        normalizedCode in listOf("pt-pt") -> "Portuguese (Portugal)"
        normalizedCode in listOf("ru", "rus", "russian") -> "Russian"
        normalizedCode in listOf("ja", "jpn", "japanese") -> "Japanese"
        normalizedCode in listOf("ko", "kor", "korean") -> "Korean"
        normalizedCode in listOf("zh", "chi", "zho", "chinese") -> "Chinese"
        normalizedCode in listOf("zh-cn", "zh-hans", "chs") -> "Chinese (Simplified)"
        normalizedCode in listOf("zh-tw", "zh-hk", "zh-hant", "cht") -> "Chinese (Traditional)"
        normalizedCode in listOf("ar", "ara", "arabic") -> "Arabic"
        normalizedCode in listOf("hi", "hin", "hindi") -> "Hindi"
        normalizedCode in listOf("te", "tel", "telugu") -> "Telugu"
        normalizedCode in listOf("ta", "tam", "tamil") -> "Tamil"
        normalizedCode in listOf("ml", "mal", "malayalam") -> "Malayalam"
        normalizedCode in listOf("kn", "kan", "kannada") -> "Kannada"
        normalizedCode in listOf("mr", "mar", "marathi") -> "Marathi"
        normalizedCode in listOf("bn", "ben", "bengali") -> "Bengali"
        normalizedCode in listOf("gu", "guj", "gujarati") -> "Gujarati"
        normalizedCode in listOf("pa", "pan", "punjabi") -> "Punjabi"
        normalizedCode in listOf("ur", "urd", "urdu") -> "Urdu"
        normalizedCode in listOf("tr", "tur", "turkish") -> "Turkish"
        normalizedCode in listOf("pl", "pol", "polish") -> "Polish"
        normalizedCode in listOf("sv", "swe", "swedish") -> "Swedish"
        normalizedCode in listOf("no", "nor", "nob", "norwegian") -> "Norwegian"
        normalizedCode in listOf("da", "dan", "danish") -> "Danish"
        normalizedCode in listOf("fi", "fin", "finnish") -> "Finnish"
        normalizedCode in listOf("el", "gre", "ell", "greek") -> "Greek"
        normalizedCode in listOf("he", "heb", "hebrew") -> "Hebrew"
        normalizedCode in listOf("id", "ind", "indonesian") -> "Indonesian"
        normalizedCode in listOf("vi", "vie", "vietnamese") -> "Vietnamese"
        normalizedCode in listOf("th", "tha", "thai") -> "Thai"
        normalizedCode in listOf("cs", "ces", "cze", "czech") -> "Czech"
        normalizedCode in listOf("hu", "hun", "hungarian") -> "Hungarian"
        normalizedCode in listOf("ro", "ron", "rum", "romanian") -> "Romanian"
        normalizedCode in listOf("uk", "ukr", "ukrainian") -> "Ukrainian"
        normalizedCode in listOf("ms", "msa", "may", "malay") -> "Malay"
        normalizedCode in listOf("fa", "fas", "per", "persian") -> "Persian"
        normalizedCode in listOf("tl", "tgl", "fil", "tagalog", "filipino") -> "Tagalog"
        normalizedCode in listOf("bg", "bul", "bulgarian") -> "Bulgarian"
        normalizedCode in listOf("sr", "srp", "serbian") -> "Serbian"
        normalizedCode in listOf("hr", "hrv", "croatian") -> "Croatian"
        normalizedCode in listOf("sk", "slk", "slo", "slovak") -> "Slovak"
        normalizedCode in listOf("sl", "slv", "slovenian") -> "Slovenian"
        normalizedCode in listOf("lt", "lit", "lithuanian") -> "Lithuanian"
        normalizedCode in listOf("lv", "lav", "latvian") -> "Latvian"
        normalizedCode in listOf("et", "est", "estonian") -> "Estonian"
        normalizedCode in listOf("is", "isl", "ice", "icelandic") -> "Icelandic"
        normalizedCode in listOf("ca", "cat", "catalan") -> "Catalan"
        normalizedCode in listOf("eu", "eus", "baq", "basque") -> "Basque"
        normalizedCode in listOf("gl", "glg", "galician") -> "Galician"
        normalizedCode in listOf("hy", "hye", "arm", "armenian") -> "Armenian"
        normalizedCode in listOf("ka", "kat", "geo", "georgian") -> "Georgian"
        normalizedCode in listOf("az", "aze", "azerbaijani") -> "Azerbaijani"
        normalizedCode in listOf("kk", "kaz", "kazakh") -> "Kazakh"
        normalizedCode in listOf("uz", "uzb", "uzbek") -> "Uzbek"
        normalizedCode in listOf("mn", "mon", "mongolian") -> "Mongolian"
        normalizedCode in listOf("ne", "nep", "nepali") -> "Nepali"
        normalizedCode in listOf("si", "sin", "sinhala") -> "Sinhala"
        normalizedCode in listOf("my", "mya", "bur", "burmese") -> "Burmese"
        normalizedCode in listOf("km", "khm", "khmer") -> "Khmer"
        normalizedCode in listOf("lo", "lao") -> "Lao"
        normalizedCode in listOf("am", "amh", "amharic") -> "Amharic"
        normalizedCode in listOf("sw", "swa", "swahili") -> "Swahili"
        normalizedCode in listOf("af", "afr", "afrikaans") -> "Afrikaans"
        normalizedCode in listOf("sq", "sqi", "alb", "albanian") -> "Albanian"
        normalizedCode in listOf("bs", "bos", "bosnian") -> "Bosnian"
        normalizedCode in listOf("mk", "mkd", "mac", "macedonian") -> "Macedonian"
        normalizedCode in listOf("cy", "cym", "wel", "welsh") -> "Welsh"
        normalizedCode in listOf("ga", "gle", "irish") -> "Irish"
        normalizedCode in listOf("gd", "gla", "scottish gaelic") -> "Scottish Gaelic"
        normalizedCode in listOf("la", "lat", "latin") -> "Latin"
        normalizedCode in listOf("eo", "epo", "esperanto") -> "Esperanto"
        normalizedCode in listOf("und", "undetermined", "unknown") -> "Unknown"
        else -> null
    }

    if (mapped != null) return mapped

    // Java Locale fallback for any ISO language code or locale tag
    return runCatching {
        val loc = if (normalizedCode.contains("-")) {
            java.util.Locale.forLanguageTag(normalizedCode)
        } else {
            java.util.Locale.Builder().setLanguage(normalizedCode).build()
        }
        val display = loc.getDisplayLanguage(java.util.Locale.ENGLISH)
        if (display.isNotBlank() && !display.equals(normalizedCode, ignoreCase = true)) {
            display
        } else {
            null
        }
    }.getOrNull() ?: raw.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

/**
 * Playback Speed Bottom Sheet.
 */
@Composable
fun MobilePlaybackSpeedSheet(
    visible: Boolean,
    currentSpeed: Float,
    onSelectSpeed: (Float) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val speeds = listOf(0.5f, 1.0f, 1.25f, 1.5f, 2.0f)
    MobileBottomSheetBase(
        visible = visible,
        title = "Playback Speed",
        onClose = onClose,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            speeds.forEach { sp ->
                val isActive = sp == currentSpeed
                val label = if (sp == 1.0f) "1x" else "${sp}x"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MobilePlayerTokens.ShapeCard)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onSelectSpeed(sp)
                                onClose()
                            }
                        )
                        .padding(horizontal = 12.dp, vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = label,
                        color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                        fontSize = 13.5.sp,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    )
                    if (isActive) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = MobilePlayerTokens.InkPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Hierarchical More Settings Bottom Sheet.
 */
@Composable
fun MobileMoreSettingsSheet(
    visible: Boolean,
    autoplayNext: Boolean,
    autoSkipIntro: Boolean,
    autoSkipOutro: Boolean,
    aspectRatio: String,
    onToggleAutoplay: (Boolean) -> Unit,
    onToggleAutoSkipIntro: (Boolean) -> Unit,
    onToggleAutoSkipOutro: (Boolean) -> Unit,
    onSelectAspectRatio: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewStack = remember { mutableStateListOf("root") }

    // Reset view stack when opening fresh, NOT during exit animation
    LaunchedEffect(visible) {
        if (visible) {
            viewStack.clear()
            viewStack.add("root")
        }
    }

    // Intercept hardware/gesture back when inside sub-pages
    BackHandler(enabled = visible && viewStack.size > 1) {
        viewStack.removeAt(viewStack.lastIndex)
    }

    val currentView = viewStack.lastOrNull() ?: "root"
    val title = when (currentView) {
        "aspect" -> "Aspect Ratio"
        else -> "More Settings"
    }

    MobileBottomSheetBase(
        visible = visible,
        title = title,
        showBackButton = viewStack.size > 1,
        onBack = { if (viewStack.size > 1) viewStack.removeAt(viewStack.lastIndex) },
        onClose = onClose,
        modifier = modifier
    ) {
        when (currentView) {
            "root" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    // Playback Section
                    item { SectionHeader("PLAYBACK") }
                    item {
                        ToggleRow(
                            label = "Autoplay next episode",
                            checked = autoplayNext,
                            onToggle = { onToggleAutoplay(!autoplayNext) }
                        )
                    }
                    item {
                        ToggleRow(
                            label = "Automatically skip intro",
                            checked = autoSkipIntro,
                            onToggle = { onToggleAutoSkipIntro(!autoSkipIntro) }
                        )
                    }
                    item {
                        ToggleRow(
                            label = "Automatically skip outro",
                            checked = autoSkipOutro,
                            onToggle = { onToggleAutoSkipOutro(!autoSkipOutro) }
                        )
                    }

                    // Video Section
                    item { SectionHeader("VIDEO") }
                    item {
                        NavRow(
                            label = "Aspect Ratio",
                            meta = aspectRatio,
                            onClick = { viewStack.add("aspect") }
                        )
                    }
                }
            }
            "aspect" -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    listOf("Auto", "Fit to Screen", "Stretch", "Crop").forEach { mode ->
                        val isActive = mode.equals(aspectRatio, ignoreCase = true)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MobilePlayerTokens.ShapeCard)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onSelectAspectRatio(mode)
                                        if (viewStack.size > 1) viewStack.removeAt(viewStack.lastIndex)
                                    }
                                )
                                .padding(horizontal = 12.dp, vertical = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = mode,
                                color = if (isActive) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                                fontSize = 13.5.sp,
                                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                            )
                            if (isActive) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = MobilePlayerTokens.InkPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        color = MobilePlayerTokens.InkTertiary,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp,
        modifier = Modifier.padding(start = 12.dp, top = 10.dp, bottom = 4.dp)
    )
}

@Composable
private fun ToggleRow(
    label: String,
    checked: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MobilePlayerTokens.ShapeCard)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onToggle
            )
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MobilePlayerTokens.InkPrimary,
            fontSize = 13.5.sp
        )
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MobilePlayerTokens.InkPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun NavRow(
    label: String,
    meta: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MobilePlayerTokens.ShapeCard)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MobilePlayerTokens.InkPrimary,
            fontSize = 13.5.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = meta,
                color = MobilePlayerTokens.InkTertiary,
                fontSize = 12.sp
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MobilePlayerTokens.InkTertiary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun StepperPanel(
    label: String,
    valueText: String,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = MobilePlayerTokens.InkSecondary,
            fontSize = 13.5.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "–",
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDecrease
                    )
                    .padding(4.dp)
            )
            Text(
                text = valueText,
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.width(60.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "+",
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onIncrease
                    )
                    .padding(4.dp)
            )
        }
    }
}

@Composable
private fun ExactDelayInputDialog(
    currentDelayMs: Long,
    title: String,
    onDismiss: () -> Unit,
    onApply: (Long) -> Unit
) {
    var textValue by remember { mutableStateOf(currentDelayMs.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF161A26),
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Enter offset in milliseconds (negative = advance, positive = delay):",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 13.sp
                )
                OutlinedTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFFFB300),
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        cursorColor = Color(0xFFFFB300)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val parsed = textValue.trim().toLongOrNull() ?: currentDelayMs
                    onApply(parsed)
                }
            ) {
                Text("Apply", color = Color(0xFFFFB300), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = { onApply(0L) }) {
                    Text("Reset (0 ms)", color = Color.White.copy(alpha = 0.65f))
                }
                TextButton(onClick = onDismiss) {
                    Text("Cancel", color = Color.White.copy(alpha = 0.65f))
                }
            }
        }
    )
}

@Composable
private fun DelayAdjusterContent(
    title: String,
    delayMs: Long,
    minMs: Long = -5000L,
    maxMs: Long = 5000L,
    onUpdateDelay: (Long) -> Unit
) {
    var showInputDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Big Readout with Amber Accent when != 0
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { showInputDialog = true }
                )
                .padding(horizontal = 24.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (delayMs > 0) "+$delayMs ms" else "$delayMs ms",
                color = if (delayMs != 0L) Color(0xFFFFB300) else MobilePlayerTokens.InkPrimary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (delayMs == 0L) "Synchronized • Tap to type number" else if (delayMs > 0) "Delayed (+) • Tap to type number" else "Advanced (–) • Tap to type number",
                color = MobilePlayerTokens.InkTertiary,
                fontSize = 11.5.sp
            )
        }

        // Continuous Slider with 0 at Center
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                value = delayMs.toFloat().coerceIn(minMs.toFloat(), maxMs.toFloat()),
                onValueChange = { onUpdateDelay(it.roundToLong()) },
                valueRange = minMs.toFloat()..maxMs.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = if (delayMs != 0L) Color(0xFFFFB300) else Color.White,
                    activeTrackColor = if (delayMs != 0L) Color(0xFFFFB300) else Color.White,
                    inactiveTrackColor = Color.White.copy(alpha = 0.2f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${minMs / 1000}s",
                    color = MobilePlayerTokens.InkTertiary,
                    fontSize = 11.sp
                )
                Text(
                    text = "0 ms",
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "+${maxMs / 1000}s",
                    color = MobilePlayerTokens.InkTertiary,
                    fontSize = 11.sp
                )
            }
        }

        // Reset Button if != 0
        if (delayMs != 0L) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.08f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onUpdateDelay(0L) }
                    )
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Reset to 0 ms",
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    if (showInputDialog) {
        ExactDelayInputDialog(
            currentDelayMs = delayMs,
            title = title,
            onDismiss = { showInputDialog = false },
            onApply = { newMs ->
                onUpdateDelay(newMs.coerceIn(minMs, maxMs))
                showInputDialog = false
            }
        )
    }
}

@Composable
private fun CombinedClickableRow(
    label: String,
    meta: String = "",
    subtitle: String? = null,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MobilePlayerTokens.ShapeCard)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onLongClick() }
                )
            }
            .padding(horizontal = 14.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = MobilePlayerTokens.InkPrimary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Medium
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = MobilePlayerTokens.InkTertiary,
                    fontSize = 11.sp
                )
            }
        }
        if (meta.isNotBlank()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = meta,
                    color = MobilePlayerTokens.InkSecondary,
                    fontSize = 12.5.sp
                )
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MobilePlayerTokens.InkTertiary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

/**
 * Tab selector for segmented sheet switching.
 */
@Composable
private fun SheetTabSelector(
    selectedTab: String,
    tabs: List<Pair<String, String>>,
    onSelectTab: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tabs.forEach { (key, label) ->
            val isSelected = selectedTab == key
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) Color.White.copy(alpha = 0.22f) else Color.Transparent)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onSelectTab(key) }
                    )
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = if (isSelected) MobilePlayerTokens.InkPrimary else MobilePlayerTokens.InkSecondary,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            }
        }
    }
}

/**
 * Base bottom sheet container with drag handle, title, optional back, and close button.
 */
@Composable
private fun MobileBottomSheetBase(
    visible: Boolean,
    title: String,
    showBackButton: Boolean = false,
    onBack: () -> Unit = {},
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    headerExtra: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(tween(300)) { it },
        exit = slideOutVertically(tween(260)) { it },
        modifier = modifier.zIndex(40f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 420.dp)
                    .clip(MobilePlayerTokens.ShapeSheet)
                    .background(MobilePlayerTokens.PanelBg)
                    .navigationBarsPadding()
                    .displayCutoutPadding()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {} // Consume taps
                    )
                    .padding(bottom = 16.dp)
            ) {
                // Drag handle
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 4.dp)
                        .width(34.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .align(Alignment.CenterHorizontally)
                )

                // Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (showBackButton) {
                            MobileIconButton(
                                icon = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                onClick = onBack
                            )
                        }
                        Text(
                            text = title,
                            color = MobilePlayerTokens.InkPrimary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    MobileIconButton(
                        icon = Icons.Default.Close,
                        contentDescription = "Close",
                        onClick = onClose
                    )
                }

                if (headerExtra != null) {
                    headerExtra()
                }

                // Content
                content()
            }
        }
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0) return ""
    val kb = bytes / 1024.0
    val mb = kb / 1024.0
    val gb = mb / 1024.0
    return when {
        gb >= 1.0 -> String.format("%.1f GB", gb)
        mb >= 1.0 -> String.format("%.1f MB", mb)
        else -> String.format("%.0f KB", kb)
    }
}

internal fun formatLanguageDisplayName(code: String?): String = getFullLanguageName(code)

internal fun formatAudioCodecDisplayName(codec: String?): String? {
    if (codec.isNullOrBlank()) return null
    return when {
        codec.contains("eac3-joc", ignoreCase = true) || codec.contains("atmos", ignoreCase = true) -> "Dolby Atmos"
        codec.contains("eac3", ignoreCase = true) || codec.contains("ec-3", ignoreCase = true) -> "E-AC-3"
        codec.contains("ac3", ignoreCase = true) || codec.contains("ac-3", ignoreCase = true) -> "Dolby Digital"
        codec.contains("dts-hd", ignoreCase = true) -> "DTS-HD"
        codec.contains("dts", ignoreCase = true) -> "DTS"
        codec.contains("truehd", ignoreCase = true) -> "TrueHD"
        codec.contains("flac", ignoreCase = true) -> "FLAC"
        codec.contains("opus", ignoreCase = true) -> "Opus"
        codec.contains("mp4a", ignoreCase = true) || codec.contains("aac", ignoreCase = true) -> "AAC"
        codec.contains("mp3", ignoreCase = true) -> "MP3"
        else -> codec.substringAfterLast('/').uppercase()
    }
}

/**
 * Interactive full-screen overlay for dragging & adjusting subtitle vertical position directly over video.
 */
@Composable
fun MobileSubtitleRepositionOverlay(
    visible: Boolean,
    verticalPercent: Int, // 1 .. 88 (% from bottom)
    hasActiveSubtitles: Boolean = false,
    subtitleColorHex: String = "#fff",
    subtitleStyle: String = "Bold",
    subtitleFont: String = "System",
    subtitleSizePct: Int = 100,
    onUpdateVerticalPercent: (Int) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!visible) return

    BackHandler(enabled = visible) {
        onDismiss()
    }

    val context = LocalContext.current
    val parsedColor = remember(subtitleColorHex) {
        when (subtitleColorHex.lowercase()) {
            "#ffe066" -> Color(0xFFFFE066)
            "#66d9ff" -> Color(0xFF66D9FF)
            "#ff6666" -> Color(0xFFFF6666)
            else -> {
                try {
                    Color(android.graphics.Color.parseColor(subtitleColorHex))
                } catch (_: Exception) {
                    Color.White
                }
            }
        }
    }
    val isBold = !subtitleStyle.equals("Normal", ignoreCase = true)
    val isBackground = subtitleStyle.equals("Background", ignoreCase = true)
    val typeface = remember(subtitleFont, isBold, context) {
        resolveSubtitleTypeface(context, subtitleFont, isBold)
    }
    val fontFamily = remember(typeface) {
        if (typeface != null) FontFamily(typeface) else FontFamily.Default
    }

    var accumulatedPct by remember { mutableFloatStateOf(verticalPercent.toFloat().coerceIn(1f, 88f)) }
    val currentUpdate = rememberUpdatedState(onUpdateVerticalPercent)

    LaunchedEffect(verticalPercent) {
        if (abs(accumulatedPct - verticalPercent) > 1.5f) {
            accumulatedPct = verticalPercent.toFloat().coerceIn(1f, 88f)
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.15f))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val totalHeight = size.height.toFloat().coerceAtLeast(1f)
                        // Dragging UP decreases Y, so -dragAmount.y > 0 (moves higher from bottom)
                        // Dragging DOWN increases Y, so -dragAmount.y < 0 (moves lower towards bottom)
                        val deltaPct = (-dragAmount.y / totalHeight) * 100f
                        accumulatedPct = (accumulatedPct + deltaPct).coerceIn(1f, 88f)
                        val intVal = accumulatedPct.roundToInt().coerceIn(1, 88)
                        currentUpdate.value(intVal)
                    }
                )
            }
            .statusBarsPadding()
            .navigationBarsPadding()
            .displayCutoutPadding()
    ) {
        val screenHeight = maxHeight
        val currentPercent = accumulatedPct.coerceIn(1f, 88f)
        val bottomOffset = screenHeight * (currentPercent / 100f)

        // Top instruction bar
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFF12141A).copy(alpha = 0.92f))
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Adjust Subtitle Position",
                    color = Color.White,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Drag up/down anywhere to move • ${currentPercent.roundToInt()}% from bottom",
                    color = Color(0xFFFFB300),
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.12f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                accumulatedPct = 2f
                                currentUpdate.value(2)
                            }
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Reset",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFB300))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onDismiss
                        )
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Done",
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Subtitle preview:
        // If there are real subtitles present on screen right now, ExoPlayer's SubtitleView
        // already renders and moves them live at this exact position.
        // If no real subtitles are on screen, render the sample subtitle preview.
        if (!hasActiveSubtitles) {
            val computedSizeSp = (24f * (subtitleSizePct.coerceIn(50, 250) / 100f)).sp

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = bottomOffset)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(contentAlignment = Alignment.Center) {
                    // Outline stroke layer (matches CaptionStyleCompat.EDGE_TYPE_OUTLINE)
                    if (!subtitleStyle.equals("Normal", ignoreCase = true) && !isBackground) {
                        Text(
                            text = "Sample Subtitle",
                            fontSize = computedSizeSp,
                            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Center,
                            style = TextStyle.Default.copy(
                                drawStyle = Stroke(
                                    width = 6f,
                                    join = StrokeJoin.Round
                                ),
                                color = Color.Black
                            )
                        )
                    }

                    // Main subtitle text
                    Text(
                        text = "Sample Subtitle",
                        color = parsedColor,
                        fontSize = computedSizeSp,
                        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                        fontFamily = fontFamily,
                        textAlign = TextAlign.Center,
                        style = if (isBackground) {
                            TextStyle(
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.8f),
                                    offset = Offset(1.5f, 1.5f),
                                    blurRadius = 3f
                                )
                            )
                        } else {
                            TextStyle.Default
                        },
                        modifier = if (isBackground) {
                            Modifier
                                .background(Color(0xB4000000), RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        } else {
                            Modifier
                        }
                    )
                }
            }
        }
    }
}
