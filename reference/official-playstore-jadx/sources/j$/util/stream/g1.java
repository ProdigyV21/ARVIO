package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class g1 extends j1 {
    @Override // j$.util.stream.a, j$.util.stream.g
    public final m1 sequential() {
        this.f18603h.f18612r = false;
        return this;
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final m1 parallel() {
        this.f18603h.f18612r = true;
        return this;
    }

    @Override // j$.util.stream.j1, j$.util.stream.m1
    public final void forEach(LongConsumer longConsumer) {
        if (this.f18603h.f18612r) {
            super.forEach(longConsumer);
        } else {
            j1.T0(R0()).forEachRemaining(longConsumer);
        }
    }

    @Override // j$.util.stream.j1, j$.util.stream.m1
    public final void forEachOrdered(LongConsumer longConsumer) {
        if (this.f18603h.f18612r) {
            super.forEachOrdered(longConsumer);
        } else {
            j1.T0(R0()).forEachRemaining(longConsumer);
        }
    }

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new u(this, y6.f18974r, 4);
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
