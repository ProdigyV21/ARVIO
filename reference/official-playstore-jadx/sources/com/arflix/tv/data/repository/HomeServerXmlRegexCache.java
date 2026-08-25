package com.arflix.tv.data.repository;

import io.ktor.http.ContentDisposition;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerXmlRegexCache;", "", "<init>", "()V", "", ContentDisposition.Parameters.Name, "Lkotlin/text/m;", "getRegex", "(Ljava/lang/String;)Lkotlin/text/m;", "j$/util/concurrent/ConcurrentHashMap", "regexCache", "Lj$/util/concurrent/ConcurrentHashMap;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class HomeServerXmlRegexCache {
    public static final HomeServerXmlRegexCache INSTANCE = new HomeServerXmlRegexCache();
    private static final ConcurrentHashMap<String, kotlin.text.m> regexCache = new ConcurrentHashMap<>();

    private HomeServerXmlRegexCache() {
    }

    public final kotlin.text.m getRegex(String name) {
        kotlin.text.m mVarPutIfAbsent;
        ConcurrentHashMap<String, kotlin.text.m> concurrentHashMap = regexCache;
        kotlin.text.m mVar = concurrentHashMap.get(name);
        if (mVar == null && (mVarPutIfAbsent = concurrentHashMap.putIfAbsent(name, (mVar = new kotlin.text.m(a0.c.l("\\b", Pattern.quote(name), "=[\"']([^\"']*)[\"']"))))) != null) {
            mVar = mVarPutIfAbsent;
        }
        return mVar;
    }
}
