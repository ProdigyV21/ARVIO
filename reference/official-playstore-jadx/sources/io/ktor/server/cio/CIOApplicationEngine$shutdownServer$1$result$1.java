package io.ktor.server.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.v1;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1$result$1", f = "CIOApplicationEngine.kt", l = {93}, m = "invokeSuspend")
public final class CIOApplicationEngine$shutdownServer$1$result$1 extends j implements p<k0, d<? super Boolean>, Object> {
    int label;
    final /* synthetic */ CIOApplicationEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOApplicationEngine$shutdownServer$1$result$1(CIOApplicationEngine cIOApplicationEngine, d<? super CIOApplicationEngine$shutdownServer$1$result$1> dVar) {
        super(2, dVar);
        this.this$0 = cIOApplicationEngine;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new CIOApplicationEngine$shutdownServer$1$result$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            v1 serverJob = this.this$0.getServerJob();
            this.label = 1;
            Object objJoin = serverJob.join(this);
            a aVar = a.f15033i;
            if (objJoin == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return Boolean.TRUE;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Boolean> dVar) {
        return ((CIOApplicationEngine$shutdownServer$1$result$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
