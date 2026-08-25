package io.ktor.server.routing;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.util.pipeline.PipelineContext;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.routing.Route$buildPipeline$1$1", f = "Route.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend")
public final class Route$buildPipeline$1$1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
    final /* synthetic */ List<q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object>> $handlers;
    final /* synthetic */ int $index;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Route$buildPipeline$1$1(List<q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object>> list, int i10, d<? super Route$buildPipeline$1$1> dVar) {
        super(3, dVar);
        this.$handlers = list;
        this.$index = i10;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
        Route$buildPipeline$1$1 route$buildPipeline$1$1 = new Route$buildPipeline$1$1(this.$handlers, this.$index, dVar);
        route$buildPipeline$1$1.L$0 = pipelineContext;
        return route$buildPipeline$1$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (ApplicationCallKt.isHandled((ApplicationCall) pipelineContext.getContext())) {
                return t0Var;
            }
            q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> qVar = this.$handlers.get(this.$index);
            this.label = 1;
            Object objInvoke = qVar.invoke(pipelineContext, t0Var, this);
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
        return t0Var;
    }
}
