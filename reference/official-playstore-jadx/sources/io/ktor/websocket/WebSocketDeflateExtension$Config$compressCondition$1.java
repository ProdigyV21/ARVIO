package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/websocket/Frame;", "invoke", "(Lio/ktor/websocket/Frame;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class WebSocketDeflateExtension$Config$compressCondition$1 extends r implements l<Frame, Boolean> {
    public static final WebSocketDeflateExtension$Config$compressCondition$1 INSTANCE = new WebSocketDeflateExtension$Config$compressCondition$1();

    public WebSocketDeflateExtension$Config$compressCondition$1() {
        super(1);
    }

    @Override // r7.l
    public final Boolean invoke(Frame frame) {
        return Boolean.TRUE;
    }
}
