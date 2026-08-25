package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.ForwardingTimeline;

/* JADX INFO: loaded from: classes3.dex */
public final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline timeline, AdPlaybackState adPlaybackState) {
        super(timeline);
        ac.b.s(timeline.getPeriodCount() == 1);
        ac.b.s(timeline.getWindowCount() == 1);
        this.adPlaybackState = adPlaybackState;
    }

    @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
        this.timeline.getPeriod(i10, period, z);
        long j10 = period.durationUs;
        if (j10 == C.TIME_UNSET) {
            j10 = this.adPlaybackState.contentDurationUs;
        }
        period.set(period.id, period.uid, period.windowIndex, j10, period.getPositionInWindowUs(), this.adPlaybackState, period.isPlaceholder);
        return period;
    }
}
