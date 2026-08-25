package io.ktor.client.plugins.cache.storage;

import d7.d;
import io.ktor.http.Url;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0004\bf\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011J#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ1\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tH¦@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CacheStorage;", "", "Lio/ktor/http/Url;", "url", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "data", "Lx6/t0;", "store", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Ld7/d;)Ljava/lang/Object;", "", "", "varyKeys", "find", "(Lio/ktor/http/Url;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "", "findAll", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface CacheStorage {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CacheStorage$Companion;", "", "<init>", "()V", "Lkotlin/Function0;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "Unlimited", "Lr7/a;", "getUnlimited", "()Lr7/a;", "Disabled", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "getDisabled", "()Lio/ktor/client/plugins/cache/storage/CacheStorage;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final a<CacheStorage> Unlimited = CacheStorage$Companion$Unlimited$1.INSTANCE;
        private static final CacheStorage Disabled = DisabledStorage.INSTANCE;

        private Companion() {
        }

        public final CacheStorage getDisabled() {
            return Disabled;
        }

        public final a<CacheStorage> getUnlimited() {
            return Unlimited;
        }
    }

    Object find(Url url, Map<String, String> map, d<? super CachedResponseData> dVar);

    Object findAll(Url url, d<? super Set<CachedResponseData>> dVar);

    Object store(Url url, CachedResponseData cachedResponseData, d<? super t0> dVar);
}
