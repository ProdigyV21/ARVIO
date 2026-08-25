package j8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f0 extends p implements g8.h0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f9.c f19274o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f19275p;

    public f0(g8.b0 b0Var, f9.c cVar) {
        super(b0Var, h8.g.f15863a, cVar.g(), g8.v0.f15554b);
        this.f19274o = cVar;
        this.f19275p = "package " + cVar + " of " + b0Var;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.B(this, obj);
    }

    @Override // g8.h0
    public final f9.c c() {
        return this.f19274o;
    }

    @Override // j8.p, g8.k
    public final g8.k d() {
        return (g8.b0) super.d();
    }

    @Override // j8.p, g8.n
    public g8.v0 f() {
        return g8.v0.f15554b;
    }

    @Override // j8.o
    public String toString() {
        return this.f19275p;
    }

    @Override // j8.p, g8.k
    public final g8.b0 d() {
        return (g8.b0) super.d();
    }
}
