package io.ktor.server.cio;

import io.ktor.network.sockets.ServerSocket;
import ka.p0;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\r\u0010\fR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/server/cio/HttpServer;", "", "Lka/v1;", "rootServerJob", "acceptJob", "Lka/p0;", "Lio/ktor/network/sockets/ServerSocket;", "serverSocket", "<init>", "(Lka/v1;Lka/v1;Lka/p0;)V", "Lka/v1;", "getRootServerJob", "()Lka/v1;", "getAcceptJob", "Lka/p0;", "getServerSocket", "()Lka/p0;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpServer {
    private final v1 acceptJob;
    private final v1 rootServerJob;
    private final p0<ServerSocket> serverSocket;

    /* JADX WARN: Multi-variable type inference failed */
    public HttpServer(v1 v1Var, v1 v1Var2, p0<? extends ServerSocket> p0Var) {
        this.rootServerJob = v1Var;
        this.acceptJob = v1Var2;
        this.serverSocket = p0Var;
    }

    public final v1 getAcceptJob() {
        return this.acceptJob;
    }

    public final v1 getRootServerJob() {
        return this.rootServerJob;
    }

    public final p0<ServerSocket> getServerSocket() {
        return this.serverSocket;
    }
}
