package com.google.android.gms.internal.cast;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class p4 extends j4 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final p4 f13540o = new p4(new Object[0], 0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f13541m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13542n;

    public p4(Object[] objArr, int i10) {
        this.f13541m = objArr;
        this.f13542n = i10;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final Object[] a() {
        return this.f13541m;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int b() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int c() {
        return this.f13542n;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final boolean e() {
        return false;
    }

    @Override // com.google.android.gms.internal.cast.j4, com.google.android.gms.internal.cast.g4
    public final int f(Object[] objArr) {
        Object[] objArr2 = this.f13541m;
        int i10 = this.f13542n;
        System.arraycopy(objArr2, 0, objArr, 0, i10);
        return i10;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        u0.r(i10, this.f13542n);
        Object obj = this.f13541m[i10];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13542n;
    }
}
