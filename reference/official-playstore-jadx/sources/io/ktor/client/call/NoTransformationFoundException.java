package io.ktor.client.call;

import f4.f;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import kotlin.Metadata;
import kotlin.reflect.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B'\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/call/NoTransformationFoundException;", "Ljava/lang/UnsupportedOperationException;", "Lkotlin/UnsupportedOperationException;", "Lio/ktor/client/statement/HttpResponse;", "response", "Lkotlin/reflect/d;", "from", "to", "<init>", "(Lio/ktor/client/statement/HttpResponse;Lkotlin/reflect/d;Lkotlin/reflect/d;)V", "", "message", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NoTransformationFoundException extends UnsupportedOperationException {
    private final String message;

    public NoTransformationFoundException(HttpResponse httpResponse, d<?> dVar, d<?> dVar2) {
        StringBuilder sb2 = new StringBuilder("\n        Expected response body of the type '");
        sb2.append(dVar2);
        sb2.append("' but was '");
        sb2.append(dVar);
        sb2.append("'\n        In response from `");
        sb2.append(HttpResponseKt.getRequest(httpResponse).getUrl());
        sb2.append("`\n        Response status `");
        sb2.append(httpResponse.getStatus());
        sb2.append("`\n        Response header `ContentType: ");
        Headers headers = httpResponse.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        sb2.append(headers.get(httpHeaders.getContentType()));
        sb2.append("` \n        Request header `Accept: ");
        sb2.append(HttpResponseKt.getRequest(httpResponse).getHeaders().get(httpHeaders.getAccept()));
        sb2.append("`\n        \n        You can read how to resolve NoTransformationFoundException at FAQ: \n        https://ktor.io/docs/faq.html#no-transformation-found-exception\n    ");
        this.message = f.y(sb2.toString());
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
