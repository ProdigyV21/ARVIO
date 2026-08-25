package io.ktor.http;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/http/InvalidCookieDateException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "data", "", "reason", "(Ljava/lang/String;Ljava/lang/String;)V", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InvalidCookieDateException extends IllegalStateException {
    public InvalidCookieDateException(String str, String str2) {
        super("Failed to parse date string: \"" + str + "\". Reason: \"" + str2 + '\"');
    }
}
