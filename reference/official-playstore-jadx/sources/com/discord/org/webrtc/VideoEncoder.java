package com.discord.org.webrtc;

import com.discord.org.webrtc.EncodedImage;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoEncoder {

    public static class BitrateAllocation {
        public final int[][] bitratesBbs;

        public BitrateAllocation(int[][] iArr) {
            this.bitratesBbs = iArr;
        }

        public int getSum() {
            int i10 = 0;
            for (int[] iArr : this.bitratesBbs) {
                for (int i11 : iArr) {
                    i10 += i11;
                }
            }
            return i10;
        }
    }

    public interface Callback {
        void onEncodedFrame(EncodedImage encodedImage, CodecSpecificInfo codecSpecificInfo);
    }

    public static class Capabilities {
        public final boolean lossNotification;

        public Capabilities(boolean z) {
            this.lossNotification = z;
        }
    }

    public static class CodecSpecificInfo {
    }

    public static class CodecSpecificInfoAV1 extends CodecSpecificInfo {
    }

    public static class CodecSpecificInfoH264 extends CodecSpecificInfo {
    }

    public static class CodecSpecificInfoVP8 extends CodecSpecificInfo {
    }

    public static class CodecSpecificInfoVP9 extends CodecSpecificInfo {
    }

    public static class EncodeInfo {
        public final EncodedImage.FrameType[] frameTypes;

        public EncodeInfo(EncodedImage.FrameType[] frameTypeArr) {
            this.frameTypes = frameTypeArr;
        }
    }

    public static class EncoderInfo {
        public final boolean applyAlignmentToAllSimulcastLayers;
        public final int requestedResolutionAlignment;

        public EncoderInfo(int i10, boolean z) {
            this.requestedResolutionAlignment = i10;
            this.applyAlignmentToAllSimulcastLayers = z;
        }

        public boolean getApplyAlignmentToAllSimulcastLayers() {
            return this.applyAlignmentToAllSimulcastLayers;
        }

        public int getRequestedResolutionAlignment() {
            return this.requestedResolutionAlignment;
        }
    }

    public static class RateControlParameters {
        public final BitrateAllocation bitrate;
        public final double framerateFps;

        public RateControlParameters(BitrateAllocation bitrateAllocation, double d4) {
            this.bitrate = bitrateAllocation;
            this.framerateFps = d4;
        }
    }

    public static class ResolutionBitrateLimits {
        public final int frameSizePixels;
        public final int maxBitrateBps;
        public final int minBitrateBps;
        public final int minStartBitrateBps;

        public ResolutionBitrateLimits(int i10, int i11, int i12, int i13) {
            this.frameSizePixels = i10;
            this.minStartBitrateBps = i11;
            this.minBitrateBps = i12;
            this.maxBitrateBps = i13;
        }

        public int getFrameSizePixels() {
            return this.frameSizePixels;
        }

        public int getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        public int getMinBitrateBps() {
            return this.minBitrateBps;
        }

        public int getMinStartBitrateBps() {
            return this.minStartBitrateBps;
        }
    }

    public static class Settings {
        public final boolean automaticResizeOn;
        public final Capabilities capabilities;
        public final int height;
        public final int maxFramerate;
        public final int numberOfCores;
        public final int numberOfSimulcastStreams;
        public final int startBitrate;
        public final int width;

        @Deprecated
        public Settings(int i10, int i11, int i12, int i13, int i14, int i15, boolean z) {
            this(i10, i11, i12, i13, i14, i15, z, new Capabilities(false));
        }

        public Settings(int i10, int i11, int i12, int i13, int i14, int i15, boolean z, Capabilities capabilities) {
            this.numberOfCores = i10;
            this.width = i11;
            this.height = i12;
            this.startBitrate = i13;
            this.maxFramerate = i14;
            this.numberOfSimulcastStreams = i15;
            this.automaticResizeOn = z;
            this.capabilities = capabilities;
        }
    }

    long createNative(long j10);

    VideoCodecStatus encode(VideoFrame videoFrame, EncodeInfo encodeInfo);

    EncoderInfo getEncoderInfo();

    String getImplementationName();

    ResolutionBitrateLimits[] getResolutionBitrateLimits();

    ScalingSettings getScalingSettings();

    VideoCodecStatus initEncode(Settings settings, Callback callback);

    boolean isHardwareEncoder();

    VideoCodecStatus release();

    VideoCodecStatus setRateAllocation(BitrateAllocation bitrateAllocation, int i10);

    VideoCodecStatus setRates(RateControlParameters rateControlParameters);

    public static class ScalingSettings {
        public static final ScalingSettings OFF = new ScalingSettings();
        public final Integer high;
        public final Integer low;
        public final boolean on;

        public ScalingSettings(int i10, int i11) {
            this.on = true;
            this.low = Integer.valueOf(i10);
            this.high = Integer.valueOf(i11);
        }

        public String toString() {
            if (!this.on) {
                return "OFF";
            }
            return "[ " + this.low + ", " + this.high + " ]";
        }

        private ScalingSettings() {
            this.on = false;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean z) {
            this.on = z;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean z, int i10, int i11) {
            this.on = z;
            this.low = Integer.valueOf(i10);
            this.high = Integer.valueOf(i11);
        }
    }
}
