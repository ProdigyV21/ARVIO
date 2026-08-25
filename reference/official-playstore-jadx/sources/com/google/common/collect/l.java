package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public class l extends g implements SortedSet {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ z2 f14043m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(z2 z2Var, SortedMap sortedMap) {
        super(z2Var, sortedMap);
        this.f14043m = z2Var;
    }

    public SortedMap a() {
        return (SortedMap) this.f14005i;
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return a().comparator();
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return a().firstKey();
    }

    public SortedSet headSet(Object obj) {
        return new l(this.f14043m, a().headMap(obj));
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return a().lastKey();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return new l(this.f14043m, a().subMap(obj, obj2));
    }

    public SortedSet tailSet(Object obj) {
        return new l(this.f14043m, a().tailMap(obj));
    }
}
