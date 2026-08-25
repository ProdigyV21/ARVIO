package com.google.common.collect;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class o3 extends h1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final o3 f14078o = new o3(new Object[0], 0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f14079m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f14080n;

    public o3(Object[] objArr, int i10) {
        this.f14079m = objArr;
        this.f14080n = i10;
    }

    @Override // com.google.common.collect.h1, com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        Object[] objArr2 = this.f14079m;
        int i11 = this.f14080n;
        System.arraycopy(objArr2, 0, objArr, i10, i11);
        return i10 + i11;
    }

    @Override // com.google.common.collect.c1
    public final Object[] c() {
        return this.f14079m;
    }

    @Override // com.google.common.collect.c1
    public final int d() {
        return this.f14080n;
    }

    @Override // com.google.common.collect.c1
    public final int e() {
        return 0;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return false;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        ac.b.n(i10, this.f14080n);
        Object obj = this.f14079m[i10];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14080n;
    }
}
