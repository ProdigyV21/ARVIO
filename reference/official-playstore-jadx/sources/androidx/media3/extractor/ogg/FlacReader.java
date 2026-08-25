package androidx.media3.extractor.ogg;

import ac.b;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ogg.StreamReader;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes3.dex */
final class FlacReader extends StreamReader {
    private static final byte AUDIO_PACKET_TYPE = -1;
    private static final int FRAME_HEADER_SAMPLE_NUMBER_OFFSET = 4;
    private FlacOggSeeker flacOggSeeker;
    private FlacStreamMetadata streamMetadata;

    public static final class FlacOggSeeker implements OggSeeker {
        private long firstFrameOffset = -1;
        private long pendingSeekGranule = -1;
        private FlacStreamMetadata.SeekTable seekTable;
        private FlacStreamMetadata streamMetadata;

        public FlacOggSeeker(FlacStreamMetadata flacStreamMetadata, FlacStreamMetadata.SeekTable seekTable) {
            this.streamMetadata = flacStreamMetadata;
            this.seekTable = seekTable;
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            b.s(this.firstFrameOffset != -1);
            return new FlacSeekTableSeekMap(this.streamMetadata, this.firstFrameOffset);
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public long read(ExtractorInput extractorInput) {
            long j10 = this.pendingSeekGranule;
            if (j10 < 0) {
                return -1L;
            }
            long j11 = -(j10 + 2);
            this.pendingSeekGranule = -1L;
            return j11;
        }

        public void setFirstFrameOffset(long j10) {
            this.firstFrameOffset = j10;
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public void startSeek(long j10) {
            long[] jArr = this.seekTable.pointSampleNumbers;
            this.pendingSeekGranule = jArr[Util.binarySearchFloor(jArr, j10, true, true)];
        }
    }

    private int getFlacFrameBlockSize(ParsableByteArray parsableByteArray) {
        int i10 = (parsableByteArray.getData()[2] & AUDIO_PACKET_TYPE) >> 4;
        if (i10 == 6 || i10 == 7) {
            parsableByteArray.skipBytes(4);
            parsableByteArray.readUtf8EncodedLong();
        }
        int frameBlockSizeSamplesFromKey = FlacFrameReader.readFrameBlockSizeSamplesFromKey(parsableByteArray, i10);
        parsableByteArray.setPosition(0);
        return frameBlockSizeSamplesFromKey;
    }

    private static boolean isAudioPacket(byte[] bArr) {
        return bArr[0] == -1;
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.bytesLeft() >= 5 && parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        if (isAudioPacket(parsableByteArray.getData())) {
            return getFlacFrameBlockSize(parsableByteArray);
        }
        return -1L;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j10, StreamReader.SetupData setupData) {
        byte[] data = parsableByteArray.getData();
        FlacStreamMetadata flacStreamMetadata = this.streamMetadata;
        if (flacStreamMetadata == null) {
            FlacStreamMetadata flacStreamMetadata2 = new FlacStreamMetadata(data, 17);
            this.streamMetadata = flacStreamMetadata2;
            setupData.format = flacStreamMetadata2.getFormat(Arrays.copyOfRange(data, 9, parsableByteArray.limit()), null).buildUpon().setContainerMimeType(MimeTypes.AUDIO_OGG).build();
            return true;
        }
        if ((data[0] & 127) == 3) {
            FlacStreamMetadata.SeekTable seekTableMetadataBlock = FlacMetadataReader.readSeekTableMetadataBlock(parsableByteArray);
            FlacStreamMetadata flacStreamMetadataCopyWithSeekTable = flacStreamMetadata.copyWithSeekTable(seekTableMetadataBlock);
            this.streamMetadata = flacStreamMetadataCopyWithSeekTable;
            this.flacOggSeeker = new FlacOggSeeker(flacStreamMetadataCopyWithSeekTable, seekTableMetadataBlock);
            return true;
        }
        if (!isAudioPacket(data)) {
            return true;
        }
        FlacOggSeeker flacOggSeeker = this.flacOggSeeker;
        if (flacOggSeeker != null) {
            flacOggSeeker.setFirstFrameOffset(j10);
            setupData.oggSeeker = this.flacOggSeeker;
        }
        setupData.format.getClass();
        return false;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.streamMetadata = null;
            this.flacOggSeeker = null;
        }
    }
}
