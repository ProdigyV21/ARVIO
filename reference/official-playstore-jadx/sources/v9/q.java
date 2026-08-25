package v9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q extends d1 implements y9.f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22241l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b0 f22242m;

    public q(b0 b0Var, b0 b0Var2) {
        this.f22241l = b0Var;
        this.f22242m = b0Var2;
    }

    @Override // v9.w
    public final List G0() {
        return P0().G0();
    }

    @Override // v9.w
    public final k0 H0() {
        return P0().H0();
    }

    @Override // v9.w
    public final o0 I0() {
        return P0().I0();
    }

    @Override // v9.w
    public final boolean J0() {
        return P0().J0();
    }

    public abstract b0 P0();

    public abstract String Q0(h9.h hVar, h9.h hVar2);

    @Override // v9.w
    public p9.n l() {
        return P0().l();
    }

    public String toString() {
        return h9.h.f15909e.V(this);
    }
}
