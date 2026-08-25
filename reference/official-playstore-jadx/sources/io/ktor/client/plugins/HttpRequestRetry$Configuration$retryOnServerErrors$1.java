package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpRequestRetry;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "<anonymous parameter 0>", "Lio/ktor/client/request/HttpRequest;", "response", "Lio/ktor/client/statement/HttpResponse;", "invoke", "(Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;Lio/ktor/client/request/HttpRequest;Lio/ktor/client/statement/HttpResponse;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestRetry$Configuration$retryOnServerErrors$1 extends r implements q<HttpRequestRetry.ShouldRetryContext, HttpRequest, HttpResponse, Boolean> {
    public static final HttpRequestRetry$Configuration$retryOnServerErrors$1 INSTANCE = new HttpRequestRetry$Configuration$retryOnServerErrors$1();

    public HttpRequestRetry$Configuration$retryOnServerErrors$1() {
        super(3);
    }

    @Override // r7.q
    public final Boolean invoke(HttpRequestRetry.ShouldRetryContext shouldRetryContext, HttpRequest httpRequest, HttpResponse httpResponse) {
        int value = httpResponse.getStatus().getValue();
        boolean z = false;
        if (500 <= value && value < 600) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
