package j$.time.format;

import j$.time.chrono.Chronology;
import j$.util.concurrent.ConcurrentHashMap;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f18304a = new ConcurrentHashMap(16, 0.75f, 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final x f18305b = new x();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final z f18306c = new z();

    public String d(j$.time.temporal.o oVar, long j10, TextStyle textStyle, Locale locale) {
        Object objA = a(oVar, locale);
        if (objA instanceof y) {
            return ((y) objA).a(j10, textStyle);
        }
        return null;
    }

    public String c(Chronology chronology, j$.time.temporal.o oVar, long j10, TextStyle textStyle, Locale locale) {
        if (chronology == j$.time.chrono.p.f18175d || !(oVar instanceof j$.time.temporal.a)) {
            return d(oVar, j10, textStyle, locale);
        }
        return null;
    }

    public Iterator f(j$.time.temporal.o oVar, TextStyle textStyle, Locale locale) {
        List list;
        Object objA = a(oVar, locale);
        if (!(objA instanceof y) || (list = (List) ((HashMap) ((y) objA).f18303b).get(textStyle)) == null) {
            return null;
        }
        return list.iterator();
    }

    public Iterator e(Chronology chronology, j$.time.temporal.o oVar, TextStyle textStyle, Locale locale) {
        if (chronology == j$.time.chrono.p.f18175d || !(oVar instanceof j$.time.temporal.a)) {
            return f(oVar, textStyle, locale);
        }
        return null;
    }

    public static String b(String str) {
        return str.substring(0, Character.charCount(str.codePointAt(0)));
    }

    public static Object a(j$.time.temporal.o oVar, Locale locale) {
        AbstractMap.SimpleImmutableEntry simpleImmutableEntry;
        ConcurrentHashMap concurrentHashMap;
        Object yVar;
        ConcurrentHashMap concurrentHashMap2;
        AbstractMap.SimpleImmutableEntry simpleImmutableEntry2 = new AbstractMap.SimpleImmutableEntry(oVar, locale);
        ConcurrentHashMap concurrentHashMap3 = f18304a;
        V v2 = concurrentHashMap3.get(simpleImmutableEntry2);
        if (v2 != 0) {
            return v2;
        }
        HashMap map = new HashMap();
        int i10 = 0;
        if (oVar == j$.time.temporal.a.ERA) {
            DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            String[] eras = dateFormatSymbols.getEras();
            while (i10 < eras.length) {
                if (!eras[i10].isEmpty()) {
                    long j10 = i10;
                    map2.put(Long.valueOf(j10), eras[i10]);
                    map3.put(Long.valueOf(j10), b(eras[i10]));
                }
                i10++;
            }
            if (!map2.isEmpty()) {
                map.put(TextStyle.FULL, map2);
                map.put(TextStyle.SHORT, map2);
                map.put(TextStyle.NARROW, map3);
            }
            yVar = new y(map);
            simpleImmutableEntry = simpleImmutableEntry2;
            concurrentHashMap2 = concurrentHashMap3;
        } else {
            int i11 = 1;
            if (oVar == j$.time.temporal.a.MONTH_OF_YEAR) {
                DateFormatSymbols dateFormatSymbols2 = DateFormatSymbols.getInstance(locale);
                int length = dateFormatSymbols2.getMonths().length;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                while (i11 < length) {
                    TimeZone timeZone = TimeZone.getTimeZone("UTC");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLLL", locale);
                    simpleDateFormat.setTimeZone(timeZone);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(0, i11, 0);
                    String str = simpleDateFormat.format(calendar.getTime());
                    AbstractMap.SimpleImmutableEntry simpleImmutableEntry3 = simpleImmutableEntry2;
                    long j11 = i11;
                    linkedHashMap.put(Long.valueOf(j11), str);
                    DateFormatSymbols dateFormatSymbols3 = dateFormatSymbols2;
                    linkedHashMap2.put(Long.valueOf(j11), str.substring(0, Character.charCount(str.codePointAt(0))));
                    TimeZone timeZone2 = TimeZone.getTimeZone("UTC");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("LLL", locale);
                    simpleDateFormat2.setTimeZone(timeZone2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.set(0, i11, 0);
                    linkedHashMap3.put(Long.valueOf(j11), simpleDateFormat2.format(calendar2.getTime()));
                    i11++;
                    dateFormatSymbols2 = dateFormatSymbols3;
                    simpleImmutableEntry2 = simpleImmutableEntry3;
                    concurrentHashMap3 = concurrentHashMap3;
                }
                DateFormatSymbols dateFormatSymbols4 = dateFormatSymbols2;
                simpleImmutableEntry = simpleImmutableEntry2;
                concurrentHashMap = concurrentHashMap3;
                if (length > 0) {
                    long j12 = length;
                    linkedHashMap.put(Long.valueOf(j12), "");
                    linkedHashMap2.put(Long.valueOf(j12), "");
                    linkedHashMap3.put(Long.valueOf(j12), "");
                    map.put(TextStyle.FULL_STANDALONE, linkedHashMap);
                    map.put(TextStyle.NARROW_STANDALONE, linkedHashMap2);
                    map.put(TextStyle.SHORT_STANDALONE, linkedHashMap3);
                }
                HashMap map4 = new HashMap();
                HashMap map5 = new HashMap();
                String[] months = dateFormatSymbols4.getMonths();
                for (int i12 = 0; i12 < months.length; i12++) {
                    if (!months[i12].isEmpty()) {
                        long j13 = ((long) i12) + 1;
                        map4.put(Long.valueOf(j13), months[i12]);
                        map5.put(Long.valueOf(j13), b(months[i12]));
                    }
                }
                if (!map4.isEmpty()) {
                    map.put(TextStyle.FULL, map4);
                    map.put(TextStyle.NARROW, map5);
                }
                HashMap map6 = new HashMap();
                String[] shortMonths = dateFormatSymbols4.getShortMonths();
                while (i10 < shortMonths.length) {
                    if (!shortMonths[i10].isEmpty()) {
                        map6.put(Long.valueOf(((long) i10) + 1), shortMonths[i10]);
                    }
                    i10++;
                }
                if (!map6.isEmpty()) {
                    map.put(TextStyle.SHORT, map6);
                }
                yVar = new y(map);
            } else {
                simpleImmutableEntry = simpleImmutableEntry2;
                concurrentHashMap = concurrentHashMap3;
                if (oVar == j$.time.temporal.a.DAY_OF_WEEK) {
                    DateFormatSymbols dateFormatSymbols5 = DateFormatSymbols.getInstance(locale);
                    HashMap map7 = new HashMap();
                    String[] weekdays = dateFormatSymbols5.getWeekdays();
                    map7.put(1L, weekdays[2]);
                    map7.put(2L, weekdays[3]);
                    map7.put(3L, weekdays[4]);
                    map7.put(4L, weekdays[5]);
                    map7.put(5L, weekdays[6]);
                    map7.put(6L, weekdays[7]);
                    map7.put(7L, weekdays[1]);
                    map.put(TextStyle.FULL, map7);
                    HashMap map8 = new HashMap();
                    map8.put(1L, b(weekdays[2]));
                    map8.put(2L, b(weekdays[3]));
                    map8.put(3L, b(weekdays[4]));
                    map8.put(4L, b(weekdays[5]));
                    map8.put(5L, b(weekdays[6]));
                    map8.put(6L, b(weekdays[7]));
                    map8.put(7L, b(weekdays[1]));
                    map.put(TextStyle.NARROW, map8);
                    HashMap map9 = new HashMap();
                    String[] shortWeekdays = dateFormatSymbols5.getShortWeekdays();
                    map9.put(1L, shortWeekdays[2]);
                    map9.put(2L, shortWeekdays[3]);
                    map9.put(3L, shortWeekdays[4]);
                    map9.put(4L, shortWeekdays[5]);
                    map9.put(5L, shortWeekdays[6]);
                    map9.put(6L, shortWeekdays[7]);
                    map9.put(7L, shortWeekdays[1]);
                    map.put(TextStyle.SHORT, map9);
                    yVar = new y(map);
                } else if (oVar == j$.time.temporal.a.AMPM_OF_DAY) {
                    DateFormatSymbols dateFormatSymbols6 = DateFormatSymbols.getInstance(locale);
                    HashMap map10 = new HashMap();
                    HashMap map11 = new HashMap();
                    String[] amPmStrings = dateFormatSymbols6.getAmPmStrings();
                    while (i10 < amPmStrings.length) {
                        if (!amPmStrings[i10].isEmpty()) {
                            long j14 = i10;
                            map10.put(Long.valueOf(j14), amPmStrings[i10]);
                            map11.put(Long.valueOf(j14), b(amPmStrings[i10]));
                        }
                        i10++;
                    }
                    if (!map10.isEmpty()) {
                        map.put(TextStyle.FULL, map10);
                        map.put(TextStyle.SHORT, map10);
                        map.put(TextStyle.NARROW, map11);
                    }
                    yVar = new y(map);
                } else {
                    yVar = "";
                }
            }
            concurrentHashMap2 = concurrentHashMap;
        }
        concurrentHashMap2.putIfAbsent(simpleImmutableEntry, yVar);
        return concurrentHashMap2.get(simpleImmutableEntry);
    }
}
