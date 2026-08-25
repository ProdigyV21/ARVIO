package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class k6 extends y5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long[] f18765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f18766d;

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final void c(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18765c = new long[(int) j10];
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final void end() {
        int i10 = 0;
        Arrays.sort(this.f18765c, 0, this.f18766d);
        long j10 = this.f18766d;
        l5 l5Var = this.f18699a;
        l5Var.c(j10);
        if (!this.f18962b) {
            while (i10 < this.f18766d) {
                l5Var.accept(this.f18765c[i10]);
                i10++;
            }
        } else {
            while (i10 < this.f18766d && !l5Var.f()) {
                l5Var.accept(this.f18765c[i10]);
                i10++;
            }
        }
        l5Var.end();
        this.f18765c = null;
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) {
        long[] jArr = this.f18765c;
        int i10 = this.f18766d;
        this.f18766d = i10 + 1;
        jArr[i10] = j10;
    }
}
