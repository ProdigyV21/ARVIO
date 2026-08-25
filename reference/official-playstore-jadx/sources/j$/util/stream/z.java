package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z extends a0 {
    @Override // j$.util.stream.a
    public final boolean O0() {
        return false;
    }

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

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new w(this, y6.f18974r, 0);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final /* bridge */ /* synthetic */ Spliterator spliterator() {
        return spliterator();
    }
}
