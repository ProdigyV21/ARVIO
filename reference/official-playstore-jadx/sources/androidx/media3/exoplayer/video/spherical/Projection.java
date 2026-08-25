package androidx.media3.exoplayer.video.spherical;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public SubMesh getSubMesh(int i10) {
            return this.subMeshes[i10];
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i10, float[] fArr, float[] fArr2, int i11) {
            this.textureId = i10;
            ac.b.j(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i11;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public Projection(Mesh mesh, int i10) {
        this(mesh, mesh, i10);
    }

    public static Projection createEquirectangular(int i10) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i10);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i10) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i10;
        this.singleMesh = mesh == mesh2;
    }

    public static Projection createEquirectangular(float f10, int i10, int i11, float f11, float f12, int i12) {
        int i13;
        int i14 = i10;
        ac.b.j(f10 > 0.0f);
        ac.b.j(i14 >= 1);
        ac.b.j(i11 >= 1);
        ac.b.j(f11 > 0.0f && f11 <= 180.0f);
        ac.b.j(f12 > 0.0f && f12 <= 360.0f);
        float radians = (float) Math.toRadians(f11);
        float radians2 = (float) Math.toRadians(f12);
        float f13 = radians / i14;
        float f14 = radians2 / i11;
        int i15 = i11 + 1;
        int i16 = ((i15 * 2) + 2) * i14;
        float[] fArr = new float[i16 * 3];
        float[] fArr2 = new float[i16 * 2];
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i17 < i14) {
            float f15 = radians / 2.0f;
            float f16 = (i17 * f13) - f15;
            int i20 = i17 + 1;
            float f17 = (i20 * f13) - f15;
            int i21 = 0;
            while (i21 < i15) {
                float f18 = radians;
                float f19 = radians2;
                int i22 = 0;
                int i23 = 2;
                while (i22 < i23) {
                    float f20 = f13;
                    float f21 = i21 * f14;
                    float f22 = f14;
                    float f23 = f16;
                    double d4 = f10;
                    double d10 = (f21 + 3.1415927f) - (f19 / 2.0f);
                    double d11 = i22 == 0 ? f16 : f17;
                    fArr[i18] = -((float) (Math.cos(d11) * Math.sin(d10) * d4));
                    fArr[i18 + 1] = (float) (Math.sin(d11) * d4);
                    int i24 = i18 + 3;
                    fArr[i18 + 2] = (float) (Math.cos(d11) * Math.cos(d10) * d4);
                    fArr2[i19] = f21 / f19;
                    int i25 = i19 + 2;
                    fArr2[i19 + 1] = ((i17 + i22) * f20) / f18;
                    if ((i21 == 0 && i22 == 0) || (i21 == i11 && i22 == 1)) {
                        System.arraycopy(fArr, i18, fArr, i24, 3);
                        i18 += 6;
                        i13 = 2;
                        System.arraycopy(fArr2, i19, fArr2, i25, 2);
                        i19 += 4;
                    } else {
                        i13 = 2;
                        i18 = i24;
                        i19 = i25;
                    }
                    i22++;
                    i23 = i13;
                    f13 = f20;
                    f14 = f22;
                    f16 = f23;
                }
                i21++;
                radians2 = f19;
                radians = f18;
                f13 = f13;
            }
            i14 = i10;
            i17 = i20;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr, fArr2, 1)), i12);
    }
}
