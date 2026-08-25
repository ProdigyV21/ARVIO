package androidx.media3.exoplayer.upstream.experimental;

import ac.b;
import java.util.ArrayDeque;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public class SlidingPercentileBandwidthStatistic implements BandwidthStatistic {
    public static final int DEFAULT_MAX_SAMPLES_COUNT = 10;
    public static final double DEFAULT_PERCENTILE = 0.5d;
    private long bitrateEstimate;
    private final int maxSampleCount;
    private final double percentile;
    private final ArrayDeque<Sample> samples;
    private final TreeSet<Sample> sortedSamples;
    private double weightSum;

    public static class Sample implements Comparable<Sample> {
        private final long bitrate;
        private final double weight;

        public Sample(long j10, double d4) {
            this.bitrate = j10;
            this.weight = d4;
        }

        @Override // java.lang.Comparable
        public int compareTo(Sample sample) {
            return Long.compare(this.bitrate, sample.bitrate);
        }
    }

    public SlidingPercentileBandwidthStatistic() {
        this(10, 0.5d);
    }

    private long calculateBitrateEstimate() {
        if (this.samples.isEmpty()) {
            return Long.MIN_VALUE;
        }
        double d4 = this.weightSum * this.percentile;
        double d10 = 0.0d;
        long j10 = 0;
        double d11 = 0.0d;
        for (Sample sample : this.sortedSamples) {
            double d12 = d10 + (sample.weight / 2.0d);
            if (d12 >= d4) {
                if (j10 == 0) {
                    return sample.bitrate;
                }
                return j10 + ((long) (((d4 - d11) * (sample.bitrate - j10)) / (d12 - d11)));
            }
            j10 = sample.bitrate;
            d11 = d12;
            d10 = (sample.weight / 2.0d) + d12;
        }
        return j10;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void addSample(long j10, long j11) {
        while (this.samples.size() >= this.maxSampleCount) {
            Sample sampleRemove = this.samples.remove();
            this.sortedSamples.remove(sampleRemove);
            this.weightSum -= sampleRemove.weight;
        }
        double dSqrt = Math.sqrt(j10);
        Sample sample = new Sample((j10 * 8000000) / j11, dSqrt);
        this.samples.add(sample);
        this.sortedSamples.add(sample);
        this.weightSum += dSqrt;
        this.bitrateEstimate = calculateBitrateEstimate();
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.experimental.BandwidthStatistic
    public void reset() {
        this.samples.clear();
        this.sortedSamples.clear();
        this.weightSum = 0.0d;
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public SlidingPercentileBandwidthStatistic(int i10, double d4) {
        b.j(d4 >= 0.0d && d4 <= 1.0d);
        this.maxSampleCount = i10;
        this.percentile = d4;
        this.samples = new ArrayDeque<>();
        this.sortedSamples = new TreeSet<>();
        this.bitrateEstimate = Long.MIN_VALUE;
    }
}
