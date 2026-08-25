package na;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends f7.j implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20619i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ k f20621m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object[] f20622n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ f7.j f20623o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public m0(r7.q qVar, d7.d dVar) {
        super(3, dVar);
        this.f20623o = (f7.j) qVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [f7.j, r7.r] */
    /* JADX WARN: Type inference failed for: r1v1, types: [f7.j, r7.q] */
    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        k kVar = (k) obj;
        Object[] objArr = (Object[]) obj2;
        d7.d dVar = (d7.d) obj3;
        switch (this.f20619i) {
            case 0:
                m0 m0Var = new m0((r7.r) this.f20623o, dVar);
                m0Var.f20621m = kVar;
                m0Var.f20622n = objArr;
                return m0Var.invokeSuspend(x6.t0.f22605a);
            default:
                m0 m0Var2 = new m0((r7.q) this.f20623o, dVar);
                m0Var2.f20621m = kVar;
                m0Var2.f20622n = objArr;
                return m0Var2.invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [f7.j, r7.r] */
    /* JADX WARN: Type inference failed for: r2v4, types: [f7.j, r7.q] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k kVar;
        k kVar2;
        switch (this.f20619i) {
            case 0:
                int i10 = this.f20620l;
                e7.a aVar = e7.a.f15033i;
                if (i10 == 0) {
                    k2.c.G(obj);
                    kVar = this.f20621m;
                    Object[] objArr = this.f20622n;
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    Object obj4 = objArr[2];
                    this.f20621m = kVar;
                    this.f20620l = 1;
                    obj = this.f20623o.invoke(obj2, obj3, obj4, this);
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
                    kVar = this.f20621m;
                    k2.c.G(obj);
                }
                this.f20621m = null;
                this.f20620l = 2;
                if (kVar.emit(obj, this) == aVar) {
                    return aVar;
                }
                return x6.t0.f22605a;
            default:
                int i11 = this.f20620l;
                e7.a aVar2 = e7.a.f15033i;
                if (i11 == 0) {
                    k2.c.G(obj);
                    kVar2 = this.f20621m;
                    Object[] objArr2 = this.f20622n;
                    Object obj5 = objArr2[0];
                    Object obj6 = objArr2[1];
                    this.f20621m = kVar2;
                    this.f20620l = 1;
                    obj = this.f20623o.invoke(obj5, obj6, this);
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
                    kVar2 = this.f20621m;
                    k2.c.G(obj);
                }
                this.f20621m = null;
                this.f20620l = 2;
                if (kVar2.emit(obj, this) == aVar2) {
                    return aVar2;
                }
                return x6.t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public m0(r7.r rVar, d7.d dVar) {
        super(3, dVar);
        this.f20623o = (f7.j) rVar;
    }
}
