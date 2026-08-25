package androidx.media3.extractor;

import androidx.media3.common.MimeTypes;

/* JADX INFO: loaded from: classes3.dex */
public final class MpegAudioUtil {
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final int SAMPLES_PER_FRAME_L1 = 384;
    private static final int SAMPLES_PER_FRAME_L2 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V1 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V2 = 576;
    private static final String[] MIME_TYPE_BY_LAYER = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] SAMPLING_RATE_V1 = {44100, OpusUtil.SAMPLE_RATE, 32000};
    private static final int[] BITRATE_V1_L1 = {32000, 64000, 96000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] BITRATE_V2_L1 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000, 176000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] BITRATE_V1_L2 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000, 384000};
    public static final int MAX_RATE_BYTES_PER_SECOND = 40000;
    private static final int[] BITRATE_V1_L3 = {32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000};
    private static final int[] BITRATE_V2 = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 24000, 32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000};

    public static final class Header {
        public int bitrate;
        public int channels;
        public int frameSize;
        public String mimeType;
        public int sampleRate;
        public int samplesPerFrame;
        public int version;

        public Header() {
        }

        public boolean setForHeaderData(int i10) {
            int i11;
            int i12;
            int i13;
            int i14;
            if (!MpegAudioUtil.isMagicPresent(i10) || (i11 = (i10 >>> 19) & 3) == 1 || (i12 = (i10 >>> 17) & 3) == 0 || (i13 = (i10 >>> 12) & 15) == 0 || i13 == 15 || (i14 = (i10 >>> 10) & 3) == 3) {
                return false;
            }
            this.version = i11;
            this.mimeType = MpegAudioUtil.MIME_TYPE_BY_LAYER[3 - i12];
            int i15 = MpegAudioUtil.SAMPLING_RATE_V1[i14];
            this.sampleRate = i15;
            if (i11 == 2) {
                this.sampleRate = i15 / 2;
            } else if (i11 == 0) {
                this.sampleRate = i15 / 4;
            }
            int i16 = (i10 >>> 9) & 1;
            this.samplesPerFrame = MpegAudioUtil.getFrameSizeInSamples(i11, i12);
            if (i12 == 3) {
                int i17 = i11 == 3 ? MpegAudioUtil.BITRATE_V1_L1[i13 - 1] : MpegAudioUtil.BITRATE_V2_L1[i13 - 1];
                this.bitrate = i17;
                this.frameSize = (((i17 * 12) / this.sampleRate) + i16) * 4;
            } else {
                if (i11 == 3) {
                    int i18 = i12 == 2 ? MpegAudioUtil.BITRATE_V1_L2[i13 - 1] : MpegAudioUtil.BITRATE_V1_L3[i13 - 1];
                    this.bitrate = i18;
                    this.frameSize = ((i18 * 144) / this.sampleRate) + i16;
                } else {
                    int i19 = MpegAudioUtil.BITRATE_V2[i13 - 1];
                    this.bitrate = i19;
                    this.frameSize = (((i12 == 1 ? 72 : 144) * i19) / this.sampleRate) + i16;
                }
            }
            this.channels = ((i10 >> 6) & 3) == 3 ? 1 : 2;
            return true;
        }

        public Header(Header header) {
            this.version = header.version;
            this.mimeType = header.mimeType;
            this.frameSize = header.frameSize;
            this.sampleRate = header.sampleRate;
            this.channels = header.channels;
            this.bitrate = header.bitrate;
            this.samplesPerFrame = header.samplesPerFrame;
        }
    }

    private MpegAudioUtil() {
    }

    public static int getFrameSize(int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        if (!isMagicPresent(i10) || (i11 = (i10 >>> 19) & 3) == 1 || (i12 = (i10 >>> 17) & 3) == 0 || (i13 = (i10 >>> 12) & 15) == 0 || i13 == 15 || (i14 = (i10 >>> 10) & 3) == 3) {
            return -1;
        }
        int i15 = SAMPLING_RATE_V1[i14];
        if (i11 == 2) {
            i15 /= 2;
        } else if (i11 == 0) {
            i15 /= 4;
        }
        int i16 = (i10 >>> 9) & 1;
        if (i12 == 3) {
            return ((((i11 == 3 ? BITRATE_V1_L1[i13 - 1] : BITRATE_V2_L1[i13 - 1]) * 12) / i15) + i16) * 4;
        }
        int i17 = i11 == 3 ? i12 == 2 ? BITRATE_V1_L2[i13 - 1] : BITRATE_V1_L3[i13 - 1] : BITRATE_V2[i13 - 1];
        if (i11 == 3) {
            return ((i17 * 144) / i15) + i16;
        }
        return (((i12 == 1 ? 72 : 144) * i17) / i15) + i16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getFrameSizeInSamples(int i10, int i11) {
        if (i11 == 1) {
            if (i10 == 3) {
                return 1152;
            }
            return SAMPLES_PER_FRAME_L3_V2;
        }
        if (i11 == 2) {
            return 1152;
        }
        if (i11 == 3) {
            return 384;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMagicPresent(int i10) {
        return (i10 & (-2097152)) == -2097152;
    }

    public static int parseMpegAudioFrameSampleCount(int i10) {
        int i11;
        int i12;
        if (!isMagicPresent(i10) || (i11 = (i10 >>> 19) & 3) == 1 || (i12 = (i10 >>> 17) & 3) == 0) {
            return -1;
        }
        int i13 = (i10 >>> 12) & 15;
        int i14 = (i10 >>> 10) & 3;
        if (i13 == 0 || i13 == 15 || i14 == 3) {
            return -1;
        }
        return getFrameSizeInSamples(i11, i12);
    }
}
