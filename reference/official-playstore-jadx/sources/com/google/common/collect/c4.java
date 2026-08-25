package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public final class c4 extends b4 implements SortedSet {
    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return ((SortedSet) this.f13942i).comparator();
    }

    @Override // java.util.SortedSet
    public final Object first() {
        Iterator it = this.f13942i.iterator();
        it.getClass();
        com.google.common.base.r rVar = this.f13943l;
        rVar.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            if (rVar.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return new c4(((SortedSet) this.f13942i).headSet(obj), this.f13943l);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        SortedSet sortedSetHeadSet = (SortedSet) this.f13942i;
        while (true) {
            Object objLast = sortedSetHeadSet.last();
            if (this.f13943l.apply(objLast)) {
                return objLast;
            }
            sortedSetHeadSet = sortedSetHeadSet.headSet(objLast);
        }
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return new c4(((SortedSet) this.f13942i).subSet(obj, obj2), this.f13943l);
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return new c4(((SortedSet) this.f13942i).tailSet(obj), this.f13943l);
    }
}
