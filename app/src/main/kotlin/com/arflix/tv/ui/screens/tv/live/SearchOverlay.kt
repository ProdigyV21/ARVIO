package com.arflix.tv.ui.screens.tv.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.util.formatGenreName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Modal search overlay. Spec §3.5 — 760dp panel, accent caret, result rows with
 * channel number / logo / name / category / quality / lang.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SearchOverlay(
    channels: List<EnrichedChannel>,
    nowNext: Map<String, IptvNowNext> = emptyMap(),
    searchProvider: (suspend (String) -> List<EnrichedChannel>)? = null,
    onDismiss: () -> Unit,
    onPick: (EnrichedChannel) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    var debounced by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<SearchResult>>(emptyList()) }
    val focusRequester = remember { FocusRequester() }
    val firstResultFocus = remember { FocusRequester() }
    LaunchedEffect(Unit) { runCatching { focusRequester.requestFocus() } }

    // Resolved up front so they can be used inside the non-composable search LaunchedEffect.
    val nowFormat = stringResource(R.string.live_search_now)
    val guideLabels = GuideMatchLabels(
        now = stringResource(R.string.now),
        next = stringResource(R.string.next),
        later = stringResource(R.string.later),
        guide = stringResource(R.string.live_program_guide),
        nowFormat = nowFormat,
    )

    // Debounce input for 150ms per spec §7.
    LaunchedEffect(query) {
        delay(150)
        debounced = query.trim()
    }

    LaunchedEffect(debounced, channels, nowNext, searchProvider) {
        val q = debounced.lowercase()
        if (q.isEmpty()) {
            // Show the first 60 by default — gives a preview list users can scroll.
            results = channels.take(60).map { channel ->
                SearchResult(channel, nowNext[channel.id]?.now?.let { nowFormat.format(it.title) })
            }
            return@LaunchedEffect
        }
        val providerResults = searchProvider
            ?.let { provider -> withContext(Dispatchers.IO) { provider(q) } }
            .orEmpty()
        if (providerResults.isNotEmpty()) {
            results = providerResults
                .distinctBy { it.id }
                .take(200)
                .map { channel ->
                    SearchResult(channel, nowNext[channel.id]?.now?.let { nowFormat.format(it.title) })
                }
            return@LaunchedEffect
        }
        results = withContext(Dispatchers.Default) {
            channels.asSequence()
                .map { ch ->
                    val nameLower = ch.name.lowercase()
                    val guideMatch = nowNext[ch.id]?.bestProgramMatch(q)
                    val guideScore = nowNext[ch.id]?.let { guide ->
                        val titles = buildList {
                            guide.now?.title?.let { add(it) }
                            guide.next?.title?.let { add(it) }
                            guide.later?.title?.let { add(it) }
                            guide.upcoming.take(8).forEach { add(it.title) }
                        }
                        when {
                            titles.any { it.equals(q, ignoreCase = true) } -> 640
                            titles.any { it.lowercase().startsWith(q) } -> 420
                            titles.any { it.lowercase().contains(q) } -> 320
                            else -> 0
                        }
                    } ?: 0
                    val score = when {
                        ch.number.toString() == q -> 1000
                        ch.source.providerChannelNumber?.lowercase() == q -> 980
                        nameLower == q -> 900
                        nameLower.startsWith(q) -> 700
                        nameLower.contains(q) -> 500
                        guideScore > 0 -> guideScore
                        ch.genre.name.lowercase().contains(q) -> 250
                        ch.country?.lowercase() == q -> 200
                        else -> 0
                    }
                    SearchResult(ch, guideMatch?.let { labelProgramMatch(it, nowNext[ch.id], guideLabels) }) to score
                }
                .filter { it.second > 0 }
                .sortedByDescending { it.second }
                .map { it.first }
                .take(200)
                .toList()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xB3000000))
            .pointerInput(Unit) { detectTapGestures(onTap = { onDismiss() }) },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .width(760.dp)
                .padding(top = 64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LiveColors.PanelRaised)
                .border(1.dp, LiveColors.Divider, RoundedCornerShape(16.dp))
                .padding(16.dp)
                .pointerInput(Unit) { detectTapGestures(onTap = {}) },
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 6.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = LiveColors.FgDim,
                    modifier = Modifier.size(20.dp),
                )
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = { runCatching { firstResultFocus.requestFocus() } },
                    ),
                    cursorBrush = SolidColor(LiveColors.Accent),
                    textStyle = TextStyle(
                        color = LiveColors.Fg,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onPreviewKeyEvent { ev ->
                            if (ev.type == KeyEventType.KeyDown &&
                                ev.key == Key.DirectionDown &&
                                results.isNotEmpty()
                            ) {
                                runCatching { firstResultFocus.requestFocus() }
                                true
                            } else {
                                false
                            }
                        }
                        .onKeyEvent { ev ->
                            if (ev.type == KeyEventType.KeyDown && ev.key == Key.Back) {
                                onDismiss(); true
                            } else false
                        },
                    decorationBox = { inner ->
                        if (query.isEmpty()) {
                            Text(
                                stringResource(R.string.live_hint_search),
                                style = TextStyle(color = LiveColors.FgMute, fontSize = 18.sp),
                            )
                        }
                        inner()
                    },
                )
                Text(
                    "ESC",
                    style = LiveType.NumberMono.copy(color = LiveColors.FgMute),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(LiveColors.Divider),
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().height(440.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                itemsIndexed(results, key = { index, result -> "${result.channel.id}#$index" }) { _, result ->
                    val ch = result.channel
                    val focusMod = if (results.isNotEmpty() && ch.id == results.first().channel.id) {
                        Modifier.focusRequester(firstResultFocus)
                    } else Modifier
                    SearchResultRow(
                        channel = ch,
                        matchText = result.matchText,
                        onPick = onPick,
                        onMoveUp = if (results.isNotEmpty() && ch.id == results.first().channel.id) {
                            { runCatching { focusRequester.requestFocus() } }
                        } else {
                            null
                        },
                        modifier = focusMod,
                    )
                }
            }
        }
    }
}

