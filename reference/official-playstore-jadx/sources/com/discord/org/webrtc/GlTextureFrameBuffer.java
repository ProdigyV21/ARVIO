package com.discord.org.webrtc;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes4.dex */
public class GlTextureFrameBuffer {
    private int frameBufferId;
    private int height;
    private final int pixelFormat;
    private int textureId;
    private int width;

    public GlTextureFrameBuffer(int i10) {
        switch (i10) {
            case 6407:
            case 6408:
            case 6409:
                this.pixelFormat = i10;
                this.width = 0;
                this.height = 0;
                return;
            default:
                throw new IllegalArgumentException(a0.c.i(i10, "Invalid pixel format: "));
        }
    }

    public int getFrameBufferId() {
        return this.frameBufferId;
    }

    public int getHeight() {
        return this.height;
    }

    public int getTextureId() {
        return this.textureId;
    }

    public int getWidth() {
        return this.width;
    }

    public void release() {
        GLES20.glDeleteTextures(1, new int[]{this.textureId}, 0);
        this.textureId = 0;
        GLES20.glDeleteFramebuffers(1, new int[]{this.frameBufferId}, 0);
        this.frameBufferId = 0;
        this.width = 0;
        this.height = 0;
    }

    public void setSize(int i10, int i11) {
        if (i10 <= 0 || i11 <= 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, i11, "Invalid size: ", "x"));
        }
        if (i10 == this.width && i11 == this.height) {
            return;
        }
        this.width = i10;
        this.height = i11;
        if (this.textureId == 0) {
            this.textureId = GlUtil.generateTexture(3553);
        }
        if (this.frameBufferId == 0) {
            int[] iArr = new int[1];
            GLES20.glGenFramebuffers(1, iArr, 0);
            this.frameBufferId = iArr[0];
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.textureId);
        int i12 = this.pixelFormat;
        GLES20.glTexImage2D(3553, 0, i12, i10, i11, 0, i12, 5121, null);
        GLES20.glBindTexture(3553, 0);
        GlUtil.checkNoGLES2Error("GlTextureFrameBuffer setSize");
        GLES20.glBindFramebuffer(36160, this.frameBufferId);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.textureId, 0);
        int iGlCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        if (iGlCheckFramebufferStatus != 36053) {
            throw new IllegalStateException(a0.c.i(iGlCheckFramebufferStatus, "Framebuffer not complete, status: "));
        }
        GLES20.glBindFramebuffer(36160, 0);
    }
}
