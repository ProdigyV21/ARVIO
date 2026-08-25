package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public static long assembleVarint(byte[] bArr, int i10, boolean z) {
        long j10 = ((long) bArr[0]) & 255;
        if (z) {
            j10 &= ~VARINT_LENGTH_MASKS[i10 - 1];
        }
        for (int i11 = 1; i11 < i10; i11++) {
            j10 = (j10 << 8) | (((long) bArr[i11]) & 255);
        }
        return j10;
    }

    public static int parseUnsignedVarintLength(int i10) {
        int i11 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (i11 >= jArr.length) {
                return -1;
            }
            if ((jArr[i11] & ((long) i10)) != 0) {
                return i11 + 1;
            }
            i11++;
        }
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z5, int i10) throws IOException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1L;
            }
            int unsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & 255);
            this.length = unsignedVarintLength;
            if (unsignedVarintLength == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int i11 = this.length;
        if (i11 > i10) {
            this.state = 0;
            return -2L;
        }
        if (i11 != 1) {
            extractorInput.readFully(this.scratch, 1, i11 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z5);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
