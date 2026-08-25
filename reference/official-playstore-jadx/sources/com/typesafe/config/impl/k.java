package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends g implements n2, l1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f14564l;

    public k(l6.k kVar, ArrayList arrayList) {
        super(kVar);
        this.f14564l = arrayList;
        if (arrayList.isEmpty()) {
            throw new ConfigException.BugOrBroken(null, "creating empty delayed merge value");
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if ((gVar instanceof k) || (gVar instanceof l)) {
                throw new ConfigException.BugOrBroken(null, "placed nested DelayedMerge in a ConfigDelayedMerge, should have consolidated stack");
            }
        }
    }

    public static g S(m1 m1Var, List list, int i10) {
        List<g> listSubList = list.subList(i10, list.size());
        g gVar = null;
        if (listSubList.isEmpty()) {
            if (x.g()) {
                x.d(m1Var.a(), "Nothing else in the merge stack, replacing with null");
            }
            return null;
        }
        for (g gVarF : listSubList) {
            if (gVar != null) {
                gVarF = gVar.f(gVarF);
            }
            gVar = gVarF;
        }
        return gVar;
    }

    public static void U(List list, StringBuilder sb2, int i10, boolean z, String str, io.sentry.util.l lVar) {
        ArrayList<g> arrayList = new ArrayList();
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        for (g gVar : arrayList) {
            if (str != null) {
                sb2.append(y.d(str));
                sb2.append(":");
            }
            gVar.F(sb2, i10, z, lVar);
            sb2.append(",");
        }
        sb2.setLength(sb2.length() - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0143  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.typesafe.config.impl.n1 W(com.typesafe.config.impl.l1 r18, java.util.List r19, com.typesafe.config.impl.m1 r20, com.typesafe.config.impl.o1 r21) {
        /*
            Method dump skipped, instruction units count: 739
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.k.W(com.typesafe.config.impl.l1, java.util.List, com.typesafe.config.impl.m1, com.typesafe.config.impl.o1):com.typesafe.config.impl.n1");
    }

    public static boolean X(List list) {
        return ((g) list.get(list.size() - 1)).w();
    }

    @Override // com.typesafe.config.impl.g
    public final g A(c cVar) {
        I();
        return (k) z(this.f14564l, cVar);
    }

    @Override // com.typesafe.config.impl.g
    public final g B(n2 n2Var) {
        return (k) C(this.f14564l, n2Var);
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new k(w1Var, this.f14564l);
    }

    @Override // com.typesafe.config.impl.g
    /* JADX INFO: renamed from: E */
    public final g Z(g1 g1Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14564l.iterator();
        while (it.hasNext()) {
            arrayList.add(((g) it.next()).Z(g1Var));
        }
        return new k(this.f14537i, arrayList);
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        U(this.f14564l, sb2, i10, z, null, lVar);
    }

    @Override // com.typesafe.config.impl.g
    public final void G(StringBuilder sb2, int i10, boolean z, String str, io.sentry.util.l lVar) {
        U(this.f14564l, sb2, i10, z, str, lVar);
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return 1;
    }

    @Override // com.typesafe.config.impl.g
    public final n1 K(m1 m1Var, o1 o1Var) {
        return W(this, this.f14564l, m1Var, o1Var);
    }

    @Override // l6.o
    public final Object a() {
        throw new ConfigException.NotResolved(null, "called unwrapped() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    @Override // l6.o
    public final int b() {
        throw new ConfigException.NotResolved(null, "called valueType() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        Object obj2 = ((k) obj).f14564l;
        ArrayList arrayList = this.f14564l;
        return arrayList == obj2 || arrayList.equals(obj2);
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        return this.f14564l.hashCode();
    }

    @Override // com.typesafe.config.impl.t0
    public final boolean k(g gVar) {
        return g.v(this.f14564l, gVar);
    }

    @Override // com.typesafe.config.impl.n2
    public final Collection p() {
        return this.f14564l;
    }

    @Override // com.typesafe.config.impl.t0
    public final g r(g gVar, g gVar2) {
        ArrayList arrayListH = g.H(this.f14564l, gVar, gVar2);
        if (arrayListH == null) {
            return null;
        }
        return new k(this.f14537i, arrayListH);
    }

    @Override // com.typesafe.config.impl.l1
    public final g s(m1 m1Var, int i10) {
        return S(m1Var, this.f14564l, i10);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof k;
    }

    @Override // com.typesafe.config.impl.g
    public final boolean w() {
        return X(this.f14564l);
    }

    @Override // com.typesafe.config.impl.g
    public final g y(g gVar) {
        return (k) z(this.f14564l, gVar);
    }
}
