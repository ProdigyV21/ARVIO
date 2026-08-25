package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.request.HttpRequestBuilder;
import k2.c;
import ka.k0;
import ka.m0;
import ka.s0;
import ka.v1;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpTimeout$Plugin$install$1$1$killer$1", f = "HttpTimeout.kt", l = {164}, m = "invokeSuspend")
public final class HttpTimeout$Plugin$install$1$1$killer$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ v1 $executionContext;
    final /* synthetic */ HttpRequestBuilder $request;
    final /* synthetic */ Long $requestTimeout;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpTimeout$Plugin$install$1$1$killer$1(Long l10, HttpRequestBuilder httpRequestBuilder, v1 v1Var, d<? super HttpTimeout$Plugin$install$1$1$killer$1> dVar) {
        super(2, dVar);
        this.$requestTimeout = l10;
        this.$request = httpRequestBuilder;
        this.$executionContext = v1Var;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new HttpTimeout$Plugin$install$1$1$killer$1(this.$requestTimeout, this.$request, this.$executionContext, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            long jLongValue = this.$requestTimeout.longValue();
            this.label = 1;
            Object objA = s0.a(jLongValue, this);
            a aVar = a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        HttpRequestTimeoutException httpRequestTimeoutException = new HttpRequestTimeoutException(this.$request);
        HttpTimeoutKt.LOGGER.h("Request timeout: " + this.$request.getUrl());
        this.$executionContext.cancel(m0.a(httpRequestTimeoutException.getMessage(), httpRequestTimeoutException));
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((HttpTimeout$Plugin$install$1$1$killer$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
