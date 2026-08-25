package i9;

import g8.b1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.l0;
import v9.b0;
import v9.d1;
import v9.o0;
import v9.r0;
import v9.t0;
import v9.z;
import w9.c;
import y9.s;

/* JADX INFO: loaded from: classes5.dex */
public final class o implements w9.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f16262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.a f16263b;

    public o(HashMap map, c.a aVar) {
        this.f16262a = map;
        this.f16263b = aVar;
    }

    @Override // y9.p
    public final int A(y9.l lVar) {
        return w9.f.t(lVar);
    }

    @Override // y9.p
    public final b0 B(y9.i iVar) {
        return w9.f.j(iVar);
    }

    @Override // y9.p
    public final b0 C(y9.d dVar) {
        return w9.f.R(dVar);
    }

    @Override // y9.p
    public final o0 D(y9.i iVar) {
        return w9.f.X(iVar);
    }

    @Override // y9.p
    public final y9.c E(y9.i iVar) {
        return w9.f.e(this, iVar);
    }

    @Override // w9.b
    public final d1 F(y9.i iVar, y9.i iVar2) {
        return w9.f.m(this, iVar, iVar2);
    }

    @Override // y9.p
    public final boolean G(y9.m mVar) {
        return w9.f.A(mVar);
    }

    @Override // y9.p
    public final boolean H(y9.i iVar) {
        return w9.f.E(w9.f.X(iVar));
    }

    @Override // y9.p
    public final b1 I(s sVar) {
        return w9.f.r(sVar);
    }

    @Override // y9.p
    public final Collection J(y9.i iVar) {
        return w9.f.T(this, iVar);
    }

    @Override // y9.p
    public final boolean K(y9.m mVar) {
        return w9.f.y(mVar);
    }

    @Override // y9.p
    public final v9.m L(y9.i iVar) {
        return w9.f.f(iVar);
    }

    @Override // y9.p
    public final b0 M(y9.h hVar) {
        b0 b0VarO;
        v9.q qVarG = w9.f.g(hVar);
        return (qVarG == null || (b0VarO = w9.f.O(qVarG)) == null) ? w9.f.h(hVar) : b0VarO;
    }

    @Override // y9.p
    public final boolean N(d1 d1Var) {
        return w9.f.G(M(d1Var)) != w9.f.G(Q(d1Var));
    }

    @Override // y9.p
    public final boolean O(y9.c cVar) {
        return w9.f.K(cVar);
    }

    @Override // y9.p
    public final void P(y9.i iVar) {
        w9.f.M(iVar);
    }

    @Override // y9.p
    public final b0 Q(y9.h hVar) {
        b0 b0VarZ;
        v9.q qVarG = w9.f.g(hVar);
        return (qVarG == null || (b0VarZ = w9.f.Z(qVarG)) == null) ? w9.f.h(hVar) : b0VarZ;
    }

    @Override // y9.p
    public final void R(y9.i iVar) {
        w9.f.N(iVar);
    }

    @Override // y9.p
    public final y9.l S(y9.k kVar, int i10) {
        if (kVar instanceof y9.i) {
            return w9.f.o((y9.h) kVar, i10);
        }
        if (kVar instanceof y9.a) {
            return (y9.l) ((y9.a) kVar).get(i10);
        }
        throw new IllegalStateException(("unknown type argument list type: " + kVar + ", " + l0.f19747a.b(kVar.getClass())).toString());
    }

    @Override // y9.p
    public final int T(y9.h hVar) {
        return w9.f.c(hVar);
    }

    @Override // y9.p
    public final int U(y9.m mVar) {
        return w9.f.S(mVar);
    }

    @Override // y9.p
    public final b0 V(y9.h hVar) {
        return w9.f.h(hVar);
    }

    @Override // y9.p
    public final boolean W(y9.n nVar, y9.m mVar) {
        return w9.f.v(nVar, mVar);
    }

    @Override // y9.p
    public final y9.k X(y9.i iVar) {
        return w9.f.d(iVar);
    }

    @Override // y9.p
    public final y9.l Y(y9.i iVar, int i10) {
        if (i10 < 0 || i10 >= w9.f.c(iVar)) {
            return null;
        }
        return w9.f.o(iVar, i10);
    }

    @Override // y9.p
    public final y9.h Z(y9.h hVar) {
        return w9.f.b0(this, hVar);
    }

    @Override // y9.p
    public final boolean a(y9.m mVar) {
        return w9.f.z(mVar);
    }

    @Override // y9.p
    public final y9.i a0(y9.i iVar) {
        b0 b0VarR;
        v9.m mVarF = w9.f.f(iVar);
        return (mVarF == null || (b0VarR = w9.f.R(mVarF)) == null) ? iVar : b0VarR;
    }

    @Override // y9.p
    public final d1 b(y9.c cVar) {
        return w9.f.P(cVar);
    }

    @Override // y9.p
    public final boolean b0(y9.m mVar) {
        return w9.f.B(mVar);
    }

    @Override // y9.p
    public final boolean c(y9.c cVar) {
        return cVar instanceof j9.a;
    }

    @Override // y9.p
    public final Collection c0(y9.m mVar) {
        return w9.f.W(mVar);
    }

    @Override // y9.p
    public final w9.a d(y9.i iVar) {
        return w9.f.V(this, iVar);
    }

    @Override // y9.p
    public final b0 d0(y9.f fVar) {
        return w9.f.Z(fVar);
    }

    @Override // y9.p
    public final int e(y9.k kVar) {
        if (kVar instanceof y9.i) {
            return w9.f.c((y9.h) kVar);
        }
        if (kVar instanceof y9.a) {
            return ((y9.a) kVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + kVar + ", " + l0.f19747a.b(kVar.getClass())).toString());
    }

    @Override // y9.p
    public final boolean e0(y9.l lVar) {
        return w9.f.L(lVar);
    }

    @Override // y9.p
    public final y9.l f(y9.h hVar, int i10) {
        return w9.f.o(hVar, i10);
    }

    @Override // y9.p
    public final int f0(y9.n nVar) {
        return w9.f.u(nVar);
    }

    @Override // y9.p
    public final d1 g(y9.l lVar) {
        return w9.f.q(lVar);
    }

    @Override // y9.p
    public final boolean g0(y9.i iVar) {
        return w9.f.G(iVar);
    }

    @Override // y9.p
    public final y9.n h(y9.m mVar, int i10) {
        return w9.f.p(mVar, i10);
    }

    @Override // y9.p
    public final boolean h0(y9.m mVar) {
        return w9.f.F(mVar);
    }

    @Override // y9.p
    public final boolean i(y9.i iVar) {
        return w9.f.z(w9.f.X(iVar));
    }

    @Override // y9.p
    public final boolean i0(y9.m mVar, y9.m mVar2) {
        if (!(mVar instanceof o0)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (!(mVar2 instanceof o0)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (w9.f.b(mVar, mVar2)) {
            return true;
        }
        o0 o0Var = (o0) mVar;
        o0 o0Var2 = (o0) mVar2;
        if (this.f16263b.b(o0Var, o0Var2)) {
            return true;
        }
        Map map = this.f16262a;
        if (map == null) {
            return false;
        }
        o0 o0Var3 = (o0) map.get(o0Var);
        o0 o0Var4 = (o0) map.get(o0Var2);
        if (o0Var3 == null || !o0Var3.equals(o0Var2)) {
            return o0Var4 != null && o0Var4.equals(o0Var);
        }
        return true;
    }

    @Override // y9.p
    public final t0 j(y9.h hVar) {
        return w9.f.i(hVar);
    }

    @Override // y9.p
    public final boolean j0(y9.m mVar) {
        return w9.f.H(mVar);
    }

    @Override // y9.p
    public final boolean k(y9.i iVar) {
        return w9.f.H(t(iVar)) && !w9.f.I(iVar);
    }

    @Override // y9.p
    public final boolean k0(y9.h hVar) {
        return hVar instanceof z;
    }

    @Override // y9.p
    public final b0 l(y9.f fVar) {
        return w9.f.O(fVar);
    }

    @Override // y9.p
    public final boolean m(y9.i iVar) {
        b0 b0VarH = w9.f.h(iVar);
        return (b0VarH != null ? w9.f.e(this, b0VarH) : null) != null;
    }

    @Override // y9.p
    public final void n(y9.h hVar) {
        w9.f.g(hVar);
    }

    @Override // y9.p
    public final d1 o(ArrayList arrayList) {
        return w9.f.x(arrayList);
    }

    @Override // y9.p
    public final b0 p(y9.i iVar, boolean z) {
        return w9.f.a0(iVar, z);
    }

    @Override // y9.p
    public final boolean q(y9.h hVar) {
        b0 b0VarH = w9.f.h(hVar);
        return (b0VarH != null ? w9.f.f(b0VarH) : null) != null;
    }

    @Override // y9.p
    public final boolean r(y9.i iVar) {
        return w9.f.C(iVar);
    }

    @Override // y9.p
    public final boolean s(y9.m mVar) {
        return w9.f.E(mVar);
    }

    @Override // y9.p
    public final o0 t(y9.h hVar) {
        b0 b0VarH = w9.f.h(hVar);
        if (b0VarH == null) {
            b0VarH = M(hVar);
        }
        return w9.f.X(b0VarH);
    }

    @Override // y9.p
    public final int u(y9.c cVar) {
        return w9.f.k(cVar);
    }

    @Override // y9.p
    public final v9.q v(y9.h hVar) {
        return w9.f.g(hVar);
    }

    @Override // y9.r
    public final boolean w(y9.i iVar, y9.i iVar2) {
        return w9.f.w(iVar, iVar2);
    }

    @Override // y9.p
    public final r0 x(y9.b bVar) {
        return w9.f.U(bVar);
    }

    @Override // y9.p
    public final w9.i y(y9.c cVar) {
        return w9.f.Y(cVar);
    }

    @Override // y9.p
    public final d1 z(y9.h hVar) {
        return w9.f.Q(hVar);
    }
}
