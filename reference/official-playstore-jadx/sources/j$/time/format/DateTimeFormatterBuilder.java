package j$.time.format;

import io.ktor.util.date.GMTDateParser;
import j$.time.chrono.Chronology;
import j$.util.Objects;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class DateTimeFormatterBuilder {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final j$.time.c f18205h = new j$.time.c(5);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Map f18206i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public DateTimeFormatterBuilder f18207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateTimeFormatterBuilder f18208b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f18209c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f18210d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f18211e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public char f18212f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f18213g;

    static {
        HashMap map = new HashMap();
        f18206i = map;
        map.put('G', j$.time.temporal.a.ERA);
        map.put('y', j$.time.temporal.a.YEAR_OF_ERA);
        map.put('u', j$.time.temporal.a.YEAR);
        j$.time.temporal.g gVar = j$.time.temporal.i.f18355a;
        map.put('Q', gVar);
        map.put('q', gVar);
        Character chValueOf = Character.valueOf(GMTDateParser.MONTH);
        j$.time.temporal.a aVar = j$.time.temporal.a.MONTH_OF_YEAR;
        map.put(chValueOf, aVar);
        map.put('L', aVar);
        map.put('D', j$.time.temporal.a.DAY_OF_YEAR);
        map.put(Character.valueOf(GMTDateParser.DAY_OF_MONTH), j$.time.temporal.a.DAY_OF_MONTH);
        map.put('F', j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        j$.time.temporal.a aVar2 = j$.time.temporal.a.DAY_OF_WEEK;
        map.put('E', aVar2);
        map.put('c', aVar2);
        map.put('e', aVar2);
        map.put('a', j$.time.temporal.a.AMPM_OF_DAY);
        map.put('H', j$.time.temporal.a.HOUR_OF_DAY);
        map.put('k', j$.time.temporal.a.CLOCK_HOUR_OF_DAY);
        map.put('K', j$.time.temporal.a.HOUR_OF_AMPM);
        map.put(Character.valueOf(GMTDateParser.HOURS), j$.time.temporal.a.CLOCK_HOUR_OF_AMPM);
        map.put(Character.valueOf(GMTDateParser.MINUTES), j$.time.temporal.a.MINUTE_OF_HOUR);
        map.put(Character.valueOf(GMTDateParser.SECONDS), j$.time.temporal.a.SECOND_OF_MINUTE);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.NANO_OF_SECOND;
        map.put('S', aVar3);
        map.put('A', j$.time.temporal.a.MILLI_OF_DAY);
        map.put('n', aVar3);
        map.put('N', j$.time.temporal.a.NANO_OF_DAY);
        map.put('g', j$.time.temporal.k.f18363a);
    }

    public static String getLocalizedDateTimePattern(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat dateTimeInstance;
        Objects.requireNonNull(locale, "locale");
        Objects.requireNonNull(chronology, "chrono");
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
        }
        if (formatStyle2 == null) {
            dateTimeInstance = DateFormat.getDateInstance(formatStyle.ordinal(), locale);
        } else if (formatStyle == null) {
            dateTimeInstance = DateFormat.getTimeInstance(formatStyle2.ordinal(), locale);
        } else {
            dateTimeInstance = DateFormat.getDateTimeInstance(formatStyle.ordinal(), formatStyle2.ordinal(), locale);
        }
        if (dateTimeInstance instanceof SimpleDateFormat) {
            String pattern = ((SimpleDateFormat) dateTimeInstance).toPattern();
            if (pattern == null) {
                return null;
            }
            int i10 = 0;
            boolean z = pattern.indexOf(66) != -1;
            boolean z5 = pattern.indexOf(98) != -1;
            if (!z && !z5) {
                return pattern;
            }
            StringBuilder sb2 = new StringBuilder(pattern.length());
            char c10 = ' ';
            while (i10 < pattern.length()) {
                char cCharAt = pattern.charAt(i10);
                if (cCharAt != ' ') {
                    if (cCharAt != 'B' && cCharAt != 'b') {
                        sb2.append(cCharAt);
                    }
                } else if (i10 == 0 || (c10 != 'B' && c10 != 'b')) {
                    sb2.append(cCharAt);
                }
                i10++;
                c10 = cCharAt;
            }
            int length = sb2.length() - 1;
            if (length >= 0 && sb2.charAt(length) == ' ') {
                sb2.deleteCharAt(length);
            }
            return sb2.toString();
        }
        throw new UnsupportedOperationException("Can't determine pattern from " + dateTimeInstance);
    }

    public DateTimeFormatterBuilder() {
        this.f18207a = this;
        this.f18209c = new ArrayList();
        this.f18213g = -1;
        this.f18208b = null;
        this.f18210d = false;
    }

    public DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder) {
        this.f18207a = this;
        this.f18209c = new ArrayList();
        this.f18213g = -1;
        this.f18208b = dateTimeFormatterBuilder;
        this.f18210d = true;
    }

    public final void j(j$.time.temporal.o oVar) {
        Objects.requireNonNull(oVar, "field");
        i(new i(oVar, 1, 19, c0.NORMAL));
    }

    public final void k(j$.time.temporal.o oVar, int i10) {
        Objects.requireNonNull(oVar, "field");
        if (i10 < 1 || i10 > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i10);
        }
        i(new i(oVar, i10, i10, c0.NOT_NEGATIVE));
    }

    public final void l(j$.time.temporal.o oVar, int i10, int i11, c0 c0Var) {
        if (i10 == i11 && c0Var == c0.NOT_NEGATIVE) {
            k(oVar, i11);
            return;
        }
        Objects.requireNonNull(oVar, "field");
        Objects.requireNonNull(c0Var, "signStyle");
        if (i10 < 1 || i10 > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i10);
        }
        if (i11 < 1 || i11 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i11);
        }
        if (i11 < i10) {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i11 + " < " + i10);
        }
        i(new i(oVar, i10, i11, c0Var));
    }

    public final void i(i iVar) {
        i iVarD;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f18207a;
        int i10 = dateTimeFormatterBuilder.f18213g;
        if (i10 < 0) {
            dateTimeFormatterBuilder.f18213g = c(iVar);
            return;
        }
        i iVar2 = (i) ((ArrayList) dateTimeFormatterBuilder.f18209c).get(i10);
        int i11 = iVar.f18249b;
        int i12 = iVar.f18250c;
        if (i11 == i12 && iVar.f18251d == c0.NOT_NEGATIVE) {
            iVarD = iVar2.e(i12);
            c(iVar.d());
            this.f18207a.f18213g = i10;
        } else {
            iVarD = iVar2.d();
            this.f18207a.f18213g = c(iVar);
        }
        ((ArrayList) this.f18207a.f18209c).set(i10, iVarD);
    }

    public final void b(j$.time.temporal.a aVar, int i10, int i11, boolean z) {
        if (i10 == i11 && !z) {
            i(new f(aVar, i10, i11, z));
        } else {
            c(new f(aVar, i10, i11, z));
        }
    }

    public final void h(j$.time.temporal.o oVar, TextStyle textStyle) {
        Objects.requireNonNull(oVar, "field");
        Objects.requireNonNull(textStyle, "textStyle");
        c(new q(oVar, textStyle, z.f18306c));
    }

    public final void g(j$.time.temporal.a aVar, Map map) {
        Objects.requireNonNull(aVar, "field");
        Objects.requireNonNull(map, "textLookup");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        TextStyle textStyle = TextStyle.FULL;
        c(new q(aVar, textStyle, new a(new y(Collections.singletonMap(textStyle, linkedHashMap)))));
    }

    public final void f(String str, String str2) {
        c(new j(str, str2));
    }

    public final void e(TextStyle textStyle) {
        Objects.requireNonNull(textStyle, "style");
        if (textStyle != TextStyle.FULL && textStyle != TextStyle.SHORT) {
            throw new IllegalArgumentException("Style must be either full or short");
        }
        c(new h(textStyle, 0));
    }

    public DateTimeFormatterBuilder appendLiteral(char c10) {
        c(new c(c10));
        return this;
    }

    public final void d(String str) {
        Objects.requireNonNull(str, "literal");
        if (str.isEmpty()) {
            return;
        }
        if (str.length() == 1) {
            c(new c(str.charAt(0)));
        } else {
            c(new h(str, 1));
        }
    }

    public final void a(DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        d dVar = dateTimeFormatter.f18199a;
        if (dVar.f18236b) {
            dVar = new d(dVar.f18235a, false);
        }
        c(dVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01e5 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x04a0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public j$.time.format.DateTimeFormatterBuilder appendPattern(java.lang.String r21) {
        /*
            Method dump skipped, instruction units count: 1304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.DateTimeFormatterBuilder.appendPattern(java.lang.String):j$.time.format.DateTimeFormatterBuilder");
    }

    public DateTimeFormatterBuilder optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f18207a;
        dateTimeFormatterBuilder.f18213g = -1;
        this.f18207a = new DateTimeFormatterBuilder(dateTimeFormatterBuilder);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f18207a;
        if (dateTimeFormatterBuilder.f18208b == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        if (((ArrayList) dateTimeFormatterBuilder.f18209c).size() > 0) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.f18207a;
            d dVar = new d(dateTimeFormatterBuilder2.f18209c, dateTimeFormatterBuilder2.f18210d);
            this.f18207a = this.f18207a.f18208b;
            c(dVar);
            return this;
        }
        this.f18207a = this.f18207a.f18208b;
        return this;
    }

    public final int c(e eVar) {
        Objects.requireNonNull(eVar, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.f18207a;
        int i10 = dateTimeFormatterBuilder.f18211e;
        if (i10 > 0) {
            if (eVar != null) {
                eVar = new k(eVar, i10, dateTimeFormatterBuilder.f18212f);
            }
            dateTimeFormatterBuilder.f18211e = 0;
            dateTimeFormatterBuilder.f18212f = (char) 0;
        }
        ((ArrayList) dateTimeFormatterBuilder.f18209c).add(eVar);
        this.f18207a.f18213g = -1;
        return ((ArrayList) r5.f18209c).size() - 1;
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        return n(locale, b0.SMART, null);
    }

    public final DateTimeFormatter m(b0 b0Var, Chronology chronology) {
        return n(Locale.getDefault(), b0Var, chronology);
    }

    public final DateTimeFormatter n(Locale locale, b0 b0Var, Chronology chronology) {
        Objects.requireNonNull(locale, "locale");
        while (this.f18207a.f18208b != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new d(this.f18209c, false), locale, DecimalStyle.f18214d, b0Var, chronology, null);
    }
}
