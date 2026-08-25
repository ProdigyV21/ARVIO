package yb;

import ga.o;
import r7.p;
import x6.t0;
import xb.f0;
import xb.s;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends f7.h implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f23017i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f23018l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s f23019m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f23020n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(s sVar, f0 f0Var, d7.d dVar) {
        super(2, dVar);
        this.f23019m = sVar;
        this.f23020n = f0Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        d dVar2 = new d(this.f23019m, this.f23020n, dVar);
        dVar2.f23018l = obj;
        return dVar2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((o) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.f23017i;
        if (i10 == 0) {
            k2.c.G(obj);
            o oVar = (o) this.f23018l;
            kotlin.collections.p pVar = new kotlin.collections.p();
            this.f23017i = 1;
            Object objC = b.c(oVar, this.f23019m, pVar, this.f23020n, false, true, this);
            e7.a aVar = e7.a.f15033i;
            if (objC == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }
}
