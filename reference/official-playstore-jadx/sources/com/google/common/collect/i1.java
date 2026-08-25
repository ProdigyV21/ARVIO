package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class i1 extends w implements g2, Serializable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient t3 f14025o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f14026p;

    public i1(t3 t3Var, int i10) {
        this.f14025o = t3Var;
        this.f14026p = i10;
    }

    @Override // com.google.common.collect.u
    public final boolean c(Object obj) {
        return obj != null && super.c(obj);
    }

    @Override // com.google.common.collect.x2
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.u
    public final Map d() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.u
    public final Collection e() {
        return new o1(this);
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final Collection entries() {
        return (c1) super.entries();
    }

    @Override // com.google.common.collect.u
    public final Set f() {
        throw new AssertionError("unreachable");
    }

    @Override // com.google.common.collect.u
    public final Collection g() {
        return new p1(this);
    }

    @Override // com.google.common.collect.u
    public final Iterator h() {
        return new m1(this);
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public l1 a() {
        return this.f14025o;
    }

    @Override // com.google.common.collect.x2
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final h1 get(Object obj) {
        h1 h1Var = (h1) this.f14025o.get(obj);
        if (h1Var != null) {
            return h1Var;
        }
        f1 f1Var = h1.f14020l;
        return o3.f14078o;
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final Set keySet() {
        return this.f14025o.keySet();
    }

    @Override // com.google.common.collect.x2
    public final boolean put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.x2
    public final int size() {
        return this.f14026p;
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final Collection values() {
        return (c1) super.values();
    }
}
