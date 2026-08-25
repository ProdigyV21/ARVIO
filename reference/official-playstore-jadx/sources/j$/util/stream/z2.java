package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z2 implements g2 {
    @Override // j$.util.stream.g2
    public final long count() {
        return 0L;
    }

    public final void e(Object obj, int i10) {
    }

    public final void g(Object obj) {
    }

    @Override // j$.util.stream.g2
    public /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.V(this, j10, j11, intFunction);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.g2
    public g2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final Object[] m(IntFunction intFunction) {
        return (Object[]) intFunction.apply(0);
    }
}
