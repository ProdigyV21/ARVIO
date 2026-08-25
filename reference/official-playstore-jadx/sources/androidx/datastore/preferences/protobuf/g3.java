package androidx.datastore.preferences.protobuf;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class g3 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g3 f2439f = new g3(0, new int[0], new Object[0], false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f2441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f2442c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2443d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2444e;

    public g3(int i10, int[] iArr, Object[] objArr, boolean z) {
        this.f2440a = i10;
        this.f2441b = iArr;
        this.f2442c = objArr;
        this.f2444e = z;
    }

    public static g3 b() {
        return new g3(0, new int[8], new Object[8], true);
    }

    public final int a() {
        int iT;
        int iW;
        int iP;
        int i10 = this.f2443d;
        if (i10 != -1) {
            return i10;
        }
        int i11 = 0;
        for (int i12 = 0; i12 < this.f2440a; i12++) {
            int i13 = this.f2441b[i12];
            int i14 = i13 >>> 3;
            int i15 = i13 & 7;
            if (i15 != 0) {
                if (i15 == 1) {
                    ((Long) this.f2442c[i12]).getClass();
                    iP = u.p(i14);
                } else if (i15 == 2) {
                    iP = u.m(i14, (m) this.f2442c[i12]);
                } else if (i15 == 3) {
                    iT = u.t(i14) * 2;
                    iW = ((g3) this.f2442c[i12]).a();
                } else {
                    if (i15 != 5) {
                        throw new IllegalStateException(InvalidProtocolBufferException.b());
                    }
                    ((Integer) this.f2442c[i12]).getClass();
                    iP = u.o(i14);
                }
                i11 = iP + i11;
            } else {
                long jLongValue = ((Long) this.f2442c[i12]).longValue();
                iT = u.t(i14);
                iW = u.w(jLongValue);
            }
            i11 = iW + iT + i11;
        }
        this.f2443d = i11;
        return i11;
    }

    public final void c(int i10, Object obj) {
        if (!this.f2444e) {
            throw new UnsupportedOperationException();
        }
        int i11 = this.f2440a;
        int[] iArr = this.f2441b;
        if (i11 == iArr.length) {
            int i12 = i11 + (i11 < 4 ? 8 : i11 >> 1);
            this.f2441b = Arrays.copyOf(iArr, i12);
            this.f2442c = Arrays.copyOf(this.f2442c, i12);
        }
        int[] iArr2 = this.f2441b;
        int i13 = this.f2440a;
        iArr2[i13] = i10;
        this.f2442c[i13] = obj;
        this.f2440a = i13 + 1;
    }

    public final void d(a4 a4Var) {
        if (this.f2440a == 0) {
            return;
        }
        a4Var.getClass();
        for (int i10 = 0; i10 < this.f2440a; i10++) {
            int i11 = this.f2441b[i10];
            Object obj = this.f2442c[i10];
            int i12 = i11 >>> 3;
            int i13 = i11 & 7;
            if (i13 == 0) {
                a4Var.D(i12, ((Long) obj).longValue());
            } else if (i13 == 1) {
                a4Var.y(i12, ((Long) obj).longValue());
            } else if (i13 == 2) {
                a4Var.H(i12, (m) obj);
            } else if (i13 == 3) {
                a4Var.getClass();
                a4Var.G(i12);
                ((g3) obj).d(a4Var);
                a4Var.I(i12);
            } else {
                if (i13 != 5) {
                    throw new RuntimeException(InvalidProtocolBufferException.b());
                }
                a4Var.d(i12, ((Integer) obj).intValue());
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof g3)) {
            return false;
        }
        g3 g3Var = (g3) obj;
        int i10 = this.f2440a;
        if (i10 == g3Var.f2440a) {
            int[] iArr = this.f2441b;
            int[] iArr2 = g3Var.f2441b;
            int i11 = 0;
            while (true) {
                if (i11 >= i10) {
                    Object[] objArr = this.f2442c;
                    Object[] objArr2 = g3Var.f2442c;
                    int i12 = this.f2440a;
                    for (int i13 = 0; i13 < i12; i13++) {
                        if (objArr[i13].equals(objArr2[i13])) {
                        }
                    }
                    return true;
                }
                if (iArr[i11] != iArr2[i11]) {
                    break;
                }
                i11++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i10 = this.f2440a;
        int i11 = (527 + i10) * 31;
        int[] iArr = this.f2441b;
        int iHashCode = 17;
        int i12 = 17;
        for (int i13 = 0; i13 < i10; i13++) {
            i12 = (i12 * 31) + iArr[i13];
        }
        int i14 = (i11 + i12) * 31;
        Object[] objArr = this.f2442c;
        int i15 = this.f2440a;
        for (int i16 = 0; i16 < i15; i16++) {
            iHashCode = (iHashCode * 31) + objArr[i16].hashCode();
        }
        return i14 + iHashCode;
    }
}
