package io.ktor.server.engine;

import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lkotlin/Function1;", "Lio/ktor/server/engine/EngineConnectorBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "connector", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;Lr7/l;)V", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EngineConnectorConfigKt {
    public static final void connector(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder, l<? super EngineConnectorBuilder, t0> lVar) {
        List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
        EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder(null, 1, null);
        lVar.invoke(engineConnectorBuilder);
        connectors.add(engineConnectorBuilder);
    }
}
