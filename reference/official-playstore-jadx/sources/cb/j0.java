package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f7520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7521b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7520a, this.f7521b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        int[] iArr = this.f7520a;
        if (iArr.length < i10) {
            int length = iArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7520a = Arrays.copyOf(iArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7521b;
    }
}
