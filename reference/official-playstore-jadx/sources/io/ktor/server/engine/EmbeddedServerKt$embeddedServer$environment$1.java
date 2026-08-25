package io.ktor.server.engine;

import d7.j;
import io.ktor.server.application.Application;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00020\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class EmbeddedServerKt$embeddedServer$environment$1 extends r implements l<ApplicationEngineEnvironmentBuilder, t0> {
    final /* synthetic */ EngineConnectorConfig[] $connectors;
    final /* synthetic */ l<Application, t0> $module;
    final /* synthetic */ j $parentCoroutineContext;
    final /* synthetic */ k0 $this_embeddedServer;
    final /* synthetic */ List<String> $watchPaths;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EmbeddedServerKt$embeddedServer$environment$1(k0 k0Var, j jVar, List<String> list, l<? super Application, t0> lVar, EngineConnectorConfig[] engineConnectorConfigArr) {
        super(1);
        this.$this_embeddedServer = k0Var;
        this.$parentCoroutineContext = jVar;
        this.$watchPaths = list;
        this.$module = lVar;
        this.$connectors = engineConnectorConfigArr;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        applicationEngineEnvironmentBuilder.setParentCoroutineContext(this.$this_embeddedServer.getCoroutineContext().plus(this.$parentCoroutineContext));
        applicationEngineEnvironmentBuilder.setLog(KtorSimpleLoggerJvmKt.KtorSimpleLogger("ktor.application"));
        applicationEngineEnvironmentBuilder.setWatchPaths(this.$watchPaths);
        applicationEngineEnvironmentBuilder.module(this.$module);
        x.c0(applicationEngineEnvironmentBuilder.getConnectors(), this.$connectors);
    }
}
