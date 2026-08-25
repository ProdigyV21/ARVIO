package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoFrame;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
class WrappedNativeI420Buffer implements VideoFrame.I420Buffer {
    private final ByteBuffer dataU;
    private final ByteBuffer dataV;
    private final ByteBuffer dataY;
    private final int height;
    private final long nativeBuffer;
    private final int strideU;
    private final int strideV;
    private final int strideY;
    private final int width;

    public WrappedNativeI420Buffer(int i10, int i11, ByteBuffer byteBuffer, int i12, ByteBuffer byteBuffer2, int i13, ByteBuffer byteBuffer3, int i14, long j10) {
        this.width = i10;
        this.height = i11;
        this.dataY = byteBuffer;
        this.strideY = i12;
        this.dataU = byteBuffer2;
        this.strideU = i13;
        this.dataV = byteBuffer3;
        this.strideV = i14;
        this.nativeBuffer = j10;
        retain();
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i10, int i11, int i12, int i13, int i14, int i15) {
        return JavaI420Buffer.cropAndScaleI420(this, i10, i11, i12, i13, i14, i15);
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer, com.discord.org.webrtc.VideoFrame.Buffer
    public final /* synthetic */ int getBufferType() {
        return h0.a(this);
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataU() {
        return this.dataU.slice();
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataV() {
        return this.dataV.slice();
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataY() {
        return this.dataY.slice();
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public int getStrideU() {
        return this.strideU;
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public int getStrideV() {
        return this.strideV;
    }

    @Override // com.discord.org.webrtc.VideoFrame.I420Buffer
    public int getStrideY() {
        return this.strideY;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void release() {
        JniCommon.nativeReleaseRef(this.nativeBuffer);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void retain() {
        JniCommon.nativeAddRef(this.nativeBuffer);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        retain();
        return this;
    }
}
