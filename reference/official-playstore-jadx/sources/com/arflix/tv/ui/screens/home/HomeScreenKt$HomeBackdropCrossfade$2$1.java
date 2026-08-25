package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeBackdropCrossfade$2$1", f = "HomeScreen.kt", l = {533, 534, 541}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeBackdropCrossfade$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<String> $displayedBackdropUrl$delegate;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $pendingAlpha;
    final /* synthetic */ MutableState<Boolean> $pendingBackdropReady$delegate;
    final /* synthetic */ MutableState<String> $pendingBackdropUrl$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeBackdropCrossfade$2$1(Animatable<Float, AnimationVector1D> animatable, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, d7.d<? super HomeScreenKt$HomeBackdropCrossfade$2$1> dVar) {
        super(2, dVar);
        this.$pendingAlpha = animatable;
        this.$pendingBackdropUrl$delegate = mutableState;
        this.$pendingBackdropReady$delegate = mutableState2;
        this.$displayedBackdropUrl$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeBackdropCrossfade$2$1(this.$pendingAlpha, this.$pendingBackdropUrl$delegate, this.$pendingBackdropReady$delegate, this.$displayedBackdropUrl$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2 A[RETURN] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r16) {
        /*
            r15 = this;
            int r0 = r15.label
            r8 = 0
            r9 = 0
            r10 = 3
            r1 = 2
            r2 = 1
            x6.t0 r11 = x6.t0.f22605a
            r12 = 0
            e7.a r13 = e7.a.f15033i
            if (r0 == 0) goto L36
            if (r0 == r2) goto L2d
            if (r0 == r1) goto L25
            if (r0 != r10) goto L1d
            java.lang.Object r0 = r15.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r16)
            goto La2
        L1d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L25:
            java.lang.Object r0 = r15.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r16)
            goto L81
        L2d:
            java.lang.Object r0 = r15.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r16)
        L34:
            r14 = r0
            goto L5d
        L36:
            k2.c.G(r16)
            androidx.compose.runtime.MutableState<java.lang.String> r0 = r15.$pendingBackdropUrl$delegate
            java.lang.String r0 = com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$4(r0)
            if (r0 != 0) goto L42
            return r11
        L42:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r15.$pendingBackdropReady$delegate
            boolean r3 = com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$7(r3)
            if (r3 != 0) goto L4b
            return r11
        L4b:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r3 = r15.$pendingAlpha
            java.lang.Float r4 = new java.lang.Float
            r4.<init>(r9)
            r15.L$0 = r0
            r15.label = r2
            java.lang.Object r2 = r3.snapTo(r4, r15)
            if (r2 != r13) goto L34
            goto La1
        L5d:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r0 = r15.$pendingAlpha
            java.lang.Float r2 = new java.lang.Float
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.<init>(r3)
            r3 = 420(0x1a4, float:5.89E-43)
            r4 = 6
            androidx.compose.animation.core.TweenSpec r3 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r3, r8, r12, r4, r12)
            r15.L$0 = r14
            r15.label = r1
            r1 = r2
            r2 = r3
            r3 = 0
            r4 = 0
            r6 = 12
            r7 = 0
            r5 = r15
            java.lang.Object r0 = androidx.compose.animation.core.Animatable.animateTo$default(r0, r1, r2, r3, r4, r5, r6, r7)
            if (r0 != r13) goto L80
            goto La1
        L80:
            r0 = r14
        L81:
            androidx.compose.runtime.MutableState<java.lang.String> r1 = r15.$displayedBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$2(r1, r0)
            androidx.compose.runtime.MutableState<java.lang.String> r0 = r15.$pendingBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$5(r0, r12)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r0 = r15.$pendingBackdropReady$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$8(r0, r8)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r0 = r15.$pendingAlpha
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r9)
            r15.L$0 = r12
            r15.label = r10
            java.lang.Object r0 = r0.snapTo(r1, r15)
            if (r0 != r13) goto La2
        La1:
            return r13
        La2:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeBackdropCrossfade$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeBackdropCrossfade$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
