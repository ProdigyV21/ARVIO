package kotlin.reflect.jvm.internal.impl.protobuf;

import androidx.media3.common.C;
import com.google.android.gms.cast.MediaError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f19785c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InputStream f19787e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f19788f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19791i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f19790h = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f19783a = new byte[4096];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19784b = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f19786d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f19789g = 0;

    public i(InputStream inputStream) {
        this.f19787e = inputStream;
    }

    public final void a(int i10) {
        if (this.f19788f != i10) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int b() {
        int i10 = this.f19790h;
        if (i10 == Integer.MAX_VALUE) {
            return -1;
        }
        return i10 - (this.f19789g + this.f19786d);
    }

    public final void c(int i10) {
        this.f19790h = i10;
        o();
    }

    public final int d(int i10) {
        if (i10 < 0) {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i11 = this.f19789g + this.f19786d + i10;
        int i12 = this.f19790h;
        if (i11 > i12) {
            throw InvalidProtocolBufferException.a();
        }
        this.f19790h = i11;
        o();
        return i12;
    }

    public final b0 e() {
        int iK = k();
        int i10 = this.f19784b;
        int i11 = this.f19786d;
        if (iK > i10 - i11 || iK <= 0) {
            return iK == 0 ? g.f19772i : new b0(h(iK));
        }
        byte[] bArr = new byte[iK];
        System.arraycopy(this.f19783a, i11, bArr, 0, iK);
        b0 b0Var = new b0(bArr);
        this.f19786d += iK;
        return b0Var;
    }

    public final int f() {
        return k();
    }

    public final c0 g(e0 e0Var, l lVar) throws InvalidProtocolBufferException {
        int iK = k();
        if (this.f19791i >= 64) {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iD = d(iK);
        this.f19791i++;
        c0 c0Var = (c0) e0Var.b(this, lVar);
        a(0);
        this.f19791i--;
        c(iD);
        return c0Var;
    }

    public final byte[] h(int i10) throws IOException {
        if (i10 <= 0) {
            if (i10 == 0) {
                return x.f19848a;
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i11 = this.f19789g;
        int i12 = this.f19786d;
        int i13 = i11 + i12 + i10;
        int i14 = this.f19790h;
        if (i13 > i14) {
            r((i14 - i11) - i12);
            throw InvalidProtocolBufferException.a();
        }
        byte[] bArr = this.f19783a;
        if (i10 < 4096) {
            byte[] bArr2 = new byte[i10];
            int i15 = this.f19784b - i12;
            System.arraycopy(bArr, i12, bArr2, 0, i15);
            this.f19786d = this.f19784b;
            int i16 = i10 - i15;
            if (i16 > 0) {
                p(i16);
            }
            System.arraycopy(bArr, 0, bArr2, i15, i16);
            this.f19786d = i16;
            return bArr2;
        }
        int i17 = this.f19784b;
        this.f19789g = i11 + i17;
        this.f19786d = 0;
        this.f19784b = 0;
        int length = i17 - i12;
        int i18 = i10 - length;
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i18 > 0) {
            int iMin = Math.min(i18, 4096);
            byte[] bArr3 = new byte[iMin];
            int i19 = 0;
            while (i19 < iMin) {
                int i20 = this.f19787e.read(bArr3, i19, iMin - i19);
                if (i20 == -1) {
                    throw InvalidProtocolBufferException.a();
                }
                this.f19789g += i20;
                i19 += i20;
            }
            i18 -= iMin;
            arrayList.add(bArr3);
        }
        byte[] bArr4 = new byte[i10];
        System.arraycopy(bArr, i12, bArr4, 0, length);
        for (byte[] bArr5 : arrayList) {
            System.arraycopy(bArr5, 0, bArr4, length, bArr5.length);
            length += bArr5.length;
        }
        return bArr4;
    }

    public final int i() throws InvalidProtocolBufferException {
        int i10 = this.f19786d;
        if (this.f19784b - i10 < 4) {
            p(4);
            i10 = this.f19786d;
        }
        this.f19786d = i10 + 4;
        byte[] bArr = this.f19783a;
        return ((bArr[i10 + 3] & 255) << 24) | (bArr[i10] & 255) | ((bArr[i10 + 1] & 255) << 8) | ((bArr[i10 + 2] & 255) << 16);
    }

    public final long j() throws InvalidProtocolBufferException {
        int i10 = this.f19786d;
        if (this.f19784b - i10 < 8) {
            p(8);
            i10 = this.f19786d;
        }
        this.f19786d = i10 + 8;
        byte[] bArr = this.f19783a;
        return ((((long) bArr[i10 + 7]) & 255) << 56) | (((long) bArr[i10]) & 255) | ((((long) bArr[i10 + 1]) & 255) << 8) | ((((long) bArr[i10 + 2]) & 255) << 16) | ((((long) bArr[i10 + 3]) & 255) << 24) | ((((long) bArr[i10 + 4]) & 255) << 32) | ((((long) bArr[i10 + 5]) & 255) << 40) | ((((long) bArr[i10 + 6]) & 255) << 48);
    }

    public final int k() {
        int i10;
        int i11 = this.f19786d;
        int i12 = this.f19784b;
        if (i12 != i11) {
            int i13 = i11 + 1;
            byte[] bArr = this.f19783a;
            byte b10 = bArr[i11];
            if (b10 >= 0) {
                this.f19786d = i13;
                return b10;
            }
            if (i12 - i13 >= 9) {
                int i14 = i11 + 2;
                int i15 = (bArr[i13] << 7) ^ b10;
                long j10 = i15;
                if (j10 < 0) {
                    i10 = (int) ((-128) ^ j10);
                } else {
                    int i16 = i11 + 3;
                    int i17 = (bArr[i14] << 14) ^ i15;
                    long j11 = i17;
                    if (j11 >= 0) {
                        i10 = (int) (16256 ^ j11);
                    } else {
                        int i18 = i11 + 4;
                        int i19 = i17 ^ (bArr[i16] << 21);
                        long j12 = i19;
                        if (j12 < 0) {
                            i10 = (int) ((-2080896) ^ j12);
                        } else {
                            i16 = i11 + 5;
                            byte b11 = bArr[i18];
                            int i20 = (int) (((long) (i19 ^ (b11 << 28))) ^ 266354560);
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
                this.f19786d = i14;
                return i10;
            }
        }
        return (int) m();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        if (r3[r2] < 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long l() {
        /*
            r12 = this;
            int r0 = r12.f19786d
            int r1 = r12.f19784b
            if (r1 != r0) goto L8
            goto Lb8
        L8:
            int r2 = r0 + 1
            byte[] r3 = r12.f19783a
            r4 = r3[r0]
            if (r4 < 0) goto L14
            r12.f19786d = r2
            long r0 = (long) r4
            return r0
        L14:
            int r1 = r1 - r2
            r5 = 9
            if (r1 >= r5) goto L1b
            goto Lb8
        L1b:
            int r1 = r0 + 2
            r2 = r3[r2]
            int r2 = r2 << 7
            r2 = r2 ^ r4
            long r4 = (long) r2
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L2e
            r2 = -128(0xffffffffffffff80, double:NaN)
        L2b:
            long r2 = r2 ^ r4
            goto Lc1
        L2e:
            int r2 = r0 + 3
            r1 = r3[r1]
            int r1 = r1 << 14
            long r8 = (long) r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L42
            r0 = 16256(0x3f80, double:8.0315E-320)
        L3c:
            long r0 = r0 ^ r4
            r10 = r0
            r1 = r2
            r2 = r10
            goto Lc1
        L42:
            int r1 = r0 + 4
            r2 = r3[r2]
            int r2 = r2 << 21
            long r8 = (long) r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L52
            r2 = -2080896(0xffffffffffe03f80, double:NaN)
            goto L2b
        L52:
            int r2 = r0 + 5
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 28
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L63
            r0 = 266354560(0xfe03f80, double:1.315966377E-315)
            goto L3c
        L63:
            int r1 = r0 + 6
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 35
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L76
            r2 = -34093383808(0xfffffff80fe03f80, double:NaN)
            goto L2b
        L76:
            int r2 = r0 + 7
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 42
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L89
            r0 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L3c
        L89:
            int r1 = r0 + 8
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 49
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L9c
            r2 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L2b
        L9c:
            int r2 = r0 + 9
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 56
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            r8 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto Lbf
            int r1 = r0 + 10
            r0 = r3[r2]
            long r2 = (long) r0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto Lbd
        Lb8:
            long r0 = r12.m()
            return r0
        Lbd:
            r2 = r4
            goto Lc1
        Lbf:
            r1 = r2
            goto Lbd
        Lc1:
            r12.f19786d = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.i.l():long");
    }

    public final long m() throws InvalidProtocolBufferException {
        long j10 = 0;
        for (int i10 = 0; i10 < 64; i10 += 7) {
            if (this.f19786d == this.f19784b) {
                p(1);
            }
            int i11 = this.f19786d;
            this.f19786d = i11 + 1;
            byte b10 = this.f19783a[i11];
            j10 |= ((long) (b10 & 127)) << i10;
            if ((b10 & 128) == 0) {
                return j10;
            }
        }
        throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
    }

    public final int n() throws InvalidProtocolBufferException {
        if (this.f19786d == this.f19784b && !s(1)) {
            this.f19788f = 0;
            return 0;
        }
        int iK = k();
        this.f19788f = iK;
        if ((iK >>> 3) != 0) {
            return iK;
        }
        throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    public final void o() {
        int i10 = this.f19784b + this.f19785c;
        this.f19784b = i10;
        int i11 = this.f19789g + i10;
        int i12 = this.f19790h;
        if (i11 <= i12) {
            this.f19785c = 0;
            return;
        }
        int i13 = i11 - i12;
        this.f19785c = i13;
        this.f19784b = i10 - i13;
    }

    public final void p(int i10) throws InvalidProtocolBufferException {
        if (!s(i10)) {
            throw InvalidProtocolBufferException.a();
        }
    }

    public final boolean q(int i10, j jVar) throws InvalidProtocolBufferException {
        int iN;
        int i11 = i10 & 7;
        if (i11 == 0) {
            long jL = l();
            jVar.v(i10);
            jVar.w(jL);
            return true;
        }
        if (i11 == 1) {
            long j10 = j();
            jVar.v(i10);
            jVar.u(j10);
            return true;
        }
        if (i11 == 2) {
            b0 b0VarE = e();
            jVar.v(i10);
            jVar.v(b0VarE.size());
            jVar.r(b0VarE);
            return true;
        }
        if (i11 != 3) {
            if (i11 == 4) {
                return false;
            }
            if (i11 != 5) {
                throw new InvalidProtocolBufferException("Protocol message tag had invalid wire type.");
            }
            int i12 = i();
            jVar.v(i10);
            jVar.t(i12);
            return true;
        }
        jVar.v(i10);
        do {
            iN = n();
            if (iN == 0) {
                break;
            }
        } while (q(iN, jVar));
        int i13 = ((i10 >>> 3) << 3) | 4;
        a(i13);
        jVar.v(i13);
        return true;
    }

    public final void r(int i10) throws InvalidProtocolBufferException {
        int i11 = this.f19784b;
        int i12 = this.f19786d;
        int i13 = i11 - i12;
        if (i10 <= i13 && i10 >= 0) {
            this.f19786d = i12 + i10;
            return;
        }
        if (i10 < 0) {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i14 = this.f19789g;
        int i15 = i14 + i12 + i10;
        int i16 = this.f19790h;
        if (i15 > i16) {
            r((i16 - i14) - i12);
            throw InvalidProtocolBufferException.a();
        }
        this.f19786d = i11;
        p(1);
        while (true) {
            int i17 = i10 - i13;
            int i18 = this.f19784b;
            if (i17 <= i18) {
                this.f19786d = i17;
                return;
            } else {
                i13 += i18;
                this.f19786d = i18;
                p(1);
            }
        }
    }

    public final boolean s(int i10) throws IOException {
        InputStream inputStream;
        int i11 = this.f19786d;
        int i12 = i11 + i10;
        int i13 = this.f19784b;
        if (i12 <= i13) {
            StringBuilder sb2 = new StringBuilder(77);
            sb2.append("refillBuffer() called when ");
            sb2.append(i10);
            sb2.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb2.toString());
        }
        if (this.f19789g + i11 + i10 <= this.f19790h && (inputStream = this.f19787e) != null) {
            byte[] bArr = this.f19783a;
            if (i11 > 0) {
                if (i13 > i11) {
                    System.arraycopy(bArr, i11, bArr, 0, i13 - i11);
                }
                this.f19789g += i11;
                this.f19784b -= i11;
                this.f19786d = 0;
            }
            int i14 = this.f19784b;
            int i15 = inputStream.read(bArr, i14, bArr.length - i14);
            if (i15 == 0 || i15 < -1 || i15 > bArr.length) {
                StringBuilder sb3 = new StringBuilder(MediaError.DetailedErrorCode.MEDIA_DECODE);
                sb3.append("InputStream#read(byte[]) returned invalid result: ");
                sb3.append(i15);
                sb3.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb3.toString());
            }
            if (i15 > 0) {
                this.f19784b += i15;
                if ((this.f19789g + i10) - C.BUFFER_FLAG_NOT_DEPENDED_ON > 0) {
                    throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
                o();
                if (this.f19784b >= i10) {
                    return true;
                }
                return s(i10);
            }
        }
        return false;
    }
}
