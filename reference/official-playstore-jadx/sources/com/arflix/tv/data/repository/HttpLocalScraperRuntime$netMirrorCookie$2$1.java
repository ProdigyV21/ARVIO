package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$netMirrorCookie$2$1", f = "HttpLocalScraperRuntime.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HttpLocalScraperRuntime$netMirrorCookie$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ gb.k0 $request;
    final /* synthetic */ HttpLocalScraperRuntime $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpLocalScraperRuntime$netMirrorCookie$2$1(HttpLocalScraperRuntime httpLocalScraperRuntime, gb.k0 k0Var, d7.d<? super HttpLocalScraperRuntime$netMirrorCookie$2$1> dVar) {
        super(2, dVar);
        this.$this_runCatching = httpLocalScraperRuntime;
        this.$request = k0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HttpLocalScraperRuntime$netMirrorCookie$2$1(this.$this_runCatching, this.$request, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        kotlin.text.k kVarB;
        List listB;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        gb.p0 p0VarD = this.$this_runCatching.okHttpClient.a(this.$request).d();
        try {
            String strA = p0VarD.f15785p.a("set-cookie");
            String str = null;
            if (strA == null) {
                strA = null;
            }
            if (strA != null && (kVarB = kotlin.text.m.b(HttpLocalScraperRuntime.T_HASH_T_REGEX, strA)) != null && (listB = kVarB.b()) != null) {
                str = (String) kotlin.collections.x.p0(1, listB);
            }
            p0VarD.close();
            return str;
        } finally {
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((HttpLocalScraperRuntime$netMirrorCookie$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
