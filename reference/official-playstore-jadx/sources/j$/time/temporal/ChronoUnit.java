package j$.time.temporal;

import androidx.media3.common.C;
import j$.time.Duration;

/* JADX INFO: loaded from: classes2.dex */
public enum ChronoUnit implements q {
    NANOS("Nanos"),
    MICROS("Micros"),
    MILLIS("Millis"),
    SECONDS("Seconds"),
    MINUTES("Minutes"),
    HOURS("Hours"),
    HALF_DAYS("HalfDays"),
    DAYS("Days"),
    WEEKS("Weeks"),
    MONTHS("Months"),
    YEARS("Years"),
    DECADES("Decades"),
    CENTURIES("Centuries"),
    MILLENNIA("Millennia"),
    ERAS("Eras"),
    FOREVER("Forever");


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18338a;

    static {
        Duration.n(1L);
        Duration.n(1000L);
        Duration.n(1000000L);
        Duration.m(1L, 0);
        Duration.m(60L, 0);
        Duration.m(3600L, 0);
        Duration.m(43200L, 0);
        Duration.m(86400L, 0);
        Duration.m(604800L, 0);
        Duration.m(2629746L, 0);
        Duration.m(31556952L, 0);
        Duration.m(315569520L, 0);
        Duration.m(3155695200L, 0);
        Duration.m(31556952000L, 0);
        Duration.m(31556952000000000L, 0);
        Duration.m(j$.com.android.tools.r8.a.L(Long.MAX_VALUE, j$.com.android.tools.r8.a.Q(999999999L, C.NANOS_PER_SECOND)), (int) j$.com.android.tools.r8.a.P(999999999L, C.NANOS_PER_SECOND));
    }

    ChronoUnit(String str) {
        this.f18338a = str;
    }

    @Override // j$.time.temporal.q
    public final Temporal l(Temporal temporal, long j10) {
        return temporal.d(j10, this);
    }

    @Override // j$.time.temporal.q
    public long between(Temporal temporal, Temporal temporal2) {
        return temporal.g(temporal2, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f18338a;
    }
}
