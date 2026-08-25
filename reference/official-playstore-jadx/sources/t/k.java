package t;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f21839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21840b;

    public final int a(int i10) {
        if (i10 >= 0 && i10 < this.f21840b) {
            return this.f21839a[i10];
        }
        StringBuilder sbS = a0.c.s(i10, "Index ", " must be in 0..");
        sbS.append(this.f21840b - 1);
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            k kVar = (k) obj;
            int i10 = kVar.f21840b;
            int i11 = this.f21840b;
            if (i10 == i11) {
                int[] iArr = this.f21839a;
                int[] iArr2 = kVar.f21839a;
                x7.i iVarX = qb.d.X(0, i11);
                int i12 = iVarX.f22619i;
                int i13 = iVarX.f22620l;
                if (i12 > i13) {
                    return true;
                }
                while (iArr[i12] == iArr2[i12]) {
                    if (i12 == i13) {
                        return true;
                    }
                    i12++;
                }
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.f21839a;
        int i10 = this.f21840b;
        int i11 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            i11 += iArr[i12] * 31;
        }
        return i11;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "[");
        int[] iArr = this.f21839a;
        int i10 = this.f21840b;
        int i11 = 0;
        while (true) {
            if (i11 >= i10) {
                sb2.append((CharSequence) "]");
                break;
            }
            int i12 = iArr[i11];
            if (i11 == -1) {
                sb2.append((CharSequence) "...");
                break;
            }
            if (i11 != 0) {
                sb2.append((CharSequence) ", ");
            }
            sb2.append(i12);
            i11++;
        }
        return sb2.toString();
    }
}
