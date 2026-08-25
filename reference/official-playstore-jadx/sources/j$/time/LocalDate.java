package j$.time;

import androidx.media3.common.C;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalDate implements Temporal, j$.time.temporal.l, ChronoLocalDate, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LocalDate f18100d = of(-999999999, 1, 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final LocalDate f18101e = of(999999999, 12, 31);
    private static final long serialVersionUID = 2942565459149668126L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final short f18103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final short f18104c;

    static {
        of(1970, 1, 1);
    }

    public static LocalDate now() {
        return d0(Clock.b());
    }

    public static LocalDate d0(a aVar) {
        Objects.requireNonNull(aVar, "clock");
        Instant instant = aVar.instant();
        ZoneId zoneId = aVar.f18132a;
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return e0(j$.com.android.tools.r8.a.Q(instant.getEpochSecond() + ((long) zoneId.V().d(instant).f18126b), 86400));
    }

    public static LocalDate of(int i10, int i11, int i12) {
        j$.time.temporal.a.YEAR.H(i10);
        j$.time.temporal.a.MONTH_OF_YEAR.H(i11);
        j$.time.temporal.a.DAY_OF_MONTH.H(i12);
        return V(i10, i11, i12);
    }

    public static LocalDate f0(int i10, int i11) {
        long j10 = i10;
        j$.time.temporal.a.YEAR.H(j10);
        j$.time.temporal.a.DAY_OF_YEAR.H(i11);
        boolean zT = j$.time.chrono.p.f18175d.T(j10);
        if (i11 == 366 && !zT) {
            throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + i10 + "' is not a leap year");
        }
        Month monthX = Month.X(((i11 - 1) / 31) + 1);
        if (i11 > (monthX.V(zT) + monthX.U(zT)) - 1) {
            monthX = Month.f18116a[((((int) 1) + 12) + monthX.ordinal()) % 12];
        }
        return new LocalDate(i10, monthX.getValue(), (i11 - monthX.U(zT)) + 1);
    }

    public static LocalDate e0(long j10) {
        long j11;
        j$.time.temporal.a.EPOCH_DAY.H(j10);
        long j12 = 719468 + j10;
        if (j12 < 0) {
            long j13 = ((j10 + 719469) / 146097) - 1;
            j11 = j13 * 400;
            j12 += (-j13) * 146097;
        } else {
            j11 = 0;
        }
        long j14 = ((j12 * 400) + 591) / 146097;
        long j15 = j12 - ((j14 / 400) + (((j14 / 4) + (j14 * 365)) - (j14 / 100)));
        if (j15 < 0) {
            j14--;
            j15 = j12 - ((j14 / 400) + (((j14 / 4) + (365 * j14)) - (j14 / 100)));
        }
        int i10 = (int) j15;
        int i11 = ((i10 * 5) + 2) / 153;
        int i12 = ((i11 + 2) % 12) + 1;
        int i13 = (i10 - (((i11 * 306) + 5) / 10)) + 1;
        long j16 = j14 + j11 + ((long) (i11 / 10));
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return new LocalDate(aVar.f18349b.a(j16, aVar), i12, i13);
    }

    public static LocalDate W(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        LocalDate localDate = (LocalDate) temporalAccessor.G(j$.time.temporal.p.f18371f);
        if (localDate != null) {
            return localDate;
        }
        throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    public static LocalDate parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return (LocalDate) dateTimeFormatter.a(charSequence, new c(1));
    }

    public static LocalDate V(int i10, int i11, int i12) {
        int i13 = 28;
        if (i12 > 28) {
            if (i11 != 2) {
                i13 = (i11 == 4 || i11 == 6 || i11 == 9 || i11 == 11) ? 30 : 31;
            } else if (j$.time.chrono.p.f18175d.T(i10)) {
                i13 = 29;
            }
            if (i12 > i13) {
                if (i12 == 29) {
                    throw new DateTimeException("Invalid date 'February 29' as '" + i10 + "' is not a leap year");
                }
                throw new DateTimeException("Invalid date '" + Month.X(i11).name() + " " + i12 + "'");
            }
        }
        return new LocalDate(i10, i11, i12);
    }

    public static LocalDate j0(int i10, int i11, int i12) {
        if (i11 == 2) {
            i12 = Math.min(i12, j$.time.chrono.p.f18175d.T((long) i10) ? 29 : 28);
        } else if (i11 == 4 || i11 == 6 || i11 == 9 || i11 == 11) {
            i12 = Math.min(i12, 30);
        }
        return new LocalDate(i10, i11, i12);
    }

    public LocalDate(int i10, int i11, int i12) {
        this.f18102a = i10;
        this.f18103b = (short) i11;
        this.f18104c = (short) i12;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.q(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.m(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        if (!aVar.isDateBased()) {
            throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
        int i10 = e.f18195a[aVar.ordinal()];
        if (i10 == 1) {
            return j$.time.temporal.s.f(1L, lengthOfMonth());
        }
        if (i10 == 2) {
            return j$.time.temporal.s.f(1L, R());
        }
        if (i10 != 3) {
            return i10 != 4 ? aVar.f18349b : getYear() <= 0 ? j$.time.temporal.s.f(1L, C.NANOS_PER_SECOND) : j$.time.temporal.s.f(1L, 999999999L);
        }
        return j$.time.temporal.s.f(1L, (getMonth() != Month.FEBRUARY || t()) ? 5L : 4L);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return X(oVar);
        }
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.EPOCH_DAY) {
                return I();
            }
            if (oVar == j$.time.temporal.a.PROLEPTIC_MONTH) {
                return Z();
            }
            return X(oVar);
        }
        return oVar.C(this);
    }

    public final int X(j$.time.temporal.o oVar) {
        switch (e.f18195a[((j$.time.temporal.a) oVar).ordinal()]) {
            case 1:
                return this.f18104c;
            case 2:
                return Y();
            case 3:
                return ((this.f18104c - 1) / 7) + 1;
            case 4:
                int i10 = this.f18102a;
                return i10 >= 1 ? i10 : 1 - i10;
            case 5:
                return getDayOfWeek().getValue();
            case 6:
                return ((this.f18104c - 1) % 7) + 1;
            case 7:
                return ((Y() - 1) % 7) + 1;
            case 8:
                throw new j$.time.temporal.r("Invalid field 'EpochDay' for get() method, use getLong() instead");
            case 9:
                return ((Y() - 1) / 7) + 1;
            case 10:
                return this.f18103b;
            case 11:
                throw new j$.time.temporal.r("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
            case 12:
                return this.f18102a;
            case 13:
                return this.f18102a >= 1 ? 1 : 0;
            default:
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
    }

    public final long Z() {
        return ((((long) this.f18102a) * 12) + ((long) this.f18103b)) - 1;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology a() {
        return j$.time.chrono.p.f18175d;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final j$.time.chrono.j L() {
        return getYear() >= 1 ? j$.time.chrono.q.CE : j$.time.chrono.q.BCE;
    }

    public int getYear() {
        return this.f18102a;
    }

    public int getMonthValue() {
        return this.f18103b;
    }

    public Month getMonth() {
        return Month.X(this.f18103b);
    }

    public int getDayOfMonth() {
        return this.f18104c;
    }

    public final int Y() {
        return (getMonth().U(t()) + this.f18104c) - 1;
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.U(((int) j$.com.android.tools.r8.a.P(I() + 3, 7)) + 1);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final boolean t() {
        return j$.time.chrono.p.f18175d.T(this.f18102a);
    }

    public int lengthOfMonth() {
        short s10 = this.f18103b;
        return s10 != 2 ? (s10 == 4 || s10 == 6 || s10 == 9 || s10 == 11) ? 30 : 31 : t() ? 29 : 28;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final int R() {
        return t() ? 366 : 365;
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final LocalDate m(j$.time.temporal.l lVar) {
        if (lVar instanceof LocalDate) {
            return (LocalDate) lVar;
        }
        return (LocalDate) lVar.r(this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public final LocalDate c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (LocalDate) oVar.G(this, j10);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        aVar.H(j10);
        switch (e.f18195a[aVar.ordinal()]) {
            case 1:
                int i10 = (int) j10;
                if (this.f18104c != i10) {
                    return of(this.f18102a, this.f18103b, i10);
                }
                return this;
            case 2:
                int i11 = (int) j10;
                if (Y() != i11) {
                    return f0(this.f18102a, i11);
                }
                return this;
            case 3:
                return h0(j10 - H(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH));
            case 4:
                if (this.f18102a < 1) {
                    j10 = 1 - j10;
                }
                return m0((int) j10);
            case 5:
                return plusDays(j10 - ((long) getDayOfWeek().getValue()));
            case 6:
                return plusDays(j10 - H(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 7:
                return plusDays(j10 - H(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 8:
                return e0(j10);
            case 9:
                return h0(j10 - H(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR));
            case 10:
                int i12 = (int) j10;
                if (this.f18103b != i12) {
                    j$.time.temporal.a.MONTH_OF_YEAR.H(i12);
                    return j0(this.f18102a, i12, this.f18104c);
                }
                return this;
            case 11:
                return plusMonths(j10 - Z());
            case 12:
                return m0((int) j10);
            case 13:
                if (H(j$.time.temporal.a.ERA) != j10) {
                    return m0(1 - this.f18102a);
                }
                return this;
            default:
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
    }

    public final LocalDate m0(int i10) {
        if (this.f18102a == i10) {
            return this;
        }
        j$.time.temporal.a.YEAR.H(i10);
        return j0(i10, this.f18103b, this.f18104c);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate O(j$.time.temporal.n nVar) {
        if (b.b(nVar)) {
            n nVar2 = (n) nVar;
            return plusMonths((((long) nVar2.f18320a) * 12) + ((long) nVar2.f18321b)).plusDays(nVar2.f18322c);
        }
        Objects.requireNonNull(nVar, "amountToAdd");
        return (LocalDate) ((n) nVar).l(this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final LocalDate d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (LocalDate) qVar.l(this, j10);
        }
        switch (e.f18196b[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return plusDays(j10);
            case 2:
                return h0(j10);
            case 3:
                return plusMonths(j10);
            case 4:
                return i0(j10);
            case 5:
                return i0(j$.com.android.tools.r8.a.R(j10, 10));
            case 6:
                return i0(j$.com.android.tools.r8.a.R(j10, 100));
            case 7:
                return i0(j$.com.android.tools.r8.a.R(j10, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.L(H(aVar), j10), aVar);
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public final LocalDate i0(long j10) {
        if (j10 == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return j0(aVar.f18349b.a(((long) this.f18102a) + j10, aVar), this.f18103b, this.f18104c);
    }

    public LocalDate plusMonths(long j10) {
        if (j10 == 0) {
            return this;
        }
        long j11 = (((long) this.f18102a) * 12) + ((long) (this.f18103b - 1)) + j10;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        long j12 = 12;
        return j0(aVar.f18349b.a(j$.com.android.tools.r8.a.Q(j11, j12), aVar), ((int) j$.com.android.tools.r8.a.P(j11, j12)) + 1, this.f18104c);
    }

    public final LocalDate h0(long j10) {
        return plusDays(j$.com.android.tools.r8.a.R(j10, 7));
    }

    public LocalDate plusDays(long j10) {
        if (j10 == 0) {
            return this;
        }
        long j11 = ((long) this.f18104c) + j10;
        if (j11 > 0) {
            if (j11 <= 28) {
                return new LocalDate(this.f18102a, this.f18103b, (int) j11);
            }
            if (j11 <= 59) {
                long jLengthOfMonth = lengthOfMonth();
                if (j11 <= jLengthOfMonth) {
                    return new LocalDate(this.f18102a, this.f18103b, (int) j11);
                }
                short s10 = this.f18103b;
                if (s10 < 12) {
                    return new LocalDate(this.f18102a, s10 + 1, (int) (j11 - jLengthOfMonth));
                }
                j$.time.temporal.a.YEAR.H(this.f18102a + 1);
                return new LocalDate(this.f18102a + 1, 1, (int) (j11 - jLengthOfMonth));
            }
        }
        return e0(j$.com.android.tools.r8.a.L(I(), j10));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final LocalDate v(long j10, j$.time.temporal.q qVar) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, qVar).d(1L, qVar) : d(-j10, qVar);
    }

    public LocalDate minusMonths(long j10) {
        return j10 == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1L) : plusMonths(-j10);
    }

    public LocalDate minusDays(long j10) {
        return j10 == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j10);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        return cVar == j$.time.temporal.p.f18371f ? this : j$.com.android.tools.r8.a.s(this, cVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return j$.com.android.tools.r8.a.a(this, temporal);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        LocalDate localDateW = W(temporal);
        if (!(qVar instanceof ChronoUnit)) {
            return qVar.between(this, localDateW);
        }
        switch (e.f18196b[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return localDateW.I() - I();
            case 2:
                return (localDateW.I() - I()) / 7;
            case 3:
                return c0(localDateW);
            case 4:
                return c0(localDateW) / 12;
            case 5:
                return c0(localDateW) / 120;
            case 6:
                return c0(localDateW) / 1200;
            case 7:
                return c0(localDateW) / 12000;
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return localDateW.H(aVar) - H(aVar);
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public final long c0(LocalDate localDate) {
        return (((localDate.Z() * 32) + ((long) localDate.getDayOfMonth())) - ((Z() * 32) + ((long) getDayOfMonth()))) / 32;
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    /* JADX INFO: renamed from: atTime, reason: merged with bridge method [inline-methods] */
    public LocalDateTime J(LocalTime localTime) {
        return LocalDateTime.X(this, localTime);
    }

    public LocalDateTime atStartOfDay() {
        return LocalDateTime.X(this, LocalTime.MIDNIGHT);
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final long I() {
        long j10 = this.f18102a;
        long j11 = this.f18103b;
        long j12 = 365 * j10;
        long j13 = (((367 * j11) - 362) / 12) + (j10 >= 0 ? ((j10 + 399) / 400) + (((3 + j10) / 4) - ((99 + j10) / 100)) + j12 : j12 - ((j10 / (-400)) + ((j10 / (-4)) - (j10 / (-100))))) + ((long) (this.f18104c - 1));
        if (j11 > 2) {
            j13 = !t() ? j13 - 2 : j13 - 1;
        }
        return j13 - 719528;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public final int compareTo(ChronoLocalDate chronoLocalDate) {
        if (chronoLocalDate instanceof LocalDate) {
            return U((LocalDate) chronoLocalDate);
        }
        return j$.com.android.tools.r8.a.e(this, chronoLocalDate);
    }

    public final int U(LocalDate localDate) {
        int i10 = this.f18102a - localDate.f18102a;
        if (i10 != 0) {
            return i10;
        }
        int i11 = this.f18103b - localDate.f18103b;
        return i11 == 0 ? this.f18104c - localDate.f18104c : i11;
    }

    public boolean isAfter(ChronoLocalDate chronoLocalDate) {
        return chronoLocalDate instanceof LocalDate ? U((LocalDate) chronoLocalDate) > 0 : I() > chronoLocalDate.I();
    }

    public final boolean a0(ChronoLocalDate chronoLocalDate) {
        return chronoLocalDate instanceof LocalDate ? U((LocalDate) chronoLocalDate) < 0 : I() < chronoLocalDate.I();
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocalDate) && U((LocalDate) obj) == 0;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        int i10 = this.f18102a;
        return (((i10 << 11) + (this.f18103b << 6)) + this.f18104c) ^ (i10 & (-2048));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public String toString() {
        int i10 = this.f18102a;
        short s10 = this.f18103b;
        short s11 = this.f18104c;
        int iAbs = Math.abs(i10);
        StringBuilder sb2 = new StringBuilder(10);
        if (iAbs >= 1000) {
            if (i10 > 9999) {
                sb2.append('+');
            }
            sb2.append(i10);
        } else if (i10 < 0) {
            sb2.append(i10 - 10000);
            sb2.deleteCharAt(1);
        } else {
            sb2.append(i10 + 10000);
            sb2.deleteCharAt(0);
        }
        sb2.append(s10 < 10 ? "-0" : "-");
        sb2.append((int) s10);
        sb2.append(s11 < 10 ? "-0" : "-");
        sb2.append((int) s11);
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
