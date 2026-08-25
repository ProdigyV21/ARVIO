package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class o2 extends q2 implements c2 {
    @Override // j$.util.stream.g2
    public final /* synthetic */ void forEach(Consumer consumer) {
        v3.Q(this, consumer);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.T(this, j10, j11);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ void k(Object[] objArr, int i10) {
        v3.N(this, (Integer[]) objArr, i10);
    }

    @Override // j$.util.stream.f2
    public final Object newArray(int i10) {
        return new int[i10];
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        return new f3(this);
    }

    @Override // j$.util.stream.g2
    public final j$.util.n0 spliterator() {
        return new f3(this);
    }
}
