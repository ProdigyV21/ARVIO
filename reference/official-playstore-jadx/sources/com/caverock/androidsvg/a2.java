package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public class a2 extends m2.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f12251a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f12252b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e2 f12253c;

    public a2(e2 e2Var, float f10, float f11) {
        this.f12253c = e2Var;
        this.f12251a = f10;
        this.f12252b = f11;
    }

    @Override // m2.f0
    public void G(String str) {
        e2 e2Var = this.f12253c;
        if (e2Var.V()) {
            c2 c2Var = e2Var.f12299c;
            if (c2Var.f12281b) {
                e2Var.f12297a.drawText(str, this.f12251a, this.f12252b, c2Var.f12283d);
            }
            c2 c2Var2 = e2Var.f12299c;
            if (c2Var2.f12282c) {
                e2Var.f12297a.drawText(str, this.f12251a, this.f12252b, c2Var2.f12284e);
            }
        }
        this.f12251a = e2Var.f12299c.f12283d.measureText(str) + this.f12251a;
    }
}
