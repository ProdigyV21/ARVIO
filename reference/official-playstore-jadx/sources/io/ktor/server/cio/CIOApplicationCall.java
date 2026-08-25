package io.ktor.server.cio;

import androidx.media3.common.MimeTypes;
import d7.j;
import io.ktor.http.cio.Request;
import io.ktor.server.application.Application;
import io.ktor.server.engine.BaseApplicationCall;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.net.SocketAddress;
import ka.r;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u0012\u001a\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011\u0012\u000e\u0010\u0013\u001a\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0019\u001a\u00020\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010 \u001a\u00020\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lio/ktor/server/cio/CIOApplicationCall;", "Lio/ktor/server/engine/BaseApplicationCall;", "Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "Lio/ktor/http/cio/Request;", "_request", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Ld7/j;", "engineDispatcher", "appDispatcher", "Lka/r;", "", "upgraded", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "remoteAddress", "localAddress", "<init>", "(Lio/ktor/server/application/Application;Lio/ktor/http/cio/Request;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/j;Ld7/j;Lka/r;Ljava/net/SocketAddress;Ljava/net/SocketAddress;)V", "Lx6/t0;", "release$ktor_server_cio", "()V", "release", "Lio/ktor/server/cio/CIOApplicationRequest;", "request", "Lio/ktor/server/cio/CIOApplicationRequest;", "getRequest", "()Lio/ktor/server/cio/CIOApplicationRequest;", "Lio/ktor/server/cio/CIOApplicationResponse;", "response", "Lio/ktor/server/cio/CIOApplicationResponse;", "getResponse", "()Lio/ktor/server/cio/CIOApplicationResponse;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOApplicationCall extends BaseApplicationCall {
    private final CIOApplicationRequest request;
    private final CIOApplicationResponse response;

    public CIOApplicationCall(Application application, Request request, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, j jVar, j jVar2, r<Boolean> rVar, SocketAddress socketAddress, SocketAddress socketAddress2) {
        super(application);
        this.request = new CIOApplicationRequest(this, socketAddress, socketAddress2, byteReadChannel, request);
        this.response = new CIOApplicationResponse(this, byteWriteChannel, byteReadChannel, jVar, jVar2, rVar);
        BaseApplicationCall.putResponseAttribute$default(this, null, 1, null);
    }

    public final void release$ktor_server_cio() {
        getRequest().release$ktor_server_cio();
    }

    @Override // io.ktor.server.application.ApplicationCall
    public CIOApplicationRequest getRequest() {
        return this.request;
    }

    @Override // io.ktor.server.application.ApplicationCall
    public CIOApplicationResponse getResponse() {
        return this.response;
    }
}
