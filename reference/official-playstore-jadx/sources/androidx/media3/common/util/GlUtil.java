package androidx.media3.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Build;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import com.google.common.collect.a0;
import com.google.common.collect.b1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GlUtil {
    private static final String EXTENSION_COLORSPACE_BT2020_HLG = "EGL_EXT_gl_colorspace_bt2020_hlg";
    private static final String EXTENSION_COLORSPACE_BT2020_PQ = "EGL_EXT_gl_colorspace_bt2020_pq";
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final String EXTENSION_YUV_TARGET = "GL_EXT_YUV_target";
    private static final long GL_FENCE_SYNC_FAILED = 0;
    public static final int HOMOGENEOUS_COORDINATE_VECTOR_SIZE = 4;
    public static final float LENGTH_NDC = 2.0f;
    public static final int MAX_BITMAP_DECODING_SIZE = 4096;
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_8888 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = {12352, 4, 12324, 10, 12323, 10, 12322, 10, 12321, 2, 12325, 0, 12326, 0, 12344};
    private static final int EGL_GL_COLORSPACE_KHR = 12445;
    private static final int EGL_GL_COLORSPACE_BT2020_PQ_EXT = 13120;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = {EGL_GL_COLORSPACE_KHR, EGL_GL_COLORSPACE_BT2020_PQ_EXT, 12344, 12344};
    private static final int EGL_GL_COLORSPACE_BT2020_HLG_EXT = 13632;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG = {EGL_GL_COLORSPACE_KHR, EGL_GL_COLORSPACE_BT2020_HLG_EXT, 12344, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = {12344};

    private GlUtil() {
    }

    private static void assertValidTextureSize(int i10, int i11) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        int i12 = iArr[0];
        ac.b.t(i12 > 0, "Create a OpenGL context first or run the GL methods on an OpenGL thread.");
        if (i10 < 0 || i11 < 0) {
            throw new GlException("width or height is less than 0");
        }
        if (i10 > i12 || i11 > i12) {
            throw new GlException(a0.c.i(i12, "width or height is greater than GL_MAX_TEXTURE_SIZE "));
        }
    }

    public static void awaitSyncObject(long j10) throws GlException {
        if (j10 == 0) {
            GLES20.glFinish();
        } else {
            GLES30.glWaitSync(j10, 0, -1L);
            checkGlError();
        }
    }

    public static void bindTexture(int i10, int i11, int i12) throws GlException {
        GLES20.glBindTexture(i10, i11);
        checkGlError();
        GLES20.glTexParameteri(i10, 10240, i12);
        checkGlError();
        GLES20.glTexParameteri(i10, 10241, i12);
        checkGlError();
        GLES20.glTexParameteri(i10, 10242, 33071);
        checkGlError();
        GLES20.glTexParameteri(i10, 10243, 33071);
        checkGlError();
    }

    public static void blitFrameBuffer(int i10, GlRect glRect, int i11, GlRect glRect2) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        checkGlError();
        GLES20.glBindFramebuffer(36008, i10);
        checkGlError();
        GLES20.glBindFramebuffer(36009, i11);
        checkGlError();
        GLES30.glBlitFramebuffer(glRect.left, glRect.bottom, glRect.right, glRect.top, glRect2.left, glRect2.bottom, glRect2.right, glRect2.top, 16384, C.TEXTURE_MIN_FILTER_LINEAR);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
    }

    private static void checkEglException(String str) throws GlException {
        int iEglGetError = EGL14.eglGetError();
        if (iEglGetError == 12288) {
            return;
        }
        StringBuilder sbP = a2.p(str, ", error code: 0x");
        sbP.append(Integer.toHexString(iEglGetError));
        throw new GlException(sbP.toString(), h1.s(Integer.valueOf(iEglGetError)));
    }

    public static void checkGlError() throws GlException {
        StringBuilder sb2 = new StringBuilder();
        a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        boolean z = false;
        int i10 = 0;
        while (true) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                break;
            }
            if (z) {
                sb2.append('\n');
            }
            String strGluErrorString = GLU.gluErrorString(iGlGetError);
            if (strGluErrorString == null) {
                strGluErrorString = "error code: 0x" + Integer.toHexString(iGlGetError);
            }
            sb2.append("glError: ");
            sb2.append(strGluErrorString);
            Integer numValueOf = Integer.valueOf(iGlGetError);
            int i11 = i10 + 1;
            int iB = b1.b(objArrCopyOf.length, i11);
            if (iB > objArrCopyOf.length) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
            }
            objArrCopyOf[i10] = numValueOf;
            z = true;
            i10 = i11;
        }
        if (z) {
            throw new GlException(sb2.toString(), h1.h(i10, objArrCopyOf));
        }
    }

    public static void checkGlException(boolean z, String str) throws GlException {
        if (!z) {
            throw new GlException(str);
        }
    }

    public static void clearFocusedBuffers() throws GlException {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glClear(16640);
        checkGlError();
    }

    public static float[] create4x4IdentityMatrix() {
        float[] fArr = new float[16];
        setToIdentity(fArr);
        return fArr;
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        return (FloatBuffer) createBuffer(fArr.length).put(fArr).flip();
    }

    public static EGLContext createEglContext(EGLDisplay eGLDisplay) throws GlException {
        return createEglContext(EGL14.EGL_NO_CONTEXT, eGLDisplay, 2, EGL_CONFIG_ATTRIBUTES_RGBA_8888);
    }

    public static EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i10, boolean z) throws GlException {
        int[] iArr;
        int[] iArr2;
        if (i10 == 3 || i10 == 10) {
            iArr = EGL_CONFIG_ATTRIBUTES_RGBA_8888;
            iArr2 = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
        } else {
            if (i10 != 7 && i10 != 6) {
                throw new IllegalArgumentException(a0.c.i(i10, "Unsupported color transfer: "));
            }
            iArr = EGL_CONFIG_ATTRIBUTES_RGBA_1010102;
            if (z) {
                iArr2 = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
            } else if (i10 == 6) {
                if (!isBt2020PqExtensionSupported()) {
                    throw new GlException("BT.2020 PQ OpenGL output isn't supported.");
                }
                iArr2 = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ;
            } else {
                if (!isBt2020HlgExtensionSupported()) {
                    throw new GlException("BT.2020 HLG OpenGL output isn't supported.");
                }
                iArr2 = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG;
            }
        }
        EGLSurface eGLSurfaceEglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), obj, iArr2, 0);
        checkEglException("Error creating a new EGL surface");
        return eGLSurfaceEglCreateWindowSurface;
    }

    public static int createExternalTexture() throws GlException {
        int iGenerateTexture = generateTexture();
        bindTexture(36197, iGenerateTexture, C.TEXTURE_MIN_FILTER_LINEAR);
        return iGenerateTexture;
    }

    public static int createFboForTexture(int i10) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i10, 0);
        checkGlError();
        return iArr[0];
    }

    public static EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) throws GlException {
        EGLSurface eGLSurfaceCreatePbufferSurface = isSurfacelessContextExtensionSupported() ? EGL14.EGL_NO_SURFACE : createPbufferSurface(eGLDisplay, 1, 1, EGL_CONFIG_ATTRIBUTES_RGBA_8888);
        focusEglSurface(eGLDisplay, eGLContext, eGLSurfaceCreatePbufferSurface, 1, 1);
        return eGLSurfaceCreatePbufferSurface;
    }

    public static long createGlSyncFence() throws GlException {
        if (getContextMajorVersion() < 3) {
            return 0L;
        }
        long jGlFenceSync = GLES30.glFenceSync(37143, 0);
        checkGlError();
        GLES20.glFlush();
        checkGlError();
        return jGlFenceSync;
    }

    private static EGLSurface createPbufferSurface(EGLDisplay eGLDisplay, int i10, int i11, int[] iArr) throws GlException {
        EGLSurface eGLSurfaceEglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), new int[]{12375, i10, 12374, i11, 12344}, 0);
        checkEglException("Error creating a new EGL Pbuffer surface");
        return eGLSurfaceEglCreatePbufferSurface;
    }

    public static int createPixelBufferObject(int i10) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGenBuffers(1, iArr, 0);
        checkGlError();
        GLES20.glBindBuffer(35051, iArr[0]);
        checkGlError();
        GLES20.glBufferData(35051, i10, null, 35049);
        checkGlError();
        GLES20.glBindBuffer(35051, 0);
        checkGlError();
        return iArr[0];
    }

    public static int createRgb10A2Texture(int i10, int i11) throws GlException {
        return createTextureUninitialized(i10, i11, 32857, 33640);
    }

    public static int createTexture(Bitmap bitmap) throws GlException {
        int iGenerateTexture = generateTexture();
        setTexture(iGenerateTexture, bitmap);
        return iGenerateTexture;
    }

    private static int createTextureUninitialized(int i10, int i11, int i12, int i13) throws GlException {
        assertValidTextureSize(i10, i11);
        int iGenerateTexture = generateTexture();
        bindTexture(3553, iGenerateTexture, C.TEXTURE_MIN_FILTER_LINEAR);
        GLES20.glTexImage2D(3553, 0, i12, i10, i11, 0, 6408, i13, null);
        checkGlError();
        return iGenerateTexture;
    }

    public static float[] createVertexBuffer(List<float[]> list) {
        float[] fArr = new float[list.size() * 4];
        for (int i10 = 0; i10 < list.size(); i10++) {
            System.arraycopy(list.get(i10), 0, fArr, i10 * 4, 4);
        }
        return fArr;
    }

    public static void deleteBuffer(int i10) throws GlException {
        GLES20.glDeleteBuffers(1, new int[]{i10}, 0);
        checkGlError();
    }

    public static void deleteFbo(int i10) throws GlException {
        GLES20.glDeleteFramebuffers(1, new int[]{i10}, 0);
        checkGlError();
    }

    public static void deleteRbo(int i10) throws GlException {
        GLES20.glDeleteRenderbuffers(1, new int[]{i10}, 0);
        checkGlError();
    }

    public static void deleteSyncObject(long j10) throws GlException {
        deleteSyncObjectQuietly(j10);
        checkGlError();
    }

    public static void deleteSyncObjectQuietly(long j10) {
        GLES30.glDeleteSync(j10);
    }

    public static void deleteTexture(int i10) throws GlException {
        GLES20.glDeleteTextures(1, new int[]{i10}, 0);
        checkGlError();
    }

    public static void destroyEglContext(EGLDisplay eGLDisplay, EGLContext eGLContext) throws GlException {
        if (eGLDisplay == null || eGLDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
            return;
        }
        EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
        checkEglException("Error releasing context");
        if (eGLContext == null || eGLContext.equals(EGL14.EGL_NO_CONTEXT)) {
            return;
        }
        EGL14.eglDestroyContext(eGLDisplay, eGLContext);
        checkEglException("Error destroying context");
    }

    public static void destroyEglSurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface) throws GlException {
        if (eGLDisplay == null || eGLDisplay.equals(EGL14.EGL_NO_DISPLAY) || eGLSurface == null || eGLSurface.equals(EGL14.EGL_NO_SURFACE)) {
            return;
        }
        EGL14.eglDestroySurface(eGLDisplay, eGLSurface);
        checkEglException("Error destroying surface");
    }

    public static void focusEglSurface(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i10, int i11) throws GlException {
        focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, 0, i10, i11);
    }

    public static void focusFramebuffer(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i10, int i11, int i12) throws GlException {
        focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, i10, i11, i12);
    }

    public static void focusFramebufferUsingCurrentContext(int i10, int i11, int i12) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        if (iArr[0] != i10) {
            GLES20.glBindFramebuffer(36160, i10);
        }
        checkGlError();
        GLES20.glViewport(0, 0, i11, i12);
        checkGlError();
    }

    private static void focusRenderTarget(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i10, int i11, int i12) throws GlException {
        EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext);
        checkEglException("Error making context current");
        focusFramebufferUsingCurrentContext(i10, i11, i12);
    }

    public static int generateTexture() throws GlException {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError();
        return iArr[0];
    }

    public static long getContextMajorVersion() throws GlException {
        EGL14.eglQueryContext(EGL14.eglGetDisplay(0), EGL14.eglGetCurrentContext(), 12440, new int[1], 0);
        checkGlError();
        return r0[0];
    }

    public static EGLContext getCurrentContext() {
        return EGL14.eglGetCurrentContext();
    }

    public static EGLDisplay getDefaultEglDisplay() throws GlException {
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
        checkGlException(!eGLDisplayEglGetDisplay.equals(EGL14.EGL_NO_DISPLAY), "No EGL display.");
        checkGlException(EGL14.eglInitialize(eGLDisplayEglGetDisplay, new int[1], 0, new int[1], 0), "Error in eglInitialize.");
        checkGlError();
        return eGLDisplayEglGetDisplay;
    }

    private static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) throws GlException {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        throw new GlException("eglChooseConfig failed.");
    }

    public static float[] getNormalizedCoordinateBounds() {
        return new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static float[] getTextureCoordinateBounds() {
        return new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static boolean isBt2020HlgExtensionSupported() throws GlException {
        return isExtensionSupported(EXTENSION_COLORSPACE_BT2020_HLG);
    }

    public static boolean isBt2020PqExtensionSupported() throws GlException {
        return Build.VERSION.SDK_INT >= 33 && isExtensionSupported(EXTENSION_COLORSPACE_BT2020_PQ);
    }

    public static boolean isColorTransferSupported(int i10) throws GlException {
        if (i10 == 6) {
            return isBt2020PqExtensionSupported();
        }
        if (i10 == 7) {
            return isBt2020HlgExtensionSupported();
        }
        return true;
    }

    private static boolean isExtensionSupported(String str) throws GlException {
        String strEglQueryString = EGL14.eglQueryString(getDefaultEglDisplay(), 12373);
        return strEglQueryString != null && strEglQueryString.contains(str);
    }

    public static boolean isProtectedContentExtensionSupported(Context context) throws GlException {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 24) {
            return false;
        }
        if (i10 < 26 && ("samsung".equals(Build.MANUFACTURER) || "XT1650".equals(Build.MODEL))) {
            return false;
        }
        if (i10 >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) {
            return isExtensionSupported(EXTENSION_PROTECTED_CONTENT);
        }
        return false;
    }

    public static boolean isSurfacelessContextExtensionSupported() throws GlException {
        return isExtensionSupported(EXTENSION_SURFACELESS_CONTEXT);
    }

    public static boolean isYuvTargetExtensionSupported() {
        String strGlGetString;
        if (Objects.equals(EGL14.eglGetCurrentContext(), EGL14.EGL_NO_CONTEXT)) {
            try {
                EGLDisplay defaultEglDisplay = getDefaultEglDisplay();
                EGLContext eGLContextCreateEglContext = createEglContext(defaultEglDisplay);
                createFocusedPlaceholderEglSurface(eGLContextCreateEglContext, defaultEglDisplay);
                strGlGetString = GLES20.glGetString(7939);
                destroyEglContext(defaultEglDisplay, eGLContextCreateEglContext);
            } catch (GlException unused) {
                return false;
            }
        } else {
            strGlGetString = GLES20.glGetString(7939);
        }
        return strGlGetString != null && strGlGetString.contains(EXTENSION_YUV_TARGET);
    }

    public static ByteBuffer mapPixelBufferObject(int i10, int i11) throws GlException {
        GLES20.glBindBuffer(35051, i10);
        checkGlError();
        ByteBuffer byteBuffer = (ByteBuffer) GLES30.glMapBufferRange(35051, 0, i11, 1);
        checkGlError();
        GLES20.glBindBuffer(35051, 0);
        checkGlError();
        return byteBuffer;
    }

    public static void schedulePixelBufferRead(int i10, int i11, int i12, int i13) throws GlException {
        focusFramebufferUsingCurrentContext(i10, i11, i12);
        GLES20.glBindBuffer(35051, i13);
        checkGlError();
        GLES30.glReadBuffer(36064);
        GLES30.glReadPixels(0, 0, i11, i12, 6408, 5121, 0);
        checkGlError();
        GLES20.glBindBuffer(35051, 0);
        checkGlError();
    }

    public static void setTexture(int i10, Bitmap bitmap) throws GlException {
        assertValidTextureSize(bitmap.getWidth(), bitmap.getHeight());
        bindTexture(3553, i10, C.TEXTURE_MIN_FILTER_LINEAR);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        checkGlError();
    }

    public static void setToIdentity(float[] fArr) {
        Matrix.setIdentityM(fArr, 0);
    }

    public static void terminate(EGLDisplay eGLDisplay) throws GlException {
        EGL14.eglReleaseThread();
        checkEglException("Error releasing thread");
        EGL14.eglTerminate(eGLDisplay);
        checkEglException("Error terminating display");
    }

    public static void unmapPixelBufferObject(int i10) throws GlException {
        GLES20.glBindBuffer(35051, i10);
        checkGlError();
        GLES30.glUnmapBuffer(35051);
        checkGlError();
        GLES20.glBindBuffer(35051, 0);
        checkGlError();
    }

    private static FloatBuffer createBuffer(int i10) {
        return ByteBuffer.allocateDirect(i10 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static EGLContext createEglContext(EGLContext eGLContext, EGLDisplay eGLDisplay, int i10, int[] iArr) throws GlException {
        boolean z = true;
        ac.b.j(Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_8888) || Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_1010102));
        if (i10 != 2 && i10 != 3) {
            z = false;
        }
        ac.b.j(z);
        EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(eGLDisplay, getEglConfig(eGLDisplay, iArr), eGLContext, new int[]{12440, i10, 12344}, 0);
        if (eGLContextEglCreateContext == null || eGLContextEglCreateContext.equals(EGL14.EGL_NO_CONTEXT)) {
            EGL14.eglTerminate(eGLDisplay);
            throw new GlException(a0.c.i(i10, "eglCreateContext() failed to create a valid context. The device may not support EGL version "));
        }
        checkGlError();
        return eGLContextEglCreateContext;
    }

    public static final class GlException extends Exception {
        public final h1 errorCodes;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public GlException(String str) {
            this(str, o3.f14078o);
            f1 f1Var = h1.f14020l;
        }

        public GlException(String str, List<Integer> list) {
            super(str);
            this.errorCodes = h1.n(list);
        }
    }

    public static int createTexture(int i10, int i11, boolean z) throws GlException {
        if (z) {
            return createTextureUninitialized(i10, i11, 34842, 5131);
        }
        return createTextureUninitialized(i10, i11, 6408, 5121);
    }
}
