package com.google.common.io;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f14157e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f14158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Character f14159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile d f14160c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile d f14161d;

    static {
        new c("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        new c("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
        new d("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
        new d("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV");
        f14157e = new b(new a("base16()", "0123456789ABCDEF".toCharArray()));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public d(com.google.common.io.a r3, java.lang.Character r4) {
        /*
            r2 = this;
            r2.<init>()
            r2.f14158a = r3
            if (r4 == 0) goto L17
            char r0 = r4.charValue()
            byte[] r3 = r3.f14153g
            int r1 = r3.length
            if (r0 >= r1) goto L17
            r3 = r3[r0]
            r0 = -1
            if (r3 == r0) goto L17
            r3 = 0
            goto L18
        L17:
            r3 = 1
        L18:
            java.lang.String r0 = "Padding character %s was already in alphabet"
            ac.b.l(r3, r0, r4)
            r2.f14159b = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.d.<init>(com.google.common.io.a, java.lang.Character):void");
    }

    public int a(byte[] bArr, CharSequence charSequence) {
        CharSequence charSequenceD = d(charSequence);
        int length = charSequenceD.length();
        a aVar = this.f14158a;
        boolean[] zArr = aVar.f14154h;
        int i10 = aVar.f14150d;
        int i11 = aVar.f14151e;
        if (!zArr[length % i11]) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequenceD.length());
        }
        int i12 = 0;
        for (int i13 = 0; i13 < charSequenceD.length(); i13 += i11) {
            long jA = 0;
            int i14 = 0;
            for (int i15 = 0; i15 < i11; i15++) {
                jA <<= i10;
                if (i13 + i15 < charSequenceD.length()) {
                    jA |= (long) aVar.a(charSequenceD.charAt(i14 + i13));
                    i14++;
                }
            }
            int i16 = aVar.f14152f;
            int i17 = (i16 * 8) - (i14 * i10);
            int i18 = (i16 - 1) * 8;
            while (i18 >= i17) {
                bArr[i12] = (byte) ((jA >>> i18) & 255);
                i18 -= 8;
                i12++;
            }
        }
        return i12;
    }

    public final void b(StringBuilder sb2, byte[] bArr, int i10, int i11) {
        ac.b.r(i10, i10 + i11, bArr.length);
        a aVar = this.f14158a;
        int i12 = aVar.f14152f;
        int i13 = aVar.f14150d;
        int i14 = 0;
        ac.b.j(i11 <= i12);
        long j10 = 0;
        for (int i15 = 0; i15 < i11; i15++) {
            j10 = (j10 | ((long) (bArr[i10 + i15] & 255))) << 8;
        }
        int i16 = ((i11 + 1) * 8) - i13;
        while (i14 < i11 * 8) {
            sb2.append(aVar.f14148b[((int) (j10 >>> (i16 - i14))) & aVar.f14149c]);
            i14 += i13;
        }
        Character ch = this.f14159b;
        if (ch != null) {
            while (i14 < aVar.f14152f * 8) {
                sb2.append(ch.charValue());
                i14 += i13;
            }
        }
    }

    public void c(StringBuilder sb2, byte[] bArr, int i10) {
        int i11 = 0;
        ac.b.r(0, i10, bArr.length);
        while (i11 < i10) {
            a aVar = this.f14158a;
            b(sb2, bArr, i11, Math.min(aVar.f14152f, i10 - i11));
            i11 += aVar.f14152f;
        }
    }

    public final CharSequence d(CharSequence charSequence) {
        charSequence.getClass();
        Character ch = this.f14159b;
        if (ch == null) {
            return charSequence;
        }
        char cCharValue = ch.charValue();
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) == cCharValue) {
            length--;
        }
        return charSequence.subSequence(0, length + 1);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (this.f14158a.equals(dVar.f14158a) && Objects.equals(this.f14159b, dVar.f14159b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f14158a.hashCode() ^ Objects.hashCode(this.f14159b);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("BaseEncoding.");
        a aVar = this.f14158a;
        sb2.append(aVar);
        if (8 % aVar.f14150d != 0) {
            Character ch = this.f14159b;
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

    public d(String str, String str2) {
        this(new a(str, str2.toCharArray()), (Character) '=');
    }
}
