package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class p1 implements l6.a, x0, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c f14609i;

    public p1(c cVar) {
        this.f14609i = cVar;
    }

    public static g i(c cVar, String str, int i10, g1 g1Var) {
        cVar.getClass();
        try {
            g gVarS = cVar.S(str);
            if (gVarS == null) {
                throw new ConfigException.Missing(cVar.f14537i, a0.c.l("No configuration setting found for key '", g1Var.e(), "'"), null);
            }
            if (i10 != 0) {
                gVarS = r.b(gVarS, i10);
            }
            if (i10 == 0 || gVarS.b() == i10 || gVarS.b() == 5) {
                return gVarS;
            }
            throw new ConfigException.WrongType(gVarS.f14537i, g1Var.e(), g8.b.q(i10), g8.b.q(gVarS.b()));
        } catch (ConfigException.NotResolved e5) {
            throw x.c(g1Var, e5);
        }
    }

    public static g j(c cVar, g1 g1Var, int i10, g1 g1Var2) {
        try {
            String str = g1Var.f14541a;
            g1 g1Var3 = g1Var.f14542b;
            if (g1Var3 == null) {
                return i(cVar, str, i10, g1Var2);
            }
            g1 g1VarF = g1Var2.f(g1Var2.b() - g1Var3.b());
            g gVarI = i(cVar, str, 1, g1VarF);
            o(gVarI, 1, g1VarF);
            return j((c) gVarI, g1Var3, i10, g1Var2);
        } catch (ConfigException.NotResolved e5) {
            throw x.c(g1Var, e5);
        }
    }

    public static void l(HashSet hashSet, g1 g1Var, c cVar) {
        for (Map.Entry<String, l6.o> entry : cVar.entrySet()) {
            String key = entry.getKey();
            l6.o value = entry.getValue();
            g1 g1Var2 = new g1(key, null);
            if (g1Var != null) {
                h1 h1Var = new h1();
                h1Var.a(g1Var);
                h1Var.a(g1Var2);
                g1Var2 = h1Var.b();
            }
            if (value instanceof c) {
                l(hashSet, g1Var2, (c) value);
            } else if (!(value instanceof m0)) {
                hashSet.add(new AbstractMap.SimpleImmutableEntry(g1Var2.e(), value));
            }
        }
    }

    public static void o(g gVar, int i10, g1 g1Var) {
        if (gVar.b() == 5) {
            w1 w1Var = gVar.f14537i;
            String strE = g1Var.e();
            String strQ = i10 != 0 ? g8.b.q(i10) : null;
            throw new ConfigException.Null(w1Var, strQ != null ? androidx.compose.material3.d.n("Configuration key '", strE, "' is set to null but expected ", strQ) : a0.c.l("Configuration key '", strE, "' is null"), null);
        }
    }

    @Override // l6.a
    public final p1 c(String str) {
        g1 g1VarC = g1.c(str);
        g gVarJ = j(this.f14609i, g1VarC, 1, g1VarC);
        o(gVarJ, 1, g1VarC);
        return ((c) gVarJ).f14511l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // l6.a
    public final ArrayList e(String str) {
        ArrayList arrayList = new ArrayList();
        g1 g1VarC = g1.c(str);
        g gVarJ = j(this.f14609i, g1VarC, 2, g1VarC);
        o(gVarJ, 2, g1VarC);
        Iterator<l6.o> it = ((l6.g) gVarJ).iterator();
        while (it.hasNext()) {
            g gVarB = r.b((g) it.next(), 6);
            if (gVarB.b() != 6) {
                throw new ConfigException.WrongType(gVarB.f14537i, str, "list of STRING", "list of ".concat(g8.b.q(gVarB.b())));
            }
            arrayList.add(gVarB.a());
        }
        return arrayList;
    }

    @Override // l6.a
    public final Set entrySet() {
        HashSet hashSet = new HashSet();
        l(hashSet, null, this.f14609i);
        return hashSet;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p1)) {
            return false;
        }
        return this.f14609i.equals(((p1) obj).f14609i);
    }

    @Override // l6.a
    public final String g(String str) {
        g1 g1VarC = g1.c(str);
        g gVarJ = j(this.f14609i, g1VarC, 6, g1VarC);
        o(gVarJ, 6, g1VarC);
        return (String) gVarJ.a();
    }

    @Override // com.typesafe.config.impl.x0
    public final l6.o h() {
        return this.f14609i;
    }

    public final int hashCode() {
        return this.f14609i.hashCode() * 41;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // l6.a
    public final ArrayList m(String str) {
        ArrayList arrayList = new ArrayList();
        g1 g1VarC = g1.c(str);
        g gVarJ = j(this.f14609i, g1VarC, 2, g1VarC);
        o(gVarJ, 2, g1VarC);
        Iterator<l6.o> it = ((l6.g) gVarJ).iterator();
        while (it.hasNext()) {
            g gVarB = r.b((g) it.next(), 1);
            if (gVarB.b() != 1) {
                throw new ConfigException.WrongType(gVarB.f14537i, str, "list of OBJECT", "list of ".concat(g8.b.q(gVarB.b())));
            }
            arrayList.add(gVarB);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((l6.j) it2.next()).d());
        }
        return arrayList2;
    }

    public final p1 n(com.typesafe.config.a aVar) {
        c cVar = this.f14609i;
        o1 o1Var = new o1(cVar);
        m1 m1Var = new m1(new z1(new h(0, h.f14544c)), aVar, null, new ArrayList(), Collections.newSetFromMap(new IdentityHashMap()));
        if (x.g()) {
            x.d(m1Var.a(), "ResolveContext restrict to child null");
        }
        try {
            g gVar = m1Var.c(cVar, o1Var).f14594b;
            return gVar == cVar ? this : new p1((c) gVar);
        } catch (f e5) {
            throw new ConfigException.BugOrBroken(e5, "NotPossibleToResolve was thrown from an outermost resolve");
        }
    }

    @Override // l6.a
    public final boolean q(String str) {
        g1 g1VarC = g1.c(str);
        try {
            c cVar = this.f14609i;
            cVar.getClass();
            g gVarY = c.Y(cVar, g1VarC);
            return (gVarY == null || gVarY.b() == 5) ? false : true;
        } catch (ConfigException.NotResolved e5) {
            throw x.c(g1VarC, e5);
        }
    }

    @Override // l6.a
    public final c root() {
        return this.f14609i;
    }

    public final String toString() {
        return "Config(" + this.f14609i.toString() + ")";
    }
}
