package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.a0;
import com.google.common.collect.f0;
import com.google.common.collect.x2;
import com.google.common.collect.y2;
import com.google.common.collect.z2;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final int PERIOD_COUNT_UNSET = -1;
    private static final MediaItem PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("MergingMediaSource").build();
    private final boolean adjustPeriodTimeOffsets;
    private final boolean clipDurations;
    private final Map<Object, Long> clippedDurationsUs;
    private final x2<Object, ClippingMediaPeriod> clippedMediaPeriods;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final List<List<MediaPeriodAndId>> mediaPeriods;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private long[][] periodTimeOffsetsUs;
    private final Timeline[] timelines;

    public static final class ClippedTimeline extends ForwardingTimeline {
        private final long[] periodDurationsUs;
        private final long[] windowDurationsUs;

        public ClippedTimeline(Timeline timeline, Map<Object, Long> map) {
            super(timeline);
            int windowCount = timeline.getWindowCount();
            this.windowDurationsUs = new long[timeline.getWindowCount()];
            Timeline.Window window = new Timeline.Window();
            for (int i10 = 0; i10 < windowCount; i10++) {
                this.windowDurationsUs[i10] = timeline.getWindow(i10, window).durationUs;
            }
            int periodCount = timeline.getPeriodCount();
            this.periodDurationsUs = new long[periodCount];
            Timeline.Period period = new Timeline.Period();
            for (int i11 = 0; i11 < periodCount; i11++) {
                timeline.getPeriod(i11, period, true);
                Long l10 = map.get(period.uid);
                l10.getClass();
                long jLongValue = l10.longValue();
                long[] jArr = this.periodDurationsUs;
                jLongValue = jLongValue == Long.MIN_VALUE ? period.durationUs : jLongValue;
                jArr[i11] = jLongValue;
                long j10 = period.durationUs;
                if (j10 != C.TIME_UNSET) {
                    long[] jArr2 = this.windowDurationsUs;
                    int i12 = period.windowIndex;
                    jArr2[i12] = jArr2[i12] - (j10 - jLongValue);
                }
            }
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            super.getPeriod(i10, period, z);
            period.durationUs = this.periodDurationsUs[i10];
            return period;
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.media3.common.Timeline.Window getWindow(int r5, androidx.media3.common.Timeline.Window r6, long r7) {
            /*
                r4 = this;
                super.getWindow(r5, r6, r7)
                long[] r7 = r4.windowDurationsUs
                r0 = r7[r5]
                r6.durationUs = r0
                r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                int r5 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
                if (r5 == 0) goto L1e
                long r2 = r6.defaultPositionUs
                int r5 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r5 != 0) goto L19
                goto L1e
            L19:
                long r7 = java.lang.Math.min(r2, r0)
                goto L20
            L1e:
                long r7 = r6.defaultPositionUs
            L20:
                r6.defaultPositionUs = r7
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.MergingMediaSource.ClippedTimeline.getWindow(int, androidx.media3.common.Timeline$Window, long):androidx.media3.common.Timeline$Window");
        }
    }

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 0;
        public final int reason;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int i10) {
            this.reason = i10;
        }
    }

    public static final class MediaPeriodAndId {
        private final MediaPeriod mediaPeriod;
        private final MediaSource.MediaPeriodId mediaPeriodId;

        private MediaPeriodAndId(MediaSource.MediaPeriodId mediaPeriodId, MediaPeriod mediaPeriod) {
            this.mediaPeriodId = mediaPeriodId;
            this.mediaPeriod = mediaPeriod;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    private void computePeriodTimeOffsets() {
        Timeline.Period period = new Timeline.Period();
        for (int i10 = 0; i10 < this.periodCount; i10++) {
            long j10 = -this.timelines[0].getPeriod(i10, period).getPositionInWindowUs();
            int i11 = 1;
            while (true) {
                Timeline[] timelineArr = this.timelines;
                if (i11 < timelineArr.length) {
                    this.periodTimeOffsetsUs[i10][i11] = j10 - (-timelineArr[i11].getPeriod(i10, period).getPositionInWindowUs());
                    i11++;
                }
            }
        }
    }

    private void updateClippedDuration() {
        Timeline[] timelineArr;
        Timeline.Period period = new Timeline.Period();
        for (int i10 = 0; i10 < this.periodCount; i10++) {
            int i11 = 0;
            long j10 = Long.MIN_VALUE;
            while (true) {
                timelineArr = this.timelines;
                if (i11 >= timelineArr.length) {
                    break;
                }
                long durationUs = timelineArr[i11].getPeriod(i10, period).getDurationUs();
                if (durationUs != C.TIME_UNSET) {
                    long j11 = durationUs + this.periodTimeOffsetsUs[i10][i11];
                    if (j10 == Long.MIN_VALUE || j11 < j10) {
                        j10 = j11;
                    }
                }
                i11++;
            }
            Object uidOfPeriod = timelineArr[0].getUidOfPeriod(i10);
            this.clippedDurationsUs.put(uidOfPeriod, Long.valueOf(j10));
            Iterator it = this.clippedMediaPeriods.get(uidOfPeriod).iterator();
            while (it.hasNext()) {
                ((ClippingMediaPeriod) it.next()).updateClipping(0L, j10);
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        MediaSource[] mediaSourceArr = this.mediaSources;
        return mediaSourceArr.length > 0 && mediaSourceArr[0].canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        int length = this.mediaSources.length;
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[length];
        int indexOfPeriod = this.timelines[0].getIndexOfPeriod(mediaPeriodId.periodUid);
        for (int i10 = 0; i10 < length; i10++) {
            MediaSource.MediaPeriodId mediaPeriodIdCopyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(this.timelines[i10].getUidOfPeriod(indexOfPeriod));
            mediaPeriodArr[i10] = this.mediaSources[i10].createPeriod(mediaPeriodIdCopyWithPeriodUid, allocator, j10 - this.periodTimeOffsetsUs[indexOfPeriod][i10]);
            this.mediaPeriods.get(i10).add(new MediaPeriodAndId(mediaPeriodIdCopyWithPeriodUid, mediaPeriodArr[i10]));
        }
        MergingMediaPeriod mergingMediaPeriod = new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, this.periodTimeOffsetsUs[indexOfPeriod], mediaPeriodArr);
        if (!this.clipDurations) {
            return mergingMediaPeriod;
        }
        Long l10 = this.clippedDurationsUs.get(mediaPeriodId.periodUid);
        l10.getClass();
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(mergingMediaPeriod, false, 0L, l10.longValue());
        this.clippedMediaPeriods.put(mediaPeriodId.periodUid, clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        MediaSource[] mediaSourceArr = this.mediaSources;
        return mediaSourceArr.length > 0 ? mediaSourceArr[0].getMediaItem() : PLACEHOLDER_MEDIA_ITEM;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalMergeException illegalMergeException = this.mergeError;
        if (illegalMergeException != null) {
            throw illegalMergeException;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        for (int i10 = 0; i10 < this.mediaSources.length; i10++) {
            prepareChildSource(Integer.valueOf(i10), this.mediaSources[i10]);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        if (this.clipDurations) {
            ClippingMediaPeriod clippingMediaPeriod = (ClippingMediaPeriod) mediaPeriod;
            Iterator it = this.clippedMediaPeriods.entries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (((ClippingMediaPeriod) entry.getValue()).equals(clippingMediaPeriod)) {
                    this.clippedMediaPeriods.remove(entry.getKey(), entry.getValue());
                    break;
                }
            }
            mediaPeriod = clippingMediaPeriod.mediaPeriod;
        }
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        for (int i10 = 0; i10 < this.mediaSources.length; i10++) {
            List<MediaPeriodAndId> list = this.mediaPeriods.get(i10);
            MediaPeriod childPeriod = mergingMediaPeriod.getChildPeriod(i10);
            int i11 = 0;
            while (true) {
                if (i11 >= list.size()) {
                    break;
                }
                if (list.get(i11).mediaPeriod.equals(childPeriod)) {
                    list.remove(i11);
                    break;
                }
                i11++;
            }
            this.mediaSources[i10].releasePeriod(mergingMediaPeriod.getChildPeriod(i10));
        }
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Arrays.fill(this.timelines, (Object) null);
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSources[0].updateMediaItem(mediaItem);
    }

    public MergingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, false, mediaSourceArr);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        List<MediaPeriodAndId> list = this.mediaPeriods.get(num.intValue());
        for (int i10 = 0; i10 < list.size(); i10++) {
            if (list.get(i10).mediaPeriodId.equals(mediaPeriodId)) {
                return this.mediaPeriods.get(0).get(i10).mediaPeriodId;
            }
        }
        return null;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void lambda$prepareChildSource$0(Integer num, MediaSource mediaSource, Timeline timeline) {
        if (this.mergeError != null) {
            return;
        }
        if (this.periodCount == -1) {
            this.periodCount = timeline.getPeriodCount();
        } else if (timeline.getPeriodCount() != this.periodCount) {
            this.mergeError = new IllegalMergeException(0);
            return;
        }
        if (this.periodTimeOffsetsUs.length == 0) {
            this.periodTimeOffsetsUs = (long[][]) Array.newInstance((Class<?>) Long.TYPE, this.periodCount, this.timelines.length);
        }
        this.pendingTimelineSources.remove(mediaSource);
        this.timelines[num.intValue()] = timeline;
        if (this.pendingTimelineSources.isEmpty()) {
            if (this.adjustPeriodTimeOffsets) {
                computePeriodTimeOffsets();
            }
            Timeline clippedTimeline = this.timelines[0];
            if (this.clipDurations) {
                updateClippedDuration();
                clippedTimeline = new ClippedTimeline(clippedTimeline, this.clippedDurationsUs);
            }
            refreshSourceInfo(clippedTimeline);
        }
    }

    public MergingMediaSource(boolean z, boolean z5, MediaSource... mediaSourceArr) {
        this(z, z5, new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    public MergingMediaSource(boolean z, boolean z5, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, MediaSource... mediaSourceArr) {
        this.adjustPeriodTimeOffsets = z;
        this.clipDurations = z5;
        this.mediaSources = mediaSourceArr;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
        this.periodCount = -1;
        this.mediaPeriods = new ArrayList(mediaSourceArr.length);
        for (int i10 = 0; i10 < mediaSourceArr.length; i10++) {
            this.mediaPeriods.add(new ArrayList());
        }
        this.timelines = new Timeline[mediaSourceArr.length];
        this.periodTimeOffsetsUs = new long[0][];
        this.clippedDurationsUs = new HashMap();
        a0.c(8, "expectedKeys");
        a0.c(2, "expectedValuesPerKey");
        f0 f0VarC = f0.c(8);
        y2 y2Var = new y2();
        z2 z2Var = new z2(f0VarC);
        z2Var.f14146q = y2Var;
        this.clippedMediaPeriods = z2Var;
    }
}
