package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f7461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7462b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7461a, this.f7462b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        float[] fArr = this.f7461a;
        if (fArr.length < i10) {
            int length = fArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7461a = Arrays.copyOf(fArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7462b;
    }
}
