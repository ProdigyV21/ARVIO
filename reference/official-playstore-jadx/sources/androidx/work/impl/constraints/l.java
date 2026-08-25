package androidx.work.impl.constraints;

import androidx.activity.w;
import androidx.compose.runtime.ProduceStateScope;
import androidx.work.CoroutineWorker;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import ka.m0;
import kotlin.collections.s;
import kotlin.collections.x;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class l extends f7.j implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6816i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6817l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f6818m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6819n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f6820o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(Object obj, Object obj2, d7.d dVar, int i10) {
        super(2, dVar);
        this.f6816i = i10;
        this.f6819n = obj;
        this.f6820o = obj2;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f6816i) {
            case 0:
                return new l((j) this.f6818m, (androidx.work.impl.model.p) this.f6819n, (f) this.f6820o, dVar, 0);
            case 1:
                return new l((w) this.f6819n, (r7.l) this.f6820o, dVar, 1);
            case 2:
                return new l((m2.n) this.f6819n, (CoroutineWorker) this.f6820o, dVar, 2);
            case 3:
                return new l((d7.j) this.f6818m, (na.j) this.f6819n, (ProduceStateScope) this.f6820o, dVar, 3);
            default:
                l lVar = new l((na.k) this.f6819n, (oa.d) this.f6820o, dVar, 4);
                lVar.f6818m = obj;
                return lVar;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        k0 k0Var = (k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f6816i) {
        }
        return ((l) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.activity.w] */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v6, types: [int] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        m2.n nVar;
        switch (this.f6816i) {
            case 0:
                androidx.work.impl.model.p pVar = (androidx.work.impl.model.p) this.f6819n;
                int i10 = this.f6817l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    List list = ((j) this.f6818m).f6812a;
                    ArrayList<androidx.work.impl.constraints.controllers.d> arrayList = new ArrayList();
                    for (Object obj2 : list) {
                        if (((androidx.work.impl.constraints.controllers.d) obj2).b(pVar)) {
                            arrayList.add(obj2);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
                    for (androidx.work.impl.constraints.controllers.d dVar : arrayList) {
                        dVar.getClass();
                        arrayList2.add(new na.c(new androidx.work.impl.constraints.controllers.c(dVar, null, 0), d7.k.f14688i, -2, 1));
                    }
                    na.j jVarH = y0.h(new i((na.j[]) x.c1(arrayList2).toArray(new na.j[0]), 0));
                    k kVar = new k((f) this.f6820o, pVar, 0);
                    this.f6817l = 1;
                    Object objCollect = jVarH.collect(kVar, this);
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
                return t0.f22605a;
            case 1:
                w wVar = this.f6817l;
                try {
                    if (wVar == 0) {
                        k2.c.G(obj);
                        w wVar2 = (w) this.f6819n;
                        r7.l lVar = (r7.l) this.f6820o;
                        wVar2.a();
                        if (!wVar2.c()) {
                            this.f6818m = wVar2;
                            this.f6817l = 1;
                            Object objInvoke = lVar.invoke(this);
                            e7.a aVar2 = e7.a.f15033i;
                            wVar = wVar2;
                            if (objInvoke == aVar2) {
                                return aVar2;
                            }
                        }
                        return t0.f22605a;
                    }
                    if (wVar != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    w wVar3 = (w) this.f6818m;
                    k2.c.G(obj);
                    wVar = wVar3;
                    return t0.f22605a;
                } finally {
                    wVar.d();
                }
            case 2:
                int i11 = this.f6817l;
                if (i11 == 0) {
                    k2.c.G(obj);
                    nVar = (m2.n) this.f6819n;
                    CoroutineWorker coroutineWorker = (CoroutineWorker) this.f6820o;
                    this.f6818m = nVar;
                    this.f6817l = 1;
                    obj = coroutineWorker.getForegroundInfo(this);
                    e7.a aVar3 = e7.a.f15033i;
                    if (obj == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    nVar = (m2.n) this.f6818m;
                    k2.c.G(obj);
                }
                nVar.f20211i.h(obj);
                return t0.f22605a;
            case 3:
                ProduceStateScope produceStateScope = (ProduceStateScope) this.f6820o;
                na.j jVar = (na.j) this.f6819n;
                d7.j jVar2 = (d7.j) this.f6818m;
                int i12 = this.f6817l;
                if (i12 == 0) {
                    k2.c.G(obj);
                    boolean zA = kotlin.jvm.internal.p.a(jVar2, d7.k.f14688i);
                    e7.a aVar4 = e7.a.f15033i;
                    if (zA) {
                        n1.a aVar5 = new n1.a(produceStateScope, 0);
                        this.f6817l = 1;
                        if (jVar.collect(aVar5, this) == aVar4) {
                            return aVar4;
                        }
                    } else {
                        androidx.work.impl.constraints.controllers.c cVar = new androidx.work.impl.constraints.controllers.c(jVar, produceStateScope, null, 1);
                        this.f6817l = 2;
                        if (m0.y(jVar2, cVar, this) == aVar4) {
                            return aVar4;
                        }
                    }
                } else {
                    if (i12 != 1 && i12 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            default:
                int i13 = this.f6817l;
                t0 t0Var = t0.f22605a;
                if (i13 != 0) {
                    if (i13 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return t0Var;
                }
                k2.c.G(obj);
                k0 k0Var = (k0) this.f6818m;
                na.k kVar2 = (na.k) this.f6819n;
                ma.w wVarG = ((oa.d) this.f6820o).g(k0Var);
                this.f6817l = 1;
                Object objK = y0.k(kVar2, wVarG, true, this);
                e7.a aVar6 = e7.a.f15033i;
                if (objK != aVar6) {
                    objK = t0Var;
                }
                return objK == aVar6 ? aVar6 : t0Var;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(Object obj, Object obj2, Object obj3, d7.d dVar, int i10) {
        super(2, dVar);
        this.f6816i = i10;
        this.f6818m = obj;
        this.f6819n = obj2;
        this.f6820o = obj3;
    }
}
