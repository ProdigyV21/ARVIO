package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class p0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3262i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3263l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f3264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f3265n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(r7.p pVar, d7.d dVar) {
        super(2, dVar);
        this.f3264m = pVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f3262i) {
            case 0:
                p0 p0Var = new p0(this.f3264m, dVar);
                p0Var.f3265n = obj;
                return p0Var;
            default:
                return new p0(this.f3264m, this.f3265n, dVar);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ka.k0 k0Var = (ka.k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f3262i) {
        }
        return ((p0) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f3262i) {
            case 0:
                int i10 = this.f3263l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    ka.k0 k0Var = (ka.k0) this.f3265n;
                    this.f3263l = 1;
                    Object objInvoke = this.f3264m.invoke(k0Var, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return x6.t0.f22605a;
            default:
                int i11 = this.f3263l;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                Object obj2 = this.f3265n;
                this.f3263l = 1;
                Object objInvoke2 = this.f3264m.invoke(obj2, this);
                e7.a aVar2 = e7.a.f15033i;
                return objInvoke2 == aVar2 ? aVar2 : objInvoke2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(r7.p pVar, Object obj, d7.d dVar) {
        super(2, dVar);
        this.f3264m = pVar;
        this.f3265n = obj;
    }
}
