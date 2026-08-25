package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NoSampleRenderer implements Renderer, RendererCapabilities {
    private RendererConfiguration configuration;
    private int index;
    private int state;
    private SampleStream stream;
    private boolean streamIsFinal;

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final /* synthetic */ void clearListener() {
        t0.a(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void disable() {
        ac.b.s(this.state == 1);
        this.state = 0;
        this.stream = null;
        this.streamIsFinal = false;
        onDisabled();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j10, boolean z, boolean z5, long j11, long j12, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        ac.b.s(this.state == 0);
        this.configuration = rendererConfiguration;
        this.state = 1;
        onEnabled(z);
        replaceStream(formatArr, sampleStream, j11, j12, mediaPeriodId);
        onPositionReset(j10, z, true);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void enableMayRenderStartOfStream() {
        s0.a(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public final RendererConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ long getDurationToProgressUs(long j10, long j11) {
        return s0.b(this, j10, j11);
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return null;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public long getReadingPositionUs() {
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final int getState() {
        return this.state;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final SampleStream getStream() {
        return this.stream;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public final int getTrackType() {
        return -2;
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i10, Object obj) throws ExoPlaybackException {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean hasReadStreamToEnd() {
        return true;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void init(int i10, PlayerId playerId, Clock clock) {
        this.index = i10;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return true;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void maybeThrowStreamError() throws IOException {
    }

    public void onDisabled() {
    }

    public void onEnabled(boolean z) throws ExoPlaybackException {
    }

    public void onPositionReset(long j10, boolean z, boolean z5) throws ExoPlaybackException {
    }

    public void onRendererOffsetChanged(long j10) throws ExoPlaybackException {
    }

    public void onReset() {
    }

    public void onStarted() throws ExoPlaybackException {
    }

    public void onStopped() {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void release() {
        s0.c(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j10, long j11, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        ac.b.s(!this.streamIsFinal);
        this.stream = sampleStream;
        onRendererOffsetChanged(j11);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void reset() {
        ac.b.s(this.state == 0);
        onReset();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void resetPosition(long j10, boolean z) throws ExoPlaybackException {
        this.streamIsFinal = false;
        onPositionReset(j10, false, z);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final /* synthetic */ void setListener(RendererCapabilities.Listener listener) {
        t0.b(this, listener);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void setPlaybackSpeed(float f10, float f11) {
        s0.d(this, f10, f11);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setTimeline(Timeline timeline) {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void start() throws ExoPlaybackException {
        ac.b.s(this.state == 1);
        this.state = 2;
        onStarted();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void stop() {
        ac.b.s(this.state == 2);
        this.state = 1;
        onStopped();
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) throws ExoPlaybackException {
        return t0.c(0);
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 0;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ boolean supportsResetPositionWithoutKeyFrameReset(long j10) {
        return s0.e(this, j10);
    }
}
