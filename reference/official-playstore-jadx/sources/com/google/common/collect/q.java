package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q extends u implements Serializable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient Map f14084o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public transient int f14085p;

    public q(Map map) {
        ac.b.j(map.isEmpty());
        this.f14084o = map;
    }

    @Override // com.google.common.collect.x2
    public final void clear() {
        Map map = this.f14084o;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        map.clear();
        this.f14085p = 0;
    }

    @Override // com.google.common.collect.u
    public Map d() {
        return new f(this, this.f14084o);
    }

    @Override // com.google.common.collect.u
    public final Collection e() {
        return this instanceof z3 ? new t(this, 0) : new s(this, 0);
    }

    @Override // com.google.common.collect.u
    public Set f() {
        return new g(this, this.f14084o);
    }

    @Override // com.google.common.collect.u
    public final Collection g() {
        return new s(this, 1);
    }

    @Override // com.google.common.collect.x2
    public Collection get(Object obj) {
        Collection collectionJ = (Collection) this.f14084o.get(obj);
        if (collectionJ == null) {
            collectionJ = j();
        }
        return k(obj, collectionJ);
    }

    @Override // com.google.common.collect.u
    public final Iterator h() {
        return new c(this, 1);
    }

    public abstract Collection j();

    public abstract Collection k(Object obj, Collection collection);

    @Override // com.google.common.collect.x2
    public boolean put(Object obj, Object obj2) {
        Map map = this.f14084o;
        Collection collection = (Collection) map.get(obj);
        if (collection != null) {
            if (!collection.add(obj2)) {
                return false;
            }
            this.f14085p++;
            return true;
        }
        Collection collectionJ = j();
        if (!collectionJ.add(obj2)) {
            throw new AssertionError("New Collection violated the Collection spec");
        }
        this.f14085p++;
        map.put(obj, collectionJ);
        return true;
    }

    @Override // com.google.common.collect.x2
    public final int size() {
        return this.f14085p;
    }
}
