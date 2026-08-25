package oa;

import ka.l0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends e {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f7.j f20828o;

    /* JADX WARN: Multi-variable type inference failed */
    public i(r7.q qVar, na.j jVar, d7.j jVar2, int i10, int i11) {
        super(jVar, jVar2, i10, i11);
        this.f20828o = (f7.j) qVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [f7.j, r7.q] */
    @Override // oa.d
    public final d d(d7.j jVar, int i10, int i11) {
        return new i(this.f20828o, this.f20812n, jVar, i10, i11);
    }

    @Override // oa.e
    public final Object h(na.k kVar, d7.d dVar) {
        Object objC = l0.c(new f(this, kVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }
}
