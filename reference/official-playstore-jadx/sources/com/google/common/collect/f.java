package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class f extends AbstractMap {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient d f13983i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient s f13984l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient Map f13985m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ q f13986n;

    public f(q qVar, Map map) {
        this.f13986n = qVar;
        this.f13985m = map;
    }

    public final d1 b(Map.Entry entry) {
        Object key = entry.getKey();
        return new d1(key, this.f13986n.k(key, (Collection) entry.getValue()));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        q qVar = this.f13986n;
        if (this.f13985m == qVar.f14084o) {
            qVar.clear();
        } else {
            a0.d(new e(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map map = this.f13985m;
        map.getClass();
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        d dVar = this.f13983i;
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this, 0);
        this.f13983i = dVar2;
        return dVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return this == obj || this.f13985m.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        Map map = this.f13985m;
        map.getClass();
        try {
            obj2 = map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection == null) {
            return null;
        }
        return this.f13986n.k(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.f13985m.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        return this.f13986n.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Collection collection = (Collection) this.f13985m.remove(obj);
        if (collection == null) {
            return null;
        }
        q qVar = this.f13986n;
        Collection collectionJ = qVar.j();
        collectionJ.addAll(collection);
        qVar.f14085p -= collection.size();
        collection.clear();
        return collectionJ;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f13985m.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.f13985m.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        s sVar = this.f13984l;
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s(this);
        this.f13984l = sVar2;
        return sVar2;
    }
}
