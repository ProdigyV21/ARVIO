package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Spliterator f18892c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f18893d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f18894e;

    public abstract Spliterator a(Spliterator spliterator, long j10, long j11, long j12, long j13);

    public t7(Spliterator spliterator, long j10, long j11, long j12, long j13) {
        this.f18892c = spliterator;
        this.f18890a = j10;
        this.f18891b = j11;
        this.f18893d = j12;
        this.f18894e = j13;
    }

    public final Spliterator trySplit() {
        long j10 = this.f18894e;
        if (this.f18890a >= j10 || this.f18893d >= j10) {
            return null;
        }
        while (true) {
            Spliterator spliteratorTrySplit = this.f18892c.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            long jEstimateSize = spliteratorTrySplit.estimateSize() + this.f18893d;
            long jMin = Math.min(jEstimateSize, this.f18891b);
            long j11 = this.f18890a;
            if (j11 >= jMin) {
                this.f18893d = jMin;
            } else {
                long j12 = this.f18891b;
                if (jMin >= j12) {
                    this.f18892c = spliteratorTrySplit;
                    this.f18894e = jMin;
                } else {
                    long j13 = this.f18893d;
                    if (j13 >= j11 && jEstimateSize <= j12) {
                        this.f18893d = jMin;
                        return spliteratorTrySplit;
                    }
                    this.f18893d = jMin;
                    return a(spliteratorTrySplit, j11, j12, j13, jMin);
                }
            }
        }
    }

    public final long estimateSize() {
        long j10 = this.f18894e;
        long j11 = this.f18890a;
        if (j11 < j10) {
            return j10 - Math.max(j11, this.f18893d);
        }
        return 0L;
    }

    public final int characteristics() {
        return this.f18892c.characteristics();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.n0 m7035trySplit() {
        return (j$.util.n0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.h0 m7033trySplit() {
        return (j$.util.h0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.k0 m7034trySplit() {
        return (j$.util.k0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.e0 m7032trySplit() {
        return (j$.util.e0) trySplit();
    }
}
