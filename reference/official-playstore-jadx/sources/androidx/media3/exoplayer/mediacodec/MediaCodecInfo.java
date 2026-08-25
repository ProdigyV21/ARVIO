package androidx.media3.exoplayer.mediacodec;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import androidx.fragment.app.a2;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.extractor.ts.TsExtractor;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaCodecInfo {
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String codecMimeType;
    public final boolean detachedSurfaceSupported;
    public final boolean hardwareAccelerated;
    private final boolean isVideo;
    private float maxFrameRate;
    private int maxFrameRateHeight;
    private int maxFrameRateWidth;
    public final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean softwareOnly;
    public final boolean tunneling;
    public final boolean vendor;

    public MediaCodecInfo(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14) {
        str.getClass();
        this.name = str;
        this.mimeType = str2;
        this.codecMimeType = str3;
        this.capabilities = codecCapabilities;
        this.hardwareAccelerated = z;
        this.softwareOnly = z5;
        this.vendor = z10;
        this.adaptive = z11;
        this.tunneling = z12;
        this.secure = z13;
        this.detachedSurfaceSupported = z14;
        this.isVideo = MimeTypes.isVideo(str2);
        this.maxFrameRate = -3.4028235E38f;
        this.maxFrameRateWidth = -1;
        this.maxFrameRateHeight = -1;
    }

    private static int adjustMaxInputChannelCount(String str, String str2, int i10) {
        if (i10 > 1 || ((Build.VERSION.SDK_INT >= 26 && i10 > 0) || MimeTypes.AUDIO_MPEG.equals(str2) || MimeTypes.AUDIO_AMR_NB.equals(str2) || MimeTypes.AUDIO_AMR_WB.equals(str2) || MimeTypes.AUDIO_AAC.equals(str2) || MimeTypes.AUDIO_VORBIS.equals(str2) || MimeTypes.AUDIO_OPUS.equals(str2) || MimeTypes.AUDIO_RAW.equals(str2) || MimeTypes.AUDIO_FLAC.equals(str2) || MimeTypes.AUDIO_ALAW.equals(str2) || MimeTypes.AUDIO_MLAW.equals(str2) || MimeTypes.AUDIO_MSGSM.equals(str2))) {
            return i10;
        }
        int i11 = MimeTypes.AUDIO_AC3.equals(str2) ? 6 : MimeTypes.AUDIO_E_AC3.equals(str2) ? 16 : 30;
        StringBuilder sbQ = a2.q("AssumedMaxChannelAdjustment: ", str, ", [", i10, " to ");
        sbQ.append(i11);
        sbQ.append("]");
        Log.w(TAG, sbQ.toString());
        return i11;
    }

    private static Point alignVideoSize(MediaCodecInfo.VideoCapabilities videoCapabilities, int i10, int i11) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i10, widthAlignment) * widthAlignment, Util.ceilDivide(i11, heightAlignment) * heightAlignment);
    }

    private static boolean areSizeAndRateSupported(MediaCodecInfo.VideoCapabilities videoCapabilities, int i10, int i11, double d4) {
        Range<Double> achievableFrameRatesFor;
        Point pointAlignVideoSize = alignVideoSize(videoCapabilities, i10, i11);
        int i12 = pointAlignVideoSize.x;
        int i13 = pointAlignVideoSize.y;
        if (d4 == -1.0d || d4 < 1.0d) {
            return videoCapabilities.isSizeSupported(i12, i13);
        }
        double dFloor = Math.floor(d4);
        if (videoCapabilities.areSizeAndRateSupported(i12, i13, dFloor)) {
            return Build.VERSION.SDK_INT < 24 || (achievableFrameRatesFor = videoCapabilities.getAchievableFrameRatesFor(i12, i13)) == null || dFloor <= ((Double) achievableFrameRatesFor.getUpper()).doubleValue();
        }
        return false;
    }

    private float computeMaxSupportedFrameRate(int i10, int i11) {
        float f10 = 1024.0f;
        if (isVideoSizeAndRateSupportedV21(i10, i11, 1024.0f)) {
            return 1024.0f;
        }
        float f11 = 0.0f;
        while (true) {
            float f12 = f10 - f11;
            if (Math.abs(f12) <= 5.0f) {
                return f11;
            }
            float f13 = (f12 / 2.0f) + f11;
            if (isVideoSizeAndRateSupportedV21(i10, i11, f13)) {
                f11 = f13;
            } else {
                f10 = f13;
            }
        }
    }

    private static MediaCodecInfo.CodecProfileLevel[] estimateLegacyAc4ProfileLevels(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        MediaCodecInfo.AudioCapabilities audioCapabilities;
        int i10 = ((codecCapabilities == null || (audioCapabilities = codecCapabilities.getAudioCapabilities()) == null) ? 2 : audioCapabilities.getMaxInputChannelCount()) > 18 ? 16 : 8;
        return new MediaCodecInfo.CodecProfileLevel[]{MediaCodecUtil.createCodecProfileLevel(TsExtractor.TS_STREAM_TYPE_AIT, i10), MediaCodecUtil.createCodecProfileLevel(513, i10), MediaCodecUtil.createCodecProfileLevel(514, i10), MediaCodecUtil.createCodecProfileLevel(AnalyticsListener.EVENT_DRM_KEYS_REMOVED, i10), MediaCodecUtil.createCodecProfileLevel(AnalyticsListener.EVENT_PLAYER_RELEASED, i10)};
    }

    private static MediaCodecInfo.CodecProfileLevel[] estimateLegacyVp9ProfileLevels(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        int iIntValue = (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) ? 0 : ((Integer) videoCapabilities.getBitrateRange().getUpper()).intValue();
        return new MediaCodecInfo.CodecProfileLevel[]{MediaCodecUtil.createCodecProfileLevel(1, iIntValue >= 180000000 ? 1024 : iIntValue >= 120000000 ? 512 : iIntValue >= 60000000 ? 256 : iIntValue >= 30000000 ? 128 : iIntValue >= 18000000 ? 64 : iIntValue >= 12000000 ? 32 : iIntValue >= 7200000 ? 16 : iIntValue >= 3600000 ? 8 : iIntValue >= 1800000 ? 4 : iIntValue >= 800000 ? 2 : 1)};
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private boolean isCodecProfileAndLevelSupported(Format format, boolean z) {
        Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
        String str = format.sampleMimeType;
        if (str != null && str.equals(MimeTypes.VIDEO_MV_HEVC)) {
            String strNormalizeMimeType = MimeTypes.normalizeMimeType(this.codecMimeType);
            if (strNormalizeMimeType.equals(MimeTypes.VIDEO_MV_HEVC)) {
                return true;
            }
            if (strNormalizeMimeType.equals(MimeTypes.VIDEO_H265)) {
                codecProfileAndLevel = MediaCodecUtil.getHevcBaseLayerCodecProfileAndLevel(format);
            }
        }
        if (codecProfileAndLevel == null) {
            return true;
        }
        int iIntValue = ((Integer) codecProfileAndLevel.first).intValue();
        int iIntValue2 = ((Integer) codecProfileAndLevel.second).intValue();
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType)) {
            String str2 = this.mimeType;
            str2.getClass();
            switch (str2) {
                case "video/av01":
                case "video/hevc":
                    iIntValue2 = 0;
                    iIntValue = 2;
                    break;
                case "video/avc":
                    iIntValue = 8;
                    iIntValue2 = 0;
                    break;
            }
        }
        if (!this.isVideo && !this.mimeType.equals(MimeTypes.AUDIO_AC4) && iIntValue != 42) {
            return true;
        }
        MediaCodecInfo.CodecProfileLevel[] profileLevels = getProfileLevels();
        if (this.mimeType.equals(MimeTypes.AUDIO_AC4) && profileLevels.length == 0) {
            profileLevels = estimateLegacyAc4ProfileLevels(this.capabilities);
        }
        if (Build.VERSION.SDK_INT == 23 && MimeTypes.VIDEO_VP9.equals(this.mimeType) && profileLevels.length == 0) {
            profileLevels = estimateLegacyVp9ProfileLevels(this.capabilities);
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : profileLevels) {
            if (codecProfileLevel.profile == iIntValue && ((codecProfileLevel.level >= iIntValue2 || !z) && !needsProfileExcludedWorkaround(this.mimeType, iIntValue))) {
                return true;
            }
        }
        logNoSupport("codec.profileLevel, " + format.codecs + ", " + this.codecMimeType);
        return false;
    }

    private boolean isCompressedAudioBitDepthSupported(Format format) {
        return (Objects.equals(format.sampleMimeType, MimeTypes.AUDIO_FLAC) && format.pcmEncoding == 22 && Build.VERSION.SDK_INT < 34 && this.name.equals("c2.android.flac.decoder")) ? false : true;
    }

    private static boolean isDetachedSurfaceSupported(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Build.VERSION.SDK_INT >= 35 && codecCapabilities != null && codecCapabilities.isFeatureSupported("detached-surface") && !needsDetachedSurfaceUnsupportedWorkaround();
    }

    private boolean isSampleMimeTypeSupported(Format format) {
        return this.mimeType.equals(format.sampleMimeType) || this.mimeType.equals(MediaCodecUtil.getAlternativeCodecMimeType(format));
    }

    private static boolean isSecure(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private void logAssumedSupport(String str) {
        StringBuilder sbT = a0.c.t("AssumedSupport [", str, "] [");
        sbT.append(this.name);
        sbT.append(", ");
        sbT.append(this.mimeType);
        sbT.append("] [");
        sbT.append(Util.DEVICE_DEBUG_INFO);
        sbT.append("]");
        Log.d(TAG, sbT.toString());
    }

    private void logNoSupport(String str) {
        StringBuilder sbT = a0.c.t("NoSupport [", str, "] [");
        sbT.append(this.name);
        sbT.append(", ");
        sbT.append(this.mimeType);
        sbT.append("] [");
        sbT.append(Util.DEVICE_DEBUG_INFO);
        sbT.append("]");
        Log.d(TAG, sbT.toString());
    }

    private static boolean needsAdaptationFlushWorkaround(String str) {
        return MimeTypes.AUDIO_OPUS.equals(str);
    }

    private static boolean needsAdaptationReconfigureWorkaround(String str) {
        return Build.MODEL.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str);
    }

    private static boolean needsDetachedSurfaceUnsupportedWorkaround() {
        String str = Build.MANUFACTURER;
        return str.equals("Xiaomi") || str.equals("OPPO") || str.equals("realme") || str.equals("motorola") || str.equals("LENOVO");
    }

    private static boolean needsProfileExcludedWorkaround(String str, int i10) {
        if (!MimeTypes.VIDEO_H265.equals(str) || 2 != i10) {
            return false;
        }
        String str2 = Build.DEVICE;
        return "sailfish".equals(str2) || "marlin".equals(str2);
    }

    private static boolean needsRotatedVerticalResolutionWorkaround(String str) {
        return ("OMX.MTK.VIDEO.DECODER.HEVC".equals(str) && "mcv5a".equals(Build.DEVICE)) ? false : true;
    }

    public static MediaCodecInfo newInstance(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z5, boolean z10, boolean z11, boolean z12) {
        return new MediaCodecInfo(str, str2, str3, codecCapabilities, z, z5, z10, (z11 || codecCapabilities == null || !isAdaptive(codecCapabilities)) ? false : true, codecCapabilities != null && isTunneling(codecCapabilities), z12 || (codecCapabilities != null && isSecure(codecCapabilities)), isDetachedSurfaceSupported(codecCapabilities));
    }

    public Point alignVideoSizeV21(int i10, int i11) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
            return null;
        }
        return alignVideoSize(videoCapabilities, i10, i11);
    }

    public DecoderReuseEvaluation canReuseCodec(Format format, Format format2) {
        Format format3;
        Format format4;
        int i10;
        int i11 = !Objects.equals(format.sampleMimeType, format2.sampleMimeType) ? 8 : 0;
        if (this.isVideo) {
            if (format.rotationDegrees != format2.rotationDegrees) {
                i11 |= 1024;
            }
            boolean z = (format.width == format2.width && format.height == format2.height) ? false : true;
            if (!this.adaptive && z) {
                i11 |= 512;
            }
            if ((!ColorInfo.isEquivalentToAssumedSdrDefault(format.colorInfo) || !ColorInfo.isEquivalentToAssumedSdrDefault(format2.colorInfo)) && !Objects.equals(format.colorInfo, format2.colorInfo)) {
                i11 |= 2048;
            }
            if (needsAdaptationReconfigureWorkaround(this.name) && !format.initializationDataEquals(format2)) {
                i11 |= 2;
            }
            int i12 = format.decodedWidth;
            if (i12 != -1 && (i10 = format.decodedHeight) != -1 && i12 == format2.decodedWidth && i10 == format2.decodedHeight && z) {
                i11 |= 2;
            }
            if (i11 == 0 && Objects.equals(format2.sampleMimeType, MimeTypes.VIDEO_DOLBY_VISION)) {
                Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
                Pair<Integer, Integer> codecProfileAndLevel2 = CodecSpecificDataUtil.getCodecProfileAndLevel(format2);
                if (codecProfileAndLevel == null || codecProfileAndLevel2 == null || !((Integer) codecProfileAndLevel.first).equals(codecProfileAndLevel2.first)) {
                    i11 |= 2;
                }
            }
            if (i11 == 0) {
                return new DecoderReuseEvaluation(this.name, format, format2, format.initializationDataEquals(format2) ? 3 : 2, 0);
            }
            format3 = format;
            format4 = format2;
        } else {
            format3 = format;
            format4 = format2;
            if (format3.channelCount != format4.channelCount) {
                i11 |= 4096;
            }
            if (format3.sampleRate != format4.sampleRate) {
                i11 |= 8192;
            }
            if (format3.pcmEncoding != format4.pcmEncoding) {
                i11 |= 16384;
            }
            if (i11 == 0 && (this.mimeType.equals(MimeTypes.AUDIO_AAC) || this.mimeType.equals(MimeTypes.AUDIO_AC4))) {
                Pair<Integer, Integer> codecProfileAndLevel3 = CodecSpecificDataUtil.getCodecProfileAndLevel(format3);
                Pair<Integer, Integer> codecProfileAndLevel4 = CodecSpecificDataUtil.getCodecProfileAndLevel(format4);
                if (codecProfileAndLevel3 != null && codecProfileAndLevel4 != null) {
                    int iIntValue = ((Integer) codecProfileAndLevel3.first).intValue();
                    int iIntValue2 = ((Integer) codecProfileAndLevel4.first).intValue();
                    if (iIntValue == 42 && iIntValue2 == 42) {
                        return new DecoderReuseEvaluation(this.name, format3, format4, 3, 0);
                    }
                    if (this.mimeType.equals(MimeTypes.AUDIO_AC4) && codecProfileAndLevel3.equals(codecProfileAndLevel4)) {
                        return new DecoderReuseEvaluation(this.name, format3, format4, 3, 0);
                    }
                }
            }
            if (i11 == 0 && (this.mimeType.equals(MimeTypes.AUDIO_E_AC3_JOC) || this.mimeType.equals(MimeTypes.AUDIO_E_AC3))) {
                return new DecoderReuseEvaluation(this.name, format3, format4, 3, 0);
            }
            if (!format3.initializationDataEquals(format4)) {
                i11 |= 32;
            }
            if (needsAdaptationFlushWorkaround(this.mimeType)) {
                i11 |= 2;
            }
            if (i11 == 0) {
                return new DecoderReuseEvaluation(this.name, format3, format4, 1, 0);
            }
        }
        return new DecoderReuseEvaluation(this.name, format3, format4, 0, i11);
    }

    public float getMaxSupportedFrameRate(int i10, int i11) {
        if (!this.isVideo) {
            return -3.4028235E38f;
        }
        float f10 = this.maxFrameRate;
        if (f10 != -3.4028235E38f && this.maxFrameRateWidth == i10 && this.maxFrameRateHeight == i11) {
            return f10;
        }
        float fComputeMaxSupportedFrameRate = computeMaxSupportedFrameRate(i10, i11);
        this.maxFrameRate = fComputeMaxSupportedFrameRate;
        this.maxFrameRateWidth = i10;
        this.maxFrameRateHeight = i11;
        return fComputeMaxSupportedFrameRate;
    }

    public int getMaxSupportedInstances() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            return -1;
        }
        return codecCapabilities.getMaxSupportedInstances();
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        return (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) ? new MediaCodecInfo.CodecProfileLevel[0] : codecProfileLevelArr;
    }

    public boolean isAudioChannelCountSupportedV21(int i10) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        }
        if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i10) {
            return true;
        }
        logNoSupport(a0.c.i(i10, "channelCount.support, "));
        return false;
    }

    public boolean isAudioSampleRateSupportedV21(int i10) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        }
        if (audioCapabilities.isSampleRateSupported(i10)) {
            return true;
        }
        logNoSupport(a0.c.i(i10, "sampleRate.support, "));
        return false;
    }

    public boolean isFormatFunctionallySupported(Format format) {
        return isSampleMimeTypeSupported(format) && isCodecProfileAndLevelSupported(format, false) && isCompressedAudioBitDepthSupported(format);
    }

    public boolean isFormatSupported(Format format) throws MediaCodecUtil.DecoderQueryException {
        int i10;
        int i11;
        if (!isSampleMimeTypeSupported(format) || !isCodecProfileAndLevelSupported(format, true) || !isCompressedAudioBitDepthSupported(format)) {
            return false;
        }
        if (!this.isVideo) {
            int i12 = format.sampleRate;
            return (i12 == -1 || isAudioSampleRateSupportedV21(i12)) && ((i10 = format.channelCount) == -1 || isAudioChannelCountSupportedV21(i10));
        }
        int i13 = format.width;
        if (i13 <= 0 || (i11 = format.height) <= 0) {
            return true;
        }
        return isVideoSizeAndRateSupportedV21(i13, i11, format.frameRate);
    }

    public boolean isHdr10PlusOutOfBandMetadataSupported() {
        if (Build.VERSION.SDK_INT >= 29 && MimeTypes.VIDEO_VP9.equals(this.mimeType)) {
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
                if (codecProfileLevel.profile == 16384) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSeamlessAdaptationSupported(Format format) {
        if (this.isVideo) {
            return this.adaptive;
        }
        Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
        return codecProfileAndLevel != null && ((Integer) codecProfileAndLevel.first).intValue() == 42;
    }

    public boolean isVideoSizeAndRateSupportedV21(int i10, int i11, double d4) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            int iAreResolutionAndFrameRateCovered = MediaCodecPerformancePointCoverageProvider.areResolutionAndFrameRateCovered(videoCapabilities, i10, i11, d4);
            if (iAreResolutionAndFrameRateCovered == 2) {
                return true;
            }
            if (iAreResolutionAndFrameRateCovered == 1) {
                StringBuilder sbV = androidx.compose.foundation.c.v("sizeAndRate.cover, ", i10, "x", i11, "@");
                sbV.append(d4);
                logNoSupport(sbV.toString());
                return false;
            }
        }
        if (!areSizeAndRateSupported(videoCapabilities, i10, i11, d4)) {
            if (i10 >= i11 || !needsRotatedVerticalResolutionWorkaround(this.name) || !areSizeAndRateSupported(videoCapabilities, i11, i10, d4)) {
                StringBuilder sbV2 = androidx.compose.foundation.c.v("sizeAndRate.support, ", i10, "x", i11, "@");
                sbV2.append(d4);
                logNoSupport(sbV2.toString());
                return false;
            }
            StringBuilder sbV3 = androidx.compose.foundation.c.v("sizeAndRate.rotated, ", i10, "x", i11, "@");
            sbV3.append(d4);
            logAssumedSupport(sbV3.toString());
        }
        return true;
    }

    public String toString() {
        return this.name;
    }
}
