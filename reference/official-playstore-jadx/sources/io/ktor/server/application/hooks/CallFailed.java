package io.ktor.server.application.hooks;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002H\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJb\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2F\u0010\u0010\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/hooks/CallFailed;", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lx6/y;", ContentDisposition.Parameters.Name, "call", "", "cause", "Ld7/d;", "Lx6/t0;", "", "<init>", "()V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/q;)V", "Lio/ktor/util/pipeline/PipelinePhase;", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CallFailed implements Hook<q<? super ApplicationCall, ? super Throwable, ? super d<? super t0>, ? extends Object>> {
    public static final CallFailed INSTANCE = new CallFailed();
    private static final PipelinePhase phase = new PipelinePhase("BeforeSetup");

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.CallFailed$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.CallFailed$install$1", f = "CommonHooks.kt", l = {44, 48}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<ApplicationCall, Throwable, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: io.ktor.server.application.hooks.CallFailed$install$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.application.hooks.CallFailed$install$1$1", f = "CommonHooks.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS}, m = "invokeSuspend")
        public static final class C02341 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PipelineContext<t0, ApplicationCall> $$this$intercept;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02341(PipelineContext<t0, ApplicationCall> pipelineContext, d<? super C02341> dVar) {
                super(2, dVar);
                this.$$this$intercept = pipelineContext;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C02341(this.$$this$intercept, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PipelineContext<t0, ApplicationCall> pipelineContext = this.$$this$intercept;
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
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C02341) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super ApplicationCall, ? super Throwable, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
        
            if (ka.l0.c(r0, r6) == r3) goto L21;
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
            /*
                r6 = this;
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L2d
                if (r0 == r2) goto L20
                if (r0 != r1) goto L18
                java.lang.Object r0 = r6.L$1
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                java.lang.Object r1 = r6.L$0
                io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
                k2.c.G(r7)
                goto L5c
            L18:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L20:
                java.lang.Object r0 = r6.L$0
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                k2.c.G(r7)     // Catch: java.lang.Throwable -> L28
                goto L6c
            L28:
                r7 = move-exception
                r5 = r0
                r0 = r7
                r7 = r5
                goto L46
            L2d:
                k2.c.G(r7)
                java.lang.Object r7 = r6.L$0
                io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
                io.ktor.server.application.hooks.CallFailed$install$1$1 r0 = new io.ktor.server.application.hooks.CallFailed$install$1$1     // Catch: java.lang.Throwable -> L45
                r4 = 0
                r0.<init>(r7, r4)     // Catch: java.lang.Throwable -> L45
                r6.L$0 = r7     // Catch: java.lang.Throwable -> L45
                r6.label = r2     // Catch: java.lang.Throwable -> L45
                java.lang.Object r7 = ka.l0.c(r0, r6)     // Catch: java.lang.Throwable -> L45
                if (r7 != r3) goto L6c
                goto L5a
            L45:
                r0 = move-exception
            L46:
                r7.q<io.ktor.server.application.ApplicationCall, java.lang.Throwable, d7.d<? super x6.t0>, java.lang.Object> r2 = r6.$handler
                java.lang.Object r4 = r7.getContext()
                io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
                r6.L$0 = r7
                r6.L$1 = r0
                r6.label = r1
                java.lang.Object r1 = r2.invoke(r4, r0, r6)
                if (r1 != r3) goto L5b
            L5a:
                return r3
            L5b:
                r1 = r7
            L5c:
                java.lang.Object r7 = r1.getContext()
                io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
                io.ktor.server.response.ApplicationResponse r7 = r7.getResponse()
                boolean r7 = r7.getIsSent()
                if (r7 == 0) goto L6f
            L6c:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            L6f:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.hooks.CallFailed.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private CallFailed() {
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, q<? super ApplicationCall, ? super Throwable, ? super d<? super t0>, ? extends Object> handler) throws InvalidPhaseException {
        PipelinePhase setup = ApplicationCallPipeline.INSTANCE.getSetup();
        PipelinePhase pipelinePhase = phase;
        pipeline.insertPhaseBefore(setup, pipelinePhase);
        pipeline.intercept(pipelinePhase, new AnonymousClass1(handler, null));
    }
}
