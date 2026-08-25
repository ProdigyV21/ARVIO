package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 extends q implements z3 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f14131q;

    public w0() {
        super(f0.c(12));
        this.f14131q = 2;
        this.f14131q = 2;
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final Collection entries() {
        return (Set) super.entries();
    }

    @Override // com.google.common.collect.q, com.google.common.collect.x2
    public final Collection get(Object obj) {
        return (Set) super.get(obj);
    }

    @Override // com.google.common.collect.q
    public final Collection j() {
        return h0.a(this.f14131q);
    }

    @Override // com.google.common.collect.q
    public final Collection k(Object obj, Collection collection) {
        return new p(this, obj, (Set) collection);
    }

    public final Set l(Object obj) {
        return (Set) super.get(obj);
    }
}
