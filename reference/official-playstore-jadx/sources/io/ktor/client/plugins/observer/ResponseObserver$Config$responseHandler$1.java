package io.ktor.client.plugins.observer;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.statement.HttpResponse;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/client/statement/HttpResponse;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.observer.ResponseObserver$Config$responseHandler$1", f = "ResponseObserver.kt", l = {}, m = "invokeSuspend")
public final class ResponseObserver$Config$responseHandler$1 extends j implements p<HttpResponse, d<? super t0>, Object> {
    int label;

    public ResponseObserver$Config$responseHandler$1(d<? super ResponseObserver$Config$responseHandler$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new ResponseObserver$Config$responseHandler$1(dVar);
    }

    @Override // r7.p
    public final Object invoke(HttpResponse httpResponse, d<? super t0> dVar) {
        return ((ResponseObserver$Config$responseHandler$1) create(httpResponse, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        return t0.f22605a;
    }
}
