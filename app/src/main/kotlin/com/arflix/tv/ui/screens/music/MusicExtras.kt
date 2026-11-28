package com.arflix.tv.ui.screens.music

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.QueueMusic
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lyrics
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import com.arflix.tv.R
import com.arflix.tv.music.CoverTint
import com.arflix.tv.music.MusicCollections
import com.arflix.tv.music.MusicPin
import com.arflix.tv.music.MusicPinType
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.ui.theme.BackgroundElevated
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.max

// ---------------------------------------------------------------------------------------------
// Speed dial and "Jump back in" on the Music home

/**
 * The top of the Music home: "Surprise me", liked songs and whatever the user pinned, as a grid of
 * compact tiles. A long press on a pin unpins it.
 */
@Composable
internal fun SpeedDial(collections: MusicCollections, accent: Color, viewModel: MusicActions) {
    val pins = collections.visiblePins()
    val likedPinned = collections.isPinned(MusicPinType.PLAYLIST, MusicCollections.LIKED_ID)
    val likedPin = MusicPin(MusicPinType.PLAYLIST, MusicCollections.LIKED_ID, "")
    // null stands for the "Surprise me" tile; liked songs show until they are pinned themselves.
    val tiles: List<MusicPin?> = listOf<MusicPin?>(null) +
        (if (collections.favoriteTracks.isNotEmpty() && !likedPinned) listOf(likedPin) else emptyList()) +
        pins
    val likedName = stringResource(R.string.music_liked_songs)
    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 12.dp).testTag("music-speed-dial")) {
        Text(
            stringResource(R.string.music_speed_dial),
            color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 10.dp),
        )
        tiles.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { pin ->
                    if (pin == null) {
                        SurpriseTile(accent, Modifier.weight(1f)) { viewModel.surpriseMe() }
                    } else {
                        val liked = pin.type == MusicPinType.PLAYLIST && pin.id == MusicCollections.LIKED_ID
                        DialTile(
                            title = if (liked) likedName else pin.name,
                            coverUrl = if (liked) null else pin.coverUrl,
                            icon = when {
                                liked -> Icons.Default.Favorite
                                pin.type == MusicPinType.ARTIST -> Icons.Default.Person
                                pin.type == MusicPinType.PLAYLIST -> Icons.AutoMirrored.Filled.QueueMusic
                                else -> Icons.Default.Album
                            },
                            accent = accent,
                            round = pin.type == MusicPinType.ARTIST,
                            modifier = Modifier.weight(1f),
                            // The plain liked-songs tile is not a pin, so there is nothing to unpin.
                            onLongClick = if (pin === likedPin) null else ({ viewModel.togglePin(pin) }),
                            onClick = { viewModel.openPin(pin) },
                        )
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        if (pins.isEmpty()) {
            Text(
                stringResource(R.string.music_speed_dial_hint),
                color = Faint, fontSize = 12.sp, lineHeight = 16.sp,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp),
            )
        }
    }
}

@Composable
private fun SurpriseTile(accent: Color, modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Brush.linearGradient(listOf(accent, accent.copy(alpha = 0.55f))))
            .clickable(onClick = onClick)
            .testTag("music-surprise-me"),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(56.dp), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.AutoAwesome, null, tint = contentOn(accent), modifier = Modifier.size(26.dp))
        }
        Text(
            stringResource(R.string.music_surprise_me), color = contentOn(accent), fontSize = 14.sp,
            fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 10.dp),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DialTile(
    title: String,
    coverUrl: String?,
    icon: ImageVector,
    accent: Color,
    modifier: Modifier,
    round: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Surface)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (coverUrl == null && icon == Icons.Default.Favorite) {
            Box(
                Modifier.size(56.dp).background(Brush.linearGradient(listOf(accent.copy(alpha = 0.6f), Surface))),
                contentAlignment = Alignment.Center,
            ) { Icon(icon, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
        } else {
            Cover(
                coverUrl,
                Modifier.size(56.dp).then(if (round) Modifier.padding(6.dp) else Modifier),
                shape = if (round) RoundedCornerShape(50) else RoundedCornerShape(0.dp),
                icon = icon,
            )
        }
        Text(
            title, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
            maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 16.sp,
            modifier = Modifier.padding(horizontal = 10.dp),
        )
    }
}

