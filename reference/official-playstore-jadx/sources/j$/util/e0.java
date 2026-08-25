package j$.util;

import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public interface e0 extends n0 {
    void forEachRemaining(DoubleConsumer doubleConsumer);

    boolean tryAdvance(DoubleConsumer doubleConsumer);

    @Override // j$.util.n0, j$.util.Spliterator
    e0 trySplit();
}
