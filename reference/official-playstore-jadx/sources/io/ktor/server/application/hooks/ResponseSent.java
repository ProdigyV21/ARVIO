package io.ktor.server.application.hooks;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J+\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/application/hooks/ResponseSent;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "Lx6/t0;", "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/l;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResponseSent implements Hook<l<? super ApplicationCall, ? extends t0>> {
    public static final ResponseSent INSTANCE = new ResponseSent();

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.ResponseSent$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.ResponseSent$install$1", f = "CommonHooks.kt", l = {111}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        final /* synthetic */ l<ApplicationCall, t0> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super ApplicationCall, t0> lVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = lVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            PipelineContext pipelineContext;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                pipelineContext = (PipelineContext) this.L$0;
                this.L$0 = pipelineContext;
                this.label = 1;
                Object objProceed = pipelineContext.proceed(this);
                a aVar = a.f15033i;
                if (objProceed == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                pipelineContext = (PipelineContext) this.L$0;
                c.G(obj);
            }
            this.$handler.invoke((ApplicationCall) pipelineContext.getContext());
            return t0.f22605a;
        }
    }

    private ResponseSent() {
    }

    @Override // io.ktor.server.application.Hook
    public /* bridge */ /* synthetic */ void install(ApplicationCallPipeline applicationCallPipeline, l<? super ApplicationCall, ? extends t0> lVar) {
        install2(applicationCallPipeline, (l<? super ApplicationCall, t0>) lVar);
    }

    /* JADX INFO: renamed from: install, reason: avoid collision after fix types in other method */
    public void install2(ApplicationCallPipeline pipeline, l<? super ApplicationCall, t0> handler) {
        pipeline.getSendPipeline().intercept(ApplicationSendPipeline.INSTANCE.getEngine(), new AnonymousClass1(handler, null));
    }
}
