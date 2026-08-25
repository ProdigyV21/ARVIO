package j$.time.chrono;

import j$.time.Clock;
import j$.time.DateTimeException;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.Month;
import j$.time.ZoneId;
import j$.time.ZonedDateTime;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class p extends a implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p f18175d = new p();
    private static final long serialVersionUID = -1440403870442975015L;

    @Override // j$.time.chrono.Chronology
    public final j x(int i10) {
        if (i10 == 0) {
            return q.BCE;
        }
        if (i10 == 1) {
            return q.CE;
        }
        throw new DateTimeException("Invalid era: " + i10);
    }

    @Override // j$.time.chrono.Chronology
    public final String k() {
        return "ISO";
    }

    @Override // j$.time.chrono.Chronology
    public final String o() {
        return "iso8601";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate N(int i10, int i11, int i12) {
        return LocalDate.of(i10, i11, i12);
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate q(int i10, int i11) {
        return LocalDate.f0(i10, i11);
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate j(long j10) {
        return LocalDate.e0(j10);
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate E(TemporalAccessor temporalAccessor) {
        return LocalDate.W(temporalAccessor);
    }

    private p() {
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoLocalDateTime K(TemporalAccessor temporalAccessor) {
        return LocalDateTime.V(temporalAccessor);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoZonedDateTime p(TemporalAccessor temporalAccessor) {
        return ZonedDateTime.U(temporalAccessor);
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoZonedDateTime Q(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate m() {
        j$.time.a aVarB = Clock.b();
        Objects.requireNonNull(aVarB, "clock");
        return LocalDate.W(LocalDate.d0(aVarB));
    }

    @Override // j$.time.chrono.Chronology
    public final boolean T(long j10) {
        if ((3 & j10) == 0) {
            return j10 % 100 != 0 || j10 % 400 == 0;
        }
        return false;
    }

    @Override // j$.time.chrono.Chronology
    public final int z(j jVar, int i10) {
        if (jVar instanceof q) {
            return jVar == q.CE ? i10 : 1 - i10;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // j$.time.chrono.Chronology
    public final List w() {
        return j$.com.android.tools.r8.a.M(q.values());
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoLocalDate P(Map map, j$.time.format.b0 b0Var) {
        return (LocalDate) super.P(map, b0Var);
    }

    @Override // j$.time.chrono.a
    public final void G(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long l10 = (Long) map.remove(aVar);
        if (l10 != null) {
            if (b0Var != j$.time.format.b0.LENIENT) {
                aVar.H(l10.longValue());
            }
            a.l(map, j$.time.temporal.a.MONTH_OF_YEAR, ((int) j$.com.android.tools.r8.a.P(l10.longValue(), r4)) + 1);
            a.l(map, j$.time.temporal.a.YEAR, j$.com.android.tools.r8.a.Q(l10.longValue(), 12));
        }
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate U(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR_OF_ERA;
        Long l10 = (Long) map.remove(aVar);
        if (l10 != null) {
            if (b0Var != j$.time.format.b0.LENIENT) {
                aVar.H(l10.longValue());
            }
            Long l11 = (Long) map.remove(j$.time.temporal.a.ERA);
            if (l11 != null) {
                if (l11.longValue() == 1) {
                    a.l(map, j$.time.temporal.a.YEAR, l10.longValue());
                    return null;
                }
                if (l11.longValue() == 0) {
                    a.l(map, j$.time.temporal.a.YEAR, j$.com.android.tools.r8.a.S(1L, l10.longValue()));
                    return null;
                }
                throw new DateTimeException("Invalid value for era: " + l11);
            }
            j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR;
            Long l12 = (Long) map.get(aVar2);
            if (b0Var != j$.time.format.b0.STRICT) {
                a.l(map, aVar2, (l12 == null || l12.longValue() > 0) ? l10.longValue() : j$.com.android.tools.r8.a.S(1L, l10.longValue()));
                return null;
            }
            if (l12 != null) {
                long jLongValue = l12.longValue();
                long jLongValue2 = l10.longValue();
                if (jLongValue <= 0) {
                    jLongValue2 = j$.com.android.tools.r8.a.S(1L, jLongValue2);
                }
                a.l(map, aVar2, jLongValue2);
                return null;
            }
            map.put(aVar, l10);
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.ERA;
        if (!map.containsKey(aVar3)) {
            return null;
        }
        aVar3.H(((Long) map.get(aVar3)).longValue());
        return null;
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate H(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int iA = aVar.f18349b.a(((Long) map.remove(aVar)).longValue(), aVar);
        boolean z = true;
        if (b0Var == j$.time.format.b0.LENIENT) {
            return LocalDate.of(iA, 1, 1).plusMonths(j$.com.android.tools.r8.a.S(((Long) map.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1L)).plusDays(j$.com.android.tools.r8.a.S(((Long) map.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1L));
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        int iA2 = aVar2.f18349b.a(((Long) map.remove(aVar2)).longValue(), aVar2);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        int iA3 = aVar3.f18349b.a(((Long) map.remove(aVar3)).longValue(), aVar3);
        if (b0Var == j$.time.format.b0.SMART) {
            if (iA2 == 4 || iA2 == 6 || iA2 == 9 || iA2 == 11) {
                iA3 = Math.min(iA3, 30);
            } else if (iA2 == 2) {
                Month month = Month.FEBRUARY;
                long j10 = iA;
                int i10 = j$.time.q.f18327b;
                if ((3 & j10) != 0 || (j10 % 100 == 0 && j10 % 400 != 0)) {
                    z = false;
                }
                iA3 = Math.min(iA3, month.V(z));
            }
        }
        return LocalDate.of(iA, iA2, iA3);
    }

    @Override // j$.time.chrono.Chronology
    public final j$.time.temporal.s u(j$.time.temporal.a aVar) {
        return aVar.f18349b;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public Object writeReplace() {
        return new b0((byte) 1, this);
    }
}
