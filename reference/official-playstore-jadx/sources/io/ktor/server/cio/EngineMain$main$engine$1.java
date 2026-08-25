package io.ktor.server.cio;

import io.ktor.server.cio.CIOApplicationEngine;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "Lx6/t0;", "invoke", "(Lio/ktor/server/cio/CIOApplicationEngine$Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class EngineMain$main$engine$1 extends r implements l<CIOApplicationEngine.Configuration, t0> {
    final /* synthetic */ ApplicationEngineEnvironment $applicationEnvironment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineMain$main$engine$1(ApplicationEngineEnvironment applicationEngineEnvironment) {
        super(1);
        this.$applicationEnvironment = applicationEngineEnvironment;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CIOApplicationEngine.Configuration) obj);
        return t0.f22605a;
    }

    public final void invoke(CIOApplicationEngine.Configuration configuration) {
        EngineMain.INSTANCE.loadConfiguration(configuration, this.$applicationEnvironment.getConfig());
    }
}
