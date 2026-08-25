package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class w2 extends z2 implements c2 {
    @Override // j$.util.stream.g2
    public final /* synthetic */ void forEach(Consumer consumer) {
        v3.Q(this, consumer);
    }

    @Override // j$.util.stream.z2, j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.T(this, j10, j11);
    }

    @Override // j$.util.stream.z2, j$.util.stream.g2
    public final /* bridge */ /* synthetic */ g2 a(int i10) {
        a(i10);
        throw null;
    }

    @Override // j$.util.stream.z2, j$.util.stream.g2
    public final f2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ void k(Object[] objArr, int i10) {
        v3.N(this, (Integer[]) objArr, i10);
    }

    @Override // j$.util.stream.f2
    public final /* bridge */ /* synthetic */ Object b() {
        return v3.f18922e;
    }

    @Override // j$.util.stream.g2
    public final /* bridge */ /* synthetic */ Spliterator spliterator() {
        return Spliterators.f18436b;
    }

    @Override // j$.util.stream.g2
    public final /* bridge */ /* synthetic */ j$.util.n0 spliterator() {
        return Spliterators.f18436b;
    }
}
