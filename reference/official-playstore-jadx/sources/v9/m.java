package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends n implements k, y9.d {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22223l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22224m;

    public m(b0 b0Var, boolean z) {
        this.f22223l = b0Var;
        this.f22224m = z;
    }

    @Override // v9.k
    public final boolean A0() {
        b0 b0Var = this.f22223l;
        return (b0Var.I0() instanceof w9.m) || (b0Var.I0().e() instanceof g8.b1);
    }

    @Override // v9.n, v9.w
    public final boolean J0() {
        return false;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return z ? this.f22223l.M0(z) : this;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return new m(this.f22223l.O0(k0Var), this.f22224m);
    }

    @Override // v9.n
    public final b0 R0() {
        return this.f22223l;
    }

    @Override // v9.n
    public final n T0(b0 b0Var) {
        return new m(b0Var, this.f22224m);
    }

    @Override // v9.b0
    public final String toString() {
        return this.f22223l + " & Any";
    }

    @Override // v9.k
    public final d1 v(w wVar) {
        return c.i(wVar.L0(), this.f22224m);
    }
}
