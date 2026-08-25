package io.ktor.client.statement;

import d7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpResponseData;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\f\u001a\u00020\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001a\u0010\"\u001a\u00020!8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001a\u0010'\u001a\u00020&8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {"Lio/ktor/client/statement/DefaultHttpResponse;", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/call/HttpClientCall;", "call", "Lio/ktor/client/request/HttpResponseData;", "responseData", "<init>", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/request/HttpResponseData;)V", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "Ld7/j;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lio/ktor/http/HttpStatusCode;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "Lio/ktor/http/HttpProtocolVersion;", "version", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "Lio/ktor/util/date/GMTDate;", "requestTime", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "responseTime", "getResponseTime", "Lio/ktor/utils/io/ByteReadChannel;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "getContent", "()Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultHttpResponse extends HttpResponse {
    private final HttpClientCall call;
    private final ByteReadChannel content;
    private final j coroutineContext;
    private final Headers headers;
    private final GMTDate requestTime;
    private final GMTDate responseTime;
    private final HttpStatusCode status;
    private final HttpProtocolVersion version;

    public DefaultHttpResponse(HttpClientCall httpClientCall, HttpResponseData httpResponseData) {
        this.call = httpClientCall;
        this.coroutineContext = httpResponseData.getCallContext();
        this.status = httpResponseData.getStatusCode();
        this.version = httpResponseData.getVersion();
        this.requestTime = httpResponseData.getRequestTime();
        this.responseTime = httpResponseData.getResponseTime();
        Object body = httpResponseData.getBody();
        ByteReadChannel byteReadChannel = body instanceof ByteReadChannel ? (ByteReadChannel) body : null;
        this.content = byteReadChannel == null ? ByteReadChannel.INSTANCE.getEmpty() : byteReadChannel;
        this.headers = httpResponseData.getHeaders();
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public ByteReadChannel getContent() {
        return this.content;
    }

    @Override // io.ktor.client.statement.HttpResponse, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.http.HttpMessage
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getRequestTime() {
        return this.requestTime;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getResponseTime() {
        return this.responseTime;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpStatusCode getStatus() {
        return this.status;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpProtocolVersion getVersion() {
        return this.version;
    }
}
