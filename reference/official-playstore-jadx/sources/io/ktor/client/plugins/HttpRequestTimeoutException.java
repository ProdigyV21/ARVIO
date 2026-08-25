package io.ktor.client.plugins;

import a0.c;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/client/plugins/HttpRequestTimeoutException;", "Ljava/io/IOException;", "Lio/ktor/utils/io/errors/IOException;", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/request/HttpRequestData;)V", "url", "", "timeoutMillis", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestTimeoutException extends IOException {
    /* JADX WARN: Illegal instructions before constructor call */
    public HttpRequestTimeoutException(String str, Long l10) {
        StringBuilder sbT = c.t("Request timeout has expired [url=", str, ", request_timeout=");
        sbT.append(l10 == null ? "unknown" : l10);
        sbT.append(" ms]");
        super(sbT.toString());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HttpRequestTimeoutException(HttpRequestBuilder httpRequestBuilder) {
        String strBuildString = httpRequestBuilder.getUrl().buildString();
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestBuilder.getCapabilityOrNull(HttpTimeout.INSTANCE);
        this(strBuildString, httpTimeoutCapabilityConfiguration != null ? httpTimeoutCapabilityConfiguration.get_requestTimeoutMillis() : null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HttpRequestTimeoutException(HttpRequestData httpRequestData) {
        String urlString = httpRequestData.getUrl().getUrlString();
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.INSTANCE);
        this(urlString, httpTimeoutCapabilityConfiguration != null ? httpTimeoutCapabilityConfiguration.get_requestTimeoutMillis() : null);
    }
}
