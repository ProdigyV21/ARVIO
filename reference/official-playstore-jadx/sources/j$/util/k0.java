package j$.util;

import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public interface k0 extends n0 {
    void forEachRemaining(LongConsumer longConsumer);

    boolean tryAdvance(LongConsumer longConsumer);

    @Override // j$.util.n0, j$.util.Spliterator
    k0 trySplit();
}
