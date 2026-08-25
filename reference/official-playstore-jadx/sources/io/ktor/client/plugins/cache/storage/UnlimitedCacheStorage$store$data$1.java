package io.ktor.client.plugins.cache.storage;

import io.ktor.client.plugins.cache.HttpCacheEntry;
import io.ktor.util.collections.ConcurrentSetKt;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class UnlimitedCacheStorage$store$data$1 extends r implements a<Set<HttpCacheEntry>> {
    public static final UnlimitedCacheStorage$store$data$1 INSTANCE = new UnlimitedCacheStorage$store$data$1();

    public UnlimitedCacheStorage$store$data$1() {
        super(0);
    }

    @Override // r7.a
    public final Set<HttpCacheEntry> invoke() {
        return ConcurrentSetKt.ConcurrentSet();
    }
}
