package io.ktor.websocket;

import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.websocket.CloseReason;
import ka.j0;
import kotlin.Metadata;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007\"\u001e\u0010\n\u001a\u00060\bj\u0002`\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010\"\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/websocket/WebSocketSession;", SettingsSessionManager.SETTINGS_KEY, "", "pingInterval", "timeoutMillis", "Lio/ktor/websocket/DefaultWebSocketSession;", "DefaultWebSocketSession", "(Lio/ktor/websocket/WebSocketSession;JJ)Lio/ktor/websocket/DefaultWebSocketSession;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "getLOGGER", "()Lvc/b;", "Lka/j0;", "IncomingProcessorCoroutineName", "Lka/j0;", "OutgoingProcessorCoroutineName", "Lio/ktor/websocket/CloseReason;", "NORMAL_CLOSE", "Lio/ktor/websocket/CloseReason;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultWebSocketSessionKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.websocket.WebSocket");
    private static final j0 IncomingProcessorCoroutineName = new j0("ws-incoming-processor");
    private static final j0 OutgoingProcessorCoroutineName = new j0("ws-outgoing-processor");
    private static final CloseReason NORMAL_CLOSE = new CloseReason(CloseReason.Codes.NORMAL, "OK");

    public static final DefaultWebSocketSession DefaultWebSocketSession(WebSocketSession webSocketSession, long j10, long j11) {
        if (webSocketSession instanceof DefaultWebSocketSession) {
            throw new IllegalArgumentException("Cannot wrap other DefaultWebSocketSession");
        }
        return new DefaultWebSocketSessionImpl(webSocketSession, j10, j11);
    }

    public static /* synthetic */ DefaultWebSocketSession DefaultWebSocketSession$default(WebSocketSession webSocketSession, long j10, long j11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = -1;
        }
        if ((i10 & 4) != 0) {
            j11 = 15000;
        }
        return DefaultWebSocketSession(webSocketSession, j10, j11);
    }

    public static final b getLOGGER() {
        return LOGGER;
    }
}
