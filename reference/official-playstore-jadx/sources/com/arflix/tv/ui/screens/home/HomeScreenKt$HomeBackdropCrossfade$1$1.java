package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeBackdropCrossfade$1$1", f = "HomeScreen.kt", l = {506, 513, 519, 525}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeBackdropCrossfade$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $backdropUrl;
    final /* synthetic */ MutableState<String> $displayedBackdropUrl$delegate;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $pendingAlpha;
    final /* synthetic */ MutableState<Boolean> $pendingBackdropReady$delegate;
    final /* synthetic */ MutableState<String> $pendingBackdropUrl$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeBackdropCrossfade$1$1(String str, Animatable<Float, AnimationVector1D> animatable, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, d7.d<? super HomeScreenKt$HomeBackdropCrossfade$1$1> dVar) {
        super(2, dVar);
        this.$backdropUrl = str;
        this.$pendingAlpha = animatable;
        this.$displayedBackdropUrl$delegate = mutableState;
        this.$pendingBackdropUrl$delegate = mutableState2;
        this.$pendingBackdropReady$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeBackdropCrossfade$1$1(this.$backdropUrl, this.$pendingAlpha, this.$displayedBackdropUrl$delegate, this.$pendingBackdropUrl$delegate, this.$pendingBackdropReady$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
    
        if (r9.snapTo(r1, r8) == r7) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r9.snapTo(r1, r8) == r7) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009b, code lost:
    
        if (r9.snapTo(r2, r8) == r7) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ba, code lost:
    
        if (r9.snapTo(r1, r8) == r7) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bc, code lost:
    
        return r7;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            int r0 = r8.label
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == 0) goto L1e
            if (r0 == r4) goto L19
            if (r0 == r3) goto L19
            if (r0 == r2) goto L19
            if (r0 != r1) goto L11
            goto L19
        L11:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L19:
            k2.c.G(r9)
            goto Lbd
        L1e:
            k2.c.G(r9)
            java.lang.String r9 = r8.$backdropUrl
            r0 = 0
            r5 = 0
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r9 == 0) goto L9e
            boolean r9 = kotlin.text.o.h0(r9)
            if (r9 == 0) goto L31
            goto L9e
        L31:
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$displayedBackdropUrl$delegate
            java.lang.String r9 = com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$1(r9)
            if (r9 != 0) goto L5a
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$displayedBackdropUrl$delegate
            java.lang.String r1 = r8.$backdropUrl
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$2(r9, r1)
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$pendingBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$5(r9, r6)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$pendingBackdropReady$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$8(r9, r5)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r9 = r8.$pendingAlpha
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r0)
            r8.label = r3
            java.lang.Object r9 = r9.snapTo(r1, r8)
            if (r9 != r7) goto Lbd
            goto Lbc
        L5a:
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$displayedBackdropUrl$delegate
            java.lang.String r9 = com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$1(r9)
            java.lang.String r3 = r8.$backdropUrl
            boolean r9 = kotlin.jvm.internal.p.a(r9, r3)
            if (r9 == 0) goto L82
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$pendingBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$5(r9, r6)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$pendingBackdropReady$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$8(r9, r5)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r9 = r8.$pendingAlpha
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r0)
            r8.label = r2
            java.lang.Object r9 = r9.snapTo(r1, r8)
            if (r9 != r7) goto Lbd
            goto Lbc
        L82:
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$pendingBackdropUrl$delegate
            java.lang.String r2 = r8.$backdropUrl
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$5(r9, r2)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$pendingBackdropReady$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$8(r9, r5)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r9 = r8.$pendingAlpha
            java.lang.Float r2 = new java.lang.Float
            r2.<init>(r0)
            r8.label = r1
            java.lang.Object r9 = r9.snapTo(r2, r8)
            if (r9 != r7) goto Lbd
            goto Lbc
        L9e:
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$displayedBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$2(r9, r6)
            androidx.compose.runtime.MutableState<java.lang.String> r9 = r8.$pendingBackdropUrl$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$5(r9, r6)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$pendingBackdropReady$delegate
            com.arflix.tv.ui.screens.home.HomeScreenKt.access$HomeBackdropCrossfade$lambda$8(r9, r5)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r9 = r8.$pendingAlpha
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r0)
            r8.label = r4
            java.lang.Object r9 = r9.snapTo(r1, r8)
            if (r9 != r7) goto Lbd
        Lbc:
            return r7
        Lbd:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeBackdropCrossfade$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeBackdropCrossfade$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
