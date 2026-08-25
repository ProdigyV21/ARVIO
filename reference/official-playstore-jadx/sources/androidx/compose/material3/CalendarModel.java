package androidx.compose.material3;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010%\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b!\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\b\u001a\u00020\u00072\f\b\u0002\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\fH&¢\u0006\u0004\b\u0010\u0010\u0013J\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H&¢\u0006\u0004\b\u0010\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\fH&¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0014H&¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0014H&¢\u0006\u0004\b\u001f\u0010\u001dJ+\u0010\"\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020 2\f\b\u0002\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\"\u0010#J+\u0010\"\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010!\u001a\u00020 2\f\b\u0002\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\"\u0010$J+\u0010'\u001a\u00020 2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020 2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H&¢\u0006\u0004\b'\u0010(J!\u0010)\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020 2\u0006\u0010&\u001a\u00020 H&¢\u0006\u0004\b)\u0010*R\u001b\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010+\u001a\u0004\b,\u0010-R&\u0010/\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010.8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0014\u00105\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0014\u00108\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R&\u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0:098&X¦\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006>"}, d2 = {"Landroidx/compose/material3/CalendarModel;", "", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "<init>", "(Ljava/util/Locale;)V", "Landroidx/compose/material3/DateInputFormat;", "getDateInputFormat", "(Ljava/util/Locale;)Landroidx/compose/material3/DateInputFormat;", "", "timeInMillis", "Landroidx/compose/material3/CalendarDate;", "getCanonicalDate", "(J)Landroidx/compose/material3/CalendarDate;", "Landroidx/compose/material3/CalendarMonth;", "getMonth", "(J)Landroidx/compose/material3/CalendarMonth;", "date", "(Landroidx/compose/material3/CalendarDate;)Landroidx/compose/material3/CalendarMonth;", "", "year", "month", "(II)Landroidx/compose/material3/CalendarMonth;", "getDayOfWeek", "(Landroidx/compose/material3/CalendarDate;)I", "from", "addedMonthsCount", "plusMonths", "(Landroidx/compose/material3/CalendarMonth;I)Landroidx/compose/material3/CalendarMonth;", "subtractedMonthsCount", "minusMonths", "", "skeleton", "formatWithSkeleton", "(Landroidx/compose/material3/CalendarMonth;Ljava/lang/String;Ljava/util/Locale;)Ljava/lang/String;", "(Landroidx/compose/material3/CalendarDate;Ljava/lang/String;Ljava/util/Locale;)Ljava/lang/String;", "utcTimeMillis", "pattern", "formatWithPattern", "(JLjava/lang/String;Ljava/util/Locale;)Ljava/lang/String;", "parse", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/compose/material3/CalendarDate;", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "", "formatterCache", "Ljava/util/Map;", "getFormatterCache$material3_release", "()Ljava/util/Map;", "getToday", "()Landroidx/compose/material3/CalendarDate;", "today", "getFirstDayOfWeek", "()I", "firstDayOfWeek", "", "Lx6/x;", "getWeekdayNames", "()Ljava/util/List;", "weekdayNames", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CalendarModel {
    public static final int $stable = 8;
    private final Map<String, Object> formatterCache = new LinkedHashMap();
    private final Locale locale;

    public CalendarModel(Locale locale) {
        this.locale = locale;
    }

    public static /* synthetic */ String formatWithSkeleton$default(CalendarModel calendarModel, CalendarMonth calendarMonth, String str, Locale locale, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: formatWithSkeleton");
        }
        if ((i10 & 4) != 0) {
            locale = calendarModel.locale;
        }
        return calendarModel.formatWithSkeleton(calendarMonth, str, locale);
    }

    public static /* synthetic */ DateInputFormat getDateInputFormat$default(CalendarModel calendarModel, Locale locale, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getDateInputFormat");
        }
        if ((i10 & 1) != 0) {
            locale = calendarModel.locale;
        }
        return calendarModel.getDateInputFormat(locale);
    }

    public abstract String formatWithPattern(long utcTimeMillis, String pattern, Locale locale);

    public final String formatWithSkeleton(CalendarMonth month, String skeleton, Locale locale) {
        return CalendarModel_androidKt.formatWithSkeleton(month.getStartUtcTimeMillis(), skeleton, locale, this.formatterCache);
    }

    public abstract CalendarDate getCanonicalDate(long timeInMillis);

    public abstract DateInputFormat getDateInputFormat(Locale locale);

    public abstract int getDayOfWeek(CalendarDate date);

    public abstract int getFirstDayOfWeek();

    public final Map<String, Object> getFormatterCache$material3_release() {
        return this.formatterCache;
    }

    public final Locale getLocale() {
        return this.locale;
    }

    public abstract CalendarMonth getMonth(int year, int month);

    public abstract CalendarMonth getMonth(long timeInMillis);

    public abstract CalendarMonth getMonth(CalendarDate date);

    public abstract CalendarDate getToday();

    public abstract List<x> getWeekdayNames();

    public abstract CalendarMonth minusMonths(CalendarMonth from, int subtractedMonthsCount);

    public abstract CalendarDate parse(String date, String pattern);

    public abstract CalendarMonth plusMonths(CalendarMonth from, int addedMonthsCount);

    public final String formatWithSkeleton(CalendarDate date, String skeleton, Locale locale) {
        return CalendarModel_androidKt.formatWithSkeleton(date.getUtcTimeMillis(), skeleton, locale, this.formatterCache);
    }

    public static /* synthetic */ String formatWithSkeleton$default(CalendarModel calendarModel, CalendarDate calendarDate, String str, Locale locale, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: formatWithSkeleton");
        }
        if ((i10 & 4) != 0) {
            locale = calendarModel.locale;
        }
        return calendarModel.formatWithSkeleton(calendarDate, str, locale);
    }
}
