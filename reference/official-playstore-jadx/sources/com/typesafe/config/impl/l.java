package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends c implements n2, l1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f14567m;

    public l(List list, l6.k kVar) {
        super(kVar);
        this.f14567m = list;
        if (list.isEmpty()) {
            throw new ConfigException.BugOrBroken(null, "creating empty delayed merge object");
        }
        if (!(list.get(0) instanceof c)) {
            throw new ConfigException.BugOrBroken(null, "created a delayed merge object not guaranteed to be an object");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if ((gVar instanceof k) || (gVar instanceof l)) {
                throw new ConfigException.BugOrBroken(null, "placed nested DelayedMerge in a ConfigDelayedMergeObject, should have consolidated stack");
            }
        }
    }

    public static ConfigException.NotResolved d0() {
        return new ConfigException.NotResolved(null, "need to Config#resolve() before using this object, see the API docs for Config#resolve()");
    }

    @Override // com.typesafe.config.impl.g
    public final g A(c cVar) {
        I();
        return (l) z(this.f14567m, cVar);
    }

    @Override // com.typesafe.config.impl.g
    public final g B(n2 n2Var) {
        I();
        return (l) C(this.f14567m, n2Var);
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        G(sb2, i10, z, null, lVar);
    }

    @Override // com.typesafe.config.impl.g
    public final void G(StringBuilder sb2, int i10, boolean z, String str, io.sentry.util.l lVar) {
        k.U(this.f14567m, sb2, i10, z, str, lVar);
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return 1;
    }

    @Override // com.typesafe.config.impl.g
    public final n1 K(m1 m1Var, o1 o1Var) {
        n1 n1VarW = k.W(this, this.f14567m, m1Var, o1Var);
        g gVar = n1VarW.f14594b;
        if (gVar instanceof c) {
            return n1VarW;
        }
        throw new ConfigException.BugOrBroken(null, "Expecting a resolve result to be an object, but it was " + gVar);
    }

    @Override // com.typesafe.config.impl.c, com.typesafe.config.impl.g
    /* JADX INFO: renamed from: N */
    public final g f(l6.i iVar) {
        return (l) super.N(iVar);
    }

    @Override // com.typesafe.config.impl.c
    public final g S(String str) {
        for (g gVar : this.f14567m) {
            if (!(gVar instanceof c)) {
                if (gVar instanceof n2) {
                    StringBuilder sbT = a0.c.t("Key '", str, "' is not available at '");
                    sbT.append(this.f14537i.description());
                    sbT.append("' because value at '");
                    sbT.append(gVar.f14537i.description());
                    sbT.append("' has not been resolved and may turn out to contain or hide '");
                    sbT.append(str);
                    sbT.append("'. Be sure to Config#resolve() before using a config object.");
                    throw new ConfigException.NotResolved(null, sbT.toString());
                }
                if (gVar.J() == 1) {
                    if (!(gVar instanceof l6.g)) {
                        throw new ConfigException.BugOrBroken(null, "Expecting a list here, not " + gVar);
                    }
                } else if (!gVar.w()) {
                    throw new ConfigException.BugOrBroken(null, "resolved non-object should ignore fallbacks");
                }
                return null;
            }
            g gVarS = ((c) gVar).S(str);
            if (gVarS != null) {
                if (gVarS.w()) {
                    return gVarS;
                }
            } else if (gVar instanceof n2) {
                throw new ConfigException.BugOrBroken(null, "should not be reached: unmergeable object returned null value");
            }
        }
        throw new ConfigException.BugOrBroken(null, "Delayed merge stack does not contain any unmergeable values");
    }

    @Override // com.typesafe.config.impl.c
    /* JADX INFO: renamed from: U */
    public final g get(Object obj) {
        throw d0();
    }

    @Override // com.typesafe.config.impl.c
    public final c X(int i10, w1 w1Var) {
        if (i10 == 1) {
            return new l(this.f14567m, w1Var);
        }
        throw new ConfigException.BugOrBroken(null, "attempt to create resolved ConfigDelayedMergeObject");
    }

    @Override // com.typesafe.config.impl.c, l6.o
    public final Object a() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.c
    /* JADX INFO: renamed from: c0 */
    public final c N(l6.i iVar) {
        return (l) super.N(iVar);
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        throw d0();
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        throw d0();
    }

    @Override // com.typesafe.config.impl.c
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final l Z(g1 g1Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14567m.iterator();
        while (it.hasNext()) {
            arrayList.add(((g) it.next()).Z(g1Var));
        }
        return new l(arrayList, this.f14537i);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<String, l6.o>> entrySet() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        Object obj2 = ((l) obj).f14567m;
        List list = this.f14567m;
        return list == obj2 || list.equals(obj2);
    }

    @Override // com.typesafe.config.impl.c, l6.j
    public final l6.j f(l6.j jVar) {
        return (l) super.N(jVar);
    }

    @Override // com.typesafe.config.impl.c, java.util.Map
    public final l6.o get(Object obj) {
        throw d0();
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        return this.f14567m.hashCode();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.t0
    public final boolean k(g gVar) {
        return g.v(this.f14567m, gVar);
    }

    @Override // java.util.Map
    public final Set<String> keySet() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.n2
    public final Collection p() {
        return this.f14567m;
    }

    @Override // com.typesafe.config.impl.t0
    public final g r(g gVar, g gVar2) {
        ArrayList arrayListH = g.H(this.f14567m, gVar, gVar2);
        if (arrayListH == null) {
            return null;
        }
        return new l(arrayListH, this.f14537i);
    }

    @Override // com.typesafe.config.impl.l1
    public final g s(m1 m1Var, int i10) {
        return k.S(m1Var, this.f14567m, i10);
    }

    @Override // java.util.Map
    public final int size() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof l;
    }

    @Override // java.util.Map
    public final Collection<l6.o> values() {
        throw d0();
    }

    @Override // com.typesafe.config.impl.g
    public final boolean w() {
        return k.X(this.f14567m);
    }

    @Override // com.typesafe.config.impl.g
    public final g y(g gVar) {
        I();
        return (l) z(this.f14567m, gVar);
    }

    @Override // com.typesafe.config.impl.c, l6.o
    public final Map a() {
        throw d0();
    }
}
