package yb;

import ga.o;
import java.util.Iterator;
import r7.p;
import x6.t0;
import xb.f0;
import xb.s;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends f7.h implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public kotlin.collections.p f23021i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Iterator f23022l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23023m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23024n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ f0 f23025o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s f23026p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f23027q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f0 f0Var, s sVar, boolean z, d7.d dVar) {
        super(2, dVar);
        this.f23025o = f0Var;
        this.f23026p = sVar;
        this.f23027q = z;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        e eVar = new e(this.f23025o, this.f23026p, this.f23027q, dVar);
        eVar.f23024n = obj;
        return eVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((o) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        kotlin.collections.p pVar;
        Iterator it;
        o oVar;
        int i10 = this.f23023m;
        s sVar = this.f23026p;
        if (i10 == 0) {
            k2.c.G(obj);
            o oVar2 = (o) this.f23024n;
            kotlin.collections.p pVar2 = new kotlin.collections.p();
            f0 f0Var = this.f23025o;
            pVar2.addLast(f0Var);
            pVar = pVar2;
            it = sVar.list(f0Var).iterator();
            oVar = oVar2;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = this.f23022l;
            kotlin.collections.p pVar3 = this.f23021i;
            o oVar3 = (o) this.f23024n;
            k2.c.G(obj);
            pVar = pVar3;
            oVar = oVar3;
        }
        while (it.hasNext()) {
            f0 f0Var2 = (f0) it.next();
            this.f23024n = oVar;
            this.f23021i = pVar;
            this.f23022l = it;
            this.f23023m = 1;
            Object objC = b.c(oVar, sVar, pVar, f0Var2, this.f23027q, false, this);
            e7.a aVar = e7.a.f15033i;
            if (objC == aVar) {
                return aVar;
            }
        }
        return t0.f22605a;
    }
}
