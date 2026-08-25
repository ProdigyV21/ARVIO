package androidx.media3.exoplayer.source;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import j$.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class MaskingMediaSource extends WrappingMediaSource {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();
        private final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        private MaskingTimeline(Timeline timeline, Object obj, Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, Object obj, Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            Object obj2;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(obj) && (obj2 = this.replacedInternalPeriodUid) != null) {
                obj = obj2;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i10, period, z);
            if (Objects.equals(period.uid, this.replacedInternalPeriodUid) && z) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i10);
            return Objects.equals(uidOfPeriod, this.replacedInternalPeriodUid) ? MASKING_EXTERNAL_PERIOD_UID : uidOfPeriod;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            this.timeline.getWindow(i10, window, j10);
            if (Objects.equals(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }
    }

    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            return obj == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            period.set(z ? 0 : null, z ? MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID : null, 0, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, false, true, null, 0L, C.TIME_UNSET, 0, 0, 0L);
            window.isPlaceholder = true;
            return window;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public MaskingMediaSource(MediaSource mediaSource, boolean z) {
        super(mediaSource);
        this.useLazyPreparation = z && mediaSource.isSingleWindow();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource.getInitialTimeline();
        if (initialTimeline == null) {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource.getMediaItem());
        } else {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, null, null);
            this.hasRealTimeline = true;
        }
    }

    private Object getExternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !this.timeline.replacedInternalPeriodUid.equals(obj)) ? obj : MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    private Object getInternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !obj.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) ? obj : this.timeline.replacedInternalPeriodUid;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private boolean setPreparePositionOverrideToUnpreparedMaskingPeriod(long j10) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(maskingMediaPeriod.id.periodUid);
        if (indexOfPeriod == -1) {
            return false;
        }
        long j11 = this.timeline.getPeriod(indexOfPeriod, this.period).durationUs;
        if (j11 != C.TIME_UNSET && j10 >= j11) {
            j10 = Math.max(0L, j11 - 1);
        }
        maskingMediaPeriod.overridePreparePositionUs(j10);
        return true;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onChildSourceInfoRefreshed(androidx.media3.common.Timeline r15) {
        /*
            r14 = this;
            boolean r0 = r14.isPrepared
            if (r0 == 0) goto L19
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            r14.timeline = r15
            androidx.media3.exoplayer.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto Lb1
            long r0 = r15.getPreparePositionOverrideUs()
            r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r0)
            goto Lb1
        L19:
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L36
            boolean r0 = r14.hasRealTimeline
            if (r0 == 0) goto L2a
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            goto L32
        L2a:
            java.lang.Object r0 = androidx.media3.common.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r1 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r0, r1)
        L32:
            r14.timeline = r15
            goto Lb1
        L36:
            androidx.media3.common.Timeline$Window r0 = r14.window
            r1 = 0
            r15.getWindow(r1, r0)
            androidx.media3.common.Timeline$Window r0 = r14.window
            long r2 = r0.getDefaultPositionUs()
            androidx.media3.common.Timeline$Window r0 = r14.window
            java.lang.Object r0 = r0.uid
            androidx.media3.exoplayer.source.MaskingMediaPeriod r4 = r14.unpreparedMaskingMediaPeriod
            if (r4 == 0) goto L74
            long r4 = r4.getPreparePositionUs()
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r6 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaPeriod r7 = r14.unpreparedMaskingMediaPeriod
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r7 = r7.id
            java.lang.Object r7 = r7.periodUid
            androidx.media3.common.Timeline$Period r8 = r14.period
            r6.getPeriodByUid(r7, r8)
            androidx.media3.common.Timeline$Period r6 = r14.period
            long r6 = r6.getPositionInWindowUs()
            long r6 = r6 + r4
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r4 = r14.timeline
            androidx.media3.common.Timeline$Window r5 = r14.window
            androidx.media3.common.Timeline$Window r1 = r4.getWindow(r1, r5)
            long r4 = r1.getDefaultPositionUs()
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L74
            r12 = r6
            goto L75
        L74:
            r12 = r2
        L75:
            androidx.media3.common.Timeline$Window r9 = r14.window
            androidx.media3.common.Timeline$Period r10 = r14.period
            r11 = 0
            r8 = r15
            android.util.Pair r15 = r8.getPeriodPositionUs(r9, r10, r11, r12)
            java.lang.Object r1 = r15.first
            java.lang.Object r15 = r15.second
            java.lang.Long r15 = (java.lang.Long) r15
            long r2 = r15.longValue()
            boolean r15 = r14.hasRealTimeline
            if (r15 == 0) goto L94
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r15.cloneWithUpdatedTimeline(r8)
            goto L98
        L94:
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r8, r0, r1)
        L98:
            r14.timeline = r15
            androidx.media3.exoplayer.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto Lb1
            boolean r0 = r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r2)
            if (r0 == 0) goto Lb1
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r15 = r15.id
            java.lang.Object r0 = r15.periodUid
            java.lang.Object r0 = r14.getInternalPeriodUid(r0)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r15 = r15.copyWithPeriodUid(r0)
            goto Lb2
        Lb1:
            r15 = 0
        Lb2:
            r0 = 1
            r14.hasRealTimeline = r0
            r14.isPrepared = r0
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            r14.refreshSourceInfo(r0)
            if (r15 == 0) goto Lc6
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r14.unpreparedMaskingMediaPeriod
            r0.getClass()
            r0.createPeriod(r15)
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.MaskingMediaSource.onChildSourceInfoRefreshed(androidx.media3.common.Timeline):void");
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void prepareSourceInternal() {
        if (this.useLazyPreparation) {
            return;
        }
        this.hasStartedPreparing = true;
        prepareChildSource();
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        if (this.hasRealTimeline) {
            this.timeline = this.timeline.cloneWithUpdatedTimeline(new TimelineWithUpdatedMediaItem(this.timeline.timeline, mediaItem));
        } else {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaItem);
        }
        this.mediaSource.updateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j10);
        maskingMediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
            return maskingMediaPeriod;
        }
        this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
        if (!this.hasStartedPreparing) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
        return maskingMediaPeriod;
    }
}
