package io.ktor.client;

import io.ktor.client.plugins.DefaultTransformKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/HttpClient;", "Lx6/t0;", "invoke", "(Lio/ktor/client/HttpClient;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class HttpClient$3$1 extends r implements l<HttpClient, t0> {
    public static final HttpClient$3$1 INSTANCE = new HttpClient$3$1();

    public HttpClient$3$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpClient) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpClient httpClient) {
        DefaultTransformKt.defaultTransformers(httpClient);
    }
}
