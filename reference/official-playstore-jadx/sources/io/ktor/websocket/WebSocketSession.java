package io.ktor.websocket;

import d7.d;
import d7.j;
import e7.a;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import ma.w;
import ma.x;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H'¢\u0006\u0004\b\t\u0010\nR\u001c\u0010\u0010\u001a\u00020\u000b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0016\u001a\u00020\u00118&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010#\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030 0\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lio/ktor/websocket/WebSocketSession;", "Lka/k0;", "Lio/ktor/websocket/Frame;", "frame", "Lx6/t0;", "send", "(Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "flush", "(Ld7/d;)Ljava/lang/Object;", "terminate", "()V", "", "getMasking", "()Z", "setMasking", "(Z)V", "masking", "", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "maxFrameSize", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "extensions", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface WebSocketSession extends k0 {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object send(WebSocketSession webSocketSession, Frame frame, d<? super t0> dVar) {
            Object objSend = webSocketSession.getOutgoing().send(frame, dVar);
            return objSend == a.f15033i ? objSend : t0.f22605a;
        }
    }

    Object flush(d<? super t0> dVar);

    @Override // ka.k0
    /* synthetic */ j getCoroutineContext();

    List<WebSocketExtension<?>> getExtensions();

    w<Frame> getIncoming();

    boolean getMasking();

    long getMaxFrameSize();

    x<Frame> getOutgoing();

    Object send(Frame frame, d<? super t0> dVar);

    void setMasking(boolean z);

    void setMaxFrameSize(long j10);

    @e
    void terminate();
}
