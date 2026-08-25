package io.ktor.network.sockets;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteChannel;
import java.net.SocketTimeoutException;
import k2.c;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1$timeout$1", f = "CIOWriter.kt", l = {}, m = "invokeSuspend")
public final class CIOWriterKt$attachForWritingImpl$1$timeout$1 extends j implements l<d<? super t0>, Object> {
    final /* synthetic */ ByteChannel $channel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOWriterKt$attachForWritingImpl$1$timeout$1(ByteChannel byteChannel, d<? super CIOWriterKt$attachForWritingImpl$1$timeout$1> dVar) {
        super(1, dVar);
        this.$channel = byteChannel;
    }

    @Override // f7.a
    public final d<t0> create(d<?> dVar) {
        return new CIOWriterKt$attachForWritingImpl$1$timeout$1(this.$channel, dVar);
    }

    @Override // r7.l
    public final Object invoke(d<? super t0> dVar) {
        return ((CIOWriterKt$attachForWritingImpl$1$timeout$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        this.$channel.close(new SocketTimeoutException());
        return t0.f22605a;
    }
}
