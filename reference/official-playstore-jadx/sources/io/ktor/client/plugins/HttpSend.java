package io.ktor.client.plugins;

import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.q;
import r7.r;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\t\u0018\u0000 \u00182\u00020\u0001:\u0004\u0019\u001a\u001b\u0018B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JG\u0010\u000e\u001a\u00020\r23\u0010\f\u001a/\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¢\u0006\u0002\b\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ?\u0010\u000e\u001a\u00020\r2-\u0010\f\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010¢\u0006\u0002\b\u000bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012RJ\u0010\u0014\u001a/\u0012+\u0012)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010¢\u0006\u0002\b\u000b0\u00138\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u0012\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/client/plugins/HttpSend;", "", "", "maxSendCount", "<init>", "(I)V", "Lkotlin/Function4;", "Lio/ktor/client/plugins/Sender;", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/request/HttpRequestBuilder;", "Ld7/d;", "Lx6/n;", "block", "Lx6/t0;", "intercept", "(Lr7/r;)V", "Lkotlin/Function3;", "(Lr7/q;)V", "I", "", "interceptors", "Ljava/util/List;", "getInterceptors$annotations", "()V", "Plugin", "Config", "DefaultSender", "InterceptedSender", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpSend {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpSend> key = new AttributeKey<>("HttpSend");
    private final List<q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object>> interceptors;
    private final int maxSendCount;

    @KtorDsl
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/client/plugins/HttpSend$Config;", "", "()V", "maxSendCount", "", "getMaxSendCount", "()I", "setMaxSendCount", "(I)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private int maxSendCount = 20;

        public final int getMaxSendCount() {
            return this.maxSendCount;
        }

        public final void setMaxSendCount(int i10) {
            this.maxSendCount = i10;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/HttpSend$DefaultSender;", "Lio/ktor/client/plugins/Sender;", "", "maxSendCount", "Lio/ktor/client/HttpClient;", "client", "<init>", "(ILio/ktor/client/HttpClient;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "requestBuilder", "Lio/ktor/client/call/HttpClientCall;", "execute", "(Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "I", "Lio/ktor/client/HttpClient;", "sentCount", "currentCall", "Lio/ktor/client/call/HttpClientCall;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultSender implements Sender {
        private final HttpClient client;
        private HttpClientCall currentCall;
        private final int maxSendCount;
        private int sentCount;

        public DefaultSender(int i10, HttpClient httpClient) {
            this.maxSendCount = i10;
            this.client = httpClient;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // io.ktor.client.plugins.Sender
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object execute(io.ktor.client.request.HttpRequestBuilder r5, d7.d<? super io.ktor.client.call.HttpClientCall> r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof io.ktor.client.plugins.HttpSend$DefaultSender$execute$1
                if (r0 == 0) goto L13
                r0 = r6
                io.ktor.client.plugins.HttpSend$DefaultSender$execute$1 r0 = (io.ktor.client.plugins.HttpSend$DefaultSender$execute$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                io.ktor.client.plugins.HttpSend$DefaultSender$execute$1 r0 = new io.ktor.client.plugins.HttpSend$DefaultSender$execute$1
                r0.<init>(r4, r6)
            L18:
                java.lang.Object r6 = r0.result
                int r1 = r0.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L32
                if (r1 != r3) goto L2a
                java.lang.Object r5 = r0.L$0
                io.ktor.client.plugins.HttpSend$DefaultSender r5 = (io.ktor.client.plugins.HttpSend.DefaultSender) r5
                k2.c.G(r6)
                goto L5d
            L2a:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L32:
                k2.c.G(r6)
                io.ktor.client.call.HttpClientCall r6 = r4.currentCall
                if (r6 == 0) goto L3c
                ka.l0.b(r6, r2)
            L3c:
                int r6 = r4.sentCount
                int r1 = r4.maxSendCount
                if (r6 >= r1) goto L81
                int r6 = r6 + r3
                r4.sentCount = r6
                io.ktor.client.HttpClient r6 = r4.client
                io.ktor.client.request.HttpSendPipeline r6 = r6.getSendPipeline()
                java.lang.Object r1 = r5.getBody()
                r0.L$0 = r4
                r0.label = r3
                java.lang.Object r6 = r6.execute(r5, r1, r0)
                e7.a r5 = e7.a.f15033i
                if (r6 != r5) goto L5c
                return r5
            L5c:
                r5 = r4
            L5d:
                boolean r0 = r6 instanceof io.ktor.client.call.HttpClientCall
                if (r0 == 0) goto L64
                r2 = r6
                io.ktor.client.call.HttpClientCall r2 = (io.ktor.client.call.HttpClientCall) r2
            L64:
                if (r2 == 0) goto L69
                r5.currentCall = r2
                return r2
            L69:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Failed to execute send pipeline. Expected [HttpClientCall], but received "
                r0.<init>(r1)
                r0.append(r6)
                java.lang.String r6 = r0.toString()
                java.lang.String r6 = r6.toString()
                r5.<init>(r6)
                throw r5
            L81:
                io.ktor.client.plugins.SendCountExceedException r5 = new io.ktor.client.plugins.SendCountExceedException
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r0 = "Max send count "
                r6.<init>(r0)
                int r0 = r4.maxSendCount
                java.lang.String r1 = " exceeded. Consider increasing the property maxSendCount if more is required."
                java.lang.String r6 = androidx.compose.material3.d.j(r0, r1, r6)
                r5.<init>(r6)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpSend.DefaultSender.execute(io.ktor.client.request.HttpRequestBuilder, d7.d):java.lang.Object");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001BA\u0012-\u0010\b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007\u0012\u0006\u0010\t\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR>\u0010\b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u00078\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\b\u0010\u000fR\u0014\u0010\t\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/HttpSend$InterceptedSender;", "Lio/ktor/client/plugins/Sender;", "Lkotlin/Function3;", "Lio/ktor/client/request/HttpRequestBuilder;", "Ld7/d;", "Lio/ktor/client/call/HttpClientCall;", "", "Lx6/n;", "interceptor", "nextSender", "<init>", "(Lr7/q;Lio/ktor/client/plugins/Sender;)V", "requestBuilder", "execute", "(Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "Lr7/q;", "Lio/ktor/client/plugins/Sender;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InterceptedSender implements Sender {
        private final q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> interceptor;
        private final Sender nextSender;

        /* JADX WARN: Multi-variable type inference failed */
        public InterceptedSender(q<? super Sender, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object> qVar, Sender sender) {
            this.interceptor = qVar;
            this.nextSender = sender;
        }

        @Override // io.ktor.client.plugins.Sender
        public Object execute(HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
            return this.interceptor.invoke(this.nextSender, httpRequestBuilder, dVar);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpSend$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/HttpSend$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpSend$Config;", "Lio/ktor/client/plugins/HttpSend;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpSend;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpSend;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, HttpSend> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpSend> getKey() {
            return HttpSend.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpSend plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getSend(), new HttpSend$Plugin$install$1(plugin, scope, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpSend prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return new HttpSend(config.getMaxSendCount(), null);
        }
    }

    public /* synthetic */ HttpSend(int i10, h hVar) {
        this(i10);
    }

    private static /* synthetic */ void getInterceptors$annotations() {
    }

    @e
    public final void intercept(r<? super Sender, ? super HttpClientCall, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object> block) {
        throw new IllegalStateException("This interceptors do not allow to intercept original call. Please use another overload and call `this.execute(request)` manually");
    }

    private HttpSend(int i10) {
        this.maxSendCount = i10;
        this.interceptors = new ArrayList();
    }

    public final void intercept(q<? super Sender, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object> block) {
        this.interceptors.add(block);
    }

    public /* synthetic */ HttpSend(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 20 : i10);
    }
}
