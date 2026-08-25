package p9;

import g8.x0;
import j8.t0;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import v9.y0;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f21244b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y0 f21245c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashMap f21246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0 f21247e = new i0(new t0(this, 3));

    public s(n nVar, y0 y0Var) {
        this.f21244b = nVar;
        this.f21245c = new y0(k2.c.K(y0Var.f()));
    }

    @Override // p9.n
    public final Set a() {
        return this.f21244b.a();
    }

    @Override // p9.n
    public final Set b() {
        return this.f21244b.b();
    }

    @Override // p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        return i(this.f21244b.c(fVar, bVar));
    }

    @Override // p9.n
    public final Set d() {
        return this.f21244b.d();
    }

    @Override // p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        g8.h hVarE = this.f21244b.e(fVar, bVar);
        if (hVarE != null) {
            return (g8.h) h(hVarE);
        }
        return null;
    }

    @Override // p9.p
    public final Collection f(f fVar, r7.l lVar) {
        return (Collection) this.f21247e.getValue();
    }

    @Override // p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        return i(this.f21244b.g(fVar, eVar));
    }

    public final g8.k h(g8.k kVar) {
        y0 y0Var = this.f21245c;
        if (y0Var.f22263a.e()) {
            return kVar;
        }
        if (this.f21246d == null) {
            this.f21246d = new HashMap();
        }
        HashMap map = this.f21246d;
        Object objB = map.get(kVar);
        if (objB == null) {
            if (!(kVar instanceof x0)) {
                throw new IllegalStateException(("Unknown descriptor in scope: " + kVar).toString());
            }
            objB = ((x0) kVar).b(y0Var);
            if (objB == null) {
                throw new AssertionError("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, but " + kVar + " substitution fails");
            }
            map.put(kVar, objB);
        }
        return (g8.k) objB;
    }

    public final Collection i(Collection collection) {
        if (this.f21245c.f22263a.e() || collection.isEmpty()) {
            return collection;
        }
        int size = collection.size();
        LinkedHashSet linkedHashSet = new LinkedHashSet(size >= 3 ? (size / 3) + size + 1 : 3);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(h((g8.k) it.next()));
        }
        return linkedHashSet;
    }
}
