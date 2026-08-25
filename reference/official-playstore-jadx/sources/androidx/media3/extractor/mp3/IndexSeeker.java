package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.SeekMap;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes3.dex */
final class IndexSeeker implements Seeker {
    static final long MIN_TIME_BETWEEN_POINTS_US = 100000;
    private final int averageBitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final IndexSeekMap indexSeekMap;

    public IndexSeeker(long j10, long j11, long j12) {
        this.indexSeekMap = new IndexSeekMap(new long[]{j11}, new long[]{0}, j10);
        this.dataStartPosition = j11;
        this.dataEndPosition = j12;
        int i10 = C.RATE_UNSET_INT;
        if (j10 == C.TIME_UNSET) {
            this.averageBitrate = C.RATE_UNSET_INT;
            return;
        }
        long jScaleLargeValue = Util.scaleLargeValue(j11 - j12, 8L, j10, RoundingMode.HALF_UP);
        if (jScaleLargeValue > 0 && jScaleLargeValue <= 2147483647L) {
            i10 = (int) jScaleLargeValue;
        }
        this.averageBitrate = i10;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.averageBitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return this.dataStartPosition;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.indexSeekMap.getDurationUs();
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j10) {
        return this.indexSeekMap.getSeekPoints(j10);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j10) {
        return this.indexSeekMap.getTimeUs(j10);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.indexSeekMap.isSeekable();
    }

    public boolean isTimeUsInIndex(long j10) {
        return this.indexSeekMap.isTimeUsInIndex(j10, 100000L);
    }

    public void maybeAddSeekPoint(long j10, long j11) {
        if (isTimeUsInIndex(j10)) {
            return;
        }
        this.indexSeekMap.addSeekPoint(j10, j11);
    }

    public void setDurationUs(long j10) {
        this.indexSeekMap.setDurationUs(j10);
    }
}
