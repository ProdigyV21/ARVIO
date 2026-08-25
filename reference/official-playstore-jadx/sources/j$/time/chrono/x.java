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
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class x extends a implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final x f18190d = new x();
    private static final long serialVersionUID = 1039765215346859963L;

    @Override // j$.time.chrono.Chronology
    public final String k() {
        return "Minguo";
    }

    @Override // j$.time.chrono.Chronology
    public final j x(int i10) {
        if (i10 == 0) {
            return a0.BEFORE_ROC;
        }
        if (i10 == 1) {
            return a0.ROC;
        }
        throw new DateTimeException("Invalid era: " + i10);
    }

    @Override // j$.time.chrono.Chronology
    public final String o() {
        return "roc";
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate N(int i10, int i11, int i12) {
        return new z(LocalDate.of(i10 + 1911, i11, i12));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate q(int i10, int i11) {
        return new z(LocalDate.f0(i10 + 1911, i11));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate j(long j10) {
        return new z(LocalDate.e0(j10));
    }

    @Override // j$.time.chrono.a
    public final ChronoLocalDate m() {
        return new z(LocalDate.W(LocalDate.d0(Clock.b())));
    }

    @Override // j$.time.chrono.Chronology
    public final ChronoLocalDate E(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof z) {
            return (z) temporalAccessor;
        }
        return new z(LocalDate.W(temporalAccessor));
    }

    @Override // j$.time.chrono.Chronology
    public final boolean T(long j10) {
        return p.f18175d.T(j10 + 1911);
    }

    @Override // j$.time.chrono.Chronology
    public final int z(j jVar, int i10) {
        if (jVar instanceof a0) {
            return jVar == a0.ROC ? i10 : 1 - i10;
        }
        throw new ClassCastException("Era must be MinguoEra");
    }

    @Override // j$.time.chrono.Chronology
    public final List w() {
        return j$.com.android.tools.r8.a.M(a0.values());
    }

    @Override // j$.time.chrono.Chronology
    public final j$.time.temporal.s u(j$.time.temporal.a aVar) {
        int i10 = w.f18189a[aVar.ordinal()];
        if (i10 == 1) {
            j$.time.temporal.s sVar = j$.time.temporal.a.PROLEPTIC_MONTH.f18349b;
            return j$.time.temporal.s.f(sVar.f18373a - 22932, sVar.f18376d - 22932);
        }
        if (i10 == 2) {
            j$.time.temporal.s sVar2 = j$.time.temporal.a.YEAR.f18349b;
            return j$.time.temporal.s.g(1L, sVar2.f18376d - 1911, (-sVar2.f18373a) + 1912);
        }
        if (i10 != 3) {
            return aVar.f18349b;
        }
        j$.time.temporal.s sVar3 = j$.time.temporal.a.YEAR.f18349b;
        return j$.time.temporal.s.f(sVar3.f18373a - 1911, sVar3.f18376d - 1911);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.Chronology
    public final ChronoLocalDate P(Map map, j$.time.format.b0 b0Var) {
        return (z) super.P(map, b0Var);
    }

    private x() {
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
