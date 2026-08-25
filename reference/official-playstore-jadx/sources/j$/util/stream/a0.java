package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a0 extends a implements d0 {
    @Override // j$.util.stream.d0
    public final j$.util.l findAny() {
        return (j$.util.l) H0(f0.f18685d);
    }

    @Override // j$.util.stream.d0
    public final j$.util.l findFirst() {
        return (j$.util.l) H0(f0.f18684c);
    }

    @Override // j$.util.stream.d0
    public final d0 sorted() {
        return new d6(this, y6.f18973q | y6.f18971o, 0);
    }

    public static j$.util.e0 T0(Spliterator spliterator) {
        if (spliterator instanceof j$.util.e0) {
            return (j$.util.e0) spliterator;
        }
        if (g8.f18703a) {
            g8.a(a.class, "using DoubleStream.adapt(Spliterator<Double> s)");
            throw null;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    @Override // j$.util.stream.d0
    public void forEach(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        H0(new m0(doubleConsumer, false));
    }

    @Override // j$.util.stream.d0
    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        H0(new m0(doubleConsumer, true));
    }

    @Override // j$.util.stream.a
    public final z6 L0() {
        return z6.DOUBLE_VALUE;
    }

    @Override // j$.util.stream.a
    public final g2 J0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return v3.b0(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator S0(a aVar, Supplier supplier, boolean z) {
        return new j7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean K0(Spliterator spliterator, l5 l5Var) {
        DoubleConsumer oVar;
        boolean zF;
        j$.util.e0 e0VarT0 = T0(spliterator);
        if (l5Var instanceof DoubleConsumer) {
            oVar = (DoubleConsumer) l5Var;
        } else {
            if (g8.f18703a) {
                g8.a(a.class, "using DoubleStream.adapt(Sink<Double> s)");
                throw null;
            }
            Objects.requireNonNull(l5Var);
            oVar = new j$.util.o(l5Var, 1);
        }
        do {
            zF = l5Var.f();
            if (zF) {
                break;
            }
        } while (e0VarT0.tryAdvance(oVar));
        return zF;
    }

    @Override // j$.util.stream.v3
    public final y1 A0(long j10, IntFunction intFunction) {
        return v3.h0(j10);
    }

    @Override // j$.util.stream.g
    public final j$.util.r iterator() {
        j$.util.e0 e0VarSpliterator = spliterator();
        Objects.requireNonNull(e0VarSpliterator);
        return new j$.util.s0(e0VarSpliterator);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final j$.util.e0 spliterator() {
        return T0(super.spliterator());
    }

    @Override // j$.util.stream.d0
    public final Stream boxed() {
        return new r(this, 0, new o(4), 0);
    }

    @Override // j$.util.stream.d0
    public final d0 map(DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        return new s(this, y6.f18972p | y6.f18970n, doubleUnaryOperator, 0);
    }

    @Override // j$.util.stream.d0
    public final Stream mapToObj(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return new r(this, y6.f18972p | y6.f18970n, doubleFunction, 0);
    }

    @Override // j$.util.stream.d0
    public final IntStream x() {
        Objects.requireNonNull(null);
        return new t(this, y6.f18972p | y6.f18970n, 0);
    }

    @Override // j$.util.stream.d0
    public final m1 s() {
        Objects.requireNonNull(null);
        return new u(this, y6.f18972p | y6.f18970n, 0);
    }

    @Override // j$.util.stream.d0
    public final d0 d(j$.util.function.i iVar) {
        Objects.requireNonNull(iVar);
        return new s(this, y6.f18972p | y6.f18970n | y6.f18976t, iVar, 1);
    }

    @Override // j$.util.stream.d0
    public final d0 b() {
        Objects.requireNonNull(null);
        return new w(this, y6.f18976t, 1);
    }

    @Override // j$.util.stream.d0
    public final d0 peek(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new s(this, doubleConsumer);
    }

    @Override // j$.util.stream.d0
    public final d0 limit(long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(Long.toString(j10));
        }
        return v3.v0(this, 0L, j10);
    }

    @Override // j$.util.stream.d0
    public final d0 skip(long j10) {
        if (j10 >= 0) {
            return j10 == 0 ? this : v3.v0(this, j10, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j10));
    }

    @Override // j$.util.stream.d0
    public final d0 a() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new d6(this, z8.f18997a, 1);
    }

    @Override // j$.util.stream.d0
    public final d0 c() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new d6(this, z8.f18998b, 2);
    }

    @Override // j$.util.stream.d0
    public final d0 distinct() {
        return ((d5) boxed()).distinct().mapToDouble(new o(5));
    }

    @Override // j$.util.stream.d0
    public final double sum() {
        double[] dArr = (double[]) collect(new o(8), new o(9), new j$.time.c(27));
        Set set = Collectors.f18598a;
        double d4 = dArr[0] + dArr[1];
        double d10 = dArr[dArr.length - 1];
        return (Double.isNaN(d4) && Double.isInfinite(d10)) ? d10 : d4;
    }

    @Override // j$.util.stream.d0
    public final j$.util.l min() {
        return reduce(new j$.time.c(28));
    }

    @Override // j$.util.stream.d0
    public final j$.util.l max() {
        return reduce(new o(7));
    }

    @Override // j$.util.stream.d0
    public final j$.util.l average() {
        double[] dArr = (double[]) collect(new j$.time.c(29), new o(0), new o(1));
        if (dArr[2] <= 0.0d) {
            return j$.util.l.f18568c;
        }
        Set set = Collectors.f18598a;
        double d4 = dArr[0] + dArr[1];
        double d10 = dArr[dArr.length - 1];
        if (Double.isNaN(d4) && Double.isInfinite(d10)) {
            d4 = d10;
        }
        return new j$.util.l(d4 / dArr[2]);
    }

    @Override // j$.util.stream.d0
    public final j$.util.h summaryStatistics() {
        return (j$.util.h) collect(new j$.time.c(15), new o(2), new o(3));
    }

    @Override // j$.util.stream.d0
    public final Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        p pVar = new p(biConsumer, 0);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(pVar);
        return H0(new a4(z6.DOUBLE_VALUE, pVar, objDoubleConsumer, supplier, 1));
    }

    @Override // j$.util.stream.d0
    public final boolean l() {
        return ((Boolean) H0(v3.u0(t1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.d0
    public final boolean r() {
        return ((Boolean) H0(v3.u0(t1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.d0
    public final boolean A() {
        return ((Boolean) H0(v3.u0(t1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.d0
    public final double[] toArray() {
        return (double[]) v3.n0((a2) I0(new o(6))).b();
    }

    @Override // j$.util.stream.d0
    public final double reduce(double d4, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return ((Double) H0(new e4(z6.DOUBLE_VALUE, doubleBinaryOperator, d4))).doubleValue();
    }

    @Override // j$.util.stream.d0
    public final j$.util.l reduce(DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return (j$.util.l) H0(new y3(z6.DOUBLE_VALUE, doubleBinaryOperator, 1));
    }

    @Override // j$.util.stream.d0
    public final long count() {
        return ((Long) H0(new c4(1))).longValue();
    }
}
