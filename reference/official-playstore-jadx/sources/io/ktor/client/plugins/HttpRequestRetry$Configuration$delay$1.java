package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.s0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "<anonymous>", "(J)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpRequestRetry$Configuration$delay$1", f = "HttpRequestRetry.kt", l = {111}, m = "invokeSuspend")
public final class HttpRequestRetry$Configuration$delay$1 extends j implements p<Long, d<? super t0>, Object> {
    /* synthetic */ long J$0;
    int label;

    public HttpRequestRetry$Configuration$delay$1(d<? super HttpRequestRetry$Configuration$delay$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        HttpRequestRetry$Configuration$delay$1 httpRequestRetry$Configuration$delay$1 = new HttpRequestRetry$Configuration$delay$1(dVar);
        httpRequestRetry$Configuration$delay$1.J$0 = ((Number) obj).longValue();
        return httpRequestRetry$Configuration$delay$1;
    }

    public final Object invoke(long j10, d<? super t0> dVar) {
        return ((HttpRequestRetry$Configuration$delay$1) create(Long.valueOf(j10), dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            long j10 = this.J$0;
            this.label = 1;
            Object objA = s0.a(j10, this);
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
        return t0.f22605a;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).longValue(), (d<? super t0>) obj2);
    }
}
