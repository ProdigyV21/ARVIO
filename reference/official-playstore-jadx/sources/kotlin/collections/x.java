package kotlin.collections;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class x extends w {
    public static Float A0(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = ((Number) it.next()).floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.max(fFloatValue, ((Number) it.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    public static Object B0(Comparator comparator, List list) {
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    public static Comparable C0(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) > 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static Float D0(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = ((Number) it.next()).floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.min(fFloatValue, ((Number) it.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    public static ArrayList E0(Object obj, List list) {
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        boolean z = false;
        for (Object obj2 : list) {
            boolean z5 = true;
            if (!z && kotlin.jvm.internal.p.a(obj2, obj)) {
                z = true;
                z5 = false;
            }
            if (z5) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public static ArrayList F0(Iterable iterable, Iterable iterable2) {
        if (iterable instanceof Collection) {
            return I0((Collection) iterable, iterable2);
        }
        ArrayList arrayList = new ArrayList();
        b0(arrayList, iterable);
        b0(arrayList, iterable2);
        return arrayList;
    }

    public static ArrayList G0(Iterable iterable, Object obj) {
        if (iterable instanceof Collection) {
            return H0(obj, (Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        b0(arrayList, iterable);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList H0(Object obj, Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList I0(Collection collection, Iterable iterable) {
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            b0(arrayList, iterable);
            return arrayList;
        }
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection2.size() + collection.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    public static Object J0(Collection collection) {
        v7.c cVar = v7.d.f22177i;
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Collection collection2 = collection;
        int iJ = v7.d.f22178l.j(collection.size());
        boolean z = collection2 instanceof List;
        if (z) {
            return ((List) collection2).get(iJ);
        }
        com.arflix.tv.data.repository.r rVar = new com.arflix.tv.data.repository.r(iJ, 1);
        if (z) {
            List list = (List) collection2;
            if (iJ >= 0 && iJ < list.size()) {
                return list.get(iJ);
            }
            rVar.invoke(Integer.valueOf(iJ));
            throw null;
        }
        if (iJ < 0) {
            rVar.invoke(Integer.valueOf(iJ));
            throw null;
        }
        int i10 = 0;
        for (Object obj : collection2) {
            int i11 = i10 + 1;
            if (iJ == i10) {
                return obj;
            }
            i10 = i11;
        }
        rVar.invoke(Integer.valueOf(iJ));
        throw null;
    }

    public static void K0(Collection collection, ga.m mVar) {
        List listU = ga.r.U(mVar);
        if (listU.isEmpty()) {
            return;
        }
        collection.removeAll(listU);
    }

    public static void L0(Collection collection, Object[] objArr) {
        if (objArr.length == 0) {
            return;
        }
        collection.removeAll(Arrays.asList(objArr));
    }

    public static boolean M0(List list, r7.l lVar) {
        int i10;
        if (!(list instanceof RandomAccess)) {
            if (!(list instanceof s7.a) || (list instanceof s7.c)) {
                return j0(list, lVar, true);
            }
            kotlin.jvm.internal.q0.h(list, "kotlin.collections.MutableIterable");
            throw null;
        }
        int iW = t7.a.w(list);
        if (iW >= 0) {
            int i11 = 0;
            i10 = 0;
            while (true) {
                Object obj = list.get(i11);
                if (!((Boolean) lVar.invoke(obj)).booleanValue()) {
                    if (i10 != i11) {
                        list.set(i10, obj);
                    }
                    i10++;
                }
                if (i11 == iW) {
                    break;
                }
                i11++;
            }
        } else {
            i10 = 0;
        }
        if (i10 >= list.size()) {
            return false;
        }
        int iW2 = t7.a.w(list);
        if (i10 <= iW2) {
            while (true) {
                list.remove(iW2);
                if (iW2 == i10) {
                    break;
                }
                iW2--;
            }
        }
        return true;
    }

    public static Object N0(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(0);
    }

    public static Object O0(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(t7.a.w(list));
    }

    public static List P0(Iterable iterable) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return c1(iterable);
        }
        List listE1 = e1(iterable);
        Collections.reverse(listE1);
        return listE1;
    }

    public static Object Q0(Iterable iterable) {
        if (iterable instanceof List) {
            return R0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static Object R0(List list) {
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static Object S0(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public static Object T0(List list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static List U0(List list, x7.i iVar) {
        return iVar.isEmpty() ? z.f19728i : c1(list.subList(iVar.f22619i, iVar.f22620l + 1));
    }

    public static List V0(Iterable iterable) {
        if (!(iterable instanceof Collection)) {
            List listE1 = e1(iterable);
            w.W(listE1);
            return listE1;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return c1(iterable);
        }
        Object[] array = collection.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        return Arrays.asList(array);
    }

    public static List W0(Iterable iterable, Comparator comparator) {
        if (!(iterable instanceof Collection)) {
            List listE1 = e1(iterable);
            w.X(comparator, listE1);
            return listE1;
        }
        Collection collection = (Collection) iterable;
        if (collection.size() <= 1) {
            return c1(iterable);
        }
        Object[] array = collection.toArray(new Object[0]);
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        return Arrays.asList(array);
    }

    public static List X0(Iterable iterable, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        if (i10 == 0) {
            return z.f19728i;
        }
        if (iterable instanceof Collection) {
            if (i10 >= ((Collection) iterable).size()) {
                return c1(iterable);
            }
            if (i10 == 1) {
                return Collections.singletonList(l0(iterable));
            }
        }
        ArrayList arrayList = new ArrayList(i10);
        Iterator it = iterable.iterator();
        int i11 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i11++;
            if (i11 == i10) {
                break;
            }
        }
        return t7.a.I(arrayList);
    }

    public static final int Y(int i10, List list) {
        if (i10 >= 0 && i10 <= t7.a.w(list)) {
            return t7.a.w(list) - i10;
        }
        StringBuilder sbS = a0.c.s(i10, "Element index ", " must be in range [");
        sbS.append(new x7.i(0, t7.a.w(list), 1));
        sbS.append("].");
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public static List Y0(int i10, List list) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        if (i10 == 0) {
            return z.f19728i;
        }
        int size = list.size();
        if (i10 >= size) {
            return c1(list);
        }
        if (i10 == 1) {
            return Collections.singletonList(w0(list));
        }
        ArrayList arrayList = new ArrayList(i10);
        if (list instanceof RandomAccess) {
            for (int i11 = size - i10; i11 < size; i11++) {
                arrayList.add(list.get(i11));
            }
        } else {
            ListIterator listIterator = list.listIterator(size - i10);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    public static final int Z(int i10, List list) {
        if (i10 >= 0 && i10 <= list.size()) {
            return list.size() - i10;
        }
        StringBuilder sbS = a0.c.s(i10, "Position index ", " must be in range [");
        sbS.append(new x7.i(0, list.size(), 1));
        sbS.append("].");
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public static void Z0(Iterable iterable, AbstractCollection abstractCollection) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static void a0(PersistentCollection.Builder builder, ga.m mVar) {
        Iterator it = mVar.iterator();
        while (it.hasNext()) {
            builder.add(it.next());
        }
    }

    public static HashSet a1(Collection collection) {
        HashSet hashSet = new HashSet(i0.q0(s.U(collection, 12)));
        Z0(collection, hashSet);
        return hashSet;
    }

    public static void b0(Collection collection, Iterable iterable) {
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static int[] b1(List list) {
        int[] iArr = new int[list.size()];
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            iArr[i10] = ((Number) it.next()).intValue();
            i10++;
        }
        return iArr;
    }

    public static void c0(Collection collection, Object[] objArr) {
        collection.addAll(Arrays.asList(objArr));
    }

    public static List c1(Iterable iterable) {
        if (!(iterable instanceof Collection)) {
            return t7.a.I(e1(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return z.f19728i;
        }
        if (size != 1) {
            return new ArrayList(collection);
        }
        return Collections.singletonList(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
    }

    public static ga.p d0(Iterable iterable) {
        return new ga.p(iterable, 3);
    }

    public static long[] d1(ArrayList arrayList) {
        long[] jArr = new long[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            jArr[i10] = ((Number) it.next()).longValue();
            i10++;
        }
        return jArr;
    }

    public static ArrayList e0(Iterable iterable, int i10) {
        androidx.work.impl.t.g(i10, i10);
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            ArrayList arrayList = new ArrayList();
            Iterator itC = androidx.work.impl.t.C(iterable.iterator(), i10, i10, true, false);
            while (itC.hasNext()) {
                arrayList.add((List) itC.next());
            }
            return arrayList;
        }
        List list = (List) iterable;
        int size = list.size();
        ArrayList arrayList2 = new ArrayList((size / i10) + (size % i10 == 0 ? 0 : 1));
        int i11 = 0;
        while (i11 >= 0 && i11 < size) {
            int i12 = size - i11;
            if (i10 <= i12) {
                i12 = i10;
            }
            ArrayList arrayList3 = new ArrayList(i12);
            for (int i13 = 0; i13 < i12; i13++) {
                arrayList3.add(list.get(i13 + i11));
            }
            arrayList2.add(arrayList3);
            i11 += i10;
        }
        return arrayList2;
    }

    public static final List e1(Iterable iterable) {
        if (iterable instanceof Collection) {
            return new ArrayList((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        Z0(iterable, arrayList);
        return arrayList;
    }

    public static boolean f0(Iterable iterable, Object obj) {
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : q0(iterable, obj) >= 0;
    }

    public static Set f1(Iterable iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Z0(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static List g0(Iterable iterable) {
        return c1(f1(iterable));
    }

    public static Set g1(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size == 1) {
                    return Collections.singleton(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(collection.size()));
                Z0(iterable, linkedHashSet);
                return linkedHashSet;
            }
        } else {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Z0(iterable, linkedHashSet2);
            int size2 = linkedHashSet2.size();
            if (size2 != 0) {
                return size2 != 1 ? linkedHashSet2 : Collections.singleton(linkedHashSet2.iterator().next());
            }
        }
        return b0.f19686i;
    }

    public static List h0(Iterable iterable, int i10) {
        ArrayList arrayList;
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        if (i10 == 0) {
            return c1(iterable);
        }
        if (iterable instanceof Collection) {
            int size = ((Collection) iterable).size() - i10;
            if (size <= 0) {
                return z.f19728i;
            }
            if (size == 1) {
                return Collections.singletonList(v0(iterable));
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    List list = (List) iterable;
                    int size2 = list.size();
                    while (i10 < size2) {
                        arrayList.add(list.get(i10));
                        i10++;
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(i10);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i11 = 0;
        for (Object obj : iterable) {
            if (i11 >= i10) {
                arrayList.add(obj);
            } else {
                i11++;
            }
        }
        return t7.a.I(arrayList);
    }

    public static ab.k h1(List list) {
        return new ab.k(new com.arflix.tv.d(list, 6), 7);
    }

    public static List i0(int i10, List list) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        int size = list.size() - i10;
        if (size < 0) {
            size = 0;
        }
        return X0(list, size);
    }

    public static ArrayList i1(List list, List list2) {
        Iterator it = list.iterator();
        Iterator it2 = list2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(s.U(list, 10), s.U(list2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(new x6.x(it.next(), it2.next()));
        }
        return arrayList;
    }

    public static final boolean j0(Iterable iterable, r7.l lVar, boolean z) {
        Iterator it = iterable.iterator();
        boolean z5 = false;
        while (it.hasNext()) {
            if (((Boolean) lVar.invoke(it.next())).booleanValue() == z) {
                it.remove();
                z5 = true;
            }
        }
        return z5;
    }

    public static ArrayList k0(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object l0(Iterable iterable) {
        if (iterable instanceof List) {
            return m0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object m0(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object n0(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static Object o0(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static Object p0(int i10, List list) {
        if (i10 < 0 || i10 >= list.size()) {
            return null;
        }
        return list.get(i10);
    }

    public static int q0(Iterable iterable, Object obj) {
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i10 = 0;
        for (Object obj2 : iterable) {
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            if (kotlin.jvm.internal.p.a(obj, obj2)) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    public static LinkedHashSet r0(Iterable iterable, Iterable iterable2) {
        Collection collectionC1 = iterable2 instanceof Collection ? (Collection) iterable2 : c1(iterable2);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : iterable) {
            if (collectionC1.contains(obj)) {
                linkedHashSet.add(obj);
            }
        }
        return linkedHashSet;
    }

    public static final void s0(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, r7.l lVar) throws IOException {
        appendable.append(charSequence2);
        int i10 = 0;
        for (Object obj : iterable) {
            i10++;
            if (i10 > 1) {
                appendable.append(charSequence);
            }
            f4.f.b(appendable, obj, lVar);
        }
        appendable.append(charSequence3);
    }

    public static /* synthetic */ void t0(Iterable iterable, Appendable appendable, String str, String str2, String str3, r7.l lVar, int i10) throws IOException {
        if ((i10 & 2) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i10 & 4) != 0 ? "" : str2;
        String str6 = (i10 & 8) != 0 ? "" : str3;
        if ((i10 & 64) != 0) {
            lVar = null;
        }
        s0(iterable, appendable, str4, str5, str6, lVar);
    }

    public static String u0(Iterable iterable, String str, String str2, String str3, r7.l lVar, int i10) {
        if ((i10 & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i10 & 2) != 0 ? "" : str2;
        String str6 = (i10 & 4) != 0 ? "" : str3;
        if ((i10 & 32) != 0) {
            lVar = null;
        }
        StringBuilder sb2 = new StringBuilder();
        s0(iterable, sb2, str4, str5, str6, lVar);
        return sb2.toString();
    }

    public static Object v0(Iterable iterable) {
        if (iterable instanceof List) {
            return w0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object w0(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(t7.a.w(list));
    }

    public static Object x0(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(list.size() - 1);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object y0(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static Comparable z0(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) < 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }
}
