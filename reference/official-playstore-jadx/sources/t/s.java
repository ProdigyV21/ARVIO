package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class s extends h {
    public final void b(float f10) {
        int i10 = this.f21829b + 1;
        float[] fArr = this.f21828a;
        if (fArr.length < i10) {
            this.f21828a = Arrays.copyOf(fArr, Math.max(i10, (fArr.length * 3) / 2));
        }
        float[] fArr2 = this.f21828a;
        int i11 = this.f21829b;
        fArr2[i11] = f10;
        this.f21829b = i11 + 1;
    }
}
