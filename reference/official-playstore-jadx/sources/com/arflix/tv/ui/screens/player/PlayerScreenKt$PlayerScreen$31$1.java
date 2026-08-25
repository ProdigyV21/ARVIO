package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.RemoteMediaPlayer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$31$1", f = "PlayerScreen.kt", l = {RemoteMediaPlayer.STATUS_CANCELED, CastStatusCodes.MEDIA_ERROR}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$31$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $containerFocusRequester;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ MutableState<Boolean> $isPlaying$delegate;
    final /* synthetic */ MutableState<Boolean> $showControls$delegate;
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleSettings$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$31$1(boolean z, FocusRequester focusRequester, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, d7.d<? super PlayerScreenKt$PlayerScreen$31$1> dVar) {
        super(2, dVar);
        this.$isCasting = z;
        this.$containerFocusRequester = focusRequester;
        this.$showControls$delegate = mutableState;
        this.$isPlaying$delegate = mutableState2;
        this.$showSubtitleMenu$delegate = mutableState3;
        this.$showSourceMenu$delegate = mutableState4;
        this.$showSubtitleSettings$delegate = mutableState5;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$31$1(this.$isCasting, this.$containerFocusRequester, this.$showControls$delegate, this.$isPlaying$delegate, this.$showSubtitleMenu$delegate, this.$showSourceMenu$delegate, this.$showSubtitleSettings$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
    
        if (ka.s0.a(100, r6) == r3) goto L27;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L1c
            if (r0 == r2) goto L18
            if (r0 != r1) goto L10
            k2.c.G(r7)
            goto L67
        L10:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L18:
            k2.c.G(r7)
            goto L56
        L1c:
            k2.c.G(r7)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showControls$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$32(r7)
            if (r7 == 0) goto L6c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$isPlaying$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$20(r7)
            if (r7 == 0) goto L6c
            boolean r7 = r6.$isCasting
            if (r7 != 0) goto L6c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showSubtitleMenu$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$109(r7)
            if (r7 != 0) goto L6c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showSourceMenu$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$112(r7)
            if (r7 != 0) goto L6c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showSubtitleSettings$delegate
            boolean r7 = com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$162(r7)
            if (r7 != 0) goto L6c
            r6.label = r2
            r4 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r7 = ka.s0.a(r4, r6)
            if (r7 != r3) goto L56
            goto L66
        L56:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$showControls$delegate
            r0 = 0
            com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$33(r7, r0)
            r6.label = r1
            r0 = 100
            java.lang.Object r7 = ka.s0.a(r0, r6)
            if (r7 != r3) goto L67
        L66:
            return r3
        L67:
            androidx.compose.ui.focus.FocusRequester r7 = r6.$containerFocusRequester     // Catch: java.lang.Exception -> L6c
            r7.requestFocus()     // Catch: java.lang.Exception -> L6c
        L6c:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$31$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$31$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
