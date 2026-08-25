package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class ClippingMediaSource extends WrappingMediaSource {
    private final boolean allowDynamicClippingUpdates;
    private final boolean allowUnseekableMedia;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    public static final class Builder {
        private boolean allowDynamicClippingUpdates;
        private boolean allowUnseekableMedia;
        private boolean buildCalled;
        private boolean enableInitialDiscontinuity;
        private long endPositionUs;
        private final MediaSource mediaSource;
        private boolean relativeToDefaultPosition;
        private long startPositionUs;

        public Builder(MediaSource mediaSource) {
            mediaSource.getClass();
            this.mediaSource = mediaSource;
            this.enableInitialDiscontinuity = true;
            this.endPositionUs = Long.MIN_VALUE;
        }

        public ClippingMediaSource build() {
            this.buildCalled = true;
            return new ClippingMediaSource(this);
        }

        public Builder setAllowDynamicClippingUpdates(boolean z) {
            ac.b.s(!this.buildCalled);
            this.allowDynamicClippingUpdates = z;
            return this;
        }

        public Builder setAllowUnseekableMedia(boolean z) {
            ac.b.s(!this.buildCalled);
            this.allowUnseekableMedia = z;
            return this;
        }

        public Builder setEnableInitialDiscontinuity(boolean z) {
            ac.b.s(!this.buildCalled);
            this.enableInitialDiscontinuity = z;
            return this;
        }

        public Builder setEndPositionMs(long j10) {
            return setEndPositionUs(Util.msToUs(j10));
        }

        public Builder setEndPositionUs(long j10) {
            ac.b.s(!this.buildCalled);
            this.endPositionUs = j10;
            return this;
        }

        public Builder setRelativeToDefaultPosition(boolean z) {
            ac.b.s(!this.buildCalled);
            this.relativeToDefaultPosition = z;
            return this;
        }

        public Builder setStartPositionMs(long j10) {
            return setStartPositionUs(Util.msToUs(j10));
        }

        public Builder setStartPositionUs(long j10) {
            ac.b.j(j10 >= 0);
            ac.b.s(!this.buildCalled);
            this.startPositionUs = j10;
            return this;
        }
    }

    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j10, long j11, boolean z) throws IllegalClippingException {
            super(timeline);
            if (j11 != Long.MIN_VALUE && j11 < j10) {
                throw new IllegalClippingException(2, j10, j11);
            }
            boolean z5 = false;
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            }
            Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
            long jMax = Math.max(0L, j10);
            if (!z && !window.isPlaceholder && jMax != 0 && !window.isSeekable) {
                throw new IllegalClippingException(1);
            }
            long jMax2 = j11 == Long.MIN_VALUE ? window.durationUs : Math.max(0L, j11);
            long j12 = window.durationUs;
            if (j12 != C.TIME_UNSET) {
                jMax2 = jMax2 > j12 ? j12 : jMax2;
                if (jMax > jMax2) {
                    jMax = jMax2;
                }
            }
            this.startUs = jMax;
            this.endUs = jMax2;
            this.durationUs = jMax2 == C.TIME_UNSET ? -9223372036854775807L : jMax2 - jMax;
            if (window.isDynamic && (jMax2 == C.TIME_UNSET || (j12 != C.TIME_UNSET && jMax2 == j12))) {
                z5 = true;
            }
            this.isDynamic = z5;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j10 = this.durationUs;
            long j11 = C.TIME_UNSET;
            if (j10 != C.TIME_UNSET) {
                j11 = j10 - positionInWindowUs;
            }
            return period.set(period.id, period.uid, 0, j11, positionInWindowUs);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            this.timeline.getWindow(0, window, 0L);
            long j11 = window.positionInFirstPeriodUs;
            long j12 = this.startUs;
            window.positionInFirstPeriodUs = j11 + j12;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            long j13 = window.defaultPositionUs;
            if (j13 != C.TIME_UNSET) {
                long jMax = Math.max(j13, j12);
                window.defaultPositionUs = jMax;
                long j14 = this.endUs;
                if (j14 != C.TIME_UNSET) {
                    jMax = Math.min(jMax, j14);
                }
                window.defaultPositionUs = jMax - this.startUs;
            }
            long jUsToMs = Util.usToMs(this.startUs);
            long j15 = window.presentationStartTimeMs;
            if (j15 != C.TIME_UNSET) {
                window.presentationStartTimeMs = j15 + jUsToMs;
            }
            long j16 = window.windowStartTimeMs;
            if (j16 != C.TIME_UNSET) {
                window.windowStartTimeMs = j16 + jUsToMs;
            }
            return window;
        }
    }

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalClippingException(int i10) {
            this(i10, C.TIME_UNSET, C.TIME_UNSET);
        }

        private static String getReasonDescription(int i10, long j10, long j11) {
            if (i10 == 0) {
                return "invalid period count";
            }
            if (i10 == 1) {
                return "not seekable to start";
            }
            if (i10 != 2) {
                return "unknown";
            }
            ac.b.s((j10 == C.TIME_UNSET || j11 == C.TIME_UNSET) ? false : true);
            return "start exceeds end. Start time: " + j10 + ", End time: " + j11;
        }

        public IllegalClippingException(int i10, long j10, long j11) {
            super("Illegal clipping: " + getReasonDescription(i10, j10, j11));
            this.reason = i10;
        }
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j10;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            j10 = this.startUs;
            long j11 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j10 += defaultPositionUs;
                j11 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j10;
            this.periodEndUs = this.endUs != Long.MIN_VALUE ? positionInFirstPeriodUs + j11 : Long.MIN_VALUE;
            int size = this.mediaPeriods.size();
            for (int i10 = 0; i10 < size; i10++) {
                this.mediaPeriods.get(i10).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j = j11;
        } else {
            j10 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j = this.periodEndUs - positionInFirstPeriodUs;
            }
        }
        try {
            ClippingTimeline clippingTimeline = new ClippingTimeline(timeline, j10, j, this.allowUnseekableMedia);
            this.clippingTimeline = clippingTimeline;
            refreshSourceInfo(clippingTimeline);
        } catch (IllegalClippingException e5) {
            this.clippingError = e5;
            for (int i11 = 0; i11 < this.mediaPeriods.size(); i11++) {
                this.mediaPeriods.get(i11).setClippingError(this.clippingError);
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return getMediaItem().clippingConfiguration.equals(mediaItem.clippingConfiguration) && this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j10), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException != null) {
            throw illegalClippingException;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void onChildSourceInfoRefreshed(Timeline timeline) {
        if (this.clippingError != null) {
            return;
        }
        refreshClippedTimeline(timeline);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ac.b.s(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (!this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            return;
        }
        ClippingTimeline clippingTimeline = this.clippingTimeline;
        clippingTimeline.getClass();
        refreshClippedTimeline(clippingTimeline.timeline);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    @Deprecated
    public ClippingMediaSource(MediaSource mediaSource, long j10, long j11) {
        this(new Builder(mediaSource).setStartPositionUs(j10).setEndPositionUs(j11));
    }

    @Deprecated
    public ClippingMediaSource(MediaSource mediaSource, long j10) {
        this(new Builder(mediaSource).setEndPositionUs(j10).setRelativeToDefaultPosition(true));
    }

    @Deprecated
    public ClippingMediaSource(MediaSource mediaSource, long j10, long j11, boolean z, boolean z5, boolean z10) {
        this(new Builder(mediaSource).setStartPositionUs(j10).setEndPositionUs(j11).setEnableInitialDiscontinuity(z).setAllowDynamicClippingUpdates(z5).setRelativeToDefaultPosition(z10));
    }

    private ClippingMediaSource(Builder builder) {
        super(builder.mediaSource);
        this.startUs = builder.startPositionUs;
        this.endUs = builder.endPositionUs;
        this.enableInitialDiscontinuity = builder.enableInitialDiscontinuity;
        this.allowDynamicClippingUpdates = builder.allowDynamicClippingUpdates;
        this.relativeToDefaultPosition = builder.relativeToDefaultPosition;
        this.allowUnseekableMedia = builder.allowUnseekableMedia;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }
}
