package t8;

import g8.v0;
import j8.f0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends f0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21960w;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w8.t f21961q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final androidx.core.provider.e f21962r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final u9.s f21963s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final d f21964t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final u9.s f21965u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final h8.h f21966v;

    static {
        m0 m0Var = l0.f19747a;
        f21960w = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(p.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(p.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public p(androidx.core.provider.e eVar, w8.t tVar) {
        s8.a aVar = (s8.a) eVar.f2140l;
        super(aVar.f21658o, tVar.c());
        this.f21961q = tVar;
        androidx.core.provider.e eVarH = qb.d.h(eVar, this, null, 6);
        this.f21962r = eVarH;
        aVar.f21648d.c().f21708c.getClass();
        e9.f fVar = e9.f.f15073g;
        s8.a aVar2 = (s8.a) eVarH.f2140l;
        u9.v vVar = aVar2.f21645a;
        this.f21963s = vVar.d(new o(this, 0));
        this.f21964t = new d(eVarH, tVar, this);
        this.f21965u = vVar.f(new o(this, 2));
        this.f21966v = aVar2.f21665v.f21191b ? h8.g.f15863a : qb.l.z(eVarH, tVar);
        vVar.d(new o(this, 1));
    }

    @Override // j8.f0, j8.p, g8.n
    public final v0 f() {
        return new kb.d(this, 23);
    }

    @Override // androidx.tvprovider.media.tv.h, h8.a
    public final h8.h getAnnotations() {
        return this.f21966v;
    }

    @Override // g8.h0
    public final p9.n l() {
        return this.f21964t;
    }

    @Override // j8.f0, j8.o
    public final String toString() {
        return "Lazy Java package fragment: " + this.f19274o + " of module " + ((s8.a) this.f21962r.f2140l).f21658o;
    }
}
