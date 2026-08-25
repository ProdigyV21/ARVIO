package j$.time.format;

import j$.time.DateTimeException;
import j$.time.ZoneId;
import j$.time.chrono.Chronology;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DateTimeFormatter {
    public static final DateTimeFormatter ISO_INSTANT;
    public static final DateTimeFormatter ISO_LOCAL_DATE;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final DateTimeFormatter f18198g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f18199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Locale f18200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DecimalStyle f18201c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b0 f18202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Chronology f18203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ZoneId f18204f;

    public static DateTimeFormatter ofPattern(String str) {
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendPattern = new DateTimeFormatterBuilder().appendPattern(str);
        dateTimeFormatterBuilderAppendPattern.getClass();
        return dateTimeFormatterBuilderAppendPattern.toFormatter(Locale.getDefault());
    }

    public static DateTimeFormatter ofPattern(String str, Locale locale) {
        return new DateTimeFormatterBuilder().appendPattern(str).toFormatter(locale);
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        c0 c0Var = c0.EXCEEDS_PAD;
        dateTimeFormatterBuilder.l(aVar, 4, 10, c0Var);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral = dateTimeFormatterBuilder.appendLiteral('-');
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        dateTimeFormatterBuilderAppendLiteral.k(aVar2, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral2 = dateTimeFormatterBuilderAppendLiteral.appendLiteral('-');
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        dateTimeFormatterBuilderAppendLiteral2.k(aVar3, 2);
        b0 b0Var = b0.STRICT;
        j$.time.chrono.p pVar = j$.time.chrono.p.f18175d;
        DateTimeFormatter dateTimeFormatterM = dateTimeFormatterBuilderAppendLiteral2.m(b0Var, pVar);
        ISO_LOCAL_DATE = dateTimeFormatterM;
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = new DateTimeFormatterBuilder();
        p pVar2 = p.INSENSITIVE;
        dateTimeFormatterBuilder2.c(pVar2);
        dateTimeFormatterBuilder2.a(dateTimeFormatterM);
        j jVar = j.f18254e;
        dateTimeFormatterBuilder2.c(jVar);
        dateTimeFormatterBuilder2.m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder3.c(pVar2);
        dateTimeFormatterBuilder3.a(dateTimeFormatterM);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart = dateTimeFormatterBuilder3.optionalStart();
        dateTimeFormatterBuilderOptionalStart.getClass();
        dateTimeFormatterBuilderOptionalStart.c(jVar);
        dateTimeFormatterBuilderOptionalStart.m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder4 = new DateTimeFormatterBuilder();
        j$.time.temporal.a aVar4 = j$.time.temporal.a.HOUR_OF_DAY;
        dateTimeFormatterBuilder4.k(aVar4, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral3 = dateTimeFormatterBuilder4.appendLiteral(':');
        j$.time.temporal.a aVar5 = j$.time.temporal.a.MINUTE_OF_HOUR;
        dateTimeFormatterBuilderAppendLiteral3.k(aVar5, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral4 = dateTimeFormatterBuilderAppendLiteral3.optionalStart().appendLiteral(':');
        j$.time.temporal.a aVar6 = j$.time.temporal.a.SECOND_OF_MINUTE;
        dateTimeFormatterBuilderAppendLiteral4.k(aVar6, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart2 = dateTimeFormatterBuilderAppendLiteral4.optionalStart();
        dateTimeFormatterBuilderOptionalStart2.b(j$.time.temporal.a.NANO_OF_SECOND, 0, 9, true);
        DateTimeFormatter dateTimeFormatterM2 = dateTimeFormatterBuilderOptionalStart2.m(b0Var, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder5 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder5.c(pVar2);
        dateTimeFormatterBuilder5.a(dateTimeFormatterM2);
        dateTimeFormatterBuilder5.c(jVar);
        dateTimeFormatterBuilder5.m(b0Var, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder6 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder6.c(pVar2);
        dateTimeFormatterBuilder6.a(dateTimeFormatterM2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart3 = dateTimeFormatterBuilder6.optionalStart();
        dateTimeFormatterBuilderOptionalStart3.getClass();
        dateTimeFormatterBuilderOptionalStart3.c(jVar);
        dateTimeFormatterBuilderOptionalStart3.m(b0Var, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder7 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder7.c(pVar2);
        dateTimeFormatterBuilder7.a(dateTimeFormatterM);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral5 = dateTimeFormatterBuilder7.appendLiteral('T');
        dateTimeFormatterBuilderAppendLiteral5.a(dateTimeFormatterM2);
        DateTimeFormatter dateTimeFormatterM3 = dateTimeFormatterBuilderAppendLiteral5.m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder8 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder8.c(pVar2);
        dateTimeFormatterBuilder8.a(dateTimeFormatterM3);
        p pVar3 = p.LENIENT;
        dateTimeFormatterBuilder8.c(pVar3);
        dateTimeFormatterBuilder8.c(jVar);
        p pVar4 = p.STRICT;
        dateTimeFormatterBuilder8.c(pVar4);
        DateTimeFormatter dateTimeFormatterM4 = dateTimeFormatterBuilder8.m(b0Var, pVar);
        f18198g = dateTimeFormatterM4;
        DateTimeFormatterBuilder dateTimeFormatterBuilder9 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder9.a(dateTimeFormatterM4);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral6 = dateTimeFormatterBuilder9.optionalStart().appendLiteral('[');
        p pVar5 = p.SENSITIVE;
        dateTimeFormatterBuilderAppendLiteral6.c(pVar5);
        j$.time.c cVar = DateTimeFormatterBuilder.f18205h;
        dateTimeFormatterBuilderAppendLiteral6.c(new s(cVar, "ZoneRegionId()"));
        dateTimeFormatterBuilderAppendLiteral6.appendLiteral(']').m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder10 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder10.a(dateTimeFormatterM3);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart4 = dateTimeFormatterBuilder10.optionalStart();
        dateTimeFormatterBuilderOptionalStart4.getClass();
        dateTimeFormatterBuilderOptionalStart4.c(jVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral7 = dateTimeFormatterBuilderOptionalStart4.optionalStart().appendLiteral('[');
        dateTimeFormatterBuilderAppendLiteral7.c(pVar5);
        dateTimeFormatterBuilderAppendLiteral7.c(new s(cVar, "ZoneRegionId()"));
        dateTimeFormatterBuilderAppendLiteral7.appendLiteral(']').m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder11 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder11.c(pVar2);
        dateTimeFormatterBuilder11.l(aVar, 4, 10, c0Var);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral8 = dateTimeFormatterBuilder11.appendLiteral('-');
        dateTimeFormatterBuilderAppendLiteral8.k(j$.time.temporal.a.DAY_OF_YEAR, 3);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart5 = dateTimeFormatterBuilderAppendLiteral8.optionalStart();
        dateTimeFormatterBuilderOptionalStart5.getClass();
        dateTimeFormatterBuilderOptionalStart5.c(jVar);
        dateTimeFormatterBuilderOptionalStart5.m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder12 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder12.c(pVar2);
        dateTimeFormatterBuilder12.l(j$.time.temporal.i.f18357c, 4, 10, c0Var);
        dateTimeFormatterBuilder12.d("-W");
        dateTimeFormatterBuilder12.k(j$.time.temporal.i.f18356b, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral9 = dateTimeFormatterBuilder12.appendLiteral('-');
        j$.time.temporal.a aVar7 = j$.time.temporal.a.DAY_OF_WEEK;
        dateTimeFormatterBuilderAppendLiteral9.k(aVar7, 1);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart6 = dateTimeFormatterBuilderAppendLiteral9.optionalStart();
        dateTimeFormatterBuilderOptionalStart6.getClass();
        dateTimeFormatterBuilderOptionalStart6.c(jVar);
        dateTimeFormatterBuilderOptionalStart6.m(b0Var, pVar);
        DateTimeFormatterBuilder dateTimeFormatterBuilder13 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder13.c(pVar2);
        dateTimeFormatterBuilder13.c(new g());
        ISO_INSTANT = dateTimeFormatterBuilder13.m(b0Var, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder14 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder14.c(pVar2);
        dateTimeFormatterBuilder14.k(aVar, 4);
        dateTimeFormatterBuilder14.k(aVar2, 2);
        dateTimeFormatterBuilder14.k(aVar3, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart7 = dateTimeFormatterBuilder14.optionalStart();
        dateTimeFormatterBuilderOptionalStart7.c(pVar3);
        dateTimeFormatterBuilderOptionalStart7.f("+HHMMss", "Z");
        dateTimeFormatterBuilderOptionalStart7.c(pVar4);
        dateTimeFormatterBuilderOptionalStart7.m(b0Var, pVar);
        HashMap map = new HashMap();
        map.put(1L, "Mon");
        map.put(2L, "Tue");
        map.put(3L, "Wed");
        map.put(4L, "Thu");
        map.put(5L, "Fri");
        map.put(6L, "Sat");
        map.put(7L, "Sun");
        HashMap map2 = new HashMap();
        map2.put(1L, "Jan");
        map2.put(2L, "Feb");
        map2.put(3L, "Mar");
        map2.put(4L, "Apr");
        map2.put(5L, "May");
        map2.put(6L, "Jun");
        map2.put(7L, "Jul");
        map2.put(8L, "Aug");
        map2.put(9L, "Sep");
        map2.put(10L, "Oct");
        map2.put(11L, "Nov");
        map2.put(12L, "Dec");
        DateTimeFormatterBuilder dateTimeFormatterBuilder15 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder15.c(pVar2);
        dateTimeFormatterBuilder15.c(pVar3);
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalStart8 = dateTimeFormatterBuilder15.optionalStart();
        dateTimeFormatterBuilderOptionalStart8.g(aVar7, map);
        dateTimeFormatterBuilderOptionalStart8.d(", ");
        DateTimeFormatterBuilder dateTimeFormatterBuilderOptionalEnd = dateTimeFormatterBuilderOptionalStart8.optionalEnd();
        dateTimeFormatterBuilderOptionalEnd.l(aVar3, 1, 2, c0.NOT_NEGATIVE);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral10 = dateTimeFormatterBuilderOptionalEnd.appendLiteral(' ');
        dateTimeFormatterBuilderAppendLiteral10.g(aVar2, map2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral11 = dateTimeFormatterBuilderAppendLiteral10.appendLiteral(' ');
        dateTimeFormatterBuilderAppendLiteral11.k(aVar, 4);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral12 = dateTimeFormatterBuilderAppendLiteral11.appendLiteral(' ');
        dateTimeFormatterBuilderAppendLiteral12.k(aVar4, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral13 = dateTimeFormatterBuilderAppendLiteral12.appendLiteral(':');
        dateTimeFormatterBuilderAppendLiteral13.k(aVar5, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral14 = dateTimeFormatterBuilderAppendLiteral13.optionalStart().appendLiteral(':');
        dateTimeFormatterBuilderAppendLiteral14.k(aVar6, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral15 = dateTimeFormatterBuilderAppendLiteral14.optionalEnd().appendLiteral(' ');
        dateTimeFormatterBuilderAppendLiteral15.f("+HHMM", "GMT");
        dateTimeFormatterBuilderAppendLiteral15.m(b0.SMART, pVar);
    }

    public DateTimeFormatter(d dVar, Locale locale, DecimalStyle decimalStyle, b0 b0Var, Chronology chronology, ZoneId zoneId) {
        this.f18199a = (d) Objects.requireNonNull(dVar, "printerParser");
        this.f18200b = (Locale) Objects.requireNonNull(locale, "locale");
        this.f18201c = (DecimalStyle) Objects.requireNonNull(decimalStyle, "decimalStyle");
        this.f18202d = (b0) Objects.requireNonNull(b0Var, "resolverStyle");
        this.f18203e = chronology;
        this.f18204f = zoneId;
    }

    public DateTimeFormatter withLocale(Locale locale) {
        if (this.f18200b.equals(locale)) {
            return this;
        }
        return new DateTimeFormatter(this.f18199a, locale, this.f18201c, this.f18202d, this.f18203e, this.f18204f);
    }

    public DateTimeFormatter withDecimalStyle(DecimalStyle decimalStyle) {
        if (this.f18201c.equals(decimalStyle)) {
            return this;
        }
        return new DateTimeFormatter(this.f18199a, this.f18200b, decimalStyle, this.f18202d, this.f18203e, this.f18204f);
    }

    public DateTimeFormatter withZone(ZoneId zoneId) {
        if (Objects.equals(this.f18204f, zoneId)) {
            return this;
        }
        return new DateTimeFormatter(this.f18199a, this.f18200b, this.f18201c, this.f18202d, this.f18203e, zoneId);
    }

    public String format(TemporalAccessor temporalAccessor) {
        StringBuilder sb2 = new StringBuilder(32);
        d dVar = this.f18199a;
        Objects.requireNonNull(temporalAccessor, "temporal");
        Objects.requireNonNull(sb2, "appendable");
        try {
            dVar.l(new w(temporalAccessor, this), sb2);
            return sb2.toString();
        } catch (IOException e5) {
            throw new DateTimeException(e5.getMessage(), e5);
        }
    }

    public final Object a(CharSequence charSequence, j$.time.c cVar) {
        String string;
        Objects.requireNonNull(charSequence, "text");
        Objects.requireNonNull(cVar, "query");
        try {
            return b(charSequence).G(cVar);
        } catch (DateTimeParseException e5) {
            throw e5;
        } catch (RuntimeException e6) {
            if (charSequence.length() > 64) {
                string = charSequence.subSequence(0, 64).toString() + "...";
            } else {
                string = charSequence.toString();
            }
            DateTimeParseException dateTimeParseException = new DateTimeParseException("Text '" + string + "' could not be parsed: " + e6.getMessage(), e6);
            charSequence.toString();
            throw dateTimeParseException;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0275  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final j$.time.format.a0 b(java.lang.CharSequence r27) {
        /*
            Method dump skipped, instruction units count: 1108
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.DateTimeFormatter.b(java.lang.CharSequence):j$.time.format.a0");
    }

    public final String toString() {
        String string = this.f18199a.toString();
        return string.startsWith("[") ? string : string.substring(1, string.length() - 1);
    }
}
