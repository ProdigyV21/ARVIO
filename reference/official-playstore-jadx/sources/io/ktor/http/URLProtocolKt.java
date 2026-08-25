package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0004"}, d2 = {"isSecure", "", "Lio/ktor/http/URLProtocol;", "isWebsocket", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLProtocolKt {
    public static final boolean isSecure(URLProtocol uRLProtocol) {
        return p.a(uRLProtocol.getName(), "https") || p.a(uRLProtocol.getName(), "wss");
    }

    public static final boolean isWebsocket(URLProtocol uRLProtocol) {
        return p.a(uRLProtocol.getName(), "ws") || p.a(uRLProtocol.getName(), "wss");
    }
}
