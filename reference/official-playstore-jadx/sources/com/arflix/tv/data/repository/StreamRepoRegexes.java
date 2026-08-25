package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000!\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001\t\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/StreamRepoRegexes;", "", "<init>", "()V", "", "pattern", "Lkotlin/text/m;", "getOrPutFilterRegex", "(Ljava/lang/String;)Lkotlin/text/m;", "com/arflix/tv/data/repository/StreamRepoRegexes$filterRegexCache$1", "filterRegexCache", "Lcom/arflix/tv/data/repository/StreamRepoRegexes$filterRegexCache$1;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class StreamRepoRegexes {
    public static final StreamRepoRegexes INSTANCE = new StreamRepoRegexes();
    private static final StreamRepoRegexes$filterRegexCache$1 filterRegexCache = new StreamRepoRegexes$filterRegexCache$1();

    private StreamRepoRegexes() {
    }

    public final kotlin.text.m getOrPutFilterRegex(String pattern) {
        kotlin.text.m mVar;
        StreamRepoRegexes$filterRegexCache$1 streamRepoRegexes$filterRegexCache$1 = filterRegexCache;
        synchronized (streamRepoRegexes$filterRegexCache$1) {
            try {
                kotlin.text.m mVar2 = streamRepoRegexes$filterRegexCache$1.get((Object) pattern);
                if (mVar2 == null) {
                    mVar2 = new kotlin.text.m(pattern, 0);
                    streamRepoRegexes$filterRegexCache$1.put(pattern, mVar2);
                }
                mVar = mVar2;
            } catch (Throwable th) {
                throw th;
            }
        }
        return mVar;
    }
}
