package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import com.google.common.collect.h1;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class FlacMetadataReader {
    private static final int SEEK_POINT_SIZE = 18;
    private static final int STREAM_MARKER = 1716281667;
    private static final int SYNC_CODE = 16382;

    public static final class FlacStreamMetadataHolder {
        public FlacStreamMetadata flacStreamMetadata;

        public FlacStreamMetadataHolder(FlacStreamMetadata flacStreamMetadata) {
            this.flacStreamMetadata = flacStreamMetadata;
        }
    }

    private FlacMetadataReader() {
    }

    public static boolean checkAndPeekStreamMarker(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
        return parsableByteArray.readUnsignedInt() == 1716281667;
    }

    public static int getFrameStartMarker(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(2);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        if ((unsignedShort >> 2) == SYNC_CODE) {
            extractorInput.resetPeekPosition();
            return unsignedShort;
        }
        extractorInput.resetPeekPosition();
        throw ParserException.createForMalformedContainer("First frame does not start with sync code.", null);
    }

    public static Metadata peekId3Metadata(ExtractorInput extractorInput, boolean z) throws IOException {
        Metadata metadataPeekId3Data = new Id3Peeker().peekId3Data(extractorInput, z ? null : Id3Decoder.NO_FRAMES_PREDICATE, 0);
        if (metadataPeekId3Data == null || metadataPeekId3Data.length() == 0) {
            return null;
        }
        return metadataPeekId3Data;
    }

    public static Metadata readId3Metadata(ExtractorInput extractorInput, boolean z) throws IOException {
        extractorInput.resetPeekPosition();
        long peekPosition = extractorInput.getPeekPosition();
        Metadata metadataPeekId3Metadata = peekId3Metadata(extractorInput, z);
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - peekPosition));
        return metadataPeekId3Metadata;
    }

    public static boolean readMetadataBlock(ExtractorInput extractorInput, FlacStreamMetadataHolder flacStreamMetadataHolder) throws IOException {
        extractorInput.resetPeekPosition();
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[4]);
        extractorInput.peekFully(parsableBitArray.data, 0, 4);
        boolean bit = parsableBitArray.readBit();
        int bits = parsableBitArray.readBits(7);
        int bits2 = parsableBitArray.readBits(24) + 4;
        if (bits == 0) {
            flacStreamMetadataHolder.flacStreamMetadata = readStreamInfoBlock(extractorInput);
            return bit;
        }
        FlacStreamMetadata flacStreamMetadata = flacStreamMetadataHolder.flacStreamMetadata;
        if (flacStreamMetadata == null) {
            throw new IllegalArgumentException();
        }
        if (bits == 3) {
            flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithSeekTable(readSeekTableMetadataBlock(extractorInput, bits2));
            return bit;
        }
        if (bits == 4) {
            flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithVorbisComments(readVorbisCommentMetadataBlock(extractorInput, bits2));
            return bit;
        }
        if (bits != 6) {
            extractorInput.skipFully(bits2);
            return bit;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(bits2);
        extractorInput.readFully(parsableByteArray.getData(), 0, bits2);
        parsableByteArray.skipBytes(4);
        flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithPictureFrames(h1.s(PictureFrame.fromPictureBlock(parsableByteArray)));
        return bit;
    }

    public static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(1);
        int unsignedInt24 = parsableByteArray.readUnsignedInt24();
        long position = ((long) parsableByteArray.getPosition()) + ((long) unsignedInt24);
        int i10 = unsignedInt24 / 18;
        long[] jArrCopyOf = new long[i10];
        long[] jArrCopyOf2 = new long[i10];
        int i11 = 0;
        while (true) {
            if (i11 >= i10) {
                break;
            }
            long j10 = parsableByteArray.readLong();
            if (j10 == -1) {
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, i11);
                jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i11);
                break;
            }
            jArrCopyOf[i11] = j10;
            jArrCopyOf2[i11] = parsableByteArray.readLong();
            parsableByteArray.skipBytes(2);
            i11++;
        }
        parsableByteArray.skipBytes((int) (position - ((long) parsableByteArray.getPosition())));
        return new FlacStreamMetadata.SeekTable(jArrCopyOf, jArrCopyOf2);
    }

    private static FlacStreamMetadata readStreamInfoBlock(ExtractorInput extractorInput) throws IOException {
        byte[] bArr = new byte[38];
        extractorInput.readFully(bArr, 0, 38);
        return new FlacStreamMetadata(bArr, 4);
    }

    public static void readStreamMarker(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.readFully(parsableByteArray.getData(), 0, 4);
        if (parsableByteArray.readUnsignedInt() != 1716281667) {
            throw ParserException.createForMalformedContainer("Failed to read FLAC stream marker.", null);
        }
    }

    private static List<String> readVorbisCommentMetadataBlock(ExtractorInput extractorInput, int i10) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        extractorInput.readFully(parsableByteArray.getData(), 0, i10);
        parsableByteArray.skipBytes(4);
        return Arrays.asList(VorbisUtil.readVorbisCommentHeader(parsableByteArray, false, false).comments);
    }

    private static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ExtractorInput extractorInput, int i10) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        extractorInput.readFully(parsableByteArray.getData(), 0, i10);
        return readSeekTableMetadataBlock(parsableByteArray);
    }
}
