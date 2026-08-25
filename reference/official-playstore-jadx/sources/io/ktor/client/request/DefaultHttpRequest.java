package io.ktor.client.request;

import d7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import io.ktor.util.InternalAPI;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\f\u001a\u00020\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010 \u001a\u00020\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0014\u0010'\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lio/ktor/client/request/DefaultHttpRequest;", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/call/HttpClientCall;", "call", "Lio/ktor/client/request/HttpRequestData;", "data", "<init>", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/request/HttpRequestData;)V", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "Lio/ktor/http/Url;", "url", "Lio/ktor/http/Url;", "getUrl", "()Lio/ktor/http/Url;", "Lio/ktor/http/content/OutgoingContent;", "content", "Lio/ktor/http/content/OutgoingContent;", "getContent", "()Lio/ktor/http/content/OutgoingContent;", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class DefaultHttpRequest implements HttpRequest {
    private final Attributes attributes;
    private final HttpClientCall call;
    private final OutgoingContent content;
    private final Headers headers;
    private final HttpMethod method;
    private final Url url;

    public DefaultHttpRequest(HttpClientCall httpClientCall, HttpRequestData httpRequestData) {
        this.call = httpClientCall;
        this.method = httpRequestData.getMethod();
        this.url = httpRequestData.getUrl();
        this.content = httpRequestData.getBody();
        this.headers = httpRequestData.getHeaders();
        this.attributes = httpRequestData.getAttributes();
    }

    @Override // io.ktor.client.request.HttpRequest
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.ktor.client.request.HttpRequest
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.request.HttpRequest
    public OutgoingContent getContent() {
        return this.content;
    }

    @Override // io.ktor.client.request.HttpRequest, ka.k0
    public j getCoroutineContext() {
        return getCall().getCoroutineContext();
    }

    @Override // io.ktor.http.HttpMessage
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.request.HttpRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.ktor.client.request.HttpRequest
    public Url getUrl() {
        return this.url;
    }
}
