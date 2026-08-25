package io.ktor.client.engine;

import io.ktor.http.Url;
import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lio/ktor/client/engine/ProxyBuilder;", "", "()V", "http", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "url", "Lio/ktor/http/Url;", "socks", "host", "", "port", "", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProxyBuilder {
    public static final ProxyBuilder INSTANCE = new ProxyBuilder();

    private ProxyBuilder() {
    }

    public final Proxy http(Url url) {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url.getHost(), url.getPort()));
    }

    public final Proxy socks(String host, int port) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
    }
}
