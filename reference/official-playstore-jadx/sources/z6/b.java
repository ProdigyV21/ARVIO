package z6;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.collections.j;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import qb.l;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends j implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f23211i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f23212l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23213m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f23214n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final c f23215o;

    public b(Object[] objArr, int i10, int i11, b bVar, c cVar) {
        this.f23211i = objArr;
        this.f23212l = i10;
        this.f23213m = i11;
        this.f23214n = bVar;
        this.f23215o = cVar;
        ((AbstractList) this).modCount = ((AbstractList) cVar).modCount;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        e();
        d();
        c(this.f23212l + this.f23213m, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        e();
        d();
        int size = collection.size();
        b(collection, this.f23212l + this.f23213m, size);
        return size > 0;
    }

    public final void b(Collection collection, int i10, int i11) {
        ((AbstractList) this).modCount++;
        c cVar = this.f23215o;
        b bVar = this.f23214n;
        if (bVar != null) {
            bVar.b(collection, i10, i11);
        } else {
            c cVar2 = c.f23216n;
            cVar.b(collection, i10, i11);
        }
        this.f23211i = cVar.f23217i;
        this.f23213m += i11;
    }

    public final void c(int i10, Object obj) {
        ((AbstractList) this).modCount++;
        c cVar = this.f23215o;
        b bVar = this.f23214n;
        if (bVar != null) {
            bVar.c(i10, obj);
        } else {
            c cVar2 = c.f23216n;
            cVar.c(i10, obj);
        }
        this.f23211i = cVar.f23217i;
        this.f23213m++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        e();
        d();
        g(this.f23212l, this.f23213m);
    }

    public final void d() {
        if (((AbstractList) this.f23215o).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void e() {
        if (this.f23215o.f23219m) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        d();
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f23211i;
            int i10 = this.f23213m;
            if (i10 == list.size()) {
                for (int i11 = 0; i11 < i10; i11++) {
                    if (p.a(objArr[this.f23212l + i11], list.get(i11))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final Object f(int i10) {
        Object objF;
        ((AbstractList) this).modCount++;
        b bVar = this.f23214n;
        if (bVar != null) {
            objF = bVar.f(i10);
        } else {
            c cVar = c.f23216n;
            objF = this.f23215o.f(i10);
        }
        this.f23213m--;
        return objF;
    }

    public final void g(int i10, int i11) {
        if (i11 > 0) {
            ((AbstractList) this).modCount++;
        }
        b bVar = this.f23214n;
        if (bVar != null) {
            bVar.g(i10, i11);
        } else {
            c cVar = c.f23216n;
            this.f23215o.g(i10, i11);
        }
        this.f23213m -= i11;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        return this.f23211i[this.f23212l + i10];
    }

    @Override // kotlin.collections.j
    public final int getSize() {
        d();
        return this.f23213m;
    }

    public final int h(int i10, int i11, Collection collection, boolean z) {
        int iH;
        b bVar = this.f23214n;
        if (bVar != null) {
            iH = bVar.h(i10, i11, collection, z);
        } else {
            c cVar = c.f23216n;
            iH = this.f23215o.h(i10, i11, collection, z);
        }
        if (iH > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f23213m -= iH;
        return iH;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        d();
        Object[] objArr = this.f23211i;
        int i10 = this.f23213m;
        int iHashCode = 1;
        for (int i11 = 0; i11 < i10; i11++) {
            Object obj = objArr[this.f23212l + i11];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        d();
        for (int i10 = 0; i10 < this.f23213m; i10++) {
            if (p.a(this.f23211i[this.f23212l + i10], obj)) {
                return i10;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        d();
        return this.f23213m == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        d();
        for (int i10 = this.f23213m - 1; i10 >= 0; i10--) {
            if (p.a(this.f23211i[this.f23212l + i10], obj)) {
                return i10;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        e();
        d();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            removeAt(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        e();
        d();
        return h(this.f23212l, this.f23213m, collection, false) > 0;
    }

    @Override // kotlin.collections.j
    public final Object removeAt(int i10) {
        e();
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        return f(this.f23212l + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        e();
        d();
        return h(this.f23212l, this.f23213m, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        e();
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        Object[] objArr = this.f23211i;
        int i12 = this.f23212l + i10;
        Object obj2 = objArr[i12];
        objArr[i12] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i10, int i11) {
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i12 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.d(i10, i11, i12);
        return new b(this.f23211i, this.f23212l + i10, i11 - i10, this, this.f23215o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        d();
        int length = objArr.length;
        int i10 = this.f23213m;
        int i11 = this.f23212l;
        if (length < i10) {
            return Arrays.copyOfRange(this.f23211i, i11, i10 + i11, objArr.getClass());
        }
        r.N(this.f23211i, objArr, 0, i11, i10 + i11);
        int i12 = this.f23213m;
        if (i12 < objArr.length) {
            objArr[i12] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        d();
        return l.a(this.f23211i, this.f23212l, this.f23213m, this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        return new a(this, i10);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        e();
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        c(this.f23212l + i10, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        e();
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23213m;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        int size = collection.size();
        b(collection, this.f23212l + i10, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        d();
        Object[] objArr = this.f23211i;
        int i10 = this.f23213m;
        int i11 = this.f23212l;
        return r.S(objArr, i11, i10 + i11);
    }
}
