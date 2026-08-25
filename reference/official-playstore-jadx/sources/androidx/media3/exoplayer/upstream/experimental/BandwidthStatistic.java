package androidx.media3.exoplayer.upstream.experimental;

/* JADX INFO: loaded from: classes3.dex */
public interface BandwidthStatistic {
    void addSample(long j10, long j11);

    long getBandwidthEstimate();

    void reset();
}
