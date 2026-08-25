package io.ktor.util.pipeline;

import d7.d;
import d7.j;
import java.util.List;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u009b\u0001\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00002\u0006\u0010\u0003\u001a\u00028\u00012H\u0010\n\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\t0\u00042\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"", "TSubject", "TContext", "context", "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Ld7/d;", "Lx6/t0;", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "interceptors", "subject", "Ld7/j;", "coroutineContext", "", "debugMode", "pipelineContextFor", "(Ljava/lang/Object;Ljava/util/List;Ljava/lang/Object;Ld7/j;Z)Lio/ktor/util/pipeline/PipelineContext;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PipelineContextKt {
    public static final <TSubject, TContext> PipelineContext<TSubject, TContext> pipelineContextFor(TContext tcontext, List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list, TSubject tsubject, j jVar, boolean z) {
        return (PipelineContext_jvmKt.getDISABLE_SFG() || z) ? new DebugPipelineContext(tcontext, list, tsubject, jVar) : new SuspendFunctionGun(tsubject, tcontext, list);
    }

    public static /* synthetic */ PipelineContext pipelineContextFor$default(Object obj, List list, Object obj2, j jVar, boolean z, int i10, Object obj3) {
        if ((i10 & 16) != 0) {
            z = false;
        }
        return pipelineContextFor(obj, list, obj2, jVar, z);
    }
}
