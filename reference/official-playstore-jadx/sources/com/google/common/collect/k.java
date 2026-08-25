package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public class k extends f implements SortedMap {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public SortedSet f14037o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ z2 f14038p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(z2 z2Var, SortedMap sortedMap) {
        super(z2Var, sortedMap);
        this.f14038p = z2Var;
    }

    public SortedSet c() {
        return new l(this.f14038p, g());
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return g().comparator();
    }

    @Override // com.google.common.collect.f, java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public SortedSet keySet() {
        SortedSet sortedSet = this.f14037o;
        if (sortedSet != null) {
            return sortedSet;
        }
        SortedSet sortedSetC = c();
        this.f14037o = sortedSetC;
        return sortedSetC;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return g().firstKey();
    }

    public SortedMap g() {
        return (SortedMap) this.f13985m;
    }

    public SortedMap headMap(Object obj) {
        return new k(this.f14038p, g().headMap(obj));
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return g().lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new k(this.f14038p, g().subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new k(this.f14038p, g().tailMap(obj));
    }
}
