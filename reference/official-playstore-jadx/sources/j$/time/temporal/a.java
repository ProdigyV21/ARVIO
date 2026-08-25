package j$.time.temporal;

import androidx.media3.common.C;
import j$.time.format.a0;
import j$.time.format.b0;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum a implements o {
    NANO_OF_SECOND("NanoOfSecond", s.f(0, 999999999)),
    NANO_OF_DAY("NanoOfDay", s.f(0, 86399999999999L)),
    MICRO_OF_SECOND("MicroOfSecond", s.f(0, 999999)),
    MICRO_OF_DAY("MicroOfDay", s.f(0, 86399999999L)),
    MILLI_OF_SECOND("MilliOfSecond", s.f(0, 999)),
    MILLI_OF_DAY("MilliOfDay", s.f(0, 86399999)),
    SECOND_OF_MINUTE("SecondOfMinute", s.f(0, 59), 0),
    SECOND_OF_DAY("SecondOfDay", s.f(0, 86399)),
    MINUTE_OF_HOUR("MinuteOfHour", s.f(0, 59), 0),
    MINUTE_OF_DAY("MinuteOfDay", s.f(0, 1439)),
    HOUR_OF_AMPM("HourOfAmPm", s.f(0, 11)),
    CLOCK_HOUR_OF_AMPM("ClockHourOfAmPm", s.f(1, 12)),
    HOUR_OF_DAY("HourOfDay", s.f(0, 23), 0),
    CLOCK_HOUR_OF_DAY("ClockHourOfDay", s.f(1, 24)),
    AMPM_OF_DAY("AmPmOfDay", s.f(0, 1), 0),
    DAY_OF_WEEK("DayOfWeek", s.f(1, 7), 0),
    ALIGNED_DAY_OF_WEEK_IN_MONTH("AlignedDayOfWeekInMonth", s.f(1, 7)),
    ALIGNED_DAY_OF_WEEK_IN_YEAR("AlignedDayOfWeekInYear", s.f(1, 7)),
    DAY_OF_MONTH("DayOfMonth", s.g(1, 28, 31), 0),
    DAY_OF_YEAR("DayOfYear", s.g(1, 365, 366)),
    EPOCH_DAY("EpochDay", s.f(-365243219162L, 365241780471L)),
    ALIGNED_WEEK_OF_MONTH("AlignedWeekOfMonth", s.g(1, 4, 5)),
    ALIGNED_WEEK_OF_YEAR("AlignedWeekOfYear", s.f(1, 53)),
    MONTH_OF_YEAR("MonthOfYear", s.f(1, 12), 0),
    PROLEPTIC_MONTH("ProlepticMonth", s.f(-11999999988L, 11999999999L)),
    YEAR_OF_ERA("YearOfEra", s.g(1, 999999999, C.NANOS_PER_SECOND)),
    YEAR("Year", s.f(-999999999, 999999999), 0),
    ERA("Era", s.f(0, 1), 0),
    INSTANT_SECONDS("InstantSeconds", s.f(Long.MIN_VALUE, Long.MAX_VALUE)),
    OFFSET_SECONDS("OffsetSeconds", s.f(-64800, 64800));


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f18349b;

    @Override // j$.time.temporal.o
    public final /* synthetic */ TemporalAccessor n(Map map, a0 a0Var, b0 b0Var) {
        return null;
    }

    static {
        ChronoUnit chronoUnit = ChronoUnit.NANOS;
    }

    a(String str, s sVar) {
        this.f18348a = str;
        this.f18349b = sVar;
    }

    a(String str, s sVar, int i10) {
        this.f18348a = str;
        this.f18349b = sVar;
    }

    public final void H(long j10) {
        this.f18349b.b(j10, this);
    }

    @Override // j$.time.temporal.o
    public final s r() {
        return this.f18349b;
    }

    @Override // j$.time.temporal.o
    public final boolean isDateBased() {
        return ordinal() >= DAY_OF_WEEK.ordinal() && ordinal() <= ERA.ordinal();
    }

    public final boolean U() {
        return ordinal() < DAY_OF_WEEK.ordinal();
    }

    @Override // j$.time.temporal.o
    public final boolean l(TemporalAccessor temporalAccessor) {
        return temporalAccessor.e(this);
    }

    @Override // j$.time.temporal.o
    public final s m(TemporalAccessor temporalAccessor) {
        return temporalAccessor.n(this);
    }

    @Override // j$.time.temporal.o
    public final long C(TemporalAccessor temporalAccessor) {
        return temporalAccessor.H(this);
    }

    @Override // j$.time.temporal.o
    public final Temporal G(Temporal temporal, long j10) {
        return temporal.c(j10, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f18348a;
    }
}
