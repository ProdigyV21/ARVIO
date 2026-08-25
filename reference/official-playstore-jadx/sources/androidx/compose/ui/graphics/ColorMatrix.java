package androidx.compose.ui.graphics;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087@\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\f\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\n¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\tH\u0086\n¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0014\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0000ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017JP\u0010!\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t26\u0010\u001e\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000e0\u0019H\u0082\b¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010$\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b#\u0010\u0017J2\u0010)\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u0015\u0010-\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\t¢\u0006\u0004\b+\u0010,J-\u00104\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t¢\u0006\u0004\b2\u00103J\u0015\u00106\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t¢\u0006\u0004\b5\u0010,J\u0015\u00108\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t¢\u0006\u0004\b7\u0010,J\u0015\u0010:\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t¢\u0006\u0004\b9\u0010,J\r\u0010<\u001a\u00020\u000e¢\u0006\u0004\b;\u0010\u0013J\r\u0010>\u001a\u00020\u000e¢\u0006\u0004\b=\u0010\u0013J\u0010\u0010B\u001a\u00020?HÖ\u0001¢\u0006\u0004\b@\u0010AJ\u0010\u0010E\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\bC\u0010DJ\u001a\u0010J\u001a\u00020G2\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bH\u0010IR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010K\u001a\u0004\bL\u0010M\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006N"}, d2 = {"Landroidx/compose/ui/graphics/ColorMatrix;", "", "", "values", "constructor-impl", "([F)[F", "", "row", "column", "", "get-impl", "([FII)F", "get", "v", "Lx6/t0;", "set-impl", "([FIIF)V", "set", "reset-impl", "([F)V", "reset", "src", "set-jHG-Opc", "([F[F)V", "degrees", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "cosine", "sine", "block", "rotateInternal-impl", "([FFLr7/p;)V", "rotateInternal", "colorMatrix", "timesAssign-jHG-Opc", "timesAssign", "m1", "m2", "dot-Me4OoYI", "([F[FI[FI)F", TtmlNode.TEXT_EMPHASIS_MARK_DOT, "sat", "setToSaturation-impl", "([FF)V", "setToSaturation", "redScale", "greenScale", "blueScale", "alphaScale", "setToScale-impl", "([FFFFF)V", "setToScale", "setToRotateRed-impl", "setToRotateRed", "setToRotateGreen-impl", "setToRotateGreen", "setToRotateBlue-impl", "setToRotateBlue", "convertRgbToYuv-impl", "convertRgbToYuv", "convertYuvToRgb-impl", "convertYuvToRgb", "", "toString-impl", "([F)Ljava/lang/String;", "toString", "hashCode-impl", "([F)I", "hashCode", "other", "", "equals-impl", "([FLjava/lang/Object;)Z", "equals", "[F", "getValues", "()[F", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class ColorMatrix {
    private final float[] values;

    private /* synthetic */ ColorMatrix(float[] fArr) {
        this.values = fArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ColorMatrix m3527boximpl(float[] fArr) {
        return new ColorMatrix(fArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static float[] m3528constructorimpl(float[] fArr) {
        return fArr;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ float[] m3529constructorimpl$default(float[] fArr, int i10, kotlin.jvm.internal.h hVar) {
        if ((i10 & 1) != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        }
        return m3528constructorimpl(fArr);
    }

    /* JADX INFO: renamed from: convertRgbToYuv-impl, reason: not valid java name */
    public static final void m3530convertRgbToYuvimpl(float[] fArr) {
        m3537resetimpl(fArr);
        fArr[0] = 0.299f;
        fArr[1] = 0.587f;
        fArr[2] = 0.114f;
        fArr[5] = -0.16874f;
        fArr[6] = -0.33126f;
        fArr[7] = 0.5f;
        fArr[10] = 0.5f;
        fArr[11] = -0.41869f;
        fArr[12] = -0.08131f;
    }

    /* JADX INFO: renamed from: convertYuvToRgb-impl, reason: not valid java name */
    public static final void m3531convertYuvToRgbimpl(float[] fArr) {
        m3537resetimpl(fArr);
        fArr[2] = 1.402f;
        fArr[5] = 1.0f;
        fArr[6] = -0.34414f;
        fArr[7] = -0.71414f;
        fArr[10] = 1.0f;
        fArr[11] = 1.772f;
        fArr[12] = 0.0f;
    }

    /* JADX INFO: renamed from: dot-Me4OoYI, reason: not valid java name */
    private static final float m3532dotMe4OoYI(float[] fArr, float[] fArr2, int i10, float[] fArr3, int i11) {
        int i12 = i10 * 5;
        return (fArr2[i12 + 3] * fArr3[15 + i11]) + (fArr2[i12 + 2] * fArr3[10 + i11]) + (fArr2[i12 + 1] * fArr3[5 + i11]) + (fArr2[i12] * fArr3[i11]);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3533equalsimpl(float[] fArr, Object obj) {
        return (obj instanceof ColorMatrix) && p.a(fArr, ((ColorMatrix) obj).m3548unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3534equalsimpl0(float[] fArr, float[] fArr2) {
        return p.a(fArr, fArr2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final float m3535getimpl(float[] fArr, int i10, int i11) {
        return fArr[(i10 * 5) + i11];
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3536hashCodeimpl(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    /* JADX INFO: renamed from: reset-impl, reason: not valid java name */
    public static final void m3537resetimpl(float[] fArr) {
        Arrays.fill(fArr, 0, fArr.length, 0.0f);
        fArr[0] = 1.0f;
        fArr[12] = 1.0f;
        fArr[6] = 1.0f;
        fArr[18] = 1.0f;
    }

    /* JADX INFO: renamed from: rotateInternal-impl, reason: not valid java name */
    private static final void m3538rotateInternalimpl(float[] fArr, float f10, r7.p<? super Float, ? super Float, t0> pVar) {
        m3537resetimpl(fArr);
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        pVar.invoke(Float.valueOf((float) Math.cos(d4)), Float.valueOf((float) Math.sin(d4)));
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m3539setimpl(float[] fArr, int i10, int i11, float f10) {
        fArr[(i10 * 5) + i11] = f10;
    }

    /* JADX INFO: renamed from: set-jHG-Opc, reason: not valid java name */
    public static final void m3540setjHGOpc(float[] fArr, float[] fArr2) {
        r.Q(fArr2, fArr, 14);
    }

    /* JADX INFO: renamed from: setToRotateBlue-impl, reason: not valid java name */
    public static final void m3541setToRotateBlueimpl(float[] fArr, float f10) {
        m3537resetimpl(fArr);
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        fArr[6] = fCos;
        fArr[0] = fCos;
        fArr[1] = fSin;
        fArr[5] = -fSin;
    }

    /* JADX INFO: renamed from: setToRotateGreen-impl, reason: not valid java name */
    public static final void m3542setToRotateGreenimpl(float[] fArr, float f10) {
        m3537resetimpl(fArr);
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        fArr[12] = fCos;
        fArr[0] = fCos;
        fArr[2] = -fSin;
        fArr[10] = fSin;
    }

    /* JADX INFO: renamed from: setToRotateRed-impl, reason: not valid java name */
    public static final void m3543setToRotateRedimpl(float[] fArr, float f10) {
        m3537resetimpl(fArr);
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        fArr[12] = fCos;
        fArr[6] = fCos;
        fArr[7] = fSin;
        fArr[11] = -fSin;
    }

    /* JADX INFO: renamed from: setToSaturation-impl, reason: not valid java name */
    public static final void m3544setToSaturationimpl(float[] fArr, float f10) {
        m3537resetimpl(fArr);
        float f11 = 1 - f10;
        float f12 = 0.213f * f11;
        float f13 = 0.715f * f11;
        float f14 = f11 * 0.072f;
        fArr[0] = f12 + f10;
        fArr[1] = f13;
        fArr[2] = f14;
        fArr[5] = f12;
        fArr[6] = f13 + f10;
        fArr[7] = f14;
        fArr[10] = f12;
        fArr[11] = f13;
        fArr[12] = f14 + f10;
    }

    /* JADX INFO: renamed from: setToScale-impl, reason: not valid java name */
    public static final void m3545setToScaleimpl(float[] fArr, float f10, float f11, float f12, float f13) {
        m3537resetimpl(fArr);
        fArr[0] = f10;
        fArr[6] = f11;
        fArr[12] = f12;
        fArr[18] = f13;
    }

    /* JADX INFO: renamed from: timesAssign-jHG-Opc, reason: not valid java name */
    public static final void m3546timesAssignjHGOpc(float[] fArr, float[] fArr2) {
        float fM3532dotMe4OoYI = m3532dotMe4OoYI(fArr, fArr, 0, fArr2, 0);
        float fM3532dotMe4OoYI2 = m3532dotMe4OoYI(fArr, fArr, 0, fArr2, 1);
        float fM3532dotMe4OoYI3 = m3532dotMe4OoYI(fArr, fArr, 0, fArr2, 2);
        float fM3532dotMe4OoYI4 = m3532dotMe4OoYI(fArr, fArr, 0, fArr2, 3);
        float f10 = (fArr[3] * fArr2[19]) + (fArr[2] * fArr2[14]) + (fArr[1] * fArr2[9]) + (fArr[0] * fArr2[4]) + fArr[4];
        float fM3532dotMe4OoYI5 = m3532dotMe4OoYI(fArr, fArr, 1, fArr2, 0);
        float fM3532dotMe4OoYI6 = m3532dotMe4OoYI(fArr, fArr, 1, fArr2, 1);
        float fM3532dotMe4OoYI7 = m3532dotMe4OoYI(fArr, fArr, 1, fArr2, 2);
        float fM3532dotMe4OoYI8 = m3532dotMe4OoYI(fArr, fArr, 1, fArr2, 3);
        float f11 = (fArr[8] * fArr2[19]) + (fArr[7] * fArr2[14]) + (fArr[6] * fArr2[9]) + (fArr[5] * fArr2[4]) + fArr[9];
        float fM3532dotMe4OoYI9 = m3532dotMe4OoYI(fArr, fArr, 2, fArr2, 0);
        float fM3532dotMe4OoYI10 = m3532dotMe4OoYI(fArr, fArr, 2, fArr2, 1);
        float fM3532dotMe4OoYI11 = m3532dotMe4OoYI(fArr, fArr, 2, fArr2, 2);
        float fM3532dotMe4OoYI12 = m3532dotMe4OoYI(fArr, fArr, 2, fArr2, 3);
        float f12 = (fArr[13] * fArr2[19]) + (fArr[12] * fArr2[14]) + (fArr[11] * fArr2[9]) + (fArr[10] * fArr2[4]) + fArr[14];
        float fM3532dotMe4OoYI13 = m3532dotMe4OoYI(fArr, fArr, 3, fArr2, 0);
        float fM3532dotMe4OoYI14 = m3532dotMe4OoYI(fArr, fArr, 3, fArr2, 1);
        float fM3532dotMe4OoYI15 = m3532dotMe4OoYI(fArr, fArr, 3, fArr2, 2);
        float fM3532dotMe4OoYI16 = m3532dotMe4OoYI(fArr, fArr, 3, fArr2, 3);
        float f13 = (fArr[18] * fArr2[19]) + (fArr[17] * fArr2[14]) + (fArr[16] * fArr2[9]) + (fArr[15] * fArr2[4]) + fArr[19];
        fArr[0] = fM3532dotMe4OoYI;
        fArr[1] = fM3532dotMe4OoYI2;
        fArr[2] = fM3532dotMe4OoYI3;
        fArr[3] = fM3532dotMe4OoYI4;
        fArr[4] = f10;
        fArr[5] = fM3532dotMe4OoYI5;
        fArr[6] = fM3532dotMe4OoYI6;
        fArr[7] = fM3532dotMe4OoYI7;
        fArr[8] = fM3532dotMe4OoYI8;
        fArr[9] = f11;
        fArr[10] = fM3532dotMe4OoYI9;
        fArr[11] = fM3532dotMe4OoYI10;
        fArr[12] = fM3532dotMe4OoYI11;
        fArr[13] = fM3532dotMe4OoYI12;
        fArr[14] = f12;
        fArr[15] = fM3532dotMe4OoYI13;
        fArr[16] = fM3532dotMe4OoYI14;
        fArr[17] = fM3532dotMe4OoYI15;
        fArr[18] = fM3532dotMe4OoYI16;
        fArr[19] = f13;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3547toStringimpl(float[] fArr) {
        return "ColorMatrix(values=" + Arrays.toString(fArr) + ')';
    }

    public boolean equals(Object obj) {
        return m3533equalsimpl(this.values, obj);
    }

    public final float[] getValues() {
        return this.values;
    }

    public int hashCode() {
        return m3536hashCodeimpl(this.values);
    }

    public String toString() {
        return m3547toStringimpl(this.values);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ float[] m3548unboximpl() {
        return this.values;
    }
}
