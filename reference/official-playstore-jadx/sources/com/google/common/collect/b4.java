package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class b4 extends AbstractCollection implements Set {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Set f13942i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final com.google.common.base.r f13943l;

    public b4(Set set, com.google.common.base.r rVar) {
        this.f13942i = set;
        this.f13943l = rVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        ac.b.j(this.f13943l.apply(obj));
        return this.f13942i.add(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            ac.b.j(this.f13943l.apply(it.next()));
        }
        return this.f13942i.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Set set = this.f13942i;
        boolean z = set instanceof RandomAccess;
        com.google.common.base.r rVar = this.f13943l;
        if (!z || !(set instanceof List)) {
            Iterator it = set.iterator();
            rVar.getClass();
            while (it.hasNext()) {
                if (rVar.apply(it.next())) {
                    it.remove();
                }
            }
            return;
        }
        List list = (List) set;
        rVar.getClass();
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            Object obj = list.get(i11);
            if (!rVar.apply(obj)) {
                if (i11 > i10) {
                    try {
                        list.set(i10, obj);
                    } catch (IllegalArgumentException unused) {
                        a0.s(list, rVar, i10, i11);
                        return;
                    } catch (UnsupportedOperationException unused2) {
                        a0.s(list, rVar, i10, i11);
                        return;
                    }
                }
                i10++;
            }
        }
        list.subList(i10, list.size()).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        boolean zContains;
        Set set = this.f13942i;
        set.getClass();
        try {
            zContains = set.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            zContains = false;
        }
        if (zContains) {
            return this.f13943l.apply(obj);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return a0.g(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return a0.l(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        Iterator it = this.f13942i.iterator();
        com.google.common.base.r rVar = this.f13943l;
        ac.b.p(rVar, "predicate");
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (rVar.apply(it.next())) {
                break;
            }
            i10++;
        }
        return true ^ (i10 != -1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        Iterator it = this.f13942i.iterator();
        it.getClass();
        com.google.common.base.r rVar = this.f13943l;
        rVar.getClass();
        return new a2(it, rVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        return contains(obj) && this.f13942i.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        Iterator it = this.f13942i.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.f13943l.apply(next) && collection.contains(next)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        Iterator it = this.f13942i.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.f13943l.apply(next) && !collection.contains(next)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Iterator it = this.f13942i.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            if (this.f13943l.apply(it.next())) {
                i10++;
            }
        }
        return i10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray() {
        Iterator it = iterator();
        ArrayList arrayList = new ArrayList();
        while (true) {
            a2 a2Var = (a2) it;
            if (!a2Var.hasNext()) {
                return arrayList.toArray();
            }
            arrayList.add(a2Var.next());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        Iterator it = iterator();
        ArrayList arrayList = new ArrayList();
        while (true) {
            a2 a2Var = (a2) it;
            if (a2Var.hasNext()) {
                arrayList.add(a2Var.next());
            } else {
                return arrayList.toArray(objArr);
            }
        }
    }
}
