package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.cast.CastManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$18$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$18$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ CastManager $castManager;
    final /* synthetic */ State<CastManager.CastState> $castState$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Long> $lastCastPositionMs$delegate;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerScreenKt$PlayerScreen$18$1(ExoPlayer exoPlayer, CastManager castManager, State<? extends CastManager.CastState> state, State<PlayerUiState> state2, MutableState<Boolean> mutableState, MutableState<Long> mutableState2, d7.d<? super PlayerScreenKt$PlayerScreen$18$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$castManager = castManager;
        this.$castState$delegate = state;
        this.$uiState$delegate = state2;
        this.$playerReleased$delegate = mutableState;
        this.$lastCastPositionMs$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$18$1(this.$exoPlayer, this.$castManager, this.$castState$delegate, this.$uiState$delegate, this.$playerReleased$delegate, this.$lastCastPositionMs$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        CastManager.CastState castStatePlayerScreen$lambda$6 = PlayerScreenKt.PlayerScreen$lambda$6(this.$castState$delegate);
        boolean z = castStatePlayerScreen$lambda$6 instanceof CastManager.CastState.Casting;
        x6.t0 t0Var = x6.t0.f22605a;
        if (z) {
            String selectedStreamUrl = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSelectedStreamUrl();
            if (selectedStreamUrl == null) {
                return t0Var;
            }
            long currentPosition = PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate) ? 0L : this.$exoPlayer.getCurrentPosition();
            if (!PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate)) {
                this.$exoPlayer.pause();
            }
            this.$castManager.loadMedia(selectedStreamUrl, PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getTitle(), PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getBackdropUrl(), PlayerScreenKt.guessCastMimeType(selectedStreamUrl), currentPosition);
        } else if (castStatePlayerScreen$lambda$6 instanceof CastManager.CastState.NotConnected) {
            long jPlayerScreen$lambda$322 = PlayerScreenKt.PlayerScreen$lambda$322(this.$lastCastPositionMs$delegate);
            if (!PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate) && jPlayerScreen$lambda$322 > 0 && !this.$exoPlayer.isPlaying()) {
                this.$exoPlayer.seekTo(jPlayerScreen$lambda$322);
                this.$exoPlayer.play();
            }
            PlayerScreenKt.PlayerScreen$lambda$323(this.$lastCastPositionMs$delegate, 0L);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$18$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
