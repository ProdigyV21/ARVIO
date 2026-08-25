package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoEncoder;

/* JADX INFO: loaded from: classes4.dex */
class VideoEncoderWrapper {
    public static VideoEncoder.Callback createEncoderCallback(long j10) {
        return new c0(j10);
    }

    public static Integer getScalingSettingsHigh(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.high;
    }

    public static Integer getScalingSettingsLow(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.low;
    }

    public static boolean getScalingSettingsOn(VideoEncoder.ScalingSettings scalingSettings) {
        return scalingSettings.on;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnEncodedFrame(long j10, EncodedImage encodedImage);
}
