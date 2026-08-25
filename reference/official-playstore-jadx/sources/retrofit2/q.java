package retrofit2;

import gb.i;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f21564d;

    public q(q0 q0Var, i.a aVar, l lVar, e eVar) {
        super(q0Var, aVar, lVar);
        this.f21564d = eVar;
    }

    @Override // retrofit2.r
    public final Object b(y yVar, Object[] objArr) {
        c cVar = (c) this.f21564d.u(yVar);
        d7.d dVar = (d7.d) objArr[objArr.length - 1];
        try {
            ka.l lVar = new ka.l(1, t7.a.A(dVar));
            lVar.c(new t(cVar, 1));
            cVar.G(new u(lVar, 1));
            return lVar.r();
        } catch (Exception e5) {
            return b1.n(e5, dVar);
        }
    }
}
