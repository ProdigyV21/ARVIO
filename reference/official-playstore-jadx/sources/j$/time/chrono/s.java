package j$.time.chrono;

import j$.time.Clock;
import j$.time.DateTimeException;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class s extends a implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final s f18178d = new s();
    private static final long serialVersionUID = 459996390165777884L;

    @Override // j$.time.chrono.Chronology
    public final String k() {
        return "Japanese";
    }

    @Override // j$.time.chrono.Chronology
    public final String o() {
        return "japanese";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate N(int i10, int i11, int i12) {
        return new u(LocalDate.of(i10, i11, i12));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate q(int i10, int i11) {
        return new u(LocalDate.f0(i10, i11));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate j(long j10) {
        return new u(LocalDate.e0(j10));
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate m() {
        return new u(LocalDate.W(LocalDate.d0(Clock.b())));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate E(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof u) {
            return (u) temporalAccessor;
        }
        return new u(LocalDate.W(temporalAccessor));
    }

    @Override // j$.time.chrono.Chronology
    public final List w() {
        v[] vVarArr = v.f18185e;
        return j$.com.android.tools.r8.a.M((v[]) Arrays.copyOf(vVarArr, vVarArr.length));
    }

    @Override // j$.time.chrono.Chronology
    public final boolean T(long j10) {
        return p.f18175d.T(j10);
    }

    private s() {
    }

    @Override // j$.time.chrono.Chronology
    public final int z(j jVar, int i10) {
        if (!(jVar instanceof v)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        v vVar = (v) jVar;
        int year = (vVar.f18187b.getYear() + i10) - 1;
        if (i10 != 1 && (year < -999999999 || year > 999999999 || year < vVar.f18187b.getYear() || jVar != v.j(LocalDate.of(year, 1, 1)))) {
            throw new DateTimeException("Invalid yearOfEra value");
        }
        return year;
    }

    @Override // j$.time.chrono.Chronology
    public final j x(int i10) {
        return v.o(i10);
    }

    @Override // j$.time.chrono.Chronology
    public final j$.time.temporal.s u(j$.time.temporal.a aVar) {
        switch (r.f18177a[aVar.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                throw new j$.time.temporal.r("Unsupported field: " + aVar);
            case 5:
                v[] vVarArr = v.f18185e;
                int year = vVarArr[vVarArr.length - 1].f18187b.getYear();
                int year2 = 1000000000 - vVarArr[vVarArr.length - 1].f18187b.getYear();
                int year3 = vVarArr[0].f18187b.getYear();
                int i10 = 1;
                while (true) {
                    v[] vVarArr2 = v.f18185e;
                    if (i10 >= vVarArr2.length) {
                        return j$.time.temporal.s.g(1L, year2, 999999999 - year);
                    }
                    v vVar = vVarArr2[i10];
                    year2 = Math.min(year2, (vVar.f18187b.getYear() - year3) + 1);
                    year3 = vVar.f18187b.getYear();
                    i10++;
                }
                break;
            case 6:
                v vVar2 = v.f18184d;
                long j10 = j$.time.temporal.a.DAY_OF_YEAR.f18349b.f18375c;
                long jMin = j10;
                for (v vVar3 : v.f18185e) {
                    long jMin2 = Math.min(jMin, (vVar3.f18187b.R() - vVar3.f18187b.Y()) + 1);
                    jMin = vVar3.k() != null ? Math.min(jMin2, vVar3.k().f18187b.Y() - 1) : jMin2;
                }
                return j$.time.temporal.s.g(1L, jMin, j$.time.temporal.a.DAY_OF_YEAR.f18349b.f18376d);
            case 7:
                return j$.time.temporal.s.f(u.f18180d.getYear(), 999999999L);
            case 8:
                long j11 = v.f18184d.f18186a;
                v[] vVarArr3 = v.f18185e;
                return j$.time.temporal.s.f(j11, vVarArr3[vVarArr3.length - 1].f18186a);
            default:
                return aVar.f18349b;
        }
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoLocalDate P(Map map, j$.time.format.b0 b0Var) {
        return (u) super.P(map, b0Var);
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate U(Map map, j$.time.format.b0 b0Var) {
        u uVarB0;
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        Long l10 = (Long) map.get(aVar);
        v vVarO = l10 != null ? v.o(u(aVar).a(l10.longValue(), aVar)) : null;
        j$.time.temporal.a aVar2 = j$.time.temporal.a.YEAR_OF_ERA;
        Long l11 = (Long) map.get(aVar2);
        int iA = l11 != null ? u(aVar2).a(l11.longValue(), aVar2) : 0;
        if (vVarO == null && l11 != null && !map.containsKey(j$.time.temporal.a.YEAR) && b0Var != j$.time.format.b0.STRICT) {
            v[] vVarArr = v.f18185e;
            vVarO = ((v[]) Arrays.copyOf(vVarArr, vVarArr.length))[((v[]) Arrays.copyOf(vVarArr, vVarArr.length)).length - 1];
        }
        if (l11 != null && vVarO != null) {
            j$.time.temporal.a aVar3 = j$.time.temporal.a.MONTH_OF_YEAR;
            if (map.containsKey(aVar3)) {
                j$.time.temporal.a aVar4 = j$.time.temporal.a.DAY_OF_MONTH;
                if (map.containsKey(aVar4)) {
                    map.remove(aVar);
                    map.remove(aVar2);
                    if (b0Var == j$.time.format.b0.LENIENT) {
                        return new u(LocalDate.of((vVarO.f18187b.getYear() + iA) - 1, 1, 1)).Z(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar3)).longValue(), 1L), ChronoUnit.MONTHS).Z(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar4)).longValue(), 1L), ChronoUnit.DAYS);
                    }
                    int iA2 = u(aVar3).a(((Long) map.remove(aVar3)).longValue(), aVar3);
                    int iA3 = u(aVar4).a(((Long) map.remove(aVar4)).longValue(), aVar4);
                    if (b0Var != j$.time.format.b0.SMART) {
                        LocalDate localDate = u.f18180d;
                        Objects.requireNonNull(vVarO, "era");
                        LocalDate localDateOf = LocalDate.of((vVarO.f18187b.getYear() + iA) - 1, iA2, iA3);
                        if (localDateOf.a0(vVarO.f18187b) || vVarO != v.j(localDateOf)) {
                            throw new DateTimeException("year, month, and day not valid for Era");
                        }
                        return new u(vVarO, iA, localDateOf);
                    }
                    if (iA < 1) {
                        throw new DateTimeException("Invalid YearOfEra: " + iA);
                    }
                    int year = (vVarO.f18187b.getYear() + iA) - 1;
                    try {
                        uVarB0 = new u(LocalDate.of(year, iA2, iA3));
                    } catch (DateTimeException unused) {
                        uVarB0 = new u(LocalDate.of(year, iA2, 1)).b0(new j$.time.c(6));
                    }
                    if (uVarB0.f18182b == vVarO || j$.time.temporal.p.a(uVarB0, j$.time.temporal.a.YEAR_OF_ERA) <= 1 || iA <= 1) {
                        return uVarB0;
                    }
                    throw new DateTimeException("Invalid YearOfEra for Era: " + vVarO + " " + iA);
                }
            }
            j$.time.temporal.a aVar5 = j$.time.temporal.a.DAY_OF_YEAR;
            if (map.containsKey(aVar5)) {
                map.remove(aVar);
                map.remove(aVar2);
                if (b0Var == j$.time.format.b0.LENIENT) {
                    return new u(LocalDate.f0((vVarO.f18187b.getYear() + iA) - 1, 1)).Z(j$.com.android.tools.r8.a.S(((Long) map.remove(aVar5)).longValue(), 1L), ChronoUnit.DAYS);
                }
                int iA4 = u(aVar5).a(((Long) map.remove(aVar5)).longValue(), aVar5);
                LocalDate localDate2 = u.f18180d;
                Objects.requireNonNull(vVarO, "era");
                LocalDate localDateF0 = iA == 1 ? LocalDate.f0(vVarO.f18187b.getYear(), (vVarO.f18187b.Y() + iA4) - 1) : LocalDate.f0((vVarO.f18187b.getYear() + iA) - 1, iA4);
                if (localDateF0.a0(vVarO.f18187b) || vVarO != v.j(localDateF0)) {
                    throw new DateTimeException("Invalid parameters");
                }
                return new u(vVarO, iA, localDateF0);
            }
        }
        return null;
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoZonedDateTime Q(Instant instant, ZoneId zoneId) {
        return i.V(this, instant, zoneId);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public Object writeReplace() {
        return new b0((byte) 1, this);
    }
}
