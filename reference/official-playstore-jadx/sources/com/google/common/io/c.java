package com.google.common.io;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends d {
    /* JADX WARN: Illegal instructions before constructor call */
    public c(String str, String str2) {
        char[] charArray = str2.toCharArray();
        super(new a(str, charArray), (Character) '=');
        ac.b.j(charArray.length == 64);
    }

    @Override // com.google.common.io.d
    public final int a(byte[] bArr, CharSequence charSequence) throws BaseEncoding$DecodingException {
        CharSequence charSequenceD = d(charSequence);
        int length = charSequenceD.length();
        a aVar = this.f14158a;
        if (!aVar.f14154h[length % aVar.f14151e]) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequenceD.length());
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < charSequenceD.length()) {
            int i12 = i10 + 2;
            int iA = (aVar.a(charSequenceD.charAt(i10 + 1)) << 12) | (aVar.a(charSequenceD.charAt(i10)) << 18);
            int i13 = i11 + 1;
            bArr[i11] = (byte) (iA >>> 16);
            if (i12 < charSequenceD.length()) {
                int i14 = i10 + 3;
                int iA2 = iA | (aVar.a(charSequenceD.charAt(i12)) << 6);
                int i15 = i11 + 2;
                bArr[i13] = (byte) ((iA2 >>> 8) & 255);
                if (i14 < charSequenceD.length()) {
                    i10 += 4;
                    i11 += 3;
                    bArr[i15] = (byte) ((iA2 | aVar.a(charSequenceD.charAt(i14))) & 255);
                } else {
                    i11 = i15;
                    i10 = i14;
                }
            } else {
                i11 = i13;
                i10 = i12;
            }
        }
        return i11;
    }

    @Override // com.google.common.io.d
    public final void c(StringBuilder sb2, byte[] bArr, int i10) {
        int i11 = 0;
        ac.b.r(0, i10, bArr.length);
        for (int i12 = i10; i12 >= 3; i12 -= 3) {
            int i13 = i11 + 2;
            int i14 = ((bArr[i11 + 1] & 255) << 8) | ((bArr[i11] & 255) << 16);
            i11 += 3;
            int i15 = i14 | (bArr[i13] & 255);
            a aVar = this.f14158a;
            char[] cArr = aVar.f14148b;
            char[] cArr2 = aVar.f14148b;
            sb2.append(cArr[i15 >>> 18]);
            sb2.append(cArr2[(i15 >>> 12) & 63]);
            sb2.append(cArr2[(i15 >>> 6) & 63]);
            sb2.append(cArr2[i15 & 63]);
        }
        if (i11 < i10) {
            b(sb2, bArr, i11, i10 - i11);
        }
    }
}
