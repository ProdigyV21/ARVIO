package t8;

import com.google.common.util.concurrent.r0;
import j8.t0;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements p9.n {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21909f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.core.provider.e f21910b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p f21911c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f21912d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.s f21913e;

    static {
        m0 m0Var = l0.f19747a;
        f21909f = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(d.class), "kotlinScopes", "getKotlinScopes()[Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
    }

    public d(androidx.core.provider.e eVar, w8.t tVar, p pVar) {
        this.f21910b = eVar;
        this.f21911c = pVar;
        this.f21912d = new u(eVar, tVar, pVar);
        this.f21913e = ((s8.a) eVar.f2140l).f21645a.d(new t0(this, 7));
    }

    @Override // p9.n
    public final Set a() {
        p9.n[] nVarArrH = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p9.n nVar : nVarArrH) {
            kotlin.collections.x.b0(linkedHashSet, nVar.a());
        }
        linkedHashSet.addAll(this.f21912d.a());
        return linkedHashSet;
    }

    @Override // p9.n
    public final Set b() {
        p9.n[] nVarArrH = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p9.n nVar : nVarArrH) {
            kotlin.collections.x.b0(linkedHashSet, nVar.b());
        }
        linkedHashSet.addAll(this.f21912d.b());
        return linkedHashSet;
    }

    @Override // p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        xc.d.E0(((s8.a) this.f21910b.f2140l).f21657n, bVar, this.f21911c, fVar);
        p9.n[] nVarArrH = h();
        Collection collectionC = this.f21912d.c(fVar, bVar);
        for (p9.n nVar : nVarArrH) {
            collectionC = r0.h(collectionC, nVar.c(fVar, bVar));
        }
        return collectionC == null ? kotlin.collections.b0.f19686i : collectionC;
    }

    @Override // p9.n
    public final Set d() {
        p9.n[] nVarArrH = h();
        HashSet hashSetH0 = xc.d.h0(nVarArrH.length == 0 ? kotlin.collections.z.f19728i : new ab.k(nVarArrH, 2));
        if (hashSetH0 == null) {
            return null;
        }
        hashSetH0.addAll(this.f21912d.d());
        return hashSetH0;
    }

    @Override // p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        xc.d.E0(((s8.a) this.f21910b.f2140l).f21657n, bVar, this.f21911c, fVar);
        g8.h hVar = null;
        g8.f fVarV = this.f21912d.v(fVar, null);
        if (fVarV != null) {
            return fVarV;
        }
        for (p9.n nVar : h()) {
            g8.h hVarE = nVar.e(fVar, bVar);
            if (hVarE != null) {
                if (!(hVarE instanceof g8.i) || !((g8.i) hVarE).i0()) {
                    return hVarE;
                }
                if (hVar == null) {
                    hVar = hVarE;
                }
            }
        }
        return hVar;
    }

    @Override // p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        p9.n[] nVarArrH = h();
        Collection collectionF = this.f21912d.f(fVar, lVar);
        for (p9.n nVar : nVarArrH) {
            collectionF = r0.h(collectionF, nVar.f(fVar, lVar));
        }
        return collectionF == null ? kotlin.collections.b0.f19686i : collectionF;
    }

    @Override // p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        xc.d.E0(((s8.a) this.f21910b.f2140l).f21657n, eVar, this.f21911c, fVar);
        p9.n[] nVarArrH = h();
        this.f21912d.getClass();
        Collection collectionH = kotlin.collections.z.f19728i;
        for (p9.n nVar : nVarArrH) {
            collectionH = r0.h(collectionH, nVar.g(fVar, eVar));
        }
        return collectionH == null ? kotlin.collections.b0.f19686i : collectionH;
    }

    public final p9.n[] h() {
        kotlin.reflect.m mVar = f21909f[0];
        return (p9.n[]) this.f21913e.invoke();
    }

    public final String toString() {
        return "scope for " + this.f21911c;
    }
}
