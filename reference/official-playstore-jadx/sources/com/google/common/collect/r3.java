package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class r3 extends w1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient l1 f14100n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient s3 f14101o;

    public r3(l1 l1Var, s3 s3Var) {
        this.f14100n = l1Var;
        this.f14101o = s3Var;
    }

    @Override // com.google.common.collect.w1, com.google.common.collect.c1
    public final h1 a() {
        return this.f14101o;
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        return this.f14101o.b(i10, objArr);
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f14100n.get(obj) != null;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return this.f14101o.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14100n.size();
    }
}
