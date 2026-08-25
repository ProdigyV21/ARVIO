package g8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f15558a = new c2.a("InvalidModuleNotifier", 2);

    public static final androidx.appcompat.app.i1 a(v9.b0 b0Var, i iVar, int i10) {
        if (iVar == null || x9.k.e(iVar)) {
            return null;
        }
        int size = iVar.n().size() + i10;
        if (iVar.i()) {
            List listSubList = b0Var.G0().subList(i10, size);
            k kVarD = iVar.d();
            return new androidx.appcompat.app.i1(iVar, listSubList, a(b0Var, kVarD instanceof i ? (i) kVarD : null, size), 15);
        }
        if (size != b0Var.G0().size()) {
            i9.f.o(iVar);
        }
        return new androidx.appcompat.app.i1(iVar, b0Var.G0().subList(i10, b0Var.G0().size()), iVar, 15);
    }

    public static final void b(i0 i0Var, f9.c cVar, ArrayList arrayList) {
        if (i0Var instanceof l0) {
            ((l0) i0Var).b(cVar, arrayList);
        } else {
            arrayList.addAll(i0Var.c(cVar));
        }
    }

    public static final List c(i iVar) {
        List parameters;
        Object next;
        v9.o0 o0VarG;
        List listN = iVar.n();
        if (!iVar.i() && !(iVar.d() instanceof a)) {
            return listN;
        }
        int i10 = m9.d.f20302a;
        m9.c cVar = m9.c.f20301i;
        List listU = ga.r.U(ga.r.L(ga.r.I(new ga.c(ga.r.H(ga.r.M(iVar, cVar), 1), v.f15550n, 1), v.f15551o), v.f15552p));
        Iterator it = ga.r.H(ga.r.M(iVar, cVar), 1).iterator();
        while (true) {
            parameters = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof f) {
                break;
            }
        }
        f fVar = (f) next;
        if (fVar != null && (o0VarG = fVar.g()) != null) {
            parameters = o0VarG.getParameters();
        }
        if (parameters == null) {
            parameters = kotlin.collections.z.f19728i;
        }
        if (listU.isEmpty() && parameters.isEmpty()) {
            return iVar.n();
        }
        ArrayList arrayListI0 = kotlin.collections.x.I0(listU, parameters);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(arrayListI0, 10));
        Iterator it2 = arrayListI0.iterator();
        while (it2.hasNext()) {
            arrayList.add(new d((b1) it2.next(), iVar, listN.size()));
        }
        return kotlin.collections.x.I0(listN, arrayList);
    }

    public static final f d(b0 b0Var, f9.b bVar) {
        h hVarE = e(b0Var, bVar);
        if (hVarE instanceof f) {
            return (f) hVarE;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0123 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final g8.h e(g8.b0 r7, f9.b r8) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.y.e(g8.b0, f9.b):g8.h");
    }

    public static final f f(b0 b0Var, f9.b bVar, androidx.emoji2.text.e0 e0Var) {
        f fVarD = d(b0Var, bVar);
        return fVarD != null ? fVarD : e0Var.v(bVar, ga.r.U(new ga.t(ga.r.M(bVar, u.f15547i), v.f15548l)));
    }

    public static final h g(k kVar) {
        k kVarD = kVar.d();
        if (kVarD == null || (kVar instanceof h0)) {
            return null;
        }
        if (!(kVarD.d() instanceof h0)) {
            return g(kVarD);
        }
        if (kVarD instanceof h) {
            return (h) kVarD;
        }
        return null;
    }

    public static final boolean h(i0 i0Var, f9.c cVar) {
        if (i0Var instanceof l0) {
            return ((l0) i0Var).a(cVar);
        }
        ArrayList arrayList = new ArrayList();
        b(i0Var, cVar, arrayList);
        return arrayList.isEmpty();
    }

    public static final f i(b0 b0Var, f9.c cVar) {
        p9.n nVarR;
        if (!cVar.d()) {
            p9.n nVarL = b0Var.B(cVar.e()).l();
            f9.f fVarF = cVar.f();
            o8.e eVar = o8.e.f20793i;
            h hVarE = ((p9.j) nVarL).e(fVarF, eVar);
            f fVar = hVarE instanceof f ? (f) hVarE : null;
            if (fVar != null) {
                return fVar;
            }
            f fVarI = i(b0Var, cVar.e());
            h hVarE2 = (fVarI == null || (nVarR = fVarI.R()) == null) ? null : nVarR.e(cVar.f(), eVar);
            if (hVarE2 instanceof f) {
                return (f) hVarE2;
            }
        }
        return null;
    }
}
