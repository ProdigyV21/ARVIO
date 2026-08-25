package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class n2 extends q2 implements a2 {
    @Override // j$.util.stream.g2
    public final /* synthetic */ void forEach(Consumer consumer) {
        v3.P(this, consumer);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.S(this, j10, j11);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ void k(Object[] objArr, int i10) {
        v3.M(this, (Double[]) objArr, i10);
    }

    @Override // j$.util.stream.f2
    public final Object newArray(int i10) {
        return new double[i10];
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        return new e3(this);
    }

    @Override // j$.util.stream.g2
    public final j$.util.n0 spliterator() {
        return new e3(this);
    }
}
