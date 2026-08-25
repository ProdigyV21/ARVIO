package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class p1 extends c1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final transient i1 f14082l;

    public p1(i1 i1Var) {
        this.f14082l = i1Var;
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        o4 it = this.f14082l.f14025o.values().iterator();
        while (it.hasNext()) {
            i10 = ((c1) it.next()).b(i10, objArr);
        }
        return i10;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f14082l.c(obj);
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        i1 i1Var = this.f14082l;
        i1Var.getClass();
        return new n1(i1Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f14082l.f14026p;
    }
}
