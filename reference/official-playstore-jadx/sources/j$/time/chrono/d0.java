package j$.time.chrono;

import j$.time.Clock;
import j$.time.DateTimeException;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import j$.time.temporal.TemporalAccessor;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class d0 extends a implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d0 f18143d = new d0();
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map.put("en", new String[]{"BB", "BE"});
        map.put("th", new String[]{"BB", "BE"});
        map2.put("en", new String[]{"B.B.", "B.E."});
        map2.put("th", new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        map3.put("en", new String[]{"Before Buddhist", "Budhhist Era"});
        map3.put("th", new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    @Override // j$.time.chrono.Chronology
    public final j x(int i10) {
        if (i10 == 0) {
            return g0.BEFORE_BE;
        }
        if (i10 == 1) {
            return g0.BE;
        }
        throw new DateTimeException("Invalid era: " + i10);
    }

    @Override // j$.time.chrono.Chronology
    public final String k() {
        return "ThaiBuddhist";
    }

    @Override // j$.time.chrono.Chronology
    public final String o() {
        return "buddhist";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate N(int i10, int i11, int i12) {
        return new f0(LocalDate.of(i10 - 543, i11, i12));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate q(int i10, int i11) {
        return new f0(LocalDate.f0(i10 - 543, i11));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate j(long j10) {
        return new f0(LocalDate.e0(j10));
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate m() {
        return new f0(LocalDate.W(LocalDate.d0(Clock.b())));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate E(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof f0) {
            return (f0) temporalAccessor;
        }
        return new f0(LocalDate.W(temporalAccessor));
    }

    @Override // j$.time.chrono.Chronology
    public final boolean T(long j10) {
        return p.f18175d.T(j10 - 543);
    }

    @Override // j$.time.chrono.Chronology
    public final int z(j jVar, int i10) {
        if (jVar instanceof g0) {
            return jVar == g0.BE ? i10 : 1 - i10;
        }
        throw new ClassCastException("Era must be BuddhistEra");
    }

    private d0() {
    }

    @Override // j$.time.chrono.Chronology
    public final List w() {
        return j$.com.android.tools.r8.a.M(g0.values());
    }

    @Override // j$.time.chrono.Chronology
    public final j$.time.temporal.s u(j$.time.temporal.a aVar) {
        int i10 = c0.f18141a[aVar.ordinal()];
        if (i10 == 1) {
            j$.time.temporal.s sVar = j$.time.temporal.a.PROLEPTIC_MONTH.f18349b;
            return j$.time.temporal.s.f(sVar.f18373a + 6516, sVar.f18376d + 6516);
        }
        if (i10 == 2) {
            j$.time.temporal.s sVar2 = j$.time.temporal.a.YEAR.f18349b;
            return j$.time.temporal.s.g(1L, (-(sVar2.f18373a + 543)) + 1, sVar2.f18376d + 543);
        }
        if (i10 != 3) {
            return aVar.f18349b;
        }
        j$.time.temporal.s sVar3 = j$.time.temporal.a.YEAR.f18349b;
        return j$.time.temporal.s.f(sVar3.f18373a + 543, sVar3.f18376d + 543);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoLocalDate P(Map map, j$.time.format.b0 b0Var) {
        return (f0) super.P(map, b0Var);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoZonedDateTime Q(Instant instant, ZoneId zoneId) {
        return i.V(this, instant, zoneId);
    }

    public Object writeReplace() {
        return new b0((byte) 1, this);
    }
}
