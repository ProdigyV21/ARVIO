package io.ktor.server.cio;

import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.Parameters;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.cio.CIOHeaders;
import io.ktor.http.cio.Request;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.engine.BaseApplicationRequest;
import io.ktor.server.request.RequestCookies;
import io.ktor.utils.io.ByteReadChannel;
import java.net.SocketAddress;
import kotlin.Metadata;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005\u0012\u000e\u0010\u0007\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0015R\u001b\u0010\u001b\u001a\u00020\u00168VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001d\u001a\u00020\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010%\u001a\u00020!8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0018\u001a\u0004\b#\u0010$R\u001b\u0010(\u001a\u00020!8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0018\u001a\u0004\b'\u0010$R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lio/ktor/server/cio/CIOApplicationRequest;", "Lio/ktor/server/engine/BaseApplicationRequest;", "Lio/ktor/server/application/ApplicationCall;", "call", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "remoteAddress", "localAddress", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/http/cio/Request;", "request", "<init>", "(Lio/ktor/server/application/ApplicationCall;Ljava/net/SocketAddress;Ljava/net/SocketAddress;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/http/cio/Request;)V", "receiveChannel", "()Lio/ktor/utils/io/ByteReadChannel;", "Lx6/t0;", "release$ktor_server_cio", "()V", "release", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/http/cio/Request;", "Lio/ktor/server/request/RequestCookies;", "cookies$delegate", "Lx6/s;", "getCookies", "()Lio/ktor/server/request/RequestCookies;", "cookies", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/http/Parameters;", "queryParameters$delegate", "getQueryParameters", "()Lio/ktor/http/Parameters;", "queryParameters", "rawQueryParameters$delegate", "getRawQueryParameters", "rawQueryParameters", "Lio/ktor/http/RequestConnectionPoint;", "local", "Lio/ktor/http/RequestConnectionPoint;", "getLocal", "()Lio/ktor/http/RequestConnectionPoint;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOApplicationRequest extends BaseApplicationRequest {

    /* JADX INFO: renamed from: cookies$delegate, reason: from kotlin metadata */
    private final s cookies;
    private final Headers headers;
    private final ByteReadChannel input;
    private final RequestConnectionPoint local;

    /* JADX INFO: renamed from: queryParameters$delegate, reason: from kotlin metadata */
    private final s queryParameters;

    /* JADX INFO: renamed from: rawQueryParameters$delegate, reason: from kotlin metadata */
    private final s rawQueryParameters;
    private final Request request;

    public CIOApplicationRequest(ApplicationCall applicationCall, SocketAddress socketAddress, SocketAddress socketAddress2, ByteReadChannel byteReadChannel, Request request) {
        super(applicationCall);
        this.input = byteReadChannel;
        this.request = request;
        this.cookies = new i0(new CIOApplicationRequest$cookies$2(this));
        this.headers = new CIOHeaders(request.getHeaders());
        this.queryParameters = new i0(new CIOApplicationRequest$queryParameters$2(this));
        this.rawQueryParameters = new i0(new CIOApplicationRequest$rawQueryParameters$2(this));
        String string = request.getVersion().toString();
        String string2 = request.getUri().toString();
        CharSequence charSequence = request.getHeaders().get(HttpHeaders.INSTANCE.getHost());
        this.local = new CIOConnectionPoint(socketAddress, socketAddress2, string, string2, charSequence != null ? charSequence.toString() : null, HttpMethod.INSTANCE.parse(request.getMethod().getValue()));
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public RequestCookies getCookies() {
        return (RequestCookies) this.cookies.getValue();
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public RequestConnectionPoint getLocal() {
        return this.local;
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public Parameters getQueryParameters() {
        return (Parameters) this.queryParameters.getValue();
    }

    @Override // io.ktor.server.request.ApplicationRequest
    public Parameters getRawQueryParameters() {
        return (Parameters) this.rawQueryParameters.getValue();
    }

    @Override // io.ktor.server.request.ApplicationRequest
    /* JADX INFO: renamed from: receiveChannel, reason: from getter */
    public ByteReadChannel getInput() {
        return this.input;
    }

    public final void release$ktor_server_cio() {
        this.request.release();
    }
}
