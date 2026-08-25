package io.ktor.server.plugins.callloging;

import d7.d;
import e7.a;
import f7.e;
import f7.i;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.plugins.callloging.MDCHookKt$MDCHook$1$install$1", f = "MDCHook.kt", l = {20}, m = "invokeSuspend")
public final class MDCHookKt$MDCHook$1$install$1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
    final /* synthetic */ q<ApplicationCall, l<? super d<? super t0>, ? extends Object>, d<? super t0>, Object> $handler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.MDCHookKt$MDCHook$1$install$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends m implements l<d<? super t0>, Object>, i {
        public AnonymousClass1(Object obj) {
            super(1, 0, PipelineContext.class, obj, "proceed", "proceed(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;");
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((PipelineContext) this.receiver).proceed(dVar);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MDCHookKt$MDCHook$1$install$1(q<? super ApplicationCall, ? super l<? super d<? super t0>, ? extends Object>, ? super d<? super t0>, ? extends Object> qVar, d<? super MDCHookKt$MDCHook$1$install$1> dVar) {
        super(3, dVar);
        this.$handler = qVar;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
        MDCHookKt$MDCHook$1$install$1 mDCHookKt$MDCHook$1$install$1 = new MDCHookKt$MDCHook$1$install$1(this.$handler, dVar);
        mDCHookKt$MDCHook$1$install$1.L$0 = pipelineContext;
        return mDCHookKt$MDCHook$1$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            q<ApplicationCall, l<? super d<? super t0>, ? extends Object>, d<? super t0>, Object> qVar = this.$handler;
            ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(pipelineContext);
            this.label = 1;
            Object objInvoke = qVar.invoke(applicationCall, anonymousClass1, this);
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
