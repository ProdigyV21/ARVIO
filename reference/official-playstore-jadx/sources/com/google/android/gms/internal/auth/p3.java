package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class p3 extends AbstractList implements RandomAccess, k2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j2 f13092i;

    public p3(j2 j2Var) {
        this.f13092i = j2Var;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i10) {
        return this.f13092i.get(i10);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new o3(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        return new n3(this, i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13092i.f13051l.size();
    }

    @Override // com.google.android.gms.internal.auth.k2
    public final k2 zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.auth.k2
    public final List zzg() {
        return Collections.unmodifiableList(this.f13092i.f13051l);
    }
}
