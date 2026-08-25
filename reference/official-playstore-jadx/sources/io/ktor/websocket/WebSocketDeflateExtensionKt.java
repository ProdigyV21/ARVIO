package io.ktor.websocket;

import io.ktor.websocket.Frame;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"CLIENT_MAX_WINDOW_BITS", "", "CLIENT_NO_CONTEXT_TAKEOVER", "MAX_WINDOW_BITS", "", "MIN_WINDOW_BITS", "PERMESSAGE_DEFLATE", "SERVER_MAX_WINDOW_BITS", "SERVER_NO_CONTEXT_TAKEOVER", "isCompressed", "", "Lio/ktor/websocket/Frame;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebSocketDeflateExtensionKt {
    private static final String CLIENT_MAX_WINDOW_BITS = "client_max_window_bits";
    private static final String CLIENT_NO_CONTEXT_TAKEOVER = "client_no_context_takeover";
    private static final int MAX_WINDOW_BITS = 15;
    private static final int MIN_WINDOW_BITS = 8;
    private static final String PERMESSAGE_DEFLATE = "permessage-deflate";
    private static final String SERVER_MAX_WINDOW_BITS = "server_max_window_bits";
    private static final String SERVER_NO_CONTEXT_TAKEOVER = "server_no_context_takeover";

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isCompressed(Frame frame) {
        if (frame.getRsv1()) {
            return (frame instanceof Frame.Text) || (frame instanceof Frame.Binary);
        }
        return false;
    }
}
