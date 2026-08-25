package com.google.android.gms.internal.cast;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class ia extends u0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Logger f13325q = Logger.getLogger(ia.class.getName());

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final boolean f13326r = h.f13270e;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ja f13327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final byte[] f13328n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f13329o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f13330p;

    public ia(byte[] bArr, int i10) {
        super(15);
        int length = bArr.length;
        if (((length - i10) | i10) < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(length, i10, "Array range is invalid. Buffer.length=", ", offset=0, length="));
        }
        this.f13328n = bArr;
        this.f13330p = 0;
        this.f13329o = i10;
    }

    public static int O(int i10) {
        return (352 - (Integer.numberOfLeadingZeros(i10) * 9)) >>> 6;
    }

    public static int P(long j10) {
        return (640 - (Long.numberOfLeadingZeros(j10) * 9)) >>> 6;
    }

    public static int Q(String str) {
        int length;
        try {
            length = j.a(str);
        } catch (i unused) {
            length = str.getBytes(ya.f13698a).length;
        }
        return O(length) + length;
    }

    public final void C(int i10, int i11) {
        J(i10 << 3);
        J(i11);
    }

    public final void D(int i10, int i11) {
        J((i10 << 3) | 5);
        K(i11);
    }

    public final void E(int i10, long j10) {
        J(i10 << 3);
        L(j10);
    }

    public final void F(int i10, long j10) {
        J((i10 << 3) | 1);
        M(j10);
    }

    public final void G(int i10, String str) {
        J((i10 << 3) | 2);
        int i11 = this.f13330p;
        try {
            int iO = O(str.length() * 3);
            int iO2 = O(str.length());
            int i12 = this.f13329o;
            byte[] bArr = this.f13328n;
            if (iO2 != iO) {
                J(j.a(str));
                int i13 = this.f13330p;
                this.f13330p = j.b(str, bArr, i13, i12 - i13);
            } else {
                int i14 = i11 + iO2;
                this.f13330p = i14;
                int iB = j.b(str, bArr, i14, i12 - i14);
                this.f13330p = i11;
                J((iB - i11) - iO2);
                this.f13330p = iB;
            }
        } catch (i e5) {
            this.f13330p = i11;
            f13325q.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) e5);
            byte[] bytes = str.getBytes(ya.f13698a);
            try {
                int length = bytes.length;
                J(length);
                N(length, bytes);
            } catch (IndexOutOfBoundsException e6) {
                throw new zzxo(e6);
            }
        } catch (IndexOutOfBoundsException e10) {
            throw new zzxo(e10);
        }
    }

    public final void H(int i10, ha haVar) {
        J((i10 << 3) | 2);
        J(haVar.c());
        N(haVar.c(), haVar.f13299l);
    }

    public final void I(int i10) throws zzxo {
        if (i10 >= 0) {
            J(i10);
        } else {
            L(i10);
        }
    }

    public final void J(int i10) {
        int i11;
        int i12 = this.f13330p;
        while (true) {
            int i13 = i10 & (-128);
            byte[] bArr = this.f13328n;
            if (i13 == 0) {
                i11 = i12 + 1;
                bArr[i12] = (byte) i10;
                this.f13330p = i11;
                return;
            } else {
                i11 = i12 + 1;
                try {
                    bArr[i12] = (byte) (i10 | 128);
                    i10 >>>= 7;
                    i12 = i11;
                } catch (IndexOutOfBoundsException e5) {
                    throw new zzxo(i11, this.f13329o, 1, e5);
                }
            }
            throw new zzxo(i11, this.f13329o, 1, e5);
        }
    }

    public final void K(int i10) throws zzxo {
        int i11 = this.f13330p;
        try {
            byte[] bArr = this.f13328n;
            bArr[i11] = (byte) i10;
            bArr[i11 + 1] = (byte) (i10 >> 8);
            bArr[i11 + 2] = (byte) (i10 >> 16);
            bArr[i11 + 3] = (byte) (i10 >> 24);
            this.f13330p = i11 + 4;
        } catch (IndexOutOfBoundsException e5) {
            throw new zzxo(i11, this.f13329o, 4, e5);
        }
    }

    public final void L(long j10) throws zzxo {
        int i10;
        int i11 = this.f13330p;
        int i12 = this.f13329o;
        byte[] bArr = this.f13328n;
        if (!f13326r || i12 - i11 < 10) {
            long j11 = j10;
            while ((j11 & (-128)) != 0) {
                int i13 = i11 + 1;
                try {
                    bArr[i11] = (byte) (((int) j11) | 128);
                    j11 >>>= 7;
                    i11 = i13;
                } catch (IndexOutOfBoundsException e5) {
                    e = e5;
                    i10 = i13;
                    throw new zzxo(i10, i12, 1, e);
                }
            }
            i10 = i11 + 1;
            try {
                bArr[i11] = (byte) j11;
            } catch (IndexOutOfBoundsException e6) {
                e = e6;
                throw new zzxo(i10, i12, 1, e);
            }
        } else {
            long j12 = j10;
            while ((j12 & (-128)) != 0) {
                h.f13268c.a(bArr, h.f13271f + ((long) i11), (byte) (((int) j12) | 128));
                j12 >>>= 7;
                i11++;
            }
            i10 = i11 + 1;
            h.f13268c.a(bArr, h.f13271f + ((long) i11), (byte) j12);
        }
        this.f13330p = i10;
    }

    public final void M(long j10) throws zzxo {
        int i10 = this.f13330p;
        try {
            byte[] bArr = this.f13328n;
            bArr[i10] = (byte) j10;
            bArr[i10 + 1] = (byte) (j10 >> 8);
            bArr[i10 + 2] = (byte) (j10 >> 16);
            bArr[i10 + 3] = (byte) (j10 >> 24);
            bArr[i10 + 4] = (byte) (j10 >> 32);
            bArr[i10 + 5] = (byte) (j10 >> 40);
            bArr[i10 + 6] = (byte) (j10 >> 48);
            bArr[i10 + 7] = (byte) (j10 >> 56);
            this.f13330p = i10 + 8;
        } catch (IndexOutOfBoundsException e5) {
            throw new zzxo(i10, this.f13329o, 8, e5);
        }
    }

    public final void N(int i10, byte[] bArr) throws zzxo {
        try {
            System.arraycopy(bArr, 0, this.f13328n, this.f13330p, i10);
            this.f13330p += i10;
        } catch (IndexOutOfBoundsException e5) {
            throw new zzxo(this.f13330p, this.f13329o, i10, e5);
        }
    }

    public final void r(int i10, int i11) {
        J((i10 << 3) | i11);
    }

    public final void u(int i10, int i11) {
        J(i10 << 3);
        I(i11);
    }
}
