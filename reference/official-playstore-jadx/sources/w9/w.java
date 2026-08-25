package w9;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.x;
import kotlin.collections.z;
import v9.b0;
import v9.d1;
import v9.k0;
import v9.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f22536a = new w();

    public static ArrayList a(AbstractCollection abstractCollection, r7.p pVar) {
        ArrayList arrayList = new ArrayList(abstractCollection);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b0 b0Var = (b0) it.next();
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    b0 b0Var2 = (b0) it2.next();
                    if (b0Var2 != b0Var && ((Boolean) pVar.invoke(b0Var2, b0Var)).booleanValue()) {
                        it.remove();
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7, types: [ba.d, v9.k0] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [v9.k0] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object, v9.b0, v9.w] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v21, types: [v9.b0] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.util.Set] */
    public final b0 b(ArrayList arrayList) {
        b0 b0Var;
        b0 b0VarC;
        arrayList.size();
        ArrayList<b0> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b0 b0Var2 = (b0) it.next();
            if (b0Var2.I0() instanceof v9.v) {
                Collection collectionD = b0Var2.I0().d();
                ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(collectionD, 10));
                Iterator it2 = collectionD.iterator();
                while (it2.hasNext()) {
                    b0 b0VarV = v9.c.v((v9.w) it2.next());
                    if (b0Var2.J0()) {
                        b0VarV = b0VarV.M0(true);
                    }
                    arrayList3.add(b0VarV);
                }
                arrayList2.addAll(arrayList3);
            } else {
                arrayList2.add(b0Var2);
            }
        }
        Iterator it3 = arrayList2.iterator();
        u uVarA = u.f22530i;
        while (it3.hasNext()) {
            uVarA = uVarA.a((d1) it3.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (b0 b0VarM0 : arrayList2) {
            if (uVarA == u.f22533n) {
                if (b0VarM0 instanceof g) {
                    g gVar = (g) b0VarM0;
                    b0VarM0 = new g(gVar.f22510l, gVar.f22511m, gVar.f22512n, gVar.f22513o, gVar.f22514p, true);
                }
                b0 b0VarP = v9.e.p(b0VarM0, false);
                b0VarM0 = (b0VarP == null && (b0VarP = v9.c.j(b0VarM0)) == null) ? b0VarM0.M0(false) : b0VarP;
            }
            linkedHashSet.add(b0VarM0);
        }
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it4 = arrayList.iterator();
        while (it4.hasNext()) {
            arrayList4.add(((b0) it4.next()).H0());
        }
        Iterator it5 = arrayList4.iterator();
        if (!it5.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        ?? next = it5.next();
        while (true) {
            b0Var = null;
            if (!it5.hasNext()) {
                break;
            }
            k0 k0Var = (k0) it5.next();
            next = (k0) next;
            fi.iki.elonen.f fVar = k0.f22220l;
            if (!next.isEmpty() || !k0Var.isEmpty()) {
                ArrayList arrayList5 = new ArrayList();
                Iterator it6 = ((ConcurrentHashMap) fVar.f15416i).values().iterator();
                while (it6.hasNext()) {
                    int iIntValue = ((Number) it6.next()).intValue();
                    v9.h hVar = (v9.h) next.f7274i.get(iIntValue);
                    v9.h hVar2 = (v9.h) k0Var.f7274i.get(iIntValue);
                    if (hVar != null) {
                        if (!kotlin.jvm.internal.p.a(hVar2, hVar)) {
                            hVar = null;
                        }
                        hVar2 = hVar;
                    } else if (hVar2 == null || !kotlin.jvm.internal.p.a(hVar, hVar2)) {
                        hVar2 = null;
                    }
                    ea.o.d(arrayList5, hVar2);
                }
                next = fi.iki.elonen.f.H(arrayList5);
            }
        }
        k0 k0Var2 = (k0) next;
        if (linkedHashSet.size() == 1) {
            b0VarC = (b0) x.Q0(linkedHashSet);
        } else {
            ArrayList arrayListA = a(linkedHashSet, new v(2, this, 0));
            arrayListA.isEmpty();
            if (!arrayListA.isEmpty()) {
                Iterator it7 = arrayListA.iterator();
                if (!it7.hasNext()) {
                    throw new UnsupportedOperationException("Empty collection can't be reduced.");
                }
                ?? next2 = it7.next();
                while (it7.hasNext()) {
                    b0 b0Var3 = (b0) it7.next();
                    next2 = (b0) next2;
                    if (next2 != 0 && b0Var3 != null) {
                        o0 o0VarI0 = next2.I0();
                        o0 o0VarI02 = b0Var3.I0();
                        boolean z = o0VarI0 instanceof k9.n;
                        if (z && (o0VarI02 instanceof k9.n)) {
                            Set set = ((k9.n) o0VarI0).f19535a;
                            Set set2 = ((k9.n) o0VarI02).f19535a;
                            Set setF1 = x.f1(set);
                            x.b0(setF1, set2);
                            k9.n nVar = new k9.n(setF1);
                            k0.f22220l.getClass();
                            next2 = v9.e.s(z.f19728i, x9.k.a(2, true, "unknown integer literal type"), k0.f22221m, nVar, false);
                        } else if (z) {
                            if (((k9.n) o0VarI0).f19535a.contains(b0Var3)) {
                                next2 = b0Var3;
                            }
                        } else if (!(o0VarI02 instanceof k9.n) || !((k9.n) o0VarI02).f19535a.contains(next2)) {
                        }
                    }
                    next2 = 0;
                }
                b0Var = (b0) next2;
            }
            if (b0Var != null) {
                b0VarC = b0Var;
            } else {
                k.f22525b.getClass();
                ArrayList arrayListA2 = a(arrayListA, new v(2, j.f22524b, 1));
                arrayListA2.isEmpty();
                b0VarC = arrayListA2.size() < 2 ? (b0) x.Q0(arrayListA2) : new v9.v(linkedHashSet).c();
            }
        }
        return b0VarC.O0(k0Var2);
    }
}
