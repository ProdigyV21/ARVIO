package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class d2 extends m2.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f12294a = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e2 f12295b;

    public d2(e2 e2Var) {
        this.f12295b = e2Var;
    }

    @Override // m2.f0
    public final void G(String str) {
        this.f12294a = this.f12295b.f12299c.f12283d.measureText(str) + this.f12294a;
    }
}
