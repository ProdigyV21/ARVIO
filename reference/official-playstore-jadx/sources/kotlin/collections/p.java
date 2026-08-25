package kotlin.collections;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Object[] f19706n = new Object[0];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19707i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f19708l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19709m;

    public p() {
        this.f19708l = f19706n;
    }

    public final void a(int i10, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f19708l.length;
        while (i10 < length && it.hasNext()) {
            this.f19708l[i10] = it.next();
            i10++;
        }
        int i11 = this.f19707i;
        for (int i12 = 0; i12 < i11 && it.hasNext(); i12++) {
            this.f19708l[i12] = it.next();
        }
        this.f19709m = collection.size() + size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        k();
        b(collection.size() + size());
        a(h(size() + this.f19707i), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        k();
        b(size() + 1);
        int length = this.f19707i;
        if (length == 0) {
            length = this.f19708l.length;
        }
        int i10 = length - 1;
        this.f19707i = i10;
        this.f19708l[i10] = obj;
        this.f19709m = size() + 1;
    }

    public final void addLast(Object obj) {
        k();
        b(size() + 1);
        this.f19708l[h(size() + this.f19707i)] = obj;
        this.f19709m = size() + 1;
    }

    public final void b(int i10) {
        if (i10 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f19708l;
        if (i10 <= objArr.length) {
            return;
        }
        if (objArr == f19706n) {
            if (i10 < 10) {
                i10 = 10;
            }
            this.f19708l = new Object[i10];
            return;
        }
        c cVar = f.Companion;
        int length = objArr.length;
        cVar.getClass();
        Object[] objArr2 = new Object[c.e(length, i10)];
        Object[] objArr3 = this.f19708l;
        r.N(objArr3, objArr2, 0, this.f19707i, objArr3.length);
        Object[] objArr4 = this.f19708l;
        int length2 = objArr4.length;
        int i11 = this.f19707i;
        r.N(objArr4, objArr2, length2 - i11, 0, i11);
        this.f19707i = 0;
        this.f19708l = objArr2;
    }

    public final Object c() {
        if (isEmpty()) {
            return null;
        }
        return this.f19708l[this.f19707i];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            k();
            g(this.f19707i, h(size() + this.f19707i));
        }
        this.f19707i = 0;
        this.f19709m = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i10) {
        if (i10 == this.f19708l.length - 1) {
            return 0;
        }
        return i10 + 1;
    }

    public final Object e() {
        if (isEmpty()) {
            return null;
        }
        return this.f19708l[h(t7.a.w(this) + this.f19707i)];
    }

    public final int f(int i10) {
        return i10 < 0 ? i10 + this.f19708l.length : i10;
    }

    public final Object first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.f19708l[this.f19707i];
    }

    public final void g(int i10, int i11) {
        if (i10 < i11) {
            Arrays.fill(this.f19708l, i10, i11, (Object) null);
            return;
        }
        Object[] objArr = this.f19708l;
        Arrays.fill(objArr, i10, objArr.length, (Object) null);
        Arrays.fill(this.f19708l, 0, i11, (Object) null);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.b(i10, size);
        return this.f19708l[h(this.f19707i + i10)];
    }

    @Override // kotlin.collections.j
    public final int getSize() {
        return this.f19709m;
    }

    public final int h(int i10) {
        Object[] objArr = this.f19708l;
        return i10 >= objArr.length ? i10 - objArr.length : i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i10;
        int iH = h(size() + this.f19707i);
        int length = this.f19707i;
        if (length < iH) {
            while (length < iH) {
                if (kotlin.jvm.internal.p.a(obj, this.f19708l[length])) {
                    i10 = this.f19707i;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (isEmpty() || (length = this.f19707i) < iH) {
            return -1;
        }
        int length2 = this.f19708l.length;
        while (true) {
            if (length >= length2) {
                for (int i11 = 0; i11 < iH; i11++) {
                    if (kotlin.jvm.internal.p.a(obj, this.f19708l[i11])) {
                        length = i11 + this.f19708l.length;
                        i10 = this.f19707i;
                    }
                }
                return -1;
            }
            if (kotlin.jvm.internal.p.a(obj, this.f19708l[length])) {
                i10 = this.f19707i;
                break;
            }
            length++;
        }
        return length - i10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return size() == 0;
    }

    public final void k() {
        ((AbstractList) this).modCount++;
    }

    public final Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.f19708l[h(t7.a.w(this) + this.f19707i)];
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i10;
        int iH = h(size() + this.f19707i);
        int i11 = this.f19707i;
        if (i11 < iH) {
            length = iH - 1;
            if (i11 <= length) {
                while (!kotlin.jvm.internal.p.a(obj, this.f19708l[length])) {
                    if (length != i11) {
                        length--;
                    }
                }
                i10 = this.f19707i;
                return length - i10;
            }
            return -1;
        }
        if (!isEmpty() && this.f19707i >= iH) {
            int i12 = iH - 1;
            while (true) {
                if (-1 >= i12) {
                    length = this.f19708l.length - 1;
                    int i13 = this.f19707i;
                    if (i13 <= length) {
                        while (!kotlin.jvm.internal.p.a(obj, this.f19708l[length])) {
                            if (length != i13) {
                                length--;
                            }
                        }
                        i10 = this.f19707i;
                    }
                } else {
                    if (kotlin.jvm.internal.p.a(obj, this.f19708l[i12])) {
                        length = i12 + this.f19708l.length;
                        i10 = this.f19707i;
                        break;
                    }
                    i12--;
                }
            }
            return length - i10;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iH;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.f19708l.length != 0) {
            int iH2 = h(size() + this.f19707i);
            int i10 = this.f19707i;
            if (i10 < iH2) {
                iH = i10;
                while (i10 < iH2) {
                    Object obj = this.f19708l[i10];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.f19708l[iH] = obj;
                        iH++;
                    }
                    i10++;
                }
                Arrays.fill(this.f19708l, iH, iH2, (Object) null);
            } else {
                int length = this.f19708l.length;
                boolean z5 = false;
                int i11 = i10;
                while (i10 < length) {
                    Object[] objArr = this.f19708l;
                    Object obj2 = objArr[i10];
                    objArr[i10] = null;
                    if (collection.contains(obj2)) {
                        z5 = true;
                    } else {
                        this.f19708l[i11] = obj2;
                        i11++;
                    }
                    i10++;
                }
                iH = h(i11);
                for (int i12 = 0; i12 < iH2; i12++) {
                    Object[] objArr2 = this.f19708l;
                    Object obj3 = objArr2[i12];
                    objArr2[i12] = null;
                    if (collection.contains(obj3)) {
                        z5 = true;
                    } else {
                        this.f19708l[iH] = obj3;
                        iH = d(iH);
                    }
                }
                z = z5;
            }
            if (z) {
                k();
                this.f19709m = f(iH - this.f19707i);
            }
        }
        return z;
    }

    @Override // kotlin.collections.j
    public final Object removeAt(int i10) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.b(i10, size);
        if (i10 == t7.a.w(this)) {
            return removeLast();
        }
        if (i10 == 0) {
            return removeFirst();
        }
        k();
        int iH = h(this.f19707i + i10);
        Object obj = this.f19708l[iH];
        if (i10 < (size() >> 1)) {
            int i11 = this.f19707i;
            if (iH >= i11) {
                Object[] objArr = this.f19708l;
                r.N(objArr, objArr, i11 + 1, i11, iH);
            } else {
                Object[] objArr2 = this.f19708l;
                r.N(objArr2, objArr2, 1, 0, iH);
                Object[] objArr3 = this.f19708l;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i12 = this.f19707i;
                r.N(objArr3, objArr3, i12 + 1, i12, objArr3.length - 1);
            }
            Object[] objArr4 = this.f19708l;
            int i13 = this.f19707i;
            objArr4[i13] = null;
            this.f19707i = d(i13);
        } else {
            int iH2 = h(t7.a.w(this) + this.f19707i);
            if (iH <= iH2) {
                Object[] objArr5 = this.f19708l;
                r.N(objArr5, objArr5, iH, iH + 1, iH2 + 1);
            } else {
                Object[] objArr6 = this.f19708l;
                r.N(objArr6, objArr6, iH, iH + 1, objArr6.length);
                Object[] objArr7 = this.f19708l;
                objArr7[objArr7.length - 1] = objArr7[0];
                r.N(objArr7, objArr7, 0, 1, iH2 + 1);
            }
            this.f19708l[iH2] = null;
        }
        this.f19709m = size() - 1;
        return obj;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        k();
        Object[] objArr = this.f19708l;
        int i10 = this.f19707i;
        Object obj = objArr[i10];
        objArr[i10] = null;
        this.f19707i = d(i10);
        this.f19709m = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        k();
        int iH = h(t7.a.w(this) + this.f19707i);
        Object[] objArr = this.f19708l;
        Object obj = objArr[iH];
        objArr[iH] = null;
        this.f19709m = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i10, int i11) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.d(i10, i11, size);
        int i12 = i11 - i10;
        if (i12 == 0) {
            return;
        }
        if (i12 == size()) {
            clear();
            return;
        }
        if (i12 == 1) {
            removeAt(i10);
            return;
        }
        k();
        if (i10 < size() - i11) {
            int iH = h(this.f19707i + (i10 - 1));
            int iH2 = h(this.f19707i + (i11 - 1));
            while (i10 > 0) {
                int i13 = iH + 1;
                int iMin = Math.min(i10, Math.min(i13, iH2 + 1));
                Object[] objArr = this.f19708l;
                int i14 = iH2 - iMin;
                int i15 = iH - iMin;
                r.N(objArr, objArr, i14 + 1, i15 + 1, i13);
                iH = f(i15);
                iH2 = f(i14);
                i10 -= iMin;
            }
            int iH3 = h(this.f19707i + i12);
            g(this.f19707i, iH3);
            this.f19707i = iH3;
        } else {
            int iH4 = h(this.f19707i + i11);
            int iH5 = h(this.f19707i + i10);
            int size2 = size();
            while (true) {
                size2 -= i11;
                if (size2 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f19708l;
                i11 = Math.min(size2, Math.min(objArr2.length - iH4, objArr2.length - iH5));
                Object[] objArr3 = this.f19708l;
                int i16 = iH4 + i11;
                r.N(objArr3, objArr3, iH5, iH4, i16);
                iH4 = h(i16);
                iH5 = h(iH5 + i11);
            }
            int iH6 = h(size() + this.f19707i);
            g(f(iH6 - i12), iH6);
        }
        this.f19709m = size() - i12;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iH;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.f19708l.length != 0) {
            int iH2 = h(size() + this.f19707i);
            int i10 = this.f19707i;
            if (i10 < iH2) {
                iH = i10;
                while (i10 < iH2) {
                    Object obj = this.f19708l[i10];
                    if (collection.contains(obj)) {
                        this.f19708l[iH] = obj;
                        iH++;
                    } else {
                        z = true;
                    }
                    i10++;
                }
                Arrays.fill(this.f19708l, iH, iH2, (Object) null);
            } else {
                int length = this.f19708l.length;
                boolean z5 = false;
                int i11 = i10;
                while (i10 < length) {
                    Object[] objArr = this.f19708l;
                    Object obj2 = objArr[i10];
                    objArr[i10] = null;
                    if (collection.contains(obj2)) {
                        this.f19708l[i11] = obj2;
                        i11++;
                    } else {
                        z5 = true;
                    }
                    i10++;
                }
                iH = h(i11);
                for (int i12 = 0; i12 < iH2; i12++) {
                    Object[] objArr2 = this.f19708l;
                    Object obj3 = objArr2[i12];
                    objArr2[i12] = null;
                    if (collection.contains(obj3)) {
                        this.f19708l[iH] = obj3;
                        iH = d(iH);
                    } else {
                        z5 = true;
                    }
                }
                z = z5;
            }
            if (z) {
                k();
                this.f19709m = f(iH - this.f19707i);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.b(i10, size);
        int iH = h(this.f19707i + i10);
        Object[] objArr = this.f19708l;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        if (objArr.length < size()) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size());
        }
        Object[] objArr2 = objArr;
        int iH = h(size() + this.f19707i);
        int i10 = this.f19707i;
        if (i10 < iH) {
            r.R(this.f19708l, objArr2, 0, i10, iH, 2);
        } else if (!isEmpty()) {
            Object[] objArr3 = this.f19708l;
            r.N(objArr3, objArr2, 0, this.f19707i, objArr3.length);
            Object[] objArr4 = this.f19708l;
            r.N(objArr4, objArr2, objArr4.length - this.f19707i, 0, iH);
        }
        int size = size();
        if (size < objArr2.length) {
            objArr2[size] = null;
        }
        return objArr2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.c(i10, size);
        if (i10 == size()) {
            addLast(obj);
            return;
        }
        if (i10 == 0) {
            addFirst(obj);
            return;
        }
        k();
        b(size() + 1);
        int iH = h(this.f19707i + i10);
        if (i10 < ((size() + 1) >> 1)) {
            int length = iH == 0 ? this.f19708l.length - 1 : iH - 1;
            int i11 = this.f19707i;
            int length2 = i11 == 0 ? this.f19708l.length - 1 : i11 - 1;
            if (length >= i11) {
                Object[] objArr = this.f19708l;
                objArr[length2] = objArr[i11];
                r.N(objArr, objArr, i11, i11 + 1, length + 1);
            } else {
                Object[] objArr2 = this.f19708l;
                r.N(objArr2, objArr2, i11 - 1, i11, objArr2.length);
                Object[] objArr3 = this.f19708l;
                objArr3[objArr3.length - 1] = objArr3[0];
                r.N(objArr3, objArr3, 0, 1, length + 1);
            }
            this.f19708l[length] = obj;
            this.f19707i = length2;
        } else {
            int iH2 = h(size() + this.f19707i);
            if (iH < iH2) {
                Object[] objArr4 = this.f19708l;
                r.N(objArr4, objArr4, iH + 1, iH, iH2);
            } else {
                Object[] objArr5 = this.f19708l;
                r.N(objArr5, objArr5, 1, 0, iH2);
                Object[] objArr6 = this.f19708l;
                objArr6[0] = objArr6[objArr6.length - 1];
                r.N(objArr6, objArr6, iH + 1, iH, objArr6.length - 1);
            }
            this.f19708l[iH] = obj;
        }
        this.f19709m = size() + 1;
    }

    public p(int i10) {
        Object[] objArr;
        if (i10 == 0) {
            objArr = f19706n;
        } else if (i10 > 0) {
            objArr = new Object[i10];
        } else {
            throw new IllegalArgumentException(a0.c.i(i10, "Illegal Capacity: "));
        }
        this.f19708l = objArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.c(i10, size);
        if (collection.isEmpty()) {
            return false;
        }
        if (i10 == size()) {
            return addAll(collection);
        }
        k();
        b(collection.size() + size());
        int iH = h(size() + this.f19707i);
        int iH2 = h(this.f19707i + i10);
        int size2 = collection.size();
        if (i10 < ((size() + 1) >> 1)) {
            int i11 = this.f19707i;
            int length = i11 - size2;
            if (iH2 < i11) {
                Object[] objArr = this.f19708l;
                r.N(objArr, objArr, length, i11, objArr.length);
                if (size2 >= iH2) {
                    Object[] objArr2 = this.f19708l;
                    r.N(objArr2, objArr2, objArr2.length - size2, 0, iH2);
                } else {
                    Object[] objArr3 = this.f19708l;
                    r.N(objArr3, objArr3, objArr3.length - size2, 0, size2);
                    Object[] objArr4 = this.f19708l;
                    r.N(objArr4, objArr4, 0, size2, iH2);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.f19708l;
                r.N(objArr5, objArr5, length, i11, iH2);
            } else {
                Object[] objArr6 = this.f19708l;
                length += objArr6.length;
                int i12 = iH2 - i11;
                int length2 = objArr6.length - length;
                if (length2 >= i12) {
                    r.N(objArr6, objArr6, length, i11, iH2);
                } else {
                    r.N(objArr6, objArr6, length, i11, i11 + length2);
                    Object[] objArr7 = this.f19708l;
                    r.N(objArr7, objArr7, 0, this.f19707i + length2, iH2);
                }
            }
            this.f19707i = length;
            a(f(iH2 - size2), collection);
            return true;
        }
        int i13 = iH2 + size2;
        if (iH2 < iH) {
            int i14 = size2 + iH;
            Object[] objArr8 = this.f19708l;
            if (i14 <= objArr8.length) {
                r.N(objArr8, objArr8, i13, iH2, iH);
            } else if (i13 >= objArr8.length) {
                r.N(objArr8, objArr8, i13 - objArr8.length, iH2, iH);
            } else {
                int length3 = iH - (i14 - objArr8.length);
                r.N(objArr8, objArr8, 0, length3, iH);
                Object[] objArr9 = this.f19708l;
                r.N(objArr9, objArr9, i13, iH2, length3);
            }
        } else {
            Object[] objArr10 = this.f19708l;
            r.N(objArr10, objArr10, size2, 0, iH);
            Object[] objArr11 = this.f19708l;
            if (i13 >= objArr11.length) {
                r.N(objArr11, objArr11, i13 - objArr11.length, iH2, objArr11.length);
            } else {
                r.N(objArr11, objArr11, 0, objArr11.length - size2, objArr11.length);
                Object[] objArr12 = this.f19708l;
                r.N(objArr12, objArr12, i13, iH2, objArr12.length - size2);
            }
        }
        a(iH2, collection);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
