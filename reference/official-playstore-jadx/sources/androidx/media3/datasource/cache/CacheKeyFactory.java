package androidx.media3.datasource.cache;

import androidx.media3.common.util.h;
import androidx.media3.datasource.DataSpec;

/* JADX INFO: loaded from: classes3.dex */
public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new h(1);

    String buildCacheKey(DataSpec dataSpec);
}
