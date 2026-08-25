package j$.time.temporal;

import j$.time.DateTimeException;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.format.a0;
import j$.time.format.b0;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class t implements o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final s f18377f = s.f(1, 7);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final s f18378g = s.g(0, 4, 6);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final s f18379h = s.g(0, 52, 54);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final s f18380i = s.g(1, 52, 53);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18381a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeekFields f18382b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q f18383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f18384d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s f18385e;

    @Override // j$.time.temporal.o
    public final boolean isDateBased() {
        return true;
    }

    public final ChronoLocalDate e(Chronology chronology, int i10, int i11, int i12) {
        ChronoLocalDate chronoLocalDateN = chronology.N(i10, 1, 1);
        int iH = h(1, b(chronoLocalDateN));
        int i13 = i12 - 1;
        return chronoLocalDateN.d(((Math.min(i11, a(iH, chronoLocalDateN.R() + this.f18382b.f18342b) - 1) - 1) * 7) + i13 + (-iH), (q) ChronoUnit.DAYS);
    }

    public t(String str, WeekFields weekFields, q qVar, q qVar2, s sVar) {
        this.f18381a = str;
        this.f18382b = weekFields;
        this.f18383c = qVar;
        this.f18384d = qVar2;
        this.f18385e = sVar;
    }

    @Override // j$.time.temporal.o
    public final long C(TemporalAccessor temporalAccessor) {
        int iC;
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        q qVar = this.f18384d;
        if (qVar == chronoUnit) {
            iC = b(temporalAccessor);
        } else if (qVar != ChronoUnit.MONTHS) {
            if (qVar != ChronoUnit.YEARS) {
                if (qVar == WeekFields.f18340h) {
                    iC = d(temporalAccessor);
                } else if (qVar == ChronoUnit.FOREVER) {
                    iC = c(temporalAccessor);
                } else {
                    throw new IllegalStateException("unreachable, rangeUnit: " + qVar + ", this: " + this);
                }
            } else {
                int iB = b(temporalAccessor);
                int iL = temporalAccessor.l(a.DAY_OF_YEAR);
                iC = a(h(iL, iB), iL);
            }
        } else {
            int iB2 = b(temporalAccessor);
            int iL2 = temporalAccessor.l(a.DAY_OF_MONTH);
            iC = a(h(iL2, iB2), iL2);
        }
        return iC;
    }

    public final int b(TemporalAccessor temporalAccessor) {
        return p.e(temporalAccessor.l(a.DAY_OF_WEEK) - this.f18382b.getFirstDayOfWeek().getValue()) + 1;
    }

    public final int c(TemporalAccessor temporalAccessor) {
        int iB = b(temporalAccessor);
        int iL = temporalAccessor.l(a.YEAR);
        a aVar = a.DAY_OF_YEAR;
        int iL2 = temporalAccessor.l(aVar);
        int iH = h(iL2, iB);
        int iA = a(iH, iL2);
        return iA == 0 ? iL - 1 : iA >= a(iH, ((int) temporalAccessor.n(aVar).f18376d) + this.f18382b.f18342b) ? iL + 1 : iL;
    }

    public final int d(TemporalAccessor temporalAccessor) {
        int iA;
        int iB = b(temporalAccessor);
        a aVar = a.DAY_OF_YEAR;
        int iL = temporalAccessor.l(aVar);
        int iH = h(iL, iB);
        int iA2 = a(iH, iL);
        if (iA2 == 0) {
            return d(Chronology.CC.a(temporalAccessor).E(temporalAccessor).v(iL, ChronoUnit.DAYS));
        }
        return (iA2 <= 50 || iA2 < (iA = a(iH, ((int) temporalAccessor.n(aVar).f18376d) + this.f18382b.f18342b))) ? iA2 : (iA2 - iA) + 1;
    }

    public final int h(int i10, int i11) {
        int iE = p.e(i10 - i11);
        return iE + 1 > this.f18382b.f18342b ? 7 - iE : -iE;
    }

    public static int a(int i10, int i11) {
        return ((i11 - 1) + (i10 + 7)) / 7;
    }

    @Override // j$.time.temporal.o
    public final Temporal G(Temporal temporal, long j10) {
        if (this.f18385e.a(j10, this) == temporal.l(this)) {
            return temporal;
        }
        if (this.f18384d != ChronoUnit.FOREVER) {
            return temporal.d(r0 - r1, this.f18383c);
        }
        WeekFields weekFields = this.f18382b;
        return e(Chronology.CC.a(temporal), (int) j10, temporal.l(weekFields.f18345e), temporal.l(weekFields.f18343c));
    }

    @Override // j$.time.temporal.o
    public final TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
        ChronoLocalDate chronoLocalDateD;
        ChronoLocalDate chronoLocalDateD2;
        a aVar;
        ChronoLocalDate chronoLocalDateD3;
        long jLongValue = ((Long) map.get(this)).longValue();
        int iK = j$.com.android.tools.r8.a.K(jLongValue);
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        s sVar = this.f18385e;
        WeekFields weekFields = this.f18382b;
        q qVar = this.f18384d;
        if (qVar == chronoUnit) {
            long jE = p.e((sVar.a(jLongValue, this) - 1) + (weekFields.getFirstDayOfWeek().getValue() - 1)) + 1;
            map.remove(this);
            map.put(a.DAY_OF_WEEK, Long.valueOf(jE));
            return null;
        }
        a aVar2 = a.DAY_OF_WEEK;
        if (!map.containsKey(aVar2)) {
            return null;
        }
        int iE = p.e(aVar2.f18349b.a(((Long) map.get(aVar2)).longValue(), aVar2) - weekFields.getFirstDayOfWeek().getValue()) + 1;
        Chronology chronologyA = Chronology.CC.a(a0Var);
        a aVar3 = a.YEAR;
        if (!map.containsKey(aVar3)) {
            if ((qVar != WeekFields.f18340h && qVar != ChronoUnit.FOREVER) || !map.containsKey(weekFields.f18346f) || !map.containsKey(weekFields.f18345e)) {
                return null;
            }
            t tVar = weekFields.f18346f;
            int iA = tVar.f18385e.a(((Long) map.get(tVar)).longValue(), weekFields.f18346f);
            if (b0Var == b0.LENIENT) {
                chronoLocalDateD = e(chronologyA, iA, 1, iE).d(j$.com.android.tools.r8.a.S(((Long) map.get(weekFields.f18345e)).longValue(), 1L), (q) chronoUnit);
            } else {
                t tVar2 = weekFields.f18345e;
                ChronoLocalDate chronoLocalDateE = e(chronologyA, iA, tVar2.f18385e.a(((Long) map.get(tVar2)).longValue(), weekFields.f18345e), iE);
                if (b0Var == b0.STRICT && c(chronoLocalDateE) != iA) {
                    throw new DateTimeException("Strict mode rejected resolved date as it is in a different week-based-year");
                }
                chronoLocalDateD = chronoLocalDateE;
            }
            map.remove(this);
            map.remove(weekFields.f18346f);
            map.remove(weekFields.f18345e);
            map.remove(aVar2);
            return chronoLocalDateD;
        }
        int iA2 = aVar3.f18349b.a(((Long) map.get(aVar3)).longValue(), aVar3);
        ChronoUnit chronoUnit2 = ChronoUnit.MONTHS;
        if (qVar == chronoUnit2) {
            a aVar4 = a.MONTH_OF_YEAR;
            if (map.containsKey(aVar4)) {
                long jLongValue2 = ((Long) map.get(aVar4)).longValue();
                long j10 = iK;
                if (b0Var == b0.LENIENT) {
                    ChronoLocalDate chronoLocalDateD4 = chronologyA.N(iA2, 1, 1).d(j$.com.android.tools.r8.a.S(jLongValue2, 1L), (q) chronoUnit2);
                    int iB = b(chronoLocalDateD4);
                    int iL = chronoLocalDateD4.l(a.DAY_OF_MONTH);
                    chronoLocalDateD3 = chronoLocalDateD4.d(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j$.com.android.tools.r8.a.S(j10, a(h(iL, iB), iL)), 7), iE - b(chronoLocalDateD4)), (q) ChronoUnit.DAYS);
                    aVar = aVar4;
                } else {
                    aVar = aVar4;
                    ChronoLocalDate chronoLocalDateN = chronologyA.N(iA2, aVar.f18349b.a(jLongValue2, aVar), 1);
                    long jA = sVar.a(j10, this);
                    int iB2 = b(chronoLocalDateN);
                    int iL2 = chronoLocalDateN.l(a.DAY_OF_MONTH);
                    ChronoLocalDate chronoLocalDateD5 = chronoLocalDateN.d((((int) (jA - ((long) a(h(iL2, iB2), iL2)))) * 7) + (iE - b(chronoLocalDateN)), (q) ChronoUnit.DAYS);
                    if (b0Var == b0.STRICT && chronoLocalDateD5.H(aVar) != jLongValue2) {
                        throw new DateTimeException("Strict mode rejected resolved date as it is in a different month");
                    }
                    chronoLocalDateD3 = chronoLocalDateD5;
                }
                map.remove(this);
                map.remove(aVar3);
                map.remove(aVar);
                map.remove(aVar2);
                return chronoLocalDateD3;
            }
        }
        if (qVar != ChronoUnit.YEARS) {
            return null;
        }
        long j11 = iK;
        ChronoLocalDate chronoLocalDateN2 = chronologyA.N(iA2, 1, 1);
        if (b0Var == b0.LENIENT) {
            int iB3 = b(chronoLocalDateN2);
            int iL3 = chronoLocalDateN2.l(a.DAY_OF_YEAR);
            chronoLocalDateD2 = chronoLocalDateN2.d(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j$.com.android.tools.r8.a.S(j11, a(h(iL3, iB3), iL3)), 7), iE - b(chronoLocalDateN2)), (q) ChronoUnit.DAYS);
        } else {
            long jA2 = sVar.a(j11, this);
            int iB4 = b(chronoLocalDateN2);
            int iL4 = chronoLocalDateN2.l(a.DAY_OF_YEAR);
            ChronoLocalDate chronoLocalDateD6 = chronoLocalDateN2.d((((int) (jA2 - ((long) a(h(iL4, iB4), iL4)))) * 7) + (iE - b(chronoLocalDateN2)), (q) ChronoUnit.DAYS);
            if (b0Var == b0.STRICT && chronoLocalDateD6.H(aVar3) != iA2) {
                throw new DateTimeException("Strict mode rejected resolved date as it is in a different year");
            }
            chronoLocalDateD2 = chronoLocalDateD6;
        }
        map.remove(this);
        map.remove(aVar3);
        map.remove(aVar2);
        return chronoLocalDateD2;
    }

    @Override // j$.time.temporal.o
    public final s r() {
        return this.f18385e;
    }

    @Override // j$.time.temporal.o
    public final boolean l(TemporalAccessor temporalAccessor) {
        if (!temporalAccessor.e(a.DAY_OF_WEEK)) {
            return false;
        }
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        q qVar = this.f18384d;
        if (qVar == chronoUnit) {
            return true;
        }
        if (qVar == ChronoUnit.MONTHS) {
            return temporalAccessor.e(a.DAY_OF_MONTH);
        }
        if (qVar == ChronoUnit.YEARS) {
            return temporalAccessor.e(a.DAY_OF_YEAR);
        }
        if (qVar == WeekFields.f18340h) {
            return temporalAccessor.e(a.DAY_OF_YEAR);
        }
        if (qVar == ChronoUnit.FOREVER) {
            return temporalAccessor.e(a.YEAR);
        }
        return false;
    }

    @Override // j$.time.temporal.o
    public final s m(TemporalAccessor temporalAccessor) {
        ChronoUnit chronoUnit = ChronoUnit.WEEKS;
        q qVar = this.f18384d;
        if (qVar == chronoUnit) {
            return this.f18385e;
        }
        if (qVar == ChronoUnit.MONTHS) {
            return f(temporalAccessor, a.DAY_OF_MONTH);
        }
        if (qVar == ChronoUnit.YEARS) {
            return f(temporalAccessor, a.DAY_OF_YEAR);
        }
        if (qVar == WeekFields.f18340h) {
            return g(temporalAccessor);
        }
        if (qVar == ChronoUnit.FOREVER) {
            return a.YEAR.f18349b;
        }
        throw new IllegalStateException("unreachable, rangeUnit: " + qVar + ", this: " + this);
    }

    public final s f(TemporalAccessor temporalAccessor, a aVar) {
        int iH = h(temporalAccessor.l(aVar), b(temporalAccessor));
        s sVarN = temporalAccessor.n(aVar);
        return s.f(a(iH, (int) sVarN.f18373a), a(iH, (int) sVarN.f18376d));
    }

    public final s g(TemporalAccessor temporalAccessor) {
        a aVar = a.DAY_OF_YEAR;
        if (!temporalAccessor.e(aVar)) {
            return f18379h;
        }
        int iB = b(temporalAccessor);
        int iL = temporalAccessor.l(aVar);
        int iH = h(iL, iB);
        int iA = a(iH, iL);
        if (iA != 0) {
            if (iA >= a(iH, this.f18382b.f18342b + ((int) temporalAccessor.n(aVar).f18376d))) {
                return g(Chronology.CC.a(temporalAccessor).E(temporalAccessor).d((r0 - iL) + 8, (q) ChronoUnit.DAYS));
            }
            return s.f(1L, r1 - 1);
        }
        return g(Chronology.CC.a(temporalAccessor).E(temporalAccessor).v(iL + 7, ChronoUnit.DAYS));
    }

    public final String toString() {
        return this.f18381a + "[" + this.f18382b.toString() + "]";
    }
}
