package com.google.common.io;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final char[] f14156f;

    public b(a aVar) {
        super(aVar, (Character) null);
        this.f14156f = new char[512];
        char[] cArr = aVar.f14148b;
        ac.b.j(cArr.length == 16);
        for (int i10 = 0; i10 < 256; i10++) {
            char[] cArr2 = this.f14156f;
            cArr2[i10] = cArr[i10 >>> 4];
            cArr2[i10 | 256] = cArr[i10 & 15];
        }
    }

    @Override // com.google.common.io.d
    public final int a(byte[] bArr, CharSequence charSequence) throws BaseEncoding$DecodingException {
        if (charSequence.length() % 2 == 1) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequence.length());
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < charSequence.length()) {
            char cCharAt = charSequence.charAt(i10);
            a aVar = this.f14158a;
            bArr[i11] = (byte) ((aVar.a(cCharAt) << 4) | aVar.a(charSequence.charAt(i10 + 1)));
            i10 += 2;
            i11++;
        }
        return i11;
    }

    @Override // com.google.common.io.d
    public final void c(StringBuilder sb2, byte[] bArr, int i10) {
        ac.b.r(0, i10, bArr.length);
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = bArr[i11] & 255;
            char[] cArr = this.f14156f;
            sb2.append(cArr[i12]);
            sb2.append(cArr[i12 | 256]);
        }
    }
}
