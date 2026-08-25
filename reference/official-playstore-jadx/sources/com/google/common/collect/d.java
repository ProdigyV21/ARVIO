package com.google.common.collect;

import j$.util.Objects;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends d4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13959i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AbstractMap f13960l;

    public /* synthetic */ d(AbstractMap abstractMap, int i10) {
        this.f13959i = i10;
        this.f13960l = abstractMap;
    }

    public final boolean a(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Map mapB = b();
        mapB.getClass();
        try {
            obj2 = mapB.get(key);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        if (a.a.L(obj2, entry.getValue())) {
            return obj2 != null || b().containsKey(key);
        }
        return false;
    }

    public final Map b() {
        switch (this.f13959i) {
            case 0:
                return (f) this.f13960l;
            default:
                return (v2) this.f13960l;
        }
    }

    public final boolean c(Object obj) {
        if (contains(obj) && (obj instanceof Map.Entry)) {
            return b().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        b().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        switch (this.f13959i) {
            case 0:
                Set setEntrySet = ((f) this.f13960l).f13985m.entrySet();
                setEntrySet.getClass();
                try {
                    return setEntrySet.contains(obj);
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            default:
                return a(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return b().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f13959i) {
            case 0:
                return new e((f) this.f13960l);
            default:
                return ((v2) this.f13960l).c();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        Object objRemove;
        switch (this.f13959i) {
            case 0:
                if (!contains(obj)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Objects.requireNonNull(entry);
                q qVar = ((f) this.f13960l).f13986n;
                Object key = entry.getKey();
                Map map = qVar.f14084o;
                map.getClass();
                try {
                    objRemove = map.remove(key);
                    break;
                } catch (ClassCastException | NullPointerException unused) {
                    objRemove = null;
                }
                Collection collection = (Collection) objRemove;
                if (collection != null) {
                    int size = collection.size();
                    collection.clear();
                    qVar.f14085p -= size;
                }
                return true;
            default:
                return c(obj);
        }
    }

    @Override // com.google.common.collect.d4, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        try {
            collection.getClass();
            return a0.r(this, collection);
        } catch (UnsupportedOperationException unused) {
            Iterator it = collection.iterator();
            boolean zRemove = false;
            while (it.hasNext()) {
                zRemove |= remove(it.next());
            }
            return zRemove;
        }
    }

    @Override // com.google.common.collect.d4, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        try {
            collection.getClass();
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            HashSet hashSet = new HashSet(w2.a(collection.size()));
            for (Object obj : collection) {
                if (contains(obj) && (obj instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) obj).getKey());
                }
            }
            return b().keySet().retainAll(hashSet);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return b().size();
    }
}