private data class SearchResult(
    val channel: EnrichedChannel,
    val matchText: String?,
)

private fun IptvNowNext.bestProgramMatch(query: String): IptvProgram? {
    val candidates = buildList {
        now?.let { add(it) }
        next?.let { add(it) }
        later?.let { add(it) }
        upcoming.take(8).forEach { add(it) }
    }
    return candidates.firstOrNull { it.title.equals(query, ignoreCase = true) }
        ?: candidates.firstOrNull { it.title.lowercase().startsWith(query) }
        ?: candidates.firstOrNull { it.title.lowercase().contains(query) }
}

private data class GuideMatchLabels(
    val now: String,
    val next: String,
    val later: String,
    val guide: String,
    val nowFormat: String,
)

private fun labelProgramMatch(
    program: IptvProgram,
    guide: IptvNowNext?,
    labels: GuideMatchLabels,
): String {
    val prefix = when (program) {
        guide?.now -> labels.now
        guide?.next -> labels.next
        guide?.later -> labels.later
        else -> labels.guide
    }
    return "$prefix: ${program.title}"
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SearchResultRow(
    channel: EnrichedChannel,
    matchText: String?,
    onPick: (EnrichedChannel) -> Unit,
    onMoveUp: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (focused) LiveColors.Panel else Color.Transparent)
            .border(
                width = if (focused) 3.dp else 0.dp,
                color = if (focused) LiveColors.FocusRing else Color.Transparent,
                shape = RoundedCornerShape(10.dp),
            )
            .onFocusChanged { focused = it.hasFocus }
            .focusable()
            .onKeyEvent { ev ->
                if (ev.type != KeyEventType.KeyDown) return@onKeyEvent false
                when (ev.key) {
                    Key.DirectionCenter, Key.Enter -> {
                        onPick(channel)
                        true
                    }

                    Key.DirectionUp -> {
                        if (onMoveUp != null) {
                            onMoveUp()
                            true
                        } else {
                            false
                        }
                    }

                    else -> false
                }
            }
            .pointerInput(channel.id) {
                detectTapGestures(onTap = { onPick(channel) })
            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = channel.number.toString(),
            style = LiveType.NumberMono.copy(color = LiveColors.FgMute),
            modifier = Modifier.width(40.dp),
        )
        ChannelLogo(channel = channel, size = 40.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = channel.name,
                style = LiveType.CellTitle.copy(color = LiveColors.Fg, fontSize = 15.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = matchText ?: formatGenreName(channel.genre.name),
                style = LiveType.SectionTag.copy(color = LiveColors.FgMute),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(LiveColors.Panel)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(channel.quality.label, style = LiveType.Badge.copy(color = LiveColors.Fg))
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(LiveColors.Panel)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(channel.lang, style = LiveType.Badge.copy(color = LiveColors.FgMute))
            }
        }
    }
}
