package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class u extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LocalDate f18180d = LocalDate.of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient LocalDate f18181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient v f18182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient int f18183c;

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime J(LocalTime localTime) {
        return new e(this, localTime);
    }

    public u(LocalDate localDate) {
        if (localDate.a0(f18180d)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
        }
        v vVarJ = v.j(localDate);
        this.f18182b = vVarJ;
        this.f18183c = (localDate.getYear() - vVarJ.f18187b.getYear()) + 1;
        this.f18181a = localDate;
    }

    public u(v vVar, int i10, LocalDate localDate) {
        if (localDate.a0(f18180d)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
        }
        this.f18182b = vVar;
        this.f18183c = i10;
        this.f18181a = localDate;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology a() {
        return s.f18178d;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        s.f18178d.getClass();
        return this.f18181a.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final j L() {
        return this.f18182b;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final int R() {
        int iR;
        v vVarK = this.f18182b.k();
        if (vVarK != null && vVarK.f18187b.getYear() == this.f18181a.getYear()) {
            iR = vVarK.f18187b.Y() - 1;
        } else {
            iR = this.f18181a.R();
        }
        return this.f18183c == 1 ? iR - (this.f18182b.f18187b.Y() - 1) : iR;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate, j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH || oVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR || oVar == j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH || oVar == j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).isDateBased();
        }
        return oVar != null && oVar.l(this);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.m(this);
        }
        if (!e(oVar)) {
            throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        int i10 = t.f18179a[aVar.ordinal()];
        if (i10 == 1) {
            return j$.time.temporal.s.f(1L, this.f18181a.lengthOfMonth());
        }
        if (i10 == 2) {
            return j$.time.temporal.s.f(1L, R());
        }
        if (i10 != 3) {
            return s.f18178d.u(aVar);
        }
        int year = this.f18182b.f18187b.getYear();
        return this.f18182b.k() != null ? j$.time.temporal.s.f(1L, (r0.f18187b.getYear() - year) + 1) : j$.time.temporal.s.f(1L, 999999999 - year);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        switch (t.f18179a[((j$.time.temporal.a) oVar).ordinal()]) {
            case 2:
                return this.f18183c == 1 ? (this.f18181a.Y() - this.f18182b.f18187b.Y()) + 1 : this.f18181a.Y();
            case 3:
                return this.f18183c;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
            case 8:
                return this.f18182b.f18186a;
            default:
                return this.f18181a.H(oVar);
        }
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public final u c(long j10, j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
            if (H(aVar) == j10) {
                return this;
            }
            int[] iArr = t.f18179a;
            int i10 = iArr[aVar.ordinal()];
            if (i10 == 3 || i10 == 8 || i10 == 9) {
                s sVar = s.f18178d;
                int iA = sVar.u(aVar).a(j10, aVar);
                int i11 = iArr[aVar.ordinal()];
                if (i11 == 3) {
                    return c0(this.f18181a.m0(sVar.z(this.f18182b, iA)));
                }
                if (i11 == 8) {
                    return c0(this.f18181a.m0(sVar.z(v.o(iA), this.f18183c)));
                }
                if (i11 == 9) {
                    return c0(this.f18181a.m0(iA));
                }
            }
            return c0(this.f18181a.c(j10, oVar));
        }
        return (u) super.c(j10, oVar);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    /* JADX INFO: renamed from: B */
    public final ChronoLocalDate m(j$.time.temporal.l lVar) {
        return (u) super.m(lVar);
    }

    public final u b0(j$.time.c cVar) {
        return (u) super.m(cVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        return (u) super.m(localDate);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate O(j$.time.temporal.n nVar) {
        return (u) super.O(nVar);
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate Y(long j10) {
        return c0(this.f18181a.i0(j10));
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate X(long j10) {
        return c0(this.f18181a.plusMonths(j10));
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate W(long j10) {
        return c0(this.f18181a.plusDays(j10));
    }

    public final u Z(long j10, ChronoUnit chronoUnit) {
        return (u) super.d(j10, (j$.time.temporal.q) chronoUnit);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final ChronoLocalDate d(long j10, j$.time.temporal.q qVar) {
        return (u) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal d(long j10, j$.time.temporal.q qVar) {
        return (u) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return (u) super.v(j10, chronoUnit);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate v(long j10, j$.time.temporal.q qVar) {
        return (u) super.v(j10, qVar);
    }

    public final u c0(LocalDate localDate) {
        return localDate.equals(this.f18181a) ? this : new u(localDate);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final long I() {
        return this.f18181a.I();
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof u) {
            return this.f18181a.equals(((u) obj).f18181a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new b0((byte) 4, this);
    }
}
