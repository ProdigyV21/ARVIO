package eb;

import x6.m0;
import x6.o0;
import x6.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends xc.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f15161c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final fb.b f15162d;

    public m(a aVar, db.b bVar) {
        this.f15161c = aVar;
        this.f15162d = bVar.f14879b;
    }

    @Override // xc.d, bb.d
    public final byte D() {
        x6.k0 k0Var;
        a aVar = this.f15161c;
        String strN = aVar.n();
        try {
            m0 m0VarH = k2.c.H(strN);
            if (m0VarH != null) {
                int i10 = m0VarH.f22592i;
                k0Var = Integer.compare(Integer.MIN_VALUE ^ i10, -2147483393) > 0 ? null : new x6.k0((byte) i10);
            }
            if (k0Var != null) {
                return k0Var.f22590i;
            }
            kotlin.text.u.M(strN);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'UByte' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // bb.b
    public final fb.b a() {
        return this.f15162d;
    }

    @Override // xc.d, bb.d
    public final int g() {
        a aVar = this.f15161c;
        String strN = aVar.n();
        try {
            m0 m0VarH = k2.c.H(strN);
            if (m0VarH != null) {
                return m0VarH.f22592i;
            }
            kotlin.text.u.M(strN);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'UInt' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // xc.d, bb.d
    public final long i() {
        a aVar = this.f15161c;
        String strN = aVar.n();
        try {
            o0 o0VarI = k2.c.I(strN);
            if (o0VarI != null) {
                return o0VarI.f22594i;
            }
            kotlin.text.u.M(strN);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'ULong' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // xc.d, bb.d
    public final short m() {
        r0 r0Var;
        a aVar = this.f15161c;
        String strN = aVar.n();
        try {
            m0 m0VarH = k2.c.H(strN);
            if (m0VarH != null) {
                int i10 = m0VarH.f22592i;
                r0Var = Integer.compare(Integer.MIN_VALUE ^ i10, -2147418113) > 0 ? null : new r0((short) i10);
            }
            if (r0Var != null) {
                return r0Var.f22603i;
            }
            kotlin.text.u.M(strN);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'UShort' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // bb.b
    public final int w(ab.g gVar) {
        throw new IllegalStateException("unsupported");
    }
}
