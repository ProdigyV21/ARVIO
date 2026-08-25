package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class j6 extends x5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f18749c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f18750d;

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void c(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18749c = new int[(int) j10];
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void end() {
        int i10 = 0;
        Arrays.sort(this.f18749c, 0, this.f18750d);
        long j10 = this.f18750d;
        l5 l5Var = this.f18691a;
        l5Var.c(j10);
        if (!this.f18951b) {
            while (i10 < this.f18750d) {
                l5Var.accept(this.f18749c[i10]);
                i10++;
            }
        } else {
            while (i10 < this.f18750d && !l5Var.f()) {
                l5Var.accept(this.f18749c[i10]);
                i10++;
            }
        }
        l5Var.end();
        this.f18749c = null;
    }

    @Override // j$.util.stream.j5, j$.util.stream.l5
    public final void accept(int i10) {
        int[] iArr = this.f18749c;
        int i11 = this.f18750d;
        this.f18750d = i11 + 1;
        iArr[i11] = i10;
    }
}
