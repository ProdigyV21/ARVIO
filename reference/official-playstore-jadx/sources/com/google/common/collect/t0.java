package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class t0 extends u0 implements Map {
    @Override // java.util.Map
    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(Object obj) {
        return delegate().containsKey(obj);
    }

    @Override // com.google.common.collect.u0
    public abstract Map delegate();

    public Set entrySet() {
        return delegate().entrySet();
    }

    public Object get(Object obj) {
        return delegate().get(obj);
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Set keySet() {
        return delegate().keySet();
    }

    @Override // java.util.Map
    @q6.a
    public Object put(@h3 Object obj, @h3 Object obj2) {
        return delegate().put(obj, obj2);
    }

    @Override // java.util.Map
    public void putAll(Map<Object, Object> map) {
        delegate().putAll(map);
    }

    @Override // java.util.Map
    @q6.a
    public Object remove(@q6.a Object obj) {
        return delegate().remove(obj);
    }

    public int size() {
        return delegate().size();
    }

    public void standardClear() {
        a0.d(entrySet().iterator());
    }

    public boolean standardContainsKey(@q6.a Object obj) {
        return a0.e(new t2(entrySet().iterator(), 0), obj);
    }

    public boolean standardContainsValue(@q6.a Object obj) {
        return a0.e(new t2(entrySet().iterator(), 1), obj);
    }

    public boolean standardEquals(@q6.a Object obj) {
        return w2.b(this, obj);
    }

    public int standardHashCode() {
        return a0.l(entrySet());
    }

    public boolean standardIsEmpty() {
        return !entrySet().iterator().hasNext();
    }

    public void standardPutAll(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @q6.a
    public Object standardRemove(@q6.a Object obj) {
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (a.a.L(entry.getKey(), obj)) {
                Object value = entry.getValue();
                it.remove();
                return value;
            }
        }
        return null;
    }

    public String standardToString() {
        return w2.c(this);
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return delegate().values();
    }
}
