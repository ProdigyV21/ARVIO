package io.github.jan.supabase.gotrue;

import a0.c;
import d7.d;
import f7.i;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.exceptions.RestException;
import io.github.jan.supabase.plugins.MainPlugin;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aR\u0010\r\u001a\u00020\f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000125\b\u0002\u0010\u000b\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a<\u0010\r\u001a\u00020\f*\u00020\u00002\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\u001b\b\u0002\u0010\u0015\u001a\u0015\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\r\u0010\u0016\u001a\u0096\u0001\u0010\r\u001a\u00020\f*\u00020\u00002!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u001125\b\u0002\u0010\u000b\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\u001b\b\u0002\u0010\u0015\u001a\u0015\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\u0002\b\u00142\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\r\u0010\u001a¨\u0006\u001b"}, d2 = {"Lio/github/jan/supabase/SupabaseClient;", "", "baseUrl", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/y;", ContentDisposition.Parameters.Name, "response", "Ld7/d;", "Lio/github/jan/supabase/exceptions/RestException;", "", "parseErrorResponse", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "authenticatedSupabaseApi", "(Lio/github/jan/supabase/SupabaseClient;Ljava/lang/String;Lr7/p;)Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "Lio/github/jan/supabase/plugins/MainPlugin;", "plugin", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "defaultRequest", "(Lio/github/jan/supabase/SupabaseClient;Lio/github/jan/supabase/plugins/MainPlugin;Lr7/l;)Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "path", "resolveUrl", "jwtToken", "(Lio/github/jan/supabase/SupabaseClient;Lr7/l;Lr7/p;Lr7/l;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AuthenticatedSupabaseApiKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthenticatedSupabaseApiKt$authenticatedSupabaseApi$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "it", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<String, String> {
        final /* synthetic */ String $baseUrl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(1);
            this.$baseUrl = str;
        }

        @Override // r7.l
        public final String invoke(String str) {
            return c.p(new StringBuilder(), this.$baseUrl, str);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthenticatedSupabaseApiKt$authenticatedSupabaseApi$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass2 extends m implements l<String, String> {
        public AnonymousClass2(Object obj) {
            super(1, 0, MainPlugin.class, obj, "resolveUrl", "resolveUrl(Ljava/lang/String;)Ljava/lang/String;");
        }

        @Override // r7.l
        public final String invoke(String str) {
            return ((MainPlugin) this.receiver).resolveUrl(str);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthenticatedSupabaseApiKt$authenticatedSupabaseApi$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass3 extends m implements p<HttpResponse, d<? super RestException>, Object>, i {
        public AnonymousClass3(Object obj) {
            super(2, 0, MainPlugin.class, obj, "parseErrorResponse", "parseErrorResponse(Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;");
        }

        @Override // r7.p
        public final Object invoke(HttpResponse httpResponse, d<? super RestException> dVar) {
            return ((MainPlugin) this.receiver).parseErrorResponse(httpResponse, dVar);
        }
    }

    @SupabaseInternal
    public static final AuthenticatedSupabaseApi authenticatedSupabaseApi(SupabaseClient supabaseClient, String str, p<? super HttpResponse, ? super d<? super RestException>, ? extends Object> pVar) {
        return authenticatedSupabaseApi$default(supabaseClient, new AnonymousClass1(str), pVar, null, null, 12, null);
    }

    public static /* synthetic */ AuthenticatedSupabaseApi authenticatedSupabaseApi$default(SupabaseClient supabaseClient, String str, p pVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        return authenticatedSupabaseApi(supabaseClient, str, (p<? super HttpResponse, ? super d<? super RestException>, ? extends Object>) pVar);
    }

    @SupabaseInternal
    public static final AuthenticatedSupabaseApi authenticatedSupabaseApi(SupabaseClient supabaseClient, MainPlugin<?> mainPlugin, l<? super HttpRequestBuilder, t0> lVar) {
        return authenticatedSupabaseApi(supabaseClient, new AnonymousClass2(mainPlugin), new AnonymousClass3(mainPlugin), lVar, mainPlugin.getConfig().getJwtToken());
    }

    public static /* synthetic */ AuthenticatedSupabaseApi authenticatedSupabaseApi$default(SupabaseClient supabaseClient, MainPlugin mainPlugin, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        return authenticatedSupabaseApi(supabaseClient, (MainPlugin<?>) mainPlugin, (l<? super HttpRequestBuilder, t0>) lVar);
    }

    @SupabaseInternal
    public static final AuthenticatedSupabaseApi authenticatedSupabaseApi(SupabaseClient supabaseClient, l<? super String, String> lVar, p<? super HttpResponse, ? super d<? super RestException>, ? extends Object> pVar, l<? super HttpRequestBuilder, t0> lVar2, String str) {
        return new AuthenticatedSupabaseApi(lVar, pVar, lVar2, supabaseClient, str);
    }

    public static /* synthetic */ AuthenticatedSupabaseApi authenticatedSupabaseApi$default(SupabaseClient supabaseClient, l lVar, p pVar, l lVar2, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        if ((i10 & 4) != 0) {
            lVar2 = null;
        }
        if ((i10 & 8) != 0) {
            str = null;
        }
        return authenticatedSupabaseApi(supabaseClient, lVar, pVar, lVar2, str);
    }
}
