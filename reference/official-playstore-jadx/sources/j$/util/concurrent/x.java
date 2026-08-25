package j$.util.concurrent;

import j$.util.h0;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class x implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f18521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18524d;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 17728;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
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
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public x(long j10, long j11, int i10, int i11) {
        this.f18521a = j10;
        this.f18522b = j11;
        this.f18523c = i10;
        this.f18524d = i11;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final x trySplit() {
        long j10 = this.f18521a;
        long j11 = (this.f18522b + j10) >>> 1;
        if (j11 <= j10) {
            return null;
        }
        this.f18521a = j11;
        return new x(j10, j11, this.f18523c, this.f18524d);
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18522b - this.f18521a;
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(IntConsumer intConsumer) {
        intConsumer.getClass();
        long j10 = this.f18521a;
        if (j10 >= this.f18522b) {
            return false;
        }
        intConsumer.accept(ThreadLocalRandom.current().b(this.f18523c, this.f18524d));
        this.f18521a = j10 + 1;
        return true;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(IntConsumer intConsumer) {
        intConsumer.getClass();
        long j10 = this.f18521a;
        long j11 = this.f18522b;
        if (j10 < j11) {
            this.f18521a = j11;
            ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
            do {
                intConsumer.accept(threadLocalRandomCurrent.b(this.f18523c, this.f18524d));
                j10++;
            } while (j10 < j11);
        }
    }
}
