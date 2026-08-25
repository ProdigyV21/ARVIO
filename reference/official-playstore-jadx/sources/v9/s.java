package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends q implements c1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q f22245n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final w f22246o;

    public s(q qVar, w wVar) {
        super(qVar.f22241l, qVar.f22242m);
        this.f22245n = qVar;
        this.f22246o = wVar;
    }

    @Override // v9.c1
    public final d1 D0() {
        return this.f22245n;
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new s(this.f22245n, this.f22246o);
    }

    @Override // v9.d1
    public final d1 M0(boolean z) {
        return c.x(this.f22245n.M0(z), this.f22246o.L0().M0(z));
    }

    @Override // v9.d1
    public final d1 N0(w9.e eVar) {
        return new s(this.f22245n, this.f22246o);
    }

    @Override // v9.d1
    public final d1 O0(k0 k0Var) {
        return c.x(this.f22245n.O0(k0Var), this.f22246o);
    }

    @Override // v9.q
    public final b0 P0() {
        return this.f22245n.P0();
    }

    @Override // v9.q
    public final String Q0(h9.h hVar, h9.h hVar2) {
        h9.l lVar = hVar2.f15910a;
        return ((Boolean) lVar.f15938m.getValue(lVar, h9.l.X[11])).booleanValue() ? hVar.V(this.f22246o) : this.f22245n.Q0(hVar, hVar2);
    }

    @Override // v9.c1
    public final w g0() {
        return this.f22246o;
    }

    @Override // v9.q
    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f22246o + ")] " + this.f22245n;
    }
}
