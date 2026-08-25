package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR\u0014\u0010\u0005\u001a\u00028\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/api/HookHandler;", "T", "", "Lio/ktor/client/plugins/api/ClientHook;", "hook", "handler", "<init>", "(Lio/ktor/client/plugins/api/ClientHook;Ljava/lang/Object;)V", "Lio/ktor/client/HttpClient;", "client", "Lx6/t0;", "install", "(Lio/ktor/client/HttpClient;)V", "Lio/ktor/client/plugins/api/ClientHook;", "Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HookHandler<T> {
    private final T handler;
    private final ClientHook<T> hook;

    public HookHandler(ClientHook<T> clientHook, T t2) {
        this.hook = clientHook;
        this.handler = t2;
    }

    public final void install(HttpClient client) {
        this.hook.install(client, this.handler);
    }
}
