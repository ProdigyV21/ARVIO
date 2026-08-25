package j$.time.chrono;

import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements ChronoLocalDate, Temporal, j$.time.temporal.l, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object G(j$.time.c cVar) {
        return j$.com.android.tools.r8.a.s(this, cVar);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: S */
    public final /* synthetic */ int compareTo(ChronoLocalDate chronoLocalDate) {
        return j$.com.android.tools.r8.a.e(this, chronoLocalDate);
    }

    public abstract ChronoLocalDate W(long j10);

    public abstract ChronoLocalDate X(long j10);

    public abstract ChronoLocalDate Y(long j10);

    @Override // j$.time.chrono.ChronoLocalDate, j$.time.temporal.TemporalAccessor
    public /* synthetic */ boolean e(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.q(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public /* synthetic */ j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ Temporal r(Temporal temporal) {
        return j$.com.android.tools.r8.a.a(this, temporal);
    }

    public static ChronoLocalDate U(Chronology chronology, Temporal temporal) {
        ChronoLocalDate chronoLocalDate = (ChronoLocalDate) temporal;
        if (chronology.equals(chronoLocalDate.a())) {
            return chronoLocalDate;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + chronology.k() + ", actual: " + chronoLocalDate.a().k());
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public ChronoLocalDateTime J(LocalTime localTime) {
        return new e(this, localTime);
    }

    @Override // j$.time.temporal.Temporal
    public ChronoLocalDate d(long j10, j$.time.temporal.q qVar) {
        boolean z = qVar instanceof ChronoUnit;
        if (!z) {
            if (!z) {
                return U(a(), qVar.l(this, j10));
            }
            throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
        switch (b.f18138a[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return W(j10);
            case 2:
                return W(j$.com.android.tools.r8.a.R(j10, 7));
            case 3:
                return X(j10);
            case 4:
                return Y(j10);
            case 5:
                return Y(j$.com.android.tools.r8.a.R(j10, 10));
            case 6:
                return Y(j$.com.android.tools.r8.a.R(j10, 100));
            case 7:
                return Y(j$.com.android.tools.r8.a.R(j10, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return c(j$.com.android.tools.r8.a.L(H(aVar), j10), (j$.time.temporal.o) aVar);
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public j L() {
        return a().x(j$.time.temporal.p.a(this, j$.time.temporal.a.ERA));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public boolean t() {
        return a().T(H(j$.time.temporal.a.YEAR));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public int R() {
        return t() ? 366 : 365;
    }

    @Override // j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        Objects.requireNonNull(temporal, "endExclusive");
        ChronoLocalDate chronoLocalDateE = a().E(temporal);
        if (!(qVar instanceof ChronoUnit)) {
            Objects.requireNonNull(qVar, "unit");
            return qVar.between(this, chronoLocalDateE);
        }
        switch (b.f18138a[((ChronoUnit) qVar).ordinal()]) {
            case 1:
                return chronoLocalDateE.I() - I();
            case 2:
                return (chronoLocalDateE.I() - I()) / 7;
            case 3:
                return V(chronoLocalDateE);
            case 4:
                return V(chronoLocalDateE) / 12;
            case 5:
                return V(chronoLocalDateE) / 120;
            case 6:
                return V(chronoLocalDateE) / 1200;
            case 7:
                return V(chronoLocalDateE) / 12000;
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return chronoLocalDateE.H(aVar) - H(aVar);
            default:
                throw new j$.time.temporal.r("Unsupported unit: " + qVar);
        }
    }

    public final long V(ChronoLocalDate chronoLocalDate) {
        if (a().u(j$.time.temporal.a.MONTH_OF_YEAR).f18376d != 12) {
            throw new IllegalStateException("ChronoLocalDateImpl only supports Chronologies with 12 months per year");
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.PROLEPTIC_MONTH;
        long jH = H(aVar) * 32;
        j$.time.temporal.a aVar2 = j$.time.temporal.a.DAY_OF_MONTH;
        return (((chronoLocalDate.H(aVar) * 32) + ((long) chronoLocalDate.l(aVar2))) - (jH + ((long) j$.time.temporal.p.a(this, aVar2)))) / 32;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDate) && j$.com.android.tools.r8.a.e(this, (ChronoLocalDate) obj) == 0;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public int hashCode() {
        long jI = I();
        return ((int) (jI ^ (jI >>> 32))) ^ a().hashCode();
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: B */
    public ChronoLocalDate m(j$.time.temporal.l lVar) {
        return U(a(), lVar.r(this));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final String toString() {
        long jH = H(j$.time.temporal.a.YEAR_OF_ERA);
        long jH2 = H(j$.time.temporal.a.MONTH_OF_YEAR);
        long jH3 = H(j$.time.temporal.a.DAY_OF_MONTH);
        StringBuilder sb2 = new StringBuilder(30);
        sb2.append(a().toString());
        sb2.append(" ");
        sb2.append(L());
        sb2.append(" ");
        sb2.append(jH);
        sb2.append(jH2 < 10 ? "-0" : "-");
        sb2.append(jH2);
        sb2.append(jH3 < 10 ? "-0" : "-");
        sb2.append(jH3);
        return sb2.toString();
    }

    @Override // j$.time.temporal.Temporal
    public ChronoLocalDate c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
        return U(a(), oVar.G(this, j10));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public ChronoLocalDate O(j$.time.temporal.n nVar) {
        return U(a(), nVar.l(this));
    }

    @Override // j$.time.temporal.Temporal
    public ChronoLocalDate v(long j10, j$.time.temporal.q qVar) {
        return U(a(), j$.time.temporal.p.b(this, j10, qVar));
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public long I() {
        return H(j$.time.temporal.a.EPOCH_DAY);
    }
}
