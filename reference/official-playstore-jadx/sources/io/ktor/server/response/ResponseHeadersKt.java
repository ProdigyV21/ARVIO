package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/response/ResponseHeaders;", "", ContentDisposition.Parameters.Name, "value", "", "safeOnly", "Lx6/t0;", "appendIfAbsent", "(Lio/ktor/server/response/ResponseHeaders;Ljava/lang/String;Ljava/lang/String;Z)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ResponseHeadersKt {
    public static final void appendIfAbsent(ResponseHeaders responseHeaders, String str, String str2, boolean z) {
        if (responseHeaders.contains(str)) {
            return;
        }
        responseHeaders.append(str, str2, z);
    }

    public static /* synthetic */ void appendIfAbsent$default(ResponseHeaders responseHeaders, String str, String str2, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        appendIfAbsent(responseHeaders, str, str2, z);
    }
}
