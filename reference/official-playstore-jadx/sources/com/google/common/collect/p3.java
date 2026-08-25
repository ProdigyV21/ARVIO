package com.google.common.collect;

import j$.util.Objects;
import java.util.AbstractMap;

/* JADX INFO: loaded from: classes4.dex */
public final class p3 extends h1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q3 f14083m;

    public p3(q3 q3Var) {
        this.f14083m = q3Var;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        q3 q3Var = this.f14083m;
        ac.b.n(i10, q3Var.f14095q);
        Object[] objArr = q3Var.f14093o;
        int i11 = i10 * 2;
        int i12 = q3Var.f14094p;
        Object obj = objArr[i11 + i12];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[i11 + (i12 ^ 1)];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14083m.f14095q;
    }
}
