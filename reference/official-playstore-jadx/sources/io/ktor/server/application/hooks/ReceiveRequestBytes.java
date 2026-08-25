package io.ktor.server.application.hooks;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import k2.c;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u000028\u00124\u00122\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJO\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b26\u0010\r\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/server/application/hooks/ReceiveRequestBytes;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Lx6/y;", ContentDisposition.Parameters.Name, "call", "Lio/ktor/utils/io/ByteReadChannel;", TtmlNode.TAG_BODY, "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "Lx6/t0;", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/p;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReceiveRequestBytes implements Hook<p<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel>> {
    public static final ReceiveRequestBytes INSTANCE = new ReceiveRequestBytes();

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.ReceiveRequestBytes$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.ReceiveRequestBytes$install$1", f = "CommonHooks.kt", l = {128}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        final /* synthetic */ p<ApplicationCall, ByteReadChannel, ByteReadChannel> $handler;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel> pVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = pVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                Object obj2 = this.L$1;
                if (!(obj2 instanceof ByteReadChannel)) {
                    return t0Var;
                }
                ByteReadChannel byteReadChannel = (ByteReadChannel) this.$handler.invoke((ApplicationCall) pipelineContext.getContext(), obj2);
                this.L$0 = null;
                this.label = 1;
                Object objProceedWith = pipelineContext.proceedWith(byteReadChannel, this);
                a aVar = a.f15033i;
                if (objProceedWith == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0Var;
        }
    }

    private ReceiveRequestBytes() {
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, p<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel> handler) {
        pipeline.getReceivePipeline().intercept(ApplicationReceivePipeline.INSTANCE.getBefore(), new AnonymousClass1(handler, null));
    }
}
