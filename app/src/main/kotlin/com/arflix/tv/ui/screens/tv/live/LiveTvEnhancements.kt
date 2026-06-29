package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SettingsInputAntenna
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.repository.IptvConfig

data class TvProviderFilter(
    val id: String,
    val label: String,
    val count: Int,
)

data class PlaybackDiagnostic(
    val title: String,
    val detail: String,
    val severity: PlaybackDiagnosticSeverity = PlaybackDiagnosticSeverity.Info,
)

enum class PlaybackDiagnosticSeverity {
    Info,
    Warning,
    Error,
}

fun buildTvProviderFilters(
    config: IptvConfig,
    channels: List<EnrichedChannel>,
): List<TvProviderFilter> {
    val enabledPlaylists = config.playlists
        .filter { it.enabled && it.id.isNotBlank() }
        .distinctBy { it.id }
    if (enabledPlaylists.size <= 1) return emptyList()

    val knownIds = enabledPlaylists.mapTo(HashSet()) { it.id }
    val counts = channels
        .mapNotNull { channelPlaylistId(it, knownIds) }
        .groupingBy { it }
        .eachCount()
    if (counts.size <= 1) return emptyList()

    return buildList {
        add(TvProviderFilter("all", "All providers", channels.size))
        enabledPlaylists.forEach { playlist ->
            val count = counts[playlist.id] ?: 0
            if (count > 0) {
                add(TvProviderFilter(playlist.id, playlist.name.ifBlank { playlist.id }, count))
            }
        }
    }
}

fun providerMatches(channel: EnrichedChannel, providerId: String, config: IptvConfig): Boolean {
    if (providerId == "all") return true
    val knownIds = config.playlists
        .filter { it.enabled && it.id.isNotBlank() }
        .mapTo(HashSet()) { it.id }
    return channelPlaylistId(channel, knownIds) == providerId
}

fun providerMatcher(providerId: String, config: IptvConfig): (EnrichedChannel) -> Boolean {
    if (providerId == "all") return { true }
    val knownIds = config.playlists
        .filter { it.enabled && it.id.isNotBlank() }
        .mapTo(HashSet()) { it.id }
    return { channel -> channelPlaylistId(channel, knownIds) == providerId }
}

private fun channelPlaylistId(channel: EnrichedChannel, knownIds: Set<String>): String? {
    val prefix = channel.id.substringBefore(':', missingDelimiterValue = "")
    return prefix.takeIf { it in knownIds }
}

fun variantGroupKey(channel: EnrichedChannel): String {
    return channel.source.variantKey
        ?.trim()
        ?.takeIf { it.isNotBlank() }
        ?: channel.source.epgId
            ?.trim()
            ?.lowercase()
            ?.takeIf { it.isNotBlank() }
        ?: channel.id
}

fun buildVariantGroups(channels: List<EnrichedChannel>): Map<String, List<EnrichedChannel>> {
    // Keep IPTV channel rows exactly as the provider supplied them. Collapsing
    // variants hides provider-specific HD/SD/catchup rows and mixes playlists,
    // which makes EPG and catchup selection unpredictable.
    return emptyMap()
}

fun collapseChannelVariants(
    channels: List<EnrichedChannel>,
    variantGroups: Map<String, List<EnrichedChannel>>,
): List<EnrichedChannel> {
    if (variantGroups.isEmpty()) return channels
    val emitted = HashSet<String>()
    return buildList(channels.size) {
        channels.forEach { channel ->
            val key = variantGroupKey(channel)
            val group = variantGroups[key]
            if (group == null) {
                add(channel)
            } else if (emitted.add(key)) {
                add(group.first())
            }
        }
    }
}

fun displayChannelIdFor(
    channelId: String?,
    allChannelsById: Map<String, EnrichedChannel>,
    variantGroups: Map<String, List<EnrichedChannel>>,
): String? {
    val channel = channelId?.let(allChannelsById::get) ?: return channelId
    return variantGroups[variantGroupKey(channel)]?.firstOrNull()?.id ?: channel.id
}

