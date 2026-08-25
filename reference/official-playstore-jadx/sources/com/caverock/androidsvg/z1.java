package com.caverock.androidsvg;

import android.graphics.Path;

/* JADX INFO: loaded from: classes4.dex */
public final class z1 extends a2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f12501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e2 f12502e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z1(e2 e2Var, Path path, float f10) {
        super(e2Var, f10, 0.0f);
        this.f12502e = e2Var;
        this.f12501d = path;
    }

    @Override // com.caverock.androidsvg.a2, m2.f0
    public final void G(String str) {
        e2 e2Var = this.f12502e;
        if (e2Var.V()) {
            c2 c2Var = e2Var.f12299c;
            if (c2Var.f12281b) {
                e2Var.f12297a.drawTextOnPath(str, this.f12501d, this.f12251a, this.f12252b, c2Var.f12283d);
            }
            c2 c2Var2 = e2Var.f12299c;
            if (c2Var2.f12282c) {
                e2Var.f12297a.drawTextOnPath(str, this.f12501d, this.f12251a, this.f12252b, c2Var2.f12284e);
            }
        }
        this.f12251a = e2Var.f12299c.f12283d.measureText(str) + this.f12251a;
    }
}
