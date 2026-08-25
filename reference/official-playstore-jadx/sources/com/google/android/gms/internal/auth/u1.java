package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class u1 extends x1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f13127n;

    public u1(byte[] bArr, int i10) {
        super(bArr);
        x1.d(0, i10, bArr.length);
        this.f13127n = i10;
    }

    @Override // com.google.android.gms.internal.auth.x1
    public final byte a(int i10) {
        int i11 = this.f13127n;
        if (((i11 - (i10 + 1)) | i10) >= 0) {
            return this.f13144l[i10];
        }
        if (i10 < 0) {
            throw new ArrayIndexOutOfBoundsException(a0.c.i(i10, "Index < 0: "));
        }
        throw new ArrayIndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i11, "Index > length: ", ", "));
    }

    @Override // com.google.android.gms.internal.auth.x1
    public final byte b(int i10) {
        return this.f13144l[i10];
    }

    @Override // com.google.android.gms.internal.auth.x1
    public final int c() {
        return this.f13127n;
    }
}
