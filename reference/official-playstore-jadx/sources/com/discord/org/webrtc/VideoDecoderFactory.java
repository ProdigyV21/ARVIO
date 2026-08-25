package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoDecoderFactory {
    VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo);

    VideoCodecInfo[] getSupportedCodecs();
}
