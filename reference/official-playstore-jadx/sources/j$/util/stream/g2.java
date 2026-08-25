package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public interface g2 {
    g2 a(int i10);

    long count();

    void forEach(Consumer consumer);

    g2 h(long j10, long j11, IntFunction intFunction);

    void k(Object[] objArr, int i10);

    Object[] m(IntFunction intFunction);

    int o();

    Spliterator spliterator();
}
