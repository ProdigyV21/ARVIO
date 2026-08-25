package j8;

import g8.a1;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 extends w implements q0 {
    public static final g0 R;
    public static final /* synthetic */ kotlin.reflect.m[] S;
    public final u9.v O;
    public final a1 P;
    public g8.e Q;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        S = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(r0.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"))};
        R = new g0();
    }

    public r0(u9.v vVar, a1 a1Var, g8.e eVar, q0 q0Var, h8.h hVar, int i10, g8.v0 v0Var) {
        super(i10, f9.h.f15376e, a1Var, q0Var, v0Var, hVar);
        this.O = vVar;
        this.P = a1Var;
        this.C = a1Var.V();
        vVar.c(new a8.l0(this, eVar, 16));
        this.Q = eVar;
    }

    @Override // j8.p
    /* JADX INFO: renamed from: D0 */
    public final g8.n a() {
        return (q0) super.a();
    }

    @Override // j8.w
    public final w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, g8.v0 v0Var, h8.h hVar) {
        if (i10 != 1) {
        }
        return new r0(this.O, this.P, this.Q, this, hVar, 1, v0Var);
    }

    @Override // j8.q0
    public final g8.e P() {
        return this.Q;
    }

    @Override // j8.w, g8.w, g8.x0
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] */
    public final r0 b(y0 y0Var) {
        r0 r0Var = (r0) super.b(y0Var);
        g8.e eVarB = this.Q.a().b(y0.d(r0Var.f19379q));
        if (eVarB == null) {
            return null;
        }
        r0Var.Q = eVarB;
        return r0Var;
    }

    @Override // g8.j
    public final boolean Z() {
        return this.Q.Z();
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.a a() {
        return (q0) super.a();
    }

    @Override // g8.j
    public final g8.f a0() {
        return this.Q.a0();
    }

    @Override // j8.w, g8.w, g8.x0
    public final /* bridge */ /* synthetic */ g8.j b(y0 y0Var) {
        throw null;
    }

    @Override // j8.p, g8.k
    public final g8.i d() {
        return this.P;
    }

    @Override // j8.w, g8.a
    public final v9.w getReturnType() {
        return this.f19379q;
    }

    @Override // j8.w, g8.c
    public final g8.c v0(g8.f fVar, int i10, g8.p pVar) {
        v vVarM0 = M0(y0.f22262b);
        vVarM0.f19350b = fVar;
        vVarM0.f19351c = i10;
        vVarM0.f19352d = pVar;
        vVarM0.f19354f = 2;
        vVarM0.f19360m = false;
        return (q0) vVarM0.x.J0(vVarM0);
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.c a() {
        return (q0) super.a();
    }

    @Override // j8.p, g8.k
    public final g8.k d() {
        return this.P;
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.k a() {
        return (q0) super.a();
    }

    @Override // j8.w, j8.p, j8.o, g8.k
    public final g8.w a() {
        return (q0) super.a();
    }
}