/** The songs heard last; a tap plays the row from that song on. */
@Composable
internal fun JumpBackInRow(tracks: List<MusicTrack>, viewModel: MusicActions) {
    Text(
        stringResource(R.string.music_jump_back_in),
        color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 10.dp),
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.testTag("music-jump-back-in"),
    ) {
        itemsIndexed(tracks, key = { _, t -> "recent-" + t.id }) { index, track ->
            Column(Modifier.width(128.dp).clip(RoundedCornerShape(10.dp)).clickable { viewModel.play(tracks, index) }) {
                Cover(track.coverUrl, Modifier.fillMaxWidth().aspectRatio(1f), shape = RoundedCornerShape(10.dp), icon = Icons.Default.MusicNote)
                Text(
                    track.title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 8.dp),
                )
                Text(track.artist, color = Faint, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

/** Pins or unpins an album, playlist or artist from its own page. */
@Composable
internal fun PinButton(pinned: Boolean, accent: Color, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            if (pinned) Icons.Filled.PushPin else Icons.Outlined.PushPin,
            stringResource(if (pinned) R.string.music_unpin else R.string.music_pin),
            tint = if (pinned) accent else Color.White,
        )
    }
}

// ---------------------------------------------------------------------------------------------
// Colours from the cover

/** The player's colours, taken from the album cover. */
internal data class CoverColors(val background: Color, val accent: Color)

/** The colours of the cover at [url], once it has loaded (Coil's cache makes that quick). */
@Composable
internal fun rememberCoverColors(url: String?): CoverColors? {
    val context = LocalContext.current
    var colors by remember(url) { mutableStateOf<CoverColors?>(null) }
    LaunchedEffect(url) {
        if (url == null) return@LaunchedEffect
        val request = ImageRequest.Builder(context).data(url).allowHardware(false).size(160).build()
        val bitmap = (context.imageLoader.execute(request).drawable as? BitmapDrawable)?.bitmap ?: return@LaunchedEffect
        colors = withContext(Dispatchers.Default) {
            runCatching {
                val palette = Palette.from(bitmap).maximumColorCount(16).generate()
                CoverTint.pick(
                    vibrant = palette.vibrantSwatch?.rgb,
                    lightVibrant = palette.lightVibrantSwatch?.rgb,
                    dominant = palette.dominantSwatch?.rgb,
                    darkMuted = palette.darkMutedSwatch?.rgb,
                    muted = palette.mutedSwatch?.rgb,
                )?.let { (background, accent) -> CoverColors(Color(background), Color(accent)) }
            }.getOrNull()
        }
    }
    return colors
}

// ---------------------------------------------------------------------------------------------
// Player: lyrics, queue and sleep timer

/** What fills the middle of the full player. */
internal enum class PlayerPanel { COVER, LYRICS }

/** Lyrics that follow the song: the line being sung lights up and stays in view; a tap seeks there. */
@Composable
internal fun LyricsPanel(
    lyrics: LyricsUi?,
    positionMs: Long,
    accent: Color,
    onSeek: (Long) -> Unit,
    modifier: Modifier = Modifier,
    tv: Boolean = false,
) {
    Box(modifier.testTag("music-lyrics"), contentAlignment = Alignment.Center) {
        val found = lyrics?.lyrics
        when {
            lyrics == null || lyrics.loading -> CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
            lyrics.failed -> LyricsMessage(stringResource(R.string.music_lyrics_failed))
            found == null || found.isEmpty -> LyricsMessage(stringResource(R.string.music_lyrics_none))
            found.synced.isNotEmpty() -> SyncedLyrics(found.synced, found.activeIndex(positionMs), accent, onSeek, tv)
            !found.plain.isNullOrBlank() -> Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(vertical = 12.dp)) {
                Text(found.plain, color = Color.White, fontSize = 20.sp, lineHeight = 30.sp, fontWeight = FontWeight.SemiBold)
                LyricsCredit()
            }
            else -> LyricsMessage(stringResource(R.string.music_lyrics_instrumental))
        }
    }
}

