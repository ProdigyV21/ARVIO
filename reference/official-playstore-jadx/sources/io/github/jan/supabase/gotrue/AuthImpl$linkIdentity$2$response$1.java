package io.github.jan.supabase.gotrue;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class AuthImpl$linkIdentity$2$response$1 extends r implements l<HttpRequestBuilder, t0> {
    public static final AuthImpl$linkIdentity$2$response$1 INSTANCE = new AuthImpl$linkIdentity$2$response$1();

    public AuthImpl$linkIdentity$2$response$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
    }
}
