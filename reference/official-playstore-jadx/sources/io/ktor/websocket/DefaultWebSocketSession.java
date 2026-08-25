package io.ktor.websocket;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.j;
import e7.a;
import io.ktor.util.InternalAPI;
import io.ktor.websocket.WebSocketSession;
import java.util.List;
import ka.p0;
import kotlin.Metadata;
import kotlin.collections.z;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J#\u0010\u0006\u001a\u00020\u00052\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002H'¢\u0006\u0004\b\u0006\u0010\u0007R\u001c\u0010\r\u001a\u00020\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0010\u001a\u00020\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001c\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lio/ktor/websocket/DefaultWebSocketSession;", "Lio/ktor/websocket/WebSocketSession;", "", "Lio/ktor/websocket/WebSocketExtension;", "negotiatedExtensions", "Lx6/t0;", TtmlNode.START, "(Ljava/util/List;)V", "", "getPingIntervalMillis", "()J", "setPingIntervalMillis", "(J)V", "pingIntervalMillis", "getTimeoutMillis", "setTimeoutMillis", "timeoutMillis", "Lka/p0;", "Lio/ktor/websocket/CloseReason;", "getCloseReason", "()Lka/p0;", "closeReason", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DefaultWebSocketSession extends WebSocketSession {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object send(DefaultWebSocketSession defaultWebSocketSession, Frame frame, d<? super t0> dVar) {
            Object objSend = WebSocketSession.DefaultImpls.send(defaultWebSocketSession, frame, dVar);
            return objSend == a.f15033i ? objSend : t0.f22605a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void start$default(DefaultWebSocketSession defaultWebSocketSession, List list, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: start");
            }
            if ((i10 & 1) != 0) {
                list = z.f19728i;
            }
            defaultWebSocketSession.start(list);
        }
    }

    p0<CloseReason> getCloseReason();

    @Override // io.ktor.websocket.WebSocketSession, ka.k0
    /* synthetic */ j getCoroutineContext();

    long getPingIntervalMillis();

    long getTimeoutMillis();

    void setPingIntervalMillis(long j10);

    void setTimeoutMillis(long j10);

    @InternalAPI
    void start(List<? extends WebSocketExtension<?>> negotiatedExtensions);
}
