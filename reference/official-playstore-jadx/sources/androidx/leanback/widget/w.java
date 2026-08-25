package androidx.leanback.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f3153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a0 f3154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f3157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final androidx.recyclerview.widget.j1 f3158g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3159h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinearInterpolator f3160i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final DecelerateInterpolator f3161j;
    public final DisplayMetrics k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3162l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f3163m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3164n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3165o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3166p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ a0 f3167q;

    public w(a0 a0Var) {
        this.f3167q = a0Var;
        Context context = a0Var.f3068r.getContext();
        this.f3152a = -1;
        androidx.recyclerview.widget.j1 j1Var = new androidx.recyclerview.widget.j1();
        j1Var.f4956d = -1;
        j1Var.f4958f = false;
        j1Var.f4959g = 0;
        j1Var.f4953a = 0;
        j1Var.f4954b = 0;
        j1Var.f4955c = Integer.MIN_VALUE;
        j1Var.f4957e = null;
        this.f3158g = j1Var;
        this.f3160i = new LinearInterpolator();
        this.f3161j = new DecelerateInterpolator();
        this.f3162l = false;
        this.f3164n = 0;
        this.f3165o = 0;
        this.k = context.getResources().getDisplayMetrics();
    }

    public final int a(int i10) {
        float fAbs = Math.abs(i10);
        boolean z = this.f3162l;
        a0 a0Var = this.f3167q;
        if (!z) {
            this.f3163m = (25.0f / this.k.densityDpi) * a0Var.f3066p;
            this.f3162l = true;
        }
        int iCeil = (int) Math.ceil(fAbs * this.f3163m);
        int i11 = ((t1) a0Var.Y.f2602n).f3142i;
        if (i11 > 0) {
            float f10 = (30.0f / i11) * i10;
            if (iCeil < f10) {
                return (int) f10;
            }
        }
        return iCeil;
    }

    public abstract PointF b(int i10);

    /* JADX WARN: Removed duplicated region for block: B:57:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.w.c(int, int):void");
    }

    public void d() {
        View viewR = this.f3153b.x.r(this.f3152a);
        a0 a0Var = this.f3167q;
        if (viewR == null) {
            int i10 = this.f3152a;
            if (i10 >= 0) {
                a0Var.n1(i10, false);
                return;
            }
            return;
        }
        int i11 = a0Var.F;
        int i12 = this.f3152a;
        if (i11 != i12) {
            a0Var.F = i12;
        }
        if (a0Var.P()) {
            a0Var.B |= 32;
            viewR.requestFocus();
            a0Var.B &= -33;
        }
        a0Var.N0();
        a0Var.O0();
    }

    public final void e() {
        if (this.f3156e) {
            this.f3156e = false;
            this.f3165o = 0;
            this.f3164n = 0;
            if (!this.f3166p) {
                d();
            }
            a0 a0Var = this.f3167q;
            if (a0Var.G == this) {
                a0Var.G = null;
            }
            if (a0Var.H == this) {
                a0Var.H = null;
            }
            this.f3153b.f4846q0.f4973a = -1;
            this.f3157f = null;
            this.f3152a = -1;
            this.f3155d = false;
            a0 a0Var2 = this.f3154c;
            if (a0Var2.f5117e == this) {
                a0Var2.f5117e = null;
            }
            this.f3154c = null;
            this.f3153b = null;
        }
    }
}
