package com.arflix.tv.ui.screens.player

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.dash.DashMediaSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.extractor.ExtractorsFactory
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.IptvStreamPolicy
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.network.MAX_IPTV_CLOSE_JOIN_MS
import com.arflix.tv.ui.screens.tv.live.IptvLoadErrorHandlingPolicy
import com.arflix.tv.ui.screens.tv.live.IptvNetworkWatch
import com.arflix.tv.ui.screens.tv.live.LiveDrop
import com.arflix.tv.ui.screens.tv.live.awaitLiveNetwork
import com.arflix.tv.ui.screens.tv.live.liveRetryAttempt
import com.arflix.tv.ui.screens.tv.live.rememberIptvNetworkWatch
import com.arflix.tv.ui.screens.tv.live.vodMidStreamDrop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient

/** Where a limited source reopens after its session was closed for the background or a cast. */
internal data class IptvVodReopen(val positionMs: Long, val playWhenReady: Boolean)

/** The pick waiting for its URL is not this URL's source, so it must not lend this URL its account. */
internal fun iptvVodStreamFor(selected: StreamSource?, url: String): StreamSource? = selected?.takeIf { it.url == url }

/** Keeps IPTV VOD's order on a limited account: at 1 the old stream ends first, at 2 or more it ends after the next prepares. */
internal class IptvVodStreamGate(
    private val sessions: IptvStreamSessionTable = IptvPlaybackConnections.sessions,
    private val network: IptvNetworkWatch = IptvNetworkWatch { true },
) {
    private var policy: IptvStreamPolicy? = null
    private var outgoing: IptvStreamSession? = null
    private var readySinceMs: Long? = null
    private var rung: Job? = null
    private var reattachOnReady = false
    private var closedForCast = false
    private var castFromMs = 0L
    private var backgroundResumePositionMs: Long? = null
    private var pendingReopen: IptvVodReopen? = null

    var session: IptvStreamSession? = null
        private set

    /** Restarts the stream effect, which picks up [takeReopen]. */
    var reopenSignal by mutableIntStateOf(0)
        private set

    /** Restarts the subtitle effect once a reopened source is ready, because that source was built without its external subtitle. */
    var reattachSubtitleSignal by mutableIntStateOf(0)
        private set

    /** The watchdogs must not fail a source over for being closed on purpose while the app is stopped. */
    val isClosedForBackground: Boolean get() = backgroundResumePositionMs != null

    /** Only a source whose rungs can ask the panel has a reason to surface repeated drops. */
    val panelCheckAllowed: Boolean get() = policy?.panelCheckAllowed == true

    /** A reopen is the same source carrying on, so the stream effect must not treat it as a startup. */
    val hasPendingReopen: Boolean get() = pendingReopen != null

    /** A released stream is a new provider connection, even if the player will resume paused. */
    suspend fun admitReopen(
        next: IptvStreamPolicy?,
        checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
    ): IptvAdmission {
        val account = next?.account ?: return IptvAdmission.Proceed
        if (!hasPendingReopen || account.kind != IptvProviderAccount.Kind.XTREAM) return IptvAdmission.Proceed
        return IptvAccountAvailability.admit { checkAccount(account) }
    }

    /** Runs right before a media source is built. Null means that source stays on today's factories. */
    suspend fun open(player: Player, next: IptvStreamPolicy?): IptvStreamSession? {
        rung?.cancel()
        val previous = session
        readySinceMs = null
        if (next == null) {
            session = null
            policy = null
            previous?.close()
            return null
        }
        if (next.effectiveLimit == 1) {
            player.stop()
            session = null
            sessions.makeRoom(next.account, 1, previous)
        } else {
            sessions.makeRoom(next.account, next.effectiveLimit)
            outgoing = previous
        }
        policy = next
        return sessions.open(next.account, next.effectiveLimit, reusesRedirects = true).also { session = it }
    }

    /** The old frame stayed up while the next source prepared; now its stream can go. */
    fun afterPrepare() {
        outgoing?.close()
        outgoing = null
    }

    fun noteReady(nowMs: Long) {
        if (readySinceMs == null) readySinceMs = nowMs
        if (reattachOnReady) {
            reattachOnReady = false
            reattachSubtitleSignal++
        }
    }

    fun reattachSubtitleWhenReady() {
        reattachOnReady = true
    }

    fun classifyDrop(error: Throwable, nowMs: Long): LiveDrop = network.classifyDrop(error, nowMs)

    /** One mid-playback rung, off the player's error callback because it may wait on the panel. */
    fun launchRung(
        scope: CoroutineScope,
        player: Player,
        error: Throwable,
        nowMs: Long,
        checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
        onBusy: () -> Unit,
    ) {
        val positionMs = player.currentPosition.coerceAtLeast(0L)
        val keepPlaying = player.playWhenReady
        val drop = classifyDrop(error, nowMs)
        track(
            scope.launch {
                if (recoverRung(player, positionMs, keepPlaying, drop, checkAccount) is IptvAdmission.Full) onBusy()
            }
        )
    }

    /** Lets a new source, a close or a cast stop a rung that may still be waiting on the panel. */
    fun track(job: Job) {
        rung?.cancel()
        rung = job
    }

    /** Same refill as Live TV: a source that has played for a while gets its rungs back at the next error. */
    fun nextRungAttempt(current: Int, nowMs: Long): Int = liveRetryAttempt(current, readySinceMs, nowMs)

    /** Before a subtitle rebuild reopens the stream at a limit of 1. The session keeps its allowance. */
    suspend fun clearStreamForRebuild(player: Player) {
        if (policy?.effectiveLimit != 1) return
        player.stop()
        joinCancelledCalls()
    }

    /** Stop, join and prepare are one step: a restart during the join would find IDLE and skip its rebuild for good. */
    suspend fun rebuildForSubtitle(player: Player, prepare: (IptvStreamSession) -> Unit) =
        withContext(NonCancellable) {
            val current = session ?: return@withContext
            clearStreamForRebuild(player)
            if (session !== current) return@withContext
            prepare(current)
        }

    /** A mid-playback rung re-prepares in place: the session stays, but its dead request must end before the new one. */
    suspend fun recoverRung(
        player: Player,
        positionMs: Long,
        keepPlaying: Boolean,
        drop: LiveDrop,
        checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
    ): IptvAdmission {
        val current = policy ?: return IptvAdmission.Proceed
        player.stop()
        joinCancelledCalls()
        if (drop == LiveDrop.NetworkLost) awaitLiveNetwork(network.hasActiveNetwork)
        if (drop == LiveDrop.Ambiguous && current.panelCheckAllowed) {
            val answer = IptvAccountAvailability.admitReconnect { checkAccount(current.account) }
            if (answer is IptvAdmission.Full) {
                closeSessions()
                return answer
            }
        }
        readySinceMs = null
        player.prepare()
        player.seekTo(positionMs)
        player.playWhenReady = keepPlaying
        return IptvAdmission.Proceed
    }

    /** Backgrounded outside picture-in-picture: gives the allowance back and remembers where to reopen. */
    fun closeForBackground(player: Player) {
        rung?.cancel()
        if (session == null) return
        backgroundResumePositionMs = player.currentPosition.coerceAtLeast(0L)
        player.stop()
        close()
    }

    /** The app stopped while this source was still on its way: its slot waits for the user to come back. */
    fun deferOpen(player: Player, positionMs: Long) {
        player.stop()
        close()
        backgroundResumePositionMs = pendingReopen?.positionMs ?: positionMs
        pendingReopen = null
    }

    /** ON_PAUSE had already paused the player, and it comes back paused, as any source does. */
    fun reopenAfterBackground() {
        val position = backgroundResumePositionMs ?: return
        backgroundResumePositionMs = null
        requestReopen(IptvVodReopen(position, playWhenReady = false))
    }

    /** True when the local stream was given up for the cast and now reopens. */
    fun reopenAfterCast(positionMs: Long): Boolean {
        if (!closedForCast) return false
        closedForCast = false
        // A cast that never reported a position must not restart the film from zero.
        requestReopen(IptvVodReopen(positionMs.takeIf { it > 0L } ?: castFromMs, playWhenReady = true))
        return true
    }

    fun takeReopen(): IptvVodReopen? = pendingReopen.also { pendingReopen = null }

    private fun requestReopen(reopen: IptvVodReopen) {
        pendingReopen = reopen
        reopenSignal++
    }

    /** A cast device holds its own connection, so the local one must not sit behind it. */
    fun closeForCast(player: Player) {
        rung?.cancel()
        if (session == null) return
        castFromMs = player.currentPosition.coerceAtLeast(0L)
        player.stop()
        close()
        closedForCast = true
    }

    fun close() {
        rung?.cancel()
        closeSessions()
    }

    private fun closeSessions() {
        session?.close()
        session = null
        outgoing?.close()
        outgoing = null
    }

    private suspend fun joinCancelledCalls() {
        val cancelling = session?.cancelCalls() ?: return
        withTimeoutOrNull(MAX_IPTV_CLOSE_JOIN_MS) { cancelling.join() }
    }
}

