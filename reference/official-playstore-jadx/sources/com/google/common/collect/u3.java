package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class u3 extends y1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v3 f14122n;

    public u3(v3 v3Var) {
        this.f14122n = v3Var;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f14122n.contains(obj);
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // com.google.common.collect.y1
    public final Object get(int i10) {
        f3 f3Var = this.f14122n.f14128o;
        ac.b.n(i10, f3Var.f14000c);
        return f3Var.f13998a[i10];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14122n.f14128o.f14000c;
    }
}
