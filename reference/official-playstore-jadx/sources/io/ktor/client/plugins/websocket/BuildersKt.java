package io.ktor.client.plugins.websocket;

import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.websocket.WebSockets;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.request.UtilsKt;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLProtocol;
import ka.k0;
import ka.m0;
import ka.s;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u001a.\u0010\u0006\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a0\u0010\f\u001a\u00020\u000b*\u00020\b2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a`\u0010\f\u001a\u00020\u000b*\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0015\u001a:\u0010\f\u001a\u00020\u000b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00102\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0017\u001aY\u0010\u001c\u001a\u00020\u0003*\u00020\b2\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0089\u0001\u0010\u001c\u001a\u00020\u0003*\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001e\u001ac\u0010\u001c\u001a\u00020\u0003*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001f\u001a\u0089\u0001\u0010 \u001a\u00020\u0003*\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b \u0010\u001e\u001aY\u0010 \u001a\u00020\u0003*\u00020\b2\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b \u0010\u001d\u001ac\u0010 \u001a\u00020\u0003*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b \u0010\u001f\u001aY\u0010!\u001a\u00020\u0003*\u00020\b2\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001d\u001ac\u0010!\u001a\u00020\u0003*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001f\u001a\u0089\u0001\u0010!\u001a\u00020\u0003*\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¢\u0006\u0002\b\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lkotlin/Function1;", "Lio/ktor/client/plugins/websocket/WebSockets$Config;", "Lx6/t0;", "Lx6/n;", "config", "WebSockets", "(Lio/ktor/client/HttpClientConfig;Lr7/l;)V", "Lio/ktor/client/HttpClient;", "Lio/ktor/client/request/HttpRequestBuilder;", "block", "Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "webSocketSession", "(Lio/ktor/client/HttpClient;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/HttpMethod;", "method", "", "host", "", "port", "path", "(Lio/ktor/client/HttpClient;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "urlString", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "request", "Lkotlin/Function2;", "Ld7/d;", "", "webSocket", "(Lio/ktor/client/HttpClient;Lr7/l;Lr7/p;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;Lr7/p;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lr7/l;Lr7/p;Ld7/d;)Ljava/lang/Object;", "ws", "wss", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuildersKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$WebSockets$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/websocket/WebSockets$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/websocket/WebSockets$Config;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<WebSockets.Config, t0> {
        final /* synthetic */ l<WebSockets.Config, t0> $config;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super WebSockets.Config, t0> lVar) {
            super(1);
            this.$config = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((WebSockets.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(WebSockets.Config config) {
            this.$config.invoke(config);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocket$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.websocket.BuildersKt", f = "builders.kt", l = {241, 244, 101, MediaError.DetailedErrorCode.MEDIA_NETWORK, MediaError.DetailedErrorCode.MEDIA_NETWORK, AnimationConstants.DURATION_IMAGE_CROSSFADE, AnimationConstants.DURATION_IMAGE_CROSSFADE}, m = "webSocket")
    public static final class C17451 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C17451(d<? super C17451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BuildersKt.webSocket(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocket$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocket$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ String $host;
        final /* synthetic */ HttpMethod $method;
        final /* synthetic */ String $path;
        final /* synthetic */ Integer $port;
        final /* synthetic */ l<HttpRequestBuilder, t0> $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$method = httpMethod;
            this.$host = str;
            this.$port = num;
            this.$path = str2;
            this.$request = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(this.$method);
            HttpRequestKt.url$default(httpRequestBuilder, "ws", this.$host, this.$port, this.$path, null, 16, null);
            this.$request.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocket$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass7 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass7 INSTANCE = new AnonymousClass7();

        public AnonymousClass7() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocket$8, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass8 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $request;
        final /* synthetic */ String $urlString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(String str, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$urlString = str;
            this.$request = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.getUrl().setProtocol(URLProtocol.INSTANCE.getWS());
            httpRequestBuilder.getUrl().setPort(UtilsKt.getPort(httpRequestBuilder));
            URLParserKt.takeFrom(httpRequestBuilder.getUrl(), this.$urlString);
            this.$request.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$2", f = "builders.kt", l = {239, 242, 49, 248, 248}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ka.r<DefaultClientWebSocketSession> $sessionDeferred;
        final /* synthetic */ HttpStatement $statement;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(HttpStatement httpStatement, ka.r<DefaultClientWebSocketSession> rVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$statement = httpStatement;
            this.$sessionDeferred = rVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$statement, this.$sessionDeferred, dVar);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:68|(1:(2:(1:(1:(1:(2:9|10)(3:11|12|63))(3:18|19|20))(5:21|73|22|50|(1:62)(1:53)))(6:26|69|27|28|75|(3:46|(3:49|50|(0))|62)(2:56|57))|60)(3:31|32|33))(4:34|35|36|(2:38|62)(1:39))|40|71|41|(3:44|75|(0)(0))|62) */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00f9, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00fa, code lost:
        
            r2 = r8;
            r6 = r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0108, code lost:
        
            if (r6.cleanup(r2, r17) != r10) goto L63;
         */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00b9 A[Catch: all -> 0x00ed, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00ed, blocks: (B:46:0x00b9, B:56:0x00f1, B:57:0x00f8), top: B:75:0x00b7 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00ec A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:56:0x00f1 A[Catch: all -> 0x00ed, TRY_ENTER, TryCatch #4 {all -> 0x00ed, blocks: (B:46:0x00b9, B:56:0x00f1, B:57:0x00f8), top: B:75:0x00b7 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 279
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.websocket.BuildersKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17464 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17464 INSTANCE = new C17464();

        public C17464() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17475 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $block;
        final /* synthetic */ String $host;
        final /* synthetic */ HttpMethod $method;
        final /* synthetic */ String $path;
        final /* synthetic */ Integer $port;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17475(HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$method = httpMethod;
            this.$host = str;
            this.$port = num;
            this.$path = str2;
            this.$block = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.setMethod(this.$method);
            HttpRequestKt.url$default(httpRequestBuilder, "ws", this.$host, this.$port, this.$path, null, 16, null);
            this.$block.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17487 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17487 INSTANCE = new C17487();

        public C17487() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$webSocketSession$8, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17498 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $block;
        final /* synthetic */ String $urlString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17498(String str, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$urlString = str;
            this.$block = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            URLParserKt.takeFrom(httpRequestBuilder.getUrl(), this.$urlString);
            this.$block.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$ws$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17502 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17502 INSTANCE = new C17502();

        public C17502() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$ws$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17515 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17515 INSTANCE = new C17515();

        public C17515() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$wss$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17522 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17522(l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$request = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.getUrl().setProtocol(URLProtocol.INSTANCE.getWSS());
            httpRequestBuilder.getUrl().setPort(httpRequestBuilder.getUrl().getProtocol().getDefaultPort());
            this.$request.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$wss$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17534 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17534 INSTANCE = new C17534();

        public C17534() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$wss$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17545 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ l<HttpRequestBuilder, t0> $request;
        final /* synthetic */ String $urlString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17545(String str, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$urlString = str;
            this.$request = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            URLParserKt.takeFrom(httpRequestBuilder.getUrl(), this.$urlString);
            this.$request.invoke(httpRequestBuilder);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$wss$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17557 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17557 INSTANCE = new C17557();

        public C17557() {
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

    /* JADX INFO: renamed from: io.ktor.client.plugins.websocket.BuildersKt$wss$8, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17568 extends r implements l<HttpRequestBuilder, t0> {
        final /* synthetic */ Integer $port;
        final /* synthetic */ l<HttpRequestBuilder, t0> $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C17568(Integer num, l<? super HttpRequestBuilder, t0> lVar) {
            super(1);
            this.$port = num;
            this.$request = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.getUrl().setProtocol(URLProtocol.INSTANCE.getWSS());
            if (this.$port != null) {
                httpRequestBuilder.getUrl().setPort(this.$port.intValue());
            }
            this.$request.invoke(httpRequestBuilder);
        }
    }

    public static final void WebSockets(HttpClientConfig<?> httpClientConfig, l<? super WebSockets.Config, t0> lVar) {
        httpClientConfig.install(WebSockets.INSTANCE, new AnonymousClass1(lVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x014e, code lost:
    
        if (r12.cleanup(r11, r1) != r6) goto L72;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f1 A[Catch: all -> 0x0051, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:19:0x004c, B:64:0x0134, B:24:0x005e, B:53:0x0104, B:61:0x0124, B:32:0x0088, B:48:0x00f1, B:65:0x0135, B:66:0x013c), top: B:76:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0114 A[Catch: CancellationException -> 0x0152, PHI: r11 r12
      0x0114: PHI (r11v14 ??) = (r11v19 ??), (r11v20 ??) binds: [B:54:0x0111, B:24:0x005e] A[DONT_GENERATE, DONT_INLINE]
      0x0114: PHI (r12v15 ??) = (r12v20 ??), (r12v21 ??) binds: [B:54:0x0111, B:24:0x005e] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x0152, blocks: (B:13:0x0033, B:72:0x0151, B:16:0x003c, B:69:0x0141, B:56:0x0114, B:35:0x0095, B:42:0x00c2, B:38:0x00b3), top: B:76:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0135 A[Catch: all -> 0x0051, TryCatch #0 {all -> 0x0051, blocks: (B:19:0x004c, B:64:0x0134, B:24:0x005e, B:53:0x0104, B:61:0x0124, B:32:0x0088, B:48:0x00f1, B:65:0x0135, B:66:0x013c), top: B:76:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r10v12, types: [r7.p] */
    /* JADX WARN: Type inference failed for: r10v39 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v0, types: [r7.l, r7.l<? super io.ktor.client.request.HttpRequestBuilder, x6.t0>] */
    /* JADX WARN: Type inference failed for: r11v1, types: [io.ktor.client.statement.HttpResponse] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v13, types: [io.ktor.client.statement.HttpResponse] */
    /* JADX WARN: Type inference failed for: r11v14, types: [io.ktor.client.statement.HttpResponse] */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v5, types: [io.ktor.client.statement.HttpResponse, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, r7.p<? super io.ktor.client.plugins.websocket.DefaultClientWebSocketSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r12v1, types: [io.ktor.client.statement.HttpStatement] */
    /* JADX WARN: Type inference failed for: r12v15, types: [io.ktor.client.statement.HttpStatement] */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object webSocket(io.ktor.client.HttpClient r10, r7.l<? super io.ktor.client.request.HttpRequestBuilder, x6.t0> r11, r7.p<? super io.ktor.client.plugins.websocket.DefaultClientWebSocketSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r12, d7.d<? super x6.t0> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.websocket.BuildersKt.webSocket(io.ktor.client.HttpClient, r7.l, r7.p, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object webSocket$default(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpMethod = HttpMethod.INSTANCE.getGet();
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        l lVar2 = lVar;
        return webSocket(httpClient, httpMethod, str, num, str2, lVar2, pVar, dVar);
    }

    public static final Object webSocketSession(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super DefaultClientWebSocketSession> dVar) {
        HttpClientPluginKt.plugin(httpClient, WebSockets.INSTANCE);
        s sVarB = m0.b();
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        httpRequestBuilder.url(BuildersKt$webSocketSession$statement$1$1.INSTANCE);
        lVar.invoke(httpRequestBuilder);
        m0.p(httpClient, null, 0, new AnonymousClass2(new HttpStatement(httpRequestBuilder, httpClient), sVarB, null), 3);
        return sVarB.j(dVar);
    }

    public static /* synthetic */ Object webSocketSession$default(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpMethod = HttpMethod.INSTANCE.getGet();
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = C17464.INSTANCE;
        }
        return webSocketSession(httpClient, httpMethod, str, num, str2, lVar, dVar);
    }

    public static final Object ws(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, httpMethod, str, num, str2, lVar, pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static /* synthetic */ Object ws$default(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpMethod = HttpMethod.INSTANCE.getGet();
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = C17502.INSTANCE;
        }
        l lVar2 = lVar;
        return ws(httpClient, httpMethod, str, num, str2, lVar2, pVar, dVar);
    }

    public static final Object wss(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, new C17522(lVar), pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static /* synthetic */ Object wss$default(HttpClient httpClient, String str, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17534.INSTANCE;
        }
        return wss(httpClient, str, lVar, pVar, dVar);
    }

    public static final Object ws(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, lVar, pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static final Object wss(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWss = wss(httpClient, new C17545(str, lVar), pVar, dVar);
        return objWss == a.f15033i ? objWss : t0.f22605a;
    }

    public static final Object ws(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, str, lVar, pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static final Object wss(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, httpMethod, str, num, str2, new C17568(num, lVar), pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static /* synthetic */ Object wss$default(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpMethod = HttpMethod.INSTANCE.getGet();
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = C17557.INSTANCE;
        }
        l lVar2 = lVar;
        return wss(httpClient, httpMethod, str, num, str2, lVar2, pVar, dVar);
    }

    public static /* synthetic */ Object webSocket$default(HttpClient httpClient, String str, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass7.INSTANCE;
        }
        return webSocket(httpClient, str, lVar, pVar, dVar);
    }

    public static /* synthetic */ Object webSocketSession$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17487.INSTANCE;
        }
        return webSocketSession(httpClient, str, lVar, dVar);
    }

    public static /* synthetic */ Object ws$default(HttpClient httpClient, String str, l lVar, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17515.INSTANCE;
        }
        return ws(httpClient, str, lVar, pVar, dVar);
    }

    public static final Object webSocketSession(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar, d<? super DefaultClientWebSocketSession> dVar) {
        return webSocketSession(httpClient, new C17475(httpMethod, str, num, str2, lVar), dVar);
    }

    public static final Object webSocketSession(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super DefaultClientWebSocketSession> dVar) {
        return webSocketSession(httpClient, new C17498(str, lVar), dVar);
    }

    public static final Object webSocket(HttpClient httpClient, HttpMethod httpMethod, String str, Integer num, String str2, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, new AnonymousClass5(httpMethod, str, num, str2, lVar), pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }

    public static final Object webSocket(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, p<? super DefaultClientWebSocketSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) throws Throwable {
        Object objWebSocket = webSocket(httpClient, HttpMethod.INSTANCE.getGet(), null, null, null, new AnonymousClass8(str, lVar), pVar, dVar);
        return objWebSocket == a.f15033i ? objWebSocket : t0.f22605a;
    }
}
