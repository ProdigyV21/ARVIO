package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes4.dex */
public class MockBuffer implements VideoFrame.Buffer {
    private static final VideoFrame.Buffer SAFE_FALLBACK = JavaI420Buffer.allocate(1, 1);
    private final int height;
    private final int width;

    public MockBuffer(int i10, int i11) {
        this.width = i10;
        this.height = i11;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i10, int i11, int i12, int i13, int i14, int i15) {
        return new MockBuffer(i14, i15);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public final /* synthetic */ int getBufferType() {
        return g0.a(this);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void release() {
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void retain() {
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        return SAFE_FALLBACK.toI420();
    }
}
