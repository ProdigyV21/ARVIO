package androidx.media3.exoplayer.video.spherical;

import android.opengl.GLES20;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.video.spherical.Projection;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes3.dex */
final class ProjectionRenderer {
    private static final String FRAGMENT_SHADER = "// This is required since the texture data is GL_TEXTURE_EXTERNAL_OES.\n#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n// Standard texture rendering shader.\nuniform samplerExternalOES uTexture;\nvarying vec2 vTexCoords;\nvoid main() {\n  gl_FragColor = texture2D(uTexture, vTexCoords);\n}\n";
    private static final String TAG = "ProjectionRenderer";
    private static final String VERTEX_SHADER = "uniform mat4 uMvpMatrix;\nuniform mat3 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec2 aTexCoords;\nvarying vec2 vTexCoords;\n// Standard transformation.\nvoid main() {\n  gl_Position = uMvpMatrix * aPosition;\n  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;\n}\n";
    private MeshData leftMeshData;
    private int mvpMatrixHandle;
    private int positionHandle;
    private GlProgram program;
    private MeshData rightMeshData;
    private int stereoMode;
    private int texCoordsHandle;
    private int textureHandle;
    private int uTexMatrixHandle;
    private static final float[] TEX_MATRIX_WHOLE = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_TOP = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 1.0f};
    private static final float[] TEX_MATRIX_BOTTOM = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_LEFT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_RIGHT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.5f, 1.0f, 1.0f};

    public static class MeshData {
        private final int drawMode;
        private final FloatBuffer textureBuffer;
        private final FloatBuffer vertexBuffer;
        private final int vertexCount;

        public MeshData(Projection.SubMesh subMesh) {
            this.vertexCount = subMesh.getVertexCount();
            this.vertexBuffer = GlUtil.createBuffer(subMesh.vertices);
            this.textureBuffer = GlUtil.createBuffer(subMesh.textureCoords);
            int i10 = subMesh.mode;
            if (i10 == 1) {
                this.drawMode = 5;
            } else if (i10 != 2) {
                this.drawMode = 4;
            } else {
                this.drawMode = 6;
            }
        }
    }

    public static boolean isSupported(Projection projection) {
        Projection.Mesh mesh = projection.leftMesh;
        Projection.Mesh mesh2 = projection.rightMesh;
        return mesh.getSubMeshCount() == 1 && mesh.getSubMesh(0).textureId == 0 && mesh2.getSubMeshCount() == 1 && mesh2.getSubMesh(0).textureId == 0;
    }

    public void draw(int i10, float[] fArr, boolean z) {
        MeshData meshData = z ? this.rightMeshData : this.leftMeshData;
        if (meshData == null) {
            return;
        }
        int i11 = this.stereoMode;
        GLES20.glUniformMatrix3fv(this.uTexMatrixHandle, 1, false, i11 == 1 ? z ? TEX_MATRIX_BOTTOM : TEX_MATRIX_TOP : i11 == 2 ? z ? TEX_MATRIX_RIGHT : TEX_MATRIX_LEFT : TEX_MATRIX_WHOLE, 0);
        GLES20.glUniformMatrix4fv(this.mvpMatrixHandle, 1, false, fArr, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i10);
        GLES20.glUniform1i(this.textureHandle, 0);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e5) {
            Log.e(TAG, "Failed to bind uniforms", e5);
        }
        GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, (Buffer) meshData.vertexBuffer);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e6) {
            Log.e(TAG, "Failed to load position data", e6);
        }
        GLES20.glVertexAttribPointer(this.texCoordsHandle, 2, 5126, false, 8, (Buffer) meshData.textureBuffer);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e10) {
            Log.e(TAG, "Failed to load texture data", e10);
        }
        GLES20.glDrawArrays(meshData.drawMode, 0, meshData.vertexCount);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e11) {
            Log.e(TAG, "Failed to render", e11);
        }
    }

    public void init() {
        try {
            GlProgram glProgram = new GlProgram(VERTEX_SHADER, FRAGMENT_SHADER);
            this.program = glProgram;
            this.mvpMatrixHandle = glProgram.getUniformLocation("uMvpMatrix");
            this.uTexMatrixHandle = this.program.getUniformLocation("uTexMatrix");
            this.positionHandle = this.program.getAttributeArrayLocationAndEnable("aPosition");
            this.texCoordsHandle = this.program.getAttributeArrayLocationAndEnable("aTexCoords");
            this.textureHandle = this.program.getUniformLocation("uTexture");
        } catch (GlUtil.GlException e5) {
            Log.e(TAG, "Failed to initialize the program", e5);
        }
    }

    public void setProjection(Projection projection) {
        if (isSupported(projection)) {
            this.stereoMode = projection.stereoMode;
            MeshData meshData = new MeshData(projection.leftMesh.getSubMesh(0));
            this.leftMeshData = meshData;
            if (!projection.singleMesh) {
                meshData = new MeshData(projection.rightMesh.getSubMesh(0));
            }
            this.rightMeshData = meshData;
        }
    }

    public void shutdown() {
        GlProgram glProgram = this.program;
        if (glProgram != null) {
            try {
                glProgram.delete();
            } catch (GlUtil.GlException e5) {
                Log.e(TAG, "Failed to delete the shader program", e5);
            }
        }
    }
}
