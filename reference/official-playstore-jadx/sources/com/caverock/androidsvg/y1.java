package com.caverock.androidsvg;

import android.graphics.Path;

/* JADX INFO: loaded from: classes4.dex */
public final class y1 implements o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f12498a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f12499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f12500c;

    public y1(n0 n0Var) {
        if (n0Var == null) {
            return;
        }
        n0Var.k(this);
    }

    @Override // com.caverock.androidsvg.o0
    public final void a(float f10, float f11, float f12, float f13) {
        this.f12498a.quadTo(f10, f11, f12, f13);
        this.f12499b = f12;
        this.f12500c = f13;
    }

    @Override // com.caverock.androidsvg.o0
    public final void b(float f10, float f11, float f12, boolean z, boolean z5, float f13, float f14) {
        e2.a(this.f12499b, this.f12500c, f10, f11, f12, z, z5, f13, f14, this);
        this.f12499b = f13;
        this.f12500c = f14;
    }

    @Override // com.caverock.androidsvg.o0
    public final void close() {
        this.f12498a.close();
    }

    @Override // com.caverock.androidsvg.o0
    public final void cubicTo(float f10, float f11, float f12, float f13, float f14, float f15) {
        this.f12498a.cubicTo(f10, f11, f12, f13, f14, f15);
        this.f12499b = f14;
        this.f12500c = f15;
    }

    @Override // com.caverock.androidsvg.o0
    public final void lineTo(float f10, float f11) {
        this.f12498a.lineTo(f10, f11);
        this.f12499b = f10;
        this.f12500c = f11;
    }

    @Override // com.caverock.androidsvg.o0
    public final void moveTo(float f10, float f11) {
        this.f12498a.moveTo(f10, f11);
        this.f12499b = f10;
        this.f12500c = f11;
    }
}
