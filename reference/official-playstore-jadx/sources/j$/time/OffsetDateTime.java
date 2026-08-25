package j$.time;

import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class OffsetDateTime implements Temporal, j$.time.temporal.l, Comparable<OffsetDateTime>, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f18118c = 0;
    private static final long serialVersionUID = 2287754244819255394L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocalDateTime f18119a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ZoneOffset f18120b;

    @Override // java.lang.Comparable
    public final int compareTo(OffsetDateTime offsetDateTime) {
        int iCompare;
        OffsetDateTime offsetDateTime2 = offsetDateTime;
        if (this.f18120b.equals(offsetDateTime2.f18120b)) {
            iCompare = toLocalDateTime().compareTo(offsetDateTime2.toLocalDateTime());
        } else {
            LocalDateTime localDateTime = this.f18119a;
            ZoneOffset zoneOffset = this.f18120b;
            localDateTime.getClass();
            long jW = j$.com.android.tools.r8.a.w(localDateTime, zoneOffset);
            LocalDateTime localDateTime2 = offsetDateTime2.f18119a;
            ZoneOffset zoneOffset2 = offsetDateTime2.f18120b;
            localDateTime2.getClass();
            iCompare = Long.compare(jW, j$.com.android.tools.r8.a.w(localDateTime2, zoneOffset2));
            if (iCompare == 0) {
                iCompare = this.f18119a.f18108b.f18115d - offsetDateTime2.f18119a.f18108b.f18115d;
            }
        }
        return iCompare == 0 ? toLocalDateTime().compareTo(offsetDateTime2.toLocalDateTime()) : iCompare;
    }

    static {
        LocalDateTime localDateTime = LocalDateTime.f18105c;
        ZoneOffset zoneOffset = ZoneOffset.f18125g;
        localDateTime.getClass();
        new OffsetDateTime(localDateTime, zoneOffset);
        LocalDateTime localDateTime2 = LocalDateTime.f18106d;
        ZoneOffset zoneOffset2 = ZoneOffset.f18124f;
        localDateTime2.getClass();
        new OffsetDateTime(localDateTime2, zoneOffset2);
    }

    public static OffsetDateTime V(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        ZoneOffset zoneOffsetD = zoneId.V().d(instant);
        return new OffsetDateTime(LocalDateTime.Y(instant.getEpochSecond(), instant.getNano(), zoneOffsetD), zoneOffsetD);
    }

    public static OffsetDateTime U(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof OffsetDateTime) {
            return (OffsetDateTime) temporalAccessor;
        }
        try {
            ZoneOffset zoneOffsetA0 = ZoneOffset.a0(temporalAccessor);
            LocalDate localDate = (LocalDate) temporalAccessor.G(j$.time.temporal.p.f18371f);
            LocalTime localTime = (LocalTime) temporalAccessor.G(j$.time.temporal.p.f18372g);
            if (localDate != null && localTime != null) {
                return new OffsetDateTime(LocalDateTime.X(localDate, localTime), zoneOffsetA0);
            }
            return V(Instant.V(temporalAccessor), zoneOffsetA0);
        } catch (DateTimeException e5) {
            throw new DateTimeException("Unable to obtain OffsetDateTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName(), e5);
        }
    }

    public static OffsetDateTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.f18198g);
    }

    public static OffsetDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return (OffsetDateTime) dateTimeFormatter.a(charSequence, new c(3));
    }

    public OffsetDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        this.f18119a = (LocalDateTime) Objects.requireNonNull(localDateTime, "dateTime");
        this.f18120b = (ZoneOffset) Objects.requireNonNull(zoneOffset, "offset");
    }

    public final OffsetDateTime X(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return (this.f18119a == localDateTime && this.f18120b.equals(zoneOffset)) ? this : new OffsetDateTime(localDateTime, zoneOffset);
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
            if (oVar != j$.time.temporal.a.INSTANT_SECONDS && oVar != j$.time.temporal.a.OFFSET_SECONDS) {
                return this.f18119a.n(oVar);
            }
            return ((j$.time.temporal.a) oVar).f18349b;
        }
        return oVar.m(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i10 = k.f18314a[((j$.time.temporal.a) oVar).ordinal()];
            if (i10 == 1) {
                throw new j$.time.temporal.r("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i10 == 2) {
                return this.f18120b.f18126b;
            }
            return this.f18119a.l(oVar);
        }
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i10 = k.f18314a[((j$.time.temporal.a) oVar).ordinal()];
        if (i10 != 1) {
            return i10 != 2 ? this.f18119a.H(oVar) : this.f18120b.f18126b;
        }
        LocalDateTime localDateTime = this.f18119a;
        ZoneOffset zoneOffset = this.f18120b;
        localDateTime.getClass();
        return j$.com.android.tools.r8.a.w(localDateTime, zoneOffset);
    }

    public LocalDateTime toLocalDateTime() {
        return this.f18119a;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        if (b.b(localDate)) {
            return X(this.f18119a.m(localDate), this.f18120b);
        }
        localDate.getClass();
        return (OffsetDateTime) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            int i10 = k.f18314a[aVar.ordinal()];
            if (i10 == 1) {
                return V(Instant.ofEpochSecond(j10, this.f18119a.f18108b.f18115d), this.f18120b);
            }
            if (i10 == 2) {
                return X(this.f18119a, ZoneOffset.d0(aVar.f18349b.a(j10, aVar)));
            }
            return X(this.f18119a.c(j10, oVar), this.f18120b);
        }
        return (OffsetDateTime) oVar.G(this, j10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public final OffsetDateTime d(long j10, j$.time.temporal.q qVar) {
        if (qVar instanceof ChronoUnit) {
            return X(this.f18119a.d(j10, qVar), this.f18120b);
        }
        return (OffsetDateTime) qVar.l(this, j10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18369d || cVar == j$.time.temporal.p.f18370e) {
            return this.f18120b;
        }
        if (cVar == j$.time.temporal.p.f18366a) {
            return null;
        }
        if (cVar == j$.time.temporal.p.f18371f) {
            return this.f18119a.f18107a;
        }
        if (cVar == j$.time.temporal.p.f18372g) {
            return this.f18119a.f18108b;
        }
        if (cVar == j$.time.temporal.p.f18367b) {
            return j$.time.chrono.p.f18175d;
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return ChronoUnit.NANOS;
        }
        return cVar.h(this);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(this.f18119a.f18107a.I(), j$.time.temporal.a.EPOCH_DAY).c(this.f18119a.f18108b.g0(), j$.time.temporal.a.NANO_OF_DAY).c(this.f18120b.f18126b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        OffsetDateTime offsetDateTimeU = U(temporal);
        if (qVar instanceof ChronoUnit) {
            ZoneOffset zoneOffset = this.f18120b;
            if (!zoneOffset.equals(offsetDateTimeU.f18120b)) {
                offsetDateTimeU = new OffsetDateTime(offsetDateTimeU.f18119a.a0(zoneOffset.f18126b - offsetDateTimeU.f18120b.f18126b), zoneOffset);
            }
            return this.f18119a.g(offsetDateTimeU.f18119a, qVar);
        }
        return qVar.between(this, offsetDateTimeU);
    }

    public Instant toInstant() {
        LocalDateTime localDateTime = this.f18119a;
        ZoneOffset zoneOffset = this.f18120b;
        localDateTime.getClass();
        return j$.com.android.tools.r8.a.y(localDateTime, zoneOffset);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OffsetDateTime) {
            OffsetDateTime offsetDateTime = (OffsetDateTime) obj;
            if (this.f18119a.equals(offsetDateTime.f18119a) && this.f18120b.equals(offsetDateTime.f18120b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f18119a.hashCode() ^ this.f18120b.f18126b;
    }

    public final String toString() {
        return this.f18119a.toString() + this.f18120b.f18127c;
    }

    private Object writeReplace() {
        return new o((byte) 10, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
