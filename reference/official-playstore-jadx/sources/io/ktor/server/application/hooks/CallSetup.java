package io.ktor.server.application.hooks;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002$\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ>\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/ktor/server/application/hooks/CallSetup;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Ld7/d;", "Lx6/t0;", "", "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/p;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CallSetup implements Hook<p<? super ApplicationCall, ? super d<? super t0>, ? extends Object>> {
    public static final CallSetup INSTANCE = new CallSetup();

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.CallSetup$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.CallSetup$install$1", f = "CommonHooks.kt", l = {26}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = pVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
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
                p<ApplicationCall, d<? super t0>, Object> pVar = this.$handler;
                ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                this.label = 1;
                Object objInvoke = pVar.invoke(applicationCall, this);
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

    private CallSetup() {
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> handler) {
        pipeline.intercept(ApplicationCallPipeline.INSTANCE.getSetup(), new AnonymousClass1(handler, null));
    }
}
