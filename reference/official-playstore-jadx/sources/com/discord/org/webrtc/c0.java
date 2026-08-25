package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoDecoder;
import com.discord.org.webrtc.VideoEncoder;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c0 implements VideoDecoder.Callback, VideoEncoder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ long f12512a;

    public /* synthetic */ c0(long j10) {
        this.f12512a = j10;
    }

    @Override // com.discord.org.webrtc.VideoDecoder.Callback
    public void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2) {
        VideoDecoderWrapper.nativeOnDecodedFrame(this.f12512a, videoFrame, num, num2);
    }

    @Override // com.discord.org.webrtc.VideoEncoder.Callback
    public void onEncodedFrame(EncodedImage encodedImage, VideoEncoder.CodecSpecificInfo codecSpecificInfo) {
        VideoEncoderWrapper.nativeOnEncodedFrame(this.f12512a, encodedImage);
    }
}
