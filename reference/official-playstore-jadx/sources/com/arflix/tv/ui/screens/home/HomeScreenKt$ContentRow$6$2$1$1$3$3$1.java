package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SpringSpec;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$6$2$1$1$3$3$1", f = "HomeScreen.kt", l = {3693}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$ContentRow$6$2$1$1$3$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $expandAnim;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$ContentRow$6$2$1$1$3$3$1(Animatable<Float, AnimationVector1D> animatable, d7.d<? super HomeScreenKt$ContentRow$6$2$1$1$3$3$1> dVar) {
        super(2, dVar);
        this.$expandAnim = animatable;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$ContentRow$6$2$1$1$3$3$1(this.$expandAnim, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            Animatable<Float, AnimationVector1D> animatable = this.$expandAnim;
            Float f10 = new Float(380.0f);
            SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            this.label = 1;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, springSpecSpring$default, null, null, this, 12, null);
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
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$ContentRow$6$2$1$1$3$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
