package oa;

import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20813i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20814l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f20815m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i f20816n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ na.k f20817o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(i iVar, na.k kVar, d7.d dVar) {
        super(2, dVar);
        this.f20816n = iVar;
        this.f20817o = kVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f20813i) {
            case 0:
                return new f(this.f20816n, this.f20817o, this.f20815m, dVar);
            default:
                f fVar = new f(this.f20816n, this.f20817o, dVar);
                fVar.f20815m = obj;
                return fVar;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        k0 k0Var = (k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f20813i) {
        }
        return ((f) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [f7.j, r7.q] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f20813i) {
            case 0:
                int i10 = this.f20814l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    ?? r72 = this.f20816n.f20828o;
                    Object obj2 = this.f20815m;
                    this.f20814l = 1;
                    Object objInvoke = r72.invoke(this.f20817o, obj2, this);
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
                return t0.f22605a;
            default:
                int i11 = this.f20814l;
                if (i11 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.f20815m;
                    kotlin.jvm.internal.k0 k0Var2 = new kotlin.jvm.internal.k0();
                    i iVar = this.f20816n;
                    na.j jVar = iVar.f20812n;
                    h hVar = new h(k0Var2, k0Var, iVar, this.f20817o);
                    this.f20814l = 1;
                    Object objCollect = jVar.collect(hVar, this);
                    e7.a aVar2 = e7.a.f15033i;
                    if (objCollect == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(i iVar, na.k kVar, Object obj, d7.d dVar) {
        super(2, dVar);
        this.f20816n = iVar;
        this.f20817o = kVar;
        this.f20815m = obj;
    }
}
