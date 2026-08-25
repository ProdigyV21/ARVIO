package com.google.android.gms.internal.fido;

import j$.util.Objects;
import java.io.IOException;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes4.dex */
public class a1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final y0 f13747d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x0 f13748a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Character f13749b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile a1 f13750c;

    static {
        new z0("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        new z0("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
        new a1("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
        new a1("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV");
        f13747d = new y0(new x0("base16()", "0123456789ABCDEF".toCharArray()));
    }

    public a1(x0 x0Var, Character ch) {
        this.f13748a = x0Var;
        if (ch != null) {
            byte[] bArr = x0Var.f13845g;
            if (bArr.length > 61 && bArr[61] != -1) {
                throw new IllegalArgumentException(b.d("Padding character %s was already in alphabet", ch));
            }
        }
        this.f13749b = ch;
    }

    public void a(StringBuilder sb2, byte[] bArr, int i10) {
        int i11 = 0;
        b.m(0, i10, bArr.length);
        while (i11 < i10) {
            x0 x0Var = this.f13748a;
            b(sb2, bArr, i11, Math.min(x0Var.f13844f, i10 - i11));
            i11 += x0Var.f13844f;
        }
    }

    public final void b(StringBuilder sb2, byte[] bArr, int i10, int i11) {
        b.m(i10, i10 + i11, bArr.length);
        x0 x0Var = this.f13748a;
        int i12 = x0Var.f13844f;
        int i13 = x0Var.f13842d;
        if (i11 > i12) {
            throw new IllegalArgumentException();
        }
        int i14 = 0;
        long j10 = 0;
        for (int i15 = 0; i15 < i11; i15++) {
            j10 = (j10 | ((long) (bArr[i10 + i15] & 255))) << 8;
        }
        int i16 = (i11 + 1) * 8;
        while (i14 < i11 * 8) {
            sb2.append(x0Var.f13840b[((int) (j10 >>> ((i16 - i13) - i14))) & x0Var.f13841c]);
            i14 += i13;
        }
        if (this.f13749b != null) {
            while (i14 < x0Var.f13844f * 8) {
                sb2.append('=');
                i14 += i13;
            }
        }
    }

    public final String c(int i10, byte[] bArr) {
        b.m(0, i10, bArr.length);
        x0 x0Var = this.f13748a;
        int i11 = x0Var.f13844f;
        RoundingMode roundingMode = RoundingMode.CEILING;
        StringBuilder sb2 = new StringBuilder(x0Var.f13843e * b.a(i10, i11));
        try {
            a(sb2, bArr, i10);
            return sb2.toString();
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a1) {
            a1 a1Var = (a1) obj;
            if (this.f13748a.equals(a1Var.f13748a) && Objects.equals(this.f13749b, a1Var.f13749b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f13748a.hashCode() ^ Objects.hashCode(this.f13749b);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("BaseEncoding.");
        x0 x0Var = this.f13748a;
        sb2.append(x0Var);
        if (8 % x0Var.f13842d != 0) {
            Character ch = this.f13749b;
            if (ch == null) {
                sb2.append(".omitPadding()");
            } else {
                sb2.append(".withPadChar('");
                sb2.append(ch);
                sb2.append("')");
            }
        }
        return sb2.toString();
    }

    public a1(String str, String str2) {
        this(new x0(str, str2.toCharArray()), (Character) '=');
    }
}
