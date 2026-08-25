package t;

import java.util.Arrays;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f21863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f21864b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f21865c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f21866d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f21867e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21868f;

    public final int a(int i10) {
        int i11 = this.f21866d;
        int i12 = i10 & i11;
        int i13 = 0;
        while (true) {
            long[] jArr = this.f21863a;
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

    public final int b(int i10) {
        int i11 = (-862048943) * i10;
        int i12 = i11 ^ (i11 << 16);
        int i13 = i12 & 127;
        int i14 = this.f21866d;
        int i15 = (i12 >>> 7) & i14;
        int i16 = 0;
        while (true) {
            long[] jArr = this.f21863a;
            int i17 = i15 >> 3;
            int i18 = (i15 & 7) << 3;
            long j10 = ((jArr[i17 + 1] << (64 - i18)) & ((-i18) >> 63)) | (jArr[i17] >>> i18);
            long j11 = (((long) i13) * 72340172838076673L) ^ j10;
            for (long j12 = (~j11) & (j11 - 72340172838076673L) & (-9187201950435737472L); j12 != 0; j12 &= j12 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j12) >> 3) + i15) & i14;
                if (this.f21864b[iNumberOfTrailingZeros] == i10) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j10 & ((~j10) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i16 += 8;
            i15 = (i15 + i16) & i14;
        }
    }

    public final int c(int i10) {
        int iB = b(i10);
        if (iB >= 0) {
            return this.f21865c[iB];
        }
        throw new NoSuchElementException(a0.c.i(i10, "Cannot find value for key "));
    }

    public final void d(int i10) {
        long[] jArr;
        int iMax = i10 > 0 ? Math.max(7, i0.c(i10)) : 0;
        this.f21866d = iMax;
        if (iMax == 0) {
            jArr = i0.f21833a;
        } else {
            int i11 = ((iMax + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i11];
            Arrays.fill(jArr2, 0, i11, -9187201950435737472L);
            jArr = jArr2;
        }
        this.f21863a = jArr;
        int i12 = iMax >> 3;
        long j10 = 255 << ((iMax & 7) << 3);
        jArr[i12] = (jArr[i12] & (~j10)) | j10;
        this.f21868f = i0.a(this.f21866d) - this.f21867e;
        this.f21864b = new int[iMax];
        this.f21865c = new int[iMax];
    }

    public final void e(int i10) {
        long[] jArr;
        t tVar = this;
        long[] jArr2 = tVar.f21863a;
        int[] iArr = tVar.f21864b;
        int[] iArr2 = tVar.f21865c;
        int i11 = tVar.f21866d;
        d(i10);
        int[] iArr3 = tVar.f21864b;
        int[] iArr4 = tVar.f21865c;
        int i12 = 0;
        while (i12 < i11) {
            if (((jArr2[i12 >> 3] >> ((i12 & 7) << 3)) & 255) < 128) {
                int i13 = iArr[i12];
                int i14 = (-862048943) * i13;
                int i15 = i14 ^ (i14 << 16);
                int iA = tVar.a(i15 >>> 7);
                long j10 = i15 & 127;
                long[] jArr3 = tVar.f21863a;
                int i16 = iA >> 3;
                int i17 = (iA & 7) << 3;
                jArr3[i16] = (jArr3[i16] & (~(255 << i17))) | (j10 << i17);
                int i18 = tVar.f21866d;
                int i19 = ((iA - 7) & i18) + (i18 & 7);
                int i20 = i19 >> 3;
                int i21 = (i19 & 7) << 3;
                jArr = jArr2;
                jArr3[i20] = ((~(255 << i21)) & jArr3[i20]) | (j10 << i21);
                iArr3[iA] = i13;
                iArr4[iA] = iArr2[i12];
            } else {
                jArr = jArr2;
            }
            i12++;
            tVar = this;
            jArr2 = jArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 1
            if (r1 != r0) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof t.t
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            t.t r1 = (t.t) r1
            int r3 = r1.f21867e
            int r5 = r0.f21867e
            if (r3 == r5) goto L17
            return r4
        L17:
            int[] r3 = r0.f21864b
            int[] r5 = r0.f21865c
            long[] r6 = r0.f21863a
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L61
            r8 = r4
        L23:
            r9 = r6[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L5c
            int r11 = r8 - r7
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r4
        L3d:
            if (r13 >= r11) goto L5a
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L56
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r3[r14]
            r14 = r5[r14]
            int r15 = r1.c(r15)
            if (r14 == r15) goto L56
            return r4
        L56:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3d
        L5a:
            if (r11 != r12) goto L61
        L5c:
            if (r8 == r7) goto L61
            int r8 = r8 + 1
            goto L23
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: t.t.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int[] iArr = this.f21864b;
        int[] iArr2 = this.f21865c;
        long[] jArr = this.f21863a;
        int length = jArr.length - 2;
        if (length < 0) {
            return 0;
        }
        int i10 = 0;
        int i11 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i12 = 8 - ((~(i10 - length)) >>> 31);
                for (int i13 = 0; i13 < i12; i13++) {
                    if ((255 & j10) < 128) {
                        int i14 = (i10 << 3) + i13;
                        i11 += iArr2[i14] ^ iArr[i14];
                    }
                    j10 >>= 8;
                }
                if (i12 != 8) {
                    return i11;
                }
            }
            if (i10 == length) {
                return i11;
            }
            i10++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068 A[PHI: r8
      0x0068: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x002e, B:19:0x0066] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.f21867e
            if (r1 != 0) goto La
            java.lang.String r1 = "{}"
            return r1
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "{"
            r1.<init>(r2)
            int[] r2 = r0.f21864b
            int[] r3 = r0.f21865c
            long[] r4 = r0.f21863a
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L6d
            r6 = 0
            r7 = r6
            r8 = r7
        L20:
            r9 = r4[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L68
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L3a:
            if (r13 >= r11) goto L66
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L62
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0.f21867e
            if (r8 >= r14) goto L62
            java.lang.String r14 = ", "
            r1.append(r14)
        L62:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3a
        L66:
            if (r11 != r12) goto L6d
        L68:
            if (r7 == r5) goto L6d
            int r7 = r7 + 1
            goto L20
        L6d:
            r2 = 125(0x7d, float:1.75E-43)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: t.t.toString():java.lang.String");
    }
}
