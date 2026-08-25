package io.ktor.server.engine;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0006\u001a\u00020\u00052\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "applicationEngineEnvironment", "(Lr7/l;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationEngineEnvironmentKt {
    public static final ApplicationEngineEnvironment applicationEngineEnvironment(l<? super ApplicationEngineEnvironmentBuilder, t0> lVar) {
        return new ApplicationEngineEnvironmentBuilder().build(lVar);
    }
}
