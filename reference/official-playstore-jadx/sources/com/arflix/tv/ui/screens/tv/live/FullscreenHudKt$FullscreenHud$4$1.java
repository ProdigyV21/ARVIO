package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$FullscreenHud$4$1", f = "FullscreenHud.kt", l = {143}, m = "invokeSuspend", v = 2)
public final class FullscreenHudKt$FullscreenHud$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $initialFocusApplied$delegate;
    final /* synthetic */ FocusRequester $playPauseFocusRequester;
    final /* synthetic */ MutableState<Boolean> $visible$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullscreenHudKt$FullscreenHud$4$1(MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, FocusRequester focusRequester, d7.d<? super FullscreenHudKt$FullscreenHud$4$1> dVar) {
        super(2, dVar);
        this.$visible$delegate = mutableState;
        this.$initialFocusApplied$delegate = mutableState2;
        this.$playPauseFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        FullscreenHudKt$FullscreenHud$4$1 fullscreenHudKt$FullscreenHud$4$1 = new FullscreenHudKt$FullscreenHud$4$1(this.$visible$delegate, this.$initialFocusApplied$delegate, this.$playPauseFocusRequester, dVar);
        fullscreenHudKt$FullscreenHud$4$1.L$0 = obj;
        return fullscreenHudKt$FullscreenHud$4$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(1:(1:5)(2:6|7))(2:8|(4:18|(1:20)|21|22)(2:12|(1:14)))|15|24|16|21|22) */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r3.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            k2.c.G(r4)
            goto L3e
        Lf:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L17:
            k2.c.G(r4)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$visible$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.access$FullscreenHud$lambda$1(r4)
            if (r4 == 0) goto L44
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$initialFocusApplied$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.access$FullscreenHud$lambda$17(r4)
            if (r4 != 0) goto L44
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$initialFocusApplied$delegate
            com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.access$FullscreenHud$lambda$18(r4, r2)
            r3.L$0 = r0
            r3.label = r2
            r0 = 100
            java.lang.Object r4 = ka.s0.a(r0, r3)
            e7.a r0 = e7.a.f15033i
            if (r4 != r0) goto L3e
            return r0
        L3e:
            androidx.compose.ui.focus.FocusRequester r4 = r3.$playPauseFocusRequester
            r4.requestFocus()     // Catch: java.lang.Throwable -> L52
            goto L52
        L44:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$visible$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.access$FullscreenHud$lambda$1(r4)
            if (r4 != 0) goto L52
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$initialFocusApplied$delegate
            r0 = 0
            com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.access$FullscreenHud$lambda$18(r4, r0)
        L52:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$FullscreenHud$4$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((FullscreenHudKt$FullscreenHud$4$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
