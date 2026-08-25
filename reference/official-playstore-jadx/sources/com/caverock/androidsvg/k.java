package com.caverock.androidsvg;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class k implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f12351a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12352b;

    public k(boolean z, String str) {
        this.f12351a = z;
        this.f12352b = str;
    }

    @Override // com.caverock.androidsvg.e
    public final boolean a(b1 b1Var) {
        int i10;
        boolean z = this.f12351a;
        String strN = this.f12352b;
        if (z && strN == null) {
            strN = b1Var.n();
        }
        z0 z0Var = b1Var.f12293b;
        if (z0Var != null) {
            Iterator it = z0Var.getChildren().iterator();
            i10 = 0;
            while (it.hasNext()) {
                b1 b1Var2 = (b1) ((d1) it.next());
                if (strN == null || b1Var2.n().equals(strN)) {
                    i10++;
                }
            }
        } else {
            i10 = 1;
        }
        return i10 == 1;
    }

    public final String toString() {
        return this.f12351a ? a0.c.l("only-of-type <", this.f12352b, ">") : "only-child";
    }
}
