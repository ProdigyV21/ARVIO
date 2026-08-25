package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c extends g implements l6.j, t0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final p1 f14511l;

    public c(l6.k kVar) {
        super(kVar);
        this.f14511l = new p1(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static l6.k W(List list) {
        w1 w1Var = null;
        if (list.isEmpty()) {
            throw new ConfigException.BugOrBroken(null, "can't merge origins on empty list");
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (w1Var == null) {
                w1Var = gVar.f14537i;
            }
            if (!(gVar instanceof c) || ((c) gVar).J() != 2 || !((l6.j) gVar).isEmpty()) {
                arrayList.add(gVar.f14537i);
                i10++;
            }
        }
        if (i10 == 0) {
            arrayList.add(w1Var);
        }
        return w1.d(arrayList);
    }

    public static g Y(c cVar, g1 g1Var) {
        try {
            g1 g1Var2 = g1Var.f14542b;
            g gVarS = cVar.S(g1Var.f14541a);
            if (g1Var2 == null) {
                return gVarS;
            }
            if (gVarS instanceof c) {
                return Y((c) gVarS, g1Var2);
            }
            return null;
        } catch (ConfigException.NotResolved e5) {
            throw x.c(g1Var, e5);
        }
    }

    public static UnsupportedOperationException a0(String str) {
        return new UnsupportedOperationException("ConfigObject is immutable, you can't call Map.".concat(str));
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return X(J(), w1Var);
    }

    @Override // com.typesafe.config.impl.g
    /* JADX INFO: renamed from: L */
    public final g h() {
        return this;
    }

    @Override // com.typesafe.config.impl.g
    public final g Q(w1 w1Var) {
        return (c) super.Q(w1Var);
    }

    public abstract g S(String str);

    @Override // java.util.Map
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public abstract g get(Object obj);

    public abstract c X(int i10, w1 w1Var);

    public abstract c Z(g1 g1Var);

    @Override // l6.o
    public final int b() {
        return 1;
    }

    @Override // l6.j
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public c f(l6.i iVar) {
        return (c) super.f(iVar);
    }

    @Override // java.util.Map
    public final void clear() {
        throw a0("clear");
    }

    @Override // l6.j
    public final p1 d() {
        return this.f14511l;
    }

    @Override // java.util.Map
    public final l6.o put(String str, l6.o oVar) {
        throw a0("put");
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends String, ? extends l6.o> map) {
        throw a0("putAll");
    }

    @Override // java.util.Map
    public final l6.o remove(Object obj) {
        throw a0("remove");
    }

    @Override // com.typesafe.config.impl.g
    public final g u(l6.k kVar, ArrayList arrayList) {
        return new l(arrayList, kVar);
    }

    @Override // com.typesafe.config.impl.g, com.typesafe.config.impl.x0
    public final l6.o h() {
        return this;
    }
}
