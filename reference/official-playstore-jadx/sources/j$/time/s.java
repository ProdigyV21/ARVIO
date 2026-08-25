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
public final class s implements Temporal, j$.time.temporal.l, Comparable, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f18331c = 0;
    private static final long serialVersionUID = 4183400860270640070L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18333b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        s sVar = (s) obj;
        int i10 = this.f18332a - sVar.f18332a;
        return i10 == 0 ? this.f18333b - sVar.f18333b : i10;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.l(j$.time.temporal.a.YEAR, 4, 10, c0.EXCEEDS_PAD);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral = dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilderAppendLiteral.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        dateTimeFormatterBuilderAppendLiteral.toFormatter(Locale.getDefault());
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        s sVar;
        if (temporal instanceof s) {
            sVar = (s) temporal;
        } else {
            Objects.requireNonNull(temporal, "temporal");
            try {
                if (!j$.time.chrono.p.f18175d.equals(Chronology.CC.a(temporal))) {
                    temporal = LocalDate.W(temporal);
                }
                j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
                int iL = temporal.l(aVar);
                j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
                int iL2 = temporal.l(aVar2);
                aVar.H(iL);
                aVar2.H(iL2);
                sVar = new s(iL, iL2);
            } catch (DateTimeException e5) {
                throw new DateTimeException("Unable to obtain YearMonth from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), e5);
            }
        }
        if (qVar instanceof ChronoUnit) {
            long jU = sVar.U() - U();
            switch (r.f18330b[((ChronoUnit) qVar).ordinal()]) {
                case 1:
                    return jU;
                case 2:
                    return jU / 12;
                case 3:
                    return jU / 120;
                case 4:
                    return jU / 1200;
                case 5:
                    return jU / 12000;
                case 6:
                    j$.time.temporal.a aVar3 = j$.time.temporal.a.ERA;
                    return sVar.H(aVar3) - H(aVar3);
                default:
                    throw new j$.time.temporal.r("Unsupported unit: " + qVar);
            }
        }
        return qVar.between(this, sVar);
    }

    public s(int i10, int i11) {
        this.f18332a = i10;
        this.f18333b = i11;
    }

    public final s Y(int i10, int i11) {
        return (this.f18332a == i10 && this.f18333b == i11) ? this : new s(i10, i11);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.YEAR || oVar == j$.time.temporal.a.MONTH_OF_YEAR || oVar == j$.time.temporal.a.PROLEPTIC_MONTH || oVar == j$.time.temporal.a.YEAR_OF_ERA || oVar == j$.time.temporal.a.ERA : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.s.f(1L, this.f18332a <= 0 ? C.NANOS_PER_SECOND : 999999999L);
        }
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        return n(oVar).a(H(oVar), oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        int i10;
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i11 = r.f18329a[((j$.time.temporal.a) oVar).ordinal()];
        if (i11 == 1) {
            i10 = this.f18333b;
        } else {
            if (i11 == 2) {
                return U();
            }
            if (i11 == 3) {
                int i12 = this.f18332a;
                if (i12 < 1) {
                    i12 = 1 - i12;
                }
                return i12;
            }
            if (i11 != 4) {
                if (i11 == 5) {
                    return this.f18332a < 1 ? 0 : 1;
                }
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
            }
            i10 = this.f18332a;
        }
        return i10;
    }

    public final long U() {
        return ((((long) this.f18332a) * 12) + ((long) this.f18333b)) - 1;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        localDate.getClass();
        return (s) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public final s c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (s) oVar.G(this, j10);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        aVar.H(j10);
        int i10 = r.f18329a[aVar.ordinal()];
        if (i10 == 1) {
            int i11 = (int) j10;
            j$.time.temporal.a.MONTH_OF_YEAR.H(i11);
            return Y(this.f18332a, i11);
        }
        if (i10 == 2) {
            return W(j10 - U());
        }
        if (i10 == 3) {
            if (this.f18332a < 1) {
                j10 = 1 - j10;
            }
            int i12 = (int) j10;
            j$.time.temporal.a.YEAR.H(i12);
            return Y(i12, this.f18333b);
        }
        if (i10 == 4) {
            int i13 = (int) j10;
            j$.time.temporal.a.YEAR.H(i13);
            return Y(i13, this.f18333b);
        }
        if (i10 != 5) {
            throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
        if (H(j$.time.temporal.a.ERA) == j10) {
            return this;
        }
        int i14 = 1 - this.f18332a;
        j$.time.temporal.a.YEAR.H(i14);
        return Y(i14, this.f18333b);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public final s d(long j10, j$.time.temporal.q qVar) {
        if (!(qVar instanceof ChronoUnit)) {
            return (s) qVar.l(this, j10);
        }
        switch (r.f18330b[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return W(j10);
            case 2:
                return X(j10);
            case 3:
                return X(j$.com.android.tools.r8.a.R(j10, 10));
            case 4:
                return X(j$.com.android.tools.r8.a.R(j10, 100));
            case 5:
                return X(j$.com.android.tools.r8.a.R(j10, 1000));
            case 6:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.L(H(aVar), j10), aVar);
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public final s X(long j10) {
        if (j10 == 0) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        return Y(aVar.f18349b.a(((long) this.f18332a) + j10, aVar), this.f18333b);
    }

    public final s W(long j10) {
        if (j10 == 0) {
            return this;
        }
        long j11 = (((long) this.f18332a) * 12) + ((long) (this.f18333b - 1)) + j10;
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        long j12 = 12;
        return Y(aVar.f18349b.a(j$.com.android.tools.r8.a.Q(j11, j12), aVar), ((int) j$.com.android.tools.r8.a.P(j11, j12)) + 1);
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
            return ChronoUnit.MONTHS;
        }
        return j$.time.temporal.p.c(this, cVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        if (!Chronology.CC.a(temporal).equals(j$.time.chrono.p.f18175d)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        return temporal.c(U(), j$.time.temporal.a.PROLEPTIC_MONTH);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof s) {
            s sVar = (s) obj;
            if (this.f18332a == sVar.f18332a && this.f18333b == sVar.f18333b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f18332a ^ (this.f18333b << 27);
    }

    public final String toString() {
        int iAbs = Math.abs(this.f18332a);
        StringBuilder sb2 = new StringBuilder(9);
        if (iAbs < 1000) {
            int i10 = this.f18332a;
            if (i10 < 0) {
                sb2.append(i10 - 10000);
                sb2.deleteCharAt(1);
            } else {
                sb2.append(i10 + 10000);
                sb2.deleteCharAt(0);
            }
        } else {
            sb2.append(this.f18332a);
        }
        sb2.append(this.f18333b < 10 ? "-0" : "-");
        sb2.append(this.f18333b);
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 12, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
