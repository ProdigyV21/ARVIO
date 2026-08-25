package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double[] f7567a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7568b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7567a, this.f7568b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        double[] dArr = this.f7567a;
        if (dArr.length < i10) {
            int length = dArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7567a = Arrays.copyOf(dArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7568b;
    }
}
