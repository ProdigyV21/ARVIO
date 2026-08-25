package io.ktor.client.engine;

import d7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.util.InternalAPI;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import ka.f0;
import ka.t;
import ka.u1;
import kotlin.Metadata;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\tR\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0014\u001a\u00020\u000f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineBase;", "Lio/ktor/client/engine/HttpClientEngine;", "", "engineName", "<init>", "(Ljava/lang/String;)V", "Lx6/t0;", "close", "()V", "Ljava/lang/String;", "Lka/f0;", "dispatcher", "Lka/f0;", "getDispatcher", "()Lka/f0;", "Ld7/j;", "coroutineContext$delegate", "Lx6/s;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class HttpClientEngineBase implements HttpClientEngine {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientEngineBase.class, "closed");
    private final String engineName;
    private volatile /* synthetic */ int closed = 0;
    private final f0 dispatcher = HttpClientEngineBase_jvmKt.ioDispatcher();

    /* JADX INFO: renamed from: coroutineContext$delegate, reason: from kotlin metadata */
    private final s coroutineContext = new i0(new HttpClientEngineBase$coroutineContext$2(this));

    public HttpClientEngineBase(String str) {
        this.engineName = str;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (closed$FU.compareAndSet(this, 0, 1)) {
            j.a aVar = getCoroutineContext().get(u1.f19642i);
            t tVar = aVar instanceof t ? (t) aVar : null;
            if (tVar == null) {
                return;
            }
            tVar.complete();
        }
    }

    @Override // io.ktor.client.engine.HttpClientEngine, ka.k0
    public j getCoroutineContext() {
        return (j) this.coroutineContext.getValue();
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public f0 getDispatcher() {
        return this.dispatcher;
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
