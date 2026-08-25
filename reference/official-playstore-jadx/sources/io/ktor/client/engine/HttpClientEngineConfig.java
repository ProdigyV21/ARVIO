package io.ktor.client.engine;

import io.ktor.util.KtorDsl;
import java.net.Proxy;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "", "()V", "pipelining", "", "getPipelining", "()Z", "setPipelining", "(Z)V", "proxy", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "getProxy", "()Ljava/net/Proxy;", "setProxy", "(Ljava/net/Proxy;)V", "threadsCount", "", "getThreadsCount$annotations", "getThreadsCount", "()I", "setThreadsCount", "(I)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class HttpClientEngineConfig {
    private boolean pipelining;
    private Proxy proxy;
    private int threadsCount = 4;

    @e
    public static /* synthetic */ void getThreadsCount$annotations() {
    }

    public final boolean getPipelining() {
        return this.pipelining;
    }

    public final Proxy getProxy() {
        return this.proxy;
    }

    public final int getThreadsCount() {
        return this.threadsCount;
    }

    public final void setPipelining(boolean z) {
        this.pipelining = z;
    }

    public final void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public final void setThreadsCount(int i10) {
        this.threadsCount = i10;
    }
}
