package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class s extends AbstractCollection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14102i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f14103l;

    public /* synthetic */ s(Object obj, int i10) {
        this.f14102i = i10;
        this.f14103l = obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        switch (this.f14102i) {
            case 0:
                ((u) this.f14103l).clear();
                break;
            case 1:
                ((q) this.f14103l).clear();
                break;
            case 2:
                ((f0) this.f14103l).clear();
                break;
            default:
                ((AbstractMap) this.f14103l).clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        switch (this.f14102i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return ((u) this.f14103l).b(entry.getKey(), entry.getValue());
            case 1:
                return ((q) this.f14103l).c(obj);
            case 2:
            default:
                return super.contains(obj);
            case 3:
                return ((AbstractMap) this.f14103l).containsValue(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        switch (this.f14102i) {
            case 3:
                return ((AbstractMap) this.f14103l).isEmpty();
            default:
                return super.isEmpty();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.f14102i) {
            case 0:
                return ((u) this.f14103l).h();
            case 1:
                return new c((q) this.f14103l, 0);
            case 2:
                f0 f0Var = (f0) this.f14103l;
                Map mapE = f0Var.e();
                return mapE != null ? mapE.values().iterator() : new c0(f0Var, 2);
            default:
                return new t2(((AbstractMap) this.f14103l).entrySet().iterator(), 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        switch (this.f14102i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return ((u) this.f14103l).remove(entry.getKey(), entry.getValue());
            case 3:
                AbstractMap abstractMap = (AbstractMap) this.f14103l;
                try {
                    return super.remove(obj);
                } catch (UnsupportedOperationException unused) {
                    for (Map.Entry entry2 : abstractMap.entrySet()) {
                        if (a.a.L(obj, entry2.getValue())) {
                            abstractMap.remove(entry2.getKey());
                            return true;
                        }
                    }
                    return false;
                }
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        switch (this.f14102i) {
            case 3:
                AbstractMap abstractMap = (AbstractMap) this.f14103l;
                try {
                    collection.getClass();
                    return super.removeAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    for (Map.Entry entry : abstractMap.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return abstractMap.keySet().removeAll(hashSet);
                }
            default:
                return super.removeAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        switch (this.f14102i) {
            case 3:
                AbstractMap abstractMap = (AbstractMap) this.f14103l;
                try {
                    collection.getClass();
                    return super.retainAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    for (Map.Entry entry : abstractMap.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return abstractMap.keySet().retainAll(hashSet);
                }
            default:
                return super.retainAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        switch (this.f14102i) {
            case 0:
                return ((u) this.f14103l).size();
            case 1:
                return ((q) this.f14103l).f14085p;
            case 2:
                return ((f0) this.f14103l).size();
            default:
                return ((AbstractMap) this.f14103l).size();
        }
    }

    public s(AbstractMap abstractMap) {
        this.f14102i = 3;
        this.f14103l = abstractMap;
    }
}
