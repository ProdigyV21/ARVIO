package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$42$1", f = "PlayerScreen.kt", l = {2570, 2576}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$42$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $containerFocusRequester;
    final /* synthetic */ MutableState<Boolean> $showControls$delegate;
    final /* synthetic */ MutableState<Boolean> $showNextEpisodePrompt$delegate;
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$42$1(FocusRequester focusRequester, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$42$1> dVar) {
        super(2, dVar);
        this.$containerFocusRequester = focusRequester;
        this.$showControls$delegate = mutableState;
        this.$showSubtitleMenu$delegate = mutableState2;
        this.$showSourceMenu$delegate = mutableState3;
        this.$showNextEpisodePrompt$delegate = mutableState4;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$42$1(this.$containerFocusRequester, this.$showControls$delegate, this.$showSubtitleMenu$delegate, this.$showSourceMenu$delegate, this.$showNextEpisodePrompt$delegate, this.$uiState$delegate, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(1:(1:(5:6|36|29|30|31)(2:7|8))(1:9))(3:10|(4:24|(1:26)|30|31)(1:20)|28)|34|23|24|(0)|30|31) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
    
        if (ka.s0.a(100, r6) == r5) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
    
        if (ka.s0.a(100, r6) == r5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 100
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r0 == 0) goto L1e
            if (r0 == r4) goto L1a
            if (r0 != r3) goto L12
            k2.c.G(r7)
            goto L70
        L12:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1a:
            k2.c.G(r7)
            goto L56
        L1e:
            k2.c.G(r7)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showControls$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$32(r7)
            if (r7 != 0) goto L5b
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showSubtitleMenu$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$109(r7)
            if (r7 != 0) goto L5b
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showSourceMenu$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$112(r7)
            if (r7 != 0) goto L5b
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showNextEpisodePrompt$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$115(r7)
            if (r7 != 0) goto L5b
            androidx.compose.runtime.State<com.arflix.tv.ui.screens.player.PlayerUiState> r7 = r6.$uiState$delegate
            com.arflix.tv.ui.screens.player.PlayerUiState r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$3(r7)
            java.lang.String r7 = r7.getError()
            if (r7 != 0) goto L5b
            r6.label = r4
            java.lang.Object r7 = ka.s0.a(r1, r6)
            if (r7 != r5) goto L56
            goto L6f
        L56:
            androidx.compose.ui.focus.FocusRequester r7 = r6.$containerFocusRequester     // Catch: java.lang.Exception -> L5b
            r7.requestFocus()     // Catch: java.lang.Exception -> L5b
        L5b:
            androidx.compose.runtime.State<com.arflix.tv.ui.screens.player.PlayerUiState> r7 = r6.$uiState$delegate
            com.arflix.tv.ui.screens.player.PlayerUiState r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$3(r7)
            java.lang.String r7 = r7.getError()
            if (r7 == 0) goto L75
            r6.label = r3
            java.lang.Object r7 = ka.s0.a(r1, r6)
            if (r7 != r5) goto L70
        L6f:
            return r5
        L70:
            androidx.compose.ui.focus.FocusRequester r7 = r6.$containerFocusRequester     // Catch: java.lang.Exception -> L75
            r7.requestFocus()     // Catch: java.lang.Exception -> L75
        L75:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$42$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$42$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
