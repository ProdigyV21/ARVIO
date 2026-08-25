package io.ktor.client.plugins.cookies;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.util.Iterator;
import java.util.List;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.cookies.HttpCookies$initializer$1", f = "HttpCookies.kt", l = {32}, m = "invokeSuspend")
public final class HttpCookies$initializer$1 extends j implements p<k0, d<? super t0>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ HttpCookies this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$initializer$1(HttpCookies httpCookies, d<? super HttpCookies$initializer$1> dVar) {
        super(2, dVar);
        this.this$0 = httpCookies;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new HttpCookies$initializer$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        HttpCookies httpCookies;
        Iterator it;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            List list = this.this$0.defaults;
            httpCookies = this.this$0;
            it = list.iterator();
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$1;
            httpCookies = (HttpCookies) this.L$0;
            c.G(obj);
        }
        while (it.hasNext()) {
            p pVar = (p) it.next();
            CookiesStorage cookiesStorage = httpCookies.storage;
            this.L$0 = httpCookies;
            this.L$1 = it;
            this.label = 1;
            Object objInvoke = pVar.invoke(cookiesStorage, this);
            a aVar = a.f15033i;
            if (objInvoke == aVar) {
                return aVar;
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((HttpCookies$initializer$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
