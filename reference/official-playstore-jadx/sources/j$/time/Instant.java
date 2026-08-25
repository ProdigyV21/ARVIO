package j$.time;

import androidx.media3.common.C;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class Instant implements Temporal, j$.time.temporal.l, Comparable<Instant>, Serializable {
    private static final long serialVersionUID = -665713676816604388L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18099b;
    public static final Instant EPOCH = new Instant(0, 0);
    public static final Instant MIN = ofEpochSecond(-31557014167219200L, 0);
    public static final Instant MAX = ofEpochSecond(31556889864403199L, 999999999);

    public static Instant now() {
        return Clock.systemUTC().instant();
    }

    public static Instant ofEpochSecond(long j10) {
        return U(j10, 0);
    }

    public static Instant ofEpochSecond(long j10, long j11) {
        return U(j$.com.android.tools.r8.a.L(j10, j$.com.android.tools.r8.a.Q(j11, C.NANOS_PER_SECOND)), (int) j$.com.android.tools.r8.a.P(j11, C.NANOS_PER_SECOND));
    }

    public static Instant ofEpochMilli(long j10) {
        long j11 = 1000;
        return U(j$.com.android.tools.r8.a.Q(j10, j11), ((int) j$.com.android.tools.r8.a.P(j10, j11)) * 1000000);
    }

    public static Instant V(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Instant) {
            return (Instant) temporalAccessor;
        }
        Objects.requireNonNull(temporalAccessor, "temporal");
        try {
            return ofEpochSecond(temporalAccessor.H(j$.time.temporal.a.INSTANT_SECONDS), temporalAccessor.l(j$.time.temporal.a.NANO_OF_SECOND));
        } catch (DateTimeException e5) {
            throw new DateTimeException("Unable to obtain Instant from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName(), e5);
        }
    }

    public static Instant parse(CharSequence charSequence) {
        return (Instant) DateTimeFormatter.ISO_INSTANT.a(charSequence, new c(0));
    }

    public static Instant U(long j10, int i10) {
        if ((((long) i10) | j10) == 0) {
            return EPOCH;
        }
        if (j10 < -31557014167219200L || j10 > 31556889864403199L) {
            throw new DateTimeException("Instant exceeds minimum or maximum instant");
        }
        return new Instant(j10, i10);
    }

    public Instant(long j10, int i10) {
        this.f18098a = j10;
        this.f18099b = i10;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.INSTANT_SECONDS || oVar == j$.time.temporal.a.NANO_OF_SECOND || oVar == j$.time.temporal.a.MICRO_OF_SECOND || oVar == j$.time.temporal.a.MILLI_OF_SECOND : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return j$.time.temporal.p.d(this, oVar).a(oVar.C(this), oVar);
        }
        int i10 = d.f18193a[((j$.time.temporal.a) oVar).ordinal()];
        if (i10 == 1) {
            return this.f18099b;
        }
        if (i10 == 2) {
            return this.f18099b / 1000;
        }
        if (i10 == 3) {
            return this.f18099b / 1000000;
        }
        if (i10 == 4) {
            j$.time.temporal.a aVar = j$.time.temporal.a.INSTANT_SECONDS;
            aVar.f18349b.a(this.f18098a, aVar);
        }
        throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        int i10;
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i11 = d.f18193a[((j$.time.temporal.a) oVar).ordinal()];
        if (i11 == 1) {
            i10 = this.f18099b;
        } else if (i11 == 2) {
            i10 = this.f18099b / 1000;
        } else {
            if (i11 != 3) {
                if (i11 == 4) {
                    return this.f18098a;
                }
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
            }
            i10 = this.f18099b / 1000000;
        }
        return i10;
    }

    public long getEpochSecond() {
        return this.f18098a;
    }

    public int getNano() {
        return this.f18099b;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        localDate.getClass();
        return (Instant) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (Instant) oVar.G(this, j10);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        aVar.H(j10);
        int i10 = d.f18193a[aVar.ordinal()];
        if (i10 != 1) {
            if (i10 == 2) {
                int i11 = ((int) j10) * 1000;
                if (i11 != this.f18099b) {
                    return U(this.f18098a, i11);
                }
            } else if (i10 == 3) {
                int i12 = ((int) j10) * 1000000;
                if (i12 != this.f18099b) {
                    return U(this.f18098a, i12);
                }
            } else {
                if (i10 != 4) {
                    throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
                }
                if (j10 != this.f18098a) {
                    return U(j10, this.f18099b);
                }
            }
        } else if (j10 != this.f18099b) {
            return U(this.f18098a, (int) j10);
        }
        return this;
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public final Instant d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (Instant) qVar.l(this, j10);
        }
        switch (d.f18194b[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return plusNanos(j10);
            case 2:
                return W(j10 / 1000000, (j10 % 1000000) * 1000);
            case 3:
                return W(j10 / 1000, (j10 % 1000) * 1000000);
            case 4:
                return plusSeconds(j10);
            case 5:
                return plusSeconds(j$.com.android.tools.r8.a.R(j10, 60));
            case 6:
                return plusSeconds(j$.com.android.tools.r8.a.R(j10, 3600));
            case 7:
                return plusSeconds(j$.com.android.tools.r8.a.R(j10, 43200));
            case 8:
                return plusSeconds(j$.com.android.tools.r8.a.R(j10, 86400));
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public Instant plusSeconds(long j10) {
        return W(j10, 0L);
    }

    public Instant plusNanos(long j10) {
        return W(0L, j10);
    }

    public final Instant W(long j10, long j11) {
        if ((j10 | j11) == 0) {
            return this;
        }
        return ofEpochSecond(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.L(this.f18098a, j10), j11 / C.NANOS_PER_SECOND), ((long) this.f18099b) + (j11 % C.NANOS_PER_SECOND));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18368c) {
            return ChronoUnit.NANOS;
        }
        if (cVar == j$.time.temporal.p.f18367b || cVar == j$.time.temporal.p.f18366a || cVar == j$.time.temporal.p.f18370e || cVar == j$.time.temporal.p.f18369d || cVar == j$.time.temporal.p.f18371f || cVar == j$.time.temporal.p.f18372g) {
            return null;
        }
        return cVar.h(this);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(this.f18098a, j$.time.temporal.a.INSTANT_SECONDS).c(this.f18099b, j$.time.temporal.a.NANO_OF_SECOND);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        Instant instantV = V(temporal);
        if (!(qVar instanceof ChronoUnit)) {
            return qVar.between(this, instantV);
        }
        switch (d.f18194b[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j$.com.android.tools.r8.a.S(instantV.f18098a, this.f18098a), C.NANOS_PER_SECOND), instantV.f18099b - this.f18099b);
            case 2:
                return j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j$.com.android.tools.r8.a.S(instantV.f18098a, this.f18098a), C.NANOS_PER_SECOND), instantV.f18099b - this.f18099b) / 1000;
            case 3:
                return j$.com.android.tools.r8.a.S(instantV.toEpochMilli(), toEpochMilli());
            case 4:
                return Y(instantV);
            case 5:
                return Y(instantV) / 60;
            case 6:
                return Y(instantV) / 3600;
            case 7:
                return Y(instantV) / 43200;
            case 8:
                return Y(instantV) / 86400;
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public final long Y(Instant instant) {
        long jS = j$.com.android.tools.r8.a.S(instant.f18098a, this.f18098a);
        long j10 = instant.f18099b - this.f18099b;
        return (jS <= 0 || j10 >= 0) ? (jS >= 0 || j10 <= 0) ? jS : jS + 1 : jS - 1;
    }

    public OffsetDateTime atOffset(ZoneOffset zoneOffset) {
        return OffsetDateTime.V(this, zoneOffset);
    }

    public ZonedDateTime atZone(ZoneId zoneId) {
        return ZonedDateTime.ofInstant(this, zoneId);
    }

    public long toEpochMilli() {
        long j10 = this.f18098a;
        return (j10 >= 0 || this.f18099b <= 0) ? j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j10, 1000), this.f18099b / 1000000) : j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j10 + 1, 1000), (this.f18099b / 1000000) - 1000);
    }

    @Override // java.lang.Comparable
    public int compareTo(Instant instant) {
        int iCompare = Long.compare(this.f18098a, instant.f18098a);
        return iCompare != 0 ? iCompare : this.f18099b - instant.f18099b;
    }

    public boolean isAfter(Instant instant) {
        return compareTo(instant) > 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Instant) {
            Instant instant = (Instant) obj;
            if (this.f18098a == instant.f18098a && this.f18099b == instant.f18099b) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j10 = this.f18098a;
        return (this.f18099b * 51) + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return DateTimeFormatter.ISO_INSTANT.format(this);
    }

    private Object writeReplace() {
        return new o((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
