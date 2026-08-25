package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class o1 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short[] f7539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7540b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7539a, this.f7540b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        short[] sArr = this.f7539a;
        if (sArr.length < i10) {
            int length = sArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7539a = Arrays.copyOf(sArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7540b;
    }
}
