package com.discord.org.webrtc;

import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLException;
import com.discord.org.webrtc.GlGenericDrawer;
import com.discord.org.webrtc.ThreadUtils;
import com.discord.org.webrtc.VideoFrame;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public final class YuvConverter {
    private static final String FRAGMENT_SHADER = "uniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 1.5 * xUnit).rgb);\n}\n";
    private static final String TAG = "YuvConverter";
    private final GlGenericDrawer drawer;
    private final GlTextureFrameBuffer i420TextureFrameBuffer;
    private final ShaderCallbacks shaderCallbacks;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final VideoFrameDrawer videoFrameDrawer;

    public static class ShaderCallbacks implements GlGenericDrawer.ShaderCallbacks {
        private float[] coeffs;
        private int coeffsLoc;
        private float stepSize;
        private int xUnitLoc;
        private static final float[] yCoeffs = {0.256788f, 0.504129f, 0.0979059f, 0.0627451f};
        private static final float[] uCoeffs = {-0.148223f, -0.290993f, 0.439216f, 0.501961f};
        private static final float[] vCoeffs = {0.439216f, -0.367788f, -0.0714274f, 0.501961f};

        public /* synthetic */ ShaderCallbacks(int i10) {
            this();
        }

        @Override // com.discord.org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onNewShader(GlShader glShader) {
            this.xUnitLoc = glShader.getUniformLocation("xUnit");
            this.coeffsLoc = glShader.getUniformLocation("coeffs");
        }

        @Override // com.discord.org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onPrepareShader(GlShader glShader, float[] fArr, int i10, int i11, int i12, int i13) {
            GLES20.glUniform4fv(this.coeffsLoc, 1, this.coeffs, 0);
            int i14 = this.xUnitLoc;
            float f10 = this.stepSize;
            float f11 = i10;
            GLES20.glUniform2f(i14, (fArr[0] * f10) / f11, (f10 * fArr[1]) / f11);
        }

        public void setPlaneU() {
            this.coeffs = uCoeffs;
            this.stepSize = 2.0f;
        }

        public void setPlaneV() {
            this.coeffs = vCoeffs;
            this.stepSize = 2.0f;
        }

        public void setPlaneY() {
            this.coeffs = yCoeffs;
            this.stepSize = 1.0f;
        }

        private ShaderCallbacks() {
        }
    }

    public YuvConverter() {
        this(new VideoFrameDrawer());
    }

    private VideoFrame.I420Buffer convertInternal(VideoFrame.TextureBuffer textureBuffer) {
        VideoFrame.TextureBuffer textureBuffer2 = (VideoFrame.TextureBuffer) this.videoFrameDrawer.prepareBufferForViewportSize(textureBuffer, textureBuffer.getWidth(), textureBuffer.getHeight());
        int width = textureBuffer2.getWidth();
        int height = textureBuffer2.getHeight();
        int i10 = ((width + 7) / 8) * 8;
        int i11 = (height + 1) / 2;
        int i12 = height + i11;
        ByteBuffer byteBufferNativeAllocateByteBuffer = JniCommon.nativeAllocateByteBuffer(i10 * i12);
        int i13 = i10 / 4;
        Matrix matrix = new Matrix();
        matrix.preTranslate(0.5f, 0.5f);
        matrix.preScale(1.0f, -1.0f);
        matrix.preTranslate(-0.5f, -0.5f);
        this.i420TextureFrameBuffer.setSize(i13, i12);
        GLES20.glBindFramebuffer(36160, this.i420TextureFrameBuffer.getFrameBufferId());
        GlUtil.checkNoGLES2Error("glBindFramebuffer");
        this.shaderCallbacks.setPlaneY();
        VideoFrameDrawer.drawTexture(this.drawer, textureBuffer2, matrix, width, height, 0, 0, i13, height);
        this.shaderCallbacks.setPlaneU();
        int i14 = i13 / 2;
        VideoFrameDrawer.drawTexture(this.drawer, textureBuffer2, matrix, width, height, 0, height, i14, i11);
        this.shaderCallbacks.setPlaneV();
        VideoFrameDrawer.drawTexture(this.drawer, textureBuffer2, matrix, width, height, i14, height, i14, i11);
        GLES20.glReadPixels(0, 0, this.i420TextureFrameBuffer.getWidth(), this.i420TextureFrameBuffer.getHeight(), 6408, 5121, byteBufferNativeAllocateByteBuffer);
        GlUtil.checkNoGLES2Error("YuvConverter.convert");
        GLES20.glBindFramebuffer(36160, 0);
        int i15 = i10 * height;
        int i16 = i10 / 2;
        int i17 = i15 + i16;
        ByteBuffer byteBufferSlice = byteBufferNativeAllocateByteBuffer.slice();
        int i18 = ((i11 - 1) * i10) + i16;
        ByteBuffer byteBufferSlice2 = byteBufferNativeAllocateByteBuffer.slice();
        ByteBuffer byteBufferSlice3 = byteBufferNativeAllocateByteBuffer.slice();
        textureBuffer2.release();
        return JavaI420Buffer.wrap(width, height, byteBufferSlice, i10, byteBufferSlice2, i10, byteBufferSlice3, i10, new p(byteBufferNativeAllocateByteBuffer, 1));
    }

    public VideoFrame.I420Buffer convert(VideoFrame.TextureBuffer textureBuffer) {
        try {
            return convertInternal(textureBuffer);
        } catch (GLException e5) {
            Logging.w(TAG, "Failed to convert TextureBuffer", e5);
            return null;
        }
    }

    public void release() {
        this.threadChecker.checkIsOnValidThread();
        this.drawer.release();
        this.i420TextureFrameBuffer.release();
        this.videoFrameDrawer.release();
        this.threadChecker.detachThread();
    }

    public YuvConverter(VideoFrameDrawer videoFrameDrawer) {
        ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();
        this.threadChecker = threadChecker;
        this.i420TextureFrameBuffer = new GlTextureFrameBuffer(6408);
        ShaderCallbacks shaderCallbacks = new ShaderCallbacks(0);
        this.shaderCallbacks = shaderCallbacks;
        this.drawer = new GlGenericDrawer(FRAGMENT_SHADER, shaderCallbacks);
        this.videoFrameDrawer = videoFrameDrawer;
        threadChecker.detachThread();
    }
}
