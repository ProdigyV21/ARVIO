package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class z0 extends a1 {
    /* JADX WARN: Illegal instructions before constructor call */
    public z0(String str, String str2) {
        char[] charArray = str2.toCharArray();
        super(new x0(str, charArray), (Character) '=');
        if (charArray.length != 64) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.google.android.gms.internal.fido.a1
    public final void a(StringBuilder sb2, byte[] bArr, int i10) {
        int i11 = 0;
        b.m(0, i10, bArr.length);
        for (int i12 = i10; i12 >= 3; i12 -= 3) {
            int i13 = ((bArr[i11 + 1] & 255) << 8) | ((bArr[i11] & 255) << 16) | (bArr[i11 + 2] & 255);
            x0 x0Var = this.f13748a;
            char[] cArr = x0Var.f13840b;
            char[] cArr2 = x0Var.f13840b;
            sb2.append(cArr[i13 >>> 18]);
            sb2.append(cArr2[(i13 >>> 12) & 63]);
            sb2.append(cArr2[(i13 >>> 6) & 63]);
            sb2.append(cArr2[i13 & 63]);
            i11 += 3;
        }
        if (i11 < i10) {
            b(sb2, bArr, i11, i10 - i11);
        }
    }
}
