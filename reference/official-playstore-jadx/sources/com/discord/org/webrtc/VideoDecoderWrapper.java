package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoDecoder;

/* JADX INFO: loaded from: classes4.dex */
class VideoDecoderWrapper {
    public static VideoDecoder.Callback createDecoderCallback(long j10) {
        return new c0(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnDecodedFrame(long j10, VideoFrame videoFrame, Integer num, Integer num2);
}
