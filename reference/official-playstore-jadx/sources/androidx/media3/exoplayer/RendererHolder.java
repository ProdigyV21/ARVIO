package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import j$.util.Objects;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class RendererHolder {
    static final int RENDERER_PREWARMING_STATE_NOT_PREWARMING_USING_PRIMARY = 0;
    static final int RENDERER_PREWARMING_STATE_NOT_PREWARMING_USING_SECONDARY = 1;
    static final int RENDERER_PREWARMING_STATE_PREWARMING_PRIMARY = 2;
    static final int RENDERER_PREWARMING_STATE_TRANSITIONING_TO_PRIMARY = 4;
    static final int RENDERER_PREWARMING_STATE_TRANSITIONING_TO_SECONDARY = 3;
    static final int REPLACE_STREAMS_DISABLE_RENDERERS_COMPLETED = 1;
    static final int REPLACE_STREAMS_DISABLE_RENDERERS_DISABLE_OFFLOAD_SCHEDULING = 2;
    private final int index;
    private final Renderer primaryRenderer;
    private final Renderer secondaryRenderer;
    private int prewarmingState = 0;
    private boolean primaryRequiresReset = false;
    private boolean secondaryRequiresReset = false;

    public RendererHolder(Renderer renderer, Renderer renderer2, int i10) {
        this.primaryRenderer = renderer;
        this.index = i10;
        this.secondaryRenderer = renderer2;
    }

    private void disableRenderer(Renderer renderer, DefaultMediaClock defaultMediaClock) {
        ac.b.s(this.primaryRenderer == renderer || this.secondaryRenderer == renderer);
        if (isRendererEnabled(renderer)) {
            defaultMediaClock.onRendererDisabled(renderer);
            ensureStopped(renderer);
            renderer.disable();
        }
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private static Format[] getFormats(ExoTrackSelection exoTrackSelection) {
        int length = exoTrackSelection != null ? exoTrackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i10 = 0; i10 < length; i10++) {
            exoTrackSelection.getClass();
            formatArr[i10] = exoTrackSelection.getFormat(i10);
        }
        return formatArr;
    }

    private Renderer getRendererReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder != null && mediaPeriodHolder.sampleStreams[this.index] != null) {
            if (this.primaryRenderer.getStream() == mediaPeriodHolder.sampleStreams[this.index]) {
                return this.primaryRenderer;
            }
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null && renderer.getStream() == mediaPeriodHolder.sampleStreams[this.index]) {
                return this.secondaryRenderer;
            }
        }
        return null;
    }

    private boolean hasFinishedReadingFromPeriodInternal(MediaPeriodHolder mediaPeriodHolder, Renderer renderer) {
        if (renderer == null) {
            return true;
        }
        SampleStream sampleStream = mediaPeriodHolder.sampleStreams[this.index];
        if (renderer.getStream() == null || (renderer.getStream() == sampleStream && (sampleStream == null || renderer.hasReadStreamToEnd() || hasReachedServerSideInsertedAdsTransition(renderer, mediaPeriodHolder)))) {
            return true;
        }
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        return next != null && next.sampleStreams[this.index] == renderer.getStream();
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder mediaPeriodHolder) {
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        if (mediaPeriodHolder.info.isFollowedByTransitionToSameStream && next != null && next.prepared) {
            return (renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= next.getStartPositionRendererTime();
        }
        return false;
    }

    private boolean isPrimaryRendererPrewarming() {
        int i10 = this.prewarmingState;
        return i10 == 2 || i10 == 4;
    }

    private boolean isSecondaryRendererPrewarming() {
        return this.prewarmingState == 3;
    }

    private void maybeDisableOrResetPositionInternal(Renderer renderer, SampleStream sampleStream, DefaultMediaClock defaultMediaClock, long j10, boolean z) throws ExoPlaybackException {
        if (isRendererEnabled(renderer)) {
            if (sampleStream != renderer.getStream()) {
                disableRenderer(renderer, defaultMediaClock);
            } else if (z) {
                renderer.resetPosition(j10, true);
            }
        }
    }

    private void maybeResetRenderer(boolean z) {
        if (z) {
            if (this.primaryRequiresReset) {
                this.primaryRenderer.reset();
                this.primaryRequiresReset = false;
                return;
            }
            return;
        }
        if (this.secondaryRequiresReset) {
            Renderer renderer = this.secondaryRenderer;
            renderer.getClass();
            renderer.reset();
            this.secondaryRequiresReset = false;
        }
    }

    private int replaceStreamsOrDisableRendererForTransitionInternal(Renderer renderer, MediaPeriodHolder mediaPeriodHolder, TrackSelectorResult trackSelectorResult, DefaultMediaClock defaultMediaClock) throws ExoPlaybackException {
        if (renderer == null || !isRendererEnabled(renderer) || ((renderer == this.primaryRenderer && isPrimaryRendererPrewarming()) || (renderer == this.secondaryRenderer && isSecondaryRendererPrewarming()))) {
            return 1;
        }
        SampleStream stream = renderer.getStream();
        SampleStream[] sampleStreamArr = mediaPeriodHolder.sampleStreams;
        int i10 = this.index;
        boolean z = stream != sampleStreamArr[i10];
        boolean zIsRendererEnabled = trackSelectorResult.isRendererEnabled(i10);
        if (zIsRendererEnabled && !z) {
            return 1;
        }
        if (!renderer.isCurrentStreamFinal()) {
            Format[] formats = getFormats(trackSelectorResult.selections[this.index]);
            SampleStream sampleStream = mediaPeriodHolder.sampleStreams[this.index];
            sampleStream.getClass();
            renderer.replaceStream(formats, sampleStream, mediaPeriodHolder.getStartPositionRendererTime(), mediaPeriodHolder.getRendererOffset(), mediaPeriodHolder.info.id);
            return 3;
        }
        if (!renderer.isEnded()) {
            return 0;
        }
        disableRenderer(renderer, defaultMediaClock);
        if (!zIsRendererEnabled || isPrewarming()) {
            maybeResetRenderer(renderer == this.primaryRenderer);
        }
        return 1;
    }

    private void setCurrentStreamFinalInternal(Renderer renderer, long j10) {
        renderer.setCurrentStreamFinal();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).setFinalStreamEndPositionUs(j10);
        }
    }

    private void transferResources(boolean z) throws ExoPlaybackException {
        if (z) {
            Renderer renderer = this.secondaryRenderer;
            renderer.getClass();
            renderer.handleMessage(17, this.primaryRenderer);
        } else {
            Renderer renderer2 = this.primaryRenderer;
            Renderer renderer3 = this.secondaryRenderer;
            renderer3.getClass();
            renderer2.handleMessage(17, renderer3);
        }
    }

    public boolean allowsPlayback(MediaPeriodHolder mediaPeriodHolder) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        return rendererReadingFromPeriod == null || rendererReadingFromPeriod.hasReadStreamToEnd() || rendererReadingFromPeriod.isReady() || rendererReadingFromPeriod.isEnded();
    }

    public void disable(DefaultMediaClock defaultMediaClock) throws ExoPlaybackException {
        disableRenderer(this.primaryRenderer, defaultMediaClock);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            boolean z = isRendererEnabled(renderer) && this.prewarmingState != 3;
            disableRenderer(this.secondaryRenderer, defaultMediaClock);
            maybeResetRenderer(false);
            if (z) {
                transferResources(true);
            }
        }
        this.prewarmingState = 0;
    }

    public void disablePrewarming(DefaultMediaClock defaultMediaClock) {
        Renderer renderer;
        if (isPrewarming()) {
            int i10 = this.prewarmingState;
            boolean z = i10 == 4 || i10 == 2;
            int i11 = i10 != 4 ? 0 : 1;
            if (z) {
                renderer = this.primaryRenderer;
            } else {
                renderer = this.secondaryRenderer;
                renderer.getClass();
            }
            disableRenderer(renderer, defaultMediaClock);
            maybeResetRenderer(z);
            this.prewarmingState = i11;
        }
    }

    public void enable(RendererConfiguration rendererConfiguration, ExoTrackSelection exoTrackSelection, SampleStream sampleStream, long j10, boolean z, boolean z5, long j11, long j12, MediaSource.MediaPeriodId mediaPeriodId, DefaultMediaClock defaultMediaClock) throws ExoPlaybackException {
        Format[] formats = getFormats(exoTrackSelection);
        int i10 = this.prewarmingState;
        if (i10 == 0 || i10 == 2 || i10 == 4) {
            this.primaryRequiresReset = true;
            this.primaryRenderer.enable(rendererConfiguration, formats, sampleStream, j10, z, z5, j11, j12, mediaPeriodId);
            defaultMediaClock.onRendererEnabled(this.primaryRenderer);
        } else {
            this.secondaryRequiresReset = true;
            Renderer renderer = this.secondaryRenderer;
            renderer.getClass();
            renderer.enable(rendererConfiguration, formats, sampleStream, j10, z, z5, j11, j12, mediaPeriodId);
            defaultMediaClock.onRendererEnabled(this.secondaryRenderer);
        }
    }

    public void enableMayRenderStartOfStream() {
        if (isRendererEnabled(this.primaryRenderer)) {
            this.primaryRenderer.enableMayRenderStartOfStream();
            return;
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            return;
        }
        this.secondaryRenderer.enableMayRenderStartOfStream();
    }

    public int getEnabledRendererCount() {
        boolean zIsRendererEnabled = isRendererEnabled(this.primaryRenderer);
        Renderer renderer = this.secondaryRenderer;
        return (zIsRendererEnabled ? 1 : 0) + ((renderer == null || !isRendererEnabled(renderer)) ? 0 : 1);
    }

    public long getMinDurationToProgressUs(long j10, long j11) {
        long durationToProgressUs = isRendererEnabled(this.primaryRenderer) ? this.primaryRenderer.getDurationToProgressUs(j10, j11) : Long.MAX_VALUE;
        Renderer renderer = this.secondaryRenderer;
        return (renderer == null || !isRendererEnabled(renderer)) ? durationToProgressUs : Math.min(durationToProgressUs, this.secondaryRenderer.getDurationToProgressUs(j10, j11));
    }

    public long getReadingPositionUs(MediaPeriodHolder mediaPeriodHolder) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        Objects.requireNonNull(rendererReadingFromPeriod);
        return rendererReadingFromPeriod.getReadingPositionUs();
    }

    public int getTrackType() {
        return this.primaryRenderer.getTrackType();
    }

    public void handleMessage(int i10, Object obj, MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        rendererReadingFromPeriod.getClass();
        rendererReadingFromPeriod.handleMessage(i10, obj);
    }

    public boolean hasFinishedReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        return hasFinishedReadingFromPeriodInternal(mediaPeriodHolder, this.primaryRenderer) && hasFinishedReadingFromPeriodInternal(mediaPeriodHolder, this.secondaryRenderer);
    }

    public boolean hasReadPeriodToEnd(MediaPeriodHolder mediaPeriodHolder) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        rendererReadingFromPeriod.getClass();
        return rendererReadingFromPeriod.hasReadStreamToEnd();
    }

    public boolean hasSecondary() {
        return this.secondaryRenderer != null;
    }

    public boolean isEnded() {
        boolean zIsEnded = isRendererEnabled(this.primaryRenderer) ? this.primaryRenderer.isEnded() : true;
        Renderer renderer = this.secondaryRenderer;
        return (renderer == null || !isRendererEnabled(renderer)) ? zIsEnded : zIsEnded & this.secondaryRenderer.isEnded();
    }

    public boolean isPrewarming() {
        return isPrimaryRendererPrewarming() || isSecondaryRendererPrewarming();
    }

    public boolean isPrewarmingPeriod(MediaPeriodHolder mediaPeriodHolder) {
        return (isPrimaryRendererPrewarming() && getRendererReadingFromPeriod(mediaPeriodHolder) == this.primaryRenderer) || (isSecondaryRendererPrewarming() && getRendererReadingFromPeriod(mediaPeriodHolder) == this.secondaryRenderer);
    }

    public boolean isReadingFromPeriod(MediaPeriodHolder mediaPeriodHolder) {
        return getRendererReadingFromPeriod(mediaPeriodHolder) != null;
    }

    public boolean isRendererEnabled() {
        int i10 = this.prewarmingState;
        if (i10 == 0 || i10 == 2 || i10 == 4) {
            return isRendererEnabled(this.primaryRenderer);
        }
        Renderer renderer = this.secondaryRenderer;
        renderer.getClass();
        return isRendererEnabled(renderer);
    }

    public void maybeDisableOrResetPosition(SampleStream sampleStream, DefaultMediaClock defaultMediaClock, long j10, boolean z) throws ExoPlaybackException {
        maybeDisableOrResetPositionInternal(this.primaryRenderer, sampleStream, defaultMediaClock, j10, z);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            maybeDisableOrResetPositionInternal(renderer, sampleStream, defaultMediaClock, j10, z);
        }
    }

    public void maybeHandlePrewarmingTransition() throws ExoPlaybackException {
        int i10 = this.prewarmingState;
        if (i10 == 3 || i10 == 4) {
            transferResources(i10 == 4);
            this.prewarmingState = this.prewarmingState != 4 ? 1 : 0;
        } else if (i10 == 2) {
            this.prewarmingState = 0;
        }
    }

    public void maybeSetOldStreamToFinal(TrackSelectorResult trackSelectorResult, TrackSelectorResult trackSelectorResult2, long j10) {
        Renderer renderer;
        int i10;
        boolean zIsRendererEnabled = trackSelectorResult.isRendererEnabled(this.index);
        boolean zIsRendererEnabled2 = trackSelectorResult2.isRendererEnabled(this.index);
        if (this.secondaryRenderer == null || (i10 = this.prewarmingState) == 3 || (i10 == 0 && isRendererEnabled(this.primaryRenderer))) {
            renderer = this.primaryRenderer;
        } else {
            renderer = this.secondaryRenderer;
            renderer.getClass();
        }
        if (!zIsRendererEnabled || renderer.isCurrentStreamFinal()) {
            return;
        }
        boolean z = getTrackType() == -2;
        RendererConfiguration[] rendererConfigurationArr = trackSelectorResult.rendererConfigurations;
        int i11 = this.index;
        RendererConfiguration rendererConfiguration = rendererConfigurationArr[i11];
        RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i11];
        if (!zIsRendererEnabled2 || !Objects.equals(rendererConfiguration2, rendererConfiguration) || z || isPrewarming()) {
            setCurrentStreamFinalInternal(renderer, j10);
        }
    }

    public void maybeThrowStreamError(MediaPeriodHolder mediaPeriodHolder) throws IOException {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        rendererReadingFromPeriod.getClass();
        rendererReadingFromPeriod.maybeThrowStreamError();
    }

    public void release() {
        this.primaryRenderer.release();
        this.primaryRequiresReset = false;
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.release();
            this.secondaryRequiresReset = false;
        }
    }

    public void render(long j10, long j11) throws ExoPlaybackException {
        if (isRendererEnabled(this.primaryRenderer)) {
            this.primaryRenderer.render(j10, j11);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            return;
        }
        this.secondaryRenderer.render(j10, j11);
    }

    public int replaceStreamsOrDisableRendererForTransition(MediaPeriodHolder mediaPeriodHolder, TrackSelectorResult trackSelectorResult, DefaultMediaClock defaultMediaClock) throws ExoPlaybackException {
        int iReplaceStreamsOrDisableRendererForTransitionInternal = replaceStreamsOrDisableRendererForTransitionInternal(this.primaryRenderer, mediaPeriodHolder, trackSelectorResult, defaultMediaClock);
        return iReplaceStreamsOrDisableRendererForTransitionInternal == 1 ? replaceStreamsOrDisableRendererForTransitionInternal(this.secondaryRenderer, mediaPeriodHolder, trackSelectorResult, defaultMediaClock) : iReplaceStreamsOrDisableRendererForTransitionInternal;
    }

    public void reset() {
        if (!isRendererEnabled(this.primaryRenderer)) {
            maybeResetRenderer(true);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || isRendererEnabled(renderer)) {
            return;
        }
        maybeResetRenderer(false);
    }

    public void resetPosition(MediaPeriodHolder mediaPeriodHolder, long j10, boolean z) throws ExoPlaybackException {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        if (rendererReadingFromPeriod != null) {
            rendererReadingFromPeriod.resetPosition(j10, z);
        }
    }

    public void setAllNonPrewarmingRendererStreamsFinal(long j10) {
        int i10;
        if (isRendererEnabled(this.primaryRenderer) && (i10 = this.prewarmingState) != 4 && i10 != 2) {
            setCurrentStreamFinalInternal(this.primaryRenderer, j10);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer) || this.prewarmingState == 3) {
            return;
        }
        setCurrentStreamFinalInternal(this.secondaryRenderer, j10);
    }

    public void setCurrentStreamFinal(MediaPeriodHolder mediaPeriodHolder, long j10) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        rendererReadingFromPeriod.getClass();
        setCurrentStreamFinalInternal(rendererReadingFromPeriod, j10);
    }

    public void setPlaybackSpeed(float f10, float f11) throws ExoPlaybackException {
        this.primaryRenderer.setPlaybackSpeed(f10, f11);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.setPlaybackSpeed(f10, f11);
        }
    }

    public void setScrubbingMode(ScrubbingModeParameters scrubbingModeParameters) throws ExoPlaybackException {
        this.primaryRenderer.handleMessage(18, scrubbingModeParameters);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.handleMessage(18, scrubbingModeParameters);
        }
    }

    public void setTimeline(Timeline timeline) {
        this.primaryRenderer.setTimeline(timeline);
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.setTimeline(timeline);
        }
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) throws ExoPlaybackException {
        if (getTrackType() == 2 || getTrackType() == 4) {
            this.primaryRenderer.handleMessage(7, videoFrameMetadataListener);
            Renderer renderer = this.secondaryRenderer;
            if (renderer != null) {
                renderer.handleMessage(7, videoFrameMetadataListener);
            }
        }
    }

    public void setVideoOutput(Object obj) throws ExoPlaybackException {
        if (getTrackType() != 2) {
            return;
        }
        int i10 = this.prewarmingState;
        if (i10 != 4 && i10 != 1) {
            this.primaryRenderer.handleMessage(1, obj);
            return;
        }
        Renderer renderer = this.secondaryRenderer;
        renderer.getClass();
        renderer.handleMessage(1, obj);
    }

    public void setVolume(float f10) throws ExoPlaybackException {
        if (getTrackType() != 1) {
            return;
        }
        this.primaryRenderer.handleMessage(2, Float.valueOf(f10));
        Renderer renderer = this.secondaryRenderer;
        if (renderer != null) {
            renderer.handleMessage(2, Float.valueOf(f10));
        }
    }

    public void start() throws ExoPlaybackException {
        if (this.primaryRenderer.getState() == 1 && this.prewarmingState != 4) {
            this.primaryRenderer.start();
            return;
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || renderer.getState() != 1 || this.prewarmingState == 3) {
            return;
        }
        this.secondaryRenderer.start();
    }

    public void startPrewarming() {
        int i10;
        ac.b.s(!isPrewarming());
        if (isRendererEnabled(this.primaryRenderer)) {
            i10 = 3;
        } else {
            Renderer renderer = this.secondaryRenderer;
            i10 = (renderer == null || !isRendererEnabled(renderer)) ? 2 : 4;
        }
        this.prewarmingState = i10;
    }

    public void stop() {
        if (isRendererEnabled(this.primaryRenderer)) {
            ensureStopped(this.primaryRenderer);
        }
        Renderer renderer = this.secondaryRenderer;
        if (renderer == null || !isRendererEnabled(renderer)) {
            return;
        }
        ensureStopped(this.secondaryRenderer);
    }

    public boolean supportsResetPositionWithoutKeyFrameReset(MediaPeriodHolder mediaPeriodHolder, long j10) {
        Renderer rendererReadingFromPeriod = getRendererReadingFromPeriod(mediaPeriodHolder);
        return rendererReadingFromPeriod != null && rendererReadingFromPeriod.supportsResetPositionWithoutKeyFrameReset(j10);
    }

    private static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }
}
