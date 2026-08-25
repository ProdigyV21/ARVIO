package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.IntStream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k1 implements m1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ LongStream f18758a;

    public /* synthetic */ k1(LongStream longStream) {
        this.f18758a = longStream;
    }

    public static /* synthetic */ m1 e(LongStream longStream) {
        if (longStream == null) {
            return null;
        }
        return longStream instanceof l1 ? ((l1) longStream).f18773a : new k1(longStream);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 a() {
        return e(this.f18758a.takeWhile(null));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ d0 asDoubleStream() {
        return b0.e(this.f18758a.asDoubleStream());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.l average() {
        return j$.com.android.tools.r8.a.D(this.f18758a.average());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 b() {
        return e(this.f18758a.filter(null));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ Stream boxed() {
        return w6.e(this.f18758a.boxed());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 c() {
        return e(this.f18758a.dropWhile(null));
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f18758a.close();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        return this.f18758a.collect(supplier, objLongConsumer, biConsumer);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ long count() {
        return this.f18758a.count();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 distinct() {
        return e(this.f18758a.distinct());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        LongStream longStream = this.f18758a;
        if (obj instanceof k1) {
            obj = ((k1) obj).f18758a;
        }
        return longStream.equals(obj);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.n findAny() {
        return j$.com.android.tools.r8.a.F(this.f18758a.findAny());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.n findFirst() {
        return j$.com.android.tools.r8.a.F(this.f18758a.findFirst());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ void forEach(LongConsumer longConsumer) {
        this.f18758a.forEach(longConsumer);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
        this.f18758a.forEachOrdered(longConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18758a.hashCode();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ d0 i() {
        return b0.e(this.f18758a.mapToDouble(null));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ boolean isParallel() {
        return this.f18758a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfLong] */
    @Override // j$.util.stream.m1, j$.util.stream.g
    public final /* synthetic */ j$.util.z iterator() {
        ?? it = this.f18758a.iterator();
        if (it == 0) {
            return null;
        }
        return it instanceof j$.util.y ? ((j$.util.y) it).f19012a : new j$.util.x(it);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Iterator iterator() {
        return this.f18758a.iterator();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ boolean k() {
        return this.f18758a.noneMatch(null);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 limit(long j10) {
        return e(this.f18758a.limit(j10));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ Stream mapToObj(LongFunction longFunction) {
        return w6.e(this.f18758a.mapToObj(longFunction));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.n max() {
        return j$.com.android.tools.r8.a.F(this.f18758a.max());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.n min() {
        return j$.com.android.tools.r8.a.F(this.f18758a.min());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ boolean n() {
        return this.f18758a.anyMatch(null);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g onClose(Runnable runnable) {
        return e.e(this.f18758a.onClose(runnable));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g parallel() {
        return e.e(this.f18758a.parallel());
    }

    @Override // j$.util.stream.m1, j$.util.stream.g
    public final /* synthetic */ m1 parallel() {
        return e(this.f18758a.parallel());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 peek(LongConsumer longConsumer) {
        return e(this.f18758a.peek(longConsumer));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ long reduce(long j10, LongBinaryOperator longBinaryOperator) {
        return this.f18758a.reduce(j10, longBinaryOperator);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ j$.util.n reduce(LongBinaryOperator longBinaryOperator) {
        return j$.com.android.tools.r8.a.F(this.f18758a.reduce(longBinaryOperator));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g sequential() {
        return e.e(this.f18758a.sequential());
    }

    @Override // j$.util.stream.m1, j$.util.stream.g
    public final /* synthetic */ m1 sequential() {
        return e(this.f18758a.sequential());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 skip(long j10) {
        return e(this.f18758a.skip(j10));
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 sorted() {
        return e(this.f18758a.sorted());
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.o0.a(this.f18758a.spliterator());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfLong] */
    @Override // j$.util.stream.m1, j$.util.stream.g
    public final /* synthetic */ j$.util.k0 spliterator() {
        return j$.util.i0.a(this.f18758a.spliterator());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ long sum() {
        return this.f18758a.sum();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ long[] toArray() {
        return this.f18758a.toArray();
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ m1 u() {
        return e(this.f18758a.map(null));
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g unordered() {
        return e.e(this.f18758a.unordered());
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ boolean v() {
        return this.f18758a.allMatch(null);
    }

    @Override // j$.util.stream.m1
    public final /* synthetic */ IntStream z() {
        return IntStream.VivifiedWrapper.convert(this.f18758a.mapToInt(null));
    }

    @Override // j$.util.stream.m1
    public final j$.util.k summaryStatistics() {
        this.f18758a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.LongSummaryStatistics");
    }

    @Override // j$.util.stream.m1
    public final m1 d(j$.util.function.i iVar) {
        LongStream longStream = this.f18758a;
        j$.util.function.i iVar2 = new j$.util.function.i(5);
        iVar2.f18557b = iVar;
        return e(longStream.flatMap(iVar2));
    }
}
