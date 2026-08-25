package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class y1 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f7594a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7595b;

    @Override // cb.e1
    public final Object a() {
        return new x6.n0(Arrays.copyOf(this.f7594a, this.f7595b));
    }

    @Override // cb.e1
    public final void b(int i10) {
        int[] iArr = this.f7594a;
        if (iArr.length < i10) {
            int length = iArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7594a = Arrays.copyOf(iArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7595b;
    }
}
