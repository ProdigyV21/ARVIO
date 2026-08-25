package io.ktor.client.plugins.websocket;

import d7.d;
import d7.j;
import e7.a;
import io.ktor.client.call.HttpClientCall;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketSession;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/client/plugins/websocket/ClientWebSocketSession;", "Lio/ktor/websocket/WebSocketSession;", "call", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ClientWebSocketSession extends WebSocketSession {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object send(ClientWebSocketSession clientWebSocketSession, Frame frame, d<? super t0> dVar) {
            Object objSend = WebSocketSession.DefaultImpls.send(clientWebSocketSession, frame, dVar);
            return objSend == a.f15033i ? objSend : t0.f22605a;
        }
    }

    HttpClientCall getCall();

    @Override // io.ktor.websocket.WebSocketSession, ka.k0
    /* synthetic */ j getCoroutineContext();
}
