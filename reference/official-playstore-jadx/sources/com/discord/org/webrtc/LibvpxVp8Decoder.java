package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class LibvpxVp8Decoder extends WrappedNativeVideoDecoder {
    public static native long nativeCreateDecoder(long j10);

    @Override // com.discord.org.webrtc.WrappedNativeVideoDecoder, com.discord.org.webrtc.VideoDecoder
    public long createNative(long j10) {
        return nativeCreateDecoder(j10);
    }
}
