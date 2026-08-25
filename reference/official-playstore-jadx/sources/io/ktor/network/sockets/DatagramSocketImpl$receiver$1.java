package io.ktor.network.sockets;

import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import ma.u;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lma/u;", "Lio/ktor/network/sockets/Datagram;", "Lx6/t0;", "<anonymous>", "(Lma/u;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.network.sockets.DatagramSocketImpl$receiver$1", f = "DatagramSocketImpl.kt", l = {51, 51}, m = "invokeSuspend")
public final class DatagramSocketImpl$receiver$1 extends j implements p<u<? super Datagram>, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DatagramSocketImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatagramSocketImpl$receiver$1(DatagramSocketImpl datagramSocketImpl, d<? super DatagramSocketImpl$receiver$1> dVar) {
        super(2, dVar);
        this.this$0 = datagramSocketImpl;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        DatagramSocketImpl$receiver$1 datagramSocketImpl$receiver$1 = new DatagramSocketImpl$receiver$1(this.this$0, dVar);
        datagramSocketImpl$receiver$1.L$0 = obj;
        return datagramSocketImpl$receiver$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0054 -> B:15:0x0030). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L29
            if (r0 == r2) goto L1d
            if (r0 != r1) goto L15
            java.lang.Object r0 = r7.L$0
            ma.u r0 = (ma.u) r0
            k2.c.G(r8)     // Catch: java.nio.channels.ClosedChannelException -> L56
            r8 = r0
            goto L30
        L15:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1d:
            java.lang.Object r0 = r7.L$1
            ma.x r0 = (ma.x) r0
            java.lang.Object r4 = r7.L$0
            ma.u r4 = (ma.u) r4
            k2.c.G(r8)     // Catch: java.nio.channels.ClosedChannelException -> L56
            goto L46
        L29:
            k2.c.G(r8)
            java.lang.Object r8 = r7.L$0
            ma.u r8 = (ma.u) r8
        L30:
            ma.t r0 = r8.getChannel()     // Catch: java.nio.channels.ClosedChannelException -> L56
            io.ktor.network.sockets.DatagramSocketImpl r4 = r7.this$0     // Catch: java.nio.channels.ClosedChannelException -> L56
            r7.L$0 = r8     // Catch: java.nio.channels.ClosedChannelException -> L56
            r7.L$1 = r0     // Catch: java.nio.channels.ClosedChannelException -> L56
            r7.label = r2     // Catch: java.nio.channels.ClosedChannelException -> L56
            java.lang.Object r4 = io.ktor.network.sockets.DatagramSocketImpl.access$receiveImpl(r4, r7)     // Catch: java.nio.channels.ClosedChannelException -> L56
            if (r4 != r3) goto L43
            goto L53
        L43:
            r6 = r4
            r4 = r8
            r8 = r6
        L46:
            r7.L$0 = r4     // Catch: java.nio.channels.ClosedChannelException -> L56
            r5 = 0
            r7.L$1 = r5     // Catch: java.nio.channels.ClosedChannelException -> L56
            r7.label = r1     // Catch: java.nio.channels.ClosedChannelException -> L56
            java.lang.Object r8 = r0.send(r8, r7)     // Catch: java.nio.channels.ClosedChannelException -> L56
            if (r8 != r3) goto L54
        L53:
            return r3
        L54:
            r8 = r4
            goto L30
        L56:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl$receiver$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(u<? super Datagram> uVar, d<? super t0> dVar) {
        return ((DatagramSocketImpl$receiver$1) create(uVar, dVar)).invokeSuspend(t0.f22605a);
    }
}
