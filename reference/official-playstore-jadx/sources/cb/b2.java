package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class b2 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f7466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7467b;

    @Override // cb.e1
    public final Object a() {
        return new x6.p0(Arrays.copyOf(this.f7466a, this.f7467b));
    }

    @Override // cb.e1
    public final void b(int i10) {
        long[] jArr = this.f7466a;
        if (jArr.length < i10) {
            int length = jArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7466a = Arrays.copyOf(jArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7467b;
    }
}
