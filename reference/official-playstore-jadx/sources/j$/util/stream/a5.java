package j$.util.stream;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class a5 extends d5 {
    @Override // j$.util.stream.d5, j$.util.stream.Stream
    public final void forEach(Consumer consumer) {
        if (!this.f18603h.f18612r) {
            R0().forEachRemaining(consumer);
        } else {
            super.forEach(consumer);
        }
    }

    @Override // j$.util.stream.d5, j$.util.stream.Stream
    public final void forEachOrdered(Consumer consumer) {
        if (!this.f18603h.f18612r) {
            R0().forEachRemaining(consumer);
        } else {
            super.forEachOrdered(consumer);
        }
    }

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new z4(this, y6.f18974r);
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
