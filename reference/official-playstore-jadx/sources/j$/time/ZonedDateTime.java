package j$.time;

import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocalDateTime f18128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ZoneOffset f18129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ZoneId f18130c;

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* synthetic */ long toEpochSecond() {
        return j$.com.android.tools.r8.a.x(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        return j$.com.android.tools.r8.a.g(this, chronoZonedDateTime);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Chronology a() {
        return ((LocalDate) f()).a();
    }

    public static ZonedDateTime V(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ZonedDateTime(localDateTime, zoneId, (ZoneOffset) zoneId);
        }
        j$.time.zone.f fVarV = zoneId.V();
        List listF = fVarV.f(localDateTime);
        if (listF.size() == 1) {
            zoneOffset = (ZoneOffset) listF.get(0);
        } else if (listF.size() != 0) {
            if (zoneOffset == null || !listF.contains(zoneOffset)) {
                zoneOffset = (ZoneOffset) Objects.requireNonNull((ZoneOffset) listF.get(0), "offset");
            }
        } else {
            Object objE = fVarV.e(localDateTime);
            j$.time.zone.b bVar = objE instanceof j$.time.zone.b ? (j$.time.zone.b) objE : null;
            localDateTime = localDateTime.a0(Duration.m(bVar.f18393d.f18126b - bVar.f18392c.f18126b, 0).f18096a);
            zoneOffset = bVar.f18393d;
        }
        return new ZonedDateTime(localDateTime, zoneId, zoneOffset);
    }

    public static ZonedDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return r(instant.getEpochSecond(), instant.getNano(), zoneId);
    }

    public static ZonedDateTime r(long j10, int i10, ZoneId zoneId) {
        ZoneOffset zoneOffsetD = zoneId.V().d(Instant.ofEpochSecond(j10, i10));
        return new ZonedDateTime(LocalDateTime.Y(j10, i10, zoneOffsetD), zoneId, zoneOffsetD);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Instant toInstant() {
        return Instant.ofEpochSecond(toEpochSecond(), b().f18115d);
    }

    public static ZonedDateTime U(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ZonedDateTime) {
            return (ZonedDateTime) temporalAccessor;
        }
        try {
            ZoneId zoneIdU = ZoneId.U(temporalAccessor);
            j$.time.temporal.a aVar = j$.time.temporal.a.INSTANT_SECONDS;
            if (!temporalAccessor.e(aVar)) {
                return V(LocalDateTime.X(LocalDate.W(temporalAccessor), LocalTime.W(temporalAccessor)), zoneIdU, null);
            }
            return r(temporalAccessor.H(aVar), temporalAccessor.l(j$.time.temporal.a.NANO_OF_SECOND), zoneIdU);
        } catch (DateTimeException e5) {
            throw new DateTimeException("Unable to obtain ZonedDateTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName(), e5);
        }
    }

    public static ZonedDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return (ZonedDateTime) dateTimeFormatter.a(charSequence, new c(4));
    }

    public ZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        this.f18128a = localDateTime;
        this.f18129b = zoneOffset;
        this.f18130c = zoneId;
    }

    public final ZonedDateTime X(LocalDateTime localDateTime) {
        return V(localDateTime, this.f18130c, this.f18129b);
    }

    public final ZonedDateTime Y(ZoneOffset zoneOffset) {
        return (zoneOffset.equals(this.f18129b) || !this.f18130c.V().f(this.f18128a).contains(zoneOffset)) ? this : new ZonedDateTime(this.f18128a, this.f18130c, zoneOffset);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return true;
        }
        return oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.INSTANT_SECONDS || oVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) oVar).f18349b;
            }
            return this.f18128a.n(oVar);
        }
        return oVar.m(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i10 = u.f18386a[((j$.time.temporal.a) oVar).ordinal()];
            if (i10 == 1) {
                throw new j$.time.temporal.r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i10 == 2) {
                return this.f18129b.f18126b;
            }
            return this.f18128a.l(oVar);
        }
        return j$.com.android.tools.r8.a.l(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i10 = u.f18386a[((j$.time.temporal.a) oVar).ordinal()];
        return i10 != 1 ? i10 != 2 ? this.f18128a.H(oVar) : this.f18129b.f18126b : j$.com.android.tools.r8.a.x(this);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneOffset h() {
        return this.f18129b;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneId F() {
        return this.f18130c;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime A(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        return this.f18130c.equals(zoneId) ? this : V(this.f18128a, zoneId, this.f18129b);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public final ZonedDateTime i(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        if (this.f18130c.equals(zoneId)) {
            return this;
        }
        LocalDateTime localDateTime = this.f18128a;
        ZoneOffset zoneOffset = this.f18129b;
        localDateTime.getClass();
        return r(j$.com.android.tools.r8.a.w(localDateTime, zoneOffset), this.f18128a.f18108b.f18115d, zoneId);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDateTime s() {
        return this.f18128a;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    /* JADX INFO: renamed from: toLocalDate, reason: merged with bridge method [inline-methods] */
    public LocalDate f() {
        return this.f18128a.f18107a;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalTime b() {
        return this.f18128a.f18108b;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        if (b.b(localDate)) {
            return X(LocalDateTime.X(localDate, this.f18128a.f18108b));
        }
        return (ZonedDateTime) localDate.r(this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            int i10 = u.f18386a[aVar.ordinal()];
            if (i10 == 1) {
                return r(j10, this.f18128a.f18108b.f18115d, this.f18130c);
            }
            if (i10 == 2) {
                return Y(ZoneOffset.d0(aVar.f18349b.a(j10, aVar)));
            }
            return X(this.f18128a.c(j10, oVar));
        }
        return (ZonedDateTime) oVar.G(this, j10);
    }

    public ZonedDateTime withDayOfMonth(int i10) {
        LocalDateTime localDateTime = this.f18128a;
        LocalDate localDateOf = localDateTime.f18107a;
        if (localDateOf.f18104c != i10) {
            localDateOf = LocalDate.of(localDateOf.f18102a, localDateOf.f18103b, i10);
        }
        return X(localDateTime.d0(localDateOf, localDateTime.f18108b));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public final ZonedDateTime d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (ZonedDateTime) qVar.l(this, j10);
        }
        ChronoUnit chronoUnit = (ChronoUnit) qVar;
        if (chronoUnit.compareTo(ChronoUnit.DAYS) >= 0 && chronoUnit != ChronoUnit.FOREVER) {
            return X(this.f18128a.d(j10, qVar));
        }
        LocalDateTime localDateTimeZ = this.f18128a.d(j10, qVar);
        ZoneOffset zoneOffset = this.f18129b;
        ZoneId zoneId = this.f18130c;
        Objects.requireNonNull(localDateTimeZ, "localDateTime");
        Objects.requireNonNull(zoneOffset, "offset");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId.V().f(localDateTimeZ).contains(zoneOffset)) {
            return new ZonedDateTime(localDateTimeZ, zoneId, zoneOffset);
        }
        localDateTimeZ.getClass();
        return r(j$.com.android.tools.r8.a.w(localDateTimeZ, zoneOffset), localDateTimeZ.f18108b.f18115d, zoneId);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18371f) {
            return f();
        }
        return j$.com.android.tools.r8.a.u(this, cVar);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        ZonedDateTime zonedDateTimeU = U(temporal);
        if (qVar instanceof ChronoUnit) {
            ZonedDateTime zonedDateTimeI = zonedDateTimeU.i(this.f18130c);
            ChronoUnit chronoUnit = (ChronoUnit) qVar;
            if (chronoUnit.compareTo(ChronoUnit.DAYS) >= 0 && chronoUnit != ChronoUnit.FOREVER) {
                return this.f18128a.g(zonedDateTimeI.f18128a, qVar);
            }
            return new OffsetDateTime(this.f18128a, this.f18129b).g(new OffsetDateTime(zonedDateTimeI.f18128a, zonedDateTimeI.f18129b), qVar);
        }
        return qVar.between(this, zonedDateTimeU);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZonedDateTime) {
            ZonedDateTime zonedDateTime = (ZonedDateTime) obj;
            if (this.f18128a.equals(zonedDateTime.f18128a) && this.f18129b.equals(zonedDateTime.f18129b) && this.f18130c.equals(zonedDateTime.f18130c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f18128a.hashCode() ^ this.f18129b.f18126b) ^ Integer.rotateLeft(this.f18130c.hashCode(), 3);
    }

    public final String toString() {
        String str = this.f18128a.toString() + this.f18129b.f18127c;
        ZoneOffset zoneOffset = this.f18129b;
        ZoneId zoneId = this.f18130c;
        if (zoneOffset == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    private Object writeReplace() {
        return new o((byte) 6, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
