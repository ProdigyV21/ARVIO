package io.ktor.client.plugins.cookies;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0002*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/t0;", "response", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.cookies.HttpCookies$Companion$install$3", f = "HttpCookies.kt", l = {124}, m = "invokeSuspend")
public final class HttpCookies$Companion$install$3 extends j implements q<PipelineContext<HttpResponse, t0>, HttpResponse, d<? super t0>, Object> {
    final /* synthetic */ HttpCookies $plugin;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCookies$Companion$install$3(HttpCookies httpCookies, d<? super HttpCookies$Companion$install$3> dVar) {
        super(3, dVar);
        this.$plugin = httpCookies;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponse, t0> pipelineContext, HttpResponse httpResponse, d<? super t0> dVar) {
        HttpCookies$Companion$install$3 httpCookies$Companion$install$3 = new HttpCookies$Companion$install$3(this.$plugin, dVar);
        httpCookies$Companion$install$3.L$0 = httpResponse;
        return httpCookies$Companion$install$3.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            HttpResponse httpResponse = (HttpResponse) this.L$0;
            HttpCookies httpCookies = this.$plugin;
            this.label = 1;
            Object objSaveCookiesFrom$ktor_client_core = httpCookies.saveCookiesFrom$ktor_client_core(httpResponse, this);
            a aVar = a.f15033i;
            if (objSaveCookiesFrom$ktor_client_core == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }
}