@Composable
private fun SyncedLyrics(lines: List<com.arflix.tv.music.LyricLine>, active: Int, accent: Color, onSeek: (Long) -> Unit, tv: Boolean = false) {
    val listState = rememberLazyListState()
    val lineGuessPx = with(androidx.compose.ui.platform.LocalDensity.current) { 48.dp.roundToPx() }
    LaunchedEffect(active) {
        if (active < 0 || listState.isScrollInProgress) return@LaunchedEffect
        if (tv) {
            // The TV lyrics area is only a few lines tall, so the sung line sits in the middle of it.
            val info = listState.layoutInfo
            val viewport = info.viewportSize.height
            val line = info.visibleItemsInfo.firstOrNull { it.index == active }?.size ?: lineGuessPx
            listState.animateScrollToItem(active, -(viewport / 2 - line / 2))
        } else {
            // Keeps the sung line a little below the top, unless the user is scrolling themselves.
            listState.animateScrollToItem(max(0, active - 2))
        }
    }
    // The lines fade out at the top and bottom edges instead of running into the title below.
    LazyColumn(
        Modifier.fillMaxSize()
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .drawWithContent {
                drawContent()
                drawRect(
                    Brush.verticalGradient(0f to Color.Transparent, 0.08f to Color.Black, 0.85f to Color.Black, 1f to Color.Transparent),
                    blendMode = BlendMode.DstIn,
                )
            },
        state = listState,
        contentPadding = if (tv) PaddingValues(bottom = 160.dp) else PaddingValues(top = 24.dp, bottom = 48.dp),
    ) {
        itemsIndexed(lines, key = { i, _ -> i }) { i, line ->
            val past = i < active
            if (tv) {
                TvLyricLine(line.text.ifEmpty { "\u266A" }, current = i == active, past = past, accent = accent)
                return@itemsIndexed
            }
            Text(
                line.text.ifEmpty { "♪" },
                color = when {
                    i == active -> Color.White
                    past -> Color.White.copy(alpha = 0.35f)
                    else -> Color.White.copy(alpha = 0.5f)
                },
                fontSize = if (i == active) 24.sp else 21.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                    .clickable { onSeek(line.timeMs) }
                    .padding(vertical = 8.dp, horizontal = 4.dp),
            )
        }
        item { LyricsCredit() }
    }
}

/**
 * One line of synced lyrics on TV: seen from the sofa, so the sung line grows, turns full white and sits on a
 * soft accent band, while the other lines step back.
 */
@Composable
private fun TvLyricLine(text: String, current: Boolean, past: Boolean, accent: Color) {
    val color by animateColorAsState(
        when {
            current -> Color.White
            past -> Color.White.copy(alpha = 0.28f)
            else -> Color.White.copy(alpha = 0.42f)
        },
        tween(280), label = "tv-lyric-color",
    )
    val band by animateFloatAsState(if (current) 0.30f else 0f, tween(280), label = "tv-lyric-band")
    Text(
        text,
        color = color,
        // A larger font rather than a scale, so a long sung line wraps instead of running past the edge.
        fontSize = if (current) 26.sp else 23.sp,
        lineHeight = 32.sp,
        fontWeight = if (current) FontWeight.ExtraBold else FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(accent.copy(alpha = band))
            .padding(vertical = 7.dp, horizontal = 12.dp),
    )
}

@Composable
private fun LyricsCredit() {
    Text(
        stringResource(R.string.music_lyrics_credit), color = Faint, fontSize = 12.sp,
        modifier = Modifier.padding(top = 20.dp, start = 4.dp),
    )
}

@Composable
private fun LyricsMessage(text: String) {
    Text(text, color = Muted, fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(24.dp))
}

/** Lyrics, sleep timer and queue under the player's controls. */
@Composable
internal fun PlayerExtrasRow(
    panel: PlayerPanel,
    playback: MusicPlaybackState,
    accent: Color,
    onLyrics: () -> Unit,
    onSleep: () -> Unit,
    onQueue: () -> Unit,
) {
    Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        ExtraButton(Icons.Default.Lyrics, stringResource(R.string.music_lyrics), active = panel == PlayerPanel.LYRICS, accent = accent, tag = "music-lyrics-button", onClick = onLyrics)
        ExtraButton(
            Icons.Default.Bedtime,
            sleepLabel(playback) ?: stringResource(R.string.music_sleep_timer),
            active = playback.sleepTimerOn, accent = accent, tag = "music-sleep-button", onClick = onSleep,
        )
        ExtraButton(Icons.AutoMirrored.Filled.QueueMusic, stringResource(R.string.music_queue), active = false, accent = accent, tag = "music-queue-button", onClick = onQueue)
    }
}

@Composable
internal fun sleepLabel(playback: MusicPlaybackState): String? {
    if (playback.sleepAtEndOfTrack) return stringResource(R.string.music_sleep_after_song)
    val at = playback.sleepAtMs ?: return null
    // Re-reads the clock every few seconds, so the minutes count down.
    var now by remember { mutableStateOf(System.currentTimeMillis()) }
    LaunchedEffect(at) { while (true) { now = System.currentTimeMillis(); delay(5_000) } }
    val minutes = ((at - now + 59_999) / 60_000).coerceAtLeast(1).toInt()
    return stringResource(R.string.music_sleep_left, minutes)
}

