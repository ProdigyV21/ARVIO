package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 H2\u00020\u0001:\u0001HB\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\f\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\n¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\tH\u0086\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u001b¢\u0006\u0004\b\u0019\u0010\u001cJ\u001b\u0010 \u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010$\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\r\u0010'\u001a\u00020\u000e¢\u0006\u0004\b%\u0010&J\r\u0010)\u001a\u00020\u000e¢\u0006\u0004\b(\u0010&J\u0018\u0010,\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u0000ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001fJ\u0015\u00100\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\t¢\u0006\u0004\b.\u0010/J\u0015\u00102\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\t¢\u0006\u0004\b1\u0010/J\u0015\u00104\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\t¢\u0006\u0004\b3\u0010/J+\u0010:\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u00020\t2\b\b\u0002\u00106\u001a\u00020\t2\b\b\u0002\u00107\u001a\u00020\t¢\u0006\u0004\b8\u00109J+\u0010<\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u00020\t2\b\b\u0002\u00106\u001a\u00020\t2\b\b\u0002\u00107\u001a\u00020\t¢\u0006\u0004\b;\u00109J\u0010\u0010?\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b=\u0010>J\u001a\u0010D\u001a\u00020A2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bB\u0010CR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010E\u001a\u0004\bF\u0010G\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006I"}, d2 = {"Landroidx/compose/ui/graphics/Matrix;", "", "", "values", "constructor-impl", "([F)[F", "", "row", "column", "", "get-impl", "([FII)F", "get", "v", "Lx6/t0;", "set-impl", "([FIIF)V", "set", "Landroidx/compose/ui/geometry/Offset;", "point", "map-MK-Hz9U", "([FJ)J", "map", "Landroidx/compose/ui/geometry/Rect;", "rect", "map-impl", "([FLandroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/geometry/MutableRect;", "([FLandroidx/compose/ui/geometry/MutableRect;)V", CmcdData.OBJECT_TYPE_MANIFEST, "timesAssign-58bKbWc", "([F[F)V", "timesAssign", "", "toString-impl", "([F)Ljava/lang/String;", "toString", "invert-impl", "([F)V", "invert", "reset-impl", "reset", "matrix", "setFrom-58bKbWc", "setFrom", "degrees", "rotateX-impl", "([FF)V", "rotateX", "rotateY-impl", "rotateY", "rotateZ-impl", "rotateZ", "x", "y", "z", "scale-impl", "([FFFF)V", "scale", "translate-impl", "translate", "hashCode-impl", "([F)I", "hashCode", "other", "", "equals-impl", "([FLjava/lang/Object;)Z", "equals", "[F", "getValues", "()[F", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class Matrix {
    public static final int Perspective0 = 3;
    public static final int Perspective1 = 7;
    public static final int Perspective2 = 15;
    public static final int ScaleX = 0;
    public static final int ScaleY = 5;
    public static final int ScaleZ = 10;
    public static final int SkewX = 4;
    public static final int SkewY = 1;
    public static final int TranslateX = 12;
    public static final int TranslateY = 13;
    public static final int TranslateZ = 14;
    private final float[] values;

    private /* synthetic */ Matrix(float[] fArr) {
        this.values = fArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Matrix m3682boximpl(float[] fArr) {
        return new Matrix(fArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static float[] m3683constructorimpl(float[] fArr) {
        return fArr;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ float[] m3684constructorimpl$default(float[] fArr, int i10, kotlin.jvm.internal.h hVar) {
        if ((i10 & 1) != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        return m3683constructorimpl(fArr);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3685equalsimpl(float[] fArr, Object obj) {
        return (obj instanceof Matrix) && p.a(fArr, ((Matrix) obj).m3705unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3686equalsimpl0(float[] fArr, float[] fArr2) {
        return p.a(fArr, fArr2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final float m3687getimpl(float[] fArr, int i10, int i11) {
        return fArr[(i10 * 4) + i11];
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3688hashCodeimpl(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    /* JADX INFO: renamed from: invert-impl, reason: not valid java name */
    public static final void m3689invertimpl(float[] fArr) {
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = fArr[6];
        float f17 = fArr[7];
        float f18 = fArr[8];
        float f19 = fArr[9];
        float f20 = fArr[10];
        float f21 = fArr[11];
        float f22 = fArr[12];
        float f23 = fArr[13];
        float f24 = fArr[14];
        float f25 = fArr[15];
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f10 * f17) - (f13 * f14);
        float f29 = (f11 * f16) - (f12 * f15);
        float f30 = (f11 * f17) - (f13 * f15);
        float f31 = (f12 * f17) - (f13 * f16);
        float f32 = (f18 * f23) - (f19 * f22);
        float f33 = (f18 * f24) - (f20 * f22);
        float f34 = (f18 * f25) - (f21 * f22);
        float f35 = (f19 * f24) - (f20 * f23);
        float f36 = (f19 * f25) - (f21 * f23);
        float f37 = (f20 * f25) - (f21 * f24);
        float f38 = (f31 * f32) + (((f29 * f34) + ((f28 * f35) + ((f26 * f37) - (f27 * f36)))) - (f30 * f33));
        if (f38 == 0.0f) {
            return;
        }
        float f39 = 1.0f / f38;
        fArr[0] = a0.c.a(f17, f35, (f15 * f37) - (f16 * f36), f39);
        fArr[1] = (((f12 * f36) + ((-f11) * f37)) - (f13 * f35)) * f39;
        fArr[2] = a0.c.a(f25, f29, (f23 * f31) - (f24 * f30), f39);
        fArr[3] = (((f20 * f30) + ((-f19) * f31)) - (f21 * f29)) * f39;
        float f40 = -f14;
        fArr[4] = (((f16 * f34) + (f40 * f37)) - (f17 * f33)) * f39;
        fArr[5] = a0.c.a(f13, f33, (f37 * f10) - (f12 * f34), f39);
        float f41 = -f22;
        fArr[6] = (((f24 * f28) + (f41 * f31)) - (f25 * f27)) * f39;
        fArr[7] = a0.c.a(f21, f27, (f18 * f31) - (f20 * f28), f39);
        fArr[8] = a0.c.a(f17, f32, (f14 * f36) - (f15 * f34), f39);
        fArr[9] = (((f34 * f11) + ((-f10) * f36)) - (f13 * f32)) * f39;
        fArr[10] = a0.c.a(f25, f26, (f22 * f30) - (f23 * f28), f39);
        fArr[11] = (((f19 * f28) + ((-f18) * f30)) - (f21 * f26)) * f39;
        fArr[12] = (((f15 * f33) + (f40 * f35)) - (f16 * f32)) * f39;
        fArr[13] = a0.c.a(f12, f32, (f10 * f35) - (f11 * f33), f39);
        fArr[14] = (((f23 * f27) + (f41 * f29)) - (f24 * f26)) * f39;
        fArr[15] = a0.c.a(f20, f26, (f18 * f29) - (f19 * f27), f39);
    }

    /* JADX INFO: renamed from: map-MK-Hz9U, reason: not valid java name */
    public static final long m3690mapMKHz9U(float[] fArr, long j10) {
        float fM3236getXimpl = Offset.m3236getXimpl(j10);
        float fM3237getYimpl = Offset.m3237getYimpl(j10);
        float f10 = 1 / (((fArr[7] * fM3237getYimpl) + (fArr[3] * fM3236getXimpl)) + fArr[15]);
        if (Float.isInfinite(f10) || Float.isNaN(f10)) {
            f10 = 0.0f;
        }
        return OffsetKt.Offset(((fArr[4] * fM3237getYimpl) + (fArr[0] * fM3236getXimpl) + fArr[12]) * f10, ((fArr[5] * fM3237getYimpl) + (fArr[1] * fM3236getXimpl) + fArr[13]) * f10);
    }

    /* JADX INFO: renamed from: map-impl, reason: not valid java name */
    public static final Rect m3691mapimpl(float[] fArr, Rect rect) {
        long jM3690mapMKHz9U = m3690mapMKHz9U(fArr, OffsetKt.Offset(rect.getLeft(), rect.getTop()));
        long jM3690mapMKHz9U2 = m3690mapMKHz9U(fArr, OffsetKt.Offset(rect.getLeft(), rect.getBottom()));
        long jM3690mapMKHz9U3 = m3690mapMKHz9U(fArr, OffsetKt.Offset(rect.getRight(), rect.getTop()));
        long jM3690mapMKHz9U4 = m3690mapMKHz9U(fArr, OffsetKt.Offset(rect.getRight(), rect.getBottom()));
        return new Rect(Math.min(Math.min(Offset.m3236getXimpl(jM3690mapMKHz9U), Offset.m3236getXimpl(jM3690mapMKHz9U2)), Math.min(Offset.m3236getXimpl(jM3690mapMKHz9U3), Offset.m3236getXimpl(jM3690mapMKHz9U4))), Math.min(Math.min(Offset.m3237getYimpl(jM3690mapMKHz9U), Offset.m3237getYimpl(jM3690mapMKHz9U2)), Math.min(Offset.m3237getYimpl(jM3690mapMKHz9U3), Offset.m3237getYimpl(jM3690mapMKHz9U4))), Math.max(Math.max(Offset.m3236getXimpl(jM3690mapMKHz9U), Offset.m3236getXimpl(jM3690mapMKHz9U2)), Math.max(Offset.m3236getXimpl(jM3690mapMKHz9U3), Offset.m3236getXimpl(jM3690mapMKHz9U4))), Math.max(Math.max(Offset.m3237getYimpl(jM3690mapMKHz9U), Offset.m3237getYimpl(jM3690mapMKHz9U2)), Math.max(Offset.m3237getYimpl(jM3690mapMKHz9U3), Offset.m3237getYimpl(jM3690mapMKHz9U4))));
    }

    /* JADX INFO: renamed from: reset-impl, reason: not valid java name */
    public static final void m3693resetimpl(float[] fArr) {
        int i10 = 0;
        while (i10 < 4) {
            int i11 = 0;
            while (i11 < 4) {
                fArr[(i11 * 4) + i10] = i10 == i11 ? 1.0f : 0.0f;
                i11++;
            }
            i10++;
        }
    }

    /* JADX INFO: renamed from: rotateX-impl, reason: not valid java name */
    public static final void m3694rotateXimpl(float[] fArr, float f10) {
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = (f11 * fCos) - (f12 * fSin);
        float f14 = fArr[5];
        float f15 = fArr[6];
        float f16 = (f14 * fCos) - (f15 * fSin);
        float f17 = fArr[9];
        float f18 = fArr[10];
        float f19 = (f17 * fCos) - (f18 * fSin);
        float f20 = fArr[13];
        float f21 = fArr[14];
        float f22 = (f20 * fCos) - (f21 * fSin);
        fArr[1] = f13;
        fArr[2] = (f12 * fCos) + (f11 * fSin);
        fArr[5] = f16;
        fArr[6] = (f15 * fCos) + (f14 * fSin);
        fArr[9] = f19;
        fArr[10] = (f18 * fCos) + (f17 * fSin);
        fArr[13] = f22;
        fArr[14] = (f21 * fCos) + (f20 * fSin);
    }

    /* JADX INFO: renamed from: rotateY-impl, reason: not valid java name */
    public static final void m3695rotateYimpl(float[] fArr, float f10) {
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        float f11 = fArr[0];
        float f12 = fArr[2];
        float f13 = (f12 * fSin) + (f11 * fCos);
        float f14 = fArr[4];
        float f15 = fArr[6];
        float f16 = (f15 * fSin) + (f14 * fCos);
        float f17 = fArr[8];
        float f18 = fArr[10];
        float f19 = (f18 * fSin) + (f17 * fCos);
        float f20 = fArr[12];
        float f21 = fArr[14];
        float f22 = (f21 * fSin) + (f20 * fCos);
        fArr[0] = f13;
        fArr[2] = (f12 * fCos) + ((-f11) * fSin);
        fArr[4] = f16;
        fArr[6] = (f15 * fCos) + ((-f14) * fSin);
        fArr[8] = f19;
        fArr[10] = (f18 * fCos) + ((-f17) * fSin);
        fArr[12] = f22;
        fArr[14] = (f21 * fCos) + ((-f20) * fSin);
    }

    /* JADX INFO: renamed from: rotateZ-impl, reason: not valid java name */
    public static final void m3696rotateZimpl(float[] fArr, float f10) {
        double d4 = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        float f11 = fArr[0];
        float f12 = fArr[4];
        float f13 = (fSin * f12) + (fCos * f11);
        float f14 = -fSin;
        float f15 = (f12 * fCos) + (f11 * f14);
        float f16 = fArr[1];
        float f17 = fArr[5];
        float f18 = (fSin * f17) + (fCos * f16);
        float f19 = (f17 * fCos) + (f16 * f14);
        float f20 = fArr[2];
        float f21 = fArr[6];
        float f22 = (fSin * f21) + (fCos * f20);
        float f23 = (f21 * fCos) + (f20 * f14);
        float f24 = fArr[3];
        float f25 = fArr[7];
        float f26 = (fSin * f25) + (fCos * f24);
        fArr[0] = f13;
        fArr[1] = f18;
        fArr[2] = f22;
        fArr[3] = f26;
        fArr[4] = f15;
        fArr[5] = f19;
        fArr[6] = f23;
        fArr[7] = (fCos * f25) + (f14 * f24);
    }

    /* JADX INFO: renamed from: scale-impl, reason: not valid java name */
    public static final void m3697scaleimpl(float[] fArr, float f10, float f11, float f12) {
        fArr[0] = fArr[0] * f10;
        fArr[1] = fArr[1] * f10;
        fArr[2] = fArr[2] * f10;
        fArr[3] = fArr[3] * f10;
        fArr[4] = fArr[4] * f11;
        fArr[5] = fArr[5] * f11;
        fArr[6] = fArr[6] * f11;
        fArr[7] = fArr[7] * f11;
        fArr[8] = fArr[8] * f12;
        fArr[9] = fArr[9] * f12;
        fArr[10] = fArr[10] * f12;
        fArr[11] = fArr[11] * f12;
    }

    /* JADX INFO: renamed from: scale-impl$default, reason: not valid java name */
    public static /* synthetic */ void m3698scaleimpl$default(float[] fArr, float f10, float f11, float f12, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = 1.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 1.0f;
        }
        if ((i10 & 4) != 0) {
            f12 = 1.0f;
        }
        m3697scaleimpl(fArr, f10, f11, f12);
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m3699setimpl(float[] fArr, int i10, int i11, float f10) {
        fArr[(i10 * 4) + i11] = f10;
    }

    /* JADX INFO: renamed from: setFrom-58bKbWc, reason: not valid java name */
    public static final void m3700setFrom58bKbWc(float[] fArr, float[] fArr2) {
        for (int i10 = 0; i10 < 16; i10++) {
            fArr[i10] = fArr2[i10];
        }
    }

    /* JADX INFO: renamed from: timesAssign-58bKbWc, reason: not valid java name */
    public static final void m3701timesAssign58bKbWc(float[] fArr, float[] fArr2) {
        float fM3707dotp89u6pk = MatrixKt.m3707dotp89u6pk(fArr, 0, fArr2, 0);
        float fM3707dotp89u6pk2 = MatrixKt.m3707dotp89u6pk(fArr, 0, fArr2, 1);
        float fM3707dotp89u6pk3 = MatrixKt.m3707dotp89u6pk(fArr, 0, fArr2, 2);
        float fM3707dotp89u6pk4 = MatrixKt.m3707dotp89u6pk(fArr, 0, fArr2, 3);
        float fM3707dotp89u6pk5 = MatrixKt.m3707dotp89u6pk(fArr, 1, fArr2, 0);
        float fM3707dotp89u6pk6 = MatrixKt.m3707dotp89u6pk(fArr, 1, fArr2, 1);
        float fM3707dotp89u6pk7 = MatrixKt.m3707dotp89u6pk(fArr, 1, fArr2, 2);
        float fM3707dotp89u6pk8 = MatrixKt.m3707dotp89u6pk(fArr, 1, fArr2, 3);
        float fM3707dotp89u6pk9 = MatrixKt.m3707dotp89u6pk(fArr, 2, fArr2, 0);
        float fM3707dotp89u6pk10 = MatrixKt.m3707dotp89u6pk(fArr, 2, fArr2, 1);
        float fM3707dotp89u6pk11 = MatrixKt.m3707dotp89u6pk(fArr, 2, fArr2, 2);
        float fM3707dotp89u6pk12 = MatrixKt.m3707dotp89u6pk(fArr, 2, fArr2, 3);
        float fM3707dotp89u6pk13 = MatrixKt.m3707dotp89u6pk(fArr, 3, fArr2, 0);
        float fM3707dotp89u6pk14 = MatrixKt.m3707dotp89u6pk(fArr, 3, fArr2, 1);
        float fM3707dotp89u6pk15 = MatrixKt.m3707dotp89u6pk(fArr, 3, fArr2, 2);
        float fM3707dotp89u6pk16 = MatrixKt.m3707dotp89u6pk(fArr, 3, fArr2, 3);
        fArr[0] = fM3707dotp89u6pk;
        fArr[1] = fM3707dotp89u6pk2;
        fArr[2] = fM3707dotp89u6pk3;
        fArr[3] = fM3707dotp89u6pk4;
        fArr[4] = fM3707dotp89u6pk5;
        fArr[5] = fM3707dotp89u6pk6;
        fArr[6] = fM3707dotp89u6pk7;
        fArr[7] = fM3707dotp89u6pk8;
        fArr[8] = fM3707dotp89u6pk9;
        fArr[9] = fM3707dotp89u6pk10;
        fArr[10] = fM3707dotp89u6pk11;
        fArr[11] = fM3707dotp89u6pk12;
        fArr[12] = fM3707dotp89u6pk13;
        fArr[13] = fM3707dotp89u6pk14;
        fArr[14] = fM3707dotp89u6pk15;
        fArr[15] = fM3707dotp89u6pk16;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3702toStringimpl(float[] fArr) {
        return f4.f.y("\n            |" + fArr[0] + ' ' + fArr[1] + ' ' + fArr[2] + ' ' + fArr[3] + "|\n            |" + fArr[4] + ' ' + fArr[5] + ' ' + fArr[6] + ' ' + fArr[7] + "|\n            |" + fArr[8] + ' ' + fArr[9] + ' ' + fArr[10] + ' ' + fArr[11] + "|\n            |" + fArr[12] + ' ' + fArr[13] + ' ' + fArr[14] + ' ' + fArr[15] + "|\n        ");
    }

    /* JADX INFO: renamed from: translate-impl, reason: not valid java name */
    public static final void m3703translateimpl(float[] fArr, float f10, float f11, float f12) {
        float f13 = (fArr[8] * f12) + (fArr[4] * f11) + (fArr[0] * f10) + fArr[12];
        float f14 = (fArr[9] * f12) + (fArr[5] * f11) + (fArr[1] * f10) + fArr[13];
        float f15 = (fArr[10] * f12) + (fArr[6] * f11) + (fArr[2] * f10) + fArr[14];
        float f16 = (fArr[11] * f12) + (fArr[7] * f11) + (fArr[3] * f10) + fArr[15];
        fArr[12] = f13;
        fArr[13] = f14;
        fArr[14] = f15;
        fArr[15] = f16;
    }

    /* JADX INFO: renamed from: translate-impl$default, reason: not valid java name */
    public static /* synthetic */ void m3704translateimpl$default(float[] fArr, float f10, float f11, float f12, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            f12 = 0.0f;
        }
        m3703translateimpl(fArr, f10, f11, f12);
    }

    public boolean equals(Object obj) {
        return m3685equalsimpl(this.values, obj);
    }

    public final float[] getValues() {
        return this.values;
    }

    public int hashCode() {
        return m3688hashCodeimpl(this.values);
    }

    public String toString() {
        return m3702toStringimpl(this.values);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ float[] m3705unboximpl() {
        return this.values;
    }

    /* JADX INFO: renamed from: map-impl, reason: not valid java name */
    public static final void m3692mapimpl(float[] fArr, MutableRect mutableRect) {
        long jM3690mapMKHz9U = m3690mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getTop()));
        long jM3690mapMKHz9U2 = m3690mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getBottom()));
        long jM3690mapMKHz9U3 = m3690mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getTop()));
        long jM3690mapMKHz9U4 = m3690mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getBottom()));
        mutableRect.setLeft(Math.min(Math.min(Offset.m3236getXimpl(jM3690mapMKHz9U), Offset.m3236getXimpl(jM3690mapMKHz9U2)), Math.min(Offset.m3236getXimpl(jM3690mapMKHz9U3), Offset.m3236getXimpl(jM3690mapMKHz9U4))));
        mutableRect.setTop(Math.min(Math.min(Offset.m3237getYimpl(jM3690mapMKHz9U), Offset.m3237getYimpl(jM3690mapMKHz9U2)), Math.min(Offset.m3237getYimpl(jM3690mapMKHz9U3), Offset.m3237getYimpl(jM3690mapMKHz9U4))));
        mutableRect.setRight(Math.max(Math.max(Offset.m3236getXimpl(jM3690mapMKHz9U), Offset.m3236getXimpl(jM3690mapMKHz9U2)), Math.max(Offset.m3236getXimpl(jM3690mapMKHz9U3), Offset.m3236getXimpl(jM3690mapMKHz9U4))));
        mutableRect.setBottom(Math.max(Math.max(Offset.m3237getYimpl(jM3690mapMKHz9U), Offset.m3237getYimpl(jM3690mapMKHz9U2)), Math.max(Offset.m3237getYimpl(jM3690mapMKHz9U3), Offset.m3237getYimpl(jM3690mapMKHz9U4))));
    }
}
