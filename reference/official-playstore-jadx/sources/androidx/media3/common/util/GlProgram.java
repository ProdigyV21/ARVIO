package androidx.media3.common.util;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.C;
import androidx.media3.common.util.GlUtil;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class GlProgram {
    private static final int GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT = 35815;
    private final Map<String, Attribute> attributeByName;
    private final Attribute[] attributes;
    private boolean externalTexturesRequireNearestSampling;
    private final int programId;
    private final Map<String, Uniform> uniformByName;
    private final Uniform[] uniforms;

    public static final class Attribute {
        private Buffer buffer;
        private final int location;
        public final String name;
        private int size;

        private Attribute(String str, int i10) {
            this.name = str;
            this.location = i10;
        }

        public static Attribute create(int i10, int i11) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i10, 35722, iArr, 0);
            int i12 = iArr[0];
            byte[] bArr = new byte[i12];
            GLES20.glGetActiveAttrib(i10, i11, i12, new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Attribute(str, GlProgram.getAttributeLocation(i10, str));
        }

        public void bind() throws GlUtil.GlException {
            Buffer buffer = this.buffer;
            ac.b.p(buffer, "call setBuffer before bind");
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, 5126, false, 0, buffer);
            GLES20.glEnableVertexAttribArray(this.location);
            GlUtil.checkGlError();
        }

        public void setBuffer(float[] fArr, int i10) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = i10;
        }
    }

    public static final class Uniform {
        private final int location;
        public final String name;
        private int texIdValue;
        private int texUnitIndex;
        private final int type;
        private final float[] floatValue = new float[16];
        private final int[] intValue = new int[4];
        private int texMinFilter = C.TEXTURE_MIN_FILTER_LINEAR;

        private Uniform(String str, int i10, int i11) {
            this.name = str;
            this.location = i10;
            this.type = i11;
        }

        public static Uniform create(int i10, int i11) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i10, 35719, iArr, 0);
            int[] iArr2 = new int[1];
            int i12 = iArr[0];
            byte[] bArr = new byte[i12];
            GLES20.glGetActiveUniform(i10, i11, i12, new int[1], 0, new int[1], 0, iArr2, 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Uniform(str, GlProgram.getUniformLocation(i10, str), iArr2[0]);
        }

        public void bind(boolean z) throws GlUtil.GlException {
            int i10 = this.type;
            if (i10 == 5124) {
                GLES20.glUniform1iv(this.location, 1, this.intValue, 0);
                GlUtil.checkGlError();
                return;
            }
            if (i10 == 5126) {
                GLES20.glUniform1fv(this.location, 1, this.floatValue, 0);
                GlUtil.checkGlError();
                return;
            }
            if (i10 == 35678 || i10 == GlProgram.GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT || i10 == 36198) {
                if (this.texIdValue == 0) {
                    throw new IllegalStateException("No call to setSamplerTexId() before bind.");
                }
                GLES20.glActiveTexture(this.texUnitIndex + 33984);
                GlUtil.checkGlError();
                int i11 = this.type;
                GlUtil.bindTexture(i11 == 35678 ? 3553 : 36197, this.texIdValue, (i11 == 35678 || !z) ? C.TEXTURE_MIN_FILTER_LINEAR : 9728);
                if (this.type == 35678) {
                    if (this.texMinFilter == 9987) {
                        GLES20.glGenerateMipmap(3553);
                        GlUtil.checkGlError();
                    }
                    GLES20.glTexParameteri(3553, 10241, this.texMinFilter);
                    GlUtil.checkGlError();
                }
                GLES20.glUniform1i(this.location, this.texUnitIndex);
                GlUtil.checkGlError();
                return;
            }
            switch (i10) {
                case 35664:
                    GLES20.glUniform2fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35665:
                    GLES20.glUniform3fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35666:
                    GLES20.glUniform4fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35667:
                    GLES20.glUniform2iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35668:
                    GLES20.glUniform3iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35669:
                    GLES20.glUniform4iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                default:
                    switch (i10) {
                        case 35675:
                            GLES20.glUniformMatrix3fv(this.location, 1, false, this.floatValue, 0);
                            GlUtil.checkGlError();
                            return;
                        case 35676:
                            GLES20.glUniformMatrix4fv(this.location, 1, false, this.floatValue, 0);
                            GlUtil.checkGlError();
                            return;
                        default:
                            throw new IllegalStateException("Unexpected uniform type: " + this.type);
                    }
            }
        }

        public void setFloat(float f10) {
            this.floatValue[0] = f10;
        }

        public void setFloats(float[] fArr) {
            System.arraycopy(fArr, 0, this.floatValue, 0, fArr.length);
        }

        public void setInt(int i10) {
            this.intValue[0] = i10;
        }

        public void setInts(int[] iArr) {
            System.arraycopy(iArr, 0, this.intValue, 0, iArr.length);
        }

        public void setSamplerTexId(int i10, int i11) {
            this.texIdValue = i10;
            this.texUnitIndex = i11;
        }

        public void setTexMinFilter(int i10) {
            this.texMinFilter = i10;
        }
    }

    public GlProgram(Context context, String str, String str2) throws IOException, GlUtil.GlException {
        this(Util.loadAsset(context, str), Util.loadAsset(context, str2));
    }

    private static void addShader(int i10, int i11, String str) throws GlUtil.GlException {
        int iGlCreateShader = GLES20.glCreateShader(i11);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        GlUtil.checkGlException(iArr[0] == 1, GLES20.glGetShaderInfoLog(iGlCreateShader) + ", source: \n" + str);
        GLES20.glAttachShader(i10, iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader);
        GlUtil.checkGlError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getAttributeLocation(int i10, String str) {
        return GLES20.glGetAttribLocation(i10, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCStringLength(byte[] bArr) {
        for (int i10 = 0; i10 < bArr.length; i10++) {
            if (bArr[i10] == 0) {
                return i10;
            }
        }
        return bArr.length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUniformLocation(int i10, String str) {
        return GLES20.glGetUniformLocation(i10, str);
    }

    public void bindAttributesAndUniforms() throws GlUtil.GlException {
        for (Attribute attribute : this.attributes) {
            attribute.bind();
        }
        for (Uniform uniform : this.uniforms) {
            uniform.bind(this.externalTexturesRequireNearestSampling);
        }
    }

    public void delete() throws GlUtil.GlException {
        GLES20.glDeleteProgram(this.programId);
        GlUtil.checkGlError();
    }

    public int getAttributeArrayLocationAndEnable(String str) throws GlUtil.GlException {
        int attributeLocation = getAttributeLocation(str);
        GLES20.glEnableVertexAttribArray(attributeLocation);
        GlUtil.checkGlError();
        return attributeLocation;
    }

    public void setBufferAttribute(String str, float[] fArr, int i10) {
        Attribute attribute = this.attributeByName.get(str);
        attribute.getClass();
        attribute.setBuffer(fArr, i10);
    }

    public void setExternalTexturesRequireNearestSampling(boolean z) {
        this.externalTexturesRequireNearestSampling = z;
    }

    public void setFloatUniform(String str, float f10) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setFloat(f10);
    }

    public void setFloatsUniform(String str, float[] fArr) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setFloats(fArr);
    }

    public void setFloatsUniformIfPresent(String str, float[] fArr) {
        Uniform uniform = this.uniformByName.get(str);
        if (uniform == null) {
            return;
        }
        uniform.setFloats(fArr);
    }

    public void setIntUniform(String str, int i10) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setInt(i10);
    }

    public void setIntsUniform(String str, int[] iArr) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setInts(iArr);
    }

    public void setSamplerTexIdUniform(String str, int i10, int i11) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setSamplerTexId(i10, i11);
    }

    public void use() throws GlUtil.GlException {
        GLES20.glUseProgram(this.programId);
        GlUtil.checkGlError();
    }

    private int getAttributeLocation(String str) {
        return getAttributeLocation(this.programId, str);
    }

    public int getUniformLocation(String str) {
        return getUniformLocation(this.programId, str);
    }

    public GlProgram(String str, String str2) throws GlUtil.GlException {
        int iGlCreateProgram = GLES20.glCreateProgram();
        this.programId = iGlCreateProgram;
        GlUtil.checkGlError();
        addShader(iGlCreateProgram, 35633, str);
        addShader(iGlCreateProgram, 35632, str2);
        GLES20.glLinkProgram(iGlCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        GlUtil.checkGlException(iArr[0] == 1, "Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(iGlCreateProgram));
        GLES20.glUseProgram(iGlCreateProgram);
        this.attributeByName = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35721, iArr2, 0);
        this.attributes = new Attribute[iArr2[0]];
        for (int i10 = 0; i10 < iArr2[0]; i10++) {
            Attribute attributeCreate = Attribute.create(this.programId, i10);
            this.attributes[i10] = attributeCreate;
            this.attributeByName.put(attributeCreate.name, attributeCreate);
        }
        this.uniformByName = new HashMap();
        int[] iArr3 = new int[1];
        GLES20.glGetProgramiv(this.programId, 35718, iArr3, 0);
        this.uniforms = new Uniform[iArr3[0]];
        for (int i11 = 0; i11 < iArr3[0]; i11++) {
            Uniform uniformCreate = Uniform.create(this.programId, i11);
            this.uniforms[i11] = uniformCreate;
            this.uniformByName.put(uniformCreate.name, uniformCreate);
        }
        GlUtil.checkGlError();
    }

    public void setSamplerTexIdUniform(String str, int i10, int i11, int i12) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setSamplerTexId(i10, i11);
        uniform.setTexMinFilter(i12);
    }
}
