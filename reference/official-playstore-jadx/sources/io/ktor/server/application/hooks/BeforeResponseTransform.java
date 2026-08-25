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
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.InternalAPI;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.ktor.util.reflect.TypeInfoJvmKt;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012H\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003B\u0015\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0004\b\r\u0010\u000eJb\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2F\u0010\u0011\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/hooks/BeforeResponseTransform;", "", "T", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lx6/y;", ContentDisposition.Parameters.Name, "call", TtmlNode.TAG_BODY, "Ld7/d;", "Lkotlin/reflect/d;", "clazz", "<init>", "(Lkotlin/reflect/d;)V", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "Lx6/t0;", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Lr7/q;)V", "Lkotlin/reflect/d;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeforeResponseTransform<T> implements Hook<q<? super ApplicationCall, ? super T, ? super d<? super Object>, ? extends Object>> {
    private final kotlin.reflect.d<T> clazz;

    /* JADX INFO: renamed from: io.ktor.server.application.hooks.BeforeResponseTransform$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "T", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.hooks.BeforeResponseTransform$install$1", f = "CommonHooks.kt", l = {149}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        final /* synthetic */ q<ApplicationCall, T, d<Object>, Object> $handler;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;
        final /* synthetic */ BeforeResponseTransform<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(BeforeResponseTransform<T> beforeResponseTransform, q<? super ApplicationCall, ? super T, ? super d<Object>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.this$0 = beforeResponseTransform;
            this.$handler = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            PipelineContext pipelineContext;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                pipelineContext = (PipelineContext) this.L$0;
                Object obj2 = this.L$1;
                if (TypeInfoJvmKt.instanceOf(obj2, ((BeforeResponseTransform) this.this$0).clazz)) {
                    q<ApplicationCall, T, d<Object>, Object> qVar = this.$handler;
                    ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                    this.L$0 = pipelineContext;
                    this.label = 1;
                    obj = qVar.invoke(applicationCall, obj2, this);
                    a aVar = a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                }
                return t0.f22605a;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            pipelineContext = (PipelineContext) this.L$0;
            c.G(obj);
            pipelineContext.setSubject(obj);
            return t0.f22605a;
        }
    }

    public BeforeResponseTransform(kotlin.reflect.d<T> dVar) {
        this.clazz = dVar;
    }

    @Override // io.ktor.server.application.Hook
    public void install(ApplicationCallPipeline pipeline, q<? super ApplicationCall, ? super T, ? super d<Object>, ? extends Object> handler) throws InvalidPhaseException {
        PipelinePhase pipelinePhase = new PipelinePhase("BeforeTransform");
        pipeline.getSendPipeline().insertPhaseBefore(ApplicationSendPipeline.INSTANCE.getTransform(), pipelinePhase);
        pipeline.getSendPipeline().intercept(pipelinePhase, new AnonymousClass1(this, handler, null));
    }
}
