package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.k1;
import g8.c0;
import g8.e1;
import g8.h0;
import g8.v0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.z;
import v9.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19860i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k f19861l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(k kVar, int i10) {
        super(0);
        this.f19860i = i10;
        this.f19861l = kVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v38, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v39, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r2v43, types: [java.util.ArrayList] */
    @Override // r7.a
    public final Object invoke() {
        Object next;
        g8.p pVar;
        Object xVar;
        y9.i iVar;
        ?? arrayList;
        int i10 = this.f19860i;
        int i11 = 0;
        int i12 = 1;
        k kVar = this.f19861l;
        switch (i10) {
            case 0:
                return g8.y.c(kVar);
            case 1:
                return kotlin.collections.x.c1(kVar.f19873v.f21731a.f21710e.D(kVar.G));
            case 2:
                a9.n nVar = kVar.f19866o;
                if ((nVar.f582m & 4) != 4) {
                    return null;
                }
                g8.h hVarE = kVar.G0().e(t7.a.x(kVar.f19873v.f21732b, nVar.f585p), o8.e.f20799q);
                if (hVarE instanceof g8.f) {
                    return (g8.f) hVarE;
                }
                return null;
            case 3:
                s9.m mVar = kVar.f19873v;
                List list = kVar.f19866o.z;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : list) {
                    if (c9.e.f7444n.c(((a9.q) obj).f635n).booleanValue()) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(mVar.f21739i.d((a9.q) it.next(), false));
                }
                return kotlin.collections.x.I0(kotlin.collections.x.I0(arrayList3, t7.a.F(kVar.C())), mVar.f21731a.f21718n.a(kVar));
            case 4:
                k kVar2 = this.f19861l;
                int i13 = kVar2.f19872u;
                if (!g8.b.c(i13)) {
                    Iterator it2 = kVar2.f19866o.z.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            next = it2.next();
                            if (!c9.e.f7444n.c(((a9.q) next).f635n).booleanValue()) {
                            }
                        } else {
                            next = null;
                        }
                    }
                    a9.q qVar = (a9.q) next;
                    if (qVar != null) {
                        return kVar2.f19873v.f21739i.d(qVar, true);
                    }
                    return null;
                }
                i9.e eVar = new i9.e(kVar2, null, h8.g.f15863a, true, 1, v0.f15554b);
                List list2 = Collections.EMPTY_LIST;
                int i14 = i9.f.f16249a;
                if (i13 == 3 || g8.b.c(i13) || i9.f.q(kVar2)) {
                    pVar = g8.q.f15524a;
                } else if (i9.f.k(kVar2)) {
                    pVar = g8.q.f15534l;
                    if (pVar == null) {
                        i9.f.a(52);
                        throw null;
                    }
                } else {
                    pVar = g8.q.f15528e;
                }
                eVar.T0(list2, pVar);
                eVar.Q0(kVar2.m());
                return eVar;
            case 5:
                int i15 = kVar.f19870s;
                if (i15 == 2) {
                    List<Integer> list3 = kVar.f19866o.E;
                    if (!list3.isEmpty()) {
                        ArrayList arrayList4 = new ArrayList();
                        for (Integer num : list3) {
                            s9.m mVar2 = kVar.f19873v;
                            g8.f fVar = (g8.f) mVar2.f21731a.f21724t.f21696b.invoke(new s9.f(t7.a.r(mVar2.f21732b, num.intValue()), null));
                            if (fVar != null) {
                                arrayList4.add(fVar);
                            }
                        }
                        return arrayList4;
                    }
                    if (i15 == 2) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        g8.k kVar3 = kVar.A;
                        if (kVar3 instanceof h0) {
                            i9.q.c(kVar, linkedHashSet, ((h0) kVar3).l(), false);
                        }
                        i9.q.c(kVar, linkedHashSet, kVar.R(), true);
                        return kotlin.collections.x.W0(linkedHashSet, new i9.i(i12));
                    }
                }
                return z.f19728i;
            default:
                s9.m mVar3 = kVar.f19873v;
                if (!kVar.isInline() && !kVar.q()) {
                    return null;
                }
                a9.n nVar2 = kVar.f19866o;
                c9.f fVar2 = mVar3.f21732b;
                androidx.work.impl.constraints.j jVar = mVar3.f21734d;
                j jVar2 = new j(i12, mVar3.f21738h, i11);
                j jVar3 = new j(i12, kVar, i12);
                if (nVar2.J.size() > 0) {
                    List list4 = nVar2.J;
                    ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(list4, 10));
                    Iterator it3 = list4.iterator();
                    while (it3.hasNext()) {
                        arrayList5.add(t7.a.x(fVar2, ((Integer) it3.next()).intValue()));
                    }
                    x6.x xVar2 = new x6.x(Integer.valueOf(nVar2.M.size()), Integer.valueOf(nVar2.L.size()));
                    if (xVar2.equals(new x6.x(Integer.valueOf(arrayList5.size()), 0))) {
                        List list5 = nVar2.M;
                        arrayList = new ArrayList(kotlin.collections.s.U(list5, 10));
                        Iterator it4 = list5.iterator();
                        while (it4.hasNext()) {
                            arrayList.add(jVar.b(((Integer) it4.next()).intValue()));
                        }
                    } else {
                        if (!xVar2.equals(new x6.x(0, Integer.valueOf(arrayList5.size())))) {
                            throw new IllegalStateException(("class " + t7.a.x(fVar2, nVar2.f584o) + " has illegal multi-field value class representation").toString());
                        }
                        arrayList = nVar2.L;
                    }
                    ArrayList arrayList6 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                    Iterator it5 = arrayList.iterator();
                    while (it5.hasNext()) {
                        arrayList6.add(jVar2.invoke(it5.next()));
                    }
                    xVar = new c0(kotlin.collections.x.i1(arrayList5, arrayList6));
                } else if ((nVar2.f582m & 8) == 8) {
                    f9.f fVarX = t7.a.x(fVar2, nVar2.G);
                    int i16 = nVar2.f582m;
                    k1 k1VarB = (i16 & 16) == 16 ? nVar2.H : (i16 & 32) == 32 ? jVar.b(nVar2.I) : null;
                    if ((k1VarB == null || (iVar = (y9.i) jVar2.invoke(k1VarB)) == null) && (iVar = (y9.i) jVar3.invoke(fVarX)) == null) {
                        throw new IllegalStateException(("cannot determine underlying type for value class " + f9.f.i(fVar2.getString(nVar2.f584o)) + " with property " + fVarX).toString());
                    }
                    xVar = new g8.x(fVarX, iVar);
                } else {
                    xVar = null;
                }
                if (xVar != null) {
                    return xVar;
                }
                if (kVar.f19867p.a(1, 5, 1)) {
                    return null;
                }
                g8.e eVarC = kVar.C();
                if (eVarC == null) {
                    throw new IllegalStateException(("Inline class has no primary constructor: " + kVar).toString());
                }
                f9.f name = ((e1) kotlin.collections.x.m0(eVarC.e())).getName();
                b0 b0VarH0 = kVar.H0(name);
                if (b0VarH0 != null) {
                    return new g8.x(name, b0VarH0);
                }
                throw new IllegalStateException(("Value class has no underlying property: " + kVar).toString());
        }
    }
}
