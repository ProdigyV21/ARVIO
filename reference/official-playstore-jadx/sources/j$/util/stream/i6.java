package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class i6 extends w5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[] f18728c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f18729d;

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void c(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18728c = new double[(int) j10];
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void end() {
        int i10 = 0;
        Arrays.sort(this.f18728c, 0, this.f18729d);
        long j10 = this.f18729d;
        l5 l5Var = this.f18680a;
        l5Var.c(j10);
        if (!this.f18942b) {
            while (i10 < this.f18729d) {
                l5Var.accept(this.f18728c[i10]);
                i10++;
            }
        } else {
            while (i10 < this.f18729d && !l5Var.f()) {
                l5Var.accept(this.f18728c[i10]);
                i10++;
            }
        }
        l5Var.end();
        this.f18728c = null;
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        double[] dArr = this.f18728c;
        int i10 = this.f18729d;
        this.f18729d = i10 + 1;
        dArr[i10] = d4;
    }
}
