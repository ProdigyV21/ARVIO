package com.google.common.collect;

import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class g1 extends h1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient int f14011m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f14012n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ h1 f14013o;

    public g1(h1 h1Var, int i10, int i11) {
        this.f14013o = h1Var;
        this.f14011m = i10;
        this.f14012n = i11;
    }

    @Override // com.google.common.collect.c1
    public final Object[] c() {
        return this.f14013o.c();
    }

    @Override // com.google.common.collect.c1
    public final int d() {
        return this.f14013o.e() + this.f14011m + this.f14012n;
    }

    @Override // com.google.common.collect.c1
    public final int e() {
        return this.f14013o.e() + this.f14011m;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        ac.b.n(i10, this.f14012n);
        return this.f14013o.get(i10 + this.f14011m);
    }

    @Override // com.google.common.collect.h1, com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // com.google.common.collect.h1, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14012n;
    }

    @Override // com.google.common.collect.h1, java.util.List
    /* JADX INFO: renamed from: v */
    public final h1 subList(int i10, int i11) {
        ac.b.r(i10, i11, this.f14012n);
        int i12 = this.f14011m;
        return this.f14013o.subList(i10 + i12, i11 + i12);
    }

    @Override // com.google.common.collect.h1, java.util.List
    public final /* bridge */ /* synthetic */ ListIterator listIterator(int i10) {
        return listIterator(i10);
    }
}
