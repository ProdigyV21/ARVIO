package t8;

import g8.p0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m2.f0;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 extends d0 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f21906p = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w8.g f21907n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final i f21908o;

    public c0(androidx.core.provider.e eVar, w8.g gVar, i iVar) {
        super(eVar, null);
        this.f21907n = gVar;
        this.f21908o = iVar;
    }

    public static p0 u(p0 p0Var) {
        if (p0Var.getKind() != 2) {
            return p0Var;
        }
        Collection collectionK = p0Var.k();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collectionK, 10));
        Iterator it = collectionK.iterator();
        while (it.hasNext()) {
            arrayList.add(u((p0) it.next()));
        }
        return (p0) kotlin.collections.x.R0(kotlin.collections.x.g0(arrayList));
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        return null;
    }

    @Override // t8.z
    public final Set h(p9.f fVar, r7.l lVar) {
        return kotlin.collections.b0.f19686i;
    }

    @Override // t8.z
    public final Set i(p9.f fVar, p9.k kVar) {
        Set setF1 = kotlin.collections.x.f1(((c) this.f21992e.invoke()).a());
        i iVar = this.f21908o;
        c0 c0VarX = f0.x(iVar);
        Set setA = c0VarX != null ? c0VarX.a() : null;
        if (setA == null) {
            setA = kotlin.collections.b0.f19686i;
        }
        setF1.addAll(setA);
        if (this.f21907n.s()) {
            setF1.addAll(t7.a.E(d8.q.f14758c, d8.q.f14756a));
        }
        androidx.core.provider.e eVar = this.f21989b;
        setF1.addAll(((s8.a) eVar.f2140l).x.a(eVar, iVar));
        return setF1;
    }

    @Override // t8.z
    public final void j(f9.f fVar, ArrayList arrayList) {
        androidx.core.provider.e eVar = this.f21989b;
        ((s8.a) eVar.f2140l).x.b(eVar, this.f21908o, fVar, arrayList);
    }

    @Override // t8.z
    public final c k() {
        return new a(this.f21907n, j.f21939n);
    }

    @Override // t8.z
    public final void l(LinkedHashSet linkedHashSet, f9.f fVar) {
        Collection collectionG1;
        i iVar = this.f21908o;
        c0 c0VarX = f0.x(iVar);
        if (c0VarX == null) {
            collectionG1 = kotlin.collections.b0.f19686i;
        } else {
            collectionG1 = kotlin.collections.x.g1(!c0VarX.a().contains(fVar) ? kotlin.collections.z.f19728i : (Collection) c0VarX.f21995h.invoke(fVar));
        }
        Collection collection = collectionG1;
        s8.a aVar = (s8.a) this.f21989b.f2140l;
        linkedHashSet.addAll(f4.f.v(fVar, collection, linkedHashSet, this.f21908o, aVar.f21650f, aVar.f21664u.a()));
        if (this.f21907n.s()) {
            if (fVar.equals(d8.q.f14758c)) {
                linkedHashSet.add(i9.q.i(iVar));
            } else if (fVar.equals(d8.q.f14756a)) {
                linkedHashSet.add(i9.q.j(iVar));
            }
        }
    }

    @Override // t8.d0, t8.z
    public final void m(f9.f fVar, ArrayList arrayList) {
        ArrayList arrayList2;
        f9.f fVar2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        f8.l lVar = new f8.l(fVar, 1);
        i iVar = this.f21908o;
        ea.o.f(Collections.singletonList(iVar), a0.f21901i, new b0(iVar, linkedHashSet, lVar));
        boolean zIsEmpty = arrayList.isEmpty();
        androidx.core.provider.e eVar = this.f21989b;
        if (zIsEmpty) {
            f9.f fVar3 = fVar;
            arrayList2 = arrayList;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : linkedHashSet) {
                p0 p0VarU = u((p0) obj);
                Object arrayList3 = linkedHashMap.get(p0VarU);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    linkedHashMap.put(p0VarU, arrayList3);
                }
                ((List) arrayList3).add(obj);
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Collection collection = (Collection) ((Map.Entry) it.next()).getValue();
                s8.a aVar = (s8.a) eVar.f2140l;
                f9.f fVar4 = fVar3;
                kotlin.collections.x.b0(arrayList4, f4.f.v(fVar4, collection, arrayList2, this.f21908o, aVar.f21650f, aVar.f21664u.a()));
                fVar3 = fVar4;
            }
            fVar2 = fVar3;
            arrayList2.addAll(arrayList4);
        } else {
            s8.a aVar2 = (s8.a) eVar.f2140l;
            arrayList2 = arrayList;
            arrayList2.addAll(f4.f.v(fVar, linkedHashSet, arrayList, this.f21908o, aVar2.f21650f, aVar2.f21664u.a()));
            fVar2 = fVar;
        }
        if (this.f21907n.s() && fVar2.equals(d8.q.f14757b)) {
            ea.o.d(arrayList2, i9.q.h(iVar));
        }
    }

    @Override // t8.z
    public final Set n() {
        Set setF1 = kotlin.collections.x.f1(((c) this.f21992e.invoke()).c());
        j jVar = j.f21940o;
        i iVar = this.f21908o;
        ea.o.f(Collections.singletonList(iVar), a0.f21901i, new b0(iVar, setF1, jVar));
        if (this.f21907n.s()) {
            setF1.add(d8.q.f14757b);
        }
        return setF1;
    }

    @Override // t8.z
    public final g8.k p() {
        return this.f21908o;
    }
}
