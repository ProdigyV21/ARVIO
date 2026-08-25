package j$.time.temporal;

import j$.time.DateTimeException;
import j$.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j$.time.c f18366a = new j$.time.c(7);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j$.time.c f18367b = new j$.time.c(8);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j$.time.c f18368c = new j$.time.c(9);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j$.time.c f18369d = new j$.time.c(10);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j$.time.c f18370e = new j$.time.c(11);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j$.time.c f18371f = new j$.time.c(12);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final j$.time.c f18372g = new j$.time.c(13);

    public static /* synthetic */ int e(int i10) {
        int i11 = i10 % 7;
        if (i11 == 0) {
            return 0;
        }
        return (((i10 ^ 7) >> 31) | 1) > 0 ? i11 : i11 + 7;
    }

    public static s d(TemporalAccessor temporalAccessor, o oVar) {
        if (!(oVar instanceof a)) {
            Objects.requireNonNull(oVar, "field");
            return oVar.m(temporalAccessor);
        }
        if (temporalAccessor.e(oVar)) {
            return ((a) oVar).f18349b;
        }
        throw new r(j$.time.b.a("Unsupported field: ", oVar));
    }

    public static int a(TemporalAccessor temporalAccessor, o oVar) {
        s sVarN = temporalAccessor.n(oVar);
        if (!sVarN.d()) {
            throw new r("Invalid field " + oVar + " for get() method, use getLong() instead");
        }
        long jH = temporalAccessor.H(oVar);
        if (sVarN.e(jH)) {
            return (int) jH;
        }
        throw new DateTimeException("Invalid value for " + oVar + " (valid values " + sVarN + "): " + jH);
    }

    public static Object c(TemporalAccessor temporalAccessor, j$.time.c cVar) {
        if (cVar == f18366a || cVar == f18367b || cVar == f18368c) {
            return null;
        }
        return cVar.h(temporalAccessor);
    }

    public static Temporal b(Temporal temporal, long j10, q qVar) {
        long j11;
        if (j10 == Long.MIN_VALUE) {
            temporal = temporal.d(Long.MAX_VALUE, qVar);
            j11 = 1;
        } else {
            j11 = -j10;
        }
        return temporal.d(j11, qVar);
    }
}
