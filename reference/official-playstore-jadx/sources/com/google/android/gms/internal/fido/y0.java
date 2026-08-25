package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class y0 extends a1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final char[] f13849e;

    public y0(x0 x0Var) {
        super(x0Var, (Character) null);
        this.f13849e = new char[512];
        char[] cArr = x0Var.f13840b;
        if (cArr.length != 16) {
            throw new IllegalArgumentException();
        }
        for (int i10 = 0; i10 < 256; i10++) {
            char[] cArr2 = this.f13849e;
            cArr2[i10] = cArr[i10 >>> 4];
            cArr2[i10 | 256] = cArr[i10 & 15];
        }
    }

    @Override // com.google.android.gms.internal.fido.a1
    public final void a(StringBuilder sb2, byte[] bArr, int i10) {
        b.m(0, i10, bArr.length);
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = bArr[i11] & 255;
            char[] cArr = this.f13849e;
            sb2.append(cArr[i12]);
            sb2.append(cArr[i12 | 256]);
        }
    }
}
