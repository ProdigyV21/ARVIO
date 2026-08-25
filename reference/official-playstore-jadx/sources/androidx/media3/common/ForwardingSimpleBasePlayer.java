package androidx.media3.common;

import a0.c;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.text.CueGroup;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.z0;
import j$.util.Objects;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingSimpleBasePlayer extends SimpleBasePlayer {
    private Metadata lastTimedMetadata;
    private LivePositionSuppliers livePositionSuppliers;
    private int pendingDiscontinuityReason;
    private boolean pendingFirstFrameRendered;
    private long pendingPositionDiscontinuityNewPositionMs;
    private int playWhenReadyChangeReason;
    private Player player;
    private final Player.Listener playerListener;

    public static final class LivePositionSuppliers {
        public final SimpleBasePlayer.LivePositionSupplier bufferedPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier contentBufferedPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier contentPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier currentPositionSupplier;
        public final SimpleBasePlayer.LivePositionSupplier totalBufferedPositionSupplier;

        public LivePositionSuppliers(final Player player) {
            Objects.requireNonNull(player);
            final int i10 = 0;
            this.currentPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.f
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    switch (i10) {
                        case 0:
                            return player.getCurrentPosition();
                        case 1:
                            return player.getBufferedPosition();
                        case 2:
                            return player.getContentPosition();
                        case 3:
                            return player.getContentBufferedPosition();
                        default:
                            return player.getTotalBufferedDuration();
                    }
                }
            });
            final int i11 = 1;
            this.bufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.f
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    switch (i11) {
                        case 0:
                            return player.getCurrentPosition();
                        case 1:
                            return player.getBufferedPosition();
                        case 2:
                            return player.getContentPosition();
                        case 3:
                            return player.getContentBufferedPosition();
                        default:
                            return player.getTotalBufferedDuration();
                    }
                }
            });
            final int i12 = 2;
            this.contentPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.f
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    switch (i12) {
                        case 0:
                            return player.getCurrentPosition();
                        case 1:
                            return player.getBufferedPosition();
                        case 2:
                            return player.getContentPosition();
                        case 3:
                            return player.getContentBufferedPosition();
                        default:
                            return player.getTotalBufferedDuration();
                    }
                }
            });
            final int i13 = 3;
            this.contentBufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.f
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    switch (i13) {
                        case 0:
                            return player.getCurrentPosition();
                        case 1:
                            return player.getBufferedPosition();
                        case 2:
                            return player.getContentPosition();
                        case 3:
                            return player.getContentBufferedPosition();
                        default:
                            return player.getTotalBufferedDuration();
                    }
                }
            });
            final int i14 = 4;
            this.totalBufferedPositionSupplier = new SimpleBasePlayer.LivePositionSupplier(new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.f
                @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
                public final long get() {
                    switch (i14) {
                        case 0:
                            return player.getCurrentPosition();
                        case 1:
                            return player.getBufferedPosition();
                        case 2:
                            return player.getContentPosition();
                        case 3:
                            return player.getContentBufferedPosition();
                        default:
                            return player.getTotalBufferedDuration();
                    }
                }
            });
        }

        public void disconnect(long j10, long j11) {
            this.currentPositionSupplier.disconnect(j10);
            this.bufferedPositionSupplier.disconnect(j10);
            this.contentPositionSupplier.disconnect(j11);
            this.contentBufferedPositionSupplier.disconnect(j11);
            this.totalBufferedPositionSupplier.disconnect(0L);
        }
    }

    public class PlayerListener implements Player.Listener {
        private PlayerListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            j.a(this, audioAttributes);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
            j.b(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            j.c(this, commands);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(CueGroup cueGroup) {
            j.d(this, cueGroup);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            j.f(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceVolumeChanged(int i10, boolean z) {
            j.g(this, i10, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            ForwardingSimpleBasePlayer.this.invalidateState();
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsLoadingChanged(boolean z) {
            j.i(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsPlayingChanged(boolean z) {
            j.j(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onLoadingChanged(boolean z) {
            j.k(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j10) {
            j.l(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i10) {
            j.m(this, mediaItem, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            j.n(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMetadata(Metadata metadata) {
            ForwardingSimpleBasePlayer.this.lastTimedMetadata = metadata;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean z, int i10) {
            ForwardingSimpleBasePlayer.this.playWhenReadyChangeReason = i10;
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            j.q(this, playbackParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackStateChanged(int i10) {
            j.r(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
            j.s(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            j.t(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            j.u(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerStateChanged(boolean z, int i10) {
            j.v(this, z, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            j.w(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPositionDiscontinuity(int i10) {
            j.x(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            ForwardingSimpleBasePlayer.this.pendingFirstFrameRendered = true;
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onRepeatModeChanged(int i10) {
            j.A(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekBackIncrementChanged(long j10) {
            j.B(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekForwardIncrementChanged(long j10) {
            j.C(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            j.D(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            j.E(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11) {
            j.F(this, i10, i11);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTimelineChanged(Timeline timeline, int i10) {
            j.G(this, timeline, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            j.H(this, trackSelectionParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTracksChanged(Tracks tracks) {
            j.I(this, tracks);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            j.J(this, videoSize);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVolumeChanged(float f10) {
            j.K(this, f10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(List list) {
            j.e(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
            ForwardingSimpleBasePlayer.this.pendingDiscontinuityReason = i10;
            ForwardingSimpleBasePlayer.this.pendingPositionDiscontinuityNewPositionMs = positionInfo2.positionMs;
            ForwardingSimpleBasePlayer.this.livePositionSuppliers.disconnect(positionInfo.positionMs, positionInfo.contentPositionMs);
            ForwardingSimpleBasePlayer forwardingSimpleBasePlayer = ForwardingSimpleBasePlayer.this;
            forwardingSimpleBasePlayer.livePositionSuppliers = new LivePositionSuppliers(forwardingSimpleBasePlayer.player);
        }
    }

    public ForwardingSimpleBasePlayer(Player player) {
        super(player.getApplicationLooper());
        initializeForwardingState(player);
        PlayerListener playerListener = new PlayerListener();
        this.playerListener = playerListener;
        player.addListener(playerListener);
    }

    @EnsuresNonNull({"this.player", "lastTimedMetadata", "playWhenReadyChangeReason", "pendingDiscontinuityReason", "livePositionSuppliers"})
    private void initializeForwardingState(Player player) {
        this.player = player;
        this.lastTimedMetadata = new Metadata(C.TIME_UNSET, new Metadata.Entry[0]);
        this.playWhenReadyChangeReason = 1;
        this.pendingDiscontinuityReason = 5;
        this.livePositionSuppliers = new LivePositionSuppliers(player);
    }

    public final Player getPlayer() {
        return this.player;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public SimpleBasePlayer.State getState() {
        SimpleBasePlayer.State.Builder builder = new SimpleBasePlayer.State.Builder();
        LivePositionSuppliers livePositionSuppliers = this.livePositionSuppliers;
        if (this.player.isCommandAvailable(16)) {
            builder.setAdBufferedPositionMs(livePositionSuppliers.bufferedPositionSupplier);
            builder.setAdPositionMs(livePositionSuppliers.currentPositionSupplier);
        }
        if (this.player.isCommandAvailable(21)) {
            builder.setAudioAttributes(this.player.getAudioAttributes());
        }
        builder.setAvailableCommands(this.player.getAvailableCommands());
        if (this.player.isCommandAvailable(16)) {
            builder.setContentBufferedPositionMs(livePositionSuppliers.contentBufferedPositionSupplier);
            builder.setContentPositionMs(livePositionSuppliers.contentPositionSupplier);
            if (this.player.isCommandAvailable(17)) {
                builder.setCurrentAd(this.player.getCurrentAdGroupIndex(), this.player.getCurrentAdIndexInAdGroup());
            }
        }
        if (this.player.isCommandAvailable(28)) {
            builder.setCurrentCues(this.player.getCurrentCues());
        }
        if (this.player.isCommandAvailable(17)) {
            builder.setCurrentMediaItemIndex(this.player.getCurrentMediaItemIndex());
        }
        builder.setDeviceInfo(this.player.getDeviceInfo());
        if (this.player.isCommandAvailable(23)) {
            builder.setDeviceVolume(this.player.getDeviceVolume());
            builder.setIsDeviceMuted(this.player.isDeviceMuted());
        }
        builder.setIsLoading(this.player.isLoading());
        builder.setMaxSeekToPreviousPositionMs(this.player.getMaxSeekToPreviousPosition());
        if (this.pendingFirstFrameRendered) {
            builder.setNewlyRenderedFirstFrame(true);
            this.pendingFirstFrameRendered = false;
        }
        builder.setPlaybackParameters(this.player.getPlaybackParameters());
        builder.setPlaybackState(this.player.getPlaybackState());
        builder.setPlaybackSuppressionReason(this.player.getPlaybackSuppressionReason());
        builder.setPlayerError(this.player.getPlayerError());
        if (this.player.isCommandAvailable(17)) {
            builder.setPlaylist(this.player.getCurrentTimeline(), this.player.isCommandAvailable(30) ? this.player.getCurrentTracks() : Tracks.EMPTY, this.player.isCommandAvailable(18) ? this.player.getMediaMetadata() : null);
        }
        if (this.player.isCommandAvailable(18)) {
            builder.setPlaylistMetadata(this.player.getPlaylistMetadata());
        }
        builder.setPlayWhenReady(this.player.getPlayWhenReady(), this.playWhenReadyChangeReason);
        long j10 = this.pendingPositionDiscontinuityNewPositionMs;
        if (j10 != C.TIME_UNSET) {
            builder.setPositionDiscontinuity(this.pendingDiscontinuityReason, j10);
            this.pendingPositionDiscontinuityNewPositionMs = C.TIME_UNSET;
        }
        builder.setRepeatMode(this.player.getRepeatMode());
        builder.setSeekBackIncrementMs(this.player.getSeekBackIncrement());
        builder.setSeekForwardIncrementMs(this.player.getSeekForwardIncrement());
        builder.setShuffleModeEnabled(this.player.getShuffleModeEnabled());
        builder.setSurfaceSize(this.player.getSurfaceSize());
        builder.setTimedMetadata(this.lastTimedMetadata);
        if (this.player.isCommandAvailable(16)) {
            builder.setTotalBufferedDurationMs(livePositionSuppliers.totalBufferedPositionSupplier);
        }
        builder.setTrackSelectionParameters(this.player.getTrackSelectionParameters());
        builder.setVideoSize(this.player.getVideoSize());
        if (this.player.isCommandAvailable(22)) {
            builder.setVolume(this.player.getVolume());
        }
        return builder.build();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleAddMediaItems(int i10, List<MediaItem> list) {
        if (list.size() == 1) {
            this.player.addMediaItem(i10, list.get(0));
        } else {
            this.player.addMediaItems(i10, list);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleClearVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.clearVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.clearVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.clearVideoSurfaceHolder((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.player.clearVideoSurface((Surface) obj);
        } else {
            if (obj != null) {
                throw new IllegalStateException();
            }
            this.player.clearVideoSurface();
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleDecreaseDeviceVolume(int i10) {
        if (this.player.isCommandAvailable(34)) {
            this.player.decreaseDeviceVolume(i10);
        } else {
            this.player.decreaseDeviceVolume();
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleIncreaseDeviceVolume(int i10) {
        if (this.player.isCommandAvailable(34)) {
            this.player.increaseDeviceVolume(i10);
        } else {
            this.player.increaseDeviceVolume();
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleMoveMediaItems(int i10, int i11, int i12) {
        if (i11 == i10 + 1) {
            this.player.moveMediaItem(i10, i12);
        } else {
            this.player.moveMediaItems(i10, i11, i12);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handlePrepare() {
        this.player.prepare();
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleRelease() {
        this.player.release();
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleRemoveMediaItems(int i10, int i11) {
        if (i11 == i10 + 1) {
            this.player.removeMediaItem(i10);
        } else {
            this.player.removeMediaItems(i10, i11);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleReplaceMediaItems(int i10, int i11, List<MediaItem> list) {
        if (i11 == i10 + 1 && list.size() == 1) {
            this.player.replaceMediaItem(i10, list.get(0));
        } else {
            this.player.replaceMediaItems(i10, i11, list);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSeek(int i10, long j10, int i11) {
        switch (i11) {
            case 4:
                this.player.seekToDefaultPosition();
                break;
            case 5:
                this.player.seekTo(j10);
                break;
            case 6:
                this.player.seekToPreviousMediaItem();
                break;
            case 7:
                this.player.seekToPrevious();
                break;
            case 8:
                this.player.seekToNextMediaItem();
                break;
            case 9:
                this.player.seekToNext();
                break;
            case 10:
                if (i10 != -1) {
                    this.player.seekTo(i10, j10);
                }
                break;
            case 11:
                this.player.seekBack();
                break;
            case 12:
                this.player.seekForward();
                break;
            default:
                throw new IllegalStateException();
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        this.player.setAudioAttributes(audioAttributes, z);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetDeviceMuted(boolean z, int i10) {
        if (this.player.isCommandAvailable(34)) {
            this.player.setDeviceMuted(z, i10);
        } else {
            this.player.setDeviceMuted(z);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetDeviceVolume(int i10, int i11) {
        if (this.player.isCommandAvailable(33)) {
            this.player.setDeviceVolume(i10, i11);
        } else {
            this.player.setDeviceVolume(i10);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetMediaItems(List<MediaItem> list, int i10, long j10) {
        boolean z = list.size() == 1 && this.player.isCommandAvailable(31);
        if (i10 == -1) {
            if (z) {
                this.player.setMediaItem(list.get(0));
            } else {
                this.player.setMediaItems(list);
            }
        } else if (z) {
            this.player.setMediaItem(list.get(0), j10);
        } else {
            this.player.setMediaItems(list, i10, j10);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetPlayWhenReady(boolean z) {
        this.player.setPlayWhenReady(z);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        this.player.setPlaybackParameters(playbackParameters);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        this.player.setPlaylistMetadata(mediaMetadata);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetRepeatMode(int i10) {
        this.player.setRepeatMode(i10);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetShuffleModeEnabled(boolean z) {
        this.player.setShuffleModeEnabled(z);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        this.player.setTrackSelectionParameters(trackSelectionParameters);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.setVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.setVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.setVideoSurfaceHolder((SurfaceHolder) obj);
        } else {
            if (!(obj instanceof Surface)) {
                throw new IllegalStateException();
            }
            this.player.setVideoSurface((Surface) obj);
        }
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public final d1<?> handleSetVolume(float f10) {
        this.player.setVolume(f10);
        return z0.f14245l;
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleStop() {
        this.player.stop();
        return z0.f14245l;
    }

    public final void setPlayer(Player player) {
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player.getApplicationLooper() != player2.getApplicationLooper()) {
            throw new IllegalArgumentException("Trying to swap players with non-matching loopers.");
        }
        player2.removeListener(this.playerListener);
        player.addListener(this.playerListener);
        initializeForwardingState(player);
        this.pendingPositionDiscontinuityNewPositionMs = player.getCurrentPosition();
        invalidateState();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    public d1<?> handleSetVolume(float f10, int i10) {
        if (i10 == 0) {
            this.player.setVolume(f10);
        } else if (i10 == 1) {
            this.player.mute();
        } else if (i10 == 2) {
            this.player.unmute();
        } else {
            throw new IllegalStateException(c.i(i10, "Unknown volume operation type: "));
        }
        return z0.f14245l;
    }
}
