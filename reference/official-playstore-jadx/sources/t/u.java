package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class u extends k {
    public u() {
        this.f21839a = new int[16];
    }

    public final void b(int i10) {
        int i11 = this.f21840b + 1;
        int[] iArr = this.f21839a;
        if (iArr.length < i11) {
            this.f21839a = Arrays.copyOf(iArr, Math.max(i11, (iArr.length * 3) / 2));
        }
        int[] iArr2 = this.f21839a;
        int i12 = this.f21840b;
        iArr2[i12] = i10;
        this.f21840b = i12 + 1;
    }

    public final int c(int i10) {
        int i11;
        if (i10 < 0 || i10 >= (i11 = this.f21840b)) {
            StringBuilder sbS = a0.c.s(i10, "Index ", " must be in 0..");
            sbS.append(this.f21840b - 1);
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        int[] iArr = this.f21839a;
        int i12 = iArr[i10];
        if (i10 != i11 - 1) {
            kotlin.collections.r.K(i10, i10 + 1, i11, iArr, iArr);
        }
        this.f21840b--;
        return i12;
    }

    public final void d(int i10, int i11) {
        if (i10 < 0 || i10 >= this.f21840b) {
            StringBuilder sbS = a0.c.s(i10, "set index ", " must be between 0 .. ");
            sbS.append(this.f21840b - 1);
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        int[] iArr = this.f21839a;
        int i12 = iArr[i10];
        iArr[i10] = i11;
    }
}
