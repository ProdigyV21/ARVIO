package j$.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class s0 implements r, DoubleConsumer, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18595a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f18596b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e0 f18597c;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.a0
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        while (hasNext()) {
            doubleConsumer.accept(nextDouble());
        }
    }

    @Override // java.util.Iterator
    public final Double next() {
        if (d1.f18533a) {
            d1.a(s0.class, "{0} calling PrimitiveIterator.OfDouble.nextLong()");
            throw null;
        }
        return Double.valueOf(nextDouble());
    }

    @Override // j$.util.r, java.util.Iterator, j$.util.j
    public final void forEachRemaining(Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            forEachRemaining((DoubleConsumer) consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        if (d1.f18533a) {
            d1.a(s0.class, "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        forEachRemaining((DoubleConsumer) new o(consumer, 0));
    }

    public s0(e0 e0Var) {
        this.f18597c = e0Var;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d4) {
        this.f18595a = true;
        this.f18596b = d4;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.f18595a) {
            this.f18597c.tryAdvance((DoubleConsumer) this);
        }
        return this.f18595a;
    }

    @Override // j$.util.r
    public final double nextDouble() {
        if (!this.f18595a && !hasNext()) {
            throw new NoSuchElementException();
        }
        this.f18595a = false;
        return this.f18596b;
    }
}
