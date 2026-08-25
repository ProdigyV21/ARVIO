package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class i2 extends f7.h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2246i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f2247l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ View f2248m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i2(View view, d7.d dVar) {
        super(2, dVar);
        this.f2248m = view;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        i2 i2Var = new i2(this.f2248m, dVar);
        i2Var.f2247l = obj;
        return i2Var;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((i2) create((ga.o) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        int i10 = this.f2246i;
        View view = this.f2248m;
        e7.a aVar = e7.a.f15033i;
        if (i10 == 0) {
            k2.c.G(obj);
            ga.o oVar = (ga.o) this.f2247l;
            this.f2247l = oVar;
            this.f2246i = 1;
            oVar.b(this, view);
            return aVar;
        }
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        ga.o oVar2 = (ga.o) this.f2247l;
        k2.c.G(obj);
        if (view instanceof ViewGroup) {
            this.f2247l = null;
            this.f2246i = 2;
            oVar2.getClass();
            z0 z0Var = new z0(new h2((ViewGroup) view));
            ga.n nVar = (ga.n) oVar2;
            if (z0Var.f2339l.hasNext()) {
                nVar.f15595m = z0Var;
                nVar.f15593i = 2;
                nVar.f15596n = this;
                obj2 = aVar;
            } else {
                obj2 = t0Var;
            }
            if (obj2 != aVar) {
                obj2 = t0Var;
            }
            if (obj2 == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }
}
