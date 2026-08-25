package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class VideoEncoderFallback extends WrappedNativeVideoEncoder {
    private final VideoEncoder fallback;
    private final VideoEncoder primary;

    public VideoEncoderFallback(VideoEncoder videoEncoder, VideoEncoder videoEncoder2) {
        this.fallback = videoEncoder;
        this.primary = videoEncoder2;
    }

    private static native long nativeCreate(long j10, VideoEncoder videoEncoder, VideoEncoder videoEncoder2);

    @Override // com.discord.org.webrtc.WrappedNativeVideoEncoder, com.discord.org.webrtc.VideoEncoder
    public long createNative(long j10) {
        return nativeCreate(j10, this.fallback, this.primary);
    }

    @Override // com.discord.org.webrtc.WrappedNativeVideoEncoder, com.discord.org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return this.primary.isHardwareEncoder();
    }
}
