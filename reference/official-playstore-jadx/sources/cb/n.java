package cb;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public char[] f7533a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7534b;

    @Override // cb.e1
    public final Object a() {
        return Arrays.copyOf(this.f7533a, this.f7534b);
    }

    @Override // cb.e1
    public final void b(int i10) {
        char[] cArr = this.f7533a;
        if (cArr.length < i10) {
            int length = cArr.length * 2;
            if (i10 < length) {
                i10 = length;
            }
            this.f7533a = Arrays.copyOf(cArr, i10);
        }
    }

    @Override // cb.e1
    public final int d() {
        return this.f7534b;
    }
}
