package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$22$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$22$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<List<AudioTrackInfo>> $audioTracks$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ MutableIntState $selectedAudioIndex$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    final /* synthetic */ MutableState<Boolean> $userPickedAudioForStream$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$22$1(MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<List<AudioTrackInfo>> mutableState3, State<PlayerUiState> state, MutableIntState mutableIntState, ExoPlayer exoPlayer, d7.d<? super PlayerScreenKt$PlayerScreen$22$1> dVar) {
        super(2, dVar);
        this.$playerReleased$delegate = mutableState;
        this.$userPickedAudioForStream$delegate = mutableState2;
        this.$audioTracks$delegate = mutableState3;
        this.$uiState$delegate = state;
        this.$selectedAudioIndex$delegate = mutableIntState;
        this.$exoPlayer = exoPlayer;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$22$1(this.$playerReleased$delegate, this.$userPickedAudioForStream$delegate, this.$audioTracks$delegate, this.$uiState$delegate, this.$selectedAudioIndex$delegate, this.$exoPlayer, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        Integer numFindPreferredAudioTrackIndex;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zPlayerScreen$lambda$274 = PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if (zPlayerScreen$lambda$274 || PlayerScreenKt.PlayerScreen$lambda$187(this.$userPickedAudioForStream$delegate) || PlayerScreenKt.PlayerScreen$lambda$181(this.$audioTracks$delegate).size() < 2) {
            return t0Var;
        }
        String string = kotlin.text.o.L0(PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getPreferredAudioLanguage()).toString();
        if (!kotlin.text.o.h0(string) && !kotlin.text.u.L(string, "none", true) && (numFindPreferredAudioTrackIndex = PlayerScreenKt.findPreferredAudioTrackIndex(PlayerScreenKt.PlayerScreen$lambda$181(this.$audioTracks$delegate), string)) != null) {
            if (numFindPreferredAudioTrackIndex.intValue() != this.$selectedAudioIndex$delegate.getIntValue()) {
                AudioTrackInfo audioTrackInfo = (AudioTrackInfo) kotlin.collections.x.p0(numFindPreferredAudioTrackIndex.intValue(), PlayerScreenKt.PlayerScreen$lambda$181(this.$audioTracks$delegate));
                if (audioTrackInfo != null) {
                    ExoPlayer exoPlayer = this.$exoPlayer;
                    MutableState<List<AudioTrackInfo>> mutableState = this.$audioTracks$delegate;
                    MutableIntState mutableIntState = this.$selectedAudioIndex$delegate;
                    Integer numApplyAudioTrackSelection = PlayerScreenKt.applyAudioTrackSelection(exoPlayer, audioTrackInfo, PlayerScreenKt.PlayerScreen$lambda$181(mutableState));
                    if (numApplyAudioTrackSelection != null) {
                        mutableIntState.setIntValue(numApplyAudioTrackSelection.intValue());
                    }
                }
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$22$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
