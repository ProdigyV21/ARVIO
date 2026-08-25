package io.ktor.websocket;

import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.io.IOException;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/websocket/CloseReason;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/websocket/CloseReason;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1", f = "DefaultWebSocketSession.kt", l = {MediaError.DetailedErrorCode.SEGMENT_NETWORK}, m = "invokeSuspend")
public final class DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 extends j implements p<CloseReason, d<? super t0>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DefaultWebSocketSessionImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, d<? super DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1> dVar) {
        super(2, dVar);
        this.this$0 = defaultWebSocketSessionImpl;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1 = new DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(this.this$0, dVar);
        defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1.L$0 = obj;
        return defaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1;
    }

    @Override // r7.p
    public final Object invoke(CloseReason closeReason, d<? super t0> dVar) {
        return ((DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1) create(closeReason, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            CloseReason closeReason = (CloseReason) this.L$0;
            DefaultWebSocketSessionImpl defaultWebSocketSessionImpl = this.this$0;
            IOException iOException = new IOException("Ping timeout");
            this.label = 1;
            Object objSendCloseSequence = defaultWebSocketSessionImpl.sendCloseSequence(closeReason, iOException, this);
            a aVar = a.f15033i;
            if (objSendCloseSequence == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }
}
