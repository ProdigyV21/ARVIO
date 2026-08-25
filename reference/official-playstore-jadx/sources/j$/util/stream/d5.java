package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.Spliterator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d5 extends a implements Stream {
    @Override // j$.util.stream.Stream
    public final Stream sorted() {
        return new g6(this);
    }

    @Override // j$.util.stream.Stream
    public final Stream distinct() {
        return new n(this, y6.f18969m | y6.f18976t);
    }

    @Override // j$.util.stream.Stream
    public final Optional min(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.a(comparator, 1));
    }

    @Override // j$.util.stream.Stream
    public final Optional findAny() {
        return (Optional) H0(i0.f18721d);
    }

    @Override // j$.util.stream.Stream
    public final Optional findFirst() {
        return (Optional) H0(i0.f18720c);
    }

    @Override // j$.util.stream.Stream
    public final Stream sorted(Comparator comparator) {
        return new g6(this, comparator);
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return H0(new a4(z6.REFERENCE, binaryOperator, biFunction, obj, 2));
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(binaryOperator);
        return H0(new a4(z6.REFERENCE, binaryOperator, binaryOperator, obj, 2));
    }

    public void forEach(Consumer consumer) {
        Objects.requireNonNull(consumer);
        H0(new p0(consumer, false));
    }

    public void forEachOrdered(Consumer consumer) {
        Objects.requireNonNull(consumer);
        H0(new p0(consumer, true));
    }

    @Override // j$.util.stream.Stream
    public final Optional max(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.a(comparator, 0));
    }

    @Override // j$.util.stream.a
    public final z6 L0() {
        return z6.REFERENCE;
    }

    @Override // j$.util.stream.Stream
    public final Optional reduce(BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return (Optional) H0(new y3(z6.REFERENCE, binaryOperator, 2));
    }

    @Override // j$.util.stream.a
    public final g2 J0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return v3.a0(aVar, spliterator, z, intFunction);
    }

    @Override // j$.util.stream.a
    public final Spliterator S0(a aVar, Supplier supplier, boolean z) {
        return new c8(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean K0(Spliterator spliterator, l5 l5Var) {
        boolean zF;
        do {
            zF = l5Var.f();
            if (zF) {
                break;
            }
        } while (spliterator.tryAdvance(l5Var));
        return zF;
    }

    @Override // j$.util.stream.v3
    public final y1 A0(long j10, IntFunction intFunction) {
        return v3.Y(j10, intFunction);
    }

    @Override // j$.util.stream.g
    public final Iterator iterator() {
        Spliterator spliterator = spliterator();
        Objects.requireNonNull(spliterator);
        return new j$.util.p0(spliterator);
    }

    @Override // j$.util.stream.Stream
    public final Stream filter(Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new r(this, y6.f18976t, predicate, 4);
    }

    @Override // j$.util.stream.Stream
    public final Stream map(Function function) {
        Objects.requireNonNull(function);
        return new r(this, y6.f18972p | y6.f18970n, function, 5);
    }

    @Override // j$.util.stream.Stream
    public final IntStream mapToInt(ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new u0(this, y6.f18972p | y6.f18970n, toIntFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return H0(new a4(z6.REFERENCE, biConsumer2, biConsumer, supplier, 3));
    }

    @Override // j$.util.stream.Stream
    public final m1 mapToLong(ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new f1(this, y6.f18972p | y6.f18970n, toLongFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final d0 mapToDouble(ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new s(this, y6.f18972p | y6.f18970n, toDoubleFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final long count() {
        return ((Long) H0(new c4(2))).longValue();
    }

    @Override // j$.util.stream.Stream
    public final Stream d(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new r(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 6);
    }

    @Override // j$.util.stream.Stream
    public final IntStream B(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new u0(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 4);
    }

    @Override // j$.util.stream.Stream
    public final d0 y(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new s(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 4);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Collector collector) {
        Collector collector2;
        Object objH0;
        if (!this.f18603h.f18612r || !collector.characteristics().contains(h.CONCURRENT) || (y6.ORDERED.r(this.f18607m) && !collector.characteristics().contains(h.UNORDERED))) {
            Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
            collector2 = collector;
            objH0 = H0(new h4(z6.REFERENCE, collector.combiner(), collector.accumulator(), supplier, collector2));
        } else {
            objH0 = collector.supplier().get();
            forEach(new j$.util.concurrent.t(7, collector.accumulator(), objH0));
            collector2 = collector;
        }
        return collector2.characteristics().contains(h.IDENTITY_FINISH) ? objH0 : collector2.finisher().apply(objH0);
    }

    @Override // j$.util.stream.Stream
    public final m1 p(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new f1(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 2);
    }

    @Override // j$.util.stream.Stream
    public final Stream peek(Consumer consumer) {
        Objects.requireNonNull(consumer);
        return new r(this, consumer);
    }

    @Override // j$.util.stream.Stream
    public final Stream limit(long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(Long.toString(j10));
        }
        return v3.C0(this, 0L, j10);
    }

    @Override // j$.util.stream.Stream
    public final Stream skip(long j10) {
        if (j10 >= 0) {
            return j10 == 0 ? this : v3.C0(this, j10, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j10));
    }

    @Override // j$.util.stream.Stream
    public final Stream takeWhile(Predicate predicate) {
        int i10 = z8.f18997a;
        Objects.requireNonNull(predicate);
        return new h8(this, z8.f18997a, predicate, 0);
    }

    @Override // j$.util.stream.Stream
    public final Stream dropWhile(Predicate predicate) {
        int i10 = z8.f18997a;
        Objects.requireNonNull(predicate);
        return new h8(this, z8.f18998b, predicate, 1);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray(IntFunction intFunction) {
        return v3.m0(I0(intFunction), intFunction).m(intFunction);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray() {
        return toArray(new b1(20));
    }

    @Override // j$.util.stream.Stream
    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) H0(v3.B0(t1.ANY, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) H0(v3.B0(t1.ALL, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) H0(v3.B0(t1.NONE, predicate))).booleanValue();
    }
}
