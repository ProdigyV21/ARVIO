package com.discord.org.webrtc;

import android.graphics.Matrix;
import com.discord.org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class i0 {
    public static VideoFrame.TextureBuffer a(VideoFrame.TextureBuffer textureBuffer, Matrix matrix, int i10, int i11) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static int b(VideoFrame.TextureBuffer textureBuffer) {
        return textureBuffer.getHeight();
    }

    public static int c(VideoFrame.TextureBuffer textureBuffer) {
        return textureBuffer.getWidth();
    }
}
