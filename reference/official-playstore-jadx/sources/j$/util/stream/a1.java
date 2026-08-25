package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a1 extends a implements IntStream {
    @Override // j$.util.stream.IntStream
    public final j$.util.m findAny() {
        return (j$.util.m) H0(g0.f18695d);
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.m findFirst() {
        return (j$.util.m) H0(g0.f18694c);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream sorted() {
        return new e6(this, y6.f18973q | y6.f18971o, 0);
    }

    @Override // j$.util.stream.IntStream
    public void forEach(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        H0(new n0(intConsumer, false));
    }

    @Override // j$.util.stream.IntStream
    public void forEachOrdered(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        H0(new n0(intConsumer, true));
    }

    public static j$.util.h0 T0(Spliterator spliterator) {
        if (spliterator instanceof j$.util.h0) {
            return (j$.util.h0) spliterator;
        }
        if (g8.f18703a) {
            g8.a(a.class, "using IntStream.adapt(Spliterator<Integer> s)");
            throw null;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // j$.util.stream.a
    public final z6 L0() {
        return z6.INT_VALUE;
    }

    @Override // j$.util.stream.a
    public final g2 J0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return v3.c0(aVar, spliterator, z);
    }

    @Override // j$.util.stream.a
    public final Spliterator S0(a aVar, Supplier supplier, boolean z) {
        return new l7(aVar, supplier, z);
    }

    @Override // j$.util.stream.a
    public final boolean K0(Spliterator spliterator, l5 l5Var) {
        IntConsumer sVar;
        boolean zF;
        j$.util.h0 h0VarT0 = T0(spliterator);
        if (l5Var instanceof IntConsumer) {
            sVar = (IntConsumer) l5Var;
        } else {
            if (g8.f18703a) {
                g8.a(a.class, "using IntStream.adapt(Sink<Integer> s)");
                throw null;
            }
            Objects.requireNonNull(l5Var);
            sVar = new j$.util.s(l5Var, 1);
        }
        do {
            zF = l5Var.f();
            if (zF) {
                break;
            }
        } while (h0VarT0.tryAdvance(sVar));
        return zF;
    }

    @Override // j$.util.stream.v3
    public final y1 A0(long j10, IntFunction intFunction) {
        return v3.s0(j10);
    }

    @Override // j$.util.stream.g
    public final j$.util.v iterator() {
        j$.util.h0 h0VarSpliterator = spliterator();
        Objects.requireNonNull(h0VarSpliterator);
        return new j$.util.q0(h0VarSpliterator);
    }

    @Override // j$.util.stream.a, j$.util.stream.g
    public final j$.util.h0 spliterator() {
        return T0(super.spliterator());
    }

    @Override // j$.util.stream.IntStream
    public final m1 asLongStream() {
        return new u(this, 0, 1);
    }

    @Override // j$.util.stream.IntStream
    public final d0 asDoubleStream() {
        return new w(this, 0, 2);
    }

    @Override // j$.util.stream.IntStream
    public final Stream boxed() {
        return new r(this, 0, new o(21), 1);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream map(IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new u0(this, y6.f18972p | y6.f18970n, intUnaryOperator, 1);
    }

    @Override // j$.util.stream.IntStream
    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return new r(this, y6.f18972p | y6.f18970n, intFunction, 1);
    }

    @Override // j$.util.stream.IntStream
    public final m1 j() {
        Objects.requireNonNull(null);
        return new u(this, y6.f18972p | y6.f18970n, 2);
    }

    @Override // j$.util.stream.IntStream
    public final d0 f() {
        Objects.requireNonNull(null);
        return new w(this, y6.f18972p | y6.f18970n, 3);
    }

    @Override // j$.util.stream.IntStream
    public final int reduce(int i10, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return ((Integer) H0(new l4(z6.INT_VALUE, intBinaryOperator, i10))).intValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream o(l0 l0Var) {
        Objects.requireNonNull(l0Var);
        return new u0(this, y6.f18972p | y6.f18970n | y6.f18976t, l0Var, 2);
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.m reduce(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return (j$.util.m) H0(new y3(z6.INT_VALUE, intBinaryOperator, 3));
    }

    @Override // j$.util.stream.IntStream
    public final IntStream b() {
        Objects.requireNonNull(null);
        return new t(this, y6.f18976t, 2);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream peek(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new u0(this, intConsumer);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream limit(long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(Long.toString(j10));
        }
        return v3.x0(this, 0L, j10);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream skip(long j10) {
        if (j10 >= 0) {
            return j10 == 0 ? this : v3.x0(this, j10, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j10));
    }

    @Override // j$.util.stream.IntStream
    public final IntStream a() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new e6(this, z8.f18997a, 1);
    }

    @Override // j$.util.stream.IntStream
    public final IntStream c() {
        int i10 = z8.f18997a;
        Objects.requireNonNull(null);
        return new e6(this, z8.f18998b, 2);
    }

    @Override // j$.util.stream.IntStream
    public final long count() {
        return ((Long) H0(new c4(3))).longValue();
    }

    @Override // j$.util.stream.IntStream
    public final IntStream distinct() {
        return ((d5) boxed()).distinct().mapToInt(new o(20));
    }

    @Override // j$.util.stream.IntStream
    public final int sum() {
        return reduce(0, new o(25));
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.m min() {
        return reduce(new o(22));
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.m max() {
        return reduce(new o(26));
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.l average() {
        long j10 = ((long[]) collect(new o(27), new o(28), new o(29)))[0];
        return j10 > 0 ? new j$.util.l(r0[1] / j10) : j$.util.l.f18568c;
    }

    @Override // j$.util.stream.IntStream
    public final j$.util.i summaryStatistics() {
        return (j$.util.i) collect(new j$.time.c(20), new o(23), new o(24));
    }

    @Override // j$.util.stream.IntStream
    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        p pVar = new p(biConsumer, 1);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(pVar);
        return H0(new a4(z6.INT_VALUE, pVar, objIntConsumer, supplier, 4));
    }

    @Override // j$.util.stream.IntStream
    public final boolean w() {
        return ((Boolean) H0(v3.w0(t1.ANY))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean m() {
        return ((Boolean) H0(v3.w0(t1.ALL))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final boolean q() {
        return ((Boolean) H0(v3.w0(t1.NONE))).booleanValue();
    }

    @Override // j$.util.stream.IntStream
    public final int[] toArray() {
        return (int[]) v3.o0((c2) I0(new o(19))).b();
    }
}
