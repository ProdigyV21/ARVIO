package com.arflix.tv.ui.screens.tv;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$28$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$28$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<ExoPlayer> $exoPlayer$delegate;
    final /* synthetic */ String $initialChannelId;
    final /* synthetic */ String $initialStreamUrl;
    final /* synthetic */ DefaultMediaSourceFactory $iptvDefaultFactory;
    final /* synthetic */ HlsMediaSource.Factory $iptvHlsFactory;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<Boolean> $isPlayerReleased$delegate;
    final /* synthetic */ MutableState<String> $lastPreparedStreamUrl$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$28$1(String str, String str2, Context context, DefaultMediaSourceFactory defaultMediaSourceFactory, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, MutableState<ExoPlayer> mutableState4, HlsMediaSource.Factory factory, MutableState<Boolean> mutableState5, d7.d<? super TvScreenKt$TvScreen$28$1> dVar) {
        super(2, dVar);
        this.$initialStreamUrl = str;
        this.$initialChannelId = str2;
        this.$context = context;
        this.$iptvDefaultFactory = defaultMediaSourceFactory;
        this.$playingChannelId$delegate = mutableState;
        this.$isFullScreen$delegate = mutableState2;
        this.$lastPreparedStreamUrl$delegate = mutableState3;
        this.$exoPlayer$delegate = mutableState4;
        this.$iptvHlsFactory = factory;
        this.$isPlayerReleased$delegate = mutableState5;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$28$1(this.$initialStreamUrl, this.$initialChannelId, this.$context, this.$iptvDefaultFactory, this.$playingChannelId$delegate, this.$isFullScreen$delegate, this.$lastPreparedStreamUrl$delegate, this.$exoPlayer$delegate, this.$iptvHlsFactory, this.$isPlayerReleased$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$initialStreamUrl != null && (str = this.$initialChannelId) != null) {
            this.$playingChannelId$delegate.setValue(str);
            TvScreenKt.TvScreen$lambda$30(this.$isFullScreen$delegate, true);
            this.$lastPreparedStreamUrl$delegate.setValue(this.$initialStreamUrl);
            if (TvScreenKt.TvScreen$lambda$94(this.$exoPlayer$delegate) == null) {
                this.$exoPlayer$delegate.setValue(TvScreenKt.createTvExoPlayer(this.$context, this.$iptvDefaultFactory));
            }
            TvScreenKt.TvScreen$prepareStream$default(this.$iptvHlsFactory, this.$exoPlayer$delegate, this.$isPlayerReleased$delegate, this.$initialStreamUrl, null, 16, null);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$28$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
