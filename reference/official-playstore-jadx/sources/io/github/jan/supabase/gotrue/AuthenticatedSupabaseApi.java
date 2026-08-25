package io.github.jan.supabase.gotrue;

import d7.d;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.exceptions.RestException;
import io.github.jan.supabase.network.SupabaseApi;
import io.github.jan.supabase.plugins.SupabasePlugin;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0094\u0001\b\u0007\u0012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\u0002\u00125\b\u0002\u0010\u000e\u001a/\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\b\u0012\u001b\b\u0002\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0002¢\u0006\u0002\b\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0016\u0010\u0017J1\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00032\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0002¢\u0006\u0002\b\u0011H\u0096@¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001a\u001a\u00020\t2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0002¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0004\b\u001a\u0010\u001cJ1\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00032\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0002¢\u0006\u0002\b\u0011H\u0096@¢\u0006\u0004\b\u001e\u0010\u001bR'\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0002¢\u0006\u0002\b\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u001fR\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010 ¨\u0006!"}, d2 = {"Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "Lio/github/jan/supabase/network/SupabaseApi;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "path", "resolveUrl", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "response", "Ld7/d;", "Lio/github/jan/supabase/exceptions/RestException;", "", "parseErrorResponse", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "defaultRequest", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "jwtToken", "<init>", "(Lr7/l;Lr7/p;Lr7/l;Lio/github/jan/supabase/SupabaseClient;Ljava/lang/String;)V", "url", "builder", "rawRequest", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "Lr7/l;", "Ljava/lang/String;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AuthenticatedSupabaseApi extends SupabaseApi {
    private final l<HttpRequestBuilder, t0> defaultRequest;
    private final String jwtToken;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi$prepareRequest$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass2 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            String strCurrentAccessTokenOrNull = AuthenticatedSupabaseApi.this.jwtToken;
            if (strCurrentAccessTokenOrNull == null) {
                SupabasePlugin supabasePlugin = AuthenticatedSupabaseApi.this.getSupabaseClient().getPluginManager().getInstalledPlugins().get(Auth.INSTANCE.getKey());
                if (!(supabasePlugin instanceof Auth)) {
                    supabasePlugin = null;
                }
                Auth auth = (Auth) supabasePlugin;
                strCurrentAccessTokenOrNull = auth != null ? auth.currentAccessTokenOrNull() : null;
                if (strCurrentAccessTokenOrNull == null) {
                    strCurrentAccessTokenOrNull = AuthenticatedSupabaseApi.this.getSupabaseClient().getSupabaseKey();
                }
            }
            io.ktor.client.request.UtilsKt.bearerAuth(httpRequestBuilder, strCurrentAccessTokenOrNull);
            this.$builder.invoke(httpRequestBuilder);
            l lVar = AuthenticatedSupabaseApi.this.defaultRequest;
            if (lVar != null) {
                lVar.invoke(httpRequestBuilder);
            }
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi$rawRequest$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C16932 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C16932(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$builder = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            String strCurrentAccessTokenOrNull = AuthenticatedSupabaseApi.this.jwtToken;
            if (strCurrentAccessTokenOrNull == null) {
                SupabasePlugin supabasePlugin = AuthenticatedSupabaseApi.this.getSupabaseClient().getPluginManager().getInstalledPlugins().get(Auth.INSTANCE.getKey());
                if (!(supabasePlugin instanceof Auth)) {
                    supabasePlugin = null;
                }
                Auth auth = (Auth) supabasePlugin;
                strCurrentAccessTokenOrNull = auth != null ? auth.currentAccessTokenOrNull() : null;
                if (strCurrentAccessTokenOrNull == null) {
                    strCurrentAccessTokenOrNull = AuthenticatedSupabaseApi.this.getSupabaseClient().getSupabaseKey();
                }
            }
            io.ktor.client.request.UtilsKt.bearerAuth(httpRequestBuilder, strCurrentAccessTokenOrNull);
            this.$builder.invoke(httpRequestBuilder);
            l lVar = AuthenticatedSupabaseApi.this.defaultRequest;
            if (lVar != null) {
                lVar.invoke(httpRequestBuilder);
            }
        }
    }

    public /* synthetic */ AuthenticatedSupabaseApi(l lVar, p pVar, l lVar2, SupabaseClient supabaseClient, String str, int i10, h hVar) {
        this(lVar, (i10 & 2) != 0 ? null : pVar, (i10 & 4) != 0 ? null : lVar2, supabaseClient, (i10 & 16) != 0 ? null : str);
    }

    @Override // io.github.jan.supabase.network.SupabaseApi, io.github.jan.supabase.network.SupabaseHttpClient
    public Object prepareRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return super.prepareRequest(str, new AnonymousClass2(lVar), dVar);
    }

    @Override // io.github.jan.supabase.network.SupabaseApi
    public Object rawRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return super.rawRequest(str, new C16932(lVar), dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SupabaseInternal
    public AuthenticatedSupabaseApi(l<? super String, String> lVar, p<? super HttpResponse, ? super d<? super RestException>, ? extends Object> pVar, l<? super HttpRequestBuilder, t0> lVar2, SupabaseClient supabaseClient, String str) {
        super(lVar, pVar, supabaseClient);
        this.defaultRequest = lVar2;
        this.jwtToken = str;
    }

    public final Object rawRequest(l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return rawRequest("", lVar, dVar);
    }
}
