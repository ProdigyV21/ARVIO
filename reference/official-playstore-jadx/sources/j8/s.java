package j8;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends p9.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u9.q f19336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u9.q f19337c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f19338d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ t f19339e;

    public s(t tVar, u9.v vVar) {
        if (vVar == null) {
            h(0);
            throw null;
        }
        this.f19339e = tVar;
        this.f19336b = vVar.g(new q(this, 0));
        this.f19337c = vVar.g(new q(this, 1));
        this.f19338d = vVar.d(new d8.h(this, 2));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void h(int r13) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.s.h(int):void");
    }

    @Override // p9.o, p9.n
    public final Set a() {
        Set set = (Set) this.f19339e.f19344s.invoke();
        if (set != null) {
            return set;
        }
        h(17);
        throw null;
    }

    @Override // p9.o, p9.n
    public final Set b() {
        Set set = (Set) this.f19339e.f19344s.invoke();
        if (set != null) {
            return set;
        }
        h(19);
        throw null;
    }

    @Override // p9.o, p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        if (fVar == null) {
            h(5);
            throw null;
        }
        Collection collection = (Collection) this.f19336b.invoke(fVar);
        if (collection != null) {
            return collection;
        }
        h(7);
        throw null;
    }

    @Override // p9.o, p9.n
    public final Set d() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        h(18);
        throw null;
    }

    @Override // p9.o, p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        if (fVar == null) {
            h(13);
            throw null;
        }
        Collection collection = (Collection) this.f19338d.invoke();
        if (collection != null) {
            return collection;
        }
        h(15);
        throw null;
    }

    @Override // p9.o, p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        if (fVar == null) {
            h(1);
            throw null;
        }
        Collection collection = (Collection) this.f19337c.invoke(fVar);
        if (collection != null) {
            return collection;
        }
        h(3);
        throw null;
    }

    public final p9.n i() {
        p9.n nVarL = ((v9.w) ((v9.g) this.f19339e.g()).d().iterator().next()).l();
        if (nVarL != null) {
            return nVarL;
        }
        h(9);
        throw null;
    }

    public final LinkedHashSet j(f9.f fVar, Collection collection) {
        if (fVar == null) {
            h(10);
            throw null;
        }
        if (collection == null) {
            h(11);
            throw null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        i9.n.f16259c.h(fVar, collection, Collections.EMPTY_SET, this.f19339e, new r(linkedHashSet));
        return linkedHashSet;
    }
}
