package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override // androidx.media3.common.Timeline
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // androidx.media3.common.Timeline
    public int getFirstWindowIndex(boolean z) {
        return this.timeline.getFirstWindowIndex(z);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    @Override // androidx.media3.common.Timeline
    public int getLastWindowIndex(boolean z) {
        return this.timeline.getLastWindowIndex(z);
    }

    @Override // androidx.media3.common.Timeline
    public int getNextWindowIndex(int i10, int i11, boolean z) {
        return this.timeline.getNextWindowIndex(i10, i11, z);
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
        return this.timeline.getPeriod(i10, period, z);
    }

    @Override // androidx.media3.common.Timeline
    public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
        return super.getPeriodByUid(obj, period);
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // androidx.media3.common.Timeline
    public int getPreviousWindowIndex(int i10, int i11, boolean z) {
        return this.timeline.getPreviousWindowIndex(i10, i11, z);
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int i10) {
        return this.timeline.getUidOfPeriod(i10);
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
        return this.timeline.getWindow(i10, window, j10);
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }

    @Override // androidx.media3.common.Timeline
    public final int hashCode() {
        return super.hashCode();
    }
}
