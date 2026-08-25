package io.ktor.client.plugins.cache;

import io.ktor.http.HeaderValue;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/cache/CacheControl;", "", "()V", "MUST_REVALIDATE", "Lio/ktor/http/HeaderValue;", "getMUST_REVALIDATE$ktor_client_core", "()Lio/ktor/http/HeaderValue;", "NO_CACHE", "getNO_CACHE$ktor_client_core", "NO_STORE", "getNO_STORE$ktor_client_core", "ONLY_IF_CACHED", "getONLY_IF_CACHED$ktor_client_core", "PRIVATE", "getPRIVATE$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CacheControl {
    public static final CacheControl INSTANCE = new CacheControl();
    private static final HeaderValue NO_STORE = new HeaderValue(io.ktor.client.utils.CacheControl.NO_STORE, null, 2, null);
    private static final HeaderValue NO_CACHE = new HeaderValue(io.ktor.client.utils.CacheControl.NO_CACHE, null, 2, null);
    private static final HeaderValue PRIVATE = new HeaderValue(io.ktor.client.utils.CacheControl.PRIVATE, null, 2, null);
    private static final HeaderValue ONLY_IF_CACHED = new HeaderValue(io.ktor.client.utils.CacheControl.ONLY_IF_CACHED, null, 2, null);
    private static final HeaderValue MUST_REVALIDATE = new HeaderValue(io.ktor.client.utils.CacheControl.MUST_REVALIDATE, null, 2, null);

    private CacheControl() {
    }

    public final HeaderValue getMUST_REVALIDATE$ktor_client_core() {
        return MUST_REVALIDATE;
    }

    public final HeaderValue getNO_CACHE$ktor_client_core() {
        return NO_CACHE;
    }

    public final HeaderValue getNO_STORE$ktor_client_core() {
        return NO_STORE;
    }

    public final HeaderValue getONLY_IF_CACHED$ktor_client_core() {
        return ONLY_IF_CACHED;
    }

    public final HeaderValue getPRIVATE$ktor_client_core() {
        return PRIVATE;
    }
}
