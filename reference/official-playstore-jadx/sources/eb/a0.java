package eb;

import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends f7.h implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15104i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ x6.d f15105l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.widget.a f15106m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(androidx.appcompat.widget.a aVar, d7.d dVar) {
        super(3, dVar);
        this.f15106m = aVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        a0 a0Var = new a0(this.f15106m, (d7.d) obj3);
        a0Var.f15105l = (x6.d) obj;
        return a0Var.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        androidx.appcompat.widget.a aVar = this.f15106m;
        a aVar2 = (a) aVar.f1501c;
        int i10 = this.f15104i;
        if (i10 == 0) {
            k2.c.G(obj);
            x6.d dVar = this.f15105l;
            byte bW = aVar2.w();
            if (bW == 1) {
                return aVar.g(true);
            }
            if (bW == 0) {
                return aVar.g(false);
            }
            if (bW != 6) {
                if (bW == 8) {
                    return aVar.f();
                }
                a.s(aVar2, "Can't begin reading element, unexpected token", 0, 6);
                throw null;
            }
            this.f15104i = 1;
            obj = androidx.appcompat.widget.a.d(aVar, dVar, this);
            e7.a aVar3 = e7.a.f15033i;
            if (obj == aVar3) {
                return aVar3;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return (db.m) obj;
    }
}
