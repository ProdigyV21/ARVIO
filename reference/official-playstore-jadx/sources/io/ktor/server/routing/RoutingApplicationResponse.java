package io.ktor.server.routing;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseCookies;
import io.ktor.server.response.ResponseHeaders;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0097\u0001¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\b\u000f\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u001c8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010#\u001a\u00020 8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010%\u001a\u00020$8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b'\u0010&¨\u0006("}, d2 = {"Lio/ktor/server/routing/RoutingApplicationResponse;", "Lio/ktor/server/response/ApplicationResponse;", "Lio/ktor/server/routing/RoutingApplicationCall;", "call", "Lio/ktor/server/response/ApplicationSendPipeline;", "pipeline", "engineResponse", "<init>", "(Lio/ktor/server/routing/RoutingApplicationCall;Lio/ktor/server/response/ApplicationSendPipeline;Lio/ktor/server/response/ApplicationResponse;)V", "Lio/ktor/server/response/ResponsePushBuilder;", "builder", "Lx6/t0;", "push", "(Lio/ktor/server/response/ResponsePushBuilder;)V", "Lio/ktor/http/HttpStatusCode;", "status", "()Lio/ktor/http/HttpStatusCode;", "value", "(Lio/ktor/http/HttpStatusCode;)V", "Lio/ktor/server/routing/RoutingApplicationCall;", "getCall", "()Lio/ktor/server/routing/RoutingApplicationCall;", "Lio/ktor/server/response/ApplicationSendPipeline;", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "Lio/ktor/server/response/ApplicationResponse;", "getEngineResponse", "()Lio/ktor/server/response/ApplicationResponse;", "Lio/ktor/server/response/ResponseCookies;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "cookies", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "headers", "", "isCommitted", "()Z", "isSent", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RoutingApplicationResponse implements ApplicationResponse {
    private final RoutingApplicationCall call;
    private final ApplicationResponse engineResponse;
    private final ApplicationSendPipeline pipeline;

    public RoutingApplicationResponse(RoutingApplicationCall routingApplicationCall, ApplicationSendPipeline applicationSendPipeline, ApplicationResponse applicationResponse) {
        this.call = routingApplicationCall;
        this.pipeline = applicationSendPipeline;
        this.engineResponse = applicationResponse;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public ResponseCookies getCookies() {
        return this.engineResponse.getCookies();
    }

    public final ApplicationResponse getEngineResponse() {
        return this.engineResponse;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public ResponseHeaders getHeaders() {
        return this.engineResponse.getHeaders();
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public ApplicationSendPipeline getPipeline() {
        return this.pipeline;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public boolean isCommitted() {
        return this.engineResponse.isCommitted();
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public boolean isSent() {
        return this.engineResponse.isSent();
    }

    @Override // io.ktor.server.response.ApplicationResponse
    @UseHttp2Push
    public void push(ResponsePushBuilder builder) {
        this.engineResponse.push(builder);
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public HttpStatusCode status() {
        return this.engineResponse.status();
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public RoutingApplicationCall getCall() {
        return this.call;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public void status(HttpStatusCode value) {
        this.engineResponse.status(value);
    }
}
