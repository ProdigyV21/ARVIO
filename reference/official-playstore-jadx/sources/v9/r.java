package v9;

import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends q implements k {
    @Override // v9.k
    public final boolean A0() {
        b0 b0Var = this.f22241l;
        return (b0Var.I0().e() instanceof g8.b1) && kotlin.jvm.internal.p.a(b0Var.I0(), this.f22242m.I0());
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new r(this.f22241l, this.f22242m);
    }

    @Override // v9.d1
    public final d1 M0(boolean z) {
        return e.j(this.f22241l.M0(z), this.f22242m.M0(z));
    }

    @Override // v9.d1
    public final d1 N0(w9.e eVar) {
        return new r(this.f22241l, this.f22242m);
    }

    @Override // v9.d1
    public final d1 O0(k0 k0Var) {
        return e.j(this.f22241l.O0(k0Var), this.f22242m.O0(k0Var));
    }

    @Override // v9.q
    public final b0 P0() {
        return this.f22241l;
    }

    @Override // v9.q
    public final String Q0(h9.h hVar, h9.h hVar2) {
        boolean zN = hVar2.f15910a.n();
        b0 b0Var = this.f22242m;
        b0 b0Var2 = this.f22241l;
        if (!zN) {
            return hVar.E(hVar.V(b0Var2), hVar.V(b0Var), I0().j());
        }
        return "(" + hVar.V(b0Var2) + ".." + hVar.V(b0Var) + ')';
    }

    @Override // v9.q
    public final String toString() {
        return "(" + this.f22241l + ".." + this.f22242m + ')';
    }

    @Override // v9.k
    public final d1 v(w wVar) {
        d1 d1VarJ;
        d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof q) {
            d1VarJ = d1VarL0;
        } else {
            if (!(d1VarL0 instanceof b0)) {
                throw new NoWhenBranchMatchedException();
            }
            b0 b0Var = (b0) d1VarL0;
            d1VarJ = e.j(b0Var, b0Var.M0(true));
        }
        return c.x(d1VarJ, c.d(d1VarL0));
    }
}
