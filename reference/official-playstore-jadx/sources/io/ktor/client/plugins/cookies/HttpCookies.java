package io.ktor.client.plugins.cookies;

import androidx.media3.container.NalUnitUtil;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ka.m0;
import ka.n1;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 '2\u00060\u0001j\u0002`\u0002:\u0002'(BC\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012-\u0010\u000b\u001a)\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n0\u0005ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\u000f\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016J\u001b\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010!R>\u0010\u000b\u001a)\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n0\u00058\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u000b\u0010\"R\u001a\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b$\u0010%\u0012\u0004\b&\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lio/ktor/client/plugins/cookies/HttpCookies;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "storage", "", "Lkotlin/Function2;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "defaults", "<init>", "(Lio/ktor/client/plugins/cookies/CookiesStorage;Ljava/util/List;)V", "Lio/ktor/http/Url;", "requestUrl", "Lio/ktor/http/Cookie;", "get", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "captureHeaderCookies$ktor_client_core", "(Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "captureHeaderCookies", "sendCookiesWith$ktor_client_core", "sendCookiesWith", "Lio/ktor/client/statement/HttpResponse;", "response", "saveCookiesFrom$ktor_client_core", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "saveCookiesFrom", "close", "()V", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "Ljava/util/List;", "Lka/v1;", "initializer", "Lka/v1;", "getInitializer$annotations", "Companion", "Config", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpCookies implements Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpCookies> key = new AttributeKey<>("HttpCookies");
    private final List<p<CookiesStorage, d<? super t0>, Object>> defaults;
    private final v1 initializer = m0.p(n1.f19624i, x0.f19654c, 0, new HttpCookies$initializer$1(this, null), 2);
    private final CookiesStorage storage;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/cookies/HttpCookies$Companion;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/cookies/HttpCookies$Config;", "Lio/ktor/client/plugins/cookies/HttpCookies;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/cookies/HttpCookies;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/cookies/HttpCookies;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, HttpCookies> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpCookies> getKey() {
            return HttpCookies.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpCookies plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getState(), new HttpCookies$Companion$install$1(plugin, null));
            scope.getSendPipeline().intercept(HttpSendPipeline.INSTANCE.getState(), new HttpCookies$Companion$install$2(plugin, null));
            scope.getReceivePipeline().intercept(HttpReceivePipeline.INSTANCE.getState(), new HttpCookies$Companion$install$3(plugin, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpCookies prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return config.build$ktor_client_core();
        }
    }

    @KtorDsl
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\n\u001a\u00020\u00072'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u000f\u001a\u00020\fH\u0000¢\u0006\u0004\b\r\u0010\u000eR>\u0010\u0011\u001a)\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\b0\u00108\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/client/plugins/cookies/HttpCookies$Config;", "", "<init>", "()V", "Lkotlin/Function2;", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "block", "default", "(Lr7/p;)V", "Lio/ktor/client/plugins/cookies/HttpCookies;", "build$ktor_client_core", "()Lio/ktor/client/plugins/cookies/HttpCookies;", "build", "", "defaults", "Ljava/util/List;", "storage", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "getStorage", "()Lio/ktor/client/plugins/cookies/CookiesStorage;", "setStorage", "(Lio/ktor/client/plugins/cookies/CookiesStorage;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private final List<p<CookiesStorage, d<? super t0>, Object>> defaults = new ArrayList();
        private CookiesStorage storage = new AcceptAllCookiesStorage();

        public final HttpCookies build$ktor_client_core() {
            return new HttpCookies(this.storage, this.defaults);
        }

        /* JADX INFO: renamed from: default, reason: not valid java name */
        public final void m6669default(p<? super CookiesStorage, ? super d<? super t0>, ? extends Object> block) {
            this.defaults.add(block);
        }

        public final CookiesStorage getStorage() {
            return this.storage;
        }

        public final void setStorage(CookiesStorage cookiesStorage) {
            this.storage = cookiesStorage;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cookies.HttpCookies$get$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cookies.HttpCookies", f = "HttpCookies.kt", l = {NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI, 40}, m = "get")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCookies.this.get(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HttpCookies(CookiesStorage cookiesStorage, List<? extends p<? super CookiesStorage, ? super d<? super t0>, ? extends Object>> list) {
        this.storage = cookiesStorage;
        this.defaults = list;
    }

    private static /* synthetic */ void getInitializer$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object captureHeaderCookies$ktor_client_core(io.ktor.client.request.HttpRequestBuilder r22, d7.d<? super x6.t0> r23) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookies.captureHeaderCookies$ktor_client_core(io.ktor.client.request.HttpRequestBuilder, d7.d):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.storage.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(io.ktor.http.Url r6, d7.d<? super java.util.List<io.ktor.http.Cookie>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.cookies.HttpCookies.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.plugins.cookies.HttpCookies$get$1 r0 = (io.ktor.client.plugins.cookies.HttpCookies.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cookies.HttpCookies$get$1 r0 = new io.ktor.client.plugins.cookies.HttpCookies$get$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            return r7
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$1
            io.ktor.http.Url r6 = (io.ktor.http.Url) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.client.plugins.cookies.HttpCookies r1 = (io.ktor.client.plugins.cookies.HttpCookies) r1
            k2.c.G(r7)
            goto L51
        L3e:
            k2.c.G(r7)
            ka.v1 r7 = r5.initializer
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r7.join(r0)
            if (r7 != r4) goto L50
            goto L60
        L50:
            r1 = r5
        L51:
            io.ktor.client.plugins.cookies.CookiesStorage r7 = r1.storage
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r1
            r0.label = r2
            java.lang.Object r6 = r7.get(r6, r0)
            if (r6 != r4) goto L61
        L60:
            return r4
        L61:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookies.get(io.ktor.http.Url, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveCookiesFrom$ktor_client_core(io.ktor.client.statement.HttpResponse r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.client.plugins.cookies.HttpCookies$saveCookiesFrom$1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.client.plugins.cookies.HttpCookies$saveCookiesFrom$1 r0 = (io.ktor.client.plugins.cookies.HttpCookies$saveCookiesFrom$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cookies.HttpCookies$saveCookiesFrom$1 r0 = new io.ktor.client.plugins.cookies.HttpCookies$saveCookiesFrom$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r8 = r0.L$2
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r1 = r0.L$1
            io.ktor.http.Url r1 = (io.ktor.http.Url) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.client.plugins.cookies.HttpCookies r3 = (io.ktor.client.plugins.cookies.HttpCookies) r3
            k2.c.G(r9)
            goto L91
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            k2.c.G(r9)
            io.ktor.client.request.HttpRequest r9 = io.ktor.client.statement.HttpResponseKt.getRequest(r8)
            io.ktor.http.Url r9 = r9.getUrl()
            io.ktor.http.Headers r1 = r8.getHeaders()
            io.ktor.http.HttpHeaders r3 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r3 = r3.getSetCookie()
            java.util.List r1 = r1.getAll(r3)
            if (r1 == 0) goto L87
            java.util.Iterator r1 = r1.iterator()
        L58:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L87
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            vc.b r4 = io.ktor.client.plugins.cookies.HttpCookiesKt.access$getLOGGER$p()
            java.lang.String r5 = "Received cookie "
            java.lang.String r6 = " in response for "
            java.lang.StringBuilder r3 = a0.c.t(r5, r3, r6)
            io.ktor.client.call.HttpClientCall r5 = r8.getCall()
            io.ktor.client.request.HttpRequest r5 = r5.getRequest()
            io.ktor.http.Url r5 = r5.getUrl()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r4.h(r3)
            goto L58
        L87:
            java.util.List r8 = io.ktor.http.HttpMessagePropertiesKt.setCookie(r8)
            java.util.Iterator r8 = r8.iterator()
            r3 = r7
            r1 = r9
        L91:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto Lb0
            java.lang.Object r9 = r8.next()
            io.ktor.http.Cookie r9 = (io.ktor.http.Cookie) r9
            io.ktor.client.plugins.cookies.CookiesStorage r4 = r3.storage
            r0.L$0 = r3
            r0.L$1 = r1
            r0.L$2 = r8
            r0.label = r2
            java.lang.Object r9 = r4.addCookie(r1, r9, r0)
            e7.a r4 = e7.a.f15033i
            if (r9 != r4) goto L91
            return r4
        Lb0:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookies.saveCookiesFrom$ktor_client_core(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendCookiesWith$ktor_client_core(io.ktor.client.request.HttpRequestBuilder r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.plugins.cookies.HttpCookies$sendCookiesWith$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.plugins.cookies.HttpCookies$sendCookiesWith$1 r0 = (io.ktor.client.plugins.cookies.HttpCookies$sendCookiesWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cookies.HttpCookies$sendCookiesWith$1 r0 = new io.ktor.client.plugins.cookies.HttpCookies$sendCookiesWith$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            io.ktor.client.request.HttpRequestBuilder r5 = (io.ktor.client.request.HttpRequestBuilder) r5
            k2.c.G(r6)
            goto L4d
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            io.ktor.http.URLBuilder r6 = r5.getUrl()
            io.ktor.http.URLBuilder r6 = io.ktor.http.URLBuilderKt.clone(r6)
            io.ktor.http.Url r6 = r6.build()
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.get(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L4d
            return r0
        L4d:
            java.util.List r6 = (java.util.List) r6
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L81
            java.lang.String r6 = io.ktor.client.plugins.cookies.HttpCookiesKt.access$renderClientCookies(r6)
            io.ktor.http.HeadersBuilder r0 = r5.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getCookie()
            r0.set(r1, r6)
            vc.b r0 = io.ktor.client.plugins.cookies.HttpCookiesKt.access$getLOGGER$p()
            java.lang.String r1 = "Sending cookie "
            java.lang.String r2 = " for "
            java.lang.StringBuilder r6 = a0.c.t(r1, r6, r2)
            io.ktor.http.URLBuilder r5 = r5.getUrl()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r0.h(r5)
            goto L8e
        L81:
            io.ktor.http.HeadersBuilder r5 = r5.getHeaders()
            io.ktor.http.HttpHeaders r6 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r6 = r6.getCookie()
            r5.remove(r6)
        L8e:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookies.sendCookiesWith$ktor_client_core(io.ktor.client.request.HttpRequestBuilder, d7.d):java.lang.Object");
    }
}
