package io.ktor.server.cio.backend;

import io.ktor.network.sockets.Socket;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpServerKt$httpServer$acceptJob$1$2$1 extends r implements l<Throwable, t0> {
    final /* synthetic */ Socket $client;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpServerKt$httpServer$acceptJob$1$2$1(Socket socket) {
        super(1);
        this.$client = socket;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) throws IOException {
        this.$client.close();
    }
}
