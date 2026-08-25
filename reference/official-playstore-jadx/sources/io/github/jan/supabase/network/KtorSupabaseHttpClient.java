package io.github.jan.supabase.network;

import d7.d;
import f7.c;
import f7.e;
import g8.b;
import io.github.jan.supabase.UtilsKt;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.exceptions.HttpRequestException;
import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.HttpClientJvmKt;
import io.ktor.client.HttpClientKt;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.plugins.DefaultRequest;
import io.ktor.client.plugins.HttpRequestTimeoutException;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation;
import io.ktor.client.plugins.websocket.BuildersKt;
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HeadersBuilder;
import io.ktor.serialization.kotlinx.json.JsonSupportKt;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import q2.f;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BJ\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012#\b\u0002\u0010\t\u001a\u001d\u0012\u0019\u0012\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b0\u0004\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ:\u0010\u0010\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00062!\u0010\t\u001a\u001d\u0012\u0019\u0012\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b0\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J1\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00022\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0096@¢\u0006\u0004\b\u0016\u0010\u0017J1\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00022\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0096@¢\u0006\u0004\b\u0019\u0010\u0017J3\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00022\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086@¢\u0006\u0004\b\u001c\u0010\u0017J\r\u0010\u001d\u001a\u00020\u0007¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010 R \u0010\"\u001a\u00020!8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\"\u0010#\u0012\u0004\b&\u0010\u001e\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lio/github/jan/supabase/network/KtorSupabaseHttpClient;", "Lio/github/jan/supabase/network/SupabaseHttpClient;", "", "supabaseKey", "", "Lkotlin/Function1;", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "Lx6/n;", "modifiers", "", "requestTimeout", "Lio/ktor/client/engine/HttpClientEngine;", "engine", "<init>", "(Ljava/lang/String;Ljava/util/List;JLio/ktor/client/engine/HttpClientEngine;)V", "applyDefaultConfiguration", "(Lio/ktor/client/HttpClientConfig;Ljava/util/List;)V", "url", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "Lio/ktor/client/statement/HttpResponse;", "request", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "block", "Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "webSocketSession", "close", "()V", "Ljava/lang/String;", "J", "Lio/ktor/client/HttpClient;", "httpClient", "Lio/ktor/client/HttpClient;", "getHttpClient", "()Lio/ktor/client/HttpClient;", "getHttpClient$annotations", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class KtorSupabaseHttpClient extends SupabaseHttpClient {
    private final HttpClient httpClient;
    private final long requestTimeout;
    private final String supabaseKey;

    /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$applyDefaultConfiguration$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass1 extends r implements l<DefaultRequest.DefaultRequestBuilder, t0> {

        /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$applyDefaultConfiguration$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/HeadersBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/HeadersBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
        public static final class C02281 extends r implements l<HeadersBuilder, t0> {
            final /* synthetic */ KtorSupabaseHttpClient this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02281(KtorSupabaseHttpClient ktorSupabaseHttpClient) {
                super(1);
                this.this$0 = ktorSupabaseHttpClient;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((HeadersBuilder) obj);
                return t0.f22605a;
            }

            public final void invoke(HeadersBuilder headersBuilder) {
                if (!o.h0(this.this$0.supabaseKey)) {
                    headersBuilder.append("apikey", this.this$0.supabaseKey);
                }
                headersBuilder.append("X-Client-Info", "supabase-kt/2.0.4");
            }
        }

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DefaultRequest.DefaultRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(DefaultRequest.DefaultRequestBuilder defaultRequestBuilder) {
            HttpRequestKt.headers(defaultRequestBuilder, new C02281(KtorSupabaseHttpClient.this));
            defaultRequestBuilder.setPort(443);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$applyDefaultConfiguration$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass2 extends r implements l<ContentNegotiation.Config, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ContentNegotiation.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(ContentNegotiation.Config config) {
            JsonSupportKt.json$default(config, UtilsKt.getSupabaseJson(), null, 2, null);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$applyDefaultConfiguration$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass3 extends r implements l<HttpTimeout.HttpTimeoutCapabilityConfiguration, t0> {
        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpTimeout.HttpTimeoutCapabilityConfiguration) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
            httpTimeoutCapabilityConfiguration.setRequestTimeoutMillis(Long.valueOf(KtorSupabaseHttpClient.this.requestTimeout));
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$request$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.network.KtorSupabaseHttpClient", f = "KtorSupabaseHttpClient.kt", l = {109}, m = "request")
    public static final class C17031 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C17031(d<? super C17031> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KtorSupabaseHttpClient.this.request(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.network.KtorSupabaseHttpClient$webSocketSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C17042 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17042 INSTANCE = new C17042();

        public C17042() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    public /* synthetic */ KtorSupabaseHttpClient(String str, List list, long j10, HttpClientEngine httpClientEngine, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? z.f19728i : list, j10, (i10 & 8) != 0 ? null : httpClientEngine);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyDefaultConfiguration(HttpClientConfig<?> httpClientConfig, List<? extends l<? super HttpClientConfig<?>, t0>> list) {
        httpClientConfig.install(DefaultRequest.INSTANCE, new AnonymousClass1());
        httpClientConfig.install(ContentNegotiation.INSTANCE, AnonymousClass2.INSTANCE);
        httpClientConfig.install(HttpTimeout.INSTANCE, new AnonymousClass3());
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((l) it.next()).invoke(httpClientConfig);
        }
    }

    @SupabaseInternal
    public static /* synthetic */ void getHttpClient$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object webSocketSession$default(KtorSupabaseHttpClient ktorSupabaseHttpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17042.INSTANCE;
        }
        return ktorSupabaseHttpClient.webSocketSession(str, lVar, dVar);
    }

    public final void close() throws IOException {
        this.httpClient.close();
    }

    public final HttpClient getHttpClient() {
        return this.httpClient;
    }

    @Override // io.github.jan.supabase.network.SupabaseHttpClient
    public Object prepareRequest(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) throws HttpRequestException, HttpRequestTimeoutException {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        try {
            HttpClient httpClient = this.httpClient;
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
            HttpRequestKt.url(httpRequestBuilder, str);
            lVar.invoke(httpRequestBuilder);
            return new HttpStatement(httpRequestBuilder, httpClient);
        } catch (HttpRequestTimeoutException e5) {
            f.A0(f.f21327l, "Core", new KtorSupabaseHttpClient$prepareRequest$response$1(this));
            throw e5;
        } catch (Exception e6) {
            f.A0(f.f21327l, "Core", new KtorSupabaseHttpClient$prepareRequest$response$2(e6));
            String message = e6.getMessage();
            if (message == null) {
                message = "";
            }
            throw new HttpRequestException(message, httpRequestBuilderG);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.network.SupabaseHttpClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object request(java.lang.String r6, r7.l<? super io.ktor.client.request.HttpRequestBuilder, x6.t0> r7, d7.d<? super io.ktor.client.statement.HttpResponse> r8) throws io.github.jan.supabase.exceptions.HttpRequestException, io.ktor.client.plugins.HttpRequestTimeoutException {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.github.jan.supabase.network.KtorSupabaseHttpClient.C17031
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.network.KtorSupabaseHttpClient$request$1 r0 = (io.github.jan.supabase.network.KtorSupabaseHttpClient.C17031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.network.KtorSupabaseHttpClient$request$1 r0 = new io.github.jan.supabase.network.KtorSupabaseHttpClient$request$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            java.lang.String r2 = "Core"
            r3 = 1
            if (r1 == 0) goto L3b
            if (r1 != r3) goto L33
            java.lang.Object r6 = r0.L$1
            io.ktor.client.request.HttpRequestBuilder r6 = (io.ktor.client.request.HttpRequestBuilder) r6
            java.lang.Object r7 = r0.L$0
            io.github.jan.supabase.network.KtorSupabaseHttpClient r7 = (io.github.jan.supabase.network.KtorSupabaseHttpClient) r7
            k2.c.G(r8)     // Catch: java.lang.Exception -> L2f io.ktor.client.plugins.HttpRequestTimeoutException -> L31
            goto L6b
        L2f:
            r7 = move-exception
            goto L74
        L31:
            r6 = move-exception
            goto L8c
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            k2.c.G(r8)
            io.ktor.client.request.HttpRequestBuilder r8 = new io.ktor.client.request.HttpRequestBuilder
            r8.<init>()
            r7.invoke(r8)
            io.ktor.client.HttpClient r1 = r5.httpClient     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            io.ktor.client.request.HttpRequestBuilder r4 = new io.ktor.client.request.HttpRequestBuilder     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r4.<init>()     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            io.ktor.client.request.HttpRequestKt.url(r4, r6)     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r7.invoke(r4)     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            io.ktor.client.statement.HttpStatement r6 = new io.ktor.client.statement.HttpStatement     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r6.<init>(r4, r1)     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r0.L$0 = r5     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r0.L$1 = r8     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            r0.label = r3     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            java.lang.Object r6 = r6.execute(r0)     // Catch: java.lang.Exception -> L6e io.ktor.client.plugins.HttpRequestTimeoutException -> L71
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L67
            return r7
        L67:
            r7 = r8
            r8 = r6
            r6 = r7
            r7 = r5
        L6b:
            io.ktor.client.statement.HttpResponse r8 = (io.ktor.client.statement.HttpResponse) r8     // Catch: java.lang.Exception -> L2f io.ktor.client.plugins.HttpRequestTimeoutException -> L31
            return r8
        L6e:
            r7 = move-exception
            r6 = r8
            goto L74
        L71:
            r6 = move-exception
            r7 = r5
            goto L8c
        L74:
            q2.f r8 = q2.f.f21327l
            io.github.jan.supabase.network.KtorSupabaseHttpClient$request$response$2 r0 = new io.github.jan.supabase.network.KtorSupabaseHttpClient$request$response$2
            r0.<init>(r7)
            q2.f.A0(r8, r2, r0)
            io.github.jan.supabase.exceptions.HttpRequestException r8 = new io.github.jan.supabase.exceptions.HttpRequestException
            java.lang.String r7 = r7.getMessage()
            if (r7 != 0) goto L88
            java.lang.String r7 = ""
        L88:
            r8.<init>(r7, r6)
            throw r8
        L8c:
            q2.f r8 = q2.f.f21327l
            io.github.jan.supabase.network.KtorSupabaseHttpClient$request$response$1 r0 = new io.github.jan.supabase.network.KtorSupabaseHttpClient$request$response$1
            r0.<init>(r7)
            q2.f.A0(r8, r2, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.network.KtorSupabaseHttpClient.request(java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    public final Object webSocketSession(String str, l<? super HttpRequestBuilder, t0> lVar, d<? super DefaultClientWebSocketSession> dVar) {
        return BuildersKt.webSocketSession(this.httpClient, str, lVar, dVar);
    }

    @SupabaseInternal
    public KtorSupabaseHttpClient(String str, List<? extends l<? super HttpClientConfig<?>, t0>> list, long j10, HttpClientEngine httpClientEngine) {
        HttpClient HttpClient;
        this.supabaseKey = str;
        this.requestTimeout = j10;
        if (httpClientEngine != null) {
            HttpClient = HttpClientKt.HttpClient(httpClientEngine, new KtorSupabaseHttpClient$httpClient$1(this, list));
        } else {
            HttpClient = HttpClientJvmKt.HttpClient(new KtorSupabaseHttpClient$httpClient$2(this, list));
        }
        this.httpClient = HttpClient;
    }
}
