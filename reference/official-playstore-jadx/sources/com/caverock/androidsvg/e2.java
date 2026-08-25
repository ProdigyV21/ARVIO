package com.caverock.androidsvg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import androidx.media3.common.C;
import androidx.media3.session.legacy.PlaybackStateCompat;
import com.arflix.tv.data.telegram.TelegramBufferPolicy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public final class e2 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static HashSet f12296g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f12297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v1 f12298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c2 f12299c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Stack f12300d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Stack f12301e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Stack f12302f;

    public static Path A(q0 q0Var) {
        Path path = new Path();
        float[] fArr = q0Var.f12412o;
        path.moveTo(fArr[0], fArr[1]);
        int i10 = 2;
        while (true) {
            float[] fArr2 = q0Var.f12412o;
            if (i10 >= fArr2.length) {
                break;
            }
            path.lineTo(fArr2[i10], fArr2[i10 + 1]);
            i10 += 2;
        }
        if (q0Var instanceof r0) {
            path.close();
        }
        if (q0Var.f12250h == null) {
            q0Var.f12250h = c(path);
        }
        return path;
    }

    public static void N(c2 c2Var, boolean z, e1 e1Var) {
        int i10;
        v0 v0Var = c2Var.f12280a;
        float fFloatValue = (z ? v0Var.f12457m : v0Var.f12459o).floatValue();
        if (e1Var instanceof x) {
            i10 = ((x) e1Var).f12487i;
        } else if (!(e1Var instanceof y)) {
            return;
        } else {
            i10 = c2Var.f12280a.f12465u.f12487i;
        }
        int i11 = i(fFloatValue, i10);
        if (z) {
            c2Var.f12283d.setColor(i11);
        } else {
            c2Var.f12284e.setColor(i11);
        }
    }

    public static void a(float f10, float f11, float f12, float f13, float f14, boolean z, boolean z5, float f15, float f16, o0 o0Var) {
        if (f10 == f15 && f11 == f16) {
            return;
        }
        if (f12 == 0.0f || f13 == 0.0f) {
            o0Var.lineTo(f15, f16);
            return;
        }
        float fAbs = Math.abs(f12);
        float fAbs2 = Math.abs(f13);
        double radians = Math.toRadians(((double) f14) % 360.0d);
        double dCos = Math.cos(radians);
        double dSin = Math.sin(radians);
        double d4 = ((double) (f10 - f15)) / 2.0d;
        double d10 = ((double) (f11 - f16)) / 2.0d;
        double d11 = (dSin * d10) + (dCos * d4);
        double d12 = (dCos * d10) + ((-dSin) * d4);
        double d13 = fAbs * fAbs;
        double d14 = fAbs2 * fAbs2;
        double d15 = d11 * d11;
        double d16 = d12 * d12;
        double d17 = (d16 / d14) + (d15 / d13);
        if (d17 > 0.99999d) {
            double dSqrt = Math.sqrt(d17) * 1.00001d;
            fAbs = (float) (((double) fAbs) * dSqrt);
            fAbs2 = (float) (dSqrt * ((double) fAbs2));
            d13 = fAbs * fAbs;
            d14 = fAbs2 * fAbs2;
        }
        double d18 = z == z5 ? -1.0d : 1.0d;
        double d19 = d13 * d14;
        double d20 = d13 * d16;
        double d21 = d14 * d15;
        double d22 = ((d19 - d20) - d21) / (d20 + d21);
        if (d22 < 0.0d) {
            d22 = 0.0d;
        }
        double dSqrt2 = Math.sqrt(d22) * d18;
        double d23 = fAbs;
        double d24 = fAbs2;
        double d25 = ((d23 * d12) / d24) * dSqrt2;
        double d26 = dSqrt2 * (-((d24 * d11) / d23));
        double d27 = ((dCos * d25) - (dSin * d26)) + (((double) (f10 + f15)) / 2.0d);
        double d28 = (dCos * d26) + (dSin * d25) + (((double) (f11 + f16)) / 2.0d);
        double d29 = (d11 - d25) / d23;
        double d30 = (d12 - d26) / d24;
        double d31 = ((-d11) - d25) / d23;
        double d32 = ((-d12) - d26) / d24;
        double d33 = (d30 * d30) + (d29 * d29);
        double dAcos = Math.acos(d29 / Math.sqrt(d33)) * (d30 < 0.0d ? -1.0d : 1.0d);
        double dSqrt3 = ((d30 * d32) + (d29 * d31)) / Math.sqrt(((d32 * d32) + (d31 * d31)) * d33);
        double dAcos2 = ((d29 * d32) - (d30 * d31) < 0.0d ? -1.0d : 1.0d) * (dSqrt3 < -1.0d ? 3.141592653589793d : dSqrt3 > 1.0d ? 0.0d : Math.acos(dSqrt3));
        if (!z5 && dAcos2 > 0.0d) {
            dAcos2 -= 6.283185307179586d;
        } else if (z5 && dAcos2 < 0.0d) {
            dAcos2 += 6.283185307179586d;
        }
        double d34 = dAcos2 % 6.283185307179586d;
        double d35 = dAcos % 6.283185307179586d;
        int iCeil = (int) Math.ceil((Math.abs(d34) * 2.0d) / 3.141592653589793d);
        double d36 = d34 / ((double) iCeil);
        double d37 = d36 / 2.0d;
        double dSin2 = (Math.sin(d37) * 1.3333333333333333d) / (Math.cos(d37) + 1.0d);
        int i10 = iCeil * 6;
        float[] fArr = new float[i10];
        int i11 = 0;
        int i12 = 0;
        while (i11 < iCeil) {
            double d38 = d35;
            double d39 = (((double) i11) * d36) + d38;
            double dCos2 = Math.cos(d39);
            double dSin3 = Math.sin(d39);
            int i13 = i11;
            int i14 = i12;
            fArr[i14] = (float) (dCos2 - (dSin2 * dSin3));
            fArr[i12 + 1] = (float) ((dCos2 * dSin2) + dSin3);
            double d40 = d39 + d36;
            double dCos3 = Math.cos(d40);
            double dSin4 = Math.sin(d40);
            fArr[i14 + 2] = (float) ((dSin2 * dSin4) + dCos3);
            fArr[i14 + 3] = (float) (dSin4 - (dSin2 * dCos3));
            fArr[i14 + 4] = (float) dCos3;
            i12 = i14 + 6;
            fArr[i14 + 5] = (float) dSin4;
            i11 = i13 + 1;
            d35 = d38;
            iCeil = iCeil;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(fAbs, fAbs2);
        matrix.postRotate(f14);
        matrix.postTranslate((float) d27, (float) d28);
        matrix.mapPoints(fArr);
        fArr[i10 - 2] = f15;
        fArr[i10 - 1] = f16;
        for (int i15 = 0; i15 < i10; i15 += 6) {
            o0Var.cubicTo(fArr[i15], fArr[i15 + 1], fArr[i15 + 2], fArr[i15 + 3], fArr[i15 + 4], fArr[i15 + 5]);
        }
    }

    public static u c(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new u(rectF.left, rectF.top, rectF.width(), rectF.height());
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
    
        if (r7 != 9) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d A[PHI: r5
      0x007d: PHI (r5v2 float) = (r5v1 float), (r5v3 float) binds: [B:32:0x006f, B:35:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Matrix e(com.caverock.androidsvg.u r9, com.caverock.androidsvg.u r10, com.caverock.androidsvg.t r11) {
        /*
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            if (r11 == 0) goto L8a
            com.caverock.androidsvg.s r1 = r11.f12440a
            if (r1 != 0) goto Ld
            goto L8a
        Ld:
            float r2 = r9.f12449c
            float r3 = r10.f12449c
            float r2 = r2 / r3
            float r3 = r9.f12450d
            float r4 = r10.f12450d
            float r3 = r3 / r4
            float r4 = r10.f12447a
            float r4 = -r4
            float r5 = r10.f12448b
            float r5 = -r5
            com.caverock.androidsvg.t r6 = com.caverock.androidsvg.t.f12438c
            boolean r6 = r11.equals(r6)
            if (r6 == 0) goto L33
            float r10 = r9.f12447a
            float r9 = r9.f12448b
            r0.preTranslate(r10, r9)
            r0.preScale(r2, r3)
            r0.preTranslate(r4, r5)
            return r0
        L33:
            int r11 = r11.f12441b
            r6 = 2
            if (r11 != r6) goto L3d
            float r11 = java.lang.Math.max(r2, r3)
            goto L41
        L3d:
            float r11 = java.lang.Math.min(r2, r3)
        L41:
            float r2 = r9.f12449c
            float r2 = r2 / r11
            float r3 = r9.f12450d
            float r3 = r3 / r11
            int r7 = r1.ordinal()
            r8 = 1073741824(0x40000000, float:2.0)
            if (r7 == r6) goto L66
            r6 = 3
            if (r7 == r6) goto L61
            r6 = 5
            if (r7 == r6) goto L66
            r6 = 6
            if (r7 == r6) goto L61
            r6 = 8
            if (r7 == r6) goto L66
            r6 = 9
            if (r7 == r6) goto L61
            goto L6b
        L61:
            float r6 = r10.f12449c
            float r6 = r6 - r2
        L64:
            float r4 = r4 - r6
            goto L6b
        L66:
            float r6 = r10.f12449c
            float r6 = r6 - r2
            float r6 = r6 / r8
            goto L64
        L6b:
            int r1 = r1.ordinal()
            switch(r1) {
                case 4: goto L78;
                case 5: goto L78;
                case 6: goto L78;
                case 7: goto L73;
                case 8: goto L73;
                case 9: goto L73;
                default: goto L72;
            }
        L72:
            goto L7d
        L73:
            float r10 = r10.f12450d
            float r10 = r10 - r3
        L76:
            float r5 = r5 - r10
            goto L7d
        L78:
            float r10 = r10.f12450d
            float r10 = r10 - r3
            float r10 = r10 / r8
            goto L76
        L7d:
            float r10 = r9.f12447a
            float r9 = r9.f12448b
            r0.preTranslate(r10, r9)
            r0.preScale(r11, r11)
            r0.preTranslate(r4, r5)
        L8a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.e2.e(com.caverock.androidsvg.u, com.caverock.androidsvg.u, com.caverock.androidsvg.t):android.graphics.Matrix");
    }

    public static Typeface h(String str, int i10, Integer num) {
        int i11;
        boolean z = i10 == 2;
        i11 = num.intValue() > 500 ? z ? 3 : 1 : z ? 2 : 0;
        str.getClass();
        switch (str) {
            case "sans-serif":
                return Typeface.create(Typeface.SANS_SERIF, i11);
            case "monospace":
                return Typeface.create(Typeface.MONOSPACE, i11);
            case "fantasy":
                return Typeface.create(Typeface.SANS_SERIF, i11);
            case "serif":
                return Typeface.create(Typeface.SERIF, i11);
            case "cursive":
                return Typeface.create(Typeface.SANS_SERIF, i11);
            default:
                return null;
        }
    }

    public static int i(float f10, int i10) {
        int i11 = 255;
        int iRound = Math.round(((i10 >> 24) & 255) * f10);
        if (iRound < 0) {
            i11 = 0;
        } else if (iRound <= 255) {
            i11 = iRound;
        }
        return (i11 << 24) | (i10 & 16777215);
    }

    public static void o(String str, Object... objArr) {
        Log.e("SVGAndroidRenderer", String.format(str, objArr));
    }

    public static void q(b0 b0Var, String str) {
        b1 b1VarD = b0Var.f12292a.d(str);
        if (b1VarD == null) {
            Log.w("SVGAndroidRenderer", "Gradient reference '" + str + "' not found");
            return;
        }
        if (!(b1VarD instanceof b0)) {
            o("Gradient href attributes must point to other gradient elements", new Object[0]);
            return;
        }
        if (b1VarD == b0Var) {
            o("Circular reference in gradient href attribute '%s'", str);
            return;
        }
        b0 b0Var2 = (b0) b1VarD;
        if (b0Var.f12258i == null) {
            b0Var.f12258i = b0Var2.f12258i;
        }
        if (b0Var.f12259j == null) {
            b0Var.f12259j = b0Var2.f12259j;
        }
        if (b0Var.k == 0) {
            b0Var.k = b0Var2.k;
        }
        if (b0Var.f12257h.isEmpty()) {
            b0Var.f12257h = b0Var2.f12257h;
        }
        try {
            if (b0Var instanceof c1) {
                c1 c1Var = (c1) b0Var;
                c1 c1Var2 = (c1) b1VarD;
                if (c1Var.f12276m == null) {
                    c1Var.f12276m = c1Var2.f12276m;
                }
                if (c1Var.f12277n == null) {
                    c1Var.f12277n = c1Var2.f12277n;
                }
                if (c1Var.f12278o == null) {
                    c1Var.f12278o = c1Var2.f12278o;
                }
                if (c1Var.f12279p == null) {
                    c1Var.f12279p = c1Var2.f12279p;
                }
            } else {
                r((g1) b0Var, (g1) b1VarD);
            }
        } catch (ClassCastException unused) {
        }
        String str2 = b0Var2.f12260l;
        if (str2 != null) {
            q(b0Var, str2);
        }
    }

    public static void r(g1 g1Var, g1 g1Var2) {
        if (g1Var.f12319m == null) {
            g1Var.f12319m = g1Var2.f12319m;
        }
        if (g1Var.f12320n == null) {
            g1Var.f12320n = g1Var2.f12320n;
        }
        if (g1Var.f12321o == null) {
            g1Var.f12321o = g1Var2.f12321o;
        }
        if (g1Var.f12322p == null) {
            g1Var.f12322p = g1Var2.f12322p;
        }
        if (g1Var.f12323q == null) {
            g1Var.f12323q = g1Var2.f12323q;
        }
    }

    public static void s(p0 p0Var, String str) {
        b1 b1VarD = p0Var.f12292a.d(str);
        if (b1VarD == null) {
            Log.w("SVGAndroidRenderer", "Pattern reference '" + str + "' not found");
            return;
        }
        if (!(b1VarD instanceof p0)) {
            o("Pattern href attributes must point to other pattern elements", new Object[0]);
            return;
        }
        if (b1VarD == p0Var) {
            o("Circular reference in pattern href attribute '%s'", str);
            return;
        }
        p0 p0Var2 = (p0) b1VarD;
        if (p0Var.f12399p == null) {
            p0Var.f12399p = p0Var2.f12399p;
        }
        if (p0Var.f12400q == null) {
            p0Var.f12400q = p0Var2.f12400q;
        }
        if (p0Var.f12401r == null) {
            p0Var.f12401r = p0Var2.f12401r;
        }
        if (p0Var.f12402s == null) {
            p0Var.f12402s = p0Var2.f12402s;
        }
        if (p0Var.f12403t == null) {
            p0Var.f12403t = p0Var2.f12403t;
        }
        if (p0Var.f12404u == null) {
            p0Var.f12404u = p0Var2.f12404u;
        }
        if (p0Var.f12405v == null) {
            p0Var.f12405v = p0Var2.f12405v;
        }
        if (p0Var.f12494i.isEmpty()) {
            p0Var.f12494i = p0Var2.f12494i;
        }
        if (p0Var.f12335o == null) {
            p0Var.f12335o = p0Var2.f12335o;
        }
        if (p0Var.f12314n == null) {
            p0Var.f12314n = p0Var2.f12314n;
        }
        String str2 = p0Var2.f12406w;
        if (str2 != null) {
            s(p0Var, str2);
        }
    }

    public static boolean x(v0 v0Var, long j10) {
        return (j10 & v0Var.f12455i) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Path B(com.caverock.androidsvg.s0 r20) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.e2.B(com.caverock.androidsvg.s0):android.graphics.Path");
    }

    public final u C(g0 g0Var, g0 g0Var2, g0 g0Var3, g0 g0Var4) {
        float fD = g0Var != null ? g0Var.d(this) : 0.0f;
        float fE = g0Var2 != null ? g0Var2.e(this) : 0.0f;
        c2 c2Var = this.f12299c;
        u uVar = c2Var.f12286g;
        if (uVar == null) {
            uVar = c2Var.f12285f;
        }
        return new u(fD, fE, g0Var3 != null ? g0Var3.d(this) : uVar.f12449c, g0Var4 != null ? g0Var4.e(this) : uVar.f12450d);
    }

    public final Path D(a1 a1Var, boolean z) {
        Path pathB;
        Path pathB2;
        this.f12300d.push(this.f12299c);
        c2 c2Var = new c2(this.f12299c);
        this.f12299c = c2Var;
        T(c2Var, a1Var);
        if (!k() || !V()) {
            this.f12299c = (c2) this.f12300d.pop();
            return null;
        }
        if (a1Var instanceof t1) {
            if (!z) {
                o("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
            }
            t1 t1Var = (t1) a1Var;
            b1 b1VarD = a1Var.f12292a.d(t1Var.f12442o);
            if (b1VarD == null) {
                o("Use reference '%s' not found", t1Var.f12442o);
                this.f12299c = (c2) this.f12300d.pop();
                return null;
            }
            if (!(b1VarD instanceof a1)) {
                this.f12299c = (c2) this.f12300d.pop();
                return null;
            }
            pathB = D((a1) b1VarD, false);
            if (pathB != null) {
                if (t1Var.f12250h == null) {
                    t1Var.f12250h = c(pathB);
                }
                Matrix matrix = t1Var.f12291n;
                if (matrix != null) {
                    pathB.transform(matrix);
                }
                if (this.f12299c.f12280a.H != null && (pathB2 = b(a1Var, a1Var.f12250h)) != null) {
                    pathB.op(pathB2, Path.Op.INTERSECT);
                }
                this.f12299c = (c2) this.f12300d.pop();
                return pathB;
            }
            return null;
        }
        if (a1Var instanceof c0) {
            c0 c0Var = (c0) a1Var;
            if (a1Var instanceof m0) {
                y1 y1Var = new y1(((m0) a1Var).f12374o);
                u uVar = a1Var.f12250h;
                Path path = y1Var.f12498a;
                if (uVar == null) {
                    a1Var.f12250h = c(path);
                }
                pathB = path;
            } else {
                pathB = a1Var instanceof s0 ? B((s0) a1Var) : a1Var instanceof v ? y((v) a1Var) : a1Var instanceof a0 ? z((a0) a1Var) : a1Var instanceof q0 ? A((q0) a1Var) : null;
            }
            if (pathB != null) {
                if (c0Var.f12250h == null) {
                    c0Var.f12250h = c(pathB);
                }
                Matrix matrix2 = c0Var.f12275n;
                if (matrix2 != null) {
                    pathB.transform(matrix2);
                }
                pathB.setFillType(w());
            }
            return null;
        }
        if (!(a1Var instanceof m1)) {
            o("Invalid %s element found in clipPath definition", a1Var.n());
            return null;
        }
        m1 m1Var = (m1) a1Var;
        ArrayList arrayList = m1Var.f12413n;
        float fE = 0.0f;
        float fD = (arrayList == null || arrayList.size() == 0) ? 0.0f : ((g0) m1Var.f12413n.get(0)).d(this);
        ArrayList arrayList2 = m1Var.f12414o;
        float fE2 = (arrayList2 == null || arrayList2.size() == 0) ? 0.0f : ((g0) m1Var.f12414o.get(0)).e(this);
        ArrayList arrayList3 = m1Var.f12415p;
        float fD2 = (arrayList3 == null || arrayList3.size() == 0) ? 0.0f : ((g0) m1Var.f12415p.get(0)).d(this);
        ArrayList arrayList4 = m1Var.f12416q;
        if (arrayList4 != null && arrayList4.size() != 0) {
            fE = ((g0) m1Var.f12416q.get(0)).e(this);
        }
        if (this.f12299c.f12280a.T != 1) {
            float fD3 = d(m1Var);
            if (this.f12299c.f12280a.T == 2) {
                fD3 /= 2.0f;
            }
            fD -= fD3;
        }
        if (m1Var.f12250h == null) {
            b2 b2Var = new b2(this, fD, fE2);
            n(m1Var, b2Var);
            Object obj = b2Var.f12270e;
            RectF rectF = (RectF) obj;
            m1Var.f12250h = new u(rectF.left, rectF.top, rectF.width(), ((RectF) obj).height());
        }
        Path path2 = new Path();
        n(m1Var, new b2(this, fD + fD2, fE2 + fE, path2));
        Matrix matrix3 = m1Var.f12375r;
        if (matrix3 != null) {
            path2.transform(matrix3);
        }
        path2.setFillType(w());
        pathB = path2;
        if (this.f12299c.f12280a.H != null) {
            pathB.op(pathB2, Path.Op.INTERSECT);
        }
        this.f12299c = (c2) this.f12300d.pop();
        return pathB;
    }

    public final void E(u uVar) {
        Canvas canvas = this.f12297a;
        if (this.f12299c.f12280a.I != null) {
            Paint paint = new Paint();
            PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
            paint.setXfermode(new PorterDuffXfermode(mode));
            canvas.saveLayer(null, paint, 31);
            Paint paint2 = new Paint();
            paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f})));
            canvas.saveLayer(null, paint2, 31);
            j0 j0Var = (j0) this.f12298b.d(this.f12299c.f12280a.I);
            L(j0Var, uVar);
            canvas.restore();
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(mode));
            canvas.saveLayer(null, paint3, 31);
            L(j0Var, uVar);
            canvas.restore();
            canvas.restore();
        }
        O();
    }

    public final boolean F() {
        b1 b1VarD;
        if (this.f12299c.f12280a.f12464t.floatValue() >= 1.0f && this.f12299c.f12280a.I == null) {
            return false;
        }
        Canvas canvas = this.f12297a;
        int iFloatValue = (int) (this.f12299c.f12280a.f12464t.floatValue() * 256.0f);
        if (iFloatValue < 0) {
            iFloatValue = 0;
        } else if (iFloatValue > 255) {
            iFloatValue = 255;
        }
        canvas.saveLayerAlpha(null, iFloatValue, 31);
        this.f12300d.push(this.f12299c);
        c2 c2Var = new c2(this.f12299c);
        this.f12299c = c2Var;
        String str = c2Var.f12280a.I;
        if (str != null && ((b1VarD = this.f12298b.d(str)) == null || !(b1VarD instanceof j0))) {
            o("Mask reference '%s' not found", this.f12299c.f12280a.I);
            this.f12299c.f12280a.I = null;
        }
        return true;
    }

    public final void G(w0 w0Var, u uVar, u uVar2, t tVar) {
        Canvas canvas = this.f12297a;
        if (uVar.f12449c == 0.0f || uVar.f12450d == 0.0f) {
            return;
        }
        if (tVar == null && (tVar = w0Var.f12314n) == null) {
            tVar = t.f12439d;
        }
        T(this.f12299c, w0Var);
        if (k()) {
            c2 c2Var = this.f12299c;
            c2Var.f12285f = uVar;
            if (!c2Var.f12280a.f12468y.booleanValue()) {
                u uVar3 = this.f12299c.f12285f;
                M(uVar3.f12447a, uVar3.f12448b, uVar3.f12449c, uVar3.f12450d);
            }
            f(w0Var, this.f12299c.f12285f);
            if (uVar2 != null) {
                canvas.concat(e(this.f12299c.f12285f, uVar2, tVar));
                this.f12299c.f12286g = w0Var.f12335o;
            } else {
                u uVar4 = this.f12299c.f12285f;
                canvas.translate(uVar4.f12447a, uVar4.f12448b);
            }
            boolean zF = F();
            U();
            I(w0Var, true);
            if (zF) {
                E(w0Var.f12250h);
            }
            R(w0Var);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void H(d1 d1Var) {
        g0 g0Var;
        String str;
        int iIndexOf;
        Set setA;
        g0 g0Var2;
        Boolean bool;
        if (d1Var instanceof k0) {
            return;
        }
        P();
        if ((d1Var instanceof b1) && (bool = ((b1) d1Var).f12262d) != null) {
            this.f12299c.f12287h = bool.booleanValue();
        }
        if (d1Var instanceof w0) {
            w0 w0Var = (w0) d1Var;
            G(w0Var, C(w0Var.f12473p, w0Var.f12474q, w0Var.f12475r, w0Var.f12476s), w0Var.f12335o, w0Var.f12314n);
        } else {
            Bitmap bitmapDecodeByteArray = null;
            if (d1Var instanceof t1) {
                t1 t1Var = (t1) d1Var;
                Canvas canvas = this.f12297a;
                g0 g0Var3 = t1Var.f12445r;
                if ((g0Var3 == null || !g0Var3.g()) && ((g0Var2 = t1Var.f12446s) == null || !g0Var2.g())) {
                    T(this.f12299c, t1Var);
                    if (k()) {
                        d1 d1VarD = t1Var.f12292a.d(t1Var.f12442o);
                        if (d1VarD == null) {
                            o("Use reference '%s' not found", t1Var.f12442o);
                        } else {
                            Matrix matrix = t1Var.f12291n;
                            if (matrix != null) {
                                canvas.concat(matrix);
                            }
                            g0 g0Var4 = t1Var.f12443p;
                            float fD = g0Var4 != null ? g0Var4.d(this) : 0.0f;
                            g0 g0Var5 = t1Var.f12444q;
                            canvas.translate(fD, g0Var5 != null ? g0Var5.e(this) : 0.0f);
                            f(t1Var, t1Var.f12250h);
                            boolean zF = F();
                            this.f12301e.push(t1Var);
                            this.f12302f.push(this.f12297a.getMatrix());
                            if (d1VarD instanceof w0) {
                                w0 w0Var2 = (w0) d1VarD;
                                u uVarC = C(null, null, t1Var.f12445r, t1Var.f12446s);
                                P();
                                G(w0Var2, uVarC, w0Var2.f12335o, w0Var2.f12314n);
                                O();
                            } else if (d1VarD instanceof j1) {
                                g0 g0Var6 = t1Var.f12445r;
                                if (g0Var6 == null) {
                                    g0Var6 = new g0(100.0f, 9);
                                }
                                g0 g0Var7 = t1Var.f12446s;
                                if (g0Var7 == null) {
                                    g0Var7 = new g0(100.0f, 9);
                                }
                                u uVarC2 = C(null, null, g0Var6, g0Var7);
                                P();
                                j1 j1Var = (j1) d1VarD;
                                if (uVarC2.f12449c != 0.0f && uVarC2.f12450d != 0.0f) {
                                    t tVar = j1Var.f12314n;
                                    if (tVar == null) {
                                        tVar = t.f12439d;
                                    }
                                    T(this.f12299c, j1Var);
                                    c2 c2Var = this.f12299c;
                                    c2Var.f12285f = uVarC2;
                                    if (!c2Var.f12280a.f12468y.booleanValue()) {
                                        u uVar = this.f12299c.f12285f;
                                        M(uVar.f12447a, uVar.f12448b, uVar.f12449c, uVar.f12450d);
                                    }
                                    u uVar2 = j1Var.f12335o;
                                    if (uVar2 != null) {
                                        canvas.concat(e(this.f12299c.f12285f, uVar2, tVar));
                                        this.f12299c.f12286g = j1Var.f12335o;
                                    } else {
                                        u uVar3 = this.f12299c.f12285f;
                                        canvas.translate(uVar3.f12447a, uVar3.f12448b);
                                    }
                                    boolean zF2 = F();
                                    I(j1Var, true);
                                    if (zF2) {
                                        E(j1Var.f12250h);
                                    }
                                    R(j1Var);
                                }
                                O();
                            } else {
                                H(d1VarD);
                            }
                            this.f12301e.pop();
                            this.f12302f.pop();
                            if (zF) {
                                E(t1Var.f12250h);
                            }
                            R(t1Var);
                        }
                    }
                }
            } else if (d1Var instanceof i1) {
                i1 i1Var = (i1) d1Var;
                T(this.f12299c, i1Var);
                if (k()) {
                    Matrix matrix2 = i1Var.f12291n;
                    if (matrix2 != null) {
                        this.f12297a.concat(matrix2);
                    }
                    f(i1Var, i1Var.f12250h);
                    boolean zF3 = F();
                    String language = Locale.getDefault().getLanguage();
                    Iterator it = i1Var.f12494i.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        d1 d1Var2 = (d1) it.next();
                        if (d1Var2 instanceof x0) {
                            x0 x0Var = (x0) d1Var2;
                            if (x0Var.b() == null && ((setA = x0Var.a()) == null || (!setA.isEmpty() && setA.contains(language)))) {
                                Set setE = x0Var.e();
                                if (setE != null) {
                                    if (f12296g == null) {
                                        synchronized (e2.class) {
                                            HashSet hashSet = new HashSet();
                                            f12296g = hashSet;
                                            hashSet.add("Structure");
                                            f12296g.add("BasicStructure");
                                            f12296g.add("ConditionalProcessing");
                                            f12296g.add("Image");
                                            f12296g.add("Style");
                                            f12296g.add("ViewportAttribute");
                                            f12296g.add("Shape");
                                            f12296g.add("BasicText");
                                            f12296g.add("PaintAttribute");
                                            f12296g.add("BasicPaintAttribute");
                                            f12296g.add("OpacityAttribute");
                                            f12296g.add("BasicGraphicsAttribute");
                                            f12296g.add("Marker");
                                            f12296g.add("Gradient");
                                            f12296g.add("Pattern");
                                            f12296g.add("Clip");
                                            f12296g.add("BasicClip");
                                            f12296g.add("Mask");
                                            f12296g.add("View");
                                        }
                                    }
                                    if (setE.isEmpty() || !f12296g.containsAll(setE)) {
                                    }
                                }
                                Set setL = x0Var.l();
                                if (setL == null) {
                                    Set setM = x0Var.m();
                                    if (setM == null) {
                                        H(d1Var2);
                                        break;
                                    }
                                    setM.isEmpty();
                                } else {
                                    setL.isEmpty();
                                }
                            }
                        }
                    }
                    if (zF3) {
                        E(i1Var.f12250h);
                    }
                    R(i1Var);
                }
            } else if (d1Var instanceof d0) {
                d0 d0Var = (d0) d1Var;
                T(this.f12299c, d0Var);
                if (k()) {
                    Matrix matrix3 = d0Var.f12291n;
                    if (matrix3 != null) {
                        this.f12297a.concat(matrix3);
                    }
                    f(d0Var, d0Var.f12250h);
                    boolean zF4 = F();
                    I(d0Var, true);
                    if (zF4) {
                        E(d0Var.f12250h);
                    }
                    R(d0Var);
                }
            } else if (d1Var instanceof f0) {
                f0 f0Var = (f0) d1Var;
                Canvas canvas2 = this.f12297a;
                g0 g0Var8 = f0Var.f12311r;
                if (g0Var8 != null && !g0Var8.g() && (g0Var = f0Var.f12312s) != null && !g0Var.g() && (str = f0Var.f12308o) != null) {
                    t tVar2 = f0Var.f12314n;
                    if (tVar2 == null) {
                        tVar2 = t.f12439d;
                    }
                    if (str.startsWith("data:") && str.length() >= 14 && (iIndexOf = str.indexOf(44)) >= 12 && ";base64".equals(str.substring(iIndexOf - 7, iIndexOf))) {
                        try {
                            byte[] bArrDecode = Base64.decode(str.substring(iIndexOf + 1), 0);
                            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                        } catch (Exception e5) {
                            Log.e("SVGAndroidRenderer", "Could not decode bad Data URL", e5);
                        }
                    }
                    if (bitmapDecodeByteArray != null) {
                        u uVar4 = new u(0.0f, 0.0f, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight());
                        T(this.f12299c, f0Var);
                        if (k() && V()) {
                            Matrix matrix4 = f0Var.f12313t;
                            if (matrix4 != null) {
                                canvas2.concat(matrix4);
                            }
                            g0 g0Var9 = f0Var.f12309p;
                            float fD2 = g0Var9 != null ? g0Var9.d(this) : 0.0f;
                            g0 g0Var10 = f0Var.f12310q;
                            float fE = g0Var10 != null ? g0Var10.e(this) : 0.0f;
                            float fD3 = f0Var.f12311r.d(this);
                            float fD4 = f0Var.f12312s.d(this);
                            c2 c2Var2 = this.f12299c;
                            c2Var2.f12285f = new u(fD2, fE, fD3, fD4);
                            if (!c2Var2.f12280a.f12468y.booleanValue()) {
                                u uVar5 = this.f12299c.f12285f;
                                M(uVar5.f12447a, uVar5.f12448b, uVar5.f12449c, uVar5.f12450d);
                            }
                            f0Var.f12250h = this.f12299c.f12285f;
                            R(f0Var);
                            f(f0Var, f0Var.f12250h);
                            boolean zF5 = F();
                            U();
                            canvas2.save();
                            canvas2.concat(e(this.f12299c.f12285f, uVar4, tVar2));
                            canvas2.drawBitmap(bitmapDecodeByteArray, 0.0f, 0.0f, new Paint(this.f12299c.f12280a.W != 3 ? 2 : 0));
                            canvas2.restore();
                            if (zF5) {
                                E(f0Var.f12250h);
                            }
                        }
                    }
                }
            } else if (d1Var instanceof m0) {
                m0 m0Var = (m0) d1Var;
                if (m0Var.f12374o != null) {
                    T(this.f12299c, m0Var);
                    if (k() && V()) {
                        c2 c2Var3 = this.f12299c;
                        if (c2Var3.f12282c || c2Var3.f12281b) {
                            Matrix matrix5 = m0Var.f12275n;
                            if (matrix5 != null) {
                                this.f12297a.concat(matrix5);
                            }
                            Path path = new y1(m0Var.f12374o).f12498a;
                            if (m0Var.f12250h == null) {
                                m0Var.f12250h = c(path);
                            }
                            R(m0Var);
                            g(m0Var);
                            f(m0Var, m0Var.f12250h);
                            boolean zF6 = F();
                            c2 c2Var4 = this.f12299c;
                            if (c2Var4.f12281b) {
                                int i10 = c2Var4.f12280a.N;
                                path.setFillType((i10 == 0 || i10 != 2) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                                l(m0Var, path);
                            }
                            if (this.f12299c.f12282c) {
                                m(path);
                            }
                            K(m0Var);
                            if (zF6) {
                                E(m0Var.f12250h);
                            }
                        }
                    }
                }
            } else if (d1Var instanceof s0) {
                s0 s0Var = (s0) d1Var;
                g0 g0Var11 = s0Var.f12433q;
                if (g0Var11 != null && s0Var.f12434r != null && !g0Var11.g() && !s0Var.f12434r.g()) {
                    T(this.f12299c, s0Var);
                    if (k() && V()) {
                        Matrix matrix6 = s0Var.f12275n;
                        if (matrix6 != null) {
                            this.f12297a.concat(matrix6);
                        }
                        Path pathB = B(s0Var);
                        R(s0Var);
                        g(s0Var);
                        f(s0Var, s0Var.f12250h);
                        boolean zF7 = F();
                        if (this.f12299c.f12281b) {
                            l(s0Var, pathB);
                        }
                        if (this.f12299c.f12282c) {
                            m(pathB);
                        }
                        if (zF7) {
                            E(s0Var.f12250h);
                        }
                    }
                }
            } else if (d1Var instanceof v) {
                v vVar = (v) d1Var;
                g0 g0Var12 = vVar.f12454q;
                if (g0Var12 != null && !g0Var12.g()) {
                    T(this.f12299c, vVar);
                    if (k() && V()) {
                        Matrix matrix7 = vVar.f12275n;
                        if (matrix7 != null) {
                            this.f12297a.concat(matrix7);
                        }
                        Path pathY = y(vVar);
                        R(vVar);
                        g(vVar);
                        f(vVar, vVar.f12250h);
                        boolean zF8 = F();
                        if (this.f12299c.f12281b) {
                            l(vVar, pathY);
                        }
                        if (this.f12299c.f12282c) {
                            m(pathY);
                        }
                        if (zF8) {
                            E(vVar.f12250h);
                        }
                    }
                }
            } else if (d1Var instanceof a0) {
                a0 a0Var = (a0) d1Var;
                g0 g0Var13 = a0Var.f12248q;
                if (g0Var13 != null && a0Var.f12249r != null && !g0Var13.g() && !a0Var.f12249r.g()) {
                    T(this.f12299c, a0Var);
                    if (k() && V()) {
                        Matrix matrix8 = a0Var.f12275n;
                        if (matrix8 != null) {
                            this.f12297a.concat(matrix8);
                        }
                        Path pathZ = z(a0Var);
                        R(a0Var);
                        g(a0Var);
                        f(a0Var, a0Var.f12250h);
                        boolean zF9 = F();
                        if (this.f12299c.f12281b) {
                            l(a0Var, pathZ);
                        }
                        if (this.f12299c.f12282c) {
                            m(pathZ);
                        }
                        if (zF9) {
                            E(a0Var.f12250h);
                        }
                    }
                }
            } else if (d1Var instanceof h0) {
                h0 h0Var = (h0) d1Var;
                T(this.f12299c, h0Var);
                if (k() && V() && this.f12299c.f12282c) {
                    Matrix matrix9 = h0Var.f12275n;
                    if (matrix9 != null) {
                        this.f12297a.concat(matrix9);
                    }
                    g0 g0Var14 = h0Var.f12331o;
                    float fD5 = g0Var14 == null ? 0.0f : g0Var14.d(this);
                    g0 g0Var15 = h0Var.f12332p;
                    float fE2 = g0Var15 == null ? 0.0f : g0Var15.e(this);
                    g0 g0Var16 = h0Var.f12333q;
                    float fD6 = g0Var16 == null ? 0.0f : g0Var16.d(this);
                    g0 g0Var17 = h0Var.f12334r;
                    fE = g0Var17 != null ? g0Var17.e(this) : 0.0f;
                    if (h0Var.f12250h == null) {
                        h0Var.f12250h = new u(Math.min(fD5, fD6), Math.min(fE2, fE), Math.abs(fD6 - fD5), Math.abs(fE - fE2));
                    }
                    Path path2 = new Path();
                    path2.moveTo(fD5, fE2);
                    path2.lineTo(fD6, fE);
                    R(h0Var);
                    g(h0Var);
                    f(h0Var, h0Var.f12250h);
                    boolean zF10 = F();
                    m(path2);
                    K(h0Var);
                    if (zF10) {
                        E(h0Var.f12250h);
                    }
                }
            } else if (d1Var instanceof r0) {
                r0 r0Var = (r0) d1Var;
                T(this.f12299c, r0Var);
                if (k() && V()) {
                    c2 c2Var5 = this.f12299c;
                    if (c2Var5.f12282c || c2Var5.f12281b) {
                        Matrix matrix10 = r0Var.f12275n;
                        if (matrix10 != null) {
                            this.f12297a.concat(matrix10);
                        }
                        if (r0Var.f12412o.length >= 2) {
                            Path pathA = A(r0Var);
                            R(r0Var);
                            g(r0Var);
                            f(r0Var, r0Var.f12250h);
                            boolean zF11 = F();
                            if (this.f12299c.f12281b) {
                                l(r0Var, pathA);
                            }
                            if (this.f12299c.f12282c) {
                                m(pathA);
                            }
                            K(r0Var);
                            if (zF11) {
                                E(r0Var.f12250h);
                            }
                        }
                    }
                }
            } else if (d1Var instanceof q0) {
                q0 q0Var = (q0) d1Var;
                T(this.f12299c, q0Var);
                if (k() && V()) {
                    c2 c2Var6 = this.f12299c;
                    if (c2Var6.f12282c || c2Var6.f12281b) {
                        Matrix matrix11 = q0Var.f12275n;
                        if (matrix11 != null) {
                            this.f12297a.concat(matrix11);
                        }
                        if (q0Var.f12412o.length >= 2) {
                            Path pathA2 = A(q0Var);
                            R(q0Var);
                            int i11 = this.f12299c.f12280a.N;
                            pathA2.setFillType((i11 == 0 || i11 != 2) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            g(q0Var);
                            f(q0Var, q0Var.f12250h);
                            boolean zF12 = F();
                            if (this.f12299c.f12281b) {
                                l(q0Var, pathA2);
                            }
                            if (this.f12299c.f12282c) {
                                m(pathA2);
                            }
                            K(q0Var);
                            if (zF12) {
                                E(q0Var.f12250h);
                            }
                        }
                    }
                }
            } else if (d1Var instanceof m1) {
                m1 m1Var = (m1) d1Var;
                T(this.f12299c, m1Var);
                if (k()) {
                    Matrix matrix12 = m1Var.f12375r;
                    if (matrix12 != null) {
                        this.f12297a.concat(matrix12);
                    }
                    ArrayList arrayList = m1Var.f12413n;
                    float fD7 = (arrayList == null || arrayList.size() == 0) ? 0.0f : ((g0) m1Var.f12413n.get(0)).d(this);
                    ArrayList arrayList2 = m1Var.f12414o;
                    float fE3 = (arrayList2 == null || arrayList2.size() == 0) ? 0.0f : ((g0) m1Var.f12414o.get(0)).e(this);
                    ArrayList arrayList3 = m1Var.f12415p;
                    float fD8 = (arrayList3 == null || arrayList3.size() == 0) ? 0.0f : ((g0) m1Var.f12415p.get(0)).d(this);
                    ArrayList arrayList4 = m1Var.f12416q;
                    if (arrayList4 != null && arrayList4.size() != 0) {
                        fE = ((g0) m1Var.f12416q.get(0)).e(this);
                    }
                    int iV = v();
                    if (iV != 1) {
                        float fD9 = d(m1Var);
                        if (iV == 2) {
                            fD9 /= 2.0f;
                        }
                        fD7 -= fD9;
                    }
                    if (m1Var.f12250h == null) {
                        b2 b2Var = new b2(this, fD7, fE3);
                        n(m1Var, b2Var);
                        RectF rectF = (RectF) b2Var.f12270e;
                        m1Var.f12250h = new u(rectF.left, rectF.top, rectF.width(), ((RectF) b2Var.f12270e).height());
                    }
                    R(m1Var);
                    g(m1Var);
                    f(m1Var, m1Var.f12250h);
                    boolean zF13 = F();
                    n(m1Var, new a2(this, fD7 + fD8, fE3 + fE));
                    if (zF13) {
                        E(m1Var.f12250h);
                    }
                }
            }
        }
        O();
    }

    public final void I(y0 y0Var, boolean z) {
        if (z) {
            this.f12301e.push(y0Var);
            this.f12302f.push(this.f12297a.getMatrix());
        }
        Iterator it = y0Var.f12494i.iterator();
        while (it.hasNext()) {
            H((d1) it.next());
        }
        if (z) {
            this.f12301e.pop();
            this.f12302f.pop();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fb A[PHI: r2
      0x00fb: PHI (r2v1 float) = (r2v0 float), (r2v2 float) binds: [B:63:0x00ef, B:66:0x00f5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void J(com.caverock.androidsvg.i0 r13, com.caverock.androidsvg.x1 r14) {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.e2.J(com.caverock.androidsvg.i0, com.caverock.androidsvg.x1):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void K(com.caverock.androidsvg.c0 r20) {
        /*
            Method dump skipped, instruction units count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.e2.K(com.caverock.androidsvg.c0):void");
    }

    public final void L(j0 j0Var, u uVar) {
        float fD;
        float fE;
        Canvas canvas = this.f12297a;
        Boolean bool = j0Var.f12346n;
        if (bool == null || !bool.booleanValue()) {
            g0 g0Var = j0Var.f12348p;
            float fB = g0Var != null ? g0Var.b(this, 1.0f) : 1.2f;
            g0 g0Var2 = j0Var.f12349q;
            float fB2 = g0Var2 != null ? g0Var2.b(this, 1.0f) : 1.2f;
            fD = fB * uVar.f12449c;
            fE = fB2 * uVar.f12450d;
        } else {
            g0 g0Var3 = j0Var.f12348p;
            fD = g0Var3 != null ? g0Var3.d(this) : uVar.f12449c;
            g0 g0Var4 = j0Var.f12349q;
            fE = g0Var4 != null ? g0Var4.e(this) : uVar.f12450d;
        }
        if (fD == 0.0f || fE == 0.0f) {
            return;
        }
        P();
        c2 c2VarT = t(j0Var);
        this.f12299c = c2VarT;
        c2VarT.f12280a.f12464t = Float.valueOf(1.0f);
        boolean zF = F();
        canvas.save();
        Boolean bool2 = j0Var.f12347o;
        if (bool2 != null && !bool2.booleanValue()) {
            canvas.translate(uVar.f12447a, uVar.f12448b);
            canvas.scale(uVar.f12449c, uVar.f12450d);
        }
        I(j0Var, false);
        canvas.restore();
        if (zF) {
            E(uVar);
        }
        O();
    }

    public final void M(float f10, float f11, float f12, float f13) {
        float fD = f12 + f10;
        float fE = f13 + f11;
        androidx.emoji2.text.e0 e0Var = this.f12299c.f12280a.z;
        if (e0Var != null) {
            f10 += ((g0) e0Var.f2603o).d(this);
            f11 += ((g0) this.f12299c.f12280a.z.f2600l).e(this);
            fD -= ((g0) this.f12299c.f12280a.z.f2601m).d(this);
            fE -= ((g0) this.f12299c.f12280a.z.f2602n).e(this);
        }
        this.f12297a.clipRect(f10, f11, fD, fE);
    }

    public final void O() {
        this.f12297a.restore();
        this.f12299c = (c2) this.f12300d.pop();
    }

    public final void P() {
        this.f12297a.save();
        this.f12300d.push(this.f12299c);
        this.f12299c = new c2(this.f12299c);
    }

    public final String Q(String str, boolean z, boolean z5) {
        if (this.f12299c.f12287h) {
            return str.replaceAll("[\\n\\t]", " ");
        }
        String strReplaceAll = str.replaceAll("\\n", "").replaceAll("\\t", " ");
        if (z) {
            strReplaceAll = strReplaceAll.replaceAll("^\\s+", "");
        }
        if (z5) {
            strReplaceAll = strReplaceAll.replaceAll("\\s+$", "");
        }
        return strReplaceAll.replaceAll("\\s{2,}", " ");
    }

    public final void R(a1 a1Var) {
        if (a1Var.f12293b == null || a1Var.f12250h == null) {
            return;
        }
        Matrix matrix = new Matrix();
        if (((Matrix) this.f12302f.peek()).invert(matrix)) {
            u uVar = a1Var.f12250h;
            float f10 = uVar.f12447a;
            float f11 = uVar.f12448b;
            float fA = uVar.a();
            u uVar2 = a1Var.f12250h;
            float f12 = uVar2.f12448b;
            float fA2 = uVar2.a();
            float fB = a1Var.f12250h.b();
            u uVar3 = a1Var.f12250h;
            float[] fArr = {f10, f11, fA, f12, fA2, fB, uVar3.f12447a, uVar3.b()};
            matrix.preConcat(this.f12297a.getMatrix());
            matrix.mapPoints(fArr);
            float f13 = fArr[0];
            float f14 = fArr[1];
            RectF rectF = new RectF(f13, f14, f13, f14);
            for (int i10 = 2; i10 <= 6; i10 += 2) {
                float f15 = fArr[i10];
                if (f15 < rectF.left) {
                    rectF.left = f15;
                }
                if (f15 > rectF.right) {
                    rectF.right = f15;
                }
                float f16 = fArr[i10 + 1];
                if (f16 < rectF.top) {
                    rectF.top = f16;
                }
                if (f16 > rectF.bottom) {
                    rectF.bottom = f16;
                }
            }
            a1 a1Var2 = (a1) this.f12301e.peek();
            u uVar4 = a1Var2.f12250h;
            if (uVar4 == null) {
                float f17 = rectF.left;
                float f18 = rectF.top;
                a1Var2.f12250h = new u(f17, f18, rectF.right - f17, rectF.bottom - f18);
                return;
            }
            float f19 = rectF.left;
            float f20 = rectF.top;
            float f21 = rectF.right - f19;
            float f22 = rectF.bottom - f20;
            if (f19 < uVar4.f12447a) {
                uVar4.f12447a = f19;
            }
            if (f20 < uVar4.f12448b) {
                uVar4.f12448b = f20;
            }
            if (f19 + f21 > uVar4.a()) {
                uVar4.f12449c = (f19 + f21) - uVar4.f12447a;
            }
            if (f20 + f22 > uVar4.b()) {
                uVar4.f12450d = (f20 + f22) - uVar4.f12448b;
            }
        }
    }

    public final void S(c2 c2Var, v0 v0Var) {
        if (x(v0Var, 4096L)) {
            c2Var.f12280a.f12465u = v0Var.f12465u;
        }
        if (x(v0Var, 2048L)) {
            c2Var.f12280a.f12464t = v0Var.f12464t;
        }
        boolean zX = x(v0Var, 1L);
        x xVar = x.f12486m;
        if (zX) {
            c2Var.f12280a.f12456l = v0Var.f12456l;
            e1 e1Var = v0Var.f12456l;
            c2Var.f12281b = (e1Var == null || e1Var == xVar) ? false : true;
        }
        if (x(v0Var, 4L)) {
            c2Var.f12280a.f12457m = v0Var.f12457m;
        }
        if (x(v0Var, 6149L)) {
            N(c2Var, true, c2Var.f12280a.f12456l);
        }
        if (x(v0Var, 2L)) {
            c2Var.f12280a.N = v0Var.N;
        }
        if (x(v0Var, 8L)) {
            c2Var.f12280a.f12458n = v0Var.f12458n;
            e1 e1Var2 = v0Var.f12458n;
            c2Var.f12282c = (e1Var2 == null || e1Var2 == xVar) ? false : true;
        }
        if (x(v0Var, 16L)) {
            c2Var.f12280a.f12459o = v0Var.f12459o;
        }
        if (x(v0Var, 6168L)) {
            N(c2Var, false, c2Var.f12280a.f12458n);
        }
        if (x(v0Var, 34359738368L)) {
            c2Var.f12280a.V = v0Var.V;
        }
        if (x(v0Var, 32L)) {
            v0 v0Var2 = c2Var.f12280a;
            g0 g0Var = v0Var.f12460p;
            v0Var2.f12460p = g0Var;
            c2Var.f12284e.setStrokeWidth(g0Var.a(this));
        }
        if (x(v0Var, 64L)) {
            v0 v0Var3 = c2Var.f12280a;
            Paint paint = c2Var.f12284e;
            v0Var3.O = v0Var.O;
            int iC = h.f0.c(v0Var.O);
            if (iC == 0) {
                paint.setStrokeCap(Paint.Cap.BUTT);
            } else if (iC == 1) {
                paint.setStrokeCap(Paint.Cap.ROUND);
            } else if (iC == 2) {
                paint.setStrokeCap(Paint.Cap.SQUARE);
            }
        }
        if (x(v0Var, 128L)) {
            v0 v0Var4 = c2Var.f12280a;
            Paint paint2 = c2Var.f12284e;
            v0Var4.P = v0Var.P;
            int iC2 = h.f0.c(v0Var.P);
            if (iC2 == 0) {
                paint2.setStrokeJoin(Paint.Join.MITER);
            } else if (iC2 == 1) {
                paint2.setStrokeJoin(Paint.Join.ROUND);
            } else if (iC2 == 2) {
                paint2.setStrokeJoin(Paint.Join.BEVEL);
            }
        }
        if (x(v0Var, 256L)) {
            c2Var.f12280a.f12461q = v0Var.f12461q;
            c2Var.f12284e.setStrokeMiter(v0Var.f12461q.floatValue());
        }
        if (x(v0Var, 512L)) {
            c2Var.f12280a.f12462r = v0Var.f12462r;
        }
        if (x(v0Var, 1024L)) {
            c2Var.f12280a.f12463s = v0Var.f12463s;
        }
        Typeface typefaceH = null;
        if (x(v0Var, 1536L)) {
            v0 v0Var5 = c2Var.f12280a;
            Paint paint3 = c2Var.f12284e;
            g0[] g0VarArr = v0Var5.f12462r;
            if (g0VarArr == null) {
                paint3.setPathEffect(null);
            } else {
                int length = g0VarArr.length;
                int i10 = length % 2 == 0 ? length : length * 2;
                float[] fArr = new float[i10];
                float f10 = 0.0f;
                for (int i11 = 0; i11 < i10; i11++) {
                    float fA = v0Var5.f12462r[i11 % length].a(this);
                    fArr[i11] = fA;
                    f10 += fA;
                }
                if (f10 == 0.0f) {
                    paint3.setPathEffect(null);
                } else {
                    float fA2 = v0Var5.f12463s.a(this);
                    if (fA2 < 0.0f) {
                        fA2 = (fA2 % f10) + f10;
                    }
                    paint3.setPathEffect(new DashPathEffect(fArr, fA2));
                }
            }
        }
        if (x(v0Var, 16384L)) {
            float textSize = this.f12299c.f12283d.getTextSize();
            c2Var.f12280a.f12467w = v0Var.f12467w;
            c2Var.f12283d.setTextSize(v0Var.f12467w.b(this, textSize));
            c2Var.f12284e.setTextSize(v0Var.f12467w.b(this, textSize));
        }
        if (x(v0Var, 8192L)) {
            c2Var.f12280a.f12466v = v0Var.f12466v;
        }
        if (x(v0Var, 32768L)) {
            if (v0Var.x.intValue() == -1 && c2Var.f12280a.x.intValue() > 100) {
                v0 v0Var6 = c2Var.f12280a;
                v0Var6.x = Integer.valueOf(v0Var6.x.intValue() - 100);
            } else if (v0Var.x.intValue() != 1 || c2Var.f12280a.x.intValue() >= 900) {
                c2Var.f12280a.x = v0Var.x;
            } else {
                v0 v0Var7 = c2Var.f12280a;
                v0Var7.x = Integer.valueOf(v0Var7.x.intValue() + 100);
            }
        }
        if (x(v0Var, 65536L)) {
            c2Var.f12280a.Q = v0Var.Q;
        }
        if (x(v0Var, 106496L)) {
            v0 v0Var8 = c2Var.f12280a;
            ArrayList arrayList = v0Var8.f12466v;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    typefaceH = h((String) it.next(), v0Var8.Q, v0Var8.x);
                    if (typefaceH != null) {
                        break;
                    }
                }
            }
            if (typefaceH == null) {
                typefaceH = h(C.SERIF_NAME, v0Var8.Q, v0Var8.x);
            }
            c2Var.f12283d.setTypeface(typefaceH);
            c2Var.f12284e.setTypeface(typefaceH);
        }
        if (x(v0Var, 131072L)) {
            v0 v0Var9 = c2Var.f12280a;
            Paint paint4 = c2Var.f12284e;
            Paint paint5 = c2Var.f12283d;
            v0Var9.R = v0Var.R;
            paint5.setStrikeThruText(v0Var.R == 4);
            paint5.setUnderlineText(v0Var.R == 2);
            paint4.setStrikeThruText(v0Var.R == 4);
            paint4.setUnderlineText(v0Var.R == 2);
        }
        if (x(v0Var, 68719476736L)) {
            c2Var.f12280a.S = v0Var.S;
        }
        if (x(v0Var, 262144L)) {
            c2Var.f12280a.T = v0Var.T;
        }
        if (x(v0Var, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED)) {
            c2Var.f12280a.f12468y = v0Var.f12468y;
        }
        if (x(v0Var, 2097152L)) {
            c2Var.f12280a.A = v0Var.A;
        }
        if (x(v0Var, 4194304L)) {
            c2Var.f12280a.B = v0Var.B;
        }
        if (x(v0Var, TelegramBufferPolicy.DEFAULT_PREFETCH_BYTES)) {
            c2Var.f12280a.C = v0Var.C;
        }
        if (x(v0Var, 16777216L)) {
            c2Var.f12280a.D = v0Var.D;
        }
        if (x(v0Var, 33554432L)) {
            c2Var.f12280a.E = v0Var.E;
        }
        if (x(v0Var, 1048576L)) {
            c2Var.f12280a.z = v0Var.z;
        }
        if (x(v0Var, 268435456L)) {
            c2Var.f12280a.H = v0Var.H;
        }
        if (x(v0Var, 536870912L)) {
            c2Var.f12280a.U = v0Var.U;
        }
        if (x(v0Var, 1073741824L)) {
            c2Var.f12280a.I = v0Var.I;
        }
        if (x(v0Var, 67108864L)) {
            c2Var.f12280a.F = v0Var.F;
        }
        if (x(v0Var, 134217728L)) {
            c2Var.f12280a.G = v0Var.G;
        }
        if (x(v0Var, 8589934592L)) {
            c2Var.f12280a.L = v0Var.L;
        }
        if (x(v0Var, 17179869184L)) {
            c2Var.f12280a.M = v0Var.M;
        }
        if (x(v0Var, 137438953472L)) {
            c2Var.f12280a.W = v0Var.W;
        }
    }

    public final void T(c2 c2Var, b1 b1Var) {
        boolean z = b1Var.f12293b == null;
        v0 v0Var = c2Var.f12280a;
        Float fValueOf = Float.valueOf(1.0f);
        Boolean bool = Boolean.TRUE;
        v0Var.D = bool;
        if (!z) {
            bool = Boolean.FALSE;
        }
        v0Var.f12468y = bool;
        v0Var.z = null;
        v0Var.H = null;
        v0Var.f12464t = fValueOf;
        v0Var.F = x.f12485l;
        v0Var.G = fValueOf;
        v0Var.I = null;
        v0Var.J = null;
        v0Var.K = fValueOf;
        v0Var.L = null;
        v0Var.M = fValueOf;
        v0Var.V = 1;
        v0 v0Var2 = b1Var.f12263e;
        if (v0Var2 != null) {
            S(c2Var, v0Var2);
        }
        ArrayList arrayList = this.f12298b.f12470b.f12373a;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (l lVar : this.f12298b.f12470b.f12373a) {
                if (p.g(lVar.f12361a, b1Var)) {
                    S(c2Var, lVar.f12362b);
                }
            }
        }
        v0 v0Var3 = b1Var.f12264f;
        if (v0Var3 != null) {
            S(c2Var, v0Var3);
        }
    }

    public final void U() {
        int i10;
        v0 v0Var = this.f12299c.f12280a;
        e1 e1Var = v0Var.L;
        if (e1Var instanceof x) {
            i10 = ((x) e1Var).f12487i;
        } else if (!(e1Var instanceof y)) {
            return;
        } else {
            i10 = v0Var.f12465u.f12487i;
        }
        Float f10 = v0Var.M;
        if (f10 != null) {
            i10 = i(f10.floatValue(), i10);
        }
        this.f12297a.drawColor(i10);
    }

    public final boolean V() {
        Boolean bool = this.f12299c.f12280a.E;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public final Path b(a1 a1Var, u uVar) {
        Path pathD;
        b1 b1VarD = a1Var.f12292a.d(this.f12299c.f12280a.H);
        if (b1VarD == null) {
            o("ClipPath reference '%s' not found", this.f12299c.f12280a.H);
            return null;
        }
        w wVar = (w) b1VarD;
        this.f12300d.push(this.f12299c);
        this.f12299c = t(wVar);
        Boolean bool = wVar.f12472o;
        boolean z = bool == null || bool.booleanValue();
        Matrix matrix = new Matrix();
        if (!z) {
            matrix.preTranslate(uVar.f12447a, uVar.f12448b);
            matrix.preScale(uVar.f12449c, uVar.f12450d);
        }
        Matrix matrix2 = wVar.f12291n;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        Path path = new Path();
        for (d1 d1Var : wVar.f12494i) {
            if ((d1Var instanceof a1) && (pathD = D((a1) d1Var, true)) != null) {
                path.op(pathD, Path.Op.UNION);
            }
        }
        if (this.f12299c.f12280a.H != null) {
            if (wVar.f12250h == null) {
                wVar.f12250h = c(path);
            }
            Path pathB = b(wVar, wVar.f12250h);
            if (pathB != null) {
                path.op(pathB, Path.Op.INTERSECT);
            }
        }
        path.transform(matrix);
        this.f12299c = (c2) this.f12300d.pop();
        return path;
    }

    public final float d(o1 o1Var) {
        d2 d2Var = new d2(this);
        n(o1Var, d2Var);
        return d2Var.f12294a;
    }

    public final void f(a1 a1Var, u uVar) {
        Path pathB;
        if (this.f12299c.f12280a.H == null || (pathB = b(a1Var, uVar)) == null) {
            return;
        }
        this.f12297a.clipPath(pathB);
    }

    public final void g(a1 a1Var) {
        e1 e1Var = this.f12299c.f12280a.f12456l;
        if (e1Var instanceof l0) {
            j(true, a1Var.f12250h, (l0) e1Var);
        }
        e1 e1Var2 = this.f12299c.f12280a.f12458n;
        if (e1Var2 instanceof l0) {
            j(false, a1Var.f12250h, (l0) e1Var2);
        }
    }

    public final void j(boolean z, u uVar, l0 l0Var) {
        float fB;
        float f10;
        float fB2;
        float f11;
        float f12;
        float fB3;
        float f13;
        float fB4;
        float f14;
        b1 b1VarD = this.f12298b.d(l0Var.f12364i);
        if (b1VarD == null) {
            o("%s reference '%s' not found", z ? "Fill" : "Stroke", l0Var.f12364i);
            e1 e1Var = l0Var.f12365l;
            if (e1Var != null) {
                N(this.f12299c, z, e1Var);
                return;
            } else if (z) {
                this.f12299c.f12281b = false;
                return;
            } else {
                this.f12299c.f12282c = false;
                return;
            }
        }
        boolean z5 = b1VarD instanceof c1;
        x xVar = x.f12485l;
        if (z5) {
            c1 c1Var = (c1) b1VarD;
            String str = c1Var.f12260l;
            if (str != null) {
                q(c1Var, str);
            }
            Boolean bool = c1Var.f12258i;
            boolean z10 = bool != null && bool.booleanValue();
            c2 c2Var = this.f12299c;
            Paint paint = z ? c2Var.f12283d : c2Var.f12284e;
            if (z10) {
                c2 c2Var2 = this.f12299c;
                f11 = 256.0f;
                u uVar2 = c2Var2.f12286g;
                if (uVar2 == null) {
                    uVar2 = c2Var2.f12285f;
                }
                g0 g0Var = c1Var.f12276m;
                float fD = g0Var != null ? g0Var.d(this) : 0.0f;
                g0 g0Var2 = c1Var.f12277n;
                fB3 = g0Var2 != null ? g0Var2.e(this) : 0.0f;
                f12 = 0.0f;
                g0 g0Var3 = c1Var.f12278o;
                float fD2 = g0Var3 != null ? g0Var3.d(this) : uVar2.f12449c;
                g0 g0Var4 = c1Var.f12279p;
                f14 = fD2;
                fB4 = g0Var4 != null ? g0Var4.e(this) : 0.0f;
                f13 = fD;
            } else {
                f11 = 256.0f;
                f12 = 0.0f;
                g0 g0Var5 = c1Var.f12276m;
                float fB5 = g0Var5 != null ? g0Var5.b(this, 1.0f) : 0.0f;
                g0 g0Var6 = c1Var.f12277n;
                fB3 = g0Var6 != null ? g0Var6.b(this, 1.0f) : 0.0f;
                g0 g0Var7 = c1Var.f12278o;
                float fB6 = g0Var7 != null ? g0Var7.b(this, 1.0f) : 1.0f;
                g0 g0Var8 = c1Var.f12279p;
                f13 = fB5;
                fB4 = g0Var8 != null ? g0Var8.b(this, 1.0f) : 0.0f;
                f14 = fB6;
            }
            float f15 = fB3;
            P();
            this.f12299c = t(c1Var);
            Matrix matrix = new Matrix();
            if (!z10) {
                matrix.preTranslate(uVar.f12447a, uVar.f12448b);
                matrix.preScale(uVar.f12449c, uVar.f12450d);
            }
            Matrix matrix2 = c1Var.f12259j;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            int size = c1Var.f12257h.size();
            if (size == 0) {
                O();
                if (z) {
                    this.f12299c.f12281b = false;
                    return;
                } else {
                    this.f12299c.f12282c = false;
                    return;
                }
            }
            int[] iArr = new int[size];
            float[] fArr = new float[size];
            Iterator it = c1Var.f12257h.iterator();
            int i10 = 0;
            float f16 = -1.0f;
            while (it.hasNext()) {
                u0 u0Var = (u0) ((d1) it.next());
                Float f17 = u0Var.f12451h;
                float fFloatValue = f17 != null ? f17.floatValue() : f12;
                if (i10 == 0 || fFloatValue >= f16) {
                    fArr[i10] = fFloatValue;
                    f16 = fFloatValue;
                } else {
                    fArr[i10] = f16;
                }
                P();
                T(this.f12299c, u0Var);
                v0 v0Var = this.f12299c.f12280a;
                x xVar2 = (x) v0Var.F;
                if (xVar2 == null) {
                    xVar2 = xVar;
                }
                iArr[i10] = i(v0Var.G.floatValue(), xVar2.f12487i);
                i10++;
                O();
            }
            if ((f13 == f14 && f15 == fB4) || size == 1) {
                O();
                paint.setColor(iArr[size - 1]);
                return;
            }
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            int i11 = c1Var.k;
            if (i11 != 0) {
                if (i11 == 2) {
                    tileMode = Shader.TileMode.MIRROR;
                } else if (i11 == 3) {
                    tileMode = Shader.TileMode.REPEAT;
                }
            }
            Shader.TileMode tileMode2 = tileMode;
            O();
            LinearGradient linearGradient = new LinearGradient(f13, f15, f14, fB4, iArr, fArr, tileMode2);
            linearGradient.setLocalMatrix(matrix);
            paint.setShader(linearGradient);
            int iFloatValue = (int) (this.f12299c.f12280a.f12457m.floatValue() * f11);
            paint.setAlpha(iFloatValue < 0 ? 0 : iFloatValue > 255 ? 255 : iFloatValue);
            return;
        }
        if (!(b1VarD instanceof g1)) {
            if (b1VarD instanceof t0) {
                t0 t0Var = (t0) b1VarD;
                if (z) {
                    if (x(t0Var.f12263e, 2147483648L)) {
                        c2 c2Var3 = this.f12299c;
                        v0 v0Var2 = c2Var3.f12280a;
                        e1 e1Var2 = t0Var.f12263e.J;
                        v0Var2.f12456l = e1Var2;
                        c2Var3.f12281b = e1Var2 != null;
                    }
                    if (x(t0Var.f12263e, 4294967296L)) {
                        this.f12299c.f12280a.f12457m = t0Var.f12263e.K;
                    }
                    if (x(t0Var.f12263e, 6442450944L)) {
                        c2 c2Var4 = this.f12299c;
                        N(c2Var4, z, c2Var4.f12280a.f12456l);
                        return;
                    }
                    return;
                }
                if (x(t0Var.f12263e, 2147483648L)) {
                    c2 c2Var5 = this.f12299c;
                    v0 v0Var3 = c2Var5.f12280a;
                    e1 e1Var3 = t0Var.f12263e.J;
                    v0Var3.f12458n = e1Var3;
                    c2Var5.f12282c = e1Var3 != null;
                }
                if (x(t0Var.f12263e, 4294967296L)) {
                    this.f12299c.f12280a.f12459o = t0Var.f12263e.K;
                }
                if (x(t0Var.f12263e, 6442450944L)) {
                    c2 c2Var6 = this.f12299c;
                    N(c2Var6, z, c2Var6.f12280a.f12458n);
                    return;
                }
                return;
            }
            return;
        }
        g1 g1Var = (g1) b1VarD;
        String str2 = g1Var.f12260l;
        if (str2 != null) {
            q(g1Var, str2);
        }
        Boolean bool2 = g1Var.f12258i;
        boolean z11 = bool2 != null && bool2.booleanValue();
        c2 c2Var7 = this.f12299c;
        Paint paint2 = z ? c2Var7.f12283d : c2Var7.f12284e;
        if (z11) {
            g0 g0Var9 = new g0(50.0f, 9);
            g0 g0Var10 = g1Var.f12319m;
            float fD3 = g0Var10 != null ? g0Var10.d(this) : g0Var9.d(this);
            g0 g0Var11 = g1Var.f12320n;
            fB = g0Var11 != null ? g0Var11.e(this) : g0Var9.e(this);
            g0 g0Var12 = g1Var.f12321o;
            fB2 = g0Var12 != null ? g0Var12.a(this) : g0Var9.a(this);
            f10 = fD3;
        } else {
            g0 g0Var13 = g1Var.f12319m;
            float fB7 = g0Var13 != null ? g0Var13.b(this, 1.0f) : 0.5f;
            g0 g0Var14 = g1Var.f12320n;
            fB = g0Var14 != null ? g0Var14.b(this, 1.0f) : 0.5f;
            g0 g0Var15 = g1Var.f12321o;
            f10 = fB7;
            fB2 = g0Var15 != null ? g0Var15.b(this, 1.0f) : 0.5f;
        }
        float f18 = fB;
        P();
        this.f12299c = t(g1Var);
        Matrix matrix3 = new Matrix();
        if (!z11) {
            matrix3.preTranslate(uVar.f12447a, uVar.f12448b);
            matrix3.preScale(uVar.f12449c, uVar.f12450d);
        }
        Matrix matrix4 = g1Var.f12259j;
        if (matrix4 != null) {
            matrix3.preConcat(matrix4);
        }
        int size2 = g1Var.f12257h.size();
        if (size2 == 0) {
            O();
            if (z) {
                this.f12299c.f12281b = false;
                return;
            } else {
                this.f12299c.f12282c = false;
                return;
            }
        }
        int[] iArr2 = new int[size2];
        float[] fArr2 = new float[size2];
        Iterator it2 = g1Var.f12257h.iterator();
        int i12 = 0;
        float f19 = -1.0f;
        while (it2.hasNext()) {
            u0 u0Var2 = (u0) ((d1) it2.next());
            Float f20 = u0Var2.f12451h;
            float fFloatValue2 = f20 != null ? f20.floatValue() : 0.0f;
            if (i12 == 0 || fFloatValue2 >= f19) {
                fArr2[i12] = fFloatValue2;
                f19 = fFloatValue2;
            } else {
                fArr2[i12] = f19;
            }
            P();
            T(this.f12299c, u0Var2);
            v0 v0Var4 = this.f12299c.f12280a;
            x xVar3 = (x) v0Var4.F;
            if (xVar3 == null) {
                xVar3 = xVar;
            }
            iArr2[i12] = i(v0Var4.G.floatValue(), xVar3.f12487i);
            i12++;
            O();
        }
        if (fB2 == 0.0f || size2 == 1) {
            O();
            paint2.setColor(iArr2[size2 - 1]);
            return;
        }
        Shader.TileMode tileMode3 = Shader.TileMode.CLAMP;
        int i13 = g1Var.k;
        if (i13 != 0) {
            if (i13 == 2) {
                tileMode3 = Shader.TileMode.MIRROR;
            } else if (i13 == 3) {
                tileMode3 = Shader.TileMode.REPEAT;
            }
        }
        Shader.TileMode tileMode4 = tileMode3;
        O();
        RadialGradient radialGradient = new RadialGradient(f10, f18, fB2, iArr2, fArr2, tileMode4);
        radialGradient.setLocalMatrix(matrix3);
        paint2.setShader(radialGradient);
        int iFloatValue2 = (int) (this.f12299c.f12280a.f12457m.floatValue() * 256.0f);
        if (iFloatValue2 < 0) {
            iFloatValue2 = 0;
        } else if (iFloatValue2 > 255) {
            iFloatValue2 = 255;
        }
        paint2.setAlpha(iFloatValue2);
    }

    public final boolean k() {
        Boolean bool = this.f12299c.f12280a.D;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0171  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(com.caverock.androidsvg.a1 r23, android.graphics.Path r24) {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.e2.l(com.caverock.androidsvg.a1, android.graphics.Path):void");
    }

    public final void m(Path path) {
        Canvas canvas = this.f12297a;
        c2 c2Var = this.f12299c;
        if (c2Var.f12280a.V != 2) {
            canvas.drawPath(path, c2Var.f12284e);
            return;
        }
        Matrix matrix = canvas.getMatrix();
        Path path2 = new Path();
        path.transform(matrix, path2);
        canvas.setMatrix(new Matrix());
        Shader shader = this.f12299c.f12284e.getShader();
        Matrix matrix2 = new Matrix();
        if (shader != null) {
            shader.getLocalMatrix(matrix2);
            Matrix matrix3 = new Matrix(matrix2);
            matrix3.postConcat(matrix);
            shader.setLocalMatrix(matrix3);
        }
        canvas.drawPath(path2, this.f12299c.f12284e);
        canvas.setMatrix(matrix);
        if (shader != null) {
            shader.setLocalMatrix(matrix2);
        }
    }

    public final void n(o1 o1Var, m2.f0 f0Var) {
        float f10;
        float fE;
        float fD;
        int iV;
        if (k()) {
            Iterator it = o1Var.f12494i.iterator();
            boolean z = true;
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                if (d1Var instanceof s1) {
                    f0Var.G(Q(((s1) d1Var).f12437c, z, !it.hasNext()));
                } else if (f0Var.l((o1) d1Var)) {
                    if (d1Var instanceof p1) {
                        P();
                        p1 p1Var = (p1) d1Var;
                        T(this.f12299c, p1Var);
                        if (k() && V()) {
                            b1 b1VarD = p1Var.f12292a.d(p1Var.f12407n);
                            if (b1VarD == null) {
                                o("TextPath reference '%s' not found", p1Var.f12407n);
                            } else {
                                m0 m0Var = (m0) b1VarD;
                                y1 y1Var = new y1(m0Var.f12374o);
                                Matrix matrix = m0Var.f12275n;
                                Path path = y1Var.f12498a;
                                if (matrix != null) {
                                    path.transform(matrix);
                                }
                                PathMeasure pathMeasure = new PathMeasure(path, false);
                                g0 g0Var = p1Var.f12408o;
                                fE = g0Var != null ? g0Var.b(this, pathMeasure.getLength()) : 0.0f;
                                int iV2 = v();
                                if (iV2 != 1) {
                                    float fD2 = d(p1Var);
                                    if (iV2 == 2) {
                                        fD2 /= 2.0f;
                                    }
                                    fE -= fD2;
                                }
                                g((a1) p1Var.f12409p);
                                boolean zF = F();
                                n(p1Var, new z1(this, path, fE));
                                if (zF) {
                                    E(p1Var.f12250h);
                                }
                            }
                        }
                        O();
                    } else if (d1Var instanceof l1) {
                        P();
                        l1 l1Var = (l1) d1Var;
                        T(this.f12299c, l1Var);
                        if (k()) {
                            ArrayList arrayList = l1Var.f12413n;
                            boolean z5 = arrayList != null && arrayList.size() > 0;
                            boolean z10 = f0Var instanceof a2;
                            if (z10) {
                                float fD3 = !z5 ? ((a2) f0Var).f12251a : ((g0) l1Var.f12413n.get(0)).d(this);
                                ArrayList arrayList2 = l1Var.f12414o;
                                fE = (arrayList2 == null || arrayList2.size() == 0) ? ((a2) f0Var).f12252b : ((g0) l1Var.f12414o.get(0)).e(this);
                                ArrayList arrayList3 = l1Var.f12415p;
                                fD = (arrayList3 == null || arrayList3.size() == 0) ? 0.0f : ((g0) l1Var.f12415p.get(0)).d(this);
                                ArrayList arrayList4 = l1Var.f12416q;
                                if (arrayList4 != null && arrayList4.size() != 0) {
                                    fE = ((g0) l1Var.f12416q.get(0)).e(this);
                                }
                                float f11 = fD3;
                                f10 = fE;
                                fE = f11;
                            } else {
                                f10 = 0.0f;
                                fE = 0.0f;
                                fD = 0.0f;
                            }
                            if (z5 && (iV = v()) != 1) {
                                float fD4 = d(l1Var);
                                if (iV == 2) {
                                    fD4 /= 2.0f;
                                }
                                fE -= fD4;
                            }
                            g((a1) l1Var.f12366r);
                            if (z10) {
                                a2 a2Var = (a2) f0Var;
                                a2Var.f12251a = fE + fD;
                                a2Var.f12252b = fE + f10;
                            }
                            boolean zF2 = F();
                            n(l1Var, f0Var);
                            if (zF2) {
                                E(l1Var.f12250h);
                            }
                        }
                        O();
                    } else if (d1Var instanceof k1) {
                        P();
                        k1 k1Var = (k1) d1Var;
                        T(this.f12299c, k1Var);
                        if (k()) {
                            g((a1) k1Var.f12354o);
                            b1 b1VarD2 = d1Var.f12292a.d(k1Var.f12353n);
                            if (b1VarD2 == null || !(b1VarD2 instanceof o1)) {
                                o("Tref reference '%s' not found", k1Var.f12353n);
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                p((o1) b1VarD2, sb2);
                                if (sb2.length() > 0) {
                                    f0Var.G(sb2.toString());
                                }
                            }
                        }
                        O();
                    }
                }
                z = false;
            }
        }
    }

    public final void p(o1 o1Var, StringBuilder sb2) {
        Iterator it = o1Var.f12494i.iterator();
        boolean z = true;
        while (it.hasNext()) {
            d1 d1Var = (d1) it.next();
            if (d1Var instanceof o1) {
                p((o1) d1Var, sb2);
            } else if (d1Var instanceof s1) {
                sb2.append(Q(((s1) d1Var).f12437c, z, !it.hasNext()));
            }
            z = false;
        }
    }

    public final c2 t(b1 b1Var) {
        c2 c2Var = new c2();
        S(c2Var, v0.a());
        u(b1Var, c2Var);
        return c2Var;
    }

    public final void u(d1 d1Var, c2 c2Var) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (d1Var instanceof b1) {
                arrayList.add(0, (b1) d1Var);
            }
            Object obj = d1Var.f12293b;
            if (obj == null) {
                break;
            } else {
                d1Var = (d1) obj;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            T(c2Var, (b1) it.next());
        }
        c2 c2Var2 = this.f12299c;
        c2Var.f12286g = c2Var2.f12286g;
        c2Var.f12285f = c2Var2.f12285f;
    }

    public final int v() {
        int i10;
        v0 v0Var = this.f12299c.f12280a;
        return (v0Var.S == 1 || (i10 = v0Var.T) == 2) ? v0Var.T : i10 == 1 ? 3 : 1;
    }

    public final Path.FillType w() {
        int i10 = this.f12299c.f12280a.U;
        return (i10 == 0 || i10 != 2) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
    }

    public final Path y(v vVar) {
        g0 g0Var = vVar.f12452o;
        float fD = g0Var != null ? g0Var.d(this) : 0.0f;
        g0 g0Var2 = vVar.f12453p;
        float fE = g0Var2 != null ? g0Var2.e(this) : 0.0f;
        float fA = vVar.f12454q.a(this);
        float f10 = fD - fA;
        float f11 = fE - fA;
        float f12 = fD + fA;
        float f13 = fE + fA;
        if (vVar.f12250h == null) {
            float f14 = 2.0f * fA;
            vVar.f12250h = new u(f10, f11, f14, f14);
        }
        float f15 = fA * 0.5522848f;
        Path path = new Path();
        path.moveTo(fD, f11);
        float f16 = fD + f15;
        float f17 = fE - f15;
        path.cubicTo(f16, f11, f12, f17, f12, fE);
        float f18 = fE + f15;
        path.cubicTo(f12, f18, f16, f13, fD, f13);
        float f19 = fD - f15;
        path.cubicTo(f19, f13, f10, f18, f10, fE);
        path.cubicTo(f10, f17, f19, f11, fD, f11);
        path.close();
        return path;
    }

    public final Path z(a0 a0Var) {
        g0 g0Var = a0Var.f12246o;
        float fD = g0Var != null ? g0Var.d(this) : 0.0f;
        g0 g0Var2 = a0Var.f12247p;
        float fE = g0Var2 != null ? g0Var2.e(this) : 0.0f;
        float fD2 = a0Var.f12248q.d(this);
        float fE2 = a0Var.f12249r.e(this);
        float f10 = fD - fD2;
        float f11 = fE - fE2;
        float f12 = fD + fD2;
        float f13 = fE + fE2;
        if (a0Var.f12250h == null) {
            a0Var.f12250h = new u(f10, f11, fD2 * 2.0f, 2.0f * fE2);
        }
        float f14 = fD2 * 0.5522848f;
        float f15 = fE2 * 0.5522848f;
        Path path = new Path();
        path.moveTo(fD, f11);
        float f16 = fD + f14;
        float f17 = fE - f15;
        path.cubicTo(f16, f11, f12, f17, f12, fE);
        float f18 = fE + f15;
        path.cubicTo(f12, f18, f16, f13, fD, f13);
        float f19 = fD - f14;
        path.cubicTo(f19, f13, f10, f18, f10, fE);
        path.cubicTo(f10, f17, f19, f11, fD, f11);
        path.close();
        return path;
    }
}
