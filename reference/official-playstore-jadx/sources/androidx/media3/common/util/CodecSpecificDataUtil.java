package androidx.media3.common.util;

import android.util.Pair;
import androidx.fragment.app.a2;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.session.MediaUtils;
import com.google.common.collect.h1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class CodecSpecificDataUtil {
    private static final String CODEC_ID_AC4 = "ac-4";
    private static final String CODEC_ID_APV1 = "apv1";
    private static final String CODEC_ID_AV01 = "av01";
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_H263 = "s263";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final String CODEC_ID_IAMF = "iamf";
    private static final String CODEC_ID_MP4A = "mp4a";
    private static final String CODEC_ID_VP09 = "vp09";
    private static final int EXTENDED_PAR = 15;
    private static final int OBU_IA_CODEC_CONFIG = 0;
    private static final int OBU_IA_SEQUENCE_HEADER = 31;
    private static final int RECTANGULAR = 0;
    private static final String TAG = "CodecSpecificDataUtil";
    private static final int VISUAL_OBJECT_LAYER = 1;
    private static final int VISUAL_OBJECT_LAYER_START = 32;
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final String[] HEVC_GENERAL_PROFILE_SPACE_STRINGS = {"", "A", "B", "C"};
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");

    private CodecSpecificDataUtil() {
    }

    private static int ac4BitstreamAndPresentationVersionsToProfileConst(int i10, int i11) {
        if (i10 == 0) {
            if (i11 == 0) {
                return TsExtractor.TS_STREAM_TYPE_AIT;
            }
            return -1;
        }
        if (i10 == 1) {
            if (i11 == 0) {
                return 513;
            }
            return i11 == 1 ? 514 : -1;
        }
        if (i10 != 2) {
            return -1;
        }
        if (i11 == 1) {
            return AnalyticsListener.EVENT_DRM_KEYS_REMOVED;
        }
        if (i11 == 2) {
            return AnalyticsListener.EVENT_PLAYER_RELEASED;
        }
        return -1;
    }

    private static int ac4LevelNumberToConst(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        if (i10 == 2) {
            return 4;
        }
        if (i10 != 3) {
            return i10 != 4 ? -1 : 16;
        }
        return 8;
    }

    private static int av1LevelNumberToConst(int i10) {
        switch (i10) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return 8192;
            case 14:
                return 16384;
            case 15:
                return 32768;
            case 16:
                return 65536;
            case 17:
                return 131072;
            case 18:
                return MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
            case 19:
                return 524288;
            case 20:
                return ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
            case 21:
                return 2097152;
            case 22:
                return 4194304;
            case 23:
                return 8388608;
            default:
                return -1;
        }
    }

    private static int avcLevelNumberToConst(int i10) {
        switch (i10) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            default:
                switch (i10) {
                    case 20:
                        return 32;
                    case 21:
                        return 64;
                    case 22:
                        return 128;
                    default:
                        switch (i10) {
                            case 30:
                                return 256;
                            case 31:
                                return 512;
                            case 32:
                                return 1024;
                            default:
                                switch (i10) {
                                    case 40:
                                        return 2048;
                                    case 41:
                                        return 4096;
                                    case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                                        return 8192;
                                    default:
                                        switch (i10) {
                                            case 50:
                                                return 16384;
                                            case 51:
                                                return 32768;
                                            case 52:
                                                return 65536;
                                            default:
                                                return -1;
                                        }
                                }
                        }
                }
        }
    }

    private static int avcProfileNumberToConst(int i10) {
        if (i10 == 66) {
            return 1;
        }
        if (i10 == 77) {
            return 2;
        }
        if (i10 == 88) {
            return 4;
        }
        if (i10 == 100) {
            return 8;
        }
        if (i10 == 110) {
            return 16;
        }
        if (i10 != 122) {
            return i10 != 244 ? -1 : 64;
        }
        return 32;
    }

    public static String buildApvCodecString(byte[] bArr) {
        ac.b.f(bArr.length, "Invalid APV CSD length: %s", bArr.length >= 17);
        byte b10 = bArr[0];
        ac.b.f(b10, "Invalid APV CSD version: %s", b10 == 1);
        return Util.formatInvariant("apv1.apvf%d.apvl%d.apvb%d", Integer.valueOf(bArr[5]), Integer.valueOf(bArr[6]), Integer.valueOf(bArr[7]));
    }

    public static String buildAvcCodecString(int i10, int i11, int i12) {
        return String.format("avc1.%02X%02X%02X", Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12));
    }

    public static List<byte[]> buildCea708InitializationData(boolean z) {
        return Collections.singletonList(z ? new byte[]{1} : new byte[]{0});
    }

    public static String buildDolbyVisionCodecString(int i10, int i11) {
        return i10 > 9 ? Util.formatInvariant("dvh1.%02d.%02d", Integer.valueOf(i10), Integer.valueOf(i11)) : i10 > 8 ? Util.formatInvariant("dvav.%02d.%02d", Integer.valueOf(i10), Integer.valueOf(i11)) : Util.formatInvariant("dvhe.%02d.%02d", Integer.valueOf(i10), Integer.valueOf(i11));
    }

    public static byte[] buildDolbyVisionInitializationData(int i10, int i11) {
        int i12;
        int i13;
        byte[] bArr = new byte[24];
        if (i10 == 8) {
            i12 = 4;
            i13 = 0;
        } else if (i10 == 9) {
            i12 = 2;
            i13 = 1;
        } else {
            i12 = 0;
            i13 = 0;
        }
        bArr[0] = 1;
        bArr[1] = 0;
        byte b10 = (byte) ((i10 & 127) << 1);
        bArr[2] = b10;
        bArr[2] = (byte) ((b10 | ((i11 >> 5) & 1)) & 255);
        byte b11 = (byte) ((i11 & 31) << 3);
        bArr[3] = b11;
        byte b12 = (byte) (b11 | 4);
        bArr[3] = b12;
        byte b13 = b12;
        bArr[3] = b13;
        bArr[3] = (byte) (b13 | 1);
        byte b14 = (byte) (i12 << 4);
        bArr[4] = b14;
        bArr[4] = (byte) (b14 | (i13 << 2));
        return bArr;
    }

    public static String buildH263CodecString(int i10, int i11) {
        return Util.formatInvariant("s263.%d.%d", Integer.valueOf(i10), Integer.valueOf(i11));
    }

    public static String buildHevcCodecString(int i10, boolean z, int i11, int i12, int[] iArr, int i13) {
        StringBuilder sb2 = new StringBuilder(Util.formatInvariant("hvc1.%s%d.%X.%c%d", HEVC_GENERAL_PROFILE_SPACE_STRINGS[i10], Integer.valueOf(i11), Integer.valueOf(i12), Character.valueOf(z ? 'H' : 'L'), Integer.valueOf(i13)));
        int length = iArr.length;
        while (length > 0 && iArr[length - 1] == 0) {
            length--;
        }
        for (int i14 = 0; i14 < length; i14++) {
            sb2.append(String.format(".%02X", Integer.valueOf(iArr[i14])));
        }
        return sb2.toString();
    }

    public static String buildIamfCodecString(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        String invariant = null;
        String string = null;
        while (parsableByteArray.bytesLeft() > 0 && (invariant == null || string == null)) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int i10 = unsignedByte >> 3;
            boolean z = (unsignedByte & 2) != 0;
            boolean z5 = (unsignedByte & 1) != 0;
            int unsignedLeb128ToInt = parsableByteArray.readUnsignedLeb128ToInt();
            if (i10 > 4 && i10 < 24 && z) {
                parsableByteArray.skipLeb128();
                parsableByteArray.skipLeb128();
            }
            if (z5) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedLeb128ToInt());
            }
            int position = parsableByteArray.getPosition() + unsignedLeb128ToInt;
            if (i10 == 31) {
                parsableByteArray.skipBytes(4);
                invariant = Util.formatInvariant("iamf.%03X.%03X", Integer.valueOf(parsableByteArray.readUnsignedByte()), Integer.valueOf(parsableByteArray.readUnsignedByte()));
            } else if (i10 == 0) {
                parsableByteArray.skipLeb128();
                string = parsableByteArray.readString(4);
                if (string.equals(CODEC_ID_MP4A)) {
                    parsableByteArray.skipLeb128();
                    parsableByteArray.skipBytes(2);
                    ParsableBitArray parsableBitArray = new ParsableBitArray();
                    parsableBitArray.reset(parsableByteArray);
                    int bits = parsableBitArray.readBits(5);
                    if (bits == 31) {
                        bits = parsableBitArray.readBits(6) + 32;
                    }
                    string = a2.j(bits, string, ".40.");
                }
            }
            parsableByteArray.setPosition(position);
        }
        if (invariant == null || string == null) {
            return null;
        }
        return androidx.compose.foundation.c.t(invariant, ".", string);
    }

    public static byte[] buildNalUnit(byte[] bArr, int i10, int i11) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[bArr2.length + i11];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i10, bArr3, bArr2.length, i11);
        return bArr3;
    }

    public static h1 buildVp9CodecPrivateInitializationData(byte b10, byte b11, byte b12, byte b13) {
        return h1.s(new byte[]{1, 1, b10, 2, 1, b11, 3, 1, b12, 4, 1, b13});
    }

    public static int dolbyVisionConstantToLevelNumber(int i10) {
        int i11 = 1;
        if (i10 != 1) {
            i11 = 2;
            if (i10 != 2) {
                switch (i10) {
                    case 4:
                        return 3;
                    case 8:
                        return 4;
                    case 16:
                        return 5;
                    case 32:
                        return 6;
                    case 64:
                        return 7;
                    case 128:
                        return 8;
                    case 256:
                        return 9;
                    case 512:
                        return 10;
                    case 1024:
                        return 11;
                    case 2048:
                        return 12;
                    case 4096:
                        return 13;
                    default:
                        throw new IllegalArgumentException(a0.c.i(i10, "Unknown Dolby Vision level: "));
                }
            }
        }
        return i11;
    }

    public static int dolbyVisionConstantToProfileNumber(int i10) {
        if (i10 == 1) {
            return 0;
        }
        if (i10 == 2) {
            return 1;
        }
        if (i10 == 4) {
            return 2;
        }
        if (i10 == 8) {
            return 3;
        }
        if (i10 == 16) {
            return 4;
        }
        if (i10 == 32) {
            return 5;
        }
        if (i10 == 64) {
            return 6;
        }
        if (i10 == 128) {
            return 7;
        }
        if (i10 == 256) {
            return 8;
        }
        if (i10 == 512) {
            return 9;
        }
        if (i10 == 1024) {
            return 10;
        }
        throw new IllegalArgumentException(a0.c.i(i10, "Unknown Dolby Vision profile: "));
    }

    private static Integer dolbyVisionStringToLevel(String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
        }
        return null;
    }

    private static Integer dolbyVisionStringToProfile(String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
        }
        return null;
    }

    private static int findNalStartCode(byte[] bArr, int i10) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i10 <= length) {
            if (isNalStartCode(bArr, i10)) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        int iMp4aAudioObjectTypeToProfile;
        if (strArr.length != 3) {
            a2.w("Ignoring malformed MP4A codec string: ", str, TAG);
            return null;
        }
        try {
            if (MimeTypes.AUDIO_AAC.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16))) && (iMp4aAudioObjectTypeToProfile = mp4aAudioObjectTypeToProfile(Integer.parseInt(strArr[2]))) != -1) {
                return new Pair<>(Integer.valueOf(iMp4aAudioObjectTypeToProfile), 0);
            }
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed MP4A codec string: ", str, TAG);
        }
        return null;
    }

    private static Pair<Integer, Integer> getAc4CodecProfileAndLevel(String str, String[] strArr) {
        if (strArr.length != 4) {
            a2.w("Ignoring malformed AC-4 codec string: ", str, TAG);
            return null;
        }
        try {
            int i10 = Integer.parseInt(strArr[1]);
            int i11 = Integer.parseInt(strArr[2]);
            int i12 = Integer.parseInt(strArr[3]);
            int iAc4BitstreamAndPresentationVersionsToProfileConst = ac4BitstreamAndPresentationVersionsToProfileConst(i10, i11);
            if (iAc4BitstreamAndPresentationVersionsToProfileConst != -1) {
                int iAc4LevelNumberToConst = ac4LevelNumberToConst(i12);
                if (iAc4LevelNumberToConst != -1) {
                    return new Pair<>(Integer.valueOf(iAc4BitstreamAndPresentationVersionsToProfileConst), Integer.valueOf(iAc4LevelNumberToConst));
                }
                a2.s(i12, "Unknown AC-4 level: ", TAG);
                return null;
            }
            Log.w(TAG, "Unknown AC-4 profile: " + i10 + "." + i11);
            return null;
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed AC-4 codec string: ", str, TAG);
            return null;
        }
    }

    private static Pair<Integer, Integer> getApvProfileAndLevel(String str, String[] strArr) {
        int i10;
        if (strArr.length < 4) {
            a2.w("Ignoring malformed APV codec string: ", str, TAG);
            return null;
        }
        try {
            int i11 = Integer.parseInt(strArr[1].substring(4));
            int i12 = Integer.parseInt(strArr[2].substring(4));
            int i13 = Integer.parseInt(strArr[3].substring(4));
            if (i11 == 33) {
                i10 = 1;
            } else {
                if (i11 != 44) {
                    a2.s(i11, "Ignoring invalid APV profile: ", TAG);
                    return null;
                }
                i10 = 8192;
            }
            int i14 = (i12 / 30) * 2;
            if (i12 % 30 == 0) {
                i14--;
            }
            return new Pair<>(Integer.valueOf(i10), Integer.valueOf((1 << i13) | (256 << (i14 - 1))));
        } catch (NumberFormatException e5) {
            Log.w(TAG, "Ignoring malformed APV codec string: " + str, e5);
            return null;
        }
    }

    private static Pair<Integer, Integer> getAv1ProfileAndLevel(String str, String[] strArr, ColorInfo colorInfo) {
        int i10;
        if (strArr.length < 4) {
            a2.w("Ignoring malformed AV1 codec string: ", str, TAG);
            return null;
        }
        try {
            int i11 = Integer.parseInt(strArr[1]);
            int i12 = Integer.parseInt(strArr[2].substring(0, 2));
            int i13 = Integer.parseInt(strArr[3]);
            if (i11 != 0) {
                a2.s(i11, "Unknown AV1 profile: ", TAG);
                return null;
            }
            if (i13 != 8 && i13 != 10) {
                a2.s(i13, "Unknown AV1 bit depth: ", TAG);
                return null;
            }
            int i14 = i13 != 8 ? (colorInfo == null || !(colorInfo.hdrStaticInfo != null || (i10 = colorInfo.colorTransfer) == 7 || i10 == 6)) ? 2 : 4096 : 1;
            int iAv1LevelNumberToConst = av1LevelNumberToConst(i12);
            if (iAv1LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(i14), Integer.valueOf(iAv1LevelNumberToConst));
            }
            a2.s(i12, "Unknown AV1 level: ", TAG);
            return null;
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed AV1 codec string: ", str, TAG);
            return null;
        }
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        int i10;
        int i11;
        if (strArr.length < 2) {
            a2.w("Ignoring malformed AVC codec string: ", str, TAG);
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                i11 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i10 = Integer.parseInt(strArr[1].substring(4), 16);
            } else {
                if (strArr.length < 3) {
                    Log.w(TAG, "Ignoring malformed AVC codec string: " + str);
                    return null;
                }
                int i12 = Integer.parseInt(strArr[1]);
                i10 = Integer.parseInt(strArr[2]);
                i11 = i12;
            }
            int iAvcProfileNumberToConst = avcProfileNumberToConst(i11);
            if (iAvcProfileNumberToConst == -1) {
                a2.s(i11, "Unknown AVC profile: ", TAG);
                return null;
            }
            int iAvcLevelNumberToConst = avcLevelNumberToConst(i10);
            if (iAvcLevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(iAvcProfileNumberToConst), Integer.valueOf(iAvcLevelNumberToConst));
            }
            a2.s(i10, "Unknown AVC level: ", TAG);
            return null;
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed AVC codec string: ", str, TAG);
            return null;
        }
    }

    public static Pair<Integer, Integer> getCodecProfileAndLevel(Format format) {
        String str = format.codecs;
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType)) {
            return getDolbyVisionProfileAndLevel(format.codecs, strArrSplit);
        }
        String str2 = strArrSplit[0];
        str2.getClass();
        switch (str2) {
            case "ac-4":
                return getAc4CodecProfileAndLevel(format.codecs, strArrSplit);
            case "apv1":
                return getApvProfileAndLevel(format.codecs, strArrSplit);
            case "av01":
                return getAv1ProfileAndLevel(format.codecs, strArrSplit, format.colorInfo);
            case "avc1":
            case "avc2":
                return getAvcProfileAndLevel(format.codecs, strArrSplit);
            case "hev1":
            case "hvc1":
                return getHevcProfileAndLevel(format.codecs, strArrSplit, format.colorInfo);
            case "iamf":
                return getIamfCodecProfileAndLevel(format.codecs, strArrSplit);
            case "mp4a":
                return getAacCodecProfileAndLevel(format.codecs, strArrSplit);
            case "s263":
                return getH263ProfileAndLevel(format.codecs, strArrSplit);
            case "vp09":
                return getVp9ProfileAndLevel(format.codecs, strArrSplit);
            default:
                return null;
        }
    }

    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            a2.w("Ignoring malformed Dolby Vision codec string: ", str, TAG);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            a2.w("Ignoring malformed Dolby Vision codec string: ", str, TAG);
            return null;
        }
        String strGroup = matcher.group(1);
        Integer numDolbyVisionStringToProfile = dolbyVisionStringToProfile(strGroup);
        if (numDolbyVisionStringToProfile == null) {
            a2.w("Unknown Dolby Vision profile string: ", strGroup, TAG);
            return null;
        }
        String str2 = strArr[2];
        Integer numDolbyVisionStringToLevel = dolbyVisionStringToLevel(str2);
        if (numDolbyVisionStringToLevel != null) {
            return new Pair<>(numDolbyVisionStringToProfile, numDolbyVisionStringToLevel);
        }
        a2.w("Unknown Dolby Vision level string: ", str2, TAG);
        return null;
    }

    private static Pair<Integer, Integer> getH263ProfileAndLevel(String str, String[] strArr) {
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        if (strArr.length < 3) {
            a2.w("Ignoring malformed H263 codec string: ", str, TAG);
            return pair;
        }
        try {
            return new Pair<>(Integer.valueOf(Integer.parseInt(strArr[1])), Integer.valueOf(Integer.parseInt(strArr[2])));
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed H263 codec string: ", str, TAG);
            return pair;
        }
    }

    public static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr, ColorInfo colorInfo) {
        if (strArr.length < 4) {
            a2.w("Ignoring malformed HEVC codec string: ", str, TAG);
            return null;
        }
        int i10 = 1;
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            a2.w("Ignoring malformed HEVC codec string: ", str, TAG);
            return null;
        }
        String strGroup = matcher.group(1);
        if (!"1".equals(strGroup)) {
            i10 = 6;
            if ("2".equals(strGroup)) {
                i10 = (colorInfo == null || colorInfo.colorTransfer != 6) ? 2 : 4096;
            } else if (!"6".equals(strGroup)) {
                a2.w("Unknown HEVC profile string: ", strGroup, TAG);
                return null;
            }
        }
        String str2 = strArr[3];
        Integer numHevcCodecStringToProfileLevel = hevcCodecStringToProfileLevel(str2);
        if (numHevcCodecStringToProfileLevel != null) {
            return new Pair<>(Integer.valueOf(i10), numHevcCodecStringToProfileLevel);
        }
        a2.w("Unknown HEVC level string: ", str2, TAG);
        return null;
    }

    private static Pair<Integer, Integer> getIamfCodecProfileAndLevel(String str, String[] strArr) {
        int i10 = 4;
        if (strArr.length < 4) {
            a2.w("Ignoring malformed IAMF codec string: ", str, TAG);
            return null;
        }
        try {
            int i11 = 1 << (Integer.parseInt(strArr[1]) + 16);
            String str2 = strArr[3];
            str2.getClass();
            switch (str2) {
                case "Opus":
                    i10 = 1;
                    break;
                case "fLaC":
                    break;
                case "ipcm":
                    i10 = 8;
                    break;
                case "mp4a":
                    i10 = 2;
                    break;
                default:
                    Log.w(TAG, "Ignoring unknown codec identifier for IAMF auxiliary profile: " + strArr[3]);
                    return null;
            }
            return new Pair<>(Integer.valueOf(16777216 | i11 | i10), 0);
        } catch (NumberFormatException e5) {
            Log.w(TAG, "Ignoring malformed primary profile in IAMF codec string: " + strArr[1], e5);
            return null;
        }
    }

    public static Pair<Integer, Integer> getVideoResolutionFromMpeg4VideoConfig(byte[] bArr) {
        boolean z;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = i11 + 3;
            if (i12 >= bArr.length) {
                z = false;
                break;
            }
            if (parsableByteArray.readUnsignedInt24() == 1 && (bArr[i12] & 240) == 32) {
                z = true;
                break;
            }
            parsableByteArray.setPosition(parsableByteArray.getPosition() - 2);
            i11++;
        }
        ac.b.k(z, "Invalid input: VOL not found.");
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.skipBits((i11 + 4) * 8);
        parsableBitArray.skipBits(1);
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        if (parsableBitArray.readBits(4) == 15) {
            parsableBitArray.skipBits(8);
            parsableBitArray.skipBits(8);
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(79);
            }
        }
        ac.b.k(parsableBitArray.readBits(2) == 0, "Only supports rectangular video object layer shape.");
        ac.b.j(parsableBitArray.readBit());
        int bits = parsableBitArray.readBits(16);
        ac.b.j(parsableBitArray.readBit());
        if (parsableBitArray.readBit()) {
            ac.b.j(bits > 0);
            for (int i13 = bits - 1; i13 > 0; i13 >>= 1) {
                i10++;
            }
            parsableBitArray.skipBits(i10);
        }
        ac.b.j(parsableBitArray.readBit());
        int bits2 = parsableBitArray.readBits(13);
        ac.b.j(parsableBitArray.readBit());
        int bits3 = parsableBitArray.readBits(13);
        ac.b.j(parsableBitArray.readBit());
        parsableBitArray.skipBits(1);
        return Pair.create(Integer.valueOf(bits2), Integer.valueOf(bits3));
    }

    public static ByteBuffer getVorbisInitializationData(Format format) {
        ac.b.k(format.initializationData.size() > 1, "csd-0 and csd-1 must be present for Vorbis.");
        byte[] bArr = format.initializationData.get(0);
        byte[] bArr2 = format.initializationData.get(1);
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] bArrXiphLaceEnc = xiphLaceEnc(length);
        byte[] bArrXiphLaceEnc2 = xiphLaceEnc(23);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrXiphLaceEnc.length + 1 + bArrXiphLaceEnc2.length + length + 23 + length2);
        byteBufferAllocate.put((byte) 2);
        byteBufferAllocate.put(bArrXiphLaceEnc);
        byteBufferAllocate.put(bArrXiphLaceEnc2);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.put(new byte[]{3, 118, 111, 114, 98, 105, 115, 7, 0, 0, 0, 97, 110, 100, 114, 111, 105, 100, 0, 0, 0, 0, 1});
        byteBufferAllocate.put(bArr2);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            a2.w("Ignoring malformed VP9 codec string: ", str, TAG);
            return null;
        }
        try {
            int i10 = Integer.parseInt(strArr[1]);
            int i11 = Integer.parseInt(strArr[2]);
            int iVp9ProfileNumberToConst = vp9ProfileNumberToConst(i10);
            if (iVp9ProfileNumberToConst == -1) {
                a2.s(i10, "Unknown VP9 profile: ", TAG);
                return null;
            }
            int iVp9LevelNumberToConst = vp9LevelNumberToConst(i11);
            if (iVp9LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(iVp9ProfileNumberToConst), Integer.valueOf(iVp9LevelNumberToConst));
            }
            a2.s(i11, "Unknown VP9 level: ", TAG);
            return null;
        } catch (NumberFormatException unused) {
            a2.w("Ignoring malformed VP9 codec string: ", str, TAG);
            return null;
        }
    }

    private static Integer hevcCodecStringToProfileLevel(String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
        }
        return null;
    }

    private static boolean isNalStartCode(byte[] bArr, int i10) {
        if (bArr.length - i10 <= NAL_START_CODE.length) {
            return false;
        }
        int i11 = 0;
        while (true) {
            byte[] bArr2 = NAL_START_CODE;
            if (i11 >= bArr2.length) {
                return true;
            }
            if (bArr[i10 + i11] != bArr2[i11]) {
                return false;
            }
            i11++;
        }
    }

    private static int mp4aAudioObjectTypeToProfile(int i10) {
        int i11 = 17;
        if (i10 != 17) {
            i11 = 20;
            if (i10 != 20) {
                i11 = 23;
                if (i10 != 23) {
                    i11 = 29;
                    if (i10 != 29) {
                        i11 = 39;
                        if (i10 != 39) {
                            i11 = 42;
                            if (i10 != 42) {
                                switch (i10) {
                                    case 1:
                                        return 1;
                                    case 2:
                                        return 2;
                                    case 3:
                                        return 3;
                                    case 4:
                                        return 4;
                                    case 5:
                                        return 5;
                                    case 6:
                                        return 6;
                                    default:
                                        return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i11;
    }

    public static int[] parseAlacAudioSpecificConfig(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.setPosition(5);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(9);
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(20);
        return new int[]{parsableByteArray.readUnsignedIntToInt(), unsignedByte2, unsignedByte};
    }

    public static boolean parseCea708InitializationData(List<byte[]> list) {
        return list.size() == 1 && list.get(0).length == 1 && list.get(0)[0] == 1;
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iFindNalStartCode = 0;
        do {
            arrayList.add(Integer.valueOf(iFindNalStartCode));
            iFindNalStartCode = findNalStartCode(bArr, iFindNalStartCode + NAL_START_CODE.length);
        } while (iFindNalStartCode != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i10 = 0;
        while (i10 < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i10)).intValue();
            int iIntValue2 = (i10 < arrayList.size() + (-1) ? ((Integer) arrayList.get(i10 + 1)).intValue() : bArr.length) - iIntValue;
            byte[] bArr3 = new byte[iIntValue2];
            System.arraycopy(bArr, iIntValue, bArr3, 0, iIntValue2);
            bArr2[i10] = bArr3;
            i10++;
        }
        return bArr2;
    }

    private static int vp9LevelNumberToConst(int i10) {
        if (i10 == 10) {
            return 1;
        }
        if (i10 == 11) {
            return 2;
        }
        if (i10 == 20) {
            return 4;
        }
        if (i10 == 21) {
            return 8;
        }
        if (i10 == 30) {
            return 16;
        }
        if (i10 == 31) {
            return 32;
        }
        if (i10 == 40) {
            return 64;
        }
        if (i10 == 41) {
            return 128;
        }
        if (i10 == 50) {
            return 256;
        }
        if (i10 == 51) {
            return 512;
        }
        switch (i10) {
            case 60:
                return 2048;
            case 61:
                return 4096;
            case 62:
                return 8192;
            default:
                return -1;
        }
    }

    private static int vp9ProfileNumberToConst(int i10) {
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return 2;
        }
        if (i10 != 2) {
            return i10 != 3 ? -1 : 8;
        }
        return 4;
    }

    private static byte[] xiphLaceEnc(int i10) {
        int i11 = i10 / 255;
        byte[] bArr = new byte[i11 + 1];
        Arrays.fill(bArr, (byte) -1);
        bArr[i11] = (byte) (i10 % 255);
        return bArr;
    }
}
