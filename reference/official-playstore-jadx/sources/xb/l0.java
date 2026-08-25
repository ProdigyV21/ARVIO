package xb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes5.dex */
public final class l0 extends m {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient byte[][] f22770o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int[] f22771p;

    public l0(byte[][] bArr, int[] iArr) {
        super(m.f22772n.f22773i);
        this.f22770o = bArr;
        this.f22771p = iArr;
    }

    @Override // xb.m
    public final m d(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[][] bArr = this.f22770o;
        int length = bArr.length;
        int i10 = 0;
        int i11 = 0;
        while (i10 < length) {
            int[] iArr = this.f22771p;
            int i12 = iArr[length + i10];
            int i13 = iArr[i10];
            messageDigest.update(bArr[i10], i12, i13 - i11);
            i10++;
            i11 = i13;
        }
        return new m(messageDigest.digest());
    }

    @Override // xb.m
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (mVar.f() == f() && p(0, mVar, f())) {
                return true;
            }
        }
        return false;
    }

    @Override // xb.m
    public final int f() {
        return this.f22771p[this.f22770o.length - 1];
    }

    @Override // xb.m
    public final int hashCode() {
        int i10 = this.f22774l;
        if (i10 != 0) {
            return i10;
        }
        byte[][] bArr = this.f22770o;
        int length = bArr.length;
        int i11 = 0;
        int i12 = 1;
        int i13 = 0;
        while (i11 < length) {
            int[] iArr = this.f22771p;
            int i14 = iArr[length + i11];
            int i15 = iArr[i11];
            byte[] bArr2 = bArr[i11];
            int i16 = (i15 - i13) + i14;
            while (i14 < i16) {
                i12 = (i12 * 31) + bArr2[i14];
                i14++;
            }
            i11++;
            i13 = i15;
        }
        this.f22774l = i12;
        return i12;
    }

    @Override // xb.m
    public final String i() {
        return w().i();
    }

    @Override // xb.m
    public final int j(int i10, byte[] bArr) {
        return w().j(i10, bArr);
    }

    @Override // xb.m
    public final byte[] l() {
        return v();
    }

    @Override // xb.m
    public final byte m(int i10) {
        byte[][] bArr = this.f22770o;
        int length = bArr.length - 1;
        int[] iArr = this.f22771p;
        b.c(iArr[length], i10, 1L);
        int iH = yb.b.h(this, i10);
        return bArr[iH][(i10 - (iH == 0 ? 0 : iArr[iH - 1])) + iArr[bArr.length + iH]];
    }

    @Override // xb.m
    public final int n(byte[] bArr) {
        return w().n(bArr);
    }

    @Override // xb.m
    public final boolean o(int i10, int i11, int i12, byte[] bArr) {
        if (i10 < 0 || i10 > f() - i12 || i11 < 0 || i11 > bArr.length - i12) {
            return false;
        }
        int i13 = i12 + i10;
        int iH = yb.b.h(this, i10);
        while (i10 < i13) {
            int[] iArr = this.f22771p;
            int i14 = iH == 0 ? 0 : iArr[iH - 1];
            int i15 = iArr[iH] - i14;
            byte[][] bArr2 = this.f22770o;
            int i16 = iArr[bArr2.length + iH];
            int iMin = Math.min(i13, i15 + i14) - i10;
            if (!b.a(bArr2[iH], (i10 - i14) + i16, i11, bArr, iMin)) {
                return false;
            }
            i11 += iMin;
            i10 += iMin;
            iH++;
        }
        return true;
    }

    @Override // xb.m
    public final boolean p(int i10, m mVar, int i11) {
        if (i10 >= 0 && i10 <= f() - i11) {
            int i12 = i11 + i10;
            int iH = yb.b.h(this, i10);
            int i13 = 0;
            while (i10 < i12) {
                int[] iArr = this.f22771p;
                int i14 = iH == 0 ? 0 : iArr[iH - 1];
                int i15 = iArr[iH] - i14;
                byte[][] bArr = this.f22770o;
                int i16 = iArr[bArr.length + iH];
                int iMin = Math.min(i12, i15 + i14) - i10;
                if (mVar.o(i13, (i10 - i14) + i16, iMin, bArr[iH])) {
                    i13 += iMin;
                    i10 += iMin;
                    iH++;
                }
            }
            return true;
        }
        return false;
    }

    @Override // xb.m
    public final m q(int i10, int i11) {
        if (i11 == -1234567890) {
            i11 = f();
        }
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "beginIndex=", " < 0").toString());
        }
        if (i11 > f()) {
            StringBuilder sbS = a0.c.s(i11, "endIndex=", " > length(");
            sbS.append(f());
            sbS.append(')');
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        int i12 = i11 - i10;
        if (i12 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i11, i10, "endIndex=", " < beginIndex=").toString());
        }
        if (i10 == 0 && i11 == f()) {
            return this;
        }
        if (i10 == i11) {
            return m.f22772n;
        }
        int iH = yb.b.h(this, i10);
        int iH2 = yb.b.h(this, i11 - 1);
        byte[][] bArr = this.f22770o;
        byte[][] bArr2 = (byte[][]) kotlin.collections.r.S(bArr, iH, iH2 + 1);
        int[] iArr = new int[bArr2.length * 2];
        int[] iArr2 = this.f22771p;
        if (iH <= iH2) {
            int i13 = iH;
            int i14 = 0;
            while (true) {
                iArr[i14] = Math.min(iArr2[i13] - i10, i12);
                int i15 = i14 + 1;
                iArr[i14 + bArr2.length] = iArr2[bArr.length + i13];
                if (i13 == iH2) {
                    break;
                }
                i13++;
                i14 = i15;
            }
        }
        int i16 = iH != 0 ? iArr2[iH - 1] : 0;
        int length = bArr2.length;
        iArr[length] = (i10 - i16) + iArr[length];
        return new l0(bArr2, iArr);
    }

    @Override // xb.m
    public final m s() {
        return w().s();
    }

    @Override // xb.m
    public final String toString() {
        return w().toString();
    }

    @Override // xb.m
    public final void u(j jVar, int i10) {
        int iH = yb.b.h(this, 0);
        int i11 = 0;
        while (i11 < i10) {
            int[] iArr = this.f22771p;
            int i12 = iH == 0 ? 0 : iArr[iH - 1];
            int i13 = iArr[iH] - i12;
            byte[][] bArr = this.f22770o;
            int i14 = iArr[bArr.length + iH];
            int iMin = Math.min(i10, i13 + i12) - i11;
            int i15 = (i11 - i12) + i14;
            j0 j0Var = new j0(bArr[iH], i15, i15 + iMin, true, false);
            j0 j0Var2 = jVar.f22758i;
            if (j0Var2 == null) {
                j0Var.f22766g = j0Var;
                j0Var.f22765f = j0Var;
                jVar.f22758i = j0Var;
            } else {
                j0Var2.f22766g.b(j0Var);
            }
            i11 += iMin;
            iH++;
        }
        jVar.f22759l += (long) i10;
    }

    public final byte[] v() {
        byte[] bArr = new byte[f()];
        byte[][] bArr2 = this.f22770o;
        int length = bArr2.length;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < length) {
            int[] iArr = this.f22771p;
            int i13 = iArr[length + i10];
            int i14 = iArr[i10];
            int i15 = i14 - i11;
            kotlin.collections.r.L(bArr2[i10], i12, i13, bArr, i13 + i15);
            i12 += i15;
            i10++;
            i11 = i14;
        }
        return bArr;
    }

    public final m w() {
        return new m(v());
    }
}
