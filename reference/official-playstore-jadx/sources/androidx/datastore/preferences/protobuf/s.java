package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends u {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final byte[] f2523f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2524g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2525h;

    public s(byte[] bArr, int i10) {
        if (((bArr.length - i10) | i10) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i10)));
        }
        this.f2523f = bArr;
        this.f2525h = 0;
        this.f2524g = i10;
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void A(int i10, byte[] bArr) throws CodedOutputStream$OutOfSpaceException {
        P(i10);
        S(bArr, 0, i10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void B(int i10, m mVar) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 2);
        C(mVar);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void C(m mVar) throws CodedOutputStream$OutOfSpaceException {
        P(mVar.size());
        l(mVar.b(), mVar.size(), mVar.f2473l);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void D(int i10, int i11) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 5);
        E(i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void E(int i10) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.f2523f;
            int i11 = this.f2525h;
            int i12 = i11 + 1;
            this.f2525h = i12;
            bArr[i11] = (byte) (i10 & 255);
            int i13 = i11 + 2;
            this.f2525h = i13;
            bArr[i12] = (byte) ((i10 >> 8) & 255);
            int i14 = i11 + 3;
            this.f2525h = i14;
            bArr[i13] = (byte) ((i10 >> 16) & 255);
            this.f2525h = i11 + 4;
            bArr[i14] = (byte) ((i10 >> 24) & 255);
        } catch (IndexOutOfBoundsException e5) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(this.f2524g), 1), e5);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void F(int i10, long j10) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 1);
        G(j10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void G(long j10) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.f2523f;
            int i10 = this.f2525h;
            int i11 = i10 + 1;
            this.f2525h = i11;
            bArr[i10] = (byte) (((int) j10) & 255);
            int i12 = i10 + 2;
            this.f2525h = i12;
            bArr[i11] = (byte) (((int) (j10 >> 8)) & 255);
            int i13 = i10 + 3;
            this.f2525h = i13;
            bArr[i12] = (byte) (((int) (j10 >> 16)) & 255);
            int i14 = i10 + 4;
            this.f2525h = i14;
            bArr[i13] = (byte) (((int) (j10 >> 24)) & 255);
            int i15 = i10 + 5;
            this.f2525h = i15;
            bArr[i14] = (byte) (((int) (j10 >> 32)) & 255);
            int i16 = i10 + 6;
            this.f2525h = i16;
            bArr[i15] = (byte) (((int) (j10 >> 40)) & 255);
            int i17 = i10 + 7;
            this.f2525h = i17;
            bArr[i16] = (byte) (((int) (j10 >> 48)) & 255);
            this.f2525h = i10 + 8;
            bArr[i17] = (byte) (((int) (j10 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e5) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(this.f2524g), 1), e5);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void H(int i10, int i11) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 0);
        I(i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void I(int i10) throws CodedOutputStream$OutOfSpaceException {
        if (i10 >= 0) {
            P(i10);
        } else {
            R(i10);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void J(int i10, v1 v1Var, o2 o2Var) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 2);
        P(((c) v1Var).g(o2Var));
        o2Var.h(v1Var, this.f2535c);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void K(v1 v1Var) throws CodedOutputStream$OutOfSpaceException {
        P(v1Var.a());
        v1Var.d(this);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void L(int i10, String str) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 2);
        M(str);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void M(String str) throws CodedOutputStream$OutOfSpaceException {
        int i10 = this.f2525h;
        try {
            int iV = u.v(str.length() * 3);
            int iV2 = u.v(str.length());
            int i11 = this.f2524g;
            byte[] bArr = this.f2523f;
            if (iV2 != iV) {
                P(s3.b(str));
                int i12 = this.f2525h;
                this.f2525h = s3.f2528a.f(str, bArr, i12, i11 - i12);
                return;
            }
            int i13 = i10 + iV2;
            this.f2525h = i13;
            int iF = s3.f2528a.f(str, bArr, i13, i11 - i13);
            this.f2525h = i10;
            P((iF - i10) - iV2);
            this.f2525h = iF;
        } catch (r3 e5) {
            this.f2525h = i10;
            x(str, e5);
        } catch (IndexOutOfBoundsException e6) {
            throw new CodedOutputStream$OutOfSpaceException(e6);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void N(int i10, int i11) throws CodedOutputStream$OutOfSpaceException {
        P((i10 << 3) | i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void O(int i10, int i11) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 0);
        P(i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void P(int i10) throws CodedOutputStream$OutOfSpaceException {
        boolean z = u.f2534e;
        int i11 = this.f2524g;
        byte[] bArr = this.f2523f;
        if (z && !e.a()) {
            int i12 = this.f2525h;
            if (i11 - i12 >= 5) {
                if ((i10 & (-128)) == 0) {
                    this.f2525h = i12 + 1;
                    p3.j(bArr, i12, (byte) i10);
                    return;
                }
                this.f2525h = i12 + 1;
                p3.j(bArr, i12, (byte) (i10 | 128));
                int i13 = i10 >>> 7;
                if ((i13 & (-128)) == 0) {
                    int i14 = this.f2525h;
                    this.f2525h = i14 + 1;
                    p3.j(bArr, i14, (byte) i13);
                    return;
                }
                int i15 = this.f2525h;
                this.f2525h = i15 + 1;
                p3.j(bArr, i15, (byte) (i13 | 128));
                int i16 = i10 >>> 14;
                if ((i16 & (-128)) == 0) {
                    int i17 = this.f2525h;
                    this.f2525h = i17 + 1;
                    p3.j(bArr, i17, (byte) i16);
                    return;
                }
                int i18 = this.f2525h;
                this.f2525h = i18 + 1;
                p3.j(bArr, i18, (byte) (i16 | 128));
                int i19 = i10 >>> 21;
                if ((i19 & (-128)) == 0) {
                    int i20 = this.f2525h;
                    this.f2525h = i20 + 1;
                    p3.j(bArr, i20, (byte) i19);
                    return;
                } else {
                    int i21 = this.f2525h;
                    this.f2525h = i21 + 1;
                    p3.j(bArr, i21, (byte) (i19 | 128));
                    int i22 = this.f2525h;
                    this.f2525h = i22 + 1;
                    p3.j(bArr, i22, (byte) (i10 >>> 28));
                    return;
                }
            }
        }
        while ((i10 & (-128)) != 0) {
            try {
                int i23 = this.f2525h;
                this.f2525h = i23 + 1;
                bArr[i23] = (byte) ((i10 & 127) | 128);
                i10 >>>= 7;
            } catch (IndexOutOfBoundsException e5) {
                throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(i11), 1), e5);
            }
        }
        int i24 = this.f2525h;
        this.f2525h = i24 + 1;
        bArr[i24] = (byte) i10;
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void Q(int i10, long j10) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 0);
        R(j10);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void R(long j10) throws CodedOutputStream$OutOfSpaceException {
        boolean z = u.f2534e;
        int i10 = this.f2524g;
        byte[] bArr = this.f2523f;
        if (z && i10 - this.f2525h >= 10) {
            while ((j10 & (-128)) != 0) {
                int i11 = this.f2525h;
                this.f2525h = i11 + 1;
                p3.j(bArr, i11, (byte) ((((int) j10) & 127) | 128));
                j10 >>>= 7;
            }
            int i12 = this.f2525h;
            this.f2525h = i12 + 1;
            p3.j(bArr, i12, (byte) j10);
            return;
        }
        while ((j10 & (-128)) != 0) {
            try {
                int i13 = this.f2525h;
                this.f2525h = i13 + 1;
                bArr[i13] = (byte) ((((int) j10) & 127) | 128);
                j10 >>>= 7;
            } catch (IndexOutOfBoundsException e5) {
                throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(i10), 1), e5);
            }
        }
        int i14 = this.f2525h;
        this.f2525h = i14 + 1;
        bArr[i14] = (byte) j10;
    }

    public final void S(byte[] bArr, int i10, int i11) throws CodedOutputStream$OutOfSpaceException {
        try {
            System.arraycopy(bArr, i10, this.f2523f, this.f2525h, i11);
            this.f2525h += i11;
        } catch (IndexOutOfBoundsException e5) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(this.f2524g), Integer.valueOf(i11)), e5);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.x1
    public final void l(int i10, int i11, byte[] bArr) throws CodedOutputStream$OutOfSpaceException {
        S(bArr, i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void y(byte b10) throws CodedOutputStream$OutOfSpaceException {
        try {
            byte[] bArr = this.f2523f;
            int i10 = this.f2525h;
            this.f2525h = i10 + 1;
            bArr[i10] = b10;
        } catch (IndexOutOfBoundsException e5) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f2525h), Integer.valueOf(this.f2524g), 1), e5);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.u
    public final void z(int i10, boolean z) throws CodedOutputStream$OutOfSpaceException {
        N(i10, 0);
        y(z ? (byte) 1 : (byte) 0);
    }
}
