package o3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class o extends n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final byte[] f20760e;

    public o(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.f20760e = bArr;
    }

    @Override // o3.n
    public final byte[] f0() {
        return this.f20760e;
    }
}
