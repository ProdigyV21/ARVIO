package io.ktor.server.plugins.callloging;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002$\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ>\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/ktor/server/plugins/callloging/ResponseSent;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function2;", "Lio/ktor/server/application/ApplicationCall;", "Ld7/d;", "Lx6/t0;", "", "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/p;)V", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResponseSent implements Hook<p<? super ApplicationCall, ? super d<? super t0>, ? extends Object>> {
    public static final ResponseSent INSTANCE = new ResponseSent();

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.ResponseSent$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.ResponseSent$install$1", f = "MDCHook.kt", l = {28, 29}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
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
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        
            if (r5.invoke(r0, r4) == r3) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r5)
                goto L47
            L10:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L18:
                java.lang.Object r0 = r4.L$0
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                k2.c.G(r5)
                goto L33
            L20:
                k2.c.G(r5)
                java.lang.Object r5 = r4.L$0
                r0 = r5
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                r4.L$0 = r0
                r4.label = r2
                java.lang.Object r5 = r0.proceed(r4)
                if (r5 != r3) goto L33
                goto L46
            L33:
                r7.p<io.ktor.server.application.ApplicationCall, d7.d<? super x6.t0>, java.lang.Object> r5 = r4.$handler
                java.lang.Object r0 = r0.getContext()
                io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
                r2 = 0
                r4.L$0 = r2
                r4.label = r1
                java.lang.Object r5 = r5.invoke(r0, r4)
                if (r5 != r3) goto L47
            L46:
                return r3
            L47:
                x6.t0 r5 = x6.t0.f22605a
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.callloging.ResponseSent.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private ResponseSent() {
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> handler) {
        pipeline.getSendPipeline().intercept(ApplicationSendPipeline.INSTANCE.getEngine(), new AnonymousClass1(handler, null));
    }
}
