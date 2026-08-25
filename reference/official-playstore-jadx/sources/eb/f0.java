package eb;

import java.util.Arrays;
import kotlinx.serialization.MissingFieldException;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends xc.d implements db.j, bb.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final db.b f15129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f15130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f15131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final fb.b f15132f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15133g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c2.a f15134h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final db.i f15135i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o f15136j;

    public f0(db.b bVar, int i10, a aVar, ab.g gVar, c2.a aVar2) {
        this.f15129c = bVar;
        this.f15130d = i10;
        this.f15131e = aVar;
        this.f15132f = bVar.f14879b;
        this.f15134h = aVar2;
        db.i iVar = bVar.f14878a;
        this.f15135i = iVar;
        this.f15136j = iVar.f14905e ? null : new o(gVar);
    }

    @Override // db.j
    public final db.b C() {
        return this.f15129c;
    }

    @Override // xc.d, bb.d
    public final byte D() {
        a aVar = this.f15131e;
        long jK = aVar.k();
        byte b10 = (byte) jK;
        if (jK == b10) {
            return b10;
        }
        a.s(aVar, "Failed to parse byte for input '" + jK + '\'', 0, 6);
        throw null;
    }

    @Override // bb.b
    public final fb.b a() {
        return this.f15132f;
    }

    @Override // xc.d, bb.d
    public final bb.b b(ab.g gVar) {
        db.b bVar = this.f15129c;
        int iO = r.o(gVar, bVar);
        a aVar = this.f15131e;
        androidx.appcompat.widget.f0 f0Var = aVar.f15101b;
        int i10 = f0Var.f1566l + 1;
        f0Var.f1566l = i10;
        Object[] objArr = (Object[]) f0Var.f1567m;
        if (i10 == objArr.length) {
            int i11 = i10 * 2;
            f0Var.f1567m = Arrays.copyOf(objArr, i11);
            f0Var.f1568n = Arrays.copyOf((int[]) f0Var.f1568n, i11);
        }
        ((Object[]) f0Var.f1567m)[i10] = gVar;
        aVar.j(com.arflix.tv.data.repository.g.c(iO));
        if (aVar.w() != 4) {
            int iC = h.f0.c(iO);
            return (iC == 1 || iC == 2 || iC == 3) ? new f0(this.f15129c, iO, aVar, gVar, this.f15134h) : (this.f15130d == iO && bVar.f14878a.f14905e) ? this : new f0(this.f15129c, iO, aVar, gVar, this.f15134h);
        }
        a.s(aVar, "Unexpected leading comma", 0, 6);
        throw null;
    }

    @Override // xc.d, bb.b
    public final Object d(ab.g gVar, int i10, ya.c cVar, Object obj) {
        androidx.appcompat.widget.f0 f0Var = this.f15131e.f15101b;
        boolean z = this.f15130d == 3 && (i10 & 1) == 0;
        if (z) {
            int[] iArr = (int[]) f0Var.f1568n;
            int i11 = f0Var.f1566l;
            if (iArr[i11] == -2) {
                ((Object[]) f0Var.f1567m)[i11] = s.f15174a;
            }
        }
        Object objQ = q(cVar);
        if (z) {
            int[] iArr2 = (int[]) f0Var.f1568n;
            int i12 = f0Var.f1566l;
            if (iArr2[i12] != -2) {
                int i13 = i12 + 1;
                f0Var.f1566l = i13;
                Object[] objArr = (Object[]) f0Var.f1567m;
                if (i13 == objArr.length) {
                    int i14 = i13 * 2;
                    f0Var.f1567m = Arrays.copyOf(objArr, i14);
                    f0Var.f1568n = Arrays.copyOf((int[]) f0Var.f1568n, i14);
                }
            }
            Object[] objArr2 = (Object[]) f0Var.f1567m;
            int i15 = f0Var.f1566l;
            objArr2[i15] = objQ;
            ((int[]) f0Var.f1568n)[i15] = -2;
        }
        return objQ;
    }

    @Override // db.j
    public final db.m e() {
        return new androidx.appcompat.widget.a(this.f15129c.f14878a, this.f15131e).e();
    }

    @Override // xc.d, bb.d
    public final int g() {
        a aVar = this.f15131e;
        long jK = aVar.k();
        int i10 = (int) jK;
        if (jK == i10) {
            return i10;
        }
        a.s(aVar, "Failed to parse int for input '" + jK + '\'', 0, 6);
        throw null;
    }

    @Override // xc.d, bb.d
    public final bb.d h(ab.g gVar) {
        return h0.a(gVar) ? new m(this.f15131e, this.f15129c) : this;
    }

    @Override // xc.d, bb.d
    public final long i() {
        return this.f15131e.k();
    }

    @Override // xc.d, bb.d
    public final short m() {
        a aVar = this.f15131e;
        long jK = aVar.k();
        short s10 = (short) jK;
        if (jK == s10) {
            return s10;
        }
        a.s(aVar, "Failed to parse short for input '" + jK + '\'', 0, 6);
        throw null;
    }

    @Override // xc.d, bb.d
    public final float n() {
        a aVar = this.f15131e;
        String strN = aVar.n();
        try {
            float f10 = Float.parseFloat(strN);
            if (this.f15129c.f14878a.f14908h || !(Float.isInfinite(f10) || Float.isNaN(f10))) {
                return f10;
            }
            a.s(aVar, "Unexpected special floating-point value " + Float.valueOf(f10) + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification", 0, 2);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'float' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // xc.d, bb.d
    public final double p() {
        a aVar = this.f15131e;
        String strN = aVar.n();
        try {
            double d4 = Double.parseDouble(strN);
            if (this.f15129c.f14878a.f14908h || !(Double.isInfinite(d4) || Double.isNaN(d4))) {
                return d4;
            }
            a.s(aVar, "Unexpected special floating-point value " + Double.valueOf(d4) + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification", 0, 2);
            throw null;
        } catch (IllegalArgumentException unused) {
            a.s(aVar, androidx.compose.material3.d.i('\'', "Failed to parse type 'double' for input '", strN), 0, 6);
            throw null;
        }
    }

    @Override // xc.d, bb.d
    public final Object q(ya.c cVar) {
        a aVar = this.f15131e;
        db.b bVar = this.f15129c;
        try {
            if (!(cVar instanceof cb.b)) {
                return cVar.deserialize(this);
            }
            db.i iVar = bVar.f14878a;
            String strI = r.i(cVar.getDescriptor(), bVar);
            String strG = aVar.g(strI, this.f15135i.f14903c);
            ya.c cVarA = strG != null ? ((cb.b) cVar).a(this, strG) : null;
            if (cVarA == null) {
                return r.j(this, cVar);
            }
            c2.a aVar2 = new c2.a();
            aVar2.f7379l = strI;
            this.f15134h = aVar2;
            return cVarA.deserialize(this);
        } catch (MissingFieldException e5) {
            if (kotlin.text.o.T(e5.getMessage(), "at path", false)) {
                throw e5;
            }
            throw new MissingFieldException(e5.f19979i, e5.getMessage() + " at path: " + aVar.f15101b.d(), e5);
        }
    }

    @Override // xc.d, bb.d
    public final boolean r() {
        boolean z;
        boolean z5 = this.f15135i.f14903c;
        a aVar = this.f15131e;
        if (!z5) {
            return aVar.d(aVar.y());
        }
        int iY = aVar.y();
        if (iY == aVar.v().length()) {
            a.s(aVar, "EOF", 0, 6);
            throw null;
        }
        if (aVar.v().charAt(iY) == '\"') {
            iY++;
            z = true;
        } else {
            z = false;
        }
        boolean zD = aVar.d(iY);
        if (!z) {
            return zD;
        }
        if (aVar.f15100a == aVar.v().length()) {
            a.s(aVar, "EOF", 0, 6);
            throw null;
        }
        if (aVar.v().charAt(aVar.f15100a) == '\"') {
            aVar.f15100a++;
            return zD;
        }
        a.s(aVar, "Expected closing quotation mark", 0, 6);
        throw null;
    }

    @Override // xc.d, bb.d
    public final char s() {
        a aVar = this.f15131e;
        String strN = aVar.n();
        if (strN.length() == 1) {
            return strN.charAt(0);
        }
        a.s(aVar, androidx.compose.material3.d.i('\'', "Expected single char, but got '", strN), 0, 6);
        throw null;
    }

    @Override // xc.d, bb.b
    public final void u(ab.g gVar) {
        if (this.f15129c.f14878a.f14902b && gVar.d() == 0) {
            while (w(gVar) != -1) {
            }
        }
        char cD = com.arflix.tv.data.repository.g.d(this.f15130d);
        a aVar = this.f15131e;
        aVar.j(cD);
        androidx.appcompat.widget.f0 f0Var = aVar.f15101b;
        int i10 = f0Var.f1566l;
        int[] iArr = (int[]) f0Var.f1568n;
        if (iArr[i10] == -2) {
            iArr[i10] = -1;
            f0Var.f1566l = i10 - 1;
        }
        int i11 = f0Var.f1566l;
        if (i11 != -1) {
            f0Var.f1566l = i11 - 1;
        }
    }

    @Override // xc.d, bb.d
    public final String v() {
        boolean z = this.f15135i.f14903c;
        a aVar = this.f15131e;
        return z ? aVar.o() : aVar.l();
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ed, code lost:
    
        r1.r(kotlin.text.o.j0(6, r1.z(0, r1.f15100a), r14), androidx.compose.material3.d.i('\'', "Encountered an unknown key '", r14), "Use 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0104, code lost:
    
        throw null;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e  */
    @Override // bb.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int w(ab.g r21) {
        /*
            Method dump skipped, instruction units count: 559
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: eb.f0.w(ab.g):int");
    }

    @Override // xc.d, bb.d
    public final boolean x() {
        o oVar = this.f15136j;
        if (!(oVar != null ? oVar.f15164b : false)) {
            a aVar = this.f15131e;
            int iX = aVar.x(aVar.y());
            int length = aVar.v().length() - iX;
            boolean z = false;
            if (length >= 4 && iX != -1) {
                int i10 = 0;
                while (true) {
                    if (i10 < 4) {
                        if ("null".charAt(i10) != aVar.v().charAt(iX + i10)) {
                            break;
                        }
                        i10++;
                    } else if (length <= 4 || r.g(aVar.v().charAt(iX + 4)) != 0) {
                        z = true;
                        aVar.f15100a = iX + 4;
                    }
                }
            }
            if (!z) {
                return true;
            }
        }
        return false;
    }

    @Override // xc.d, bb.d
    public final int z(ab.g gVar) {
        return r.l(gVar, this.f15129c, v(), " at path ".concat(this.f15131e.f15101b.d()));
    }
}
