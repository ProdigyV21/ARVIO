package io.ktor.client.plugins.cache.storage;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/client/plugins/cache/storage/UnlimitedStorage;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CacheStorage$Companion$Unlimited$1 extends r implements a<UnlimitedStorage> {
    public static final CacheStorage$Companion$Unlimited$1 INSTANCE = new CacheStorage$Companion$Unlimited$1();

    public CacheStorage$Companion$Unlimited$1() {
        super(0);
    }

    @Override // r7.a
    public final UnlimitedStorage invoke() {
        return new UnlimitedStorage();
    }
}
