package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

/* JADX INFO: loaded from: classes3.dex */
public interface LoadControl {

    @Deprecated
    public static final MediaSource.MediaPeriodId EMPTY_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());

    public static final class Parameters {
        public final long bufferedDurationUs;
        public final long lastRebufferRealtimeMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final boolean playWhenReady;
        public final long playbackPositionUs;
        public final float playbackSpeed;
        public final PlayerId playerId;
        public final boolean rebuffering;
        public final long targetLiveOffsetUs;
        public final Timeline timeline;

        public Parameters(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10, long j11, float f10, boolean z, boolean z5, long j12, long j13) {
            this.playerId = playerId;
            this.timeline = timeline;
            this.mediaPeriodId = mediaPeriodId;
            this.playbackPositionUs = j10;
            this.bufferedDurationUs = j11;
            this.playbackSpeed = f10;
            this.playWhenReady = z;
            this.rebuffering = z5;
            this.targetLiveOffsetUs = j12;
            this.lastRebufferRealtimeMs = j13;
        }
    }

    Allocator getAllocator(PlayerId playerId);

    @Deprecated
    long getBackBufferDurationUs();

    long getBackBufferDurationUs(PlayerId playerId);

    @Deprecated
    void onPrepared();

    void onPrepared(PlayerId playerId);

    @Deprecated
    void onReleased();

    void onReleased(PlayerId playerId);

    @Deprecated
    void onStopped();

    void onStopped(PlayerId playerId);

    @Deprecated
    void onTracksSelected(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    void onTracksSelected(Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    @Deprecated
    void onTracksSelected(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    @Deprecated
    void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    @Deprecated
    boolean retainBackBufferFromKeyframe();

    boolean retainBackBufferFromKeyframe(PlayerId playerId);

    @Deprecated
    boolean shouldContinueLoading(long j10, long j11, float f10);

    boolean shouldContinueLoading(Parameters parameters);

    boolean shouldContinuePreloading(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10);

    @Deprecated
    boolean shouldStartPlayback(long j10, float f10, boolean z, long j11);

    @Deprecated
    boolean shouldStartPlayback(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10, float f10, boolean z, long j11);

    boolean shouldStartPlayback(Parameters parameters);
}
