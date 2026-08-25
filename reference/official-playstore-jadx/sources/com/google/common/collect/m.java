package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class m extends AbstractCollection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f14051i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Collection f14052l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final m f14053m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Collection f14054n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ q f14055o;

    public m(q qVar, Object obj, Collection collection, m mVar) {
        this.f14055o = qVar;
        this.f14051i = obj;
        this.f14052l = collection;
        this.f14053m = mVar;
        this.f14054n = mVar == null ? null : mVar.f14052l;
    }

    public final void a() {
        m mVar = this.f14053m;
        if (mVar != null) {
            mVar.a();
        } else {
            this.f14055o.f14084o.put(this.f14051i, this.f14052l);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        b();
        boolean zIsEmpty = this.f14052l.isEmpty();
        boolean zAdd = this.f14052l.add(obj);
        if (zAdd) {
            this.f14055o.f14085p++;
            if (zIsEmpty) {
                a();
            }
        }
        return zAdd;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zAddAll = this.f14052l.addAll(collection);
        if (zAddAll) {
            this.f14055o.f14085p += this.f14052l.size() - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }

    public final void b() {
        Collection collection;
        m mVar = this.f14053m;
        if (mVar != null) {
            mVar.b();
            if (mVar.f14052l != this.f14054n) {
                throw new ConcurrentModificationException();
            }
        } else {
            if (!this.f14052l.isEmpty() || (collection = (Collection) this.f14055o.f14084o.get(this.f14051i)) == null) {
                return;
            }
            this.f14052l = collection;
        }
    }

    public final void c() {
        m mVar = this.f14053m;
        if (mVar != null) {
            mVar.c();
        } else if (this.f14052l.isEmpty()) {
            this.f14055o.f14084o.remove(this.f14051i);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.f14052l.clear();
        this.f14055o.f14085p -= size;
        c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        b();
        return this.f14052l.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        b();
        return this.f14052l.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        b();
        return this.f14052l.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        b();
        return this.f14052l.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        b();
        return new e(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        b();
        boolean zRemove = this.f14052l.remove(obj);
        if (zRemove) {
            q qVar = this.f14055o;
            qVar.f14085p--;
            c();
        }
        return zRemove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zRemoveAll = this.f14052l.removeAll(collection);
        if (zRemoveAll) {
            this.f14055o.f14085p += this.f14052l.size() - size;
            c();
        }
        return zRemoveAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean zRetainAll = this.f14052l.retainAll(collection);
        if (zRetainAll) {
            this.f14055o.f14085p += this.f14052l.size() - size;
            c();
        }
        return zRetainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        b();
        return this.f14052l.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        b();
        return this.f14052l.toString();
    }
}
