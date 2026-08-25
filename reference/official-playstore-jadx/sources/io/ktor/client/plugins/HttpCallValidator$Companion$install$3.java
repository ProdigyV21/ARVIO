package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.client.plugins.HttpCallValidator$Companion$install$3", f = "HttpCallValidator.kt", l = {151, 152}, m = "invokeSuspend")
public final class HttpCallValidator$Companion$install$3 extends j implements q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCallValidator$Companion$install$3(HttpCallValidator httpCallValidator, d<? super HttpCallValidator$Companion$install$3> dVar) {
        super(3, dVar);
        this.$plugin = httpCallValidator;
    }

    @Override // r7.q
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
        HttpCallValidator$Companion$install$3 httpCallValidator$Companion$install$3 = new HttpCallValidator$Companion$install$3(this.$plugin, dVar);
        httpCallValidator$Companion$install$3.L$0 = sender;
        httpCallValidator$Companion$install$3.L$1 = httpRequestBuilder;
        return httpCallValidator$Companion$install$3.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            Sender sender = (Sender) this.L$0;
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
            this.L$0 = null;
            this.label = 1;
            obj = sender.execute(httpRequestBuilder, this);
            if (obj != aVar) {
            }
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            HttpClientCall httpClientCall = (HttpClientCall) this.L$0;
            c.G(obj);
            return httpClientCall;
        }
        c.G(obj);
        HttpClientCall httpClientCall2 = (HttpClientCall) obj;
        HttpCallValidator httpCallValidator = this.$plugin;
        HttpResponse response = httpClientCall2.getResponse();
        this.L$0 = httpClientCall2;
        this.label = 2;
        return httpCallValidator.validateResponse(response, this) == aVar ? aVar : httpClientCall2;
    }
}
