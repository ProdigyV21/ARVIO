package oa;

import java.util.ArrayList;
import ka.c0;
import ka.k0;
import ka.l0;
import m2.f0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d7.j f20809i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f20810l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20811m;

    public d(d7.j jVar, int i10, int i11) {
        this.f20809i = jVar;
        this.f20810l = i10;
        this.f20811m = i11;
    }

    public String a() {
        return null;
    }

    public abstract Object c(ma.u uVar, d7.d dVar);

    @Override // na.j
    public Object collect(na.k kVar, d7.d dVar) {
        Object objC = l0.c(new androidx.work.impl.constraints.l(kVar, this, null, 4), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    public abstract d d(d7.j jVar, int i10, int i11);

    public na.j f() {
        return null;
    }

    public ma.w g(k0 k0Var) {
        int i10 = this.f20810l;
        if (i10 == -3) {
            i10 = -2;
        }
        r7.p cVar = new androidx.work.impl.constraints.controllers.c(this, null, 2);
        ma.t tVar = new ma.t(c0.b(k0Var, this.f20809i), f0.a(i10, this.f20811m, 4));
        tVar.S(3, tVar, cVar);
        return tVar;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        String strA = a();
        if (strA != null) {
            arrayList.add(strA);
        }
        d7.k kVar = d7.k.f14688i;
        d7.j jVar = this.f20809i;
        if (jVar != kVar) {
            arrayList.add("context=" + jVar);
        }
        int i10 = this.f20810l;
        if (i10 != -3) {
            arrayList.add("capacity=" + i10);
        }
        int i11 = this.f20811m;
        if (i11 != 1) {
            arrayList.add("onBufferOverflow=".concat(g8.b.v(i11)));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append('[');
        return androidx.compose.foundation.c.u(sb2, kotlin.collections.x.u0(arrayList, ", ", null, null, null, 62), ']');
    }
}
