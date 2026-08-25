package t8;

import androidx.appcompat.app.i1;
import com.google.common.util.concurrent.r0;
import g8.e1;
import g8.p0;
import g8.q0;
import g8.s0;
import g8.u0;
import j8.m0;
import j8.n0;
import j8.v0;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m2.f0;
import p8.j0;
import p8.k0;
import v9.b1;
import v9.d1;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends z {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g8.f f21950n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final w8.g f21951o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f21952p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u9.s f21953q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final u9.s f21954r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final u9.s f21955s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final u9.s f21956t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final u9.r f21957u;

    public n(androidx.core.provider.e eVar, g8.f fVar, w8.g gVar, boolean z, n nVar) {
        super(eVar, nVar);
        this.f21950n = fVar;
        this.f21951o = gVar;
        this.f21952p = z;
        u9.v vVar = ((s8.a) eVar.f2140l).f21645a;
        this.f21953q = vVar.d(new l(this, eVar));
        this.f21954r = vVar.d(new m(this, 1));
        this.f21955s = vVar.d(new l(eVar, this));
        this.f21956t = vVar.d(new m(this, 0));
        this.f21957u = vVar.e(new androidx.navigation.p(this, eVar, 14));
    }

    public static u0 A(u0 u0Var, g8.w wVar, AbstractCollection abstractCollection) {
        if (abstractCollection.isEmpty()) {
            return u0Var;
        }
        Iterator it = abstractCollection.iterator();
        while (it.hasNext()) {
            u0 u0Var2 = (u0) it.next();
            if (!u0Var.equals(u0Var2) && u0Var2.n0() == null && D(u0Var2, wVar)) {
                return (u0) u0Var.C0().h().build();
            }
        }
        return u0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static g8.u0 B(g8.u0 r4) {
        /*
            java.util.List r0 = r4.e()
            java.lang.Object r0 = kotlin.collections.x.y0(r0)
            g8.e1 r0 = (g8.e1) r0
            r1 = 0
            if (r0 == 0) goto L75
            v9.w r2 = r0.getType()
            v9.o0 r2 = r2.I0()
            g8.h r2 = r2.e()
            if (r2 == 0) goto L30
            int r3 = m9.d.f20302a
            f9.e r2 = i9.f.g(r2)
            boolean r3 = r2.d()
            if (r3 == 0) goto L28
            goto L29
        L28:
            r2 = r1
        L29:
            if (r2 == 0) goto L30
            f9.c r2 = r2.g()
            goto L31
        L30:
            r2 = r1
        L31:
            f9.c r3 = d8.q.f14762g
            boolean r2 = kotlin.jvm.internal.p.a(r2, r3)
            if (r2 == 0) goto L3a
            goto L3b
        L3a:
            r0 = r1
        L3b:
            if (r0 != 0) goto L3e
            goto L75
        L3e:
            g8.w$a r1 = r4.C0()
            java.util.List r4 = r4.e()
            r2 = 1
            java.util.List r4 = kotlin.collections.x.i0(r2, r4)
            g8.w$a r4 = r1.a(r4)
            v9.w r0 = r0.getType()
            java.util.List r0 = r0.G0()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            v9.r0 r0 = (v9.r0) r0
            v9.w r0 = r0.getType()
            g8.w$a r4 = r4.j(r0)
            g8.w r4 = r4.build()
            g8.u0 r4 = (g8.u0) r4
            r0 = r4
            j8.o0 r0 = (j8.o0) r0
            if (r0 != 0) goto L72
            return r4
        L72:
            r0.F = r2
            return r4
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: t8.n.B(g8.u0):g8.u0");
    }

    public static boolean D(g8.w wVar, g8.w wVar2) {
        return i9.n.f16259c.n(wVar2, wVar, true).c() == 1 && !f0.m(wVar2, wVar);
    }

    public static boolean E(u0 u0Var, u0 u0Var2) {
        int i10 = p8.d.f21123l;
        u0 u0VarA = u0Var2;
        if (kotlin.jvm.internal.p.a(u0Var.getName().d(), "removeAt")) {
            u0VarA = u0Var2;
            if (kotlin.jvm.internal.p.a(r0.g(u0Var), j0.f21163g.f21148e)) {
                u0VarA = u0Var2.a();
            }
        }
        return D(u0VarA, u0Var);
    }

    public static u0 F(p0 p0Var, String str, r7.l lVar) {
        u0 u0Var;
        Iterator it = ((Iterable) lVar.invoke(f9.f.j(str))).iterator();
        do {
            u0Var = null;
            if (!it.hasNext()) {
                break;
            }
            u0 u0Var2 = (u0) it.next();
            if (u0Var2.e().size() == 0) {
                w9.l lVar2 = w9.c.f22506a;
                v9.w returnType = u0Var2.getReturnType();
                if (returnType == null ? false : lVar2.d(returnType, p0Var.getType())) {
                    u0Var = u0Var2;
                }
            }
        } while (u0Var == null);
        return u0Var;
    }

    public static u0 H(p0 p0Var, r7.l lVar) {
        u0 u0Var;
        v9.w returnType;
        String strD = p0Var.getName().d();
        f9.c cVar = p8.a0.f21078a;
        StringBuilder sb2 = new StringBuilder("set");
        sb2.append(p8.a0.b(strD) ? strD.substring(2) : kotlin.reflect.b0.e(strD));
        Iterator it = ((Iterable) lVar.invoke(f9.f.j(sb2.toString()))).iterator();
        do {
            u0Var = null;
            if (!it.hasNext()) {
                break;
            }
            u0 u0Var2 = (u0) it.next();
            if (u0Var2.e().size() == 1 && (returnType = u0Var2.getReturnType()) != null) {
                f9.f fVar = d8.k.f14705e;
                if (d8.k.D(returnType, d8.p.f14736d) && w9.c.f22506a.b(((e1) kotlin.collections.x.R0(u0Var2.e())).getType(), p0Var.getType())) {
                    u0Var = u0Var2;
                }
            }
        } while (u0Var == null);
        return u0Var;
    }

    public static final ArrayList u(n nVar, f9.f fVar) {
        Collection collectionE = ((c) nVar.f21992e.invoke()).e(fVar);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collectionE, 10));
        Iterator it = collectionE.iterator();
        while (it.hasNext()) {
            arrayList.add(nVar.s((w8.q) it.next()));
        }
        return arrayList;
    }

    public static final ArrayList v(n nVar, f9.f fVar) {
        LinkedHashSet linkedHashSetI = nVar.I(fVar);
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedHashSetI) {
            u0 u0Var = (u0) obj;
            if (qb.l.r(u0Var) == null && p8.f.a(u0Var) == null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean C(p0 p0Var, r7.l lVar) {
        if (p0Var.getGetter() == null) {
            return false;
        }
        u0 u0VarG = G(p0Var, lVar);
        u0 u0VarH = H(p0Var, lVar);
        if (u0VarG == null) {
            return false;
        }
        if (p0Var.K()) {
            return u0VarH != null && u0VarH.o() == u0VarG.o();
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, java.util.Map] */
    public final u0 G(p0 p0Var, r7.l lVar) {
        f9.f fVar;
        m0 getter = p0Var.getGetter();
        String strD = null;
        q0 q0Var = getter != null ? (q0) qb.l.r(getter) : null;
        if (q0Var != null) {
            d8.k.z(q0Var);
            g8.c cVarB = m9.d.b(m9.d.i(q0Var), p8.e.f21128n);
            if (cVarB != null && (fVar = (f9.f) p8.g.f21140a.get(m9.d.g(cVarB))) != null) {
                strD = fVar.d();
            }
        }
        return (strD == null || qb.l.s(this.f21950n, q0Var)) ? F(p0Var, p8.a0.a(p0Var.getName().d()), lVar) : F(p0Var, strD, lVar);
    }

    public final LinkedHashSet I(f9.f fVar) {
        Collection collectionD;
        boolean z = this.f21952p;
        g8.f fVar2 = this.f21950n;
        if (z) {
            collectionD = fVar2.g().d();
        } else {
            ((s8.a) this.f21989b.f2140l).f21664u.c().getClass();
            collectionD = fVar2.g().d();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = collectionD.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(linkedHashSet, ((v9.w) it.next()).l().c(fVar, o8.e.f20797o));
        }
        return linkedHashSet;
    }

    public final Set J(f9.f fVar) {
        Collection collectionD;
        boolean z = this.f21952p;
        g8.f fVar2 = this.f21950n;
        if (z) {
            collectionD = fVar2.g().d();
        } else {
            ((s8.a) this.f21989b.f2140l).f21664u.c().getClass();
            collectionD = fVar2.g().d();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = collectionD.iterator();
        while (it.hasNext()) {
            Collection collectionG = ((v9.w) it.next()).l().g(fVar, o8.e.f20797o);
            ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(collectionG, 10));
            Iterator it2 = collectionG.iterator();
            while (it2.hasNext()) {
                arrayList2.add((p0) it2.next());
            }
            kotlin.collections.x.b0(arrayList, arrayList2);
        }
        return kotlin.collections.x.g1(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0193  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean K(g8.u0 r11) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t8.n.K(g8.u0):boolean");
    }

    @Override // t8.z, p9.o, p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        xc.d.D0(((s8.a) this.f21989b.f2140l).f21657n, bVar, this.f21950n, fVar);
        return super.c(fVar, bVar);
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        u9.r rVar;
        g8.f fVar2;
        xc.d.D0(((s8.a) this.f21989b.f2140l).f21657n, bVar, this.f21950n, fVar);
        n nVar = (n) this.f21990c;
        return (nVar == null || (rVar = nVar.f21957u) == null || (fVar2 = (g8.f) rVar.invoke(fVar)) == null) ? (g8.h) this.f21957u.invoke(fVar) : fVar2;
    }

    @Override // t8.z, p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        xc.d.D0(((s8.a) this.f21989b.f2140l).f21657n, eVar, this.f21950n, fVar);
        return super.g(fVar, eVar);
    }

    @Override // t8.z
    public final Set h(p9.f fVar, r7.l lVar) {
        return kotlin.collections.q0.b0((Set) this.f21954r.invoke(), ((Map) this.f21956t.invoke()).keySet());
    }

    @Override // t8.z
    public final Set i(p9.f fVar, p9.k kVar) {
        g8.f fVar2 = this.f21950n;
        Collection collectionD = fVar2.g().d();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = collectionD.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(linkedHashSet, ((v9.w) it.next()).l().a());
        }
        u9.s sVar = this.f21992e;
        linkedHashSet.addAll(((c) sVar.invoke()).a());
        linkedHashSet.addAll(((c) sVar.invoke()).b());
        linkedHashSet.addAll(h(fVar, kVar));
        androidx.core.provider.e eVar = this.f21989b;
        linkedHashSet.addAll(((s8.a) eVar.f2140l).x.g(eVar, fVar2));
        return linkedHashSet;
    }

    @Override // t8.z
    public final void j(f9.f fVar, ArrayList arrayList) throws Throwable {
        boolean zO = this.f21951o.o();
        g8.f fVar2 = this.f21950n;
        androidx.core.provider.e eVar = this.f21989b;
        if (zO) {
            u9.s sVar = this.f21992e;
            if (((c) sVar.invoke()).f(fVar) != null) {
                if (arrayList.isEmpty()) {
                    w8.v vVarF = ((c) sVar.invoke()).f(fVar);
                    s8.e eVarZ = qb.l.z(eVar, vVarF);
                    s8.a aVar = (s8.a) eVar.f2140l;
                    r8.g gVarV0 = r8.g.V0(fVar2, eVarZ, vVarF.getName(), aVar.f21654j.c(vVarF), true);
                    v9.w wVarD0 = ((i1) eVar.f2144p).d0(vVarF.getType(), qb.l.C(2, false, null, 6));
                    s0 s0VarO = o();
                    g8.p pVar = g8.q.f15528e;
                    kotlin.collections.z zVar = kotlin.collections.z.f19728i;
                    gVarV0.U0(null, s0VarO, zVar, zVar, zVar, wVarD0, 3, pVar, null);
                    gVarV0.O = 1;
                    aVar.f21651g.getClass();
                    arrayList.add(gVarV0);
                } else {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (((u0) it.next()).e().isEmpty()) {
                            break;
                        }
                    }
                    w8.v vVarF2 = ((c) sVar.invoke()).f(fVar);
                    s8.e eVarZ2 = qb.l.z(eVar, vVarF2);
                    s8.a aVar2 = (s8.a) eVar.f2140l;
                    r8.g gVarV02 = r8.g.V0(fVar2, eVarZ2, vVarF2.getName(), aVar2.f21654j.c(vVarF2), true);
                    v9.w wVarD02 = ((i1) eVar.f2144p).d0(vVarF2.getType(), qb.l.C(2, false, null, 6));
                    s0 s0VarO2 = o();
                    g8.p pVar2 = g8.q.f15528e;
                    kotlin.collections.z zVar2 = kotlin.collections.z.f19728i;
                    gVarV02.U0(null, s0VarO2, zVar2, zVar2, zVar2, wVarD02, 3, pVar2, null);
                    gVarV02.O = 1;
                    aVar2.f21651g.getClass();
                    arrayList.add(gVarV02);
                }
            }
        }
        ((s8.a) eVar.f2140l).x.f(eVar, fVar2, fVar, arrayList);
    }

    @Override // t8.z
    public final c k() {
        return new a(this.f21951o, j.f21937l);
    }

    @Override // t8.z
    public final void l(LinkedHashSet linkedHashSet, f9.f fVar) {
        LinkedHashSet linkedHashSetI = I(fVar);
        ArrayList arrayList = j0.f21157a;
        if (!j0.f21166j.contains(fVar)) {
            int i10 = p8.f.f21135l;
            if (!j0.f21161e.contains(fVar)) {
                if (!linkedHashSetI.isEmpty()) {
                    Iterator it = linkedHashSetI.iterator();
                    while (it.hasNext()) {
                        if (((g8.w) it.next()).isSuspend()) {
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : linkedHashSetI) {
                    if (K((u0) obj)) {
                        arrayList2.add(obj);
                    }
                }
                x(linkedHashSet, fVar, arrayList2, false);
                return;
            }
        }
        ea.l lVar = new ea.l();
        LinkedHashSet linkedHashSetU = f4.f.u(fVar, linkedHashSetI, kotlin.collections.z.f19728i, this.f21950n, s9.o.f21740a, ((s8.a) this.f21989b.f2140l).f21664u.a());
        int i11 = 1;
        y(fVar, linkedHashSet, linkedHashSetU, linkedHashSet, new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.j(i11, this, 3));
        y(fVar, linkedHashSet, linkedHashSetU, lVar, new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.j(i11, this, 4));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : linkedHashSetI) {
            if (K((u0) obj2)) {
                arrayList3.add(obj2);
            }
        }
        x(linkedHashSet, fVar, kotlin.collections.x.I0(arrayList3, lVar), true);
    }

    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.Object, x6.s] */
    @Override // t8.z
    public final void m(f9.f fVar, ArrayList arrayList) throws Throwable {
        w8.q qVar;
        boolean zM = this.f21951o.m();
        androidx.core.provider.e eVar = this.f21989b;
        if (zM && (qVar = (w8.q) kotlin.collections.x.S0(((c) this.f21992e.invoke()).e(fVar))) != null) {
            r8.h hVarN0 = r8.h.N0(this.f21950n, qb.l.z(eVar, qVar), k0.a(qVar.getVisibility()), false, qVar.getName(), ((s8.a) eVar.f2140l).f21654j.c(qVar), false);
            m0 m0VarF = i9.q.f(hVarN0, h8.g.f15863a);
            hVarN0.K0(m0VarF, null, null, null);
            v9.w wVarD0 = ((i1) qb.d.g(eVar, hVarN0, qVar, 0, eVar.f2142n).f2144p).d0(qVar.y(), qb.l.C(2, qVar.l().f20288a.isAnnotation(), null, 6));
            s0 s0VarO = o();
            kotlin.collections.z zVar = kotlin.collections.z.f19728i;
            hVarN0.M0(wVarD0, zVar, s0VarO, null, zVar);
            m0VarF.f19324w = wVarD0;
            arrayList.add(hVarN0);
        }
        Set setJ = J(fVar);
        if (setJ.isEmpty()) {
            return;
        }
        ea.l lVar = new ea.l();
        ea.l lVar2 = new ea.l();
        z(setJ, arrayList, lVar, new k(this, 0));
        z(kotlin.collections.q0.a0(setJ, lVar), lVar2, null, new k(this, 1));
        LinkedHashSet linkedHashSetB0 = kotlin.collections.q0.b0(setJ, lVar2);
        s8.a aVar = (s8.a) eVar.f2140l;
        arrayList.addAll(f4.f.u(fVar, linkedHashSetB0, arrayList, this.f21950n, aVar.f21650f, aVar.f21664u.a()));
    }

    @Override // t8.z
    public final Set n() {
        if (this.f21951o.m()) {
            return a();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((c) this.f21992e.invoke()).c());
        Iterator it = this.f21950n.g().d().iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(linkedHashSet, ((v9.w) it.next()).l().b());
        }
        return linkedHashSet;
    }

    @Override // t8.z
    public final s0 o() {
        g8.f fVar = this.f21950n;
        if (fVar != null) {
            int i10 = i9.f.f16249a;
            return fVar.F0();
        }
        i9.f.a(0);
        throw null;
    }

    @Override // t8.z
    public final g8.k p() {
        return this.f21950n;
    }

    @Override // t8.z
    public final boolean q(r8.g gVar) {
        if (this.f21951o.m()) {
            return false;
        }
        return K(gVar);
    }

    @Override // t8.z
    public final v r(w8.q qVar, ArrayList arrayList, v9.w wVar, List list) {
        androidx.emoji2.text.e0 e0VarA = ((s8.a) this.f21989b.f2140l).f21649e.a(qVar, this.f21950n, wVar, list, arrayList);
        v9.w wVar2 = (v9.w) e0VarA.f2600l;
        List list2 = (List) e0VarA.f2601m;
        ArrayList arrayList2 = (ArrayList) e0VarA.f2602n;
        List list3 = Collections.EMPTY_LIST;
        if (list3 != null) {
            return new v(wVar2, list2, arrayList2, list3);
        }
        androidx.emoji2.text.e0.m(7);
        throw null;
    }

    @Override // t8.z
    public final String toString() {
        return "Lazy Java member scope for " + this.f21951o.c();
    }

    public final void w(ArrayList arrayList, r8.b bVar, int i10, w8.q qVar, v9.w wVar, v9.w wVar2) {
        f9.f name = qVar.getName();
        if (wVar == null) {
            b1.a(2);
            throw null;
        }
        d1 d1VarG = b1.g(wVar, false);
        boolean zF = qVar.F();
        d1 d1VarG2 = wVar2 != null ? b1.g(wVar2, false) : null;
        arrayList.add(new v0(bVar, null, i10, h8.g.f15863a, name, d1VarG, zF, false, false, d1VarG2, ((s8.a) this.f21989b.f2140l).f21654j.c(qVar)));
    }

    public final void x(LinkedHashSet linkedHashSet, f9.f fVar, ArrayList arrayList, boolean z) {
        s8.a aVar = (s8.a) this.f21989b.f2140l;
        LinkedHashSet<u0> linkedHashSetU = f4.f.u(fVar, arrayList, linkedHashSet, this.f21950n, aVar.f21650f, aVar.f21664u.a());
        if (!z) {
            linkedHashSet.addAll(linkedHashSetU);
            return;
        }
        ArrayList arrayListI0 = kotlin.collections.x.I0(linkedHashSet, linkedHashSetU);
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(linkedHashSetU, 10));
        for (u0 u0VarA : linkedHashSetU) {
            g8.c cVarR = qb.l.r(u0VarA);
            if (cVarR == null) {
                int i10 = p8.f.f21135l;
                cVarR = !j0.f21161e.contains(u0VarA.getName()) ? null : m9.d.b(u0VarA, p8.e.f21132r);
            }
            u0 u0Var = (u0) cVarR;
            if (u0Var != null) {
                u0VarA = A(u0VarA, u0Var, arrayListI0);
            }
            arrayList2.add(u0VarA);
        }
        linkedHashSet.addAll(arrayList2);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void y(f9.f r10, java.util.LinkedHashSet r11, java.util.LinkedHashSet r12, java.util.AbstractSet r13, r7.l r14) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t8.n.y(f9.f, java.util.LinkedHashSet, java.util.LinkedHashSet, java.util.AbstractSet, r7.l):void");
    }

    public final void z(Set set, AbstractCollection abstractCollection, ea.l lVar, r7.l lVar2) {
        n0 n0VarM;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            p0 p0Var = (p0) it.next();
            r8.d dVar = null;
            if (C(p0Var, lVar2)) {
                u0 u0VarG = G(p0Var, lVar2);
                u0 u0VarH = p0Var.K() ? H(p0Var, lVar2) : null;
                if (u0VarH != null) {
                    u0VarH.o();
                    u0VarG.o();
                }
                r8.d dVar2 = new r8.d(this.f21950n, u0VarG, u0VarH, p0Var);
                v9.w returnType = u0VarG.getReturnType();
                s0 s0VarO = o();
                kotlin.collections.z zVar = kotlin.collections.z.f19728i;
                dVar2.M0(returnType, zVar, s0VarO, null, zVar);
                m0 m0VarL = i9.q.l(dVar2, u0VarG.getAnnotations(), false, u0VarG.f());
                m0VarL.f19299v = u0VarG;
                m0VarL.I0(dVar2.getType());
                if (u0VarH != null) {
                    e1 e1Var = (e1) kotlin.collections.x.o0(u0VarH.e());
                    if (e1Var == null) {
                        throw new AssertionError("No parameter found for " + u0VarH);
                    }
                    n0VarM = i9.q.m(dVar2, u0VarH.getAnnotations(), e1Var.getAnnotations(), false, u0VarH.getVisibility(), u0VarH.f());
                    n0VarM.f19299v = u0VarH;
                } else {
                    n0VarM = null;
                }
                dVar2.K0(m0VarL, n0VarM, null, null);
                dVar = dVar2;
            }
            if (dVar != null) {
                abstractCollection.add(dVar);
                if (lVar != null) {
                    lVar.add(p0Var);
                    return;
                }
                return;
            }
        }
    }
}
