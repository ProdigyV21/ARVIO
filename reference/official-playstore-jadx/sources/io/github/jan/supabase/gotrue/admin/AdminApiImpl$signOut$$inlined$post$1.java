package io.github.jan.supabase.gotrue.admin;

import io.github.jan.supabase.gotrue.SignOutScope;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.UtilsKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "io/github/jan/supabase/network/SupabaseHttpClient$post$3", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class AdminApiImpl$signOut$$inlined$post$1 extends r implements l<HttpRequestBuilder, t0> {
    final /* synthetic */ String $jwt$inlined;
    final /* synthetic */ SignOutScope $scope$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdminApiImpl$signOut$$inlined$post$1(SignOutScope signOutScope, String str) {
        super(1);
        this.$scope$inlined = signOutScope;
        this.$jwt$inlined = str;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        UtilsKt.parameter(httpRequestBuilder, "scope", this.$scope$inlined.name().toLowerCase(Locale.ROOT));
        httpRequestBuilder.getHeaders().set(HttpHeaders.INSTANCE.getAuthorization(), "Bearer " + this.$jwt$inlined);
    }
}
