package com.google.common.collect;

import j$.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class h1 extends c1 implements List, RandomAccess, j$.util.List {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final f1 f14020l = new f1(0, o3.f14078o);

    public static o3 h(int i10, Object[] objArr) {
        return i10 == 0 ? o3.f14078o : new o3(objArr, i10);
    }

    public static e1 k() {
        return new e1(4);
    }

    public static e1 m(int i10) {
        a0.c(i10, "expectedSize");
        return new e1(i10);
    }

    public static h1 n(Collection collection) {
        if (!(collection instanceof c1)) {
            Object[] array = collection.toArray();
            a0.a(array.length, array);
            return h(array.length, array);
        }
        h1 h1VarA = ((c1) collection).a();
        if (!h1VarA.f()) {
            return h1VarA;
        }
        Object[] array2 = h1VarA.toArray(c1.f13956i);
        return h(array2.length, array2);
    }

    public static o3 p(Object[] objArr) {
        if (objArr.length == 0) {
            return o3.f14078o;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        a0.a(objArr2.length, objArr2);
        return h(objArr2.length, objArr2);
    }

    public static o3 r(Long l10, Long l11, Long l12, Long l13, Long l14) {
        Object[] objArr = {l10, l11, l12, l13, l14};
        a0.a(5, objArr);
        return h(5, objArr);
    }

    public static o3 s(Object obj) {
        Object[] objArr = {obj};
        a0.a(1, objArr);
        return h(1, objArr);
    }

    public static o3 t(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        a0.a(2, objArr);
        return h(2, objArr);
    }

    public static o3 u(Comparator comparator, List list) {
        comparator.getClass();
        if (!androidx.compose.material3.d.B(list)) {
            Iterator it = list.iterator();
            ArrayList arrayList = new ArrayList();
            it.getClass();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            list = arrayList;
        }
        Object[] array = list.toArray();
        a0.a(array.length, array);
        Arrays.sort(array, comparator);
        return h(array.length, array);
    }

    @Override // com.google.common.collect.c1
    public final h1 a() {
        return this;
    }

    @Override // java.util.List
    public final void add(int i10, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i10, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c1
    public int b(int i10, Object[] objArr) {
        int size = size();
        for (int i11 = 0; i11 < size; i11++) {
            objArr[i10 + i11] = get(i11);
        }
        return i10 + size;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof List) {
                List list = (List) obj;
                int size = size();
                if (size == list.size()) {
                    if (!(list instanceof RandomAccess)) {
                        Iterator it = iterator();
                        Iterator it2 = list.iterator();
                        while (it.hasNext()) {
                            if (it2.hasNext() && a.a.L(it.next(), it2.next())) {
                            }
                        }
                        return !it2.hasNext();
                    }
                    for (int i10 = 0; i10 < size; i10++) {
                        if (a.a.L(get(i10), list.get(i10))) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return listIterator(0);
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i10 = 1;
        for (int i11 = 0; i11 < size; i11++) {
            i10 = ~(~(get(i11).hashCode() + (i10 * 31)));
        }
        return i10;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            if (obj.equals(get(i10))) {
                return i10;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final f1 listIterator(int i10) {
        ac.b.q(i10, size());
        return isEmpty() ? f14020l : new f1(i10, this);
    }

    @Override // java.util.List
    public final Object remove(int i10) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public /* synthetic */ void replaceAll(UnaryOperator unaryOperator) {
        List.CC.$default$replaceAll(this, unaryOperator);
    }

    @Override // java.util.List
    public final Object set(int i10, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public /* synthetic */ void sort(Comparator comparator) {
        List.CC.$default$sort(this, comparator);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public h1 subList(int i10, int i11) {
        ac.b.r(i10, i11, size());
        int i12 = i11 - i10;
        return i12 == size() ? this : i12 == 0 ? o3.f14078o : new g1(this, i10, i12);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }
}
