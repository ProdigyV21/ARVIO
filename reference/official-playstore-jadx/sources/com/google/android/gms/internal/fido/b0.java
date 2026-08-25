package com.google.android.gms.internal.fido;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class b0 extends q {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b0 f13755o = new b0(new Object[0], 0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f13756m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13757n;

    public b0(Object[] objArr, int i10) {
        this.f13756m = objArr;
        this.f13757n = i10;
    }

    @Override // com.google.android.gms.internal.fido.q, com.google.android.gms.internal.fido.l
    public final int a(Object[] objArr) {
        Object[] objArr2 = this.f13756m;
        int i10 = this.f13757n;
        System.arraycopy(objArr2, 0, objArr, 0, i10);
        return i10;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int b() {
        return this.f13757n;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int c() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final Object[] e() {
        return this.f13756m;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        b.e(i10, this.f13757n);
        Object obj = this.f13756m[i10];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13757n;
    }
}
