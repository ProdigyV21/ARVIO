package com.google.common.collect;

import com.google.common.collect.b3;
import j$.util.Collection;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class t1 extends u1 implements b3, Collection {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f14110n = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient o3 f14111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient w1 f14112m;

    @Override // com.google.common.collect.c1
    public final h1 a() {
        o3 o3Var = this.f14111l;
        if (o3Var != null) {
            return o3Var;
        }
        h1 h1VarA = super.a();
        this.f14111l = (o3) h1VarA;
        return h1VarA;
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        o4 it = D0().iterator();
        while (it.hasNext()) {
            b3.a aVar = (b3.a) it.next();
            Arrays.fill(objArr, i10, aVar.getCount() + i10, aVar.a());
            i10 += aVar.getCount();
        }
        return i10;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return ((v3) this).f14128o.b(obj) > 0;
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b3)) {
            return false;
        }
        b3 b3Var = (b3) obj;
        v3 v3Var = (v3) this;
        if (v3Var.f14129p != b3Var.size() || D0().size() != b3Var.D0().size()) {
            return false;
        }
        for (b3.a aVar : b3Var.D0()) {
            if (v3Var.f14128o.b(aVar.a()) != aVar.getCount()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return new q1(D0().iterator());
    }

    @Override // com.google.common.collect.b3
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final w1 D0() {
        w1 s1Var = this.f14112m;
        if (s1Var == null) {
            s1Var = isEmpty() ? w3.f14135t : new s1(this);
            this.f14112m = s1Var;
        }
        return s1Var;
    }

    @Override // java.util.Collection
    public final int hashCode() {
        return a0.l(D0());
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return D0().toString();
    }
}
