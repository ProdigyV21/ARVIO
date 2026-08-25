package com.google.android.gms.internal.fido;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 extends w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final d0 f13769q;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient q f13770p;

    static {
        m mVar = q.f13808l;
        f13769q = new d0(b0.f13755o, z.f13850l);
    }

    public d0(q qVar, Comparator comparator) {
        super(comparator);
        this.f13770p = qVar;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int a(Object[] objArr) {
        return this.f13770p.a(objArr);
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int b() {
        return this.f13770p.b();
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int c() {
        return this.f13770p.c();
    }

    @Override // java.util.NavigableSet
    public final Object ceiling(Object obj) {
        int iR = r(obj, true);
        q qVar = this.f13770p;
        if (iR == qVar.size()) {
            return null;
        }
        return qVar.get(iR);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj != null) {
            try {
                if (Collections.binarySearch(this.f13770p, obj, this.f13831n) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        if (collection instanceof y) {
            collection = ((y) collection).zza();
        }
        Comparator comparator = this.f13831n;
        if (!b.i(comparator, collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        m mVarListIterator = this.f13770p.listIterator(0);
        Iterator it = collection.iterator();
        if (mVarListIterator.hasNext()) {
            Object next = it.next();
            Object next2 = mVarListIterator.next();
            while (true) {
                try {
                    int iCompare = comparator.compare(next2, next);
                    if (iCompare >= 0) {
                        if (iCompare != 0) {
                            break;
                        }
                        if (!it.hasNext()) {
                            return true;
                        }
                        next = it.next();
                    } else {
                        if (!mVarListIterator.hasNext()) {
                            break;
                        }
                        next2 = mVarListIterator.next();
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final h0 d() {
        return this.f13770p.listIterator(0);
    }

    @Override // java.util.NavigableSet
    public final Iterator descendingIterator() {
        return this.f13770p.f().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.l
    public final Object[] e() {
        return this.f13770p.e();
    }

    @Override // com.google.android.gms.internal.fido.s, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            q qVar = this.f13770p;
            if (qVar.size() == set.size()) {
                if (isEmpty()) {
                    return true;
                }
                Comparator comparator = this.f13831n;
                if (!b.i(comparator, set)) {
                    return containsAll(set);
                }
                Iterator it = set.iterator();
                try {
                    m mVarListIterator = qVar.listIterator(0);
                    while (mVarListIterator.hasNext()) {
                        Object next = mVarListIterator.next();
                        Object next2 = it.next();
                        if (next2 == null || comparator.compare(next, next2) != 0) {
                        }
                    }
                    return true;
                } catch (ClassCastException | NoSuchElementException unused) {
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fido.w, java.util.SortedSet
    public final Object first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.f13770p.get(0);
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object obj) {
        int iQ = q(obj, true) - 1;
        if (iQ == -1) {
            return null;
        }
        return this.f13770p.get(iQ);
    }

    @Override // com.google.android.gms.internal.fido.s
    public final q h() {
        return this.f13770p;
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object obj) {
        int iR = r(obj, false);
        q qVar = this.f13770p;
        if (iR == qVar.size()) {
            return null;
        }
        return qVar.get(iR);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public final /* synthetic */ Iterator iterator() {
        return this.f13770p.listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.w, java.util.SortedSet
    public final Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.f13770p.get(r0.size() - 1);
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object obj) {
        int iQ = q(obj, false) - 1;
        if (iQ == -1) {
            return null;
        }
        return this.f13770p.get(iQ);
    }

    public final int q(Object obj, boolean z) {
        obj.getClass();
        int iBinarySearch = Collections.binarySearch(this.f13770p, obj, this.f13831n);
        return iBinarySearch >= 0 ? z ? iBinarySearch + 1 : iBinarySearch : ~iBinarySearch;
    }

    public final int r(Object obj, boolean z) {
        obj.getClass();
        int iBinarySearch = Collections.binarySearch(this.f13770p, obj, this.f13831n);
        return iBinarySearch >= 0 ? z ? iBinarySearch : iBinarySearch + 1 : ~iBinarySearch;
    }

    public final d0 s(int i10, int i11) {
        q qVar = this.f13770p;
        if (i10 == 0) {
            if (i11 == qVar.size()) {
                return this;
            }
            i10 = 0;
        }
        Comparator comparator = this.f13831n;
        return i10 < i11 ? new d0(qVar.subList(i10, i11), comparator) : w.p(comparator);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13770p.size();
    }
}
