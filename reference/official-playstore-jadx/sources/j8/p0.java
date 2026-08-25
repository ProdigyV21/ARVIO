package j8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 extends p9.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g8.b0 f19331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f9.c f19332c;

    public p0(g8.b0 b0Var, f9.c cVar) {
        this.f19331b = b0Var;
        this.f19332c = cVar;
    }

    @Override // p9.o, p9.n
    public final Set d() {
        return kotlin.collections.b0.f19686i;
    }

    @Override // p9.o, p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        if (fVar.a(p9.f.f21208h)) {
            f9.c cVar = this.f19332c;
            if (!cVar.d() || !fVar.f21219a.contains(p9.c.f21200a)) {
                g8.b0 b0Var = this.f19331b;
                Collection collectionP = b0Var.p(cVar, lVar);
                ArrayList arrayList = new ArrayList(collectionP.size());
                Iterator it = collectionP.iterator();
                while (it.hasNext()) {
                    f9.f fVarF = ((f9.c) it.next()).f();
                    if (((Boolean) lVar.invoke(fVarF)).booleanValue()) {
                        g8.m0 m0Var = null;
                        if (!fVarF.f15369l) {
                            g8.m0 m0VarB = b0Var.B(cVar.c(fVarF));
                            if (!m0VarB.isEmpty()) {
                                m0Var = m0VarB;
                            }
                        }
                        ea.o.d(arrayList, m0Var);
                    }
                }
                return arrayList;
            }
        }
        return kotlin.collections.z.f19728i;
    }

    public final String toString() {
        return "subpackages of " + this.f19332c + " from " + this.f19331b;
    }
}
