package androidx.media3.exoplayer.upstream.experimental;

/* JADX INFO: loaded from: classes3.dex */
public class ExponentialWeightedAverageStatistic implements BandwidthStatistic {
    public static final double DEFAULT_SMOOTHING_FACTOR = 0.9999d;
    private long bitrateEstimate;
    private final double smoothingFactor;

    public ExponentialWeightedAverageStatistic() {
        this(0.9999d);
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void addSample(long j10, long j11) {
        long j12 = (8000000 * j10) / j11;
        if (this.bitrateEstimate == Long.MIN_VALUE) {
            this.bitrateEstimate = j12;
            return;
        }
        double dPow = Math.pow(this.smoothingFactor, Math.sqrt(j10));
        this.bitrateEstimate = (long) (((1.0d - dPow) * j12) + (this.bitrateEstimate * dPow));
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void reset() {
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public ExponentialWeightedAverageStatistic(double d4) {
        this.smoothingFactor = d4;
        this.bitrateEstimate = Long.MIN_VALUE;
    }
}
