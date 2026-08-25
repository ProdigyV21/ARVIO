package com.google.common.collect;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a0 {
    public static void a(int i10, Object[] objArr) {
        for (int i11 = 0; i11 < i10; i11++) {
            if (objArr[i11] == null) {
                throw new NullPointerException(a0.c.i(i11, "at index "));
            }
        }
    }

    public static void b(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        }
        if (obj2 != null) {
            return;
        }
        throw new NullPointerException("null value in entry: " + obj + "=null");
    }

    public static void c(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.fragment.app.a2.j(i10, str, " cannot be negative but was: "));
        }
    }

    public static void d(Iterator it) {
        it.getClass();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static boolean e(l4 l4Var, Object obj) {
        Iterator it = l4Var.f14050i;
        if (obj == null) {
            while (it.hasNext()) {
                if (l4Var.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(l4Var.next())) {
                return true;
            }
        }
        return false;
    }

    public static Object f(int i10) {
        if (i10 < 2 || i10 > 1073741824 || Integer.highestOneBit(i10) != i10) {
            throw new IllegalArgumentException(a0.c.i(i10, "must be power of 2 between 2^1 and 2^30: "));
        }
        return i10 <= 256 ? new byte[i10] : i10 <= 65536 ? new short[i10] : new int[i10];
    }

    public static boolean g(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() == set2.size()) {
                return set.containsAll(set2);
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static b4 h(Set set, com.google.common.base.r rVar) {
        if (set instanceof SortedSet) {
            Set set2 = (SortedSet) set;
            if (!(set2 instanceof b4)) {
                return new c4(set2, rVar);
            }
            b4 b4Var = (b4) set2;
            com.google.common.base.r rVar2 = b4Var.f13943l;
            rVar2.getClass();
            return new c4((SortedSet) b4Var.f13942i, new com.google.common.base.s(Arrays.asList(rVar2, rVar)));
        }
        if (!(set instanceof b4)) {
            set.getClass();
            return new b4(set, rVar);
        }
        b4 b4Var2 = (b4) set;
        com.google.common.base.r rVar3 = b4Var2.f13943l;
        rVar3.getClass();
        return new b4(b4Var2.f13942i, new com.google.common.base.s(Arrays.asList(rVar3, rVar)));
    }

    public static Object i(String str, Collection collection) {
        Iterator it = collection.iterator();
        return it.hasNext() ? it.next() : str;
    }

    public static Object j(Iterable iterable) {
        Object next;
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return list.get(list.size() - 1);
        }
        Iterator it = iterable.iterator();
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static Object k(Iterable iterable) {
        Object next;
        if (iterable instanceof Collection) {
            if (((Collection) iterable).isEmpty()) {
                return null;
            }
            if (iterable instanceof List) {
                return ((List) iterable).get(r2.size() - 1);
            }
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static int l(Set set) {
        Iterator it = set.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i10 = ~(~(i10 + (next != null ? next.hashCode() : 0)));
        }
        return i10;
    }

    public static a4 m(w1 w1Var, w1 w1Var2) {
        ac.b.p(w1Var, "set1");
        ac.b.p(w1Var2, "set2");
        return new a4(w1Var, w1Var2);
    }

    public static int n(int i10, int i11, int i12) {
        return (i10 & (~i12)) | (i11 & i12);
    }

    public static ArrayList o(Object... objArr) {
        int length = objArr.length;
        c(length, "arraySize");
        ArrayList arrayList = new ArrayList(m2.f0.J(((long) length) + 5 + ((long) (length / 10))));
        Collections.addAll(arrayList, objArr);
        return arrayList;
    }

    public static int p(int i10) {
        return (i10 + 1) * (i10 < 32 ? 4 : 2);
    }

    public static int q(Object obj, Object obj2, int i10, Object obj3, int[] iArr, Object[] objArr, Object[] objArr2) {
        int i11;
        int i12;
        int iU = u(obj);
        int i13 = iU & i10;
        int iV = v(i13, obj3);
        if (iV != 0) {
            int i14 = ~i10;
            int i15 = iU & i14;
            int i16 = -1;
            while (true) {
                i11 = iV - 1;
                i12 = iArr[i11];
                if ((i12 & i14) == i15 && a.a.L(obj, objArr[i11]) && (objArr2 == null || a.a.L(obj2, objArr2[i11]))) {
                    break;
                }
                int i17 = i12 & i10;
                if (i17 == 0) {
                    break;
                }
                i16 = i11;
                iV = i17;
            }
            int i18 = i12 & i10;
            if (i16 == -1) {
                w(i13, i18, obj3);
                return i11;
            }
            iArr[i16] = n(iArr[i16], i18, i10);
            return i11;
        }
        return -1;
    }

    public static boolean r(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof b3) {
            collection = ((b3) collection).t0();
        }
        boolean zRemove = false;
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                zRemove |= set.remove(it.next());
            }
            return zRemove;
        }
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                it2.remove();
                zRemove = true;
            }
        }
        return zRemove;
    }

    public static void s(List list, com.google.common.base.r rVar, int i10, int i11) {
        for (int size = list.size() - 1; size > i11; size--) {
            if (rVar.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i12 = i11 - 1; i12 >= i10; i12--) {
            list.remove(i12);
        }
    }

    public static int t(int i10) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i10) * (-862048943)), 15)) * 461845907);
    }

    public static int u(Object obj) {
        return t(obj == null ? 0 : obj.hashCode());
    }

    public static int v(int i10, Object obj) {
        return obj instanceof byte[] ? ((byte[]) obj)[i10] & 255 : obj instanceof short[] ? ((short[]) obj)[i10] & 65535 : ((int[]) obj)[i10];
    }

    public static void w(int i10, int i11, Object obj) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i10] = (byte) i11;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i10] = (short) i11;
        } else {
            ((int[]) obj)[i10] = i11;
        }
    }

    public static AbstractList x(List list, com.google.common.base.m mVar) {
        return list instanceof RandomAccess ? new i2(list, mVar) : new j2(list, mVar);
    }
}
