package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class x extends a0 {
    @Override // j$.util.stream.a, j$.util.stream.g
    public final d0 sequential() {
        this.f18603h.f18612r = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final d0 parallel() {
        this.f18603h.f18612r = true;
        return this;
    }

    @Override // j$.util.stream.a0, j$.util.stream.d0
    public final void forEach(DoubleConsumer doubleConsumer) {
        if (this.f18603h.f18612r) {
            super.forEach(doubleConsumer);
        } else {
            a0.T0(R0()).forEachRemaining(doubleConsumer);
        }
    }

    @Override // j$.util.stream.a0, j$.util.stream.d0
    public final void forEachOrdered(DoubleConsumer doubleConsumer) {
        if (this.f18603h.f18612r) {
            super.forEachOrdered(doubleConsumer);
        } else {
            a0.T0(R0()).forEachRemaining(doubleConsumer);
        }
    }

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new w(this, y6.f18974r, 0);
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
