package com.google.common.collect;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class q3 extends w1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient l1 f14092n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient Object[] f14093o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f14094p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f14095q;

    public q3(l1 l1Var, Object[] objArr, int i10, int i11) {
        this.f14092n = l1Var;
        this.f14093o = objArr;
        this.f14094p = i10;
        this.f14095q = i11;
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        return a().b(i10, objArr);
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f14092n.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return true;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return a().listIterator(0);
    }

    @Override // com.google.common.collect.w1
    public final h1 n() {
        return new p3(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14095q;
    }
}
