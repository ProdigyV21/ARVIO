package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public class o extends m implements List {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ q f14075p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(q qVar, Object obj, List list, m mVar) {
        super(qVar, obj, list, mVar);
        this.f14075p = qVar;
    }

    @Override // java.util.List
    public final void add(int i10, Object obj) {
        b();
        boolean zIsEmpty = this.f14052l.isEmpty();
        ((List) this.f14052l).add(i10, obj);
        this.f14075p.f14085p++;
        if (zIsEmpty) {
            a();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i10, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zAddAll = ((List) this.f14052l).addAll(i10, collection);
        if (zAddAll) {
            this.f14075p.f14085p += this.f14052l.size() - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }

    @Override // java.util.List
    public final Object get(int i10) {
        b();
        return ((List) this.f14052l).get(i10);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        b();
        return ((List) this.f14052l).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        b();
        return ((List) this.f14052l).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        b();
        return new n(this);
    }

    @Override // java.util.List
    public final Object remove(int i10) {
        b();
        Object objRemove = ((List) this.f14052l).remove(i10);
        q qVar = this.f14075p;
        qVar.f14085p--;
        c();
        return objRemove;
    }

    @Override // java.util.List
    public final Object set(int i10, Object obj) {
        b();
        return ((List) this.f14052l).set(i10, obj);
    }

    @Override // java.util.List
    public final List subList(int i10, int i11) {
        b();
        List listSubList = ((List) this.f14052l).subList(i10, i11);
        m mVar = this.f14053m;
        if (mVar == null) {
            mVar = this;
        }
        boolean z = listSubList instanceof RandomAccess;
        q qVar = this.f14075p;
        Object obj = this.f14051i;
        return z ? new j(qVar, obj, listSubList, mVar) : new o(qVar, obj, listSubList, mVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i10) {
        b();
        return new n(this, i10);
    }
}
