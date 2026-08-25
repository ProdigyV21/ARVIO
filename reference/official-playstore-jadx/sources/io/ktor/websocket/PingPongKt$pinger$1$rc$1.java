package io.ktor.websocket;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.websocket.Frame;
import ka.k0;
import kotlin.Metadata;
import ma.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.websocket.PingPongKt$pinger$1$rc$1", f = "PingPong.kt", l = {75, 79}, m = "invokeSuspend")
public final class PingPongKt$pinger$1$rc$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ ma.j<Frame.Pong> $channel;
    final /* synthetic */ x<Frame> $outgoing;
    final /* synthetic */ String $pingMessage;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PingPongKt$pinger$1$rc$1(x<? super Frame> xVar, String str, ma.j<Frame.Pong> jVar, d<? super PingPongKt$pinger$1$rc$1> dVar) {
        super(2, dVar);
        this.$outgoing = xVar;
        this.$pingMessage = str;
        this.$channel = jVar;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new PingPongKt$pinger$1$rc$1(this.$outgoing, this.$pingMessage, this.$channel, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        if (r10 == r4) goto L19;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005e -> B:20:0x0061). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            int r0 = r9.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L1d
            if (r0 == r3) goto L19
            if (r0 != r2) goto L11
            k2.c.G(r10)
            goto L61
        L11:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L19:
            k2.c.G(r10)
            goto L56
        L1d:
            k2.c.G(r10)
            vc.b r10 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.String r0 = "WebSocket Pinger: sending ping frame"
            r10.h(r0)
            ma.x<io.ktor.websocket.Frame> r10 = r9.$outgoing
            io.ktor.websocket.Frame$Ping r0 = new io.ktor.websocket.Frame$Ping
            java.lang.String r5 = r9.$pingMessage
            java.nio.charset.Charset r6 = kotlin.text.a.f19926c
            java.nio.charset.Charset r7 = kotlin.text.a.f19924a
            boolean r8 = kotlin.jvm.internal.p.a(r6, r7)
            if (r8 == 0) goto L3e
            byte[] r5 = r5.getBytes(r7)
            goto L4a
        L3e:
            java.nio.charset.CharsetEncoder r6 = r6.newEncoder()
            int r7 = r5.length()
            byte[] r5 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r6, r5, r1, r7)
        L4a:
            r0.<init>(r5)
            r9.label = r3
            java.lang.Object r10 = r10.send(r0, r9)
            if (r10 != r4) goto L56
            goto L60
        L56:
            ma.j<io.ktor.websocket.Frame$Pong> r10 = r9.$channel
            r9.label = r2
            java.lang.Object r10 = r10.receive(r9)
            if (r10 != r4) goto L61
        L60:
            return r4
        L61:
            io.ktor.websocket.Frame$Pong r10 = (io.ktor.websocket.Frame.Pong) r10
            byte[] r0 = r10.getData()
            java.nio.charset.Charset r3 = kotlin.text.a.f19926c
            int r5 = r0.length
            java.lang.String r6 = new java.lang.String
            r6.<init>(r0, r1, r5, r3)
            java.lang.String r0 = r9.$pingMessage
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L8f
            vc.b r0 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "WebSocket Pinger: received valid pong frame "
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r0.h(r10)
            x6.t0 r10 = x6.t0.f22605a
            return r10
        L8f:
            vc.b r0 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "WebSocket Pinger: received invalid pong frame "
            r3.<init>(r5)
            r3.append(r10)
            java.lang.String r10 = ", continue waiting"
            r3.append(r10)
            java.lang.String r10 = r3.toString()
            r0.h(r10)
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt$pinger$1$rc$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((PingPongKt$pinger$1$rc$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
