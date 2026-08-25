package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import j$.util.Objects;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    private static final int MSG_UPDATE_TIMELINE = 1;
    private MediaItem mediaItem;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final h1 mediaSourceHolders;
    private Handler playbackThreadHandler;
    private boolean timelineUpdateScheduled;

    public static final class Builder {
        private int index;
        private MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;
        private final e1 mediaSourceHoldersBuilder = h1.k();

        public Builder add(MediaItem mediaItem) {
            return add(mediaItem, C.TIME_UNSET);
        }

        public ConcatenatingMediaSource2 build() {
            ac.b.k(this.index > 0, "Must add at least one source to the concatenation.");
            if (this.mediaItem == null) {
                this.mediaItem = MediaItem.fromUri(Uri.EMPTY);
            }
            return new ConcatenatingMediaSource2(this.mediaItem, this.mediaSourceHoldersBuilder.f());
        }

        public Builder setMediaItem(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            factory.getClass();
            this.mediaSourceFactory = factory;
            return this;
        }

        public Builder useDefaultMediaSourceFactory(Context context) {
            return setMediaSourceFactory(new DefaultMediaSourceFactory(context));
        }

        public Builder add(MediaSource mediaSource) {
            return add(mediaSource, C.TIME_UNSET);
        }

        public Builder add(MediaItem mediaItem, long j10) {
            mediaItem.getClass();
            if (j10 == C.TIME_UNSET) {
                MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
                if (clippingConfiguration.endPositionMs != Long.MIN_VALUE) {
                    j10 = Util.usToMs(clippingConfiguration.endPositionUs - clippingConfiguration.startPositionUs);
                }
            }
            ac.b.p(this.mediaSourceFactory, "Must use useDefaultMediaSourceFactory or setMediaSourceFactory first.");
            return add(this.mediaSourceFactory.createMediaSource(mediaItem), j10);
        }

        public Builder add(MediaSource mediaSource, long j10) {
            mediaSource.getClass();
            ac.b.t(((mediaSource instanceof ProgressiveMediaSource) && j10 == C.TIME_UNSET) ? false : true, "Progressive media source must define an initial placeholder duration.");
            e1 e1Var = this.mediaSourceHoldersBuilder;
            int i10 = this.index;
            this.index = i10 + 1;
            e1Var.c(new MediaSourceHolder(mediaSource, i10, Util.msToUs(j10)));
            return this;
        }
    }

    public static final class ConcatenatedTimeline extends Timeline {
        private final long defaultPositionUs;
        private final long durationUs;
        private final h1 firstPeriodIndices;
        private final boolean isDynamic;
        private final boolean isSeekable;
        private final Object manifest;
        private final MediaItem mediaItem;
        private final h1 periodOffsetsInWindowUs;
        private final h1 timelines;

        public ConcatenatedTimeline(MediaItem mediaItem, h1 h1Var, h1 h1Var2, h1 h1Var3, boolean z, boolean z5, long j10, long j11, Object obj) {
            this.mediaItem = mediaItem;
            this.timelines = h1Var;
            this.firstPeriodIndices = h1Var2;
            this.periodOffsetsInWindowUs = h1Var3;
            this.isSeekable = z;
            this.isDynamic = z5;
            this.durationUs = j10;
            this.defaultPositionUs = j11;
            this.manifest = obj;
        }

        private int getChildIndexByPeriodIndex(int i10) {
            return Util.binarySearchFloor((List<? extends Comparable<? super Integer>>) this.firstPeriodIndices, Integer.valueOf(i10 + 1), false, false);
        }

        private long getPeriodDurationUs(Timeline.Period period, int i10) {
            if (period.durationUs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            return (i10 == this.periodOffsetsInWindowUs.size() + (-1) ? this.durationUs : ((Long) this.periodOffsetsInWindowUs.get(i10 + 1)).longValue()) - ((Long) this.periodOffsetsInWindowUs.get(i10)).longValue();
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Pair) || !(((Pair) obj).first instanceof Integer)) {
                return -1;
            }
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            int indexOfPeriod = ((Timeline) this.timelines.get(childIndex)).getIndexOfPeriod(ConcatenatingMediaSource2.getChildPeriodUid(obj));
            if (indexOfPeriod == -1) {
                return -1;
            }
            return ((Integer) this.firstPeriodIndices.get(childIndex)).intValue() + indexOfPeriod;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i10);
            ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getPeriod(i10 - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(i10)).longValue();
            period.durationUs = getPeriodDurationUs(period, i10);
            if (z) {
                Object obj = period.uid;
                obj.getClass();
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, obj);
            }
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            Object childPeriodUid = ConcatenatingMediaSource2.getChildPeriodUid(obj);
            Timeline timeline = (Timeline) this.timelines.get(childIndex);
            int indexOfPeriod = timeline.getIndexOfPeriod(childPeriodUid) + ((Integer) this.firstPeriodIndices.get(childIndex)).intValue();
            timeline.getPeriodByUid(childPeriodUid, period);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(indexOfPeriod)).longValue();
            period.durationUs = getPeriodDurationUs(period, indexOfPeriod);
            period.uid = obj;
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i10);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getUidOfPeriod(i10 - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue()));
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
            return window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, null, this.defaultPositionUs, this.durationUs, 0, getPeriodCount() - 1, -((Long) this.periodOffsetsInWindowUs.get(0)).longValue());
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public static final class MediaSourceHolder {
        public int activeMediaPeriods;
        public final int index;
        public final long initialPlaceholderDurationUs;
        public final MaskingMediaSource mediaSource;
        public final HashMap<Object, Long> periodTimeOffsetsByUid = new HashMap<>();

        public MediaSourceHolder(MediaSource mediaSource, int i10, long j10) {
            this.mediaSource = new MaskingMediaSource(mediaSource, false);
            this.index = i10;
            this.initialPlaceholderDurationUs = j10;
        }
    }

    private void disableUnusedMediaSources() {
        for (int i10 = 0; i10 < this.mediaSourceHolders.size(); i10++) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i10);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getChildIndex(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    private static int getChildIndexFromChildWindowSequenceNumber(long j10, int i10) {
        return (int) (j10 % ((long) i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getChildPeriodUid(Object obj) {
        return ((Pair) obj).second;
    }

    private static long getChildWindowSequenceNumber(long j10, int i10, int i11) {
        return (j10 * ((long) i10)) + ((long) i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getPeriodUid(int i10, Object obj) {
        return Pair.create(Integer.valueOf(i10), obj);
    }

    private static long getWindowSequenceNumberFromChildWindowSequenceNumber(long j10, int i10) {
        return j10 / ((long) i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            updateTimeline();
        }
        return true;
    }

    private ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        e1 e1Var;
        e1 e1Var2;
        int i10;
        long j10;
        long j11;
        Timeline.Window window;
        boolean z;
        ConcatenatingMediaSource2 concatenatingMediaSource2 = this;
        Timeline.Window window2 = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        e1 e1VarK = h1.k();
        e1 e1VarK2 = h1.k();
        e1 e1VarK3 = h1.k();
        int size = concatenatingMediaSource2.mediaSourceHolders.size();
        boolean z5 = true;
        boolean z10 = true;
        boolean z11 = true;
        int i11 = 0;
        Object obj = null;
        int periodCount = 0;
        boolean z12 = false;
        boolean z13 = false;
        long j12 = 0;
        long j13 = 0;
        long j14 = 0;
        while (i11 < size) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) concatenatingMediaSource2.mediaSourceHolders.get(i11);
            Timeline timeline = mediaSourceHolder.mediaSource.getTimeline();
            ac.b.k(timeline.isEmpty() ^ z5, "Can't concatenate empty child Timeline.");
            e1VarK.c(timeline);
            e1VarK2.c(Integer.valueOf(periodCount));
            periodCount += timeline.getPeriodCount();
            int i12 = 0;
            while (i12 < timeline.getWindowCount()) {
                timeline.getWindow(i12, window2);
                if (!z12) {
                    z12 = z5;
                    obj = window2.manifest;
                }
                z10 = (z10 && Objects.equals(obj, window2.manifest)) ? z5 : false;
                Timeline timeline2 = timeline;
                long j15 = window2.durationUs;
                if (j15 == C.TIME_UNSET) {
                    j15 = mediaSourceHolder.initialPlaceholderDurationUs;
                    if (j15 == C.TIME_UNSET) {
                        return null;
                    }
                }
                j12 += j15;
                if (mediaSourceHolder.index == 0 && i12 == 0) {
                    e1Var = e1VarK;
                    e1Var2 = e1VarK2;
                    j13 = window2.defaultPositionUs;
                    j14 = -window2.positionInFirstPeriodUs;
                } else {
                    e1Var = e1VarK;
                    e1Var2 = e1VarK2;
                }
                z11 &= window2.isSeekable || window2.isPlaceholder;
                z13 |= window2.isDynamic;
                int i13 = window2.firstPeriodIndex;
                while (i13 <= window2.lastPeriodIndex) {
                    e1VarK3.c(Long.valueOf(j14));
                    Timeline timeline3 = timeline2;
                    timeline3.getPeriod(i13, period, true);
                    e1 e1Var3 = e1VarK3;
                    long j16 = period.durationUs;
                    if (j16 == C.TIME_UNSET) {
                        ac.b.k(window2.firstPeriodIndex == window2.lastPeriodIndex, "Can't apply placeholder duration to multiple periods with unknown duration in a single window.");
                        j16 = window2.positionInFirstPeriodUs + j15;
                    }
                    if (i13 != window2.firstPeriodIndex || ((mediaSourceHolder.index == 0 && i12 == 0) || j16 == C.TIME_UNSET)) {
                        i10 = i13;
                        j10 = j16;
                        j11 = 0;
                    } else {
                        i10 = i13;
                        j11 = -window2.positionInFirstPeriodUs;
                        j10 = j16 + j11;
                    }
                    Object obj2 = period.uid;
                    obj2.getClass();
                    int i14 = i10;
                    if (mediaSourceHolder.activeMediaPeriods == 0 || !mediaSourceHolder.periodTimeOffsetsByUid.containsKey(obj2)) {
                        window = window2;
                    } else {
                        window = window2;
                        if (!mediaSourceHolder.periodTimeOffsetsByUid.get(obj2).equals(Long.valueOf(j11))) {
                            z = false;
                        }
                        ac.b.k(z, "Can't handle windows with changing offset in first period.");
                        mediaSourceHolder.periodTimeOffsetsByUid.put(obj2, Long.valueOf(j11));
                        j14 += j10;
                        i13 = i14 + 1;
                        e1VarK3 = e1Var3;
                        timeline2 = timeline3;
                        window2 = window;
                    }
                    z = true;
                    ac.b.k(z, "Can't handle windows with changing offset in first period.");
                    mediaSourceHolder.periodTimeOffsetsByUid.put(obj2, Long.valueOf(j11));
                    j14 += j10;
                    i13 = i14 + 1;
                    e1VarK3 = e1Var3;
                    timeline2 = timeline3;
                    window2 = window;
                }
                i12++;
                e1VarK = e1Var;
                e1VarK2 = e1Var2;
                timeline = timeline2;
                z5 = true;
            }
            i11++;
            concatenatingMediaSource2 = this;
            z5 = true;
        }
        return new ConcatenatedTimeline(getMediaItem(), e1VarK.f(), e1VarK2.f(), e1VarK3.f(), z11, z13, j12, j13, z10 ? obj : null);
    }

    private void scheduleTimelineUpdate() {
        if (this.timelineUpdateScheduled) {
            return;
        }
        Handler handler = this.playbackThreadHandler;
        handler.getClass();
        handler.obtainMessage(1).sendToTarget();
        this.timelineUpdateScheduled = true;
    }

    private void updateTimeline() {
        this.timelineUpdateScheduled = false;
        ConcatenatedTimeline concatenatedTimelineMaybeCreateConcatenatedTimeline = maybeCreateConcatenatedTimeline();
        if (concatenatedTimelineMaybeCreateConcatenatedTimeline != null) {
            refreshSourceInfo(concatenatedTimelineMaybeCreateConcatenatedTimeline);
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        long jLongValue;
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId mediaPeriodIdCopyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        if (mediaPeriodId.isAd()) {
            jLongValue = 0;
        } else {
            Long l10 = mediaSourceHolder.periodTimeOffsetsByUid.get(mediaPeriodIdCopyWithWindowSequenceNumber.periodUid);
            l10.getClass();
            jLongValue = l10.longValue();
        }
        TimeOffsetMediaPeriod timeOffsetMediaPeriod = new TimeOffsetMediaPeriod(mediaSourceHolder.mediaSource.createPeriod(mediaPeriodIdCopyWithWindowSequenceNumber, allocator, j10 - jLongValue), jLongValue);
        this.mediaSourceByMediaPeriod.put(timeOffsetMediaPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return timeOffsetMediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void enableInternal() {
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public int getWindowIndexForChildWindowIndex(Integer num, int i10) {
        return 0;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new b(this, 1));
        for (int i10 = 0; i10 < this.mediaSourceHolders.size(); i10++) {
            prepareChildSource(Integer.valueOf(i10), ((MediaSourceHolder) this.mediaSourceHolders.get(i10)).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        mediaSourceHolderRemove.getClass();
        mediaSourceHolderRemove.mediaSource.releasePeriod(((TimeOffsetMediaPeriod) mediaPeriod).getWrappedMediaPeriod());
        mediaSourceHolderRemove.activeMediaPeriods--;
        if (this.mediaSourceByMediaPeriod.isEmpty()) {
            return;
        }
        disableUnusedMediaSources();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Handler handler = this.playbackThreadHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    private ConcatenatingMediaSource2(MediaItem mediaItem, h1 h1Var) {
        this.mediaItem = mediaItem;
        this.mediaSourceHolders = h1Var;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != getChildIndexFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size())) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid(getPeriodUid(num.intValue(), mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getWindowSequenceNumberFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size()));
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Integer num, long j10, MediaSource.MediaPeriodId mediaPeriodId) {
        Long l10;
        return (j10 == C.TIME_UNSET || mediaPeriodId == null || mediaPeriodId.isAd() || (l10 = ((MediaSourceHolder) this.mediaSourceHolders.get(num.intValue())).periodTimeOffsetsByUid.get(mediaPeriodId.periodUid)) == null) ? j10 : Util.usToMs(l10.longValue()) + j10;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    /* JADX INFO: renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void lambda$prepareChildSource$0(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }
}
