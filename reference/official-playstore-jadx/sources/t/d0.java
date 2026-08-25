package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends j0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f21800e;

    public d0(int i10) {
        this.f21835a = i0.f21833a;
        this.f21836b = u.a.f22010c;
        if (i10 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        h(i0.d(i10));
    }

    public final boolean d(Object obj) {
        int i10 = this.f21838d;
        this.f21836b[f(obj)] = obj;
        return this.f21838d != i10;
    }

    public final void e() {
        this.f21838d = 0;
        long[] jArr = this.f21835a;
        if (jArr != i0.f21833a) {
            kotlin.collections.r.V(jArr);
            long[] jArr2 = this.f21835a;
            int i10 = this.f21837c;
            int i11 = i10 >> 3;
            long j10 = 255 << ((i10 & 7) << 3);
            jArr2[i11] = (jArr2[i11] & (~j10)) | j10;
        }
        Arrays.fill(this.f21836b, 0, this.f21837c, (Object) null);
        this.f21800e = i0.a(this.f21837c) - this.f21838d;
    }

    public final int f(Object obj) {
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i10 = iHashCode ^ (iHashCode << 16);
        int i11 = i10 >>> 7;
        int i12 = i10 & 127;
        int i13 = this.f21837c;
        int i14 = i11 & i13;
        int i15 = 0;
        while (true) {
            long[] jArr = this.f21835a;
            int i16 = i14 >> 3;
            int i17 = (i14 & 7) << 3;
            long j10 = ((jArr[i16 + 1] << (64 - i17)) & ((-i17) >> 63)) | (jArr[i16] >>> i17);
            long j11 = i12;
            int i18 = i12;
            long j12 = j10 ^ (j11 * 72340172838076673L);
            for (long j13 = (~j12) & (j12 - 72340172838076673L) & (-9187201950435737472L); j13 != 0; j13 &= j13 - 1) {
                int iNumberOfTrailingZeros = (i14 + (Long.numberOfTrailingZeros(j13) >> 3)) & i13;
                if (kotlin.jvm.internal.p.a(this.f21836b[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((((~j10) << 6) & j10 & (-9187201950435737472L)) != 0) {
                int iG = g(i11);
                if (this.f21800e == 0 && ((this.f21835a[iG >> 3] >> ((iG & 7) << 3)) & 255) != 254) {
                    int i19 = this.f21837c;
                    if (i19 <= 8 || Long.compare((((long) this.f21838d) * 32) ^ Long.MIN_VALUE, (((long) i19) * 25) ^ Long.MIN_VALUE) > 0) {
                        l(i0.b(this.f21837c));
                    } else {
                        l(i0.b(this.f21837c));
                    }
                    iG = g(i11);
                }
                this.f21838d++;
                int i20 = this.f21800e;
                long[] jArr2 = this.f21835a;
                int i21 = iG >> 3;
                long j14 = jArr2[i21];
                int i22 = (iG & 7) << 3;
                this.f21800e = i20 - (((j14 >> i22) & 255) == 128 ? 1 : 0);
                jArr2[i21] = (j14 & (~(255 << i22))) | (j11 << i22);
                int i23 = this.f21837c;
                int i24 = ((iG - 7) & i23) + (i23 & 7);
                int i25 = i24 >> 3;
                int i26 = (i24 & 7) << 3;
                jArr2[i25] = ((~(255 << i26)) & jArr2[i25]) | (j11 << i26);
                return iG;
            }
            i15 += 8;
            i14 = (i14 + i15) & i13;
            i12 = i18;
        }
    }

    public final int g(int i10) {
        int i11 = this.f21837c;
        int i12 = i10 & i11;
        int i13 = 0;
        while (true) {
            long[] jArr = this.f21835a;
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

    public final void h(int i10) {
        long[] jArr;
        int iMax = i10 > 0 ? Math.max(7, i0.c(i10)) : 0;
        this.f21837c = iMax;
        if (iMax == 0) {
            jArr = i0.f21833a;
        } else {
            int i11 = ((iMax + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i11];
            Arrays.fill(jArr2, 0, i11, -9187201950435737472L);
            jArr = jArr2;
        }
        this.f21835a = jArr;
        int i12 = iMax >> 3;
        long j10 = 255 << ((iMax & 7) << 3);
        jArr[i12] = (jArr[i12] & (~j10)) | j10;
        this.f21800e = i0.a(this.f21837c) - this.f21838d;
        this.f21836b = new Object[iMax];
    }

    public final void i(j0 j0Var) {
        Object[] objArr = j0Var.f21836b;
        long[] jArr = j0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        Object obj = objArr[(i10 << 3) + i12];
                        this.f21836b[f(obj)] = obj;
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006e, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0070, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean j(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r1.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0.f21837c
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.f21835a
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r13 = r7[r8]
            int r7 = 64 - r9
            long r7 = r13 << r7
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L48:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L67
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.f21836b
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.p.a(r15, r1)
            if (r15 == 0) goto L61
            goto L71
        L61:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L48
        L67:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L7a
            r11 = -1
        L71:
            if (r11 < 0) goto L74
            r2 = r12
        L74:
            if (r2 == 0) goto L79
            r0.k(r11)
        L79:
            return r2
        L7a:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: t.d0.j(java.lang.Object):boolean");
    }

    public final void k(int i10) {
        this.f21838d--;
        long[] jArr = this.f21835a;
        int i11 = i10 >> 3;
        int i12 = (i10 & 7) << 3;
        jArr[i11] = (jArr[i11] & (~(255 << i12))) | (254 << i12);
        int i13 = this.f21837c;
        int i14 = ((i10 - 7) & i13) + (i13 & 7);
        int i15 = i14 >> 3;
        int i16 = (i14 & 7) << 3;
        jArr[i15] = (jArr[i15] & (~(255 << i16))) | (254 << i16);
        this.f21836b[i10] = null;
    }

    public final void l(int i10) {
        int i11;
        long[] jArr = this.f21835a;
        Object[] objArr = this.f21836b;
        int i12 = this.f21837c;
        h(i10);
        Object[] objArr2 = this.f21836b;
        int i13 = 0;
        while (i13 < i12) {
            if (((jArr[i13 >> 3] >> ((i13 & 7) << 3)) & 255) < 128) {
                Object obj = objArr[i13];
                int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
                int i14 = iHashCode ^ (iHashCode << 16);
                int iG = g(i14 >>> 7);
                long j10 = i14 & 127;
                long[] jArr2 = this.f21835a;
                int i15 = iG >> 3;
                int i16 = (iG & 7) << 3;
                i11 = i13;
                jArr2[i15] = (jArr2[i15] & (~(255 << i16))) | (j10 << i16);
                int i17 = this.f21837c;
                int i18 = ((iG - 7) & i17) + (i17 & 7);
                int i19 = i18 >> 3;
                int i20 = (i18 & 7) << 3;
                jArr2[i19] = ((~(255 << i20)) & jArr2[i19]) | (j10 << i20);
                objArr2[iG] = obj;
            } else {
                i11 = i13;
            }
            i13 = i11 + 1;
        }
    }

    public /* synthetic */ d0() {
        this(6);
    }
}
