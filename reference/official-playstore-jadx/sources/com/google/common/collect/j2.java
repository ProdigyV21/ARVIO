package com.google.common.collect;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class j2 extends AbstractSequentialList implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f14032i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final com.google.common.base.m f14033l;

    public j2(List list, com.google.common.base.m mVar) {
        list.getClass();
        this.f14032i = list;
        this.f14033l = mVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f14032i.isEmpty();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        return new h2(this, this.f14032i.listIterator(i10), 1);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i10, int i11) {
        this.f14032i.subList(i10, i11).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14032i.size();
    }
}
