package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$30$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$30$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $epgPrefetchAnchorId$delegate;
    final /* synthetic */ Map<String, Integer> $filteredChannelIndexById;
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ String $initialChannelId;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ Map<String, String> $rememberedChannelByCategory;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    final /* synthetic */ MutableState<Boolean> $startupChannelApplied$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ Map<String, List<EnrichedChannel>> $variantGroups;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LiveTvScreenKt$LiveTvScreen$30$1(List<EnrichedChannel> list, String str, Map<String, Integer> map, MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map2, Map<String, String> map3, State<TvUiState> state, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableLongState mutableLongState, d7.d<? super LiveTvScreenKt$LiveTvScreen$30$1> dVar) {
        super(2, dVar);
        this.$filteredChannels = list;
        this.$initialChannelId = str;
        this.$filteredChannelIndexById = map;
        this.$visibleEnrichedState = mutableState;
        this.$variantGroups = map2;
        this.$rememberedChannelByCategory = map3;
        this.$state$delegate = state;
        this.$playingChannelId$delegate = mutableState2;
        this.$startupChannelApplied$delegate = mutableState3;
        this.$selectedProviderId$delegate = mutableState4;
        this.$focusedChannelId$delegate = mutableState5;
        this.$epgPrefetchAnchorId$delegate = mutableState6;
        this.$selectedCategoryId$delegate = mutableState7;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$30$1(this.$filteredChannels, this.$initialChannelId, this.$filteredChannelIndexById, this.$visibleEnrichedState, this.$variantGroups, this.$rememberedChannelByCategory, this.$state$delegate, this.$playingChannelId$delegate, this.$startupChannelApplied$delegate, this.$selectedProviderId$delegate, this.$focusedChannelId$delegate, this.$epgPrefetchAnchorId$delegate, this.$selectedCategoryId$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$lastGuideUserNavigationAt$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0230  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 599
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$30$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$30$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
