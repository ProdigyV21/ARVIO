package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$30$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$30$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<ExoPlayer> $exoPlayer$delegate;
    final /* synthetic */ MutableState<PlayerView> $fullPlayerView$delegate;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<Boolean> $isPlayerReleased$delegate;
    final /* synthetic */ MutableState<PlayerView> $miniPlayerView$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$30$1(MutableState<ExoPlayer> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<PlayerView> mutableState4, MutableState<PlayerView> mutableState5, d7.d<? super TvScreenKt$TvScreen$30$1> dVar) {
        super(2, dVar);
        this.$exoPlayer$delegate = mutableState;
        this.$isPlayerReleased$delegate = mutableState2;
        this.$isFullScreen$delegate = mutableState3;
        this.$miniPlayerView$delegate = mutableState4;
        this.$fullPlayerView$delegate = mutableState5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(PlayerView playerView, ExoPlayer exoPlayer, MutableState mutableState) {
        if (TvScreenKt.TvScreen$lambda$91(mutableState)) {
            return;
        }
        playerView.setResizeMode(0);
        playerView.setPlayer(exoPlayer);
        playerView.requestLayout();
        playerView.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(PlayerView playerView, ExoPlayer exoPlayer, MutableState mutableState) {
        if (TvScreenKt.TvScreen$lambda$91(mutableState)) {
            return;
        }
        playerView.setResizeMode(0);
        playerView.setPlayer(exoPlayer);
        playerView.requestLayout();
        playerView.invalidate();
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$30$1(this.$exoPlayer$delegate, this.$isPlayerReleased$delegate, this.$isFullScreen$delegate, this.$miniPlayerView$delegate, this.$fullPlayerView$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        final ExoPlayer exoPlayerTvScreen$lambda$94 = TvScreenKt.TvScreen$lambda$94(this.$exoPlayer$delegate);
        t0 t0Var = t0.f22605a;
        if (exoPlayerTvScreen$lambda$94 != null && !TvScreenKt.TvScreen$lambda$91(this.$isPlayerReleased$delegate)) {
            if (TvScreenKt.TvScreen$lambda$29(this.$isFullScreen$delegate)) {
                PlayerView playerViewTvScreen$lambda$97 = TvScreenKt.TvScreen$lambda$97(this.$miniPlayerView$delegate);
                if (playerViewTvScreen$lambda$97 != null) {
                    playerViewTvScreen$lambda$97.setPlayer(null);
                }
                final PlayerView playerViewTvScreen$lambda$100 = TvScreenKt.TvScreen$lambda$100(this.$fullPlayerView$delegate);
                if (playerViewTvScreen$lambda$100 != null) {
                    final MutableState<Boolean> mutableState = this.$isPlayerReleased$delegate;
                    final int i10 = 0;
                    playerViewTvScreen$lambda$100.postDelayed(new Runnable() { // from class: com.arflix.tv.ui.screens.tv.d0
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i10) {
                                case 0:
                                    TvScreenKt$TvScreen$30$1.invokeSuspend$lambda$0(playerViewTvScreen$lambda$100, exoPlayerTvScreen$lambda$94, mutableState);
                                    break;
                                default:
                                    TvScreenKt$TvScreen$30$1.invokeSuspend$lambda$1(playerViewTvScreen$lambda$100, exoPlayerTvScreen$lambda$94, mutableState);
                                    break;
                            }
                        }
                    }, 50L);
                    return t0Var;
                }
            } else {
                PlayerView playerViewTvScreen$lambda$1002 = TvScreenKt.TvScreen$lambda$100(this.$fullPlayerView$delegate);
                if (playerViewTvScreen$lambda$1002 != null) {
                    playerViewTvScreen$lambda$1002.setPlayer(null);
                }
                final PlayerView playerViewTvScreen$lambda$972 = TvScreenKt.TvScreen$lambda$97(this.$miniPlayerView$delegate);
                if (playerViewTvScreen$lambda$972 != null) {
                    final MutableState<Boolean> mutableState2 = this.$isPlayerReleased$delegate;
                    final int i11 = 1;
                    playerViewTvScreen$lambda$972.postDelayed(new Runnable() { // from class: com.arflix.tv.ui.screens.tv.d0
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i11) {
                                case 0:
                                    TvScreenKt$TvScreen$30$1.invokeSuspend$lambda$0(playerViewTvScreen$lambda$972, exoPlayerTvScreen$lambda$94, mutableState2);
                                    break;
                                default:
                                    TvScreenKt$TvScreen$30$1.invokeSuspend$lambda$1(playerViewTvScreen$lambda$972, exoPlayerTvScreen$lambda$94, mutableState2);
                                    break;
                            }
                        }
                    }, 50L);
                    return t0Var;
                }
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$30$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
