package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class s4 extends m4 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient u4 f13601n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient t4 f13602o;

    public s4(u4 u4Var, t4 t4Var) {
        this.f13601n = u4Var;
        this.f13602o = t4Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f13601n.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.cast.m4, com.google.android.gms.internal.cast.g4
    public final j4 d() {
        return this.f13602o;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int f(Object[] objArr) {
        return this.f13602o.f(objArr);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.f13602o.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13601n.f13627p;
    }
}
