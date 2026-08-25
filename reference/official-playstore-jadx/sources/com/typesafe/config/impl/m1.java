package com.typesafe.config.impl;

import com.google.android.gms.internal.cast.n4;
import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class m1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z1 f14576a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.typesafe.config.a f14577b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g1 f14578c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f14579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f14580e;

    public m1(z1 z1Var, com.typesafe.config.a aVar, g1 g1Var, ArrayList arrayList, Set set) {
        this.f14576a = z1Var;
        this.f14577b = aVar;
        this.f14578c = g1Var;
        this.f14579d = arrayList;
        this.f14580e = set;
    }

    public final int a() {
        ArrayList arrayList = this.f14579d;
        if (arrayList.size() <= 30) {
            return arrayList.size();
        }
        throw new ConfigException.BugOrBroken(null, "resolve getting too deep");
    }

    public final m1 b(w0 w0Var, g gVar) {
        n4[] n4VarArr;
        int i10;
        h hVar = (h) this.f14576a.f14652a;
        int i11 = hVar.f14546a + 1;
        n4[] n4VarArr2 = hVar.f14547b;
        if (i11 > n4VarArr2.length) {
            int i12 = (i11 * 2) - 1;
            int i13 = 0;
            while (true) {
                int[] iArr = h.f14545d;
                if (i13 >= 174) {
                    i10 = iArr[173];
                    break;
                }
                i10 = iArr[i13];
                if (i10 > i12) {
                    break;
                }
                i13++;
            }
            n4VarArr = new n4[i10];
        } else {
            n4VarArr = new n4[n4VarArr2.length];
        }
        if (n4VarArr.length == n4VarArr2.length) {
            System.arraycopy(n4VarArr2, 0, n4VarArr, 0, n4VarArr2.length);
        } else {
            for (n4 n4Var : n4VarArr2) {
                while (n4Var != null) {
                    n4 n4Var2 = (n4) n4Var.f13491n;
                    int i14 = n4Var.f13488i;
                    int length = i14 % n4VarArr.length;
                    n4 n4Var3 = n4VarArr[length];
                    if (n4Var3 == null && n4Var2 == null) {
                        n4VarArr[length] = n4Var;
                    } else {
                        n4VarArr[length] = new n4(i14, (w0) n4Var.f13489l, n4Var.f13490m, n4Var3);
                    }
                    n4Var = n4Var2;
                }
            }
        }
        int iAbs = Math.abs(w0Var.hashCode());
        int length2 = iAbs % n4VarArr.length;
        n4VarArr[length2] = new n4(iAbs, w0Var, gVar, n4VarArr[length2]);
        return new m1(new z1(new h(i11, n4VarArr)), this.f14577b, this.f14578c, this.f14579d, this.f14580e);
    }

    public final n1 c(g gVar, o1 o1Var) {
        w0 w0Var;
        m1 m1VarB;
        n1 n1Var;
        if (x.g()) {
            x.d(a(), "resolving " + gVar + " restrictToChild=" + this.f14578c + " in " + o1Var);
        }
        if (x.g()) {
            x.d(a(), "pushing trace " + gVar);
        }
        ArrayList arrayList = new ArrayList(this.f14579d);
        arrayList.add(gVar);
        z1 z1Var = this.f14576a;
        com.typesafe.config.a aVar = this.f14577b;
        g1 g1Var = this.f14578c;
        Set set = this.f14580e;
        m1 m1Var = new m1(z1Var, aVar, g1Var, arrayList, set);
        w0 w0Var2 = new w0(gVar, null);
        g gVarB = z1Var.b(w0Var2);
        if (gVarB != null || g1Var == null) {
            w0Var = null;
        } else {
            w0 w0Var3 = new w0(gVar, g1Var);
            gVarB = z1Var.b(w0Var3);
            w0Var = w0Var3;
        }
        if (gVarB != null) {
            if (x.g()) {
                x.d(m1Var.a(), "using cached resolution " + gVarB + " for " + gVar + " restrictToChild " + g1Var);
            }
            n1Var = new n1(m1Var, gVarB);
        } else {
            if (x.g()) {
                x.d(m1Var.a(), "not found in cache, resolving " + gVar + "@" + System.identityHashCode(gVar));
            }
            if (set.contains(gVar)) {
                if (x.g()) {
                    x.d(m1Var.a(), "Cycle detected, can't resolve; " + gVar + "@" + System.identityHashCode(gVar));
                }
                throw new f(m1Var);
            }
            n1 n1VarK = gVar.K(m1Var, o1Var);
            g gVar2 = n1VarK.f14594b;
            if (x.g()) {
                x.d(m1Var.a(), "resolved to " + gVar2 + "@" + System.identityHashCode(gVar2) + " from " + gVar + "@" + System.identityHashCode(gVar2));
            }
            m1 m1Var2 = n1VarK.f14593a;
            if (gVar2 == null || gVar2.J() == 2) {
                if (x.g()) {
                    x.d(m1Var.a(), "caching " + w0Var2 + " result " + gVar2);
                }
                m1VarB = m1Var2.b(w0Var2, gVar2);
            } else {
                if (g1Var == null) {
                    aVar.getClass();
                    throw new ConfigException.BugOrBroken(null, "resolveSubstitutions() did not give us a resolved object");
                }
                if (w0Var == null) {
                    throw new ConfigException.BugOrBroken(null, "restrictedKey should not be null here");
                }
                if (x.g()) {
                    x.d(m1Var.a(), "caching " + w0Var + " result " + gVar2);
                }
                m1VarB = m1Var2.b(w0Var, gVar2);
            }
            n1Var = new n1(m1VarB, gVar2);
        }
        m1 m1Var3 = n1Var.f14593a;
        ArrayList arrayList2 = new ArrayList(m1Var3.f14579d);
        g gVar3 = (g) arrayList2.remove(r0.size() - 1);
        if (x.g()) {
            x.d(m1Var3.a() - 1, "popped trace " + gVar3);
        }
        return new n1(new m1(m1Var3.f14576a, m1Var3.f14577b, m1Var3.f14578c, arrayList2, m1Var3.f14580e), n1Var.f14594b);
    }

    public final m1 d(g1 g1Var) {
        if (g1Var == this.f14578c) {
            return this;
        }
        return new m1(this.f14576a, this.f14577b, g1Var, this.f14579d, this.f14580e);
    }
}
