package io.ktor.client.plugins;

import androidx.compose.foundation.c;
import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/client/plugins/ServerResponseException;", "Lio/ktor/client/plugins/ResponseException;", "response", "Lio/ktor/client/statement/HttpResponse;", "(Lio/ktor/client/statement/HttpResponse;)V", "cachedResponseText", "", "(Lio/ktor/client/statement/HttpResponse;Ljava/lang/String;)V", "message", "getMessage", "()Ljava/lang/String;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ServerResponseException extends ResponseException {
    private final String message;

    public ServerResponseException(HttpResponse httpResponse, String str) {
        super(httpResponse, str);
        StringBuilder sb2 = new StringBuilder("Server error(");
        sb2.append(httpResponse.getCall().getRequest().getMethod().getValue());
        sb2.append(' ');
        sb2.append(httpResponse.getCall().getRequest().getUrl());
        sb2.append(": ");
        sb2.append(httpResponse.getStatus());
        sb2.append(". Text: \"");
        this.message = c.u(sb2, str, '\"');
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @e
    public ServerResponseException(HttpResponse httpResponse) {
        this(httpResponse, "<no response text provided>");
    }
}
