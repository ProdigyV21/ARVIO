package io.github.jan.supabase.gotrue.admin;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "io/github/jan/supabase/network/SupabaseHttpClient$get$3", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class AdminApiImpl$retrieveUsers$$inlined$get$1 extends r implements l<HttpRequestBuilder, t0> {
    final /* synthetic */ Integer $page$inlined;
    final /* synthetic */ Integer $perPage$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdminApiImpl$retrieveUsers$$inlined$get$1(Integer num, Integer num2) {
        super(1);
        this.$page$inlined = num;
        this.$perPage$inlined = num2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        Integer num = this.$page$inlined;
        if (num != null) {
            httpRequestBuilder.getUrl().getParameters().append("page", String.valueOf(num.intValue()));
        }
        Integer num2 = this.$perPage$inlined;
        if (num2 != null) {
            httpRequestBuilder.getUrl().getParameters().append("per_page", String.valueOf(num2.intValue()));
        }
    }
}
