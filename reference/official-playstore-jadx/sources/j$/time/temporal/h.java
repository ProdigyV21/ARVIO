package j$.time.temporal;

import j$.time.Duration;

/* JADX INFO: loaded from: classes2.dex */
public enum h implements q {
    WEEK_BASED_YEARS("WeekBasedYears"),
    QUARTER_YEARS("QuarterYears");


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18354a;

    static {
        Duration.m(31556952L, 0);
        Duration.m(7889238L, 0);
    }

    h(String str) {
        this.f18354a = str;
    }

    @Override // j$.time.temporal.q
    public final Temporal l(Temporal temporal, long j10) {
        int i10 = b.f18350a[ordinal()];
        if (i10 == 1) {
            return temporal.c(j$.com.android.tools.r8.a.L(temporal.l(r0), j10), i.f18357c);
        }
        if (i10 == 2) {
            return temporal.d(j10 / 4, ChronoUnit.YEARS).d((j10 % 4) * 3, ChronoUnit.MONTHS);
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // j$.time.temporal.q
    public final long between(Temporal temporal, Temporal temporal2) {
        if (temporal.getClass() != temporal2.getClass()) {
            return temporal.g(temporal2, this);
        }
        int i10 = b.f18350a[ordinal()];
        if (i10 == 1) {
            g gVar = i.f18357c;
            return j$.com.android.tools.r8.a.S(temporal2.H(gVar), temporal.H(gVar));
        }
        if (i10 == 2) {
            return temporal.g(temporal2, ChronoUnit.MONTHS) / 3;
        }
        throw new IllegalStateException("Unreachable");
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f18354a;
    }
}
