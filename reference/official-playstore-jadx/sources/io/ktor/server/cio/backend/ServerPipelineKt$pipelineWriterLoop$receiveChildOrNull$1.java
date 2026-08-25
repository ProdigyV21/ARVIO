package io.ktor.server.cio.backend;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import ma.o;
import ma.w;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "<anonymous>", "(Lka/k0;)Lio/ktor/utils/io/ByteReadChannel;"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1", f = "ServerPipeline.kt", l = {201}, m = "invokeSuspend")
public final class ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1 extends j implements p<k0, d<? super ByteReadChannel>, Object> {
    final /* synthetic */ w<ByteReadChannel> $channel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1(w<? extends ByteReadChannel> wVar, d<? super ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1> dVar) {
        super(2, dVar);
        this.$channel = wVar;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1(this.$channel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objE;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            w<ByteReadChannel> wVar = this.$channel;
            this.label = 1;
            objE = wVar.e(this);
            a aVar = a.f15033i;
            if (objE == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            objE = ((o) obj).f20347a;
        }
        return o.a(objE);
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super ByteReadChannel> dVar) {
        return ((ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
