package com.caverock.androidsvg;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class w1 implements o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f12477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f12478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f12479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x1 f12480d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f12481e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f12482f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f12483g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f12484h;

    public w1(e2 e2Var, n0 n0Var) {
        ArrayList arrayList = new ArrayList();
        this.f12477a = arrayList;
        this.f12480d = null;
        this.f12481e = false;
        this.f12482f = true;
        this.f12483g = -1;
        if (n0Var == null) {
            return;
        }
        n0Var.k(this);
        if (this.f12484h) {
            this.f12480d.b((x1) arrayList.get(this.f12483g));
            arrayList.set(this.f12483g, this.f12480d);
            this.f12484h = false;
        }
        x1 x1Var = this.f12480d;
        if (x1Var != null) {
            arrayList.add(x1Var);
        }
    }

    @Override // com.caverock.androidsvg.o0
    public final void a(float f10, float f11, float f12, float f13) {
        this.f12480d.a(f10, f11);
        this.f12477a.add(this.f12480d);
        this.f12480d = new x1(f12, f13, f12 - f10, f13 - f11);
        this.f12484h = false;
    }

    @Override // com.caverock.androidsvg.o0
    public final void b(float f10, float f11, float f12, boolean z, boolean z5, float f13, float f14) {
        this.f12481e = true;
        this.f12482f = false;
        x1 x1Var = this.f12480d;
        e2.a(x1Var.f12488a, x1Var.f12489b, f10, f11, f12, z, z5, f13, f14, this);
        this.f12482f = true;
        this.f12484h = false;
    }

    @Override // com.caverock.androidsvg.o0
    public final void close() {
        this.f12477a.add(this.f12480d);
        lineTo(this.f12478b, this.f12479c);
        this.f12484h = true;
    }

    @Override // com.caverock.androidsvg.o0
    public final void cubicTo(float f10, float f11, float f12, float f13, float f14, float f15) {
        if (this.f12482f || this.f12481e) {
            this.f12480d.a(f10, f11);
            this.f12477a.add(this.f12480d);
            this.f12481e = false;
        }
        this.f12480d = new x1(f14, f15, f14 - f12, f15 - f13);
        this.f12484h = false;
    }

    @Override // com.caverock.androidsvg.o0
    public final void lineTo(float f10, float f11) {
        this.f12480d.a(f10, f11);
        this.f12477a.add(this.f12480d);
        x1 x1Var = this.f12480d;
        this.f12480d = new x1(f10, f11, f10 - x1Var.f12488a, f11 - x1Var.f12489b);
        this.f12484h = false;
    }

    @Override // com.caverock.androidsvg.o0
    public final void moveTo(float f10, float f11) {
        boolean z = this.f12484h;
        ArrayList arrayList = this.f12477a;
        if (z) {
            this.f12480d.b((x1) arrayList.get(this.f12483g));
            arrayList.set(this.f12483g, this.f12480d);
            this.f12484h = false;
        }
        x1 x1Var = this.f12480d;
        if (x1Var != null) {
            arrayList.add(x1Var);
        }
        this.f12478b = f10;
        this.f12479c = f11;
        this.f12480d = new x1(f10, f11, 0.0f, 0.0f);
        this.f12483g = arrayList.size();
    }
}
