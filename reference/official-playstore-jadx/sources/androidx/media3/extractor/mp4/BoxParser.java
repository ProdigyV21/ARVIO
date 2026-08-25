package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4AlternateGroupData;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.m;
import com.google.common.base.o;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class BoxParser {
    private static final int EDIT_LIST_DURATION_TOLERANCE_TIMESCALE_UNITS = 2;
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final int SAMPLE_RATE_AMR_NB = 8000;
    private static final int SAMPLE_RATE_AMR_WB = 16000;
    private static final String TAG = "BoxParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subp = 1937072752;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    public static final class BtrtData {
        private final long avgBitrate;
        private final long maxBitrate;

        public BtrtData(long j10, long j11) {
            this.avgBitrate = j10;
            this.maxBitrate = j11;
        }

        public static /* synthetic */ long access$1400(BtrtData btrtData) {
            return btrtData.maxBitrate;
        }

        public static /* synthetic */ long access$1500(BtrtData btrtData) {
            return btrtData.avgBitrate;
        }
    }

    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i10 = this.index + 1;
            this.index = i10;
            if (i10 == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i11 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i11;
                this.nextSamplesPerChunkChangeIndex = i11 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    public static final class EsdsData {
        private final long bitrate;
        private final byte[] initializationData;
        private final String mimeType;
        private final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j10, long j11) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j10;
            this.peakBitrate = j11;
        }

        public static /* synthetic */ String access$1300(EsdsData esdsData) {
            return esdsData.mimeType;
        }

        public static /* synthetic */ long access$1600(EsdsData esdsData) {
            return esdsData.peakBitrate;
        }

        public static /* synthetic */ long access$1700(EsdsData esdsData) {
            return esdsData.bitrate;
        }

        public static /* synthetic */ byte[] access$700(EsdsData esdsData) {
            return esdsData.initializationData;
        }
    }

    public static final class EyesData {
        private final StriData striData;

        public EyesData(StriData striData) {
            this.striData = striData;
        }

        public static /* synthetic */ StriData access$1100(EyesData eyesData) {
            return eyesData.striData;
        }
    }

    public static final class MdhdData {
        private final String language;
        private final long mediaDurationUs;
        private final long timescale;

        public MdhdData(long j10, long j11, String str) {
            this.timescale = j10;
            this.mediaDurationUs = j11;
            this.language = str;
        }
    }

    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    public static final class StriData {
        private final boolean eyeViewsReversed;
        private final boolean hasLeftEyeView;
        private final boolean hasRightEyeView;

        public StriData(boolean z, boolean z5, boolean z10) {
            this.hasLeftEyeView = z;
            this.hasRightEyeView = z5;
            this.eyeViewsReversed = z10;
        }

        public static /* synthetic */ boolean access$1200(StriData striData) {
            return striData.eyeViewsReversed;
        }
    }

    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i10) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i10];
        }
    }

    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox leafBox, Format format) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (unsignedIntToInt == 0 || unsignedIntToInt % pcmFrameSize != 0) {
                    Log.w(BoxParser.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + unsignedIntToInt);
                    unsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = unsignedIntToInt == 0 ? -1 : unsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i10 = this.fixedSampleSize;
            return i10 == -1 ? this.data.readUnsignedIntToInt() : i10;
        }
    }

    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Mp4Box.LeafBox leafBox) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i10 = this.fieldSize;
            if (i10 == 8) {
                return this.data.readUnsignedByte();
            }
            if (i10 == 16) {
                return this.data.readUnsignedShort();
            }
            int i11 = this.sampleIndex;
            this.sampleIndex = i11 + 1;
            if (i11 % 2 != 0) {
                return this.currentByte & 15;
            }
            int unsignedByte = this.data.readUnsignedByte();
            this.currentByte = unsignedByte;
            return (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }

    public static final class TkhdData {
        private final int alternateGroup;
        private final long duration;
        private final int height;
        private final int id;
        private final int rotationDegrees;
        private final int width;

        public TkhdData(int i10, long j10, int i11, int i12, int i13, int i14) {
            this.id = i10;
            this.duration = j10;
            this.alternateGroup = i11;
            this.rotationDegrees = i12;
            this.width = i13;
            this.height = i14;
        }
    }

    public static final class VexuData {
        private final EyesData eyesData;

        public VexuData(EyesData eyesData) {
            this.eyesData = eyesData;
        }

        public static /* synthetic */ EyesData access$1000(VexuData vexuData) {
            return vexuData.eyesData;
        }

        public boolean hasBothEyeViews() {
            EyesData eyesData = this.eyesData;
            return eyesData != null && eyesData.striData.hasLeftEyeView && this.eyesData.striData.hasRightEyeView;
        }
    }

    private BoxParser() {
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j10, long j11, long j12) {
        int length = jArr.length - 1;
        return jArr[0] <= j11 && j11 < jArr[Util.constrainValue(4, 0, length)] && jArr[Util.constrainValue(jArr.length - 4, 0, length)] < j12 && j12 <= j10 + 2;
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i10, int i11, int i12) throws ParserException {
        int position = parsableByteArray.getPosition();
        ExtractorUtil.checkContainerInput(position >= i11, null);
        while (position - i11 < i12) {
            parsableByteArray.setPosition(position);
            int i13 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i13 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i10) {
                return position;
            }
            position += i13;
        }
        return -1;
    }

    private static String formatVobsubIdx(byte[] bArr, int i10, int i11) {
        ac.b.s(bArr.length == 64);
        ArrayList arrayList = new ArrayList(16);
        for (int i12 = 0; i12 < bArr.length - 3; i12 += 4) {
            arrayList.add(String.format("%06x", Integer.valueOf(vobsubYuvToRgb(f0.n(bArr[i12], bArr[i12 + 1], bArr[i12 + 2], bArr[i12 + 3])))));
        }
        StringBuilder sbV = c.v("size: ", i10, "x", i11, "\npalette: ");
        sbV.append(new o(", ").b(arrayList));
        sbV.append("\n");
        return sbV.toString();
    }

    private static String getLanguageFromCode(int i10) {
        char[] cArr = {(char) (((i10 >> 10) & 31) + 96), (char) (((i10 >> 5) & 31) + 96), (char) ((i10 & 31) + 96)};
        for (int i11 = 0; i11 < 3; i11++) {
            char c10 = cArr[i11];
            if (c10 < 'a' || c10 > 'z') {
                return null;
            }
        }
        return new String(cArr);
    }

    private static int getTrackTypeForHdlr(int i10) {
        if (i10 == TYPE_soun) {
            return 1;
        }
        if (i10 == TYPE_vide) {
            return 2;
        }
        if (i10 == TYPE_text || i10 == TYPE_sbtl || i10 == TYPE_subt || i10 == TYPE_clcp || i10 == TYPE_subp) {
            return 3;
        }
        return i10 == 1835365473 ? 5 : -1;
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    private static ColorInfo parseApvc(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int bits = parsableBitArray.readBits(8);
        for (int i10 = 0; i10 < bits; i10++) {
            parsableBitArray.skipBytes(1);
            int bits2 = parsableBitArray.readBits(8);
            for (int i11 = 0; i11 < bits2; i11++) {
                parsableBitArray.skipBits(6);
                boolean bit = parsableBitArray.readBit();
                parsableBitArray.skipBit();
                parsableBitArray.skipBytes(11);
                parsableBitArray.skipBits(4);
                int bits3 = parsableBitArray.readBits(4) + 8;
                builder.setLumaBitdepth(bits3);
                builder.setChromaBitdepth(bits3);
                parsableBitArray.skipBytes(1);
                if (bit) {
                    int bits4 = parsableBitArray.readBits(8);
                    int bits5 = parsableBitArray.readBits(8);
                    parsableBitArray.skipBytes(1);
                    builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(bits4)).setColorRange(parsableBitArray.readBit() ? 1 : 2).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(bits5));
                }
            }
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x03b2 A[PHI: r2 r8 r9 r11 r16 r20 r23
      0x03b2: PHI (r2v17 java.util.List<byte[]>) = 
      (r2v2 java.util.List<byte[]>)
      (r2v2 java.util.List<byte[]>)
      (r2v16 java.util.List<byte[]>)
      (r2v2 java.util.List<byte[]>)
      (r2v26 java.util.List<byte[]>)
     binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r8v11 int) = (r8v8 int), (r8v8 int), (r8v8 int), (r8v8 int), (r8v15 int) binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r9v8 int) = (r9v5 int), (r9v5 int), (r9v5 int), (r9v5 int), (r9v10 int) binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r11v11 java.lang.String) = 
      (r11v9 java.lang.String)
      (r11v10 java.lang.String)
      (r11v10 java.lang.String)
      (r11v12 java.lang.String)
      (r11v9 java.lang.String)
     binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r16v3 androidx.media3.extractor.mp4.BoxParser$EsdsData) = 
      (r16v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r16v2 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r16v2 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r16v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r16v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
     binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r20v1 int) = (r20v0 int), (r20v0 int), (r20v0 int), (r20v2 int), (r20v4 int) binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]
      0x03b2: PHI (r23v2 int) = (r23v1 int), (r23v1 int), (r23v1 int), (r23v3 int), (r23v4 int) binds: [B:240:0x0464, B:242:0x0472, B:245:0x047c, B:233:0x0428, B:206:0x0382] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray r26, int r27, int r28, int r29, int r30, java.lang.String r31, boolean r32, androidx.media3.common.DrmInitData r33, androidx.media3.extractor.mp4.BoxParser.StsdData r34, int r35) throws androidx.media3.common.ParserException {
        /*
            Method dump skipped, instruction units count: 1305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, androidx.media3.common.DrmInitData, androidx.media3.extractor.mp4.BoxParser$StsdData, int):void");
    }

    private static ColorInfo parseAv1c(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int bits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(6);
        boolean bit = parsableBitArray.readBit();
        boolean bit2 = parsableBitArray.readBit();
        if (bits == 2 && bit) {
            builder.setLumaBitdepth(bit2 ? 12 : 10);
            builder.setChromaBitdepth(bit2 ? 12 : 10);
        } else if (bits <= 2) {
            builder.setLumaBitdepth(bit ? 10 : 8);
            builder.setChromaBitdepth(bit ? 10 : 8);
        }
        parsableBitArray.skipBits(13);
        parsableBitArray.skipBit();
        int bits2 = parsableBitArray.readBits(4);
        if (bits2 != 1) {
            Log.i(TAG, "Unsupported obu_type: " + bits2);
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported obu_extension_flag");
            return builder.build();
        }
        boolean bit3 = parsableBitArray.readBit();
        parsableBitArray.skipBit();
        if (bit3 && parsableBitArray.readBits(8) > 127) {
            Log.i(TAG, "Excessive obu_size");
            return builder.build();
        }
        int bits3 = parsableBitArray.readBits(3);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported reduced_still_picture_header");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported timing_info_present_flag");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported initial_display_delay_present_flag");
            return builder.build();
        }
        int bits4 = parsableBitArray.readBits(5);
        boolean z = false;
        for (int i10 = 0; i10 <= bits4; i10++) {
            parsableBitArray.skipBits(12);
            if (parsableBitArray.readBits(5) > 7) {
                parsableBitArray.skipBit();
            }
        }
        int bits5 = parsableBitArray.readBits(4);
        int bits6 = parsableBitArray.readBits(4);
        parsableBitArray.skipBits(bits5 + 1);
        parsableBitArray.skipBits(bits6 + 1);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(7);
        }
        parsableBitArray.skipBits(7);
        boolean bit4 = parsableBitArray.readBit();
        if (bit4) {
            parsableBitArray.skipBits(2);
        }
        if ((parsableBitArray.readBit() ? 2 : parsableBitArray.readBits(1)) > 0 && !parsableBitArray.readBit()) {
            parsableBitArray.skipBits(1);
        }
        if (bit4) {
            parsableBitArray.skipBits(3);
        }
        parsableBitArray.skipBits(3);
        boolean bit5 = parsableBitArray.readBit();
        if (bits3 == 2 && bit5) {
            parsableBitArray.skipBit();
        }
        if (bits3 != 1 && parsableBitArray.readBit()) {
            z = true;
        }
        if (parsableBitArray.readBit()) {
            int bits7 = parsableBitArray.readBits(8);
            int bits8 = parsableBitArray.readBits(8);
            builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(bits7)).setColorRange(((z || bits7 != 1 || bits8 != 13 || parsableBitArray.readBits(8) != 0) ? parsableBitArray.readBits(1) : 1) != 1 ? 2 : 1).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(bits8));
        }
        return builder.build();
    }

    private static BtrtData parseBtrtFromParent(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.setPosition(i10 + 8);
        parsableByteArray.skipBytes(4);
        return new BtrtData(parsableByteArray.readUnsignedInt(), parsableByteArray.readUnsignedInt());
    }

    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i10, int i11) throws ParserException {
        int i12 = i10 + 8;
        int i13 = -1;
        int i14 = 0;
        String string = null;
        Integer numValueOf = null;
        while (i12 - i10 < i11) {
            parsableByteArray.setPosition(i12);
            int i15 = parsableByteArray.readInt();
            int i16 = parsableByteArray.readInt();
            if (i16 == 1718775137) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i16 == 1935894637) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i16 == 1935894633) {
                i13 = i12;
                i14 = i15;
            }
            i12 += i15;
        }
        if (!C.CENC_TYPE_cenc.equals(string) && !C.CENC_TYPE_cbc1.equals(string) && !C.CENC_TYPE_cens.equals(string) && !C.CENC_TYPE_cbcs.equals(string)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(numValueOf != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i13 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i13, i14, string);
        ExtractorUtil.checkContainerInput(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, (TrackEncryptionBox) Util.castNonNull(schiFromParent));
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_elst);
        if (leafBoxOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType.data;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i10 = 0; i10 < unsignedIntToInt; i10++) {
            jArr[i10] = fullBoxVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i10] = fullBoxVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.setPosition(i10 + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, null, -1L, -1L);
        }
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        long j10 = unsignedInt2;
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        if (j10 <= 0) {
            j10 = -1;
        }
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, j10, unsignedInt > 0 ? unsignedInt : -1L);
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i10 = unsignedByte & 127;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i10 = (i10 << 7) | (unsignedByte & 127);
        }
        return i10;
    }

    public static int parseFullBoxFlags(int i10) {
        return i10 & 16777215;
    }

    public static int parseFullBoxVersion(int i10) {
        return (i10 >> 24) & 255;
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i10) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        long j10;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullBoxVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        int position = parsableByteArray.getPosition();
        int i10 = fullBoxVersion == 0 ? 4 : 8;
        int i11 = 0;
        while (true) {
            j10 = C.TIME_UNSET;
            if (i11 >= i10) {
                parsableByteArray.skipBytes(i10);
                break;
            }
            if (parsableByteArray.getData()[position + i11] != -1) {
                long unsignedInt2 = fullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                if (unsignedInt2 != 0) {
                    long jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedInt2, 1000000L, unsignedInt);
                    unsignedInt = unsignedInt;
                    j10 = jScaleLargeTimestamp;
                }
            } else {
                i11++;
            }
        }
        return new MdhdData(unsignedInt, j10, getLanguageFromCode(parsableByteArray.readUnsignedShort()));
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_hdlr);
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_keys);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ilst);
        if (leafBoxOfType == null || leafBoxOfType2 == null || leafBoxOfType3 == null || parseHdlr(leafBoxOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType2.data;
        parsableByteArray.setPosition(12);
        int i10 = parsableByteArray.readInt();
        String[] strArr = new String[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i11] = parsableByteArray.readString(i12 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafBoxOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int i13 = parsableByteArray2.readInt();
            int i14 = parsableByteArray2.readInt() - 1;
            if (i14 < 0 || i14 >= i10) {
                a2.s(i14, "Skipped metadata with unknown key index: ", TAG);
            } else {
                MdtaMetadataEntry mdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + i13, strArr[i14]);
                if (mdtaMetadataEntryFromIlst != null) {
                    arrayList.add(mdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + i13);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i10, int i11, int i12, StsdData stsdData) {
        parsableByteArray.setPosition(i11 + 16);
        if (i10 == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String nullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (nullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i12).setSampleMimeType(nullTerminatedString).build();
            }
        }
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray parsableByteArray) {
        long unsignedInt;
        long unsignedInt2;
        parsableByteArray.setPosition(8);
        if (parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            unsignedInt = parsableByteArray.readUnsignedInt();
            unsignedInt2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedInt = parsableByteArray.readLong();
            unsignedInt2 = parsableByteArray.readLong();
        }
        return new Mp4TimestampData(unsignedInt, unsignedInt2, parsableByteArray.readUnsignedInt());
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.setPosition(i10 + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i10, int i11) {
        int i12 = i10 + 8;
        while (i12 - i10 < i11) {
            parsableByteArray.setPosition(i12);
            int i13 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i12, i13 + i12);
            }
            i12 += i13;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i10, int i11) throws ParserException {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i10 < i11) {
            parsableByteArray.setPosition(position);
            int i12 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i12 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i12)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i12;
        }
        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i10, int i11, String str) {
        int i12;
        int i13;
        int i14 = i10 + 8;
        while (true) {
            byte[] bArr = null;
            if (i14 - i10 >= i11) {
                return null;
            }
            parsableByteArray.setPosition(i14);
            int i15 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullBoxVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i13 = 0;
                    i12 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i12 = unsignedByte & 15;
                    i13 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i13, i12, bArr);
            }
            i14 += i15;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0203, code lost:
    
        r4 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017e  */
    /* JADX WARN: Type inference failed for: r26v2 */
    /* JADX WARN: Type inference failed for: r26v3 */
    /* JADX WARN: Type inference failed for: r26v4 */
    /* JADX WARN: Type inference failed for: r26v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r26v8 */
    /* JADX WARN: Type inference failed for: r29v0 */
    /* JADX WARN: Type inference failed for: r29v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r29v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.extractor.mp4.TrackSampleTable parseStbl(androidx.media3.extractor.mp4.Track r43, androidx.media3.container.Mp4Box.ContainerBox r44, androidx.media3.extractor.GaplessInfoHolder r45, boolean r46) throws androidx.media3.common.ParserException {
        /*
            Method dump skipped, instruction units count: 1618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseStbl(androidx.media3.extractor.mp4.Track, androidx.media3.container.Mp4Box$ContainerBox, androidx.media3.extractor.GaplessInfoHolder, boolean):androidx.media3.extractor.mp4.TrackSampleTable");
    }

    private static EyesData parseStereoViewBox(ParsableByteArray parsableByteArray, int i10, int i11) throws ParserException {
        parsableByteArray.setPosition(i10 + 8);
        int position = parsableByteArray.getPosition();
        while (position - i10 < i11) {
            parsableByteArray.setPosition(position);
            int i12 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i12 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1937011305) {
                parsableByteArray.skipBytes(4);
                int unsignedByte = parsableByteArray.readUnsignedByte();
                return new EyesData(new StriData((unsignedByte & 1) == 1, (unsignedByte & 2) == 2, (unsignedByte & 8) == 8));
            }
            position += i12;
        }
        return null;
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, TkhdData tkhdData, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i10 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i10);
        for (int i11 = 0; i11 < i10; i11++) {
            int position = parsableByteArray.getPosition();
            int i12 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i12 > 0, "childAtomSize must be positive");
            int i13 = parsableByteArray.readInt();
            if (i13 == 1635148593 || i13 == 1635148595 || i13 == 1701733238 || i13 == 1831958048 || i13 == 1836070006 || i13 == 1752589105 || i13 == 1751479857 || i13 == 1932670515 || i13 == 1211250227 || i13 == 1748121139 || i13 == 1987063864 || i13 == 1987063865 || i13 == 1635135537 || i13 == 1685479798 || i13 == 1685479729 || i13 == 1685481573 || i13 == 1685481521 || i13 == 1634760241) {
                parseVideoSampleEntry(parsableByteArray, i13, position, i12, tkhdData.id, str, tkhdData.rotationDegrees, drmInitData, stsdData, i11);
            } else if (i13 == 1836069985 || i13 == 1701733217 || i13 == 1633889587 || i13 == 1700998451 || i13 == 1633889588 || i13 == 1835823201 || i13 == 1685353315 || i13 == 1685353317 || i13 == 1685353320 || i13 == 1685353324 || i13 == 1685353336 || i13 == 1935764850 || i13 == 1935767394 || i13 == 1819304813 || i13 == 1936684916 || i13 == 1953984371 || i13 == 778924082 || i13 == 778924083 || i13 == 1835557169 || i13 == 1835560241 || i13 == 1634492771 || i13 == 1634492791 || i13 == 1970037111 || i13 == 1332770163 || i13 == 1716281667 || i13 == 1767992678 || i13 == 1768973165 || i13 == 1718641517) {
                parseAudioSampleEntry(parsableByteArray, i13, position, i12, tkhdData.id, str, z, drmInitData, stsdData, i11);
            } else if (i13 == 1414810956 || i13 == 1954034535 || i13 == 2004251764 || i13 == 1937010800 || i13 == 1664495672 || i13 == 1836070003) {
                StsdData stsdData2 = stsdData;
                parseTextSampleEntry(parsableByteArray, i13, position, i12, tkhdData, str, stsdData2);
                stsdData = stsdData2;
            } else if (i13 == 1835365492) {
                parseMetaDataSampleEntry(parsableByteArray, i13, position, tkhdData.id, stsdData);
            } else if (i13 == 1667329389) {
                stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
            }
            parsableByteArray.setPosition(position + i12);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i10, int i11, int i12, TkhdData tkhdData, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i11 + 16);
        String str2 = MimeTypes.APPLICATION_TTML;
        o3 o3VarS = null;
        long j10 = Long.MAX_VALUE;
        if (i10 != 1414810956) {
            if (i10 == 1954034535) {
                int i13 = i12 - 16;
                byte[] bArr = new byte[i13];
                parsableByteArray.readBytes(bArr, 0, i13);
                o3VarS = h1.s(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i10 == 2004251764) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i10 == 1937010800) {
                j10 = 0;
            } else if (i10 == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                if (i10 != 1836070003) {
                    throw new IllegalStateException();
                }
                int position = parsableByteArray.getPosition();
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() == 1702061171) {
                    EsdsData esdsFromParent = parseEsdsFromParent(parsableByteArray, position);
                    if (esdsFromParent.initializationData == null || esdsFromParent.initializationData.length != 64) {
                        return;
                    }
                    o3VarS = h1.s(Util.getUtf8Bytes(formatVobsubIdx(esdsFromParent.initializationData, tkhdData.width, tkhdData.height)));
                    str2 = MimeTypes.APPLICATION_VOBSUB;
                } else {
                    str2 = null;
                }
            }
        }
        if (str2 != null) {
            stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j10).setInitializationData(o3VarS).build();
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j10;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullBoxVersion == 0 ? 8 : 16);
        int i10 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i11 = fullBoxVersion == 0 ? 4 : 8;
        int i12 = 0;
        while (true) {
            j10 = C.TIME_UNSET;
            if (i12 >= i11) {
                parsableByteArray.skipBytes(i11);
                break;
            }
            if (parsableByteArray.getData()[position + i12] != -1) {
                long unsignedInt = fullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                if (unsignedInt != 0) {
                    j10 = unsignedInt;
                }
            } else {
                i12++;
            }
        }
        parsableByteArray.skipBytes(10);
        int i13 = 0;
        long j11 = j10;
        int unsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(4);
        int i14 = parsableByteArray.readInt();
        int i15 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i16 = parsableByteArray.readInt();
        int i17 = parsableByteArray.readInt();
        if (i14 == 0 && i15 == 65536 && ((i16 == -65536 || i16 == 65536) && i17 == 0)) {
            i13 = 90;
        } else if (i14 == 0 && i15 == -65536 && ((i16 == 65536 || i16 == -65536) && i17 == 0)) {
            i13 = 270;
        } else if ((i14 == -65536 || i14 == 65536) && i15 == 0 && i16 == 0 && i17 == -65536) {
            i13 = 180;
        }
        int i18 = i13;
        parsableByteArray.skipBytes(16);
        short s10 = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        return new TkhdData(i10, j11, unsignedShort, i18, s10, parsableByteArray.readShort());
    }

    public static Track parseTrak(Mp4Box.ContainerBox containerBox, Mp4Box.LeafBox leafBox, long j10, DrmInitData drmInitData, boolean z, boolean z5) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Format formatBuild;
        Mp4Box.ContainerBox containerBoxOfType;
        Pair<long[], long[]> edts;
        Mp4Box.ContainerBox containerBoxOfType2 = containerBox.getContainerBoxOfType(Mp4Box.TYPE_mdia);
        containerBoxOfType2.getClass();
        Mp4Box.LeafBox leafBoxOfType = containerBoxOfType2.getLeafBoxOfType(Mp4Box.TYPE_hdlr);
        leafBoxOfType.getClass();
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(leafBoxOfType.data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_tkhd);
        leafBoxOfType2.getClass();
        TkhdData tkhd = parseTkhd(leafBoxOfType2.data);
        long jScaleLargeTimestamp = C.TIME_UNSET;
        long j11 = j10 == C.TIME_UNSET ? tkhd.duration : j10;
        long j12 = parseMvhd(leafBox.data).timescale;
        if (j11 != C.TIME_UNSET) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j11, 1000000L, j12);
        }
        long j13 = jScaleLargeTimestamp;
        Mp4Box.ContainerBox containerBoxOfType3 = containerBoxOfType2.getContainerBoxOfType(Mp4Box.TYPE_minf);
        containerBoxOfType3.getClass();
        Mp4Box.ContainerBox containerBoxOfType4 = containerBoxOfType3.getContainerBoxOfType(Mp4Box.TYPE_stbl);
        containerBoxOfType4.getClass();
        Mp4Box.LeafBox leafBoxOfType3 = containerBoxOfType2.getLeafBoxOfType(Mp4Box.TYPE_mdhd);
        leafBoxOfType3.getClass();
        MdhdData mdhd = parseMdhd(leafBoxOfType3.data);
        Mp4Box.LeafBox leafBoxOfType4 = containerBoxOfType4.getLeafBoxOfType(Mp4Box.TYPE_stsd);
        if (leafBoxOfType4 == null) {
            Log.w(TAG, "Ignoring track where sample table (stbl) box is missing a sample description (stsd).");
            return null;
        }
        StsdData stsd = parseStsd(leafBoxOfType4.data, tkhd, mdhd.language, drmInitData, z5);
        if (z || (containerBoxOfType = containerBox.getContainerBoxOfType(Mp4Box.TYPE_edts)) == null || (edts = parseEdts(containerBoxOfType)) == null) {
            jArr = null;
            jArr2 = null;
        } else {
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        if (tkhd.alternateGroup != 0) {
            Mp4AlternateGroupData mp4AlternateGroupData = new Mp4AlternateGroupData(tkhd.alternateGroup);
            Format.Builder builderBuildUpon = stsd.format.buildUpon();
            Metadata metadata = stsd.format.metadata;
            formatBuild = builderBuildUpon.setMetadata(metadata != null ? metadata.copyWithAppendedEntries(mp4AlternateGroupData) : new Metadata(mp4AlternateGroupData)).build();
        } else {
            formatBuild = stsd.format;
        }
        return new Track(tkhd.id, trackTypeForHdlr, mdhd.timescale, j12, j13, mdhd.mediaDurationUs, formatBuild, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder, long j10, DrmInitData drmInitData, boolean z, boolean z5, m<Track, Track> mVar, boolean z10) throws ParserException {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < containerBox.containerChildren.size(); i10++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i10);
            if (containerBox2.type == 1953653099) {
                Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd);
                leafBoxOfType.getClass();
                Track trackApply = mVar.apply(parseTrak(containerBox2, leafBoxOfType, j10, drmInitData, z, z5));
                if (trackApply != null) {
                    Mp4Box.ContainerBox containerBoxOfType = containerBox2.getContainerBoxOfType(Mp4Box.TYPE_mdia);
                    containerBoxOfType.getClass();
                    Mp4Box.ContainerBox containerBoxOfType2 = containerBoxOfType.getContainerBoxOfType(Mp4Box.TYPE_minf);
                    containerBoxOfType2.getClass();
                    Mp4Box.ContainerBox containerBoxOfType3 = containerBoxOfType2.getContainerBoxOfType(Mp4Box.TYPE_stbl);
                    containerBoxOfType3.getClass();
                    arrayList.add(parseStbl(trackApply, containerBoxOfType3, gaplessInfoHolder, z10));
                }
            }
        }
        return arrayList;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox leafBox) {
        ParsableByteArray parsableByteArray = leafBox.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i10 = parsableByteArray.readInt();
            int i11 = parsableByteArray.readInt();
            if (i11 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(parsableByteArray, position + i10));
            } else if (i11 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(parsableByteArray, position + i10));
            } else if (i11 == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(parsableByteArray));
            }
            parsableByteArray.setPosition(position + i10);
        }
        return metadata;
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i10) {
            int position = parsableByteArray.getPosition();
            int i11 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i11);
            }
            parsableByteArray.setPosition(position + i11);
        }
        return null;
    }

    public static VexuData parseVideoExtendedUsageBox(ParsableByteArray parsableByteArray, int i10, int i11) throws ParserException {
        parsableByteArray.setPosition(i10 + 8);
        int position = parsableByteArray.getPosition();
        EyesData stereoViewBox = null;
        while (position - i10 < i11) {
            parsableByteArray.setPosition(position);
            int i12 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i12 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1702454643) {
                stereoViewBox = parseStereoViewBox(parsableByteArray, position, i12);
            }
            position += i12;
        }
        if (stereoViewBox == null) {
            return null;
        }
        return new VexuData(stereoViewBox);
    }

    /*  JADX ERROR: Type inference failed with stack overflow
        jadx.core.utils.exceptions.JadxOverflowException
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private static void parseVideoSampleEntry(androidx.media3.common.util.ParsableByteArray r41, int r42, int r43, int r44, int r45, java.lang.String r46, int r47, androidx.media3.common.DrmInitData r48, androidx.media3.extractor.mp4.BoxParser.StsdData r49, int r50) throws androidx.media3.common.ParserException {
        /*
            Method dump skipped, instruction units count: 1772
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseVideoSampleEntry(androidx.media3.common.util.ParsableByteArray, int, int, int, int, java.lang.String, int, androidx.media3.common.DrmInitData, androidx.media3.extractor.mp4.BoxParser$StsdData, int):void");
    }

    private static Metadata parseXyz(ParsableByteArray parsableByteArray) {
        short s10 = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        String string = parsableByteArray.readString(s10);
        int iMax = Math.max(string.lastIndexOf(43), string.lastIndexOf(45));
        try {
            return new Metadata(new Mp4LocationData(Float.parseFloat(string.substring(0, iMax)), Float.parseFloat(string.substring(iMax, string.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static int vobsubYuvToRgb(int i10) {
        int i11 = (i10 >> 16) & 255;
        int i12 = ((i10 >> 8) & 255) - 128;
        int i13 = (i10 & 255) - 128;
        return Util.constrainValue(((i13 * 17790) / 10000) + i11, 0, 255) | (Util.constrainValue(((i12 * 14075) / 10000) + i11, 0, 255) << 16) | (Util.constrainValue((i11 - ((i13 * 3455) / 10000)) - ((i12 * 7169) / 10000), 0, 255) << 8);
    }
}
