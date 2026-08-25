package w9;

import g8.b1;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.l0;
import v9.b0;
import v9.d1;
import v9.o0;
import v9.r0;
import v9.t0;
import v9.z;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f22527a = new n();

    @Override // y9.p
    public final int A(y9.l lVar) {
        return f.t(lVar);
    }

    @Override // y9.p
    public final b0 B(y9.i iVar) {
        return f.j(iVar);
    }

    @Override // y9.p
    public final b0 C(y9.d dVar) {
        return f.R(dVar);
    }

    @Override // y9.p
    public final o0 D(y9.i iVar) {
        return f.X(iVar);
    }

    @Override // y9.p
    public final y9.c E(y9.i iVar) {
        return f.e(this, iVar);
    }

    @Override // w9.b
    public final d1 F(y9.i iVar, y9.i iVar2) {
        return f.m(this, iVar, iVar2);
    }

    @Override // y9.p
    public final boolean G(y9.m mVar) {
        return f.A(mVar);
    }

    @Override // y9.p
    public final boolean H(y9.i iVar) {
        return f.E(f.X(iVar));
    }

    @Override // y9.p
    public final b1 I(y9.s sVar) {
        return f.r(sVar);
    }

    @Override // y9.p
    public final Collection J(y9.i iVar) {
        return f.T(this, iVar);
    }

    @Override // y9.p
    public final boolean K(y9.m mVar) {
        return f.y(mVar);
    }

    @Override // y9.p
    public final v9.m L(y9.i iVar) {
        return f.f(iVar);
    }

    @Override // y9.p
    public final b0 M(y9.h hVar) {
        b0 b0VarO;
        v9.q qVarG = f.g(hVar);
        return (qVarG == null || (b0VarO = f.O(qVarG)) == null) ? f.h(hVar) : b0VarO;
    }

    @Override // y9.p
    public final boolean N(d1 d1Var) {
        return f.G(M(d1Var)) != f.G(Q(d1Var));
    }

    @Override // y9.p
    public final boolean O(y9.c cVar) {
        return f.K(cVar);
    }

    @Override // y9.p
    public final void P(y9.i iVar) {
        f.M(iVar);
    }

    @Override // y9.p
    public final b0 Q(y9.h hVar) {
        b0 b0VarZ;
        v9.q qVarG = f.g(hVar);
        return (qVarG == null || (b0VarZ = f.Z(qVarG)) == null) ? f.h(hVar) : b0VarZ;
    }

    @Override // y9.p
    public final void R(y9.i iVar) {
        f.N(iVar);
    }

    @Override // y9.p
    public final y9.l S(y9.k kVar, int i10) {
        if (kVar instanceof y9.i) {
            return f.o((y9.h) kVar, i10);
        }
        if (kVar instanceof y9.a) {
            return (y9.l) ((y9.a) kVar).get(i10);
        }
        throw new IllegalStateException(("unknown type argument list type: " + kVar + ", " + l0.f19747a.b(kVar.getClass())).toString());
    }

    @Override // y9.p
    public final int T(y9.h hVar) {
        return f.c(hVar);
    }

    @Override // y9.p
    public final int U(y9.m mVar) {
        return f.S(mVar);
    }

    @Override // y9.p
    public final b0 V(y9.h hVar) {
        return f.h(hVar);
    }

    @Override // y9.p
    public final boolean W(y9.n nVar, y9.m mVar) {
        return f.v(nVar, mVar);
    }

    @Override // y9.p
    public final y9.k X(y9.i iVar) {
        return f.d(iVar);
    }

    @Override // y9.p
    public final y9.l Y(y9.i iVar, int i10) {
        if (i10 < 0 || i10 >= f.c(iVar)) {
            return null;
        }
        return f.o(iVar, i10);
    }

    @Override // y9.p
    public final y9.h Z(y9.h hVar) {
        return f.b0(this, hVar);
    }

    @Override // y9.p
    public final boolean a(y9.m mVar) {
        return f.z(mVar);
    }

    @Override // y9.p
    public final y9.i a0(y9.i iVar) {
        b0 b0VarR;
        v9.m mVarF = f.f(iVar);
        return (mVarF == null || (b0VarR = f.R(mVarF)) == null) ? iVar : b0VarR;
    }

    @Override // y9.p
    public final d1 b(y9.c cVar) {
        return f.P(cVar);
    }

    @Override // y9.p
    public final boolean b0(y9.m mVar) {
        return f.B(mVar);
    }

    @Override // y9.p
    public final boolean c(y9.c cVar) {
        return cVar instanceof j9.a;
    }

    @Override // y9.p
    public final Collection c0(y9.m mVar) {
        return f.W(mVar);
    }

    @Override // y9.p
    public final a d(y9.i iVar) {
        return f.V(this, iVar);
    }

    @Override // y9.p
    public final b0 d0(y9.f fVar) {
        return f.Z(fVar);
    }

    @Override // y9.p
    public final int e(y9.k kVar) {
        if (kVar instanceof y9.i) {
            return f.c((y9.h) kVar);
        }
        if (kVar instanceof y9.a) {
            return ((y9.a) kVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + kVar + ", " + l0.f19747a.b(kVar.getClass())).toString());
    }

    @Override // y9.p
    public final boolean e0(y9.l lVar) {
        return f.L(lVar);
    }

    @Override // y9.p
    public final y9.l f(y9.h hVar, int i10) {
        return f.o(hVar, i10);
    }

    @Override // y9.p
    public final int f0(y9.n nVar) {
        return f.u(nVar);
    }

    @Override // y9.p
    public final d1 g(y9.l lVar) {
        return f.q(lVar);
    }

    @Override // y9.p
    public final boolean g0(y9.i iVar) {
        return f.G(iVar);
    }

    @Override // y9.p
    public final y9.n h(y9.m mVar, int i10) {
        return f.p(mVar, i10);
    }

    @Override // y9.p
    public final boolean h0(y9.m mVar) {
        return f.F(mVar);
    }

    @Override // y9.p
    public final boolean i(y9.i iVar) {
        return f.z(f.X(iVar));
    }

    @Override // y9.p
    public final boolean i0(y9.m mVar, y9.m mVar2) {
        return f.b(mVar, mVar2);
    }

    @Override // y9.p
    public final t0 j(y9.h hVar) {
        return f.i(hVar);
    }

    @Override // y9.p
    public final boolean j0(y9.m mVar) {
        return f.H(mVar);
    }

    @Override // y9.p
    public final boolean k(y9.i iVar) {
        return f.H(t(iVar)) && !f.I(iVar);
    }

    @Override // y9.p
    public final boolean k0(y9.h hVar) {
        return hVar instanceof z;
    }

    @Override // y9.p
    public final b0 l(y9.f fVar) {
        return f.O(fVar);
    }

    public final boolean l0(y9.h hVar, f9.c cVar) {
        if (androidx.compose.material3.d.B(hVar)) {
            return ((v9.w) hVar).getAnnotations().x(cVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    @Override // y9.p
    public final boolean m(y9.i iVar) {
        b0 b0VarH = f.h(iVar);
        return (b0VarH != null ? f.e(this, b0VarH) : null) != null;
    }

    public final y9.h m0(y9.h hVar) {
        b0 b0VarA0;
        b0 b0VarH = f.h(hVar);
        return (b0VarH == null || (b0VarA0 = f.a0(b0VarH, true)) == null) ? hVar : b0VarA0;
    }

    @Override // y9.p
    public final void n(y9.h hVar) {
        f.g(hVar);
    }

    @Override // y9.p
    public final d1 o(ArrayList arrayList) {
        return f.x(arrayList);
    }

    @Override // y9.p
    public final b0 p(y9.i iVar, boolean z) {
        return f.a0(iVar, z);
    }

    @Override // y9.p
    public final boolean q(y9.h hVar) {
        b0 b0VarH = f.h(hVar);
        return (b0VarH != null ? f.f(b0VarH) : null) != null;
    }

    @Override // y9.p
    public final boolean r(y9.i iVar) {
        return f.C(iVar);
    }

    @Override // y9.p
    public final boolean s(y9.m mVar) {
        return f.E(mVar);
    }

    @Override // y9.p
    public final o0 t(y9.h hVar) {
        b0 b0VarH = f.h(hVar);
        if (b0VarH == null) {
            b0VarH = M(hVar);
        }
        return f.X(b0VarH);
    }

    @Override // y9.p
    public final int u(y9.c cVar) {
        return f.k(cVar);
    }

    @Override // y9.p
    public final v9.q v(y9.h hVar) {
        return f.g(hVar);
    }

    @Override // y9.r
    public final boolean w(y9.i iVar, y9.i iVar2) {
        return f.w(iVar, iVar2);
    }

    @Override // y9.p
    public final r0 x(y9.b bVar) {
        return f.U(bVar);
    }

    @Override // y9.p
    public final i y(y9.c cVar) {
        return f.Y(cVar);
    }

    @Override // y9.p
    public final d1 z(y9.h hVar) {
        return f.Q(hVar);
    }
}
