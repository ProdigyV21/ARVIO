package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class n extends c {
    private static final long serialVersionUID = -5207853542612002020L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient l f18170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient int f18171b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient int f18172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient int f18173d;

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime J(LocalTime localTime) {
        return new e(this, localTime);
    }

    public n(l lVar, int i10, int i11, int i12) {
        lVar.Y(i10, i11, i12);
        this.f18170a = lVar;
        this.f18171b = i10;
        this.f18172c = i11;
        this.f18173d = i12;
    }

    public n(l lVar, long j10) {
        int i10 = (int) j10;
        lVar.V();
        if (i10 < lVar.f18163f || i10 >= lVar.f18164g) {
            throw new DateTimeException("Hijrah date out of range");
        }
        int iBinarySearch = Arrays.binarySearch(lVar.f18162e, i10);
        iBinarySearch = iBinarySearch < 0 ? (-iBinarySearch) - 2 : iBinarySearch;
        int[] iArr = {lVar.X(iBinarySearch), ((lVar.f18165h + iBinarySearch) % 12) + 1, (i10 - lVar.f18162e[iBinarySearch]) + 1};
        this.f18170a = lVar;
        this.f18171b = iArr[0];
        this.f18172c = iArr[1];
        this.f18173d = iArr[2];
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology a() {
        return this.f18170a;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final j L() {
        return o.AH;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final int R() {
        return this.f18170a.b0(this.f18171b, 12);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.m(this);
        }
        if (!j$.com.android.tools.r8.a.q(this, oVar)) {
            throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        int i10 = m.f18169a[aVar.ordinal()];
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? this.f18170a.u(aVar) : j$.time.temporal.s.f(1L, 5L) : j$.time.temporal.s.f(1L, R()) : j$.time.temporal.s.f(1L, this.f18170a.Z(this.f18171b, this.f18172c));
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        switch (m.f18169a[((j$.time.temporal.a) oVar).ordinal()]) {
            case 1:
                return this.f18173d;
            case 2:
                return Z();
            case 3:
                return ((this.f18173d - 1) / 7) + 1;
            case 4:
                return ((int) j$.com.android.tools.r8.a.P(I() + 3, 7)) + 1;
            case 5:
                return ((this.f18173d - 1) % 7) + 1;
            case 6:
                return ((Z() - 1) % 7) + 1;
            case 7:
                return I();
            case 8:
                return ((Z() - 1) / 7) + 1;
            case 9:
                return this.f18172c;
            case 10:
                return ((((long) this.f18171b) * 12) + ((long) this.f18172c)) - 1;
            case 11:
                return this.f18171b;
            case 12:
                return this.f18171b;
            case 13:
                return this.f18171b <= 1 ? 0 : 1;
            default:
                throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public final n c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (n) super.c(j10, oVar);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        this.f18170a.u(aVar).b(j10, aVar);
        int i10 = (int) j10;
        switch (m.f18169a[aVar.ordinal()]) {
            case 1:
                return c0(this.f18171b, this.f18172c, i10);
            case 2:
                return W(Math.min(i10, R()) - Z());
            case 3:
                return W((j10 - H(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH)) * 7);
            case 4:
                return W(j10 - ((long) (((int) j$.com.android.tools.r8.a.P(I() + 3, 7)) + 1)));
            case 5:
                return W(j10 - H(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 6:
                return W(j10 - H(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 7:
                return new n(this.f18170a, j10);
            case 8:
                return W((j10 - H(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR)) * 7);
            case 9:
                return c0(this.f18171b, i10, this.f18173d);
            case 10:
                return X(j10 - (((((long) this.f18171b) * 12) + ((long) this.f18172c)) - 1));
            case 11:
                if (this.f18171b < 1) {
                    i10 = 1 - i10;
                }
                return c0(i10, this.f18172c, this.f18173d);
            case 12:
                return c0(i10, this.f18172c, this.f18173d);
            case 13:
                return c0(1 - this.f18171b, this.f18172c, this.f18173d);
            default:
                throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
    }

    public final n c0(int i10, int i11, int i12) {
        int iZ = this.f18170a.Z(i10, i11);
        if (i12 > iZ) {
            i12 = iZ;
        }
        return new n(this.f18170a, i10, i11, i12);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    /* JADX INFO: renamed from: B */
    public final ChronoLocalDate m(j$.time.temporal.l lVar) {
        return (n) super.m(lVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        return (n) super.m(localDate);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate O(j$.time.temporal.n nVar) {
        return (n) super.O(nVar);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final long I() {
        return this.f18170a.Y(this.f18171b, this.f18172c, this.f18173d);
    }

    public final int Z() {
        return this.f18170a.b0(this.f18171b, this.f18172c - 1) + this.f18173d;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final boolean t() {
        return this.f18170a.T(this.f18171b);
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate Y(long j10) {
        if (j10 == 0) {
            return this;
        }
        long j11 = ((long) this.f18171b) + ((long) ((int) j10));
        int i10 = (int) j11;
        if (j11 == i10) {
            return c0(i10, this.f18172c, this.f18173d);
        }
        throw new ArithmeticException();
    }

    @Override // j$.time.chrono.c
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
    public final n X(long j10) {
        if (j10 == 0) {
            return this;
        }
        long j11 = (((long) this.f18171b) * 12) + ((long) (this.f18172c - 1)) + j10;
        l lVar = this.f18170a;
        long jQ = j$.com.android.tools.r8.a.Q(j11, 12L);
        if (jQ >= lVar.X(0) && jQ <= lVar.X(lVar.f18162e.length - 1) - 1) {
            return c0((int) jQ, ((int) j$.com.android.tools.r8.a.P(j11, 12L)) + 1, this.f18173d);
        }
        throw new DateTimeException("Invalid Hijrah year: " + jQ);
    }

    @Override // j$.time.chrono.c
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public final n W(long j10) {
        return new n(this.f18170a, I() + j10);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final ChronoLocalDate d(long j10, j$.time.temporal.q qVar) {
        return (n) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal d(long j10, j$.time.temporal.q qVar) {
        return (n) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return (n) super.v(j10, chronoUnit);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate v(long j10, j$.time.temporal.q qVar) {
        return (n) super.v(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (this.f18171b == nVar.f18171b && this.f18172c == nVar.f18172c && this.f18173d == nVar.f18173d && this.f18170a.equals(nVar.f18170a)) {
                return true;
            }
        }
        return false;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        int i10 = this.f18171b;
        int i11 = this.f18172c;
        int i12 = this.f18173d;
        this.f18170a.getClass();
        return (((i10 << 11) + (i11 << 6)) + i12) ^ ((i10 & (-2048)) ^ 2100100019);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new b0((byte) 6, this);
    }
}
