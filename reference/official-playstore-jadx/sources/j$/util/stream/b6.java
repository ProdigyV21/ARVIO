package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class b6 extends x5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public q6 f18641c;

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void c(long j10) {
        q6 q6Var;
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j10 <= 0) {
            q6Var = new q6();
        } else {
            q6Var = new q6((int) j10);
        }
        this.f18641c = q6Var;
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void end() {
        int[] iArr = (int[]) this.f18641c.b();
        Arrays.sort(iArr);
        long length = iArr.length;
        l5 l5Var = this.f18691a;
        l5Var.c(length);
        int i10 = 0;
        if (!this.f18951b) {
            int length2 = iArr.length;
            while (i10 < length2) {
                l5Var.accept(iArr[i10]);
                i10++;
            }
        } else {
            int length3 = iArr.length;
            while (i10 < length3) {
                int i11 = iArr[i10];
                if (l5Var.f()) {
                    break;
                }
                l5Var.accept(i11);
                i10++;
            }
        }
        l5Var.end();
    }

    @Override // j$.util.stream.j5, j$.util.stream.l5
    public final void accept(int i10) {
        this.f18641c.accept(i10);
    }
}
