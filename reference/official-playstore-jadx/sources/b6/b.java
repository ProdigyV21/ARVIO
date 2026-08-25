package b6;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements Cloneable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7206i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7207l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f7209n;

    public final boolean a(int i10, int i11) {
        return ((this.f7209n[(i10 / 32) + (i11 * this.f7208m)] >>> (i10 & 31)) & 1) != 0;
    }

    public final Object clone() {
        int i10 = this.f7206i;
        int i11 = this.f7207l;
        int i12 = this.f7208m;
        int[] iArr = (int[]) this.f7209n.clone();
        b bVar = new b();
        bVar.f7206i = i10;
        bVar.f7207l = i11;
        bVar.f7208m = i12;
        bVar.f7209n = iArr;
        return bVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f7206i == bVar.f7206i && this.f7207l == bVar.f7207l && this.f7208m == bVar.f7208m && Arrays.equals(this.f7209n, bVar.f7209n);
    }

    public final int hashCode() {
        int i10 = this.f7206i;
        return Arrays.hashCode(this.f7209n) + (((((((i10 * 31) + i10) * 31) + this.f7207l) * 31) + this.f7208m) * 31);
    }

    public final String toString() {
        int i10 = this.f7207l;
        int i11 = this.f7206i;
        StringBuilder sb2 = new StringBuilder((i11 + 1) * i10);
        for (int i12 = 0; i12 < i10; i12++) {
            for (int i13 = 0; i13 < i11; i13++) {
                sb2.append(a(i13, i12) ? "X " : "  ");
            }
            sb2.append("\n");
        }
        return sb2.toString();
    }
}
