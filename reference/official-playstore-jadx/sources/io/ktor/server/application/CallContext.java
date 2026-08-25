package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0010\u0010\u0006\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0006\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00050\u00048\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/application/CallContext;", "", "PluginConfig", "pluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "context", "<init>", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "Lx6/t0;", "finish$ktor_server_core", "()V", "finish", "Ljava/lang/Object;", "getPluginConfig", "()Ljava/lang/Object;", "Lio/ktor/util/pipeline/PipelineContext;", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class CallContext<PluginConfig> {
    private final PipelineContext<?, ApplicationCall> context;
    private final PluginConfig pluginConfig;

    public CallContext(PluginConfig pluginconfig, PipelineContext<?, ApplicationCall> pipelineContext) {
        this.pluginConfig = pluginconfig;
        this.context = pipelineContext;
    }

    public final void finish$ktor_server_core() {
        getContext().finish();
    }

    public PipelineContext<?, ApplicationCall> getContext() {
        return this.context;
    }

    public final PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }
}
