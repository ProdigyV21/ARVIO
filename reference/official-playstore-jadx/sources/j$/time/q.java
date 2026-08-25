package j$.time;

import androidx.media3.common.C;
import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.format.c0;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class q implements Temporal, j$.time.temporal.l, Comparable, Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f18327b = 0;
    private static final long serialVersionUID = -23038383694477807L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18328a;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f18328a - ((q) obj).f18328a;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.l(j$.time.temporal.a.YEAR, 4, 10, c0.EXCEEDS_PAD);
        dateTimeFormatterBuilder.toFormatter(Locale.getDefault());
    }

    public static q U(int i10) {
        j$.time.temporal.a.YEAR.H(i10);
        return new q(i10);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        q qVarU;
        if (temporal instanceof q) {
            qVarU = (q) temporal;
        } else {
            Objects.requireNonNull(temporal, "temporal");
            try {
                if (!j$.time.chrono.p.f18175d.equals(Chronology.CC.a(temporal))) {
                    temporal = LocalDate.W(temporal);
                }
                qVarU = U(temporal.l(j$.time.temporal.a.YEAR));
            } catch (DateTimeException e5) {
                throw new DateTimeException("Unable to obtain Year from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), e5);
            }
        }
        if (qVar instanceof ChronoUnit) {
            long j10 = ((long) qVarU.f18328a) - ((long) this.f18328a);
            int i10 = p.f18326b[((ChronoUnit) qVar).ordinal()];
            if (i10 == 1) {
                return j10;
            }
            if (i10 == 2) {
                return j10 / 10;
            }
            if (i10 == 3) {
                return j10 / 100;
            }
            if (i10 == 4) {
                return j10 / 1000;
            }
            if (i10 == 5) {
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return qVarU.H(aVar) - H(aVar);
            }
            throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
        return qVar.between(this, qVarU);
    }

    public q(int i10) {
        this.f18328a = i10;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.YEAR || oVar == j$.time.temporal.a.YEAR_OF_ERA || oVar == j$.time.temporal.a.ERA : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.s.f(1L, this.f18328a <= 0 ? C.NANOS_PER_SECOND : 999999999L);
        }
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        return n(oVar).a(H(oVar), oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i10 = p.f18325a[((j$.time.temporal.a) oVar).ordinal()];
        if (i10 == 1) {
            int i11 = this.f18328a;
            if (i11 < 1) {
                i11 = 1 - i11;
            }
            return i11;
        }
        if (i10 == 2) {
            return this.f18328a;
        }
        if (i10 == 3) {
            return this.f18328a < 1 ? 0 : 1;
        }
        throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        localDate.getClass();
        return (q) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public final q c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (q) oVar.G(this, j10);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        aVar.H(j10);
        int i10 = p.f18325a[aVar.ordinal()];
        if (i10 == 1) {
            if (this.f18328a < 1) {
                j10 = 1 - j10;
            }
            return U((int) j10);
        }
        if (i10 == 2) {
            return U((int) j10);
        }
        if (i10 == 3) {
            return H(j$.time.temporal.a.ERA) == j10 ? this : U(1 - this.f18328a);
        }
        throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public final q d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (q) qVar.l(this, j10);
        }
        int i10 = p.f18326b[((ChronoUnit) qVar).ordinal()];
        if (i10 == 1) {
            return W(j10);
        }
        if (i10 == 2) {
            return W(j$.com.android.tools.r8.a.R(j10, 10));
        }
        if (i10 == 3) {
            return W(j$.com.android.tools.r8.a.R(j10, 100));
        }
        if (i10 == 4) {
            return W(j$.com.android.tools.r8.a.R(j10, 1000));
        }
        if (i10 == 5) {
            j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
            return c(j$.com.android.tools.r8.a.L(H(aVar), j10), aVar);
        }
        throw new j$.time.temporal.r("Unsupported unit: " + qVar);
    }

    public final q W(long j10) {
        if (j10 == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return U(aVar.f18349b.a(((long) this.f18328a) + j10, aVar));
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18367b) {
            return j$.time.chrono.p.f18175d;
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return ChronoUnit.YEARS;
        }
        return j$.time.temporal.p.c(this, cVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        if (!Chronology.CC.a(temporal).equals(j$.time.chrono.p.f18175d)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.c(this.f18328a, j$.time.temporal.a.YEAR);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof q) && this.f18328a == ((q) obj).f18328a;
    }

    public final int hashCode() {
        return this.f18328a;
    }

    public final String toString() {
        return Integer.toString(this.f18328a);
    }

    private Object writeReplace() {
        return new o((byte) 11, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
