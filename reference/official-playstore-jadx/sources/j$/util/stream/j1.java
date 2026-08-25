package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j1 extends a implements m1 {
    @Override // j$.util.stream.m1
    public final j$.util.n findAny() {
        return (j$.util.n) H0(h0.f18706d);
    }

    @Override // j$.util.stream.m1
    public final j$.util.n findFirst() {
        return (j$.util.n) H0(h0.f18705c);
    }

    @Override // j$.util.stream.m1
    public final m1 sorted() {
        return new f6(this, y6.f18973q | y6.f18971o, 0);
    }

    public void forEach(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        H0(new o0(longConsumer, false));
    }

    public void forEachOrdered(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        H0(new o0(longConsumer, true));
    }

    public static j$.util.k0 T0(Spliterator spliterator) {
        if (spliterator instanceof j$.util.k0) {
            return (j$.util.k0) spliterator;
        }
        if (g8.f18703a) {
            g8.a(a.class, "using LongStream.adapt(Spliterator<Long> s)");
            throw null;
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // j$.util.stream.a
    public final z6 L0() {
        return z6.LONG_VALUE;
    }

    @Override // j$.util.stream.a
    public final g2 J0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return v3.d0(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator S0(a aVar, Supplier supplier, boolean z) {
        return new n7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean K0(Spliterator spliterator, l5 l5Var) {
        LongConsumer wVar;
        boolean zF;
        j$.util.k0 k0VarT0 = T0(spliterator);
        if (l5Var instanceof LongConsumer) {
            wVar = (LongConsumer) l5Var;
        } else {
            if (g8.f18703a) {
                g8.a(a.class, "using LongStream.adapt(Sink<Long> s)");
                throw null;
            }
            Objects.requireNonNull(l5Var);
            wVar = new j$.util.w(l5Var, 1);
        }
        do {
            zF = l5Var.f();
            if (zF) {
                break;
            }
        } while (k0VarT0.tryAdvance(wVar));
        return zF;
    }

    @Override // j$.util.stream.v3
    public final y1 A0(long j10, IntFunction intFunction) {
        return v3.t0(j10);
    }

    @Override // j$.util.stream.g
    public final j$.util.z iterator() {
        j$.util.k0 k0VarSpliterator = spliterator();
        Objects.requireNonNull(k0VarSpliterator);
        return new j$.util.r0(k0VarSpliterator);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final j$.util.k0 spliterator() {
        return T0(super.spliterator());
    }

    @Override // j$.util.stream.m1
    public final d0 asDoubleStream() {
        return new w(this, y6.f18970n, 4);
    }

    @Override // j$.util.stream.m1
    public final Stream boxed() {
        return new r(this, 0, new b1(5), 2);
    }

    @Override // j$.util.stream.m1
    public final m1 u() {
        Objects.requireNonNull(null);
        return new u(this, y6.f18972p | y6.f18970n, 3);
    }

    @Override // j$.util.stream.m1
    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return new r(this, y6.f18972p | y6.f18970n, longFunction, 2);
    }

    @Override // j$.util.stream.m1
    public final IntStream z() {
        Objects.requireNonNull(null);
        return new t(this, y6.f18972p | y6.f18970n, 3);
    }

    @Override // j$.util.stream.m1
    public final d0 i() {
        Objects.requireNonNull(null);
        return new w(this, y6.f18972p | y6.f18970n, 5);
    }

    @Override // j$.util.stream.m1
    public final m1 d(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new f1(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 0);
    }

    @Override // j$.util.stream.m1
    public final m1 b() {
        Objects.requireNonNull(null);
        return new u(this, y6.f18976t, 5);
    }

    @Override // j$.util.stream.m1
    public final m1 peek(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new f1(this, longConsumer);
    }

    @Override // j$.util.stream.m1
    public final m1 limit(long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(Long.toString(j10));
        }
        return v3.z0(this, 0L, j10);
    }

    @Override // j$.util.stream.m1
    public final m1 skip(long j10) {
        if (j10 >= 0) {
            return j10 == 0 ? this : v3.z0(this, j10, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j10));
    }

    @Override // j$.util.stream.m1
    public final m1 a() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new f6(this, z8.f18997a, 1);
    }

    @Override // j$.util.stream.m1
    public final m1 c() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new f6(this, z8.f18998b, 2);
    }

    @Override // j$.util.stream.m1
    public final m1 distinct() {
        return ((d5) boxed()).distinct().mapToLong(new b1(2));
    }

    @Override // j$.util.stream.m1
    public final long sum() {
        return reduce(0L, new b1(10));
    }

    @Override // j$.util.stream.m1
    public final j$.util.n min() {
        return reduce(new b1(1));
    }

    @Override // j$.util.stream.m1
    public final j$.util.n max() {
        return reduce(new b1(9));
    }

    @Override // j$.util.stream.m1
    public final j$.util.l average() {
        long j10 = ((long[]) collect(new b1(6), new b1(7), new b1(8)))[0];
        return j10 > 0 ? new j$.util.l(r0[1] / j10) : j$.util.l.f18568c;
    }

    @Override // j$.util.stream.m1
    public final long reduce(long j10, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return ((Long) H0(new w3(z6.LONG_VALUE, longBinaryOperator, j10))).longValue();
    }

    @Override // j$.util.stream.m1
    public final j$.util.k summaryStatistics() {
        return (j$.util.k) collect(new j$.time.c(21), new b1(0), new b1(3));
    }

    @Override // j$.util.stream.m1
    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        p pVar = new p(biConsumer, 2);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(pVar);
        return H0(new a4(z6.LONG_VALUE, pVar, objLongConsumer, supplier, 0));
    }

    @Override // j$.util.stream.m1
    public final boolean n() {
        return ((Boolean) H0(v3.y0(t1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.m1
    public final j$.util.n reduce(LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return (j$.util.n) H0(new y3(z6.LONG_VALUE, longBinaryOperator, 0));
    }

    @Override // j$.util.stream.m1
    public final boolean v() {
        return ((Boolean) H0(v3.y0(t1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.m1
    public final boolean k() {
        return ((Boolean) H0(v3.y0(t1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.m1
    public final long[] toArray() {
        return (long[]) v3.p0((e2) I0(new b1(4))).b();
    }

    @Override // j$.util.stream.m1
    public final long count() {
        return ((Long) H0(new c4(0))).longValue();
    }
}
