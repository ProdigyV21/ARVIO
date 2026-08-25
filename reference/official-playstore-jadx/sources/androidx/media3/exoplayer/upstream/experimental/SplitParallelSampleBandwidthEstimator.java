package androidx.media3.exoplayer.upstream.experimental;

import ac.b;
import android.os.Handler;
import androidx.media3.common.util.Clock;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* JADX INFO: loaded from: classes3.dex */
public class SplitParallelSampleBandwidthEstimator implements BandwidthEstimator {
    private long bandwidthEstimate;
    private final BandwidthStatistic bandwidthStatistic;
    private final Clock clock;
    private final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    private long lastReportedBandwidthEstimate;
    private final long minBytesTransferred;
    private final int minSamples;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private int streamCount;
    private long totalBytesTransferred;
    private int totalSamplesAdded;

    public static class Builder {
        private BandwidthStatistic bandwidthStatistic = new SlidingWeightedAverageBandwidthStatistic();
        private Clock clock = Clock.DEFAULT;
        private long minBytesTransferred;
        private int minSamples;

        public SplitParallelSampleBandwidthEstimator build() {
            return new SplitParallelSampleBandwidthEstimator(this);
        }

        public Builder setBandwidthStatistic(BandwidthStatistic bandwidthStatistic) {
            bandwidthStatistic.getClass();
            this.bandwidthStatistic = bandwidthStatistic;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setMinBytesTransferred(long j10) {
            b.j(j10 >= 0);
            this.minBytesTransferred = j10;
            return this;
        }

        public Builder setMinSamples(int i10) {
            b.j(i10 >= 0);
            this.minSamples = i10;
            return this;
        }
    }

    private void maybeNotifyBandwidthSample(int i10, long j10, long j11) {
        if (j11 != Long.MIN_VALUE) {
            if (i10 == 0 && j10 == 0 && j11 == this.lastReportedBandwidthEstimate) {
                return;
            }
            this.lastReportedBandwidthEstimate = j11;
            this.eventDispatcher.bandwidthSample(i10, j10, j11);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.addListener(handler, eventListener);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public long getBandwidthEstimate() {
        return this.bandwidthEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onBytesTransferred(DataSource dataSource, int i10) {
        long j10 = i10;
        this.sampleBytesTransferred += j10;
        this.totalBytesTransferred += j10;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onNetworkTypeChange(long j10) {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        maybeNotifyBandwidthSample(this.streamCount > 0 ? (int) (jElapsedRealtime - this.sampleStartTimeMs) : 0, this.sampleBytesTransferred, j10);
        this.bandwidthStatistic.reset();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.sampleStartTimeMs = jElapsedRealtime;
        this.sampleBytesTransferred = 0L;
        this.totalSamplesAdded = 0;
        this.totalBytesTransferred = 0L;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferEnd(DataSource dataSource) {
        b.s(this.streamCount > 0);
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long j10 = (int) (jElapsedRealtime - this.sampleStartTimeMs);
        if (j10 > 0) {
            this.bandwidthStatistic.addSample(this.sampleBytesTransferred, 1000 * j10);
            int i10 = this.totalSamplesAdded + 1;
            this.totalSamplesAdded = i10;
            if (i10 > this.minSamples && this.totalBytesTransferred > this.minBytesTransferred) {
                this.bandwidthEstimate = this.bandwidthStatistic.getBandwidthEstimate();
            }
            maybeNotifyBandwidthSample((int) j10, this.sampleBytesTransferred, this.bandwidthEstimate);
            this.sampleStartTimeMs = jElapsedRealtime;
            this.sampleBytesTransferred = 0L;
        }
        this.streamCount--;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferInitializing(DataSource dataSource) {
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void onTransferStart(DataSource dataSource) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthEstimator
    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    private SplitParallelSampleBandwidthEstimator(Builder builder) {
        this.bandwidthStatistic = builder.bandwidthStatistic;
        this.minSamples = builder.minSamples;
        this.minBytesTransferred = builder.minBytesTransferred;
        this.clock = builder.clock;
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.lastReportedBandwidthEstimate = Long.MIN_VALUE;
    }
}
