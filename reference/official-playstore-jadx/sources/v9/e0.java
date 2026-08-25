package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends n implements c1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22203l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w f22204m;

    public e0(b0 b0Var, w wVar) {
        this.f22203l = b0Var;
        this.f22204m = wVar;
    }

    @Override // v9.c1
    public final d1 D0() {
        return this.f22203l;
    }

    @Override // v9.n, v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new e0(this.f22203l, this.f22204m);
    }

    @Override // v9.n, v9.d1
    public final d1 N0(w9.e eVar) {
        return new e0(this.f22203l, this.f22204m);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return (b0) c.x(this.f22203l.M0(z), this.f22204m.L0().M0(z));
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return (b0) c.x(this.f22203l.O0(k0Var), this.f22204m);
    }

    @Override // v9.n
    public final b0 R0() {
        return this.f22203l;
    }

    @Override // v9.n
    /* JADX INFO: renamed from: S0 */
    public final b0 K0(w9.e eVar) {
        return new e0(this.f22203l, this.f22204m);
    }

    @Override // v9.n
    public final n T0(b0 b0Var) {
        return new e0(b0Var, this.f22204m);
    }

    @Override // v9.c1
    public final w g0() {
        return this.f22204m;
    }

    @Override // v9.b0
    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f22204m + ")] " + this.f22203l;
    }
}
