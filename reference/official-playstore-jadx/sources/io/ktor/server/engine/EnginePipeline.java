package io.ktor.server.engine;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lio/ktor/server/engine/EnginePipeline;", "Lio/ktor/util/pipeline/Pipeline;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "", "developmentMode", "<init>", "(Z)V", "Z", "getDevelopmentMode", "()Z", "Lio/ktor/server/request/ApplicationReceivePipeline;", "receivePipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getReceivePipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "Lio/ktor/server/response/ApplicationSendPipeline;", "sendPipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getSendPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EnginePipeline extends Pipeline<t0, ApplicationCall> {
    private final boolean developmentMode;
    private final ApplicationReceivePipeline receivePipeline;
    private final ApplicationSendPipeline sendPipeline;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PipelinePhase Before = new PipelinePhase(TtmlNode.ANNOTATION_POSITION_BEFORE);
    private static final PipelinePhase Call = new PipelinePhase("call");

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/EnginePipeline$Companion;", "", "()V", "Before", "Lio/ktor/util/pipeline/PipelinePhase;", "getBefore", "()Lio/ktor/util/pipeline/PipelinePhase;", "Call", "getCall", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final PipelinePhase getBefore() {
            return EnginePipeline.Before;
        }

        public final PipelinePhase getCall() {
            return EnginePipeline.Call;
        }

        private Companion() {
        }
    }

    public EnginePipeline() {
        this(false, 1, null);
    }

    @Override // io.ktor.util.pipeline.Pipeline
    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final ApplicationReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final ApplicationSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public /* synthetic */ EnginePipeline(boolean z, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z);
    }

    public EnginePipeline(boolean z) {
        super(Before, Call);
        this.developmentMode = z;
        this.receivePipeline = new ApplicationReceivePipeline(getDevelopmentMode());
        this.sendPipeline = new ApplicationSendPipeline(getDevelopmentMode());
    }
}
