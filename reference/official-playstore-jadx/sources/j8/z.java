package j8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends o implements g8.m0 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f19396r;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d0 f19397m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f9.c f19398n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final u9.s f19399o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final u9.s f19400p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final p9.j f19401q;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f19396r = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(z.class), "fragments", "getFragments()Ljava/util/List;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(z.class), "empty", "getEmpty()Z"))};
    }

    public z(d0 d0Var, f9.c cVar, u9.v vVar) {
        super(h8.g.f15863a, cVar.g());
        this.f19397m = d0Var;
        this.f19398n = cVar;
        this.f19399o = vVar.d(new y(this, 1));
        this.f19400p = vVar.d(new y(this, 0));
        this.f19401q = new p9.j(vVar, new y(this, 2));
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.A(this, obj);
    }

    @Override // g8.m0
    public final f9.c c() {
        return this.f19398n;
    }

    @Override // g8.k
    public final g8.k d() {
        f9.c cVar = this.f19398n;
        if (cVar.d()) {
            return null;
        }
        return this.f19397m.B(cVar.e());
    }

    public final boolean equals(Object obj) {
        g8.m0 m0Var = obj instanceof g8.m0 ? (g8.m0) obj : null;
        return m0Var != null && kotlin.jvm.internal.p.a(this.f19398n, m0Var.c()) && kotlin.jvm.internal.p.a(this.f19397m, m0Var.x0());
    }

    @Override // g8.m0
    public final List h0() {
        kotlin.reflect.m mVar = f19396r[0];
        return (List) this.f19399o.invoke();
    }

    public final int hashCode() {
        return this.f19398n.hashCode() + (this.f19397m.hashCode() * 31);
    }

    @Override // g8.m0
    public final boolean isEmpty() {
        kotlin.reflect.m mVar = f19396r[1];
        return ((Boolean) this.f19400p.invoke()).booleanValue();
    }

    @Override // g8.m0
    public final p9.n l() {
        return this.f19401q;
    }

    @Override // g8.m0
    public final d0 x0() {
        return this.f19397m;
    }
}
