package ka;

import d7.j;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s0 {
    public static final Object a(long j10, d7.d dVar) {
        if (j10 > 0) {
            l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            if (j10 < Long.MAX_VALUE) {
                b(lVar.f19609o).y(j10, lVar);
            }
            Object objR = lVar.r();
            if (objR == e7.a.f15033i) {
                return objR;
            }
        }
        return x6.t0.f22605a;
    }

    public static final r0 b(d7.j jVar) {
        j.a aVar = jVar.get(d7.f.f14687i);
        r0 r0Var = aVar instanceof r0 ? (r0) aVar : null;
        return r0Var == null ? o0.f19625a : r0Var;
    }
}
