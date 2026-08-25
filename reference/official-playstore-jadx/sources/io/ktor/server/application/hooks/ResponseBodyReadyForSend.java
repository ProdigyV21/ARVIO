package io.ktor.server.application.hooks;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u000025\u00121\u0012/\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\t0\u0001:\u0001\u0011B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJO\u0010\u000f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f23\u0010\u000e\u001a/\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\tH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/hooks/ResponseBodyReadyForSend;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function4;", "Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/http/content/OutgoingContent;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/r;)V", "Context", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResponseBodyReadyForSend implements Hook<r<? super Context, ? super ApplicationCall, ? super OutgoingContent, ? super d<? super t0>, ? extends Object>> {
    public static final ResponseBodyReadyForSend INSTANCE = new ResponseBodyReadyForSend();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "context", "<init>", "(Lio/ktor/util/pipeline/PipelineContext;)V", "Lio/ktor/http/content/OutgoingContent;", TtmlNode.TAG_BODY, "Lx6/t0;", "transformBodyTo", "(Lio/ktor/http/content/OutgoingContent;)V", "Lio/ktor/util/pipeline/PipelineContext;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Context {
        private final PipelineContext<Object, ApplicationCall> context;

        public Context(PipelineContext<Object, ApplicationCall> pipelineContext) {
            this.context = pipelineContext;
        }

        public final void transformBodyTo(OutgoingContent body) {
            this.context.setSubject(body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.ResponseBodyReadyForSend$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.ResponseBodyReadyForSend$install$1", f = "CommonHooks.kt", l = {99}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        final /* synthetic */ r<Context, ApplicationCall, OutgoingContent, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(r<? super Context, ? super ApplicationCall, ? super OutgoingContent, ? super d<? super t0>, ? extends Object> rVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = rVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                r<Context, ApplicationCall, OutgoingContent, d<? super t0>, Object> rVar = this.$handler;
                Context context = new Context(pipelineContext);
                ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                OutgoingContent outgoingContent = (OutgoingContent) pipelineContext.getSubject();
                this.label = 1;
                Object objInvoke = rVar.invoke(context, applicationCall, outgoingContent, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    private ResponseBodyReadyForSend() {
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, r<? super Context, ? super ApplicationCall, ? super OutgoingContent, ? super d<? super t0>, ? extends Object> handler) {
        pipeline.getSendPipeline().intercept(ApplicationSendPipeline.INSTANCE.getAfter(), new AnonymousClass1(handler, null));
    }
}
