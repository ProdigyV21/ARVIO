package io.ktor.server.cio;

import io.ktor.server.cio.CIOApplicationEngine;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.ApplicationEngineFactory;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/cio/CIO;", "Lio/ktor/server/engine/ApplicationEngineFactory;", "Lio/ktor/server/cio/CIOApplicationEngine;", "Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "<init>", "()V", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environment", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "create", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lr7/l;)Lio/ktor/server/cio/CIOApplicationEngine;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIO implements ApplicationEngineFactory<CIOApplicationEngine, CIOApplicationEngine.Configuration> {
    public static final CIO INSTANCE = new CIO();

    private CIO() {
    }

    @Override // io.ktor.server.engine.ApplicationEngineFactory
    public /* bridge */ /* synthetic */ ApplicationEngine create(ApplicationEngineEnvironment applicationEngineEnvironment, l lVar) {
        return create(applicationEngineEnvironment, (l<? super CIOApplicationEngine.Configuration, t0>) lVar);
    }

    @Override // io.ktor.server.engine.ApplicationEngineFactory
    public CIOApplicationEngine create(ApplicationEngineEnvironment environment, l<? super CIOApplicationEngine.Configuration, t0> configure) {
        return new CIOApplicationEngine(environment, configure);
    }
}
