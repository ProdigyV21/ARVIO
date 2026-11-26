package com.arflix.tv.ui.screens.tv.live

import android.app.ActivityManager
import android.content.Context
import android.media.MediaCodecList
import android.media.MediaFormat
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvPlaybackTarget
import com.arflix.tv.data.repository.IptvStreamPolicy
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderRequestGuard
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.screens.player.safePlaybackHeaders
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/** How many tiles this device can run, read once per process. */
internal fun deviceMultiviewCapacity(context: Context): Int {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
    return multiviewCapacity(
        decoderInstances = hardwareAvcDecoderInstances(),
        isLowRamDevice = activityManager?.isLowRamDevice == true,
        memoryClassMb = activityManager?.memoryClass ?: 256,
    )
}

private val cachedAvcInstances: Int? by lazy {
    runCatching {
        MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos
            .filter { info ->
                !info.isEncoder && info.supportedTypes.any { it.equals(MediaFormat.MIMETYPE_VIDEO_AVC, ignoreCase = true) } &&
                    !info.name.startsWith("OMX.google.", ignoreCase = true) && !info.name.startsWith("c2.android.", ignoreCase = true)
            }
            .maxOfOrNull { it.getCapabilitiesForType(MediaFormat.MIMETYPE_VIDEO_AVC).maxSupportedInstances }
    }.getOrNull()
}

private fun hardwareAvcDecoderInstances(): Int? = cachedAvcInstances

private sealed class TileStatus {
    data object Loading : TileStatus()
    data object Playing : TileStatus()
    data class NoConnection(val limit: Int) : TileStatus()
    data class Failed(val reason: String) : TileStatus()
}

/**
 * Up to four live games at once. The highlighted tile has the sound and the sharpest picture;
 * the others play muted at a lower rendition. D-pad moves between tiles, OK opens the tile's
 * actions (full screen, swap, remove), Back closes. Each tile holds one IPTV connection and
 * never takes one away from somebody else: a tile that finds the subscription full says so.
 */
