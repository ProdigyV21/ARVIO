package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class o7 extends r7 implements j$.util.e0 {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.i(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.z(this, consumer);
    }

    @Override // j$.util.stream.t7
    public final Spliterator a(Spliterator spliterator, long j10, long j11, long j12, long j13) {
        return new o7((j$.util.e0) spliterator, j10, j11, j12, j13);
    }

    @Override // j$.util.stream.r7
    public final Object b() {
        return new z1(1);
    }
}
