package io.ktor.websocket;

import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.RawWebSocketCommon$readerJob$1", f = "RawWebSocketCommon.kt", l = {88, 92, 95, 99}, m = "invokeSuspend")
public final class RawWebSocketCommon$readerJob$1 extends j implements p<k0, d<? super t0>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RawWebSocketCommon this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RawWebSocketCommon$readerJob$1(RawWebSocketCommon rawWebSocketCommon, d<? super RawWebSocketCommon$readerJob$1> dVar) {
        super(2, dVar);
        this.this$0 = rawWebSocketCommon;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RawWebSocketCommon$readerJob$1(this.this$0, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0104, code lost:
    
        if (r10.send(r2, r9) != r6) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0064 A[Catch: all -> 0x0034, CancellationException -> 0x0036, ProtocolViolationException -> 0x0039, FrameTooBigException -> 0x003d, ChannelIOException -> 0x009e, EOFException | ClosedReceiveChannelException -> 0x00a7, EOFException | ClosedReceiveChannelException -> 0x00a7, PHI: r10
      0x0064: PHI (r10v21 java.lang.Object) = (r10v26 java.lang.Object), (r10v0 java.lang.Object) binds: [B:31:0x0060, B:27:0x0041] A[DONT_GENERATE, DONT_INLINE], TryCatch #3 {EOFException | ClosedReceiveChannelException -> 0x00a7, blocks: (B:18:0x0030, B:30:0x0048, B:30:0x0048, B:33:0x0064, B:33:0x0064, B:35:0x0070, B:35:0x0070, B:39:0x0082, B:39:0x0082, B:38:0x007a, B:38:0x007a, B:40:0x0085, B:40:0x0085, B:27:0x0041), top: B:62:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0070 A[Catch: all -> 0x0034, CancellationException -> 0x0036, ProtocolViolationException -> 0x0039, FrameTooBigException -> 0x003d, ChannelIOException -> 0x009e, EOFException | ClosedReceiveChannelException -> 0x00a7, EOFException | ClosedReceiveChannelException -> 0x00a7, TryCatch #3 {EOFException | ClosedReceiveChannelException -> 0x00a7, blocks: (B:18:0x0030, B:30:0x0048, B:30:0x0048, B:33:0x0064, B:33:0x0064, B:35:0x0070, B:35:0x0070, B:39:0x0082, B:39:0x0082, B:38:0x007a, B:38:0x007a, B:40:0x0085, B:40:0x0085, B:27:0x0041), top: B:62:0x0009 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0091 -> B:30:0x0048). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommon$readerJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((RawWebSocketCommon$readerJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
