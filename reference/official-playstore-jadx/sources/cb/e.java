package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean[] f7489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7490b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7489a, this.f7490b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        boolean[] zArr = this.f7489a;
        if (zArr.length < i10) {
            int length = zArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7489a = Arrays.copyOf(zArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7490b;
    }
}
