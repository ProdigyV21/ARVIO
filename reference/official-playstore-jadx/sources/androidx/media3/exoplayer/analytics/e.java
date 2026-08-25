package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements ListenerSet.Event, SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3467i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3468l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3469m;

    public /* synthetic */ e(long j10, Clock clock) {
        this.f3467i = 4;
        this.f3469m = j10;
        this.f3468l = clock;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3467i) {
            case 0:
                ((AnalyticsListener) obj).onSeekForwardIncrementChanged((AnalyticsListener.EventTime) this.f3468l, this.f3469m);
                break;
            case 1:
                ((AnalyticsListener) obj).onAudioPositionAdvancing((AnalyticsListener.EventTime) this.f3468l, this.f3469m);
                break;
            case 2:
                ((AnalyticsListener) obj).onSeekBackIncrementChanged((AnalyticsListener.EventTime) this.f3468l, this.f3469m);
                break;
            default:
                ((AnalyticsListener) obj).onMaxSeekToPreviousPositionChanged((AnalyticsListener.EventTime) this.f3468l, this.f3469m);
                break;
        }
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction
    public boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.lambda$getAgeBasedEvictionFunction$1(this.f3469m, (Clock) this.f3468l, deque);
    }

    public /* synthetic */ e(AnalyticsListener.EventTime eventTime, long j10, int i10) {
        this.f3467i = i10;
        this.f3468l = eventTime;
        this.f3469m = j10;
    }
}
