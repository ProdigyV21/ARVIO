package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.ClientUpgradeContent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLProtocolKt;
import k2.c;
import ka.m0;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpTimeout$Plugin$install$1", f = "HttpTimeout.kt", l = {146, 174}, m = "invokeSuspend")
public final class HttpTimeout$Plugin$install$1 extends j implements q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpTimeout $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpTimeout$Plugin$install$1(HttpTimeout httpTimeout, HttpClient httpClient, d<? super HttpTimeout$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$plugin = httpTimeout;
        this.$scope = httpClient;
    }

    @Override // r7.q
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
        HttpTimeout$Plugin$install$1 httpTimeout$Plugin$install$1 = new HttpTimeout$Plugin$install$1(this.$plugin, this.$scope, dVar);
        httpTimeout$Plugin$install$1.L$0 = sender;
        httpTimeout$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpTimeout$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 == 1) {
                c.G(obj);
                return obj;
            }
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        c.G(obj);
        Sender sender = (Sender) this.L$0;
        HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
        boolean zIsWebsocket = URLProtocolKt.isWebsocket(httpRequestBuilder.getUrl().getProtocol());
        a aVar = a.f15033i;
        if (zIsWebsocket || (httpRequestBuilder.getBody() instanceof ClientUpgradeContent)) {
            this.L$0 = null;
            this.label = 1;
            Object objExecute = sender.execute(httpRequestBuilder, this);
            if (objExecute != aVar) {
                return objExecute;
            }
        } else {
            HttpTimeout.Companion companion = HttpTimeout.INSTANCE;
            HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestBuilder.getCapabilityOrNull(companion);
            if (httpTimeoutCapabilityConfiguration == null && this.$plugin.hasNotNullTimeouts()) {
                HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration2 = new HttpTimeout.HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
                httpRequestBuilder.setCapability(companion, httpTimeoutCapabilityConfiguration2);
                httpTimeoutCapabilityConfiguration = httpTimeoutCapabilityConfiguration2;
            }
            if (httpTimeoutCapabilityConfiguration != null) {
                HttpTimeout httpTimeout = this.$plugin;
                HttpClient httpClient = this.$scope;
                Long l10 = httpTimeoutCapabilityConfiguration.get_connectTimeoutMillis();
                if (l10 == null) {
                    l10 = httpTimeout.connectTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setConnectTimeoutMillis(l10);
                Long l11 = httpTimeoutCapabilityConfiguration.get_socketTimeoutMillis();
                if (l11 == null) {
                    l11 = httpTimeout.socketTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setSocketTimeoutMillis(l11);
                Long l12 = httpTimeoutCapabilityConfiguration.get_requestTimeoutMillis();
                if (l12 == null) {
                    l12 = httpTimeout.requestTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setRequestTimeoutMillis(l12);
                Long l13 = httpTimeoutCapabilityConfiguration.get_requestTimeoutMillis();
                if (l13 == null) {
                    l13 = httpTimeout.requestTimeoutMillis;
                }
                if (l13 != null && l13.longValue() != Long.MAX_VALUE) {
                    httpRequestBuilder.getExecutionContext().invokeOnCompletion(new HttpTimeout$Plugin$install$1$1$1(m0.p(httpClient, null, 0, new HttpTimeout$Plugin$install$1$1$killer$1(l13, httpRequestBuilder, httpRequestBuilder.getExecutionContext(), null), 3)));
                }
            }
            this.L$0 = null;
            this.label = 2;
            Object objExecute2 = sender.execute(httpRequestBuilder, this);
            if (objExecute2 != aVar) {
                return objExecute2;
            }
        }
        return aVar;
    }
}
