package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class v extends l {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21869f;

    public v(int i10) {
        this.f21842a = i0.f21833a;
        this.f21843b = n.f21855a;
        this.f21844c = u.a.f22010c;
        if (i10 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        d(i0.d(i10));
    }

    public final int c(int i10) {
        int i11 = this.f21845d;
        int i12 = i10 & i11;
        int i13 = 0;
        while (true) {
            long[] jArr = this.f21842a;
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

    public final void d(int i10) {
        long[] jArr;
        int iMax = i10 > 0 ? Math.max(7, i0.c(i10)) : 0;
        this.f21845d = iMax;
        if (iMax == 0) {
            jArr = i0.f21833a;
        } else {
            int i11 = ((iMax + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i11];
            Arrays.fill(jArr2, 0, i11, -9187201950435737472L);
            jArr = jArr2;
        }
        this.f21842a = jArr;
        int i12 = iMax >> 3;
        long j10 = 255 << ((iMax & 7) << 3);
        jArr[i12] = (jArr[i12] & (~j10)) | j10;
        this.f21869f = i0.a(this.f21845d) - this.f21846e;
        this.f21843b = new int[iMax];
        this.f21844c = new Object[iMax];
    }

    public final void e(int i10) {
        long[] jArr;
        v vVar = this;
        long[] jArr2 = vVar.f21842a;
        int[] iArr = vVar.f21843b;
        Object[] objArr = vVar.f21844c;
        int i11 = vVar.f21845d;
        d(i10);
        int[] iArr2 = vVar.f21843b;
        Object[] objArr2 = vVar.f21844c;
        int i12 = 0;
        while (i12 < i11) {
            if (((jArr2[i12 >> 3] >> ((i12 & 7) << 3)) & 255) < 128) {
                int i13 = iArr[i12];
                int i14 = (-862048943) * i13;
                int i15 = i14 ^ (i14 << 16);
                int iC = vVar.c(i15 >>> 7);
                long j10 = i15 & 127;
                long[] jArr3 = vVar.f21842a;
                int i16 = iC >> 3;
                int i17 = (iC & 7) << 3;
                jArr3[i16] = (jArr3[i16] & (~(255 << i17))) | (j10 << i17);
                int i18 = vVar.f21845d;
                int i19 = ((iC - 7) & i18) + (i18 & 7);
                int i20 = i19 >> 3;
                int i21 = (i19 & 7) << 3;
                jArr = jArr2;
                jArr3[i20] = ((~(255 << i21)) & jArr3[i20]) | (j10 << i21);
                iArr2[iC] = i13;
                objArr2[iC] = objArr[i12];
            } else {
                jArr = jArr2;
            }
            i12++;
            vVar = this;
            jArr2 = jArr;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x006c, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006e, code lost:
    
        r2 = c(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0076, code lost:
    
        if (r20.f21869f != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0088, code lost:
    
        if (((r20.f21842a[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x008b, code lost:
    
        r2 = r20.f21845d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008d, code lost:
    
        if (r2 <= 8) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a4, code lost:
    
        if (java.lang.Long.compare((((long) r20.f21846e) * 32) ^ Long.MIN_VALUE, (((long) r2) * 25) ^ Long.MIN_VALUE) > 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a6, code lost:
    
        e(t.i0.b(r20.f21845d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b0, code lost:
    
        e(t.i0.b(r20.f21845d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b9, code lost:
    
        r2 = c(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bd, code lost:
    
        r16 = r2;
        r20.f21846e++;
        r2 = r20.f21869f;
        r3 = r20.f21842a;
        r4 = r16 >> 3;
        r7 = r3[r4];
        r9 = (r16 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d7, code lost:
    
        if (((r7 >> r9) & 255) != 128) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00da, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00db, code lost:
    
        r20.f21869f = r2 - r13;
        r3[r4] = (r7 & (~(255 << r9))) | (r10 << r9);
        r2 = r20.f21845d;
        r4 = ((r16 - 7) & r2) + (r2 & 7);
        r2 = r4 >> 3;
        r4 = (r4 & 7) << 3;
        r3[r2] = ((~(255 << r4)) & r3[r2]) | (r10 << r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(int r21, java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.v.f(int, java.lang.Object):void");
    }

    public /* synthetic */ v() {
        this(6);
    }
}
