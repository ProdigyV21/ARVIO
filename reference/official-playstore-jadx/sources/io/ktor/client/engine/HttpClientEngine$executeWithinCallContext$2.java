package io.ktor.client.engine;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lio/ktor/client/request/HttpResponseData;", "<anonymous>", "(Lka/k0;)Lio/ktor/client/request/HttpResponseData;"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$2", f = "HttpClientEngine.kt", l = {99}, m = "invokeSuspend")
public final class HttpClientEngine$executeWithinCallContext$2 extends j implements p<k0, d<? super HttpResponseData>, Object> {
    final /* synthetic */ HttpRequestData $requestData;
    int label;
    final /* synthetic */ HttpClientEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientEngine$executeWithinCallContext$2(HttpClientEngine httpClientEngine, HttpRequestData httpRequestData, d<? super HttpClientEngine$executeWithinCallContext$2> dVar) {
        super(2, dVar);
        this.this$0 = httpClientEngine;
        this.$requestData = httpRequestData;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new HttpClientEngine$executeWithinCallContext$2(this.this$0, this.$requestData, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        c.G(obj);
        if (HttpClientEngine.DefaultImpls.getClosed(this.this$0)) {
            throw new ClientEngineClosedException(null, 1, null);
        }
        HttpClientEngine httpClientEngine = this.this$0;
        HttpRequestData httpRequestData = this.$requestData;
        this.label = 1;
        Object objExecute = httpClientEngine.execute(httpRequestData, this);
        a aVar = a.f15033i;
        return objExecute == aVar ? aVar : objExecute;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super HttpResponseData> dVar) {
        return ((HttpClientEngine$executeWithinCallContext$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
