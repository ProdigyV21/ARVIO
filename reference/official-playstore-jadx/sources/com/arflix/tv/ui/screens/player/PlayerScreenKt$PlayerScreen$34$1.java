package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$34$1", f = "PlayerScreen.kt", l = {2127}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$34$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ FocusRequester $playButtonFocusRequester;
    final /* synthetic */ MutableState<Boolean> $showControls$delegate;
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$34$1(FocusRequester focusRequester, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$34$1> dVar) {
        super(2, dVar);
        this.$playButtonFocusRequester = focusRequester;
        this.$showControls$delegate = mutableState;
        this.$hasPlaybackStarted$delegate = mutableState2;
        this.$showSubtitleMenu$delegate = mutableState3;
        this.$showSourceMenu$delegate = mutableState4;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$34$1(this.$playButtonFocusRequester, this.$showControls$delegate, this.$hasPlaybackStarted$delegate, this.$showSubtitleMenu$delegate, this.$showSourceMenu$delegate, this.$uiState$delegate, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|2|(1:(1:5)(2:6|7))(4:8|(2:18|(1:20))|28|29)|30|21|28|29) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if ((r3 instanceof java.util.concurrent.CancellationException) != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        throw r3;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) throws java.lang.Exception {
        /*
            r2 = this;
            int r0 = r2.label
            r1 = 1
            if (r0 == 0) goto L13
            if (r0 != r1) goto Lb
            k2.c.G(r3)
            goto L4f
        Lb:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r0)
            throw r3
        L13:
            k2.c.G(r3)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r2.$showControls$delegate
            boolean r3 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$32(r3)
            if (r3 == 0) goto L5c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r2.$hasPlaybackStarted$delegate
            boolean r3 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$26(r3)
            if (r3 == 0) goto L5c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r2.$showSubtitleMenu$delegate
            boolean r3 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$109(r3)
            if (r3 != 0) goto L5c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r2.$showSourceMenu$delegate
            boolean r3 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$112(r3)
            if (r3 != 0) goto L5c
            androidx.compose.runtime.State<com.arflix.tv.ui.screens.player.PlayerUiState> r3 = r2.$uiState$delegate
            com.arflix.tv.ui.screens.player.PlayerUiState r3 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$3(r3)
            java.lang.String r3 = r3.getError()
            if (r3 != 0) goto L5c
            r2.label = r1
            r0 = 300(0x12c, double:1.48E-321)
            java.lang.Object r3 = ka.s0.a(r0, r2)
            e7.a r0 = e7.a.f15033i
            if (r3 != r0) goto L4f
            return r0
        L4f:
            androidx.compose.ui.focus.FocusRequester r3 = r2.$playButtonFocusRequester     // Catch: java.lang.Exception -> L55
            r3.requestFocus()     // Catch: java.lang.Exception -> L55
            goto L5c
        L55:
            r3 = move-exception
            boolean r0 = r3 instanceof java.util.concurrent.CancellationException
            if (r0 != 0) goto L5b
            goto L5c
        L5b:
            throw r3
        L5c:
            x6.t0 r3 = x6.t0.f22605a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$34$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$34$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
