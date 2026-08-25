package androidx.compose.material3;

import j$.util.DesugarTimeZone;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.r;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 A2\u00020\u0001:\u0001AB\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u000b*\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0010\u001a\u00020\u000b*\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0010\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00162\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u000e\u0010\u001dJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u000e\u0010\u001fJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\"J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0012H\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010'\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0007H\u0016¢\u0006\u0004\b'\u0010(J\u001f\u0010*\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u0007H\u0016¢\u0006\u0004\b*\u0010(J+\u0010.\u001a\u00020,2\u0006\u0010+\u001a\u00020\u00192\u0006\u0010-\u001a\u00020,2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0016¢\u0006\u0004\b.\u0010/J!\u00100\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001e\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020,H\u0016¢\u0006\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R,\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,09088\u0016X\u0096\u0004¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0014\u0010@\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?¨\u0006B"}, d2 = {"Landroidx/compose/material3/LegacyCalendarModelImpl;", "Landroidx/compose/material3/CalendarModel;", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "<init>", "(Ljava/util/Locale;)V", "", "day", "dayInISO8601", "(I)I", "Ljava/util/Calendar;", "firstDayCalendar", "Landroidx/compose/material3/CalendarMonth;", "getMonth", "(Ljava/util/Calendar;)Landroidx/compose/material3/CalendarMonth;", "toCalendar", "(Landroidx/compose/material3/CalendarMonth;)Ljava/util/Calendar;", "Landroidx/compose/material3/CalendarDate;", "Ljava/util/TimeZone;", "timeZone", "(Landroidx/compose/material3/CalendarDate;Ljava/util/TimeZone;)Ljava/util/Calendar;", "Landroidx/compose/material3/DateInputFormat;", "getDateInputFormat", "(Ljava/util/Locale;)Landroidx/compose/material3/DateInputFormat;", "", "timeInMillis", "getCanonicalDate", "(J)Landroidx/compose/material3/CalendarDate;", "(J)Landroidx/compose/material3/CalendarMonth;", "date", "(Landroidx/compose/material3/CalendarDate;)Landroidx/compose/material3/CalendarMonth;", "year", "month", "(II)Landroidx/compose/material3/CalendarMonth;", "getDayOfWeek", "(Landroidx/compose/material3/CalendarDate;)I", "from", "addedMonthsCount", "plusMonths", "(Landroidx/compose/material3/CalendarMonth;I)Landroidx/compose/material3/CalendarMonth;", "subtractedMonthsCount", "minusMonths", "utcTimeMillis", "", "pattern", "formatWithPattern", "(JLjava/lang/String;Ljava/util/Locale;)Ljava/lang/String;", "parse", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/compose/material3/CalendarDate;", "toString", "()Ljava/lang/String;", "firstDayOfWeek", "I", "getFirstDayOfWeek", "()I", "", "Lx6/x;", "weekdayNames", "Ljava/util/List;", "getWeekdayNames", "()Ljava/util/List;", "getToday", "()Landroidx/compose/material3/CalendarDate;", "today", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LegacyCalendarModelImpl extends CalendarModel {
    private final int firstDayOfWeek;
    private final List<x> weekdayNames;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final TimeZone utcTimeZone = DesugarTimeZone.getTimeZone("UTC");

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0010J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/LegacyCalendarModelImpl$Companion;", "", "()V", "utcTimeZone", "Ljava/util/TimeZone;", "getUtcTimeZone$material3_release", "()Ljava/util/TimeZone;", "formatWithPattern", "", "utcTimeMillis", "", "pattern", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "cache", "", "getCachedSimpleDateFormat", "Ljava/text/SimpleDateFormat;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private final SimpleDateFormat getCachedSimpleDateFormat(String pattern, Locale locale, Map<String, Object> cache) {
            StringBuilder sbS = d.s(pattern);
            sbS.append(locale.toLanguageTag());
            String string = sbS.toString();
            Object obj = cache.get(string);
            Object obj2 = obj;
            if (obj == null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
                simpleDateFormat.setTimeZone(LegacyCalendarModelImpl.INSTANCE.getUtcTimeZone$material3_release());
                cache.put(string, simpleDateFormat);
                obj2 = simpleDateFormat;
            }
            return (SimpleDateFormat) obj2;
        }

        public final String formatWithPattern(long utcTimeMillis, String pattern, Locale locale, Map<String, Object> cache) {
            SimpleDateFormat cachedSimpleDateFormat = getCachedSimpleDateFormat(pattern, locale, cache);
            Calendar calendar = Calendar.getInstance(getUtcTimeZone$material3_release());
            calendar.setTimeInMillis(utcTimeMillis);
            return cachedSimpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
        }

        public final TimeZone getUtcTimeZone$material3_release() {
            return LegacyCalendarModelImpl.utcTimeZone;
        }

        private Companion() {
        }
    }

    public LegacyCalendarModelImpl(Locale locale) {
        super(locale);
        this.firstDayOfWeek = dayInISO8601(Calendar.getInstance(locale).getFirstDayOfWeek());
        z6.c cVarN = t7.a.n();
        String[] weekdays = new DateFormatSymbols(locale).getWeekdays();
        String[] shortWeekdays = new DateFormatSymbols(locale).getShortWeekdays();
        List listT = r.T(2, weekdays);
        int size = listT.size();
        for (int i10 = 0; i10 < size; i10++) {
            cVarN.add(new x((String) listT.get(i10), shortWeekdays[i10 + 2]));
        }
        cVarN.add(new x(weekdays[1], shortWeekdays[1]));
        this.weekdayNames = t7.a.e(cVarN);
    }

    private final int dayInISO8601(int day) {
        int i10 = (day + 6) % 7;
        if (i10 == 0) {
            return 7;
        }
        return i10;
    }

    private final Calendar toCalendar(CalendarMonth calendarMonth) {
        Calendar calendar = Calendar.getInstance(utcTimeZone);
        calendar.setTimeInMillis(calendarMonth.getStartUtcTimeMillis());
        return calendar;
    }

    @Override // androidx.compose.material3.CalendarModel
    public String formatWithPattern(long utcTimeMillis, String pattern, Locale locale) {
        return INSTANCE.formatWithPattern(utcTimeMillis, pattern, locale, getFormatterCache$material3_release());
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarDate getCanonicalDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance(utcTimeZone);
        calendar.setTimeInMillis(timeInMillis);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return new CalendarDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.getTimeInMillis());
    }

    @Override // androidx.compose.material3.CalendarModel
    public DateInputFormat getDateInputFormat(Locale locale) {
        return CalendarModelKt.datePatternAsInputFormat(((SimpleDateFormat) DateFormat.getDateInstance(3, locale)).toPattern());
    }

    @Override // androidx.compose.material3.CalendarModel
    public int getDayOfWeek(CalendarDate date) {
        return dayInISO8601(toCalendar(date, TimeZone.getDefault()).get(7));
    }

    @Override // androidx.compose.material3.CalendarModel
    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarMonth getMonth(long timeInMillis) {
        Calendar calendar = Calendar.getInstance(utcTimeZone);
        calendar.setTimeInMillis(timeInMillis);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return getMonth(calendar);
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarDate getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return new CalendarDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), ((long) (calendar.get(16) + calendar.get(15))) + calendar.getTimeInMillis());
    }

    @Override // androidx.compose.material3.CalendarModel
    public List<x> getWeekdayNames() {
        return this.weekdayNames;
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarMonth minusMonths(CalendarMonth from, int subtractedMonthsCount) {
        if (subtractedMonthsCount <= 0) {
            return from;
        }
        Calendar calendar = toCalendar(from);
        calendar.add(2, -subtractedMonthsCount);
        return getMonth(calendar);
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarDate parse(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        TimeZone timeZone = utcTimeZone;
        simpleDateFormat.setTimeZone(timeZone);
        simpleDateFormat.setLenient(false);
        try {
            Date date2 = simpleDateFormat.parse(date);
            if (date2 == null) {
                return null;
            }
            Calendar calendar = Calendar.getInstance(timeZone);
            calendar.setTime(date2);
            return new CalendarDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.getTimeInMillis());
        } catch (ParseException unused) {
            return null;
        }
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarMonth plusMonths(CalendarMonth from, int addedMonthsCount) {
        if (addedMonthsCount <= 0) {
            return from;
        }
        Calendar calendar = toCalendar(from);
        calendar.add(2, addedMonthsCount);
        return getMonth(calendar);
    }

    public String toString() {
        return "LegacyCalendarModel";
    }

    private final Calendar toCalendar(CalendarDate calendarDate, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.clear();
        calendar.set(1, calendarDate.getYear());
        calendar.set(2, calendarDate.getMonth() - 1);
        calendar.set(5, calendarDate.getDayOfMonth());
        return calendar;
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarMonth getMonth(CalendarDate date) {
        return getMonth(date.getYear(), date.getMonth());
    }

    @Override // androidx.compose.material3.CalendarModel
    public CalendarMonth getMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance(utcTimeZone);
        calendar.clear();
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, 1);
        return getMonth(calendar);
    }

    private final CalendarMonth getMonth(Calendar firstDayCalendar) {
        int iDayInISO8601 = dayInISO8601(firstDayCalendar.get(7)) - getFirstDayOfWeek();
        if (iDayInISO8601 < 0) {
            iDayInISO8601 += 7;
        }
        return new CalendarMonth(firstDayCalendar.get(1), firstDayCalendar.get(2) + 1, firstDayCalendar.getActualMaximum(5), iDayInISO8601, firstDayCalendar.getTimeInMillis());
    }
}
