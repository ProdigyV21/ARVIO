package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class LibvpxVp9Decoder extends WrappedNativeVideoDecoder {
    public static native long nativeCreateDecoder();

    public static native boolean nativeIsSupported();

    @Override // com.discord.org.webrtc.WrappedNativeVideoDecoder, com.discord.org.webrtc.VideoDecoder
    public long createNative(long j10) {
        return nativeCreateDecoder();
    }
}
