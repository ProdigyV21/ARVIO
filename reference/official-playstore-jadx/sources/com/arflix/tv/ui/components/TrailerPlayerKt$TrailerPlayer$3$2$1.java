package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MergingMediaSource;
import com.arflix.tv.data.api.YoutubeChunkedDataSourceFactory;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$3$2$1", f = "TrailerPlayer.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TrailerPlayerKt$TrailerPlayer$3$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $audioUrl$delegate;
    final /* synthetic */ ExoPlayer $player;
    final /* synthetic */ MutableState<String> $videoUrl$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrailerPlayerKt$TrailerPlayer$3$2$1(ExoPlayer exoPlayer, MutableState<String> mutableState, MutableState<String> mutableState2, d7.d<? super TrailerPlayerKt$TrailerPlayer$3$2$1> dVar) {
        super(2, dVar);
        this.$player = exoPlayer;
        this.$videoUrl$delegate = mutableState;
        this.$audioUrl$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new TrailerPlayerKt$TrailerPlayer$3$2$1(this.$player, this.$videoUrl$delegate, this.$audioUrl$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String strTrailerPlayer$lambda$6 = TrailerPlayerKt.TrailerPlayer$lambda$6(this.$videoUrl$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if (strTrailerPlayer$lambda$6 == null) {
            return t0Var;
        }
        String strTrailerPlayer$lambda$9 = TrailerPlayerKt.TrailerPlayer$lambda$9(this.$audioUrl$delegate);
        if (strTrailerPlayer$lambda$9 == null || kotlin.text.o.h0(strTrailerPlayer$lambda$9)) {
            this.$player.setMediaItem(MediaItem.fromUri(strTrailerPlayer$lambda$6));
        } else {
            DefaultMediaSourceFactory defaultMediaSourceFactory = new DefaultMediaSourceFactory(new YoutubeChunkedDataSourceFactory(0L, 1, null));
            this.$player.setMediaSource(new MergingMediaSource(defaultMediaSourceFactory.createMediaSource(MediaItem.fromUri(strTrailerPlayer$lambda$6)), defaultMediaSourceFactory.createMediaSource(MediaItem.fromUri(TrailerPlayerKt.TrailerPlayer$lambda$9(this.$audioUrl$delegate)))));
        }
        this.$player.prepare();
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((TrailerPlayerKt$TrailerPlayer$3$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
