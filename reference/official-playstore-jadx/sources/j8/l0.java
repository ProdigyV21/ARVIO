package j8;

import g8.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public class l0 extends x0 implements g8.p0 {
    public final boolean A;
    public final boolean B;
    public List C;
    public g8.s0 D;
    public g8.s0 E;
    public ArrayList F;
    public m0 G;
    public g8.r0 H;
    public g8.t I;
    public g8.t J;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f19312s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public g8.p f19313t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Collection f19314u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final g8.p0 f19315v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f19316w;
    public final boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f19317y;
    public final boolean z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l0(g8.k kVar, g8.p0 p0Var, h8.h hVar, int i10, g8.p pVar, boolean z, f9.f fVar, int i11, g8.v0 v0Var, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13) {
        super(kVar, hVar, fVar, z, v0Var);
        if (kVar == null) {
            v(0);
            throw null;
        }
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (i10 == 0) {
            v(2);
            throw null;
        }
        if (pVar == null) {
            v(3);
            throw null;
        }
        if (fVar == null) {
            v(4);
            throw null;
        }
        if (i11 == 0) {
            v(5);
            throw null;
        }
        if (v0Var == null) {
            v(6);
            throw null;
        }
        this.f19314u = null;
        this.C = Collections.EMPTY_LIST;
        this.f19312s = i10;
        this.f19313t = pVar;
        this.f19315v = p0Var == null ? this : p0Var;
        this.f19316w = i11;
        this.x = z5;
        this.f19317y = z10;
        this.z = z11;
        this.A = z12;
        this.B = z13;
    }

    public static l0 I0(g8.k kVar, int i10, boolean z, f9.f fVar, int i11, g8.v0 v0Var) {
        g8.p pVar = g8.q.f15528e;
        if (kVar == null) {
            v(7);
            throw null;
        }
        if (i10 == 0) {
            v(9);
            throw null;
        }
        if (fVar == null) {
            v(11);
            throw null;
        }
        if (i11 == 0) {
            v(12);
            throw null;
        }
        if (v0Var != null) {
            return new l0(kVar, null, h8.g.f15863a, i10, pVar, z, fVar, i11, v0Var, false, false, false, false, false);
        }
        v(13);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void v(int r11) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.l0.v(int):void");
    }

    @Override // g8.c
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public final l0 v0(g8.k kVar, int i10, g8.p pVar) {
        k0 k0Var = new k0(this);
        if (kVar == null) {
            k0.a(0);
            throw null;
        }
        k0Var.f19300a = kVar;
        k0Var.f19303d = null;
        if (i10 == 0) {
            k0.a(6);
            throw null;
        }
        k0Var.f19301b = i10;
        k0Var.f19302c = pVar;
        k0Var.f19304e = 2;
        k0Var.f19306g = false;
        l0 l0VarB = k0Var.b();
        if (l0VarB != null) {
            return l0VarB;
        }
        v(42);
        throw null;
    }

    @Override // j8.w0, g8.a
    public final g8.s0 I() {
        return this.D;
    }

    public l0 J0(g8.k kVar, int i10, g8.p pVar, g8.p0 p0Var, int i11, f9.f fVar) {
        if (kVar == null) {
            v(32);
            throw null;
        }
        if (i10 == 0) {
            v(33);
            throw null;
        }
        if (pVar == null) {
            v(34);
            throw null;
        }
        if (i11 == 0) {
            v(35);
            throw null;
        }
        if (fVar == null) {
            v(36);
            throw null;
        }
        return new l0(kVar, p0Var, getAnnotations(), i10, pVar, this.f19391p, fVar, i11, g8.v0.f15554b, this.x, isConst(), this.z, isExternal(), this.B);
    }

    public final void K0(m0 m0Var, n0 n0Var, g8.t tVar, g8.t tVar2) {
        this.G = m0Var;
        this.H = n0Var;
        this.I = tVar;
        this.J = tVar2;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.q(this, obj);
    }

    @Override // j8.w0, g8.a
    public final g8.s0 M() {
        return this.E;
    }

    public final void M0(v9.w wVar, List list, g8.s0 s0Var, x xVar, List list2) {
        if (wVar == null) {
            v(17);
            throw null;
        }
        if (list == null) {
            v(18);
            throw null;
        }
        if (list2 == null) {
            v(19);
            throw null;
        }
        this.f19387o = wVar;
        this.F = new ArrayList(list);
        this.E = xVar;
        this.D = s0Var;
        this.C = list2;
    }

    @Override // g8.p0
    public final g8.t N() {
        return this.J;
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    @Override // g8.a
    public Object b0(a.InterfaceC0225a interfaceC0225a) {
        return null;
    }

    @Override // g8.p0
    public final m0 getGetter() {
        return this.G;
    }

    @Override // g8.c
    public final int getKind() {
        int i10 = this.f19316w;
        if (i10 != 0) {
            return i10;
        }
        v(39);
        throw null;
    }

    @Override // j8.w0, g8.a
    public final v9.w getReturnType() {
        v9.w type = getType();
        if (type != null) {
            return type;
        }
        v(23);
        throw null;
    }

    @Override // g8.p0
    public final g8.r0 getSetter() {
        return this.H;
    }

    @Override // j8.w0, g8.a
    public final List getTypeParameters() {
        ArrayList arrayList = this.F;
        if (arrayList != null) {
            return arrayList;
        }
        throw new IllegalStateException("typeParameters == null for " + this);
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p pVar = this.f19313t;
        if (pVar != null) {
            return pVar;
        }
        v(25);
        throw null;
    }

    @Override // g8.a0
    public final boolean i0() {
        return this.z;
    }

    @Override // g8.g1
    public boolean isConst() {
        return this.f19317y;
    }

    @Override // g8.a0
    public boolean isExternal() {
        return this.A;
    }

    @Override // g8.a
    public final Collection k() {
        Collection collection = this.f19314u;
        if (collection == null) {
            collection = Collections.EMPTY_LIST;
        }
        if (collection != null) {
            return collection;
        }
        v(41);
        throw null;
    }

    @Override // g8.a0
    public final int o() {
        int i10 = this.f19312s;
        if (i10 != 0) {
            return i10;
        }
        v(24);
        throw null;
    }

    @Override // g8.p0
    public final g8.t r0() {
        return this.I;
    }

    @Override // g8.p0
    public final ArrayList s() {
        ArrayList arrayList = new ArrayList(2);
        m0 m0Var = this.G;
        if (m0Var != null) {
            arrayList.add(m0Var);
        }
        g8.r0 r0Var = this.H;
        if (r0Var != null) {
            arrayList.add(r0Var);
        }
        return arrayList;
    }

    @Override // g8.a
    public final List s0() {
        List list = this.C;
        if (list != null) {
            return list;
        }
        v(22);
        throw null;
    }

    @Override // g8.g1
    public final boolean t0() {
        return this.x;
    }

    @Override // g8.h1
    public final boolean z() {
        return this.B;
    }

    @Override // g8.c
    public final void z0(Collection collection) {
        if (collection != null) {
            this.f19314u = collection;
        } else {
            v(40);
            throw null;
        }
    }

    @Override // g8.x0
    public final g8.p0 b(y0 y0Var) {
        if (y0Var == null) {
            v(27);
            throw null;
        }
        if (y0Var.f22263a.e()) {
            return this;
        }
        k0 k0Var = new k0(this);
        v9.v0 v0VarF = y0Var.f();
        if (v0VarF == null) {
            k0.a(15);
            throw null;
        }
        k0Var.f19305f = v0VarF;
        k0Var.f19303d = a();
        return k0Var.b();
    }

    @Override // j8.p, j8.o, g8.k
    public final g8.p0 a() {
        g8.p0 p0Var = this.f19315v;
        g8.p0 p0VarA = p0Var == this ? this : p0Var.a();
        if (p0VarA != null) {
            return p0VarA;
        }
        v(38);
        throw null;
    }

    public void L0(v9.w wVar) {
    }
}
