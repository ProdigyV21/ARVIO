package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q0 extends ac.b {
    public static LinkedHashSet Z(Set set, Object obj) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(set.size()));
        boolean z = false;
        for (Object obj2 : set) {
            boolean z5 = true;
            if (!z && kotlin.jvm.internal.p.a(obj2, obj)) {
                z = true;
                z5 = false;
            }
            if (z5) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    public static Set a0(Set set, Iterable iterable) {
        Collection<?> collectionC1 = iterable instanceof Collection ? (Collection) iterable : x.c1(iterable);
        if (collectionC1.isEmpty()) {
            return x.g1(set);
        }
        if (!(collectionC1 instanceof Set)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(set);
            linkedHashSet.removeAll(collectionC1);
            return linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Object obj : set) {
            if (!((Set) collectionC1).contains(obj)) {
                linkedHashSet2.add(obj);
            }
        }
        return linkedHashSet2;
    }

    public static LinkedHashSet b0(Set set, Iterable iterable) {
        Integer numValueOf = iterable instanceof Collection ? Integer.valueOf(((Collection) iterable).size()) : null;
        LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(numValueOf != null ? set.size() + numValueOf.intValue() : set.size() * 2));
        linkedHashSet.addAll(set);
        x.b0(linkedHashSet, iterable);
        return linkedHashSet;
    }

    public static LinkedHashSet c0(Set set, Object obj) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }
}
