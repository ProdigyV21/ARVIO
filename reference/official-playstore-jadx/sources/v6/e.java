package v6;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends v7.d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f22173m = new e();

    @Override // v7.d
    public final int a(int i10) {
        byte[] bArr = new byte[4];
        ((SecureRandom) g.f22175a.getValue()).nextBytes(bArr);
        return ((1 << i10) - 1) & ((bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8));
    }

    @Override // v7.d
    public final byte[] c(int i10, byte[] bArr) {
        byte[] bArr2 = new byte[i10];
        ((SecureRandom) g.f22175a.getValue()).nextBytes(bArr2);
        System.arraycopy(bArr2, 0, bArr, 0, i10);
        return bArr;
    }
}
