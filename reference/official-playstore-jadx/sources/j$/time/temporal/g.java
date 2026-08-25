package j$.time.temporal;

import j$.time.DateTimeException;
import j$.time.DayOfWeek;
import j$.time.LocalDate;
import j$.time.chrono.Chronology;
import j$.time.format.a0;
import j$.time.format.b0;
import java.util.Map;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public abstract class g implements o {
    public static final g DAY_OF_QUARTER;
    public static final g QUARTER_OF_YEAR;
    public static final g WEEK_BASED_YEAR;
    public static final g WEEK_OF_WEEK_BASED_YEAR;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f18351a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ g[] f18352b;

    @Override // j$.time.temporal.o
    public final boolean isDateBased() {
        return true;
    }

    public /* synthetic */ TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
        return null;
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f18352b.clone();
    }

    static {
        g gVar = new g() { // from class: j$.time.temporal.c
            @Override // j$.time.temporal.o
            public final s r() {
                return s.g(1L, 90L, 92L);
            }

            @Override // j$.time.temporal.o
            public final boolean l(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.e(a.DAY_OF_YEAR) || !temporalAccessor.e(a.MONTH_OF_YEAR) || !temporalAccessor.e(a.YEAR)) {
                    return false;
                }
                g gVar2 = i.f18355a;
                return Chronology.CC.a(temporalAccessor).equals(j$.time.chrono.p.f18175d);
            }

            @Override // j$.time.temporal.o
            public final s m(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: DayOfQuarter");
                }
                long jH = temporalAccessor.H(g.QUARTER_OF_YEAR);
                if (jH == 1) {
                    return j$.time.chrono.p.f18175d.T(temporalAccessor.H(a.YEAR)) ? s.f(1L, 91L) : s.f(1L, 90L);
                }
                if (jH == 2) {
                    return s.f(1L, 91L);
                }
                if (jH == 3 || jH == 4) {
                    return s.f(1L, 92L);
                }
                return r();
            }

            @Override // j$.time.temporal.o
            public final long C(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: DayOfQuarter");
                }
                return temporalAccessor.l(a.DAY_OF_YEAR) - g.f18351a[((temporalAccessor.l(a.MONTH_OF_YEAR) - 1) / 3) + (j$.time.chrono.p.f18175d.T(temporalAccessor.H(a.YEAR)) ? 4 : 0)];
            }

            @Override // j$.time.temporal.o
            public final Temporal G(Temporal temporal, long j10) {
                long jC = C(temporal);
                r().b(j10, this);
                a aVar = a.DAY_OF_YEAR;
                return temporal.c((j10 - jC) + temporal.H(aVar), aVar);
            }

            @Override // j$.time.temporal.g, j$.time.temporal.o
            public final TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
                LocalDate localDatePlusMonths;
                long jS;
                a aVar = a.YEAR;
                Long l10 = (Long) map.get(aVar);
                o oVar = g.QUARTER_OF_YEAR;
                Long l11 = (Long) map.get(oVar);
                if (l10 == null || l11 == null) {
                    return null;
                }
                int iA = aVar.f18349b.a(l10.longValue(), aVar);
                long jLongValue = ((Long) map.get(g.DAY_OF_QUARTER)).longValue();
                g gVar2 = i.f18355a;
                if (!Chronology.CC.a(a0Var).equals(j$.time.chrono.p.f18175d)) {
                    throw new DateTimeException("Resolve requires IsoChronology");
                }
                if (b0Var == b0.LENIENT) {
                    localDatePlusMonths = LocalDate.of(iA, 1, 1).plusMonths(j$.com.android.tools.r8.a.R(j$.com.android.tools.r8.a.S(l11.longValue(), 1L), 3));
                    jS = j$.com.android.tools.r8.a.S(jLongValue, 1L);
                } else {
                    LocalDate localDateOf = LocalDate.of(iA, ((oVar.r().a(l11.longValue(), oVar) - 1) * 3) + 1, 1);
                    if (jLongValue < 1 || jLongValue > 90) {
                        if (b0Var == b0.STRICT) {
                            m(localDateOf).b(jLongValue, this);
                        } else {
                            r().b(jLongValue, this);
                        }
                    }
                    localDatePlusMonths = localDateOf;
                    jS = jLongValue - 1;
                }
                map.remove(this);
                map.remove(aVar);
                map.remove(oVar);
                return localDatePlusMonths.plusDays(jS);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "DayOfQuarter";
            }
        };
        DAY_OF_QUARTER = gVar;
        g gVar2 = new g() { // from class: j$.time.temporal.d
            @Override // j$.time.temporal.o
            public final s r() {
                return s.f(1L, 4L);
            }

            @Override // j$.time.temporal.o
            public final boolean l(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.e(a.MONTH_OF_YEAR)) {
                    return false;
                }
                g gVar3 = i.f18355a;
                return Chronology.CC.a(temporalAccessor).equals(j$.time.chrono.p.f18175d);
            }

            @Override // j$.time.temporal.o
            public final long C(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: QuarterOfYear");
                }
                return (temporalAccessor.H(a.MONTH_OF_YEAR) + 2) / 3;
            }

            @Override // j$.time.temporal.o
            public final s m(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: QuarterOfYear");
                }
                return r();
            }

            @Override // j$.time.temporal.o
            public final Temporal G(Temporal temporal, long j10) {
                long jC = C(temporal);
                r().b(j10, this);
                a aVar = a.MONTH_OF_YEAR;
                return temporal.c(((j10 - jC) * 3) + temporal.H(aVar), aVar);
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "QuarterOfYear";
            }
        };
        QUARTER_OF_YEAR = gVar2;
        g gVar3 = new g() { // from class: j$.time.temporal.e
            @Override // j$.time.temporal.o
            public final s r() {
                return s.g(1L, 52L, 53L);
            }

            @Override // j$.time.temporal.o
            public final boolean l(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.e(a.EPOCH_DAY)) {
                    return false;
                }
                g gVar4 = i.f18355a;
                return Chronology.CC.a(temporalAccessor).equals(j$.time.chrono.p.f18175d);
            }

            @Override // j$.time.temporal.o
            public final s m(TemporalAccessor temporalAccessor) {
                if (l(temporalAccessor)) {
                    return g.W(LocalDate.W(temporalAccessor));
                }
                throw new r("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // j$.time.temporal.o
            public final long C(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: WeekOfWeekBasedYear");
                }
                return g.H(LocalDate.W(temporalAccessor));
            }

            @Override // j$.time.temporal.o
            public final Temporal G(Temporal temporal, long j10) {
                r().b(j10, this);
                return temporal.d(j$.com.android.tools.r8.a.S(j10, C(temporal)), ChronoUnit.WEEKS);
            }

            @Override // j$.time.temporal.g, j$.time.temporal.o
            public final TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
                LocalDate localDateC;
                long j10;
                long j11;
                o oVar = g.WEEK_BASED_YEAR;
                Long l10 = (Long) map.get(oVar);
                a aVar = a.DAY_OF_WEEK;
                Long l11 = (Long) map.get(aVar);
                if (l10 == null || l11 == null) {
                    return null;
                }
                int iA = oVar.r().a(l10.longValue(), oVar);
                long jLongValue = ((Long) map.get(g.WEEK_OF_WEEK_BASED_YEAR)).longValue();
                g gVar4 = i.f18355a;
                if (!Chronology.CC.a(a0Var).equals(j$.time.chrono.p.f18175d)) {
                    throw new DateTimeException("Resolve requires IsoChronology");
                }
                LocalDate localDateOf = LocalDate.of(iA, 1, 4);
                if (b0Var == b0.LENIENT) {
                    long jLongValue2 = l11.longValue();
                    if (jLongValue2 > 7) {
                        long j12 = jLongValue2 - 1;
                        j10 = 1;
                        localDateOf = localDateOf.h0(j12 / 7);
                        j11 = j12 % 7;
                    } else {
                        j10 = 1;
                        if (jLongValue2 < 1) {
                            localDateOf = localDateOf.h0(j$.com.android.tools.r8.a.S(jLongValue2, 7L) / 7);
                            j11 = (jLongValue2 + 6) % 7;
                        }
                        localDateC = localDateOf.h0(j$.com.android.tools.r8.a.S(jLongValue, j10)).c(jLongValue2, aVar);
                    }
                    jLongValue2 = j11 + j10;
                    localDateC = localDateOf.h0(j$.com.android.tools.r8.a.S(jLongValue, j10)).c(jLongValue2, aVar);
                } else {
                    int iA2 = aVar.f18349b.a(l11.longValue(), aVar);
                    if (jLongValue < 1 || jLongValue > 52) {
                        if (b0Var == b0.STRICT) {
                            g.W(localDateOf).b(jLongValue, this);
                        } else {
                            r().b(jLongValue, this);
                        }
                    }
                    localDateC = localDateOf.h0(jLongValue - 1).c(iA2, aVar);
                }
                map.remove(this);
                map.remove(oVar);
                map.remove(aVar);
                return localDateC;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekOfWeekBasedYear";
            }
        };
        WEEK_OF_WEEK_BASED_YEAR = gVar3;
        g gVar4 = new g() { // from class: j$.time.temporal.f
            @Override // j$.time.temporal.o
            public final s r() {
                return a.YEAR.f18349b;
            }

            @Override // j$.time.temporal.o
            public final boolean l(TemporalAccessor temporalAccessor) {
                if (!temporalAccessor.e(a.EPOCH_DAY)) {
                    return false;
                }
                g gVar5 = i.f18355a;
                return Chronology.CC.a(temporalAccessor).equals(j$.time.chrono.p.f18175d);
            }

            @Override // j$.time.temporal.o
            public final long C(TemporalAccessor temporalAccessor) {
                if (l(temporalAccessor)) {
                    return g.U(LocalDate.W(temporalAccessor));
                }
                throw new r("Unsupported field: WeekBasedYear");
            }

            @Override // j$.time.temporal.o
            public final s m(TemporalAccessor temporalAccessor) {
                if (!l(temporalAccessor)) {
                    throw new r("Unsupported field: WeekBasedYear");
                }
                return r();
            }

            @Override // j$.time.temporal.o
            public final Temporal G(Temporal temporal, long j10) {
                if (!l(temporal)) {
                    throw new r("Unsupported field: WeekBasedYear");
                }
                int iA = a.YEAR.f18349b.a(j10, g.WEEK_BASED_YEAR);
                LocalDate localDateW = LocalDate.W(temporal);
                int iL = localDateW.l(a.DAY_OF_WEEK);
                int iH = g.H(localDateW);
                if (iH == 53 && g.V(iA) == 52) {
                    iH = 52;
                }
                return temporal.m(LocalDate.of(iA, 1, 4).plusDays(((iH - 1) * 7) + (iL - r6.l(r0))));
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "WeekBasedYear";
            }
        };
        WEEK_BASED_YEAR = gVar4;
        f18352b = new g[]{gVar, gVar2, gVar3, gVar4};
        f18351a = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
    }

    public static s W(LocalDate localDate) {
        return s.f(1L, V(U(localDate)));
    }

    public static int V(int i10) {
        LocalDate localDateOf = LocalDate.of(i10, 1, 1);
        if (localDateOf.getDayOfWeek() != DayOfWeek.THURSDAY) {
            return (localDateOf.getDayOfWeek() == DayOfWeek.WEDNESDAY && localDateOf.t()) ? 53 : 52;
        }
        return 53;
    }

    public static int H(LocalDate localDate) {
        int iOrdinal = localDate.getDayOfWeek().ordinal();
        int iY = localDate.Y() - 1;
        int i10 = (3 - iOrdinal) + iY;
        int i11 = i10 - ((i10 / 7) * 7);
        int i12 = i11 - 3;
        if (i12 < -3) {
            i12 = i11 + 4;
        }
        if (iY >= i12) {
            int i13 = ((iY - i12) / 7) + 1;
            if (i13 != 53 || i12 == -3 || (i12 == -2 && localDate.t())) {
                return i13;
            }
            return 1;
        }
        if (localDate.Y() != 180) {
            localDate = LocalDate.f0(localDate.f18102a, 180);
        }
        return (int) W(localDate.i0(-1L)).f18376d;
    }

    public static int U(LocalDate localDate) {
        int year = localDate.getYear();
        int iY = localDate.Y();
        if (iY <= 3) {
            return iY - localDate.getDayOfWeek().ordinal() < -2 ? year - 1 : year;
        }
        if (iY >= 363) {
            return ((iY - 363) - (localDate.t() ? 1 : 0)) - localDate.getDayOfWeek().ordinal() >= 0 ? year + 1 : year;
        }
        return year;
    }
}
