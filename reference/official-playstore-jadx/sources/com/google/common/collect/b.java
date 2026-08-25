package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b extends q implements g2 {
    @Override // com.google.common.collect.q, com.google.common.collect.x2
    public final Collection get(Object obj) {
        return (List) super.get(obj);
    }

    @Override // com.google.common.collect.q
    public final Collection k(Object obj, Collection collection) {
        List list = (List) collection;
        return list instanceof RandomAccess ? new j(this, obj, list, null) : new o(this, obj, list, null);
    }

    @Override // com.google.common.collect.q, com.google.common.collect.x2
    public final List get(Object obj) {
        return (List) super.get(obj);
    }
}
