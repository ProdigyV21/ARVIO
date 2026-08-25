package t;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f21813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f21815c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f21816d;

    public g() {
        int iHighestOneBit = Integer.bitCount(8) != 1 ? Integer.highestOneBit(7) << 1 : 8;
        this.f21816d = iHighestOneBit - 1;
        this.f21813a = new int[iHighestOneBit];
    }

    public final void a(int i10) {
        int[] iArr = this.f21813a;
        int i11 = this.f21815c;
        iArr[i11] = i10;
        int i12 = this.f21816d & (i11 + 1);
        this.f21815c = i12;
        int i13 = this.f21814b;
        if (i12 == i13) {
            int length = iArr.length;
            int i14 = length - i13;
            int i15 = length << 1;
            if (i15 < 0) {
                throw new RuntimeException("Max array capacity exceeded");
            }
            int[] iArr2 = new int[i15];
            kotlin.collections.r.K(0, i13, length, iArr, iArr2);
            kotlin.collections.r.K(i14, 0, this.f21814b, this.f21813a, iArr2);
            this.f21813a = iArr2;
            this.f21814b = 0;
            this.f21815c = length;
            this.f21816d = i15 - 1;
        }
    }

    public final int b(int i10) {
        if (i10 < 0 || i10 >= c()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.f21813a[this.f21816d & (this.f21814b + i10)];
    }

    public final int c() {
        return (this.f21815c - this.f21814b) & this.f21816d;
    }
}
