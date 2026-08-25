package io.ktor.server.application;

import d7.j;
import io.ktor.util.KtorDsl;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.p2;
import ka.t;
import ka.u1;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/server/application/Application;", "Lio/ktor/server/application/ApplicationCallPipeline;", "Lka/k0;", "Lio/ktor/server/application/ApplicationEnvironment;", "environment", "<init>", "(Lio/ktor/server/application/ApplicationEnvironment;)V", "Lx6/t0;", "dispose", "()V", "Lio/ktor/server/application/ApplicationEnvironment;", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "Lka/t;", "applicationJob", "Lka/t;", "Ld7/j;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Application extends ApplicationCallPipeline implements k0 {
    private final t applicationJob;
    private final j coroutineContext;
    private final ApplicationEnvironment environment;

    public Application(ApplicationEnvironment applicationEnvironment) {
        super(applicationEnvironment.getDevelopmentMode(), applicationEnvironment);
        this.environment = applicationEnvironment;
        p2 p2Var = new p2((v1) getEnvironment().getParentCoroutineContext().get(u1.f19642i));
        this.applicationJob = p2Var;
        this.coroutineContext = getEnvironment().getParentCoroutineContext().plus(p2Var);
    }

    public final void dispose() {
        this.applicationJob.cancel((CancellationException) null);
        ApplicationPluginKt.uninstallAllPlugins(this);
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.server.application.ApplicationCallPipeline
    public ApplicationEnvironment getEnvironment() {
        return this.environment;
    }
}
