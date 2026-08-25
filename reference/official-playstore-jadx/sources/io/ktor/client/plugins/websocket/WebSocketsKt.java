package io.ktor.client.plugins.websocket;

import io.ktor.util.AttributeKey;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.websocket.WebSocketExtension;
import java.util.List;
import kotlin.Metadata;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"$\u0010\u0003\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004\"\u001e\u0010\u0007\u001a\u00060\u0005j\u0002`\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/util/AttributeKey;", "", "Lio/ktor/websocket/WebSocketExtension;", "REQUEST_EXTENSIONS_KEY", "Lio/ktor/util/AttributeKey;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "getLOGGER", "()Lvc/b;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebSocketsKt {
    private static final AttributeKey<List<WebSocketExtension<?>>> REQUEST_EXTENSIONS_KEY = new AttributeKey<>("Websocket extensions");
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.websocket.WebSockets");

    public static final b getLOGGER() {
        return LOGGER;
    }
}
