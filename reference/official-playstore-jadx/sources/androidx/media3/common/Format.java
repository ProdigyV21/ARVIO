package androidx.media3.common;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.math.c;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class Format {
    public static final int CUE_REPLACEMENT_BEHAVIOR_MERGE = 1;
    public static final int CUE_REPLACEMENT_BEHAVIOR_REPLACE = 2;
    public static final int NO_VALUE = -1;
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;
    public final int accessibilityChannel;
    public final int auxiliaryTrackType;
    public final int averageBitrate;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final int cryptoType;
    public final int cueReplacementBehavior;
    public final Object customData;
    public final int decodedHeight;
    public final int decodedWidth;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    public final boolean hasPrerollSamples;
    private int hashCode;
    public final int height;
    public final String id;
    public final List<byte[]> initializationData;
    public final String label;
    public final List<Label> labels;
    public final String language;
    public final int maxInputSize;
    public final int maxNumReorderSamples;
    public final int maxSubLayers;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final int peakBitrate;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int roleFlags;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int tileCountHorizontal;
    public final int tileCountVertical;
    public final int width;
    private static final Format DEFAULT = new Builder().build();
    private static final String FIELD_ID = Util.intToStringMaxRadix(0);
    private static final String FIELD_LABEL = Util.intToStringMaxRadix(1);
    private static final String FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
    private static final String FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
    private static final String FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
    private static final String FIELD_AVERAGE_BITRATE = Util.intToStringMaxRadix(5);
    private static final String FIELD_PEAK_BITRATE = Util.intToStringMaxRadix(6);
    private static final String FIELD_CODECS = Util.intToStringMaxRadix(7);
    private static final String UNUSED_FIELD_METADATA = Util.intToStringMaxRadix(8);
    private static final String FIELD_CONTAINER_MIME_TYPE = Util.intToStringMaxRadix(9);
    private static final String FIELD_SAMPLE_MIME_TYPE = Util.intToStringMaxRadix(10);
    private static final String FIELD_MAX_INPUT_SIZE = Util.intToStringMaxRadix(11);
    private static final String FIELD_INITIALIZATION_DATA = Util.intToStringMaxRadix(12);
    private static final String FIELD_DRM_INIT_DATA = Util.intToStringMaxRadix(13);
    private static final String FIELD_SUBSAMPLE_OFFSET_US = Util.intToStringMaxRadix(14);
    private static final String FIELD_WIDTH = Util.intToStringMaxRadix(15);
    private static final String FIELD_HEIGHT = Util.intToStringMaxRadix(16);
    private static final String FIELD_FRAME_RATE = Util.intToStringMaxRadix(17);
    private static final String FIELD_ROTATION_DEGREES = Util.intToStringMaxRadix(18);
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(19);
    private static final String FIELD_PROJECTION_DATA = Util.intToStringMaxRadix(20);
    private static final String FIELD_STEREO_MODE = Util.intToStringMaxRadix(21);
    private static final String FIELD_COLOR_INFO = Util.intToStringMaxRadix(22);
    private static final String FIELD_CHANNEL_COUNT = Util.intToStringMaxRadix(23);
    private static final String FIELD_SAMPLE_RATE = Util.intToStringMaxRadix(24);
    private static final String FIELD_PCM_ENCODING = Util.intToStringMaxRadix(25);
    private static final String FIELD_ENCODER_DELAY = Util.intToStringMaxRadix(26);
    private static final String FIELD_ENCODER_PADDING = Util.intToStringMaxRadix(27);
    private static final String FIELD_ACCESSIBILITY_CHANNEL = Util.intToStringMaxRadix(28);
    private static final String FIELD_CRYPTO_TYPE = Util.intToStringMaxRadix(29);
    private static final String FIELD_TILE_COUNT_HORIZONTAL = Util.intToStringMaxRadix(30);
    private static final String FIELD_TILE_COUNT_VERTICAL = Util.intToStringMaxRadix(31);
    private static final String FIELD_LABELS = Util.intToStringMaxRadix(32);
    private static final String FIELD_AUXILIARY_TRACK_TYPE = Util.intToStringMaxRadix(33);
    private static final String FIELD_MAX_SUB_LAYERS = Util.intToStringMaxRadix(34);
    private static final String FIELD_DECODED_WIDTH = Util.intToStringMaxRadix(35);
    private static final String FIELD_DECODED_HEIGHT = Util.intToStringMaxRadix(36);

    public static final class Builder {
        private int accessibilityChannel;
        private int auxiliaryTrackType;
        private int averageBitrate;
        private int channelCount;
        private String codecs;
        private ColorInfo colorInfo;
        private String containerMimeType;
        private int cryptoType;
        private int cueReplacementBehavior;
        private Object customData;
        private int decodedHeight;
        private int decodedWidth;
        private DrmInitData drmInitData;
        private int encoderDelay;
        private int encoderPadding;
        private float frameRate;
        private boolean hasPrerollSamples;
        private int height;
        private String id;
        private List<byte[]> initializationData;
        private String label;
        private List<Label> labels;
        private String language;
        private int maxInputSize;
        private int maxNumReorderSamples;
        private int maxSubLayers;
        private Metadata metadata;
        private int pcmEncoding;
        private int peakBitrate;
        private float pixelWidthHeightRatio;
        private byte[] projectionData;
        private int roleFlags;
        private int rotationDegrees;
        private String sampleMimeType;
        private int sampleRate;
        private int selectionFlags;
        private int stereoMode;
        private long subsampleOffsetUs;
        private int tileCountHorizontal;
        private int tileCountVertical;
        private int width;

        public Format build() {
            return new Format(this);
        }

        public Builder setAccessibilityChannel(int i10) {
            this.accessibilityChannel = i10;
            return this;
        }

        public Builder setAuxiliaryTrackType(int i10) {
            this.auxiliaryTrackType = i10;
            return this;
        }

        public Builder setAverageBitrate(int i10) {
            this.averageBitrate = i10;
            return this;
        }

        public Builder setChannelCount(int i10) {
            this.channelCount = i10;
            return this;
        }

        public Builder setCodecs(String str) {
            this.codecs = str;
            return this;
        }

        public Builder setColorInfo(ColorInfo colorInfo) {
            this.colorInfo = colorInfo;
            return this;
        }

        public Builder setContainerMimeType(String str) {
            this.containerMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        public Builder setCryptoType(int i10) {
            this.cryptoType = i10;
            return this;
        }

        public Builder setCueReplacementBehavior(int i10) {
            this.cueReplacementBehavior = i10;
            return this;
        }

        public Builder setCustomData(Object obj) {
            this.customData = obj;
            return this;
        }

        public Builder setDecodedHeight(int i10) {
            this.decodedHeight = i10;
            return this;
        }

        public Builder setDecodedWidth(int i10) {
            this.decodedWidth = i10;
            return this;
        }

        public Builder setDrmInitData(DrmInitData drmInitData) {
            this.drmInitData = drmInitData;
            return this;
        }

        public Builder setEncoderDelay(int i10) {
            this.encoderDelay = i10;
            return this;
        }

        public Builder setEncoderPadding(int i10) {
            this.encoderPadding = i10;
            return this;
        }

        public Builder setFrameRate(float f10) {
            this.frameRate = f10;
            return this;
        }

        public Builder setHasPrerollSamples(boolean z) {
            this.hasPrerollSamples = z;
            return this;
        }

        public Builder setHeight(int i10) {
            this.height = i10;
            return this;
        }

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setInitializationData(List<byte[]> list) {
            this.initializationData = list;
            return this;
        }

        public Builder setLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder setLabels(List<Label> list) {
            this.labels = h1.n(list);
            return this;
        }

        public Builder setLanguage(String str) {
            this.language = str;
            return this;
        }

        public Builder setMaxInputSize(int i10) {
            this.maxInputSize = i10;
            return this;
        }

        public Builder setMaxNumReorderSamples(int i10) {
            this.maxNumReorderSamples = i10;
            return this;
        }

        public Builder setMaxSubLayers(int i10) {
            this.maxSubLayers = i10;
            return this;
        }

        public Builder setMetadata(Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder setPcmEncoding(int i10) {
            this.pcmEncoding = i10;
            return this;
        }

        public Builder setPeakBitrate(int i10) {
            this.peakBitrate = i10;
            return this;
        }

        public Builder setPixelWidthHeightRatio(float f10) {
            this.pixelWidthHeightRatio = f10;
            return this;
        }

        public Builder setProjectionData(byte[] bArr) {
            this.projectionData = bArr;
            return this;
        }

        public Builder setRoleFlags(int i10) {
            this.roleFlags = i10;
            return this;
        }

        public Builder setRotationDegrees(int i10) {
            this.rotationDegrees = i10;
            return this;
        }

        public Builder setSampleMimeType(String str) {
            this.sampleMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        public Builder setSampleRate(int i10) {
            this.sampleRate = i10;
            return this;
        }

        public Builder setSelectionFlags(int i10) {
            this.selectionFlags = i10;
            return this;
        }

        public Builder setStereoMode(int i10) {
            this.stereoMode = i10;
            return this;
        }

        public Builder setSubsampleOffsetUs(long j10) {
            this.subsampleOffsetUs = j10;
            return this;
        }

        public Builder setTileCountHorizontal(int i10) {
            this.tileCountHorizontal = i10;
            return this;
        }

        public Builder setTileCountVertical(int i10) {
            this.tileCountVertical = i10;
            return this;
        }

        public Builder setWidth(int i10) {
            this.width = i10;
            return this;
        }

        public Builder() {
            f1 f1Var = h1.f14020l;
            this.labels = o3.f14078o;
            this.averageBitrate = -1;
            this.peakBitrate = -1;
            this.maxInputSize = -1;
            this.maxNumReorderSamples = -1;
            this.subsampleOffsetUs = Long.MAX_VALUE;
            this.width = -1;
            this.height = -1;
            this.decodedWidth = -1;
            this.decodedHeight = -1;
            this.frameRate = -1.0f;
            this.pixelWidthHeightRatio = 1.0f;
            this.stereoMode = -1;
            this.maxSubLayers = -1;
            this.channelCount = -1;
            this.sampleRate = -1;
            this.pcmEncoding = -1;
            this.accessibilityChannel = -1;
            this.cueReplacementBehavior = 1;
            this.tileCountHorizontal = -1;
            this.tileCountVertical = -1;
            this.cryptoType = 0;
            this.auxiliaryTrackType = 0;
        }

        public Builder setId(int i10) {
            this.id = Integer.toString(i10);
            return this;
        }

        private Builder(Format format) {
            this.id = format.id;
            this.label = format.label;
            this.labels = format.labels;
            this.language = format.language;
            this.selectionFlags = format.selectionFlags;
            this.roleFlags = format.roleFlags;
            this.averageBitrate = format.averageBitrate;
            this.peakBitrate = format.peakBitrate;
            this.codecs = format.codecs;
            this.metadata = format.metadata;
            this.customData = format.customData;
            this.containerMimeType = format.containerMimeType;
            this.sampleMimeType = format.sampleMimeType;
            this.maxInputSize = format.maxInputSize;
            this.maxNumReorderSamples = format.maxNumReorderSamples;
            this.initializationData = format.initializationData;
            this.drmInitData = format.drmInitData;
            this.subsampleOffsetUs = format.subsampleOffsetUs;
            this.hasPrerollSamples = format.hasPrerollSamples;
            this.width = format.width;
            this.height = format.height;
            this.decodedWidth = format.decodedWidth;
            this.decodedHeight = format.decodedHeight;
            this.frameRate = format.frameRate;
            this.rotationDegrees = format.rotationDegrees;
            this.pixelWidthHeightRatio = format.pixelWidthHeightRatio;
            this.projectionData = format.projectionData;
            this.stereoMode = format.stereoMode;
            this.colorInfo = format.colorInfo;
            this.maxSubLayers = format.maxSubLayers;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.pcmEncoding = format.pcmEncoding;
            this.encoderDelay = format.encoderDelay;
            this.encoderPadding = format.encoderPadding;
            this.accessibilityChannel = format.accessibilityChannel;
            this.cueReplacementBehavior = format.cueReplacementBehavior;
            this.tileCountHorizontal = format.tileCountHorizontal;
            this.tileCountVertical = format.tileCountVertical;
            this.cryptoType = format.cryptoType;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CueReplacementBehavior {
    }

    private static <T> T defaultIfNull(T t2, T t10) {
        return t2 != null ? t2 : t10;
    }

    public static Format fromBundle(Bundle bundle) {
        List<Label> listFromBundleList;
        Builder builder = new Builder();
        BundleCollectionUtil.ensureClassLoader(bundle);
        String string = bundle.getString(FIELD_ID);
        Format format = DEFAULT;
        builder.setId((String) defaultIfNull(string, format.id)).setLabel((String) defaultIfNull(bundle.getString(FIELD_LABEL), format.label));
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_LABELS);
        if (parcelableArrayList == null) {
            f1 f1Var = h1.f14020l;
            listFromBundleList = o3.f14078o;
        } else {
            listFromBundleList = BundleCollectionUtil.fromBundleList(new e(1), parcelableArrayList);
        }
        builder.setLabels(listFromBundleList).setLanguage((String) defaultIfNull(bundle.getString(FIELD_LANGUAGE), format.language)).setSelectionFlags(bundle.getInt(FIELD_SELECTION_FLAGS, format.selectionFlags)).setRoleFlags(bundle.getInt(FIELD_ROLE_FLAGS, format.roleFlags)).setAuxiliaryTrackType(bundle.getInt(FIELD_AUXILIARY_TRACK_TYPE, format.auxiliaryTrackType)).setAverageBitrate(bundle.getInt(FIELD_AVERAGE_BITRATE, format.averageBitrate)).setPeakBitrate(bundle.getInt(FIELD_PEAK_BITRATE, format.peakBitrate)).setCodecs((String) defaultIfNull(bundle.getString(FIELD_CODECS), format.codecs)).setContainerMimeType((String) defaultIfNull(bundle.getString(FIELD_CONTAINER_MIME_TYPE), format.containerMimeType)).setSampleMimeType((String) defaultIfNull(bundle.getString(FIELD_SAMPLE_MIME_TYPE), format.sampleMimeType)).setMaxInputSize(bundle.getInt(FIELD_MAX_INPUT_SIZE, format.maxInputSize));
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (true) {
            byte[] byteArray = bundle.getByteArray(keyForInitializationData(i10));
            if (byteArray == null) {
                break;
            }
            arrayList.add(byteArray);
            i10++;
        }
        Builder drmInitData = builder.setInitializationData(arrayList).setDrmInitData((DrmInitData) bundle.getParcelable(FIELD_DRM_INIT_DATA));
        String str = FIELD_SUBSAMPLE_OFFSET_US;
        Format format2 = DEFAULT;
        drmInitData.setSubsampleOffsetUs(bundle.getLong(str, format2.subsampleOffsetUs)).setWidth(bundle.getInt(FIELD_WIDTH, format2.width)).setHeight(bundle.getInt(FIELD_HEIGHT, format2.height)).setDecodedWidth(bundle.getInt(FIELD_DECODED_WIDTH, format2.decodedWidth)).setDecodedHeight(bundle.getInt(FIELD_DECODED_HEIGHT, format2.decodedHeight)).setFrameRate(bundle.getFloat(FIELD_FRAME_RATE, format2.frameRate)).setRotationDegrees(bundle.getInt(FIELD_ROTATION_DEGREES, format2.rotationDegrees)).setPixelWidthHeightRatio(bundle.getFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, format2.pixelWidthHeightRatio)).setProjectionData(bundle.getByteArray(FIELD_PROJECTION_DATA)).setStereoMode(bundle.getInt(FIELD_STEREO_MODE, format2.stereoMode)).setMaxSubLayers(bundle.getInt(FIELD_MAX_SUB_LAYERS, format2.maxSubLayers));
        Bundle bundle2 = bundle.getBundle(FIELD_COLOR_INFO);
        if (bundle2 != null) {
            builder.setColorInfo(ColorInfo.fromBundle(bundle2));
        }
        builder.setChannelCount(bundle.getInt(FIELD_CHANNEL_COUNT, format2.channelCount)).setSampleRate(bundle.getInt(FIELD_SAMPLE_RATE, format2.sampleRate)).setPcmEncoding(bundle.getInt(FIELD_PCM_ENCODING, format2.pcmEncoding)).setEncoderDelay(bundle.getInt(FIELD_ENCODER_DELAY, format2.encoderDelay)).setEncoderPadding(bundle.getInt(FIELD_ENCODER_PADDING, format2.encoderPadding)).setAccessibilityChannel(bundle.getInt(FIELD_ACCESSIBILITY_CHANNEL, format2.accessibilityChannel)).setTileCountHorizontal(bundle.getInt(FIELD_TILE_COUNT_HORIZONTAL, format2.tileCountHorizontal)).setTileCountVertical(bundle.getInt(FIELD_TILE_COUNT_VERTICAL, format2.tileCountVertical)).setCryptoType(bundle.getInt(FIELD_CRYPTO_TYPE, format2.cryptoType));
        return builder.build();
    }

    private static String getDefaultLabel(List<Label> list, String str) {
        for (Label label : list) {
            if (TextUtils.equals(label.language, str)) {
                return label.value;
            }
        }
        return list.get(0).value;
    }

    private static boolean isLabelPartOfLabels(Builder builder) {
        if (builder.labels.isEmpty() && builder.label == null) {
            return true;
        }
        for (int i10 = 0; i10 < builder.labels.size(); i10++) {
            if (((Label) builder.labels.get(i10)).value.equals(builder.label)) {
                return true;
            }
        }
        return false;
    }

    private static String keyForInitializationData(int i10) {
        return FIELD_INITIALIZATION_DATA + "_" + Integer.toString(i10, 36);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$toLogString$0(Label label) {
        return label.language + ": " + label.value;
    }

    public static String toLogString(Format format) {
        if (format == null) {
            return "null";
        }
        com.google.common.base.o oVar = new com.google.common.base.o(String.valueOf(','));
        StringBuilder sbS = androidx.compose.material3.d.s("id=");
        sbS.append(format.id);
        sbS.append(", mimeType=");
        sbS.append(format.sampleMimeType);
        if (format.containerMimeType != null) {
            sbS.append(", container=");
            sbS.append(format.containerMimeType);
        }
        if (format.bitrate != -1) {
            sbS.append(", bitrate=");
            sbS.append(format.bitrate);
        }
        if (format.codecs != null) {
            sbS.append(", codecs=");
            sbS.append(format.codecs);
        }
        if (format.drmInitData != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i10 = 0;
            while (true) {
                DrmInitData drmInitData = format.drmInitData;
                if (i10 >= drmInitData.schemeDataCount) {
                    break;
                }
                UUID uuid = drmInitData.get(i10).uuid;
                if (uuid.equals(C.COMMON_PSSH_UUID)) {
                    linkedHashSet.add(C.CENC_TYPE_cenc);
                } else if (uuid.equals(C.CLEARKEY_UUID)) {
                    linkedHashSet.add("clearkey");
                } else if (uuid.equals(C.PLAYREADY_UUID)) {
                    linkedHashSet.add("playready");
                } else if (uuid.equals(C.WIDEVINE_UUID)) {
                    linkedHashSet.add("widevine");
                } else if (uuid.equals(C.UUID_NIL)) {
                    linkedHashSet.add("universal");
                } else {
                    linkedHashSet.add("unknown (" + uuid + ")");
                }
                i10++;
            }
            sbS.append(", drm=[");
            oVar.a(sbS, linkedHashSet.iterator());
            sbS.append(']');
        }
        if (format.width != -1 && format.height != -1) {
            sbS.append(", res=");
            sbS.append(format.width);
            sbS.append("x");
            sbS.append(format.height);
        }
        if (format.decodedWidth != -1 && format.decodedHeight != -1) {
            sbS.append(", decRes=");
            sbS.append(format.decodedWidth);
            sbS.append("x");
            sbS.append(format.decodedHeight);
        }
        double d4 = format.pixelWidthHeightRatio;
        int i11 = c.f14164a;
        if (Math.copySign(d4 - 1.0d, 1.0d) > 0.001d && d4 != 1.0d && (!Double.isNaN(d4) || !Double.isNaN(1.0d))) {
            sbS.append(", par=");
            sbS.append(Util.formatInvariant("%.3f", Float.valueOf(format.pixelWidthHeightRatio)));
        }
        ColorInfo colorInfo = format.colorInfo;
        if (colorInfo != null && colorInfo.isValid()) {
            sbS.append(", color=");
            sbS.append(format.colorInfo.toLogString());
        }
        if (format.frameRate != -1.0f) {
            sbS.append(", fps=");
            sbS.append(format.frameRate);
        }
        if (format.maxSubLayers != -1) {
            sbS.append(", maxSubLayers=");
            sbS.append(format.maxSubLayers);
        }
        if (format.channelCount != -1) {
            sbS.append(", channels=");
            sbS.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            sbS.append(", sample_rate=");
            sbS.append(format.sampleRate);
        }
        if (format.language != null) {
            sbS.append(", language=");
            sbS.append(format.language);
        }
        if (!format.labels.isEmpty()) {
            sbS.append(", labels=[");
            oVar.a(sbS, com.google.common.collect.a0.x(format.labels, new e(2)).iterator());
            sbS.append("]");
        }
        if (format.selectionFlags != 0) {
            sbS.append(", selectionFlags=[");
            oVar.a(sbS, Util.getSelectionFlagStrings(format.selectionFlags).iterator());
            sbS.append("]");
        }
        if (format.roleFlags != 0) {
            sbS.append(", roleFlags=[");
            oVar.a(sbS, Util.getRoleFlagStrings(format.roleFlags).iterator());
            sbS.append("]");
        }
        if (format.customData != null) {
            sbS.append(", customData=");
            sbS.append(format.customData);
        }
        if ((format.roleFlags & 32768) != 0) {
            sbS.append(", auxiliaryTrackType=");
            sbS.append(Util.getAuxiliaryTrackTypeString(format.auxiliaryTrackType));
        }
        return sbS.toString();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public Format copyWithCryptoType(int i10) {
        return buildUpon().setCryptoType(i10).build();
    }

    public boolean equals(Object obj) {
        int i10;
        if (this == obj) {
            return true;
        }
        if (obj != null && Format.class == obj.getClass()) {
            Format format = (Format) obj;
            int i11 = this.hashCode;
            if ((i11 == 0 || (i10 = format.hashCode) == 0 || i11 == i10) && this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.auxiliaryTrackType == format.auxiliaryTrackType && this.averageBitrate == format.averageBitrate && this.peakBitrate == format.peakBitrate && this.maxInputSize == format.maxInputSize && this.subsampleOffsetUs == format.subsampleOffsetUs && this.width == format.width && this.height == format.height && this.decodedWidth == format.decodedWidth && this.decodedHeight == format.decodedHeight && this.rotationDegrees == format.rotationDegrees && this.stereoMode == format.stereoMode && this.maxSubLayers == format.maxSubLayers && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.accessibilityChannel == format.accessibilityChannel && this.tileCountHorizontal == format.tileCountHorizontal && this.tileCountVertical == format.tileCountVertical && this.cryptoType == format.cryptoType && Float.compare(this.frameRate, format.frameRate) == 0 && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && Objects.equals(this.id, format.id) && Objects.equals(this.label, format.label) && this.labels.equals(format.labels) && Objects.equals(this.codecs, format.codecs) && Objects.equals(this.containerMimeType, format.containerMimeType) && Objects.equals(this.sampleMimeType, format.sampleMimeType) && Objects.equals(this.language, format.language) && Arrays.equals(this.projectionData, format.projectionData) && Objects.equals(this.metadata, format.metadata) && Objects.equals(this.colorInfo, format.colorInfo) && Objects.equals(this.drmInitData, format.drmInitData) && initializationDataEquals(format) && Objects.equals(this.customData, format.customData)) {
                return true;
            }
        }
        return false;
    }

    public int getPixelCount() {
        int i10;
        int i11 = this.width;
        if (i11 == -1 || (i10 = this.height) == -1) {
            return -1;
        }
        return i11 * i10;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.id;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            int iHashCode2 = (this.labels.hashCode() + ((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            String str3 = this.language;
            int iHashCode3 = (((((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.auxiliaryTrackType) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31;
            String str4 = this.codecs;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Metadata metadata = this.metadata;
            int iHashCode5 = (iHashCode4 + (metadata == null ? 0 : metadata.hashCode())) * 31;
            Object obj = this.customData;
            int iHashCode6 = (iHashCode5 + (obj == null ? 0 : obj.hashCode())) * 31;
            String str5 = this.containerMimeType;
            int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.sampleMimeType;
            this.hashCode = ((((((((((((((((((((a0.c.b(this.pixelWidthHeightRatio, (a0.c.b(this.frameRate, (((((((((((((iHashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31) + this.decodedWidth) * 31) + this.decodedHeight) * 31, 31) + this.rotationDegrees) * 31, 31) + this.stereoMode) * 31) + this.maxSubLayers) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31) + this.accessibilityChannel) * 31) + this.tileCountHorizontal) * 31) + this.tileCountVertical) * 31) + this.cryptoType;
        }
        return this.hashCode;
    }

    public boolean initializationDataEquals(Format format) {
        if (this.initializationData.size() != format.initializationData.size()) {
            return false;
        }
        for (int i10 = 0; i10 < this.initializationData.size(); i10++) {
            if (!Arrays.equals(this.initializationData.get(i10), format.initializationData.get(i10))) {
                return false;
            }
        }
        return true;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_ID, this.id);
        bundle.putString(FIELD_LABEL, this.label);
        bundle.putParcelableArrayList(FIELD_LABELS, BundleCollectionUtil.toBundleArrayList(this.labels, new e(0)));
        bundle.putString(FIELD_LANGUAGE, this.language);
        bundle.putInt(FIELD_SELECTION_FLAGS, this.selectionFlags);
        bundle.putInt(FIELD_ROLE_FLAGS, this.roleFlags);
        int i10 = this.auxiliaryTrackType;
        if (i10 != DEFAULT.auxiliaryTrackType) {
            bundle.putInt(FIELD_AUXILIARY_TRACK_TYPE, i10);
        }
        bundle.putInt(FIELD_AVERAGE_BITRATE, this.averageBitrate);
        bundle.putInt(FIELD_PEAK_BITRATE, this.peakBitrate);
        bundle.putString(FIELD_CODECS, this.codecs);
        bundle.putString(FIELD_CONTAINER_MIME_TYPE, this.containerMimeType);
        bundle.putString(FIELD_SAMPLE_MIME_TYPE, this.sampleMimeType);
        bundle.putInt(FIELD_MAX_INPUT_SIZE, this.maxInputSize);
        for (int i11 = 0; i11 < this.initializationData.size(); i11++) {
            bundle.putByteArray(keyForInitializationData(i11), this.initializationData.get(i11));
        }
        bundle.putParcelable(FIELD_DRM_INIT_DATA, this.drmInitData);
        bundle.putLong(FIELD_SUBSAMPLE_OFFSET_US, this.subsampleOffsetUs);
        bundle.putInt(FIELD_WIDTH, this.width);
        bundle.putInt(FIELD_HEIGHT, this.height);
        bundle.putInt(FIELD_DECODED_WIDTH, this.decodedWidth);
        bundle.putInt(FIELD_DECODED_HEIGHT, this.decodedHeight);
        bundle.putFloat(FIELD_FRAME_RATE, this.frameRate);
        bundle.putInt(FIELD_ROTATION_DEGREES, this.rotationDegrees);
        bundle.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, this.pixelWidthHeightRatio);
        bundle.putByteArray(FIELD_PROJECTION_DATA, this.projectionData);
        bundle.putInt(FIELD_STEREO_MODE, this.stereoMode);
        ColorInfo colorInfo = this.colorInfo;
        if (colorInfo != null) {
            bundle.putBundle(FIELD_COLOR_INFO, colorInfo.toBundle());
        }
        bundle.putInt(FIELD_MAX_SUB_LAYERS, this.maxSubLayers);
        bundle.putInt(FIELD_CHANNEL_COUNT, this.channelCount);
        bundle.putInt(FIELD_SAMPLE_RATE, this.sampleRate);
        bundle.putInt(FIELD_PCM_ENCODING, this.pcmEncoding);
        bundle.putInt(FIELD_ENCODER_DELAY, this.encoderDelay);
        bundle.putInt(FIELD_ENCODER_PADDING, this.encoderPadding);
        bundle.putInt(FIELD_ACCESSIBILITY_CHANNEL, this.accessibilityChannel);
        bundle.putInt(FIELD_TILE_COUNT_HORIZONTAL, this.tileCountHorizontal);
        bundle.putInt(FIELD_TILE_COUNT_VERTICAL, this.tileCountVertical);
        bundle.putInt(FIELD_CRYPTO_TYPE, this.cryptoType);
        return bundle;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Format(");
        sb2.append(this.id);
        sb2.append(", ");
        sb2.append(this.label);
        sb2.append(", ");
        sb2.append(this.containerMimeType);
        sb2.append(", ");
        sb2.append(this.sampleMimeType);
        sb2.append(", ");
        sb2.append(this.codecs);
        sb2.append(", ");
        sb2.append(this.bitrate);
        sb2.append(", ");
        sb2.append(this.language);
        sb2.append(", [");
        sb2.append(this.width);
        sb2.append(", ");
        sb2.append(this.height);
        sb2.append(", ");
        sb2.append(this.frameRate);
        sb2.append(", ");
        sb2.append(this.colorInfo);
        sb2.append("], [");
        sb2.append(this.channelCount);
        sb2.append(", ");
        return androidx.compose.material3.d.j(this.sampleRate, "])", sb2);
    }

    public Format withManifestFormatInfo(Format format) {
        String str;
        if (this == format) {
            return this;
        }
        int trackType = MimeTypes.getTrackType(this.sampleMimeType);
        String str2 = format.id;
        int i10 = format.tileCountHorizontal;
        int i11 = format.tileCountVertical;
        String str3 = format.label;
        if (str3 == null) {
            str3 = this.label;
        }
        List<Label> list = !format.labels.isEmpty() ? format.labels : this.labels;
        String str4 = this.language;
        if ((trackType == 3 || trackType == 1) && (str = format.language) != null) {
            str4 = str;
        }
        int i12 = this.averageBitrate;
        if (i12 == -1) {
            i12 = format.averageBitrate;
        }
        int i13 = this.peakBitrate;
        if (i13 == -1) {
            i13 = format.peakBitrate;
        }
        String str5 = this.codecs;
        if (str5 == null) {
            String codecsOfType = Util.getCodecsOfType(format.codecs, trackType);
            if (Util.splitCodecs(codecsOfType).length == 1) {
                str5 = codecsOfType;
            }
        }
        Metadata metadata = this.metadata;
        Metadata metadataCopyWithAppendedEntriesFrom = metadata == null ? format.metadata : metadata.copyWithAppendedEntriesFrom(format.metadata);
        float f10 = this.frameRate;
        if (f10 == -1.0f && trackType == 2) {
            f10 = format.frameRate;
        }
        return buildUpon().setId(str2).setLabel(str3).setLabels(list).setLanguage(str4).setSelectionFlags(this.selectionFlags | format.selectionFlags).setRoleFlags(this.roleFlags | format.roleFlags).setAverageBitrate(i12).setPeakBitrate(i13).setCodecs(str5).setMetadata(metadataCopyWithAppendedEntriesFrom).setDrmInitData(DrmInitData.createSessionCreationData(format.drmInitData, this.drmInitData)).setFrameRate(f10).setTileCountHorizontal(i10).setTileCountVertical(i11).build();
    }

    private Format(Builder builder) {
        this.id = builder.id;
        String strNormalizeLanguageCode = Util.normalizeLanguageCode(builder.language);
        this.language = strNormalizeLanguageCode;
        if (builder.labels.isEmpty() && builder.label != null) {
            this.labels = h1.s(new Label(strNormalizeLanguageCode, builder.label));
            this.label = builder.label;
        } else if (builder.labels.isEmpty() || builder.label != null) {
            ac.b.s(isLabelPartOfLabels(builder));
            this.labels = builder.labels;
            this.label = builder.label;
        } else {
            this.labels = builder.labels;
            this.label = getDefaultLabel(builder.labels, strNormalizeLanguageCode);
        }
        this.selectionFlags = builder.selectionFlags;
        ac.b.t(builder.auxiliaryTrackType == 0 || (builder.roleFlags & 32768) != 0, "Auxiliary track type must only be set to a value other than AUXILIARY_TRACK_TYPE_UNDEFINED only when ROLE_FLAG_AUXILIARY is set");
        this.roleFlags = builder.roleFlags;
        this.auxiliaryTrackType = builder.auxiliaryTrackType;
        int i10 = builder.averageBitrate;
        this.averageBitrate = i10;
        int i11 = builder.peakBitrate;
        this.peakBitrate = i11;
        this.bitrate = i11 != -1 ? i11 : i10;
        this.codecs = builder.codecs;
        this.metadata = builder.metadata;
        this.customData = builder.customData;
        this.containerMimeType = builder.containerMimeType;
        this.sampleMimeType = builder.sampleMimeType;
        this.maxInputSize = builder.maxInputSize;
        this.maxNumReorderSamples = builder.maxNumReorderSamples;
        this.initializationData = builder.initializationData == null ? Collections.EMPTY_LIST : builder.initializationData;
        DrmInitData drmInitData = builder.drmInitData;
        this.drmInitData = drmInitData;
        this.subsampleOffsetUs = builder.subsampleOffsetUs;
        this.hasPrerollSamples = builder.hasPrerollSamples;
        this.width = builder.width;
        this.height = builder.height;
        this.decodedWidth = builder.decodedWidth;
        this.decodedHeight = builder.decodedHeight;
        this.frameRate = builder.frameRate;
        this.rotationDegrees = builder.rotationDegrees == -1 ? 0 : builder.rotationDegrees;
        this.pixelWidthHeightRatio = builder.pixelWidthHeightRatio == -1.0f ? 1.0f : builder.pixelWidthHeightRatio;
        this.projectionData = builder.projectionData;
        this.stereoMode = builder.stereoMode;
        this.colorInfo = builder.colorInfo;
        this.maxSubLayers = builder.maxSubLayers;
        this.channelCount = builder.channelCount;
        this.sampleRate = builder.sampleRate;
        this.pcmEncoding = builder.pcmEncoding;
        this.encoderDelay = builder.encoderDelay == -1 ? 0 : builder.encoderDelay;
        this.encoderPadding = builder.encoderPadding != -1 ? builder.encoderPadding : 0;
        this.accessibilityChannel = builder.accessibilityChannel;
        this.cueReplacementBehavior = builder.cueReplacementBehavior;
        this.tileCountHorizontal = builder.tileCountHorizontal;
        this.tileCountVertical = builder.tileCountVertical;
        if (builder.cryptoType != 0 || drmInitData == null) {
            this.cryptoType = builder.cryptoType;
        } else {
            this.cryptoType = 1;
        }
    }
}
