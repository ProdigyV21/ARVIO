package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0000ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\t"}, d2 = {"Landroidx/compose/ui/graphics/Matrix;", "Landroid/graphics/Matrix;", "matrix", "Lx6/t0;", "setFrom-tU-YjHk", "([FLandroid/graphics/Matrix;)V", "setFrom", "setFrom-EL8BTi8", "(Landroid/graphics/Matrix;[F)V", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidMatrixConversions_androidKt {
    /* JADX INFO: renamed from: setFrom-EL8BTi8, reason: not valid java name */
    public static final void m3348setFromEL8BTi8(android.graphics.Matrix matrix, float[] fArr) {
        float f10 = fArr[2];
        if (f10 == 0.0f) {
            float f11 = fArr[6];
            if (f11 == 0.0f && fArr[10] == 1.0f && fArr[14] == 0.0f) {
                float f12 = fArr[8];
                if (f12 == 0.0f && fArr[9] == 0.0f && fArr[11] == 0.0f) {
                    float f13 = fArr[0];
                    float f14 = fArr[1];
                    float f15 = fArr[3];
                    float f16 = fArr[4];
                    float f17 = fArr[5];
                    float f18 = fArr[7];
                    float f19 = fArr[12];
                    float f20 = fArr[13];
                    float f21 = fArr[15];
                    fArr[0] = f13;
                    fArr[1] = f16;
                    fArr[2] = f19;
                    fArr[3] = f14;
                    fArr[4] = f17;
                    fArr[5] = f20;
                    fArr[6] = f15;
                    fArr[7] = f18;
                    fArr[8] = f21;
                    matrix.setValues(fArr);
                    fArr[0] = f13;
                    fArr[1] = f14;
                    fArr[2] = f10;
                    fArr[3] = f15;
                    fArr[4] = f16;
                    fArr[5] = f17;
                    fArr[6] = f11;
                    fArr[7] = f18;
                    fArr[8] = f12;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Android does not support arbitrary transforms");
    }

    /* JADX INFO: renamed from: setFrom-tU-YjHk, reason: not valid java name */
    public static final void m3349setFromtUYjHk(float[] fArr, android.graphics.Matrix matrix) {
        matrix.getValues(fArr);
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = fArr[6];
        float f17 = fArr[7];
        float f18 = fArr[8];
        fArr[0] = f10;
        fArr[1] = f13;
        fArr[2] = 0.0f;
        fArr[3] = f16;
        fArr[4] = f11;
        fArr[5] = f14;
        fArr[6] = 0.0f;
        fArr[7] = f17;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = f12;
        fArr[13] = f15;
        fArr[14] = 0.0f;
        fArr[15] = f18;
    }
}
