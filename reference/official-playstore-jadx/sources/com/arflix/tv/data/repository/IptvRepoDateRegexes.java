package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import j$.time.format.DateTimeFormatter;
import j$.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0013\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010R\u0017\u0010\u0015\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0017\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0019\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\u001b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001c\u0010\u0010R \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u0010R\u0017\u0010\"\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\"\u0010\u000e\u001a\u0004\b#\u0010\u0010R\u0017\u0010$\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b$\u0010\u000e\u001a\u0004\b%\u0010\u0010R\u0014\u0010'\u001a\u00020&8\u0002X\u0082T¢\u0006\u0006\n\u0004\b'\u0010(R#\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u001d8\u0006¢\u0006\f\n\u0004\b)\u0010\u001f\u001a\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/arflix/tv/data/repository/IptvRepoDateRegexes;", "", "<init>", "()V", "", "key", "Lkotlin/text/m;", "getDatePatternRegex", "(Ljava/lang/String;)Lkotlin/text/m;", "pattern", "j$/time/format/DateTimeFormatter", "formatterFor", "(Ljava/lang/String;)Lj$/time/format/DateTimeFormatter;", "MINUTE_PATTERN", "Lj$/time/format/DateTimeFormatter;", "getMINUTE_PATTERN", "()Lj$/time/format/DateTimeFormatter;", "SECOND_PATTERN", "getSECOND_PATTERN", "SPACE_SECOND_PATTERN", "getSPACE_SECOND_PATTERN", "SPACE_MINUTE_PATTERN", "getSPACE_MINUTE_PATTERN", "YEAR_PATTERN", "getYEAR_PATTERN", "MONTH_PATTERN", "getMONTH_PATTERN", "DAY_PATTERN", "getDAY_PATTERN", "j$/util/concurrent/ConcurrentHashMap", "datePatternRegexCache", "Lj$/util/concurrent/ConcurrentHashMap;", "HOUR_PATTERN", "getHOUR_PATTERN", "MIN_PATTERN", "getMIN_PATTERN", "SEC_PATTERN", "getSEC_PATTERN", "", "MAX_DYNAMIC_FORMATTERS", "I", "formatterCache", "getFormatterCache", "()Lj$/util/concurrent/ConcurrentHashMap;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class IptvRepoDateRegexes {
    private static final int MAX_DYNAMIC_FORMATTERS = 256;
    public static final IptvRepoDateRegexes INSTANCE = new IptvRepoDateRegexes();
    private static final DateTimeFormatter MINUTE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm");
    private static final DateTimeFormatter SECOND_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss");
    private static final DateTimeFormatter SPACE_SECOND_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter SPACE_MINUTE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter YEAR_PATTERN = DateTimeFormatter.ofPattern("yyyy");
    private static final DateTimeFormatter MONTH_PATTERN = DateTimeFormatter.ofPattern("MM");
    private static final DateTimeFormatter DAY_PATTERN = DateTimeFormatter.ofPattern("dd");
    private static final ConcurrentHashMap<String, kotlin.text.m> datePatternRegexCache = new ConcurrentHashMap<>();
    private static final DateTimeFormatter HOUR_PATTERN = DateTimeFormatter.ofPattern("HH");
    private static final DateTimeFormatter MIN_PATTERN = DateTimeFormatter.ofPattern("mm");
    private static final DateTimeFormatter SEC_PATTERN = DateTimeFormatter.ofPattern("ss");
    private static final ConcurrentHashMap<String, DateTimeFormatter> formatterCache = new ConcurrentHashMap<>();

    private IptvRepoDateRegexes() {
    }

    public final DateTimeFormatter formatterFor(String pattern) {
        DateTimeFormatter dateTimeFormatterPutIfAbsent;
        ConcurrentHashMap<String, DateTimeFormatter> concurrentHashMap = formatterCache;
        DateTimeFormatter dateTimeFormatter = concurrentHashMap.get(pattern);
        if (dateTimeFormatter != null) {
            return dateTimeFormatter;
        }
        if (concurrentHashMap.size() >= 256) {
            return DateTimeFormatter.ofPattern(pattern);
        }
        DateTimeFormatter dateTimeFormatterOfPattern = concurrentHashMap.get(pattern);
        if (dateTimeFormatterOfPattern == null && (dateTimeFormatterPutIfAbsent = concurrentHashMap.putIfAbsent(pattern, (dateTimeFormatterOfPattern = DateTimeFormatter.ofPattern(pattern)))) != null) {
            dateTimeFormatterOfPattern = dateTimeFormatterPutIfAbsent;
        }
        return dateTimeFormatterOfPattern;
    }

    public final DateTimeFormatter getDAY_PATTERN() {
        return DAY_PATTERN;
    }

    public final kotlin.text.m getDatePatternRegex(String key) {
        kotlin.text.m mVarPutIfAbsent;
        ConcurrentHashMap<String, kotlin.text.m> concurrentHashMap = datePatternRegexCache;
        kotlin.text.m mVar = concurrentHashMap.get(key);
        if (mVar == null && (mVarPutIfAbsent = concurrentHashMap.putIfAbsent(key, (mVar = new kotlin.text.m(a2.m("\\$\\{", key, ":([^}]+)\\}|\\{", key, ":([^}]+)\\}"))))) != null) {
            mVar = mVarPutIfAbsent;
        }
        return mVar;
    }

    public final ConcurrentHashMap<String, DateTimeFormatter> getFormatterCache() {
        return formatterCache;
    }

    public final DateTimeFormatter getHOUR_PATTERN() {
        return HOUR_PATTERN;
    }

    public final DateTimeFormatter getMINUTE_PATTERN() {
        return MINUTE_PATTERN;
    }

    public final DateTimeFormatter getMIN_PATTERN() {
        return MIN_PATTERN;
    }

    public final DateTimeFormatter getMONTH_PATTERN() {
        return MONTH_PATTERN;
    }

    public final DateTimeFormatter getSECOND_PATTERN() {
        return SECOND_PATTERN;
    }

    public final DateTimeFormatter getSEC_PATTERN() {
        return SEC_PATTERN;
    }

    public final DateTimeFormatter getSPACE_MINUTE_PATTERN() {
        return SPACE_MINUTE_PATTERN;
    }

    public final DateTimeFormatter getSPACE_SECOND_PATTERN() {
        return SPACE_SECOND_PATTERN;
    }

    public final DateTimeFormatter getYEAR_PATTERN() {
        return YEAR_PATTERN;
    }
}
