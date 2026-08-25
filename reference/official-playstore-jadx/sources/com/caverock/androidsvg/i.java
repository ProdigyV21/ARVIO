package com.caverock.androidsvg;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List f12337a;

    @Override // com.caverock.androidsvg.e
    public final boolean a(b1 b1Var) {
        Iterator it = this.f12337a.iterator();
        while (it.hasNext()) {
            if (p.g((n) it.next(), b1Var)) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        return a0.c.q(new StringBuilder("not("), this.f12337a, ")");
    }
}
