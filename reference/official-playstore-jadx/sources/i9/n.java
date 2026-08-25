package i9;

import g8.b1;
import g8.e1;
import g8.o0;
import g8.p0;
import g8.r0;
import g8.s0;
import h.f0;
import j8.j0;
import j8.l0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.collections.s;
import kotlin.collections.x;
import v9.n0;
import v9.w;
import w9.c;

/* JADX INFO: loaded from: classes5.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List f16258b = x.c1(ServiceLoader.load(g.class, g.class.getClassLoader()));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f16259c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f16260d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a f16261a;

    static {
        j jVar = new j();
        f16260d = jVar;
        f16259c = new n(jVar);
    }

    public n(c.a aVar) {
        if (aVar != null) {
            this.f16261a = aVar;
        } else {
            a(5);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void a(int r25) {
        /*
            Method dump skipped, instruction units count: 1320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i9.n.a(int):void");
    }

    public static boolean b(w wVar, w wVar2, n0 n0Var) {
        if (wVar == null) {
            a(46);
            throw null;
        }
        if (wVar2 == null) {
            a(47);
            throw null;
        }
        if (v9.c.g(wVar) && v9.c.g(wVar2)) {
            return true;
        }
        return v9.e.g(n0Var, wVar.L0(), wVar2.L0());
    }

    public static void c(g8.c cVar, LinkedHashSet linkedHashSet) {
        if (cVar == null) {
            a(17);
            throw null;
        }
        if (cVar.getKind() != 2) {
            linkedHashSet.add(cVar);
            return;
        }
        if (cVar.k().isEmpty()) {
            throw new IllegalStateException("No overridden descriptors found for (fake override) " + cVar);
        }
        Iterator it = cVar.k().iterator();
        while (it.hasNext()) {
            c((g8.c) it.next(), linkedHashSet);
        }
    }

    public static ArrayList d(g8.a aVar) {
        s0 s0VarM = aVar.M();
        ArrayList arrayList = new ArrayList();
        if (s0VarM != null) {
            arrayList.add(s0VarM.getType());
        }
        Iterator it = aVar.e().iterator();
        while (it.hasNext()) {
            arrayList.add(((e1) it.next()).getType());
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0171, code lost:
    
        r0 = g8.q.f15530g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0173, code lost:
    
        r13 = ((g8.c) s(r12, new i9.k(0))).v0(r13, r7, r0);
        r14.p(r13, r12);
        r14.b(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0188, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x016c, code lost:
    
        if (r2 == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x016e, code lost:
    
        r0 = g8.q.f15531h;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(java.util.Collection r12, g8.f r13, i9.q r14) {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i9.n.e(java.util.Collection, g8.f, i9.q):void");
    }

    public static ArrayList g(Object obj, LinkedList linkedList, r7.l lVar, r7.l lVar2) {
        if (obj == null) {
            a(99);
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        g8.a aVar = (g8.a) lVar.invoke(obj);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            g8.a aVar2 = (g8.a) lVar.invoke(next);
            if (obj == next) {
                it.remove();
            } else {
                int iJ = j(aVar, aVar2);
                if (iJ == 1) {
                    arrayList.add(next);
                    it.remove();
                } else if (iJ == 3) {
                    lVar2.invoke(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public static m i(g8.a aVar, g8.a aVar2) {
        boolean z;
        if (aVar == null) {
            a(40);
            throw null;
        }
        if (aVar2 == null) {
            a(41);
            throw null;
        }
        boolean z5 = aVar instanceof g8.w;
        if ((z5 && !(aVar2 instanceof g8.w)) || (((z = aVar instanceof p0)) && !(aVar2 instanceof p0))) {
            return m.d("Member kind mismatch");
        }
        if (!z5 && !z) {
            throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + aVar);
        }
        if (!aVar.getName().equals(aVar2.getName())) {
            return m.d("Name mismatch");
        }
        m mVarD = (aVar.M() == null) != (aVar2.M() == null) ? m.d("Receiver presence mismatch") : aVar.e().size() != aVar2.e().size() ? m.d("Value parameter number mismatch") : null;
        if (mVarD != null) {
            return mVarD;
        }
        return null;
    }

    public static int j(g8.a aVar, g8.a aVar2) {
        n nVar = f16259c;
        int iC = nVar.l(aVar2, aVar, null).c();
        int iC2 = nVar.m(aVar, aVar2, null, false).c();
        if (iC == 1 && iC2 == 1) {
            return 1;
        }
        return (iC == 3 || iC2 == 3) ? 3 : 2;
    }

    public static boolean k(g8.a aVar, g8.a aVar2) {
        if (aVar == null) {
            a(67);
            throw null;
        }
        if (aVar2 == null) {
            a(68);
            throw null;
        }
        w returnType = aVar.getReturnType();
        w returnType2 = aVar2.getReturnType();
        if (!p(aVar, aVar2)) {
            return false;
        }
        n0 n0VarF = f16259c.f(aVar.getTypeParameters(), aVar2.getTypeParameters());
        if (aVar instanceof g8.w) {
            return o(aVar, returnType, aVar2, returnType2, n0VarF);
        }
        if (!(aVar instanceof p0)) {
            throw new IllegalArgumentException("Unexpected callable: " + aVar.getClass());
        }
        p0 p0Var = (p0) aVar;
        p0 p0Var2 = (p0) aVar2;
        r0 setter = p0Var.getSetter();
        r0 setter2 = p0Var2.getSetter();
        if ((setter == null || setter2 == null) ? true : p(setter, setter2)) {
            return (p0Var.K() && p0Var2.K()) ? v9.e.g(n0VarF, returnType.L0(), returnType2.L0()) : (p0Var.K() || !p0Var2.K()) && o(aVar, returnType, aVar2, returnType2, n0VarF);
        }
        return false;
    }

    public static boolean o(g8.a aVar, w wVar, g8.a aVar2, w wVar2, n0 n0Var) {
        if (aVar == null) {
            a(73);
            throw null;
        }
        if (wVar == null) {
            a(74);
            throw null;
        }
        if (aVar2 == null) {
            a(75);
            throw null;
        }
        if (wVar2 != null) {
            return v9.e.n(v9.e.f22202a, n0Var, wVar.L0(), wVar2.L0());
        }
        a(76);
        throw null;
    }

    public static boolean p(g8.a aVar, g8.a aVar2) {
        if (aVar == null) {
            a(69);
            throw null;
        }
        if (aVar2 != null) {
            Integer numB = g8.q.b(aVar.getVisibility(), aVar2.getVisibility());
            return numB == null || numB.intValue() >= 0;
        }
        a(70);
        throw null;
    }

    public static boolean q(g8.a aVar, g8.a aVar2) {
        if (aVar == null) {
            a(13);
            throw null;
        }
        if (aVar2 == null) {
            a(14);
            throw null;
        }
        boolean zEquals = aVar.equals(aVar2);
        d dVar = d.f16248a;
        if (!zEquals && dVar.a(aVar.a(), aVar2.a(), false)) {
            return true;
        }
        g8.a aVarA = aVar2.a();
        int i10 = f.f16249a;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        f.b(aVar.a(), linkedHashSet);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            if (dVar.a(aVarA, (g8.a) it.next(), false)) {
                return true;
            }
        }
        return false;
    }

    public static void r(g8.c cVar, r7.l lVar) {
        g8.p pVar;
        g8.p pVarG;
        g8.p pVar2;
        if (cVar == null) {
            a(107);
            throw null;
        }
        for (g8.c cVar2 : cVar.k()) {
            if (cVar2.getVisibility() == g8.q.f15530g) {
                r(cVar2, lVar);
            }
        }
        if (cVar.getVisibility() != g8.q.f15530g) {
            return;
        }
        Collection<g8.c> collectionK = cVar.k();
        if (collectionK == null) {
            a(109);
            throw null;
        }
        if (collectionK.isEmpty()) {
            pVarG = g8.q.f15534l;
        } else {
            Iterator it = collectionK.iterator();
            loop3: while (true) {
                pVar = null;
                while (it.hasNext()) {
                    g8.p visibility = ((g8.c) it.next()).getVisibility();
                    if (pVar != null) {
                        Integer numB = g8.q.b(visibility, pVar);
                        if (numB == null) {
                            break;
                        } else if (numB.intValue() > 0) {
                        }
                    }
                    pVar = visibility;
                }
            }
            if (pVar == null) {
                pVarG = null;
                break;
            }
            Iterator it2 = collectionK.iterator();
            while (it2.hasNext()) {
                Integer numB2 = g8.q.b(pVar, ((g8.c) it2.next()).getVisibility());
                if (numB2 == null || numB2.intValue() < 0) {
                    pVarG = null;
                    break;
                }
            }
            pVarG = pVar;
        }
        if (pVarG == null) {
            pVarG = null;
            break;
        }
        if (cVar.getKind() == 2) {
            for (g8.c cVar3 : collectionK) {
                if (cVar3.o() != 4 && !cVar3.getVisibility().equals(pVarG)) {
                    pVarG = null;
                    break;
                }
            }
        } else {
            pVarG = g8.q.g(pVarG.f15522a.c());
        }
        if (pVarG == null) {
            if (lVar != null) {
                lVar.invoke(cVar);
            }
            pVar2 = g8.q.f15528e;
        } else {
            pVar2 = pVarG;
        }
        if (cVar instanceof l0) {
            ((l0) cVar).f19313t = pVar2;
            Iterator it3 = ((p0) cVar).s().iterator();
            while (it3.hasNext()) {
                r((o0) it3.next(), pVarG == null ? null : lVar);
            }
            return;
        }
        if (cVar instanceof j8.w) {
            ((j8.w) cVar).f19384v = pVar2;
            return;
        }
        j0 j0Var = (j0) cVar;
        j0Var.f19298u = pVar2;
        if (pVar2 != j0Var.T().getVisibility()) {
            j0Var.f19292o = false;
        }
    }

    public static Object s(Collection collection, r7.l lVar) {
        Object next;
        if (collection.size() == 1) {
            Object objL0 = x.l0(collection);
            if (objL0 != null) {
                return objL0;
            }
            a(80);
            throw null;
        }
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(s.U(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList2.add(lVar.invoke(it.next()));
        }
        Object objL02 = x.l0(collection);
        g8.a aVar = (g8.a) lVar.invoke(objL02);
        for (Object obj : collection) {
            g8.a aVar2 = (g8.a) lVar.invoke(obj);
            if (aVar2 == null) {
                a(71);
                throw null;
            }
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    arrayList.add(obj);
                    break;
                }
                if (!k(aVar2, (g8.a) it2.next())) {
                    break;
                }
            }
            if (k(aVar2, aVar) && !k(aVar, aVar2)) {
                objL02 = obj;
            }
        }
        if (arrayList.isEmpty()) {
            if (objL02 != null) {
                return objL02;
            }
            a(81);
            throw null;
        }
        if (arrayList.size() == 1) {
            Object objL03 = x.l0(arrayList);
            if (objL03 != null) {
                return objL03;
            }
            a(82);
            throw null;
        }
        Iterator it3 = arrayList.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            if (!(((g8.a) lVar.invoke(next)).getReturnType().L0() instanceof v9.q)) {
                break;
            }
        }
        if (next != null) {
            return next;
        }
        Object objL04 = x.l0(arrayList);
        if (objL04 != null) {
            return objL04;
        }
        a(84);
        throw null;
    }

    public final n0 f(List list, List list2) {
        if (list == null) {
            a(42);
            throw null;
        }
        if (list2 == null) {
            a(43);
            throw null;
        }
        boolean zIsEmpty = list.isEmpty();
        w9.d dVar = w9.d.f22507a;
        w9.e eVar = w9.e.f22508a;
        c.a aVar = this.f16261a;
        if (zIsEmpty) {
            return new n0(true, true, new o(null, aVar), dVar, eVar);
        }
        HashMap map = new HashMap();
        for (int i10 = 0; i10 < list.size(); i10++) {
            map.put(((b1) list.get(i10)).g(), ((b1) list2.get(i10)).g());
        }
        return new n0(true, true, new o(map, aVar), dVar, eVar);
    }

    public final void h(f9.f fVar, Collection collection, Collection collection2, g8.f fVar2, q qVar) {
        Integer numB;
        if (fVar == null) {
            a(52);
            throw null;
        }
        if (collection == null) {
            a(53);
            throw null;
        }
        if (collection2 == null) {
            a(54);
            throw null;
        }
        if (fVar2 == null) {
            a(55);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            g8.c cVar = (g8.c) it.next();
            if (cVar == null) {
                a(59);
                throw null;
            }
            ArrayList arrayList = new ArrayList(collection.size());
            ea.l lVar = new ea.l();
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                g8.c cVar2 = (g8.c) it2.next();
                int iC = l(cVar2, cVar, fVar2).c();
                boolean z = !g8.q.e(cVar2.getVisibility()) && g8.q.f(cVar2, cVar);
                int iC2 = f0.c(iC);
                if (iC2 == 0) {
                    if (z) {
                        lVar.add(cVar2);
                    }
                    arrayList.add(cVar2);
                } else if (iC2 == 2) {
                    if (z) {
                        qVar.d(cVar2, cVar);
                    }
                    arrayList.add(cVar2);
                }
            }
            qVar.p(cVar, lVar);
            linkedHashSet.removeAll(arrayList);
        }
        if (linkedHashSet.size() >= 2) {
            g8.k kVarD = ((g8.c) linkedHashSet.iterator().next()).d();
            if (!linkedHashSet.isEmpty()) {
                Iterator it3 = linkedHashSet.iterator();
                while (it3.hasNext()) {
                    if (((g8.c) it3.next()).d() != kVarD) {
                        LinkedList<g8.c> linkedList = new LinkedList(linkedHashSet);
                        while (!linkedList.isEmpty()) {
                            linkedList.isEmpty();
                            g8.c cVar3 = null;
                            for (g8.c cVar4 : linkedList) {
                                if (cVar3 == null || ((numB = g8.q.b(cVar3.getVisibility(), cVar4.getVisibility())) != null && numB.intValue() < 0)) {
                                    cVar3 = cVar4;
                                }
                            }
                            e(g(cVar3, linkedList, new k(1), new l(qVar, cVar3)), fVar2, qVar);
                        }
                        return;
                    }
                }
            }
        }
        Iterator it4 = linkedHashSet.iterator();
        while (it4.hasNext()) {
            e(Collections.singleton((g8.c) it4.next()), fVar2, qVar);
        }
    }

    public final m l(g8.a aVar, g8.a aVar2, g8.f fVar) {
        if (aVar == null) {
            a(19);
            throw null;
        }
        if (aVar2 != null) {
            return m(aVar, aVar2, fVar, false);
        }
        a(20);
        throw null;
    }

    public final m m(g8.a aVar, g8.a aVar2, g8.f fVar, boolean z) {
        if (aVar == null) {
            a(22);
            throw null;
        }
        if (aVar2 == null) {
            a(23);
            throw null;
        }
        m mVarN = n(aVar, aVar2, z);
        boolean z5 = mVarN.c() == 1;
        List<g> list = f16258b;
        for (g gVar : list) {
            if (gVar.b() != 1 && (!z5 || gVar.b() != 2)) {
                int iC = f0.c(gVar.a(aVar, aVar2, fVar));
                if (iC == 0) {
                    z5 = true;
                } else {
                    if (iC == 1) {
                        return m.b("External condition failed");
                    }
                    if (iC == 2) {
                        return m.d("External condition");
                    }
                }
            }
        }
        if (!z5) {
            return mVarN;
        }
        for (g gVar2 : list) {
            if (gVar2.b() == 1) {
                int iC2 = f0.c(gVar2.a(aVar, aVar2, fVar));
                if (iC2 == 0) {
                    throw new IllegalStateException("Contract violation in " + gVar2.getClass().getName() + " condition. It's not supposed to end with success");
                }
                if (iC2 == 1) {
                    return m.b("External condition failed");
                }
                if (iC2 == 2) {
                    return m.d("External condition");
                }
            }
        }
        m mVar = m.f16256b;
        if (mVar != null) {
            return mVar;
        }
        m.a(0);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00af, code lost:
    
        r14.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final i9.m n(g8.a r17, g8.a r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i9.n.n(g8.a, g8.a, boolean):i9.m");
    }
}
