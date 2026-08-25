package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class x0 extends a1 {
    @Override // j$.util.stream.a, j$.util.stream.g
    public final IntStream sequential() {
        this.f18603h.f18612r = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final IntStream parallel() {
        this.f18603h.f18612r = true;
        return this;
    }

    @Override // j$.util.stream.a1, j$.util.stream.IntStream
    public final void forEach(IntConsumer intConsumer) {
        if (this.f18603h.f18612r) {
            super.forEach(intConsumer);
        } else {
            a1.T0(R0()).forEachRemaining(intConsumer);
        }
    }

    @Override // j$.util.stream.a1, j$.util.stream.IntStream
    public final void forEachOrdered(IntConsumer intConsumer) {
        if (this.f18603h.f18612r) {
            super.forEachOrdered(intConsumer);
        } else {
            a1.T0(R0()).forEachRemaining(intConsumer);
        }
    }

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new t(this, y6.f18974r, 1);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final /* bridge */ /* synthetic */ Spliterator spliterator() {
        return spliterator();
    }

    @Override // j$.util.stream.a
    public final boolean O0() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        throw new UnsupportedOperationException();
    }
}
