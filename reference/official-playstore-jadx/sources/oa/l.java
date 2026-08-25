package oa;

import java.util.concurrent.atomic.AtomicInteger;
import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20834i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ na.j[] f20835l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20836m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AtomicInteger f20837n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ ma.f f20838o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(na.j[] jVarArr, int i10, AtomicInteger atomicInteger, ma.f fVar, d7.d dVar) {
        super(2, dVar);
        this.f20835l = jVarArr;
        this.f20836m = i10;
        this.f20837n = atomicInteger;
        this.f20838o = fVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new l(this.f20835l, this.f20836m, this.f20837n, this.f20838o, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((l) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f20834i;
        AtomicInteger atomicInteger = this.f20837n;
        ma.f fVar = this.f20838o;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                na.j[] jVarArr = this.f20835l;
                int i11 = this.f20836m;
                na.j jVar = jVarArr[i11];
                k kVar = new k(fVar, i11);
                this.f20834i = 1;
                Object objCollect = jVar.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            if (atomicInteger.decrementAndGet() == 0) {
                fVar.close(null);
            }
            return t0.f22605a;
        } finally {
            if (atomicInteger.decrementAndGet() == 0) {
                fVar.close(null);
            }
        }
    }
}
