package z6;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.collections.j;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import qb.l;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends j implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f23216n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f23217i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f23218l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f23219m;

    static {
        c cVar = new c(0);
        cVar.f23219m = true;
        f23216n = cVar;
    }

    public c(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        this.f23217i = new Object[i10];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        d();
        int i10 = this.f23218l;
        ((AbstractList) this).modCount++;
        e(i10, 1);
        this.f23217i[i10] = obj;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        d();
        int size = collection.size();
        b(collection, this.f23218l, size);
        return size > 0;
    }

    public final void b(Collection collection, int i10, int i11) {
        ((AbstractList) this).modCount++;
        e(i10, i11);
        Iterator it = collection.iterator();
        for (int i12 = 0; i12 < i11; i12++) {
            this.f23217i[i10 + i12] = it.next();
        }
    }

    public final void c(int i10, Object obj) {
        ((AbstractList) this).modCount++;
        e(i10, 1);
        this.f23217i[i10] = obj;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        d();
        g(0, this.f23218l);
    }

    public final void d() {
        if (this.f23219m) {
            throw new UnsupportedOperationException();
        }
    }

    public final void e(int i10, int i11) {
        int i12 = this.f23218l + i11;
        if (i12 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.f23217i;
        if (i12 > objArr.length) {
            kotlin.collections.c cVar = kotlin.collections.f.Companion;
            int length = objArr.length;
            cVar.getClass();
            this.f23217i = Arrays.copyOf(this.f23217i, kotlin.collections.c.e(length, i12));
        }
        Object[] objArr2 = this.f23217i;
        r.N(objArr2, objArr2, i10 + i11, i10, this.f23218l);
        this.f23218l += i11;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.f23217i;
            int i10 = this.f23218l;
            if (i10 == list.size()) {
                for (int i11 = 0; i11 < i10; i11++) {
                    if (p.a(objArr[i11], list.get(i11))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final Object f(int i10) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.f23217i;
        Object obj = objArr[i10];
        r.N(objArr, objArr, i10, i10 + 1, this.f23218l);
        Object[] objArr2 = this.f23217i;
        int i11 = this.f23218l;
        objArr2[i11 - 1] = null;
        this.f23218l = i11 - 1;
        return obj;
    }

    public final void g(int i10, int i11) {
        if (i11 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.f23217i;
        r.N(objArr, objArr, i10, i10 + i11, this.f23218l);
        Object[] objArr2 = this.f23217i;
        int i12 = this.f23218l;
        l.y(objArr2, i12 - i11, i12);
        this.f23218l -= i11;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        return this.f23217i[i10];
    }

    @Override // kotlin.collections.j
    public final int getSize() {
        return this.f23218l;
    }

    public final int h(int i10, int i11, Collection collection, boolean z) {
        int i12 = 0;
        int i13 = 0;
        while (i12 < i11) {
            int i14 = i10 + i12;
            if (collection.contains(this.f23217i[i14]) == z) {
                Object[] objArr = this.f23217i;
                i12++;
                objArr[i13 + i10] = objArr[i14];
                i13++;
            } else {
                i12++;
            }
        }
        int i15 = i11 - i13;
        Object[] objArr2 = this.f23217i;
        r.N(objArr2, objArr2, i10 + i13, i11 + i10, this.f23218l);
        Object[] objArr3 = this.f23217i;
        int i16 = this.f23218l;
        l.y(objArr3, i16 - i15, i16);
        if (i15 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f23218l -= i15;
        return i15;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        Object[] objArr = this.f23217i;
        int i10 = this.f23218l;
        int iHashCode = 1;
        for (int i11 = 0; i11 < i10; i11++) {
            Object obj = objArr[i11];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i10 = 0; i10 < this.f23218l; i10++) {
            if (p.a(this.f23217i[i10], obj)) {
                return i10;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f23218l == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i10 = this.f23218l - 1; i10 >= 0; i10--) {
            if (p.a(this.f23217i[i10], obj)) {
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
        d();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            removeAt(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        d();
        return h(0, this.f23218l, collection, false) > 0;
    }

    @Override // kotlin.collections.j
    public final Object removeAt(int i10) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        return f(i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        d();
        return h(0, this.f23218l, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.b(i10, i11);
        Object[] objArr = this.f23217i;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i10, int i11) {
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i12 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.d(i10, i11, i12);
        return new b(this.f23217i, i10, i11 - i10, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int i10 = this.f23218l;
        if (length < i10) {
            return Arrays.copyOfRange(this.f23217i, 0, i10, objArr.getClass());
        }
        r.N(this.f23217i, objArr, 0, 0, i10);
        int i11 = this.f23218l;
        if (i11 < objArr.length) {
            objArr[i11] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return l.a(this.f23217i, 0, this.f23218l, this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        return new a(this, i10);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        int size = collection.size();
        b(collection, i10, size);
        return size > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        d();
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int i11 = this.f23218l;
        cVar.getClass();
        kotlin.collections.c.c(i10, i11);
        ((AbstractList) this).modCount++;
        e(i10, 1);
        this.f23217i[i10] = obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return r.S(this.f23217i, 0, this.f23218l);
    }
}
