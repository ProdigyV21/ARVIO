package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends n {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22185l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b0 f22186m;

    public a(b0 b0Var, b0 b0Var2) {
        this.f22185l = b0Var;
        this.f22186m = b0Var2;
    }

    @Override // v9.n, v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new a(this.f22185l, this.f22186m);
    }

    @Override // v9.n, v9.d1
    public final d1 N0(w9.e eVar) {
        return new a(this.f22185l, this.f22186m);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return new a(this.f22185l.O0(k0Var), this.f22186m);
    }

    @Override // v9.n
    public final b0 R0() {
        return this.f22185l;
    }

    @Override // v9.n
    /* JADX INFO: renamed from: S0 */
    public final b0 K0(w9.e eVar) {
        return new a(this.f22185l, this.f22186m);
    }

    @Override // v9.n
    public final n T0(b0 b0Var) {
        return new a(b0Var, this.f22186m);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] */
    public final a M0(boolean z) {
        return new a(this.f22185l.M0(z), this.f22186m.M0(z));
    }
}
