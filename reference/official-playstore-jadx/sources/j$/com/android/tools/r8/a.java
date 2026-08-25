package j$.com.android.tools.r8;

import j$.time.Instant;
import j$.time.ZoneOffset;
import j$.time.c;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.j;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.o;
import j$.time.temporal.p;
import j$.time.temporal.r;
import j$.util.Comparator;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.l;
import j$.util.concurrent.t;
import j$.util.d1;
import j$.util.e0;
import j$.util.function.b;
import j$.util.function.g;
import j$.util.h0;
import j$.util.k0;
import j$.util.m;
import j$.util.n;
import j$.util.s;
import j$.util.w;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ int K(long j10) {
        int i10 = (int) j10;
        if (j10 == i10) {
            return i10;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long L(long j10, long j11) {
        long j12 = j10 + j11;
        if (((j11 ^ j10) < 0) || ((j10 ^ j12) >= 0)) {
            return j12;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ List M(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static /* synthetic */ Map.Entry N(Object obj, Object obj2) {
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(obj), Objects.requireNonNull(obj2));
    }

    public static /* synthetic */ boolean O(Unsafe unsafe, Object obj, long j10, l lVar) {
        while (true) {
            Unsafe unsafe2 = unsafe;
            Object obj2 = obj;
            long j11 = j10;
            l lVar2 = lVar;
            if (unsafe2.compareAndSwapObject(obj2, j11, (Object) null, lVar2)) {
                return true;
            }
            if (unsafe2.getObject(obj2, j11) != null) {
                return false;
            }
            unsafe = unsafe2;
            obj = obj2;
            j10 = j11;
            lVar = lVar2;
        }
    }

    public static /* synthetic */ long P(long j10, long j11) {
        long j12 = j10 % j11;
        if (j12 == 0) {
            return 0L;
        }
        return (((j10 ^ j11) >> 63) | 1) > 0 ? j12 : j12 + j11;
    }

    public static /* synthetic */ long Q(long j10, long j11) {
        long j12 = j10 / j11;
        return (j10 - (j11 * j12) != 0 && (((j10 ^ j11) >> 63) | 1) < 0) ? j12 - 1 : j12;
    }

    public static /* synthetic */ long R(long j10, long j11) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(~j11) + Long.numberOfLeadingZeros(j11) + Long.numberOfLeadingZeros(~j10) + Long.numberOfLeadingZeros(j10);
        if (iNumberOfLeadingZeros > 65) {
            return j10 * j11;
        }
        if (iNumberOfLeadingZeros >= 64) {
            if ((j11 != Long.MIN_VALUE) | (j10 >= 0)) {
                long j12 = j10 * j11;
                if (j10 == 0 || j12 / j10 == j11) {
                    return j12;
                }
            }
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long S(long j10, long j11) {
        long j12 = j10 - j11;
        if (((j11 ^ j10) >= 0) || ((j10 ^ j12) >= 0)) {
            return j12;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ Comparator U(Comparator comparator, Comparator comparator2) {
        return comparator instanceof j$.util.Comparator ? ((j$.util.Comparator) comparator).thenComparing(comparator2) : Comparator.CC.$default$thenComparing(comparator, comparator2);
    }

    public static Optional G(j$.util.Optional optional) {
        if (optional == null) {
            return null;
        }
        if (optional.isPresent()) {
            return Optional.of(optional.get());
        }
        return Optional.empty();
    }

    public static j$.util.Optional C(Optional optional) {
        if (optional == null) {
            return null;
        }
        if (optional.isPresent()) {
            return j$.util.Optional.of(optional.get());
        }
        return j$.util.Optional.empty();
    }

    public static j$.util.l D(OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        if (!optionalDouble.isPresent()) {
            return j$.util.l.f18568c;
        }
        return new j$.util.l(optionalDouble.getAsDouble());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.function.b] */
    public static b c(final DoubleConsumer doubleConsumer, final DoubleConsumer doubleConsumer2) {
        Objects.requireNonNull(doubleConsumer2);
        return new DoubleConsumer() { // from class: j$.util.function.b
            public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer3) {
                return j$.com.android.tools.r8.a.c(this, doubleConsumer3);
            }

            @Override // java.util.function.DoubleConsumer
            public final void accept(double d4) {
                doubleConsumer.accept(d4);
                doubleConsumer2.accept(d4);
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.function.g] */
    public static g d(final LongConsumer longConsumer, final LongConsumer longConsumer2) {
        Objects.requireNonNull(longConsumer2);
        return new LongConsumer() { // from class: j$.util.function.g
            public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer3) {
                return j$.com.android.tools.r8.a.d(this, longConsumer3);
            }

            @Override // java.util.function.LongConsumer
            public final void accept(long j10) {
                longConsumer.accept(j10);
                longConsumer2.accept(j10);
            }
        };
    }

    public static n F(OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        if (!optionalLong.isPresent()) {
            return n.f18576c;
        }
        return new n(optionalLong.getAsLong());
    }

    public static t b(BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(biConsumer2);
        return new t(1, biConsumer, biConsumer2);
    }

    public static m E(OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        if (!optionalInt.isPresent()) {
            return m.f18572c;
        }
        return new m(optionalInt.getAsInt());
    }

    public static void h(ConcurrentMap concurrentMap, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        for (Map.Entry entry : concurrentMap.entrySet()) {
            try {
                biConsumer.accept(entry.getKey(), entry.getValue());
            } catch (IllegalStateException unused) {
            }
        }
    }

    public static String T(Object obj, Object obj2) {
        String string;
        String string2;
        String str = "null";
        if (obj == null || (string = obj.toString()) == null) {
            string = "null";
        }
        int length = string.length();
        if (obj2 != null && (string2 = obj2.toString()) != null) {
            str = string2;
        }
        int length2 = str.length();
        char[] cArr = new char[length + length2 + 1];
        string.getChars(0, length, cArr, 0);
        cArr[length] = '=';
        str.getChars(0, length2, cArr, length + 1);
        return new String(cArr);
    }

    public static OptionalDouble H(j$.util.l lVar) {
        if (lVar == null) {
            return null;
        }
        boolean z = lVar.f18569a;
        if (!z) {
            return OptionalDouble.empty();
        }
        if (z) {
            return OptionalDouble.of(lVar.f18570b);
        }
        throw new NoSuchElementException("No value present");
    }

    public static OptionalInt I(m mVar) {
        if (mVar == null) {
            return null;
        }
        boolean z = mVar.f18573a;
        if (!z) {
            return OptionalInt.empty();
        }
        if (z) {
            return OptionalInt.of(mVar.f18574b);
        }
        throw new NoSuchElementException("No value present");
    }

    public static OptionalLong J(n nVar) {
        if (nVar == null) {
            return null;
        }
        boolean z = nVar.f18577a;
        if (!z) {
            return OptionalLong.empty();
        }
        if (z) {
            return OptionalLong.of(nVar.f18578b);
        }
        throw new NoSuchElementException("No value present");
    }

    public static boolean r(j jVar, o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.ERA : oVar != null && oVar.l(jVar);
    }

    public static int l(ChronoZonedDateTime chronoZonedDateTime, o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i10 = j$.time.chrono.g.f18153a[((j$.time.temporal.a) oVar).ordinal()];
            if (i10 == 1) {
                throw new r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i10 != 2) {
                return chronoZonedDateTime.s().l(oVar);
            }
            return chronoZonedDateTime.h().f18126b;
        }
        return p.a(chronoZonedDateTime, oVar);
    }

    public static int m(j jVar, o oVar) {
        if (oVar == j$.time.temporal.a.ERA) {
            return jVar.getValue();
        }
        return p.a(jVar, oVar);
    }

    public static long o(j jVar, o oVar) {
        if (oVar == j$.time.temporal.a.ERA) {
            return jVar.getValue();
        }
        if (oVar instanceof j$.time.temporal.a) {
            throw new r(j$.time.b.a("Unsupported field: ", oVar));
        }
        return oVar.C(jVar);
    }

    public static Object v(j jVar, c cVar) {
        if (cVar == p.f18368c) {
            return ChronoUnit.ERAS;
        }
        return p.c(jVar, cVar);
    }

    public static Object t(ChronoLocalDateTime chronoLocalDateTime, c cVar) {
        if (cVar == p.f18366a || cVar == p.f18370e || cVar == p.f18369d) {
            return null;
        }
        if (cVar == p.f18372g) {
            return chronoLocalDateTime.b();
        }
        if (cVar == p.f18367b) {
            return chronoLocalDateTime.a();
        }
        if (cVar == p.f18368c) {
            return ChronoUnit.NANOS;
        }
        return cVar.h(chronoLocalDateTime);
    }

    public static boolean q(ChronoLocalDate chronoLocalDate, o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).isDateBased();
        }
        return oVar != null && oVar.l(chronoLocalDate);
    }

    public static long n(Spliterator spliterator) {
        if ((spliterator.characteristics() & 64) == 0) {
            return -1L;
        }
        return spliterator.estimateSize();
    }

    public static boolean p(Spliterator spliterator, int i10) {
        return (spliterator.characteristics() & i10) == i10;
    }

    public static Instant y(ChronoLocalDateTime chronoLocalDateTime, ZoneOffset zoneOffset) {
        return Instant.ofEpochSecond(chronoLocalDateTime.toEpochSecond(zoneOffset), chronoLocalDateTime.b().f18115d);
    }

    public static long w(ChronoLocalDateTime chronoLocalDateTime, ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, "offset");
        return ((chronoLocalDateTime.f().I() * 86400) + ((long) chronoLocalDateTime.b().h0())) - ((long) zoneOffset.f18126b);
    }

    public static Object u(ChronoZonedDateTime chronoZonedDateTime, c cVar) {
        if (cVar == p.f18370e || cVar == p.f18366a) {
            return chronoZonedDateTime.F();
        }
        if (cVar == p.f18369d) {
            return chronoZonedDateTime.h();
        }
        if (cVar == p.f18372g) {
            return chronoZonedDateTime.b();
        }
        if (cVar == p.f18367b) {
            return chronoZonedDateTime.a();
        }
        if (cVar == p.f18368c) {
            return ChronoUnit.NANOS;
        }
        return cVar.h(chronoZonedDateTime);
    }

    public static int f(ChronoLocalDateTime chronoLocalDateTime, ChronoLocalDateTime chronoLocalDateTime2) {
        int iS = chronoLocalDateTime.f().compareTo(chronoLocalDateTime2.f());
        return (iS == 0 && (iS = chronoLocalDateTime.b().U(chronoLocalDateTime2.b())) == 0) ? chronoLocalDateTime.a().y(chronoLocalDateTime2.a()) : iS;
    }

    public static Object s(ChronoLocalDate chronoLocalDate, c cVar) {
        if (cVar == p.f18366a || cVar == p.f18370e || cVar == p.f18369d || cVar == p.f18372g) {
            return null;
        }
        if (cVar == p.f18367b) {
            return chronoLocalDate.a();
        }
        if (cVar == p.f18368c) {
            return ChronoUnit.DAYS;
        }
        return cVar.h(chronoLocalDate);
    }

    public static Temporal a(ChronoLocalDate chronoLocalDate, Temporal temporal) {
        return temporal.c(chronoLocalDate.I(), j$.time.temporal.a.EPOCH_DAY);
    }

    public static long x(ChronoZonedDateTime chronoZonedDateTime) {
        return ((chronoZonedDateTime.f().I() * 86400) + ((long) chronoZonedDateTime.b().h0())) - ((long) chronoZonedDateTime.h().f18126b);
    }

    public static int g(ChronoZonedDateTime chronoZonedDateTime, ChronoZonedDateTime chronoZonedDateTime2) {
        int iCompare = Long.compare(chronoZonedDateTime.toEpochSecond(), chronoZonedDateTime2.toEpochSecond());
        return (iCompare == 0 && (iCompare = chronoZonedDateTime.b().f18115d - chronoZonedDateTime2.b().f18115d) == 0 && (iCompare = chronoZonedDateTime.s().M(chronoZonedDateTime2.s())) == 0 && (iCompare = chronoZonedDateTime.F().k().compareTo(chronoZonedDateTime2.F().k())) == 0) ? chronoZonedDateTime.a().y(chronoZonedDateTime2.a()) : iCompare;
    }

    public static boolean A(h0 h0Var, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            return h0Var.tryAdvance((IntConsumer) consumer);
        }
        if (d1.f18533a) {
            d1.a(h0Var.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return h0Var.tryAdvance((IntConsumer) new s(consumer, 0));
    }

    public static void j(h0 h0Var, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            h0Var.forEachRemaining((IntConsumer) consumer);
        } else {
            if (d1.f18533a) {
                d1.a(h0Var.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            h0Var.forEachRemaining((IntConsumer) new s(consumer, 0));
        }
    }

    public static int e(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
        int iCompare = Long.compare(chronoLocalDate.I(), chronoLocalDate2.I());
        if (iCompare != 0) {
            return iCompare;
        }
        return ((j$.time.chrono.a) chronoLocalDate.a()).y(chronoLocalDate2.a());
    }

    public static boolean B(k0 k0Var, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            return k0Var.tryAdvance((LongConsumer) consumer);
        }
        if (d1.f18533a) {
            d1.a(k0Var.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return k0Var.tryAdvance((LongConsumer) new w(consumer, 0));
    }

    public static void k(k0 k0Var, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            k0Var.forEachRemaining((LongConsumer) consumer);
        } else {
            if (d1.f18533a) {
                d1.a(k0Var.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            k0Var.forEachRemaining((LongConsumer) new w(consumer, 0));
        }
    }

    public static boolean z(e0 e0Var, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            return e0Var.tryAdvance((DoubleConsumer) consumer);
        }
        if (d1.f18533a) {
            d1.a(e0Var.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return e0Var.tryAdvance((DoubleConsumer) new j$.util.o(consumer, 0));
    }

    public static void i(e0 e0Var, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            e0Var.forEachRemaining((DoubleConsumer) consumer);
        } else {
            if (d1.f18533a) {
                d1.a(e0Var.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            e0Var.forEachRemaining((DoubleConsumer) new j$.util.o(consumer, 0));
        }
    }

    public Spliterator trySplit() {
        return null;
    }

    public boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        return false;
    }

    public void forEachRemaining(Object obj) {
        Objects.requireNonNull(obj);
    }

    public long estimateSize() {
        return 0L;
    }

    public int characteristics() {
        return 16448;
    }
}
