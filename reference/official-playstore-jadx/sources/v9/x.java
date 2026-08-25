package v9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends w {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u9.v f22258l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final kotlin.jvm.internal.r f22259m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u9.s f22260n;

    /* JADX WARN: Multi-variable type inference failed */
    public x(u9.v vVar, r7.a aVar) {
        this.f22258l = vVar;
        this.f22259m = (kotlin.jvm.internal.r) aVar;
        this.f22260n = vVar.d(aVar);
    }

    @Override // v9.w
    public final List G0() {
        return M0().G0();
    }

    @Override // v9.w
    public final k0 H0() {
        return M0().H0();
    }

    @Override // v9.w
    public final o0 I0() {
        return M0().I0();
    }

    @Override // v9.w
    public final boolean J0() {
        return M0().J0();
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new x(this.f22258l, new a8.l0(eVar, this, 24));
    }

    @Override // v9.w
    public final d1 L0() {
        w wVarM0 = M0();
        while (wVarM0 instanceof x) {
            wVarM0 = ((x) wVarM0).M0();
        }
        return (d1) wVarM0;
    }

    public final w M0() {
        return (w) this.f22260n.invoke();
    }

    @Override // v9.w
    public final p9.n l() {
        return M0().l();
    }

    public final String toString() {
        return this.f22260n.p() ? M0().toString() : "<Not computed yet>";
    }
}
