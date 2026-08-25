package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class g implements l6.o, x0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w1 f14537i;

    public g(l6.k kVar) {
        this.f14537i = (w1) kVar;
    }

    public static ArrayList H(List list, g gVar, g gVar2) {
        int i10 = 0;
        while (i10 < list.size() && list.get(i10) != gVar) {
            i10++;
        }
        if (i10 == list.size()) {
            throw new ConfigException.BugOrBroken(null, "tried to replace " + gVar + " which is not in " + list);
        }
        ArrayList arrayList = new ArrayList(list);
        if (gVar2 != null) {
            arrayList.set(i10, gVar2);
        } else {
            arrayList.remove(i10);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public static boolean v(List list, g gVar) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((g) it.next()) == gVar) {
                return true;
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            l6.i iVar = (g) it2.next();
            if ((iVar instanceof t0) && ((t0) iVar).k(gVar)) {
                return true;
            }
        }
        return false;
    }

    public g A(c cVar) {
        I();
        List listSingletonList = Collections.singletonList(this);
        I();
        if (this instanceof c) {
            throw new ConfigException.BugOrBroken(null, "Objects must reimplement mergedWithObject");
        }
        return z(listSingletonList, cVar);
    }

    public g B(n2 n2Var) {
        I();
        return C(Collections.singletonList(this), n2Var);
    }

    public final g C(Collection collection, n2 n2Var) {
        I();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.addAll(n2Var.p());
        return u(c.W(arrayList), arrayList);
    }

    public abstract g D(w1 w1Var);

    public g E(g1 g1Var) {
        return this;
    }

    public void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        sb2.append(a().toString());
    }

    public void G(StringBuilder sb2, int i10, boolean z, String str, io.sentry.util.l lVar) {
        if (str != null) {
            sb2.append(y.d(str));
            sb2.append(":");
        }
        F(sb2, i10, z, lVar);
    }

    public final void I() {
        if (w()) {
            throw new ConfigException.BugOrBroken(null, "method should not have been called with ignoresFallbacks=true ".concat(getClass().getSimpleName()));
        }
    }

    public int J() {
        return 2;
    }

    public n1 K(m1 m1Var, o1 o1Var) {
        return new n1(m1Var, this);
    }

    @Override // com.typesafe.config.impl.x0
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public g h() {
        return this;
    }

    public String M() {
        return null;
    }

    /* JADX INFO: renamed from: N */
    public g f(l6.i iVar) {
        if (w()) {
            return this;
        }
        l6.o oVarH = ((x0) iVar).h();
        return oVarH instanceof n2 ? B((n2) oVarH) : oVarH instanceof c ? A((c) oVarH) : y((g) oVarH);
    }

    public g P() {
        if (w()) {
            return this;
        }
        throw new ConfigException.BugOrBroken(null, "value class doesn't implement forced fallback-ignoring " + this);
    }

    public g Q(w1 w1Var) {
        return this.f14537i == w1Var ? this : D(w1Var);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l6.o)) {
            return false;
        }
        l6.o oVar = (l6.o) obj;
        return t(oVar) && b() == oVar.b() && y.a(a(), oVar.a());
    }

    public int hashCode() {
        Object objA = a();
        if (objA == null) {
            return 0;
        }
        return objA.hashCode();
    }

    public boolean t(l6.o oVar) {
        return true;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        G(sb2, 0, true, null, new io.sentry.util.l(5));
        return getClass().getSimpleName() + "(" + sb2.toString() + ")";
    }

    public g u(l6.k kVar, ArrayList arrayList) {
        return new k(kVar, arrayList);
    }

    public boolean w() {
        return J() == 2;
    }

    public g y(g gVar) {
        I();
        return z(Collections.singletonList(this), gVar);
    }

    public final g z(Collection collection, g gVar) {
        I();
        if (J() == 2) {
            return P();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        arrayList.add(gVar);
        return u(c.W(arrayList), arrayList);
    }
}
