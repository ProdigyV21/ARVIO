package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public interface d0 extends g {
    boolean A();

    d0 a();

    j$.util.l average();

    d0 b();

    Stream boxed();

    d0 c();

    Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer);

    long count();

    d0 d(j$.util.function.i iVar);

    d0 distinct();

    j$.util.l findAny();

    j$.util.l findFirst();

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    @Override // j$.util.stream.g
    j$.util.r iterator();

    boolean l();

    d0 limit(long j10);

    d0 map(DoubleUnaryOperator doubleUnaryOperator);

    Stream mapToObj(DoubleFunction doubleFunction);

    j$.util.l max();

    j$.util.l min();

    @Override // j$.util.stream.g
    d0 parallel();

    d0 peek(DoubleConsumer doubleConsumer);

    boolean r();

    double reduce(double d4, DoubleBinaryOperator doubleBinaryOperator);

    j$.util.l reduce(DoubleBinaryOperator doubleBinaryOperator);

    m1 s();

    @Override // j$.util.stream.g
    d0 sequential();

    d0 skip(long j10);

    d0 sorted();

    @Override // j$.util.stream.g
    j$.util.e0 spliterator();

    double sum();

    j$.util.h summaryStatistics();

    double[] toArray();

    IntStream x();
}
