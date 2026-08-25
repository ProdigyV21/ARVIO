package com.google.common.collect;

import com.google.common.collect.b3;

/* JADX INFO: loaded from: classes4.dex */
public final class s1 extends y1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t1 f14104n;

    public s1(t1 t1Var) {
        this.f14104n = t1Var;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof b3.a)) {
            return false;
        }
        b3.a aVar = (b3.a) obj;
        if (aVar.getCount() <= 0) {
            return false;
        }
        return ((v3) this.f14104n).f14128o.b(aVar.a()) == aVar.getCount();
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        this.f14104n.getClass();
        return false;
    }

    @Override // com.google.common.collect.y1
    public final Object get(int i10) {
        f3 f3Var = ((v3) this.f14104n).f14128o;
        ac.b.n(i10, f3Var.f14000c);
        return new e3(f3Var, i10);
    }

    @Override // com.google.common.collect.w1, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f14104n.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        v3 v3Var = (v3) this.f14104n;
        u3 u3Var = v3Var.f14130q;
        if (u3Var == null) {
            u3Var = new u3(v3Var);
            v3Var.f14130q = u3Var;
        }
        return u3Var.size();
    }
}
