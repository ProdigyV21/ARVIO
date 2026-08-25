package b6;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements Cloneable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f7203m = new int[0];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7205l = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f7204i = f7203m;

    public final void a(boolean z) {
        c(this.f7205l + 1);
        if (z) {
            int[] iArr = this.f7204i;
            int i10 = this.f7205l;
            int i11 = i10 / 32;
            iArr[i11] = (1 << (i10 & 31)) | iArr[i11];
        }
        this.f7205l++;
    }

    public final void b(int i10, int i11) {
        if (i11 < 0 || i11 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        int i12 = this.f7205l;
        c(i12 + i11);
        for (int i13 = i11 - 1; i13 >= 0; i13--) {
            if (((1 << i13) & i10) != 0) {
                int[] iArr = this.f7204i;
                int i14 = i12 / 32;
                iArr[i14] = iArr[i14] | (1 << (i12 & 31));
            }
            i12++;
        }
        this.f7205l = i12;
    }

    public final void c(int i10) {
        if (i10 > this.f7204i.length * 32) {
            int[] iArr = new int[(((int) Math.ceil(i10 / 0.75f)) + 31) / 32];
            int[] iArr2 = this.f7204i;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            this.f7204i = iArr;
        }
    }

    public final Object clone() {
        int[] iArr = (int[]) this.f7204i.clone();
        int i10 = this.f7205l;
        a aVar = new a();
        aVar.f7204i = iArr;
        aVar.f7205l = i10;
        return aVar;
    }

    public final boolean d(int i10) {
        return ((1 << (i10 & 31)) & this.f7204i[i10 / 32]) != 0;
    }

    public final int e() {
        return (this.f7205l + 7) / 8;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f7205l == aVar.f7205l && Arrays.equals(this.f7204i, aVar.f7204i);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f7204i) + (this.f7205l * 31);
    }

    public final String toString() {
        int i10 = this.f7205l;
        StringBuilder sb2 = new StringBuilder((i10 / 8) + i10 + 1);
        for (int i11 = 0; i11 < this.f7205l; i11++) {
            if ((i11 & 7) == 0) {
                sb2.append(' ');
            }
            sb2.append(d(i11) ? 'X' : '.');
        }
        return sb2.toString();
    }
}
