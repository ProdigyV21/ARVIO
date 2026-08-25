package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$21$1", f = "HomeScreen.kt", l = {921, 923}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$21$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $isTrailerPlaying$delegate;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $trailerOverlayAlpha;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$21$1(Animatable<Float, AnimationVector1D> animatable, MutableState<Boolean> mutableState, d7.d<? super HomeScreenKt$HomeScreen$21$1> dVar) {
        super(2, dVar);
        this.$trailerOverlayAlpha = animatable;
        this.$isTrailerPlaying$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$21$1(this.$trailerOverlayAlpha, this.$isTrailerPlaying$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
    
        if (r14 == r4) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006d, code lost:
    
        if (r14 == r4) goto L18;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            int r0 = r13.label
            r1 = 1
            r2 = 2
            if (r0 == 0) goto L1b
            if (r0 == r1) goto L16
            if (r0 != r2) goto Le
            k2.c.G(r14)
            goto L70
        Le:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L16:
            k2.c.G(r14)
            r10 = r13
            goto L4b
        L1b:
            k2.c.G(r14)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r14 = r13.$isTrailerPlaying$delegate
            boolean r14 = com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeScreen$lambda$57(r14)
            r0 = 0
            r3 = 0
            e7.a r4 = e7.a.f15033i
            if (r14 == 0) goto L4e
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r5 = r13.$trailerOverlayAlpha
            java.lang.Float r6 = new java.lang.Float
            r14 = 0
            r6.<init>(r14)
            r14 = 1500(0x5dc, float:2.102E-42)
            androidx.compose.animation.core.Easing r7 = androidx.compose.animation.core.EasingKt.getFastOutSlowInEasing()
            androidx.compose.animation.core.TweenSpec r7 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r14, r3, r7, r2, r0)
            r13.label = r1
            r8 = 0
            r9 = 0
            r11 = 12
            r12 = 0
            r10 = r13
            java.lang.Object r14 = androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r14 != r4) goto L4b
            goto L6f
        L4b:
            androidx.compose.animation.core.AnimationResult r14 = (androidx.compose.animation.core.AnimationResult) r14
            goto L72
        L4e:
            r10 = r13
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r5 = r10.$trailerOverlayAlpha
            java.lang.Float r6 = new java.lang.Float
            r14 = 1065353216(0x3f800000, float:1.0)
            r6.<init>(r14)
            r14 = 500(0x1f4, float:7.0E-43)
            androidx.compose.animation.core.Easing r1 = androidx.compose.animation.core.EasingKt.getFastOutSlowInEasing()
            androidx.compose.animation.core.TweenSpec r7 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r14, r3, r1, r2, r0)
            r10.label = r2
            r8 = 0
            r9 = 0
            r11 = 12
            r12 = 0
            java.lang.Object r14 = androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r14 != r4) goto L70
        L6f:
            return r4
        L70:
            androidx.compose.animation.core.AnimationResult r14 = (androidx.compose.animation.core.AnimationResult) r14
        L72:
            x6.t0 r14 = x6.t0.f22605a
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$21$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$21$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
