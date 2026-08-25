package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoEncoderFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class e0 {
    public static VideoEncoderFactory.VideoEncoderSelector a(VideoEncoderFactory videoEncoderFactory) {
        return null;
    }

    public static VideoCodecInfo[] b(VideoEncoderFactory videoEncoderFactory) {
        return videoEncoderFactory.getSupportedCodecs();
    }
}
