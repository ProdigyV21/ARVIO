package v9;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o extends n {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22237l;

    public o(b0 b0Var) {
        this.f22237l = b0Var;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return z == J0() ? this : this.f22237l.M0(z).O0(H0());
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return k0Var != H0() ? new d0(this, k0Var) : this;
    }

    @Override // v9.n
    public final b0 R0() {
        return this.f22237l;
    }
}
