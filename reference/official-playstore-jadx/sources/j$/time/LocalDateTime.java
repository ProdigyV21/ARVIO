package j$.time;

import androidx.media3.common.C;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
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
public final class LocalDateTime implements Temporal, j$.time.temporal.l, ChronoLocalDateTime<LocalDate>, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LocalDateTime f18105c = X(LocalDate.f18100d, LocalTime.f18109e);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LocalDateTime f18106d = X(LocalDate.f18101e, LocalTime.f18110f);
    private static final long serialVersionUID = 6207766400415563566L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocalDate f18107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LocalTime f18108b;

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final /* synthetic */ long toEpochSecond(ZoneOffset zoneOffset) {
        return j$.com.android.tools.r8.a.w(this, zoneOffset);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final Chronology a() {
        return ((LocalDate) f()).a();
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    /* JADX INFO: renamed from: atZone, reason: merged with bridge method [inline-methods] */
    public ZonedDateTime D(ZoneId zoneId) {
        return ZonedDateTime.V(this, zoneId, null);
    }

    public static LocalDateTime X(LocalDate localDate, LocalTime localTime) {
        Objects.requireNonNull(localDate, "date");
        Objects.requireNonNull(localTime, "time");
        return new LocalDateTime(localDate, localTime);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(((LocalDate) f()).I(), j$.time.temporal.a.EPOCH_DAY).c(b().g0(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public static LocalDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return Y(instant.getEpochSecond(), instant.getNano(), zoneId.V().d(instant));
    }

    public static LocalDateTime Y(long j10, int i10, ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, "offset");
        long j11 = i10;
        j$.time.temporal.a.NANO_OF_SECOND.H(j11);
        long j12 = j10 + ((long) zoneOffset.f18126b);
        long j13 = 86400;
        return new LocalDateTime(LocalDate.e0(j$.com.android.tools.r8.a.Q(j12, j13)), LocalTime.Z((((long) ((int) j$.com.android.tools.r8.a.P(j12, j13))) * C.NANOS_PER_SECOND) + j11));
    }

    public static LocalDateTime V(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        }
        if (!(temporalAccessor instanceof ZonedDateTime)) {
            if (temporalAccessor instanceof OffsetDateTime) {
                return ((OffsetDateTime) temporalAccessor).toLocalDateTime();
            }
            try {
                return new LocalDateTime(LocalDate.W(temporalAccessor), LocalTime.W(temporalAccessor));
            } catch (DateTimeException e5) {
                throw new DateTimeException("Unable to obtain LocalDateTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName(), e5);
            }
        }
        return ((ZonedDateTime) temporalAccessor).f18128a;
    }

    public static LocalDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return (LocalDateTime) dateTimeFormatter.a(charSequence, new c(2));
    }

    public LocalDateTime(LocalDate localDate, LocalTime localTime) {
        this.f18107a = localDate;
        this.f18108b = localTime;
    }

    public final LocalDateTime d0(LocalDate localDate, LocalTime localTime) {
        return (this.f18107a == localDate && this.f18108b == localTime) ? this : new LocalDateTime(localDate, localTime);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar != null && oVar.l(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        return aVar.isDateBased() || aVar.U();
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) oVar).U()) {
                LocalTime localTime = this.f18108b;
                localTime.getClass();
                return j$.time.temporal.p.d(localTime, oVar);
            }
            return this.f18107a.n(oVar);
        }
        return oVar.m(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).U() ? this.f18108b.l(oVar) : this.f18107a.l(oVar);
        }
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).U() ? this.f18108b.H(oVar) : this.f18107a.H(oVar);
        }
        return oVar.C(this);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoLocalDate f() {
        return this.f18107a;
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalTime b() {
        return this.f18108b;
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public final LocalDateTime m(j$.time.temporal.l lVar) {
        if (lVar instanceof LocalDate) {
            return d0((LocalDate) lVar, this.f18108b);
        }
        if (lVar instanceof LocalTime) {
            return d0(this.f18107a, (LocalTime) lVar);
        }
        if (lVar instanceof LocalDateTime) {
            return (LocalDateTime) lVar;
        }
        return (LocalDateTime) lVar.r(this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public final LocalDateTime c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) oVar).U()) {
                return d0(this.f18107a, this.f18108b.c(j10, oVar));
            }
            return d0(this.f18107a.c(j10, oVar), this.f18108b);
        }
        return (LocalDateTime) oVar.G(this, j10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public final LocalDateTime d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (LocalDateTime) qVar.l(this, j10);
        }
        switch (f.f18197a[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return b0(this.f18107a, 0L, 0L, 0L, j10);
            case 2:
                LocalDateTime localDateTimeD0 = d0(this.f18107a.plusDays(j10 / 86400000000L), this.f18108b);
                return localDateTimeD0.b0(localDateTimeD0.f18107a, 0L, 0L, 0L, (j10 % 86400000000L) * 1000);
            case 3:
                LocalDateTime localDateTimeD02 = d0(this.f18107a.plusDays(j10 / 86400000), this.f18108b);
                return localDateTimeD02.b0(localDateTimeD02.f18107a, 0L, 0L, 0L, (j10 % 86400000) * 1000000);
            case 4:
                return a0(j10);
            case 5:
                return b0(this.f18107a, 0L, j10, 0L, 0L);
            case 6:
                return b0(this.f18107a, j10, 0L, 0L, 0L);
            case 7:
                LocalDateTime localDateTimeD03 = d0(this.f18107a.plusDays(j10 / 256), this.f18108b);
                return localDateTimeD03.b0(localDateTimeD03.f18107a, (j10 % 256) * 12, 0L, 0L, 0L);
            default:
                return d0(this.f18107a.d(j10, qVar), this.f18108b);
        }
    }

    public final LocalDateTime a0(long j10) {
        return b0(this.f18107a, 0L, 0L, j10, 0L);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    public final LocalDateTime b0(LocalDate localDate, long j10, long j11, long j12, long j13) {
        if ((j10 | j11 | j12 | j13) == 0) {
            return d0(localDate, this.f18108b);
        }
        long j14 = 1;
        long j15 = ((j10 % 24) * 3600000000000L) + ((j11 % 1440) * 60000000000L) + ((j12 % 86400) * C.NANOS_PER_SECOND) + (j13 % 86400000000000L);
        long jG0 = this.f18108b.g0();
        long j16 = (j15 * j14) + jG0;
        long jQ = j$.com.android.tools.r8.a.Q(j16, 86400000000000L) + (((j10 / 24) + (j11 / 1440) + (j12 / 86400) + (j13 / 86400000000000L)) * j14);
        long jP = j$.com.android.tools.r8.a.P(j16, 86400000000000L);
        return d0(localDate.plusDays(jQ), jP == jG0 ? this.f18108b : LocalTime.Z(jP));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18371f) {
            return this.f18107a;
        }
        return j$.com.android.tools.r8.a.t(this, cVar);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        long jR;
        long j10;
        LocalDateTime localDateTimeV = V(temporal);
        if (!(qVar instanceof ChronoUnit)) {
            return qVar.between(this, localDateTimeV);
        }
        ChronoUnit chronoUnit = (ChronoUnit) qVar;
        if (chronoUnit.compareTo(ChronoUnit.DAYS) >= 0) {
            LocalDate localDatePlusDays = localDateTimeV.f18107a;
            if (localDatePlusDays.isAfter(this.f18107a) && localDateTimeV.f18108b.compareTo(this.f18108b) < 0) {
                localDatePlusDays = localDatePlusDays.minusDays(1L);
            } else if (localDatePlusDays.a0(this.f18107a) && localDateTimeV.f18108b.compareTo(this.f18108b) > 0) {
                localDatePlusDays = localDatePlusDays.plusDays(1L);
            }
            return this.f18107a.g(localDatePlusDays, qVar);
        }
        LocalDate localDate = this.f18107a;
        LocalDate localDate2 = localDateTimeV.f18107a;
        localDate.getClass();
        long jI = localDate2.I() - localDate.I();
        if (jI == 0) {
            return this.f18108b.g(localDateTimeV.f18108b, qVar);
        }
        long jG0 = localDateTimeV.f18108b.g0() - this.f18108b.g0();
        if (jI > 0) {
            jR = jI - 1;
            j10 = jG0 + 86400000000000L;
        } else {
            jR = jI + 1;
            j10 = jG0 - 86400000000000L;
        }
        switch (f.f18197a[chronoUnit.ordinal()]) {
            case 1:
                jR = j$.com.android.tools.r8.a.R(jR, 86400000000000L);
                break;
            case 2:
                jR = j$.com.android.tools.r8.a.R(jR, 86400000000L);
                j10 /= 1000;
                break;
            case 3:
                jR = j$.com.android.tools.r8.a.R(jR, 86400000L);
                j10 /= 1000000;
                break;
            case 4:
                jR = j$.com.android.tools.r8.a.R(jR, 86400);
                j10 /= C.NANOS_PER_SECOND;
                break;
            case 5:
                jR = j$.com.android.tools.r8.a.R(jR, 1440);
                j10 /= 60000000000L;
                break;
            case 6:
                jR = j$.com.android.tools.r8.a.R(jR, 24);
                j10 /= 3600000000000L;
                break;
            case 7:
                jR = j$.com.android.tools.r8.a.R(jR, 2);
                j10 /= 43200000000000L;
                break;
        }
        return j$.com.android.tools.r8.a.L(jR, j10);
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public final int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return U((LocalDateTime) chronoLocalDateTime);
        }
        return j$.com.android.tools.r8.a.f(this, chronoLocalDateTime);
    }

    public final int U(LocalDateTime localDateTime) {
        int iU = this.f18107a.U(localDateTime.f18107a);
        return iU == 0 ? this.f18108b.compareTo(localDateTime.f18108b) : iU;
    }

    public final boolean W(ChronoLocalDateTime chronoLocalDateTime) {
        if (chronoLocalDateTime instanceof LocalDateTime) {
            return U((LocalDateTime) chronoLocalDateTime) < 0;
        }
        long jI = this.f18107a.I();
        long jI2 = chronoLocalDateTime.f().I();
        if (jI >= jI2) {
            return jI == jI2 && this.f18108b.g0() < chronoLocalDateTime.b().g0();
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) obj;
            if (this.f18107a.equals(localDateTime.f18107a) && this.f18108b.equals(localDateTime.f18108b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f18107a.hashCode() ^ this.f18108b.hashCode();
    }

    public final String toString() {
        return this.f18107a.toString() + "T" + this.f18108b.toString();
    }

    private Object writeReplace() {
        return new o((byte) 5, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
