package androidx.media3.common;

import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import androidx.media3.common.util.GlUtil;

/* JADX INFO: loaded from: classes3.dex */
public interface GlObjectsProvider {
    GlTextureInfo createBuffersForTexture(int i10, int i11, int i12) throws GlUtil.GlException;

    EGLContext createEglContext(EGLDisplay eGLDisplay, int i10, int[] iArr) throws GlUtil.GlException;

    EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i10, boolean z) throws GlUtil.GlException;

    EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) throws GlUtil.GlException;

    void release(EGLDisplay eGLDisplay) throws GlUtil.GlException;
}
