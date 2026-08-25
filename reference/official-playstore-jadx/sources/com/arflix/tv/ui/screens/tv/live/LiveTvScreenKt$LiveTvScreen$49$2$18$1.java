package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class LiveTvScreenKt$LiveTvScreen$49$2$18$1 extends kotlin.jvm.internal.m implements r7.a<x6.t0> {
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    final /* synthetic */ MutableIntState $pagedLoadedLimit$delegate;
    final /* synthetic */ int $selectedCategoryTotalCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$49$2$18$1(int i10, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3) {
        super(0, kotlin.jvm.internal.o.class, "requestGuideWindowAfter", "LiveTvScreen$requestGuideWindowAfter(ILjava/util/List;Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableIntState;)V", 0);
        this.$selectedCategoryTotalCount = i10;
        this.$filteredChannels = list;
        this.$guideWindowEnd$delegate = mutableIntState;
        this.$pagedLoadedLimit$delegate = mutableIntState2;
        this.$guideWindowStart$delegate = mutableIntState3;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m6441invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m6441invoke() {
        LiveTvScreenKt.LiveTvScreen$requestGuideWindowAfter(this.$selectedCategoryTotalCount, this.$filteredChannels, this.$guideWindowEnd$delegate, this.$pagedLoadedLimit$delegate, this.$guideWindowStart$delegate);
    }
}
