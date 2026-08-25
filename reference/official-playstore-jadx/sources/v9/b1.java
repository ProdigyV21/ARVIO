package v9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x9.h f22188a = x9.k.b(x9.j.f22701v, new String[0]);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final x9.h f22189b = x9.k.b(x9.j.f22698s, new String[0]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a1 f22190c = new a1("NO_EXPECTED_TYPE");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a1 f22191d = new a1("UNIT_EXPECTED_TYPE");

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r27) {
        /*
            Method dump skipped, instruction units count: 774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.b1.a(int):void");
    }

    public static boolean b(w wVar) {
        if (wVar == null) {
            a(28);
            throw null;
        }
        if (wVar.J0()) {
            return true;
        }
        return (wVar.L0() instanceof q) && b(((q) wVar.L0()).f22242m);
    }

    public static boolean c(w wVar, r7.l lVar, ea.l lVar2) {
        if (wVar == null) {
            return false;
        }
        d1 d1VarL0 = wVar.L0();
        if (l(wVar)) {
            return ((Boolean) lVar.invoke(d1VarL0)).booleanValue();
        }
        if (lVar2 != null && lVar2.contains(wVar)) {
            return false;
        }
        if (((Boolean) lVar.invoke(d1VarL0)).booleanValue()) {
            return true;
        }
        if (lVar2 == null) {
            lVar2 = new ea.l();
        }
        lVar2.add(wVar);
        q qVar = d1VarL0 instanceof q ? (q) d1VarL0 : null;
        if (qVar != null && (c(qVar.f22241l, lVar, lVar2) || c(qVar.f22242m, lVar, lVar2))) {
            return true;
        }
        if ((d1VarL0 instanceof m) && c(((m) d1VarL0).f22223l, lVar, lVar2)) {
            return true;
        }
        o0 o0VarI0 = wVar.I0();
        if (o0VarI0 instanceof v) {
            Iterator it = ((v) o0VarI0).f22254b.iterator();
            while (it.hasNext()) {
                if (c((w) it.next(), lVar, lVar2)) {
                    return true;
                }
            }
            return false;
        }
        for (r0 r0Var : wVar.G0()) {
            if (!r0Var.b()) {
                if (c(r0Var.getType(), lVar, lVar2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List d(List list) {
        if (list == null) {
            a(16);
            throw null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new t0(((g8.b1) it.next()).m()));
        }
        return kotlin.collections.x.c1(arrayList);
    }

    public static boolean e(w wVar) {
        if (wVar == null) {
            a(27);
            throw null;
        }
        if (!wVar.J0() && (!(wVar.L0() instanceof q) || !e(((q) wVar.L0()).f22242m))) {
            if (!(wVar.L0() instanceof m)) {
                if (f(wVar)) {
                    if (!(wVar.I0().e() instanceof g8.f)) {
                        y0 y0VarD = y0.d(wVar);
                        Collection<w> collectionD = wVar.I0().d();
                        ArrayList arrayList = new ArrayList(collectionD.size());
                        for (w wVar2 : collectionD) {
                            if (wVar2 == null) {
                                a(21);
                                throw null;
                            }
                            w wVarI = y0VarD.i(1, wVar2);
                            w wVarH = wVarI != null ? h(wVarI, wVar.J0()) : null;
                            if (wVarH != null) {
                                arrayList.add(wVarH);
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (e((w) it.next())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                o0 o0VarI0 = wVar.I0();
                if (o0VarI0 instanceof v) {
                    Iterator it2 = ((v) o0VarI0).f22254b.iterator();
                    while (it2.hasNext()) {
                        if (e((w) it2.next())) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static boolean f(w wVar) {
        if (wVar != null) {
            return (wVar.I0().e() instanceof g8.b1 ? (g8.b1) wVar.I0().e() : null) != null || (wVar.I0() instanceof w9.m);
        }
        a(60);
        throw null;
    }

    public static d1 g(w wVar, boolean z) {
        if (wVar == null) {
            a(3);
            throw null;
        }
        d1 d1VarM0 = wVar.L0().M0(z);
        if (d1VarM0 != null) {
            return d1VarM0;
        }
        a(4);
        throw null;
    }

    public static w h(w wVar, boolean z) {
        if (wVar != null) {
            return z ? g(wVar, true) : wVar;
        }
        a(8);
        throw null;
    }

    public static b0 i(b0 b0Var, boolean z) {
        if (b0Var == null) {
            a(5);
            throw null;
        }
        if (!z) {
            return b0Var;
        }
        b0 b0VarM0 = b0Var.M0(true);
        if (b0VarM0 != null) {
            return b0VarM0;
        }
        a(6);
        throw null;
    }

    public static g0 j(g8.b1 b1Var) {
        if (b1Var != null) {
            return new g0(b1Var);
        }
        a(45);
        throw null;
    }

    public static s0 k(g8.b1 b1Var, u8.a aVar) {
        if (b1Var != null) {
            return aVar.f22057b == 1 ? new t0(1, c.o(b1Var)) : new g0(b1Var);
        }
        a(46);
        throw null;
    }

    public static boolean l(w wVar) {
        if (wVar != null) {
            return wVar == f22190c || wVar == f22191d;
        }
        a(0);
        throw null;
    }
}
