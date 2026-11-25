package com.arflix.tv.music

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import java.io.IOException
import java.util.concurrent.TimeUnit

/** The service is started by media3, not by Hilt, so it fetches the repository itself. */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface MusicServiceEntryPoint {
    fun musicRepository(): MusicRepository
}

/**
 * Plays the Music tab's queue in the background, with the system media notification and
 * lock screen controls that come with a media3 session.
 */
class MusicPlaybackService : MediaSessionService() {

    private var session: MediaSession? = null

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        val renderers = DefaultRenderersFactory(this)
            // The bundled FFmpeg decoder covers formats the platform lacks (ALAC, older WMA, ...).
            .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
        // Addon tracks carry a placeholder URI; the addon is asked for the real stream only
        // when the track is about to load, so a whole album queues without a request per track.
        val http = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        val repository = EntryPointAccessors.fromApplication(applicationContext, MusicServiceEntryPoint::class.java).musicRepository()
        fun addonStream(uri: Uri): Uri {
            val base = uri.getQueryParameter("base") ?: throw IOException("Missing addon")
            val id = uri.getQueryParameter("id") ?: throw IOException("Missing track")
            val stream = runCatching { MusicAddonSource.resolveStream(http, base, id, uri.getQueryParameter("p")) }
                .getOrElse { throw IOException(it.message, it) }
            return Uri.parse(stream)
        }
        val dataSources = ResolvingDataSource.Factory(DefaultDataSource.Factory(this)) { spec ->
            val uri = spec.uri
            when (uri.scheme) {
                MusicAddonSource.STREAM_SCHEME -> spec.withUri(addonStream(uri))
                // A chart song: found in the user's own sources only now that it starts.
                MusicMatch.SCHEME -> {
                    val title = uri.getQueryParameter("t").orEmpty()
                    val track = runBlocking {
                        val source = repository.currentSource() ?: throw MusicMatchMissingException(title)
                        MusicMatch.find(source, title, uri.getQueryParameter("a").orEmpty(), uri.getQueryParameter("d")?.toIntOrNull() ?: 0)
                    }
                    val stream = Uri.parse(track.streamUrl)
                    spec.withUri(if (stream.scheme == MusicAddonSource.STREAM_SCHEME) addonStream(stream) else stream)
                }
                else -> spec
            }
        }
        val player = ExoPlayer.Builder(this, renderers)
            .setMediaSourceFactory(DefaultMediaSourceFactory(dataSources))
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .build(),
                /* handleAudioFocus = */ true,
            )
            .setHandleAudioBecomingNoisy(true)
            .setWakeMode(C.WAKE_MODE_NETWORK)
            .build()
        // A chart song none of the user's sources has is skipped instead of stopping the queue.
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                if (generateSequence<Throwable>(error) { it.cause }.none { it is MusicMatchMissingException }) return
                if (!player.hasNextMediaItem()) return
                player.seekToNextMediaItem()
                player.prepare()
                player.play()
            }
        })

        val builder = MediaSession.Builder(this, player)
            .setId("arvio_music")
            .setCallback(object : MediaSession.Callback {
                // Controllers do not pass a media item's URI on; it travels in requestMetadata.
                override fun onAddMediaItems(
                    mediaSession: MediaSession,
                    controller: MediaSession.ControllerInfo,
                    mediaItems: MutableList<MediaItem>,
                ): ListenableFuture<MutableList<MediaItem>> = Futures.immediateFuture(
                    mediaItems.map { item ->
                        val uri = item.requestMetadata.mediaUri
                        if (item.localConfiguration == null && uri != null) item.buildUpon().setUri(uri).build() else item
                    }.toMutableList()
                )
            })
        packageManager.getLaunchIntentForPackage(packageName)?.let { launch ->
            builder.setSessionActivity(
                PendingIntent.getActivity(
                    this,
                    0,
                    launch.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
                )
            )
        }
        session = builder.build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = session

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = session?.player
        if (player == null || !player.playWhenReady || player.mediaItemCount == 0) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        session?.run {
            player.release()
            release()
        }
        session = null
        super.onDestroy()
    }
}
