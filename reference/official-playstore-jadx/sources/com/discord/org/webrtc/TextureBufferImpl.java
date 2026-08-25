package com.discord.org.webrtc;

import android.graphics.Matrix;
import android.os.Handler;
import com.discord.org.webrtc.VideoFrame;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;
    private final int id;
    private final RefCountDelegate refCountDelegate;
    private final RefCountMonitor refCountMonitor;
    private final Handler toI420Handler;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int unscaledHeight;
    private final int unscaledWidth;
    private final int width;
    private final YuvConverter yuvConverter;

    public interface RefCountMonitor {
        void onDestroy(TextureBufferImpl textureBufferImpl);

        void onRelease(TextureBufferImpl textureBufferImpl);

        void onRetain(TextureBufferImpl textureBufferImpl);
    }

    public TextureBufferImpl(int i10, int i11, VideoFrame.TextureBuffer.Type type, int i12, Matrix matrix, Handler handler, YuvConverter yuvConverter, final Runnable runnable) {
        this(i10, i11, i10, i11, type, i12, matrix, handler, yuvConverter, new RefCountMonitor() { // from class: com.discord.org.webrtc.TextureBufferImpl.1
            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onDestroy(TextureBufferImpl textureBufferImpl) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }

            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRelease(TextureBufferImpl textureBufferImpl) {
            }

            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRetain(TextureBufferImpl textureBufferImpl) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(RefCountMonitor refCountMonitor) {
        refCountMonitor.onDestroy(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ VideoFrame.I420Buffer lambda$toI420$1() throws Exception {
        return this.yuvConverter.convert(this);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i10, int i11, int i12, int i13, int i14, int i15) {
        Matrix matrix = new Matrix();
        matrix.preTranslate(i10 / this.width, (r1 - (i11 + i13)) / this.height);
        matrix.preScale(i12 / this.width, i13 / this.height);
        return applyTransformMatrix(matrix, Math.round((this.unscaledWidth * i12) / this.width), Math.round((this.unscaledHeight * i13) / this.height), i14, i15);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public final /* synthetic */ int getBufferType() {
        return g0.a(this);
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public int getTextureId() {
        return this.id;
    }

    public Handler getToI420Handler() {
        return this.toI420Handler;
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public Matrix getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public int getUnscaledHeight() {
        return this.unscaledHeight;
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public int getUnscaledWidth() {
        return this.unscaledWidth;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    public YuvConverter getYuvConverter() {
        return this.yuvConverter;
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void release() {
        this.refCountMonitor.onRelease(this);
        this.refCountDelegate.release();
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer, com.discord.org.webrtc.RefCounted
    public void retain() {
        this.refCountMonitor.onRetain(this);
        this.refCountDelegate.retain();
    }

    @Override // com.discord.org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        return (VideoFrame.I420Buffer) ThreadUtils.invokeAtFrontUninterruptibly(this.toI420Handler, new Callable() { // from class: com.discord.org.webrtc.z
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f12553a.lambda$toI420$1();
            }
        });
    }

    public TextureBufferImpl(int i10, int i11, VideoFrame.TextureBuffer.Type type, int i12, Matrix matrix, Handler handler, YuvConverter yuvConverter, RefCountMonitor refCountMonitor) {
        this(i10, i11, i10, i11, type, i12, matrix, handler, yuvConverter, refCountMonitor);
    }

    @Override // com.discord.org.webrtc.VideoFrame.TextureBuffer
    public TextureBufferImpl applyTransformMatrix(Matrix matrix, int i10, int i11) {
        return applyTransformMatrix(matrix, i10, i11, i10, i11);
    }

    private TextureBufferImpl(int i10, int i11, int i12, int i13, VideoFrame.TextureBuffer.Type type, int i14, Matrix matrix, Handler handler, YuvConverter yuvConverter, RefCountMonitor refCountMonitor) {
        this.unscaledWidth = i10;
        this.unscaledHeight = i11;
        this.width = i12;
        this.height = i13;
        this.type = type;
        this.id = i14;
        this.transformMatrix = matrix;
        this.toI420Handler = handler;
        this.yuvConverter = yuvConverter;
        this.refCountDelegate = new RefCountDelegate(new y(this, refCountMonitor, 0));
        this.refCountMonitor = refCountMonitor;
    }

    private TextureBufferImpl applyTransformMatrix(Matrix matrix, int i10, int i11, int i12, int i13) {
        Matrix matrix2 = new Matrix(this.transformMatrix);
        matrix2.preConcat(matrix);
        retain();
        return new TextureBufferImpl(i10, i11, i12, i13, this.type, this.id, matrix2, this.toI420Handler, this.yuvConverter, new RefCountMonitor() { // from class: com.discord.org.webrtc.TextureBufferImpl.2
            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onDestroy(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.release();
            }

            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRelease(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.refCountMonitor.onRelease(TextureBufferImpl.this);
            }

            @Override // com.discord.org.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRetain(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.refCountMonitor.onRetain(TextureBufferImpl.this);
            }
        });
    }
}
