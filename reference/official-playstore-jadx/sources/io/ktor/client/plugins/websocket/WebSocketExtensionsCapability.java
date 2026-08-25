package io.ktor.client.plugins.websocket;

import io.ktor.client.engine.HttpClientEngineCapability;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/plugins/websocket/WebSocketExtensionsCapability;", "Lio/ktor/client/engine/HttpClientEngineCapability;", "Lx6/t0;", "<init>", "()V", "", "toString", "()Ljava/lang/String;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketExtensionsCapability implements HttpClientEngineCapability<t0> {
    public static final WebSocketExtensionsCapability INSTANCE = new WebSocketExtensionsCapability();

    private WebSocketExtensionsCapability() {
    }

    public String toString() {
        return "WebSocketExtensionsCapability";
    }
}
