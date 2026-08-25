package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhaseRelation;", "", "()V", "After", "Before", "Last", "Lio/ktor/util/pipeline/PipelinePhaseRelation$After;", "Lio/ktor/util/pipeline/PipelinePhaseRelation$Before;", "Lio/ktor/util/pipeline/PipelinePhaseRelation$Last;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class PipelinePhaseRelation {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhaseRelation$After;", "Lio/ktor/util/pipeline/PipelinePhaseRelation;", "relativeTo", "Lio/ktor/util/pipeline/PipelinePhase;", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "getRelativeTo", "()Lio/ktor/util/pipeline/PipelinePhase;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class After extends PipelinePhaseRelation {
        private final PipelinePhase relativeTo;

        public After(PipelinePhase pipelinePhase) {
            super(null);
            this.relativeTo = pipelinePhase;
        }

        public final PipelinePhase getRelativeTo() {
            return this.relativeTo;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhaseRelation$Before;", "Lio/ktor/util/pipeline/PipelinePhaseRelation;", "relativeTo", "Lio/ktor/util/pipeline/PipelinePhase;", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "getRelativeTo", "()Lio/ktor/util/pipeline/PipelinePhase;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Before extends PipelinePhaseRelation {
        private final PipelinePhase relativeTo;

        public Before(PipelinePhase pipelinePhase) {
            super(null);
            this.relativeTo = pipelinePhase;
        }

        public final PipelinePhase getRelativeTo() {
            return this.relativeTo;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhaseRelation$Last;", "Lio/ktor/util/pipeline/PipelinePhaseRelation;", "()V", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Last extends PipelinePhaseRelation {
        public static final Last INSTANCE = new Last();

        private Last() {
            super(null);
        }
    }

    public /* synthetic */ PipelinePhaseRelation(h hVar) {
        this();
    }

    private PipelinePhaseRelation() {
    }
}
