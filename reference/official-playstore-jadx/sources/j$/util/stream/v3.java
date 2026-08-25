package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v3 implements e8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y2 f18918a = new y2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w2 f18919b = new w2();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final x2 f18920c = new x2();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v2 f18921d = new v2();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f18922e = new int[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f18923f = new long[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final double[] f18924g = new double[0];

    public abstract y1 A0(long j10, IntFunction intFunction);

    public abstract q4 D0();

    public abstract l5 E0(Spliterator spliterator, l5 l5Var);

    public abstract l5 F0(l5 l5Var);

    public abstract Spliterator G0(Spliterator spliterator);

    public abstract void f0(Spliterator spliterator, l5 l5Var);

    public abstract boolean g0(Spliterator spliterator, l5 l5Var);

    public abstract g2 j0(Spliterator spliterator, boolean z, IntFunction intFunction);

    public abstract long k0(Spliterator spliterator);

    @Override // j$.util.stream.e8
    public /* synthetic */ int t() {
        return 0;
    }

    public static j$.util.function.i r0(Function function) {
        j$.util.function.i iVar = new j$.util.function.i(4);
        iVar.f18557b = function;
        return iVar;
    }

    public static Set q0(Set set) {
        if (set == null || set.isEmpty()) {
            return set;
        }
        HashSet hashSet = new HashSet();
        Object next = set.iterator().next();
        if (next instanceof h) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                try {
                    h hVar = (h) it.next();
                    hashSet.add(hVar == null ? null : hVar == h.CONCURRENT ? Collector.Characteristics.CONCURRENT : hVar == h.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                } catch (ClassCastException e5) {
                    j$.util.f.a(e5, "java.util.stream.Collector.Characteristics");
                    throw null;
                }
            }
        } else {
            if (!(next instanceof Collector.Characteristics)) {
                j$.util.f.a(next.getClass(), "java.util.stream.Collector.Characteristics");
                throw null;
            }
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                try {
                    Collector.Characteristics characteristics = (Collector.Characteristics) it2.next();
                    hashSet.add(characteristics == null ? null : characteristics == Collector.Characteristics.CONCURRENT ? h.CONCURRENT : characteristics == Collector.Characteristics.UNORDERED ? h.UNORDERED : h.IDENTITY_FINISH);
                } catch (ClassCastException e6) {
                    j$.util.f.a(e6, "java.util.stream.Collector.Characteristics");
                    throw null;
                }
            }
        }
        return hashSet;
    }

    public static long W(long j10, long j11, long j12) {
        if (j10 >= 0) {
            return Math.max(-1L, Math.min(j10 - j11, j12));
        }
        return -1L;
    }

    public static long Z(long j10, long j11) {
        long j12 = j11 >= 0 ? j10 + j11 : Long.MAX_VALUE;
        if (j12 >= 0) {
            return j12;
        }
        return Long.MAX_VALUE;
    }

    public static Spliterator X(z6 z6Var, Spliterator spliterator, long j10, long j11) {
        long jZ = Z(j10, j11);
        int i10 = u5.f18908a[z6Var.ordinal()];
        if (i10 == 1) {
            return new s7(spliterator, j10, jZ);
        }
        if (i10 == 2) {
            return new p7((j$.util.h0) spliterator, j10, jZ);
        }
        if (i10 == 3) {
            return new q7((j$.util.k0) spliterator, j10, jZ);
        }
        if (i10 != 4) {
            throw new IllegalStateException("Unknown shape " + z6Var);
        }
        return new o7((j$.util.e0) spliterator, j10, jZ);
    }

    public static j$.util.concurrent.t B0(t1 t1Var, Predicate predicate) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(t1Var);
        return new j$.util.concurrent.t(z6.REFERENCE, t1Var, new j$.util.concurrent.t(5, t1Var, predicate));
    }

    public static z2 i0(z6 z6Var) {
        int i10 = h2.f18707a[z6Var.ordinal()];
        if (i10 == 1) {
            return f18918a;
        }
        if (i10 == 2) {
            return f18919b;
        }
        if (i10 == 3) {
            return f18920c;
        }
        if (i10 == 4) {
            return f18921d;
        }
        throw new IllegalStateException("Unknown shape " + z6Var);
    }

    public static j$.util.concurrent.t w0(t1 t1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(t1Var);
        return new j$.util.concurrent.t(z6.INT_VALUE, t1Var, new n1(t1Var, 1));
    }

    public static n5 C0(d5 d5Var, long j10, long j11) {
        if (j10 < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j10);
        }
        return new n5(d5Var, l0(j11), j10, j11);
    }

    public static g2 V(g2 g2Var, long j10, long j11, IntFunction intFunction) {
        if (j10 == 0 && j11 == g2Var.count()) {
            return g2Var;
        }
        Spliterator spliterator = g2Var.spliterator();
        long j12 = j11 - j10;
        y1 y1VarY = Y(j12, intFunction);
        y1VarY.c(j12);
        for (int i10 = 0; i10 < j10 && spliterator.tryAdvance(new b1(11)); i10++) {
        }
        if (j11 == g2Var.count()) {
            spliterator.forEachRemaining(y1VarY);
        } else {
            for (int i11 = 0; i11 < j12 && spliterator.tryAdvance(y1VarY); i11++) {
            }
        }
        y1VarY.end();
        return y1VarY.build();
    }

    public static i2 e0(z6 z6Var, g2 g2Var, g2 g2Var2) {
        int i10 = h2.f18707a[z6Var.ordinal()];
        if (i10 == 1) {
            return new r2(g2Var, g2Var2);
        }
        if (i10 == 2) {
            return new o2((c2) g2Var, (c2) g2Var2);
        }
        if (i10 == 3) {
            return new p2((e2) g2Var, (e2) g2Var2);
        }
        if (i10 != 4) {
            throw new IllegalStateException("Unknown shape " + z6Var);
        }
        return new n2((a2) g2Var, (a2) g2Var2);
    }

    public static j$.util.concurrent.t y0(t1 t1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(t1Var);
        return new j$.util.concurrent.t(z6.LONG_VALUE, t1Var, new n1(t1Var, 0));
    }

    public static void J() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static j$.util.concurrent.t u0(t1 t1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(t1Var);
        return new j$.util.concurrent.t(z6.DOUBLE_VALUE, t1Var, new n1(t1Var, 2));
    }

    public static void K() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static y1 Y(long j10, IntFunction intFunction) {
        if (j10 >= 0 && j10 < 2147483639) {
            return new a3(j10, intFunction);
        }
        return new s3();
    }

    public static void C() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static void F(j5 j5Var, Integer num) {
        if (g8.f18703a) {
            g8.a(j5Var.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
            throw null;
        }
        j5Var.accept(num.intValue());
    }

    public static void H(k5 k5Var, Long l10) {
        if (g8.f18703a) {
            g8.a(k5Var.getClass(), "{0} calling Sink.OfLong.accept(Long)");
            throw null;
        }
        k5Var.accept(l10.longValue());
    }

    public static w1 s0(long j10) {
        if (j10 < 0 || j10 >= 2147483639) {
            return new d3();
        }
        return new c3(j10);
    }

    public static void D(i5 i5Var, Double d4) {
        if (g8.f18703a) {
            g8.a(i5Var.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
            throw null;
        }
        i5Var.accept(d4.doubleValue());
    }

    public static p5 x0(a1 a1Var, long j10, long j11) {
        if (j10 < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j10);
        }
        return new p5(a1Var, l0(j11), j10, j11);
    }

    public static x1 t0(long j10) {
        if (j10 < 0 || j10 >= 2147483639) {
            return new m3();
        }
        return new l3(j10);
    }

    public static Object[] L(f2 f2Var, IntFunction intFunction) {
        if (g8.f18703a) {
            g8.a(f2Var.getClass(), "{0} calling Node.OfPrimitive.asArray");
            throw null;
        }
        if (f2Var.count() >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) f2Var.count());
        f2Var.k(objArr, 0);
        return objArr;
    }

    public static v1 h0(long j10) {
        if (j10 < 0 || j10 >= 2147483639) {
            return new u2();
        }
        return new t2(j10);
    }

    public static g2 a0(v3 v3Var, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long jK0 = v3Var.k0(spliterator);
        if (jK0 < 0 || !spliterator.hasCharacteristics(16384)) {
            l0 l0Var = new l0();
            l0Var.f18772a = intFunction;
            g2 g2Var = (g2) new l2(v3Var, spliterator, l0Var, new b1(19), 3).invoke();
            return z ? m0(g2Var, intFunction) : g2Var;
        }
        if (jK0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jK0);
        new q3(spliterator, v3Var, objArr).invoke();
        return new j2(objArr);
    }

    public static void Q(c2 c2Var, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            c2Var.g((IntConsumer) consumer);
        } else {
            if (g8.f18703a) {
                g8.a(c2Var.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.h0) c2Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static r5 z0(j1 j1Var, long j10, long j11) {
        if (j10 < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j10);
        }
        return new r5(j1Var, l0(j11), j10, j11);
    }

    public static void N(c2 c2Var, Integer[] numArr, int i10) {
        if (g8.f18703a) {
            g8.a(c2Var.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
            throw null;
        }
        int[] iArr = (int[]) c2Var.b();
        for (int i11 = 0; i11 < iArr.length; i11++) {
            numArr[i10 + i11] = Integer.valueOf(iArr[i11]);
        }
    }

    public static c2 T(c2 c2Var, long j10, long j11) {
        if (j10 == 0 && j11 == c2Var.count()) {
            return c2Var;
        }
        long j12 = j11 - j10;
        j$.util.h0 h0Var = (j$.util.h0) c2Var.spliterator();
        w1 w1VarS0 = s0(j12);
        w1VarS0.c(j12);
        for (int i10 = 0; i10 < j10 && h0Var.tryAdvance((IntConsumer) new b2(0)); i10++) {
        }
        if (j11 == c2Var.count()) {
            h0Var.forEachRemaining((IntConsumer) w1VarS0);
        } else {
            for (int i11 = 0; i11 < j12 && h0Var.tryAdvance((IntConsumer) w1VarS0); i11++) {
            }
        }
        w1VarS0.end();
        return w1VarS0.build();
    }

    public static c2 c0(v3 v3Var, Spliterator spliterator, boolean z) {
        long jK0 = v3Var.k0(spliterator);
        if (jK0 < 0 || !spliterator.hasCharacteristics(16384)) {
            c2 c2Var = (c2) new l2(v3Var, spliterator, new b1(15), new b1(16), 1).invoke();
            return z ? o0(c2Var) : c2Var;
        }
        if (jK0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jK0];
        new o3(spliterator, v3Var, iArr).invoke();
        return new b3(iArr);
    }

    public static e2 d0(v3 v3Var, Spliterator spliterator, boolean z) {
        long jK0 = v3Var.k0(spliterator);
        if (jK0 < 0 || !spliterator.hasCharacteristics(16384)) {
            e2 e2Var = (e2) new l2(v3Var, spliterator, new b1(17), new b1(18), 2).invoke();
            return z ? p0(e2Var) : e2Var;
        }
        if (jK0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jK0];
        new p3(spliterator, v3Var, jArr).invoke();
        return new k3(jArr);
    }

    public static void R(e2 e2Var, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            e2Var.g((LongConsumer) consumer);
        } else {
            if (g8.f18703a) {
                g8.a(e2Var.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.k0) e2Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static void O(e2 e2Var, Long[] lArr, int i10) {
        if (g8.f18703a) {
            g8.a(e2Var.getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
            throw null;
        }
        long[] jArr = (long[]) e2Var.b();
        for (int i11 = 0; i11 < jArr.length; i11++) {
            lArr[i10 + i11] = Long.valueOf(jArr[i11]);
        }
    }

    public static e2 U(e2 e2Var, long j10, long j11) {
        if (j10 == 0 && j11 == e2Var.count()) {
            return e2Var;
        }
        long j12 = j11 - j10;
        j$.util.k0 k0Var = (j$.util.k0) e2Var.spliterator();
        x1 x1VarT0 = t0(j12);
        x1VarT0.c(j12);
        for (int i10 = 0; i10 < j10 && k0Var.tryAdvance((LongConsumer) new d2(0)); i10++) {
        }
        if (j11 == e2Var.count()) {
            k0Var.forEachRemaining((LongConsumer) x1VarT0);
        } else {
            for (int i11 = 0; i11 < j12 && k0Var.tryAdvance((LongConsumer) x1VarT0); i11++) {
            }
        }
        x1VarT0.end();
        return x1VarT0.build();
    }

    public static a2 b0(v3 v3Var, Spliterator spliterator, boolean z) {
        long jK0 = v3Var.k0(spliterator);
        if (jK0 < 0 || !spliterator.hasCharacteristics(16384)) {
            a2 a2Var = (a2) new l2(v3Var, spliterator, new b1(13), new b1(14), 0).invoke();
            return z ? n0(a2Var) : a2Var;
        }
        if (jK0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jK0];
        new n3(spliterator, v3Var, dArr).invoke();
        return new s2(dArr);
    }

    public static t5 v0(a0 a0Var, long j10, long j11) {
        if (j10 < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j10);
        }
        return new t5(a0Var, l0(j11), j10, j11);
    }

    public static g2 m0(g2 g2Var, IntFunction intFunction) {
        if (g2Var.o() <= 0) {
            return g2Var;
        }
        long jCount = g2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        new u3(g2Var, objArr, 1).invoke();
        return new j2(objArr);
    }

    public static void P(a2 a2Var, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            a2Var.g((DoubleConsumer) consumer);
        } else {
            if (g8.f18703a) {
                g8.a(a2Var.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.e0) a2Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static c2 o0(c2 c2Var) {
        if (c2Var.o() <= 0) {
            return c2Var;
        }
        long jCount = c2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jCount];
        new t3(c2Var, iArr, 0).invoke();
        return new b3(iArr);
    }

    public static void M(a2 a2Var, Double[] dArr, int i10) {
        if (g8.f18703a) {
            g8.a(a2Var.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
            throw null;
        }
        double[] dArr2 = (double[]) a2Var.b();
        for (int i11 = 0; i11 < dArr2.length; i11++) {
            dArr[i10 + i11] = Double.valueOf(dArr2[i11]);
        }
    }

    public static a2 S(a2 a2Var, long j10, long j11) {
        if (j10 == 0 && j11 == a2Var.count()) {
            return a2Var;
        }
        long j12 = j11 - j10;
        j$.util.e0 e0Var = (j$.util.e0) a2Var.spliterator();
        v1 v1VarH0 = h0(j12);
        v1VarH0.c(j12);
        for (int i10 = 0; i10 < j10 && e0Var.tryAdvance((DoubleConsumer) new z1(0)); i10++) {
        }
        if (j11 == a2Var.count()) {
            e0Var.forEachRemaining((DoubleConsumer) v1VarH0);
        } else {
            for (int i11 = 0; i11 < j12 && e0Var.tryAdvance((DoubleConsumer) v1VarH0); i11++) {
            }
        }
        v1VarH0.end();
        return v1VarH0.build();
    }

    public static e2 p0(e2 e2Var) {
        if (e2Var.o() <= 0) {
            return e2Var;
        }
        long jCount = e2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jCount];
        new t3(e2Var, jArr, 0).invoke();
        return new k3(jArr);
    }

    public static int l0(long j10) {
        return (j10 != -1 ? y6.f18977u : 0) | y6.f18976t;
    }

    public static a2 n0(a2 a2Var) {
        if (a2Var.o() <= 0) {
            return a2Var;
        }
        long jCount = a2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jCount];
        new t3(a2Var, dArr, 0).invoke();
        return new s2(dArr);
    }

    @Override // j$.util.stream.e8
    public Object e(a aVar, Spliterator spliterator) {
        q4 q4VarD0 = D0();
        aVar.E0(spliterator, q4VarD0);
        return q4VarD0.get();
    }

    @Override // j$.util.stream.e8
    public Object h(v3 v3Var, Spliterator spliterator) {
        return ((q4) new x4(this, v3Var, spliterator).invoke()).get();
    }
}
