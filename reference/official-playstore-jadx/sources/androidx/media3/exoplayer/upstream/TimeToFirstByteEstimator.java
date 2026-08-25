package androidx.media3.exoplayer.upstream;

import androidx.media3.datasource.DataSpec;

/* JADX INFO: loaded from: classes3.dex */
public interface TimeToFirstByteEstimator {
    long getTimeToFirstByteEstimateUs();

    void onTransferInitializing(DataSpec dataSpec);

    void onTransferStart(DataSpec dataSpec);

    void reset();
}
