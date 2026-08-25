package androidx.media3.extractor.mp3;

import androidx.compose.material3.d;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* JADX INFO: loaded from: classes3.dex */
final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final int bitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] jArr, long[] jArr2, long j10, long j11, long j12, int i10) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j10;
        this.dataStartPosition = j11;
        this.dataEndPosition = j12;
        this.bitrate = i10;
    }

    public static VbriSeeker create(long j10, long j11, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int unsignedByte;
        parsableByteArray.skipBytes(6);
        int i10 = parsableByteArray.readInt();
        long j12 = j11 + ((long) header.frameSize);
        long jMax = ((long) i10) + j12;
        int i11 = parsableByteArray.readInt();
        if (i11 <= 0) {
            return null;
        }
        long jSampleCountToDurationUs = Util.sampleCountToDurationUs((((long) i11) * ((long) header.samplesPerFrame)) - 1, header.sampleRate);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        int unsignedShort3 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(2);
        int i12 = unsignedShort2;
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        int i13 = 0;
        long j13 = j11 + ((long) header.frameSize);
        while (i13 < unsignedShort) {
            long[] jArr3 = jArr2;
            long[] jArr4 = jArr;
            jArr4[i13] = (((long) i13) * jSampleCountToDurationUs) / ((long) unsignedShort);
            jArr3[i13] = j13;
            if (unsignedShort3 == 1) {
                unsignedByte = parsableByteArray.readUnsignedByte();
            } else if (unsignedShort3 == 2) {
                unsignedByte = parsableByteArray.readUnsignedShort();
            } else if (unsignedShort3 == 3) {
                unsignedByte = parsableByteArray.readUnsignedInt24();
            } else {
                if (unsignedShort3 != 4) {
                    return null;
                }
                unsignedByte = parsableByteArray.readUnsignedIntToInt();
            }
            int i14 = i13;
            int i15 = i12;
            j13 += ((long) unsignedByte) * ((long) i15);
            i12 = i15;
            i13 = i14 + 1;
            unsignedShort = unsignedShort;
            jArr = jArr4;
            jArr2 = jArr3;
        }
        long[] jArr5 = jArr2;
        long[] jArr6 = jArr;
        if (j10 != -1 && j10 != jMax) {
            StringBuilder sbR = d.r(j10, "VBRI data size mismatch: ", ", ");
            sbR.append(jMax);
            Log.w(TAG, sbR.toString());
        }
        if (jMax != j13) {
            StringBuilder sbR2 = d.r(jMax, "VBRI bytes and ToC mismatch (using max): ", ", ");
            sbR2.append(j13);
            sbR2.append("\nSeeking will be inaccurate.");
            Log.w(TAG, sbR2.toString());
            jMax = Math.max(jMax, j13);
        }
        return new VbriSeeker(jArr6, jArr5, jSampleCountToDurationUs, j12, jMax, header.bitrate);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
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
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j10) {
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j10, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[iBinarySearchFloor], this.positions[iBinarySearchFloor]);
        if (seekPoint.timeUs >= j10 || iBinarySearchFloor == this.timesUs.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i10 = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i10], this.positions[i10]));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long j10) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j10, true, true)];
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
