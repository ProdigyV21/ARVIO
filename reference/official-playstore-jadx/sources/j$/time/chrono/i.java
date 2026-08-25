package j$.time.chrono;

import j$.time.Duration;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class i implements ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient e f18156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient ZoneOffset f18157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient ZoneId f18158c;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object G(j$.time.c cVar) {
        return j$.com.android.tools.r8.a.u(this, cVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.l(this, oVar);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final /* synthetic */ long toEpochSecond() {
        return j$.com.android.tools.r8.a.x(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        return j$.com.android.tools.r8.a.g(this, chronoZonedDateTime);
    }

    public static i U(ZoneId zoneId, ZoneOffset zoneOffset, e eVar) {
        Objects.requireNonNull(eVar, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new i(zoneId, (ZoneOffset) zoneId, eVar);
        }
        j$.time.zone.f fVarV = zoneId.V();
        LocalDateTime localDateTimeV = LocalDateTime.V(eVar);
        List listF = fVarV.f(localDateTimeV);
        if (listF.size() == 1) {
            zoneOffset = (ZoneOffset) listF.get(0);
        } else if (listF.size() != 0) {
            if (zoneOffset == null || !listF.contains(zoneOffset)) {
                zoneOffset = (ZoneOffset) listF.get(0);
            }
            eVar = eVar;
        } else {
            Object objE = fVarV.e(localDateTimeV);
            j$.time.zone.b bVar = objE instanceof j$.time.zone.b ? (j$.time.zone.b) objE : null;
            eVar = eVar.W(eVar.f18144a, 0L, 0L, Duration.m(bVar.f18393d.f18126b - bVar.f18392c.f18126b, 0).f18096a, 0L);
            zoneOffset = bVar.f18393d;
        }
        Objects.requireNonNull(zoneOffset, "offset");
        return new i(zoneId, zoneOffset, eVar);
    }

    public static i V(Chronology chronology, Instant instant, ZoneId zoneId) {
        ZoneOffset zoneOffsetD = zoneId.V().d(instant);
        Objects.requireNonNull(zoneOffsetD, "offset");
        return new i(zoneId, zoneOffsetD, (e) chronology.K(LocalDateTime.Y(instant.getEpochSecond(), instant.getNano(), zoneOffsetD)));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar != j$.time.temporal.a.INSTANT_SECONDS && oVar != j$.time.temporal.a.OFFSET_SECONDS) {
                return ((e) s()).n(oVar);
            }
            return ((j$.time.temporal.a) oVar).f18349b;
        }
        return oVar.m(this);
    }

    public static i r(Chronology chronology, Temporal temporal) {
        i iVar = (i) temporal;
        if (chronology.equals(iVar.a())) {
            return iVar;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chronology.k() + ", actual: " + iVar.a().k());
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i10 = g.f18153a[((j$.time.temporal.a) oVar).ordinal()];
            if (i10 == 1) {
                return toEpochSecond();
            }
            if (i10 != 2) {
                return ((e) s()).H(oVar);
            }
            return h().f18126b;
        }
        return oVar.C(this);
    }

    public i(ZoneId zoneId, ZoneOffset zoneOffset, e eVar) {
        this.f18156a = (e) Objects.requireNonNull(eVar, "dateTime");
        this.f18157b = (ZoneOffset) Objects.requireNonNull(zoneOffset, "offset");
        this.f18158c = (ZoneId) Objects.requireNonNull(zoneId, "zone");
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneOffset h() {
        return this.f18157b;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDate f() {
        return ((e) s()).f();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final LocalTime b() {
        return ((e) s()).b();
    }

    public final int hashCode() {
        return (this.f18156a.hashCode() ^ this.f18157b.f18126b) ^ Integer.rotateLeft(this.f18158c.hashCode(), 3);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoLocalDateTime s() {
        return this.f18156a;
    }

    public final String toString() {
        String str = this.f18156a.toString() + this.f18157b.f18127c;
        ZoneOffset zoneOffset = this.f18157b;
        ZoneId zoneId = this.f18158c;
        if (zoneOffset == zoneId) {
            return str;
        }
        return str + "[" + zoneId.toString() + "]";
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ZoneId F() {
        return this.f18158c;
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Chronology a() {
        return f().a();
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime A(ZoneId zoneId) {
        return U(zoneId, this.f18157b, this.f18156a);
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final ChronoZonedDateTime i(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        if (this.f18158c.equals(zoneId)) {
            return this;
        }
        e eVar = this.f18156a;
        ZoneOffset zoneOffset = this.f18157b;
        eVar.getClass();
        return V(a(), j$.com.android.tools.r8.a.y(eVar, zoneOffset), zoneId);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return true;
        }
        return oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return r(a(), oVar.G(this, j10));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        int i10 = h.f18155a[aVar.ordinal()];
        if (i10 == 1) {
            return d(j10 - j$.com.android.tools.r8.a.x(this), ChronoUnit.SECONDS);
        }
        if (i10 != 2) {
            return U(this.f18158c, this.f18157b, this.f18156a.c(j10, oVar));
        }
        ZoneOffset zoneOffsetD0 = ZoneOffset.d0(aVar.f18349b.a(j10, aVar));
        e eVar = this.f18156a;
        eVar.getClass();
        return V(a(), j$.com.android.tools.r8.a.y(eVar, zoneOffsetD0), this.f18158c);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public final i d(long j10, j$.time.temporal.q qVar) {
        if (qVar instanceof ChronoUnit) {
            return r(a(), this.f18156a.d(j10, qVar).r(this));
        }
        return r(a(), qVar.l(this, j10));
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        Objects.requireNonNull(temporal, "endExclusive");
        ChronoZonedDateTime chronoZonedDateTimeP = a().p(temporal);
        if (qVar instanceof ChronoUnit) {
            return this.f18156a.g(chronoZonedDateTimeP.i(this.f18157b).s(), qVar);
        }
        Objects.requireNonNull(qVar, "unit");
        return qVar.between(this, chronoZonedDateTimeP);
    }

    private Object writeReplace() {
        return new b0((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && j$.com.android.tools.r8.a.g(this, (ChronoZonedDateTime) obj) == 0;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        return r(a(), localDate.r(this));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return r(a(), j$.time.temporal.p.b(this, j10, chronoUnit));
    }

    @Override // j$.time.chrono.ChronoZonedDateTime
    public final Instant toInstant() {
        return Instant.ofEpochSecond(toEpochSecond(), b().f18115d);
    }
}
