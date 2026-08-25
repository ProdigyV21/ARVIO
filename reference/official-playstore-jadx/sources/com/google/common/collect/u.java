package com.google.common.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class u implements x2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient Collection f14118i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient Set f14119l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient Collection f14120m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient Map f14121n;

    @Override // com.google.common.collect.x2
    public Map a() {
        Map map = this.f14121n;
        if (map != null) {
            return map;
        }
        Map mapD = d();
        this.f14121n = mapD;
        return mapD;
    }

    public final boolean b(Object obj, Object obj2) {
        Collection collection = (Collection) a().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public boolean c(Object obj) {
        Iterator it = a().values().iterator();
        while (it.hasNext()) {
            if (((Collection) it.next()).contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public abstract Map d();

    public abstract Collection e();

    @Override // com.google.common.collect.x2
    public Collection entries() {
        Collection collection = this.f14118i;
        if (collection != null) {
            return collection;
        }
        Collection collectionE = e();
        this.f14118i = collectionE;
        return collectionE;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof x2) {
            return a().equals(((x2) obj).a());
        }
        return false;
    }

    public abstract Set f();

    public abstract Collection g();

    public abstract Iterator h();

    public final int hashCode() {
        return a().hashCode();
    }

    public final void i(String str, ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            return;
        }
        get(str).addAll(arrayList);
    }

    @Override // com.google.common.collect.x2
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.x2
    public Set keySet() {
        Set set = this.f14119l;
        if (set != null) {
            return set;
        }
        Set setF = f();
        this.f14119l = setF;
        return setF;
    }

    @Override // com.google.common.collect.x2
    public boolean remove(Object obj, Object obj2) {
        Collection collection = (Collection) a().get(obj);
        return collection != null && collection.remove(obj2);
    }

    public final String toString() {
        return a().toString();
    }

    @Override // com.google.common.collect.x2
    public Collection values() {
        Collection collection = this.f14120m;
        if (collection != null) {
            return collection;
        }
        Collection collectionG = g();
        this.f14120m = collectionG;
        return collectionG;
    }
}
