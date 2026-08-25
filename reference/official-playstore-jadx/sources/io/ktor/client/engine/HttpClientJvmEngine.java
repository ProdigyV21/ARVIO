package io.ktor.client.engine;

import d7.d;
import d7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.util.CoroutinesUtilsKt;
import io.ktor.util.InternalAPI;
import java.util.Set;
import ka.m0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import ka.z0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.e;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0084@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001b\u0010\u0012\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/client/engine/HttpClientJvmEngine;", "Lio/ktor/client/engine/HttpClientEngine;", "", "engineName", "<init>", "(Ljava/lang/String;)V", "Ld7/j;", "createCallContext", "(Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "close", "()V", "clientContext", "Ld7/j;", "coroutineContext$delegate", "Lx6/s;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public abstract class HttpClientJvmEngine implements HttpClientEngine {
    private final j clientContext = CoroutinesUtilsKt.SilentSupervisor$default(null, 1, null);

    /* JADX INFO: renamed from: coroutineContext$delegate, reason: from kotlin metadata */
    private final s coroutineContext;

    /* JADX INFO: renamed from: io.ktor.client.engine.HttpClientJvmEngine$createCallContext$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ z0 $onParentCancelCleanupHandle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(z0 z0Var) {
            super(1);
            this.$onParentCancelCleanupHandle = z0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            z0 z0Var = this.$onParentCancelCleanupHandle;
            if (z0Var != null) {
                z0Var.dispose();
            }
        }
    }

    public HttpClientJvmEngine(String str) {
        this.coroutineContext = new i0(new HttpClientJvmEngine$coroutineContext$2(this, str));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ((t) m0.k(this.clientContext)).complete();
    }

    public final Object createCallContext(d<? super j> dVar) {
        j jVar = this.clientContext;
        u1 u1Var = u1.f19642i;
        x1 x1Var = new x1((v1) jVar.get(u1Var));
        j jVarPlus = getCoroutineContext().plus(x1Var);
        v1 v1Var = (v1) dVar.getContext().get(u1Var);
        x1Var.invokeOnCompletion(new AnonymousClass2(v1Var != null ? v1Var.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new HttpClientJvmEngine$createCallContext$onParentCancelCleanupHandle$1(jVarPlus)) : null));
        return jVarPlus;
    }

    @Override // io.ktor.client.engine.HttpClientEngine, ka.k0
    public j getCoroutineContext() {
        return (j) this.coroutineContext.getValue();
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public Set<HttpClientEngineCapability<?>> getSupportedCapabilities() {
        return HttpClientEngine.DefaultImpls.getSupportedCapabilities(this);
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    @InternalAPI
    public void install(HttpClient httpClient) {
        HttpClientEngine.DefaultImpls.install(this, httpClient);
    }
}
