package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class fa extends ha {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f13252n;

    public fa(byte[] bArr) {
        super(bArr);
        ha.d(bArr.length);
        this.f13252n = 47;
    }

    @Override // com.google.android.gms.internal.cast.ha
    public final byte a(int i10) {
        int i11 = this.f13252n;
        if (((i11 - (i10 + 1)) | i10) >= 0) {
            return this.f13299l[i10];
        }
        if (i10 < 0) {
            throw new ArrayIndexOutOfBoundsException(androidx.compose.foundation.c.p(i10, "Index < 0: ", new StringBuilder(String.valueOf(i10).length() + 11)));
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 18 + String.valueOf(i11).length());
        sb2.append("Index > length: ");
        sb2.append(i10);
        sb2.append(", ");
        sb2.append(i11);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.cast.ha
    public final byte b(int i10) {
        return this.f13299l[i10];
    }

    @Override // com.google.android.gms.internal.cast.ha
    public final int c() {
        return this.f13252n;
    }
}
