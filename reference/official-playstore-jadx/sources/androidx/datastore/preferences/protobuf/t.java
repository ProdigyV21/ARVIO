package androidx.datastore.preferences.protobuf;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends u {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final byte[] f2529f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2530g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2531h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.i f2532i;

    public t(xb.i iVar, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        int iMax = Math.max(i10, 20);
        this.f2529f = new byte[iMax];
        this.f2530g = iMax;
        this.f2532i = iVar;
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void A(int i10, byte[] bArr) throws IOException {
        P(i10);
        Z(bArr, 0, i10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void B(int i10, m mVar) {
        N(i10, 2);
        C(mVar);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void C(m mVar) {
        P(mVar.size());
        l(mVar.b(), mVar.size(), mVar.f2473l);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void D(int i10, int i11) {
        Y(14);
        U(i10, 5);
        S(i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void E(int i10) {
        Y(4);
        S(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void F(int i10, long j10) {
        Y(18);
        U(i10, 1);
        T(j10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void G(long j10) {
        Y(8);
        T(j10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void H(int i10, int i11) {
        Y(20);
        U(i10, 0);
        if (i11 >= 0) {
            V(i11);
        } else {
            W(i11);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void I(int i10) {
        if (i10 >= 0) {
            P(i10);
        } else {
            R(i10);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void J(int i10, v1 v1Var, o2 o2Var) {
        N(i10, 2);
        P(((c) v1Var).g(o2Var));
        o2Var.h(v1Var, this.f2535c);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void K(v1 v1Var) {
        P(v1Var.a());
        v1Var.d(this);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void L(int i10, String str) throws IOException {
        N(i10, 2);
        M(str);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void M(String str) throws IOException {
        try {
            int length = str.length() * 3;
            int iV = u.v(length);
            int i10 = iV + length;
            int i11 = this.f2530g;
            if (i10 > i11) {
                byte[] bArr = new byte[length];
                int iF = s3.f2528a.f(str, bArr, 0, length);
                P(iF);
                Z(bArr, 0, iF);
                return;
            }
            if (i10 > i11 - this.f2531h) {
                X();
            }
            int iV2 = u.v(str.length());
            int i12 = this.f2531h;
            byte[] bArr2 = this.f2529f;
            try {
                if (iV2 == iV) {
                    int i13 = i12 + iV2;
                    this.f2531h = i13;
                    int iF2 = s3.f2528a.f(str, bArr2, i13, i11 - i13);
                    this.f2531h = i12;
                    V((iF2 - i12) - iV2);
                    this.f2531h = iF2;
                } else {
                    int iB = s3.b(str);
                    V(iB);
                    this.f2531h = s3.f2528a.f(str, bArr2, this.f2531h, iB);
                }
            } catch (r3 e5) {
                this.f2531h = i12;
                throw e5;
            } catch (ArrayIndexOutOfBoundsException e6) {
                throw new CodedOutputStream$OutOfSpaceException(e6);
            }
        } catch (r3 e10) {
            x(str, e10);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void N(int i10, int i11) {
        P((i10 << 3) | i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void O(int i10, int i11) {
        Y(20);
        U(i10, 0);
        V(i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void P(int i10) {
        Y(5);
        V(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void Q(int i10, long j10) {
        Y(20);
        U(i10, 0);
        W(j10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void R(long j10) {
        Y(10);
        W(j10);
    }

    public final void S(int i10) {
        int i11 = this.f2531h;
        int i12 = i11 + 1;
        this.f2531h = i12;
        byte[] bArr = this.f2529f;
        bArr[i11] = (byte) (i10 & 255);
        int i13 = i11 + 2;
        this.f2531h = i13;
        bArr[i12] = (byte) ((i10 >> 8) & 255);
        int i14 = i11 + 3;
        this.f2531h = i14;
        bArr[i13] = (byte) ((i10 >> 16) & 255);
        this.f2531h = i11 + 4;
        bArr[i14] = (byte) ((i10 >> 24) & 255);
    }

    public final void T(long j10) {
        int i10 = this.f2531h;
        int i11 = i10 + 1;
        this.f2531h = i11;
        byte[] bArr = this.f2529f;
        bArr[i10] = (byte) (j10 & 255);
        int i12 = i10 + 2;
        this.f2531h = i12;
        bArr[i11] = (byte) ((j10 >> 8) & 255);
        int i13 = i10 + 3;
        this.f2531h = i13;
        bArr[i12] = (byte) ((j10 >> 16) & 255);
        int i14 = i10 + 4;
        this.f2531h = i14;
        bArr[i13] = (byte) (255 & (j10 >> 24));
        int i15 = i10 + 5;
        this.f2531h = i15;
        bArr[i14] = (byte) (((int) (j10 >> 32)) & 255);
        int i16 = i10 + 6;
        this.f2531h = i16;
        bArr[i15] = (byte) (((int) (j10 >> 40)) & 255);
        int i17 = i10 + 7;
        this.f2531h = i17;
        bArr[i16] = (byte) (((int) (j10 >> 48)) & 255);
        this.f2531h = i10 + 8;
        bArr[i17] = (byte) (((int) (j10 >> 56)) & 255);
    }

    public final void U(int i10, int i11) {
        V((i10 << 3) | i11);
    }

    public final void V(int i10) {
        boolean z = u.f2534e;
        byte[] bArr = this.f2529f;
        if (z) {
            while ((i10 & (-128)) != 0) {
                int i11 = this.f2531h;
                this.f2531h = i11 + 1;
                p3.j(bArr, i11, (byte) ((i10 & 127) | 128));
                i10 >>>= 7;
            }
            int i12 = this.f2531h;
            this.f2531h = i12 + 1;
            p3.j(bArr, i12, (byte) i10);
            return;
        }
        while ((i10 & (-128)) != 0) {
            int i13 = this.f2531h;
            this.f2531h = i13 + 1;
            bArr[i13] = (byte) ((i10 & 127) | 128);
            i10 >>>= 7;
        }
        int i14 = this.f2531h;
        this.f2531h = i14 + 1;
        bArr[i14] = (byte) i10;
    }

    public final void W(long j10) {
        boolean z = u.f2534e;
        byte[] bArr = this.f2529f;
        if (z) {
            while ((j10 & (-128)) != 0) {
                int i10 = this.f2531h;
                this.f2531h = i10 + 1;
                p3.j(bArr, i10, (byte) ((((int) j10) & 127) | 128));
                j10 >>>= 7;
            }
            int i11 = this.f2531h;
            this.f2531h = i11 + 1;
            p3.j(bArr, i11, (byte) j10);
            return;
        }
        while ((j10 & (-128)) != 0) {
            int i12 = this.f2531h;
            this.f2531h = i12 + 1;
            bArr[i12] = (byte) ((((int) j10) & 127) | 128);
            j10 >>>= 7;
        }
        int i13 = this.f2531h;
        this.f2531h = i13 + 1;
        bArr[i13] = (byte) j10;
    }

    public final void X() {
        this.f2532i.write(this.f2529f, 0, this.f2531h);
        this.f2531h = 0;
    }

    public final void Y(int i10) {
        if (this.f2530g - this.f2531h < i10) {
            X();
        }
    }

    public final void Z(byte[] bArr, int i10, int i11) throws IOException {
        int i12 = this.f2531h;
        int i13 = this.f2530g;
        int i14 = i13 - i12;
        byte[] bArr2 = this.f2529f;
        if (i14 >= i11) {
            System.arraycopy(bArr, i10, bArr2, i12, i11);
            this.f2531h += i11;
            return;
        }
        System.arraycopy(bArr, i10, bArr2, i12, i14);
        int i15 = i10 + i14;
        int i16 = i11 - i14;
        this.f2531h = i13;
        X();
        if (i16 > i13) {
            this.f2532i.write(bArr, i15, i16);
        } else {
            System.arraycopy(bArr, i15, bArr2, 0, i16);
            this.f2531h = i16;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.x1
    public final void l(int i10, int i11, byte[] bArr) throws IOException {
        Z(bArr, i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void y(byte b10) {
        if (this.f2531h == this.f2530g) {
            X();
        }
        int i10 = this.f2531h;
        this.f2531h = i10 + 1;
        this.f2529f[i10] = b10;
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void z(int i10, boolean z) {
        Y(11);
        U(i10, 0);
        byte b10 = z ? (byte) 1 : (byte) 0;
        int i11 = this.f2531h;
        this.f2531h = i11 + 1;
        this.f2529f[i11] = b10;
    }
}
