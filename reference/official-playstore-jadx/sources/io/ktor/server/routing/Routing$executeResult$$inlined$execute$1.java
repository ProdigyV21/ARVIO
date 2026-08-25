package io.ktor.server.routing;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.util.pipeline.Pipeline;
import k2.c;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"", "TContext", "Lx6/t0;", "<anonymous>", "()V", "io/ktor/util/pipeline/PipelineKt$execute$2"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.util.pipeline.PipelineKt$execute$2", f = "Pipeline.kt", l = {478}, m = "invokeSuspend")
public final class Routing$executeResult$$inlined$execute$1 extends j implements l<d<? super t0>, Object> {
    final /* synthetic */ Object $context;
    final /* synthetic */ Pipeline $this_execute;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Routing$executeResult$$inlined$execute$1(Pipeline pipeline, Object obj, d dVar) {
        super(1, dVar);
        this.$this_execute = pipeline;
        this.$context = obj;
    }

    @Override // f7.a
    public final d<t0> create(d<?> dVar) {
        return new Routing$executeResult$$inlined$execute$1(this.$this_execute, this.$context, dVar);
    }

    @Override // r7.l
    public final Object invoke(d<? super t0> dVar) {
        return ((Routing$executeResult$$inlined$execute$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            c.G(obj);
            Pipeline pipeline = this.$this_execute;
            Object obj2 = this.$context;
            this.label = 1;
            Object objExecute = pipeline.execute(obj2, t0Var, this);
            a aVar = a.f15033i;
            if (objExecute == aVar) {
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
