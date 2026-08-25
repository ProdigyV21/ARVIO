package androidx.media3.extractor.wav;

import ac.b;
import android.util.Pair;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.WavUtil;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class WavHeaderReader {
    private static final String TAG = "WavHeaderReader";
    private static final byte[] WAVEEXT_SUBFORMAT = {0, 0, 0, 0, 16, 0, -128, 0, 0, -86, 0, 56, -101, 113};
    private static final byte[] AMBISONIC_SUBFORMAT = {0, 0, 33, 7, -45, 17, -122, 68, -56, -63, -54, 0, 0, 0};

    public static final class ChunkHeader {
        public static final int SIZE_IN_BYTES = 8;
        public final int id;
        public final long size;

        private ChunkHeader(int i10, long j10) {
            this.id = i10;
            this.size = j10;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
            extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }

    private WavHeaderReader() {
    }

    public static boolean checkFileType(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        int i10 = ChunkHeader.peek(extractorInput, parsableByteArray).id;
        if (i10 != 1380533830 && i10 != 1380333108) {
            return false;
        }
        extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
        parsableByteArray.setPosition(0);
        int i11 = parsableByteArray.readInt();
        if (i11 == 1463899717) {
            return true;
        }
        Log.e(TAG, "Unsupported form type: " + i11);
        return false;
    }

    public static WavFormat readFormat(ExtractorInput extractorInput) throws IOException {
        byte[] bArr;
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        ChunkHeader chunkHeaderSkipToChunk = skipToChunk(WavUtil.FMT_FOURCC, extractorInput, parsableByteArray);
        b.s(chunkHeaderSkipToChunk.size >= 16);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 16);
        parsableByteArray.setPosition(0);
        int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int littleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int littleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianUnsignedShort4 = parsableByteArray.readLittleEndianUnsignedShort();
        int i10 = ((int) chunkHeaderSkipToChunk.size) - 16;
        if (i10 > 0) {
            bArr = new byte[i10];
            extractorInput.peekFully(bArr, 0, i10);
            if (littleEndianUnsignedShort == 65534 && i10 == 24) {
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(bArr);
                parsableByteArray2.readLittleEndianUnsignedShort();
                int littleEndianUnsignedShort5 = parsableByteArray2.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort5 != 0 && littleEndianUnsignedShort5 != littleEndianUnsignedShort4) {
                    throw ParserException.createForUnsupportedContainerFeature("validBits ( " + littleEndianUnsignedShort5 + ")  != bitsPerSample( " + littleEndianUnsignedShort4 + ") are not supported");
                }
                int littleEndianUnsignedIntToInt3 = parsableByteArray2.readLittleEndianUnsignedIntToInt();
                if ((littleEndianUnsignedIntToInt3 >> 18) != 0) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid channel mask " + littleEndianUnsignedIntToInt3);
                }
                if (littleEndianUnsignedIntToInt3 != 0 && Integer.bitCount(littleEndianUnsignedIntToInt3) != littleEndianUnsignedShort2) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid number of channels (" + Integer.bitCount(littleEndianUnsignedIntToInt3) + ") in channel mask " + littleEndianUnsignedIntToInt3);
                }
                littleEndianUnsignedShort = parsableByteArray2.readLittleEndianUnsignedShort();
                byte[] bArr2 = new byte[14];
                parsableByteArray2.readBytes(bArr2, 0, 14);
                if (!Arrays.equals(bArr2, WAVEEXT_SUBFORMAT) && !Arrays.equals(bArr2, AMBISONIC_SUBFORMAT)) {
                    throw ParserException.createForUnsupportedContainerFeature("invalid wav format extension guid");
                }
            }
        } else {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr3 = bArr;
        int i11 = littleEndianUnsignedShort;
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
        return new WavFormat(i11, littleEndianUnsignedShort2, littleEndianUnsignedIntToInt, littleEndianUnsignedIntToInt2, littleEndianUnsignedShort3, littleEndianUnsignedShort4, bArr3);
    }

    public static long readRf64SampleDataSize(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (chunkHeaderPeek.id != 1685272116) {
            extractorInput.resetPeekPosition();
            return -1L;
        }
        extractorInput.advancePeekPosition(8);
        parsableByteArray.setPosition(0);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
        long littleEndianLong = parsableByteArray.readLittleEndianLong();
        extractorInput.skipFully(((int) chunkHeaderPeek.size) + 8);
        return littleEndianLong;
    }

    private static ChunkHeader skipToChunk(int i10, ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
        ChunkHeader chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (chunkHeaderPeek.id != i10) {
            Log.w(TAG, "Ignoring unknown WAV chunk: " + chunkHeaderPeek.id);
            long j10 = chunkHeaderPeek.size;
            long j11 = 8 + j10;
            if (j10 % 2 != 0) {
                j11 = 9 + j10;
            }
            if (j11 > 2147483647L) {
                throw ParserException.createForUnsupportedContainerFeature("Chunk is too large (~2GB+) to skip; id: " + chunkHeaderPeek.id);
            }
            extractorInput.skipFully((int) j11);
            chunkHeaderPeek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        return chunkHeaderPeek;
    }

    public static Pair<Long, Long> skipToSampleData(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        ChunkHeader chunkHeaderSkipToChunk = skipToChunk(1684108385, extractorInput, new ParsableByteArray(8));
        extractorInput.skipFully(8);
        return Pair.create(Long.valueOf(extractorInput.getPosition()), Long.valueOf(chunkHeaderSkipToChunk.size));
    }
}
