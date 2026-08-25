package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$41$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$41$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$41$1(State<PlayerUiState> state, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, d7.d<? super PlayerScreenKt$PlayerScreen$41$1> dVar) {
        super(2, dVar);
        this.$uiState$delegate = state;
        this.$showSourceMenu$delegate = mutableState;
        this.$showSubtitleMenu$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$41$1(this.$uiState$delegate, this.$showSourceMenu$delegate, this.$showSubtitleMenu$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getError() != null) {
            PlayerScreenKt.PlayerScreen$lambda$113(this.$showSourceMenu$delegate, false);
            PlayerScreenKt.PlayerScreen$lambda$110(this.$showSubtitleMenu$delegate, false);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$41$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
