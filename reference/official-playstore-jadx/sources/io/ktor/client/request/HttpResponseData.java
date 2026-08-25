package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.j;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0001\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\n\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\n\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b$\u0010\u0015\u001a\u0004\b%\u0010\u0017¨\u0006&"}, d2 = {"Lio/ktor/client/request/HttpResponseData;", "", "Lio/ktor/http/HttpStatusCode;", "statusCode", "Lio/ktor/util/date/GMTDate;", "requestTime", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/HttpProtocolVersion;", "version", TtmlNode.TAG_BODY, "Ld7/j;", "callContext", "<init>", "(Lio/ktor/http/HttpStatusCode;Lio/ktor/util/date/GMTDate;Lio/ktor/http/Headers;Lio/ktor/http/HttpProtocolVersion;Ljava/lang/Object;Ld7/j;)V", "", "toString", "()Ljava/lang/String;", "Lio/ktor/http/HttpStatusCode;", "getStatusCode", "()Lio/ktor/http/HttpStatusCode;", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "Ljava/lang/Object;", "getBody", "()Ljava/lang/Object;", "Ld7/j;", "getCallContext", "()Ld7/j;", "responseTime", "getResponseTime", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpResponseData {
    private final Object body;
    private final j callContext;
    private final Headers headers;
    private final GMTDate requestTime;
    private final GMTDate responseTime = DateJvmKt.GMTDate$default(null, 1, null);
    private final HttpStatusCode statusCode;
    private final HttpProtocolVersion version;

    public HttpResponseData(HttpStatusCode httpStatusCode, GMTDate gMTDate, Headers headers, HttpProtocolVersion httpProtocolVersion, Object obj, j jVar) {
        this.statusCode = httpStatusCode;
        this.requestTime = gMTDate;
        this.headers = headers;
        this.version = httpProtocolVersion;
        this.body = obj;
        this.callContext = jVar;
    }

    public final Object getBody() {
        return this.body;
    }

    public final j getCallContext() {
        return this.callContext;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final GMTDate getRequestTime() {
        return this.requestTime;
    }

    public final GMTDate getResponseTime() {
        return this.responseTime;
    }

    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }

    public final HttpProtocolVersion getVersion() {
        return this.version;
    }

    public String toString() {
        return "HttpResponseData=(statusCode=" + this.statusCode + ')';
    }
}
