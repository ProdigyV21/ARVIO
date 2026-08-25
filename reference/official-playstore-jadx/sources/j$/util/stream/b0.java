package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.IntStream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b0 implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ DoubleStream f18633a;

    public /* synthetic */ b0(DoubleStream doubleStream) {
        this.f18633a = doubleStream;
    }

    public static /* synthetic */ d0 e(DoubleStream doubleStream) {
        if (doubleStream == null) {
            return null;
        }
        return doubleStream instanceof c0 ? ((c0) doubleStream).f18649a : new b0(doubleStream);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ boolean A() {
        return this.f18633a.noneMatch(null);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 a() {
        return e(this.f18633a.takeWhile(null));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l average() {
        return j$.com.android.tools.r8.a.D(this.f18633a.average());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 b() {
        return e(this.f18633a.filter(null));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ Stream boxed() {
        return w6.e(this.f18633a.boxed());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 c() {
        return e(this.f18633a.dropWhile(null));
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f18633a.close();
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        return this.f18633a.collect(supplier, objDoubleConsumer, biConsumer);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ long count() {
        return this.f18633a.count();
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 distinct() {
        return e(this.f18633a.distinct());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        DoubleStream doubleStream = this.f18633a;
        if (obj instanceof b0) {
            obj = ((b0) obj).f18633a;
        }
        return doubleStream.equals(obj);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l findAny() {
        return j$.com.android.tools.r8.a.D(this.f18633a.findAny());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l findFirst() {
        return j$.com.android.tools.r8.a.D(this.f18633a.findFirst());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
        this.f18633a.forEach(doubleConsumer);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
        this.f18633a.forEachOrdered(doubleConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18633a.hashCode();
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ boolean isParallel() {
        return this.f18633a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfDouble] */
    @Override // j$.util.stream.d0, j$.util.stream.g
    public final /* synthetic */ j$.util.r iterator() {
        ?? it = this.f18633a.iterator();
        if (it == 0) {
            return null;
        }
        return it instanceof j$.util.q ? ((j$.util.q) it).f18586a : new j$.util.p(it);
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Iterator iterator() {
        return this.f18633a.iterator();
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ boolean l() {
        return this.f18633a.anyMatch(null);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 limit(long j10) {
        return e(this.f18633a.limit(j10));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 map(DoubleUnaryOperator doubleUnaryOperator) {
        return e(this.f18633a.map(doubleUnaryOperator));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ Stream mapToObj(DoubleFunction doubleFunction) {
        return w6.e(this.f18633a.mapToObj(doubleFunction));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l max() {
        return j$.com.android.tools.r8.a.D(this.f18633a.max());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l min() {
        return j$.com.android.tools.r8.a.D(this.f18633a.min());
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g onClose(Runnable runnable) {
        return e.e(this.f18633a.onClose(runnable));
    }

    @Override // j$.util.stream.d0, j$.util.stream.g
    public final /* synthetic */ d0 parallel() {
        return e(this.f18633a.parallel());
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g parallel() {
        return e.e(this.f18633a.parallel());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 peek(DoubleConsumer doubleConsumer) {
        return e(this.f18633a.peek(doubleConsumer));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ boolean r() {
        return this.f18633a.allMatch(null);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ double reduce(double d4, DoubleBinaryOperator doubleBinaryOperator) {
        return this.f18633a.reduce(d4, doubleBinaryOperator);
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ j$.util.l reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return j$.com.android.tools.r8.a.D(this.f18633a.reduce(doubleBinaryOperator));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ m1 s() {
        return k1.e(this.f18633a.mapToLong(null));
    }

    @Override // j$.util.stream.d0, j$.util.stream.g
    public final /* synthetic */ d0 sequential() {
        return e(this.f18633a.sequential());
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g sequential() {
        return e.e(this.f18633a.sequential());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 skip(long j10) {
        return e(this.f18633a.skip(j10));
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ d0 sorted() {
        return e(this.f18633a.sorted());
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.o0.a(this.f18633a.spliterator());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfDouble] */
    @Override // j$.util.stream.d0, j$.util.stream.g
    public final /* synthetic */ j$.util.e0 spliterator() {
        return j$.util.c0.a(this.f18633a.spliterator());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ double sum() {
        return this.f18633a.sum();
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ double[] toArray() {
        return this.f18633a.toArray();
    }

    @Override // j$.util.stream.g
    public final /* synthetic */ g unordered() {
        return e.e(this.f18633a.unordered());
    }

    @Override // j$.util.stream.d0
    public final /* synthetic */ IntStream x() {
        return IntStream.VivifiedWrapper.convert(this.f18633a.mapToInt(null));
    }

    @Override // j$.util.stream.d0
    public final j$.util.h summaryStatistics() {
        this.f18633a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.DoubleSummaryStatistics");
    }

    @Override // j$.util.stream.d0
    public final d0 d(j$.util.function.i iVar) {
        DoubleStream doubleStream = this.f18633a;
        j$.util.function.i iVar2 = new j$.util.function.i(3);
        iVar2.f18557b = iVar;
        return e(doubleStream.flatMap(iVar2));
    }
}
