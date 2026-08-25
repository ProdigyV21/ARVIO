package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends g implements n2, t0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f14559l;

    public j(List list, l6.k kVar) {
        super(kVar);
        this.f14559l = list;
        if (list.size() < 2) {
            throw new ConfigException.BugOrBroken(null, "Created concatenation with less than 2 items: " + this);
        }
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar instanceof j) {
                throw new ConfigException.BugOrBroken(null, "ConfigConcatenation should never be nested: " + this);
            }
            if (gVar instanceof n2) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        throw new ConfigException.BugOrBroken(null, "Created concatenation without an unmergeable in it: " + this);
    }

    public static g S(ArrayList arrayList) {
        List listU = U(arrayList);
        ArrayList arrayList2 = (ArrayList) listU;
        if (arrayList2.isEmpty()) {
            return null;
        }
        if (arrayList2.size() == 1) {
            return (g) arrayList2.get(0);
        }
        ArrayList arrayList3 = new ArrayList(arrayList2.size());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((g) it.next()).f14537i);
        }
        return new j(listU, w1.d(arrayList3));
    }

    public static List U(ArrayList arrayList) {
        if (arrayList.size() < 2) {
            return arrayList;
        }
        ArrayList<g> arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar instanceof j) {
                arrayList2.addAll(((j) gVar).f14559l);
            } else {
                arrayList2.add(gVar);
            }
        }
        ArrayList arrayList3 = new ArrayList(arrayList2.size());
        for (g gVarB : arrayList2) {
            if (arrayList3.isEmpty()) {
                arrayList3.add(gVarB);
            } else {
                g t1Var = (g) a0.c.h(1, arrayList3);
                if ((t1Var instanceof l6.j) && (gVarB instanceof t1)) {
                    t1Var = r.b(t1Var, 2);
                } else if ((t1Var instanceof t1) && (gVarB instanceof l6.j)) {
                    gVarB = r.b(gVarB, 2);
                }
                boolean z = t1Var instanceof l6.j;
                if (z && (gVarB instanceof l6.j)) {
                    t1Var = gVarB.f(t1Var);
                } else {
                    boolean z5 = t1Var instanceof t1;
                    if (z5 && (gVarB instanceof t1)) {
                        t1 t1Var2 = (t1) t1Var;
                        t1 t1Var3 = (t1) gVarB;
                        w1 w1VarE = w1.e(t1Var2.f14537i, t1Var3.f14537i);
                        List list = t1Var2.f14620l;
                        int size = list.size();
                        List list2 = t1Var3.f14620l;
                        ArrayList arrayList4 = new ArrayList(list2.size() + size);
                        arrayList4.addAll(list);
                        arrayList4.addAll(list2);
                        t1Var = new t1(w1VarE, arrayList4, com.arflix.tv.data.repository.g.a(arrayList4));
                    } else if ((!z5 && !z) || !(gVarB instanceof s0) || (((s0) gVarB) instanceof q0)) {
                        q0 q0Var = null;
                        if ((t1Var instanceof j) || (gVarB instanceof j)) {
                            throw new ConfigException.BugOrBroken(null, "unflattened ConfigConcatenation");
                        }
                        if (!(t1Var instanceof n2) && !(gVarB instanceof n2)) {
                            String strM = t1Var.M();
                            w1 w1Var = t1Var.f14537i;
                            String strM2 = gVarB.M();
                            if (strM == null || strM2 == null) {
                                throw new ConfigException.WrongType(w1Var, "Cannot concatenate object or list with a non-object-or-list, " + t1Var + " and " + gVarB + " are not compatible", null);
                            }
                            q0Var = new q0(w1.e(w1Var, gVarB.f14537i), strM.concat(strM2));
                        }
                        t1Var = q0Var;
                    }
                }
                if (t1Var == null) {
                    arrayList3.add(gVarB);
                } else {
                    arrayList3.remove(arrayList3.size() - 1);
                    arrayList3.add(t1Var);
                }
            }
        }
        return arrayList3;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new j(this.f14559l, w1Var);
    }

    @Override // com.typesafe.config.impl.g
    /* JADX INFO: renamed from: E */
    public final g Z(g1 g1Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14559l.iterator();
        while (it.hasNext()) {
            arrayList.add(((g) it.next()).Z(g1Var));
        }
        return new j(arrayList, this.f14537i);
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        Iterator it = this.f14559l.iterator();
        while (it.hasNext()) {
            ((g) it.next()).F(sb2, i10, z, lVar);
        }
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return 1;
    }

    @Override // com.typesafe.config.impl.g
    public final n1 K(m1 m1Var, o1 o1Var) {
        boolean zG = x.g();
        List<g> list = this.f14559l;
        if (zG) {
            int iA = m1Var.a();
            int i10 = iA + 2;
            x.d(iA + 1, "concatenation has " + list.size() + " pieces:");
            Iterator it = list.iterator();
            int i11 = 0;
            while (it.hasNext()) {
                x.d(i10, i11 + ": " + ((g) it.next()));
                i11++;
            }
        }
        ArrayList arrayList = new ArrayList(list.size());
        m1 m1VarD = m1Var;
        for (g gVar : list) {
            g1 g1Var = m1VarD.f14578c;
            n1 n1VarC = m1VarD.d(null).c(gVar, o1Var);
            g gVar2 = n1VarC.f14594b;
            m1VarD = n1VarC.f14593a.d(g1Var);
            if (x.g()) {
                x.d(m1Var.a(), "resolved concat piece to " + gVar2);
            }
            if (gVar2 != null) {
                arrayList.add(gVar2);
            }
        }
        List listU = U(arrayList);
        ArrayList arrayList2 = (ArrayList) listU;
        if (arrayList2.size() > 1) {
            m1Var.f14577b.getClass();
        }
        if (arrayList2.isEmpty()) {
            return new n1(m1VarD, null);
        }
        if (arrayList2.size() == 1) {
            return new n1(m1VarD, (g) arrayList2.get(0));
        }
        throw new ConfigException.BugOrBroken(null, "Bug in the library; resolved list was joined to too many values: " + listU);
    }

    @Override // l6.o
    public final Object a() {
        throw new ConfigException.NotResolved(null, "need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    @Override // l6.o
    public final int b() {
        throw new ConfigException.NotResolved(null, "need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            if (this.f14559l.equals(((j) obj).f14559l)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        return this.f14559l.hashCode();
    }

    @Override // com.typesafe.config.impl.t0
    public final boolean k(g gVar) {
        return g.v(this.f14559l, gVar);
    }

    @Override // com.typesafe.config.impl.n2
    public final Collection p() {
        return Collections.singleton(this);
    }

    @Override // com.typesafe.config.impl.t0
    public final g r(g gVar, g gVar2) {
        ArrayList arrayListH = g.H(this.f14559l, gVar, gVar2);
        if (arrayListH == null) {
            return null;
        }
        return new j(arrayListH, this.f14537i);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof j;
    }

    @Override // com.typesafe.config.impl.g
    public final boolean w() {
        return false;
    }
}
