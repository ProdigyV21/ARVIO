package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$20$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$20$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$20$1(ExoPlayer exoPlayer, MutableState<Boolean> mutableState, State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$20$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$playerReleased$delegate = mutableState;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$20$1(this.$exoPlayer, this.$playerReleased$delegate, this.$uiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zPlayerScreen$lambda$274 = PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if (zPlayerScreen$lambda$274) {
            return t0Var;
        }
        TrackSelector trackSelector = this.$exoPlayer.getTrackSelector();
        String str = null;
        DefaultTrackSelector defaultTrackSelector = trackSelector instanceof DefaultTrackSelector ? (DefaultTrackSelector) trackSelector : null;
        if (defaultTrackSelector != null) {
            DefaultTrackSelector.Parameters.Builder builderBuildUponParameters = defaultTrackSelector.buildUponParameters();
            String preferredAudioLanguage = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getPreferredAudioLanguage();
            if (!kotlin.text.o.h0(preferredAudioLanguage) && !kotlin.text.u.L(preferredAudioLanguage, "none", true)) {
                str = preferredAudioLanguage;
            }
            defaultTrackSelector.setParameters(builderBuildUponParameters.setPreferredAudioLanguage(str).build());
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$20$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
