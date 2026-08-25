package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.request.ApplicationRequest;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/server/engine/BaseApplicationRequest;", "Lio/ktor/server/request/ApplicationRequest;", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "pipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getPipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BaseApplicationRequest implements ApplicationRequest {
    private final ApplicationCall call;
    private final ApplicationReceivePipeline pipeline;

    public BaseApplicationRequest(ApplicationCall applicationCall) {
        this.call = applicationCall;
        ApplicationReceivePipeline applicationReceivePipeline = new ApplicationReceivePipeline(applicationCall.getApplication().getEnvironment().getDevelopmentMode());
        applicationReceivePipeline.resetFrom(applicationCall.getApplication().getReceivePipeline());
        this.pipeline = applicationReceivePipeline;
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public final ApplicationCall getCall() {
        return this.call;
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public ApplicationReceivePipeline getPipeline() {
        return this.pipeline;
    }
}
