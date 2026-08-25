package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class y extends e0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21880f;

    public y(int i10) {
        this.f21804a = i0.f21833a;
        this.f21805b = u.a.f22010c;
        this.f21806c = n.f21855a;
        if (i10 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        d(i0.d(i10));
    }

    public final int b(int i10) {
        int i11 = this.f21807d;
        int i12 = i10 & i11;
        int i13 = 0;
        while (true) {
            long[] jArr = this.f21804a;
            int i14 = i12 >> 3;
            int i15 = (i12 & 7) << 3;
            long j10 = ((jArr[i14 + 1] << (64 - i15)) & ((-i15) >> 63)) | (jArr[i14] >>> i15);
            long j11 = j10 & ((~j10) << 7) & (-9187201950435737472L);
            if (j11 != 0) {
                return (i12 + (Long.numberOfTrailingZeros(j11) >> 3)) & i11;
            }
            i13 += 8;
            i12 = (i12 + i13) & i11;
        }
    }

    public final int c(Object obj) {
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 >>> 7;
        int i12 = i10 & 127;
        int i13 = this.f21807d;
        int i14 = i11 & i13;
        int i15 = 0;
        while (true) {
            long[] jArr = this.f21804a;
            int i16 = i14 >> 3;
            int i17 = (i14 & 7) << 3;
            long j10 = ((jArr[i16 + 1] << (64 - i17)) & ((-i17) >> 63)) | (jArr[i16] >>> i17);
            long j11 = i12;
            int i18 = i12;
            long j12 = j10 ^ (j11 * 72340172838076673L);
            for (long j13 = (~j12) & (j12 - 72340172838076673L) & (-9187201950435737472L); j13 != 0; j13 &= j13 - 1) {
                int iNumberOfTrailingZeros = (i14 + (Long.numberOfTrailingZeros(j13) >> 3)) & i13;
                if (kotlin.jvm.internal.p.a(this.f21805b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((((~j10) << 6) & j10 & (-9187201950435737472L)) != 0) {
                int iB = b(i11);
                if (this.f21880f == 0 && ((this.f21804a[iB >> 3] >> ((iB & 7) << 3)) & 255) != 254) {
                    int i19 = this.f21807d;
                    if (i19 <= 8 || Long.compare((((long) this.f21808e) * 32) ^ Long.MIN_VALUE, (((long) i19) * 25) ^ Long.MIN_VALUE) > 0) {
                        f(i0.b(this.f21807d));
                    } else {
                        f(i0.b(this.f21807d));
                    }
                    iB = b(i11);
                }
                this.f21808e++;
                int i20 = this.f21880f;
                long[] jArr2 = this.f21804a;
                int i21 = iB >> 3;
                long j14 = jArr2[i21];
                int i22 = (iB & 7) << 3;
                this.f21880f = i20 - (((j14 >> i22) & 255) == 128 ? 1 : 0);
                jArr2[i21] = (j14 & (~(255 << i22))) | (j11 << i22);
                int i23 = this.f21807d;
                int i24 = ((iB - 7) & i23) + (i23 & 7);
                int i25 = i24 >> 3;
                int i26 = (i24 & 7) << 3;
                jArr2[i25] = ((~(255 << i26)) & jArr2[i25]) | (j11 << i26);
                return ~iB;
            }
            i15 += 8;
            i14 = (i14 + i15) & i13;
            i12 = i18;
        }
    }

    public final void d(int i10) {
        long[] jArr;
        int iMax = i10 > 0 ? Math.max(7, i0.c(i10)) : 0;
        this.f21807d = iMax;
        if (iMax == 0) {
            jArr = i0.f21833a;
        } else {
            int i11 = ((iMax + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i11];
            Arrays.fill(jArr2, 0, i11, -9187201950435737472L);
            jArr = jArr2;
        }
        this.f21804a = jArr;
        int i12 = iMax >> 3;
        long j10 = 255 << ((iMax & 7) << 3);
        jArr[i12] = (jArr[i12] & (~j10)) | j10;
        this.f21880f = i0.a(this.f21807d) - this.f21808e;
        this.f21805b = new Object[iMax];
        this.f21806c = new int[iMax];
    }

    public final void e(int i10) {
        this.f21808e--;
        long[] jArr = this.f21804a;
        int i11 = i10 >> 3;
        int i12 = (i10 & 7) << 3;
        jArr[i11] = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        int i13 = this.f21807d;
        int i14 = ((i10 - 7) & i13) + (i13 & 7);
        int i15 = i14 >> 3;
        int i16 = (i14 & 7) << 3;
        jArr[i15] = (jArr[i15] & (~(255 << i16))) | (254 << i16);
        this.f21805b[i10] = null;
    }

    public final void f(int i10) {
        int i11;
        long[] jArr = this.f21804a;
        Object[] objArr = this.f21805b;
        int[] iArr = this.f21806c;
        int i12 = this.f21807d;
        d(i10);
        Object[] objArr2 = this.f21805b;
        int[] iArr2 = this.f21806c;
        int i13 = 0;
        while (i13 < i12) {
            if (((jArr[i13 >> 3] >> ((i13 & 7) << 3)) & 255) < 128) {
                Object obj = objArr[i13];
                int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
                int i14 = iHashCode ^ (iHashCode << 16);
                int iB = b(i14 >>> 7);
                long j10 = i14 & 127;
                long[] jArr2 = this.f21804a;
                int i15 = iB >> 3;
                int i16 = (iB & 7) << 3;
                i11 = i13;
                jArr2[i15] = (jArr2[i15] & (~(255 << i16))) | (j10 << i16);
                int i17 = this.f21807d;
                int i18 = ((iB - 7) & i17) + (i17 & 7);
                int i19 = i18 >> 3;
                int i20 = (i18 & 7) << 3;
                jArr2[i19] = (jArr2[i19] & (~(255 << i20))) | (j10 << i20);
                objArr2[iB] = obj;
                iArr2[iB] = iArr[i11];
            } else {
                i11 = i13;
            }
            i13 = i11 + 1;
        }
    }

    public /* synthetic */ y() {
        this(6);
    }
}