@Composable
internal fun rememberIptvVodGate(): IptvVodStreamGate {
    val network = rememberIptvNetworkWatch()
    return remember { IptvVodStreamGate(network = network) }
}

/** Every loader of a limited IPTV VOD source shares one session's factory, so nothing escapes its allowance. */
internal class IptvVodMediaSources(
    private val context: Context,
    playbackClient: OkHttpClient,
    private val userAgent: String,
    private val extractors: ExtractorsFactory,
) {
    // Never cancelAllAsync or evictAll here: those sweep every call on the client.
    private val client = playbackClient.newBuilder()
        .addInterceptor(IptvPlaybackConnections())
        .build()

    fun create(
        session: IptvStreamSession,
        headers: Map<String, String>,
        mediaItem: MediaItem,
        isHls: Boolean,
        isDash: Boolean,
        hasSidecars: Boolean,
        panelCheckAllowed: Boolean = false,
    ): MediaSource {
        val http = OkHttpDataSource.Factory(session.callFactory(client))
            .setUserAgent(userAgent)
            .setDefaultRequestProperties(headers)
        val policy = IptvLoadErrorHandlingPolicy(vodMidStreamDrop(isHls, isDash, panelCheckAllowed))
        return when {
            // Sidecar subtitle configs only merge through a DefaultMediaSourceFactory.
            hasSidecars -> DefaultMediaSourceFactory(context, extractors)
                .setDataSourceFactory(DefaultDataSource.Factory(context, http))
                .setLoadErrorHandlingPolicy(policy)
                .createMediaSource(mediaItem)
            isHls -> HlsMediaSource.Factory(http)
                .setAllowChunklessPreparation(true)
                .setLoadErrorHandlingPolicy(policy)
                .createMediaSource(mediaItem)
            isDash -> DashMediaSource.Factory(http)
                .setLoadErrorHandlingPolicy(policy)
                .createMediaSource(mediaItem)
            else -> ProgressiveMediaSource.Factory(http, extractors)
                .setLoadErrorHandlingPolicy(policy)
                .createMediaSource(mediaItem)
        }
    }
}
