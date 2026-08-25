package androidx.media3.extractor;

import androidx.compose.material3.MenuKt;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class AacUtil {
    public static final int AAC_ELD_MAX_RATE_BYTES_PER_SECOND = 8000;
    public static final int AAC_HE_AUDIO_SAMPLE_COUNT = 2048;
    public static final int AAC_HE_V2_MAX_RATE_BYTES_PER_SECOND = 7000;
    public static final int AAC_LC_AUDIO_SAMPLE_COUNT = 1024;
    public static final int AAC_LC_MAX_RATE_BYTES_PER_SECOND = 100000;
    public static final int AAC_LD_AUDIO_SAMPLE_COUNT = 512;
    public static final int AAC_XHE_AUDIO_SAMPLE_COUNT = 1024;
    public static final int AAC_XHE_MAX_RATE_BYTES_PER_SECOND = 256000;
    public static final int AUDIO_OBJECT_TYPE_AAC_ELD = 23;
    public static final int AUDIO_OBJECT_TYPE_AAC_ER_BSAC = 22;
    public static final int AUDIO_OBJECT_TYPE_AAC_LC = 2;
    public static final int AUDIO_OBJECT_TYPE_AAC_PS = 29;
    public static final int AUDIO_OBJECT_TYPE_AAC_SBR = 5;
    public static final int AUDIO_OBJECT_TYPE_AAC_XHE = 42;
    private static final int AUDIO_OBJECT_TYPE_ESCAPE = 31;
    private static final int AUDIO_SPECIFIC_CONFIG_CHANNEL_CONFIGURATION_INVALID = -1;
    private static final int AUDIO_SPECIFIC_CONFIG_FREQUENCY_INDEX_ARBITRARY = 15;
    private static final String CODECS_STRING_PREFIX = "mp4a.40.";
    private static final String TAG = "AacUtil";
    public static final int AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND = 16000;
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = {96000, 88200, 64000, OpusUtil.SAMPLE_RATE, 44100, 32000, 24000, 22050, AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 12000, 11025, 8000, 7350};
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AacAudioObjectType {
    }

    public static final class Config {
        public final int channelCount;
        public final String codecs;
        public final int sampleRateHz;

        private Config(int i10, int i11, String str) {
            this.sampleRateHz = i10;
            this.channelCount = i11;
            this.codecs = str;
        }
    }

    private AacUtil() {
    }

    public static byte[] buildAacLcAudioSpecificConfig(int i10, int i11) {
        int i12 = 0;
        int i13 = -1;
        int i14 = 0;
        while (true) {
            int[] iArr = AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
            if (i14 >= iArr.length) {
                break;
            }
            if (i10 == iArr[i14]) {
                i13 = i14;
            }
            i14++;
        }
        int i15 = -1;
        while (true) {
            int[] iArr2 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE;
            if (i12 >= iArr2.length) {
                break;
            }
            if (i11 == iArr2[i12]) {
                i15 = i12;
            }
            i12++;
        }
        if (i10 == -1 || i15 == -1) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, i11, "Invalid sample rate or number of channels: ", ", "));
        }
        return buildAudioSpecificConfig(2, i13, i15);
    }

    public static byte[] buildAudioSpecificConfig(int i10, int i11, int i12) {
        return new byte[]{(byte) (((i10 << 3) & 248) | ((i11 >> 1) & 7)), (byte) (((i11 << 7) & 128) | ((i12 << 3) & MenuKt.InTransitionDuration))};
    }

    private static int getAudioObjectType(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(5);
        return bits == 31 ? parsableBitArray.readBits(6) + 32 : bits;
    }

    private static int getSamplingFrequency(ParsableBitArray parsableBitArray) throws ParserException {
        int bits = parsableBitArray.readBits(4);
        if (bits == 15) {
            if (parsableBitArray.bitsLeft() >= 24) {
                return parsableBitArray.readBits(24);
            }
            throw ParserException.createForMalformedContainer("AAC header insufficient data", null);
        }
        if (bits < 13) {
            return AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[bits];
        }
        throw ParserException.createForMalformedContainer("AAC header wrong Sampling Frequency Index", null);
    }

    public static Config parseAudioSpecificConfig(byte[] bArr) throws ParserException {
        return parseAudioSpecificConfig(new ParsableBitArray(bArr), false);
    }

    private static void parseGaSpecificConfig(ParsableBitArray parsableBitArray, int i10, int i11) {
        if (parsableBitArray.readBit()) {
            Log.w(TAG, "Unexpected frameLengthFlag = 1");
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(14);
        }
        boolean bit = parsableBitArray.readBit();
        if (i11 == 0) {
            throw new UnsupportedOperationException();
        }
        if (i10 == 6 || i10 == 20) {
            parsableBitArray.skipBits(3);
        }
        if (bit) {
            if (i10 == 22) {
                parsableBitArray.skipBits(16);
            }
            if (i10 == 17 || i10 == 19 || i10 == 20 || i10 == 23) {
                parsableBitArray.skipBits(3);
            }
            parsableBitArray.skipBits(1);
        }
    }

    public static Config parseAudioSpecificConfig(ParsableBitArray parsableBitArray, boolean z) throws ParserException {
        int audioObjectType = getAudioObjectType(parsableBitArray);
        int samplingFrequency = getSamplingFrequency(parsableBitArray);
        int bits = parsableBitArray.readBits(4);
        String strI = a0.c.i(audioObjectType, CODECS_STRING_PREFIX);
        if (audioObjectType == 5 || audioObjectType == 29) {
            samplingFrequency = getSamplingFrequency(parsableBitArray);
            audioObjectType = getAudioObjectType(parsableBitArray);
            if (audioObjectType == 22) {
                bits = parsableBitArray.readBits(4);
            }
        }
        if (z) {
            if (audioObjectType != 1 && audioObjectType != 2 && audioObjectType != 3 && audioObjectType != 4 && audioObjectType != 6 && audioObjectType != 7 && audioObjectType != 17) {
                switch (audioObjectType) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        throw ParserException.createForUnsupportedContainerFeature("Unsupported audio object type: " + audioObjectType);
                }
            }
            parseGaSpecificConfig(parsableBitArray, audioObjectType, bits);
            switch (audioObjectType) {
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int bits2 = parsableBitArray.readBits(2);
                    if (bits2 == 2 || bits2 == 3) {
                        throw ParserException.createForUnsupportedContainerFeature("Unsupported epConfig: " + bits2);
                    }
                    break;
            }
        }
        int i10 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[bits];
        if (i10 != -1) {
            return new Config(samplingFrequency, i10, strI);
        }
        throw ParserException.createForMalformedContainer(null, null);
    }
}
