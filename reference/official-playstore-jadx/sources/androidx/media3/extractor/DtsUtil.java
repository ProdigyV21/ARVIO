package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import io.ktor.network.sockets.DatagramKt;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class DtsUtil {
    public static final int DTS_EXPRESS_MAX_RATE_BITS_PER_SECOND = 768000;
    public static final int DTS_HD_MAX_RATE_BYTES_PER_SECOND = 2250000;
    private static final byte FIRST_BYTE_14B_BE = 31;
    private static final byte FIRST_BYTE_14B_LE = -1;
    private static final byte FIRST_BYTE_BE = 127;
    private static final byte FIRST_BYTE_EXTSS_BE = 100;
    private static final byte FIRST_BYTE_EXTSS_LE = 37;
    private static final byte FIRST_BYTE_LE = -2;
    private static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_BE = 113;
    private static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_LE = -24;
    private static final byte FIRST_BYTE_UHD_FTOC_SYNC_BE = 64;
    private static final byte FIRST_BYTE_UHD_FTOC_SYNC_LE = -14;
    public static final int FRAME_TYPE_CORE = 1;
    public static final int FRAME_TYPE_EXTENSION_SUBSTREAM = 2;
    public static final int FRAME_TYPE_UHD_NON_SYNC = 4;
    public static final int FRAME_TYPE_UHD_SYNC = 3;
    public static final int FRAME_TYPE_UNKNOWN = 0;
    private static final int SYNC_VALUE_14B_BE = 536864768;
    private static final int SYNC_VALUE_14B_LE = -14745368;
    private static final int SYNC_VALUE_BE = 2147385345;
    private static final int SYNC_VALUE_EXTSS_BE = 1683496997;
    private static final int SYNC_VALUE_EXTSS_LE = 622876772;
    private static final int SYNC_VALUE_LE = -25230976;
    private static final int SYNC_VALUE_UHD_FTOC_NONSYNC_BE = 1908687592;
    private static final int SYNC_VALUE_UHD_FTOC_NONSYNC_LE = -398277519;
    private static final int SYNC_VALUE_UHD_FTOC_SYNC_BE = 1078008818;
    private static final int SYNC_VALUE_UHD_FTOC_SYNC_LE = -233094848;
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, OpusUtil.SAMPLE_RATE, -1, -1};
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, PsExtractor.AUDIO_STREAM, 224, 256, RendererCapabilities.DECODER_SUPPORT_MASK, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    public static final int DTS_MAX_RATE_BYTES_PER_SECOND = 192000;
    private static final int[] SAMPLE_RATE_BY_INDEX = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, OpusUtil.SAMPLE_RATE, 96000, DTS_MAX_RATE_BYTES_PER_SECOND, 384000};
    private static final int[] UHD_FTOC_PAYLOAD_LENGTH_TABLE = {5, 8, 10, 12};
    private static final int[] UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = {6, 9, 12, 15};
    private static final int[] UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = {2, 4, 6, 8};
    private static final int[] UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = {9, 11, 13, 16};
    private static final int[] UHD_HEADER_SIZE_LENGTH_TABLE = {5, 8, 10, 12};

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DtsAudioMimeType {
    }

    public static final class DtsHeader {
        public final int bitrate;
        public final int channelCount;
        public final long frameDurationUs;
        public final int frameSize;
        public final String mimeType;
        public final int sampleRate;

        private DtsHeader(String str, int i10, int i11, int i12, long j10, int i13) {
            this.mimeType = str;
            this.channelCount = i10;
            this.sampleRate = i11;
            this.frameSize = i12;
            this.frameDurationUs = j10;
            this.bitrate = i13;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }

    private DtsUtil() {
    }

    private static void checkCrc(byte[] bArr, int i10) throws ParserException {
        int i11 = i10 - 2;
        if (((bArr[i10 - 1] & FIRST_BYTE_14B_LE) | ((bArr[i11] << 8) & DatagramKt.MAX_DATAGRAM_SIZE)) != Util.crc16(bArr, 0, i11, DatagramKt.MAX_DATAGRAM_SIZE)) {
            throw ParserException.createForMalformedContainer("CRC check failed", null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getDtsFrameSize(byte[] r7) {
        /*
            r0 = 0
            r1 = r7[r0]
            r2 = -2
            r3 = 7
            r4 = 6
            r5 = 1
            r6 = 4
            if (r1 == r2) goto L4f
            r2 = -1
            if (r1 == r2) goto L3e
            r2 = 31
            if (r1 == r2) goto L26
            r1 = 5
            r1 = r7[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            r2 = r7[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            r7 = r7[r3]
        L20:
            r7 = r7 & 240(0xf0, float:3.36E-43)
            int r7 = r7 >> r6
            r7 = r7 | r1
            int r7 = r7 + r5
            goto L5e
        L26:
            r0 = r7[r4]
            r0 = r0 & 3
            int r0 = r0 << 12
            r1 = r7[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 8
            r7 = r7[r1]
        L36:
            r7 = r7 & 60
            int r7 = r7 >> 2
            r7 = r7 | r0
            int r7 = r7 + r5
            r0 = r5
            goto L5e
        L3e:
            r0 = r7[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            r1 = r7[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 9
            r7 = r7[r1]
            goto L36
        L4f:
            r1 = r7[r6]
            r1 = r1 & 3
            int r1 = r1 << 12
            r2 = r7[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            r7 = r7[r4]
            goto L20
        L5e:
            if (r0 == 0) goto L64
            int r7 = r7 * 16
            int r7 = r7 / 14
        L64:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.DtsUtil.getDtsFrameSize(byte[]):int");
    }

    public static int getFrameType(int i10) {
        if (i10 == SYNC_VALUE_BE || i10 == SYNC_VALUE_LE || i10 == SYNC_VALUE_14B_BE || i10 == SYNC_VALUE_14B_LE) {
            return 1;
        }
        if (i10 == SYNC_VALUE_EXTSS_BE || i10 == SYNC_VALUE_EXTSS_LE) {
            return 2;
        }
        if (i10 == SYNC_VALUE_UHD_FTOC_SYNC_BE || i10 == SYNC_VALUE_UHD_FTOC_SYNC_LE) {
            return 3;
        }
        return (i10 == SYNC_VALUE_UHD_FTOC_NONSYNC_BE || i10 == SYNC_VALUE_UHD_FTOC_NONSYNC_LE) ? 4 : 0;
    }

    private static ParsableBitArray getNormalizedFrame(byte[] bArr) {
        byte b10 = bArr[0];
        if (b10 == 127 || b10 == 100 || b10 == 64 || b10 == 113) {
            return new ParsableBitArray(bArr);
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(bArrCopyOf)) {
            for (int i10 = 0; i10 < bArrCopyOf.length - 1; i10 += 2) {
                byte b11 = bArrCopyOf[i10];
                int i11 = i10 + 1;
                bArrCopyOf[i10] = bArrCopyOf[i11];
                bArrCopyOf[i11] = b11;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArrCopyOf);
        if (bArrCopyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(bArrCopyOf);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(bArrCopyOf);
        return parsableBitArray;
    }

    private static boolean isLittleEndianFrameHeader(byte[] bArr) {
        byte b10 = bArr[0];
        return b10 == -2 || b10 == -1 || b10 == 37 || b10 == -14 || b10 == -24;
    }

    public static boolean isSampleDtsHd(ExtractorInput extractorInput, int i10) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        if (!extractorInput.peekFully(parsableByteArray.getData(), 0, i10, true)) {
            return false;
        }
        extractorInput.resetPeekPosition();
        if (getFrameType(parsableByteArray.peekInt()) != 1 || parsableByteArray.bytesLeft() < 10) {
            return false;
        }
        byte[] bArr = new byte[10];
        parsableByteArray.readBytes(bArr, 0, 10);
        parsableByteArray.setPosition(0);
        int dtsFrameSize = getDtsFrameSize(bArr);
        if (dtsFrameSize > 0 && parsableByteArray.bytesLeft() >= dtsFrameSize + 4) {
            parsableByteArray.skipBytes(dtsFrameSize);
            if (getFrameType(parsableByteArray.readInt()) == 2) {
                return true;
            }
        }
        return false;
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        int i10;
        byte b10;
        int i11;
        byte b11;
        byte b12 = bArr[0];
        if (b12 != -2) {
            if (b12 == -1) {
                i10 = (bArr[4] & 7) << 4;
                b11 = bArr[7];
            } else if (b12 != 31) {
                i10 = (bArr[4] & 1) << 6;
                b10 = bArr[5];
            } else {
                i10 = (bArr[5] & 7) << 4;
                b11 = bArr[6];
            }
            i11 = b11 & 60;
            return (((i11 >> 2) | i10) + 1) * 32;
        }
        i10 = (bArr[5] & 1) << 6;
        b10 = bArr[4];
        i11 = b10 & 252;
        return (((i11 >> 2) | i10) + 1) * 32;
    }

    public static Format parseDtsFormat(byte[] bArr, String str, String str2, int i10, String str3, DrmInitData drmInitData) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(60);
        int i11 = CHANNELS_BY_AMODE[normalizedFrame.readBits(6)];
        int i12 = SAMPLE_RATE_BY_SFREQ[normalizedFrame.readBits(4)];
        int bits = normalizedFrame.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        int i13 = bits >= iArr.length ? -1 : (iArr[bits] * 1000) / 2;
        normalizedFrame.skipBits(10);
        return new Format.Builder().setId(str).setContainerMimeType(str3).setSampleMimeType(MimeTypes.AUDIO_DTS).setAverageBitrate(i13).setChannelCount(i11 + (normalizedFrame.readBits(2) > 0 ? 1 : 0)).setSampleRate(i12).setDrmInitData(drmInitData).setLanguage(str2).setRoleFlags(i10).build();
    }

    public static DtsHeader parseDtsHdHeader(byte[] bArr) throws ParserException {
        int i10;
        int i11;
        int bits;
        int i12;
        long jScaleLargeTimestamp;
        int i13;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(40);
        int bits2 = normalizedFrame.readBits(2);
        if (normalizedFrame.readBit()) {
            i10 = 20;
            i11 = 12;
        } else {
            i10 = 16;
            i11 = 8;
        }
        normalizedFrame.skipBits(i11);
        int bits3 = normalizedFrame.readBits(i10) + 1;
        boolean bit = normalizedFrame.readBit();
        int bits4 = -1;
        int i14 = 0;
        if (bit) {
            bits = normalizedFrame.readBits(2);
            int bits5 = (normalizedFrame.readBits(3) + 1) * 512;
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            int bits6 = normalizedFrame.readBits(3) + 1;
            int bits7 = normalizedFrame.readBits(3) + 1;
            if (bits6 != 1 || bits7 != 1) {
                throw ParserException.createForUnsupportedContainerFeature("Multiple audio presentations or assets not supported");
            }
            int i15 = bits2 + 1;
            int bits8 = normalizedFrame.readBits(i15);
            for (int i16 = 0; i16 < i15; i16++) {
                if (((bits8 >> i16) & 1) == 1) {
                    normalizedFrame.skipBits(8);
                }
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(2);
                int bits9 = (normalizedFrame.readBits(2) + 1) << 2;
                int bits10 = normalizedFrame.readBits(2) + 1;
                while (i14 < bits10) {
                    normalizedFrame.skipBits(bits9);
                    i14++;
                }
            }
            i14 = bits5;
        } else {
            bits = -1;
        }
        normalizedFrame.skipBits(i10);
        normalizedFrame.skipBits(12);
        if (bit) {
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(4);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(24);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBytes(normalizedFrame.readBits(10) + 1);
            }
            normalizedFrame.skipBits(5);
            i12 = SAMPLE_RATE_BY_INDEX[normalizedFrame.readBits(4)];
            bits4 = normalizedFrame.readBits(8) + 1;
        } else {
            i12 = C.RATE_UNSET_INT;
        }
        int i17 = i12;
        if (bit) {
            if (bits == 0) {
                i13 = 32000;
            } else if (bits == 1) {
                i13 = 44100;
            } else {
                if (bits != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported reference clock code in DTS HD header: " + bits, null);
                }
                i13 = OpusUtil.SAMPLE_RATE;
            }
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(i14, 1000000L, i13);
        } else {
            jScaleLargeTimestamp = C.TIME_UNSET;
        }
        return new DtsHeader(MimeTypes.AUDIO_DTS_EXPRESS, bits4, i17, bits3, jScaleLargeTimestamp, 0);
    }

    public static int parseDtsHdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(42);
        return normalizedFrame.readBits(normalizedFrame.readBit() ? 12 : 8) + 1;
    }

    public static DtsHeader parseDtsUhdHeader(byte[] bArr, AtomicInteger atomicInteger) throws ParserException {
        int bits;
        long jScaleLargeTimestamp;
        AtomicInteger atomicInteger2;
        int i10;
        int i11;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        int i12 = normalizedFrame.readBits(32) == SYNC_VALUE_UHD_FTOC_SYNC_BE ? 1 : 0;
        int unsignedVarInt = parseUnsignedVarInt(normalizedFrame, UHD_FTOC_PAYLOAD_LENGTH_TABLE, true) + 1;
        if (i12 == 0) {
            bits = C.RATE_UNSET_INT;
            jScaleLargeTimestamp = C.TIME_UNSET;
        } else {
            if (!normalizedFrame.readBit()) {
                throw ParserException.createForUnsupportedContainerFeature("Only supports full channel mask-based audio presentation");
            }
            checkCrc(bArr, unsignedVarInt);
            int bits2 = normalizedFrame.readBits(2);
            if (bits2 == 0) {
                i10 = 512;
            } else if (bits2 == 1) {
                i10 = 480;
            } else {
                if (bits2 != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported base duration index in DTS UHD header: " + bits2, null);
                }
                i10 = RendererCapabilities.DECODER_SUPPORT_MASK;
            }
            int bits3 = (normalizedFrame.readBits(3) + 1) * i10;
            int bits4 = normalizedFrame.readBits(2);
            if (bits4 == 0) {
                i11 = 32000;
            } else if (bits4 == 1) {
                i11 = 44100;
            } else {
                if (bits4 != 2) {
                    throw ParserException.createForMalformedContainer("Unsupported clock rate index in DTS UHD header: " + bits4, null);
                }
                i11 = OpusUtil.SAMPLE_RATE;
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            bits = (1 << normalizedFrame.readBits(2)) * i11;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(bits3, 1000000L, i11);
        }
        int i13 = bits;
        long j10 = jScaleLargeTimestamp;
        int unsignedVarInt2 = 0;
        for (int i14 = 0; i14 < i12; i14++) {
            unsignedVarInt2 += parseUnsignedVarInt(normalizedFrame, UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        if (i12 != 0) {
            atomicInteger2 = atomicInteger;
            atomicInteger2.set(parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_ID_LENGTH_TABLE, true));
        } else {
            atomicInteger2 = atomicInteger;
        }
        return new DtsHeader(MimeTypes.AUDIO_DTS_X, 2, i13, unsignedVarInt2 + (atomicInteger2.get() != 0 ? parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE, true) : 0) + unsignedVarInt, j10, 0);
    }

    public static int parseDtsUhdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(32);
        return parseUnsignedVarInt(normalizedFrame, UHD_HEADER_SIZE_LENGTH_TABLE, true) + 1;
    }

    private static int parseUnsignedVarInt(ParsableBitArray parsableBitArray, int[] iArr, boolean z) {
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < 3 && parsableBitArray.readBit(); i12++) {
            i11++;
        }
        if (z) {
            int i13 = 0;
            while (i10 < i11) {
                i13 += 1 << iArr[i10];
                i10++;
            }
            i10 = i13;
        }
        return parsableBitArray.readBits(iArr[i11]) + i10;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        int i10;
        byte b10;
        int i11;
        byte b11;
        if (byteBuffer.getInt(0) == SYNC_VALUE_UHD_FTOC_SYNC_LE || byteBuffer.getInt(0) == SYNC_VALUE_UHD_FTOC_NONSYNC_LE) {
            return 1024;
        }
        if (byteBuffer.getInt(0) == SYNC_VALUE_EXTSS_LE) {
            return 4096;
        }
        int iPosition = byteBuffer.position();
        byte b12 = byteBuffer.get(iPosition);
        if (b12 != -2) {
            if (b12 == -1) {
                i10 = (byteBuffer.get(iPosition + 4) & 7) << 4;
                b11 = byteBuffer.get(iPosition + 7);
            } else if (b12 != 31) {
                i10 = (byteBuffer.get(iPosition + 4) & 1) << 6;
                b10 = byteBuffer.get(iPosition + 5);
            } else {
                i10 = (byteBuffer.get(iPosition + 5) & 7) << 4;
                b11 = byteBuffer.get(iPosition + 6);
            }
            i11 = b11 & 60;
            return (((i11 >> 2) | i10) + 1) * 32;
        }
        i10 = (byteBuffer.get(iPosition + 5) & 1) << 6;
        b10 = byteBuffer.get(iPosition + 4);
        i11 = b10 & 252;
        return (((i11 >> 2) | i10) + 1) * 32;
    }
}
