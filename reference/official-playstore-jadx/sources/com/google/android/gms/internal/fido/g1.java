package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class g1 extends i1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f13781n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f13782o;

    public g1(byte[] bArr, int i10, int i11) {
        super(bArr);
        i1.g(i10, i10 + i11, bArr.length);
        this.f13781n = i10;
        this.f13782o = i11;
    }

    @Override // com.google.android.gms.internal.fido.i1
    public final byte a(int i10) {
        int i11 = this.f13782o;
        if (((i11 - (i10 + 1)) | i10) >= 0) {
            return this.f13786l[this.f13781n + i10];
        }
        if (i10 < 0) {
            throw new ArrayIndexOutOfBoundsException(a0.c.i(i10, "Index < 0: "));
        }
        throw new ArrayIndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i11, "Index > length: ", ", "));
    }

    @Override // com.google.android.gms.internal.fido.i1
    public final byte b(int i10) {
        return this.f13786l[this.f13781n + i10];
    }

    @Override // com.google.android.gms.internal.fido.i1
    public final int c() {
        return this.f13781n;
    }

    @Override // com.google.android.gms.internal.fido.i1
    public final int d() {
        return this.f13782o;
    }

    @Override // com.google.android.gms.internal.fido.i1
    public final void e(int i10, byte[] bArr) {
        System.arraycopy(this.f13786l, this.f13781n, bArr, 0, i10);
    }
}
