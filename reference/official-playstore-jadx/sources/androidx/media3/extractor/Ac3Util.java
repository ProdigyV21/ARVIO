package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class Ac3Util {
    public static final int AC3_MAX_RATE_BYTES_PER_SECOND = 80000;
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    public static final int E_AC3_MAX_RATE_BYTES_PER_SECOND = 768000;
    public static final int TRUEHD_MAX_RATE_BYTES_PER_SECOND = 3062500;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 16;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 10;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, PsExtractor.AUDIO_STREAM, 224, 256, 320, RendererCapabilities.DECODER_SUPPORT_MASK, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED, 121, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static final class SyncFrameInfo {
        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int bitrate;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface StreamType {
        }

        private SyncFrameInfo(String str, int i10, int i11, int i12, int i13, int i14, int i15) {
            this.mimeType = str;
            this.streamType = i10;
            this.channelCount = i11;
            this.sampleRate = i12;
            this.frameSize = i13;
            this.sampleCount = i14;
            this.bitrate = i15;
        }
    }

    private Ac3Util() {
    }

    private static int calculateEac3Bitrate(int i10, int i11, int i12) {
        return (i10 * i11) / (i12 * 32);
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit() - 10;
        for (int i10 = iPosition; i10 <= iLimit; i10++) {
            if ((Util.getBigEndianInt(byteBuffer, i10 + 4) & (-2)) == -126718022) {
                return i10 - iPosition;
            }
        }
        return -1;
    }

    private static int getAc3SyncframeSize(int i10, int i11) {
        int i12 = i11 / 2;
        if (i10 < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i10 >= iArr.length || i11 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i12 >= iArr2.length) {
            return -1;
        }
        int i13 = iArr[i10];
        if (i13 == 44100) {
            return ((i11 % 2) + iArr2[i12]) * 2;
        }
        int i14 = BITRATE_BY_HALF_FRMSIZECOD[i12];
        return i13 == 32000 ? i14 * 6 : i14 * 4;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i10 = SAMPLE_RATE_BY_FSCOD[parsableBitArray.readBits(2)];
        parsableBitArray.skipBits(8);
        int i11 = CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)];
        if (parsableBitArray.readBits(1) != 0) {
            i11++;
        }
        int i12 = BITRATE_BY_HALF_FRMSIZECOD[parsableBitArray.readBits(5)] * 1000;
        parsableBitArray.byteAlign();
        parsableByteArray.setPosition(parsableBitArray.getBytePosition());
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_AC3).setChannelCount(i11).setSampleRate(i10).setDrmInitData(drmInitData).setLanguage(str2).setAverageBitrate(i12).setPeakBitrate(i12).build();
    }

    public static int parseAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        if (((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) > 10) {
            return BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? (byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4 : 3] * 256;
        }
        return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int ac3SyncframeSize;
        int i10;
        int i11;
        int i12;
        String str;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int position = parsableBitArray.getPosition();
        parsableBitArray.skipBits(40);
        boolean z = parsableBitArray.readBits(5) > 10;
        parsableBitArray.setPosition(position);
        int i19 = -1;
        if (z) {
            parsableBitArray.skipBits(16);
            int bits = parsableBitArray.readBits(2);
            if (bits == 0) {
                i19 = 0;
            } else if (bits == 1) {
                i19 = 1;
            } else if (bits == 2) {
                i19 = 2;
            }
            parsableBitArray.skipBits(3);
            ac3SyncframeSize = (parsableBitArray.readBits(11) + 1) * 2;
            int bits2 = parsableBitArray.readBits(2);
            if (bits2 == 3) {
                i10 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
                i14 = 3;
                i15 = 6;
            } else {
                int bits3 = parsableBitArray.readBits(2);
                int i20 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[bits3];
                i14 = bits3;
                i10 = SAMPLE_RATE_BY_FSCOD[bits2];
                i15 = i20;
            }
            i12 = i15 * 256;
            int iCalculateEac3Bitrate = calculateEac3Bitrate(ac3SyncframeSize, i10, i15);
            int bits4 = parsableBitArray.readBits(3);
            boolean bit = parsableBitArray.readBit();
            i11 = CHANNEL_COUNT_BY_ACMOD[bits4] + (bit ? 1 : 0);
            parsableBitArray.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(8);
            }
            if (bits4 == 0) {
                parsableBitArray.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
            }
            if (i19 == 1 && parsableBitArray.readBit()) {
                parsableBitArray.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (bits4 > 2) {
                    parsableBitArray.skipBits(2);
                }
                if ((bits4 & 1) == 0 || bits4 <= 2) {
                    i17 = 6;
                } else {
                    i17 = 6;
                    parsableBitArray.skipBits(6);
                }
                if ((bits4 & 4) != 0) {
                    parsableBitArray.skipBits(i17);
                }
                if (bit && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(5);
                }
                if (i19 == 0) {
                    if (parsableBitArray.readBit()) {
                        i18 = 6;
                        parsableBitArray.skipBits(6);
                    } else {
                        i18 = 6;
                    }
                    if (bits4 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(i18);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(i18);
                    }
                    int bits5 = parsableBitArray.readBits(2);
                    if (bits5 == 1) {
                        parsableBitArray.skipBits(5);
                    } else if (bits5 == 2) {
                        parsableBitArray.skipBits(12);
                    } else if (bits5 == 3) {
                        int bits6 = parsableBitArray.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(4);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray.skipBits((bits6 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (bits4 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                        if (bits4 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (i14 == 0) {
                            parsableBitArray.skipBits(5);
                        } else {
                            for (int i21 = 0; i21 < i15; i21++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(5);
                if (bits4 == 2) {
                    parsableBitArray.skipBits(4);
                }
                if (bits4 >= 6) {
                    parsableBitArray.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
                if (bits4 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
                if (bits2 < 3) {
                    parsableBitArray.skipBit();
                }
            }
            if (i19 == 0 && i14 != 3) {
                parsableBitArray.skipBit();
            }
            if (i19 == 2 && (i14 == 3 || parsableBitArray.readBit())) {
                i16 = 6;
                parsableBitArray.skipBits(6);
            } else {
                i16 = 6;
            }
            str = (parsableBitArray.readBit() && parsableBitArray.readBits(i16) == 1 && parsableBitArray.readBits(8) == 1) ? MimeTypes.AUDIO_E_AC3_JOC : MimeTypes.AUDIO_E_AC3;
            i13 = iCalculateEac3Bitrate;
        } else {
            parsableBitArray.skipBits(32);
            int bits7 = parsableBitArray.readBits(2);
            String str2 = bits7 == 3 ? null : MimeTypes.AUDIO_AC3;
            int bits8 = parsableBitArray.readBits(6);
            int i22 = BITRATE_BY_HALF_FRMSIZECOD[bits8 / 2] * 1000;
            ac3SyncframeSize = getAc3SyncframeSize(bits7, bits8);
            parsableBitArray.skipBits(8);
            int bits9 = parsableBitArray.readBits(3);
            if ((bits9 & 1) != 0 && bits9 != 1) {
                parsableBitArray.skipBits(2);
            }
            if ((bits9 & 4) != 0) {
                parsableBitArray.skipBits(2);
            }
            if (bits9 == 2) {
                parsableBitArray.skipBits(2);
            }
            int[] iArr = SAMPLE_RATE_BY_FSCOD;
            i10 = bits7 < iArr.length ? iArr[bits7] : -1;
            i11 = CHANNEL_COUNT_BY_ACMOD[bits9] + (parsableBitArray.readBit() ? 1 : 0);
            i12 = AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
            str = str2;
            i13 = i22;
        }
        return new SyncFrameInfo(str, i19, i11, i10, ac3SyncframeSize, i12, i13);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) * 2;
        }
        byte b10 = bArr[4];
        return getAc3SyncframeSize((b10 & 192) >> 6, b10 & 63);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.common.Format parseEAc3AnnexFFormat(androidx.media3.common.util.ParsableByteArray r7, java.lang.String r8, java.lang.String r9, androidx.media3.common.DrmInitData r10) {
        /*
            androidx.media3.common.util.ParsableBitArray r0 = new androidx.media3.common.util.ParsableBitArray
            r0.<init>()
            r0.reset(r7)
            r1 = 13
            int r1 = r0.readBits(r1)
            int r1 = r1 * 1000
            r2 = 3
            r0.skipBits(r2)
            r3 = 2
            int r3 = r0.readBits(r3)
            int[] r4 = androidx.media3.extractor.Ac3Util.SAMPLE_RATE_BY_FSCOD
            r3 = r4[r3]
            r4 = 10
            r0.skipBits(r4)
            int[] r4 = androidx.media3.extractor.Ac3Util.CHANNEL_COUNT_BY_ACMOD
            int r5 = r0.readBits(r2)
            r4 = r4[r5]
            r5 = 1
            int r6 = r0.readBits(r5)
            if (r6 == 0) goto L33
            int r4 = r4 + 1
        L33:
            r0.skipBits(r2)
            r2 = 4
            int r2 = r0.readBits(r2)
            r0.skipBits(r5)
            if (r2 <= 0) goto L4f
            r2 = 6
            r0.skipBits(r2)
            int r2 = r0.readBits(r5)
            if (r2 == 0) goto L4c
            int r4 = r4 + 2
        L4c:
            r0.skipBits(r5)
        L4f:
            int r2 = r0.bitsLeft()
            r6 = 7
            if (r2 <= r6) goto L62
            r0.skipBits(r6)
            int r2 = r0.readBits(r5)
            if (r2 == 0) goto L62
            java.lang.String r2 = "audio/eac3-joc"
            goto L64
        L62:
            java.lang.String r2 = "audio/eac3"
        L64:
            r0.byteAlign()
            int r0 = r0.getBytePosition()
            r7.setPosition(r0)
            androidx.media3.common.Format$Builder r7 = new androidx.media3.common.Format$Builder
            r7.<init>()
            androidx.media3.common.Format$Builder r7 = r7.setId(r8)
            androidx.media3.common.Format$Builder r7 = r7.setSampleMimeType(r2)
            androidx.media3.common.Format$Builder r7 = r7.setChannelCount(r4)
            androidx.media3.common.Format$Builder r7 = r7.setSampleRate(r3)
            androidx.media3.common.Format$Builder r7 = r7.setDrmInitData(r10)
            androidx.media3.common.Format$Builder r7 = r7.setLanguage(r9)
            androidx.media3.common.Format$Builder r7 = r7.setPeakBitrate(r1)
            androidx.media3.common.Format r7 = r7.build()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.Ac3Util.parseEAc3AnnexFFormat(androidx.media3.common.util.ParsableByteArray, java.lang.String, java.lang.String, androidx.media3.common.DrmInitData):androidx.media3.common.Format");
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b10 = bArr[7];
            if ((b10 & 254) == 186) {
                return 40 << ((bArr[(b10 & 255) == 187 ? '\t' : '\b'] >> 4) & 7);
            }
        }
        return 0;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer byteBuffer, int i10) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i10) + ((byteBuffer.get((byteBuffer.position() + i10) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7);
    }
}
