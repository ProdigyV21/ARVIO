package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class b0 extends AbstractCollection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Collection f13939i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r2 f13940l;

    public b0(Collection collection, r2 r2Var) {
        collection.getClass();
        this.f13939i = collection;
        this.f13940l = r2Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f13939i.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.f13939i.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        Iterator it = this.f13939i.iterator();
        r2 r2Var = this.f13940l;
        r2Var.getClass();
        return new b2(it, r2Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f13939i.size();
    }
}
