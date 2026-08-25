package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class v2 extends AbstractMap {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14124i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f14125l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f14126m;

    public v2(n4 n4Var, Collection collection) {
        this.f14126m = n4Var;
        this.f14125l = collection;
    }

    public final void b() {
        a0.d(c());
    }

    public final Iterator c() {
        switch (this.f14124i) {
            case 0:
                Iterator it = ((Map) this.f14125l).entrySet().iterator();
                u2 u2Var = (u2) this.f14126m;
                u2Var.getClass();
                return new b2(it, new r2(u2Var, 1));
            default:
                return ((Iterable) this.f14125l).iterator();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        switch (this.f14124i) {
            case 0:
                ((Map) this.f14125l).clear();
                break;
            default:
                b();
                break;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        switch (this.f14124i) {
            case 0:
                return ((Map) this.f14125l).containsKey(obj);
            default:
                return get(obj) != null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new d(this, 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        switch (this.f14124i) {
            case 0:
                Map map = (Map) this.f14125l;
                Object obj2 = map.get(obj);
                if (obj2 != null || map.containsKey(obj)) {
                    return ((u2) this.f14126m).d(obj, obj2);
                }
                return null;
            default:
                if (obj instanceof j3) {
                    j3 j3Var = (j3) obj;
                    m4 m4Var = (m4) ((n4) this.f14126m).f14074a.get(j3Var.f14035i);
                    if (m4Var != null && m4Var.f14060i.equals(j3Var)) {
                        return m4Var.f14061l;
                    }
                }
                return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        switch (this.f14124i) {
            case 0:
                return ((Map) this.f14125l).keySet();
            default:
                return super.keySet();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        switch (this.f14124i) {
            case 0:
                Map map = (Map) this.f14125l;
                if (map.containsKey(obj)) {
                    return ((u2) this.f14126m).d(obj, map.remove(obj));
                }
                return null;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        switch (this.f14124i) {
            case 0:
                return ((Map) this.f14125l).size();
            default:
                return ((n4) this.f14126m).f14074a.size();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection values() {
        switch (this.f14124i) {
            case 0:
                return new s(this);
            default:
                return super.values();
        }
    }

    public v2(Map map, u2 u2Var) {
        map.getClass();
        this.f14125l = map;
        this.f14126m = u2Var;
    }
}
