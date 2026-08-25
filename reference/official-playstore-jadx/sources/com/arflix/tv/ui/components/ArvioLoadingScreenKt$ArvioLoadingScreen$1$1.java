package com.arflix.tv.ui.components;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.ArvioLoadingScreenKt$ArvioLoadingScreen$1$1", f = "ArvioLoadingScreen.kt", l = {57}, m = "invokeSuspend", v = 2)
public final class ArvioLoadingScreenKt$ArvioLoadingScreen$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $reveal;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArvioLoadingScreenKt$ArvioLoadingScreen$1$1(Animatable<Float, AnimationVector1D> animatable, d7.d<? super ArvioLoadingScreenKt$ArvioLoadingScreen$1$1> dVar) {
        super(2, dVar);
        this.$reveal = animatable;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new ArvioLoadingScreenKt$ArvioLoadingScreen$1$1(this.$reveal, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            Animatable<Float, AnimationVector1D> animatable = this.$reveal;
            Float f10 = new Float(1.0f);
            TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(720, 0, ArvioLoadingScreenKt.EaseInOutCubic, 2, null);
            this.label = 1;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, tweenSpecTween$default, null, null, this, 12, null);
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
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((ArvioLoadingScreenKt$ArvioLoadingScreen$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