@OptIn(UnstableApi::class)
@Composable
internal fun MultiviewOverlay(
    tiles: List<MultiviewTile>,
    capacity: Int,
    candidates: List<MultiviewTile>,
    resolve: suspend (IptvChannel) -> IptvPlaybackTarget,
    policyFor: suspend (IptvChannel) -> IptvStreamPolicy?,
    onAdd: (MultiviewTile) -> Unit,
    onReplace: (Int, MultiviewTile) -> Unit,
    onRemove: (Int) -> Unit,
    onFullscreen: (MultiviewTile) -> Unit,
    onClose: () -> Unit,
) {
    val canAdd = tiles.size < capacity
    val cellCount = (tiles.size + if (canAdd) 1 else 0).coerceIn(1, MULTIVIEW_MAX_TILES)
    var focusIndex by remember { mutableIntStateOf(0) }
    var audioIndex by remember { mutableIntStateOf(0) }
    var menuFor by remember { mutableStateOf<Int?>(null) }
    // null = closed; -1 = add a game; otherwise the tile to swap.
    var pickerFor by remember { mutableStateOf<Int?>(null) }
    val rootFocus = remember { FocusRequester() }
    LaunchedEffect(tiles.size) {
        focusIndex = focusIndex.coerceIn(0, cellCount - 1)
        audioIndex = audioIndex.coerceIn(0, (tiles.size - 1).coerceAtLeast(0))
    }
    LaunchedEffect(Unit) { runCatching { rootFocus.requestFocus() } }

    val client = remember {
        OkHttpClient.Builder()
            .addInterceptor(IptvPlaybackConnections())
            .connectionPool(ConnectionPool(MULTIVIEW_MAX_TILES * 2, 2, TimeUnit.MINUTES))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor(IptvProviderRequestGuard.shared.preflightInterceptor(playback = true))
            .addNetworkInterceptor(IptvProviderRequestGuard.shared.playbackInterceptor())
            .dns(OkHttpProvider.dns)
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    BackHandler(enabled = menuFor == null && pickerFor == null) { onClose() }

    fun select(index: Int) {
        focusIndex = index
        if (index < tiles.size) audioIndex = index
    }

    BoxWithConstraints(
        Modifier.fillMaxSize().background(Color.Black)
            .focusRequester(rootFocus)
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown || menuFor != null || pickerFor != null) return@onPreviewKeyEvent false
                val direction = when (event.key) {
                    Key.DirectionLeft -> MultiviewDirection.Left
                    Key.DirectionRight -> MultiviewDirection.Right
                    Key.DirectionUp -> MultiviewDirection.Up
                    Key.DirectionDown -> MultiviewDirection.Down
                    else -> null
                }
                when {
                    direction != null -> {
                        select(multiviewNeighbour(focusIndex, direction, cellCount))
                        true
                    }
                    event.key == Key.DirectionCenter || event.key == Key.Enter || event.key == Key.NumPadEnter -> {
                        if (focusIndex >= tiles.size) pickerFor = -1 else menuFor = focusIndex
                        true
                    }
                    else -> false
                }
            }
            .focusable(),
    ) {
        val cells = multiviewCells(cellCount)
        val areaW = maxWidth
        val areaH = maxHeight
        cells.forEachIndexed { index, cell ->
            val tile = tiles.getOrNull(index)
            val focused = index == focusIndex
            // Keyed by game: closing one tile must not restart the streams that move up.
            androidx.compose.runtime.key(tile?.key ?: "multiview-add") {
            Box(
                Modifier
                    .offset(areaW * cell.x, areaH * cell.y)
                    .size(areaW * cell.width, areaH * cell.height)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(if (focused) 3.dp else 1.dp, if (focused) Color.White else LiveColors.DividerStrong, RoundedCornerShape(6.dp))
                    .background(LiveColors.Bg)
                    .clickable {
                        if (tile == null) pickerFor = -1 else if (focusIndex == index) menuFor = index else select(index)
                    },
            ) {
                if (tile != null) {
                    MultiviewTilePlayer(
                        tile = tile,
                        hasAudio = index == audioIndex,
                        tileCount = tiles.size,
                        client = client,
                        resolve = resolve,
                        policyFor = policyFor,
                    )
                    Row(
                        Modifier.align(Alignment.TopStart).padding(8.dp).clip(RoundedCornerShape(4.dp))
                            .background(Color.Black.copy(alpha = .6f)).padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (index == audioIndex && tiles.size > 1) Icon(Icons.AutoMirrored.Filled.VolumeUp, null,
                            tint = Color.White, modifier = Modifier.size(14.dp).padding(end = 4.dp))
                        Text(tile.title, color = Color.White, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(max = areaW * cell.width * .8f))
                    }
                } else {
                    Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Add, null, tint = if (focused) Color.White else LiveColors.FgDim, modifier = Modifier.size(40.dp))
                        Text(stringResource(R.string.multiview_add_game), color = if (focused) Color.White else LiveColors.FgDim, fontSize = 14.sp)
                    }
                }
            }
            }
        }
        if (tiles.size <= 1 && focusIndex == 0) Text(
            stringResource(R.string.multiview_hint, capacity),
            color = LiveColors.FgDim, fontSize = 12.sp,
            modifier = Modifier.align(Alignment.BottomCenter).padding(10.dp)
                .background(Color.Black.copy(alpha = .6f), RoundedCornerShape(4.dp)).padding(horizontal = 10.dp, vertical = 5.dp),
        )
    }

    menuFor?.let { index ->
        val tile = tiles.getOrNull(index)
        if (tile == null) {
            menuFor = null
        } else {
            MultiviewMenu(
                title = tile.title,
                canAdd = canAdd,
                onDismiss = { menuFor = null; runCatching { rootFocus.requestFocus() } },
                onFullscreen = { menuFor = null; onFullscreen(tile) },
                onSwap = { menuFor = null; pickerFor = index },
                onAdd = { menuFor = null; pickerFor = -1 },
                onRemove = {
                    menuFor = null
                    audioIndex = multiviewAudioAfterRemoval(audioIndex, index, tiles.size - 1)
                    onRemove(index)
                    runCatching { rootFocus.requestFocus() }
                },
                onClose = { menuFor = null; onClose() },
            )
        }
    }
    pickerFor?.let { slot ->
        val present = tiles.mapTo(hashSetOf()) { it.key }
        MultiviewPicker(
            candidates = candidates.filter { it.key !in present },
            onDismiss = { pickerFor = null; runCatching { rootFocus.requestFocus() } },
            onPick = { picked ->
                pickerFor = null
                if (slot >= 0) onReplace(slot, picked) else {
                    onAdd(picked)
                    focusIndex = tiles.size
                    audioIndex = tiles.size
                }
                runCatching { rootFocus.requestFocus() }
            },
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun MultiviewTilePlayer(
    tile: MultiviewTile,
    hasAudio: Boolean,
    tileCount: Int,
    client: OkHttpClient,
    resolve: suspend (IptvChannel) -> IptvPlaybackTarget,
    policyFor: suspend (IptvChannel) -> IptvStreamPolicy?,
) {
    val context = LocalContext.current
    val player = remember(tile.key) {
        // Small buffers: four players share one app heap.
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(6_000, 12_000, 1_000, 2_500)
            .setTargetBufferBytes(10 * 1024 * 1024)
            .setPrioritizeTimeOverSizeThresholds(false)
            .setBackBuffer(0, false)
            .build()
        ExoPlayer.Builder(context)
            .setLoadControl(loadControl)
            .build().apply {
                playWhenReady = true
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            }
    }
    var channel by remember(tile.key) { mutableStateOf(tile.channel) }
    var status by remember(tile.key) { mutableStateOf<TileStatus>(TileStatus.Loading) }
    val tried = remember(tile.key) { HashSet<String>() }
    var session by remember(tile.key) { mutableStateOf<IptvStreamSession?>(null) }

    DisposableEffect(player) {
        onDispose {
            session?.close()
            session = null
            player.release()
        }
    }
    LaunchedEffect(player, hasAudio, tileCount) {
        player.volume = if (hasAudio) 1f else 0f
        val maxHeight = multiviewMaxVideoHeight(hasAudio, tileCount)
        player.trackSelectionParameters = player.trackSelectionParameters.buildUpon()
            .setMaxVideoSize(if (maxHeight == Int.MAX_VALUE) Int.MAX_VALUE else maxHeight * 16 / 9, maxHeight)
            .build()
    }
    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) status = TileStatus.Playing
            }
            override fun onPlayerError(error: PlaybackException) {
                val failed = channel
                tried += failed.id
                val next = tile.nextChannelAfter(failed.id, tried)
                if (next != null) channel = next
                else status = TileStatus.Failed(error.errorCodeName)
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }
    LaunchedEffect(player, channel.id) {
        status = TileStatus.Loading
        player.stop()
        session?.close()
        session = null
        val policy = try { policyFor(channel) } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
        var opened: IptvStreamSession? = null
        if (policy != null) {
            // The Live TV player hands its connection back asynchronously; give it a moment.
            for (round in 0 until 3) {
                opened = IptvPlaybackConnections.sessions.tryOpenSpare(policy.account, policy.effectiveLimit)
                if (opened != null) break
                delay(1_200L * (round + 1))
            }
            if (opened == null) {
                status = TileStatus.NoConnection(policy.effectiveLimit)
                return@LaunchedEffect
            }
        }
        session = opened
        val target = try { resolve(channel) } catch (e: Exception) {
            if (e is CancellationException) throw e
            opened?.close()
            session = null
            tried += channel.id
            val next = tile.nextChannelAfter(channel.id, tried)
            if (next != null) channel = next else status = TileStatus.Failed(e.message ?: "")
            return@LaunchedEffect
        }
        val headers = (MULTIVIEW_BASE_HEADERS + channel.requestHeaders).safePlaybackHeaders()
        val http = OkHttpDataSource.Factory(opened?.callFactory(client) ?: client).setDefaultRequestProperties(headers)
        val url = target.url
        val dataFactory = DataSource.Factory {
            val upstream = http.createDataSource()
            if (target.isHls) upstream else IptvHlsDetectingDataSource(upstream, url)
        }
        val item = MediaItem.Builder().setUri(url).setMediaId(channel.id).apply {
            when {
                target.isHls -> setMimeType(MimeTypes.APPLICATION_M3U8)
                target.mimeType != null -> setMimeType(target.mimeType)
            }
            setLiveConfiguration(buildLiveTvConfiguration())
        }.build()
        val source = if (target.isHls) {
            HlsMediaSource.Factory(dataFactory)
                .setAllowChunklessPreparation(IPTV_HLS_CHUNKLESS_PREPARATION)
                .setExtractorFactory(iptvHlsExtractorFactory())
                .setLoadErrorHandlingPolicy(IptvLoadErrorHandlingPolicy())
                .createMediaSource(item)
        } else {
            DefaultMediaSourceFactory(context, iptvExtractorsFactory())
                .setDataSourceFactory(dataFactory)
                .setLoadErrorHandlingPolicy(IptvLoadErrorHandlingPolicy())
                .createMediaSource(item)
        }
        player.setMediaSource(source)
        player.prepare()
        player.play()
    }

    Box(Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false
                    setShutterBackgroundColor(android.graphics.Color.BLACK)
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    this.player = player
                }
            },
            update = { view -> if (view.player !== player) view.player = player },
            onRelease = { view -> view.player = null },
            modifier = Modifier.fillMaxSize(),
        )
        when (val current = status) {
            TileStatus.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center).size(28.dp), color = Color.White, strokeWidth = 2.dp)
            TileStatus.Playing -> Unit
            is TileStatus.NoConnection -> TileMessage(stringResource(R.string.multiview_no_connection, current.limit), Modifier.align(Alignment.Center))
            is TileStatus.Failed -> TileMessage(stringResource(R.string.multiview_tile_failed), Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun TileMessage(text: String, modifier: Modifier) {
    Text(text, color = LiveColors.Fg, fontSize = 12.sp, lineHeight = 16.sp,
        modifier = modifier.padding(16.dp).background(Color.Black.copy(alpha = .7f), RoundedCornerShape(6.dp)).padding(10.dp))
}

@Composable
private fun MultiviewMenu(
    title: String,
    canAdd: Boolean,
    onDismiss: () -> Unit,
    onFullscreen: () -> Unit,
    onSwap: () -> Unit,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onClose: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        val first = remember { FocusRequester() }
        LaunchedEffect(Unit) { runCatching { first.requestFocus() } }
        Column(Modifier.widthIn(max = 360.dp).clip(RoundedCornerShape(8.dp)).background(LiveColors.Panel)
            .border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(8.dp)).padding(12.dp)) {
            Text(title, color = LiveColors.Fg, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 2,
                overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(start = 6.dp, bottom = 8.dp))
            MenuRow(stringResource(R.string.multiview_fullscreen), Icons.Default.Fullscreen, Modifier.focusRequester(first), onFullscreen)
            MenuRow(stringResource(R.string.multiview_swap_game), Icons.Default.SwapHoriz, Modifier, onSwap)
            if (canAdd) MenuRow(stringResource(R.string.multiview_add_game), Icons.Default.Add, Modifier, onAdd)
            MenuRow(stringResource(R.string.multiview_remove_tile), Icons.Default.Close, Modifier, onRemove)
            MenuRow(stringResource(R.string.multiview_exit), Icons.Default.Close, Modifier, onClose)
        }
    }
}

