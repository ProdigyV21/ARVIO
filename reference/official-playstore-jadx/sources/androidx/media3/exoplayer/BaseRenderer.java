package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import j$.util.Objects;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private Clock clock;
    private RendererConfiguration configuration;
    private int index;
    private long lastResetPositionUs;
    private MediaSource.MediaPeriodId mediaPeriodId;
    private PlayerId playerId;
    private RendererCapabilities.Listener rendererCapabilitiesListener;
    private int state;
    private SampleStream stream;
    private Format[] streamFormats;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private boolean throwRendererExceptionIsExecuting;
    private final int trackType;
    private final Object lock = new Object();
    private final FormatHolder formatHolder = new FormatHolder();
    private long readingPositionUs = Long.MIN_VALUE;
    private Timeline timeline = Timeline.EMPTY;

    public BaseRenderer(int i10) {
        this.trackType = i10;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final void clearListener() {
        synchronized (this.lock) {
            this.rendererCapabilitiesListener = null;
        }
    }

    public final ExoPlaybackException createRendererException(Throwable th, Format format, int i10) {
        return createRendererException(th, format, false, i10);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void disable() {
        ac.b.s(this.state == 1);
        this.formatHolder.clear();
        this.state = 0;
        this.stream = null;
        this.streamFormats = null;
        this.streamIsFinal = false;
        onDisabled();
        this.mediaPeriodId = null;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j10, boolean z, boolean z5, long j11, long j12, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        ac.b.s(this.state == 0);
        this.configuration = rendererConfiguration;
        this.mediaPeriodId = mediaPeriodId;
        this.state = 1;
        onEnabled(z, z5);
        replaceStream(formatArr, sampleStream, j11, j12, mediaPeriodId);
        resetPosition(j11, z, true);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ void enableMayRenderStartOfStream() {
        s0.a(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public final Clock getClock() {
        Clock clock = this.clock;
        clock.getClass();
        return clock;
    }

    public final RendererConfiguration getConfiguration() {
        RendererConfiguration rendererConfiguration = this.configuration;
        rendererConfiguration.getClass();
        return rendererConfiguration;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ long getDurationToProgressUs(long j10, long j11) {
        return s0.b(this, j10, j11);
    }

    public final FormatHolder getFormatHolder() {
        this.formatHolder.clear();
        return this.formatHolder;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getLastResetPositionUs() {
        return this.lastResetPositionUs;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return null;
    }

    public final MediaSource.MediaPeriodId getMediaPeriodId() {
        return this.mediaPeriodId;
    }

    public final PlayerId getPlayerId() {
        PlayerId playerId = this.playerId;
        playerId.getClass();
        return playerId;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final long getReadingPositionUs() {
        return this.readingPositionUs;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final int getState() {
        return this.state;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final SampleStream getStream() {
        return this.stream;
    }

    public final Format[] getStreamFormats() {
        Format[] formatArr = this.streamFormats;
        formatArr.getClass();
        return formatArr;
    }

    public final long getStreamOffsetUs() {
        return this.streamOffsetUs;
    }

    public final Timeline getTimeline() {
        return this.timeline;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public final int getTrackType() {
        return this.trackType;
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i10, Object obj) throws ExoPlaybackException {
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean hasReadStreamToEnd() {
        return this.readingPositionUs == Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void init(int i10, PlayerId playerId, Clock clock) {
        this.index = i10;
        this.playerId = playerId;
        this.clock = clock;
        onInit();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final boolean isSourceReady() {
        if (hasReadStreamToEnd()) {
            return this.streamIsFinal;
        }
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        return sampleStream.isReady();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void maybeThrowStreamError() throws IOException {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        sampleStream.maybeThrowError();
    }

    public void onDisabled() {
    }

    public void onEnabled(boolean z, boolean z5) throws ExoPlaybackException {
    }

    public void onInit() {
    }

    public void onPositionReset(long j10, boolean z, boolean z5) throws ExoPlaybackException {
    }

    public void onRelease() {
    }

    public final void onRendererCapabilitiesChanged() {
        RendererCapabilities.Listener listener;
        synchronized (this.lock) {
            listener = this.rendererCapabilitiesListener;
        }
        if (listener != null) {
            listener.onRendererCapabilitiesChanged(this);
        }
    }

    public void onReset() {
    }

    public void onStarted() throws ExoPlaybackException {
    }

    public void onStopped() {
    }

    public void onStreamChanged(Format[] formatArr, long j10, long j11, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
    }

    public void onTimelineChanged(Timeline timeline) {
    }

    public final int readSource(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i10) {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        int data = sampleStream.readData(formatHolder, decoderInputBuffer, i10);
        if (data != -4) {
            if (data == -5) {
                Format format = formatHolder.format;
                format.getClass();
                if (format.subsampleOffsetUs != Long.MAX_VALUE) {
                    formatHolder.format = format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.streamOffsetUs).build();
                }
            }
            return data;
        }
        if (decoderInputBuffer.isEndOfStream()) {
            this.readingPositionUs = Long.MIN_VALUE;
            return this.streamIsFinal ? -4 : -3;
        }
        long j10 = decoderInputBuffer.timeUs + this.streamOffsetUs;
        decoderInputBuffer.timeUs = j10;
        this.readingPositionUs = Math.max(this.readingPositionUs, j10);
        return data;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void release() {
        ac.b.s(this.state == 0);
        onRelease();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j10, long j11, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        ac.b.s(!this.streamIsFinal);
        this.stream = sampleStream;
        this.mediaPeriodId = mediaPeriodId;
        if (this.readingPositionUs == Long.MIN_VALUE) {
            this.readingPositionUs = j10;
        }
        this.streamFormats = formatArr;
        this.streamOffsetUs = j11;
        onStreamChanged(formatArr, j10, j11, mediaPeriodId);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void reset() {
        ac.b.s(this.state == 0);
        this.formatHolder.clear();
        onReset();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void resetPosition(long j10, boolean z) throws ExoPlaybackException {
        resetPosition(j10, false, z);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final void setListener(RendererCapabilities.Listener listener) {
        synchronized (this.lock) {
            this.rendererCapabilitiesListener = listener;
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ void setPlaybackSpeed(float f10, float f11) {
        s0.d(this, f10, f11);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final void setTimeline(Timeline timeline) {
        if (Objects.equals(this.timeline, timeline)) {
            return;
        }
        this.timeline = timeline;
        onTimelineChanged(timeline);
    }

    public int skipSource(long j10) {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        return sampleStream.skipData(j10 - this.streamOffsetUs);
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
    public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 0;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ boolean supportsResetPositionWithoutKeyFrameReset(long j10) {
        return s0.e(this, j10);
    }

    private void resetPosition(long j10, boolean z, boolean z5) throws ExoPlaybackException {
        this.streamIsFinal = false;
        this.lastResetPositionUs = j10;
        this.readingPositionUs = j10;
        if (!z5) {
            z5 = skipSource(j10) != 0;
        }
        onPositionReset(j10, z, z5);
    }

    public final ExoPlaybackException createRendererException(Throwable th, Format format, boolean z, int i10) {
        int iK;
        if (format == null || this.throwRendererExceptionIsExecuting) {
            iK = 4;
        } else {
            this.throwRendererExceptionIsExecuting = true;
            try {
                iK = t0.k(supportsFormat(format));
            } catch (ExoPlaybackException unused) {
                iK = 4;
            } finally {
                this.throwRendererExceptionIsExecuting = false;
            }
        }
        return ExoPlaybackException.createForRenderer(th, getName(), getIndex(), format, iK, this.mediaPeriodId, z, i10);
    }
}
