package j$.time.chrono;

import androidx.media3.common.C;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements ChronoLocalDateTime, Temporal, j$.time.temporal.l, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient ChronoLocalDate f18144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient LocalTime f18145b;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object G(j$.time.c cVar) {
        return j$.com.android.tools.r8.a.t(this, cVar);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: M */
    public final /* synthetic */ int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        return j$.com.android.tools.r8.a.f(this, chronoLocalDateTime);
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final /* synthetic */ long toEpochSecond(ZoneOffset zoneOffset) {
        return j$.com.android.tools.r8.a.w(this, zoneOffset);
    }

    public static e U(Chronology chronology, Temporal temporal) {
        e eVar = (e) temporal;
        if (chronology.equals(eVar.f18144a.a())) {
            return eVar;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chronology.k() + ", actual: " + eVar.f18144a.a().k());
    }

    public e(ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "date");
        Objects.requireNonNull(localTime, "time");
        this.f18144a = chronoLocalDate;
        this.f18145b = localTime;
    }

    public final e Y(Temporal temporal, LocalTime localTime) {
        ChronoLocalDate chronoLocalDate = this.f18144a;
        return (chronoLocalDate == temporal && this.f18145b == localTime) ? this : new e(c.U(chronoLocalDate.a(), temporal), localTime);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return U(this.f18144a.a(), j$.time.temporal.p.b(this, j10, chronoUnit));
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final Chronology a() {
        return this.f18144a.a();
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoLocalDate f() {
        return this.f18144a;
    }

    public final int hashCode() {
        return this.f18144a.hashCode() ^ this.f18145b.hashCode();
    }

    public final String toString() {
        return this.f18144a.toString() + "T" + this.f18145b.toString();
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final LocalTime b() {
        return this.f18145b;
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
            if (!((j$.time.temporal.a) oVar).U()) {
                return this.f18144a.n(oVar);
            }
            LocalTime localTime = this.f18145b;
            localTime.getClass();
            return j$.time.temporal.p.d(localTime, oVar);
        }
        return oVar.m(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).U() ? this.f18145b.l(oVar) : this.f18144a.l(oVar);
        }
        return n(oVar).a(H(oVar), oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).U() ? this.f18145b.H(oVar) : this.f18144a.H(oVar);
        }
        return oVar.C(this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        if (j$.time.b.b(localDate)) {
            return Y(localDate, this.f18145b);
        }
        Chronology chronologyA = this.f18144a.a();
        localDate.getClass();
        return U(chronologyA, (e) j$.com.android.tools.r8.a.a(localDate, this));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public final e c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (((j$.time.temporal.a) oVar).U()) {
                return Y(this.f18144a, this.f18145b.c(j10, oVar));
            }
            return Y(this.f18144a.c(j10, oVar), this.f18145b);
        }
        return U(this.f18144a.a(), oVar.G(this, j10));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public final e d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return U(this.f18144a.a(), qVar.l(this, j10));
        }
        switch (d.f18142a[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return W(this.f18144a, 0L, 0L, 0L, j10);
            case 2:
                e eVarY = Y(this.f18144a.d(j10 / 86400000000L, (j$.time.temporal.q) ChronoUnit.DAYS), this.f18145b);
                return eVarY.W(eVarY.f18144a, 0L, 0L, 0L, (j10 % 86400000000L) * 1000);
            case 3:
                e eVarY2 = Y(this.f18144a.d(j10 / 86400000, (j$.time.temporal.q) ChronoUnit.DAYS), this.f18145b);
                return eVarY2.W(eVarY2.f18144a, 0L, 0L, 0L, (j10 % 86400000) * 1000000);
            case 4:
                return W(this.f18144a, 0L, 0L, j10, 0L);
            case 5:
                return W(this.f18144a, 0L, j10, 0L, 0L);
            case 6:
                return W(this.f18144a, j10, 0L, 0L, 0L);
            case 7:
                e eVarY3 = Y(this.f18144a.d(j10 / 256, (j$.time.temporal.q) ChronoUnit.DAYS), this.f18145b);
                return eVarY3.W(eVarY3.f18144a, (j10 % 256) * 12, 0L, 0L, 0L);
            default:
                return Y(this.f18144a.d(j10, qVar), this.f18145b);
        }
    }

    public final e W(ChronoLocalDate chronoLocalDate, long j10, long j11, long j12, long j13) {
        if ((j10 | j11 | j12 | j13) == 0) {
            return Y(chronoLocalDate, this.f18145b);
        }
        long j14 = j10 / 24;
        long j15 = ((j10 % 24) * 3600000000000L) + ((j11 % 1440) * 60000000000L) + ((j12 % 86400) * C.NANOS_PER_SECOND) + (j13 % 86400000000000L);
        long jG0 = this.f18145b.g0();
        long j16 = j15 + jG0;
        long jQ = j$.com.android.tools.r8.a.Q(j16, 86400000000000L) + j14 + (j11 / 1440) + (j12 / 86400) + (j13 / 86400000000000L);
        long jP = j$.com.android.tools.r8.a.P(j16, 86400000000000L);
        return Y(chronoLocalDate.d(jQ, (j$.time.temporal.q) ChronoUnit.DAYS), jP == jG0 ? this.f18145b : LocalTime.Z(jP));
    }

    @Override // j$.time.chrono.ChronoLocalDateTime
    public final ChronoZonedDateTime D(ZoneId zoneId) {
        return i.U(zoneId, null, this);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        Objects.requireNonNull(temporal, "endExclusive");
        ChronoLocalDateTime chronoLocalDateTimeK = this.f18144a.a().K(temporal);
        if (!(qVar instanceof ChronoUnit)) {
            Objects.requireNonNull(qVar, "unit");
            return qVar.between(this, chronoLocalDateTimeK);
        }
        ChronoUnit chronoUnit = (ChronoUnit) qVar;
        ChronoUnit chronoUnit2 = ChronoUnit.DAYS;
        if (chronoUnit.compareTo(chronoUnit2) >= 0) {
            ChronoLocalDate chronoLocalDateF = chronoLocalDateTimeK.f();
            if (chronoLocalDateTimeK.b().compareTo(this.f18145b) < 0) {
                chronoLocalDateF = chronoLocalDateF.v(1L, chronoUnit2);
            }
            return this.f18144a.g(chronoLocalDateF, qVar);
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
        long jH = chronoLocalDateTimeK.H(aVar) - this.f18144a.H(aVar);
        switch (d.f18142a[chronoUnit.ordinal()]) {
            case 1:
                jH = j$.com.android.tools.r8.a.R(jH, 86400000000000L);
                break;
            case 2:
                jH = j$.com.android.tools.r8.a.R(jH, 86400000000L);
                break;
            case 3:
                jH = j$.com.android.tools.r8.a.R(jH, 86400000L);
                break;
            case 4:
                jH = j$.com.android.tools.r8.a.R(jH, 86400);
                break;
            case 5:
                jH = j$.com.android.tools.r8.a.R(jH, 1440);
                break;
            case 6:
                jH = j$.com.android.tools.r8.a.R(jH, 24);
                break;
            case 7:
                jH = j$.com.android.tools.r8.a.R(jH, 2);
                break;
        }
        return j$.com.android.tools.r8.a.L(jH, this.f18145b.g(chronoLocalDateTimeK.b(), qVar));
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(f().I(), j$.time.temporal.a.EPOCH_DAY).c(b().g0(), j$.time.temporal.a.NANO_OF_DAY);
    }

    private Object writeReplace() {
        return new b0((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDateTime) && j$.com.android.tools.r8.a.f(this, (ChronoLocalDateTime) obj) == 0;
    }
}
