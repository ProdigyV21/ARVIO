package io.ktor.server.application;

import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u001e\u0010\t\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR/\u0010\t\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/Interception;", "", "T", "Lio/ktor/util/pipeline/PipelinePhase;", "phase", "Lkotlin/Function1;", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "Lx6/t0;", "action", "<init>", "(Lio/ktor/util/pipeline/PipelinePhase;Lr7/l;)V", "Lio/ktor/util/pipeline/PipelinePhase;", "getPhase", "()Lio/ktor/util/pipeline/PipelinePhase;", "Lr7/l;", "getAction", "()Lr7/l;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Interception<T> {
    private final l<Pipeline<T, ApplicationCall>, t0> action;
    private final PipelinePhase phase;

    /* JADX WARN: Multi-variable type inference failed */
    public Interception(PipelinePhase pipelinePhase, l<? super Pipeline<T, ApplicationCall>, t0> lVar) {
        this.phase = pipelinePhase;
        this.action = lVar;
    }

    public final l<Pipeline<T, ApplicationCall>, t0> getAction() {
        return this.action;
    }

    public final PipelinePhase getPhase() {
        return this.phase;
    }
}
