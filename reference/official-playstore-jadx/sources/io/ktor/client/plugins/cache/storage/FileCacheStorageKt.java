package io.ktor.client.plugins.cache.storage;

import java.io.File;
import ka.f0;
import ka.x0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Ljava/io/File;", "directory", "Lka/f0;", "dispatcher", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "FileStorage", "(Ljava/io/File;Lka/f0;)Lio/ktor/client/plugins/cache/storage/CacheStorage;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FileCacheStorageKt {
    public static final CacheStorage FileStorage(File file, f0 f0Var) {
        return new CachingCacheStorage(new FileCacheStorage(file, f0Var));
    }

    public static CacheStorage FileStorage$default(File file, f0 f0Var, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f0Var = x0.f19655d;
        }
        return FileStorage(file, f0Var);
    }
}
