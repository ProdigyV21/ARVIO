package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z0 extends a1 {
    @Override // j$.util.stream.a
    public final boolean O0() {
        return false;
    }

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

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new t(this, y6.f18974r, 1);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final /* bridge */ /* synthetic */ Spliterator spliterator() {
        return spliterator();
    }
}
