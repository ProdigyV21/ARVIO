package com.google.common.io;

import androidx.work.impl.t;
import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14147a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char[] f14148b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f14149c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f14150d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14151e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14152f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final byte[] f14153g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean[] f14154h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f14155i;

    public a(String str, char[] cArr, byte[] bArr, boolean z) {
        str.getClass();
        this.f14147a = str;
        cArr.getClass();
        this.f14148b = cArr;
        try {
            int length = cArr.length;
            RoundingMode roundingMode = RoundingMode.UNNECESSARY;
            int iP = f4.f.p(length);
            this.f14150d = iP;
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(iP);
            int i10 = 1 << (3 - iNumberOfTrailingZeros);
            this.f14151e = i10;
            this.f14152f = iP >> iNumberOfTrailingZeros;
            this.f14149c = cArr.length - 1;
            this.f14153g = bArr;
            boolean[] zArr = new boolean[i10];
            for (int i11 = 0; i11 < this.f14152f; i11++) {
                int i12 = this.f14150d;
                RoundingMode roundingMode2 = RoundingMode.CEILING;
                zArr[f4.f.i(i11 * 8, i12)] = true;
            }
            this.f14154h = zArr;
            this.f14155i = z;
        } catch (ArithmeticException e5) {
            throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e5);
        }
    }

    public final int a(char c10) throws BaseEncoding$DecodingException {
        if (c10 > 127) {
            throw new BaseEncoding$DecodingException("Unrecognized character: 0x" + Integer.toHexString(c10));
        }
        byte b10 = this.f14153g[c10];
        if (b10 != -1) {
            return b10;
        }
        if (c10 <= ' ' || c10 == 127) {
            throw new BaseEncoding$DecodingException("Unrecognized character: 0x" + Integer.toHexString(c10));
        }
        throw new BaseEncoding$DecodingException("Unrecognized character: " + c10);
    }

    public final a b() {
        if (this.f14155i) {
            return this;
        }
        byte[] bArr = this.f14153g;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        for (int i10 = 65; i10 <= 90; i10++) {
            int i11 = i10 | 32;
            byte b10 = bArr[i10];
            byte b11 = bArr[i11];
            if (b10 == -1) {
                bArrCopyOf[i10] = b11;
            } else {
                char c10 = (char) i10;
                char c11 = (char) i11;
                if (!(b11 == -1)) {
                    throw new IllegalStateException(t.y("Can't ignoreCase() since '%s' and '%s' encode different values", Character.valueOf(c10), Character.valueOf(c11)));
                }
                bArrCopyOf[i11] = b10;
            }
        }
        return new a(a0.c.p(new StringBuilder(), this.f14147a, ".ignoreCase()"), this.f14148b, bArrCopyOf, true);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f14155i == aVar.f14155i && Arrays.equals(this.f14148b, aVar.f14148b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f14148b) + (this.f14155i ? 1231 : 1237);
    }

    public final String toString() {
        return this.f14147a;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(String str, char[] cArr) {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i10 = 0; i10 < cArr.length; i10++) {
            char c10 = cArr[i10];
            if (c10 < 128) {
                if (bArr[c10] == -1) {
                    bArr[c10] = (byte) i10;
                } else {
                    throw new IllegalArgumentException(t.y("Duplicate character: %s", Character.valueOf(c10)));
                }
            } else {
                throw new IllegalArgumentException(t.y("Non-ASCII character: %s", Character.valueOf(c10)));
            }
        }
        this(str, cArr, bArr, false);
    }
}
