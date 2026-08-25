package com.google.android.gms.internal.cast;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class t4 extends j4 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Object[] f13615m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13616n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient int f13617o;

    public t4(Object[] objArr, int i10, int i11) {
        this.f13615m = objArr;
        this.f13616n = i10;
        this.f13617o = i11;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final boolean e() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        u0.r(i10, this.f13617o);
        Object obj = this.f13615m[i10 + i10 + this.f13616n];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13617o;
    }
}
