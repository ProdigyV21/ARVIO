package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.DayOfWeek;
import j$.time.Instant;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAccessor;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceConfigurationError;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements Chronology {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f18134a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap f18135b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Locale f18136c = new Locale("ja", "JP", "JP");

    public abstract /* synthetic */ ChronoLocalDate m();

    public static Chronology r(Chronology chronology, String str) {
        String strO;
        Chronology chronology2 = (Chronology) f18134a.putIfAbsent(str, chronology);
        if (chronology2 == null && (strO = chronology.o()) != null) {
            f18135b.putIfAbsent(strO, chronology);
        }
        return chronology2;
    }

    public static boolean n() {
        if (f18134a.get("ISO") != null) {
            return false;
        }
        l lVar = l.f18160m;
        lVar.getClass();
        r(lVar, "Hijrah-umalqura");
        s sVar = s.f18178d;
        sVar.getClass();
        r(sVar, "Japanese");
        x xVar = x.f18190d;
        xVar.getClass();
        r(xVar, "Minguo");
        d0 d0Var = d0.f18143d;
        d0Var.getClass();
        r(d0Var, "ThaiBuddhist");
        try {
            for (a aVar : Arrays.asList(new a[0])) {
                if (!aVar.k().equals("ISO")) {
                    r(aVar, aVar.k());
                }
            }
            p pVar = p.f18175d;
            pVar.getClass();
            r(pVar, "ISO");
            return true;
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    @Override // j$.time.chrono.Chronology
    public ChronoLocalDate P(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
        if (map.containsKey(aVar)) {
            return j(((Long) map.remove(aVar)).longValue());
        }
        G(map, b0Var);
        ChronoLocalDate chronoLocalDateU = U(map, b0Var);
        if (chronoLocalDateU != null) {
            return chronoLocalDateU;
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR;
        if (!map.containsKey(aVar2)) {
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.MONTH_OF_YEAR;
        if (map.containsKey(aVar3)) {
            if (map.containsKey(j$.time.temporal.a.DAY_OF_MONTH)) {
                return H(map, b0Var);
            }
            j$.time.temporal.a aVar4 = j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH;
            if (map.containsKey(aVar4)) {
                j$.time.temporal.a aVar5 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH;
                if (map.containsKey(aVar5)) {
                    int iA = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
                    if (b0Var == j$.time.format.b0.LENIENT) {
                        long jS = j$.com.android.tools.r8.a.S(((Long) map.remove(aVar3)).longValue(), 1L);
                        return N(iA, 1, 1).d(jS, (j$.time.temporal.q) ChronoUnit.MONTHS).d(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar4)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.WEEKS).d(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar5)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.DAYS);
                    }
                    int iA2 = u(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    int iA3 = u(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4);
                    ChronoLocalDate chronoLocalDateD = N(iA, iA2, 1).d((u(aVar5).a(((Long) map.remove(aVar5)).longValue(), aVar5) - 1) + ((iA3 - 1) * 7), (j$.time.temporal.q) ChronoUnit.DAYS);
                    if (b0Var != j$.time.format.b0.STRICT || chronoLocalDateD.l(aVar3) == iA2) {
                        return chronoLocalDateD;
                    }
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
                }
                j$.time.temporal.a aVar6 = j$.time.temporal.a.DAY_OF_WEEK;
                if (map.containsKey(aVar6)) {
                    int iA4 = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
                    if (b0Var == j$.time.format.b0.LENIENT) {
                        return C(N(iA4, 1, 1), j$.com.android.tools.r8.a.S(((Long) map.remove(aVar3)).longValue(), 1L), j$.com.android.tools.r8.a.S(((Long) map.remove(aVar4)).longValue(), 1L), j$.com.android.tools.r8.a.S(((Long) map.remove(aVar6)).longValue(), 1L));
                    }
                    int iA5 = u(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    ChronoLocalDate chronoLocalDateM = N(iA4, iA5, 1).d((u(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4) - 1) * 7, (j$.time.temporal.q) ChronoUnit.DAYS).m(new j$.time.temporal.m(DayOfWeek.U(u(aVar6).a(((Long) map.remove(aVar6)).longValue(), aVar6)).getValue(), 0));
                    if (b0Var != j$.time.format.b0.STRICT || chronoLocalDateM.l(aVar3) == iA5) {
                        return chronoLocalDateM;
                    }
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
                }
            }
        }
        j$.time.temporal.a aVar7 = j$.time.temporal.a.DAY_OF_YEAR;
        if (map.containsKey(aVar7)) {
            int iA6 = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
            if (b0Var != j$.time.format.b0.LENIENT) {
                return q(iA6, u(aVar7).a(((Long) map.remove(aVar7)).longValue(), aVar7));
            }
            return q(iA6, 1).d(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar7)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.DAYS);
        }
        j$.time.temporal.a aVar8 = j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR;
        if (!map.containsKey(aVar8)) {
            return null;
        }
        j$.time.temporal.a aVar9 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR;
        if (map.containsKey(aVar9)) {
            int iA7 = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
            if (b0Var == j$.time.format.b0.LENIENT) {
                return q(iA7, 1).d(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar8)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.WEEKS).d(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar9)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.DAYS);
            }
            int iA8 = u(aVar8).a(((Long) map.remove(aVar8)).longValue(), aVar8);
            ChronoLocalDate chronoLocalDateD2 = q(iA7, 1).d((u(aVar9).a(((Long) map.remove(aVar9)).longValue(), aVar9) - 1) + ((iA8 - 1) * 7), (j$.time.temporal.q) ChronoUnit.DAYS);
            if (b0Var != j$.time.format.b0.STRICT || chronoLocalDateD2.l(aVar2) == iA7) {
                return chronoLocalDateD2;
            }
            throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
        }
        j$.time.temporal.a aVar10 = j$.time.temporal.a.DAY_OF_WEEK;
        if (!map.containsKey(aVar10)) {
            return null;
        }
        int iA9 = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
        if (b0Var == j$.time.format.b0.LENIENT) {
            return C(q(iA9, 1), 0L, j$.com.android.tools.r8.a.S(((Long) map.remove(aVar8)).longValue(), 1L), j$.com.android.tools.r8.a.S(((Long) map.remove(aVar10)).longValue(), 1L));
        }
        ChronoLocalDate chronoLocalDateM2 = q(iA9, 1).d((u(aVar8).a(((Long) map.remove(aVar8)).longValue(), aVar8) - 1) * 7, (j$.time.temporal.q) ChronoUnit.DAYS).m(new j$.time.temporal.m(DayOfWeek.U(u(aVar10).a(((Long) map.remove(aVar10)).longValue(), aVar10)).getValue(), 0));
        if (b0Var != j$.time.format.b0.STRICT || chronoLocalDateM2.l(aVar2) == iA9) {
            return chronoLocalDateM2;
        }
        throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
    }

    @Override // j$.time.chrono.Chronology
    public ChronoLocalDateTime K(TemporalAccessor temporalAccessor) {
        try {
            return E(temporalAccessor).J(LocalTime.W(temporalAccessor));
        } catch (DateTimeException e5) {
            throw new DateTimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + temporalAccessor.getClass(), e5);
        }
    }

    public void G(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long l10 = (Long) map.remove(aVar);
        if (l10 != null) {
            if (b0Var != j$.time.format.b0.LENIENT) {
                aVar.H(l10.longValue());
            }
            ChronoLocalDate chronoLocalDateC = m().c(1L, (j$.time.temporal.o) j$.time.temporal.a.DAY_OF_MONTH).c(l10.longValue(), (j$.time.temporal.o) aVar);
            l(map, j$.time.temporal.a.MONTH_OF_YEAR, chronoLocalDateC.l(r0));
            l(map, j$.time.temporal.a.YEAR, chronoLocalDateC.l(r0));
        }
    }

    public ChronoLocalDate U(Map map, j$.time.format.b0 b0Var) {
        int iK;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR_OF_ERA;
        Long l10 = (Long) map.remove(aVar);
        if (l10 != null) {
            Long l11 = (Long) map.remove(j$.time.temporal.a.ERA);
            if (b0Var != j$.time.format.b0.LENIENT) {
                iK = u(aVar).a(l10.longValue(), aVar);
            } else {
                iK = j$.com.android.tools.r8.a.K(l10.longValue());
            }
            if (l11 != null) {
                l(map, j$.time.temporal.a.YEAR, z(x(u(r2).a(l11.longValue(), r2)), iK));
                return null;
            }
            j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR;
            if (map.containsKey(aVar2)) {
                l(map, aVar2, z(q(u(aVar2).a(((Long) map.get(aVar2)).longValue(), aVar2), 1).L(), iK));
                return null;
            }
            if (b0Var == j$.time.format.b0.STRICT) {
                map.put(aVar, l10);
                return null;
            }
            if (w().isEmpty()) {
                l(map, aVar2, iK);
                return null;
            }
            l(map, aVar2, z((j) r9.get(r9.size() - 1), iK));
            return null;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.ERA;
        if (!map.containsKey(aVar3)) {
            return null;
        }
        u(aVar3).b(((Long) map.get(aVar3)).longValue(), aVar3);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v6, types: [j$.time.chrono.ChronoZonedDateTime] */
    @Override // j$.time.chrono.Chronology
    public ChronoZonedDateTime p(TemporalAccessor temporalAccessor) {
        try {
            ZoneId zoneIdU = ZoneId.U(temporalAccessor);
            try {
                temporalAccessor = Q(Instant.V(temporalAccessor), zoneIdU);
                return temporalAccessor;
            } catch (DateTimeException unused) {
                return i.U(zoneIdU, null, e.U(this, K(temporalAccessor)));
            }
        } catch (DateTimeException e5) {
            throw new DateTimeException("Unable to obtain ChronoZonedDateTime from TemporalAccessor: " + temporalAccessor.getClass(), e5);
        }
    }

    public ChronoLocalDate H(Map map, j$.time.format.b0 b0Var) {
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int iA = u(aVar).a(((Long) map.remove(aVar)).longValue(), aVar);
        if (b0Var == j$.time.format.b0.LENIENT) {
            long jS = j$.com.android.tools.r8.a.S(((Long) map.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1L);
            return N(iA, 1, 1).d(jS, (j$.time.temporal.q) ChronoUnit.MONTHS).d(j$.com.android.tools.r8.a.S(((Long) map.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1L), (j$.time.temporal.q) ChronoUnit.DAYS);
        }
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        int iA2 = u(aVar2).a(((Long) map.remove(aVar2)).longValue(), aVar2);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        int iA3 = u(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
        if (b0Var != j$.time.format.b0.SMART) {
            return N(iA, iA2, iA3);
        }
        try {
            return N(iA, iA2, iA3);
        } catch (DateTimeException unused) {
            return N(iA, iA2, 1).m(new j$.time.c(6));
        }
    }

    public static ChronoLocalDate C(ChronoLocalDate chronoLocalDate, long j10, long j11, long j12) {
        long j13;
        ChronoLocalDate chronoLocalDateD = chronoLocalDate.d(j10, (j$.time.temporal.q) ChronoUnit.MONTHS);
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        ChronoLocalDate chronoLocalDateD2 = chronoLocalDateD.d(j11, (j$.time.temporal.q) chronoUnit);
        if (j12 > 7) {
            long j14 = j12 - 1;
            chronoLocalDateD2 = chronoLocalDateD2.d(j14 / 7, (j$.time.temporal.q) chronoUnit);
            j13 = j14 % 7;
        } else {
            if (j12 < 1) {
                chronoLocalDateD2 = chronoLocalDateD2.d(j$.com.android.tools.r8.a.S(j12, 7L) / 7, (j$.time.temporal.q) chronoUnit);
                j13 = (j12 + 6) % 7;
            }
            return chronoLocalDateD2.m(new j$.time.temporal.m(DayOfWeek.U((int) j12).getValue(), 0));
        }
        j12 = j13 + 1;
        return chronoLocalDateD2.m(new j$.time.temporal.m(DayOfWeek.U((int) j12).getValue(), 0));
    }

    public static void l(Map map, j$.time.temporal.a aVar, long j10) {
        Long l10 = (Long) map.get(aVar);
        if (l10 != null && l10.longValue() != j10) {
            throw new DateTimeException("Conflict found: " + aVar + " " + l10 + " differs from " + aVar + " " + j10);
        }
        map.put(aVar, Long.valueOf(j10));
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public final int compareTo(Chronology chronology) {
        return k().compareTo(chronology.k());
    }

    @Override // j$.time.chrono.Chronology
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && compareTo((a) obj) == 0;
    }

    @Override // j$.time.chrono.Chronology
    public final int hashCode() {
        return getClass().hashCode() ^ k().hashCode();
    }

    @Override // j$.time.chrono.Chronology
    public final String toString() {
        return k();
    }
}
