package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$24$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$24$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Map<String, Integer> $filteredChannelIndexById;
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableState<LiveTvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$24$1(Map<String, Integer> map, List<EnrichedChannel> list, MutableLongState mutableLongState, MutableState<LiveTvFocusZone> mutableState, MutableState<String> mutableState2, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super LiveTvScreenKt$LiveTvScreen$24$1> dVar) {
        super(2, dVar);
        this.$filteredChannelIndexById = map;
        this.$filteredChannels = list;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
        this.$focusZone$delegate = mutableState;
        this.$playingChannelId$delegate = mutableState2;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$24$1(this.$filteredChannelIndexById, this.$filteredChannels, this.$lastGuideUserNavigationAt$delegate, this.$focusZone$delegate, this.$playingChannelId$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String strLiveTvScreen$lambda$63;
        Integer num;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zLiveTvScreen$isGuideUserNavigating = LiveTvScreenKt.LiveTvScreen$isGuideUserNavigating(this.$lastGuideUserNavigationAt$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if ((!zLiveTvScreen$isGuideUserNavigating || (LiveTvScreenKt.LiveTvScreen$lambda$41(this.$focusZone$delegate) != LiveTvFocusZone.CHANNEL_LIST && LiveTvScreenKt.LiveTvScreen$lambda$41(this.$focusZone$delegate) != LiveTvFocusZone.EPG)) && (strLiveTvScreen$lambda$63 = LiveTvScreenKt.LiveTvScreen$lambda$63(this.$playingChannelId$delegate)) != null && (num = this.$filteredChannelIndexById.get(strLiveTvScreen$lambda$63)) != null) {
            int iIntValue = num.intValue();
            int intValue = this.$guideWindowStart$delegate.getIntValue();
            if (iIntValue >= this.$guideWindowEnd$delegate.getIntValue() || intValue > iIntValue) {
                List<EnrichedChannel> list = this.$filteredChannels;
                LiveTvScreenKt.LiveTvScreen$setGuideWindow(list, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, LiveTvScreenKt.guideWindowAround(iIntValue, list.size()));
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$24$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
