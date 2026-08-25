package com.arflix.tv.ui.screens.player;

import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.SkipIntroButtonKt$SkipIntroButton$4$1", f = "SkipIntroButton.kt", l = {88}, m = "invokeSuspend", v = 2)
public final class SkipIntroButtonKt$SkipIntroButton$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $controlsVisible;
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ boolean $isVisible;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipIntroButtonKt$SkipIntroButton$4$1(boolean z, boolean z5, FocusRequester focusRequester, d7.d<? super SkipIntroButtonKt$SkipIntroButton$4$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$controlsVisible = z5;
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SkipIntroButtonKt$SkipIntroButton$4$1 skipIntroButtonKt$SkipIntroButton$4$1 = new SkipIntroButtonKt$SkipIntroButton$4$1(this.$isVisible, this.$controlsVisible, this.$focusRequester, dVar);
        skipIntroButtonKt$SkipIntroButton$4$1.L$0 = obj;
        return skipIntroButtonKt$SkipIntroButton$4$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(1:(1:5)(2:6|7))(4:8|(2:12|(1:14))|17|18)|15|20|16|17|18) */
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
            goto L31
        Lf:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L17:
            k2.c.G(r4)
            boolean r4 = r3.$isVisible
            if (r4 == 0) goto L36
            boolean r4 = r3.$controlsVisible
            if (r4 != 0) goto L36
            r3.L$0 = r0
            r3.label = r2
            r0 = 160(0xa0, double:7.9E-322)
            java.lang.Object r4 = ka.s0.a(r0, r3)
            e7.a r0 = e7.a.f15033i
            if (r4 != r0) goto L31
            return r0
        L31:
            androidx.compose.ui.focus.FocusRequester r4 = r3.$focusRequester
            r4.requestFocus()     // Catch: java.lang.Throwable -> L36
        L36:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SkipIntroButtonKt$SkipIntroButton$4$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SkipIntroButtonKt$SkipIntroButton$4$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
