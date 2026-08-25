package v7;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f22177i = new c();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f22178l;

    static {
        Integer num = k7.a.f19521a;
        f22178l = (num == null || num.intValue() >= 34) ? new w7.a() : new b();
    }

    public abstract int a(int i10);

    public void b(byte[] bArr) {
        c(bArr.length, bArr);
    }

    public byte[] c(int i10, byte[] bArr) {
        if (bArr.length < 0 || i10 < 0 || i10 > bArr.length) {
            throw new IllegalArgumentException(a0.c.o(a0.c.s(i10, "fromIndex (0) or toIndex (", ") are out of range: 0.."), bArr.length, '.').toString());
        }
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "fromIndex (0) must be not greater than toIndex (", ").").toString());
        }
        int i11 = i10 / 4;
        int i12 = 0;
        for (int i13 = 0; i13 < i11; i13++) {
            int iD = d();
            bArr[i12] = (byte) iD;
            bArr[i12 + 1] = (byte) (iD >>> 8);
            bArr[i12 + 2] = (byte) (iD >>> 16);
            bArr[i12 + 3] = (byte) (iD >>> 24);
            i12 += 4;
        }
        int i14 = i10 - i12;
        int iA = a(i14 * 8);
        for (int i15 = 0; i15 < i14; i15++) {
            bArr[i12 + i15] = (byte) (iA >>> (i15 * 8));
        }
        return bArr;
    }

    public int d() {
        return a(32);
    }

    public int e(int i10, int i11) {
        int iD;
        int i12;
        int iA;
        if (i11 <= i10) {
            throw new IllegalArgumentException(("Random range is empty: [" + Integer.valueOf(i10) + ", " + Integer.valueOf(i11) + ").").toString());
        }
        int i13 = i11 - i10;
        if (i13 > 0 || i13 == Integer.MIN_VALUE) {
            if (((-i13) & i13) == i13) {
                iA = a(31 - Integer.numberOfLeadingZeros(i13));
            } else {
                do {
                    iD = d() >>> 1;
                    i12 = iD % i13;
                } while ((i13 - 1) + (iD - i12) < 0);
                iA = i12;
            }
            return i10 + iA;
        }
        while (true) {
            int iD2 = d();
            if (i10 <= iD2 && iD2 < i11) {
                return iD2;
            }
        }
    }

    public long f() {
        return (((long) d()) << 32) + ((long) d());
    }

    public long g(long j10) {
        return h(j10);
    }

    public long h(long j10) {
        long jF;
        long j11;
        if (j10 <= 0) {
            throw new IllegalArgumentException(("Random range is empty: [" + ((Object) 0L) + ", " + Long.valueOf(j10) + ").").toString());
        }
        if (j10 > 0) {
            if (((-j10) & j10) == j10) {
                int i10 = (int) j10;
                int i11 = (int) (j10 >>> 32);
                return i10 != 0 ? ((long) a(31 - Integer.numberOfLeadingZeros(i10))) & 4294967295L : i11 == 1 ? ((long) d()) & 4294967295L : (((long) a(31 - Integer.numberOfLeadingZeros(i11))) << 32) + (((long) d()) & 4294967295L);
            }
            do {
                jF = f() >>> 1;
                j11 = jF % j10;
            } while ((j10 - 1) + (jF - j11) < 0);
            return j11;
        }
        while (true) {
            long jF2 = f();
            if (0 <= jF2 && jF2 < j10) {
                return jF2;
            }
        }
    }
}
