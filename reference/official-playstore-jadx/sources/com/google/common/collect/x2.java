package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
@p0
@x4.f
public interface x2<K, V> {
    Map a();

    void clear();

    Collection entries();

    Collection get(Object obj);

    boolean isEmpty();

    Set keySet();

    boolean put(Object obj, Object obj2);

    boolean remove(Object obj, Object obj2);

    int size();

    Collection values();
}
