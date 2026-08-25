package j$.util;

import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public interface h0 extends n0 {
    void forEachRemaining(IntConsumer intConsumer);

    boolean tryAdvance(IntConsumer intConsumer);

    @Override // j$.util.n0, j$.util.Spliterator
    h0 trySplit();
}
