package androidx.media3.extractor.mp3;

import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.metadata.id3.MlltFrame;

/* JADX INFO: loaded from: classes3.dex */
final class MlltSeeker implements Seeker {
    private final long durationUs;
    private final long[] referencePositions;
    private final long[] referenceTimesMs;

    private MlltSeeker(long[] jArr, long[] jArr2, long j10) {
        this.referencePositions = jArr;
        this.referenceTimesMs = jArr2;
        this.durationUs = j10 == C.TIME_UNSET ? Util.msToUs(jArr2[jArr2.length - 1]) : j10;
    }

    public static MlltSeeker create(long j10, MlltFrame mlltFrame, long j11) {
        int length = mlltFrame.bytesDeviations.length;
        int i10 = length + 1;
        long[] jArr = new long[i10];
        long[] jArr2 = new long[i10];
        jArr[0] = j10;
        long j12 = 0;
        jArr2[0] = 0;
        for (int i11 = 1; i11 <= length; i11++) {
            int i12 = i11 - 1;
            j10 += (long) (mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i12]);
            j12 += (long) (mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i12]);
            jArr[i11] = j10;
            jArr2[i11] = j12;
        }
        return new MlltSeeker(jArr, jArr2, j11);
    }

    private static Pair<Long, Long> linearlyInterpolate(long j10, long[] jArr, long[] jArr2) {
        int iBinarySearchFloor = Util.binarySearchFloor(jArr, j10, true, true);
        long j11 = jArr[iBinarySearchFloor];
        long j12 = jArr2[iBinarySearchFloor];
        int i10 = iBinarySearchFloor + 1;
        if (i10 == jArr.length) {
            return Pair.create(Long.valueOf(j11), Long.valueOf(j12));
        }
        return Pair.create(Long.valueOf(j10), Long.valueOf(((long) ((jArr[i10] == j11 ? 0.0d : (j10 - j11) / (r6 - j11)) * (jArr2[i10] - j12))) + j12));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return C.RATE_UNSET_INT;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return -1L;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return 0L;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j10) {
        Pair<Long, Long> pairLinearlyInterpolate = linearlyInterpolate(Util.usToMs(Util.constrainValue(j10, 0L, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        return new SeekMap.SeekPoints(new SeekPoint(Util.msToUs(((Long) pairLinearlyInterpolate.first).longValue()), ((Long) pairLinearlyInterpolate.second).longValue()));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j10) {
        return Util.msToUs(((Long) linearlyInterpolate(j10, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
