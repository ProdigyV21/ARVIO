package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends m implements Set {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ w0 f14081p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(w0 w0Var, Object obj, Set set) {
        super(w0Var, obj, set, null);
        this.f14081p = w0Var;
    }

    @Override // com.google.common.collect.m, java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zR = a0.r((Set) this.f14052l, collection);
        if (zR) {
            this.f14081p.f14085p += this.f14052l.size() - size;
            c();
        }
        return zR;
    }
}
