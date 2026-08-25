package androidx.media3.common.util;

import android.media.MediaFormat;
import android.os.Build;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import com.google.common.collect.a0;
import com.google.common.collect.b1;
import com.google.common.collect.h1;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaFormatUtil {
    public static final String KEY_MAX_BIT_RATE = "max-bitrate";
    public static final String KEY_PCM_ENCODING_EXTENDED = "exo-pcm-encoding-int";
    public static final String KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT = "exo-pixel-width-height-ratio-float";
    private static final int MAX_POWER_OF_TWO_INT = 1073741824;

    private MediaFormatUtil() {
    }

    public static Format createFormatFromMediaFormat(MediaFormat mediaFormat) {
        int i10 = 0;
        Format.Builder pcmEncoding = new Format.Builder().setSampleMimeType(mediaFormat.getString("mime")).setLanguage(mediaFormat.getString("language")).setPeakBitrate(getInteger(mediaFormat, KEY_MAX_BIT_RATE, -1)).setAverageBitrate(getInteger(mediaFormat, "bitrate", -1)).setCodecs(getCodecString(mediaFormat)).setFrameRate(getFloatFromIntOrFloat(mediaFormat, "frame-rate", -1.0f)).setWidth(getInteger(mediaFormat, "width", -1)).setHeight(getInteger(mediaFormat, "height", -1)).setPixelWidthHeightRatio(getPixelWidthHeightRatio(mediaFormat, 1.0f)).setMaxInputSize(getInteger(mediaFormat, "max-input-size", -1)).setRotationDegrees(getInteger(mediaFormat, "rotation-degrees", 0)).setColorInfo(getColorInfo(mediaFormat)).setSampleRate(getInteger(mediaFormat, "sample-rate", -1)).setChannelCount(getInteger(mediaFormat, "channel-count", -1)).setPcmEncoding(getInteger(mediaFormat, "pcm-encoding", -1));
        a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i11 = 0;
        while (true) {
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-" + i10);
            if (byteBuffer == null) {
                break;
            }
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.rewind();
            int i12 = i11 + 1;
            int iB = b1.b(objArrCopyOf.length, i12);
            if (iB > objArrCopyOf.length) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
            }
            objArrCopyOf[i11] = bArr;
            i10++;
            i11 = i12;
        }
        pcmEncoding.setInitializationData(h1.h(i11, objArrCopyOf));
        if (mediaFormat.containsKey("track-id")) {
            pcmEncoding.setId(mediaFormat.getInteger("track-id"));
        }
        return pcmEncoding.build();
    }

    public static MediaFormat createMediaFormatFromFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        maybeSetInteger(mediaFormat, "bitrate", format.bitrate);
        maybeSetInteger(mediaFormat, KEY_MAX_BIT_RATE, format.peakBitrate);
        maybeSetInteger(mediaFormat, "channel-count", format.channelCount);
        int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(format.channelCount);
        if (audioTrackChannelConfig != 0) {
            mediaFormat.setInteger("channel-mask", audioTrackChannelConfig);
        }
        maybeSetColorInfo(mediaFormat, format.colorInfo);
        maybeSetString(mediaFormat, "mime", format.sampleMimeType);
        maybeSetString(mediaFormat, "codecs-string", format.codecs);
        maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        maybeSetInteger(mediaFormat, "width", format.width);
        maybeSetInteger(mediaFormat, "height", format.height);
        setCsdBuffers(mediaFormat, format.initializationData);
        maybeSetPcmEncoding(mediaFormat, format.pcmEncoding);
        maybeSetString(mediaFormat, "language", format.language);
        maybeSetInteger(mediaFormat, "max-input-size", format.maxInputSize);
        maybeSetInteger(mediaFormat, "sample-rate", format.sampleRate);
        maybeSetInteger(mediaFormat, "caption-service-number", format.accessibilityChannel);
        mediaFormat.setInteger("rotation-degrees", format.rotationDegrees);
        int i10 = format.selectionFlags;
        setBooleanAsInt(mediaFormat, "is-autoselect", i10 & 4);
        setBooleanAsInt(mediaFormat, "is-default", i10 & 1);
        setBooleanAsInt(mediaFormat, "is-forced-subtitle", i10 & 2);
        mediaFormat.setInteger("encoder-delay", format.encoderDelay);
        mediaFormat.setInteger("encoder-padding", format.encoderPadding);
        maybeSetPixelAspectRatio(mediaFormat, format.pixelWidthHeightRatio);
        String str = format.id;
        if (str != null) {
            try {
                mediaFormat.setInteger("track-id", Integer.parseInt(str));
            } catch (NumberFormatException unused) {
            }
        }
        return mediaFormat;
    }

    public static byte[] getArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static String getCodecString(MediaFormat mediaFormat) {
        return (Objects.equals(mediaFormat.getString("mime"), MimeTypes.VIDEO_H263) && mediaFormat.containsKey("profile") && mediaFormat.containsKey("level")) ? CodecSpecificDataUtil.buildH263CodecString(mediaFormat.getInteger("profile"), mediaFormat.getInteger("level")) : (Objects.equals(mediaFormat.getString("mime"), MimeTypes.VIDEO_DOLBY_VISION) && mediaFormat.containsKey("profile") && mediaFormat.containsKey("level")) ? CodecSpecificDataUtil.buildDolbyVisionCodecString(CodecSpecificDataUtil.dolbyVisionConstantToProfileNumber(mediaFormat.getInteger("profile")), CodecSpecificDataUtil.dolbyVisionConstantToLevelNumber(mediaFormat.getInteger("level"))) : getString(mediaFormat, "codecs-string", null);
    }

    public static ColorInfo getColorInfo(MediaFormat mediaFormat) {
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        int integer = getInteger(mediaFormat, "color-standard", -1);
        int integer2 = getInteger(mediaFormat, "color-range", -1);
        int integer3 = getInteger(mediaFormat, "color-transfer", -1);
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("hdr-static-info");
        byte[] array = byteBuffer != null ? getArray(byteBuffer) : null;
        if (!isValidColorSpace(integer)) {
            integer = -1;
        }
        if (!isValidColorRange(integer2)) {
            integer2 = -1;
        }
        if (!isValidColorTransfer(integer3)) {
            integer3 = -1;
        }
        if (integer == -1 && integer2 == -1 && integer3 == -1 && array == null) {
            return null;
        }
        return new ColorInfo.Builder().setColorSpace(integer).setColorRange(integer2).setColorTransfer(integer3).setHdrStaticInfo(array).build();
    }

    public static float getFloat(MediaFormat mediaFormat, String str, float f10) {
        return mediaFormat.containsKey(str) ? mediaFormat.getFloat(str) : f10;
    }

    public static float getFloatFromIntOrFloat(MediaFormat mediaFormat, String str, float f10) {
        int integer;
        if (!mediaFormat.containsKey(str)) {
            return f10;
        }
        if (Build.VERSION.SDK_INT < 29) {
            try {
                return mediaFormat.getFloat(str);
            } catch (ClassCastException unused) {
                integer = mediaFormat.getInteger(str);
            }
        } else {
            if (mediaFormat.getValueTypeForKey(str) == 3) {
                return mediaFormat.getFloat(str);
            }
            integer = mediaFormat.getInteger(str);
        }
        return integer;
    }

    public static int getInteger(MediaFormat mediaFormat, String str, int i10) {
        return mediaFormat.containsKey(str) ? mediaFormat.getInteger(str) : i10;
    }

    private static float getPixelWidthHeightRatio(MediaFormat mediaFormat, float f10) {
        return (mediaFormat.containsKey("sar-width") && mediaFormat.containsKey("sar-height")) ? mediaFormat.getInteger("sar-width") / mediaFormat.getInteger("sar-height") : f10;
    }

    public static String getString(MediaFormat mediaFormat, String str, String str2) {
        return mediaFormat.containsKey(str) ? mediaFormat.getString(str) : str2;
    }

    public static Integer getTimeLapseFrameRate(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("time-lapse-enable") && mediaFormat.getInteger("time-lapse-enable") > 0 && mediaFormat.containsKey("time-lapse-fps")) {
            return Integer.valueOf(mediaFormat.getInteger("time-lapse-fps"));
        }
        return null;
    }

    public static boolean isAudioFormat(MediaFormat mediaFormat) {
        return MimeTypes.isAudio(mediaFormat.getString("mime"));
    }

    private static boolean isValidColorRange(int i10) {
        return i10 == 2 || i10 == 1 || i10 == -1;
    }

    private static boolean isValidColorSpace(int i10) {
        return i10 == 2 || i10 == 1 || i10 == 6 || i10 == -1;
    }

    private static boolean isValidColorTransfer(int i10) {
        return i10 == 1 || i10 == 3 || i10 == 2 || i10 == 6 || i10 == 7 || i10 == -1;
    }

    public static boolean isVideoFormat(MediaFormat mediaFormat) {
        return MimeTypes.isVideo(mediaFormat.getString("mime"));
    }

    public static void maybeSetByteBuffer(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void maybeSetColorInfo(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo != null) {
            maybeSetInteger(mediaFormat, "color-transfer", colorInfo.colorTransfer);
            maybeSetInteger(mediaFormat, "color-standard", colorInfo.colorSpace);
            maybeSetInteger(mediaFormat, "color-range", colorInfo.colorRange);
            maybeSetByteBuffer(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
        }
    }

    public static void maybeSetFloat(MediaFormat mediaFormat, String str, float f10) {
        if (f10 != -1.0f) {
            mediaFormat.setFloat(str, f10);
        }
    }

    public static void maybeSetInteger(MediaFormat mediaFormat, String str, int i10) {
        if (i10 != -1) {
            mediaFormat.setInteger(str, i10);
        }
    }

    private static void maybeSetPcmEncoding(MediaFormat mediaFormat, int i10) {
        int i11;
        if (i10 == -1) {
            return;
        }
        maybeSetInteger(mediaFormat, KEY_PCM_ENCODING_EXTENDED, i10);
        if (i10 != 0) {
            i11 = 2;
            if (i10 != 2) {
                i11 = 3;
                if (i10 != 3) {
                    i11 = 4;
                    if (i10 != 4) {
                        i11 = 21;
                        if (i10 != 21) {
                            i11 = 22;
                            if (i10 != 22) {
                                return;
                            }
                        }
                    }
                }
            }
        } else {
            i11 = 0;
        }
        mediaFormat.setInteger("pcm-encoding", i11);
    }

    private static void maybeSetPixelAspectRatio(MediaFormat mediaFormat, float f10) {
        int i10;
        mediaFormat.setFloat(KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT, f10);
        int i11 = 1073741824;
        if (f10 < 1.0f) {
            i11 = (int) (f10 * 1073741824);
            i10 = 1073741824;
        } else if (f10 > 1.0f) {
            i10 = (int) (1073741824 / f10);
        } else {
            i11 = 1;
            i10 = 1;
        }
        mediaFormat.setInteger("sar-width", i11);
        mediaFormat.setInteger("sar-height", i10);
    }

    public static void maybeSetString(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    private static void setBooleanAsInt(MediaFormat mediaFormat, String str, int i10) {
        mediaFormat.setInteger(str, i10 != 0 ? 1 : 0);
    }

    public static void setCsdBuffers(MediaFormat mediaFormat, List<byte[]> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            mediaFormat.setByteBuffer(a0.c.i(i10, "csd-"), ByteBuffer.wrap(list.get(i10)));
        }
    }
}
