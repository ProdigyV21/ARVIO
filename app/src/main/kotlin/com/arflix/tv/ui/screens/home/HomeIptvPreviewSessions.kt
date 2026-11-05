package com.arflix.tv.ui.screens.home

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import com.arflix.tv.data.repository.ResolvedIptvChannelPolicy
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamSession
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.network.MAX_IPTV_CLOSE_JOIN_MS
import com.arflix.tv.ui.screens.tv.live.IPTV_HLS_CHUNKLESS_PREPARATION
import com.arflix.tv.ui.screens.tv.live.iptvExtractorsFactory
import com.arflix.tv.ui.screens.tv.live.iptvHlsExtractorFactory
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient

internal const val DEFAULT_IPTV_PREVIEW_REFUSAL_MS = 60_000L

internal data class HomeIptvPreviewAdmission(val session: IptvStreamSession?)

/**
 * A Home channel preview is a playback session like any other: counted against its account,
 * opened only with verified spare capacity and closed as soon as it leaves.
 */
internal class HomeIptvPreviewSessions(
    private val sessions: IptvStreamSessionTable = IptvPlaybackConnections.sessions,
    private val clock: () -> Long = System::currentTimeMillis,
    private val checkAccount: suspend (IptvProviderAccount) -> IptvAccountUsage?,
) {
    private var session: IptvStreamSession? = null
    private val refusedAt = HashMap<IptvProviderAccount, Long>()

    /**
     * Runs before the hero probes or prepares. Null means show no preview.
     * Only an explicit No limit can proceed without a session or panel check.
     */
    suspend fun admit(player: Player?, readPolicy: suspend () -> ResolvedIptvChannelPolicy?): HomeIptvPreviewAdmission? {
        val previous = session
        session = null
        val read = try {
            readPolicy()
        } catch (e: Exception) {
            // The last card's session is no longer held anywhere, so even a moved focus must close it.
            release(player, previous)
            if (e is kotlinx.coroutines.CancellationException) throw e
            return null
        }
        if (read?.allowsHomePreview != true) {
            release(player, previous)
            return null
        }
        val limit = read.limit
        if (limit == null) {
            if (previous != null) release(player, previous)
            return HomeIptvPreviewAdmission(null)
        }

        player?.stop()
        player?.clearMediaItems()
        previous?.close()?.let { closing ->
            withTimeoutOrNull(MAX_IPTV_CLOSE_JOIN_MS) { closing.join() }
        }
        if (sessions.openCount(limit.account) >= limit.effectiveLimit || !panelAllows(limit.account)) return null
        val spare = sessions.tryOpenSpare(limit.account, limit.effectiveLimit) ?: return null
        session = spare
        return HomeIptvPreviewAdmission(spare)
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
        session?.close()
        session = null
    }

    /** Full and unavailable answers hold for a minute, so browsing does not hammer the panel. */
    private suspend fun panelAllows(account: IptvProviderAccount): Boolean {
        refusedAt[account]?.let { if (clock() - it < DEFAULT_IPTV_PREVIEW_REFUSAL_MS) return false }
        if (IptvAccountAvailability.hasVerifiedSpareSlot { checkAccount(account) }) return true
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
