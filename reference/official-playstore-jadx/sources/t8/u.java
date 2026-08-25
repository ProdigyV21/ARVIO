package t8;

import a8.l0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends d0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w8.t f21972n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final p f21973o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final u9.t f21974p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u9.r f21975q;

    public u(androidx.core.provider.e eVar, w8.t tVar, p pVar) {
        super(eVar, null);
        this.f21972n = tVar;
        this.f21973o = pVar;
        u9.v vVar = ((s8.a) eVar.f2140l).f21645a;
        this.f21974p = vVar.c(new l0(eVar, this, 23));
        this.f21975q = vVar.e(new androidx.navigation.p(this, eVar, 15));
    }

    public static final e9.f u(u uVar) {
        ((s8.a) uVar.f21989b.f2140l).f21648d.c().f21708c.getClass();
        return e9.f.f15073g;
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        return v(fVar, null);
    }

    @Override // t8.z, p9.o, p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        if (!fVar.a(p9.f.f21211l | p9.f.f21205e)) {
            return kotlin.collections.z.f19728i;
        }
        Iterable iterable = (Iterable) this.f21991d.invoke();
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            g8.k kVar = (g8.k) obj;
            if ((kVar instanceof g8.f) && ((Boolean) lVar.invoke(((g8.f) kVar).getName())).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // t8.z, p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        return kotlin.collections.z.f19728i;
    }

    @Override // t8.z
    public final Set h(p9.f fVar, r7.l lVar) {
        if (!fVar.a(p9.f.f21205e)) {
            return kotlin.collections.b0.f19686i;
        }
        Set set = (Set) this.f21974p.invoke();
        if (set != null) {
            HashSet hashSet = new HashSet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(f9.f.j((String) it.next()));
            }
            return hashSet;
        }
        this.f21972n.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (true) {
            kotlin.collections.y yVar = kotlin.collections.y.f19727i;
            if (!yVar.hasNext()) {
                return linkedHashSet;
            }
            w8.g gVar = (w8.g) yVar.next();
            gVar.getClass();
            f9.f name = gVar.getName();
            if (name != null) {
                linkedHashSet.add(name);
            }
        }
    }

    @Override // t8.z
    public final Set i(p9.f fVar, p9.k kVar) {
        return kotlin.collections.b0.f19686i;
    }

    @Override // t8.z
    public final c k() {
        return b.f21902a;
    }

    @Override // t8.z
    public final Set n() {
        return kotlin.collections.b0.f19686i;
    }

    @Override // t8.z
    public final g8.k p() {
        return this.f21973o;
    }

    public final g8.f v(f9.f fVar, w8.g gVar) {
        f9.f fVar2 = f9.h.f15372a;
        if (fVar.d().length() <= 0 || fVar.f15369l) {
            return null;
        }
        Set set = (Set) this.f21974p.invoke();
        if (gVar == null && set != null && !set.contains(fVar.d())) {
            return null;
        }
        return (g8.f) this.f21975q.invoke(new q(fVar, gVar));
    }

    @Override // t8.z
    public final void l(LinkedHashSet linkedHashSet, f9.f fVar) {
    }
}
