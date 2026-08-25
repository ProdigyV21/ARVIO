package io.ktor.server.response;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH'¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u001c8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lio/ktor/server/response/ApplicationResponse;", "", "Lio/ktor/http/HttpStatusCode;", "status", "()Lio/ktor/http/HttpStatusCode;", "value", "Lx6/t0;", "(Lio/ktor/http/HttpStatusCode;)V", "Lio/ktor/server/response/ResponsePushBuilder;", "builder", "push", "(Lio/ktor/server/response/ResponsePushBuilder;)V", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "call", "Lio/ktor/server/response/ApplicationSendPipeline;", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "pipeline", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "headers", "", "isCommitted", "()Z", "isSent", "Lio/ktor/server/response/ResponseCookies;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "cookies", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationResponse {
    ApplicationCall getCall();

    ResponseCookies getCookies();

    ResponseHeaders getHeaders();

    ApplicationSendPipeline getPipeline();

    boolean isCommitted();

    boolean isSent();

    @UseHttp2Push
    void push(ResponsePushBuilder builder);

    HttpStatusCode status();

    void status(HttpStatusCode value);
}
