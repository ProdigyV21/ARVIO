package io.ktor.client.engine;

import io.ktor.http.URLUtilsKt;
import java.net.Proxy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0016\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"http", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "Lio/ktor/client/engine/ProxyBuilder;", "urlString", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ProxyConfigKt {
    public static final Proxy http(ProxyBuilder proxyBuilder, String str) {
        return proxyBuilder.http(URLUtilsKt.Url(str));
    }
}
