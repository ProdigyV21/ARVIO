package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class v1 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f7575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7576b;

    @Override // cb.e1
    public final Object a() {
        return new x6.l0(Arrays.copyOf(this.f7575a, this.f7576b));
    }

    @Override // cb.e1
    public final void b(int i10) {
        byte[] bArr = this.f7575a;
        if (bArr.length < i10) {
            int length = bArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7575a = Arrays.copyOf(bArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7576b;
    }
}
