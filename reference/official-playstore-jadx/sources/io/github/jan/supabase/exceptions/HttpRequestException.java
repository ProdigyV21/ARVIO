package io.github.jan.supabase.exceptions;

import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/exceptions/HttpRequestException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "(Ljava/lang/String;Lio/ktor/client/request/HttpRequestBuilder;)V", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HttpRequestException extends Exception {
    public HttpRequestException(String str, HttpRequestBuilder httpRequestBuilder) {
        super("HTTP request to " + httpRequestBuilder.getUrl().buildString() + " (" + httpRequestBuilder.getMethod().getValue() + ") failed with message: " + str);
    }
}
