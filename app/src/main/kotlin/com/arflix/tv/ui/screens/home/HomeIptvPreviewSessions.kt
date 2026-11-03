package com.arflix.tv.ui.screens.home

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import com.arflix.tv.data.repository.IptvStreamPolicy
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.ui.screens.tv.live.IPTV_HLS_CHUNKLESS_PREPARATION
import com.arflix.tv.ui.screens.tv.live.iptvExtractorsFactory
import com.arflix.tv.ui.screens.tv.live.iptvHlsExtractorFactory
import okhttp3.OkHttpClient

internal const val DEFAULT_IPTV_PREVIEW_REFUSAL_MS = 60_000L

/**
 * A Home channel preview is a playback session like any other: counted against its account,
 * never opened when a single cold-start check finds the account full, and closed as soon as it leaves.
 */
internal class HomeIptvPreviewSessions(
    private val sessions: IptvStreamSessionTable = IptvPlaybackConnections.sessions,
    private val clock: () -> Long = System::currentTimeMillis,
    private val checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
) {
    private var policy: IptvStreamPolicy? = null
    private var session: IptvStreamSession? = null
    private val refusedAt = HashMap<IptvProviderAccount, Long>()

    /**
     * Runs before a channel card's hero resolves or prepares anything; false means show no preview.
     * A null policy (No limit, unknown owner) keeps today's untagged hero and asks nothing.
     */
    suspend fun admit(player: Player?, readPolicy: suspend () -> IptvStreamPolicy?): Boolean {
        val previous = session
        session = null
        policy = null
        val read = try {
            readPolicy()
        } catch (e: Exception) {
            // The last card's session is no longer held anywhere, so even a moved focus must close it.
            release(player, previous)
            if (e is kotlinx.coroutines.CancellationException) throw e
            return false
        }
        if (read == null) {
            if (previous != null) release(player, previous)
            return true
        }
        if (!read.homePreviewAllowed) {
            release(player, previous)
            return false
        }
        player?.stop()
        player?.clearMediaItems()
        sessions.makeRoom(read.account, read.effectiveLimit, previous)
        if (read.panelCheckAllowed && !panelAllows(read.account)) return false
        policy = read
        return true
    }

    /** Called right before the hero prepares, and never waits. Null keeps the hero's requests untagged, as today. */
    fun open(): IptvStreamSession? {
        val current = policy ?: return null
        session?.close()
        return sessions.open(current.account, current.effectiveLimit).also { session = it }
    }

    /** What the hero binds to when it prepares: anything that is not a channel card gives a session back first. */
    fun sessionForPrepare(isHeroIptv: Boolean): IptvStreamSession? =
        if (isHeroIptv) open() else {
            close()
            null
        }

    /** Leaving the card: a session's stream stops rather than pauses, since a paused loader keeps its socket. True when it stopped. */
    fun leave(player: Player?, stopOnLeave: Boolean): Boolean {
        val stop = stopOnLeave || session != null
        player?.leaveHomeHeroPreview(stop)
        close()
        return stop
    }

    /** Stop, URL change or dispose: the allowance goes back now, not when a body happens to close. */
    fun close() {
        policy = null
        session?.close()
        session = null
    }

    /** A full answer holds for a minute, so browsing more cards on a full account sends no further checks. */
    private suspend fun panelAllows(account: IptvProviderAccount): Boolean {
        refusedAt[account]?.let { if (clock() - it < DEFAULT_IPTV_PREVIEW_REFUSAL_MS) return false }
        if (!sessions.isColdStart(account)) return true
        if (IptvAccountAvailability.admitOnce { checkAccount(account) } !is IptvAdmission.Full) return true
        refusedAt[account] = clock()
        return false
    }

    private fun release(player: Player?, previous: IptvStreamSession?) {
        player?.stop()
        player?.clearMediaItems()
        previous?.close()
    }
}

/** The hero's client for counted previews; collection videos and untagged previews keep the plain client. */
internal fun homeHeroIptvClient(heroClient: OkHttpClient): OkHttpClient =
    heroClient.newBuilder().addInterceptor(IptvPlaybackConnections()).build()

/** Every request of a counted preview, HLS playlists and segments included, carries its session. */
internal fun homeIptvPreviewSource(
    context: Context,
    iptvClient: OkHttpClient,
    userAgent: String,
    session: IptvStreamSession,
    item: MediaItem,
    useHls: Boolean,
): MediaSource {
    val http = OkHttpDataSource.Factory(session.callFactory(iptvClient)).setUserAgent(userAgent)
    return if (useHls) {
        HlsMediaSource.Factory(http)
            .setAllowChunklessPreparation(IPTV_HLS_CHUNKLESS_PREPARATION)
            .setExtractorFactory(iptvHlsExtractorFactory())
            .createMediaSource(item)
    } else {
        DefaultMediaSourceFactory(context, iptvExtractorsFactory()).setDataSourceFactory(http).createMediaSource(item)
    }
}
