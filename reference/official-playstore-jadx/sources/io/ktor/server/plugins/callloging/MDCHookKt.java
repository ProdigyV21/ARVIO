package io.ktor.server.plugins.callloging;

import d7.d;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aX\u0010\t\u001a@\u0012<\u0012:\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhase;", "phase", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "MDCHook", "(Lio/ktor/util/pipeline/PipelinePhase;)Lio/ktor/server/application/Hook;", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MDCHookKt {
    public static final Hook<q<ApplicationCall, l<? super d<? super t0>, ? extends Object>, d<? super t0>, Object>> MDCHook(final PipelinePhase pipelinePhase) {
        return new Hook<q<? super ApplicationCall, ? super l<? super d<? super t0>, ? extends Object>, ? super d<? super t0>, ? extends Object>>() { // from class: io.ktor.server.plugins.callloging.MDCHookKt.MDCHook.1
            @Override // io.ktor.server.application.Hook
            public void install(ApplicationCallPipeline pipeline, q<? super ApplicationCall, ? super l<? super d<? super t0>, ? extends Object>, ? super d<? super t0>, ? extends Object> handler) throws InvalidPhaseException {
                PipelinePhase pipelinePhase2 = new PipelinePhase(pipelinePhase.getName() + "MDC");
                pipeline.insertPhaseBefore(pipelinePhase, pipelinePhase2);
                pipeline.intercept(pipelinePhase2, new MDCHookKt$MDCHook$1$install$1(handler, null));
            }
        };
    }
}
