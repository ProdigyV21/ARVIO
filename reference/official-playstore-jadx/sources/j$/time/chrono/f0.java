package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class f0 extends c {
    private static final long serialVersionUID = -8722293800195731463L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient LocalDate f18152a;

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime J(LocalTime localTime) {
        return new e(this, localTime);
    }

    public f0(LocalDate localDate) {
        Objects.requireNonNull(localDate, "isoDate");
        this.f18152a = localDate;
    }

    @Override // j$.time.chrono.ChronoLocalDate
    public final Chronology a() {
        return d0.f18143d;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final int hashCode() {
        d0.f18143d.getClass();
        return this.f18152a.hashCode() ^ 146118545;
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final j L() {
        return Z() >= 1 ? g0.BE : g0.BEFORE_BE;
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
        int i10 = e0.f18146a[aVar.ordinal()];
        if (i10 == 1 || i10 == 2 || i10 == 3) {
            return this.f18152a.n(oVar);
        }
        if (i10 != 4) {
            return d0.f18143d.u(aVar);
        }
        j$.time.temporal.s sVar = j$.time.temporal.a.YEAR.f18349b;
        return j$.time.temporal.s.f(1L, Z() <= 0 ? (-(sVar.f18373a + 543)) + 1 : 543 + sVar.f18376d);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            int i10 = e0.f18146a[((j$.time.temporal.a) oVar).ordinal()];
            if (i10 == 4) {
                int iZ = Z();
                if (iZ < 1) {
                    iZ = 1 - iZ;
                }
                return iZ;
            }
            if (i10 == 5) {
                return ((((long) Z()) * 12) + ((long) this.f18152a.getMonthValue())) - 1;
            }
            if (i10 == 6) {
                return Z();
            }
            if (i10 != 7) {
                return this.f18152a.H(oVar);
            }
            return Z() < 1 ? 0 : 1;
        }
        return oVar.C(this);
    }

    public final int Z() {
        return this.f18152a.getYear() + 543;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final j$.time.chrono.f0 c(long r8, j$.time.temporal.o r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof j$.time.temporal.a
            if (r0 == 0) goto La3
            r0 = r10
            j$.time.temporal.a r0 = (j$.time.temporal.a) r0
            long r1 = r7.H(r0)
            int r1 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r1 != 0) goto L10
            return r7
        L10:
            int[] r1 = j$.time.chrono.e0.f18146a
            int r2 = r0.ordinal()
            r2 = r1[r2]
            r3 = 7
            r4 = 6
            r5 = 4
            if (r2 == r5) goto L4d
            r6 = 5
            if (r2 == r6) goto L25
            if (r2 == r4) goto L4d
            if (r2 == r3) goto L4d
            goto L63
        L25:
            j$.time.chrono.d0 r10 = j$.time.chrono.d0.f18143d
            j$.time.temporal.s r10 = r10.u(r0)
            r10.b(r8, r0)
            int r10 = r7.Z()
            long r0 = (long) r10
            r2 = 12
            long r0 = r0 * r2
            j$.time.LocalDate r10 = r7.f18152a
            int r10 = r10.getMonthValue()
            long r2 = (long) r10
            long r0 = r0 + r2
            r2 = 1
            long r0 = r0 - r2
            long r8 = r8 - r0
            j$.time.LocalDate r10 = r7.f18152a
            j$.time.LocalDate r8 = r10.plusMonths(r8)
            j$.time.chrono.f0 r8 = r7.b0(r8)
            return r8
        L4d:
            j$.time.chrono.d0 r2 = j$.time.chrono.d0.f18143d
            j$.time.temporal.s r2 = r2.u(r0)
            int r2 = r2.a(r8, r0)
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r5) goto L8c
            if (r0 == r4) goto L7f
            if (r0 == r3) goto L6e
        L63:
            j$.time.LocalDate r0 = r7.f18152a
            j$.time.LocalDate r8 = r0.c(r8, r10)
            j$.time.chrono.f0 r8 = r7.b0(r8)
            return r8
        L6e:
            j$.time.LocalDate r8 = r7.f18152a
            int r9 = r7.Z()
            int r9 = (-542) - r9
            j$.time.LocalDate r8 = r8.m0(r9)
            j$.time.chrono.f0 r8 = r7.b0(r8)
            return r8
        L7f:
            j$.time.LocalDate r8 = r7.f18152a
            int r2 = r2 + (-543)
            j$.time.LocalDate r8 = r8.m0(r2)
            j$.time.chrono.f0 r8 = r7.b0(r8)
            return r8
        L8c:
            j$.time.LocalDate r8 = r7.f18152a
            int r9 = r7.Z()
            r10 = 1
            if (r9 < r10) goto L96
            goto L98
        L96:
            int r2 = 1 - r2
        L98:
            int r2 = r2 + (-543)
            j$.time.LocalDate r8 = r8.m0(r2)
            j$.time.chrono.f0 r8 = r7.b0(r8)
            return r8
        La3:
            j$.time.chrono.ChronoLocalDate r8 = super.c(r8, r10)
            j$.time.chrono.f0 r8 = (j$.time.chrono.f0) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.chrono.f0.c(long, j$.time.temporal.o):j$.time.chrono.f0");
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    /* JADX INFO: renamed from: B */
    public final ChronoLocalDate m(j$.time.temporal.l lVar) {
        return (f0) super.m(lVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        return (f0) super.m(localDate);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate O(j$.time.temporal.n nVar) {
        return (f0) super.O(nVar);
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate Y(long j10) {
        return b0(this.f18152a.i0(j10));
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate X(long j10) {
        return b0(this.f18152a.plusMonths(j10));
    }

    @Override // j$.time.chrono.c
    public final ChronoLocalDate W(long j10) {
        return b0(this.f18152a.plusDays(j10));
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate, j$.time.temporal.Temporal
    public final ChronoLocalDate d(long j10, j$.time.temporal.q qVar) {
        return (f0) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    public final Temporal d(long j10, j$.time.temporal.q qVar) {
        return (f0) super.d(j10, qVar);
    }

    @Override // j$.time.chrono.c, j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return (f0) super.v(j10, chronoUnit);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final ChronoLocalDate v(long j10, j$.time.temporal.q qVar) {
        return (f0) super.v(j10, qVar);
    }

    public final f0 b0(LocalDate localDate) {
        return localDate.equals(this.f18152a) ? this : new f0(localDate);
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final long I() {
        return this.f18152a.I();
    }

    @Override // j$.time.chrono.c, j$.time.chrono.ChronoLocalDate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f0) {
            return this.f18152a.equals(((f0) obj).f18152a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new b0((byte) 8, this);
    }
}
