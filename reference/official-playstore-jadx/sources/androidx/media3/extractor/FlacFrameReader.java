package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ts.PsExtractor;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class FlacFrameReader {
    private static final String TAG = "FlacFrameReader";

    public static final class SampleNumberHolder {
        public long sampleNumber;
    }

    private FlacFrameReader() {
    }

    private static boolean checkAndReadBlockSizeSamples(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i10, long j10) {
        int frameBlockSizeSamplesFromKey = readFrameBlockSizeSamplesFromKey(parsableByteArray, i10);
        long j11 = flacStreamMetadata.totalSamples;
        return frameBlockSizeSamplesFromKey != -1 && (((j11 > 0L ? 1 : (j11 == 0L ? 0 : -1)) == 0 || ((j10 + ((long) frameBlockSizeSamplesFromKey)) > j11 ? 1 : ((j10 + ((long) frameBlockSizeSamplesFromKey)) == j11 ? 0 : -1)) >= 0) || frameBlockSizeSamplesFromKey >= flacStreamMetadata.minBlockSizeSamples) && frameBlockSizeSamplesFromKey <= flacStreamMetadata.maxBlockSizeSamples;
    }

    private static boolean checkAndReadCrc(ParsableByteArray parsableByteArray, int i10) {
        return parsableByteArray.readUnsignedByte() == Util.crc8(parsableByteArray.getData(), i10, parsableByteArray.getPosition() - 1, 0);
    }

    private static boolean checkAndReadFirstSampleNumber(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, boolean z, SampleNumberHolder sampleNumberHolder) {
        try {
            long utf8EncodedLong = parsableByteArray.readUtf8EncodedLong();
            if (!z) {
                utf8EncodedLong *= (long) flacStreamMetadata.maxBlockSizeSamples;
            }
            long j10 = flacStreamMetadata.totalSamples;
            if (j10 != 0 && utf8EncodedLong > j10) {
                return false;
            }
            sampleNumberHolder.sampleNumber = utf8EncodedLong;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean checkAndReadFrameHeader(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i10, SampleNumberHolder sampleNumberHolder) {
        int position = parsableByteArray.getPosition();
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long j10 = unsignedInt >>> 16;
        if (j10 != i10) {
            return false;
        }
        return checkChannelAssignment((int) ((unsignedInt >> 4) & 15), flacStreamMetadata) && checkBitsPerSample((int) ((unsignedInt >> 1) & 7), flacStreamMetadata) && !(((unsignedInt & 1) > 1L ? 1 : ((unsignedInt & 1) == 1L ? 0 : -1)) == 0) && checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, ((j10 & 1) > 1L ? 1 : ((j10 & 1) == 1L ? 0 : -1)) == 0, sampleNumberHolder) && checkAndReadBlockSizeSamples(parsableByteArray, flacStreamMetadata, (int) ((unsignedInt >> 12) & 15), sampleNumberHolder.sampleNumber) && checkAndReadSampleRate(parsableByteArray, flacStreamMetadata, (int) ((unsignedInt >> 8) & 15)) && checkAndReadCrc(parsableByteArray, position) && checkFirstSubframeHeaderFromPeek(parsableByteArray);
    }

    private static boolean checkAndReadSampleRate(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i10) {
        int i11 = flacStreamMetadata.sampleRate;
        if (i10 == 0) {
            return true;
        }
        if (i10 <= 11) {
            return i10 == flacStreamMetadata.sampleRateLookupKey;
        }
        if (i10 == 12) {
            return parsableByteArray.readUnsignedByte() * 1000 == i11;
        }
        if (i10 <= 14) {
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (i10 == 14) {
                unsignedShort *= 10;
            }
            if (unsignedShort == i11) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkBitsPerSample(int i10, FlacStreamMetadata flacStreamMetadata) {
        return i10 == 0 || i10 == flacStreamMetadata.bitsPerSampleLookupKey;
    }

    private static boolean checkChannelAssignment(int i10, FlacStreamMetadata flacStreamMetadata) {
        return i10 <= 7 ? i10 == flacStreamMetadata.channels - 1 : i10 <= 10 && flacStreamMetadata.channels == 2;
    }

    private static boolean checkFirstSubframeHeaderFromPeek(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() == 0) {
            return true;
        }
        int iPeekUnsignedByte = parsableByteArray.peekUnsignedByte();
        if ((iPeekUnsignedByte & 128) != 0) {
            return false;
        }
        int i10 = (iPeekUnsignedByte & 126) >> 1;
        if ((i10 < 2 || i10 > 7) && (i10 < 13 || i10 > 31)) {
            return true;
        }
        Log.i(TAG, "Ignoring frame where first subframe has a reserved type: " + i10);
        return false;
    }

    public static boolean checkFrameHeaderFromPeek(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata, int i10, SampleNumberHolder sampleNumberHolder) throws IOException {
        long peekPosition = extractorInput.getPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(17);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 2);
        if (parsableByteArray.peekChar() != i10) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
            return false;
        }
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 2, 15) + 2);
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
        return checkAndReadFrameHeader(parsableByteArray, flacStreamMetadata, i10, sampleNumberHolder);
    }

    public static long getFirstSampleNumber(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata) throws IOException {
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(1);
        byte[] bArr = new byte[1];
        extractorInput.peekFully(bArr, 0, 1);
        boolean z = (bArr[0] & 1) == 1;
        extractorInput.advancePeekPosition(2);
        int i10 = z ? 7 : 6;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 0, i10));
        extractorInput.resetPeekPosition();
        SampleNumberHolder sampleNumberHolder = new SampleNumberHolder();
        if (checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, z, sampleNumberHolder)) {
            return sampleNumberHolder.sampleNumber;
        }
        throw ParserException.createForMalformedContainer(null, null);
    }

    public static int readFrameBlockSizeSamplesFromKey(ParsableByteArray parsableByteArray, int i10) {
        switch (i10) {
            case 1:
                return PsExtractor.AUDIO_STREAM;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i10 - 2);
            case 6:
                return parsableByteArray.readUnsignedByte() + 1;
            case 7:
                return parsableByteArray.readUnsignedShort() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i10 - 8);
            default:
                return -1;
        }
    }
}
