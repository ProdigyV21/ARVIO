package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class a6 extends w5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o6 f18616c;

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void c(long j10) {
        o6 o6Var;
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j10 <= 0) {
            o6Var = new o6();
        } else {
            o6Var = new o6((int) j10);
        }
        this.f18616c = o6Var;
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void end() {
        double[] dArr = (double[]) this.f18616c.b();
        Arrays.sort(dArr);
        long length = dArr.length;
        l5 l5Var = this.f18680a;
        l5Var.c(length);
        int i10 = 0;
        if (!this.f18942b) {
            int length2 = dArr.length;
            while (i10 < length2) {
                l5Var.accept(dArr[i10]);
                i10++;
            }
        } else {
            int length3 = dArr.length;
            while (i10 < length3) {
                double d4 = dArr[i10];
                if (l5Var.f()) {
                    break;
                }
                l5Var.accept(d4);
                i10++;
            }
        }
        l5Var.end();
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        this.f18616c.accept(d4);
    }
}
