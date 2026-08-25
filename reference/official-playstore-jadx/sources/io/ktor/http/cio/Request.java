package io.ktor.http.cio;

import io.ktor.http.HttpMethod;
import io.ktor.http.cio.internals.CharArrayBuilder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/http/cio/Request;", "Lio/ktor/http/cio/HttpMessage;", "method", "Lio/ktor/http/HttpMethod;", "uri", "", "version", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "builder", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "(Lio/ktor/http/HttpMethod;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Lio/ktor/http/cio/HttpHeadersMap;Lio/ktor/http/cio/internals/CharArrayBuilder;)V", "getMethod", "()Lio/ktor/http/HttpMethod;", "getUri", "()Ljava/lang/CharSequence;", "getVersion", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Request extends HttpMessage {
    private final HttpMethod method;
    private final CharSequence uri;
    private final CharSequence version;

    public Request(HttpMethod httpMethod, CharSequence charSequence, CharSequence charSequence2, HttpHeadersMap httpHeadersMap, CharArrayBuilder charArrayBuilder) {
        super(httpHeadersMap, charArrayBuilder);
        this.method = httpMethod;
        this.uri = charSequence;
        this.version = charSequence2;
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public final CharSequence getUri() {
        return this.uri;
    }

    public final CharSequence getVersion() {
        return this.version;
    }
}
