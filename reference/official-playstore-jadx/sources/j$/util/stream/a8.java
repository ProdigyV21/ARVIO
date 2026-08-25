package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Spliterator f18626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f18627b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18628c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f18629d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicLong f18630e;

    public abstract Spliterator b(Spliterator spliterator);

    public a8(Spliterator spliterator, long j10, long j11) {
        this.f18626a = spliterator;
        this.f18627b = j11 < 0;
        this.f18629d = j11 >= 0 ? j11 : 0L;
        this.f18628c = 128;
        this.f18630e = new AtomicLong(j11 >= 0 ? j10 + j11 : j10);
    }

    public a8(Spliterator spliterator, a8 a8Var) {
        this.f18626a = spliterator;
        this.f18627b = a8Var.f18627b;
        this.f18630e = a8Var.f18630e;
        this.f18629d = a8Var.f18629d;
        this.f18628c = a8Var.f18628c;
    }

    public final long a(long j10) {
        long j11;
        boolean z;
        long jMin;
        do {
            j11 = this.f18630e.get();
            z = this.f18627b;
            if (j11 != 0) {
                jMin = Math.min(j11, j10);
                if (jMin <= 0) {
                    break;
                }
            } else {
                if (z) {
                    return j10;
                }
                return 0L;
            }
        } while (!this.f18630e.compareAndSet(j11, j11 - jMin));
        if (z) {
            return Math.max(j10 - jMin, 0L);
        }
        long j12 = this.f18629d;
        return j11 > j12 ? Math.max(jMin - (j11 - j12), 0L) : jMin;
    }

    public final z7 e() {
        if (this.f18630e.get() > 0) {
            return z7.MAYBE_MORE;
        }
        return this.f18627b ? z7.UNLIMITED : z7.NO_MORE;
    }

    public final Spliterator trySplit() {
        Spliterator spliteratorTrySplit;
        if (this.f18630e.get() == 0 || (spliteratorTrySplit = this.f18626a.trySplit()) == null) {
            return null;
        }
        return b(spliteratorTrySplit);
    }

    public final long estimateSize() {
        return this.f18626a.estimateSize();
    }

    public final int characteristics() {
        return this.f18626a.characteristics() & (-16465);
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.n0 m7031trySplit() {
        return (j$.util.n0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.h0 m7029trySplit() {
        return (j$.util.h0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.k0 m7030trySplit() {
        return (j$.util.k0) trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ j$.util.e0 m7028trySplit() {
        return (j$.util.e0) trySplit();
    }
}
