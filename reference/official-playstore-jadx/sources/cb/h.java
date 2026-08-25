package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f7508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7509b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7508a, this.f7509b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        byte[] bArr = this.f7508a;
        if (bArr.length < i10) {
            int length = bArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7508a = Arrays.copyOf(bArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7509b;
    }
}
