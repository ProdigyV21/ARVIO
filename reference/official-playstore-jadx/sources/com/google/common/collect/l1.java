package com.google.common.collect;

import j$.util.Map;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l1 implements Map, Serializable, j$.util.Map {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient w1 f14047i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient w1 f14048l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient c1 f14049m;

    public static l1 b(Map map) {
        if ((map instanceof l1) && !(map instanceof SortedMap)) {
            return (l1) map;
        }
        Set setEntrySet = map.entrySet();
        k1 k1Var = new k1(setEntrySet instanceof Collection ? setEntrySet.size() : 4);
        k1Var.e(setEntrySet);
        return k1Var.a(true);
    }

    public abstract q3 c();

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public abstract r3 e();

    @Override // java.util.Map
    public final Set entrySet() {
        w1 w1Var = this.f14047i;
        if (w1Var != null) {
            return w1Var;
        }
        q3 q3VarC = c();
        this.f14047i = q3VarC;
        return q3VarC;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return w2.b(this, obj);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    public abstract c1 g();

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final w1 keySet() {
        w1 w1Var = this.f14048l;
        if (w1Var != null) {
            return w1Var;
        }
        r3 r3VarE = e();
        this.f14048l = r3VarE;
        return r3VarE;
    }

    @Override // java.util.Map
    public final int hashCode() {
        w1 w1VarC = this.f14047i;
        if (w1VarC == null) {
            w1VarC = c();
            this.f14047i = w1VarC;
        }
        return a0.l(w1VarC);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public c1 values() {
        c1 c1Var = this.f14049m;
        if (c1Var != null) {
            return c1Var;
        }
        c1 c1VarG = g();
        this.f14049m = c1VarG;
        return c1VarG;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(java.util.Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean remove(Object obj, Object obj2) {
        return Map.CC.$default$remove(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public final String toString() {
        return w2.c(this);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }
}
