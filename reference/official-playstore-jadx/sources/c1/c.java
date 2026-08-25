package c1;

import f7.j;
import java.util.Collections;
import java.util.LinkedHashMap;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends j implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7370i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7371l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7372m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f7373n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public c(p pVar, d7.d dVar, int i10) {
        super(2, dVar);
        this.f7370i = i10;
        switch (i10) {
            case 1:
                this.f7373n = (j) pVar;
                super(2, dVar);
                break;
            default:
                this.f7373n = (j) pVar;
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [f7.j, r7.p] */
    /* JADX WARN: Type inference failed for: r1v1, types: [f7.j, r7.p] */
    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f7370i) {
            case 0:
                c cVar = new c(this.f7373n, dVar, 0);
                cVar.f7372m = obj;
                return cVar;
            default:
                c cVar2 = new c(this.f7373n, dVar, 1);
                cVar2.f7372m = obj;
                return cVar2;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        g gVar = (g) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f7370i) {
        }
        return ((c) create(gVar, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [f7.j, r7.p] */
    /* JADX WARN: Type inference failed for: r4v15, types: [f7.j, r7.p] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f7370i) {
            case 0:
                int i10 = this.f7371l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    g gVar = (g) this.f7372m;
                    this.f7371l = 1;
                    obj = this.f7373n.invoke(gVar, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                g gVar2 = (g) obj;
                ((b) gVar2).f7369b.set(true);
                return gVar2;
            default:
                int i11 = this.f7371l;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    b bVar = (b) this.f7372m;
                    k2.c.G(obj);
                    return bVar;
                }
                k2.c.G(obj);
                g gVar3 = (g) this.f7372m;
                gVar3.getClass();
                b bVar2 = new b(new LinkedHashMap(Collections.unmodifiableMap(((b) gVar3).f7368a)), false);
                this.f7372m = bVar2;
                this.f7371l = 1;
                Object objInvoke = this.f7373n.invoke(bVar2, this);
                e7.a aVar2 = e7.a.f15033i;
                return objInvoke == aVar2 ? aVar2 : bVar2;
        }
    }
}
