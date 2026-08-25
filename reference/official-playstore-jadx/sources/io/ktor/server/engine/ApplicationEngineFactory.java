package io.ktor.server.engine;

import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.ApplicationEngine.Configuration;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u00020\u0005J0\u0010\f\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH&¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineFactory;", "Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environment", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "create", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lr7/l;)Lio/ktor/server/engine/ApplicationEngine;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationEngineFactory<TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> {
    TEngine create(ApplicationEngineEnvironment environment, l<? super TConfiguration, t0> configure);
}
