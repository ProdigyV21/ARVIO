package androidx.tv.foundation.lazy.list;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5420i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5421l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m0 f5422m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l0(m0 m0Var, d7.d dVar, int i10) {
        super(2, dVar);
        this.f5420i = i10;
        this.f5422m = m0Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f5420i) {
            case 0:
                return new l0(this.f5422m, dVar, 0);
            default:
                return new l0(this.f5422m, dVar, 1);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ka.k0 k0Var = (ka.k0) obj;
        d7.d dVar = (d7.d) obj2;
        switch (this.f5420i) {
        }
        return ((l0) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f5420i) {
            case 0:
                int i10 = this.f5421l;
                if (i10 == 0) {
                    k2.c.G(obj);
                    AnimationState animationState = this.f5422m.f5449y;
                    Float f10 = new Float(0.0f);
                    SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 400.0f, new Float(0.5f), 1, null);
                    this.f5421l = 1;
                    Object objAnimateTo$default = SuspendAnimationKt.animateTo$default(animationState, f10, springSpecSpring$default, true, null, this, 8, null);
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
                return t0.f22605a;
            default:
                int i11 = this.f5421l;
                if (i11 == 0) {
                    k2.c.G(obj);
                    AnimationState animationState2 = this.f5422m.f5449y;
                    Float f11 = new Float(0.0f);
                    SpringSpec springSpecSpring$default2 = AnimationSpecKt.spring$default(0.0f, 400.0f, new Float(0.5f), 1, null);
                    this.f5421l = 1;
                    Object objAnimateTo$default2 = SuspendAnimationKt.animateTo$default(animationState2, f11, springSpecSpring$default2, true, null, this, 8, null);
                    e7.a aVar2 = e7.a.f15033i;
                    if (objAnimateTo$default2 == aVar2) {
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
}
