package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$23$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$23$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Map<String, Integer> $filteredChannelIndexById;
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ MutableState<String> $guideScopeKey$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ String $initialChannelId;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ Map<String, String> $rememberedChannelByCategory;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$23$1(List<EnrichedChannel> list, Map<String, String> map, String str, Map<String, Integer> map2, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableLongState mutableLongState, d7.d<? super LiveTvScreenKt$LiveTvScreen$23$1> dVar) {
        super(2, dVar);
        this.$filteredChannels = list;
        this.$rememberedChannelByCategory = map;
        this.$initialChannelId = str;
        this.$filteredChannelIndexById = map2;
        this.$selectedProviderId$delegate = mutableState;
        this.$selectedCategoryId$delegate = mutableState2;
        this.$guideScopeKey$delegate = mutableState3;
        this.$focusedChannelId$delegate = mutableState4;
        this.$playingChannelId$delegate = mutableState5;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$23$1(this.$filteredChannels, this.$rememberedChannelByCategory, this.$initialChannelId, this.$filteredChannelIndexById, this.$selectedProviderId$delegate, this.$selectedCategoryId$delegate, this.$guideScopeKey$delegate, this.$focusedChannelId$delegate, this.$playingChannelId$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$lastGuideUserNavigationAt$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ca A[PHI: r7
      0x00ca: PHI (r7v22 java.lang.Integer) = (r7v18 java.lang.Integer), (r7v24 java.lang.Integer) binds: [B:37:0x00e1, B:31:0x00c8] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$23$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$23$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
