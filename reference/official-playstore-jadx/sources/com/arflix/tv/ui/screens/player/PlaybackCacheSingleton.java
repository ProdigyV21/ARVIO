package com.arflix.tv.ui.screens.player;

import android.content.Context;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlaybackCacheSingleton;", "", "<init>", "()V", "instance", "Landroidx/media3/datasource/cache/SimpleCache;", "getInstance", "context", "Landroid/content/Context;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class PlaybackCacheSingleton {
    public static final PlaybackCacheSingleton INSTANCE = new PlaybackCacheSingleton();
    private static volatile SimpleCache instance;

    private PlaybackCacheSingleton() {
    }

    public final SimpleCache getInstance(Context context) {
        SimpleCache simpleCache;
        SimpleCache simpleCache2 = instance;
        if (simpleCache2 != null) {
            return simpleCache2;
        }
        synchronized (this) {
            simpleCache = instance;
            if (simpleCache == null) {
                File file = new File(context.getApplicationContext().getCacheDir(), "media3_playback_cache");
                file.mkdirs();
                SimpleCache simpleCache3 = new SimpleCache(file, new LeastRecentlyUsedCacheEvictor(268435456L), new StandaloneDatabaseProvider(context.getApplicationContext()));
                instance = simpleCache3;
                simpleCache = simpleCache3;
            }
        }
        return simpleCache;
    }
}
