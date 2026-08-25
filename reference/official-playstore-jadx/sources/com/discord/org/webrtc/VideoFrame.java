package com.discord.org.webrtc;

import android.graphics.Matrix;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public class VideoFrame implements RefCounted {
    private final Buffer buffer;
    private final int rotation;
    private final long timestampNs;

    public interface Buffer extends RefCounted {
        Buffer cropAndScale(int i10, int i11, int i12, int i13, int i14, int i15);

        int getBufferType();

        int getHeight();

        int getWidth();

        @Override // com.discord.org.webrtc.RefCounted
        void release();

        @Override // com.discord.org.webrtc.RefCounted
        void retain();

        I420Buffer toI420();
    }

    public interface I420Buffer extends Buffer {
        @Override // com.discord.org.webrtc.VideoFrame.Buffer
        int getBufferType();

        ByteBuffer getDataU();

        ByteBuffer getDataV();

        ByteBuffer getDataY();

        int getStrideU();

        int getStrideV();

        int getStrideY();
    }

    public interface TextureBuffer extends Buffer {

        public enum Type {
            OES(36197),
            RGB(3553);

            private final int glTarget;

            Type(int i10) {
                this.glTarget = i10;
            }

            public int getGlTarget() {
                return this.glTarget;
            }
        }

        TextureBuffer applyTransformMatrix(Matrix matrix, int i10, int i11);

        int getTextureId();

        Matrix getTransformMatrix();

        Type getType();

        int getUnscaledHeight();

        int getUnscaledWidth();
    }

    public VideoFrame(Buffer buffer, int i10, long j10) {
        if (buffer == null) {
            throw new IllegalArgumentException("buffer not allowed to be null");
        }
        if (i10 % 90 != 0) {
            throw new IllegalArgumentException("rotation must be a multiple of 90");
        }
        this.buffer = buffer;
        this.rotation = i10;
        this.timestampNs = j10;
    }

    public Buffer getBuffer() {
        return this.buffer;
    }

    public int getRotatedHeight() {
        return this.rotation % 180 == 0 ? this.buffer.getHeight() : this.buffer.getWidth();
    }

    public int getRotatedWidth() {
        return this.rotation % 180 == 0 ? this.buffer.getWidth() : this.buffer.getHeight();
    }

    public int getRotation() {
        return this.rotation;
    }

    public long getTimestampNs() {
        return this.timestampNs;
    }

    @Override // com.discord.org.webrtc.RefCounted
    public void release() {
        this.buffer.release();
    }

    @Override // com.discord.org.webrtc.RefCounted
    public void retain() {
        this.buffer.retain();
    }
}
