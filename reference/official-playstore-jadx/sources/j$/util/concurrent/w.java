package j$.util.concurrent;

import j$.util.e0;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class w implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f18517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f18519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f18520d;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 17728;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.i(this, consumer);
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
        return j$.com.android.tools.r8.a.z(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public w(long j10, long j11, double d4, double d10) {
        this.f18517a = j10;
        this.f18518b = j11;
        this.f18519c = d4;
        this.f18520d = d10;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final w trySplit() {
        long j10 = this.f18517a;
        long j11 = (this.f18518b + j10) >>> 1;
        if (j11 <= j10) {
            return null;
        }
        this.f18517a = j11;
        return new w(j10, j11, this.f18519c, this.f18520d);
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18518b - this.f18517a;
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        long j10 = this.f18517a;
        if (j10 >= this.f18518b) {
            return false;
        }
        doubleConsumer.accept(ThreadLocalRandom.current().a(this.f18519c, this.f18520d));
        this.f18517a = j10 + 1;
        return true;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        long j10 = this.f18517a;
        long j11 = this.f18518b;
        if (j10 < j11) {
            this.f18517a = j11;
            ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
            do {
                doubleConsumer.accept(threadLocalRandomCurrent.a(this.f18519c, this.f18520d));
                j10++;
            } while (j10 < j11);
        }
    }
}
