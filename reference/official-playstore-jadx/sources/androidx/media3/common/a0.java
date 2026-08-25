package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a0 implements SimpleBasePlayer.PositionSupplier, SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f3361i;

    public /* synthetic */ a0(long j10) {
        this.f3361i = j10;
    }

    @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
    public long get() {
        return b0.c(this.f3361i);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction
    public boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.lambda$getMaxCountEvictionFunction$0(this.f3361i, deque);
    }
}
