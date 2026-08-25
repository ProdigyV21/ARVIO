package t;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f21828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21829b;

    public final float a(int i10) {
        if (i10 >= 0 && i10 < this.f21829b) {
            return this.f21828a[i10];
        }
        StringBuilder sbS = a0.c.s(i10, "Index ", " must be in 0..");
        sbS.append(this.f21829b - 1);
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            h hVar = (h) obj;
            int i10 = hVar.f21829b;
            int i11 = this.f21829b;
            if (i10 == i11) {
                float[] fArr = this.f21828a;
                float[] fArr2 = hVar.f21828a;
                x7.i iVarX = qb.d.X(0, i11);
                int i12 = iVarX.f22619i;
                int i13 = iVarX.f22620l;
                if (i12 > i13) {
                    return true;
                }
                while (fArr[i12] == fArr2[i12]) {
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
        float[] fArr = this.f21828a;
        int i10 = this.f21829b;
        int iFloatToIntBits = 0;
        for (int i11 = 0; i11 < i10; i11++) {
            iFloatToIntBits += Float.floatToIntBits(fArr[i11]) * 31;
        }
        return iFloatToIntBits;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "[");
        float[] fArr = this.f21828a;
        int i10 = this.f21829b;
        int i11 = 0;
        while (true) {
            if (i11 >= i10) {
                sb2.append((CharSequence) "]");
                break;
            }
            float f10 = fArr[i11];
            if (i11 == -1) {
                sb2.append((CharSequence) "...");
                break;
            }
            if (i11 != 0) {
                sb2.append((CharSequence) ", ");
            }
            sb2.append(f10);
            i11++;
        }
        return sb2.toString();
    }
}
