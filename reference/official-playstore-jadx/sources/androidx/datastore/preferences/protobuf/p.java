package androidx.datastore.preferences.protobuf;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final FileInputStream f2487c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f2488d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2489e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2490f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2491g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2492h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2493i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2494j = Integer.MAX_VALUE;

    public p(FileInputStream fileInputStream) {
        Charset charset = f1.f2421a;
        this.f2487c = fileInputStream;
        this.f2488d = new byte[4096];
        this.f2489e = 0;
        this.f2491g = 0;
        this.f2493i = 0;
    }

    public final ArrayList A(int i10) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i10 > 0) {
            int iMin = Math.min(i10, 4096);
            byte[] bArr = new byte[iMin];
            int i11 = 0;
            while (i11 < iMin) {
                int i12 = this.f2487c.read(bArr, i11, iMin - i11);
                if (i12 == -1) {
                    throw InvalidProtocolBufferException.f();
                }
                this.f2493i += i12;
                i11 += i12;
            }
            i10 -= iMin;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public final int B() throws InvalidProtocolBufferException {
        int i10 = this.f2491g;
        if (this.f2489e - i10 < 4) {
            H(4);
            i10 = this.f2491g;
        }
        this.f2491g = i10 + 4;
        byte[] bArr = this.f2488d;
        return ((bArr[i10 + 3] & 255) << 24) | (bArr[i10] & 255) | ((bArr[i10 + 1] & 255) << 8) | ((bArr[i10 + 2] & 255) << 16);
    }

    public final long C() throws InvalidProtocolBufferException {
        int i10 = this.f2491g;
        if (this.f2489e - i10 < 8) {
            H(8);
            i10 = this.f2491g;
        }
        this.f2491g = i10 + 8;
        byte[] bArr = this.f2488d;
        return ((((long) bArr[i10 + 7]) & 255) << 56) | (((long) bArr[i10]) & 255) | ((((long) bArr[i10 + 1]) & 255) << 8) | ((((long) bArr[i10 + 2]) & 255) << 16) | ((((long) bArr[i10 + 3]) & 255) << 24) | ((((long) bArr[i10 + 4]) & 255) << 32) | ((((long) bArr[i10 + 5]) & 255) << 40) | ((((long) bArr[i10 + 6]) & 255) << 48);
    }

    public final int D() {
        int i10;
        int i11 = this.f2491g;
        int i12 = this.f2489e;
        if (i12 != i11) {
            int i13 = i11 + 1;
            byte[] bArr = this.f2488d;
            byte b10 = bArr[i11];
            if (b10 >= 0) {
                this.f2491g = i13;
                return b10;
            }
            if (i12 - i13 >= 9) {
                int i14 = i11 + 2;
                int i15 = (bArr[i13] << 7) ^ b10;
                if (i15 < 0) {
                    i10 = i15 ^ (-128);
                } else {
                    int i16 = i11 + 3;
                    int i17 = (bArr[i14] << 14) ^ i15;
                    if (i17 >= 0) {
                        i10 = i17 ^ 16256;
                    } else {
                        int i18 = i11 + 4;
                        int i19 = i17 ^ (bArr[i16] << 21);
                        if (i19 < 0) {
                            i10 = (-2080896) ^ i19;
                        } else {
                            i16 = i11 + 5;
                            byte b11 = bArr[i18];
                            int i20 = (i19 ^ (b11 << 28)) ^ 266354560;
                            if (b11 < 0) {
                                i18 = i11 + 6;
                                if (bArr[i16] < 0) {
                                    i16 = i11 + 7;
                                    if (bArr[i18] < 0) {
                                        i18 = i11 + 8;
                                        if (bArr[i16] < 0) {
                                            i16 = i11 + 9;
                                            if (bArr[i18] < 0) {
                                                int i21 = i11 + 10;
                                                if (bArr[i16] >= 0) {
                                                    i14 = i21;
                                                    i10 = i20;
                                                }
                                            }
                                        }
                                    }
                                }
                                i10 = i20;
                            }
                            i10 = i20;
                        }
                        i14 = i18;
                    }
                    i14 = i16;
                }
                this.f2491g = i14;
                return i10;
            }
        }
        return (int) F();
    }

    public final long E() {
        long j10;
        long j11;
        long j12;
        long j13;
        int i10 = this.f2491g;
        int i11 = this.f2489e;
        if (i11 != i10) {
            int i12 = i10 + 1;
            byte[] bArr = this.f2488d;
            byte b10 = bArr[i10];
            if (b10 >= 0) {
                this.f2491g = i12;
                return b10;
            }
            if (i11 - i12 >= 9) {
                int i13 = i10 + 2;
                int i14 = (bArr[i12] << 7) ^ b10;
                if (i14 < 0) {
                    j10 = i14 ^ (-128);
                } else {
                    int i15 = i10 + 3;
                    int i16 = (bArr[i13] << 14) ^ i14;
                    if (i16 >= 0) {
                        j10 = i16 ^ 16256;
                        i13 = i15;
                    } else {
                        int i17 = i10 + 4;
                        int i18 = i16 ^ (bArr[i15] << 21);
                        if (i18 < 0) {
                            j13 = (-2080896) ^ i18;
                        } else {
                            long j14 = i18;
                            i13 = i10 + 5;
                            long j15 = j14 ^ (((long) bArr[i17]) << 28);
                            if (j15 >= 0) {
                                j12 = 266354560;
                            } else {
                                i17 = i10 + 6;
                                long j16 = j15 ^ (((long) bArr[i13]) << 35);
                                if (j16 < 0) {
                                    j11 = -34093383808L;
                                } else {
                                    i13 = i10 + 7;
                                    j15 = j16 ^ (((long) bArr[i17]) << 42);
                                    if (j15 >= 0) {
                                        j12 = 4363953127296L;
                                    } else {
                                        i17 = i10 + 8;
                                        j16 = j15 ^ (((long) bArr[i13]) << 49);
                                        if (j16 < 0) {
                                            j11 = -558586000294016L;
                                        } else {
                                            i13 = i10 + 9;
                                            long j17 = (j16 ^ (((long) bArr[i17]) << 56)) ^ 71499008037633920L;
                                            if (j17 < 0) {
                                                int i19 = i10 + 10;
                                                if (bArr[i13] >= 0) {
                                                    i13 = i19;
                                                }
                                            }
                                            j10 = j17;
                                        }
                                    }
                                }
                                j13 = j11 ^ j16;
                            }
                            j10 = j12 ^ j15;
                        }
                        i13 = i17;
                        j10 = j13;
                    }
                }
                this.f2491g = i13;
                return j10;
            }
        }
        return F();
    }

    public final long F() throws InvalidProtocolBufferException {
        long j10 = 0;
        for (int i10 = 0; i10 < 64; i10 += 7) {
            if (this.f2491g == this.f2489e) {
                H(1);
            }
            int i11 = this.f2491g;
            this.f2491g = i11 + 1;
            byte b10 = this.f2488d[i11];
            j10 |= ((long) (b10 & 127)) << i10;
            if ((b10 & 128) == 0) {
                return j10;
            }
        }
        throw InvalidProtocolBufferException.c();
    }

    public final void G() {
        int i10 = this.f2489e + this.f2490f;
        this.f2489e = i10;
        int i11 = this.f2493i + i10;
        int i12 = this.f2494j;
        if (i11 <= i12) {
            this.f2490f = 0;
            return;
        }
        int i13 = i11 - i12;
        this.f2490f = i13;
        this.f2489e = i10 - i13;
    }

    public final void H(int i10) throws InvalidProtocolBufferException {
        if (J(i10)) {
            return;
        }
        if (i10 <= (Integer.MAX_VALUE - this.f2493i) - this.f2491g) {
            throw InvalidProtocolBufferException.f();
        }
        throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public final void I(int i10) throws InvalidProtocolBufferException {
        int i11 = this.f2489e;
        int i12 = this.f2491g;
        int i13 = i11 - i12;
        if (i10 <= i13 && i10 >= 0) {
            this.f2491g = i12 + i10;
            return;
        }
        FileInputStream fileInputStream = this.f2487c;
        if (i10 < 0) {
            throw InvalidProtocolBufferException.d();
        }
        int i14 = this.f2493i;
        int i15 = i14 + i12;
        int i16 = i15 + i10;
        int i17 = this.f2494j;
        if (i16 > i17) {
            I((i17 - i14) - i12);
            throw InvalidProtocolBufferException.f();
        }
        this.f2493i = i15;
        this.f2489e = 0;
        this.f2491g = 0;
        while (i13 < i10) {
            long j10 = i10 - i13;
            try {
                long jSkip = fileInputStream.skip(j10);
                if (jSkip < 0 || jSkip > j10) {
                    throw new IllegalStateException(fileInputStream.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                }
                if (jSkip == 0) {
                    break;
                } else {
                    i13 += (int) jSkip;
                }
            } finally {
                this.f2493i += i13;
                G();
            }
        }
        if (i13 >= i10) {
            return;
        }
        int i18 = this.f2489e;
        int i19 = i18 - this.f2491g;
        this.f2491g = i18;
        H(1);
        while (true) {
            int i20 = i10 - i19;
            int i21 = this.f2489e;
            if (i20 <= i21) {
                this.f2491g = i20;
                return;
            } else {
                i19 += i21;
                this.f2491g = i21;
                H(1);
            }
        }
    }

    public final boolean J(int i10) throws IOException {
        int i11 = this.f2491g;
        int i12 = i11 + i10;
        int i13 = this.f2489e;
        if (i12 <= i13) {
            throw new IllegalStateException(androidx.compose.foundation.c.o(i10, "refillBuffer() called when ", " bytes were already available in buffer"));
        }
        int i14 = this.f2493i;
        if (i10 <= (Integer.MAX_VALUE - i14) - i11 && i14 + i11 + i10 <= this.f2494j) {
            byte[] bArr = this.f2488d;
            if (i11 > 0) {
                if (i13 > i11) {
                    System.arraycopy(bArr, i11, bArr, 0, i13 - i11);
                }
                this.f2493i += i11;
                this.f2489e -= i11;
                this.f2491g = 0;
            }
            int i15 = this.f2489e;
            int iMin = Math.min(bArr.length - i15, (Integer.MAX_VALUE - this.f2493i) - i15);
            FileInputStream fileInputStream = this.f2487c;
            int i16 = fileInputStream.read(bArr, i15, iMin);
            if (i16 == 0 || i16 < -1 || i16 > bArr.length) {
                throw new IllegalStateException(fileInputStream.getClass() + "#read(byte[]) returned invalid result: " + i16 + "\nThe InputStream implementation is buggy.");
            }
            if (i16 > 0) {
                this.f2489e += i16;
                G();
                if (this.f2489e >= i10) {
                    return true;
                }
                return J(i10);
            }
        }
        return false;
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final void a(int i10) throws InvalidProtocolBufferException {
        if (this.f2492h != i10) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int b() {
        return this.f2493i + this.f2491g;
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final boolean c() {
        return this.f2491g == this.f2489e && !J(1);
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final void d(int i10) {
        this.f2494j = i10;
        G();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int e(int i10) throws InvalidProtocolBufferException {
        if (i10 < 0) {
            throw InvalidProtocolBufferException.d();
        }
        int i11 = this.f2493i + this.f2491g + i10;
        int i12 = this.f2494j;
        if (i11 > i12) {
            throw InvalidProtocolBufferException.f();
        }
        this.f2494j = i11;
        G();
        return i12;
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final boolean f() {
        return E() != 0;
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final m g() throws IOException {
        int iD = D();
        int i10 = this.f2489e;
        int i11 = this.f2491g;
        int i12 = i10 - i11;
        byte[] bArr = this.f2488d;
        if (iD <= i12 && iD > 0) {
            m mVarA = m.a(i11, iD, bArr);
            this.f2491g += iD;
            return mVarA;
        }
        if (iD == 0) {
            return m.f2470m;
        }
        byte[] bArrZ = z(iD);
        if (bArrZ != null) {
            return m.a(0, bArrZ.length, bArrZ);
        }
        int i13 = this.f2491g;
        int i14 = this.f2489e;
        int length = i14 - i13;
        this.f2493i += i14;
        this.f2491g = 0;
        this.f2489e = 0;
        ArrayList<byte[]> arrayListA = A(iD - length);
        byte[] bArr2 = new byte[iD];
        System.arraycopy(bArr, i13, bArr2, 0, length);
        for (byte[] bArr3 : arrayListA) {
            System.arraycopy(bArr3, 0, bArr2, length, bArr3.length);
            length += bArr3.length;
        }
        m mVar = m.f2470m;
        return new m(bArr2);
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final double h() {
        return Double.longBitsToDouble(C());
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int i() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int j() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final long k() {
        return C();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final float l() {
        return Float.intBitsToFloat(B());
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int m() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final long n() {
        return E();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int o() {
        return B();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final long p() {
        return C();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int q() {
        int iD = D();
        return (-(iD & 1)) ^ (iD >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final long r() {
        long jE = E();
        return (-(jE & 1)) ^ (jE >>> 1);
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final String s() throws InvalidProtocolBufferException {
        int iD = D();
        byte[] bArr = this.f2488d;
        if (iD > 0) {
            int i10 = this.f2489e;
            int i11 = this.f2491g;
            if (iD <= i10 - i11) {
                String str = new String(bArr, i11, iD, f1.f2421a);
                this.f2491g += iD;
                return str;
            }
        }
        if (iD == 0) {
            return "";
        }
        if (iD > this.f2489e) {
            return new String(y(iD), f1.f2421a);
        }
        H(iD);
        String str2 = new String(bArr, this.f2491g, iD, f1.f2421a);
        this.f2491g += iD;
        return str2;
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final String t() throws IOException {
        int iD = D();
        int i10 = this.f2491g;
        int i11 = this.f2489e;
        int i12 = i11 - i10;
        byte[] bArrY = this.f2488d;
        if (iD <= i12 && iD > 0) {
            this.f2491g = i10 + iD;
        } else {
            if (iD == 0) {
                return "";
            }
            i10 = 0;
            if (iD <= i11) {
                H(iD);
                this.f2491g = iD;
            } else {
                bArrY = y(iD);
            }
        }
        return s3.f2528a.e(i10, iD, bArrY);
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int u() throws InvalidProtocolBufferException {
        if (c()) {
            this.f2492h = 0;
            return 0;
        }
        int iD = D();
        this.f2492h = iD;
        if ((iD >>> 3) != 0) {
            return iD;
        }
        throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final int v() {
        return D();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final long w() {
        return E();
    }

    @Override // androidx.datastore.preferences.protobuf.q
    public final boolean x(int i10) throws InvalidProtocolBufferException {
        int iU;
        int i11 = i10 & 7;
        int i12 = 0;
        if (i11 == 0) {
            int i13 = this.f2489e - this.f2491g;
            byte[] bArr = this.f2488d;
            if (i13 >= 10) {
                while (i12 < 10) {
                    int i14 = this.f2491g;
                    this.f2491g = i14 + 1;
                    if (bArr[i14] < 0) {
                        i12++;
                    }
                }
                throw InvalidProtocolBufferException.c();
            }
            while (i12 < 10) {
                if (this.f2491g == this.f2489e) {
                    H(1);
                }
                int i15 = this.f2491g;
                this.f2491g = i15 + 1;
                if (bArr[i15] < 0) {
                    i12++;
                }
            }
            throw InvalidProtocolBufferException.c();
            return true;
        }
        if (i11 == 1) {
            I(8);
            return true;
        }
        if (i11 == 2) {
            I(D());
            return true;
        }
        if (i11 != 3) {
            if (i11 == 4) {
                return false;
            }
            if (i11 != 5) {
                throw InvalidProtocolBufferException.b();
            }
            I(4);
            return true;
        }
        do {
            iU = u();
            if (iU == 0) {
                break;
            }
        } while (x(iU));
        a(((i10 >>> 3) << 3) | 4);
        return true;
    }

    public final byte[] y(int i10) throws IOException {
        byte[] bArrZ = z(i10);
        if (bArrZ != null) {
            return bArrZ;
        }
        int i11 = this.f2491g;
        int i12 = this.f2489e;
        int length = i12 - i11;
        this.f2493i += i12;
        this.f2491g = 0;
        this.f2489e = 0;
        ArrayList<byte[]> arrayListA = A(i10 - length);
        byte[] bArr = new byte[i10];
        System.arraycopy(this.f2488d, i11, bArr, 0, length);
        for (byte[] bArr2 : arrayListA) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    public final byte[] z(int i10) throws IOException {
        if (i10 == 0) {
            return f1.f2422b;
        }
        if (i10 < 0) {
            throw InvalidProtocolBufferException.d();
        }
        int i11 = this.f2493i;
        int i12 = this.f2491g;
        int i13 = i11 + i12 + i10;
        if (i13 - Integer.MAX_VALUE > 0) {
            throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        int i14 = this.f2494j;
        if (i13 > i14) {
            I((i14 - i11) - i12);
            throw InvalidProtocolBufferException.f();
        }
        int i15 = this.f2489e - i12;
        int i16 = i10 - i15;
        FileInputStream fileInputStream = this.f2487c;
        if (i16 >= 4096 && i16 > fileInputStream.available()) {
            return null;
        }
        byte[] bArr = new byte[i10];
        System.arraycopy(this.f2488d, this.f2491g, bArr, 0, i15);
        this.f2493i += this.f2489e;
        this.f2491g = 0;
        this.f2489e = 0;
        while (i15 < i10) {
            int i17 = fileInputStream.read(bArr, i15, i10 - i15);
            if (i17 == -1) {
                throw InvalidProtocolBufferException.f();
            }
            this.f2493i += i17;
            i15 += i17;
        }
        return bArr;
    }
}
