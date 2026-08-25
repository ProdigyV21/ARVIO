package j$.util.stream;

import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
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
public final /* synthetic */ class w6 implements Stream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.util.stream.Stream f18943a;

    public /* synthetic */ w6(java.util.stream.Stream stream) {
        this.f18943a = stream;
    }

    public static /* synthetic */ Stream e(java.util.stream.Stream stream) {
        if (stream == null) {
            return null;
        }
        return stream instanceof Stream.Wrapper ? Stream.this : new w6(stream);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ IntStream B(j$.util.function.i iVar) {
        return IntStream.VivifiedWrapper.convert(this.f18943a.flatMapToInt(v3.r0(iVar)));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean allMatch(Predicate predicate) {
        return this.f18943a.allMatch(predicate);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean anyMatch(Predicate predicate) {
        return this.f18943a.anyMatch(predicate);
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f18943a.close();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object collect(Collector collector) {
        return this.f18943a.collect(collector == null ? null : collector instanceof i ? ((i) collector).f18719a : new j(collector));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        return this.f18943a.collect(supplier, biConsumer, biConsumer2);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ long count() {
        return this.f18943a.count();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream d(j$.util.function.i iVar) {
        return e(this.f18943a.flatMap(v3.r0(iVar)));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream distinct() {
        return e(this.f18943a.distinct());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream dropWhile(Predicate predicate) {
        return e(this.f18943a.dropWhile(predicate));
    }

    public final /* synthetic */ boolean equals(Object obj) {
        java.util.stream.Stream stream = this.f18943a;
        if (obj instanceof w6) {
            obj = ((w6) obj).f18943a;
        }
        return stream.equals(obj);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream filter(Predicate predicate) {
        return e(this.f18943a.filter(predicate));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Optional findAny() {
        return j$.com.android.tools.r8.a.C(this.f18943a.findAny());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Optional findFirst() {
        return j$.com.android.tools.r8.a.C(this.f18943a.findFirst());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ void forEach(Consumer consumer) {
        this.f18943a.forEach(consumer);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ void forEachOrdered(Consumer consumer) {
        this.f18943a.forEachOrdered(consumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18943a.hashCode();
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ boolean isParallel() {
        return this.f18943a.isParallel();
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Iterator iterator() {
        return this.f18943a.iterator();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream limit(long j10) {
        return e(this.f18943a.limit(j10));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream map(Function function) {
        return e(this.f18943a.map(function));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ d0 mapToDouble(ToDoubleFunction toDoubleFunction) {
        return b0.e(this.f18943a.mapToDouble(toDoubleFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ IntStream mapToInt(ToIntFunction toIntFunction) {
        return IntStream.VivifiedWrapper.convert(this.f18943a.mapToInt(toIntFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ m1 mapToLong(ToLongFunction toLongFunction) {
        return k1.e(this.f18943a.mapToLong(toLongFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Optional max(Comparator comparator) {
        return j$.com.android.tools.r8.a.C(this.f18943a.max(comparator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Optional min(Comparator comparator) {
        return j$.com.android.tools.r8.a.C(this.f18943a.min(comparator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean noneMatch(Predicate predicate) {
        return this.f18943a.noneMatch(predicate);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g onClose(Runnable runnable) {
        return e.e(this.f18943a.onClose(runnable));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ m1 p(j$.util.function.i iVar) {
        return k1.e(this.f18943a.flatMapToLong(v3.r0(iVar)));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g parallel() {
        return e.e(this.f18943a.parallel());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream peek(Consumer consumer) {
        return e(this.f18943a.peek(consumer));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Optional reduce(BinaryOperator binaryOperator) {
        return j$.com.android.tools.r8.a.C(this.f18943a.reduce(binaryOperator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        return this.f18943a.reduce(obj, biFunction, binaryOperator);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
        return this.f18943a.reduce(obj, binaryOperator);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g sequential() {
        return e.e(this.f18943a.sequential());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream skip(long j10) {
        return e(this.f18943a.skip(j10));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream sorted() {
        return e(this.f18943a.sorted());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream sorted(Comparator comparator) {
        return e(this.f18943a.sorted(comparator));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.o0.a(this.f18943a.spliterator());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream takeWhile(Predicate predicate) {
        return e(this.f18943a.takeWhile(predicate));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object[] toArray() {
        return this.f18943a.toArray();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return this.f18943a.toArray(intFunction);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g unordered() {
        return e.e(this.f18943a.unordered());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ d0 y(j$.util.function.i iVar) {
        return b0.e(this.f18943a.flatMapToDouble(v3.r0(iVar)));
    }
}
