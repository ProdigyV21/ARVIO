package io.ktor.http.cio;

import io.ktor.http.cio.internals.CharArrayBuilder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/http/cio/Response;", "Lio/ktor/http/cio/HttpMessage;", "version", "", "status", "", "statusText", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "builder", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "(Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lio/ktor/http/cio/HttpHeadersMap;Lio/ktor/http/cio/internals/CharArrayBuilder;)V", "getStatus", "()I", "getStatusText", "()Ljava/lang/CharSequence;", "getVersion", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Response extends HttpMessage {
    private final int status;
    private final CharSequence statusText;
    private final CharSequence version;

    public Response(CharSequence charSequence, int i10, CharSequence charSequence2, HttpHeadersMap httpHeadersMap, CharArrayBuilder charArrayBuilder) {
        super(httpHeadersMap, charArrayBuilder);
        this.version = charSequence;
        this.status = i10;
        this.statusText = charSequence2;
    }

    public final int getStatus() {
        return this.status;
    }

    public final CharSequence getStatusText() {
        return this.statusText;
    }

    public final CharSequence getVersion() {
        return this.version;
    }
}