@Composable
private fun MenuRow(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    Row(modifier.fillMaxWidth().heightIn(min = 44.dp).clip(RoundedCornerShape(6.dp))
        .background(if (focused) LiveColors.FocusBg else Color.Transparent)
        .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
        .onFocusChanged { focused = it.isFocused }
        .clickable(onClick = onClick).padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = LiveColors.Fg, modifier = Modifier.size(18.dp))
        Text(label, color = LiveColors.Fg, fontSize = 14.sp, modifier = Modifier.padding(start = 10.dp))
    }
}

@Composable
private fun MultiviewPicker(
    candidates: List<MultiviewTile>,
    onDismiss: () -> Unit,
    onPick: (MultiviewTile) -> Unit,
) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        val first = remember { FocusRequester() }
        LaunchedEffect(candidates.isEmpty()) { runCatching { first.requestFocus() } }
        Column(Modifier.widthIn(max = 520.dp).fillMaxWidth().padding(24.dp).clip(RoundedCornerShape(8.dp))
            .background(LiveColors.Panel).border(1.dp, LiveColors.DividerStrong, RoundedCornerShape(8.dp)).padding(14.dp)) {
            Text(stringResource(R.string.multiview_pick_title), color = LiveColors.Fg, fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
            if (candidates.isEmpty()) {
                Text(stringResource(R.string.multiview_no_candidates), color = LiveColors.FgDim, fontSize = 13.sp,
                    modifier = Modifier.padding(vertical = 12.dp))
                MenuRow(stringResource(R.string.close), Icons.Default.Close, Modifier.focusRequester(first), onDismiss)
            } else {
                LazyColumn(Modifier.heightIn(max = 420.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    itemsIndexed(candidates, key = { _, tile -> tile.key }) { index, tile ->
                        var focused by remember { mutableStateOf(false) }
                        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(6.dp))
                            .then(if (index == 0) Modifier.focusRequester(first) else Modifier)
                            .background(if (focused) LiveColors.FocusBg else Color.Transparent)
                            .border(1.dp, if (focused) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
                            .onFocusChanged { focused = it.isFocused }
                            .clickable { onPick(tile) }
                            .padding(horizontal = 10.dp, vertical = 8.dp)) {
                            Text(tile.title, color = LiveColors.Fg, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(tile.channel.name, color = LiveColors.FgDim, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
    }
}

private val MULTIVIEW_BASE_HEADERS = mapOf(
    "Accept" to "*/*",
    "Accept-Encoding" to "identity",
    "User-Agent" to OkHttpProvider.userAgentOr("VLC/3.0.20 LibVLC/3.0.20"),
)
