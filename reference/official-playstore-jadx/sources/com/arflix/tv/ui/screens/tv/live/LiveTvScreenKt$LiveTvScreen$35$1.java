package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.IptvProgram;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$35$1", f = "LiveTvScreen.kt", l = {1771}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$35$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableLongState $catchupPlaybackOffsetMs$delegate;
    final /* synthetic */ MutableState<String> $channelNumberBuffer$delegate;
    final /* synthetic */ FocusRequester $epgFocus;
    final /* synthetic */ MutableState<String> $epgPrefetchAnchorId$delegate;
    final /* synthetic */ Map<String, Integer> $filteredChannelIndexById;
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableIntState $focusSelectedChannelSignal$delegate;
    final /* synthetic */ MutableState<LiveTvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ MutableState<Boolean> $fullscreenGuideOpen$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ MutableState<Integer> $hudPokeSignal$delegate;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<IptvProgram> $playingCatchupProgram$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ Map<String, String> $rememberedChannelByCategory;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$35$1(MutableState<EnrichedChannels> mutableState, MutableState<String> mutableState2, Map<String, String> map, MutableLongState mutableLongState, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<IptvProgram> mutableState6, MutableLongState mutableLongState2, MutableState<Boolean> mutableState7, MutableState<String> mutableState8, Map<String, Integer> map2, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState9, MutableIntState mutableIntState3, FocusRequester focusRequester, MutableState<Integer> mutableState10, d7.d<? super LiveTvScreenKt$LiveTvScreen$35$1> dVar) {
        super(2, dVar);
        this.$visibleEnrichedState = mutableState;
        this.$channelNumberBuffer$delegate = mutableState2;
        this.$rememberedChannelByCategory = map;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
        this.$playingChannelId$delegate = mutableState3;
        this.$focusedChannelId$delegate = mutableState4;
        this.$epgPrefetchAnchorId$delegate = mutableState5;
        this.$playingCatchupProgram$delegate = mutableState6;
        this.$catchupPlaybackOffsetMs$delegate = mutableLongState2;
        this.$fullscreenGuideOpen$delegate = mutableState7;
        this.$selectedCategoryId$delegate = mutableState8;
        this.$filteredChannelIndexById = map2;
        this.$filteredChannels = list;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
        this.$focusZone$delegate = mutableState9;
        this.$focusSelectedChannelSignal$delegate = mutableIntState3;
        this.$epgFocus = focusRequester;
        this.$hudPokeSignal$delegate = mutableState10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$35$1(this.$visibleEnrichedState, this.$channelNumberBuffer$delegate, this.$rememberedChannelByCategory, this.$lastGuideUserNavigationAt$delegate, this.$playingChannelId$delegate, this.$focusedChannelId$delegate, this.$epgPrefetchAnchorId$delegate, this.$playingCatchupProgram$delegate, this.$catchupPlaybackOffsetMs$delegate, this.$fullscreenGuideOpen$delegate, this.$selectedCategoryId$delegate, this.$filteredChannelIndexById, this.$filteredChannels, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$focusZone$delegate, this.$focusSelectedChannelSignal$delegate, this.$epgFocus, this.$hudPokeSignal$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String strLiveTvScreen$lambda$182;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            strLiveTvScreen$lambda$182 = LiveTvScreenKt.LiveTvScreen$lambda$182(this.$channelNumberBuffer$delegate);
            if (!kotlin.text.o.h0(strLiveTvScreen$lambda$182)) {
                this.L$0 = strLiveTvScreen$lambda$182;
                this.label = 1;
                Object objA = ka.s0.a(1200L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        strLiveTvScreen$lambda$182 = (String) this.L$0;
        k2.c.G(obj);
        if (kotlin.jvm.internal.p.a(LiveTvScreenKt.LiveTvScreen$lambda$182(this.$channelNumberBuffer$delegate), strLiveTvScreen$lambda$182)) {
            List<EnrichedChannel> all = this.$visibleEnrichedState.getValue().getAll();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : all) {
                if (kotlin.text.u.P(String.valueOf(((EnrichedChannel) obj2).getNumber()), strLiveTvScreen$lambda$182, false)) {
                    arrayList.add(obj2);
                }
            }
            EnrichedChannel enrichedChannel = (EnrichedChannel) kotlin.collections.x.T0(kotlin.collections.x.X0(arrayList, 2));
            if (enrichedChannel != null) {
                LiveTvScreenKt.LiveTvScreen$tuneChannelNumber(this.$rememberedChannelByCategory, this.$lastGuideUserNavigationAt$delegate, this.$playingChannelId$delegate, this.$focusedChannelId$delegate, this.$epgPrefetchAnchorId$delegate, this.$playingCatchupProgram$delegate, this.$catchupPlaybackOffsetMs$delegate, this.$fullscreenGuideOpen$delegate, this.$selectedCategoryId$delegate, this.$filteredChannelIndexById, this.$filteredChannels, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$focusZone$delegate, this.$focusSelectedChannelSignal$delegate, this.$epgFocus, this.$hudPokeSignal$delegate, enrichedChannel);
            }
            this.$channelNumberBuffer$delegate.setValue("");
            return t0Var;
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$35$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
