package com.google.common.collect;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class s3 extends h1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f14107m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f14108n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient int f14109o;

    public s3(Object[] objArr, int i10, int i11) {
        this.f14107m = objArr;
        this.f14108n = i10;
        this.f14109o = i11;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        ac.b.n(i10, this.f14109o);
        Object obj = this.f14107m[(i10 * 2) + this.f14108n];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14109o;
    }
}
