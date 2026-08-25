package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.request.HttpRequestBuilder;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "context", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpRedirect$Plugin$install$1", f = "HttpRedirect.kt", l = {64, 69}, m = "invokeSuspend")
public final class HttpRedirect$Plugin$install$1 extends j implements q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpRedirect $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRedirect$Plugin$install$1(HttpRedirect httpRedirect, HttpClient httpClient, d<? super HttpRedirect$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$plugin = httpRedirect;
        this.$scope = httpClient;
    }

    @Override // r7.q
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
        HttpRedirect$Plugin$install$1 httpRedirect$Plugin$install$1 = new HttpRedirect$Plugin$install$1(this.$plugin, this.$scope, dVar);
        httpRedirect$Plugin$install$1.L$0 = sender;
        httpRedirect$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpRedirect$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        HttpRequestBuilder httpRequestBuilder;
        Sender sender;
        int i10 = this.label;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            Sender sender2 = (Sender) this.L$0;
            httpRequestBuilder = (HttpRequestBuilder) this.L$1;
            this.L$0 = sender2;
            this.L$1 = httpRequestBuilder;
            this.label = 1;
            Object objExecute = sender2.execute(httpRequestBuilder, this);
            if (objExecute != aVar) {
                sender = sender2;
                obj = objExecute;
            }
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        httpRequestBuilder = (HttpRequestBuilder) this.L$1;
        Sender sender3 = (Sender) this.L$0;
        c.G(obj);
        sender = sender3;
        HttpRequestBuilder httpRequestBuilder2 = httpRequestBuilder;
        HttpClientCall httpClientCall = (HttpClientCall) obj;
        if (this.$plugin.checkHttpMethod && !HttpRedirectKt.ALLOWED_FOR_REDIRECT.contains(httpClientCall.getRequest().getMethod())) {
            return httpClientCall;
        }
        HttpRedirect.Companion companion = HttpRedirect.INSTANCE;
        boolean z = this.$plugin.allowHttpsDowngrade;
        HttpClient httpClient = this.$scope;
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        Object objHandleCall = companion.handleCall(sender, httpRequestBuilder2, httpClientCall, z, httpClient, this);
        return objHandleCall == aVar ? aVar : objHandleCall;
    }
}
