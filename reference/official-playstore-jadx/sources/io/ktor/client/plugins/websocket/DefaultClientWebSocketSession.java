package io.ktor.client.plugins.websocket;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.util.InternalAPI;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketExtension;
import java.util.List;
import ka.p0;
import kotlin.Metadata;
import ma.w;
import ma.x;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0096Aø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0096Aø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u0012\u001a\u00020\b2\u0012\b\u0002\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fH\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\bH\u0097\u0001¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00198\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010!\u001a\u00020\u001e8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001e\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0%8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001c\u0010.\u001a\u00020)8\u0016@\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u00104\u001a\u00020/8\u0016@\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020\u000b058\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b6\u00107R\u001c\u0010;\u001a\u00020/8\u0016@\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b9\u00101\"\u0004\b:\u00103R\u001c\u0010>\u001a\u00020/8\u0016@\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b<\u00101\"\u0004\b=\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "Lio/ktor/client/plugins/websocket/ClientWebSocketSession;", "Lio/ktor/websocket/DefaultWebSocketSession;", "Lio/ktor/client/call/HttpClientCall;", "call", "delegate", "<init>", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/websocket/DefaultWebSocketSession;)V", "Lx6/t0;", "flush", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/websocket/Frame;", "frame", "send", "(Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "", "Lio/ktor/websocket/WebSocketExtension;", "negotiatedExtensions", TtmlNode.START, "(Ljava/util/List;)V", "terminate", "()V", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "Lka/p0;", "Lio/ktor/websocket/CloseReason;", "getCloseReason", "()Lka/p0;", "closeReason", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "getExtensions", "()Ljava/util/List;", "extensions", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "", "getMasking", "()Z", "setMasking", "(Z)V", "masking", "", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "maxFrameSize", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "getPingIntervalMillis", "setPingIntervalMillis", "pingIntervalMillis", "getTimeoutMillis", "setTimeoutMillis", "timeoutMillis", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultClientWebSocketSession implements ClientWebSocketSession, DefaultWebSocketSession {
    private final /* synthetic */ DefaultWebSocketSession $$delegate_0;
    private final HttpClientCall call;

    public DefaultClientWebSocketSession(HttpClientCall httpClientCall, DefaultWebSocketSession defaultWebSocketSession) {
        this.call = httpClientCall;
        this.$$delegate_0 = defaultWebSocketSession;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object flush(d<? super t0> dVar) {
        return this.$$delegate_0.flush(dVar);
    }

    @Override // io.ktor.client.plugins.websocket.ClientWebSocketSession
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public p0<CloseReason> getCloseReason() {
        return this.$$delegate_0.getCloseReason();
    }

    @Override // io.ktor.client.plugins.websocket.ClientWebSocketSession, io.ktor.websocket.WebSocketSession, ka.k0
    public j getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public List<WebSocketExtension<?>> getExtensions() {
        return this.$$delegate_0.getExtensions();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public w<Frame> getIncoming() {
        return this.$$delegate_0.getIncoming();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public boolean getMasking() {
        return this.$$delegate_0.getMasking();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public long getMaxFrameSize() {
        return this.$$delegate_0.getMaxFrameSize();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public x<Frame> getOutgoing() {
        return this.$$delegate_0.getOutgoing();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getPingIntervalMillis() {
        return this.$$delegate_0.getPingIntervalMillis();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getTimeoutMillis() {
        return this.$$delegate_0.getTimeoutMillis();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object send(Frame frame, d<? super t0> dVar) {
        return this.$$delegate_0.send(frame, dVar);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMasking(boolean z) {
        this.$$delegate_0.setMasking(z);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMaxFrameSize(long j10) {
        this.$$delegate_0.setMaxFrameSize(j10);
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setPingIntervalMillis(long j10) {
        this.$$delegate_0.setPingIntervalMillis(j10);
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setTimeoutMillis(long j10) {
        this.$$delegate_0.setTimeoutMillis(j10);
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    @InternalAPI
    public void start(List<? extends WebSocketExtension<?>> negotiatedExtensions) {
        this.$$delegate_0.start(negotiatedExtensions);
    }

    @Override // io.ktor.websocket.WebSocketSession
    @e
    public void terminate() {
        this.$$delegate_0.terminate();
    }
}
