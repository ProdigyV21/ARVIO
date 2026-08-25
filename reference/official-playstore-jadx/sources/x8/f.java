package x8;

import v9.b0;
import v9.b1;
import v9.d1;
import v9.k0;
import v9.w;
import v9.z;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends v9.n implements z {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b0 f22647l;

    public f(b0 b0Var) {
        this.f22647l = b0Var;
    }

    @Override // v9.k
    public final boolean A0() {
        return true;
    }

    @Override // v9.n, v9.w
    public final boolean J0() {
        return false;
    }

    @Override // v9.b0, v9.d1
    public final d1 O0(k0 k0Var) {
        return new f(this.f22647l.O0(k0Var));
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        return z ? this.f22647l.M0(true) : this;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        return new f(this.f22647l.O0(k0Var));
    }

    @Override // v9.n
    public final b0 R0() {
        return this.f22647l;
    }

    @Override // v9.n
    public final v9.n T0(b0 b0Var) {
        return new f(b0Var);
    }

    @Override // v9.k
    public final d1 v(w wVar) {
        d1 d1VarL0 = wVar.L0();
        if (!b1.f(d1VarL0) && !b1.e(d1VarL0)) {
            return d1VarL0;
        }
        if (d1VarL0 instanceof b0) {
            b0 b0Var = (b0) d1VarL0;
            b0 b0VarM0 = b0Var.M0(false);
            return !b1.f(b0Var) ? b0VarM0 : new f(b0VarM0);
        }
        if (!(d1VarL0 instanceof v9.q)) {
            throw new IllegalStateException(("Incorrect type: " + d1VarL0).toString());
        }
        v9.q qVar = (v9.q) d1VarL0;
        b0 b0Var2 = qVar.f22241l;
        b0 b0VarM02 = b0Var2.M0(false);
        if (b1.f(b0Var2)) {
            b0VarM02 = new f(b0VarM02);
        }
        b0 b0Var3 = qVar.f22242m;
        b0 b0VarM03 = b0Var3.M0(false);
        if (b1.f(b0Var3)) {
            b0VarM03 = new f(b0VarM03);
        }
        return v9.c.x(v9.e.j(b0VarM02, b0VarM03), v9.c.d(d1VarL0));
    }
}
