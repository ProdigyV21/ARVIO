package io.ktor.client.utils;

import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/client/utils/HttpResponseReceiveFail;", "", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "(Lio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpResponseReceiveFail {
    private final Throwable cause;
    private final HttpResponse response;

    public HttpResponseReceiveFail(HttpResponse httpResponse, Throwable th) {
        this.response = httpResponse;
        this.cause = th;
    }

    public final Throwable getCause() {
        return this.cause;
    }

    public final HttpResponse getResponse() {
        return this.response;
    }
}
