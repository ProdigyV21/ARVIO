package com.google.android.gms.internal.fido;

import j$.util.Map;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends r implements NavigableMap, Map {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final v f13826p;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient d0 f13827m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient q f13828n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient v f13829o;

    static {
        d0 d0VarP = w.p(z.f13850l);
        m mVar = q.f13808l;
        f13826p = new v(d0VarP, b0.f13755o, null);
    }

    public v(d0 d0Var, q qVar, v vVar) {
        this.f13827m = d0Var;
        this.f13828n = qVar;
        this.f13829o = vVar;
    }

    public static v c(TreeMap treeMap) {
        z zVar = z.f13850l;
        Comparator comparator = treeMap.comparator();
        int i10 = 1;
        boolean zEquals = comparator == null ? true : zVar.equals(comparator);
        Collection collectionEntrySet = treeMap.entrySet();
        if (!(collectionEntrySet instanceof Collection)) {
            Iterator it = collectionEntrySet.iterator();
            ArrayList arrayList = new ArrayList();
            it.getClass();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            collectionEntrySet = arrayList;
        }
        Map.Entry[] entryArr = (Map.Entry[]) collectionEntrySet.toArray(r.f13810l);
        int length = entryArr.length;
        if (length == 0) {
            return e(zVar);
        }
        int i11 = 0;
        if (length == 1) {
            Map.Entry entry = entryArr[0];
            Objects.requireNonNull(entry);
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object[] objArr = {key};
            for (int i12 = 0; i12 < 1; i12++) {
                if (objArr[i12] == null) {
                    throw new NullPointerException(a0.c.i(i12, "at index "));
                }
            }
            d0 d0Var = new d0(q.h(1, objArr), zVar);
            Object[] objArr2 = {value};
            while (i11 < 1) {
                if (objArr2[i11] == null) {
                    throw new NullPointerException(a0.c.i(i11, "at index "));
                }
                i11++;
            }
            return new v(d0Var, q.h(1, objArr2), null);
        }
        Object[] objArr3 = new Object[length];
        Object[] objArr4 = new Object[length];
        if (zEquals) {
            while (i11 < length) {
                Map.Entry entry2 = entryArr[i11];
                Objects.requireNonNull(entry2);
                Object key2 = entry2.getKey();
                Object value2 = entry2.getValue();
                b.f(key2, value2);
                objArr3[i11] = key2;
                objArr4[i11] = value2;
                i11++;
            }
        } else {
            Arrays.sort(entryArr, 0, length, new a8.h(4));
            Map.Entry entry3 = entryArr[0];
            Objects.requireNonNull(entry3);
            Object key3 = entry3.getKey();
            objArr3[0] = key3;
            Object value3 = entry3.getValue();
            objArr4[0] = value3;
            b.f(objArr3[0], value3);
            while (i10 < length) {
                Map.Entry entry4 = entryArr[i10 - 1];
                Objects.requireNonNull(entry4);
                Map.Entry entry5 = entryArr[i10];
                Objects.requireNonNull(entry5);
                Object key4 = entry5.getKey();
                Object value4 = entry5.getValue();
                b.f(key4, value4);
                objArr3[i10] = key4;
                objArr4[i10] = value4;
                if (zVar.compare(key3, key4) == 0) {
                    throw new IllegalArgumentException(androidx.compose.material3.d.n("Multiple entries with same key: ", String.valueOf(entry4), " and ", String.valueOf(entry5)));
                }
                i10++;
                key3 = key4;
            }
        }
        return new v(new d0(q.h(length, objArr3), zVar), q.h(length, objArr4), null);
    }

    public static v e(Comparator comparator) {
        if (z.f13850l.equals(comparator)) {
            return f13826p;
        }
        d0 d0VarP = w.p(comparator);
        m mVar = q.f13808l;
        return new v(d0VarP, b0.f13755o, null);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object ceilingKey(Object obj) {
        Map.Entry entryCeilingEntry = ceilingEntry(obj);
        if (entryCeilingEntry == null) {
            return null;
        }
        return entryCeilingEntry.getKey();
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return this.f13827m.f13831n;
    }

    @Override // java.util.NavigableMap
    public final /* synthetic */ NavigableSet descendingKeySet() {
        return this.f13827m.descendingSet();
    }

    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap descendingMap() {
        v vVar = this.f13829o;
        if (vVar != null) {
            return vVar;
        }
        boolean zIsEmpty = isEmpty();
        d0 d0Var = this.f13827m;
        if (!zIsEmpty) {
            return new v((d0) d0Var.descendingSet(), this.f13828n.f(), this);
        }
        Comparator comparator = d0Var.f13831n;
        return e((comparator instanceof a0 ? (a0) comparator : new k(comparator)).a());
    }

    @Override // java.util.NavigableMap
    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().h().get(0);
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return this.f13827m.first();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object floorKey(Object obj) {
        Map.Entry entryFloorEntry = floorEntry(obj);
        if (entryFloorEntry == null) {
            return null;
        }
        return entryFloorEntry.getKey();
    }

    @Override // java.util.NavigableMap
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final v headMap(Object obj, boolean z) {
        obj.getClass();
        return j(0, this.f13827m.q(obj, z));
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0005  */
    @Override // com.google.android.gms.internal.fido.r, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.fido.d0 r0 = r3.f13827m
            r1 = -1
            if (r4 != 0) goto L7
        L5:
            r4 = r1
            goto L12
        L7:
            com.google.android.gms.internal.fido.q r2 = r0.f13770p     // Catch: java.lang.ClassCastException -> L5
            java.util.Comparator r0 = r0.f13831n     // Catch: java.lang.ClassCastException -> L5
            int r4 = java.util.Collections.binarySearch(r2, r4, r0)     // Catch: java.lang.ClassCastException -> L5
            if (r4 >= 0) goto L12
            goto L5
        L12:
            if (r4 != r1) goto L16
            r4 = 0
            return r4
        L16:
            com.google.android.gms.internal.fido.q r0 = r3.f13828n
            java.lang.Object r4 = r0.get(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.v.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.NavigableMap
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final v subMap(Object obj, boolean z, Object obj2, boolean z5) {
        obj.getClass();
        obj2.getClass();
        if (this.f13827m.f13831n.compare(obj, obj2) <= 0) {
            return headMap(obj2, z5).tailMap(obj, z);
        }
        throw new IllegalArgumentException(b.d("expected fromKey <= toKey but %s > %s", obj, obj2));
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* synthetic */ SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object higherKey(Object obj) {
        Map.Entry entryHigherEntry = higherEntry(obj);
        if (entryHigherEntry == null) {
            return null;
        }
        return entryHigherEntry.getKey();
    }

    @Override // java.util.NavigableMap
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final v tailMap(Object obj, boolean z) {
        obj.getClass();
        return j(this.f13827m.r(obj, z), this.f13828n.size());
    }

    public final v j(int i10, int i11) {
        q qVar = this.f13828n;
        if (i10 == 0) {
            if (i11 == qVar.size()) {
                return this;
            }
            i10 = 0;
        }
        d0 d0Var = this.f13827m;
        return i10 == i11 ? e(d0Var.f13831n) : new v(d0Var.s(i10, i11), qVar.subList(i10, i11), null);
    }

    @Override // java.util.Map, java.util.SortedMap
    public final /* synthetic */ Set keySet() {
        return this.f13827m;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().h().get(this.f13828n.size() - 1);
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return this.f13827m.last();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object lowerKey(Object obj) {
        Map.Entry entryLowerEntry = lowerEntry(obj);
        if (entryLowerEntry == null) {
            return null;
        }
        return entryLowerEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final /* synthetic */ NavigableSet navigableKeySet() {
        return this.f13827m;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final int size() {
        return this.f13828n.size();
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* bridge */ /* synthetic */ SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* synthetic */ SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    @Override // java.util.Map, java.util.SortedMap
    public final /* synthetic */ Collection values() {
        return this.f13828n;
    }
}
