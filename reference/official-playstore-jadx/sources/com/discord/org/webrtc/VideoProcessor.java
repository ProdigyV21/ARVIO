package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoProcessor extends CapturerObserver {

    public static class FrameAdaptationParameters {
        public final int cropHeight;
        public final int cropWidth;
        public final int cropX;
        public final int cropY;
        public final boolean drop;
        public final int scaleHeight;
        public final int scaleWidth;
        public final long timestampNs;

        public FrameAdaptationParameters(int i10, int i11, int i12, int i13, int i14, int i15, long j10, boolean z) {
            this.cropX = i10;
            this.cropY = i11;
            this.cropWidth = i12;
            this.cropHeight = i13;
            this.scaleWidth = i14;
            this.scaleHeight = i15;
            this.timestampNs = j10;
            this.drop = z;
        }
    }

    void onFrameCaptured(VideoFrame videoFrame, FrameAdaptationParameters frameAdaptationParameters);

    void setSink(VideoSink videoSink);
}
