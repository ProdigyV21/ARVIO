package j$.util;

import j$.util.function.IntConsumer$CC;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class q0 implements v, IntConsumer, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18587a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h0 f18589c;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.a0
    public final void forEachRemaining(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        while (hasNext()) {
            intConsumer.accept(nextInt());
        }
    }

    @Override // java.util.Iterator
    public final Integer next() {
        if (d1.f18533a) {
            d1.a(q0.class, "{0} calling PrimitiveIterator.OfInt.nextInt()");
            throw null;
        }
        return Integer.valueOf(nextInt());
    }

    @Override // j$.util.v, java.util.Iterator, j$.util.j
    public final void forEachRemaining(Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            forEachRemaining((IntConsumer) consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        if (d1.f18533a) {
            d1.a(q0.class, "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        forEachRemaining((IntConsumer) new s(consumer, 0));
    }

    public q0(h0 h0Var) {
        this.f18589c = h0Var;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        this.f18587a = true;
        this.f18588b = i10;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.f18587a) {
            this.f18589c.tryAdvance((IntConsumer) this);
        }
        return this.f18587a;
    }

    @Override // j$.util.v
    public final int nextInt() {
        if (!this.f18587a && !hasNext()) {
            throw new NoSuchElementException();
        }
        this.f18587a = false;
        return this.f18588b;
    }
}
