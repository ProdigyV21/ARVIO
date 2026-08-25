package com.discord.org.webrtc;

import android.opengl.GLES20;
import android.opengl.GLException;
import androidx.fragment.app.a2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes4.dex */
public class GlUtil {

    public static class GlOutOfMemoryException extends GLException {
        public GlOutOfMemoryException(int i10, String str) {
            super(i10, str);
        }
    }

    private GlUtil() {
    }

    public static void checkNoGLES2Error(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            if (iGlGetError != 1285) {
                throw new GLException(iGlGetError, a2.j(iGlGetError, str, ": GLES20 error: "));
            }
        }
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        floatBufferAsFloatBuffer.put(fArr);
        return floatBufferAsFloatBuffer;
    }

    public static int generateTexture(int i10) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i11 = iArr[0];
        GLES20.glBindTexture(i10, i11);
        GLES20.glTexParameterf(i10, 10241, 9729.0f);
        GLES20.glTexParameterf(i10, 10240, 9729.0f);
        GLES20.glTexParameterf(i10, 10242, 33071.0f);
        GLES20.glTexParameterf(i10, 10243, 33071.0f);
        checkNoGLES2Error("generateTexture");
        return i11;
    }
}
