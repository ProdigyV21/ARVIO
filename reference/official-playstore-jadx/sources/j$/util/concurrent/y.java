package j$.util.concurrent;

import j$.util.k0;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class y implements k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f18525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18526b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18527c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f18528d;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 17728;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.k(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.B(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public y(long j10, long j11, long j12, long j13) {
        this.f18525a = j10;
        this.f18526b = j11;
        this.f18527c = j12;
        this.f18528d = j13;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final y trySplit() {
        long j10 = this.f18525a;
        long j11 = (this.f18526b + j10) >>> 1;
        if (j11 <= j10) {
            return null;
        }
        this.f18525a = j11;
        return new y(j10, j11, this.f18527c, this.f18528d);
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18526b - this.f18525a;
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(LongConsumer longConsumer) {
        longConsumer.getClass();
        long j10 = this.f18525a;
        if (j10 >= this.f18526b) {
            return false;
        }
        longConsumer.accept(ThreadLocalRandom.current().c(this.f18527c, this.f18528d));
        this.f18525a = j10 + 1;
        return true;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(LongConsumer longConsumer) {
        longConsumer.getClass();
        long j10 = this.f18525a;
        long j11 = this.f18526b;
        if (j10 < j11) {
            this.f18525a = j11;
            ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
            do {
                longConsumer.accept(threadLocalRandomCurrent.c(this.f18527c, this.f18528d));
                j10++;
            } while (j10 < j11);
        }
    }
}
