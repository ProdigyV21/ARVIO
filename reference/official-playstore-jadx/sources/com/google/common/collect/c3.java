package com.google.common.collect;

import com.google.common.collect.b3;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c3 implements b3.a {
    public final boolean equals(Object obj) {
        if (!(obj instanceof b3.a)) {
            return false;
        }
        b3.a aVar = (b3.a) obj;
        e3 e3Var = (e3) this;
        return e3Var.getCount() == aVar.getCount() && a.a.L(e3Var.f13979a, aVar.a());
    }

    public final int hashCode() {
        e3 e3Var = (e3) this;
        Object obj = e3Var.f13979a;
        return e3Var.getCount() ^ (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        e3 e3Var = (e3) this;
        String strValueOf = String.valueOf(e3Var.f13979a);
        int count = e3Var.getCount();
        return count == 1 ? strValueOf : androidx.fragment.app.a2.j(count, strValueOf, " x ");
    }
}
