package eb;

import com.google.android.gms.internal.auth.d1;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 extends a.a implements db.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d1 f15138b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final db.b f15139c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f15140d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final db.s[] f15141e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final fb.b f15142f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final db.i f15143g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15144h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f15145i;

    public g0(d1 d1Var, db.b bVar, int i10, db.s[] sVarArr) {
        super(6);
        this.f15138b = d1Var;
        this.f15139c = bVar;
        this.f15140d = i10;
        this.f15141e = sVarArr;
        this.f15142f = bVar.f14879b;
        this.f15143g = bVar.f14878a;
        int iC = h.f0.c(i10);
        if (sVarArr != null) {
            db.s sVar = sVarArr[iC];
            if (sVar == null && sVar == this) {
                return;
            }
            sVarArr[iC] = this;
        }
    }

    @Override // a.a, bb.e
    public final void A(int i10) {
        if (this.f15144h) {
            E(String.valueOf(i10));
        } else {
            this.f15138b.f(i10);
        }
    }

    @Override // a.a, bb.c
    public final boolean C() {
        return this.f15143g.f14901a;
    }

    @Override // a.a, bb.e
    public final void E(String str) {
        this.f15138b.j(str);
    }

    @Override // a.a
    public final void J(ab.g gVar, int i10) {
        int iC = h.f0.c(this.f15140d);
        d1 d1Var = this.f15138b;
        boolean z = true;
        if (iC == 1) {
            if (!d1Var.f13017b) {
                d1Var.e(',');
            }
            d1Var.c();
            return;
        }
        if (iC == 2) {
            if (d1Var.f13017b) {
                this.f15144h = true;
                d1Var.c();
                return;
            }
            if (i10 % 2 == 0) {
                d1Var.e(',');
                d1Var.c();
            } else {
                d1Var.e(':');
                d1Var.k();
                z = false;
            }
            this.f15144h = z;
            return;
        }
        if (iC != 3) {
            if (!d1Var.f13017b) {
                d1Var.e(',');
            }
            d1Var.c();
            r.n(gVar, this.f15139c);
            E(gVar.e(i10));
            d1Var.e(':');
            d1Var.k();
            return;
        }
        if (i10 == 0) {
            this.f15144h = true;
        }
        if (i10 == 1) {
            d1Var.e(',');
            d1Var.k();
            this.f15144h = false;
        }
    }

    @Override // bb.e
    public final fb.b a() {
        return this.f15142f;
    }

    @Override // a.a, bb.e
    public final bb.c b(ab.g gVar) {
        db.s sVar;
        db.b bVar = this.f15139c;
        int iO = r.o(gVar, bVar);
        char c10 = com.arflix.tv.data.repository.g.c(iO);
        d1 d1Var = this.f15138b;
        d1Var.e(c10);
        d1Var.f13017b = true;
        if (this.f15145i != null) {
            d1Var.c();
            E(this.f15145i);
            d1Var.e(':');
            E(gVar.h());
            this.f15145i = null;
        }
        if (this.f15140d == iO) {
            return this;
        }
        db.s[] sVarArr = this.f15141e;
        return (sVarArr == null || (sVar = sVarArr[h.f0.c(iO)]) == null) ? new g0(d1Var, bVar, iO, sVarArr) : sVar;
    }

    @Override // a.a, bb.c
    public final void e() {
        int i10 = this.f15140d;
        com.arflix.tv.data.repository.g.d(i10);
        d1 d1Var = this.f15138b;
        d1Var.getClass();
        d1Var.c();
        d1Var.e(com.arflix.tv.data.repository.g.d(i10));
    }

    @Override // a.a, bb.e
    public final void f(double d4) {
        boolean z = this.f15144h;
        d1 d1Var = this.f15138b;
        if (z) {
            E(String.valueOf(d4));
        } else {
            ((androidx.core.provider.j) d1Var.f13018c).i(String.valueOf(d4));
        }
        if (this.f15143g.f14908h) {
            return;
        }
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            throw r.a(((androidx.core.provider.j) d1Var.f13018c).toString(), Double.valueOf(d4));
        }
    }

    @Override // a.a, bb.e
    public final void h(byte b10) {
        if (this.f15144h) {
            E(String.valueOf((int) b10));
        } else {
            this.f15138b.d(b10);
        }
    }

    @Override // a.a, bb.e
    public final void i(ab.g gVar, int i10) {
        E(gVar.e(i10));
    }

    @Override // a.a, bb.e
    public final bb.e k(ab.g gVar) {
        boolean zA = h0.a(gVar);
        int i10 = this.f15140d;
        db.b bVar = this.f15139c;
        d1 kVar = this.f15138b;
        if (zA) {
            if (!(kVar instanceof l)) {
                kVar = new l((androidx.core.provider.j) kVar.f13018c, this.f15144h);
            }
            return new g0(kVar, bVar, i10, null);
        }
        if (!gVar.isInline() || !gVar.equals(db.n.f14910a)) {
            return this;
        }
        if (!(kVar instanceof k)) {
            kVar = new k((androidx.core.provider.j) kVar.f13018c, this.f15144h);
        }
        return new g0(kVar, bVar, i10, null);
    }

    @Override // db.s
    public final void l(db.b0 b0Var) {
        z(db.q.f14921a, b0Var);
    }

    @Override // a.a, bb.c
    public final void m(ab.g gVar, int i10, ya.s sVar, Object obj) {
        if (obj != null || this.f15143g.f14905e) {
            super.m(gVar, i10, sVar, obj);
        }
    }

    @Override // a.a, bb.e
    public final void p(long j10) {
        if (this.f15144h) {
            E(String.valueOf(j10));
        } else {
            this.f15138b.g(j10);
        }
    }

    @Override // a.a, bb.e
    public final void q() {
        ((androidx.core.provider.j) this.f15138b.f13018c).i("null");
    }

    @Override // a.a, bb.e
    public final void s(short s10) {
        if (this.f15144h) {
            E(String.valueOf((int) s10));
        } else {
            this.f15138b.i(s10);
        }
    }

    @Override // a.a, bb.e
    public final void t(boolean z) {
        if (this.f15144h) {
            E(String.valueOf(z));
        } else {
            ((androidx.core.provider.j) this.f15138b.f13018c).i(String.valueOf(z));
        }
    }

    @Override // a.a, bb.e
    public final void w(float f10) {
        boolean z = this.f15144h;
        d1 d1Var = this.f15138b;
        if (z) {
            E(String.valueOf(f10));
        } else {
            ((androidx.core.provider.j) d1Var.f13018c).i(String.valueOf(f10));
        }
        if (this.f15143g.f14908h) {
            return;
        }
        if (Float.isInfinite(f10) || Float.isNaN(f10)) {
            throw r.a(((androidx.core.provider.j) d1Var.f13018c).toString(), Float.valueOf(f10));
        }
    }

    @Override // a.a, bb.e
    public final void y(char c10) {
        E(String.valueOf(c10));
    }

    @Override // a.a, bb.e
    public final void z(ya.s sVar, Object obj) {
        if (!(sVar instanceof cb.b)) {
            sVar.serialize(this, obj);
            return;
        }
        cb.b bVar = (cb.b) sVar;
        String strI = r.i(sVar.getDescriptor(), this.f15139c);
        ya.s sVarK = k2.c.k(bVar, this, obj);
        r.e(bVar, sVarK, strI);
        r.h(sVarK.getDescriptor().getKind());
        this.f15145i = strI;
        sVarK.serialize(this, obj);
    }
}
