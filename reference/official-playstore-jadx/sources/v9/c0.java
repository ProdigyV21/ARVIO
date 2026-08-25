package v9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 extends b0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o0 f22192l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f22193m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f22194n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final p9.n f22195o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final r7.l f22196p;

    public c0(o0 o0Var, List list, boolean z, p9.n nVar, r7.l lVar) {
        this.f22192l = o0Var;
        this.f22193m = list;
        this.f22194n = z;
        this.f22195o = nVar;
        this.f22196p = lVar;
        if (!(nVar instanceof x9.g) || (nVar instanceof x9.l)) {
            return;
        }
        throw new IllegalStateException("SimpleTypeImpl should not be created for error type: " + nVar + '\n' + o0Var);
    }

    @Override // v9.w
    public final List G0() {
        return this.f22193m;
    }

    @Override // v9.w
    public final k0 H0() {
        k0.f22220l.getClass();
        return k0.f22221m;
    }

    @Override // v9.w
    public final o0 I0() {
        return this.f22192l;
    }

    @Override // v9.w
    public final boolean J0() {
        return this.f22194n;
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        b0 b0Var = (b0) this.f22196p.invoke(eVar);
        return b0Var == null ? this : b0Var;
    }

    @Override // v9.d1
    public final d1 N0(w9.e eVar) {
        b0 b0Var = (b0) this.f22196p.invoke(eVar);
        return b0Var == null ? this : b0Var;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return z == this.f22194n ? this : z ? new y(this, 1) : new y(this, 0);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return k0Var.isEmpty() ? this : new d0(this, k0Var);
    }

    @Override // v9.w
    public final p9.n l() {
        return this.f22195o;
    }
}