@Composable
private fun ExtraButton(icon: ImageVector, label: String, active: Boolean, accent: Color, tag: String, onClick: () -> Unit) {
    Row(
        Modifier.tvFocusRing(RoundedCornerShape(18.dp)).clip(RoundedCornerShape(18.dp))
            .background(if (active) accent.copy(alpha = 0.18f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag(tag),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, tint = if (active) accent else Muted, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(6.dp))
        Text(label, color = if (active) accent else Muted, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
    }
}

/** Choices for the sleep timer, and turning it off. */
@Composable
internal fun SleepTimerDialog(playback: MusicPlaybackState, accent: Color, viewModel: MusicActions, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = BackgroundElevated,
        title = { Text(stringResource(R.string.music_sleep_timer), color = Color.White) },
        text = {
            Column {
                listOf(15, 30, 45, 60, 90).forEach { minutes ->
                    SleepOption(stringResource(R.string.music_sleep_minutes, minutes)) { viewModel.setSleepTimer(minutes); onDismiss() }
                }
                SleepOption(stringResource(R.string.music_sleep_end_of_track)) { viewModel.sleepAtEndOfTrack(); onDismiss() }
                if (playback.sleepTimerOn) {
                    SleepOption(stringResource(R.string.music_sleep_off), color = accent) { viewModel.cancelSleepTimer(); onDismiss() }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.music_close), color = Color.White) }
        },
    )
}

@Composable
private fun SleepOption(label: String, color: Color = Color.White, onClick: () -> Unit) {
    Text(
        label, color = color, fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).clickable(onClick = onClick).padding(vertical = 12.dp, horizontal = 4.dp),
    )
}

/**
 * The queue over the player: the song playing now, then what comes next in play order (shuffle
 * included). A tap jumps there; the buttons move a song or take it out.
 */
@Composable
internal fun QueueOverlay(playback: MusicPlaybackState, background: Color, accent: Color, viewModel: MusicActions, onClose: () -> Unit) {
    val current = playback.current
    val upNext = playback.upNext
    Column(
        Modifier.fillMaxSize().background(background).clickable(enabled = false) {}
            .statusBarsPadding().navigationBarsPadding().testTag("music-queue"),
    ) {
        Row(Modifier.fillMaxWidth().padding(start = 20.dp, end = 4.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.music_queue), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            IconButton(onClick = onClose) { Icon(Icons.Default.Close, stringResource(R.string.music_close), tint = Color.White) }
        }
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 24.dp)) {
            if (current != null) {
                item(key = "now-title") { QueueHeading(stringResource(R.string.music_now_playing)) }
                item(key = "now") { QueueRow(current, playing = true, accent = accent, onClick = {}) }
            }
            item(key = "next-title") { QueueHeading(stringResource(R.string.music_up_next)) }
            if (upNext.isEmpty()) item(key = "empty") {
                Text(stringResource(R.string.music_queue_empty), color = Faint, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))
            }
            itemsIndexed(upNext, key = { _, index -> "q-" + (playback.queue.getOrNull(index)?.id ?: "") + "-" + index }) { position, index ->
                val track = playback.queue.getOrNull(index) ?: return@itemsIndexed
                // Moving only makes sense in queue order; with shuffle on, play order is the shuffle's.
                val canMove = !playback.shuffle
                QueueRow(
                    track, playing = false, accent = accent,
                    onUp = if (canMove && position > 0) ({ viewModel.moveInQueue(index, upNext[position - 1]) }) else null,
                    onDown = if (canMove && position < upNext.lastIndex) ({ viewModel.moveInQueue(index, upNext[position + 1]) }) else null,
                    onRemove = { viewModel.removeFromQueue(index) },
                    onClick = { viewModel.skipTo(index) },
                )
            }
        }
    }
}

@Composable
private fun QueueHeading(text: String) {
    Text(text, color = Muted, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 6.dp))
}

@Composable
private fun QueueRow(
    track: MusicTrack,
    playing: Boolean,
    accent: Color,
    onUp: (() -> Unit)? = null,
    onDown: (() -> Unit)? = null,
    onRemove: (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(start = 20.dp, end = 4.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Cover(track.coverUrl, Modifier.size(44.dp), shape = RoundedCornerShape(6.dp), icon = Icons.Default.MusicNote)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(track.title, color = if (playing) accent else Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(track.artist, color = Faint, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        if (onUp != null) IconButton(onClick = onUp) { Icon(Icons.Default.ArrowUpward, stringResource(R.string.music_move_up), tint = Faint) }
        if (onDown != null) IconButton(onClick = onDown) { Icon(Icons.Default.ArrowDownward, stringResource(R.string.music_move_down), tint = Faint) }
        if (onRemove != null) IconButton(onClick = onRemove) { Icon(Icons.Default.Close, stringResource(R.string.music_remove_from_queue), tint = Faint) }
    }
}