fun variantCountFor(channel: EnrichedChannel, variantGroups: Map<String, List<EnrichedChannel>>): Int {
    return variantGroups[variantGroupKey(channel)]?.size ?: 1
}

private fun Quality.rank(): Int = when (this) {
    Quality.K4 -> 4
    Quality.FHD -> 3
    Quality.HD -> 2
    Quality.SD -> 1
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ProviderSelector(
    providers: List<TvProviderFilter>,
    selectedId: String,
    onSelect: (String) -> Unit,
    focusRequester: FocusRequester? = null,
    onMoveUp: () -> Unit = {},
    onMoveDown: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    if (providers.size <= 1) return
    var focusedId by remember { mutableStateOf<String?>(null) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 10.dp, end = 14.dp, top = 6.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        providers.forEachIndexed { index, provider ->
            val selected = provider.id == selectedId
            val focused = focusedId == provider.id
            val selectedIndex = providers.indexOfFirst { it.id == selectedId }.takeIf { it >= 0 } ?: index
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (selected || focused) LiveColors.PanelRaised else LiveColors.PanelDeep)
                    .border(
                        width = if (focused) 2.dp else 1.dp,
                        color = when {
                            focused -> LiveColors.FocusRing
                            selected -> LiveColors.Accent
                            else -> LiveColors.Divider
                        },
                        shape = RoundedCornerShape(999.dp),
                    )
                    .then(if (selected && focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
                    .onFocusChanged { if (it.hasFocus) focusedId = provider.id }
                    .focusable()
                    .onKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onKeyEvent false
                        when (event.key) {
                            Key.DirectionLeft -> {
                                val next = providers.getOrNull((selectedIndex - 1).coerceAtLeast(0)) ?: provider
                                focusedId = next.id
                                onSelect(next.id)
                                true
                            }
                            Key.DirectionRight -> {
                                val next = providers.getOrNull((selectedIndex + 1).coerceAtMost(providers.lastIndex)) ?: provider
                                focusedId = next.id
                                onSelect(next.id)
                                true
                            }
                            Key.DirectionUp -> {
                                onMoveUp()
                                true
                            }
                            Key.DirectionDown -> {
                                onMoveDown()
                                true
                            }
                            Key.DirectionCenter, Key.Enter -> {
                                onSelect(provider.id)
                                true
                            }
                            else -> false
                        }
                    }
                    .clickable { onSelect(provider.id) }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.SettingsInputAntenna,
                        contentDescription = null,
                        tint = if (selected) LiveColors.Accent else LiveColors.FgMute,
                        modifier = Modifier.size(14.dp),
                    )
                    Text(
                        text = provider.label,
                        style = LiveType.CatLabel.copy(color = if (selected) LiveColors.Fg else LiveColors.FgDim),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = provider.count.toString(),
                        style = LiveType.NumberMono.copy(color = LiveColors.FgMute),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun EpgStatusStrip(
    isLoading: Boolean,
    warning: String?,
    matchedCount: Int,
    totalChannels: Int,
    hasGuideSource: Boolean,
    modifier: Modifier = Modifier,
) {
    val visible = isLoading ||
        !warning.isNullOrBlank() ||
        (!hasGuideSource && totalChannels > 0)
    if (!visible) return
    val text = when {
        !warning.isNullOrBlank() -> warning
        isLoading -> stringResource(R.string.live_status_loading_guide)
        !hasGuideSource -> stringResource(R.string.live_status_no_epg_source)
        else -> stringResource(R.string.live_status_guide_pending)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 14.dp, top = 2.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(LiveColors.PanelDeep)
            .border(1.dp, LiveColors.Divider, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null,
                tint = if (warning.isNullOrBlank()) LiveColors.Accent else Color(0xFFFFC04A),
                modifier = Modifier.size(14.dp),
            )
            Text(
                text = stringResource(R.string.live_status_matched, text ?: "", matchedCount, totalChannels),
                style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelNumberOverlay(
    buffer: String,
    matchCount: Int,
    exactChannelName: String?,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = buffer.isNotBlank(),
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.78f))
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = buffer,
                style = LiveType.NumberMono.copy(color = LiveColors.Fg, fontSize = 24.sp),
            )
            Text(
                text = exactChannelName ?: if (matchCount > 0) stringResource(R.string.live_label_matches, matchCount) else stringResource(R.string.live_empty_no_channel),
                style = LiveType.SectionTag.copy(color = if (matchCount > 0) LiveColors.FgDim else Color(0xFFFF8A9A)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PlaybackDiagnosticBanner(
    diagnostic: PlaybackDiagnostic?,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = diagnostic != null,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier,
    ) {
        val current = diagnostic ?: return@AnimatedVisibility
        val tint = when (current.severity) {
            PlaybackDiagnosticSeverity.Info -> LiveColors.Accent
            PlaybackDiagnosticSeverity.Warning -> Color(0xFFFFC04A)
            PlaybackDiagnosticSeverity.Error -> Color(0xFFFF6B81)
        }
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.78f))
                .border(1.dp, tint.copy(alpha = 0.65f), RoundedCornerShape(12.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(20.dp),
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(current.title, style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 13.sp))
                Text(
                    current.detail,
                    style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VariantPickerOverlay(
    channel: EnrichedChannel?,
    variants: List<EnrichedChannel>,
    onDismiss: () -> Unit,
    onPick: (EnrichedChannel) -> Unit,
) {
    if (channel == null || variants.size <= 1) return
    val firstFocus = remember { FocusRequester() }
    LaunchedEffect(channel.id, variants) {
        runCatching { firstFocus.requestFocus() }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.72f))
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Back) {
                    onDismiss()
                    true
                } else {
                    false
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(560.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LiveColors.PanelRaised)
                .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.SwapHoriz,
                    contentDescription = null,
                    tint = LiveColors.Accent,
                    modifier = Modifier.size(22.dp),
                )
                Column {
                    Text(stringResource(R.string.live_label_choose_source), style = LiveType.ChannelName.copy(color = LiveColors.Fg, fontSize = 18.sp))
                    Text(
                        channel.name,
                        style = LiveType.SectionTag.copy(color = LiveColors.FgDim),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 360.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                itemsIndexed(variants, key = { index, item -> "${item.id}#$index" }) { index, variant ->
                    VariantRow(
                        channel = variant,
                        modifier = if (index == 0) Modifier.focusRequester(firstFocus) else Modifier,
                        onPick = {
                            onPick(variant)
                            onDismiss()
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun VariantRow(
    channel: EnrichedChannel,
    onPick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (focused) LiveColors.Panel else LiveColors.PanelDeep)
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) LiveColors.FocusRing else LiveColors.Divider,
                shape = RoundedCornerShape(10.dp),
            )
            .onFocusChanged { focused = it.hasFocus }
            .focusable()
            .clickable(onClick = onPick)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && (event.key == Key.DirectionCenter || event.key == Key.Enter)) {
                    onPick()
                    true
                } else {
                    false
                }
            }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(if (focused) LiveColors.Accent else LiveColors.FgMute, CircleShape),
        )
        Text(
            text = channel.number.toString(),
            style = LiveType.NumberMono.copy(color = LiveColors.FgMute),
            modifier = Modifier.width(44.dp),
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = channel.name,
                style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 13.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = channel.source.streamUrl.substringBefore('?').takeLast(42),
                style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = channel.quality.label,
            style = LiveType.Badge.copy(color = LiveColors.Fg),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(LiveColors.PanelRaised)
                .padding(horizontal = 8.dp, vertical = 3.dp),
        )
        Text(
            text = channel.lang,
            style = LiveType.Badge.copy(color = LiveColors.FgMute),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(LiveColors.PanelRaised)
                .padding(horizontal = 8.dp, vertical = 3.dp),
        )
    }
}
