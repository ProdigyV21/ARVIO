package j$.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class r0 implements z, LongConsumer, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18590a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18591b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ k0 f18592c;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.a0
    public final void forEachRemaining(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        while (hasNext()) {
            longConsumer.accept(nextLong());
        }
    }

    @Override // java.util.Iterator
    public final Long next() {
        if (d1.f18533a) {
            d1.a(r0.class, "{0} calling PrimitiveIterator.OfLong.nextLong()");
            throw null;
        }
        return Long.valueOf(nextLong());
    }

    @Override // j$.util.z, java.util.Iterator, j$.util.j
    public final void forEachRemaining(Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            forEachRemaining((LongConsumer) consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        if (d1.f18533a) {
            d1.a(r0.class, "{0} calling PrimitiveIterator.OfLong.forEachRemainingLong(action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        forEachRemaining((LongConsumer) new w(consumer, 0));
    }

    public r0(k0 k0Var) {
        this.f18592c = k0Var;
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        this.f18590a = true;
        this.f18591b = j10;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.f18590a) {
            this.f18592c.tryAdvance((LongConsumer) this);
        }
        return this.f18590a;
    }

    @Override // j$.util.z
    public final long nextLong() {
        if (!this.f18590a && !hasNext()) {
            throw new NoSuchElementException();
        }
        this.f18590a = false;
        return this.f18591b;
    }
}
