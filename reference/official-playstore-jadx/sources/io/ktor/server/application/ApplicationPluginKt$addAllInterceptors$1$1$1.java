package io.ktor.server.application;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.routing.RoutingApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [TContext, TSubject] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\n\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000\"\u0004\b\u0002\u0010\u0003\"\u0004\b\u0003\u0010\u0004\"\u0014\b\u0004\u0010\u0006*\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005*\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00072\u0006\u0010\b\u001a\u00028\u0002H\u008a@"}, d2 = {"", "B", "F", "TSubject", "TContext", "Lio/ktor/util/pipeline/Pipeline;", "P", "Lio/ktor/util/pipeline/PipelineContext;", "subject", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.application.ApplicationPluginKt$addAllInterceptors$1$1$1", f = "ApplicationPlugin.kt", l = {169}, m = "invokeSuspend")
public final class ApplicationPluginKt$addAllInterceptors$1$1$1<TContext, TSubject> extends j implements q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object> {
    final /* synthetic */ q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object> $interceptor;
    final /* synthetic */ BaseRouteScopedPlugin<B, F> $plugin;
    final /* synthetic */ F $pluginInstance;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ApplicationPluginKt$addAllInterceptors$1$1$1(BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, F f10, q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object> qVar, d<? super ApplicationPluginKt$addAllInterceptors$1$1$1> dVar) {
        super(3, dVar);
        this.$plugin = baseRouteScopedPlugin;
        this.$pluginInstance = f10;
        this.$interceptor = qVar;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<TSubject, TContext> pipelineContext, TSubject tsubject, d<? super t0> dVar) {
        ApplicationPluginKt$addAllInterceptors$1$1$1 applicationPluginKt$addAllInterceptors$1$1$1 = new ApplicationPluginKt$addAllInterceptors$1$1$1(this.$plugin, this.$pluginInstance, this.$interceptor, dVar);
        applicationPluginKt$addAllInterceptors$1$1$1.L$0 = pipelineContext;
        applicationPluginKt$addAllInterceptors$1$1$1.L$1 = tsubject;
        return applicationPluginKt$addAllInterceptors$1$1$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            Object context = pipelineContext.getContext();
            if ((context instanceof RoutingApplicationCall) && p.a(RouteScopedPluginKt.findPluginInRoute(((RoutingApplicationCall) context).getRoute(), this.$plugin), this.$pluginInstance)) {
                q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object> qVar = this.$interceptor;
                this.L$0 = null;
                this.label = 1;
                Object objInvoke = qVar.invoke(pipelineContext, obj2, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
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
