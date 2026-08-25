package v7;

import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a extends d {
    @Override // v7.d
    public final int a(int i10) {
        return ((-i10) >> 31) & (i().nextInt() >>> (32 - i10));
    }

    @Override // v7.d
    public final void b(byte[] bArr) {
        i().nextBytes(bArr);
    }

    @Override // v7.d
    public final int d() {
        return i().nextInt();
    }

    @Override // v7.d
    public final long f() {
        return i().nextLong();
    }

    public abstract Random i();

    public final int j(int i10) {
        return i().nextInt(i10);
    }
}
