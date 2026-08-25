package io.ktor.http;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/http/URLParserException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "urlString", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class URLParserException extends IllegalStateException {
    public URLParserException(String str, Throwable th) {
        super("Fail to parse url: ".concat(str), th);
    }
}
