package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$49$11$1", f = "LiveTvScreen.kt", l = {2948}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$49$11$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $fsFocus;
    final /* synthetic */ MutableState<Boolean> $fullscreenGuideOpen$delegate;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<Boolean> $quickZapOpen$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$49$11$1(MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, FocusRequester focusRequester, d7.d<? super LiveTvScreenKt$LiveTvScreen$49$11$1> dVar) {
        super(2, dVar);
        this.$isFullScreen$delegate = mutableState;
        this.$fullscreenGuideOpen$delegate = mutableState2;
        this.$quickZapOpen$delegate = mutableState3;
        this.$fsFocus = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$49$11$1 liveTvScreenKt$LiveTvScreen$49$11$1 = new LiveTvScreenKt$LiveTvScreen$49$11$1(this.$isFullScreen$delegate, this.$fullscreenGuideOpen$delegate, this.$quickZapOpen$delegate, this.$fsFocus, dVar);
        liveTvScreenKt$LiveTvScreen$49$11$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$49$11$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(1:(1:5)(2:6|7))(4:8|(2:14|(1:16))|19|20)|17|22|18|19|20) */
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
            goto L41
        Lf:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L17:
            k2.c.G(r4)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$isFullScreen$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$139(r4)
            if (r4 == 0) goto L46
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$fullscreenGuideOpen$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$142(r4)
            if (r4 != 0) goto L46
            androidx.compose.runtime.MutableState<java.lang.Boolean> r4 = r3.$quickZapOpen$delegate
            boolean r4 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$158(r4)
            if (r4 != 0) goto L46
            r3.L$0 = r0
            r3.label = r2
            r0 = 50
            java.lang.Object r4 = ka.s0.a(r0, r3)
            e7.a r0 = e7.a.f15033i
            if (r4 != r0) goto L41
            return r0
        L41:
            androidx.compose.ui.focus.FocusRequester r4 = r3.$fsFocus
            r4.requestFocus()     // Catch: java.lang.Throwable -> L46
        L46:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$49$11$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$49$11$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
