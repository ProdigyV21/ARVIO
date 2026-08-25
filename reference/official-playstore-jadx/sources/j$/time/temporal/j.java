package j$.time.temporal;

import j$.time.DateTimeException;
import j$.time.chrono.Chronology;
import j$.time.format.a0;
import j$.time.format.b0;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum j implements o {
    JULIAN_DAY("JulianDay", 2440588),
    MODIFIED_JULIAN_DAY("ModifiedJulianDay", 40587),
    RATA_DIE("RataDie", 719163);

    private static final long serialVersionUID = -7501623920830201812L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient String f18360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient s f18361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient long f18362c;

    @Override // j$.time.temporal.o
    public final boolean isDateBased() {
        return true;
    }

    static {
        ChronoUnit chronoUnit = ChronoUnit.NANOS;
    }

    j(String str, long j10) {
        this.f18360a = str;
        this.f18361b = s.f((-365243219162L) + j10, 365241780471L + j10);
        this.f18362c = j10;
    }

    @Override // j$.time.temporal.o
    public final Temporal G(Temporal temporal, long j10) {
        if (!this.f18361b.e(j10)) {
            throw new DateTimeException("Invalid value: " + this.f18360a + " " + j10);
        }
        return temporal.c(j$.com.android.tools.r8.a.S(j10, this.f18362c), a.EPOCH_DAY);
    }

    @Override // j$.time.temporal.o
    public final s r() {
        return this.f18361b;
    }

    @Override // j$.time.temporal.o
    public final boolean l(TemporalAccessor temporalAccessor) {
        return temporalAccessor.e(a.EPOCH_DAY);
    }

    @Override // j$.time.temporal.o
    public final s m(TemporalAccessor temporalAccessor) {
        if (temporalAccessor.e(a.EPOCH_DAY)) {
            return this.f18361b;
        }
        throw new DateTimeException("Unsupported field: " + this);
    }

    @Override // j$.time.temporal.o
    public final long C(TemporalAccessor temporalAccessor) {
        return temporalAccessor.H(a.EPOCH_DAY) + this.f18362c;
    }

    @Override // j$.time.temporal.o
    public final TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
        long jLongValue = ((Long) map.remove(this)).longValue();
        Chronology chronologyA = Chronology.CC.a(a0Var);
        b0 b0Var2 = b0.LENIENT;
        long j10 = this.f18362c;
        if (b0Var == b0Var2) {
            return chronologyA.j(j$.com.android.tools.r8.a.S(jLongValue, j10));
        }
        this.f18361b.b(jLongValue, this);
        return chronologyA.j(jLongValue - j10);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f18360a;
    }
}
