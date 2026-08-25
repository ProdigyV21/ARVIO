package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

/* JADX INFO: loaded from: classes3.dex */
public final class IndexSeekMap implements SeekMap {
    private long durationUs;
    private final LongArray positions;
    private final LongArray timesUs;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j10) {
        ac.b.j(jArr.length == jArr2.length);
        int length = jArr2.length;
        if (length <= 0 || jArr2[0] <= 0) {
            this.positions = new LongArray(length);
            this.timesUs = new LongArray(length);
        } else {
            int i10 = length + 1;
            LongArray longArray = new LongArray(i10);
            this.positions = longArray;
            LongArray longArray2 = new LongArray(i10);
            this.timesUs = longArray2;
            longArray.add(0L);
            longArray2.add(0L);
        }
        this.positions.addAll(jArr);
        this.timesUs.addAll(jArr2);
        this.durationUs = j10;
    }

    public void addSeekPoint(long j10, long j11) {
        if (this.timesUs.size() == 0 && j10 > 0) {
            this.positions.add(0L);
            this.timesUs.add(0L);
        }
        this.positions.add(j11);
        this.timesUs.add(j10);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j10) {
        if (this.timesUs.size() == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j10, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs.get(iBinarySearchFloor), this.positions.get(iBinarySearchFloor));
        if (seekPoint.timeUs == j10 || iBinarySearchFloor == this.timesUs.size() - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i10 = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs.get(i10), this.positions.get(i10)));
    }

    public long getTimeUs(long j10) {
        if (this.timesUs.size() == 0) {
            return C.TIME_UNSET;
        }
        return this.timesUs.get(Util.binarySearchFloor(this.positions, j10, true, true));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.timesUs.size() > 0;
    }

    public boolean isTimeUsInIndex(long j10, long j11) {
        if (this.timesUs.size() == 0) {
            return false;
        }
        LongArray longArray = this.timesUs;
        return j10 - longArray.get(longArray.size() - 1) < j11;
    }

    public void setDurationUs(long j10) {
        this.durationUs = j10;
    }
}
