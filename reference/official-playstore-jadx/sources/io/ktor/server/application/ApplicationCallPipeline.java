package io.ktor.server.application;

import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u001d\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/server/application/ApplicationCallPipeline;", "Lio/ktor/util/pipeline/Pipeline;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "", "developmentMode", "Lio/ktor/server/application/ApplicationEnvironment;", "environment", "<init>", "(ZLio/ktor/server/application/ApplicationEnvironment;)V", "Z", "getDevelopmentMode", "()Z", "Lio/ktor/server/application/ApplicationEnvironment;", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "Lio/ktor/server/request/ApplicationReceivePipeline;", "receivePipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getReceivePipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "Lio/ktor/server/response/ApplicationSendPipeline;", "sendPipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getSendPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "ApplicationPhase", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class ApplicationCallPipeline extends Pipeline<t0, ApplicationCall> {
    private static final PipelinePhase Call;
    private static final PipelinePhase Fallback;
    private static final PipelinePhase Features;
    private static final PipelinePhase Plugins;
    private final boolean developmentMode;
    private final ApplicationEnvironment environment;
    private final ApplicationReceivePipeline receivePipeline;
    private final ApplicationSendPipeline sendPipeline;

    /* JADX INFO: renamed from: ApplicationPhase, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PipelinePhase Setup = new PipelinePhase("Setup");
    private static final PipelinePhase Monitoring = new PipelinePhase("Monitoring");

    /* JADX INFO: renamed from: io.ktor.server.application.ApplicationCallPipeline$ApplicationPhase, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0006R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0006R\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/ApplicationCallPipeline$ApplicationPhase;", "", "()V", "Call", "Lio/ktor/util/pipeline/PipelinePhase;", "getCall", "()Lio/ktor/util/pipeline/PipelinePhase;", "Fallback", "getFallback", "Features", "getFeatures$annotations", "getFeatures", "Monitoring", "getMonitoring", "Plugins", "getPlugins", "Setup", "getSetup", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @e
        public static /* synthetic */ void getFeatures$annotations() {
        }

        public final PipelinePhase getCall() {
            return ApplicationCallPipeline.Call;
        }

        public final PipelinePhase getFallback() {
            return ApplicationCallPipeline.Fallback;
        }

        public final PipelinePhase getFeatures() {
            return ApplicationCallPipeline.Features;
        }

        public final PipelinePhase getMonitoring() {
            return ApplicationCallPipeline.Monitoring;
        }

        public final PipelinePhase getPlugins() {
            return ApplicationCallPipeline.Plugins;
        }

        public final PipelinePhase getSetup() {
            return ApplicationCallPipeline.Setup;
        }

        private Companion() {
        }
    }

    static {
        PipelinePhase pipelinePhase = new PipelinePhase("Plugins");
        Plugins = pipelinePhase;
        Call = new PipelinePhase("Call");
        Fallback = new PipelinePhase("Fallback");
        Features = pipelinePhase;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ApplicationCallPipeline() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    @Override // io.ktor.util.pipeline.Pipeline
    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public ApplicationEnvironment getEnvironment() {
        return this.environment;
    }

    public final ApplicationReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final ApplicationSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public /* synthetic */ ApplicationCallPipeline(boolean z, ApplicationEnvironment applicationEnvironment, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : applicationEnvironment);
    }

    public ApplicationCallPipeline(boolean z, ApplicationEnvironment applicationEnvironment) {
        super(Setup, Monitoring, Plugins, Call, Fallback);
        this.developmentMode = z;
        this.environment = applicationEnvironment;
        this.receivePipeline = new ApplicationReceivePipeline(z);
        this.sendPipeline = new ApplicationSendPipeline(z);
    }
}
