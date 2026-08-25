package com.google.common.collect;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class f3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient Object[] f13998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient int[] f13999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient int f14000c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient int[] f14001d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public transient long[] f14002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient float f14003f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public transient int f14004g;

    public final void a(int i10) {
        if (i10 > this.f14002e.length) {
            f(i10);
        }
        if (i10 >= this.f14004g) {
            g(Math.max(2, Integer.highestOneBit(i10 - 1) << 1));
        }
    }

    public final int b(Object obj) {
        int iC = c(obj);
        if (iC == -1) {
            return 0;
        }
        return this.f13999b[iC];
    }

    public final int c(Object obj) {
        int iU = a0.u(obj);
        int i10 = this.f14001d[(r1.length - 1) & iU];
        while (i10 != -1) {
            long j10 = this.f14002e[i10];
            if (((int) (j10 >>> 32)) == iU && a.a.L(obj, this.f13998a[i10])) {
                return i10;
            }
            i10 = (int) j10;
        }
        return -1;
    }

    public final void d(int i10) {
        ac.b.k(i10 >= 0, "Initial capacity must be non-negative");
        int iMax = Math.max(i10, 2);
        int iHighestOneBit = Integer.highestOneBit(iMax);
        if (iMax > ((int) (((double) 1.0f) * ((double) iHighestOneBit))) && (iHighestOneBit = iHighestOneBit << 1) <= 0) {
            iHighestOneBit = 1073741824;
        }
        int[] iArr = new int[iHighestOneBit];
        Arrays.fill(iArr, -1);
        this.f14001d = iArr;
        this.f14003f = 1.0f;
        this.f13998a = new Object[i10];
        this.f13999b = new int[i10];
        long[] jArr = new long[i10];
        Arrays.fill(jArr, -1L);
        this.f14002e = jArr;
        this.f14004g = Math.max(1, (int) (iHighestOneBit * 1.0f));
    }

    public final void e(int i10, Object obj) {
        long j10;
        if (i10 <= 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "count must be positive but was: "));
        }
        long[] jArr = this.f14002e;
        Object[] objArr = this.f13998a;
        int[] iArr = this.f13999b;
        int iU = a0.u(obj);
        int[] iArr2 = this.f14001d;
        int length = (iArr2.length - 1) & iU;
        int i11 = this.f14000c;
        int i12 = iArr2[length];
        if (i12 == -1) {
            iArr2[length] = i11;
            j10 = 4294967295L;
        } else {
            while (true) {
                long j11 = jArr[i12];
                j10 = 4294967295L;
                if (((int) (j11 >>> 32)) == iU && a.a.L(obj, objArr[i12])) {
                    int i13 = iArr[i12];
                    iArr[i12] = i10;
                    return;
                } else {
                    int i14 = (int) j11;
                    if (i14 == -1) {
                        jArr[i12] = ((-4294967296L) & j11) | (((long) i11) & 4294967295L);
                        break;
                    }
                    i12 = i14;
                }
            }
        }
        if (i11 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i15 = i11 + 1;
        int length2 = this.f14002e.length;
        if (i15 > length2) {
            int iMax = Math.max(1, length2 >>> 1) + length2;
            int i16 = iMax >= 0 ? iMax : Integer.MAX_VALUE;
            if (i16 != length2) {
                f(i16);
            }
        }
        this.f14002e[i11] = (((long) iU) << 32) | j10;
        this.f13998a[i11] = obj;
        this.f13999b[i11] = i10;
        this.f14000c = i15;
        if (i11 >= this.f14004g) {
            g(this.f14001d.length * 2);
        }
    }

    public final void f(int i10) {
        this.f13998a = Arrays.copyOf(this.f13998a, i10);
        this.f13999b = Arrays.copyOf(this.f13999b, i10);
        long[] jArr = this.f14002e;
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, i10);
        if (i10 > length) {
            Arrays.fill(jArrCopyOf, length, i10, -1L);
        }
        this.f14002e = jArrCopyOf;
    }

    public final void g(int i10) {
        if (this.f14001d.length >= 1073741824) {
            this.f14004g = Integer.MAX_VALUE;
            return;
        }
        int i11 = ((int) (i10 * this.f14003f)) + 1;
        int[] iArr = new int[i10];
        Arrays.fill(iArr, -1);
        long[] jArr = this.f14002e;
        int i12 = i10 - 1;
        for (int i13 = 0; i13 < this.f14000c; i13++) {
            int i14 = (int) (jArr[i13] >>> 32);
            int i15 = i14 & i12;
            int i16 = iArr[i15];
            iArr[i15] = i13;
            jArr[i13] = (((long) i14) << 32) | (((long) i16) & 4294967295L);
        }
        this.f14004g = i11;
        this.f14001d = iArr;
    }
}
