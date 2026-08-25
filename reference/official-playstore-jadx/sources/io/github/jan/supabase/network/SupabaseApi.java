package io.github.jan.supabase.network;

import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.exceptions.RestException;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001Bk\b\u0007\u0012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\u0002\u00125\b\u0002\u0010\u000e\u001a/\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J1\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0002¢\u0006\u0002\b\u0016H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J1\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0002¢\u0006\u0002\b\u0016H\u0096@¢\u0006\u0004\b\u001a\u0010\u0019J1\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0002¢\u0006\u0002\b\u0016H\u0096@¢\u0006\u0004\b\u001c\u0010\u0019J)\u0010\u001c\u001a\u00020\u001b2\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0002¢\u0006\u0002\b\u0016H\u0086@¢\u0006\u0004\b\u001c\u0010\u001dR/\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001eRA\u0010\u000e\u001a/\b\u0001\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u001fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010 \u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lio/github/jan/supabase/network/SupabaseApi;", "Lio/github/jan/supabase/network/SupabaseHttpClient;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "path", "resolveUrl", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "response", "Ld7/d;", "Lio/github/jan/supabase/exceptions/RestException;", "", "parseErrorResponse", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "<init>", "(Lr7/l;Lr7/p;Lio/github/jan/supabase/SupabaseClient;)V", "url", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "request", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "rawRequest", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lr7/l;", "Lr7/p;", "Lio/github/jan/supabase/SupabaseClient;", "getSupabaseClient", "()Lio/github/jan/supabase/SupabaseClient;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class SupabaseApi extends SupabaseHttpClient {
    private final p<HttpResponse, d<? super RestException>, Object> parseErrorResponse;
    private final l<String, String> resolveUrl;
    private final SupabaseClient supabaseClient;

    /* JADX INFO: renamed from: io.github.jan.supabase.network.SupabaseApi$rawRequest$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.network.SupabaseApi", f = "SupabaseApi.kt", l = {24, 25}, m = "rawRequest$suspendImpl")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SupabaseApi.rawRequest$suspendImpl(SupabaseApi.this, null, null, this);
        }
    }

    public /* synthetic */ SupabaseApi(l lVar, p pVar, SupabaseClient supabaseClient, int i10, h hVar) {
        this(lVar, (i10 & 2) != 0 ? null : pVar, supabaseClient);
    }

    public static /* synthetic */ Object prepareRequest$suspendImpl(SupabaseApi supabaseApi, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return supabaseApi.supabaseClient.getHttpClient().prepareRequest((String) supabaseApi.resolveUrl.invoke(str), lVar, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0068, code lost:
    
        if (r8 == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object rawRequest$suspendImpl(io.github.jan.supabase.network.SupabaseApi r5, java.lang.String r6, r7.l<? super io.ktor.client.request.HttpRequestBuilder, x6.t0> r7, d7.d<? super io.ktor.client.statement.HttpResponse> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof io.github.jan.supabase.network.SupabaseApi.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.network.SupabaseApi$rawRequest$1 r0 = (io.github.jan.supabase.network.SupabaseApi.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.network.SupabaseApi$rawRequest$1 r0 = new io.github.jan.supabase.network.SupabaseApi$rawRequest$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 == r2) goto L2e
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2e:
            k2.c.G(r8)
            goto L6b
        L32:
            java.lang.Object r5 = r0.L$0
            io.github.jan.supabase.network.SupabaseApi r5 = (io.github.jan.supabase.network.SupabaseApi) r5
            k2.c.G(r8)
            goto L4e
        L3a:
            k2.c.G(r8)
            io.github.jan.supabase.SupabaseClient r8 = r5.supabaseClient
            io.github.jan.supabase.network.KtorSupabaseHttpClient r8 = r8.getHttpClient()
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = r8.request(r6, r7, r0)
            if (r8 != r4) goto L4e
            goto L6a
        L4e:
            r6 = r8
            io.ktor.client.statement.HttpResponse r6 = (io.ktor.client.statement.HttpResponse) r6
            io.ktor.http.HttpStatusCode r7 = r6.getStatus()
            boolean r7 = io.ktor.http.HttpStatusCodeKt.isSuccess(r7)
            if (r7 != 0) goto L6e
            r7.p<io.ktor.client.statement.HttpResponse, d7.d<? super io.github.jan.supabase.exceptions.RestException>, java.lang.Object> r5 = r5.parseErrorResponse
            if (r5 == 0) goto L6e
            r7 = 0
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r8 = r5.invoke(r6, r0)
            if (r8 != r4) goto L6b
        L6a:
            return r4
        L6b:
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L6e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.network.SupabaseApi.rawRequest$suspendImpl(io.github.jan.supabase.network.SupabaseApi, java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    public final SupabaseClient getSupabaseClient() {
        return this.supabaseClient;
    }

    @Override // io.github.jan.supabase.network.SupabaseHttpClient
    public Object prepareRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return prepareRequest$suspendImpl(this, str, lVar, dVar);
    }

    public Object rawRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return rawRequest$suspendImpl(this, str, lVar, dVar);
    }

    @Override // io.github.jan.supabase.network.SupabaseHttpClient
    public final Object request(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return rawRequest((String) this.resolveUrl.invoke(str), lVar, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SupabaseInternal
    public SupabaseApi(l<? super String, String> lVar, p<? super HttpResponse, ? super d<? super RestException>, ? extends Object> pVar, SupabaseClient supabaseClient) {
        this.resolveUrl = lVar;
        this.parseErrorResponse = pVar;
        this.supabaseClient = supabaseClient;
    }

    public final Object prepareRequest(l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return prepareRequest("", lVar, dVar);
    }
}
