package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends v0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v0 f22238b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f22239c;

    public p(v0 v0Var, v0 v0Var2) {
        this.f22238b = v0Var;
        this.f22239c = v0Var2;
    }

    @Override // v9.v0
    public final boolean a() {
        return this.f22238b.a() || this.f22239c.a();
    }

    @Override // v9.v0
    public final boolean b() {
        return this.f22238b.b() || this.f22239c.b();
    }

    @Override // v9.v0
    public final h8.h c(h8.h hVar) {
        return this.f22239c.c(this.f22238b.c(hVar));
    }

    @Override // v9.v0
    public final r0 d(w wVar) {
        r0 r0VarD = this.f22238b.d(wVar);
        return r0VarD == null ? this.f22239c.d(wVar) : r0VarD;
    }

    @Override // v9.v0
    public final w f(int i10, w wVar) {
        return this.f22239c.f(i10, this.f22238b.f(i10, wVar));
    }
}
