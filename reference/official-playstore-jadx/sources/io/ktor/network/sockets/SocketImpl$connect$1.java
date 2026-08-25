package io.ktor.network.sockets;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.network.sockets.SocketImpl", f = "SocketImpl.kt", l = {47, 65}, m = "connect$ktor_network")
public final class SocketImpl$connect$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SocketImpl<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SocketImpl$connect$1(SocketImpl<? extends S> socketImpl, d<? super SocketImpl$connect$1> dVar) {
        super(dVar);
        this.this$0 = socketImpl;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type d7.d to io.ktor.network.sockets.SocketImpl$connect$1 for r1v1 'this'  d7.d
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // f7.a
    public final java.lang.Object invokeSuspend(java.lang.Object r2) {
        /*
            r1 = this;
            r1.result = r2
            int r2 = r1.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 | r0
            r1.label = r2
            io.ktor.network.sockets.SocketImpl<S> r2 = r1.this$0
            r0 = 0
            java.lang.Object r2 = r2.connect$ktor_network(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketImpl$connect$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
