package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2", f = "PlayerScreen.kt", l = {1217}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    final /* synthetic */ ExoPlayer $player;
    final /* synthetic */ AtomicBoolean $playerReleasedAtomic;
    final /* synthetic */ boolean $wasPlaying;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2(AtomicBoolean atomicBoolean, MutableState<Boolean> mutableState, State<PlayerUiState> state, ExoPlayer exoPlayer, boolean z, d7.d<? super PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2> dVar) {
        super(2, dVar);
        this.$playerReleasedAtomic = atomicBoolean;
        this.$hasPlaybackStarted$delegate = mutableState;
        this.$latestUiState$delegate = state;
        this.$player = exoPlayer;
        this.$wasPlaying = z;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2 playerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2 = new PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2(this.$playerReleasedAtomic, this.$hasPlaybackStarted$delegate, this.$latestUiState$delegate, this.$player, this.$wasPlaying, dVar);
        playerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2.L$0 = obj;
        return playerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.L$0 = k0Var;
            this.label = 1;
            Object objA = ka.s0.a(650L, this);
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
        if (!this.$playerReleasedAtomic.get() && !PlayerScreenKt.PlayerScreen$lambda$26(this.$hasPlaybackStarted$delegate) && PlayerScreenKt.PlayerScreen$lambda$4(this.$latestUiState$delegate).getSelectedStreamUrl() != null) {
            ExoPlayer exoPlayer = this.$player;
            boolean z = this.$wasPlaying;
            try {
                exoPlayer.stop();
                exoPlayer.prepare();
                exoPlayer.setPlayWhenReady(z);
            } catch (Throwable unused) {
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$exoPlayer$1$2$1$onPlayerError$2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
