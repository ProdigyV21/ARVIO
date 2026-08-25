package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class t1 extends g implements l6.g, t0, Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f14620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f14621m;

    public t1(List list, l6.k kVar) {
        this(kVar, list, com.arflix.tv.data.repository.g.a(list));
    }

    public static UnsupportedOperationException U(String str) {
        return new UnsupportedOperationException(a0.c.l("ConfigList is immutable, you can't call List.'", str, "'"));
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new t1(this.f14620l, w1Var);
    }

    @Override // com.typesafe.config.impl.g
    public final g E(g1 g1Var) {
        try {
            return S(new q1(g1Var, 0), this.f14621m ? 2 : 1);
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception e6) {
            throw new ConfigException.BugOrBroken(e6, "unexpected checked exception");
        }
    }

    @Override // com.typesafe.config.impl.g
    public final void F(StringBuilder sb2, int i10, boolean z, io.sentry.util.l lVar) {
        List list = this.f14620l;
        if (list.isEmpty()) {
            sb2.append("[]");
            return;
        }
        sb2.append("[");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((g) it.next()).F(sb2, i10 + 1, z, lVar);
            sb2.append(",");
        }
        sb2.setLength(sb2.length() - 1);
        sb2.append("]");
    }

    @Override // com.typesafe.config.impl.g
    public final int J() {
        return this.f14621m ? 2 : 1;
    }

    @Override // com.typesafe.config.impl.g
    public final n1 K(m1 m1Var, o1 o1Var) throws f {
        if (this.f14621m) {
            return new n1(m1Var, this);
        }
        if (m1Var.f14578c != null) {
            return new n1(m1Var, this);
        }
        try {
            io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(27, m1Var, false, o1Var.d(this));
            m1Var.f14577b.getClass();
            return new n1((m1) cVar.f17367l, S(cVar, 2));
        } catch (f e5) {
            throw e5;
        } catch (RuntimeException e6) {
            throw e6;
        } catch (Exception e10) {
            throw new ConfigException.BugOrBroken(e10, "unexpected checked exception");
        }
    }

    @Override // com.typesafe.config.impl.g
    public final g Q(w1 w1Var) {
        return (t1) super.Q(w1Var);
    }

    public final t1 S(d dVar, int i10) {
        List<g> list = this.f14620l;
        ArrayList arrayList = null;
        int i11 = 0;
        for (g gVar : list) {
            g gVarL = dVar.L(gVar, null);
            if (arrayList == null && gVarL != gVar) {
                arrayList = new ArrayList();
                for (int i12 = 0; i12 < i11; i12++) {
                    arrayList.add(list.get(i12));
                }
            }
            if (arrayList != null && gVarL != null) {
                arrayList.add(gVarL);
            }
            i11++;
        }
        if (arrayList == null) {
            return this;
        }
        w1 w1Var = this.f14537i;
        return i10 != 0 ? new t1(w1Var, arrayList, i10) : new t1(w1Var, arrayList, com.arflix.tv.data.repository.g.a(arrayList));
    }

    @Override // l6.o
    public final Object a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14620l.iterator();
        while (it.hasNext()) {
            arrayList.add(((g) it.next()).a());
        }
        return arrayList;
    }

    @Override // java.util.List
    public final void add(int i10, l6.o oVar) {
        throw U("add");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw U("addAll");
    }

    @Override // l6.o
    public final int b() {
        return 2;
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        throw U("clear");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f14620l.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.f14620l.containsAll(collection);
    }

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof t1)) {
            return false;
        }
        Object obj2 = ((t1) obj).f14620l;
        List list = this.f14620l;
        return list == obj2 || list.equals(obj2);
    }

    @Override // java.util.List
    public final l6.o get(int i10) {
        return (g) this.f14620l.get(i10);
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        return this.f14620l.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.f14620l.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f14620l.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new r1(this.f14620l.iterator());
    }

    @Override // com.typesafe.config.impl.t0
    public final boolean k(g gVar) {
        return g.v(this.f14620l, gVar);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return this.f14620l.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator<l6.o> listIterator() {
        return new s1(this.f14620l.listIterator());
    }

    @Override // com.typesafe.config.impl.t0
    public final g r(g gVar, g gVar2) {
        ArrayList arrayListH = g.H(this.f14620l, gVar, gVar2);
        if (arrayListH == null) {
            return null;
        }
        return new t1(this.f14537i, arrayListH, com.arflix.tv.data.repository.g.a(arrayListH));
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw U("remove");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw U("removeAll");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw U("retainAll");
    }

    @Override // java.util.List
    public final l6.o set(int i10, l6.o oVar) {
        throw U("set");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f14620l.size();
    }

    @Override // java.util.List
    public final List<l6.o> subList(int i10, int i11) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14620l.subList(i10, i11).iterator();
        while (it.hasNext()) {
            arrayList.add((g) it.next());
        }
        return arrayList;
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof t1;
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return this.f14620l.toArray();
    }

    @Override // java.util.List
    public final boolean addAll(int i10, Collection<? extends l6.o> collection) {
        throw U("addAll");
    }

    @Override // java.util.List
    public final l6.o remove(int i10) {
        throw U("remove");
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.f14620l.toArray(objArr);
    }

    public t1(l6.k kVar, List list, int i10) {
        super(kVar);
        this.f14620l = list;
        this.f14621m = i10 == 2;
        if (i10 == com.arflix.tv.data.repository.g.a(list)) {
            return;
        }
        throw new ConfigException.BugOrBroken(null, "SimpleConfigList created with wrong resolve status: " + this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        throw U("add");
    }

    @Override // java.util.List
    public final ListIterator<l6.o> listIterator(int i10) {
        return new s1(this.f14620l.listIterator(i10));
    }
}
