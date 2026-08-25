package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpRequestRetry;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpRequestRetry$Configuration$modifyRequest$1 extends r implements p<HttpRequestRetry.ModifyRequestContext, HttpRequestBuilder, t0> {
    public static final HttpRequestRetry$Configuration$modifyRequest$1 INSTANCE = new HttpRequestRetry$Configuration$modifyRequest$1();

    public HttpRequestRetry$Configuration$modifyRequest$1() {
        super(2);
    }

    public final void invoke(HttpRequestRetry.ModifyRequestContext modifyRequestContext, HttpRequestBuilder httpRequestBuilder) {
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((HttpRequestRetry.ModifyRequestContext) obj, (HttpRequestBuilder) obj2);
        return t0.f22605a;
    }
}
