package io.sentry.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicLong f17969m = new AtomicLong(System.nanoTime());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f17970i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f17971l;

    public p() {
        long jA = a();
        long jA2 = (a() << 1) | 1;
        this.f17971l = jA2;
        this.f17970i = jA2 + jA;
    }

    public static long a() {
        AtomicLong atomicLong;
        long j10;
        long j11;
        do {
            atomicLong = f17969m;
            j10 = atomicLong.get();
            long j12 = (j10 >> 12) ^ j10;
            long j13 = j12 ^ (j12 << 25);
            j11 = (j13 ^ (j13 >> 27)) * 2685821657736338717L;
        } while (!atomicLong.compareAndSet(j10, j11));
        return j11;
    }

    public final void b(byte[] bArr) {
        for (int i10 = 0; i10 < bArr.length; i10++) {
            this.f17970i = (this.f17970i * 6364136223846793005L) + this.f17971l;
            bArr[i10] = (byte) ((((r1 >>> 22) ^ r1) >>> ((int) ((r1 >>> 61) + 22))) >>> 24);
        }
    }

    public final double c() {
        long j10 = this.f17970i * 6364136223846793005L;
        long j11 = this.f17971l;
        long j12 = j10 + j11;
        long j13 = (((j12 >>> 22) ^ j12) >>> ((int) ((j12 >>> 61) + 22))) & 4294967295L;
        this.f17970i = (j12 * 6364136223846793005L) + j11;
        return (((j13 >>> 6) << 27) + (((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) & 4294967295L) >>> 5)) / 9.007199254740992E15d;
    }
}
