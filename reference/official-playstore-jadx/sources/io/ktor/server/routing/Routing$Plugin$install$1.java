package io.ktor.server.routing;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.routing.Routing$Plugin$install$1", f = "Routing.kt", l = {140}, m = "invokeSuspend")
public final class Routing$Plugin$install$1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
    final /* synthetic */ Routing $routing;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Routing$Plugin$install$1(Routing routing, d<? super Routing$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$routing = routing;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
        Routing$Plugin$install$1 routing$Plugin$install$1 = new Routing$Plugin$install$1(this.$routing, dVar);
        routing$Plugin$install$1.L$0 = pipelineContext;
        return routing$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext<t0, ApplicationCall> pipelineContext = (PipelineContext) this.L$0;
            Routing routing = this.$routing;
            this.label = 1;
            Object objInterceptor = routing.interceptor(pipelineContext, this);
            a aVar = a.f15033i;
            if (objInterceptor == aVar) {
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
