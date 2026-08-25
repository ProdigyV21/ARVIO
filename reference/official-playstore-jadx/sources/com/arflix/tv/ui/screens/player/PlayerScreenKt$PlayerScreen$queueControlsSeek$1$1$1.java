package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1", f = "PlayerScreen.kt", l = {1489}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $isControlScrubbing$delegate;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ MutableLongState $scrubPreviewPosition$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1(ExoPlayer exoPlayer, MutableState<Boolean> mutableState, MutableLongState mutableLongState, MutableState<Boolean> mutableState2, d7.d<? super PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$playerReleased$delegate = mutableState;
        this.$scrubPreviewPosition$delegate = mutableLongState;
        this.$isControlScrubbing$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1(this.$exoPlayer, this.$playerReleased$delegate, this.$scrubPreviewPosition$delegate, this.$isControlScrubbing$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.label = 1;
            Object objA = ka.s0.a(260L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        if (!PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate)) {
            this.$exoPlayer.seekTo(this.$scrubPreviewPosition$delegate.getLongValue());
        }
        PlayerScreenKt.PlayerScreen$lambda$66(this.$isControlScrubbing$delegate, false);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
