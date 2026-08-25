package j$.time;

import androidx.media3.common.C;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements Temporal, j$.time.temporal.l, Comparable, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f18316c = 0;
    private static final long serialVersionUID = 7264499704384272492L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocalTime f18317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ZoneOffset f18318b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        m mVar = (m) obj;
        if (this.f18318b.equals(mVar.f18318b)) {
            return this.f18317a.compareTo(mVar.f18317a);
        }
        int iCompare = Long.compare(V(), mVar.V());
        return iCompare == 0 ? this.f18317a.compareTo(mVar.f18317a) : iCompare;
    }

    static {
        LocalTime localTime = LocalTime.f18109e;
        ZoneOffset zoneOffset = ZoneOffset.f18125g;
        localTime.getClass();
        new m(localTime, zoneOffset);
        LocalTime localTime2 = LocalTime.f18110f;
        ZoneOffset zoneOffset2 = ZoneOffset.f18124f;
        localTime2.getClass();
        new m(localTime2, zoneOffset2);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        m mVar;
        if (temporal instanceof m) {
            mVar = (m) temporal;
        } else {
            try {
                mVar = new m(LocalTime.W(temporal), ZoneOffset.a0(temporal));
            } catch (DateTimeException e5) {
                throw new DateTimeException("Unable to obtain OffsetTime from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), e5);
            }
        }
        if (qVar instanceof ChronoUnit) {
            long jV = mVar.V() - V();
            switch (l.f18315a[((ChronoUnit) qVar).ordinal()]) {
                case 1:
                    return jV;
                case 2:
                    return jV / 1000;
                case 3:
                    return jV / 1000000;
                case 4:
                    return jV / C.NANOS_PER_SECOND;
                case 5:
                    return jV / 60000000000L;
                case 6:
                    return jV / 3600000000000L;
                case 7:
                    return jV / 43200000000000L;
                default:
                    throw new j$.time.temporal.r("Unsupported unit: " + qVar);
            }
        }
        return qVar.between(this, mVar);
    }

    public m(LocalTime localTime, ZoneOffset zoneOffset) {
        this.f18317a = (LocalTime) Objects.requireNonNull(localTime, "time");
        this.f18318b = (ZoneOffset) Objects.requireNonNull(zoneOffset, "offset");
    }

    public final m W(LocalTime localTime, ZoneOffset zoneOffset) {
        return (this.f18317a == localTime && this.f18318b.equals(zoneOffset)) ? this : new m(localTime, zoneOffset);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) oVar).U() || oVar == j$.time.temporal.a.OFFSET_SECONDS : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar != j$.time.temporal.a.OFFSET_SECONDS) {
                LocalTime localTime = this.f18317a;
                localTime.getClass();
                return j$.time.temporal.p.d(localTime, oVar);
            }
            return ((j$.time.temporal.a) oVar).f18349b;
        }
        return oVar.m(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return this.f18318b.f18126b;
            }
            return this.f18317a.H(oVar);
        }
        return oVar.C(this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        localDate.getClass();
        return (m) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
                j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
                return W(this.f18317a, ZoneOffset.d0(aVar.f18349b.a(j10, aVar)));
            }
            return W(this.f18317a.c(j10, oVar), this.f18318b);
        }
        return (m) oVar.G(this, j10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public final m d(long j10, j$.time.temporal.q qVar) {
        if (qVar instanceof ChronoUnit) {
            return W(this.f18317a.d(j10, qVar), this.f18318b);
        }
        return (m) qVar.l(this, j10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18369d || cVar == j$.time.temporal.p.f18370e) {
            return this.f18318b;
        }
        if (((cVar == j$.time.temporal.p.f18366a) || (cVar == j$.time.temporal.p.f18367b)) || cVar == j$.time.temporal.p.f18371f) {
            return null;
        }
        if (cVar == j$.time.temporal.p.f18372g) {
            return this.f18317a;
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return ChronoUnit.NANOS;
        }
        return cVar.h(this);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(this.f18317a.g0(), j$.time.temporal.a.NANO_OF_DAY).c(this.f18318b.f18126b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final long V() {
        return this.f18317a.g0() - (((long) this.f18318b.f18126b) * C.NANOS_PER_SECOND);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (this.f18317a.equals(mVar.f18317a) && this.f18318b.equals(mVar.f18318b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f18317a.hashCode() ^ this.f18318b.f18126b;
    }

    public final String toString() {
        return this.f18317a.toString() + this.f18318b.f18127c;
    }

    private Object writeReplace() {
        return new o((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
