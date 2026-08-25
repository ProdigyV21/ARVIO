package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends k implements NavigableMap {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ z2 f14014q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(z2 z2Var, NavigableMap navigableMap) {
        super(z2Var, navigableMap);
        this.f14014q = z2Var;
    }

    @Override // com.google.common.collect.k
    public final SortedSet c() {
        return new i(this.f14014q, g());
    }

    @Override // java.util.NavigableMap
    public final Map.Entry ceilingEntry(Object obj) {
        Map.Entry entryCeilingEntry = g().ceilingEntry(obj);
        if (entryCeilingEntry == null) {
            return null;
        }
        return b(entryCeilingEntry);
    }

    @Override // java.util.NavigableMap
    public final Object ceilingKey(Object obj) {
        return g().ceilingKey(obj);
    }

    @Override // java.util.NavigableMap
    public final NavigableSet descendingKeySet() {
        return (NavigableSet) super.keySet();
    }

    @Override // java.util.NavigableMap
    public final NavigableMap descendingMap() {
        return new h(this.f14014q, g().descendingMap());
    }

    @Override // com.google.common.collect.k
    /* JADX INFO: renamed from: e */
    public final SortedSet keySet() {
        return (NavigableSet) super.keySet();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry firstEntry() {
        Map.Entry entryFirstEntry = g().firstEntry();
        if (entryFirstEntry == null) {
            return null;
        }
        return b(entryFirstEntry);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry floorEntry(Object obj) {
        Map.Entry entryFloorEntry = g().floorEntry(obj);
        if (entryFloorEntry == null) {
            return null;
        }
        return b(entryFloorEntry);
    }

    @Override // java.util.NavigableMap
    public final Object floorKey(Object obj) {
        return g().floorKey(obj);
    }

    public final d1 h(Iterator it) {
        if (!it.hasNext()) {
            return null;
        }
        Map.Entry entry = (Map.Entry) it.next();
        Collection collectionJ = this.f14014q.j();
        collectionJ.addAll((Collection) entry.getValue());
        it.remove();
        return new d1(entry.getKey(), Collections.unmodifiableList((List) collectionJ));
    }

    @Override // com.google.common.collect.k, java.util.SortedMap, java.util.NavigableMap
    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry higherEntry(Object obj) {
        Map.Entry entryHigherEntry = g().higherEntry(obj);
        if (entryHigherEntry == null) {
            return null;
        }
        return b(entryHigherEntry);
    }

    @Override // java.util.NavigableMap
    public final Object higherKey(Object obj) {
        return g().higherKey(obj);
    }

    @Override // com.google.common.collect.k
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final NavigableMap g() {
        return (NavigableMap) ((SortedMap) this.f13985m);
    }

    @Override // com.google.common.collect.k, com.google.common.collect.f, java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return (NavigableSet) super.keySet();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lastEntry() {
        Map.Entry entryLastEntry = g().lastEntry();
        if (entryLastEntry == null) {
            return null;
        }
        return b(entryLastEntry);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lowerEntry(Object obj) {
        Map.Entry entryLowerEntry = g().lowerEntry(obj);
        if (entryLowerEntry == null) {
            return null;
        }
        return b(entryLowerEntry);
    }

    @Override // java.util.NavigableMap
    public final Object lowerKey(Object obj) {
        return g().lowerKey(obj);
    }

    @Override // java.util.NavigableMap
    public final NavigableSet navigableKeySet() {
        return (NavigableSet) super.keySet();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollFirstEntry() {
        return h(((d) entrySet()).iterator());
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollLastEntry() {
        return h(((d) ((f) descendingMap()).entrySet()).iterator());
    }

    @Override // com.google.common.collect.k, java.util.SortedMap, java.util.NavigableMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    @Override // com.google.common.collect.k, java.util.SortedMap, java.util.NavigableMap
    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    @Override // java.util.NavigableMap
    public final NavigableMap headMap(Object obj, boolean z) {
        return new h(this.f14014q, g().headMap(obj, z));
    }

    @Override // java.util.NavigableMap
    public final NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z5) {
        return new h(this.f14014q, g().subMap(obj, z, obj2, z5));
    }

    @Override // java.util.NavigableMap
    public final NavigableMap tailMap(Object obj, boolean z) {
        return new h(this.f14014q, g().tailMap(obj, z));
    }
}
