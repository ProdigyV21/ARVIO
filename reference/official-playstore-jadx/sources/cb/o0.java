package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f7537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7538b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7537a, this.f7538b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        long[] jArr = this.f7537a;
        if (jArr.length < i10) {
            int length = jArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7537a = Arrays.copyOf(jArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7538b;
    }
}
