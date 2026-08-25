package j8;

import g8.b1;
import g8.c1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends b0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b0 f19242i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y0 f19243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y0 f19244m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f19245n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList f19246o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public v9.j f19247p;

    public a0(b0 b0Var, y0 y0Var) {
        this.f19242i = b0Var;
        this.f19243l = y0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void A0(int r15) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.a0.A0(int):void");
    }

    @Override // g8.f
    public final g8.e C() {
        return this.f19242i.C();
    }

    public final y0 D0() {
        if (this.f19244m == null) {
            y0 y0Var = this.f19243l;
            if (y0Var.f22263a.e()) {
                this.f19244m = y0Var;
            } else {
                List parameters = this.f19242i.g().getParameters();
                this.f19245n = new ArrayList(parameters.size());
                this.f19244m = v9.c.r(parameters, y0Var.f(), this, this.f19245n);
                ArrayList arrayList = this.f19245n;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (!((b1) obj).O()) {
                        arrayList2.add(obj);
                    }
                }
                this.f19246o = arrayList2;
            }
        }
        return this.f19244m;
    }

    @Override // g8.f
    public final boolean E0() {
        return this.f19242i.E0();
    }

    @Override // g8.f
    public final g8.s0 F0() {
        throw new UnsupportedOperationException();
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.l(this, obj);
    }

    @Override // g8.f
    public final p9.n R() {
        p9.n nVarR = this.f19242i.R();
        if (nVarR != null) {
            return nVarR;
        }
        A0(28);
        throw null;
    }

    @Override // g8.f
    public final c1 S() {
        c1 c1VarS = this.f19242i.S();
        if (c1VarS == null) {
            return null;
        }
        boolean z = c1VarS instanceof g8.x;
        y0 y0Var = this.f19243l;
        if (z) {
            g8.x xVar = (g8.x) c1VarS;
            f9.f fVar = xVar.f15556a;
            v9.b0 b0Var = (v9.b0) xVar.f15557b;
            if (b0Var != null && !y0Var.f22263a.e()) {
                b0Var = (v9.b0) D0().i(1, b0Var);
            }
            return new g8.x(fVar, b0Var);
        }
        if (!(c1VarS instanceof g8.c0)) {
            throw new NoWhenBranchMatchedException();
        }
        ArrayList<x6.x> arrayList = ((g8.c0) c1VarS).f15495a;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        for (x6.x xVar2 : arrayList) {
            f9.f fVar2 = (f9.f) xVar2.f22608i;
            v9.b0 b0Var2 = (v9.b0) ((y9.i) xVar2.f22609l);
            if (b0Var2 != null && !y0Var.f22263a.e()) {
                b0Var2 = (v9.b0) D0().i(1, b0Var2);
            }
            arrayList2.add(new x6.x(fVar2, b0Var2));
        }
        return new g8.c0(arrayList2);
    }

    @Override // g8.f
    public final p9.n U() {
        m9.d.h(i9.f.d(this.f19242i));
        return g0(w9.e.f22508a);
    }

    @Override // g8.a0
    public final boolean V() {
        return this.f19242i.V();
    }

    @Override // g8.f
    public final List W() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        A0(17);
        throw null;
    }

    @Override // g8.f
    public final boolean Y() {
        return this.f19242i.Y();
    }

    @Override // j8.b0, g8.f, g8.k
    public final g8.f a() {
        g8.f fVarA = this.f19242i.a();
        if (fVarA != null) {
            return fVarA;
        }
        A0(21);
        throw null;
    }

    @Override // g8.x0
    public final g8.l b(y0 y0Var) {
        if (y0Var != null) {
            return y0Var.f22263a.e() ? this : new a0(this, y0.e(y0Var.f(), D0().f()));
        }
        A0(23);
        throw null;
    }

    @Override // g8.f
    public final boolean c0() {
        return this.f19242i.c0();
    }

    @Override // g8.k
    public final g8.k d() {
        g8.k kVarD = this.f19242i.d();
        if (kVarD != null) {
            return kVarD;
        }
        A0(22);
        throw null;
    }

    @Override // g8.n
    public final g8.v0 f() {
        return g8.v0.f15554b;
    }

    @Override // g8.h
    public final v9.o0 g() {
        v9.o0 o0VarG = this.f19242i.g();
        if (this.f19243l.f22263a.e()) {
            if (o0VarG != null) {
                return o0VarG;
            }
            A0(0);
            throw null;
        }
        if (this.f19247p == null) {
            y0 y0VarD0 = D0();
            Collection collectionD = o0VarG.d();
            ArrayList arrayList = new ArrayList(collectionD.size());
            Iterator it = collectionD.iterator();
            while (it.hasNext()) {
                arrayList.add(y0VarD0.i(1, (v9.w) it.next()));
            }
            this.f19247p = new v9.j(this, this.f19245n, arrayList, u9.p.f22089e);
        }
        v9.j jVar = this.f19247p;
        if (jVar != null) {
            return jVar;
        }
        A0(1);
        throw null;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        p9.n nVarG0 = this.f19242i.g0(eVar);
        if (!this.f19243l.f22263a.e()) {
            return new p9.s(nVarG0, D0());
        }
        if (nVarG0 != null) {
            return nVarG0;
        }
        A0(14);
        throw null;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        h8.h annotations = this.f19242i.getAnnotations();
        if (annotations != null) {
            return annotations;
        }
        A0(19);
        throw null;
    }

    @Override // g8.d0
    public final f9.f getName() {
        f9.f name = this.f19242i.getName();
        if (name != null) {
            return name;
        }
        A0(20);
        throw null;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p visibility = this.f19242i.getVisibility();
        if (visibility != null) {
            return visibility;
        }
        A0(27);
        throw null;
    }

    @Override // g8.f
    public final Collection h() {
        Collection<g8.e> collectionH = this.f19242i.h();
        ArrayList arrayList = new ArrayList(collectionH.size());
        for (g8.e eVar : collectionH) {
            arrayList.add(((g8.e) eVar.C0().d(eVar.a()).k(eVar.o()).n(eVar.getVisibility()).b(eVar.getKind()).f().build()).b(D0()));
        }
        return arrayList;
    }

    @Override // g8.i
    public final boolean i() {
        return this.f19242i.i();
    }

    @Override // g8.a0
    public final boolean i0() {
        return this.f19242i.i0();
    }

    @Override // g8.a0
    public final boolean isExternal() {
        return this.f19242i.isExternal();
    }

    @Override // g8.f
    public final boolean isInline() {
        return this.f19242i.isInline();
    }

    @Override // g8.f
    public final p9.n j0() {
        p9.n nVarJ0 = this.f19242i.j0();
        if (nVarJ0 != null) {
            return nVarJ0;
        }
        A0(15);
        throw null;
    }

    @Override // g8.f, g8.h
    public final v9.b0 m() {
        v9.k0 k0VarH;
        List listD = v9.b1.d(g().getParameters());
        h8.h annotations = getAnnotations();
        if (annotations.isEmpty()) {
            v9.k0.f22220l.getClass();
            k0VarH = v9.k0.f22221m;
        } else {
            fi.iki.elonen.f fVar = v9.k0.f22220l;
            List listSingletonList = Collections.singletonList(new v9.h(annotations));
            fVar.getClass();
            k0VarH = fi.iki.elonen.f.H(listSingletonList);
        }
        return v9.e.s(listD, U(), k0VarH, g(), false);
    }

    @Override // g8.f, g8.i
    public final List n() {
        D0();
        ArrayList arrayList = this.f19246o;
        if (arrayList != null) {
            return arrayList;
        }
        A0(30);
        throw null;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        int iO = this.f19242i.o();
        if (iO != 0) {
            return iO;
        }
        A0(26);
        throw null;
    }

    @Override // g8.f
    public final int p0() {
        int iP0 = this.f19242i.p0();
        if (iP0 != 0) {
            return iP0;
        }
        A0(25);
        throw null;
    }

    @Override // g8.f
    public final boolean q() {
        return this.f19242i.q();
    }

    @Override // j8.b0
    public final p9.n v(v9.v0 v0Var, w9.e eVar) {
        p9.n nVarV = this.f19242i.v(v0Var, eVar);
        if (!this.f19243l.f22263a.e()) {
            return new p9.s(nVarV, D0());
        }
        if (nVarV != null) {
            return nVarV;
        }
        A0(7);
        throw null;
    }

    @Override // g8.f
    public final Collection w() {
        Collection collectionW = this.f19242i.w();
        if (collectionW != null) {
            return collectionW;
        }
        A0(31);
        throw null;
    }

    @Override // g8.f
    public final p9.n x(v9.v0 v0Var) {
        m9.d.h(i9.f.d(this));
        return v(v0Var, w9.e.f22508a);
    }
}
