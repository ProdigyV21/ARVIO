package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class LiveTvScreenKt$LiveTvScreen$49$3$11$17$1 extends kotlin.jvm.internal.m implements r7.a<x6.t0> {
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$49$3$11$17$1(MutableIntState mutableIntState, MutableIntState mutableIntState2, List<EnrichedChannel> list) {
        super(0, kotlin.jvm.internal.o.class, "requestGuideWindowBefore", "LiveTvScreen$requestGuideWindowBefore(Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableIntState;Ljava/util/List;)V", 0);
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
        this.$filteredChannels = list;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m6442invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m6442invoke() {
        LiveTvScreenKt.LiveTvScreen$requestGuideWindowBefore(this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, this.$filteredChannels);
    }
}
