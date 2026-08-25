package com.arflix.tv.ui.screens.tv.live;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$13$1$index$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$13$1$index$1 extends f7.j implements r7.p<ka.k0, d7.d<? super LiveCategoryIndex>, Object> {
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ List<EnrichedChannel> $visibleChannels;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$13$1$index$1(List<EnrichedChannel> list, Set<String> set, d7.d<? super LiveTvScreenKt$LiveTvScreen$13$1$index$1> dVar) {
        super(2, dVar);
        this.$visibleChannels = list;
        this.$hiddenGroupSet = set;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$13$1$index$1(this.$visibleChannels, this.$hiddenGroupSet, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return LiveCategoryKt.buildCategoryIndex(this.$visibleChannels, this.$hiddenGroupSet);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super LiveCategoryIndex> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$13$1$index$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
