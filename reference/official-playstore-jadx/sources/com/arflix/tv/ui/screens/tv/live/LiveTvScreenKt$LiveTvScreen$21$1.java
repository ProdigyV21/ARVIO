package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$21$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$21$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ EnrichedChannel $indexedPlayingChannel;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<EnrichedChannel> $retainedPlayingChannel$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$21$1(EnrichedChannel enrichedChannel, MutableState<String> mutableState, MutableState<EnrichedChannel> mutableState2, d7.d<? super LiveTvScreenKt$LiveTvScreen$21$1> dVar) {
        super(2, dVar);
        this.$indexedPlayingChannel = enrichedChannel;
        this.$playingChannelId$delegate = mutableState;
        this.$retainedPlayingChannel$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$21$1(this.$indexedPlayingChannel, this.$playingChannelId$delegate, this.$retainedPlayingChannel$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        MutableState<EnrichedChannel> mutableState = this.$retainedPlayingChannel$delegate;
        EnrichedChannel enrichedChannelLiveTvScreen$lambda$88 = null;
        if (LiveTvScreenKt.LiveTvScreen$lambda$63(this.$playingChannelId$delegate) != null) {
            EnrichedChannel enrichedChannel = this.$indexedPlayingChannel;
            if (enrichedChannel != null) {
                enrichedChannelLiveTvScreen$lambda$88 = enrichedChannel;
            } else {
                EnrichedChannel enrichedChannelLiveTvScreen$lambda$882 = LiveTvScreenKt.LiveTvScreen$lambda$88(this.$retainedPlayingChannel$delegate);
                enrichedChannelLiveTvScreen$lambda$88 = kotlin.jvm.internal.p.a(enrichedChannelLiveTvScreen$lambda$882 != null ? enrichedChannelLiveTvScreen$lambda$882.getId() : null, LiveTvScreenKt.LiveTvScreen$lambda$63(this.$playingChannelId$delegate)) ? LiveTvScreenKt.LiveTvScreen$lambda$88(this.$retainedPlayingChannel$delegate) : LiveTvScreenKt.LiveTvScreen$lambda$88(this.$retainedPlayingChannel$delegate);
            }
        }
        mutableState.setValue(enrichedChannelLiveTvScreen$lambda$88);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$21$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
