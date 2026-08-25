package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class b2 extends m2.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f12267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f12268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ e2 f12269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f12270e;

    public b2(e2 e2Var, float f10, float f11) {
        this.f12266a = 1;
        this.f12269d = e2Var;
        this.f12270e = new RectF();
        this.f12267b = f10;
        this.f12268c = f11;
    }

    @Override // m2.f0
    public final void G(String str) {
        String str2;
        switch (this.f12266a) {
            case 0:
                e2 e2Var = this.f12269d;
                if (e2Var.V()) {
                    Path path = new Path();
                    str2 = str;
                    e2Var.f12299c.f12283d.getTextPath(str2, 0, str.length(), this.f12267b, this.f12268c, path);
                    ((Path) this.f12270e).addPath(path);
                } else {
                    str2 = str;
                }
                this.f12267b = e2Var.f12299c.f12283d.measureText(str2) + this.f12267b;
                break;
            default:
                e2 e2Var2 = this.f12269d;
                if (e2Var2.V()) {
                    Rect rect = new Rect();
                    e2Var2.f12299c.f12283d.getTextBounds(str, 0, str.length(), rect);
                    RectF rectF = new RectF(rect);
                    rectF.offset(this.f12267b, this.f12268c);
                    ((RectF) this.f12270e).union(rectF);
                }
                this.f12267b = e2Var2.f12299c.f12283d.measureText(str) + this.f12267b;
                break;
        }
    }

    @Override // m2.f0
    public final boolean l(o1 o1Var) {
        switch (this.f12266a) {
            case 0:
                if (o1Var instanceof p1) {
                    Log.w("SVGAndroidRenderer", "Using <textPath> elements in a clip path is not supported.");
                }
                break;
            default:
                if (o1Var instanceof p1) {
                    p1 p1Var = (p1) o1Var;
                    b1 b1VarD = o1Var.f12292a.d(p1Var.f12407n);
                    if (b1VarD != null) {
                        m0 m0Var = (m0) b1VarD;
                        y1 y1Var = new y1(m0Var.f12374o);
                        Matrix matrix = m0Var.f12275n;
                        Path path = y1Var.f12498a;
                        if (matrix != null) {
                            path.transform(matrix);
                        }
                        RectF rectF = new RectF();
                        path.computeBounds(rectF, true);
                        ((RectF) this.f12270e).union(rectF);
                    } else {
                        e2.o("TextPath path reference '%s' not found", p1Var.f12407n);
                    }
                }
                break;
        }
        return true;
    }

    public b2(e2 e2Var, float f10, float f11, Path path) {
        this.f12266a = 0;
        this.f12269d = e2Var;
        this.f12267b = f10;
        this.f12268c = f11;
        this.f12270e = path;
    }
}
