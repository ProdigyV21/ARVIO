package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class LibvpxVp9Encoder extends WrappedNativeVideoEncoder {
    public static native long nativeCreate(long j10);

    public static native boolean nativeIsSupported();

    @Override // com.discord.org.webrtc.WrappedNativeVideoEncoder, com.discord.org.webrtc.VideoEncoder
    public long createNative(long j10) {
        return nativeCreate(j10);
    }

    @Override // com.discord.org.webrtc.WrappedNativeVideoEncoder, com.discord.org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }
}
