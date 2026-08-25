package io.ktor.util.date;

import j$.util.DesugarTimeZone;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000f\u001a\u0006\u0010\u0010\u001a\u00020\u000e\u001a\u0019\u0010\u0011\u001a\u00020\u0004*\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0013\u001a\n\u0010\u0014\u001a\u00020\u0015*\u00020\u0004\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"GMT_TIMEZONE", "Ljava/util/TimeZone;", "kotlin.jvm.PlatformType", "GMTDate", "Lio/ktor/util/date/GMTDate;", "seconds", "", "minutes", "hours", "dayOfMonth", "month", "Lio/ktor/util/date/Month;", "year", "timestamp", "", "(Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "getTimeMillis", "toDate", "Ljava/util/Calendar;", "(Ljava/util/Calendar;Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "toJvmDate", "Ljava/util/Date;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateJvmKt {
    private static final TimeZone GMT_TIMEZONE = DesugarTimeZone.getTimeZone("GMT");

    public static final GMTDate GMTDate(Long l10) {
        return toDate(Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT), l10);
    }

    public static /* synthetic */ GMTDate GMTDate$default(Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            l10 = null;
        }
        return GMTDate(l10);
    }

    public static final long getTimeMillis() {
        return System.currentTimeMillis();
    }

    public static final GMTDate toDate(Calendar calendar, Long l10) {
        if (l10 != null) {
            calendar.setTimeInMillis(l10.longValue());
        }
        int i10 = calendar.get(16) + calendar.get(15);
        return new GMTDate(calendar.get(13), calendar.get(12), calendar.get(11), WeekDay.INSTANCE.from((calendar.get(7) + 5) % 7), calendar.get(5), calendar.get(6), Month.INSTANCE.from(calendar.get(2)), calendar.get(1), calendar.getTimeInMillis() + ((long) i10));
    }

    public static final Date toJvmDate(GMTDate gMTDate) {
        return new Date(gMTDate.getTimestamp());
    }

    public static final GMTDate GMTDate(int i10, int i11, int i12, int i13, Month month, int i14) {
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        calendar.set(1, i14);
        calendar.set(2, month.ordinal());
        calendar.set(5, i13);
        calendar.set(11, i12);
        calendar.set(12, i11);
        calendar.set(13, i10);
        calendar.set(14, 0);
        return toDate(calendar, null);
    }
}
