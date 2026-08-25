package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class VideoDecoderFallback extends WrappedNativeVideoDecoder {
    private final VideoDecoder fallback;
    private final VideoDecoder primary;

    public VideoDecoderFallback(VideoDecoder videoDecoder, VideoDecoder videoDecoder2) {
        this.fallback = videoDecoder;
        this.primary = videoDecoder2;
    }

    private static native long nativeCreate(long j10, VideoDecoder videoDecoder, VideoDecoder videoDecoder2);

    @Override // com.discord.org.webrtc.WrappedNativeVideoDecoder, com.discord.org.webrtc.VideoDecoder
    public long createNative(long j10) {
        return nativeCreate(j10, this.fallback, this.primary);
    }
}
