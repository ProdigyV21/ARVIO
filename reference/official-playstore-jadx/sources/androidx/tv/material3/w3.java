package androidx.tv.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.TweenSpec;

/* JADX INFO: loaded from: classes3.dex */
public final class w3 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6491i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6492l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f6493m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6494n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w3(Object obj, float f10, d7.d dVar, int i10) {
        super(2, dVar);
        this.f6491i = i10;
        this.f6494n = obj;
        this.f6493m = f10;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f6491i) {
            case 0:
                return new w3((Animatable) this.f6494n, this.f6493m, dVar, 0);
            default:
                return new w3((i2.m) this.f6494n, this.f6493m, dVar, 1);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ka.k0 k0Var = (ka.k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f6491i) {
        }
        return ((w3) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f6491i) {
            case 0:
                int i10 = this.f6492l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    Animatable animatable = (Animatable) this.f6494n;
                    Float f10 = new Float(this.f6493m);
                    TweenSpec tweenSpec = SwitchKt.f5762f;
                    this.f6492l = 1;
                    Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, tweenSpec, null, null, this, 12, null);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimateTo$default == aVar) {
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
                int i11 = this.f6492l;
                if (i11 == 0) {
                    k2.c.G(obj);
                    i2.m mVar = (i2.m) this.f6494n;
                    this.f6492l = 1;
                    Object objB = mVar.b(this.f6493m, this);
                    e7.a aVar2 = e7.a.f15033i;
                    if (objB == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return x6.t0.f22605a;
        }
    }
}
