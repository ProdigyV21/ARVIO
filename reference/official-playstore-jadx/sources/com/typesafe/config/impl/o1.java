package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public final class o1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f14604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14605b;

    public o1(c cVar, io.sentry.internal.debugmeta.c cVar2) {
        this.f14604a = cVar;
        this.f14605b = cVar2;
    }

    public static io.sentry.internal.debugmeta.c a(c cVar, g1 g1Var, io.sentry.internal.debugmeta.c cVar2) {
        String str = g1Var.f14541a;
        g1 g1Var2 = g1Var.f14542b;
        if (x.g()) {
            x.e("*** looking up '" + str + "' in " + cVar);
        }
        g gVarS = cVar.S(str);
        Object obj = null;
        io.sentry.internal.debugmeta.c cVar3 = cVar2 == null ? new io.sentry.internal.debugmeta.c(24, cVar, false, obj) : new io.sentry.internal.debugmeta.c(24, cVar, false, cVar2);
        return g1Var2 == null ? new io.sentry.internal.debugmeta.c(26, gVarS, false, cVar3) : gVarS instanceof c ? a((c) gVarS, g1Var2, cVar3) : new io.sentry.internal.debugmeta.c(26, obj, false, cVar3);
    }

    public static io.sentry.internal.debugmeta.c b(c cVar, m1 m1Var, g1 g1Var) {
        if (x.g()) {
            x.e("*** finding '" + g1Var + "' in " + cVar);
        }
        g1 g1Var2 = m1Var.f14578c;
        n1 n1VarC = m1Var.d(g1Var).c(cVar, new o1(cVar));
        m1 m1VarD = n1VarC.f14593a.d(g1Var2);
        g gVar = n1VarC.f14594b;
        if (!(gVar instanceof c)) {
            throw new ConfigException.BugOrBroken(null, "resolved object to non-object " + cVar + " to " + n1VarC);
        }
        try {
            io.sentry.internal.debugmeta.c cVarA = a((c) gVar, g1Var, null);
            return new io.sentry.internal.debugmeta.c(25, new n1(m1VarD, (g) cVarA.f17367l), false, (io.sentry.internal.debugmeta.c) cVarA.f17368m);
        } catch (ConfigException.NotResolved e5) {
            throw x.c(g1Var, e5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static io.sentry.internal.debugmeta.c e(io.sentry.internal.debugmeta.c cVar, t0 t0Var, g gVar) {
        Object obj = cVar.f17367l;
        io.sentry.internal.debugmeta.c cVar2 = (io.sentry.internal.debugmeta.c) cVar.f17368m;
        t0 t0Var2 = (t0) obj;
        Object obj2 = null;
        if (t0Var2 != t0Var) {
            throw new ConfigException.BugOrBroken(null, "Can only replace() the top node we're resolving; had " + t0Var2 + " on top and tried to replace " + t0Var + " overall list was " + cVar);
        }
        t0 t0Var3 = cVar2 == null ? null : (t0) cVar2.f17367l;
        if (gVar == 0 || !(gVar instanceof t0)) {
            if (t0Var3 == null) {
                return null;
            }
            return e(cVar2, t0Var3, t0Var3.r((g) t0Var, null));
        }
        if (t0Var3 == null) {
            return new io.sentry.internal.debugmeta.c(24, (t0) gVar, false, obj2);
        }
        io.sentry.internal.debugmeta.c cVarE = e(cVar2, t0Var3, t0Var3.r((g) t0Var, gVar));
        if (cVarE != null) {
            return new io.sentry.internal.debugmeta.c(24, (t0) gVar, false, cVarE);
        }
        return new io.sentry.internal.debugmeta.c(24, (t0) gVar, false, obj2);
    }

    public final io.sentry.internal.debugmeta.c c(m1 m1Var, b2 b2Var, int i10) {
        if (x.g()) {
            x.d(m1Var.a(), "searching for " + b2Var);
        }
        if (x.g()) {
            x.d(m1Var.a(), b2Var + " - looking up relative to file it occurred in");
        }
        io.sentry.internal.debugmeta.c cVarB = b(this.f14604a, m1Var, b2Var.f14509a);
        if (((n1) cVarB.f17367l).f14594b == null) {
            g1 g1Var = b2Var.f14509a;
            g1Var.getClass();
            int i11 = i10;
            while (g1Var != null && i11 > 0) {
                i11--;
                g1Var = g1Var.f14542b;
            }
            if (i10 > 0) {
                if (x.g()) {
                    x.d(((n1) cVarB.f17367l).f14593a.a(), g1Var + " - looking up relative to parent file");
                }
                cVarB = b(this.f14604a, ((n1) cVarB.f17367l).f14593a, g1Var);
            }
            n1 n1Var = (n1) cVarB.f17367l;
            if (n1Var.f14594b == null) {
                n1Var.f14593a.f14577b.getClass();
                if (x.g()) {
                    x.d(((n1) cVarB.f17367l).f14593a.a(), g1Var + " - looking up in system environment");
                }
                try {
                    cVarB = b(s.f14616a, m1Var, g1Var);
                } catch (ExceptionInInitializerError e5) {
                    throw y.b(e5);
                }
            }
        }
        if (x.g()) {
            x.d(((n1) cVarB.f17367l).f14593a.a(), "resolved to " + cVarB);
        }
        return cVarB;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final o1 d(t0 t0Var) {
        boolean zG = x.g();
        c cVar = this.f14604a;
        if (zG) {
            StringBuilder sb2 = new StringBuilder("pushing parent ");
            sb2.append(t0Var);
            sb2.append(" ==root ");
            sb2.append(t0Var == cVar);
            sb2.append(" onto ");
            sb2.append(this);
            x.e(sb2.toString());
        }
        io.sentry.internal.debugmeta.c cVar2 = this.f14605b;
        if (cVar2 == null) {
            if (t0Var == cVar) {
                return new o1(cVar, new io.sentry.internal.debugmeta.c(24, t0Var, false, null));
            }
            if (x.g() && cVar.k((g) t0Var)) {
                x.e("***** BUG ***** tried to push parent " + t0Var + " without having a path to it in " + this);
            }
            return this;
        }
        t0 t0Var2 = (t0) cVar2.f17367l;
        if (x.g() && t0Var2 != null && !t0Var2.k((g) t0Var)) {
            x.e("***** BUG ***** trying to push non-child of " + t0Var2 + ", non-child was " + t0Var);
        }
        return new o1(cVar, new io.sentry.internal.debugmeta.c(24, t0Var, false, cVar2));
    }

    public final String toString() {
        return "ResolveSource(root=" + this.f14604a + ", pathFromRoot=" + this.f14605b + ")";
    }

    public o1(c cVar) {
        this.f14604a = cVar;
        this.f14605b = null;
    }
}
