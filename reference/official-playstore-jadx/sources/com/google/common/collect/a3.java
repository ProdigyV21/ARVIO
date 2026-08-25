package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class a3 extends u implements g2 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final g2 f13935o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a8.e f13936p;

    public a3(g2 g2Var, a8.e eVar) {
        g2Var.getClass();
        this.f13935o = g2Var;
        this.f13936p = eVar;
    }

    @Override // com.google.common.collect.x2
    public final void clear() {
        this.f13935o.clear();
    }

    @Override // com.google.common.collect.u
    public final Map d() {
        return new v2(this.f13935o.a(), new a3.b(this, 15));
    }

    @Override // com.google.common.collect.u
    public final Collection e() {
        return new s(this, 0);
    }

    @Override // com.google.common.collect.u
    public final Set f() {
        return this.f13935o.keySet();
    }

    @Override // com.google.common.collect.u
    public final Collection g() {
        return new b0(this.f13935o.entries(), new r2(this.f13936p, 0));
    }

    @Override // com.google.common.collect.u
    public final Iterator h() {
        return new b2(this.f13935o.entries().iterator(), new r2(this.f13936p, 1));
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final boolean isEmpty() {
        return this.f13935o.isEmpty();
    }

    @Override // com.google.common.collect.x2
    public final boolean put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.u, com.google.common.collect.x2
    public final boolean remove(Object obj, Object obj2) {
        return get(obj).remove(obj2);
    }

    @Override // com.google.common.collect.x2
    public final int size() {
        return this.f13935o.size();
    }

    @Override // com.google.common.collect.x2
    public final List get(Object obj) {
        return a0.x((List) this.f13935o.get(obj), new q2(this.f13936p, obj));
    }
}
