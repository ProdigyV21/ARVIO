package i9;

import g8.c0;
import g8.c1;
import g8.g1;
import g8.p0;
import g8.q0;
import g8.x;
import v9.b0;
import v9.b1;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f16250a = 0;

    static {
        f9.b.j(new f9.c("kotlin.jvm.JvmInline"));
    }

    public static final boolean a(g8.c cVar) {
        c1 c1VarS;
        if (!(cVar instanceof q0)) {
            return false;
        }
        p0 p0VarT = ((q0) cVar).T();
        if (p0VarT.M() != null) {
            return false;
        }
        g8.k kVarD = p0VarT.d();
        g8.f fVar = kVarD instanceof g8.f ? (g8.f) kVarD : null;
        return (fVar == null || (c1VarS = fVar.S()) == null || !c1VarS.a(p0VarT.getName())) ? false : true;
    }

    public static final boolean b(g8.k kVar) {
        return (kVar instanceof g8.f) && (((g8.f) kVar).S() instanceof x);
    }

    public static final boolean c(g8.k kVar) {
        return (kVar instanceof g8.f) && (((g8.f) kVar).S() instanceof c0);
    }

    public static final boolean d(g1 g1Var) {
        if (g1Var.M() != null) {
            return false;
        }
        g8.k kVarD = g1Var.d();
        f9.f fVar = null;
        g8.f fVar2 = kVarD instanceof g8.f ? (g8.f) kVarD : null;
        if (fVar2 != null) {
            int i10 = m9.d.f20302a;
            c1 c1VarS = fVar2.S();
            x xVar = c1VarS instanceof x ? (x) c1VarS : null;
            if (xVar != null) {
                fVar = xVar.f15556a;
            }
        }
        return kotlin.jvm.internal.p.a(fVar, g1Var.getName());
    }

    public static final boolean e(g8.k kVar) {
        return b(kVar) || c(kVar);
    }

    public static final boolean f(w wVar) {
        g8.h hVarE = wVar.I0().e();
        return (hVarE == null || !c(hVarE) || b1.e(wVar)) ? false : true;
    }

    public static final b0 g(w wVar) {
        g8.h hVarE = wVar.I0().e();
        g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
        if (fVar != null) {
            int i10 = m9.d.f20302a;
            c1 c1VarS = fVar.S();
            x xVar = c1VarS instanceof x ? (x) c1VarS : null;
            if (xVar != null) {
                return (b0) xVar.f15557b;
            }
        }
        return null;
    }
}
