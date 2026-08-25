package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoFrame;
import com.discord.org.webrtc.VideoProcessor;
import com.discord.org.webrtc.VideoSource;

/* JADX INFO: loaded from: classes4.dex */
class NativeAndroidVideoTrackSource {
    private final long nativeAndroidVideoTrackSource;

    public NativeAndroidVideoTrackSource(long j10) {
        this.nativeAndroidVideoTrackSource = j10;
    }

    public static VideoProcessor.FrameAdaptationParameters createFrameAdaptationParameters(int i10, int i11, int i12, int i13, int i14, int i15, long j10, boolean z) {
        return new VideoProcessor.FrameAdaptationParameters(i10, i11, i12, i13, i14, i15, j10, z);
    }

    private static native VideoProcessor.FrameAdaptationParameters nativeAdaptFrame(long j10, int i10, int i11, int i12, long j11);

    private static native void nativeAdaptOutputFormat(long j10, int i10, int i11, Integer num, int i12, int i13, Integer num2, Integer num3);

    private static native void nativeOnFrameCaptured(long j10, int i10, long j11, VideoFrame.Buffer buffer);

    private static native void nativeSetIsScreencast(long j10, boolean z);

    private static native void nativeSetState(long j10, boolean z);

    public VideoProcessor.FrameAdaptationParameters adaptFrame(VideoFrame videoFrame) {
        return nativeAdaptFrame(this.nativeAndroidVideoTrackSource, videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation(), videoFrame.getTimestampNs());
    }

    public void adaptOutputFormat(VideoSource.AspectRatio aspectRatio, Integer num, VideoSource.AspectRatio aspectRatio2, Integer num2, Integer num3) {
        nativeAdaptOutputFormat(this.nativeAndroidVideoTrackSource, aspectRatio.width, aspectRatio.height, num, aspectRatio2.width, aspectRatio2.height, num2, num3);
    }

    public void onFrameCaptured(VideoFrame videoFrame) {
        nativeOnFrameCaptured(this.nativeAndroidVideoTrackSource, videoFrame.getRotation(), videoFrame.getTimestampNs(), videoFrame.getBuffer());
    }

    public void setIsScreencast(boolean z) {
        nativeSetIsScreencast(this.nativeAndroidVideoTrackSource, z);
    }

    public void setState(boolean z) {
        nativeSetState(this.nativeAndroidVideoTrackSource, z);
    }
}
