package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVidMody$2$ok$1", f = "HttpLocalScraperRuntime.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HttpLocalScraperRuntime$resolveVidMody$2$ok$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ gb.k0 $request;
    final /* synthetic */ HttpLocalScraperRuntime $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpLocalScraperRuntime$resolveVidMody$2$ok$1(HttpLocalScraperRuntime httpLocalScraperRuntime, gb.k0 k0Var, d7.d<? super HttpLocalScraperRuntime$resolveVidMody$2$ok$1> dVar) {
        super(2, dVar);
        this.$this_runCatching = httpLocalScraperRuntime;
        this.$request = k0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HttpLocalScraperRuntime$resolveVidMody$2$ok$1(this.$this_runCatching, this.$request, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        gb.p0 p0VarD = this.$this_runCatching.okHttpClient.a(this.$request).d();
        try {
            Boolean boolValueOf = Boolean.valueOf(p0VarD.k());
            p0VarD.close();
            return boolValueOf;
        } finally {
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((HttpLocalScraperRuntime$resolveVidMody$2$ok$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
