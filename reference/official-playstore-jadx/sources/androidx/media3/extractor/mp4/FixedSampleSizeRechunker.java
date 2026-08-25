package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;
        public final long totalSize;

        private Results(long[] jArr, int[] iArr, int i10, long[] jArr2, int[] iArr2, long j10, long j11) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i10;
            this.timestamps = jArr2;
            this.flags = iArr2;
            this.duration = j10;
            this.totalSize = j11;
        }
    }

    private FixedSampleSizeRechunker() {
    }

    public static Results rechunk(int i10, long[] jArr, int[] iArr, long j10) {
        int[] iArr2 = iArr;
        int i11 = 8192 / i10;
        int i12 = 0;
        int iCeilDivide = 0;
        for (int i13 : iArr2) {
            iCeilDivide += Util.ceilDivide(i13, i11);
        }
        long[] jArr2 = new long[iCeilDivide];
        int[] iArr3 = new int[iCeilDivide];
        long[] jArr3 = new long[iCeilDivide];
        int[] iArr4 = new int[iCeilDivide];
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int iMax = 0;
        while (i12 < iArr2.length) {
            int i17 = iArr2[i12];
            long j11 = jArr[i12];
            while (i17 > 0) {
                int iMin = Math.min(i11, i17);
                jArr2[i16] = j11;
                int i18 = i10 * iMin;
                iArr3[i16] = i18;
                i15 += i18;
                iMax = Math.max(iMax, i18);
                jArr3[i16] = ((long) i14) * j10;
                iArr4[i16] = 1;
                j11 += (long) iArr3[i16];
                i14 += iMin;
                i17 -= iMin;
                i16++;
                i11 = i11;
            }
            i12++;
            iArr2 = iArr;
        }
        return new Results(jArr2, iArr3, iMax, jArr3, iArr4, j10 * ((long) i14), i15);
    }
}
