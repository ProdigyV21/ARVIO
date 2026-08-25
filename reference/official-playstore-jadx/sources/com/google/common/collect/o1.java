package com.google.common.collect;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class o1 extends c1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f14077l;

    public o1(i1 i1Var) {
        this.f14077l = i1Var;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.f14077l.b(entry.getKey(), entry.getValue());
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        i1 i1Var = this.f14077l;
        i1Var.getClass();
        return new m1(i1Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f14077l.f14026p;
    }
}
