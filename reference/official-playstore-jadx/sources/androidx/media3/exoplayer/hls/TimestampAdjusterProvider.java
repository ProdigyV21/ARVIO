package androidx.media3.exoplayer.hls;

import android.util.SparseArray;
import androidx.media3.common.util.TimestampAdjuster;

/* JADX INFO: loaded from: classes3.dex */
public final class TimestampAdjusterProvider {
    private final SparseArray<TimestampAdjuster> timestampAdjusters = new SparseArray<>();

    public TimestampAdjuster getAdjuster(int i10) {
        TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i10);
        if (timestampAdjuster != null) {
            return timestampAdjuster;
        }
        TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(TimestampAdjuster.MODE_SHARED);
        this.timestampAdjusters.put(i10, timestampAdjuster2);
        return timestampAdjuster2;
    }

    public void reset() {
        this.timestampAdjusters.clear();
    }
}
