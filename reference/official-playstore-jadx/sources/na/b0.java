package na;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends f7.j implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20533i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ k f20535m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f20536n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ x6.o f20537o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(r7.p pVar, d7.d dVar) {
        super(3, dVar);
        this.f20537o = pVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        k kVar = (k) obj;
        switch (this.f20533i) {
            case 0:
                b0 b0Var = new b0((r7.p) this.f20537o, (d7.d) obj3);
                b0Var.f20535m = kVar;
                b0Var.f20536n = obj2;
                return b0Var.invokeSuspend(x6.t0.f22605a);
            default:
                b0 b0Var2 = new b0((r7.s) this.f20537o, (d7.d) obj3);
                b0Var2.f20535m = kVar;
                b0Var2.f20536n = (Object[]) obj2;
                return b0Var2.invokeSuspend(x6.t0.f22605a);
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k kVar;
        k kVar2;
        b0 b0Var;
        switch (this.f20533i) {
            case 0:
                int i10 = this.f20534l;
                e7.a aVar = e7.a.f15033i;
                if (i10 == 0) {
                    k2.c.G(obj);
                    kVar = this.f20535m;
                    Object obj2 = this.f20536n;
                    r7.p pVar = (r7.p) this.f20537o;
                    this.f20535m = kVar;
                    this.f20534l = 1;
                    obj = pVar.invoke(obj2, this);
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        if (i10 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        return x6.t0.f22605a;
                    }
                    kVar = this.f20535m;
                    k2.c.G(obj);
                }
                this.f20535m = null;
                this.f20534l = 2;
                if (kVar.emit(obj, this) == aVar) {
                    return aVar;
                }
                return x6.t0.f22605a;
            default:
                int i11 = this.f20534l;
                e7.a aVar2 = e7.a.f15033i;
                if (i11 == 0) {
                    k2.c.G(obj);
                    kVar2 = this.f20535m;
                    Object[] objArr = (Object[]) this.f20536n;
                    r7.s sVar = (r7.s) this.f20537o;
                    Object obj3 = objArr[0];
                    Object obj4 = objArr[1];
                    Object obj5 = objArr[2];
                    Object obj6 = objArr[3];
                    this.f20535m = kVar2;
                    this.f20534l = 1;
                    b0Var = this;
                    obj = sVar.invoke(obj3, obj4, obj5, obj6, b0Var);
                    if (obj == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i11 != 1) {
                        if (i11 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        return x6.t0.f22605a;
                    }
                    kVar2 = this.f20535m;
                    k2.c.G(obj);
                    b0Var = this;
                }
                b0Var.f20535m = null;
                b0Var.f20534l = 2;
                if (kVar2.emit(obj, this) == aVar2) {
                    return aVar2;
                }
                return x6.t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(r7.s sVar, d7.d dVar) {
        super(3, dVar);
        this.f20537o = sVar;
    }
}
