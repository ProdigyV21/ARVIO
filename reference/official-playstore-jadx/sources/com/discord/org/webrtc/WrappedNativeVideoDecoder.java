package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoDecoder;

/* JADX INFO: loaded from: classes4.dex */
public abstract class WrappedNativeVideoDecoder implements VideoDecoder {
    @Override // com.discord.org.webrtc.VideoDecoder
    public abstract long createNative(long j10);

    @Override // com.discord.org.webrtc.VideoDecoder
    public final VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public final String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public final VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public final VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public final /* synthetic */ void setStreamId(String str) {
        a0.b(this, str);
    }
}
