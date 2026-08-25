package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public interface m1 extends g {
    m1 a();

    d0 asDoubleStream();

    j$.util.l average();

    m1 b();

    Stream boxed();

    m1 c();

    Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer);

    long count();

    m1 d(j$.util.function.i iVar);

    m1 distinct();

    j$.util.n findAny();

    j$.util.n findFirst();

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    d0 i();

    @Override // j$.util.stream.g
    j$.util.z iterator();

    boolean k();

    m1 limit(long j10);

    Stream mapToObj(LongFunction longFunction);

    j$.util.n max();

    j$.util.n min();

    boolean n();

    @Override // j$.util.stream.g
    m1 parallel();

    m1 peek(LongConsumer longConsumer);

    long reduce(long j10, LongBinaryOperator longBinaryOperator);

    j$.util.n reduce(LongBinaryOperator longBinaryOperator);

    @Override // j$.util.stream.g
    m1 sequential();

    m1 skip(long j10);

    m1 sorted();

    @Override // j$.util.stream.g
    j$.util.k0 spliterator();

    long sum();

    j$.util.k summaryStatistics();

    long[] toArray();

    m1 u();

    boolean v();

    IntStream z();
}
