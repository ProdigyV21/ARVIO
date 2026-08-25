package androidx.media3.extractor.mp4;

import androidx.media3.common.C;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final boolean hasOnlySyncSamples;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final int[] syncSampleIndices;
    public final long[] timestampsUs;
    public final Track track;

    public TrackSampleTable(Track track, long[] jArr, int[] iArr, int i10, long[] jArr2, int[] iArr2, int[] iArr3, boolean z, long j10, int i11) {
        ac.b.j(iArr.length == jArr2.length);
        ac.b.j(jArr.length == jArr2.length);
        ac.b.j(iArr2.length == jArr2.length);
        this.track = track;
        this.offsets = jArr;
        this.sizes = iArr;
        this.maximumSize = i10;
        this.timestampsUs = jArr2;
        this.flags = iArr2;
        this.syncSampleIndices = iArr3;
        this.hasOnlySyncSamples = z;
        this.durationUs = j10;
        this.sampleCount = i11;
        if (iArr2.length > 0) {
            int length = iArr2.length - 1;
            iArr2[length] = iArr2[length] | C.BUFFER_FLAG_LAST_SAMPLE;
        }
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long j10) {
        int i10 = 0;
        if (this.hasOnlySyncSamples) {
            return Util.binarySearchFloor(this.timestampsUs, j10, true, false);
        }
        int length = this.syncSampleIndices.length - 1;
        int i11 = -1;
        while (i10 <= length) {
            int i12 = ((length - i10) / 2) + i10;
            if (this.timestampsUs[this.syncSampleIndices[i12]] <= j10) {
                i10 = i12 + 1;
                i11 = i12;
            } else {
                length = i12 - 1;
            }
        }
        if (i11 == -1) {
            return -1;
        }
        long j11 = this.timestampsUs[this.syncSampleIndices[i11]];
        if (j11 == j10) {
            while (i11 > 0 && this.timestampsUs[this.syncSampleIndices[i11 - 1]] == j11) {
                i11--;
            }
        }
        return this.syncSampleIndices[i11];
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long j10) {
        int i10 = 0;
        if (this.hasOnlySyncSamples) {
            return Util.binarySearchCeil(this.timestampsUs, j10, true, false);
        }
        int length = this.syncSampleIndices.length - 1;
        int i11 = -1;
        while (i10 <= length) {
            int i12 = ((length - i10) / 2) + i10;
            if (this.timestampsUs[this.syncSampleIndices[i12]] >= j10) {
                length = i12 - 1;
                i11 = i12;
            } else {
                i10 = i12 + 1;
            }
        }
        if (i11 == -1) {
            return -1;
        }
        long j11 = this.timestampsUs[this.syncSampleIndices[i11]];
        if (j11 == j10) {
            while (true) {
                int[] iArr = this.syncSampleIndices;
                if (i11 >= iArr.length - 1) {
                    break;
                }
                int i13 = i11 + 1;
                if (this.timestampsUs[iArr[i13]] != j11) {
                    break;
                }
                i11 = i13;
            }
        }
        return this.syncSampleIndices[i11];
    }
}
