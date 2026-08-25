package androidx.media3.exoplayer.upstream.experimental;

import android.os.Handler;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* JADX INFO: loaded from: classes3.dex */
public interface BandwidthEstimator {
    public static final long ESTIMATE_NOT_AVAILABLE = Long.MIN_VALUE;

    void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener);

    long getBandwidthEstimate();

    void onBytesTransferred(DataSource dataSource, int i10);

    void onNetworkTypeChange(long j10);

    void onTransferEnd(DataSource dataSource);

    void onTransferInitializing(DataSource dataSource);

    void onTransferStart(DataSource dataSource);

    void removeEventListener(BandwidthMeter.EventListener eventListener);
}
