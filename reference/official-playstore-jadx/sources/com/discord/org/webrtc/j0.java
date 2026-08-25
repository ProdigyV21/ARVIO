package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoProcessor;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class j0 {
    public static void a(VideoProcessor videoProcessor, VideoFrame videoFrame, VideoProcessor.FrameAdaptationParameters frameAdaptationParameters) {
        VideoFrame videoFrameB = b(videoFrame, frameAdaptationParameters);
        if (videoFrameB != null) {
            videoProcessor.onFrameCaptured(videoFrameB);
            videoFrameB.release();
        }
    }

    public static VideoFrame b(VideoFrame videoFrame, VideoProcessor.FrameAdaptationParameters frameAdaptationParameters) {
        if (frameAdaptationParameters.drop) {
            return null;
        }
        return new VideoFrame(videoFrame.getBuffer().cropAndScale(frameAdaptationParameters.cropX, frameAdaptationParameters.cropY, frameAdaptationParameters.cropWidth, frameAdaptationParameters.cropHeight, frameAdaptationParameters.scaleWidth, frameAdaptationParameters.scaleHeight), videoFrame.getRotation(), frameAdaptationParameters.timestampNs);
    }
}
