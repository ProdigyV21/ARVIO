package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1", f = "LiveTvScreen.kt", l = {1642}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $epgFocus;
    final /* synthetic */ MutableState<String> $epgPrefetchAnchorId$delegate;
    final /* synthetic */ Map<String, Integer> $filteredChannelIndexById;
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableIntState $focusSelectedChannelSignal$delegate;
    final /* synthetic */ MutableState<LiveTvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ Map<String, String> $rememberedChannelByCategory;
    final /* synthetic */ String $returnFocusChannelId;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1(String str, MutableState<String> mutableState, MutableState<String> mutableState2, MutableLongState mutableLongState, Map<String, String> map, Map<String, Integer> map2, List<EnrichedChannel> list, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, d7.d<? super LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1> dVar) {
        super(2, dVar);
        this.$returnFocusChannelId = str;
        this.$focusedChannelId$delegate = mutableState;
        this.$playingChannelId$delegate = mutableState2;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
        this.$rememberedChannelByCategory = map;
        this.$filteredChannelIndexById = map2;
        this.$filteredChannels = list;
        this.$epgPrefetchAnchorId$delegate = mutableState3;
        this.$selectedCategoryId$delegate = mutableState4;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
        this.$focusZone$delegate = mutableState5;
        this.$focusSelectedChannelSignal$delegate = mutableIntState3;
        this.$epgFocus = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1(this.$returnFocusChannelId, this.$focusedChannelId$delegate, this.$playingChannelId$delegate, this.$lastGuideUserNavigationAt$delegate, this.$rememberedChannelByCategory, this.$filteredChannelIndexById, this.$filteredChannels, this.$epgPrefetchAnchorId$delegate, this.$selectedCategoryId$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$focusZone$delegate, this.$focusSelectedChannelSignal$delegate, this.$epgFocus, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.label = 1;
            Object objA = ka.s0.a(16L, this);
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
        LiveTvScreenKt.LiveTvScreen$focusChannelList(this.$focusedChannelId$delegate, this.$playingChannelId$delegate, this.$lastGuideUserNavigationAt$delegate, this.$rememberedChannelByCategory, this.$filteredChannelIndexById, this.$filteredChannels, this.$epgPrefetchAnchorId$delegate, this.$selectedCategoryId$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$focusZone$delegate, this.$focusSelectedChannelSignal$delegate, this.$epgFocus, this.$returnFocusChannelId);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
