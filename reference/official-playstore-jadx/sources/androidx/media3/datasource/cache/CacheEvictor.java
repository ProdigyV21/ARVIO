package androidx.media3.datasource.cache;

import androidx.media3.datasource.cache.Cache;

/* JADX INFO: loaded from: classes3.dex */
public interface CacheEvictor extends Cache.Listener {
    void onCacheInitialized();

    void onStartFile(Cache cache, String str, long j10, long j11);

    boolean requiresCacheSpanTouches();
}
